#include "CV_Main.h"

using namespace std;
using namespace cv;

void convertYUV_I420toNV12(unsigned char* i420bytes, unsigned char* nv12bytes, int width, int height){
    int nLenY = width * height;
    int nLenU = nLenY / 4;
    memcpy(nv12bytes, i420bytes, width * height);
    for (int i = 0; i < nLenU; i++) {
        nv12bytes[nLenY + 2 * i] = i420bytes[nLenY + i];             // U
        nv12bytes[nLenY + 2 * i + 1] = i420bytes[nLenY + nLenU + i]; // V
    }
}

void BGR2YUV_nv12(cv::Mat &src, cv::Mat &dst){
    int w_img = src.cols;
    int h_img = src.rows;
    dst = cv::Mat(h_img*1.5, w_img, CV_8UC1, cv::Scalar(0));
    cv::Mat YUV_I420(h_img*1.5, w_img, CV_8UC1, cv::Scalar(0));  //YUV_I420
    cv::cvtColor(src, YUV_I420, cv::COLOR_BGR2YUV_I420);
    convertYUV_I420toNV12(YUV_I420.data, dst.data, w_img, h_img);
}

CV_Main::CV_Main() : m_camera_ready(false), m_image(nullptr), m_image_reader(nullptr), m_native_camera(nullptr) { };

CV_Main::~CV_Main() {
    // ACameraCaptureSession_stopRepeating(m_capture_session);
    if (m_native_camera != nullptr) {
        delete m_native_camera;
        m_native_camera = nullptr;
    }

    // make sure we don't leak native windows
    if (m_native_window != nullptr) {
        ANativeWindow_release(m_native_window);
        m_native_window = nullptr;
    }

    if (m_image_reader != nullptr) {
        delete (m_image_reader);
        m_image_reader = nullptr;
    }
}

void CV_Main::SetNativeWindow(ANativeWindow *native_window) {
    // Save native window
    m_native_window = native_window;
}

void CV_Main::SetUpCamera(bool switchCamera) {
    if(switchCamera){
        if(m_selected_camera_type==FRONT_CAMERA){
            m_selected_camera_type=BACK_CAMERA;
        }else if(m_selected_camera_type==BACK_CAMERA){
            m_selected_camera_type=FRONT_CAMERA;
        }
    }
    m_native_camera = new Native_Camera(m_selected_camera_type);

    m_native_camera->MatchCaptureSizeRequest(&m_view,ANativeWindow_getWidth(m_native_window),ANativeWindow_getHeight(m_native_window));

    ASSERT(m_view.width && m_view.height, "Could not find supportable resolution");

    // Here we set the buffer to use RGBX_8888 as default might be; RGB_565
    ANativeWindow_setBuffersGeometry(m_native_window, m_view.height, m_view.width,WINDOW_FORMAT_RGBX_8888);

    m_image_reader = new Image_Reader(&m_view, AIMAGE_FORMAT_YUV_420_888);
    m_image_reader->SetPresentRotation(m_native_camera->GetOrientation());

    ANativeWindow *image_reader_window = m_image_reader->GetNativeWindow();

    m_camera_ready = m_native_camera->CreateCaptureSession(image_reader_window);
}

void CV_Main::CameraLoop() {
    bool buffer_printout = false;
    int i = 1;
    while (1) {
        if (m_camera_thread_stopped) { break; }
        if (!m_camera_ready || !m_image_reader) { continue; }
        m_image = m_image_reader->GetLatestImage();
        if (m_image == nullptr) { continue; }

        ANativeWindow_acquire(m_native_window);
        ANativeWindow_Buffer buffer;
        if (ANativeWindow_lock(m_native_window, &buffer, nullptr) < 0) {
            m_image_reader->DeleteImage(m_image);
            m_image = nullptr;
            continue;
        }

        if (false == buffer_printout) {
            buffer_printout = true;
            LOGI("/// H-W-S-F: %d, %d, %d, %d", buffer.height, buffer.width, buffer.stride, buffer.format);
        }

        m_image_reader->DisplayImage(&buffer, m_image);

        display_mat = Mat(buffer.height, buffer.stride, CV_8UC4, buffer.bits);

        //BarcodeDetect(display_mat);
        if(i!=1){
            MotionDetect(display_mat, last_frame);
        }
        i=2;


        //putText(display_mat, "Motion Detected", Point(10, 20), FONT_HERSHEY_SIMPLEX, 0.75, Scalar(0,0,255),2);

        cvtColor(display_mat,display_mat,COLOR_RGBA2RGB);
        BGR2YUV_nv12(display_mat, outOpencv);
        int yPlaneSize = outOpencv.total() * outOpencv.elemSize();

        m_Encode->Encode(outOpencv.data, yPlaneSize);

        ANativeWindow_unlockAndPost(m_native_window);
        ANativeWindow_release(m_native_window);
        last_frame=display_mat;
        ReleaseMats();
    }
}

void CV_Main::MotionDetect(Mat &frame, Mat &lastFrame){
    Mat gray, frameDelta, threshMD, firstFrame;
    vector<vector<Point> > cnts;
    Mat lastFrameI;

    LOGI("----------------------test motiondectect------------------------");
    //putText(display_mat, "Motion Detected", Point(10, 20), FONT_HERSHEY_SIMPLEX, 0.75, Scalar(0,0,255),2);
    //convert to grayscale and set the first frame
    cvtColor(lastFrame, lastFrameI, COLOR_BGR2GRAY);
    GaussianBlur(lastFrameI, lastFrameI, Size(21, 21), 0);

    //convert to grayscale
    cvtColor(frame, gray, COLOR_BGR2GRAY);
    GaussianBlur(gray, gray, Size(21, 21), 0);

    //compute difference between first frame and current frame
    absdiff(lastFrameI, gray, frameDelta);
    threshold(frameDelta, threshMD, 25, 255, THRESH_BINARY);

    dilate(threshMD, threshMD, Mat(), Point(-1,-1), 2);
    findContours(threshMD, cnts, RETR_EXTERNAL, CHAIN_APPROX_SIMPLE);
    //putText(frame, "Motion Detected", Point(10, 20), FONT_HERSHEY_SIMPLEX, 0.75, Scalar(0,0,255),2);

    for(int i = 0; i< cnts.size(); i++) {
        if (contourArea(cnts[i]) < 500) {
            continue;
        }
        putText(frame, "Motion Detected", Point(200, 50), FONT_HERSHEY_SIMPLEX, 0.75,
                Scalar(0, 0, 255), 2);
        LOGI("----------------------test------------------------");

    }
}

void CV_Main::BarcodeDetect(Mat &frame) {
    int ddepth = CV_16S;

    // Convert to grayscale
    cvtColor(frame, frame_gray, CV_RGBA2GRAY);

    // Gradient X
    Sobel(frame_gray, grad_x, ddepth, 1, 0);
    convertScaleAbs(grad_x, abs_grad_x);
    // Gradient Y
    Sobel(frame_gray, grad_y, ddepth, 0, 1);
    convertScaleAbs(grad_y, abs_grad_y);

    // Total Gradient (approximate)
    addWeighted(abs_grad_x, 0.5, abs_grad_x, 0.5, 0, detected_edges);

    // Reduce noise with a 3x3 kernel
    GaussianBlur(detected_edges, detected_edges, Size(3,3), 0, 0, BORDER_DEFAULT);

    // Reducing noise further by using threshold
    threshold(detected_edges, thresh, 120, 255, CV_THRESH_BINARY);

    // Otsu's threshold
    threshold(thresh, thresh, 0, 255, CV_THRESH_BINARY+THRESH_OTSU);

    // Close gaps using a closing kernel
    kernel = getStructuringElement(MORPH_RECT, Size(21,7));
    morphologyEx(thresh, cleaned, MORPH_CLOSE, kernel);

    // Perform erosions and dilations
    erode(cleaned, cleaned, anchor, Point(-1,-1), 4);
    dilate(cleaned, cleaned, anchor, Point(-1,-1), 4);

    // Extract all contours
    findContours(cleaned, contours, hierarchy, RETR_EXTERNAL, CHAIN_APPROX_SIMPLE);

    // Sort contours in ascending order
    std::sort(contours.begin(), contours.end(), [](const vector<Point>& c1, const vector<Point>& c2) {
        return contourArea(c1, false) < contourArea(c2, false);
    });

    // Draw the largest contour
    drawContours(frame, contours, int(contours.size()-1), CV_PURPLE, 2, LINE_8, hierarchy, 0, Point());

    //m_client->SendImage(frame);
}

void CV_Main::ReleaseMats() {
    display_mat.release();
    frame_gray.release();
    grad_x.release();
    abs_grad_x.release();
    grad_y.release();
    abs_grad_y.release();
    detected_edges.release();
    thresh.release();
    kernel.release();
    anchor.release();
    cleaned.release();
    hierarchy.release();
    outOpencv.release();
}

void CV_Main::SetupSocketClient() {
    //int port = 3009;//5555
    //const char hostname[] = "192.168.142.197"; //"192.168.236.45"
    //m_client = new SocketClient(hostname, port);
    m_client = new SocketClient(m_hostname, m_port);
    m_client->ConnectToServer();
    //m_client->SendImageDims(640,800);
}

void CV_Main::SetupEncoder(){
    m_Encode = new Encoder();
    m_Encode->setSocketClient(m_client);
    m_Encode->InitCodec(800,640,15,100000);
}

void CV_Main::SetupIpPort(const char *ipAddress, int port){
    m_hostname = ipAddress;
    m_port=port;
}
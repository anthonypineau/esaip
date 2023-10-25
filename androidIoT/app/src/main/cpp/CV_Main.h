#ifndef OPENCV_NDK_CV_MAIN_H
#define OPENCV_NDK_CV_MAIN_H

// Android
#include <android/native_window.h>
#include <jni.h>
// OpenCV
#include <opencv2/core.hpp>
#include <opencv2/imgproc.hpp>
// OpenCV-NDK App
#include "Image_Reader.h"
#include "Native_Camera.h"
#include "Util.h"
// STD Libs
#include <cstdlib>
#include <string>
#include <vector>
#include <thread>
//Socket
#include "SocketClient.h"
#include "Encoder.h"

using namespace cv;
using namespace std;

void convertYUV_I420toNV12(unsigned char* i420bytes, unsigned char* nv12bytes, int width, int height);
void BGR2YUV_nv12(cv::Mat &src, cv::Mat &dst);
double clockToMilliseconds(clock_t ticks);

class CV_Main {
public:
    CV_Main();

    ~CV_Main();

    CV_Main(const CV_Main &other) = delete;

    CV_Main &operator=(const CV_Main &other) = delete;

    // sets Surface buffer reference pointer
    void SetNativeWindow(ANativeWindow *native_window);

    void SetUpCamera(bool switchCamera);

    void CameraLoop();

    void MotionDetect(Mat &frame, Mat &lastFrame);

    void BarcodeDetect(Mat &frame);

    void ReleaseMats();

    void SetupSocketClient();

    void SetupEncoder();

    void SetupIpPort(const char* ipAddress, int port);

private:
    // holds native window to write buffer too
    ANativeWindow *m_native_window;

    // buffer to hold native window when writing to it
    ANativeWindow_Buffer m_native_buffer;

    // Camera variables
    Native_Camera *m_native_camera;

    camera_type m_selected_camera_type = FRONT_CAMERA;//BACK_CAMERA; // Default

    // Image Reader
    ImageFormat m_view{0, 0, 0};
    Image_Reader *m_image_reader;
    AImage *m_image;

    volatile bool m_camera_ready;

    // OpenCV values
    Mat display_mat;
    Mat frame_gray;
    Mat grad_x;
    Mat abs_grad_x;
    Mat grad_y;
    Mat abs_grad_y;
    Mat detected_edges;
    Mat thresh;
    Mat kernel;
    Mat anchor;
    Mat cleaned;
    Mat hierarchy;
    Mat outOpencv;
    Mat last_frame;

    vector<vector<Point>> contours;

    Scalar CV_PURPLE = Scalar(255, 0, 255);
    Scalar CV_RED = Scalar(255, 0, 0);
    Scalar CV_GREEN = Scalar(0, 255, 0);
    Scalar CV_BLUE = Scalar(0, 0, 255);

    bool m_camera_thread_stopped = false;

    SocketClient* m_client;
    Encoder*    m_Encode;
    AMediaCodec* m_MediaCodec;

    int m_port;
    const char* m_hostname;
};

#endif  // OPENCV_NDK_CV_MAIN_H

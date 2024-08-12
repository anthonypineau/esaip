#ifndef NATIVEBARCODETRACKER_ENCODER_H
#define NATIVEBARCODETRACKER_ENCODER_H

// local include
//#include "Native_Camera.h"
#include "SocketClient.h"
// OpenCV biblio
#include "opencv2/core.hpp"
#include "opencv2/highgui.hpp"
#include "opencv2/imgproc.hpp"
// Android biblio
#include <media/NdkMediaCodec.h>
#include <media/NdkMediaMuxer.h>
#include <media/NdkImage.h>

// c biblio
#include <time.h>
#include <unistd.h>

class Encoder {
public:
    Encoder();

    virtual ~Encoder();

    void Encode(unsigned char *YUV_NV12, int planeSize);

    void InitCodec(int height , int width, int framerate ,int bitrate);

    media_status_t getStatus();

    void handleOutput();

    bool initFile(const char* H264_FILE_PATH, struct tm* timeInfos, time_t timestamp);

    bool writeFile(uint8_t* Buf, int32_t buffSize, size_t blocCount);

    void setSocketClient(SocketClient *m_Client);

private:
    int mheight ;
    int mwidth ;
    int mYSize;

    uint8_t*       mHeaderBuf;
    int            mSizeofHeader;

    AMediaCodec*   mMediaCodec;
    media_status_t mStatus;
    AMediaFormat*  mMediaFormat;

    int            mRawH264Fd;
    pthread_t      mDecoderThread;

    SocketClient*      mClient;

    FILE*                 mfptr= NULL;
    FILE*                 mjptr= NULL;

    char     mfilename[255], mjsonfilename[255];
    //const char *pframe;
};
#endif //NATIVEBARCODETRACKER_ENCODER_H
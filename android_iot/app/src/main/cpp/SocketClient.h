//
// Created by Anthony on 01/03/2022.
//

#ifndef NATIVEBARCODETRACKER_SOCKETCLIENT_H
#define NATIVEBARCODETRACKER_SOCKETCLIENT_H

#include <opencv2/core.hpp>

class SocketClient {
    public:
        SocketClient(const char* hostname, int port);
        //~SocketClient();
        void ConnectToServer();
        void SendImageDims(const int image_rows, const int image_cols);
        void SendImage(cv::Mat& image);
        void SendImageH264(uint8_t* buffer, int size);
    private:
        const char* hostname_;
        int port_;
        int pic_num_;
        int socket_fdesc_;
};


#endif //NATIVEBARCODETRACKER_SOCKETCLIENT_H

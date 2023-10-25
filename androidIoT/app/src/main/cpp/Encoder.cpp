#include <android/log.h>
#include "Encoder.h"
#include "Util.h"
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <thread>
#include <mutex>
#include <iostream>

#define TIMEOUT_US  0 // 0

//#define RAW_H264_FILE_PATH "/sdcard/DCIM/Camera/cogelec_recording.h264"

#define RAW_H264_FILE_PATH  "/storage/emulated/0/DCIM/Camera/%02d_%02d_%04d_%02d:%02d:%02d.h264" //"/storage/19EA-3759/seesee_smart_module/%04d_%02d_%02d_%02d:%02d:%02d.h264"

//#define JSON_H264_FILE_PATH  "/sdcard/DCIM/Camera/%02d_%02d_%04d_%02d:%02d:%02d.txt" //"/storage/19EA-3759/seesee_smart_module/%04d_%02d_%02d_%02d:%02d:%02d.h264"

#define MAX_SIZE  1

#define NBRE_P_FRAME  10

#define I_FRAME_INTERVAL  5//60  

#define NSEC_PER_SEC 1000000000

static uint64_t startTime = 0;

static inline uint64_t timespecToNsec(const struct timespec *a) {
    return (uint64_t)a->tv_sec * NSEC_PER_SEC + a->tv_nsec;
}

static uint64_t getTime() {
    struct timespec tp;
    clock_gettime(CLOCK_MONOTONIC, &tp);
    return  timespecToNsec(&tp);
}

static void* s_handleOutput(void* data) {
    Encoder* encoder = (Encoder*)data;

    encoder->handleOutput();
    return NULL;
}

Encoder::Encoder() : mHeaderBuf(NULL) {

};

void Encoder::handleOutput() {
    media_status_t        status;
    AMediaCodecBufferInfo info;
    size_t                size;
    uint8_t*              outBuf;
    ssize_t               outBufId;
    //ssize_t               ret;
    size_t                ret;

    struct   tm* tm;

    time_t   now;

    int compt = 0;

    bool record = true;

    int sizeBetweenP = 0;

    int tabSize[NBRE_P_FRAME]={0};

    mSizeofHeader = 0;

    bool file_ok;
    bool header_ok;
    bool cogelec_ok;
    /**
    * Obtenez l'index du prochain buffer disponible de donnes traitées
    * où le résultat est stocké de la même manière que lors de l'encodage
    * L'ID du buffer de sortie à ce moment n'a rien à voir avec l'ID du buffer d'entrée, 
    * et les résultats de codage sont sortis dans l'ordre d'entrée dans la file d'attente
    * le buffer de sorti sera bloqué au plus "timeout" microsecondes.
    * un timeout négatif indique "infini".
    */
    while (1) {
        outBufId = AMediaCodec_dequeueOutputBuffer(mMediaCodec, &info, TIMEOUT_US/*-1*/);
        if (outBufId < 0) { continue; }

        /**
        * Renvoie un ByteBuffer en lecture seule pour un index de tampon de sortie retiré de la file d'attente.
        * La position et la limite du buffer renvoyé sont définies sur les données de sortie valides. 
        * Après avoir appelé cette méthode, tout objet ByteBuffer ou 
        * Image précédemment renvoyé pour le même index de sortie NE DOIT plus être utilisé.
        */
        /*auto*/ outBuf = AMediaCodec_getOutputBuffer(mMediaCodec, outBufId, &size); // obtenez un buffer de sortie.
        if (outBuf) {
            //printf("flag:%d offset:%d size:%d \n", info.flags, info.offset, info.size);
            /**
            * info.flags = 4; Fin du flux. 
            * info.flags = 2; Informations de trame d'en-tête de trame.
            * info.flags = 1; frame clé. BUFFER_FLAG_KEY_FRAME
            * info.flags = 0; frame diff.
            */
            this->mClient->SendImageH264(outBuf, info.size);

            //-- Recording -------
            /*
            if (info.flags == 2 && info.size != 0) {
                // Free mHeaderBuf if already allocated
                if (mHeaderBuf) { free(mHeaderBuf); }
                mSizeofHeader = info.size;
                mHeaderBuf = new uint8_t[mSizeofHeader];
                //printf("Informations d'en tete \n");
                memcpy(mHeaderBuf,outBuf, info.size);
            }

            if (info.flags == 1) {
                if (record == true) {
                    cogelec_ok = writeFile(outBuf, info.size, MAX_SIZE) ;
                    if (cogelec_ok) {
                        compt ++ ;
                        tabSize[compt]= sizeBetweenP;
                        sizeBetweenP = sizeBetweenP + info.size;
                    }
                    // printf("compt : %d \n",  compt);
                    // printf(" size P%d: %d\n", compt, tabSize[compt]);
                } else {
                    file_ok = initFile(RAW_H264_FILE_PATH, tm, now);

                    header_ok = writeFile(mHeaderBuf, mSizeofHeader, MAX_SIZE);
                    if (header_ok) {
                        writeFile(outBuf, info.size, MAX_SIZE);
                    }

                    tabSize[compt] = 0;
                    sizeBetweenP = info.size;
                    record = true;

                    // printf(" record %s\n", record ? "true" : "false");
                    // printf("compt : %d \n",  compt);
                    // printf(" size P%d: %d\n", compt, tabSize[compt]);
                }
            } else if (info.flags == 0) {
                cogelec_ok = writeFile(outBuf, info.size, MAX_SIZE);
                if (cogelec_ok) {
                    sizeBetweenP = sizeBetweenP + info.size;
                }
            }

            if (compt == 10) {
                tabSize[compt]= sizeBetweenP;
                record = false;
                compt = 0;
                sizeBetweenP = 0;
                // printf("compt:%d \n",  compt);
                // printf("%s\n", record ? "true" : "false");
                // printf(" size P%d: %d\n", compt, tabSize[compt]);
            }*/
        }

        //Si vous avez terminé avec un buffer, utilisez cet appel pour renvoyer le tampon au codec.
        status = AMediaCodec_releaseOutputBuffer(mMediaCodec, outBufId, false);
        if (status != AMEDIA_OK) {
            LOGI("ON EST DANS LE MAL");
            __android_log_write(ANDROID_LOG_ERROR, "EncoderNDK", "Failed to release output buffer !");
        }
    } // end while
} // end function

void Encoder::InitCodec(int height , int width, int framerate ,int bitrate) {
    mheight = height;
    mwidth = width;
    mMediaCodec = AMediaCodec_createEncoderByType("video/avc"); // Creez le codec par son nom (/avc = h264).

    if (mMediaCodec == NULL) {
        __android_log_write(ANDROID_LOG_ERROR, "EncoderNDK",
                            "Unable to create an encoder for video/avc");
    } else {
        __android_log_write(ANDROID_LOG_DEBUG, "EncoderNDK", "Encoder for video/avc created");
    }

    mMediaFormat   = AMediaFormat_new();
    //Le type de format. (video/avc = h264)
    AMediaFormat_setString(mMediaFormat, AMEDIAFORMAT_KEY_MIME, "video/avc");// H.264 Advanced Video Coding
    // La description la largeur de l'image
    AMediaFormat_setInt32(mMediaFormat, AMEDIAFORMAT_KEY_WIDTH, mwidth);
    // La description de la hauteur de l'image
    AMediaFormat_setInt32(mMediaFormat, AMEDIAFORMAT_KEY_HEIGHT, mheight);
    // La description du framerate (image/seconde)
    AMediaFormat_setInt32(mMediaFormat, AMEDIAFORMAT_KEY_FRAME_RATE, framerate); //  // 15fps
    // La description du débit binaire souhaité en bits / seconde
    AMediaFormat_setInt32(mMediaFormat, AMEDIAFORMAT_KEY_BIT_RATE, bitrate); // bitrate = 100000 
    // La description des intervalles de temps en (s)entre les images clés.
    AMediaFormat_setInt32(mMediaFormat, AMEDIAFORMAT_KEY_I_FRAME_INTERVAL, I_FRAME_INTERVAL); //2, 5 seconds between I-frames
    // défini par l'utilisateur pour les encodeurs, lisible dans le format de sortie des décodeurs (21 = nv12)
    AMediaFormat_setInt32(mMediaFormat, AMEDIAFORMAT_KEY_COLOR_FORMAT, 21/*35033992*/); //19 yuv420p 21 nv12
    //AMediaFormat_setInt32(mMediaFormat, AMEDIAFORMAT_KEY_COLOR_FORMAT, 0x15);//0x7f420888);//0x15
    //AMediaFormat_setInt32(mMediaFormat, AMEDIAFORMAT_KEY_BITRATE_MODE, 2);
    /**
    * #if __ANDROID_API__ >= 28
    * int BITRATE_MODE_CBR = 2;
    * int BITRATE_MODE_VBR = 1;
    * int BITRATE_MODE_CQ = 0;
    */
    // //Auto uniquement 
    // AMediaFormat_setInt32(mMediaFormat, AMEDIAFORMAT_KEY_FLAC_COMPRESSION_LEVEL, 90);
    // // definition du format sps (sequence parametrer sets) 
    // //uint8_t sps[2] = {0x12, 0x12}; 
    // uint8_t sps[2] = {0x00, 0x00};
    // // definition du format pps (picture parametrer sets) 
    // //uint8_t pps[2] = {0x12, 0x12}; 
    // uint8_t pps[2] = {0x00, 0x01};

    // // buffer CSD n ° 0
    // AMediaFormat_setBuffer(mMediaFormat, "csd-0", sps, 2); // sps
    // // buffer CSD n ° 1
    // AMediaFormat_setBuffer(mMediaFormat, "csd-1", pps, 2); // pps
    // configuration du codec
    mStatus = AMediaCodec_configure(mMediaCodec, mMediaFormat, nullptr/* surface */, nullptr /* crypto*/,AMEDIACODEC_CONFIGURE_FLAG_ENCODE);

    if (mStatus != 0) {
        __android_log_print( ANDROID_LOG_ERROR,"EncoderNDK","AMediaCodec_configure() failed with error %i for format %u", (int)mStatus, 21);
    }
    else {
        if ((mStatus = AMediaCodec_start(mMediaCodec)) != AMEDIA_OK) { // Start the codec.
            __android_log_print(ANDROID_LOG_ERROR, " EncoderNDK ", "AMediaCodec_start: Could not start encoder.");
        }
        else {
            __android_log_print(ANDROID_LOG_DEBUG, " EncoderNDK ", "AMediaCodec_start: encoder successfully started");
        }
    }

    AMediaFormat_delete(mMediaFormat);
    // Start the codec.
    // mStatus = AMediaCodec_start(mMediaCodec);
    // if(mStatus != AMEDIA_OK){
    //             printf("Error occurred: %s ", mStatus);
    //         }

    pthread_create(&mDecoderThread, NULL, s_handleOutput, this);
    //std::thread t (&Encoder::handleOutput, Encoder());
    //t.join();
}

Encoder::~Encoder() {

}

void Encoder::Encode (unsigned char *YUV_NV12, int planeSize) {
    mYSize =  planeSize;
    size_t   size;

    uint8_t* bufferPointer;
    uint64_t currentTime;

    ssize_t inputBufId;

    if (startTime == 0) {
        startTime = getTime();
    }
    currentTime = getTime() - startTime;
    /**
    * etape 1 : prendre l'adress du  buffer 
    * Obtenez l'index du prochain buffer d'entrée disponible. 
    * getInputBuffer () sera utiliser pour obtenir un pointeur vers le tampon, 
    * puis copiera les données à encoder dans le buffer avant de les transmettre au codec.
    * ici le timeout == 0 par conséquent 
    */
    inputBufId = AMediaCodec_dequeueInputBuffer(mMediaCodec, TIMEOUT_US/*0*/);
    if (inputBufId < 0) {
        __android_log_write(ANDROID_LOG_ERROR, "EncoderNDK", "Dequeue buffer failed");
        return;
    }
    /**
    * etape 2 : obtenir les données du buffer
    * Obtenez un buffer d'entrée. 
    * L'index du buffer spécifié doit avoir été précédemment obtenu 
    * à partir de dequeueInputBuffer et pas encore mis en file d'attente.
    */
    if (inputBufId >= 0) {
        bufferPointer = AMediaCodec_getInputBuffer(mMediaCodec, static_cast<size_t>(inputBufId), &size);

        if (bufferPointer) {
            // ici, nous copions réellement les données image dans le buffer.
            memcpy(bufferPointer, YUV_NV12, mYSize);
            /**
            * etape 3 : envoyer le buffer au codec
            * Envoyez le buffer spécifié au codec pour traitement.
            *  AMediaCodec_queueInputBuffer est la file d'attente de données en attente de consommation, 
            * AMediaCodec_releaseOutputBuffer sont les données de publication.
            *  Cette méthode retournera immédiatement si timeoutUs == 0, 
            * attendra indéfiniment la disponibilité d'un tampon d'entrée si timeoutUs <0 
            * ou attendra jusqu'à "timeoutUs" microsecondes si timeoutUs> 0.
            */
            //int64_t presentationTimeNs = computePresentationTimeNsec();
            mStatus = AMediaCodec_queueInputBuffer(mMediaCodec, static_cast<size_t>(inputBufId), 0, size, currentTime,
                                                   0);
            if (mStatus != AMEDIA_OK) {
                __android_log_write(ANDROID_LOG_ERROR, "EncoderNDK", "Error while buffer queued");
            }

        } else {
            __android_log_print(ANDROID_LOG_DEBUG, " EncoderNDK ", "obtained InputBuffer, but no address.");
        }

    } else if (inputBufId == AMEDIA_ERROR_UNKNOWN) {
        __android_log_print(ANDROID_LOG_DEBUG, " EncoderNDK ", " AMediaCodec_dequeueInputBuffer() had an exception");

    }
    /**
    * Maintenant, retirer les trames codées éventuellement en attente
    */
    //Creation des fichiers (.h264 et .json)

    //pthread_create(&mDecoderThread, NULL, s_handleOutput, this);
}

media_status_t Encoder::getStatus() {
    return mStatus;
}

bool Encoder::initFile(const char* H264_FILE_PATH, struct tm* timeInfos, time_t timestamp) {
    timestamp  = time(NULL);                 // get current time
    timeInfos  = localtime(&timestamp );     // get time structure

    int retValPaht = sprintf(mfilename, H264_FILE_PATH, timeInfos->tm_mday, timeInfos->tm_mon+1, timeInfos->tm_year+1900,
                             timeInfos->tm_hour, timeInfos->tm_min, timeInfos->tm_sec);
    // if (retValPaht < 0) {

    // }

    if (mfptr != NULL) {
        fclose (mfptr);
    }
    // Ouvrir le fichier pour ecrire les binaires dedans

    mfptr = fopen(mfilename, "ab");
    if (mfptr == NULL) {
        printf("Cannot open filename \n");
        return false;
    } else{
        //printf("open filename \n");
        return true;
    }
    return true;
}

bool Encoder::writeFile(uint8_t* Buf, int32_t buffSize, size_t blocCount) {
    if (Buf == NULL || buffSize <= 0) {
        printf("Buffer is null cannot write");
        return false ;
    }
    /* Check for existence */
    if( access( mfilename, F_OK ) == 0 ) {
        //printf("The File %s\t was Found\n",mfilename);
        if (Buf != NULL || mfptr != NULL) {
            int ret = fwrite( Buf, static_cast<size_t>(buffSize), blocCount, mfptr);
            if (ret != 1) {
                printf("Cannot write block in file \n");
                return false;
            }
        } else {
            printf("Buf or file is NULL \n");
            return false ;
        }
    } else {
        return false;
    }
    return true;
}

void Encoder::setSocketClient(SocketClient *m_Client) {
    this->mClient=m_Client;
}
package com.fileloader.android.model;

import android.support.annotation.NonNull;

import java.util.List;

public interface MainInteractor {
    void getImages(@NonNull ResponseListener listener);

    interface ResponseListener {
        void onSuccess(@NonNull List<UserImage> userImages);

        void onError(@NonNull String errorMessage);
    }
}

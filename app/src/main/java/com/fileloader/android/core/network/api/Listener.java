package com.fileloader.android.core.network.api;

public interface Listener<T> {
    void onSuccess(T response);

    void onError(ServiceError error);
}
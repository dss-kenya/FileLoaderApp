package com.fileloaderlib.android;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;

class RequestCreator {
    private final FileLoader fileLoader;

    RequestCreator(@NonNull final FileLoader fileLoader) {
        this.fileLoader = fileLoader;
    }

    RequestBuilder<Drawable> load(@Nullable final String url) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("url cannot be null or empty");
        }
        return asDrawable().load(url);
    }

    public RequestBuilder<Drawable> asDrawable() {
        return as(Drawable.class);
    }

    public RequestBuilder<File> asFile() {
        return as(File.class);
    }

    private <ReturnClass> RequestBuilder<ReturnClass> as(@NonNull final Class<ReturnClass> classType) {
        return new RequestBuilder<>(fileLoader, classType);
    }
}

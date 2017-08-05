package com.fileloader.android.di;

import android.content.Context;

import com.fileloader.android.dagger2.FileLoaderComponent;

public interface FileLoaderComponentProvider {
    FileLoaderComponent getFileLoaderComponent(Context context);
}

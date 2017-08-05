package com.fileloader.android.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.fileloader.android.dagger2.FileLoaderComponent;

public class FileLoaderInjector {
    public static FileLoaderComponent from(@NonNull final Context context) {
        final FileLoaderComponentProvider provider = (FileLoaderComponentProvider) context.getApplicationContext();
        return provider.getFileLoaderComponent(context);
    }
}

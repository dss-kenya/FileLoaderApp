package com.fileloader.android.core.utils;

import android.util.Log;

import com.fileloader.android.BuildConfig;
import com.fileloader.android.FileLoaderApp;
import com.fileloader.android.core.di.CoreInjector;

public class FileLoaderLog {
    public FileLoaderLog() {
        CoreInjector.from(FileLoaderApp.INSTANCE).inject(this);
    }

    public static void v(final String tag, final String msg) {
        if (useLog()) {
            Log.v(String.valueOf(tag), String.valueOf(msg));
        }
        return;
    }

    private static boolean useLog() {
        return BuildConfig.DEBUG;
    }
}

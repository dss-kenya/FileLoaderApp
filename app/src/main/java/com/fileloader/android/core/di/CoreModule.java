package com.fileloader.android.core.di;

import android.app.Application;
import android.content.Context;

import com.fileloader.android.core.utils.FileLoaderLog;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CoreModule {
    private final Context context;

    public CoreModule(final Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public FileLoaderLog provideFileLoaderLog() {
        return new FileLoaderLog();
    }

    @Singleton
    @Provides
    Application providesApplication() {
        return (Application) context;
    }
}
package com.fileloader.android.dagger2;

import android.app.Application;

import com.fileloader.android.FileLoaderApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    FileLoaderApp application;

    public AppModule(FileLoaderApp application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Application provideApplication() {
        return application;
    }
}
package com.fileloader.android.core.di;

import com.fileloader.android.core.utils.AppInit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    @Provides
    @Singleton
    public AppInit provideAppInit() {
        throw new IllegalStateException("stub");
    }
}
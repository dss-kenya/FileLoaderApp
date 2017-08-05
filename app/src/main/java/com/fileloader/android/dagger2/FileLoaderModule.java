package com.fileloader.android.dagger2;

import com.fileloader.android.data_source.FileLoaderDataSource;
import com.fileloader.android.data_source.FileLoaderDataSourceImpl;
import com.fileloader.android.model.MainInteractor;
import com.fileloader.android.model.MainInteractorImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FileLoaderModule {
    @Provides
    public FileLoaderDataSource provideFileDataSource() {
        return new FileLoaderDataSourceImpl();
    }

    @Provides
    public MainInteractor provideMainInteractor() {
        return new MainInteractorImpl();
    }
}
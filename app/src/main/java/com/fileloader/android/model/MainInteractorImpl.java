package com.fileloader.android.model;

import android.support.annotation.NonNull;

import com.fileloader.android.FileLoaderApp;
import com.fileloader.android.core.network.api.Listener;
import com.fileloader.android.core.network.api.ServiceError;
import com.fileloader.android.data_source.FileLoaderDataSource;
import com.fileloader.android.di.FileLoaderInjector;

import java.util.List;

import javax.inject.Inject;

public class MainInteractorImpl implements MainInteractor {
    @Inject
    FileLoaderDataSource dataSource;

    public MainInteractorImpl() {
        FileLoaderInjector.from(FileLoaderApp.INSTANCE).inject(this);
    }

    @Override
    public void getImages(@NonNull final ResponseListener listener) {
        dataSource.getImages(new Listener<List<UserImage>>() {
            @Override
            public void onSuccess(final List<UserImage> response) {
                listener.onSuccess(response);
            }

            @Override
            public void onError(final ServiceError error) {
                listener.onError(error.getErrorMessage());
            }
        });
    }
}

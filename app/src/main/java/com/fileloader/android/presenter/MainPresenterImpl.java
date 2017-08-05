package com.fileloader.android.presenter;

import android.support.annotation.NonNull;

import com.fileloader.android.FileLoaderApp;
import com.fileloader.android.di.FileLoaderInjector;
import com.fileloader.android.model.ImageModelAdapterImpl;
import com.fileloader.android.model.MainInteractor;
import com.fileloader.android.model.UserImage;
import com.fileloader.android.view.MainView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class MainPresenterImpl implements MainPresenter, MainInteractor.ResponseListener {
    private final MainView mainView;

    @Inject
    MainInteractor interactor;

    public static MainPresenterImpl create(@NonNull final MainView mainView) {
        return new MainPresenterImpl(mainView);
    }

    private MainPresenterImpl(@NonNull final MainView mainView) {
        this.mainView = mainView;
        FileLoaderInjector.from(FileLoaderApp.INSTANCE).inject(this);
    }

    @Override
    public void handleOnViewCreated() {
        mainView.initViews(ImageModelAdapterImpl.from(Collections.<UserImage>emptyList()));
        interactor.getImages(this);
    }

    @Override
    public void onSuccess(@NonNull final List<UserImage> userImages) {
        mainView.updateAdapter(ImageModelAdapterImpl.from(userImages));
    }

    @Override
    public void onError(@NonNull final String errorMessage) {
        mainView.showError(errorMessage);
    }
}

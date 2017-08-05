package com.fileloader.android.view;

import android.support.annotation.NonNull;

import com.fileloader.android.model.ImageModelAdapter;

public interface MainView {
    void initViews( @NonNull ImageModelAdapter modelAdapter);

    void updateAdapter(@NonNull ImageModelAdapter modelAdapter);

    void showError(@NonNull String errorMessage);
}

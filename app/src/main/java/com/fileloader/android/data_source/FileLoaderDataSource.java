package com.fileloader.android.data_source;

import android.support.annotation.NonNull;

import com.fileloader.android.core.network.api.Listener;
import com.fileloader.android.model.UserImage;

import java.util.List;

public interface FileLoaderDataSource {
    void getImages(@NonNull Listener<List<UserImage>> listener);
}

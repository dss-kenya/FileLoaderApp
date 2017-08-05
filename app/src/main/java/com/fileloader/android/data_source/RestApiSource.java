package com.fileloader.android.data_source;

import com.fileloader.android.model.UserImage;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RestApiSource {
    @GET(".")
    Observable<List<UserImage>> getImages();
}
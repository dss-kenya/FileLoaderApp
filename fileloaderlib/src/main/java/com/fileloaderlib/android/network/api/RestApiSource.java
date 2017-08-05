package com.fileloaderlib.android.network.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface RestApiSource {
    @Streaming
    @GET
    Observable<ResponseBody> fetchFile(@Url String url);
}
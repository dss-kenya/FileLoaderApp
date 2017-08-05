package com.fileloader.android.core.di;

import com.fileloader.android.core.utils.AppInit;
import com.fileloader.android.core.utils.FileLoaderLog;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {ApplicationModule.class, CoreModule.class, NetModule.class})
public interface CoreComponent {
    FileLoaderLog getFileLoaderLog();

    Retrofit getRetrofit();

    Gson getGson();

    OkHttpClient getOkHttpClient();

    AppInit getAppInit();

    void inject(FileLoaderLog fileLoaderLog);
}

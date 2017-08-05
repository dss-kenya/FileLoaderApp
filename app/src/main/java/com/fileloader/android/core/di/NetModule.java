package com.fileloader.android.core.di;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {
    final String baseUrl;

    public NetModule(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    public Cache provideHttpCache(final Application application) {
        final int cacheSize = 10 * 1024 * 1024;
        new Cache(application.getCacheDir(), cacheSize);
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkhttpClient(final Cache cache) {
        final OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        return client.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(final Gson gson, final OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
    }
}

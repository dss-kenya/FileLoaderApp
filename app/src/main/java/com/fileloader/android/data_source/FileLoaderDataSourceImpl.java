package com.fileloader.android.data_source;

import android.support.annotation.NonNull;

import com.fileloader.android.FileLoaderApp;
import com.fileloader.android.core.network.api.Listener;
import com.fileloader.android.core.network.api.ServiceError;
import com.fileloader.android.di.FileLoaderInjector;
import com.fileloader.android.model.UserImage;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class FileLoaderDataSourceImpl implements FileLoaderDataSource {
    private static final String TAG = FileLoaderDataSourceImpl.class.getSimpleName();
    @Inject
    Retrofit retrofit;

    public FileLoaderDataSourceImpl() {
        FileLoaderInjector.from(FileLoaderApp.INSTANCE).inject(this);
    }

    @Override
    public void getImages(@NonNull final Listener<List<UserImage>> listener) {
        final Observable<List<UserImage>> observable = retrofit.create(RestApiSource.class).getImages();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<UserImage>>() {
                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull List<UserImage> userImages) {
                        listener.onSuccess(userImages);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        listener.onError(new ServiceError(e.getMessage()));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

package com.fileloaderlib.android;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.fileloaderlib.android.network.api.RestApiSource;
import com.fileloaderlib.android.network.api.RestClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class RequestBuilder<ReturnClass> {
    private final FileLoader fileLoader;
    private final Context context;
    private Class<ReturnClass> returnClass;
    private String url;
    private final Retrofit retrofit;

    RequestBuilder(@NonNull final FileLoader fileLoader, @NonNull final Class<ReturnClass> returnClass) {
        this.fileLoader = fileLoader;
        this.context = fileLoader.getContext();
        this.returnClass = returnClass;
        retrofit = RestClient.getInstance();
    }

    RequestBuilder<ReturnClass> load(@NonNull final String url) {
        this.url = url;
        return this;
    }

    // defaulting to an image view right now
    public void into(final ImageView target) {
        if (target == null) {
            throw new IllegalArgumentException("target cannot be null");
        }

        if (!fileLoader.hasDataInCache(url)) {
            final Observable<ResponseBody> observer = retrofit.create(RestApiSource.class).fetchFile(url);
            observer.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(new Consumer<ResponseBody>() {
                        @Override
                        public void accept(@io.reactivex.annotations.NonNull final ResponseBody responseBody) {
                            try {
                                final byte[] imageBytes = getBytes(responseBody.byteStream());
                                if (imageBytes == null) {
                                    return;
                                }
                                fileLoader.addDataToCache(url, imageBytes);
                                target.setImageBitmap(BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length));
                            } catch (final Exception e) {
                                e.printStackTrace();
                            }
                        }

                        private byte[] getBytes(final InputStream is) {
                            try {
                                final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                                int nRead;
                                final byte[] data = new byte[16384];

                                while ((nRead = is.read(data, 0, data.length)) != -1) {
                                    buffer.write(data, 0, nRead);
                                }

                                buffer.flush();
                                return buffer.toByteArray();
                            } catch (final IOException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }

                    }).subscribe();
        }

        if (fileLoader.hasDataInCache(url)) {
            final byte[] imageBytes = fileLoader.getDataFromCache(url);
            if (imageBytes == null) return;
            target.setImageBitmap(BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length));
        }
    }
}

package com.fileloaderlib.android;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.WeakHashMap;

public class FileLoader {
    private static volatile FileLoader fileLoader;
    final Context context;
    private WeakHashMap<String, byte[]> bitmapCache;

    private FileLoader(final Context context) {
        this.context = context;
        this.bitmapCache = new WeakHashMap<>();
    }

    public static FileLoader with(@Nullable final Context context) {
        if (fileLoader == null) {
            synchronized (FileLoader.class) {
                if (fileLoader == null) {
                    if (context == null) {
                        throw new IllegalStateException("context is null");
                    }
                }
                fileLoader = new FileLoaderBuilder(context).build();
            }
        }
        return fileLoader;
    }

    public RequestBuilder<Drawable> load(@Nullable final String url) {
        return new RequestCreator(this).load(url);
    }

    public Context getContext() {
        return context;
    }

    public boolean hasDataInCache(@NonNull final String url) {
        return bitmapCache.containsKey(url);
    }

    public void addDataToCache(@NonNull final String url, @NonNull final byte[] bytes) {
        bitmapCache.put(url, bytes);
    }

    public byte[] getDataFromCache(@NonNull final String url) {
        return bitmapCache.get(url);
    }

    private static class FileLoaderBuilder {
        Context context;
        FileLoaderBuilder(final Context context) {
            if (context == null) {
                throw new IllegalArgumentException("context cannot be null");
            }
            this.context = context.getApplicationContext();
        }

        FileLoader build() {
            final Context context = this.context;
            return new FileLoader(context);
        }
    }
}

package com.fileloader.android.model;

import android.support.annotation.NonNull;

import java.util.List;

public class ImageModelAdapterImpl implements ImageModelAdapter {
    private final List<UserImage> imageList;

    public static ImageModelAdapterImpl from(@NonNull final List<UserImage> imageList) {
        return new ImageModelAdapterImpl(imageList);
    }

    private ImageModelAdapterImpl(@NonNull final List<UserImage> images) {
        this.imageList = images;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public String getImageUrl(final int position) {
        return imageList.get(position).getImageUrl().getRegular();
    }
}

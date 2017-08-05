package com.fileloader.android.model;

import com.google.gson.annotations.SerializedName;

public class UserImage {
    String id;
    @SerializedName("created_at")
    String createdAt;
    @SerializedName("urls")
    ImageUrl imageUrl;

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public ImageUrl getImageUrl() {
        return imageUrl;
    }
}

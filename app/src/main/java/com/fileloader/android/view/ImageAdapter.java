package com.fileloader.android.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fileloader.android.R;
import com.fileloader.android.model.ImageModelAdapter;
import com.fileloaderlib.android.FileLoader;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private ImageModelAdapter modelAdpater;
    public static ImageAdapter from(@NonNull final ImageModelAdapter modelAdapter) {
        return new ImageAdapter(modelAdapter);
    }

    private ImageAdapter(@NonNull final ImageModelAdapter modelAdapter) {
        this.modelAdpater = modelAdapter;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_individual_grid, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        FileLoader.with(holder.itemView.getContext()).load(modelAdpater.getImageUrl(position)).into(holder.userImageView);
    }

    @Override
    public int getItemCount() {
        return modelAdpater.getCount();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView userImageView;
        ViewHolder(final View itemView) {
            super(itemView);
            userImageView = (ImageView) itemView.findViewById(R.id.iv_user);
        }
    }
}

package com.fileloader.android.view;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.fileloader.android.R;
import com.fileloader.android.model.ImageModelAdapter;

public class MainViewImpl implements MainView {
    private final Activity activity;
    private RecyclerView recyclerView;

    public static MainViewImpl create(@NonNull final Activity activity) {
        return new MainViewImpl(activity);
    }

    private MainViewImpl(@NonNull final Activity activity) {
        this.activity = activity;
    }

    @Override
    public void initViews( @NonNull final ImageModelAdapter modelAdapter) {
        recyclerView = (RecyclerView) activity.findViewById(R.id.rv_image_list);
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        final ImageAdapter adapter = ImageAdapter.from(modelAdapter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateAdapter(@NonNull final ImageModelAdapter modelAdapter) {
        final ImageAdapter adapter = ImageAdapter.from(modelAdapter);
        recyclerView.swapAdapter(adapter, false);
    }

    @Override
    public void showError(@NonNull final String errorMessage) {
        Toast.makeText(activity, errorMessage, Toast.LENGTH_SHORT).show();
    }
}

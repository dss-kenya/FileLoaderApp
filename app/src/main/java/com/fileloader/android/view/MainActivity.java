package com.fileloader.android.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fileloader.android.R;
import com.fileloader.android.presenter.MainPresenter;
import com.fileloader.android.presenter.MainPresenterImpl;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MainView mainView = MainViewImpl.create(this);
        final MainPresenter mainPresenter = MainPresenterImpl.create(mainView);

        mainPresenter.handleOnViewCreated();
    }
}

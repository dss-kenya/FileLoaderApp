package com.fileloader.android.dagger2;

import com.fileloader.android.core.di.CoreComponent;
import com.fileloader.android.core.di.ModuleScope;
import com.fileloader.android.data_source.FileLoaderDataSourceImpl;
import com.fileloader.android.model.MainInteractorImpl;
import com.fileloader.android.presenter.MainPresenterImpl;

import dagger.Component;

@ModuleScope
@Component(modules = FileLoaderModule.class, dependencies = CoreComponent.class)
public interface FileLoaderComponent {

    void inject(FileLoaderDataSourceImpl fileLoaderDataSource);

    void inject(MainInteractorImpl fileLoaderInteractor);

    void inject(MainPresenterImpl mainPresenter);
}
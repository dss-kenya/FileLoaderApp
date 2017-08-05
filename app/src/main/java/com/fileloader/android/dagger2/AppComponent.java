package com.fileloader.android.dagger2;

import com.fileloader.android.core.di.CoreComponent;
import com.fileloader.android.core.di.ModuleScope;

import dagger.Component;

@ModuleScope
@Component(modules = AppModule.class, dependencies = CoreComponent.class)
public interface AppComponent {

}
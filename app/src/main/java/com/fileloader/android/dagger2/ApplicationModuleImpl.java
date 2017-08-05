package com.fileloader.android.dagger2;

import com.fileloader.android.core.di.ApplicationModule;
import com.fileloader.android.core.utils.AppInit;
import com.fileloader.android.core.utils.AppInitImpl;

public class ApplicationModuleImpl extends ApplicationModule {
    @Override
    public AppInit provideAppInit() {
        return new AppInitImpl();
    }
}
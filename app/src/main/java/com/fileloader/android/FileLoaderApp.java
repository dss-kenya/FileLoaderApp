package com.fileloader.android;

import android.app.Application;
import android.content.Context;

import com.fileloader.android.core.di.ApplicationModule;
import com.fileloader.android.core.di.CoreComponent;
import com.fileloader.android.core.di.CoreComponentProvider;
import com.fileloader.android.core.di.CoreModule;
import com.fileloader.android.core.di.DaggerCoreComponent;
import com.fileloader.android.core.di.NetModule;
import com.fileloader.android.core.network.api.Api;
import com.fileloader.android.core.utils.AppInitImpl;
import com.fileloader.android.dagger2.AppComponent;
import com.fileloader.android.dagger2.AppModule;
import com.fileloader.android.dagger2.ApplicationModuleImpl;
import com.fileloader.android.dagger2.CoreModuleImpl;
import com.fileloader.android.dagger2.DaggerAppComponent;
import com.fileloader.android.dagger2.DaggerFileLoaderComponent;
import com.fileloader.android.dagger2.FileLoaderComponent;
import com.fileloader.android.dagger2.FileLoaderModuleImpl;
import com.fileloader.android.di.FileLoaderComponentProvider;

public class FileLoaderApp extends Application implements CoreComponentProvider, FileLoaderComponentProvider {
        public static FileLoaderApp INSTANCE;
        public static AppComponent INJECTOR;
        public static FileLoaderComponent FILE_LOADER_INJECTOR;
        private static volatile CoreComponent CORE_INJECTOR;

        @Override
        public void onCreate () {
        super.onCreate();

        INSTANCE = this;

        final ApplicationModule applicationModule = new ApplicationModuleImpl();
        final CoreModule coreModule = new CoreModuleImpl(this);
        final NetModule netModule = new NetModule(Api.getHost());

        CORE_INJECTOR = DaggerCoreComponent.builder()
                .coreModule(coreModule)
                .netModule(netModule)
                .applicationModule(applicationModule)
                .build();

        INJECTOR = DaggerAppComponent.builder()
                .coreComponent(CORE_INJECTOR)
                .appModule(new AppModule(this))
                .build();


        FILE_LOADER_INJECTOR = DaggerFileLoaderComponent.builder()
                .coreComponent(CORE_INJECTOR)
                .fileLoaderModule(new FileLoaderModuleImpl())
                .build();


        CORE_INJECTOR.getFileLoaderLog();

        new AppInitImpl().start();
    }

        @Override
        public CoreComponent getCoreComponent () {
        return CORE_INJECTOR;
    }

        @Override
        public FileLoaderComponent getFileLoaderComponent ( final Context context) {
            return FILE_LOADER_INJECTOR;
    }
}

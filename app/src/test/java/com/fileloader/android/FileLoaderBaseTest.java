package com.fileloader.android;

import android.content.Context;
import android.support.annotation.CallSuper;

import com.fileloader.android.dagger2.DaggerFileLoaderComponent;
import com.fileloader.android.dagger2.FileLoaderComponent;
import com.fileloader.android.dagger2.FileLoaderModule;
import com.fileloader.android.di.FileLoaderInjector;

import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.mockito.Matchers.any;

@PrepareForTest({FileLoaderInjector.class})
public class FileLoaderBaseTest extends CoreBaseTest {

    @Mock
    protected FileLoaderModule fileLoaderModule;

    @Override
    @CallSuper
    public void setUp() throws Exception {
        super.setUp();

        final FileLoaderComponent fileLoaderComponent = DaggerFileLoaderComponent
                .builder()
                .coreComponent(coreComponent)
                .fileLoaderModule(fileLoaderModule)
                .build();
        PowerMockito.mockStatic(FileLoaderInjector.class);
        PowerMockito.when(FileLoaderInjector.from(any(Context.class))).thenReturn(fileLoaderComponent);
    }
}
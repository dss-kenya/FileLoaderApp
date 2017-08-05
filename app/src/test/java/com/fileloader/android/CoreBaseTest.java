package com.fileloader.android;

import android.app.Application;
import android.content.Context;
import android.support.annotation.CallSuper;

import com.fileloader.android.core.di.ApplicationModule;
import com.fileloader.android.core.di.CoreComponent;
import com.fileloader.android.core.di.CoreInjector;
import com.fileloader.android.core.di.CoreModule;
import com.fileloader.android.core.di.DaggerCoreComponent;
import com.fileloader.android.core.di.NetModule;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CoreInjector.class, Retrofit.class, okhttp3.Cache.class})
public class CoreBaseTest {
    @Mock
    protected NetModule mNetModule;
    @Mock
    protected CoreModule coreModule;
    @Mock
    protected ApplicationModule applicationModule;
    @Mock
    protected Retrofit retrofit;
    @Mock
    protected OkHttpClient client;
    @Mock
    protected Application application;
    @Mock
    protected Cache cache;
    protected CoreComponent coreComponent;

    @Before
    @CallSuper
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        coreComponent = DaggerCoreComponent
                .builder()
                .coreModule(coreModule)
                .netModule(mNetModule)
                .applicationModule(applicationModule)
                .build();
        PowerMockito.mockStatic(CoreInjector.class, Retrofit.class, okhttp3.Cache.class);
        PowerMockito.when(CoreInjector.from(any(Context.class))).thenReturn(coreComponent);

        when(mNetModule.provideRetrofit(Matchers.any(Gson.class), Matchers.any(OkHttpClient.class))).thenReturn(retrofit);
        when(mNetModule.provideGson()).thenReturn(new Gson());
        when(mNetModule.provideHttpCache(application)).thenReturn(cache);
        when(mNetModule.provideOkhttpClient(cache)).thenReturn(client);
    }

    @Test
    public void emptyTest() {
        //to compile with Runner
    }
}
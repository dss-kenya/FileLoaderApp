package com.fileloaderlib.android;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.fileloaderlib.android.network.api.RestClient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({RestClient.class, OkHttpClient.class, Retrofit.class})
public class FileLoaderTest {
    private static final String URL = "URL";
    private static final byte[] BYTES = new byte[10];
    private FileLoader fileLoader;

    @Mock
    Context context;
    @Mock
    RequestCreator requestCreator;
    @Mock
    RequestBuilder<Drawable> requestBuilder;
    @Mock
    Retrofit retrofit;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(RestClient.class, OkHttpClient.class, Retrofit.class);
        fileLoader = FileLoader.with(context);
    }

    @Test
    public void testLoad() throws Exception {
        whenNew(RequestBuilder.class).withArguments(fileLoader, Drawable.class).thenReturn(requestBuilder);
        whenNew(RequestCreator.class).withArguments(fileLoader).thenReturn(requestCreator);
        when(requestCreator.load(URL)).thenReturn(requestBuilder);
        assertEquals(fileLoader.load(URL), requestCreator.load(URL));
    }

    @Test
    public void testHasDataInCache() {
        assertFalse(fileLoader.hasDataInCache(URL));
    }

    @Test
    public void testAddToCache() {
        fileLoader.addDataToCache(URL, BYTES);
        assertTrue(fileLoader.hasDataInCache(URL));
    }

    @Test
    public void testGetDataFromCache() {
        fileLoader.getDataFromCache(URL);
        assertEquals(fileLoader.getDataFromCache(URL), BYTES);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(context, requestCreator, requestBuilder);
    }
}

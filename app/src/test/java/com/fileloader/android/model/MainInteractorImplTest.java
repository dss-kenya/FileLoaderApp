package com.fileloader.android.model;

import com.fileloader.android.FileLoaderBaseTest;
import com.fileloader.android.core.network.api.Listener;
import com.fileloader.android.core.network.api.ServiceError;
import com.fileloader.android.data_source.FileLoaderDataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class MainInteractorImplTest extends FileLoaderBaseTest {
    private static final String ERROR_MESSAGE = "message";
    private static final String ERROR_CODE = "code";
    private MainInteractorImpl interactor;

    @Mock
    FileLoaderDataSource dataSource;
    @Mock
    MainInteractor.ResponseListener responseListener;
    @Captor
    ArgumentCaptor<Listener<List<UserImage>>> userImageListListener;
    @Mock
    List<UserImage> userImages;
    @Mock
    ServiceError error;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        when(fileLoaderModule.provideFileDataSource()).thenReturn(dataSource);
        interactor = new MainInteractorImpl();
    }

    @Test
    public void testGetImagesSuccess() {
        interactor.getImages(responseListener);
        verify(dataSource).getImages(userImageListListener.capture());
        userImageListListener.getValue().onSuccess(userImages);
        verify(responseListener).onSuccess(userImages);
    }

    @Test
    public void testGetImagesFailure() {
        when(error.getErrorMessage()).thenReturn(ERROR_MESSAGE);
        when(error.getErrorCode()).thenReturn(ERROR_CODE);
        interactor.getImages(responseListener);
        verify(dataSource).getImages(userImageListListener.capture());
        userImageListListener.getValue().onError(error);
        verify(error).getErrorMessage();
        verify(responseListener).onError(eq(ERROR_MESSAGE));
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(dataSource, responseListener, userImages, error);
    }
}

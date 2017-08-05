package com.fileloader.android.presenter;

import com.fileloader.android.FileLoaderBaseTest;
import com.fileloader.android.model.ImageModelAdapter;
import com.fileloader.android.model.MainInteractor;
import com.fileloader.android.model.UserImage;
import com.fileloader.android.view.MainView;

import org.junit.After;
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
public class MainPresenterImplTest extends FileLoaderBaseTest {
    private static final String ERROR_MESSAGE = "message";
    private MainPresenterImpl presenter;

    @Mock
    MainInteractor interactor;
    @Mock
    MainView mainView;
    @Captor
    ArgumentCaptor<ImageModelAdapter> modelAdaptorCaptor;
    @Mock
    List<UserImage> userImages;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        when(fileLoaderModule.provideMainInteractor()).thenReturn(interactor);
        presenter = MainPresenterImpl.create(mainView);
    }

    @Test
    public void testHandleOnViewCreated() {
        presenter.handleOnViewCreated();
        verify(mainView).initViews(modelAdaptorCaptor.capture());
        verify(interactor).getImages(eq(presenter));
    }

    @Test
    public void testOnSuccess() {
        presenter.onSuccess(userImages);
        verify(mainView).updateAdapter(modelAdaptorCaptor.capture());
    }

    @Test
    public void testOnError() {
        presenter.onError(ERROR_MESSAGE);
        verify(mainView).showError(eq(ERROR_MESSAGE));
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(mainView, interactor, userImages);
    }
}

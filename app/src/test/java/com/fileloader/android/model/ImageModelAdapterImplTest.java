package com.fileloader.android.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class ImageModelAdapterImplTest {
    private static final int POSITION = 0;
    private static final String URL = "https://images.unsplash.com/photo-1464550883968-cec281c19761?ixlib=rb-0.3.5\\u0026q=80\\u0026fm=jpg\\u0026crop=entropy\\u0026w=1080\\u0026fit=max\\u0026s=1881cd689e10e5dca28839e68678f432";
    private ImageModelAdapterImpl modelAdapter;
    private List<UserImage> imageList;

    @Mock
    ImageUrl imageUrl;
    @Mock
    UserImage userImage;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(userImage.getImageUrl()).thenReturn(imageUrl);
        when(imageUrl.getRegular()).thenReturn(URL);

        imageList = new ArrayList<>();
        imageList.add(userImage);

        modelAdapter = ImageModelAdapterImpl.from(imageList);
    }

    @Test
    public void testGetCount() {
        modelAdapter.getCount();
        assertEquals(modelAdapter.getCount(), 1);
    }

    @Test
    public void testGetImageUrl() {
        modelAdapter.getImageUrl(POSITION);
        assertEquals(modelAdapter.getImageUrl(POSITION), URL);
    }
}

package com.fileloader.android.core.di;

import android.app.Fragment;
import android.content.Context;

public class CoreInjector {
    public static CoreComponent from(Fragment fragment) {
        return from(fragment.getActivity());
    }

    @SuppressWarnings("unchecked")
    public static CoreComponent from(Context context) {
        CoreComponentProvider provider = (CoreComponentProvider) context.getApplicationContext();
        return provider.getCoreComponent();
    }
}

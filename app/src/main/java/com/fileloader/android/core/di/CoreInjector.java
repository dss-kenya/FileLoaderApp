package com.fileloader.android.core.di;

import android.content.Context;

public class CoreInjector {
    @SuppressWarnings("unchecked")
    public static CoreComponent from(Context context) {
        CoreComponentProvider provider = (CoreComponentProvider) context.getApplicationContext();
        return provider.getCoreComponent();
    }
}

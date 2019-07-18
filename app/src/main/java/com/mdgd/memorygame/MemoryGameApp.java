package com.mdgd.memorygame;

import android.app.Application;

import com.mdgd.memorygame.core.AppScope;
import com.mdgd.memorygame.core.Provider;

public class MemoryGameApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppScope.setProvider(new Provider(this));
    }
}

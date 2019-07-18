package com.mdgd.memorygame.core;

import android.content.Context;

import com.mdgd.memorygame.core.cache.CacheImpl;
import com.mdgd.memorygame.core.cache.ICache;
import com.mdgd.memorygame.core.network.INetwork;
import com.mdgd.memorygame.core.network.NetworkImpl;

public class Provider implements IProvider {
    private final Context appCtx;
    private final CacheImpl cache;

    public Provider(Context appCtx) {
        this.appCtx = appCtx;
        cache = new CacheImpl();
    }

    @Override
    public INetwork getNetwork() {
        return new NetworkImpl();
    }

    @Override
    public ICache getCache() {
        return cache;
    }
}

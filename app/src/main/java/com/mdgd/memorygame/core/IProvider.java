package com.mdgd.memorygame.core;

import com.mdgd.memorygame.core.cache.ICache;
import com.mdgd.memorygame.core.network.INetwork;

public interface IProvider {
    INetwork getNetwork();

    ICache getCache();
}

package com.mdgd.memorygame.core;

public class AppScope {
    private static IProvider provider;

    public static IProvider getProvider() {
        return provider;
    }

    public static void setProvider(IProvider provider) {
        AppScope.provider = provider;
    }
}

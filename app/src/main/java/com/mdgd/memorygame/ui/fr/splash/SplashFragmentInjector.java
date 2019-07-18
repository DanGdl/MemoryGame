package com.mdgd.memorygame.ui.fr.splash;

import com.mdgd.memorygame.core.AppScope;
import com.mdgd.memorygame.core.IProvider;

public class SplashFragmentInjector {
    public static SplashFragmentContract.IPresenter getCreatePresenter(SplashFragmentContract.IView view) {
        final IProvider provider = AppScope.getProvider();
        return new SplashPresenter(view, provider.getNetwork(), provider.getCache());
    }
}

package com.mdgd.memorygame.ui.fr.game;

import com.mdgd.memorygame.core.AppScope;
import com.mdgd.memorygame.core.IProvider;

public class GameFragmentInjector {
    public static GameFragmentContract.IPresenter getCreatePresenter(GameFragmentContract.IView view) {
        final IProvider provider = AppScope.getProvider();
        return new GamePresenter(view, provider.getNetwork(), provider.getCache());
    }
}

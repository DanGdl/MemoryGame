package com.mdgd.memorygame.ui.fr.splash;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mdgd.j_commons.fragment.HostedFragment;
import com.mdgd.memorygame.R;

/**
 * Created by Owner
 * on 07/10/2018.
 */
public class SplashFragment extends HostedFragment<SplashFragmentContract.IPresenter, SplashFragmentContract.IHost>
        implements SplashFragmentContract.IView {

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Override
    protected SplashFragmentContract.IPresenter getPresenter() {
        return SplashFragmentInjector.getCreatePresenter(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_splash;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.loadPersonalities();
    }

    @Override
    public void proceedToGame() {
        if (host != null) host.proceedToGame();
    }
}

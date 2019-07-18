package com.mdgd.memorygame.ui.fr.splash;

import com.mdgd.commons.contract.fragment.FragmentContract;

/**
 * Created by Owner
 * on 07/10/2018.
 */
public class SplashFragmentContract {

    public interface IPresenter extends FragmentContract.IPresenter {
        void loadPersonalities();
    }

    public interface IView extends FragmentContract.IView {
        void proceedToGame();

        String getString(int strResId, Object... message);

        void showToast(String string);
    }

    public interface IHost extends FragmentContract.IHost {
        void proceedToGame();
    }
}

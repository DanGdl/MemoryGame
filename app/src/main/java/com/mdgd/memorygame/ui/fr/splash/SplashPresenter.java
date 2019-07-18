package com.mdgd.memorygame.ui.fr.splash;

import com.mdgd.commons.result.Result;
import com.mdgd.j_commons.fragment.FragmentPresenter;
import com.mdgd.memorygame.R;
import com.mdgd.memorygame.core.cache.ICache;
import com.mdgd.memorygame.core.network.INetwork;
import com.mdgd.memorygame.dto.Personalities;

/**
 * Created by Owner
 * on 07/10/2018.
 */
public class SplashPresenter extends FragmentPresenter<SplashFragmentContract.IView> implements SplashFragmentContract.IPresenter {


    private final INetwork network;
    private final ICache cache;

    public SplashPresenter(SplashFragmentContract.IView view, INetwork network, ICache cache) {
        super(view);
        this.network = network;
        this.cache = cache;
    }

    @Override
    public void loadPersonalities() {
        network.loadPersonalities((Result<Personalities> result) -> {
            if (result.isFail()) {
                view.showToast(view.getString(R.string.request_failed, result.error.getMessage()));
            } else {
                cache.putPersonalitiesCount(result.data.getInfo().getCount());
                view.proceedToGame();
            }
        });
    }
}

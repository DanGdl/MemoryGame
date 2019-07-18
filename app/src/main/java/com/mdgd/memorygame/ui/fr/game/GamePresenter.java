package com.mdgd.memorygame.ui.fr.game;

import com.mdgd.j_commons.fragment.FragmentPresenter;
import com.mdgd.memorygame.R;
import com.mdgd.memorygame.core.cache.ICache;
import com.mdgd.memorygame.core.network.INetwork;
import com.mdgd.memorygame.dto.GameTab;
import com.mdgd.memorygame.dto.Personality;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Owner
 * on 07/10/2018.
 */
public class GamePresenter extends FragmentPresenter<GameFragmentContract.IView> implements GameFragmentContract.IPresenter {

    private final int PAIRS_COUNT = 50;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final INetwork network;
    private final ICache cache;
    private GameTab firstTab;
    private int firstPosition = GameFragmentContract.DEF_POSITION;
    private int count = 0;

    GamePresenter(GameFragmentContract.IView view, INetwork network, ICache cache) {
        super(view);
        this.network = network;
        this.cache = cache;
    }

    @Override
    public void createGame() {
        view.showProgress(R.string.empty, R.string.creating_game);
        final int personalitiesCount = cache.getPersonalitiesCount();
        final Random random = new Random();
        final List<Integer> ids = new ArrayList<>();

        int id = random.nextInt(personalitiesCount);
        while (ids.size() < 50) {
            while (ids.contains(id)) {
                id = random.nextInt(personalitiesCount);
            }
            ids.add(id);
        }
        // caching disconnected
        disposables.add(Observable.fromIterable(ids)
                .map((Integer personalityId) -> network.loadPersonality(personalityId))
                .map((Personality personality) -> new GameTab(personality))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .collectInto(new ArrayList<>(), (ArrayList<GameTab> objects, GameTab gameTab) -> {
                    objects.add(gameTab);
                    objects.add(gameTab.clone());
                })
                .subscribe((ArrayList<GameTab> gameTabs, Throwable throwable) -> {
                    view.hideProgress();
                    if (throwable != null) {
                        throwable.printStackTrace();
                        view.showToast(view.getString(R.string.request_failed, throwable.getMessage()));
                    } else {
                        Collections.shuffle(gameTabs);
                        view.showGame(gameTabs);
                    }
                }));
    }

    @Override
    public void onTabSelected(GameTab item, int position) {
        if (firstTab == null) {
            firstTab = item;
            this.firstPosition = position;
            return;
        }
        count++;
        if (count >= PAIRS_COUNT) {
            view.showGameLost();
        } else {
            if (firstTab.getId() != item.getId()) view.closeTabsDelayed(firstPosition, position);
            firstTab = null;
            firstPosition = GameFragmentContract.DEF_POSITION;
            if (view.areAllTabsOpened()) view.showGameWon();
        }
    }

    @Override
    public void stopLoading() {
        if (disposables.isDisposed() || disposables.size() == 0) return;
        disposables.dispose();
        disposables.clear();
    }
}

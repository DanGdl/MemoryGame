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

    private final int TOTAL_CARDS = 36;
    private final int SAME_CARDS = 3;
    private final int TOTAL_TRYOUTS = 50;
    private final int DEF_CARD_ID = -1;

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final INetwork network;
    private final ICache cache;
    private final List<Integer> openPositions = new ArrayList<>(SAME_CARDS);

    private int cardId = DEF_CARD_ID;
    private int tryouts = 0;

    GamePresenter(GameFragmentContract.IView view, INetwork network, ICache cache) {
        super(view);
        this.network = network;
        this.cache = cache;
    }

    @Override
    public int getSpanCount() {
        return (int)Math.sqrt(TOTAL_CARDS);
    }

    @Override
    public void createGame() {
        view.showProgress(R.string.empty, R.string.creating_game);
        final int personalitiesCount = cache.getPersonalitiesCount();
        final Random random = new Random();
        final List<Integer> ids = new ArrayList<>();

        int id = random.nextInt(personalitiesCount);
        while (ids.size() < TOTAL_CARDS / SAME_CARDS) {
            while (ids.contains(id)) {
                id = random.nextInt(personalitiesCount);
            }
            ids.add(id);
        }
        // caching disconnected
        disposables.add(Observable.fromIterable(ids)
                .map((Integer personalityId) -> {
                    if(cache.hasPersonality(personalityId))
                        return cache.getPersonality(personalityId);
                    else return network.loadPersonality(personalityId);
                })
                .map((Personality personality) -> {
                    if(!cache.hasPersonality(personality.getId()))
                        cache.putPersonality(personality);
                    return new GameTab(personality);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .collectInto(new ArrayList<>(), (ArrayList<GameTab> objects, GameTab gameTab) -> {
                    objects.add(gameTab);
                    for (int i = 1; i < SAME_CARDS; i++) {
                        objects.add(gameTab.clone());
                    }
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
        if (cardId == DEF_CARD_ID) {
            cardId = item.getId();
            openPositions.add(position);
            return;
        }
        tryouts++;
        if (tryouts >= TOTAL_TRYOUTS) {
            view.showGameLost();
        } else {
            if (cardId != item.getId()) {
                openPositions.add(position);
                view.closeTabsDelayed(new ArrayList<>(openPositions));
            }
            else if(openPositions.size() < SAME_CARDS) {
                openPositions.add(position);
                if(openPositions.size() != SAME_CARDS) return;
            }
            cardId = DEF_CARD_ID;
            openPositions.clear();
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

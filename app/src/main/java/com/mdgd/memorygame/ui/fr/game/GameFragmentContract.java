package com.mdgd.memorygame.ui.fr.game;

import com.mdgd.commons.contract.fragment.FragmentContract;
import com.mdgd.memorygame.dto.GameTab;

import java.util.List;

/**
 * Created by Owner
 * on 07/10/2018.
 */
public class GameFragmentContract {

    public interface IPresenter extends FragmentContract.IPresenter {

        int getSpanCount();

        void createGame();

        void onTabSelected(GameTab item, int position);

        void stopLoading();
    }

    public interface IView extends FragmentContract.IView {

        void showGame(List<GameTab> gameTabs);

        void showGameLost();

        void showGameWon();

        boolean areAllTabsOpened();

        String getString(int strResId, Object... message);

        void showToast(String string);

        void closeTabsDelayed(List<Integer> openPositions);
    }

    public interface IHost extends FragmentContract.IHost {

        void showGameLost();

        void showGameWon();
    }
}

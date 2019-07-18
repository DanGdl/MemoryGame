package com.mdgd.memorygame.ui.fr.game;

import com.mdgd.commons.contract.fragment.FragmentContract;
import com.mdgd.memorygame.dto.GameTab;

import java.util.List;

/**
 * Created by Owner
 * on 07/10/2018.
 */
public class GameFragmentContract {
    public static final int DEF_POSITION = -1;

    public interface IPresenter extends FragmentContract.IPresenter {

        void createGame();

        void onTabSelected(GameTab item, int position);

        void stopLoading();
    }

    public interface IView extends FragmentContract.IView {
        void closeTabsDelayed(int firstPosition, int position);

        void showGame(List<GameTab> gameTabs);

        void showGameLost();

        void showGameWon();

        boolean areAllTabsOpened();
    }

    public interface IHost extends FragmentContract.IHost {

        void showGameLost();

        void showGameWon();
    }
}

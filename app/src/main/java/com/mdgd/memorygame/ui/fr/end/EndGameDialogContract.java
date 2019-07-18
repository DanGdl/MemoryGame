package com.mdgd.memorygame.ui.fr.end;

import com.mdgd.commons.contract.fragment.FragmentContract;

public class EndGameDialogContract {

    public interface IHost extends FragmentContract.IHost {

        void restartGame();

        void exit();
    }
}

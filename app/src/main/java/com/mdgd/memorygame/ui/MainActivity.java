package com.mdgd.memorygame.ui;

import androidx.fragment.app.Fragment;

import com.mdgd.commons.contract.mvp.ViewContract;
import com.mdgd.j_commons.fragment.HostActivity;
import com.mdgd.memorygame.ui.fr.end.EndGameDialog;
import com.mdgd.memorygame.ui.fr.end.EndGameDialogContract;
import com.mdgd.memorygame.ui.fr.game.GameFragment;
import com.mdgd.memorygame.ui.fr.game.GameFragmentContract;
import com.mdgd.memorygame.ui.fr.splash.SplashFragment;
import com.mdgd.memorygame.ui.fr.splash.SplashFragmentContract;

public class MainActivity extends HostActivity implements SplashFragmentContract.IHost,
        GameFragmentContract.IHost, EndGameDialogContract.IHost {

    @Override
    protected ViewContract.IPresenter getPresenter() {
        return null;
    }

    @Override
    protected Fragment getFirstFragment() {
        return SplashFragment.newInstance();
    }

    @Override
    public void proceedToGame() {
        replaceFragment(GameFragment.newInstance());
    }

    @Override
    public void showGameLost() {
        EndGameDialog.newInstance(false).show(getSupportFragmentManager(), "");
    }

    @Override
    public void showGameWon() {
        EndGameDialog.newInstance(true).show(getSupportFragmentManager(), "");
    }

    @Override
    public void restartGame() {
        proceedToGame();
    }

    @Override
    public void exit() {
        finish();
    }
}

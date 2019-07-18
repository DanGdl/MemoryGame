package com.mdgd.memorygame.ui.fr.game;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.mdgd.j_commons.recycler.CommonRecyclerAdapter;
import com.mdgd.j_commons.recycler_fragment.RecyclerFragment;
import com.mdgd.memorygame.dto.GameTab;

import java.util.List;

/**
 * Created by Owner
 * on 07/10/2018.
 */
public class GameFragment extends RecyclerFragment<GameFragmentContract.IPresenter, GameFragmentContract.IHost, GameTab>
        implements GameFragmentContract.IView {

    public static GameFragment newInstance() {
        return new GameFragment();
    }

    @Override
    protected GameFragmentContract.IPresenter getPresenter() {
        return GameFragmentInjector.getCreatePresenter(this);
    }

    @Override
    protected CommonRecyclerAdapter<GameTab> getAdapter() {
        return new GameTabAdapter(getActivity(), this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recycler.setLayoutManager(new GridLayoutManager(getActivity(), 10));
        presenter.createGame();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stopLoading();
    }

    @Override
    public void onItemClicked(GameTab item, int position) {
        presenter.onTabSelected(item, position);
    }

    @Override
    public void closeTabsDelayed(int firstPosition, int position) {
        recycler.postDelayed(() -> ((GameTabAdapter) adapter).closeTabs(firstPosition, position), 1000);
    }

    @Override
    public void showGame(List<GameTab> gameTabs) {
        adapter.addItems(gameTabs);
    }

    @Override
    public void showGameLost() {
        if (host != null) host.showGameLost();
    }

    @Override
    public void showGameWon() {
        if (host != null) host.showGameWon();
    }

    @Override
    public boolean areAllTabsOpened() {
        return ((GameTabAdapter) adapter).areAllTabsOpened();
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
    }
}

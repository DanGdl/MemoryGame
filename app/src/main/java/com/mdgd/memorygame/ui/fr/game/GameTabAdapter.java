package com.mdgd.memorygame.ui.fr.game;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mdgd.j_commons.recycler.CommonRecyclerAdapter;
import com.mdgd.j_commons.recycler.CommonViewHolder;
import com.mdgd.memorygame.R;
import com.mdgd.memorygame.dto.GameTab;
import com.mdgd.memorygame.util.ImageLoader;

public class GameTabAdapter extends CommonRecyclerAdapter<GameTab> {

    GameTabAdapter(Context context, IOnItemClickListener<GameTab> listener) {
        super(context, listener);
    }

    @NonNull
    @Override
    public CommonViewHolder<GameTab> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GameTabVH(inflater.inflate(R.layout.item_game_tab, parent, false));
    }

    void closeTabs(int firstPosition, int position) {
        if (firstPosition != GameFragmentContract.DEF_POSITION) {
            items.get(firstPosition).setIsOpened(false);
            notifyItemChanged(firstPosition);
        }
        if (position != GameFragmentContract.DEF_POSITION) {
            items.get(position).setIsOpened(false);
            notifyItemChanged(position);
        }
    }

    boolean areAllTabsOpened() {
        for (GameTab tab : items) {
            if (!tab.getIsOpened()) return false;
        }
        return true;
    }


    private class GameTabVH extends CommonViewHolder<GameTab> implements View.OnClickListener {

        private final ImageView image;
        private final TextView name;

        GameTabVH(View view) {
            super(view);
            image = view.findViewById(R.id.personalityImg);
            name = view.findViewById(R.id.personalityName);
            view.setOnClickListener(this);
        }

        @Override
        public void bindItem(GameTab item, int position) {
            if (item.getIsOpened()) {
                image.setVisibility(View.VISIBLE);
                name.setVisibility(View.VISIBLE);
                name.setText(item.getName());
                ImageLoader.load(item.getUrl(), image, R.drawable.ic_no_portrait_24dp);
            } else {
                image.setVisibility(View.INVISIBLE);
                name.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onClick(View view) {
            final int position = getAdapterPosition();
            final GameTab gameTab = items.get(position);
            if (gameTab.getIsOpened()) return;

            gameTab.setIsOpened(!gameTab.getIsOpened());
            notifyItemChanged(position);
            if (listener != null) listener.onItemClicked(gameTab, position);
        }
    }
}

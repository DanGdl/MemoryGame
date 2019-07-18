package com.mdgd.memorygame.ui.fr.end;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.mdgd.memorygame.R;

public class EndGameDialog extends DialogFragment {

    private static final String KEY_IS_WIN = "key_is_win";
    private EndGameDialogContract.IHost host;

    public static EndGameDialog newInstance(boolean isWin) {
        final Bundle args = new Bundle();
        args.putInt(KEY_IS_WIN, isWin ? 1 : 0);
        final EndGameDialog dialog = new EndGameDialog();
        dialog.setArguments(args);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.start_new_game);
        final Bundle args = getArguments();
        builder.setTitle(args.getInt(KEY_IS_WIN) == 1 ? R.string.you_won : R.string.you_lose);
        builder.setCancelable(false);
        builder.setNegativeButton(R.string.exit, (DialogInterface dialogInterface, int i) -> {
            dialogInterface.dismiss();
            if (host != null) host.exit();
        });

        builder.setPositiveButton(R.string.new_game, (DialogInterface dialogInterface, int i) -> {
            dialogInterface.dismiss();
            if (host != null) host.restartGame();
        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        host = (EndGameDialogContract.IHost) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        host = null;
    }
}

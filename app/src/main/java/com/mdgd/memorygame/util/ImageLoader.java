package com.mdgd.memorygame.util;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageLoader {
    public static void load(String url, ImageView into, int errorResId) {
        Picasso.get().load(url).error(errorResId).into(into);
    }
}

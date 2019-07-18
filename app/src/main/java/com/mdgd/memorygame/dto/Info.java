package com.mdgd.memorygame.dto;

import com.google.gson.annotations.SerializedName;

public class Info {
    @SerializedName("count")
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

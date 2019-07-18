package com.mdgd.memorygame.dto;

import com.google.gson.annotations.SerializedName;

public class Personalities {
    @SerializedName("info")
    private Info info;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}

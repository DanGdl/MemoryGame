package com.mdgd.memorygame.dto;

import androidx.annotation.NonNull;

import java.util.Objects;

public class GameTab implements Cloneable {
    private final String name;
    private final String url;
    private int id;
    private boolean isOpened = false;

    public GameTab(Personality personality) {
        id = personality.getId();
        name = personality.getName();
        url = personality.getImageUrl();
    }

    public GameTab(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public boolean getIsOpened() {
        return isOpened;
    }

    public void setIsOpened(boolean isOpened) {
        this.isOpened = isOpened;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameTab gameTab = (GameTab) o;
        return id == gameTab.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @NonNull
    @Override
    public GameTab clone() {
        return new GameTab(getId(), getName(), getUrl());
    }
}

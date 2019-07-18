package com.mdgd.memorygame.dto;

import java.util.Objects;

public class GameTab {
    private final String name;
    private final String url;
    private int id;
    private boolean isOpened;

    public GameTab(Personality personality) {
        id = personality.getId();
        name = personality.getName();
        url = personality.getImageUrl();
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
}

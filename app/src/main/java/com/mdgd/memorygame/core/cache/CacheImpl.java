package com.mdgd.memorygame.core.cache;

import com.mdgd.memorygame.dto.Personality;

import java.util.HashMap;
import java.util.Map;

public class CacheImpl implements ICache {

    private final Map<Integer, Personality> personalities = new HashMap<>();
    private int count;

    @Override
    public void putPersonalitiesCount(int count) {
        this.count = count;
    }

    @Override
    public int getPersonalitiesCount() {
        return count;
    }

    @Override
    public boolean hasPersonality(Integer personalityId) {
        return personalities.get(personalityId) != null;
    }

    @Override
    public Personality getPersonality(int personalityId) {
        return personalities.get(personalityId);
    }

    @Override
    public void putPersonality(Personality personality) {
        personalities.put(personality.getId(), personality);
    }
}

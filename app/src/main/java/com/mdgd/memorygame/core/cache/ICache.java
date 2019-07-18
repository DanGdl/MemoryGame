package com.mdgd.memorygame.core.cache;

import com.mdgd.memorygame.dto.Personality;

public interface ICache {
    void putPersonalitiesCount(int count);

    int getPersonalitiesCount();

    boolean hasPersonality(Integer personalityId);

    Personality getPersonality(int personalityId);

    void putPersonality(Personality personality);
}

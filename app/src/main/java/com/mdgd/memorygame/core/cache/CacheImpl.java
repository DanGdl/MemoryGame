package com.mdgd.memorygame.core.cache;

import com.mdgd.memorygame.dto.Personality;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheImpl implements ICache {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
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
        lock.readLock().lock();
        try {
            return personalities.get(personalityId) != null;
        }finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public Personality getPersonality(int personalityId) {
        lock.readLock().lock();
        try {
            return personalities.get(personalityId);
        }finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void putPersonality(Personality personality) {
        lock.writeLock().lock();
        try {
            personalities.put(personality.getId(), personality);
        }finally {
            lock.writeLock().unlock();
        }
    }
}

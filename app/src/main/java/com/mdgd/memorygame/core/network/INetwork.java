package com.mdgd.memorygame.core.network;

import com.mdgd.commons.result.ICallback;
import com.mdgd.memorygame.dto.Personalities;
import com.mdgd.memorygame.dto.Personality;

import java.io.IOException;

public interface INetwork {
    void loadPersonalities(ICallback<Personalities> callback);

    Personality loadPersonality(int personalityId) throws IOException;
}

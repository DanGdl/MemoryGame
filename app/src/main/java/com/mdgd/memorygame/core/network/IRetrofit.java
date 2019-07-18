package com.mdgd.memorygame.core.network;

import com.mdgd.memorygame.dto.Personalities;
import com.mdgd.memorygame.dto.Personality;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IRetrofit {

    @GET("api/character/")
    Call<Personalities> getPersonalities();

    @GET("api/character/{id}")
    Call<Personality> getPersonality(@Path("id") String id);
}

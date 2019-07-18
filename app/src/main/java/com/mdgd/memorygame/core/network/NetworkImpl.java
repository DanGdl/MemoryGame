package com.mdgd.memorygame.core.network;

import com.mdgd.commons.result.ICallback;
import com.mdgd.commons.retrofit_support.BasicNetwork;
import com.mdgd.memorygame.BuildConfig;
import com.mdgd.memorygame.dto.Personalities;
import com.mdgd.memorygame.dto.Personality;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkImpl extends BasicNetwork implements INetwork {

    private final IRetrofit service;

    public NetworkImpl() {
        // https://rickandmortyapi.com/api/character/
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        httpClient.readTimeout(10, TimeUnit.SECONDS);
        httpClient.writeTimeout(10, TimeUnit.SECONDS);
        httpClient.connectTimeout(10, TimeUnit.SECONDS);

        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .baseUrl("https://rickandmortyapi.com/")
                .build();

        service = retrofit.create(IRetrofit.class);
    }

    @Override
    public void loadPersonalities(ICallback<Personalities> callback) {
        execAsync(service.getPersonalities(), callback);
    }

    @Override
    public Personality loadPersonality(int personalityId) throws IOException {
        final Response<Personality> execute = service.getPersonality(String.valueOf(personalityId)).execute();
        return execute.body();
}
}

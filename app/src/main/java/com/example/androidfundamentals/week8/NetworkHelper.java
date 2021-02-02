package com.example.androidfundamentals.week8;

import com.example.androidfundamentals.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

final class NetworkHelper {

    private static Retrofit retrofit;

    private static final String BASE_URL = "https://api.themoviedb.org/";
    private static final String API_KEY = "0b808e56d790e7192b1fd1c794964caa";

    static Retrofit getRetrofit() {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = getClient();

            httpClient.addInterceptor(new QueryInterceptor("api_key", API_KEY));
//            httpClient.addInterceptor(new QueryInterceptor("language", "en-US"));

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient.Builder getClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(interceptor);
        }
        return client;
    }



}
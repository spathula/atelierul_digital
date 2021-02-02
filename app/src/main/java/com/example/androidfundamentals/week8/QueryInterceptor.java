package com.example.androidfundamentals.week8;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public final class QueryInterceptor implements Interceptor {

    private final String name;
    private final String value;

    public QueryInterceptor(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request originalRequest = chain.request();

        HttpUrl.Builder urlBuilder = originalRequest.url().newBuilder();
        urlBuilder.addQueryParameter(name, value);

        Request request = originalRequest.newBuilder()
                .url(urlBuilder.build())
                .build();

        return chain.proceed(request);
    }
}

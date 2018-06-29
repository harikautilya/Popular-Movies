package com.movies.book.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.movies.book.utils.Constants.URL;

public class RequestFactory {


    public static <T> T createRetroFitService(final Class<T> clazz) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .client(client)
                .build();
        return retrofit.create(clazz);
    }
}

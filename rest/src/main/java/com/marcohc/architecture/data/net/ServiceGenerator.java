package com.marcohc.architecture.data.net;

import android.util.Base64;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class ServiceGenerator {

    // No need to instantiate this class.
    private ServiceGenerator() {
    }

    public static <S> S createService(Class<S> serviceClass, Converter.Factory factory, String baseUrl) {
        return createService(serviceClass, factory, baseUrl, null, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, Converter.Factory factory, String baseUrl, String token) {
        return createService(serviceClass, factory, baseUrl, null, null, token);
    }

    public static <S> S createService(Class<S> serviceClass, Converter.Factory factory, String baseUrl, String username, String password) {
        return createService(serviceClass, factory, baseUrl, username, password, null);
    }

    public static <S> S createService(Class<S> serviceClass, Converter.Factory factory, String baseUrl, String username, String password, final String token) {

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(8, TimeUnit.SECONDS)
                .writeTimeout(8, TimeUnit.SECONDS)
                .readTimeout(8, TimeUnit.SECONDS);

        // Add logging
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);

        if (username != null && password != null) {
            addBasicAuthentication(username, password, okHttpClientBuilder);
        }

        if (token != null) {
            addTokenAuthentication(token, okHttpClientBuilder);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(factory)
                .client(okHttpClientBuilder.build())
                .build();

        return retrofit.create(serviceClass);
    }

    private static void addTokenAuthentication(final String token, OkHttpClient.Builder okHttpClientBuilder) {
        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                request.newBuilder().addHeader("Authorization", "Token " + token);
                return chain.proceed(request);
            }
        });
    }

    private static void addBasicAuthentication(String username, String password, OkHttpClient.Builder okHttpClientBuilder) {
        // concatenate username and password with colon for authentication
        final String credentials = username + ":" + password;
        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                String string = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                request.newBuilder().addHeader("Authorization", string);
                return chain.proceed(request);
            }
        });
    }
}

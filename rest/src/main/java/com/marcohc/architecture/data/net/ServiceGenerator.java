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

    private static long connectTimeOut = 10;
    private static long writeTimeOut = 10;
    private static long readTimeOut = 10;
    private static ServiceGenerator instance;

    public static ServiceGenerator getInstance() {
        if (instance == null) {
            instance = new ServiceGenerator();
        }
        return instance;
    }

    // No need to instantiate this class.
    private ServiceGenerator() {
    }

    public <S> S createService(Class<S> serviceClass, Converter.Factory factory, String baseUrl) {
        return createService(serviceClass, factory, baseUrl, null, null, null);
    }

    public <S> S createService(Class<S> serviceClass, Converter.Factory factory, String baseUrl, String token) {
        return createService(serviceClass, factory, baseUrl, null, null, token);
    }

    public <S> S createService(Class<S> serviceClass, Converter.Factory factory, String baseUrl, String username, String password) {
        return createService(serviceClass, factory, baseUrl, username, password, null);
    }

    public <S> S createService(Class<S> serviceClass, Converter.Factory factory, String baseUrl, String username, String password, final String token) {

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(connectTimeOut, TimeUnit.SECONDS)
                .writeTimeout(writeTimeOut, TimeUnit.SECONDS)
                .readTimeout(readTimeOut, TimeUnit.SECONDS);

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

    public static void setConnectTimeOut(long connectTimeOut) {
        ServiceGenerator.connectTimeOut = connectTimeOut;
    }

    public static void setWriteTimeOut(long writeTimeOut) {
        ServiceGenerator.writeTimeOut = writeTimeOut;
    }

    public static void setReadTimeOut(long readTimeOut) {
        ServiceGenerator.readTimeOut = readTimeOut;
    }

    private void addTokenAuthentication(final String token, OkHttpClient.Builder okHttpClientBuilder) {
        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .header("Authorization", "Token " + token)
                        .header("Accept", "application/json")
                        .method(originalRequest.method(), originalRequest.body());
                Request newRequest = requestBuilder.build();
                return chain.proceed(newRequest);
            }
        });
    }

    private void addBasicAuthentication(String username, String password, OkHttpClient.Builder okHttpClientBuilder) {
        // concatenate username and password with colon for authentication
        final String credentials = username + ":" + password;
        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                String basicAuth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .header("Authorization", basicAuth)
                        .header("Accept", "application/json")
                        .method(originalRequest.method(), originalRequest.body());
                Request newRequest = requestBuilder.build();
                return chain.proceed(newRequest);
            }
        });
    }


}

package com.marcohc.architecture.app.data.net;

import android.util.Base64;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class ServiceGenerator {

    private static final String LOG_TAG = "ServiceGenerator";

    // No need to instantiate this class.
    private ServiceGenerator() {
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        // call basic auth generator method without user and pass
        return createService(serviceClass, baseUrl, null, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl, String token) {
        // call basic auth generator method without user and pass
        return createService(serviceClass, baseUrl, null, null, token);
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl, String username, String password) {
        // call basic auth generator method without user and pass
        return createService(serviceClass, baseUrl, username, password, null);
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl, String username, String password, final String token) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory()) // This is the important line ;)
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(8, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(8, TimeUnit.SECONDS);

        // set endpoint url and use OkHTTP as HTTP client
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog(LOG_TAG))
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(okHttpClient));

        if (username != null && password != null) {
            // concatenate username and password with colon for authentication
            final String credentials = username + ":" + password;

            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    // create Base64 encodet string
                    String string = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                    request.addHeader("Authorization", string);
                }
            });
        }

        if (token != null) {
            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader("Authorization", "Token " + token);
                }
            });
        }

        RestAdapter adapter = builder.build();

        return adapter.create(serviceClass);
    }
}

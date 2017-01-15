package com.marcohc.architecture.rx.data.net;

import android.support.annotation.Nullable;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Service generator for Retrofit.
 *
 * @author Marco Hernaiz
 * @since 09/08/16
 */
public final class ServiceFactory {

    private static final int CONNECTION_TIME_OUT = 10;
    private static final int WRITE_TIME_OUT = 10;
    private static final int READ_TIME_OUT = 10;

    // No need to instantiate this class.
    private ServiceFactory() {
    }

    /**
     * Create service with the given parameters.
     *
     * @param serviceClass the service class
     * @param factory the converter factory
     * @param baseUrl the base url
     * @param <S> the service class
     * @return an instance of the service with the specific type
     */
    public static <S> S createService(Class<S> serviceClass, Converter.Factory factory, String baseUrl) {
        return createService(serviceClass, factory, null, baseUrl);
    }

    /**
     * Create service with the given parameters.
     *
     * @param serviceClass the service class
     * @param factory the converter factory
     * @param adapter the response adapter
     * @param baseUrl the base url
     * @param <S> the service class
     * @return an instance of the service with the specific type
     */
    public static <S> S createService(Class<S> serviceClass, Converter.Factory factory, @Nullable CallAdapter.Factory adapter, String baseUrl) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);

        // Add logging
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(factory)
                .client(okHttpClientBuilder.build());

        if (adapter != null) {
            builder.addCallAdapterFactory(adapter);
        }

        Retrofit retrofit = builder.build();

        return retrofit.create(serviceClass);
    }

}

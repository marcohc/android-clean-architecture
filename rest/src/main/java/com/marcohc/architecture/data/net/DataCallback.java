package com.marcohc.architecture.data.net;

import com.marcohc.architecture.data.error.RestError;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public abstract class DataCallback<T> implements Callback<T> {

    public abstract void onFailure(RestError error);

    public abstract void onSuccess(T response);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        // response.isSuccess() is true if the response code is 2xx
        if (response.isSuccess()) {
            T body = response.body();
            onSuccess(body);
        }
        // handle request errors yourself
        else {
            int statusCode = response.code();
            ResponseBody errorBody = response.errorBody();
            RestError restError = new RestError("Error unknown", statusCode);
            try {
                restError.setMessage(response.errorBody().string());
            } catch (IOException e) {
                Timber.e("Error when parsing error response: " + e.getMessage());
            }
            onFailure(restError);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        // handle execution failures like no internet connectivity
        Timber.e("onFailure: " + t.getMessage());
        onFailure(new RestError(t.getMessage()));
    }
}

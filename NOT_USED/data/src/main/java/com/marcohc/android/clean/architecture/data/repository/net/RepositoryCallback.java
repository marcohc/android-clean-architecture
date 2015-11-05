package com.marcohc.android.clean.architecture.data.repository.net;

import android.util.Log;

import com.google.gson.Gson;
import com.marcohc.android.clean.architecture.exception.DataException;
import com.marcohc.android.clean.architecture.data.util.NetworkManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public abstract class RepositoryCallback<T> implements Callback<T> {

    public abstract void failure(DataException error);

    public abstract void success(T response);

    @Override
    public void failure(RetrofitError retrofitError) {
        try {
            DataException error = new DataException("Error unknown", -1);
            switch (retrofitError.getKind()) {
                case NETWORK:
                    error = new DataException("Network error: Check internet connection!", -1);
                    break;
                case CONVERSION:
                    error = new DataException("Conversion error: Check correct parsing!", -1);
                    break;
                case HTTP:
                    error = new DataException("Server error: " + retrofitError.getMessage(), retrofitError.getResponse() != null ? retrofitError.getResponse().getStatus() : -1);
                    break;
                case UNEXPECTED:
                    error = new DataException("Unexpected error: " + retrofitError.getMessage(), retrofitError.getResponse() != null ? retrofitError.getResponse().getStatus() : -1);
                    break;
            }
            failure(error);
        } catch (Exception e) {
            Log.wtf(NetworkManager.LOG_TAG, "Error when parsing response: " + e.getMessage());
            DataException error = new DataException("Check internet connection", -1);
            failure(error);
        }
    }

    @Override
    public void success(T type, Response response) {
        try {
            JSONObject jsonResponse = new JSONObject(new Gson().toJson(type));
            Log.d(NetworkManager.LOG_TAG, "Response: " + jsonResponse.toString());
            success((T) jsonResponse);
        } catch (JSONException ignored) {
        }

        try {
            JSONArray jsonArray = new JSONArray(new Gson().toJson(type));
            Log.d(NetworkManager.LOG_TAG, "Response: " + jsonArray.toString());
            success((T) jsonArray);
        } catch (JSONException ignored) {
        }
    }
}

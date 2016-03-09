package com.marcohc.architecture.data.net;

import com.google.gson.Gson;
import com.marcohc.architecture.data.error.RestError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public abstract class RestCallback<T> implements Callback<T> {

    public abstract void failure(RestError error);

    public abstract void success(T response);

    @Override
    public void failure(RetrofitError retrofitError) {
        try {
            RestError error = new RestError("Error unknown", -1);
            switch (retrofitError.getKind()) {
                case NETWORK:
                    error = new RestError("Network error: Check internet connection!", -1);
                    break;
                case CONVERSION:
                    error = new RestError("Conversion error: Check correct parsing!", -1);
                    break;
                case HTTP:
                    error = new RestError("Server error: " + retrofitError.getMessage(), retrofitError.getResponse() != null ? retrofitError.getResponse().getStatus() : -1);
                    break;
                case UNEXPECTED:
                    error = new RestError("Unexpected error: " + retrofitError.getMessage(), retrofitError.getResponse() != null ? retrofitError.getResponse().getStatus() : -1);
                    break;
            }
            failure(error);
        } catch (Exception e) {
            Timber.wtf("Error when parsing response: " + e.getMessage());
            RestError error = new RestError("Check internet connection", -1);
            failure(error);
        }
    }

    @Override
    public void success(T type, Response response) {

        // Parsing for JSONArrays
        try {
            JSONArray jsonArray = new JSONArray(new Gson().toJson(type));
            Timber.d("Response: " + jsonArray.toString());
            success((T) jsonArray);
            return;
        } catch (JSONException ignored) {
        }

        // Parsing for JSONObjects
        try {
            JSONObject jsonResponse = new JSONObject(new Gson().toJson(type));
            Timber.d("Response: " + jsonResponse.toString());
            success((T) jsonResponse);
            return;
        } catch (JSONException ignored) {
        }

        // For empty responses
        try {
            success((T) new JSONObject("{}"));
        } catch (JSONException ignored) {
        }
    }
}

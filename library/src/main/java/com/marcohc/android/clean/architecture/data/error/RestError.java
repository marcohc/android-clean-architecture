package com.marcohc.android.clean.architecture.data.error;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Representation of a Rest error. Happens in data layer
 */
public class RestError {

    @SerializedName("error_code")
    private Integer code;

    @SerializedName("error_detail")
    private String message;

    public RestError(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static RestError getError(JSONObject response) {

        RestError error = null;
        try {
            response = (JSONObject) response.get("error");
            error = new RestError(response.getString("error_detail"), response.getInt("error_code"));
        } catch (Exception ignored) {
        }

        return error;
    }
}

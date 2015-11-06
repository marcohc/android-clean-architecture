package com.marcohc.android.clean.architecture.common.exception;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class JsonDataException {

    @SerializedName("error_code")
    private Integer code;

    @SerializedName("error_detail")
    private String message;

    public JsonDataException(String message, Integer code) {
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

    public static JsonDataException getError(JSONObject response) {

        JsonDataException error = null;
        try {
            response = (JSONObject) response.get("error");
            error = new JsonDataException(response.getString("error_detail"), response.getInt("error_code"));
        } catch (Exception ignored) {
        }

        return error;
    }
}

package com.marcohc.android.clean.architecture.common.exception;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class DataError {

    @SerializedName("error_code")
    private Integer code;

    @SerializedName("error_detail")
    private String message;

    public DataError(String message, Integer code) {
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

    public static DataError getError(JSONObject response) {

        DataError error = null;
        try {
            response = (JSONObject) response.get("error");
            error = new DataError(response.getString("error_detail"), response.getInt("error_code"));
        } catch (Exception e) {
        }

        return error;
    }
}

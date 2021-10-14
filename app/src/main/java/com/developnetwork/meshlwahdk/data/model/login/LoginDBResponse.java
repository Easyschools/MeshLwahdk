package com.ivestment.doctorna.data.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.developnetwork.meshlwahdk.data.model.User;

public class LoginDBResponse {

    @SerializedName("userData")
    @Expose
    private User userData;
    @SerializedName("accessToken")
    @Expose
    private String accessToken;
    @SerializedName("tokenExpiry")
    @Expose
    private String tokenExpiry;

    @SerializedName("message")
    @Expose
    private String message;

    public User getUserData() {
        return userData;
    }

    public void setUserData(User userData) {
        this.userData = userData;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenExpiry() {
        return tokenExpiry;
    }

    public void setTokenExpiry(String tokenExpiry) {
        this.tokenExpiry = tokenExpiry;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

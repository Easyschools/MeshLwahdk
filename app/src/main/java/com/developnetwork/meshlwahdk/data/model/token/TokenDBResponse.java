
package com.ivestment.doctorna.data.model.token;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenDBResponse {

    @SerializedName("response")
    @Expose
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}

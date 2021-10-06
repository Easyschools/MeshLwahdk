
package com.ivestment.doctorna.data.model.notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationDBResponse {

    @SerializedName("response")
    @Expose
    private List<ResponseModelClass> response = null;

    public List<ResponseModelClass> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseModelClass> response) {
        this.response = response;
    }

}

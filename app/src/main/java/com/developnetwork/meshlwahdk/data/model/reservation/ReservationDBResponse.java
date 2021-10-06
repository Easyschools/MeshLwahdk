
package com.ivestment.doctorna.data.model.reservation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReservationDBResponse {

    @SerializedName("data")
    @Expose
    private ReservationModelClass data;
    @SerializedName("message")
    @Expose
    private String message;

    public ReservationModelClass getData() {
        return data;
    }

    public void setData(ReservationModelClass data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


package com.ivestment.doctorna.data.model.booking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ivestment.doctorna.data.model.AvailableAppointment;

import java.util.List;

public class BookingDBResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("number of doctors")
    @Expose
    private Integer numberOfDoctors;
    @SerializedName("data")
    @Expose
    private List<AvailableAppointment> data = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getNumberOfDoctors() {
        return numberOfDoctors;
    }

    public void setNumberOfDoctors(Integer numberOfDoctors) {
        this.numberOfDoctors = numberOfDoctors;
    }

    public List<AvailableAppointment> getData() {
        return data;
    }

    public void setData(List<AvailableAppointment> data) {
        this.data = data;
    }

}

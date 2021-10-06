
package com.ivestment.doctorna.data.model.booking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingModelClass {

    @SerializedName("numberOfAppointments")
    @Expose
    private Integer numberOfAppointments;
    @SerializedName("appointmentTime")
    @Expose
    private String appointmentTime;
    @SerializedName("timeZone")
    @Expose
    private String timeZone;
    @SerializedName("hasAppointment")
    @Expose
    private Boolean hasAppointment;

    public Integer getNumberOfAppointments() {
        return numberOfAppointments;
    }

    public void setNumberOfAppointments(Integer numberOfAppointments) {
        this.numberOfAppointments = numberOfAppointments;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Boolean getHasAppointment() {
        return hasAppointment;
    }

    public void setHasAppointment(Boolean hasAppointment) {
        this.hasAppointment = hasAppointment;
    }
}

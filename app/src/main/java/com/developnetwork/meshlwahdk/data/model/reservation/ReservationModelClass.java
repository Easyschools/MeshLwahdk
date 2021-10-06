
package com.ivestment.doctorna.data.model.reservation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReservationModelClass {

    @SerializedName("appointment_date")
    @Expose
    private String appointmentDate;
    @SerializedName("appointment_time")
    @Expose
    private Integer appointmentTime;
    @SerializedName("patient_id")
    @Expose
    private Integer patientId;
    @SerializedName("hub_id")
    @Expose
    private Integer hubId;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Integer getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Integer appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getHubId() {
        return hubId;
    }

    public void setHubId(Integer hubId) {
        this.hubId = hubId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

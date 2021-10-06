
package com.ivestment.doctorna.data.model.PatientAppointment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppointmentModelClass {

    @SerializedName("id")
    @Expose
    private Integer id;
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
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("prescription")
    @Expose
    private String prescription;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("zoom_secret")
    @Expose
    private String zoomSecret;
    @SerializedName("doctor_id")
    @Expose
    private Integer doctorId;
    @SerializedName("zoom_password")
    @Expose
    private String zoomPassword;
    @SerializedName("time_of_appointment")
    @Expose
    private String timeOfAppointment;
    @SerializedName("date_by_month")
    @Expose
    private String dateByMonth;
    @SerializedName("zoom_secret_value")
    @Expose
    private Boolean zoom_secret_value;
    @SerializedName("doctor")
    @Expose
    private DoctorModelClass doctor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getZoomSecret() {
        return zoomSecret;
    }

    public void setZoomSecret(String zoomSecret) {
        this.zoomSecret = zoomSecret;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getZoomPassword() {
        return zoomPassword;
    }

    public void setZoomPassword(String zoomPassword) {
        this.zoomPassword = zoomPassword;
    }

    public String getTimeOfAppointment() {
        return timeOfAppointment;
    }

    public void setTimeOfAppointment(String timeOfAppointment) {
        this.timeOfAppointment = timeOfAppointment;
    }

    public String getDateByMonth() {
        return dateByMonth;
    }

    public void setDateByMonth(String dateByMonth) {
        this.dateByMonth = dateByMonth;
    }

    public Boolean getZoom_secret_value() {
        return zoom_secret_value;
    }

    public void setZoom_secret_value(Boolean zoom_secret_value) {
        this.zoom_secret_value = zoom_secret_value;
    }

    public DoctorModelClass getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorModelClass doctor) {
        this.doctor = doctor;
    }

}

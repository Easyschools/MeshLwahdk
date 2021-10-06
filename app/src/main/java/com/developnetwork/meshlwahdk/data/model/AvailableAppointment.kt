package com.ivestment.doctorna.data.model


import com.google.gson.annotations.SerializedName

data class AvailableAppointment(
    @SerializedName("appointment_date")
    val appointmentDate: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("hasAppointment")
    val hasAppointment: Boolean,
    @SerializedName("hub_id")
    val hubId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("number_of_appointments")
    val numberOfAppointments: Int,
    @SerializedName("remainingAppointmentNumber")
    val remainingAppointmentNumber: Int,
    @SerializedName("totalAppointmentsNumber")
    val totalAppointmentsNumber: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("working_hours")
    val workingHours: String
)
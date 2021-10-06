package com.ivestment.doctorna.data.repository

import com.ivestment.doctorna.data.model.PatientAppointment.PatientAppointmentDBResponse
import com.ivestment.doctorna.data.model.booking.BookingDBResponse
import com.ivestment.doctorna.data.model.reservation.ReservationDBResponse
import com.ivestment.doctorna.data.network.Service

interface AppointmentsRepo {
    suspend fun getAppointment(
        hub_id: Int
    ): PatientAppointmentDBResponse

    suspend fun reservationDoctor(
        appointment_date: String,
        patient_id: Int,
    slotID:Int
    ): ReservationDBResponse

    suspend fun bookAppointment(appointment_date: String,patient_id: Int): BookingDBResponse

}

class AppointmentsRepoImpl(private val service: Service) : AppointmentsRepo {
    override suspend fun getAppointment(hub_id: Int): PatientAppointmentDBResponse {
        return service.getPatientsAppointment(hub_id)
    }

    override suspend fun reservationDoctor(
        appointment_date: String,
        patient_id: Int,
        slotID: Int
    ): ReservationDBResponse {
        return service.reservationDoctor(appointment_date, patient_id,slotID)
    }

    override suspend fun bookAppointment(appointment_date: String,patient_id: Int): BookingDBResponse {
        return service.bookingDoctor(appointment_date,1,patient_id)
    }
}
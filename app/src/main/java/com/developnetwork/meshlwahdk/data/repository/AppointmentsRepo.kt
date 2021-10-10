package com.developnetwork.meshlwahdk.data.repository

import com.developnetwork.meshlwahdk.data.network.Service

interface AppointmentsRepo {
//    suspend fun getAppointment(
//        hub_id: Int
//    ): PatientAppointmentDBResponse
//
//    suspend fun reservationDoctor(
//        appointment_date: String,
//        patient_id: Int,
//    slotID:Int
//    ): ReservationDBResponse
//
//    suspend fun bookAppointment(appointment_date: String,patient_id: Int): BookingDBResponse

}

class AppointmentsRepoImpl(private val service: Service) : AppointmentsRepo {
//    override suspend fun getAppointment(hub_id: Int): PatientAppointmentDBResponse {
//        return service.getPatientsAppointment(hub_id)
//    }
//
//    override suspend fun reservationDoctor(
//        appointment_date: String,
//        patient_id: Int,
//        slotID: Int
//    ): ReservationDBResponse {
//        return service.reservationDoctor(appointment_date, patient_id,slotID)
//    }
//
//    override suspend fun bookAppointment(appointment_date: String,patient_id: Int): BookingDBResponse {
//        return service.bookingDoctor(appointment_date,1,patient_id)
//    }
}
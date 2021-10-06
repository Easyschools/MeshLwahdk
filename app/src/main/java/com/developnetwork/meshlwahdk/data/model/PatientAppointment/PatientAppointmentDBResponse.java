
package com.ivestment.doctorna.data.model.PatientAppointment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PatientAppointmentDBResponse {

    @SerializedName("appointments")
    @Expose
    private List<AppointmentModelClass> appointments = null;

    public List<AppointmentModelClass> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentModelClass> appointments) {
        this.appointments = appointments;
    }

}

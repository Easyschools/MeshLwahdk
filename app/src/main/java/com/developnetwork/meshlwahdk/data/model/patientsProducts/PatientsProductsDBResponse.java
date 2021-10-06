
package com.ivestment.doctorna.data.model.patientsProducts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PatientsProductsDBResponse {

    @SerializedName("patients")
    @Expose
    private List<PatientModelClass> patients = null;
    @SerializedName("history")
    @Expose
    private List<Object> history = null;

    public List<PatientModelClass> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientModelClass> patients) {
        this.patients = patients;
    }

    public List<Object> getHistory() {
        return history;
    }

    public void setHistory(List<Object> history) {
        this.history = history;
    }

}

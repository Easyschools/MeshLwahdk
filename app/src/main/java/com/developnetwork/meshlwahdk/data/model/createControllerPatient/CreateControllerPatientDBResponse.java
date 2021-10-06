
package com.ivestment.doctorna.data.model.createControllerPatient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateControllerPatientDBResponse {

    @SerializedName("companies")
    @Expose
    private List<CompanyModelClass> companies = null;
    @SerializedName("regions")
    @Expose
    private List<RegionModelClass> regions = null;

    public List<CompanyModelClass> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyModelClass> companies) {
        this.companies = companies;
    }

    public List<RegionModelClass> getRegions() {
        return regions;
    }

    public void setRegions(List<RegionModelClass> regions) {
        this.regions = regions;
    }

}

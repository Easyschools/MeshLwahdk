
package com.ivestment.doctorna.data.model.createControllerPatient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubRegionModelClass {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ar_name")
    @Expose
    private String ar_name;
    @SerializedName("regions_id")
    @Expose
    private Integer regionsId;
    @SerializedName("sub_regions")
    @Expose
    private List<SubSubRegionModelClass> subSubRegions = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAr_name() {
        return ar_name;
    }

    public void setAr_name(String ar_name) {
        this.ar_name = ar_name;
    }

    public Integer getRegionsId() {
        return regionsId;
    }

    public void setRegionsId(Integer regionsId) {
        this.regionsId = regionsId;
    }

    public List<SubSubRegionModelClass> getSubSubRegions() {
        return subSubRegions;
    }

    public void setSubSubRegions(List<SubSubRegionModelClass> subSubRegions) {
        this.subSubRegions = subSubRegions;
    }
}

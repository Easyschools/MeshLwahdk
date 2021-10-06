
package com.ivestment.doctorna.data.model.createControllerPatient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductModelClass {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("has_mobile_app")
    @Expose
    private Integer hasMobileApp;
    @SerializedName("company_id")
    @Expose
    private Integer companyId;
    @SerializedName("teamleader_id")
    @Expose
    private Integer teamleaderId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHasMobileApp() {
        return hasMobileApp;
    }

    public void setHasMobileApp(Integer hasMobileApp) {
        this.hasMobileApp = hasMobileApp;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getTeamleaderId() {
        return teamleaderId;
    }

    public void setTeamleaderId(Integer teamleaderId) {
        this.teamleaderId = teamleaderId;
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

}

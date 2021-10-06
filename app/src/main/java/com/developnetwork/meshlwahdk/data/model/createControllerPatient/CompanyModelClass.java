
package com.ivestment.doctorna.data.model.createControllerPatient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompanyModelClass {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("tax")
    @Expose
    private String tax;
    @SerializedName("commercial")
    @Expose
    private String commercial;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("has_mobile_app")
    @Expose
    private Integer hasMobileApp;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("products")
    @Expose
    private List<ProductModelClass> products = null;
    @SerializedName("user")
    @Expose
    private UserModelClass user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getCommercial() {
        return commercial;
    }

    public void setCommercial(String commercial) {
        this.commercial = commercial;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getHasMobileApp() {
        return hasMobileApp;
    }

    public void setHasMobileApp(Integer hasMobileApp) {
        this.hasMobileApp = hasMobileApp;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public List<ProductModelClass> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModelClass> products) {
        this.products = products;
    }

    public UserModelClass getUser() {
        return user;
    }

    public void setUser(UserModelClass user) {
        this.user = user;
    }

}

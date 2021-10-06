
package com.ivestment.doctorna.data.model.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegisterErrors {

    @SerializedName("displayName")
    @Expose
    private List<String> displayName = null;
    @SerializedName("product_id")
    @Expose
    private List<String> productId = null;
    @SerializedName("phone")
    @Expose
    private List<String> phone = null;
    @SerializedName("nationalId")
    @Expose
    private List<String> nationalId = null;
    @SerializedName("email")
    @Expose
    private List<String> email = null;
    @SerializedName("region_id")
    @Expose
    private List<String> regionId = null;
    @SerializedName("subRegion_id")
    @Expose
    private List<String> subRegionId = null;
    @SerializedName("gender")
    @Expose
    private List<String> gender = null;
    @SerializedName("age")
    @Expose
    private List<String> age = null;

    public List<String> getDisplayName() {
        return displayName;
    }

    public void setDisplayName(List<String> displayName) {
        this.displayName = displayName;
    }

    public List<String> getProductId() {
        return productId;
    }

    public void setProductId(List<String> productId) {
        this.productId = productId;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public List<String> getNationalId() {
        return nationalId;
    }

    public void setNationalId(List<String> nationalId) {
        this.nationalId = nationalId;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public List<String> getRegionId() {
        return regionId;
    }

    public void setRegionId(List<String> regionId) {
        this.regionId = regionId;
    }

    public List<String> getSubRegionId() {
        return subRegionId;
    }

    public void setSubRegionId(List<String> subRegionId) {
        this.subRegionId = subRegionId;
    }

    public List<String> getGender() {
        return gender;
    }

    public void setGender(List<String> gender) {
        this.gender = gender;
    }

    public List<String> getAge() {
        return age;
    }

    public void setAge(List<String> age) {
        this.age = age;
    }

}

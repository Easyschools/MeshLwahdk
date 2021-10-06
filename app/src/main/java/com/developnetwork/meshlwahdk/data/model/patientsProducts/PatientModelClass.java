
package com.ivestment.doctorna.data.model.patientsProducts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ivestment.doctorna.data.model.createControllerPatient.ProductModelClass;

public class PatientModelClass {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("national_id")
    @Expose
    private Double nationalId;
    @SerializedName("call_date")
    @Expose
    private Object callDate;
    @SerializedName("call_type")
    @Expose
    private Object callType;
    @SerializedName("region_id")
    @Expose
    private Integer regionId;
    @SerializedName("call_objective_id")
    @Expose
    private Object callObjectiveId;
    @SerializedName("subRegion_id")
    @Expose
    private Integer subRegionId;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("Age")
    @Expose
    private Integer age;
    @SerializedName("diagnosis")
    @Expose
    private Object diagnosis;
    @SerializedName("current_medication")
    @Expose
    private Object currentMedication;
    @SerializedName("dose_frequency")
    @Expose
    private Object doseFrequency;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("password")
    @Expose
    private Object password;
    @SerializedName("intials")
    @Expose
    private Object intials;
    @SerializedName("date_of_birth")
    @Expose
    private Object dateOfBirth;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("date_of_diagnosis")
    @Expose
    private Object dateOfDiagnosis;
    @SerializedName("pervious_medication")
    @Expose
    private Object perviousMedication;
    @SerializedName("reason_for_switching_medication")
    @Expose
    private Object reasonForSwitchingMedication;
    @SerializedName("other_medications_currently_taken")
    @Expose
    private Object otherMedicationsCurrentlyTaken;
    @SerializedName("followup_call_sms_date")
    @Expose
    private Object followupCallSmsDate;
    @SerializedName("lab_test_imaging_name")
    @Expose
    private Object labTestImagingName;
    @SerializedName("lab_test_imaging_date")
    @Expose
    private Object labTestImagingDate;
    @SerializedName("lab_test_imaging_results")
    @Expose
    private Object labTestImagingResults;
    @SerializedName("date_sample_withdrwal_receipt")
    @Expose
    private Object dateSampleWithdrwalReceipt;
    @SerializedName("pharmacy_name")
    @Expose
    private Object pharmacyName;
    @SerializedName("coupon_activation_code")
    @Expose
    private Object couponActivationCode;
    @SerializedName("adverse_event")
    @Expose
    private Object adverseEvent;
    @SerializedName("adverse_event_type")
    @Expose
    private Object adverseEventType;
    @SerializedName("adverse_event_report")
    @Expose
    private Object adverseEventReport;
    @SerializedName("product_compain")
    @Expose
    private Object productCompain;
    @SerializedName("product_compain_report")
    @Expose
    private Object productCompainReport;
    @SerializedName("eligible_for_FOC")
    @Expose
    private Object eligibleForFOC;
    @SerializedName("FOC_recipt")
    @Expose
    private Object fOCRecipt;
    @SerializedName("FOC_recipt_date")
    @Expose
    private Object fOCReciptDate;
    @SerializedName("supportive_documents_for_FOC_recipt")
    @Expose
    private Object supportiveDocumentsForFOCRecipt;
    @SerializedName("FU_type")
    @Expose
    private Object fUType;
    @SerializedName("comments")
    @Expose
    private Object comments;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("product")
    @Expose
    private ProductModelClass product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getNationalId() {
        return nationalId;
    }

    public void setNationalId(Double nationalId) {
        this.nationalId = nationalId;
    }

    public Object getCallDate() {
        return callDate;
    }

    public void setCallDate(Object callDate) {
        this.callDate = callDate;
    }

    public Object getCallType() {
        return callType;
    }

    public void setCallType(Object callType) {
        this.callType = callType;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Object getCallObjectiveId() {
        return callObjectiveId;
    }

    public void setCallObjectiveId(Object callObjectiveId) {
        this.callObjectiveId = callObjectiveId;
    }

    public Integer getSubRegionId() {
        return subRegionId;
    }

    public void setSubRegionId(Integer subRegionId) {
        this.subRegionId = subRegionId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Object getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Object diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Object getCurrentMedication() {
        return currentMedication;
    }

    public void setCurrentMedication(Object currentMedication) {
        this.currentMedication = currentMedication;
    }

    public Object getDoseFrequency() {
        return doseFrequency;
    }

    public void setDoseFrequency(Object doseFrequency) {
        this.doseFrequency = doseFrequency;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public Object getIntials() {
        return intials;
    }

    public void setIntials(Object intials) {
        this.intials = intials;
    }

    public Object getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Object dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getDateOfDiagnosis() {
        return dateOfDiagnosis;
    }

    public void setDateOfDiagnosis(Object dateOfDiagnosis) {
        this.dateOfDiagnosis = dateOfDiagnosis;
    }

    public Object getPerviousMedication() {
        return perviousMedication;
    }

    public void setPerviousMedication(Object perviousMedication) {
        this.perviousMedication = perviousMedication;
    }

    public Object getReasonForSwitchingMedication() {
        return reasonForSwitchingMedication;
    }

    public void setReasonForSwitchingMedication(Object reasonForSwitchingMedication) {
        this.reasonForSwitchingMedication = reasonForSwitchingMedication;
    }

    public Object getOtherMedicationsCurrentlyTaken() {
        return otherMedicationsCurrentlyTaken;
    }

    public void setOtherMedicationsCurrentlyTaken(Object otherMedicationsCurrentlyTaken) {
        this.otherMedicationsCurrentlyTaken = otherMedicationsCurrentlyTaken;
    }

    public Object getFollowupCallSmsDate() {
        return followupCallSmsDate;
    }

    public void setFollowupCallSmsDate(Object followupCallSmsDate) {
        this.followupCallSmsDate = followupCallSmsDate;
    }

    public Object getLabTestImagingName() {
        return labTestImagingName;
    }

    public void setLabTestImagingName(Object labTestImagingName) {
        this.labTestImagingName = labTestImagingName;
    }

    public Object getLabTestImagingDate() {
        return labTestImagingDate;
    }

    public void setLabTestImagingDate(Object labTestImagingDate) {
        this.labTestImagingDate = labTestImagingDate;
    }

    public Object getLabTestImagingResults() {
        return labTestImagingResults;
    }

    public void setLabTestImagingResults(Object labTestImagingResults) {
        this.labTestImagingResults = labTestImagingResults;
    }

    public Object getDateSampleWithdrwalReceipt() {
        return dateSampleWithdrwalReceipt;
    }

    public void setDateSampleWithdrwalReceipt(Object dateSampleWithdrwalReceipt) {
        this.dateSampleWithdrwalReceipt = dateSampleWithdrwalReceipt;
    }

    public Object getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(Object pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public Object getCouponActivationCode() {
        return couponActivationCode;
    }

    public void setCouponActivationCode(Object couponActivationCode) {
        this.couponActivationCode = couponActivationCode;
    }

    public Object getAdverseEvent() {
        return adverseEvent;
    }

    public void setAdverseEvent(Object adverseEvent) {
        this.adverseEvent = adverseEvent;
    }

    public Object getAdverseEventType() {
        return adverseEventType;
    }

    public void setAdverseEventType(Object adverseEventType) {
        this.adverseEventType = adverseEventType;
    }

    public Object getAdverseEventReport() {
        return adverseEventReport;
    }

    public void setAdverseEventReport(Object adverseEventReport) {
        this.adverseEventReport = adverseEventReport;
    }

    public Object getProductCompain() {
        return productCompain;
    }

    public void setProductCompain(Object productCompain) {
        this.productCompain = productCompain;
    }

    public Object getProductCompainReport() {
        return productCompainReport;
    }

    public void setProductCompainReport(Object productCompainReport) {
        this.productCompainReport = productCompainReport;
    }

    public Object getEligibleForFOC() {
        return eligibleForFOC;
    }

    public void setEligibleForFOC(Object eligibleForFOC) {
        this.eligibleForFOC = eligibleForFOC;
    }

    public Object getFOCRecipt() {
        return fOCRecipt;
    }

    public void setFOCRecipt(Object fOCRecipt) {
        this.fOCRecipt = fOCRecipt;
    }

    public Object getFOCReciptDate() {
        return fOCReciptDate;
    }

    public void setFOCReciptDate(Object fOCReciptDate) {
        this.fOCReciptDate = fOCReciptDate;
    }

    public Object getSupportiveDocumentsForFOCRecipt() {
        return supportiveDocumentsForFOCRecipt;
    }

    public void setSupportiveDocumentsForFOCRecipt(Object supportiveDocumentsForFOCRecipt) {
        this.supportiveDocumentsForFOCRecipt = supportiveDocumentsForFOCRecipt;
    }

    public Object getFUType() {
        return fUType;
    }

    public void setFUType(Object fUType) {
        this.fUType = fUType;
    }

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
        this.comments = comments;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public ProductModelClass getProduct() {
        return product;
    }

    public void setProduct(ProductModelClass product) {
        this.product = product;
    }

}

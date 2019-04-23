package vn.shippo.rider.entity.firebase;

import com.avaje.ebean.annotation.DbJsonB;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.SerializedName;
import vn.shippo.rider.entity.PickupTaskParcel;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Map;

public class FirebaseTransportationTask implements Serializable {

    private Integer id;

    private Integer assignee;

    @SerializedName("assignee_type")
    @JsonProperty("assignee_type")
    private String assigneeType;

    private Double cod;

    @SerializedName("created_at")
    @JsonProperty("created_at")
    private Long createdAt;

    @SerializedName("created_by")
    @JsonProperty("created_by")
    @PropertyName("created_by")
    private Integer createdBy;

    @SerializedName("deliver_before")
    @JsonProperty("deliver_before")
    @PropertyName("deliver_before")
    private Long deliverBefore;

    @SerializedName("deliver_contact_name")
    @JsonProperty("deliver_contact_name")
    @PropertyName("deliver_contact_name")
    private String deliverContactName;

    @SerializedName("deliver_contact_phone")
    @JsonProperty("deliver_contact_phone")
    @PropertyName("deliver_contact_phone")
    private String deliverContactPhone;

    @SerializedName("deliver_detail")
    @JsonProperty("deliver_detail")
    @PropertyName("deliver_detail")
    private String deliverDetail;

    @SerializedName("deliver_full_address")
    @JsonProperty("deliver_full_address")
    @PropertyName("deliver_full_address")
    private String deliverFullAddress;

    @SerializedName("deliver_location_ids_path")
    @JsonProperty("deliver_location_ids_path")
    @PropertyName("deliver_location_ids_path")
    private String deliverLocationIdsPath;

    private String description;

    @SerializedName("fail_reason")
    @JsonProperty("fail_reason")
    @PropertyName("fail_reason")
    private String failReason;

    @SerializedName("failed_at")
    @JsonProperty("failed_at")
    @PropertyName("failed_at")
    private Long failedAt;

    @SerializedName("internal_reason_fail")
    @JsonProperty("internal_reason_fail")
    @PropertyName("internal_reason_fail")
    private String internalReasonFail;

    @SerializedName("is_last_mile_delivery")
    @JsonProperty("is_last_mile_delivery")
    @PropertyName("is_last_mile_delivery")
    private Boolean isLastMileDelivery;

    @SerializedName("merchant_user_id")
    @JsonProperty("merchant_user_id")
    @PropertyName("merchant_user_id")
    private Integer merchantUserId;

    @DbJsonB
    private Object metadata;

    private String note;

    @SerializedName("object_code")
    @JsonProperty("object_code")
    @PropertyName("object_code")
    private String objectCode;

    @SerializedName("object_id")
    @JsonProperty("object_id")
    @PropertyName("object_id")
    private Integer objectId;

    @SerializedName("object_type")
    @JsonProperty("object_type")
    @PropertyName("object_type")
    private String objectType;

    @SerializedName("pick_location_ids_path")
    @JsonProperty("pick_location_ids_path")
    @PropertyName("pick_location_ids_path")
    private String pickLocationIdsPath;

    @SerializedName("pickup_before")
    @JsonProperty("pickup_before")
    @PropertyName("pickup_before")
    private Long pickupBefore;

    @SerializedName("pickup_contact_name")
    @JsonProperty("pickup_contact_name")
    @PropertyName("pickup_contact_name")
    private String pickupContactName;

    @SerializedName("pickup_contact_phone")
    @JsonProperty("pickup_contact_phone")
    @PropertyName("pickup_contact_phone")
    private String pickupContactPhone;

    @SerializedName("pickup_detail")
    @JsonProperty("pickup_detail")
    @PropertyName("pickup_detail")
    private String pickupDetail;

    @SerializedName("pickup_full_address")
    @JsonProperty("pickup_full_address")
    @PropertyName("pickup_full_address")
    private String pickupFullAddress;

    @SerializedName("real_cod")
    @JsonProperty("real_cod")
    @PropertyName("real_cod")
    private Double realCod;

    @SerializedName("reason_code")
    @JsonProperty("reason_code")
    @PropertyName("reason_code")
    private Object reasonCode;

    @SerializedName("recipient_pay_courier_fee")
    @JsonProperty("recipient_pay_courier_fee")
    @PropertyName("recipient_pay_courier_fee")
    private String recipientPayCourierFee;

    @SerializedName("request_id")
    @JsonProperty("request_id")
    @PropertyName("request_id")
    private Integer requestId;

    @SerializedName("rider_shift_id")
    @JsonProperty("rider_shift_id")
    @PropertyName("rider_shift_id")
    private Integer riderShiftId;

    @SerializedName("started_at")
    @JsonProperty("started_at")
    @PropertyName("started_at")
    private Long startedAt;

    private String state;

    @SerializedName("success_at")
    @JsonProperty("success_at")
    @PropertyName("success_at")
    private Long successAt;

    @SerializedName("task_deadline")
    @JsonProperty("task_deadline")
    @PropertyName("task_deadline")
    private Long taskDeadline;

    @SerializedName("tookan_job_id")
    @JsonProperty("tookan_job_id")
    @PropertyName("tookan_job_id")
    private String tookanJobId;

    private String type;

    @SerializedName("updated_at")
    @JsonProperty("updated_at")
    @PropertyName("updated_at")
    private Long updatedAt;

    private Integer version;

    @SerializedName("consignee_code")
    @JsonProperty("consignee_code")
    private String consigneeCode;

    private String goods;

    @SerializedName("delivery_notes")
    @JsonProperty("delivery_notes")
    private String deliveryNotes;

    @SerializedName("merchant_brand")
    @JsonProperty("merchant_brand")
    private String merchantBrand;

    @SerializedName("merchant_phone")
    @JsonProperty("merchant_phone")
    private String merchantPhone;

    @Column(name = "image")
    @SerializedName("image")
    @JsonProperty("image")
    private Object image;

    @Column(name = "rider_note")
    @SerializedName("rider_note")
    @JsonProperty("rider_note")
    private String riderNote;

    @SerializedName("merchant_username")
    @JsonProperty("merchant_username")
    private String merchantUsername;

    @Column(name="confirmed_consignee_code")
    @SerializedName("confirmed_consignee_code")
    @JsonProperty("confirmed_consignee_code")
    private String confirmedConsigneeCode;

    private Map<String, PickupTaskParcel> parcels;

    @SerializedName("total_fee")
    @JsonProperty("total_fee")
    private Double totalFee;

    @SerializedName("total_merchant_fee")
    @JsonProperty("total_merchant_fee")
    private Double totalMerchantFee;

    @SerializedName("is_update")
    @JsonProperty("is_update")
    private Boolean isUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssignee() {
        return assignee;
    }

    public void setAssignee(Integer assignee) {
        this.assignee = assignee;
    }

    public String getAssigneeType() {
        return assigneeType;
    }

    public void setAssigneeType(String assigneeType) {
        this.assigneeType = assigneeType;
    }

    public Double getCod() {
        return cod;
    }

    public void setCod(Double cod) {
        this.cod = cod;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Long getDeliverBefore() {
        return deliverBefore;
    }

    public void setDeliverBefore(Long deliverBefore) {
        this.deliverBefore = deliverBefore;
    }

    public String getDeliverContactName() {
        return deliverContactName;
    }

    public void setDeliverContactName(String deliverContactName) {
        this.deliverContactName = deliverContactName;
    }

    public String getDeliverContactPhone() {
        return deliverContactPhone;
    }

    public void setDeliverContactPhone(String deliverContactPhone) {
        this.deliverContactPhone = deliverContactPhone;
    }

    public String getDeliverDetail() {
        return deliverDetail;
    }

    public void setDeliverDetail(String deliverDetail) {
        this.deliverDetail = deliverDetail;
    }

    public String getDeliverFullAddress() {
        return deliverFullAddress;
    }

    public void setDeliverFullAddress(String deliverFullAddress) {
        this.deliverFullAddress = deliverFullAddress;
    }

    public String getDeliverLocationIdsPath() {
        return deliverLocationIdsPath;
    }

    public void setDeliverLocationIdsPath(String deliverLocationIdsPath) {
        this.deliverLocationIdsPath = deliverLocationIdsPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public Long getFailedAt() {
        return failedAt;
    }

    public void setFailedAt(Long failedAt) {
        this.failedAt = failedAt;
    }

    public String getInternalReasonFail() {
        return internalReasonFail;
    }

    public void setInternalReasonFail(String internalReasonFail) {
        this.internalReasonFail = internalReasonFail;
    }

    public Boolean getLastMileDelivery() {
        return isLastMileDelivery;
    }

    public void setLastMileDelivery(Boolean lastMileDelivery) {
        isLastMileDelivery = lastMileDelivery;
    }

    public Integer getMerchantUserId() {
        return merchantUserId;
    }

    public void setMerchantUserId(Integer merchantUserId) {
        this.merchantUserId = merchantUserId;
    }

    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getPickLocationIdsPath() {
        return pickLocationIdsPath;
    }

    public void setPickLocationIdsPath(String pickLocationIdsPath) {
        this.pickLocationIdsPath = pickLocationIdsPath;
    }

    public Long getPickupBefore() {
        return pickupBefore;
    }

    public void setPickupBefore(Long pickupBefore) {
        this.pickupBefore = pickupBefore;
    }

    public String getPickupContactName() {
        return pickupContactName;
    }

    public void setPickupContactName(String pickupContactName) {
        this.pickupContactName = pickupContactName;
    }

    public String getPickupContactPhone() {
        return pickupContactPhone;
    }

    public void setPickupContactPhone(String pickupContactPhone) {
        this.pickupContactPhone = pickupContactPhone;
    }

    public String getPickupDetail() {
        return pickupDetail;
    }

    public void setPickupDetail(String pickupDetail) {
        this.pickupDetail = pickupDetail;
    }

    public String getPickupFullAddress() {
        return pickupFullAddress;
    }

    public void setPickupFullAddress(String pickupFullAddress) {
        this.pickupFullAddress = pickupFullAddress;
    }

    public Double getRealCod() {
        return realCod;
    }

    public void setRealCod(Double realCod) {
        this.realCod = realCod;
    }

    public Object getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(Object reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getRecipientPayCourierFee() {
        return recipientPayCourierFee;
    }

    public void setRecipientPayCourierFee(String recipientPayCourierFee) {
        this.recipientPayCourierFee = recipientPayCourierFee;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getRiderShiftId() {
        return riderShiftId;
    }

    public void setRiderShiftId(Integer riderShiftId) {
        this.riderShiftId = riderShiftId;
    }

    public Long getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Long startedAt) {
        this.startedAt = startedAt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getSuccessAt() {
        return successAt;
    }

    public void setSuccessAt(Long successAt) {
        this.successAt = successAt;
    }

    public Long getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(Long taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public String getTookanJobId() {
        return tookanJobId;
    }

    public void setTookanJobId(String tookanJobId) {
        this.tookanJobId = tookanJobId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getConsigneeCode() {
        return consigneeCode;
    }

    public void setConsigneeCode(String consigneeCode) {
        this.consigneeCode = consigneeCode;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getDeliveryNotes() {
        return deliveryNotes;
    }

    public void setDeliveryNotes(String deliveryNotes) {
        this.deliveryNotes = deliveryNotes;
    }

    public String getMerchantBrand() {
        return merchantBrand;
    }

    public void setMerchantBrand(String merchantBrand) {
        this.merchantBrand = merchantBrand;
    }

    public String getMerchantPhone() {
        return merchantPhone;
    }

    public void setMerchantPhone(String merchantPhone) {
        this.merchantPhone = merchantPhone;
    }

    public Map<String, PickupTaskParcel> getParcels() {
        return parcels;
    }

    public void setParcels(Map<String, PickupTaskParcel> parcels) {
        this.parcels = parcels;
    }

    public String getMerchantUsername() {
        return merchantUsername;
    }

    public void setMerchantUsername(String merchantUsername) {
        this.merchantUsername = merchantUsername;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public String getRiderNote() {
        return riderNote;
    }

    public void setRiderNote(String riderNote) {
        this.riderNote = riderNote;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public Double getTotalMerchantFee() {
        return totalMerchantFee;
    }

    public void setTotalMerchantFee(Double totalMerchantFee) {
        this.totalMerchantFee = totalMerchantFee;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        isUpdate = isUpdate;
    }

    public String getConfirmedConsigneeCode() {
        return confirmedConsigneeCode;
    }

    public void setConfirmedConsigneeCode(String confirmedConsigneeCode) {
        this.confirmedConsigneeCode = confirmedConsigneeCode;
    }
}

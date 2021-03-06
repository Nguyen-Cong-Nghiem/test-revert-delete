package vn.shippo.rider.entity;

import com.avaje.ebean.annotation.DbJson;
import com.avaje.ebean.annotation.DbJsonB;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import org.json.JSONArray;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the transportation_tasks database table.
 *
 */
@Entity
@Table(name="transportation_tasks")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransportationTask implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private Integer assignee;

    @Column(name="assignee_type")
    @SerializedName("assignee_type")
    @JsonProperty("assignee_type")
    private String assigneeType;

    private Double cod;

    @Column(name="created_at")
    @SerializedName("created_at")
    @JsonProperty("created_at")
    private Timestamp createdAt;

    @Column(name="created_by")
    @SerializedName("created_by")
    @JsonProperty("created_by")
    private Integer createdBy;

    @Column(name="deliver_before")
    @SerializedName("deliver_before")
    @JsonProperty("deliver_before")
    private Timestamp deliverBefore;

    @Column(name="deliver_contact_name")
    @SerializedName("deliver_contact_name")
    @JsonProperty("deliver_contact_name")
    private String deliverContactName;

    @Column(name="deliver_contact_phone")
    @SerializedName("deliver_contact_phone")
    @JsonProperty("deliver_contact_phone")
    private String deliverContactPhone;

    @Column(name="deliver_detail")
    @SerializedName("deliver_detail")
    @JsonProperty("deliver_detail")
    private String deliverDetail;

    @Column(name="deliver_full_address")
    @SerializedName("deliver_full_address")
    @JsonProperty("deliver_full_address")
    private String deliverFullAddress;

    @Column(name="deliver_location_ids_path")
    @SerializedName("deliver_location_ids_path")
    @JsonProperty("deliver_location_ids_path")
    private String deliverLocationIdsPath;

    private String description;

    @Column(name="fail_reason")
    @SerializedName("fail_reason")
    @JsonProperty("fail_reason")
    private String failReason;

    @Column(name="failed_at")
    @SerializedName("failed_at")
    @JsonProperty("failed_at")
    private Timestamp failedAt;

    @Column(name="internal_reason_fail")
    @SerializedName("internal_reason_fail")
    @JsonProperty("internal_reason_fail")
    private String internalReasonFail;

    @Column(name="is_last_mile_delivery")
    @SerializedName("is_last_mile_delivery")
    @JsonProperty("is_last_mile_delivery")
    private Boolean isLastMileDelivery;

    @Column(name="merchant_user_id")
    @SerializedName("merchant_user_id")
    @JsonProperty("merchant_user_id")
    private Integer merchantUserId;

//	@DbJsonB
//	@Column(name="metadata")
//	private List<Metadata> metadata;

	@DbJsonB
	@Column(name="metadata")
	private Object metadata;

	private String note;

	@Column(name="object_code")
	@SerializedName("object_code")
	@JsonProperty("object_code")
	private String objectCode;

	@Column(name="object_id")
	@SerializedName("object_id")
	@JsonProperty("object_id")
	private Integer objectId;

	@Column(name="object_type")
	@SerializedName("object_type")
	@JsonProperty("object_type")
	private String objectType;

	@Column(name="pick_location_ids_path")
	@SerializedName("pick_location_ids_path")
	@JsonProperty("pick_location_ids_path")
	private String pickLocationIdsPath;

	@Column(name="pickup_before")
	@SerializedName("pickup_before")
	@JsonProperty("pickup_before")
	private Timestamp pickupBefore;

	@Column(name="pickup_contact_name")
	@SerializedName("pickup_contact_name")
	@JsonProperty("pickup_contact_name")
	private String pickupContactName;

	@Column(name="pickup_contact_phone")
	@SerializedName("pickup_contact_phone")
	@JsonProperty("pickup_contact_phone")
	private String pickupContactPhone;

	@Column(name="pickup_detail")
	@SerializedName("pickup_detail")
	@JsonProperty("pickup_detail")
	private String pickupDetail;

	@Column(name="pickup_full_address")
	@SerializedName("pickup_full_address")
	@JsonProperty("pickup_full_address")
	private String pickupFullAddress;

	@Column(name="real_cod")
	@SerializedName("real_cod")
	@JsonProperty("real_cod")
	private Double realCod;

//	@DbJsonB
//	@Column(name="reason_code")
//	@SerializedName("reason_code")
//	@JsonProperty("reason_code")
//	private List<ReasonCode> reasonCode;

	@DbJsonB
	@Column(name="reason_code")
	@SerializedName("reason_code")
	@JsonProperty("reason_code")
	private Object reasonCode;

	@Column(name="recipient_pay_courier_fee")
	@SerializedName("recipient_pay_courier_fee")
	@JsonProperty("recipient_pay_courier_fee")
	private String recipientPayCourierFee;

	@Column(name="request_id")
	@SerializedName("request_id")
	@JsonProperty("request_id")
	private Integer requestId;

	@Column(name="rider_shift_id")
	@SerializedName("rider_shift_id")
	@JsonProperty("rider_shift_id")
	private Integer riderShiftId;

	@Column(name="started_at")
	@SerializedName("started_at")
	@JsonProperty("started_at")
	private Timestamp startedAt;

	private String state;

	@Column(name="success_at")
	@SerializedName("success_at")
	@JsonProperty("success_at")
	private Timestamp successAt;

	@Column(name="task_deadline")
	@SerializedName("task_deadline")
	@JsonProperty("task_deadline")
	private Timestamp taskDeadline;

	@Column(name="tookan_job_id")
	@SerializedName("tookan_job_id")
	@JsonProperty("tookan_job_id")
	private String tookanJobId;

	private String type;

	@Column(name="updated_at")
	@SerializedName("updated_at")
	@JsonProperty("updated_at")
	private Timestamp updatedAt;

	private Integer version;

	@Column(name="consignee_code")
	@SerializedName("consignee_code")
	@JsonProperty("consignee_code")
	private String consigneeCode;

	private String goods;

	@Column(name="delivery_notes")
	@SerializedName("delivery_notes")
	@JsonProperty("delivery_notes")
	private String deliveryNotes;

	@Column(name = "merchant_brand")
	@SerializedName("merchant_brand")
	@JsonProperty("merchant_brand")
	private String merchantBrand;

	@Column(name = "merchant_phone")
	@SerializedName("merchant_phone")
	@JsonProperty("merchant_phone")
	private String merchantPhone;

	@DbJson
	@Column(name = "image")
	@SerializedName("image")
	@JsonProperty("image")
	private Object image;

	@Column(name = "rider_note")
	@SerializedName("rider_note")
	@JsonProperty("rider_note")
	private String riderNote;

	@Column(name="confirmed_consignee_code")
	@SerializedName("confirmed_consignee_code")
	@JsonProperty("confirmed_consignee_code")
	private String confirmedConsigneeCode;

	@Column(name = "payment_partner_code")
	@SerializedName("payment_partner_code")
	@JsonProperty("payment_partner_code")
	private String paymentPartnerCode;

	@Column(name = "payment_method")
	@SerializedName("payment_method")
	@JsonProperty("payment_method")
	private String paymentMethod;

	@Column(name = "transaction_id")
	@SerializedName("transaction_id")
	@JsonProperty("transaction_id")
	private String transactionId;

	@Column(name = "qr_code")
	@SerializedName("qr_code")
	@JsonProperty("qr_code")
	private String qrCode;

	@Column(name = "merchant_username")
	@SerializedName("merchant_username")
	@JsonProperty("merchant_username")
	private String merchantUsername;

	@Column(name = "total_fee")
	@SerializedName("total_fee")
	@JsonProperty("total_fee")
	private Double totalFee;

	@Column(name = "total_merchant_fee")
	@SerializedName("total_merchant_fee")
	@JsonProperty("total_merchant_fee")
	private Double totalMerchantFee;

	@Column(name="last_import_at")
	@SerializedName("last_import_at")
	@JsonProperty("last_import_at")
	private Timestamp lastImportAt;

	@Transient
	private Integer parcelCount;

	public TransportationTask() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAssignee() {
		return this.assignee;
	}

	public void setAssignee(Integer assignee) {
		this.assignee = assignee;
	}

	public String getAssigneeType() {
		return this.assigneeType;
	}

	public void setAssigneeType(String assigneeType) {
		this.assigneeType = assigneeType;
	}

	public Double getCod() {
		return this.cod;
	}

	public void setCod(Double cod) {
		this.cod = cod;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getDeliverBefore() {
		return this.deliverBefore;
	}

	public void setDeliverBefore(Timestamp deliverBefore) {
		this.deliverBefore = deliverBefore;
	}

	public String getDeliverContactName() {
		return this.deliverContactName;
	}

	public void setDeliverContactName(String deliverContactName) {
		this.deliverContactName = deliverContactName;
	}

	public String getDeliverContactPhone() {
		return this.deliverContactPhone;
	}

	public void setDeliverContactPhone(String deliverContactPhone) {
		this.deliverContactPhone = deliverContactPhone;
	}

	public String getDeliverDetail() {
		return this.deliverDetail;
	}

	public void setDeliverDetail(String deliverDetail) {
		this.deliverDetail = deliverDetail;
	}

	public String getDeliverFullAddress() {
		return this.deliverFullAddress;
	}

	public void setDeliverFullAddress(String deliverFullAddress) {
		this.deliverFullAddress = deliverFullAddress;
	}

	public String getDeliverLocationIdsPath() {
		return this.deliverLocationIdsPath;
	}

	public void setDeliverLocationIdsPath(String deliverLocationIdsPath) {
		this.deliverLocationIdsPath = deliverLocationIdsPath;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFailReason() {
		return this.failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public Timestamp getFailedAt() {
		return this.failedAt;
	}

	public void setFailedAt(Timestamp failedAt) {
		this.failedAt = failedAt;
	}

	public String getInternalReasonFail() {
		return this.internalReasonFail;
	}

	public void setInternalReasonFail(String internalReasonFail) {
		this.internalReasonFail = internalReasonFail;
	}

	public Boolean getIsLastMileDelivery() {
		return this.isLastMileDelivery;
	}

	public void setIsLastMileDelivery(Boolean isLastMileDelivery) {
		this.isLastMileDelivery = isLastMileDelivery;
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
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getObjectCode() {
		return this.objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}

	public Integer getObjectId() {
		return this.objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public String getObjectType() {
		return this.objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getPickLocationIdsPath() {
		return this.pickLocationIdsPath;
	}

	public void setPickLocationIdsPath(String pickLocationIdsPath) {
		this.pickLocationIdsPath = pickLocationIdsPath;
	}

	public Timestamp getPickupBefore() {
		return this.pickupBefore;
	}

	public void setPickupBefore(Timestamp pickupBefore) {
		this.pickupBefore = pickupBefore;
	}

	public String getPickupContactName() {
		return this.pickupContactName;
	}

	public void setPickupContactName(String pickupContactName) {
		this.pickupContactName = pickupContactName;
	}

	public String getPickupContactPhone() {
		return this.pickupContactPhone;
	}

	public void setPickupContactPhone(String pickupContactPhone) {
		this.pickupContactPhone = pickupContactPhone;
	}

	public String getPickupDetail() {
		return this.pickupDetail;
	}

	public void setPickupDetail(String pickupDetail) {
		this.pickupDetail = pickupDetail;
	}

	public String getPickupFullAddress() {
		return this.pickupFullAddress;
	}

	public void setPickupFullAddress(String pickupFullAddress) {
		this.pickupFullAddress = pickupFullAddress;
	}

	public Double getRealCod() {
		return this.realCod;
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
		return this.recipientPayCourierFee;
	}

	public void setRecipientPayCourierFee(String recipientPayCourierFee) {
		this.recipientPayCourierFee = recipientPayCourierFee;
	}

	public Integer getRequestId() {
		return this.requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public Integer getRiderShiftId() {
		return this.riderShiftId;
	}

	public void setRiderShiftId(Integer riderShiftId) {
		this.riderShiftId = riderShiftId;
	}

	public Timestamp getStartedAt() {
		return this.startedAt;
	}

	public void setStartedAt(Timestamp startedAt) {
		this.startedAt = startedAt;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Timestamp getSuccessAt() {
		return this.successAt;
	}

	public void setSuccessAt(Timestamp successAt) {
		this.successAt = successAt;
	}

	public Timestamp getTaskDeadline() {
		return this.taskDeadline;
	}

	public void setTaskDeadline(Timestamp taskDeadline) {
		this.taskDeadline = taskDeadline;
	}

	public String getTookanJobId() {
		return this.tookanJobId;
	}

	public void setTookanJobId(String tookanJobId) {
		this.tookanJobId = tookanJobId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getVersion() {
		return this.version;
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

	public String getConfirmedConsigneeCode() {
		return confirmedConsigneeCode;
	}

	public void setConfirmedConsigneeCode(String confirmedConsigneeCode) {
		this.confirmedConsigneeCode = confirmedConsigneeCode;
	}

	public String getPaymentPartnerCode() {
		return paymentPartnerCode;
	}

	public void setPaymentPartnerCode(String paymentPartnerCode) {
		this.paymentPartnerCode = paymentPartnerCode;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getMerchantUsername() {
		return merchantUsername;
	}

	public void setMerchantUsername(String merchantUsername) {
		this.merchantUsername = merchantUsername;
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

	public Timestamp getLastImportAt() {
		return lastImportAt;
	}

	public void setLastImportAt(Timestamp lastImportAt) {
		this.lastImportAt = lastImportAt;
	}

	public Integer getParcelCount() {
		return parcelCount;
	}

	public void setParcelCount(Integer parcelCount) {
		this.parcelCount = parcelCount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TransportationTask)) return false;
		TransportationTask that = (TransportationTask) o;

		return Objects.equals(getId(), that.getId()) &&
				Objects.equals(getAssignee(), that.getAssignee()) &&
				Objects.equals(getAssigneeType(), that.getAssigneeType()) &&
				Objects.equals(getCod(), that.getCod()) &&
				Objects.equals(getCreatedBy(), that.getCreatedBy()) &&
				Objects.equals(getDeliverBefore(), that.getDeliverBefore()) &&
				Objects.equals(getDeliverContactName(), that.getDeliverContactName()) &&
				Objects.equals(getDeliverContactPhone(), that.getDeliverContactPhone()) &&
				Objects.equals(getDeliverDetail(), that.getDeliverDetail()) &&
				Objects.equals(getDeliverFullAddress(), that.getDeliverFullAddress()) &&
				Objects.equals(getDeliverLocationIdsPath(), that.getDeliverLocationIdsPath()) &&
				Objects.equals(getDescription(), that.getDescription()) &&
				Objects.equals(getFailReason(), that.getFailReason()) &&
				Objects.equals(getInternalReasonFail(), that.getInternalReasonFail()) &&
				Objects.equals(getIsLastMileDelivery(), that.getIsLastMileDelivery()) &&
				Objects.equals(getMerchantUserId(), that.getMerchantUserId()) &&
				Objects.equals(getNote(), that.getNote()) &&
				Objects.equals(getObjectCode(), that.getObjectCode()) &&
				Objects.equals(getObjectId(), that.getObjectId()) &&
				Objects.equals(getObjectType(), that.getObjectType()) &&
				Objects.equals(getPickLocationIdsPath(), that.getPickLocationIdsPath()) &&
				Objects.equals(getPickupBefore(), that.getPickupBefore()) &&
				Objects.equals(getPickupContactName(), that.getPickupContactName()) &&
				Objects.equals(getPickupContactPhone(), that.getPickupContactPhone()) &&
				Objects.equals(getPickupDetail(), that.getPickupDetail()) &&
				Objects.equals(getPickupFullAddress(), that.getPickupFullAddress()) &&
				Objects.equals(getRealCod(), that.getRealCod()) &&
				Objects.equals(getRecipientPayCourierFee(), that.getRecipientPayCourierFee()) &&
				Objects.equals(getRequestId(), that.getRequestId()) &&
				Objects.equals(getRiderShiftId(), that.getRiderShiftId()) &&
				Objects.equals(getState(), that.getState()) &&
				Objects.equals(getTaskDeadline(), that.getTaskDeadline()) &&
				Objects.equals(getTookanJobId(), that.getTookanJobId()) &&
				Objects.equals(getType(), that.getType()) &&
				Objects.equals(getLastImportAt(),that.lastImportAt);
	}

	@Override
	public int hashCode() {
		return id;
	}

    @Override
    public String toString() {
        return "TransportationTask{" +
                "id=" + id +
                ", assignee=" + assignee +
                ", assigneeType='" + assigneeType + '\'' +
                ", cod=" + cod +
                ", createdAt=" + createdAt +
                ", createdBy=" + createdBy +
                ", deliverBefore=" + deliverBefore +
                ", deliverContactName='" + deliverContactName + '\'' +
                ", deliverContactPhone='" + deliverContactPhone + '\'' +
                ", deliverDetail='" + deliverDetail + '\'' +
                ", deliverFullAddress='" + deliverFullAddress + '\'' +
                ", deliverLocationIdsPath='" + deliverLocationIdsPath + '\'' +
                ", description='" + description + '\'' +
                ", failReason='" + failReason + '\'' +
                ", failedAt=" + failedAt +
                ", internalReasonFail='" + internalReasonFail + '\'' +
                ", isLastMileDelivery=" + isLastMileDelivery +
                ", merchantUserId=" + merchantUserId +
                ", metadata=" + metadata +
                ", note='" + note + '\'' +
                ", objectCode='" + objectCode + '\'' +
                ", objectId=" + objectId +
                ", objectType='" + objectType + '\'' +
                ", pickLocationIdsPath='" + pickLocationIdsPath + '\'' +
                ", pickupBefore=" + pickupBefore +
                ", pickupContactName='" + pickupContactName + '\'' +
                ", pickupContactPhone='" + pickupContactPhone + '\'' +
                ", pickupDetail='" + pickupDetail + '\'' +
                ", pickupFullAddress='" + pickupFullAddress + '\'' +
                ", realCod=" + realCod +
                ", reasonCode=" + reasonCode +
                ", recipientPayCourierFee='" + recipientPayCourierFee + '\'' +
                ", requestId=" + requestId +
                ", riderShiftId=" + riderShiftId +
                ", startedAt=" + startedAt +
                ", state='" + state + '\'' +
                ", successAt=" + successAt +
                ", taskDeadline=" + taskDeadline +
                ", tookanJobId='" + tookanJobId + '\'' +
                ", type='" + type + '\'' +
                ", updatedAt=" + updatedAt +
                ", version=" + version +
				", lastImportAt" + lastImportAt +
                '}';
    }

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public class Type {
		public static final String DELIVER = "DELIVER";
		public static final String PICKUP = "PICKUP";
		public static final String PICKUP_AND_DELIVER = "PICKUP_AND_DELIVER";
	}

	public class AssigneeType {
		public static final String PARTNER = "Partner";
	}

	public class State{
        public static final String NEW = "NEW";
        public static final String IN_PROCESS = "IN_PROCESS";
        public static final String DONE = "DONE";
        public static final String FAILED = "FAILED";
        public static final String REJECT = "REJECT";
        public static final String INTERNAL_FAILED = "INTERNAL_FAILED";
        public static final String CANCELLED = "CANCELLED";
        public static final String FINISHED = "FINISHED";
    }

}

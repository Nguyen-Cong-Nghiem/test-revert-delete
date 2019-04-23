package vn.shippo.rider.event.evententity;

import com.avaje.ebean.annotation.DbJson;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the parcels database table.
 *
 */
public class Parcel implements Serializable , Cloneable{
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @SerializedName("actual_cod")
    @JsonProperty("actual_cod")
    private Double actualCod;

    private String barcode;

    @SerializedName("cancelled_at")
    @JsonProperty("cancelled_at")
    private Timestamp cancelledAt;

    private Double cod;

    private String consignee;

    @SerializedName("consignee_detail_address")
    @JsonProperty("consignee_detail_address")
    private String consigneeDetailAddress;

    @SerializedName("consignee_phone")
    @JsonProperty("consignee_phone")
    private String consigneePhone;

    @SerializedName("created_at")
    @JsonProperty("created_at")
    private Timestamp createdAt;

    @SerializedName("current_warehouse_code")
    @JsonProperty("current_warehouse_code")
    private String currentWarehouseCode;

    @SerializedName("current_warehouse_id")
    @JsonProperty("current_warehouse_id")
    private Integer currentWarehouseId;

    @SerializedName("custom_fields")
    @JsonProperty("custom_fields")
    private Object customFields;

    @SerializedName("delivery_date")
    @JsonProperty("delivery_date")
    private Timestamp deliveryDate;

    @SerializedName("delivery_due_date")
    @JsonProperty("delivery_due_date")
    private Timestamp deliveryDueDate;

    @SerializedName("delivery_location_ids_path")
    @JsonProperty("delivery_location_ids_path")
    private String deliveryLocationIdsPath;

    @SerializedName("delivery_location_names_path")
    @JsonProperty("delivery_location_names_path")
    private String deliveryLocationNamesPath;

    @SerializedName("delivery_notes")
    @JsonProperty("delivery_notes")
    private String deliveryNotes;

    @SerializedName("delivery_shift")
    @JsonProperty("delivery_shift")
    private String deliveryShift;

    @SerializedName("delivery_times")
    @JsonProperty("delivery_times")
    private Integer deliveryTimes;

    @SerializedName("delivery_warehouse_code")
    @JsonProperty("delivery_warehouse_code")
    private String deliveryWarehouseCode;

    @SerializedName("delivery_warehouse_id")
    @JsonProperty("delivery_warehouse_id")
    private Integer deliveryWarehouseId;

    @SerializedName("done_at")
    @JsonProperty("done_at")
    private Timestamp doneAt;

    @SerializedName("failed_at")
    @JsonProperty("failed_at")
    private Timestamp failedAt;

    @DbJson
    private Object features;

    private String goods;

    private Integer height;

    @SerializedName("in_process_at")
    @JsonProperty("in_process_at")
    private Timestamp inProcessAt;

    @SerializedName("internal_failed_at")
    @JsonProperty("internal_failed_at")
    private Timestamp internalFailedAt;

    @SerializedName("is_return")
    @JsonProperty("is_return")
    private Boolean isReturn;

    @SerializedName("last_export_at")
    @JsonProperty("last_export_at")
    private Timestamp lastExportAt;

    @SerializedName("last_import_at")
    @JsonProperty("last_import_at")
    private Timestamp lastImportAt;

    @SerializedName("last_inventory_at")
    @JsonProperty("last_inventory_at")
    private Timestamp lastInventoryAt;

    private Integer lenght;

    @SerializedName("merchant_id")
    @JsonProperty("merchant_id")
    private Integer merchantId;

    @SerializedName("merchant_order_code")
    @JsonProperty("merchant_order_code")
    private String merchantOrderCode;

    @SerializedName("merchant_username")
    @JsonProperty("merchant_username")
    private String merchantUsername;

    @SerializedName("order_code")
    @JsonProperty("order_code")
    private String orderCode;

    @SerializedName("order_id")
    @JsonProperty("order_id")
    private Integer orderId;

    @SerializedName("order_state")
    @JsonProperty("order_state")
    private String orderState;

    @SerializedName("pickup_address_id")
    @JsonProperty("pickup_address_id")
    private Integer pickupAddressId;

    @SerializedName("pickup_contact")
    @JsonProperty("pickup_contact")
    private String pickupContact;

    @SerializedName("pickup_date")
    @JsonProperty("pickup_date")
    private Timestamp pickupDate;

    @SerializedName("pickup_detail_address")
    @JsonProperty("pickup_detail_address")
    private String pickupDetailAddress;

    @SerializedName("pickup_due_date")
    @JsonProperty("pickup_due_date")
    private Timestamp pickupDueDate;

    @SerializedName("pickup_full_address")
    @JsonProperty("pickup_full_address")
    private String pickupFullAddress;

    @SerializedName("pickup_location_ids_path")
    @JsonProperty("pickup_location_ids_path")
    private String pickupLocationIdsPath;

    @SerializedName("pickup_location_names_path")
    @JsonProperty("pickup_location_names_path")
    private String pickupLocationNamesPath;

    @SerializedName("pickup_notes")
    @JsonProperty("pickup_notes")
    private String pickupNotes;

    @SerializedName("pickup_phone")
    @JsonProperty("pickup_phone")
    private String pickupPhone;

    @SerializedName("pickup_request_id")
    @JsonProperty("pickup_request_id")
    private Integer pickupRequestId;

    @SerializedName("pickup_shift")
    @JsonProperty("pickup_shift")
    private String pickupShift;

    @SerializedName("pickup_times")
    @JsonProperty("pickup_times")
    private Integer pickupTimes;

    @SerializedName("pickup_warehouse_code")
    @JsonProperty("pickup_warehouse_code")
    private String pickupWarehouseCode;

    @SerializedName("pickup_warehouse_id")
    @JsonProperty("pickup_warehouse_id")
    private Integer pickupWarehouseId;

    @SerializedName("receiver_payment_amount")
    @JsonProperty("receiver_payment_amount")
    private double receiverPaymentAmount;

    @SerializedName("reject_at")
    @JsonProperty("reject_at")
    private Timestamp rejectAt;

    private String state;

    @SerializedName("total_fee")
    @JsonProperty("total_fee")
    private double totalFee;

    @SerializedName("total_merchant_fee")
    @JsonProperty("total_merchant_fee")
    private double totalMerchantFee;

    @DbJson
    @SerializedName("transit_failure_notes")
    @JsonProperty("transit_failure_notes")
    private Object transitFailureNotes;

    @SerializedName("updated_at")
    @JsonProperty("updated_at")
    private Timestamp updatedAt;

    @SerializedName("user_id")
    @JsonProperty("user_id")
    private Integer userId;

    private Integer version;

    private Integer volume;

    @SerializedName("warehouse_state")
    @JsonProperty("warehouse_state")
    private String warehouseState;

    private Integer weight;

    private Integer width;

    @SerializedName("consignee_code")
    @JsonProperty("consignee_code")
    private String consigneeCode;

    @SerializedName("confirmed_consignee_code")
    @JsonProperty("confirmed_consignee_code")
    private String confirmedConsigneeCode;

    @SerializedName("delivery_schedule_notes")
    @JsonProperty("delivery_schedule_notes")
    private String deliveryScheduleNotes;

    @Temporal(TemporalType.DATE)
    @SerializedName("delivery_schedule_appointment_date")
    @JsonProperty("delivery_schedule_appointment_date")
    private Date deliveryScheduleAppointmentDate;

    private String shift;

    @SerializedName("payment_partner_code")
    @JsonProperty("payment_partner_code")
    private String paymentPartnerCode;

    @SerializedName("payment_method")
    @JsonProperty("payment_method")
    private String paymentMethod;

    @SerializedName("transaction_id")
    @JsonProperty("transaction_id")
    private String transactionId;

    @SerializedName("qr_code")
    @JsonProperty("qr_code")
    private String qrCode;

    @SerializedName("picking_up_at")
    @JsonProperty("picking_up_at")
    private Timestamp pickingUpAt;

    @SerializedName("in_transfer_at")
    @JsonProperty("in_transfer_at")
    private Timestamp inTransferAt;

    @SerializedName("lost_at")
    @JsonProperty("lost_at")
    private Timestamp lostAt;

    @SerializedName("rejected_at")
    @JsonProperty("rejected_at")
    private Timestamp rejectedAt;

    @DbJson
    private Object services;

    public Parcel() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getActualCod() {
        return this.actualCod;
    }

    public void setActualCod(Double actualCod) {
        this.actualCod = actualCod;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Timestamp getCancelledAt() {
        return this.cancelledAt;
    }

    public void setCancelledAt(Timestamp cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    public Double getCod() {
        return this.cod;
    }

    public void setCod(Double cod) {
        this.cod = cod;
    }

    public String getConsignee() {
        return this.consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsigneeDetailAddress() {
        return this.consigneeDetailAddress;
    }

    public void setConsigneeDetailAddress(String consigneeDetailAddress) {
        this.consigneeDetailAddress = consigneeDetailAddress;
    }

    public String getConsigneePhone() {
        return this.consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getCurrentWarehouseCode() {
        return this.currentWarehouseCode;
    }

    public void setCurrentWarehouseCode(String currentWarehouseCode) {
        this.currentWarehouseCode = currentWarehouseCode;
    }

    public Integer getCurrentWarehouseId() {
        return this.currentWarehouseId;
    }

    public void setCurrentWarehouseId(Integer currentWarehouseId) {
        this.currentWarehouseId = currentWarehouseId;
    }

    public Object getCustomFields() {
        return this.customFields;
    }

    public void setCustomFields(Object customFields) {
        this.customFields = customFields;
    }

    public Timestamp getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(Timestamp deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Timestamp getDeliveryDueDate() {
        return this.deliveryDueDate;
    }

    public void setDeliveryDueDate(Timestamp deliveryDueDate) {
        this.deliveryDueDate = deliveryDueDate;
    }

    public String getDeliveryLocationIdsPath() {
        return this.deliveryLocationIdsPath;
    }

    public void setDeliveryLocationIdsPath(String deliveryLocationIdsPath) {
        this.deliveryLocationIdsPath = deliveryLocationIdsPath;
    }

    public String getDeliveryLocationNamesPath() {
        return this.deliveryLocationNamesPath;
    }

    public void setDeliveryLocationNamesPath(String deliveryLocationNamesPath) {
        this.deliveryLocationNamesPath = deliveryLocationNamesPath;
    }

    public String getDeliveryNotes() {
        return this.deliveryNotes;
    }

    public void setDeliveryNotes(String deliveryNotes) {
        this.deliveryNotes = deliveryNotes;
    }

    public String getDeliveryShift() {
        return this.deliveryShift;
    }

    public void setDeliveryShift(String deliveryShift) {
        this.deliveryShift = deliveryShift;
    }

    public Integer getDeliveryTimes() {
        return this.deliveryTimes;
    }

    public void setDeliveryTimes(Integer deliveryTimes) {
        this.deliveryTimes = deliveryTimes;
    }

    public String getDeliveryWarehouseCode() {
        return this.deliveryWarehouseCode;
    }

    public void setDeliveryWarehouseCode(String deliveryWarehouseCode) {
        this.deliveryWarehouseCode = deliveryWarehouseCode;
    }

    public Integer getDeliveryWarehouseId() {
        return this.deliveryWarehouseId;
    }

    public void setDeliveryWarehouseId(Integer deliveryWarehouseId) {
        this.deliveryWarehouseId = deliveryWarehouseId;
    }

    public Timestamp getDoneAt() {
        return this.doneAt;
    }

    public void setDoneAt(Timestamp doneAt) {
        this.doneAt = doneAt;
    }

    public Timestamp getFailedAt() {
        return this.failedAt;
    }

    public void setFailedAt(Timestamp failedAt) {
        this.failedAt = failedAt;
    }

    public Object getFeatures() {
        return this.features;
    }

    public void setFeatures(Object features) {
        this.features = features;
    }

    public String getGoods() {
        return this.goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public Integer getHeight() {
        return this.height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Timestamp getInProcessAt() {
        return this.inProcessAt;
    }

    public void setInProcessAt(Timestamp inProcessAt) {
        this.inProcessAt = inProcessAt;
    }

    public Timestamp getInternalFailedAt() {
        return this.internalFailedAt;
    }

    public void setInternalFailedAt(Timestamp internalFailedAt) {
        this.internalFailedAt = internalFailedAt;
    }

    public Boolean getIsReturn() {
        return this.isReturn;
    }

    public void setIsReturn(Boolean isReturn) {
        this.isReturn = isReturn;
    }

    public Timestamp getLastExportAt() {
        return this.lastExportAt;
    }

    public void setLastExportAt(Timestamp lastExportAt) {
        this.lastExportAt = lastExportAt;
    }

    public Timestamp getLastImportAt() {
        return this.lastImportAt;
    }

    public void setLastImportAt(Timestamp lastImportAt) {
        this.lastImportAt = lastImportAt;
    }

    public Timestamp getLastInventoryAt() {
        return this.lastInventoryAt;
    }

    public void setLastInventoryAt(Timestamp lastInventoryAt) {
        this.lastInventoryAt = lastInventoryAt;
    }

    public Integer getLenght() {
        return this.lenght;
    }

    public void setLenght(Integer lenght) {
        this.lenght = lenght;
    }

    public Integer getMerchantId() {
        return this.merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantOrderCode() {
        return this.merchantOrderCode;
    }

    public void setMerchantOrderCode(String merchantOrderCode) {
        this.merchantOrderCode = merchantOrderCode;
    }

    public String getMerchantUsername() {
        return this.merchantUsername;
    }

    public void setMerchantUsername(String merchantUsername) {
        this.merchantUsername = merchantUsername;
    }

    public String getOrderCode() {
        return this.orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderState() {
        return this.orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public Integer getPickupAddressId() {
        return this.pickupAddressId;
    }

    public void setPickupAddressId(Integer pickupAddressId) {
        this.pickupAddressId = pickupAddressId;
    }

    public String getPickupContact() {
        return this.pickupContact;
    }

    public void setPickupContact(String pickupContact) {
        this.pickupContact = pickupContact;
    }

    public Timestamp getPickupDate() {
        return this.pickupDate;
    }

    public void setPickupDate(Timestamp pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getPickupDetailAddress() {
        return this.pickupDetailAddress;
    }

    public void setPickupDetailAddress(String pickupDetailAddress) {
        this.pickupDetailAddress = pickupDetailAddress;
    }

    public Timestamp getPickupDueDate() {
        return this.pickupDueDate;
    }

    public void setPickupDueDate(Timestamp pickupDueDate) {
        this.pickupDueDate = pickupDueDate;
    }

    public String getPickupFullAddress() {
        return this.pickupFullAddress;
    }

    public void setPickupFullAddress(String pickupFullAddress) {
        this.pickupFullAddress = pickupFullAddress;
    }

    public String getPickupLocationIdsPath() {
        return this.pickupLocationIdsPath;
    }

    public void setPickupLocationIdsPath(String pickupLocationIdsPath) {
        this.pickupLocationIdsPath = pickupLocationIdsPath;
    }

    public String getPickupLocationNamesPath() {
        return this.pickupLocationNamesPath;
    }

    public void setPickupLocationNamesPath(String pickupLocationNamesPath) {
        this.pickupLocationNamesPath = pickupLocationNamesPath;
    }

    public String getPickupNotes() {
        return this.pickupNotes;
    }

    public void setPickupNotes(String pickupNotes) {
        this.pickupNotes = pickupNotes;
    }

    public String getPickupPhone() {
        return this.pickupPhone;
    }

    public void setPickupPhone(String pickupPhone) {
        this.pickupPhone = pickupPhone;
    }

    public Integer getPickupRequestId() {
        return this.pickupRequestId;
    }

    public void setPickupRequestId(Integer pickupRequestId) {
        this.pickupRequestId = pickupRequestId;
    }

    public String getPickupShift() {
        return this.pickupShift;
    }

    public void setPickupShift(String pickupShift) {
        this.pickupShift = pickupShift;
    }

    public Integer getPickupTimes() {
        return this.pickupTimes;
    }

    public void setPickupTimes(Integer pickupTimes) {
        this.pickupTimes = pickupTimes;
    }

    public String getPickupWarehouseCode() {
        return this.pickupWarehouseCode;
    }

    public void setPickupWarehouseCode(String pickupWarehouseCode) {
        this.pickupWarehouseCode = pickupWarehouseCode;
    }

    public Integer getPickupWarehouseId() {
        return this.pickupWarehouseId;
    }

    public void setPickupWarehouseId(Integer pickupWarehouseId) {
        this.pickupWarehouseId = pickupWarehouseId;
    }

    public double getReceiverPaymentAmount() {
        return this.receiverPaymentAmount;
    }

    public void setReceiverPaymentAmount(double receiverPaymentAmount) {
        this.receiverPaymentAmount = receiverPaymentAmount;
    }

    public Timestamp getRejectAt() {
        return this.rejectAt;
    }

    public void setRejectAt(Timestamp rejectAt) {
        this.rejectAt = rejectAt;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getTotalFee() {
        return this.totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public double getTotalMerchantFee() {
        return this.totalMerchantFee;
    }

    public void setTotalMerchantFee(double totalMerchantFee) {
        this.totalMerchantFee = totalMerchantFee;
    }

    public Object getTransitFailureNotes() {
        return transitFailureNotes;
    }

    public void setTransitFailureNotes(Object transitFailureNotes) {
        this.transitFailureNotes = transitFailureNotes;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getVolume() {
        return this.volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getWarehouseState() {
        return this.warehouseState;
    }

    public void setWarehouseState(String warehouseState) {
        this.warehouseState = warehouseState;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getConsigneeCode() {
        return consigneeCode;
    }

    public void setConsigneeCode(String consigneeCode) {
        this.consigneeCode = consigneeCode;
    }

    public String getConfirmedConsigneeCode() {
        return confirmedConsigneeCode;
    }

    public void setConfirmedConsigneeCode(String confirmedConsigneeCode) {
        this.confirmedConsigneeCode = confirmedConsigneeCode;
    }

    public String getDeliveryScheduleNotes() {
        return deliveryScheduleNotes;
    }

    public void setDeliveryScheduleNotes(String deliveryScheduleNotes) {
        this.deliveryScheduleNotes = deliveryScheduleNotes;
    }

    public Date getDeliveryScheduleAppointmentDate() {
        return deliveryScheduleAppointmentDate;
    }

    public void setDeliveryScheduleAppointmentDate(Date deliveryScheduleAppointmentDate) {
        this.deliveryScheduleAppointmentDate = deliveryScheduleAppointmentDate;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
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

    public Timestamp getPickingUpAt() {
        return pickingUpAt;
    }

    public void setPickingUpAt(Timestamp pickingUpAt) {
        this.pickingUpAt = pickingUpAt;
    }

    public Timestamp getInTransferAt() {
        return inTransferAt;
    }

    public void setInTransferAt(Timestamp inTransferAt) {
        this.inTransferAt = inTransferAt;
    }

    public Timestamp getLostAt() {
        return lostAt;
    }

    public void setLostAt(Timestamp lostAt) {
        this.lostAt = lostAt;
    }

    public Timestamp getRejectedAt() {
        return rejectedAt;
    }

    public void setRejectedAt(Timestamp rejectedAt) {
        this.rejectedAt = rejectedAt;
    }

    public Object getServices() {
        return services;
    }

    public void setServices(Object services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "id=" + id +
                ", actualCod=" + actualCod +
                ", barcode='" + barcode + '\'' +
                ", cancelledAt=" + cancelledAt +
                ", cod=" + cod +
                ", consignee='" + consignee + '\'' +
                ", consigneeDetailAddress='" + consigneeDetailAddress + '\'' +
                ", consigneePhone='" + consigneePhone + '\'' +
                ", createdAt=" + createdAt +
                ", currentWarehouseCode='" + currentWarehouseCode + '\'' +
                ", currentWarehouseId=" + currentWarehouseId +
                ", customFields=" + customFields +
                ", deliveryDate=" + deliveryDate +
                ", deliveryDueDate=" + deliveryDueDate +
                ", deliveryLocationIdsPath='" + deliveryLocationIdsPath + '\'' +
                ", deliveryLocationNamesPath='" + deliveryLocationNamesPath + '\'' +
                ", deliveryNotes='" + deliveryNotes + '\'' +
                ", deliveryShift='" + deliveryShift + '\'' +
                ", deliveryTimes=" + deliveryTimes +
                ", deliveryWarehouseCode='" + deliveryWarehouseCode + '\'' +
                ", deliveryWarehouseId=" + deliveryWarehouseId +
                ", doneAt=" + doneAt +
                ", failedAt=" + failedAt +
                ", features=" + features +
                ", goods='" + goods + '\'' +
                ", height=" + height +
                ", inProcessAt=" + inProcessAt +
                ", internalFailedAt=" + internalFailedAt +
                ", isReturn=" + isReturn +
                ", lastExportAt=" + lastExportAt +
                ", lastImportAt=" + lastImportAt +
                ", lastInventoryAt=" + lastInventoryAt +
                ", lenght=" + lenght +
                ", merchantId=" + merchantId +
                ", merchantOrderCode='" + merchantOrderCode + '\'' +
                ", merchantUsername='" + merchantUsername + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", orderId=" + orderId +
                ", orderState='" + orderState + '\'' +
                ", pickupAddressId=" + pickupAddressId +
                ", pickupContact='" + pickupContact + '\'' +
                ", pickupDate=" + pickupDate +
                ", pickupDetailAddress='" + pickupDetailAddress + '\'' +
                ", pickupDueDate=" + pickupDueDate +
                ", pickupFullAddress='" + pickupFullAddress + '\'' +
                ", pickupLocationIdsPath='" + pickupLocationIdsPath + '\'' +
                ", pickupLocationNamesPath='" + pickupLocationNamesPath + '\'' +
                ", pickupNotes='" + pickupNotes + '\'' +
                ", pickupPhone='" + pickupPhone + '\'' +
                ", pickupRequestId=" + pickupRequestId +
                ", pickupShift='" + pickupShift + '\'' +
                ", pickupTimes=" + pickupTimes +
                ", pickupWarehouseCode='" + pickupWarehouseCode + '\'' +
                ", pickupWarehouseId=" + pickupWarehouseId +
                ", receiverPaymentAmount=" + receiverPaymentAmount +
                ", rejectAt=" + rejectAt +
                ", state='" + state + '\'' +
                ", totalFee=" + totalFee +
                ", totalMerchantFee=" + totalMerchantFee +
                ", transitFailureNotes='" + transitFailureNotes + '\'' +
                ", updatedAt=" + updatedAt +
                ", userId=" + userId +
                ", version=" + version +
                ", volume=" + volume +
                ", warehouseState='" + warehouseState + '\'' +
                ", weight=" + weight +
                ", width=" + width +
                ", consigneeCode=" + consigneeCode +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

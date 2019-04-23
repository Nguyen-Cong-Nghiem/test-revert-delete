package vn.shippo.rider.event.evententity;

import com.avaje.ebean.annotation.DbJsonB;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;


/**
 * The persistent class for the pickup_requests database table.
 *
 */
public class PickupRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SerializedName("id")
    @JsonProperty("id")
    private Integer id;

    @SerializedName("created_at")
    @JsonProperty("created_at")
    private Timestamp createdAt;

    @SerializedName("last_change_state_at")
    @JsonProperty("last_change_state_at")
    private Timestamp lastChangeStateAt;

    @SerializedName("merchant_user_id")
    @JsonProperty("merchant_user_id")
    private Integer merchantUserId;

    //	@Column(name = "metadata")
    @DbJsonB
    @SerializedName("metadata")
    @JsonProperty("metadata")
    private Object metadata;

    @SerializedName("no_orders")
    @JsonProperty("no_orders")
    private Integer noOrders;

    @SerializedName("pickup_contact_name")
    @JsonProperty("pickup_contact_name")
    private String pickupContactName;

    @SerializedName("pickup_contact_phone")
    @JsonProperty("pickup_contact_phone")
    private String pickupContactPhone;

    @SerializedName("pickup_detail_address")
    @JsonProperty("pickup_detail_address")
    private String pickupDetailAddress;

    @SerializedName("pickup_full_address")
    @JsonProperty("pickup_full_address")
    private String pickupFullAddress;

    @SerializedName("pickup_location_ids_path")
    @JsonProperty("pickup_location_ids_path")
    private String pickupLocationIdsPath;

    @SerializedName("state")
    @JsonProperty("state")
    private String state;

    @DbJsonB
    @SerializedName("task_ids")
    @JsonProperty("task_ids")
    private Object taskIds;

    @SerializedName("total_weight")
    @JsonProperty("total_weight")
    private Integer totalWeight;

    @SerializedName("updated_at")
    @JsonProperty("updated_at")
    private Timestamp updatedAt;

    @SerializedName("version")
    @JsonProperty("version")
    private Integer version;

    @SerializedName("warehouse_id")
    @JsonProperty("warehouse_id")
    private Integer warehouseId;

    public PickupRequest() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getMerchantUserId() {
        return merchantUserId;
    }

    public void setMerchantUserId(Integer merchantUserId) {
        this.merchantUserId = merchantUserId;
    }

    public Object getMetadata() {
        return this.metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }

    public Integer getNoOrders() {
        return this.noOrders;
    }

    public void setNoOrders(Integer noOrders) {
        this.noOrders = noOrders;
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

    public String getPickupDetailAddress() {
        return this.pickupDetailAddress;
    }

    public void setPickupDetailAddress(String pickupDetailAddress) {
        this.pickupDetailAddress = pickupDetailAddress;
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

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Object getTaskIds() {
        return this.taskIds;
    }

    public void setTaskIds(Object taskIds) {
        this.taskIds = taskIds;
    }

    public Integer getTotalWeight() {
        return this.totalWeight;
    }

    public void setTotalWeight(Integer totalWeight) {
        this.totalWeight = totalWeight;
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

    public Integer getWarehouseId() {
        return this.warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Timestamp getLastChangeStateAt() {
        return lastChangeStateAt;
    }

    public void setLastChangeStateAt(Timestamp lastChangeStateAt) {
        this.lastChangeStateAt = lastChangeStateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PickupRequest)) return false;
        PickupRequest pickupRequest = (PickupRequest) o;
        return Objects.equals(getId(), pickupRequest.getId()) &&
                Objects.equals(getMerchantUserId(), pickupRequest.getMerchantUserId()) &&
                Objects.equals(getCreatedAt(), pickupRequest.getCreatedAt()) &&
                Objects.equals(getUpdatedAt(), pickupRequest.getUpdatedAt()) &&
                Objects.equals(getPickupLocationIdsPath(), pickupRequest.getPickupLocationIdsPath()) &&
                Objects.equals(getPickupDetailAddress(), pickupRequest.getPickupDetailAddress()) &&
                Objects.equals(getPickupContactName(), pickupRequest.getPickupContactName()) &&
                Objects.equals(getPickupContactPhone(), pickupRequest.getPickupContactPhone()) &&
                Objects.equals(getPickupFullAddress(), pickupRequest.getPickupFullAddress()) &&
                Objects.equals(getNoOrders(), pickupRequest.getNoOrders()) &&
                Objects.equals(getTaskIds(), pickupRequest.getTaskIds()) &&
                Objects.equals(getWarehouseId(), pickupRequest.getWarehouseId()) &&
                Objects.equals(getTotalWeight(), pickupRequest.getTotalWeight()) &&
                Objects.equals(getState(), pickupRequest.getState()) &&
                Objects.equals(getLastChangeStateAt(), pickupRequest.getLastChangeStateAt()) &&
                Objects.equals(getVersion(), pickupRequest.getVersion());
    }

    @Override
    public String toString() {
        return "PickupRequest{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", merchantUserId=" + merchantUserId +
                ", metadata=" + metadata +
                ", noOrders=" + noOrders +
                ", pickupContactName='" + pickupContactName + '\'' +
                ", pickupContactPhone='" + pickupContactPhone + '\'' +
                ", pickupDetailAddress='" + pickupDetailAddress + '\'' +
                ", pickupFullAddress='" + pickupFullAddress + '\'' +
                ", pickupLocationIdsPath='" + pickupLocationIdsPath + '\'' +
                ", state='" + state + '\'' +
                ", taskIds=" + taskIds +
                ", totalWeight=" + totalWeight +
                ", updatedAt=" + updatedAt +
                ", lastChangeStateAt=" + lastChangeStateAt +
                ", version=" + version +
                ", warehouseId=" + warehouseId +
                '}';
    }

    @Override
    public int hashCode() {
        return id;
    }

    public class State{
        public static final String NEW = "NEW";
        public static final String IN_PROCESS = "IN_PROCESS";
        public static final String ASSIGNED_TO = "ASSIGNED_TO";
        public static final String HAND_OVER = "HAND_OVER";
        public static final String DONE = "DONE";
        public static final String FAILED = "FAILED";
        public static final String CANCELLED = "CANCELLED";
    }

}
package vn.shippo.rider.event.evententity;

import com.avaje.ebean.annotation.DbJsonB;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;


/**
 * The persistent class for the pickup_request_orders database table.
 * 
 */
public class PickupRequestOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SerializedName("id")
	@JsonProperty("id")
	private Integer id;

	@SerializedName("created_at")
	@JsonProperty("created_at")
	private Timestamp createdAt;

	@SerializedName("is_night_delivery")
	@JsonProperty("is_night_delivery")
	private Boolean isNightDelivery;

	@DbJsonB
	@SerializedName("metadata")
	@JsonProperty("metadata")
	private Object metadata;

	@SerializedName("order_id")
	@JsonProperty("order_id")
	private Integer orderId;

	@SerializedName("request_id")
	@JsonProperty("request_id")
	private Integer requestId;

	@SerializedName("updated_at")
	@JsonProperty("updated_at")
	private Timestamp updatedAt;

	@SerializedName("version")
	@JsonProperty("version")
	private Integer version;

	private String barcode;

	@SerializedName("order_code")
	@JsonProperty("order_code")
	private String orderCode;

	public PickupRequestOrder() {
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

	public Boolean getIsNightDelivery() {
		return this.isNightDelivery;
	}

	public void setIsNightDelivery(Boolean isNightDelivery) {
		this.isNightDelivery = isNightDelivery;
	}

	public Object getMetadata() {
		return this.metadata;
	}

	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getRequestId() {
		return this.requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
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

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PickupRequestOrder)) return false;
		PickupRequestOrder pickupRequestOrder = (PickupRequestOrder) o;
		return Objects.equals(getId(), pickupRequestOrder.getId()) &&
				Objects.equals(getRequestId(), pickupRequestOrder.getRequestId()) &&
				Objects.equals(getOrderId(), pickupRequestOrder.getOrderId()) &&
				Objects.equals(getIsNightDelivery(), pickupRequestOrder.getIsNightDelivery()) &&
				Objects.equals(getVersion(), pickupRequestOrder.getVersion());
	}

	@Override
	public String toString() {
		return "PickupRequestOrder{" +
				"id=" + id +
				", createdAt=" + createdAt +
				", isNightDelivery=" + isNightDelivery +
				", metadata=" + metadata +
				", orderId=" + orderId +
				", requestId=" + requestId +
				", updatedAt=" + updatedAt +
				", version=" + version +
				'}';
	}

	@Override
	public int hashCode() {
		return id;
	}
}
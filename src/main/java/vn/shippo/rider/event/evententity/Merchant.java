package vn.shippo.rider.event.evententity;

import com.avaje.ebean.annotation.DbJsonB;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;


/**
 * The persistent class for the merchants database table.
 * 
 */
public class Merchant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String avatar;

	@DbJsonB
	private Object banks;

	private Timestamp birthday;

	private String code;

	@SerializedName("created_at")
	@JsonProperty("created_at")
	private Timestamp createdAt;

	@SerializedName("trial_policy_expired_date")
	@JsonProperty("trial_policy_expired_date")
	private Timestamp trialPolicyExpiredDate;

	private String email;

	@SerializedName("first_order_at")
	@JsonProperty("first_order_at")
	private Timestamp firstOrderAt;

	@SerializedName("full_name")
	@JsonProperty("full_name")
	private String fullName;

	private Integer gender;

	@SerializedName("is_email_verified")
	@JsonProperty("is_email_verified")
	private Boolean isEmailVerified;

	@SerializedName("last_order_at")
	@JsonProperty("last_order_at")
	private Timestamp lastOrderAt;

	@DbJsonB
	private Object metadata;

	private String mobile;

	@SerializedName("real_balance")
	@JsonProperty("real_balance")
	private double realBalance;

	private String state;

	@SerializedName("updated_at")
	@JsonProperty("updated_at")
	private Timestamp updatedAt;

	@SerializedName("user_id")
	@JsonProperty("user_id")
	private Integer userId;

	private String username;

	private Integer version;

	public Merchant() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Object getBanks() {
		return this.banks;
	}

	public void setBanks(Object banks) {
		this.banks = banks;
	}

	public Timestamp getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getFirstOrderAt() {
		return this.firstOrderAt;
	}

	public void setFirstOrderAt(Timestamp firstOrderAt) {
		this.firstOrderAt = firstOrderAt;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Boolean getIsEmailVerified() {
		return this.isEmailVerified;
	}

	public void setIsEmailVerified(Boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public Timestamp getLastOrderAt() {
		return this.lastOrderAt;
	}

	public void setLastOrderAt(Timestamp lastOrderAt) {
		this.lastOrderAt = lastOrderAt;
	}

	public Object getMetadata() {
		return this.metadata;
	}

	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public double getRealBalance() {
		return this.realBalance;
	}

	public void setRealBalance(double realBalance) {
		this.realBalance = realBalance;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
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

	public Timestamp getTrialPolicyExpiredDate() {
		return trialPolicyExpiredDate;
	}

	public void setTrialPolicyExpiredDate(Timestamp trialPolicyExpiredDate) {
		this.trialPolicyExpiredDate = trialPolicyExpiredDate;
	}

	public Boolean getEmailVerified() {
		return isEmailVerified;
	}

	public void setEmailVerified(Boolean emailVerified) {
		isEmailVerified = emailVerified;
	}

	@Override
    public int hashCode() {
        return id;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Merchant)) return false;
		Merchant merchant = (Merchant) o;
		return Double.compare(merchant.getRealBalance(), getRealBalance()) == 0 &&
				Objects.equals(getId(), merchant.getId()) &&
				Objects.equals(getAvatar(), merchant.getAvatar()) &&
				Objects.equals(getBanks(), merchant.getBanks()) &&
				Objects.equals(getBirthday(), merchant.getBirthday()) &&
				Objects.equals(getCode(), merchant.getCode()) &&
				Objects.equals(getCreatedAt(), merchant.getCreatedAt()) &&
				Objects.equals(getTrialPolicyExpiredDate(), merchant.getTrialPolicyExpiredDate()) &&
				Objects.equals(getEmail(), merchant.getEmail()) &&
				Objects.equals(getFirstOrderAt(), merchant.getFirstOrderAt()) &&
				Objects.equals(getFullName(), merchant.getFullName()) &&
				Objects.equals(getGender(), merchant.getGender()) &&
				Objects.equals(getIsEmailVerified(), merchant.getIsEmailVerified()) &&
				Objects.equals(getLastOrderAt(), merchant.getLastOrderAt()) &&
				Objects.equals(getMetadata(), merchant.getMetadata()) &&
				Objects.equals(getMobile(), merchant.getMobile()) &&
				Objects.equals(getState(), merchant.getState()) &&
				Objects.equals(getUpdatedAt(), merchant.getUpdatedAt()) &&
				Objects.equals(getUserId(), merchant.getUserId()) &&
				Objects.equals(getUsername(), merchant.getUsername()) &&
				Objects.equals(getVersion(), merchant.getVersion());
	}

    @Override
    public String toString() {
        return "Merchant{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", username='" + username + '\'' +
                ", banks=" + banks +
                ", birthday=" + birthday +
                ", code='" + code + '\'' +
                ", createdAt=" + createdAt +
                ", email='" + email + '\'' +
                ", firstOrderAt=" + firstOrderAt +
                ", fullName='" + fullName + '\'' +
                ", gender=" + gender +
                ", isEmailVerified=" + isEmailVerified +
                ", lastOrderAt=" + lastOrderAt +
                ", metadata=" + metadata +
                ", mobile='" + mobile + '\'' +
                ", realBalance=" + realBalance +
                ", state='" + state + '\'' +
                ", updatedAt=" + updatedAt +
                ", userId=" + userId +
                ", version=" + version +
                '}';
    }
}
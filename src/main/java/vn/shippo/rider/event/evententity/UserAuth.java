package vn.shippo.rider.event.evententity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

public class UserAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String email;
    private String section;
    private String state;

    @SerializedName("is_email_verified")
    @JsonProperty("is_email_verified")
    private Boolean isEmailVerified;

    @SerializedName("is_required_change_pass")
    @JsonProperty("is_required_change_pass")
    private Boolean isRequiredChangePass;

    private String password;
    @SerializedName("change_pass_at")
    @JsonProperty("change_pass_at")

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp changePassAt;

    @Basic(optional = false)
    @SerializedName("created_at")
    @JsonProperty("created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;

    @Basic(optional = false)
    @SerializedName("updated_at")
    @JsonProperty("updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updatedAt;

    private Integer version;
    private String level;

    public Integer getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public Boolean getRequiredChangePass() {
        return isRequiredChangePass;
    }

    public void setRequiredChangePass(Boolean requiredChangePass) {
        isRequiredChangePass = requiredChangePass;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getChangePassAt() {
        return changePassAt;
    }

    public void setChangePassAt(Timestamp changePassAt) {
        this.changePassAt = changePassAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}


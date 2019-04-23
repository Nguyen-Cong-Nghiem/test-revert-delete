package vn.shippo.rider.entity;

import com.avaje.ebean.annotation.DbJson;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="transportation_task_comments")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransportationTaskComment {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column(name="created_by")
    @SerializedName("created_by")
    @JsonProperty("created_by")
    private Integer createdBy;

    @Column(name = "task_id")
    @SerializedName("task_id")
    @JsonProperty("files")
    private Integer taskId;

    @Column(name = "files")
    @SerializedName("files")
    @JsonProperty("files")
    private String files;

    @Column(name = "context_type")
    @SerializedName("context_type")
    @JsonProperty("context_type")
    private String contextType;

    @DbJson
    @Column(name = "context")
    @SerializedName("context")
    @JsonProperty("context")
    private Object context;

    @Column(name = "scope")
    @SerializedName("scope")
    @JsonProperty("scope")
    private String scope;

    @Column(name = "version")
    @SerializedName("version")
    @JsonProperty("version")
    private Integer version;

    @Column(name = "created_at")
    @SerializedName("created_at")
    @JsonProperty("created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @SerializedName("updated_at")
    @JsonProperty("updated_at")
    private Timestamp updatedAt;

    @Column(name = "username")
    @SerializedName("username")
    @JsonProperty("username")
    private String username;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getContextType() {
        return contextType;
    }

    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    public Object getContext() {
        return context;
    }

    public void setContext(Object context) {
        this.context = context;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

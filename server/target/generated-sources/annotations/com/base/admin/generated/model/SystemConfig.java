package com.base.admin.generated.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * SystemConfig
 */

@JsonTypeName("systemConfig")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SystemConfig implements Serializable {

  private static final long serialVersionUID = 1L;

  private String groupName;

  private String name;

  private String value;

  private String description;

  public SystemConfig groupName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  /**
   * 组名
   * @return groupName
  */
  
  @Schema(name = "groupName", description = "组名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("groupName")
  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public SystemConfig name(String name) {
    this.name = name;
    return this;
  }

  /**
   * 配置名称
   * @return name
  */
  
  @Schema(name = "name", description = "配置名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public SystemConfig value(String value) {
    this.value = value;
    return this;
  }

  /**
   * 配置内容
   * @return value
  */
  
  @Schema(name = "value", description = "配置内容", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("value")
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public SystemConfig description(String description) {
    this.description = description;
    return this;
  }

  /**
   * 说明
   * @return description
  */
  
  @Schema(name = "description", description = "说明", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SystemConfig systemConfig = (SystemConfig) o;
    return Objects.equals(this.groupName, systemConfig.groupName) &&
        Objects.equals(this.name, systemConfig.name) &&
        Objects.equals(this.value, systemConfig.value) &&
        Objects.equals(this.description, systemConfig.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupName, name, value, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SystemConfig {\n");
    sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


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
 * ErrorModel
 */

@JsonTypeName("errorModel")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ErrorModel implements Serializable {

  private static final long serialVersionUID = 1L;

  private String errorCode;

  private String devMessage;

  public ErrorModel errorCode(String errorCode) {
    this.errorCode = errorCode;
    return this;
  }

  /**
   * 错误编码
   * @return errorCode
  */
  
  @Schema(name = "errorCode", description = "错误编码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("errorCode")
  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public ErrorModel devMessage(String devMessage) {
    this.devMessage = devMessage;
    return this;
  }

  /**
   * 错误说明
   * @return devMessage
  */
  
  @Schema(name = "devMessage", description = "错误说明", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("devMessage")
  public String getDevMessage() {
    return devMessage;
  }

  public void setDevMessage(String devMessage) {
    this.devMessage = devMessage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorModel errorModel = (ErrorModel) o;
    return Objects.equals(this.errorCode, errorModel.errorCode) &&
        Objects.equals(this.devMessage, errorModel.devMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errorCode, devMessage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorModel {\n");
    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
    sb.append("    devMessage: ").append(toIndentedString(devMessage)).append("\n");
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


/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.base.admin.generated.controller.interfaces;

import com.base.admin.generated.model.ErrorModel;
import com.base.admin.generated.model.SystemConfig;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Validated
@Tag(name = "system-config", description = "系统配置")
public interface SystemConfigApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /system/config
     * 系统配置列表
     *
     * @param name 名称 (optional)
     * @return 查询系统配置列表成功 (status code 200)
     *         or 非法请求 (status code 400)
     *         or 服务器未知错误 (status code 500)
     */
    @Operation(
        operationId = "getSystemConfig",
        description = "系统配置列表",
        tags = { "system-config" },
        responses = {
            @ApiResponse(responseCode = "200", description = "查询系统配置列表成功", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SystemConfig.class)))
            }),
            @ApiResponse(responseCode = "400", description = "非法请求", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))
            }),
            @ApiResponse(responseCode = "500", description = "服务器未知错误", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/system/config",
        produces = { "application/json" }
    )
    default ResponseEntity<List<SystemConfig>> getSystemConfig(
        @Parameter(name = "name", description = "名称", in = ParameterIn.QUERY) @Valid @RequestParam(value = "name", required = false) String name
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"groupName\" : \"groupName\", \"name\" : \"name\", \"description\" : \"description\", \"value\" : \"value\" }, { \"groupName\" : \"groupName\", \"name\" : \"name\", \"description\" : \"description\", \"value\" : \"value\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}

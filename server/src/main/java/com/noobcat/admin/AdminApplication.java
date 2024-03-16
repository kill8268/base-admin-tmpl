package com.noobcat.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
@MapperScan("com.noobcat.admin.mapper")
public class AdminApplication {

  public static void main(String[] args) {
    SpringApplication.run(AdminApplication.class, args);
  }

  @Bean
  public GroupedOpenApi swggerGroup(@Value("${springdoc.version}") String appVersion,
      @Value("${springdoc.version}") String appTitle, @Value("${springdoc.group}") String appGroup) {
    return GroupedOpenApi.builder().group(appGroup)
        .addOpenApiCustomizer(openApi -> openApi.info(new Info().title(appTitle).version(appVersion)))
        .packagesToScan("com.noobcat.admin")
        .build();
  }
}

package com.noobcat.admin.mapper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {

  @Bean
  public FillDataInterceptor fillDataInterceptor() {
    return new FillDataInterceptor();
  }
}

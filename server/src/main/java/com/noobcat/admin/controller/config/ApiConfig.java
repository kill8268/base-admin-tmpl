package com.noobcat.admin.controller.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.noobcat.admin.component.JwtUtil;
import com.noobcat.admin.service.AuthService;

@Configuration
public class ApiConfig implements WebMvcConfigurer {

  private JwtUtil jwtUtil;

  private AuthService authService;

  public ApiConfig(JwtUtil jwtUtil, AuthService authService) {
    this.jwtUtil = jwtUtil;
    this.authService = authService;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(new UserInterceptor(jwtUtil, authService))
        .addPathPatterns("/**")
        .excludePathPatterns("/swagger-ui/*")
        .excludePathPatterns("/v3/api-docs")
        .excludePathPatterns("/v3/api-docs/*")
        .excludePathPatterns("/auth/sign-in");
    ;
  }
}

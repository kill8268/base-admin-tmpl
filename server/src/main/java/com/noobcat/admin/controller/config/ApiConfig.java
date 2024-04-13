package com.noobcat.admin.controller.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.noobcat.admin.component.JwtUtil;
import com.noobcat.admin.service.UserService;

@Configuration
public class ApiConfig implements WebMvcConfigurer {

  private JwtUtil jwtUtil;

  private UserService userService;

  public ApiConfig(JwtUtil jwtUtil, UserService authService) {
    this.jwtUtil = jwtUtil;
    this.userService = authService;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(new UserInterceptor(jwtUtil, userService))
        .addPathPatterns("/**")
        .excludePathPatterns("/swagger-ui/*")
        .excludePathPatterns("/v3/api-docs")
        .excludePathPatterns("/v3/api-docs/*")
        .excludePathPatterns("/user/sign-in");
    ;
  }
}

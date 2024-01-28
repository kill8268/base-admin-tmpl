package com.base.admin.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.base.admin.Component.JwtUtil;
import com.base.admin.interceptor.UserInterceptor;
import com.base.admin.service.AuthService;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  private JwtUtil jwtUtil;

  private AuthService authService;

  public WebConfig(JwtUtil jwtUtil, AuthService authService) {
    this.jwtUtil = jwtUtil;
    this.authService = authService;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new UserInterceptor(jwtUtil, authService)).addPathPatterns("/**");
  }
}

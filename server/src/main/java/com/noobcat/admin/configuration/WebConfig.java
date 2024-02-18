package com.noobcat.admin.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.noobcat.admin.component.JwtUtil;
import com.noobcat.admin.interceptor.UserInterceptor;
import com.noobcat.admin.service.AuthService;

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
    registry
        .addInterceptor(new UserInterceptor(jwtUtil, authService))
        .addPathPatterns("/**")
        .excludePathPatterns("/swagger-ui/")
        .excludePathPatterns("/swagger-ui.html")
        .excludePathPatterns("/auth/sign-in");
    ;
  }
}

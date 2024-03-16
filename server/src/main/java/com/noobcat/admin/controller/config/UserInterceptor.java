package com.noobcat.admin.controller.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.openapitools.model.Auth;
import org.springframework.web.servlet.HandlerInterceptor;

import com.noobcat.admin.component.JwtUtil;
import com.noobcat.admin.context.UserContext;
import com.noobcat.admin.service.AuthService;

import io.jsonwebtoken.ExpiredJwtException;

public class UserInterceptor implements HandlerInterceptor {

  private JwtUtil jwtUtil;

  private AuthService authService;

  public UserInterceptor(JwtUtil jwtUtil, AuthService authService) {
    this.jwtUtil = jwtUtil;
    this.authService = authService;
  }

  private static final void setEerrorResponse(HttpServletResponse response, String errorMessage) throws Exception {
    // 设置响应状态码为401
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    // 设置响应内容类型和编码
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    // 将错误信息写入响应体
    response.getWriter().write(errorMessage);
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    // 从请求头中获取JWT令牌
    String token = request.getHeader("Authorization");
    // 使用JwtUtil验证令牌
    if (token == null) {
      setEerrorResponse(response, "{\"message\": \"未登录\"}");
      return false;
    }
    try {
      Auth auth = authService.getById(jwtUtil.extractUserId(token));
      if (auth == null) {
        setEerrorResponse(response, "{\"message\": \"非法访问\"}");
        return false;
      }
      if (!auth.getEnable()) {
        setEerrorResponse(response, "{\"message\": \"账号已禁用\"}");
        return false;
      }
      UserContext.currentUser.set(auth);
      return true;
    } catch (ExpiredJwtException e) {
      setEerrorResponse(response, "{\"message\": \"登录超时\"}");
      return false;
    } catch (Exception e) {
      setEerrorResponse(response, "{\"message\": \"非法访问\"}");
      return false;
    }
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    UserContext.currentUser.remove();
  }
}

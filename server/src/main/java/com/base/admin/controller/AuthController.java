package com.base.admin.controller;

import java.util.List;
import org.openapitools.api.AuthApi;
import org.openapitools.model.Auth;
import org.openapitools.model.SignInRequest;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.base.admin.Component.JwtUtil;
import com.base.admin.service.AuthService;
import com.base.admin.utils.PasswordEncoder;

@RestController
public class AuthController implements AuthApi {
  @Autowired
  private AuthService authService;

  @Autowired
  private JwtUtil jwtUtil;

  @Override
  public ResponseEntity<String> signIn(SignInRequest signInRequest) {
    Auth auth = authService.getOne(Wrappers.<Auth>lambdaQuery()
        .eq(Auth::getUsername, signInRequest.getAuth()).or()
        .eq(Auth::getPhone, signInRequest.getAuth()));
    if (auth == null) {
      return ResponseEntity.badRequest().body("账号不存在");
    }
    if (!auth.getEnable()) {
      return ResponseEntity.badRequest().body("账号已禁用");
    }
    boolean isMatch = PasswordEncoder.matches(signInRequest.getPassword(), auth.getPassword());
    if (!isMatch) {
      return ResponseEntity.badRequest().body("密码错误");
    }
    return ResponseEntity.ok(jwtUtil.generateToken(auth.getId()));
  }

  @Override
  public ResponseEntity<List<Auth>> getList(String username, String phone) {
    List<Auth> list = authService.list(
        Wrappers.<Auth>lambdaQuery()
            .like(!ObjectUtils.isEmpty(username), Auth::getUsername, username)
            .like(!ObjectUtils.isEmpty(phone), Auth::getPhone, phone));
    return ResponseEntity.ok(list.stream().peek(auth -> auth.setPassword(null)).toList());
  }

  @Override
  public ResponseEntity<Auth> getAuth(String id) {
    Auth auth = authService.getById(id);
    auth.setPassword(null);
    return ResponseEntity.ok(auth);
  }

  @Override
  public ResponseEntity<Auth> createAuth(Auth auth) {
    auth.setId(null);
    auth.setPassword(PasswordEncoder.encode(auth.getPassword()));
    authService.save(auth);
    return ResponseEntity.ok(auth);
  }

  @Override
  public ResponseEntity<Auth> updateAuth(String id, Auth auth) {
    auth.setId(id);
    authService.updateById(auth);
    return ResponseEntity.ok(auth);
  }

  @Override
  public ResponseEntity<Auth> toggleEnable(String id) {
    Auth auth = authService.getById(id);
    auth.setEnable(!auth.getEnable());
    authService.updateById(auth);
    return ResponseEntity.ok(auth);
  }
}

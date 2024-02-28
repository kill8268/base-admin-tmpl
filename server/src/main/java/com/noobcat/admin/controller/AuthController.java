package com.noobcat.admin.controller;

import java.net.URI;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.openapitools.api.AuthApi;
import org.openapitools.model.Auth;
import org.openapitools.model.AuthPage;
import org.openapitools.model.GetList200Response;
import org.openapitools.model.SignIn200Response;
import org.openapitools.model.SignInRequest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.noobcat.admin.component.JwtUtil;
import com.noobcat.admin.service.AuthService;

import com.noobcat.admin.component.PasswordEncoder;
import com.noobcat.admin.context.UserContext;

@RestController
public class AuthController implements AuthApi {
  @Autowired
  private AuthService authService;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public ResponseEntity<SignIn200Response> signIn(SignInRequest signInRequest) {
    Auth auth = authService.getOne(Wrappers.<Auth>lambdaQuery()
        .eq(Auth::getUsername, signInRequest.getAuth()).or()
        .eq(Auth::getPhone, signInRequest.getAuth()));
    SignIn200Response response = new SignIn200Response();
    if (auth == null) {
      return ResponseEntity.badRequest().body(response.message("账号不存在"));
    }
    if (!auth.getEnable()) {
      return ResponseEntity.badRequest().body(response.message("账号已禁用"));
    }
    boolean isMatch = passwordEncoder.matches(signInRequest.getPassword(), auth.getPassword());
    if (!isMatch) {
      return ResponseEntity.badRequest().body(response.message("密码错误"));
    }
    return ResponseEntity.ok(response.result(jwtUtil.generateToken(auth.getId())));
  }

  @Override
  public ResponseEntity<AuthPage> page(Integer current, Integer size, String username, String phone) {
    IPage<Auth> page = authService.page(new Page<Auth>(current, size),
        Wrappers.<Auth>lambdaQuery()
            .like(!ObjectUtils.isEmpty(username), Auth::getUsername, username)
            .like(!ObjectUtils.isEmpty(phone), Auth::getPhone, phone));
    return ResponseEntity.ok(new ModelMapper().map(page, AuthPage.class));
  }

  @Override
  public ResponseEntity<GetList200Response> getList(String username, String phone) {
    List<Auth> list = authService.list(
        Wrappers.<Auth>lambdaQuery()
            .like(!ObjectUtils.isEmpty(username), Auth::getUsername, username)
            .like(!ObjectUtils.isEmpty(phone), Auth::getPhone, phone));
    return ResponseEntity.ok(new GetList200Response().result(list.stream().peek(auth -> auth.setPassword(null)).toList()) );
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
    if (authService.getOne(Wrappers.<Auth>lambdaQuery().eq(Auth::getUsername, auth.getUsername())) != null) {
      throw new RuntimeException("用户名已存在");
    }
    if (authService.getOne(Wrappers.<Auth>lambdaQuery().eq(Auth::getPhone, auth.getPhone())) != null) {
      throw new RuntimeException("手机号已存在");
    }
    System.out.println(UserContext.currentUser.get().toString());
    auth.setPassword(passwordEncoder.encode(auth.getPassword()));
    authService.save(auth);
    auth.setPassword(null);
    return ResponseEntity.created(URI.create("/auth/" + auth.getId())).build();
  }

  @Override
  public ResponseEntity<Auth> updateAuth(String id, Auth auth) {
    auth.setId(id);
    authService.updateById(auth);
    auth.setPassword(null);
    return ResponseEntity.created(URI.create("/auth/" + auth.getId())).build();
  }

  @Override
  public ResponseEntity<Auth> toggleEnable(String id) {
    Auth auth = authService.getById(id);
    auth.setEnable(!auth.getEnable());
    authService.updateById(auth);
    return ResponseEntity.ok(auth);
  }
}

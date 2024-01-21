package com.base.admin.controller;

import java.util.List;
import org.openapitools.api.AuthApi;
import org.openapitools.model.Auth;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.base.admin.service.AuthService;

@RestController
public class AuthController implements AuthApi {
  @Autowired
  private AuthService authService;

  @Override
  public ResponseEntity<List<Auth>> getList(String authName, String phone) {
    List<Auth> list = authService.list(
        Wrappers.<Auth>lambdaQuery()
            .like(!ObjectUtils.isEmpty(authName), Auth::getAuthName, authName)
            .like(!ObjectUtils.isEmpty(phone), Auth::getPhone, phone));
    return ResponseEntity.ok(list);
  }
}

package com.noobcat.admin.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.openapitools.api.UserApi;
import org.openapitools.model.User;
import org.openapitools.model.UserPage;
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

import com.noobcat.admin.component.PasswordEncoder;
import com.noobcat.admin.context.UserContext;
import com.noobcat.admin.mapper.UserMapper;

@RestController
public class UserController implements UserApi {
  @Autowired
  private UserMapper userMapper;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public ResponseEntity<SignIn200Response> signIn(SignInRequest signInRequest) {
    User auth = userMapper.selectOne(Wrappers.<User>lambdaQuery()
        .eq(User::getUsername, signInRequest.getAuth()).or()
        .eq(User::getPhone, signInRequest.getAuth()));
    if (auth == null) {
      throw new IllegalArgumentException("账号不存在");
    }
    if (!auth.getEnable()) {
      throw new IllegalArgumentException("账号已禁用");
    }
    boolean isMatch = passwordEncoder.matches(signInRequest.getPassword(), auth.getPassword());
    if (!isMatch) {
      throw new IllegalArgumentException("密码错误");
    }
    return ResponseEntity.ok(new SignIn200Response().token(jwtUtil.generateToken(auth.getUuid())));
  }

  @Override
  public ResponseEntity<User> getInfo() {
    User auth = userMapper
        .selectOne(
            Wrappers.<User>lambdaQuery()
                .eq(User::getUuid, UserContext.currentUser.get().getUuid()));
    auth.setPassword(null);
    return ResponseEntity.ok(new ModelMapper().map(auth, User.class));
  }

  @Override
  public ResponseEntity<UserPage> authPage(Integer size, Integer current, String name, String phone) {
    IPage<User> page = userMapper.selectPage(new Page<User>(current, size),
        Wrappers.<User>lambdaQuery()
            .like(!ObjectUtils.isEmpty(name), User::getUsername, name)
            .like(!ObjectUtils.isEmpty(phone), User::getPhone, phone));
    return ResponseEntity.ok(new ModelMapper().map(page, UserPage.class));
  }

  @Override
  public ResponseEntity<List<User>> getAuthList(String name, String phone) {
    List<User> list = userMapper.selectList(
        Wrappers.<User>lambdaQuery()
            .like(!ObjectUtils.isEmpty(name), User::getUsername, name)
            .like(!ObjectUtils.isEmpty(phone), User::getPhone, phone));
    return ResponseEntity.ok(list);
  }

  @Override
  public ResponseEntity<User> getAuth(String user) {
    User auth = userMapper.selectOne(
        Wrappers.<User>lambdaQuery()
            .eq(User::getUuid, user));
    auth.setPassword(null);
    return ResponseEntity.ok(auth);
  }

  @Override
  public ResponseEntity<User> createAuth(User user) {
    user.setId(null);
    if (userMapper.selectOne(Wrappers.<User>lambdaQuery()
        .eq(User::getUsername, user.getUsername())) != null) {
      throw new RuntimeException("用户名已存在");
    }
    if (userMapper.selectOne(Wrappers.<User>lambdaQuery()
        .eq(User::getPhone, user.getPhone())) != null) {
      throw new RuntimeException("手机号已存在");
    }
    System.out.println(UserContext.currentUser.get().toString());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userMapper.insert(user);
    user.setPassword(null);
    return ResponseEntity.created(URI.create("/user/" + user.getUuid())).build();
  }

  @Override
  public ResponseEntity<User> updateAuth(String uuid, User user) {
    user.setUuid(uuid);
    userMapper.updateById(user);
    return ResponseEntity.created(URI.create("/user/" + user.getUuid())).build();
  }

  @Override
  public ResponseEntity<User> authToggleEnable(String id) {
    User auth = userMapper.selectById(id);
    auth.setEnable(!auth.getEnable());
    userMapper.updateById(auth);
    return ResponseEntity.ok(auth);
  }
}

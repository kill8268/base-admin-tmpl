package com.noobcat.admin.service;

import org.openapitools.model.User;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.noobcat.admin.mapper.UserMapper;

import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}

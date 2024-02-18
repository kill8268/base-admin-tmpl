package com.noobcat.admin.service;

import org.openapitools.model.Auth;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.noobcat.admin.mapper.AuthMapper;

import org.springframework.stereotype.Service;

@Service
public class AuthService extends ServiceImpl<AuthMapper, Auth> {
}

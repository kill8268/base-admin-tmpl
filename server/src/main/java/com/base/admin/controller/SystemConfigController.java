package com.base.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.base.admin.bean.SystemConfigBean;
import com.base.admin.controller.map.SystemConfigMap;
import com.base.admin.generated.controller.interfaces.SystemConfigApi;
import com.base.admin.generated.model.SystemConfig;
import com.base.admin.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class SystemConfigController implements SystemConfigApi {

    @Autowired
    private SystemConfigService systemConfigService;
    @Autowired
    private SystemConfigMap systemConfigMap;

    @Override
    public ResponseEntity<List<SystemConfig>> getSystemConfig(String name) {
        return ResponseEntity.ok(systemConfigMap.convert(systemConfigService.list(Wrappers.<SystemConfigBean>lambdaQuery()
                .eq(!ObjectUtils.isEmpty(name), SystemConfigBean::getName, name))));
    }
}

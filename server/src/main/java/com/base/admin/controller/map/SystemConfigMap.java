package com.base.admin.controller.map;

import com.base.admin.bean.SystemConfigBean;
import com.base.admin.generated.model.SystemConfig;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SystemConfigMap {

    SystemConfig convert(SystemConfigBean systemConfigBean);
    List<SystemConfig> convert(List<SystemConfigBean> systemConfigBean);

}

package com.base.admin.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("system_config")
public class SystemConfigBean {

    private String groupName;
    private String name;
    private String value;
    private String description;

}

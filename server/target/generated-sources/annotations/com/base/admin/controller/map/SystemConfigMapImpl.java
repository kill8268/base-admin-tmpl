package com.base.admin.controller.map;

import com.base.admin.bean.SystemConfigBean;
import com.base.admin.generated.model.SystemConfig;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-18T22:40:26+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (N/A)"
)
@Component
public class SystemConfigMapImpl implements SystemConfigMap {

    @Override
    public SystemConfig convert(SystemConfigBean systemConfigBean) {
        if ( systemConfigBean == null ) {
            return null;
        }

        SystemConfig systemConfig = new SystemConfig();

        systemConfig.setGroupName( systemConfigBean.getGroupName() );
        systemConfig.setName( systemConfigBean.getName() );
        systemConfig.setValue( systemConfigBean.getValue() );
        systemConfig.setDescription( systemConfigBean.getDescription() );

        return systemConfig;
    }

    @Override
    public List<SystemConfig> convert(List<SystemConfigBean> systemConfigBean) {
        if ( systemConfigBean == null ) {
            return null;
        }

        List<SystemConfig> list = new ArrayList<SystemConfig>( systemConfigBean.size() );
        for ( SystemConfigBean systemConfigBean1 : systemConfigBean ) {
            list.add( convert( systemConfigBean1 ) );
        }

        return list;
    }
}

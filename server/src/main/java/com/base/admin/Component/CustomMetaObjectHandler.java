package com.base.admin.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.base.admin.context.UserContext;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {

  @Override
  public void insertFill(MetaObject metaObject) {
    this.strictInsertFill(metaObject, "createdBy", String.class, UserContext.currentUser.get().getId()); // 模拟设置为admin
    this.strictInsertFill(metaObject, "updatedDate", Date.class, new Date());
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    this.strictUpdateFill(metaObject, "updatedDate", Date.class, new Date());
  }
}

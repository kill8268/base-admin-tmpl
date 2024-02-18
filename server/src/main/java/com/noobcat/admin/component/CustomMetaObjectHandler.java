package com.noobcat.admin.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.noobcat.admin.context.UserContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {

  @Override
  public void insertFill(MetaObject metaObject) {
    System.out.println("start insert fill ....");
    if (metaObject.hasSetter("createdBy")) {
      this.strictInsertFill(metaObject, "createdBy", String.class, UserContext.currentUser.get().getId());
    }
    if (metaObject.hasSetter("createdAt")) {
      this.strictInsertFill(metaObject, "createdAt", Date.class, new Date());
    }
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    if (metaObject.hasSetter("updatedBy")) {
      this.strictUpdateFill(metaObject, "updatedBy", String.class, UserContext.currentUser.get().getId());
    }
    if (metaObject.hasSetter("updatedAt")) {
      this.strictUpdateFill(metaObject, "updatedAt", Date.class, new Date());
    }
  }
}

package com.noobcat.admin.mapper.config;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.springframework.util.ReflectionUtils;

import com.noobcat.admin.context.UserContext;
import com.noobcat.admin.utils.SqlUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Properties;

@Intercepts({
    @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class FillDataInterceptor implements Interceptor {

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
    String sql = statementHandler.getBoundSql().getSql();
    if (sql.startsWith("INSERT INTO")) {
      String newSql = new SqlUtil(sql)
          .setColumns("updated_At", LocalDateTime.now().toString())
          .setColumns("created_At", LocalDateTime.now().toString())
          .setColumns("created_By", UserContext.currentUser.get().getId())
          .setColumns("updated_By", UserContext.currentUser.get().getId())
          .getSql();
      Field field = ReflectionUtils.findField(BoundSql.class, "sql");
      if (field != null) {
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, statementHandler.getBoundSql(), newSql);
      }
    }
    if (sql.startsWith("UPDATE")) {
      String newSql = new SqlUtil(sql)
          .setColumns("updated_At", LocalDateTime.now().toString())
          .setColumns("updated_By", UserContext.currentUser.get().getId())
          .getSql();
      Field field = ReflectionUtils.findField(BoundSql.class, "sql");
      if (field != null) {
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, statementHandler.getBoundSql(), newSql);
      }
    }
    return invocation.proceed();
  }

  @Override
  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  @Override
  public void setProperties(Properties properties) {
  }
}

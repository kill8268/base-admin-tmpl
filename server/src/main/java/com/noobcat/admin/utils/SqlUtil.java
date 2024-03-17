package com.noobcat.admin.utils;

import java.util.List;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.statement.update.UpdateSet;

/**
 * SqlUtil
 */
public class SqlUtil {
  Statement statement;

  List<Column> columns;

  String type;

  public SqlUtil(String sql) {
    try {
      this.statement = CCJSqlParserUtil.parse(sql);
      if (this.statement instanceof Insert) {
        this.type = "INSERT";
      }
      if (this.statement instanceof Update) {
        this.type = "UPDATE";
      }
    } catch (JSQLParserException e) {
      System.err.println("fill sql data error");
    }
  }

  public SqlUtil setColumns(String name, String value) {
    if (this.type.equals("INSERT")) {
      Insert insert = (Insert) statement;
      insert.addColumns(new Column(name));
      insert.getItemsList(ExpressionList.class).getExpressions().add(new StringValue(value));
    }
    if (this.type.equals("UPDATE")) {
      Update update = (Update) statement;
      update.getUpdateSets().add(new UpdateSet(new Column(name), new StringValue(value)));
    }
    return this;
  }

  public String getSql() {
    return this.statement.toString();
  }

}

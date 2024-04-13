package com.noobcat.admin.utils;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.statement.update.UpdateSet;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;
import net.sf.jsqlparser.util.deparser.SelectDeParser;

/**
 * SqlUtil
 */
public class SqlUtil {
  Statement statement;

  public SqlUtil(String sql) {
    try {
      this.statement = CCJSqlParserUtil.parse(sql);
    } catch (JSQLParserException e) {
      System.err.println("fill sql data error");
    }
  }

  public SqlUtil setColumns(String name, String value) {
    if (this.statement instanceof Insert) {
      Insert insert = (Insert) statement;
      insert.addColumns(new Column(name));
      insert.getItemsList(ExpressionList.class).getExpressions().add(new StringValue(value));
    }
    if (this.statement instanceof Update) {
      Update update = (Update) statement;
      update.getUpdateSets().add(new UpdateSet(new Column(name), new StringValue(value)));
    }

    if (this.statement instanceof Select) {
      Select select = (Select) statement;
      ExpressionDeParser expressionDeParser = new ExpressionDeParser() {
        @Override
        public void visit(Column column) {
          if (column.getColumnName().equalsIgnoreCase(name)) {
            column.setColumnName(value);
          }
          super.visit(column);
        }
      };
      SelectDeParser selectDeParser = new SelectDeParser(expressionDeParser, new StringBuilder());
      expressionDeParser.setSelectVisitor(selectDeParser);
      expressionDeParser.setBuffer(selectDeParser.getBuffer());
      select.getSelectBody().accept(selectDeParser);
      PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
      Table table = (Table) plainSelect.getFromItem();
      table.setAlias(new Alias("m"));
    }
    return this;
  }

  public String getSql() {
    return this.statement.toString();
  }

}

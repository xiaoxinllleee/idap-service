package org.cmms.common.util;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.util.TablesNamesFinder;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;
import net.sf.jsqlparser.util.deparser.SelectDeParser;
import net.sf.jsqlparser.util.deparser.UpdateDeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author: fuzongle
 * @description:  SQL 解析器
 **/
@Component
public class SQLParser {


    protected static class SQLParserSelect extends SelectDeParser {
        private String schemaName;

        public SQLParserSelect(ExpressionVisitor expressionVisitor, StringBuilder buffer) {
            super(expressionVisitor, buffer);
        }

        public String getSchemaName() {
            return schemaName;
        }

        public void setSchemaName(String schemaName) {
            this.schemaName = schemaName;
        }

        @Override
        public void visit(Table tableName) {
            tableName.setSchemaName(schemaName);
            StringBuilder buffer = getBuffer();
            buffer.append(tableName.getFullyQualifiedName());
            Pivot pivot = tableName.getPivot();
            if (pivot != null) {
                pivot.accept(this);
            }
            Alias alias = tableName.getAlias();
            if (alias != null) {
                buffer.append(alias);
            }
        }
    }



    public static String doInsert(Insert insert, String schemaName) {
        Table table = insert.getTable();
        if( table.getSchemaName()==null|| table.getSchemaName().equals("")){
            table.setSchemaName(schemaName);
        }
        insert.setTable(table);
        return insert.toString().
                replaceAll(", systime,",", `systime`,").
                replaceAll(", no,",", `no`,").
                replaceAll(", custom,",", `custom`,").
                replaceAll(", data,",", `data`,").
                replaceAll(", system_time,",", `system_time`,");
    }


    public static String doDelete(Delete delete, String schemaName) {
        //设置库名
        Table t = delete.getTable();
        if( t.getSchemaName()==null|| t.getSchemaName().equals("")){
            t.setSchemaName(schemaName);
        }
        delete.setTable(t);
        return delete.toString().
                replaceAll("\\(systime ","\\(`systime` ").
                replaceAll("\\(no ","\\(`no` ").
                replaceAll("\\(custom ","\\(`custom` ").
                replaceAll("\\(data ","\\(`data` ").
                replaceAll("\\(system_time ","\\(`system_time` ").
                replaceAll(" systime ="," `systime` =").
                replaceAll(" no ="," `no` =").
                replaceAll(" custom ="," `custom` =").
                replaceAll(" data ="," `data` =").
                replaceAll(" system_time ="," `system_time` =");
    }


    public static String doUpdate(Update update, String schemaName) throws JSQLParserException{
        //追加库名
        StringBuilder buffer = new StringBuilder();
        Table tb = update.getTable();
        if( tb.getSchemaName()==null|| tb.getSchemaName().equals("")){
            tb.setSchemaName(schemaName);
        }
        update.setTable(tb);
        // 处理from
        FromItem fromItem = update.getFromItem();
        if (fromItem != null) {
            Table tf = (Table) fromItem;
            if( tf.getSchemaName()==null|| tf.getSchemaName().equals("")){
                tf.setSchemaName(schemaName);
            }
        }
        // 处理join
        List<Join> joins = update.getJoins();
        if (joins != null && joins.size() > 0) {
            for (Object object : joins) {
                Join t = (Join) object;
                Table rightItem = (Table) t.getRightItem();
                if( rightItem.getSchemaName()==null|| rightItem.getSchemaName().equals("")){
                    rightItem.setSchemaName(schemaName);
                }
            }
        }
        ExpressionDeParser expressionDeParser = new ExpressionDeParser();
        UpdateDeParser p = new UpdateDeParser(expressionDeParser, null, buffer);
        expressionDeParser.setBuffer(buffer);
        p.deParse(update);

        return update.toString().
                replaceAll("\\(systime ","\\(`systime` ").
                replaceAll("\\(no ","\\(`no` ").
                replaceAll("\\(custom ","\\(`custom` ").
                replaceAll("\\(data ","\\(`data` ").
                replaceAll("\\(system_time ","\\(`system_time` ").
                replaceAll(" systime ="," `systime` =").
                replaceAll(" no ="," `no` =").
                replaceAll(" custom ="," `custom` =").
                replaceAll(" data ="," `data` =").
                replaceAll(" system_time ="," `system_time` =");

    }



    public static String doSelect(Select select, String schemaName){
        StringBuilder buffer = new StringBuilder();
        try {
            Select selectStatement = (Select) select;
            TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
            List tableList = tablesNamesFinder.getTableList(selectStatement);
            String tableName = (String) tableList.get(0);
            String[] split = tableName.split("\\.");
            if(split.length!=2){
                ExpressionDeParser expressionDeParser = new ExpressionDeParser();
                SQLParserSelect parser = new SQLParserSelect(expressionDeParser, buffer);
                parser.setSchemaName(schemaName);
                expressionDeParser.setSelectVisitor(parser);
                expressionDeParser.setBuffer(buffer);
                select.getSelectBody().accept(parser);
            }else{
              /*  ExpressionDeParser expressionDeParser = new ExpressionDeParser();
                SQLParserSelect parser = new SQLParserSelect(expressionDeParser, buffer);
                parser.setSchemaName(split[0]);
                expressionDeParser.setSelectVisitor(parser);
                expressionDeParser.setBuffer(buffer);
                select.getSelectBody().accept(parser);*/
                buffer = new StringBuilder();
                buffer.append(select.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString().
                replaceAll("\\(systime ","\\(`systime` ").
                replaceAll("\\(no ","\\(`no` ").
                replaceAll("\\(custom ","\\(`custom` ").
                replaceAll("\\(data ","\\(`data` ").
                replaceAll("\\(system_time ","\\(`system_time` ").
                replaceAll(" systime ="," `systime` =").
                replaceAll(" no ="," `no` =").
                replaceAll(" custom ="," `custom` =").
                replaceAll(" data ="," `data` =").
                replaceAll(" system_time ="," `system_time` =").
                replaceAll(", systime,",", `systime`,").
                replaceAll(", no,",", `no`,").
                replaceAll(", custom,",", `custom`,").
                replaceAll(", data,",", `data`,").
                replaceAll(", system_time,",", `system_time`,");

    }



}

package org.cmms.common.util.dynamic.db;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.lang.StringUtils;
import org.cmms.common.constant.DataBaseConstant;
import org.cmms.common.system.vo.DynamicDataSourceModel;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 根据不同的数据库，动态生成SQL，例如分页
 */
public class SqlUtils {

    public static final String DATABSE_TYPE_MYSQL = "mysql";
    public static final String DATABSE_TYPE_POSTGRE = "postgresql";
    public static final String DATABSE_TYPE_ORACLE = "oracle";
    public static final String DATABSE_TYPE_SQLSERVER = "sqlserver";


    /**
     * 分页SQL
     */
    public static final String MYSQL_SQL = "select * from ( {0}) sel_tab00 limit {1},{2}";
    public static final String POSTGRE_SQL = "select * from ( {0}) sel_tab00 limit {2} offset {1}";
    public static final String ORACLE_SQL = "select * from (select row_.*,rownum rownum_ from ({0}) row_ where rownum <= {1}) where rownum_>{2}";
    public static final String SQLSERVER_SQL = "select * from ( select row_number() over(order by tempColumn) tempRowNumber, * from (select top {1} tempColumn = 0, {0}) t ) tt where tempRowNumber > {2}";

    /**
     * 获取所有表的SQL
     */
    public static final String MYSQL_ALLTABLES_SQL = "select distinct table_name from information_schema.columns where table_schema = {0}";
    public static final String POSTGRE__ALLTABLES_SQL = "SELECT distinct c.relname AS  table_name FROM pg_class c";
    public static final String ORACLE__ALLTABLES_SQL = "select distinct colstable.table_name as  table_name from user_tab_cols colstable";
    public static final String SQLSERVER__ALLTABLES_SQL = "select distinct c.name as  table_name from sys.objects c";

    /**
     * 获取指定表的所有列名
     */
    public static final String MYSQL_ALLCOLUMNS_SQL = "select column_name from information_schema.columns where table_name = {0} and table_schema = {1}";
    public static final String POSTGRE_ALLCOLUMNS_SQL = "select table_name from information_schema.columns where table_name = {0}";
    public static final String ORACLE_ALLCOLUMNS_SQL = "select column_name from all_tab_columns where table_name ={0}";
    public static final String SQLSERVER_ALLCOLUMNS_SQL = "select name from syscolumns where id={0}";

    /*
     * 判断数据库类型
     */

    public static boolean dbTypeIsMySQL(String dbType) {
        return dbTypeIf(dbType, DATABSE_TYPE_MYSQL, DataBaseConstant.DB_TYPE_MYSQL_NUM);
    }

    public static boolean dbTypeIsOracle(String dbType) {
        return dbTypeIf(dbType, DATABSE_TYPE_ORACLE, DataBaseConstant.DB_TYPE_ORACLE_NUM);
    }

    public static boolean dbTypeIsSQLServer(String dbType) {
        return dbTypeIf(dbType, DATABSE_TYPE_SQLSERVER, DataBaseConstant.DB_TYPE_SQLSERVER_NUM);
    }

    public static boolean dbTypeIsPostgre(String dbType) {
        return dbTypeIf(dbType, DATABSE_TYPE_POSTGRE, DataBaseConstant.DB_TYPE_POSTGRESQL_NUM);
    }

    /**
     * 判断数据库类型
     */
    public static boolean dbTypeIf(String dbType, String... correctTypes) {
        for (String type : correctTypes) {
            if (type.equalsIgnoreCase(dbType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取全 SQL
     * 拼接 where 条件
     *
     * @param sql
     * @param params
     * @return
     */
    public static String getFullSql(String sql, Map params) {
        return getFullSql(sql, params, null, null);
    }

    /**
     * 获取全 SQL
     * 拼接 where 条件
     * 拼接 order 排序
     *
     * @param sql
     * @param params
     * @param orderColumn 排序字段
     * @param orderBy     排序方式，只能是 DESC 或 ASC
     * @return
     */
    public static String getFullSql(String sql, Map params, String orderColumn, String orderBy) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT t.* FROM ( ").append(sql).append(" ) t ");
        if (params != null && params.size() >= 1) {
            sqlBuilder.append("WHERE 1=1 ");
            for (Object key : params.keySet()) {
                String value = String.valueOf(params.get(key));
                if (StringUtils.isNotBlank(value)) {
                    sqlBuilder.append(" AND (").append(key).append(" = N'").append(value).append("')");
                }
            }
            if (StringUtils.isNotBlank(orderColumn) && StringUtils.isNotBlank(orderBy)) {
                sqlBuilder.append("ORDER BY ").append(orderColumn).append(" ").append("DESC".equalsIgnoreCase(orderBy) ? "DESC" : "ASC");
            }
        }
        return sqlBuilder.toString();
    }

    /**
     * 获取求数量 SQL
     *
     * @param sql
     * @return
     */
    public static String getCountSql(String sql) {
        return String.format("SELECT COUNT(1) \"total\" FROM ( %s ) temp_count", sql);
    }

    /**
     * 生成分页查询 SQL
     *
     * @param dbType 数据库类型
     * @param sql
     * @param page
     * @param rows
     * @return
     */
    public static String createPageSqlByDBType(String dbType, String sql, int page, int rows) {
        int beginNum = (page - 1) * rows;
        Object[] sqlParam = new Object[3];
        sqlParam[0] = sql;
        sqlParam[1] = String.valueOf(beginNum);
        sqlParam[2] = String.valueOf(rows);
        if (dbTypeIsMySQL(dbType)) {
            sql = MessageFormat.format(MYSQL_SQL, sqlParam);
        } else if (dbTypeIsPostgre(dbType)) {
            sql = MessageFormat.format(POSTGRE_SQL, sqlParam);
        } else {
            int beginIndex = (page - 1) * rows;
            int endIndex = beginIndex + rows;
            sqlParam[2] = Integer.toString(beginIndex);
            sqlParam[1] = Integer.toString(endIndex);
            if (dbTypeIsOracle(dbType)) {
                sql = MessageFormat.format(ORACLE_SQL, sqlParam);
            } else if (dbTypeIsSQLServer(dbType)) {
                sqlParam[0] = sql.substring(getAfterSelectInsertPoint(sql));
                sql = MessageFormat.format(SQLSERVER_SQL, sqlParam);
            }
        }
        return sql;
    }

    /**
     * 生成分页查询 SQL
     *
     * @param sql
     * @param page
     * @param rows
     * @return
     */
    public static String createPageSqlByDBKey(String dbKey, String sql, int page, int rows) {
        DynamicDataSourceModel dynamicSourceEntity = DataSourceCachePool.getCacheDynamicDataSourceModel(dbKey);
        String dbType = dynamicSourceEntity.getDbType();
        return createPageSqlByDBType(dbType, sql, page, rows);
    }

    private static int getAfterSelectInsertPoint(String sql) {
        int selectIndex = sql.toLowerCase().indexOf("select");
        int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
        return selectIndex + (selectDistinctIndex == selectIndex ? 15 : 6);
    }

    public static String getAllTableSql(String dbType, Object... params) {
        if (StringUtils.isNotEmpty(dbType)) {
            if (dbTypeIsMySQL(dbType)) {
                return MessageFormat.format(MYSQL_ALLTABLES_SQL, params);
            } else if (dbTypeIsOracle(dbType)) {
                return ORACLE__ALLTABLES_SQL;
            } else if (dbTypeIsPostgre(dbType)) {
                return POSTGRE__ALLTABLES_SQL;
            } else if (dbTypeIsSQLServer(dbType)) {
                return SQLSERVER__ALLTABLES_SQL;
            }
        }
        return null;
    }

    public static String getAllColumnSQL(String dbType, Object... params) {
        if (StringUtils.isNotEmpty(dbType)) {
            if (dbTypeIsMySQL(dbType)) {
                return MessageFormat.format(MYSQL_ALLCOLUMNS_SQL, params);
            } else if (dbTypeIsOracle(dbType)) {
                return MessageFormat.format(ORACLE_ALLCOLUMNS_SQL, params);
            } else if (dbTypeIsPostgre(dbType)) {
                return MessageFormat.format(POSTGRE_ALLCOLUMNS_SQL, params);
            } else if (dbTypeIsSQLServer(dbType)) {
                return MessageFormat.format(SQLSERVER_ALLCOLUMNS_SQL, params);
            }
        }
        return null;
    }
    /*StringBuffer sql = new StringBuffer();
        if ("ads".equals(ds)){
             sql = new StringBuffer("insert into "+tablename+" (");
        }else {
             sql = new StringBuffer("into  "+tablename+" (");
        }*/
    /**
     * 根据实体类动态生成insert批量插入语句
     */
    public static StringBuffer getInsertSql(Class entity,String sfdsjpt,String dsName) {
        //获取表名
        String  tablename = "";
        TableName tableNameAnnotation = (TableName)entity.getAnnotation(TableName.class);
        if (tableNameAnnotation == null) {
            return  null;
        }
        tablename = tableNameAnnotation.value();
        StringBuffer sql =new StringBuffer();
        if("true".equals(sfdsjpt)){
            sql.append("insert into "+dsName+"."+tablename+" (");
        }else{
            sql.append("into "+tablename+" (");
        }

        Field[] declaredFields = ReflectUtil.getFields(entity);
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            Excel excel = field.getAnnotation(Excel.class);
            TableId tableId = field.getAnnotation(TableId.class);
            if (excel != null || tableId != null) {

                sql.append(SqlUtils.camelToUnderline(field.getName()) + ",");
            }
        }
        sql = new StringBuffer(StrUtil.removeSuffix(sql.toString(), ","));
        sql.append(") values ");
        return sql;
    }





    /**
     * 根据实体类动态生成insert批量插入语句
     */
    public static StringBuffer getInsertValueSql(Object object, Class entity,String sfdsjpt) {
        StringBuffer sql = new StringBuffer(" (");
        try {
            Field[] declaredFields = ReflectUtil.getFields(entity);
            for (int i = 0; i < declaredFields.length; i++) {
                Field field = declaredFields[i];
                //设置是否允许访问，不是修改原来的访问权限修饰词。
                field.setAccessible(true);
                TableId tableId = field.getAnnotation(TableId.class);
                if(tableId != null && (field.get(object) == null || "".equals(field.get(object)))) {
                    //如果是id类型，并且对象中没有值
                    String id = IdUtil.fastSimpleUUID();
                    sql.append("'").append(id).append("',");
                }else if(field.get(object) != null && !"".equals(field.get(object))) {
                    String fieldType = field.getType().getName();

                    if("java.util.Date".equals(fieldType)) {
                        //TODO 日期字段处理
                        String result= null;
                        DateFormat cst = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        DateFormat gmt = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                        if (field.get(object).toString().contains("CST")){
                            Date dateTime = gmt.parse(field.get(object).toString());
                             result = cst.format(dateTime).substring(0, 10);
                        }else {
                            Date parse = DateUtil.parse(field.get(object).toString(), "yyyy-MM-dd");
                            result= parse.toString().substring(0, 10);
                        }
                        if("true".equals(sfdsjpt)){
                            sql.append(result).append(",");
                        }else{
                            String s = "to_date('"+result+"','yyyy-mm-dd')";
                            sql.append(s).append(",");
                        }

                    }else if("java.math.BigDecimal".equals(fieldType) || "java.lang.Double".equals(fieldType)) {
                        sql.append(field.get(object)).append(",");
                    } else {
                        sql.append("'").append(field.get(object).toString().replaceAll("'","''")).append("',");
                    }
                } else {
                    sql.append("null,");
                }

            }
            sql = new StringBuffer(StrUtil.removeSuffix(sql.toString(), ","));
            sql.append("),");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sql;
    }

    /**
     * 驼峰法转下划线
     * @param line 源字符串
     * @return 转换后的字符串
     */
    public static String camelToUnderline(String line){
        if(line==null||"".equals(line)){
            return "";
        }
        line=String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuffer sb=new StringBuffer();
        Pattern pattern=Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(word.toUpperCase());
            sb.append(matcher.end()==line.length()?"":"_");
        }
        return sb.toString();
    }

}

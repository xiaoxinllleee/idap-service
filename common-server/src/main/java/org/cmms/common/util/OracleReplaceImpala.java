package org.cmms.common.util;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;

public class OracleReplaceImpala {
    @Value(value = "${common.path.upload}")
    protected String uploadpath;

    public static void main(String[] args) throws Exception {
        String oracleUrl = "jdbc:oracle:thin:@192.168.0.245:1521:ods";
        String oracleUser = "imxs";
        String oraclePassword = "imdb";
        Connection oracleConn = DriverManager.getConnection(oracleUrl, oracleUser, oraclePassword);
        DatabaseMetaData meta = oracleConn.getMetaData();
        Statement stmt = oracleConn.createStatement();

        try {

            ResultSet rs = stmt.executeQuery("SELECT table_name FROM all_tables WHERE owner = 'IMXS'" );
            String schema= "IMXS";
            while (rs.next()) {
                String tableName = rs.getString("table_name");
                ResultSet oracleColumns = meta.getColumns(null, oracleUser.toUpperCase(), tableName, null);
                StringBuilder impalaSql = new StringBuilder("CREATE TABLE " + "das."+tableName + " (");
                while (oracleColumns.next()) {
                    String columnName = oracleColumns.getString("COLUMN_NAME");
                    String dataType = oracleColumns.getString("TYPE_NAME");
                    int columnSize = oracleColumns.getInt("COLUMN_SIZE");
                    int decimalDigits = oracleColumns.getInt("DECIMAL_DIGITS");
                    // 转换Oracle数据类型为Impala数据类型
                    String impalaType = convertDataType(dataType, columnSize, decimalDigits);
                    impalaSql.append(columnName).append(" ").append(impalaType).append(" ");

                }
                impalaSql.append(")");
                impalaSql.deleteCharAt(impalaSql.length() - 3);
                impalaSql.append(" ROW FORMAT DELIMITED FIELDS TERMINATED BY '\\u0001' LINES TERMINATED BY '\\n'");
                impalaSql.append(" WITH SERDEPROPERTIES ('field.delim'='\\u0001', 'line.delim'='\\n', 'serialization.format'='1', 'serialization.null.format'='')");
                impalaSql.append(" STORED AS TEXTFILE");
                impalaSql.append(" LOCATION 'hdfs://nameservice1/user/hive/warehouse/das.db/"+tableName+"';");
                System.out.println(impalaSql);
            }

        }catch (Exception e){
            e.getMessage();
        }finally {
            stmt.close();
            oracleConn.close();
        }



    }
    public static String convertDataType(String dataType, int columnSize, int decimalDigits){
        if(dataType.equals("VARCHAR2")){
            return "STRING,";
        }else if(dataType.equals("DATE")){
            return "TIMESTAMP,";
        }else if(dataType.equals("NUMBER")){
            return "DECIMAL("+columnSize+","+decimalDigits+"),";
        }else if(dataType.equals("TIMESTAMP(6)")){
            return "TIMESTAMP,";
        }else if(dataType.equals("NVARCHAR2")){
            return "STRING,";
        }else if(dataType.equals("INTEGER")){
            return "DECIMAL(22,0),";
        }else{
            System.out.println(dataType);
            return dataType;
        }
    }


}

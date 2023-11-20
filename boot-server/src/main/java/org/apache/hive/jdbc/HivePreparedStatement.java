
package org.apache.hive.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import org.apache.hive.jdbc.parse.EscapeParserDriver;
import org.apache.hive.jdbc.parse.EscapeSyntaxParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HivePreparedStatement extends HiveStatement implements PreparedStatement {
    public static final Logger logger = LoggerFactory.getLogger(HivePreparedStatement.class);
    private boolean enableBatch = true;
    private boolean isInsert = false;
    private boolean needBuildRowKey = true;
    private String rowKeyName;
    private String partition = "";
    private String pstatement;
    private Vector<HivePreparedStatement.BatchItem> batchItems = new Vector();
    private String[] columns;
    private String[] keyColumns;
    private int numColumn;
    private Vector<String> values = new Vector();
    private HashMap<String, String> oriColumnValueMap = new HashMap();
    private String fullTableName;
    private final HashMap<Integer, String> parameters = new HashMap();
    private boolean isFastInsert = false;
    private String hTableName;
    private String[] cfc;
    private Vector<String> vName = new Vector();
    private Vector<String> vTypes = new Vector();
    private HashMap<String, String> columnTypeMap = new HashMap();
    private HashMap<Integer, String> mapFastInsert = new HashMap();

    static boolean isWhite(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }

    private void sqlParser() throws SQLException {
        logger.trace("{}", this.traceInfo());
        String sql = this.pstatement.trim().toLowerCase();
        byte var2 = 0;

        try {
            if (sql.startsWith("insert")) {
                int index;
                for(index = var2 + 6; isWhite(sql.charAt(index)); ++index) {
                }

                if (sql.startsWith("into", index)) {
                    for(index += 4; isWhite(sql.charAt(index)); ++index) {
                    }

                    StringBuilder table = new StringBuilder();

                    char c;
                    for(c = sql.charAt(index); !isWhite(c) && c != '('; c = sql.charAt(index)) {
                        ++index;
                        table.append(c);
                    }

                    if ("table".equalsIgnoreCase(table.toString())) {
                        table.setLength(0);

                        while(isWhite(sql.charAt(index))) {
                            ++index;
                        }

                        for(c = sql.charAt(index); !isWhite(c) && c != '('; c = sql.charAt(index)) {
                            ++index;
                            table.append(c);
                        }
                    }

                    for(this.fullTableName = table.toString(); isWhite(sql.charAt(index)); ++index) {
                    }

                    int begin;
                    if ("partition".equalsIgnoreCase(sql.substring(index, index + 9))) {
                        begin = index;

                        for(index += "partition".length(); isWhite(sql.charAt(index)); ++index) {
                        }

                        if (sql.charAt(index) == '(') {
                            index = sql.indexOf(41, index) + 1;
                        } else {
                            while(!isWhite(sql.charAt(index)) && sql.charAt(index) != '(') {
                                ++index;
                            }
                        }

                        this.partition = sql.substring(begin, index);
                    }

                    while(isWhite(sql.charAt(index))) {
                        ++index;
                    }

                    if (sql.charAt(index) == '(') {
                        ++index;
                        begin = index;
                        int end = sql.indexOf(41, index);
                        if (end == -1) {
                            throw new SQLException("Incorrect SQL: " + sql);
                        }

                        index = end + 1;
                        this.columns = sql.substring(begin, end).split(",");
                        this.numColumn = this.columns.length;

                        for(int i = 0; i < this.columns.length; ++i) {
                            this.columns[i] = this.columns[i].trim();
                        }
                    }

                    while(isWhite(sql.charAt(index))) {
                        ++index;
                    }

                    if (sql.startsWith("values", index)) {
                        for(index += 6; isWhite(sql.charAt(index)); ++index) {
                        }

                        if (sql.charAt(index) == '(') {
                            ++index;

                            while(isWhite(sql.charAt(index))) {
                                ++index;
                            }

                            while(index < sql.length()) {
                                while(isWhite(sql.charAt(index))) {
                                    ++index;
                                }

                                char prefix = sql.charAt(index);
                                if (prefix != '\'' && prefix != '"') {
                                    for(begin = index; sql.charAt(index) != ')' && sql.charAt(index) != ','; ++index) {
                                    }

                                    this.values.add(sql.substring(begin, index).trim());
                                    if (sql.charAt(index) == ',') {
                                        ++index;
                                    }
                                } else {
                                    begin = index++;

                                    while(index < sql.length()) {
                                        if ((sql.charAt(index) != '\\' || sql.charAt(index + 1) != '\\') && (sql.charAt(index) != '\\' || sql.charAt(index + 1) != prefix)) {
                                            if (sql.charAt(index) == prefix) {
                                                ++index;
                                                break;
                                            }

                                            ++index;
                                        } else {
                                            index += 2;
                                        }
                                    }

                                    this.values.add(sql.substring(begin, index).trim());

                                    while(isWhite(sql.charAt(index))) {
                                        ++index;
                                    }

                                    if (sql.charAt(index) == ',') {
                                        ++index;
                                    }
                                }

                                if (sql.charAt(index) == ')') {
                                    ++index;

                                    while(index < sql.length() && isWhite(sql.charAt(index))) {
                                        ++index;
                                    }

                                    if (index == sql.length()) {
                                        this.isInsert = true;
                                    }

                                    return;
                                }
                            }
                        }
                    }
                }
            }
        } catch (StringIndexOutOfBoundsException var8) {
            throw new SQLException("Can't parse SQL clause: " + sql);
        }

        this.isInsert = false;
    }

    private void getRowKey() throws SQLException {
        logger.trace("{}", this.traceInfo());
        Connection conn = this.getConnection();
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        String[] values = this.fullTableName.split("\\.");
        String database = null;
        String tablename = null;
        if (values.length > 2) {
            throw new SQLException("Illegal database expression: " + this.fullTableName);
        } else {
            for(int i = 0; i < values.length; ++i) {
                if (values[i].startsWith("`")) {
                    values[i] = values[i].substring(1, values[0].length());
                }

                if (values[i].endsWith("`")) {
                    values[i] = values[i].substring(0, values[0].length() - 1);
                }
            }

            if (values.length == 1) {
                database = conn.getCatalog();
                tablename = values[0].trim();
            }

            if (values.length == 2) {
                database = values[0].trim();
                tablename = values[1].trim();
            }

            String rowKey;
            ResultSet rs;
            if (this.isFastInsert) {
                rs = databaseMetaData.getColumns(database, database, tablename, "%");

                String line;
                while(rs.next()) {
                    rowKey = rs.getString(4).trim().toLowerCase();
                    line = rs.getString(6).trim().toLowerCase();
                    this.columnTypeMap.put(rowKey, line);
                    this.vName.add(rowKey);
                    this.vTypes.add(line);
                }

                rs.close();
                rs = this.executeQuery("desc formatted " + tablename);

                while(rs.next()) {
                    for(int i = 1; i <= 2; ++i) {
                        line = rs.getString(i);
                        if (line != null && line.trim().equalsIgnoreCase("hbase.table.name")) {
                            this.hTableName = rs.getString(i + 1).trim();
                        } else if (line != null && line.trim().equalsIgnoreCase("hbase.columns.mapping")) {
                            this.cfc = rs.getString(i + 1).trim().split(",");
                        }
                    }
                }

                rs.close();
            }

            rs = databaseMetaData.getColumns(database, database, tablename, "%");
            if (rs.next()) {
                this.rowKeyName = rs.getString(4).trim().toLowerCase();
                rowKey = rs.getString(6).trim().toLowerCase();
                rs.close();
                this.needBuildRowKey = false;
                if (rowKey.startsWith("struct")) {
                    int begin = rowKey.indexOf("<");
                    int end = rowKey.indexOf(">", begin);
                    if (begin != -1 && end != -1) {
                        String[] KeyAndType = rowKey.substring(begin + 1, end).split(",");
                        this.keyColumns = new String[KeyAndType.length];

                        for(int i = 0; i < KeyAndType.length; ++i) {
                            this.keyColumns[i] = KeyAndType[i].split(":")[0].trim().toLowerCase();
                        }

                        this.needBuildRowKey = true;
                        String[] arr$ = this.columns;
                        int len$ = arr$.length;

                        for(int i$ = 0; i$ < len$; ++i$) {
                            String col = arr$[i$];
                            if (this.rowKeyName.equalsIgnoreCase(col)) {
                                this.needBuildRowKey = false;
                                break;
                            }
                        }

                        return;
                    }

                    throw new SQLException("Rowkey structure is not correct!");
                }
            }

            rs.close();
            this.needBuildRowKey = false;
        }
    }

    public HivePreparedStatement(HiveConnection connection, String sql) throws SQLException {
        super(connection);
        logger.trace("{}, {}", this.traceInfo(), sql);
        this.checkClosed();
        this.originalStatement = sql;

        try {
            if (connection.getSessionVars().containsKey("escape") && ((String)connection.getSessionVars().get("escape")).equalsIgnoreCase("false")) {
                this.statement = sql;
            } else {
                this.statement = EscapeParserDriver.parse(sql);
            }
        } catch (EscapeSyntaxParseException var9) {
            throw new SQLException(var9);
        }

        this.pstatement = sql;
        this.enableBatch = false;
        this.isInsert = false;
        this.needBuildRowKey = true;
        this.batchItems.clear();
        this.parameters.clear();
        Map<String, String> sessVars = ((HiveConnection)this.getConnection()).getSessionVars();
        if (sessVars.containsKey("fastinsert") && (((String)sessVars.get("fastinsert")).equalsIgnoreCase("1") || ((String)sessVars.get("fastinsert")).equalsIgnoreCase("true"))) {
            this.isFastInsert = true;
        }

        this.sqlParser();
        if (this.isInsert) {
            this.getRowKey();
            this.enableBatch = false;
            Object[] arr$ = this.values.toArray();
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Object c = arr$[i$];
                if (((String)c).trim().equalsIgnoreCase("?")) {
                    this.enableBatch = true;
                    break;
                }
            }
        }

        try {
            if (this.enableBatch && this.needBuildRowKey) {
                for(int index = 0; index < this.numColumn; ++index) {
                    this.oriColumnValueMap.put(this.columns[index], this.values.get(index));
                }
            }

        } catch (Exception var8) {
            throw new SQLException(getStackTraceString(var8));
        }
    }

    private boolean isPreCompilingMode(String sql) {
        return sql.contains("?") && !sql.contains("'?'");
    }

    private static String getStackTraceString(Exception e) {
        StringBuffer sb = (new StringBuffer(e.toString())).append('\n');
        StackTraceElement[] arr$ = e.getStackTrace();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            StackTraceElement s = arr$[i$];
            sb.append(s.toString()).append('\n');
        }

        return sb.toString();
    }

    public void addBatch() throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        HashMap newParameters;
        int index;
        int i;
        String value;
        String column;
        if (this.isFastInsert) {
            newParameters = new HashMap();
            index = 1;

            for(i = 0; i < this.numColumn; ++i) {
                value = null;
                column = this.columns[i];
                if (((String)this.values.get(i)).trim().equalsIgnoreCase("?")) {
                    value = (String)this.mapFastInsert.get(index++);
                } else {
                    value = (String)this.values.get(i);
                }

                newParameters.put(column, value);
            }

            this.batchItems.add(new HivePreparedStatement.BatchItem(newParameters, (String)null, true));
        } else {
            if (this.enableBatch && this.needBuildRowKey) {
                newParameters = new HashMap();
                index = 1;

                for(i = 0; i < this.numColumn; ++i) {
                    value = null;
                    column = this.columns[i];
                    if (((String)this.oriColumnValueMap.get(column)).trim().equalsIgnoreCase("?")) {
                        value = (String)this.parameters.get(index++);
                    } else {
                        value = (String)this.oriColumnValueMap.get(column);
                    }

                    newParameters.put(column, value);
                }

                this.batchItems.add(new HivePreparedStatement.BatchItem(newParameters, (String)null, true));
            } else if (this.enableBatch && !this.needBuildRowKey) {
                newParameters = new HashMap();
                index = 1;

                for(i = 0; i < this.numColumn; ++i) {
                    value = null;
                    column = this.columns[i];
                    if (((String)this.values.get(i)).trim().equalsIgnoreCase("?")) {
                        value = (String)this.parameters.get(index++);
                    } else {
                        value = (String)this.values.get(i);
                    }

                    newParameters.put(column, value);
                }

                this.batchItems.add(new HivePreparedStatement.BatchItem(newParameters, (String)null, true));
            } else {
                this.addBatch(this.updateSql(this.pstatement, this.parameters));
            }

        }
    }

    public void clearParameters() throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        this.parameters.clear();
    }

    public boolean execute() throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        return super.execute(this.updateSql(this.pstatement, this.parameters));
    }

    public ResultSet executeQuery() throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        return super.executeQuery(this.updateSql(this.pstatement, this.parameters));
    }

    public int executeUpdate() throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        return super.executeUpdate(this.updateSql(this.pstatement, this.parameters));
    }

    private String updateSql(String sql, HashMap<Integer, String> parameters) throws SQLException {
        logger.trace("{}, {}", sql, parameters);
        this.checkClosed();
        StringBuilder newSql = new StringBuilder(sql.length() * 2);
        Vector<Integer> qMarkPos = new Vector();
        int signalCount = 0;
        int i = 0;

        while(true) {
            while(i < sql.length()) {
                char c = sql.charAt(i);
                if (c == '\\') {
                    i += 2;
                } else if (c == '\'') {
                    ++signalCount;
                    ++i;
                } else if (c == '?' && signalCount % 2 == 0) {
                    qMarkPos.add(i);
                    ++i;
                } else {
                    ++i;
                }
            }

            int begin = 0;
            int end = 0;
            int wild = 0;

            for(i = 0; i < qMarkPos.size(); ++i) {
                end = (Integer)qMarkPos.get(i);
                newSql.append(sql.substring(begin, end));
                if (parameters.containsKey(i + 1)) {
                    newSql.append((String)parameters.get(i + 1));
                } else {
                    newSql.append("widcard(" + wild++ + ")");
                }

                begin = end + 1;
            }

            newSql.append(sql.substring(begin, sql.length()));
            return newSql.toString();
        }
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        return this.resultSet != null ? this.resultSet.getMetaData() : null;
    }

    public ParameterMetaData getParameterMetaData() throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        throw new SQLException("Method getParameterMetaData not supported");
    }

    public void setArray(int i, Array x) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        throw new SQLException("Method setArray not supported");
    }

    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        this.setAsciiStream(parameterIndex, x, 2147483647);
    }

    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        if (x == null) {
            this.setNull(parameterIndex, 12);
        } else {
            StringBuilder sb = new StringBuilder();
            int ret = 0;
            int count = 0;

            //int ret;
            try {
                while((ret = x.read()) != -1 && count < length) {
                    int b0 = (ret & 240) >> 4;
                    int b1 = ret & 15;
                    sb.append(Integer.toString(b0));
                    sb.append(Integer.toString(b1));
                    ++count;
                }
            } catch (IOException var9) {
                var9.printStackTrace();
                throw new SQLException("Failed to set AsciiStream.", var9);
            }

            this.parameters.put(parameterIndex, "0x" + sb.toString());
            if (this.isFastInsert) {
                this.mapFastInsert.put(parameterIndex, sb.toString());
            }

        }
    }

    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        this.setAsciiStream(parameterIndex, x, (int)length);
    }

    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        logger.trace("{}, {}", this.traceInfo(), parameterIndex + ", " + x);
        this.checkClosed();
        this.parameters.put(parameterIndex, x.toString() + "BD");
        if (this.isFastInsert) {
            this.mapFastInsert.put(parameterIndex, x.toString());
        }

    }

    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        logger.trace("{}, {}", this.traceInfo(), parameterIndex);
        this.checkClosed();
        this.setBinaryStream(parameterIndex, x, 2147483647);
    }

    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        logger.trace("{}, {}", this.traceInfo(), parameterIndex);
        this.checkClosed();
        this.setBlob(parameterIndex, x, (long)length);
    }

    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        logger.trace("{}, {}", this.traceInfo(), parameterIndex);
        this.checkClosed();
        this.setBinaryStream(parameterIndex, x, (int)length);
    }

    public void setBlob(int i, Blob x) throws SQLException {
        logger.trace("{}, {}", this.traceInfo(), x);
        this.checkClosed();
        HiveBlob blob = (HiveBlob)x;
        byte[] bytes = blob.getBytes(1L, (int)blob.length());
        StringBuffer sb = new StringBuffer(bytes.length * 2 + 2);
        sb.append("0x");
        byte[] arr$ = bytes;
        int len$ = bytes.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            byte b = arr$[i$];
            byte h0 = (byte)((b & 240) >> 4);
            byte h1 = (byte)(b & 15);
            if (h0 < 10) {
                h0 = (byte)(h0 + 48);
            } else {
                h0 = (byte)(h0 + 87);
            }

            if (h1 < 10) {
                h1 = (byte)(h1 + 48);
            } else {
                h1 = (byte)(h1 + 87);
            }

            sb.append((char)h0);
            sb.append((char)h1);
        }

        this.parameters.put(i, sb.toString());
        if (this.isFastInsert) {
            this.mapFastInsert.put(i, sb.toString());
        }

    }

    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        this.setBlob(parameterIndex, inputStream, 2147483647L);
    }

    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        byte[] data = new byte[64];
        int ret = 0;
        int count = 0;

       // int ret;
        try {
            while((ret = inputStream.read()) != -1 && (long)count < length) {
                if (count >= data.length) {
                    data = Arrays.copyOf(data, data.length << 1);
                }

                data[count] = (byte)ret;
                ++count;
            }
        } catch (IOException var9) {
            var9.printStackTrace();
            throw new SQLException("Failed to setBlob.", var9);
        }

        HiveBlob blob = new HiveBlob(data, count);
        this.setBlob(parameterIndex, (Blob)blob);
    }

    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        this.parameters.put(parameterIndex, "" + x);
        if (this.isFastInsert) {
            this.mapFastInsert.put(parameterIndex, "" + x);
        }

    }

    public void setByte(int parameterIndex, byte x) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        this.parameters.put(parameterIndex, "" + x + "Y");
        if (this.isFastInsert) {
            this.mapFastInsert.put(parameterIndex, "" + x);
        }

    }

    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        StringBuilder sb = new StringBuilder();
        byte[] arr$ = x;
        int len$ = x.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            byte b = arr$[i$];
            int t0 = (b & 240) >> 4;
            int t1 = b & 15;
            sb.append(Integer.toHexString(t0));
            sb.append(Integer.toHexString(t1));
        }

        this.parameters.put(parameterIndex, "0x" + sb.toString());
        if (this.isFastInsert) {
            this.mapFastInsert.put(parameterIndex, sb.toString());
        }

    }

    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        this.setCharacterStream(parameterIndex, reader, 2147483647);
    }

    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        if (reader == null) {
            this.setNull(parameterIndex, 12);
        } else {
            StringBuilder sb = new StringBuilder();
            char[] cs = new char[128];
            boolean var6 = true;

            int r;
            try {
                do {
                    r = reader.read(cs);
                    length -= r;
                    char[] arr$ = cs;
                    int len$ = cs.length;

                    for(int i$ = 0; i$ < len$; ++i$) {
                        char c = arr$[i$];
                        sb.append(Integer.toHexString(c));
                    }
                } while(r != -1 && length > 0);
            } catch (IOException var11) {
                var11.printStackTrace();
                throw new SQLException("Failed to setCharacterStream", var11);
            }

            this.setString(parameterIndex, sb.toString());
        }

    }

    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        this.setCharacterStream(parameterIndex, reader, (int)length);
    }

    public void setClob(int i, Clob x) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        this.setCharacterStream(i, x.getCharacterStream());
    }

    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        this.setCharacterStream(parameterIndex, reader);
    }

    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        this.setCharacterStream(parameterIndex, reader, length);
    }

    public void setDate(int parameterIndex, Date x) throws SQLException {
        logger.trace("{}, {}", this.traceInfo(), parameterIndex + ", " + x);
        this.checkClosed();
        this.setString(parameterIndex, x.toString());
    }

    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        logger.trace("{}, {}", this.traceInfo(), parameterIndex + ", " + x);
        this.checkClosed();
        throw new SQLException("Method not supported");
    }

    public void setDouble(int parameterIndex, double x) throws SQLException {
        logger.trace("{}, {}", this.traceInfo(), parameterIndex + ", " + x);
        this.checkClosed();
        this.parameters.put(parameterIndex, "" + x);
        if (this.isFastInsert) {
            this.mapFastInsert.put(parameterIndex, "" + x);
        }

    }

    public void setFloat(int parameterIndex, float x) throws SQLException {
        logger.trace("{}, {}", this.traceInfo(), parameterIndex + ", " + x);
        this.checkClosed();
        this.parameters.put(parameterIndex, "" + x);
        if (this.isFastInsert) {
            this.mapFastInsert.put(parameterIndex, "" + x);
        }

    }

    public void setInt(int parameterIndex, int x) throws SQLException {
        logger.trace("{}, {}", this.traceInfo(), parameterIndex + ", " + x);
        this.checkClosed();
        this.parameters.put(parameterIndex, "" + x);
        if (this.isFastInsert) {
            this.mapFastInsert.put(parameterIndex, "" + x);
        }

    }

    public void setLong(int parameterIndex, long x) throws SQLException {
        logger.trace("{}, {}", this.traceInfo(), parameterIndex + ", " + x);
        this.checkClosed();
        this.parameters.put(parameterIndex, "" + x + "L");
        if (this.isFastInsert) {
            this.mapFastInsert.put(parameterIndex, "" + x);
        }

    }

    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        this.setNCharacterStream(parameterIndex, value, 2147483647L);
    }

    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        if (value == null) {
            this.setNull(parameterIndex, 12);
        } else {
            StringBuilder sb = new StringBuilder();
            char[] cs = new char[128];
            boolean var7 = true;

            int r;
            try {
                do {
                    r = value.read(cs);
                    length -= (long)r;
                    char[] arr$ = cs;
                    int len$ = cs.length;

                    for(int i$ = 0; i$ < len$; ++i$) {
                        char c = arr$[i$];
                        sb.append(Integer.toHexString(c));
                        sb.append(Integer.toHexString(c));
                    }
                } while(r != -1 && length > 0L);
            } catch (IOException var12) {
                var12.printStackTrace();
                throw new SQLException("Failed to setNCharacterStream.", var12);
            }

            this.setString(parameterIndex, sb.toString());
        }

    }

    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        throw new SQLException("Method setNClob not supported");
    }

    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        this.setNClob(parameterIndex, reader, 9223372036854775807L);
    }

    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        throw new SQLException("Method setNClob not supported");
    }

    public void setNString(int parameterIndex, String value) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        throw new SQLException("Method setNString not supported");
    }

    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        logger.trace("{}, {}", this.traceInfo(), parameterIndex);
        this.checkClosed();
        this.parameters.put(parameterIndex, "null");
        if (this.isFastInsert) {
            this.mapFastInsert.put(parameterIndex, "null");
        }

    }

    public void setNull(int paramIndex, int sqlType, String typeName) throws SQLException {
        logger.trace("{}, {}", this.traceInfo(), paramIndex);
        this.checkClosed();
        this.parameters.put(paramIndex, "null");
        if (this.isFastInsert) {
            this.mapFastInsert.put(paramIndex, "null");
        }

    }

    public void setObject(int parameterIndex, Object x) throws SQLException {
        logger.trace("{}, {}", this.traceInfo(), parameterIndex + ", " + x);
        this.checkClosed();
        if (x instanceof String) {
            this.setString(parameterIndex, (String)x);
        } else if (x instanceof Short) {
            this.setShort(parameterIndex, (Short)x);
        } else if (x instanceof Integer) {
            this.setInt(parameterIndex, (Integer)x);
        } else if (x instanceof Long) {
            this.setLong(parameterIndex, (Long)x);
        } else if (x instanceof Float) {
            this.setFloat(parameterIndex, (Float)x);
        } else if (x instanceof Double) {
            this.setDouble(parameterIndex, (Double)x);
        } else if (x instanceof BigDecimal) {
            this.setBigDecimal(parameterIndex, (BigDecimal)x);
        } else if (x instanceof Boolean) {
            this.setBoolean(parameterIndex, (Boolean)x);
        } else if (x instanceof Byte) {
            this.setByte(parameterIndex, (Byte)x);
        } else if (x instanceof Character) {
            this.setString(parameterIndex, ((Character)x).toString());
        } else {
            if (!(x instanceof HiveStruct)) {
                throw new SQLException(MessageFormat.format("Can''t infer the SQL type to use for an instance of {0}. Use setObject() with an explicit Types value to specify the type to use.", x.getClass().getName()));
            }

            this.setStruct(parameterIndex, (HiveStruct)x);
        }

    }

    public void setStruct(int parameterIndex, HiveStruct hiveStruct) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        this.parameters.put(parameterIndex, hiveStruct.toString());
    }

    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        logger.trace("{}, {}", this.traceInfo(), parameterIndex + ", " + x + ", " + targetSqlType);
        this.checkClosed();
        this.setObject(parameterIndex, x);
    }

    public void setObject(int parameterIndex, Object x, int targetSqlType, int scale) throws SQLException {
        logger.trace("{}, {}", this.traceInfo(), parameterIndex + ", " + x + ", " + scale);
        this.checkClosed();
        this.setObject(parameterIndex, x);
    }

    public void setRef(int i, Ref x) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        throw new SQLException("Method not supported");
    }

    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        throw new SQLException("Method not supported");
    }

    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        throw new SQLException("Method not supported");
    }

    public void setShort(int parameterIndex, short x) throws SQLException {
        logger.trace("{}, {}", this.traceInfo(), parameterIndex + ", " + x);
        this.checkClosed();
        this.parameters.put(parameterIndex, "" + x + "S");
        if (this.isFastInsert) {
            this.mapFastInsert.put(parameterIndex, "" + x);
        }

    }

    public void setString(int parameterIndex, String x) throws SQLException {
        logger.trace("{}, {}", this.traceInfo(), parameterIndex + ", " + x);
        this.checkClosed();
        if (this.isFastInsert) {
            this.mapFastInsert.put(parameterIndex, x);
        }

        StringBuffer escaped = new StringBuffer(x.length() + 10);

        for(int i = 0; i < x.length(); ++i) {
            switch(x.charAt(i)) {
                case '\'':
                    escaped.append("\\'");
                    break;
                case ';':
                    escaped.append("\\;");
                    break;
                case '\\':
                    escaped.append("\\\\");
                    break;
                default:
                    escaped.append(x.charAt(i));
            }
        }

        this.parameters.put(parameterIndex, "'" + escaped + "'");
    }

    public void setTime(int parameterIndex, Time x) throws SQLException {
        logger.trace("{}, {}", this.traceInfo(), parameterIndex + ", " + x);
        this.checkClosed();
        this.setString(parameterIndex, x.toString());
    }

    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        throw new SQLException("Method not supported");
    }

    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        logger.trace("{}, {}", this.traceInfo(), parameterIndex + ", " + x);
        this.checkClosed();
        this.setString(parameterIndex, x.toString());
    }

    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        throw new SQLException("Method not supported");
    }

    public void setURL(int parameterIndex, URL x) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        this.setString(parameterIndex, x.toString());
    }

    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        throw new SQLException("Method not supported");
    }

    public void addBatch(String sql) throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        this.batchItems.add(new HivePreparedStatement.BatchItem((HashMap)null, sql, false));
    }

    public void clearBatch() throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        this.batchItems.clear();
    }

    public void close() throws SQLException {
        logger.trace("{}", this.traceInfo());
        if (this.batchItems.size() != 0) {
            this.executeBatch();
        }

        super.close();
    }

    public int[] executeBatch() throws SQLException {
        logger.trace("{}", this.traceInfo());
        this.checkClosed();
        int[] ret = new int[this.batchItems.size()];
        StringBuilder sqlHeader;
        StringBuilder finalSQL;
        int icolumn;
        if (this.isFastInsert) {
            sqlHeader = new StringBuilder("fastinsert " + this.hTableName);
            StringBuilder cols = new StringBuilder();
            finalSQL = new StringBuilder();
            StringBuilder tlen = new StringBuilder();

            for(icolumn = 0; icolumn < this.columns.length; ++icolumn) {
                for(int j = 0; j < this.vName.size(); ++j) {
                    if (this.columns[icolumn].equalsIgnoreCase((String)this.vName.get(j))) {
                        if (icolumn != 0) {
                            cols.append(',');
                            tlen.append(',');
                        }

                        cols.append(this.cfc[j]);
                        finalSQL.append((String)this.vTypes.get(j));
                        tlen.append(((String)this.vTypes.get(j)).length());
                    }
                }
            }

            sqlHeader.append(" (" + cols + ")");
            sqlHeader.append(" (" + tlen + ")");
            sqlHeader.append(" (" + finalSQL + ")");

            for(icolumn = 0; icolumn < this.batchItems.size(); ++icolumn) {
                StringBuilder lengths = new StringBuilder();
                StringBuilder values = new StringBuilder();

                for(icolumn = 0; icolumn < this.numColumn; ++icolumn) {
                    String value = (String)((HivePreparedStatement.BatchItem)this.batchItems.get(icolumn)).getMap().get(this.columns[icolumn]);
                    if (icolumn > 0) {
                        lengths.append(',');
                    }

                    lengths.append(value.length());
                    values.append(value);
                }

                sqlHeader.append(" (" + lengths + ")");
                sqlHeader.append(" (" + values + ")");
            }

            this.executeUpdate(sqlHeader.toString());
        } else {
            int i;
            //int icolumn;
            if (this.enableBatch && this.needBuildRowKey) {
                sqlHeader = new StringBuilder("batchinsert into " + this.fullTableName + " " + this.partition + "(" + this.rowKeyName);

                for(i = 0; i < this.numColumn; ++i) {
                    sqlHeader.append("," + this.columns[i]);
                }

                sqlHeader.append(") batchvalues(");
                finalSQL = new StringBuilder(1048576);
                finalSQL.append(sqlHeader);

                for(i = 0; i < this.batchItems.size(); ++i) {
                    if (!((HivePreparedStatement.BatchItem)this.batchItems.get(i)).getMode()) {
                        this.executeUpdate(((HivePreparedStatement.BatchItem)this.batchItems.get(i)).getSQL());
                    } else {
                        finalSQL.append("values(named_struct(");

                        for(icolumn = 0; icolumn < this.keyColumns.length - 1; ++icolumn) {
                            finalSQL.append("'" + this.keyColumns[icolumn] + "'," + (String)((HivePreparedStatement.BatchItem)this.batchItems.get(i)).getMap().get(this.keyColumns[icolumn]) + ",");
                        }

                        finalSQL.append("'" + this.keyColumns[icolumn] + "'," + (String)((HivePreparedStatement.BatchItem)this.batchItems.get(i)).getMap().get(this.keyColumns[icolumn]) + ")");

                        for(icolumn = 0; icolumn < this.numColumn; ++icolumn) {
                            finalSQL.append("," + (String)((HivePreparedStatement.BatchItem)this.batchItems.get(i)).getMap().get(this.columns[icolumn]));
                        }

                        finalSQL.append("),");
                        if (finalSQL.length() > 1047552 || i + 1 == this.batchItems.size() || !((HivePreparedStatement.BatchItem)this.batchItems.get(i + 1)).getMode()) {
                            finalSQL.replace(finalSQL.length() - 1, finalSQL.length(), ")");
                            this.executeUpdate(finalSQL.toString());
                            finalSQL.setLength(0);
                            finalSQL.append(sqlHeader);
                        }
                    }
                }
            } else if (this.enableBatch && !this.needBuildRowKey) {
                sqlHeader = new StringBuilder("batchinsert into " + this.fullTableName + " " + this.partition + "(");

                for(i = 0; i < this.numColumn - 1; ++i) {
                    sqlHeader.append(this.columns[i] + ",");
                }

                sqlHeader.append(this.columns[this.numColumn - 1]);
                sqlHeader.append(") batchvalues(");
                finalSQL = new StringBuilder(1048576);
                finalSQL.append(sqlHeader);

                for(i = 0; i < this.batchItems.size(); ++i) {
                    if (!((HivePreparedStatement.BatchItem)this.batchItems.get(i)).getMode()) {
                        this.executeUpdate(((HivePreparedStatement.BatchItem)this.batchItems.get(i)).getSQL());
                    } else {
                        finalSQL.append("values(");

                        for(icolumn = 0; icolumn < this.numColumn; ++icolumn) {
                            finalSQL.append((String)((HivePreparedStatement.BatchItem)this.batchItems.get(i)).getMap().get(this.columns[icolumn]) + ",");
                        }

                        finalSQL.deleteCharAt(finalSQL.length() - 1);
                        finalSQL.append("),");
                        if (i + 1 == this.batchItems.size() || !((HivePreparedStatement.BatchItem)this.batchItems.get(i + 1)).getMode()) {
                            finalSQL.replace(finalSQL.length() - 1, finalSQL.length(), ")");
                            this.executeUpdate(finalSQL.toString());
                            finalSQL.setLength(0);
                            finalSQL.append(sqlHeader);
                        }
                    }
                }
            }
        }

        int i;
        for(i = 0; i < this.batchItems.size(); ++i) {
            if (!((HivePreparedStatement.BatchItem)this.batchItems.get(i)).getMode()) {
                this.executeUpdate(((HivePreparedStatement.BatchItem)this.batchItems.get(i)).getSQL());
            }
        }

        this.batchItems.clear();

        for(i = 0; i < ret.length; ++i) {
            ret[i] = -2;
        }

        return ret;
    }

    private String traceInfo() {
        return "@" + Integer.toHexString(this.hashCode());
    }

    static class BatchItem {
        private HashMap<String, String> columnValueMap;
        private String sql;
        private boolean mode;

        BatchItem(HashMap<String, String> map, String strSQL, boolean b) {
            this.columnValueMap = map;
            this.mode = b;
            this.sql = strSQL;
        }

        HashMap<String, String> getMap() {
            return this.columnValueMap;
        }

        String getSQL() {
            return this.sql;
        }

        Boolean getMode() {
            return this.mode;
        }
    }
}

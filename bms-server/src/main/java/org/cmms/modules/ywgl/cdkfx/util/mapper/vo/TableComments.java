package org.cmms.modules.ywgl.cdkfx.util.mapper.vo;

import lombok.Data;

@Data
public class TableComments {
    private  String columnName;

    private  String dataType;

    private  String dataLength;

    private  String dataPrecision;

    private  String dataScale;

    private  String comments;

    private String   tableName;
}

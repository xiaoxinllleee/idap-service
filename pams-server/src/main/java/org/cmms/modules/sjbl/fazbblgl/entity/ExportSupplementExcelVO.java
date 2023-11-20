package org.cmms.modules.sjbl.fazbblgl.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ExportSupplementExcelVO {
    private String date;
    private String schemeName;
    private String schemeId;
    private String evlObjType;
    private String evlObjId;
    private String indexName;
    private String indexId;
    private String applyTypeId;
}

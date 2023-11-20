package org.cmms.modules.qxfk.qx.dto;

import lombok.Data;

@Data
public class QxPdf {
    private String success;
    private String code;
    private String message;
    private PdfInfo info;

}

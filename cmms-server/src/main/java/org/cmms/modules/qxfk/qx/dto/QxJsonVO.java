package org.cmms.modules.qxfk.qx.dto;

import lombok.Data;

/**
 * @Date 2022/8/18
 * @Created by eran
 */
@Data
public class QxJsonVO {
    private String success;
    private String code;
    private String message;
    private QxJsonInfo info;
}

package org.cmms.modules.activiti.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BusinessInfoVo {
    private String spId;
    private String procInstId;
    private String taskId;
    private String hhbm;
    private String zjhm;
    private BigDecimal jysxed;
    private String pddj;
    private String spyj;
    private String userId;
    private String userName;
    private String yggh;
    private String tableId;
}

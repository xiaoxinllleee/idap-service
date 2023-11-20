package org.cmms.modules.xyjlcx.xybg.xybgcx.vo;

import lombok.Data;
import lombok.ToString;

/**
 * 客户信息表
 */

@Data
@ToString
public class EcifCustomerVO {
    /**机构代码*/
    private String branchNo;
    /**客户编号*/
    private String custId;
    /**客户名称*/
    private String khmc;
    /**证件号码*/
    private String zjhm;
    /**最新更新时间*/
    private String LUpdateTime;
}

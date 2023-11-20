package org.cmms.modules.xyjlcx.xybg.xybgcx.vo;

import lombok.Data;
import lombok.ToString;

/**
 * 卡基本信息表VO
 */
@Data
@ToString
public class CbscCardVO {

    /**发卡机构*/
    private String issueBranch;
    /**卡号*/
    private String no;
    /**卡产品号*/
    private String product;

}

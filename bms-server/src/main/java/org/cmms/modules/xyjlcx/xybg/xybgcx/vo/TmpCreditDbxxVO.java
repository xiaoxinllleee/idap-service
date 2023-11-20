package org.cmms.modules.xyjlcx.xybg.xybgcx.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 担保信息
 */

@Data
@ToString
public class TmpCreditDbxxVO {
    /**机构代码*/
    private String jgdm;
    /**机构名称*/
    private String jgmc;
    /**客户经理*/
    private String khjl;
    /**客户经理姓名*/
    private String khjlxm;
    /**证件号码*/
    private String zjhm;
    /**贷款账号*/
    private String dkzh;
    /**借款日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy.MM.dd")
    @DateTimeFormat(pattern="yyyy.MM.dd")
    private Date jkrq;
    /**到期日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy.MM.dd")
    @DateTimeFormat(pattern="yyyy.MM.dd")
    private Date dqrq;
    /**借款人姓名*/
    private String jkrxm;
    /**贷款金额*/
    private java.math.BigDecimal dkje;
    /**核心余额*/
    private java.math.BigDecimal hxye;
    /**最新欠息日*/
    private String zxqxr;
    /**五级分类标识*/
    private String wjflbz;
    /**五级分类名称*/
    private String wjflmc;
    /**累计欠息次数*/
    private Integer ljqxcs;
    //最新起息日转换
    private String zxqxrShow;
}

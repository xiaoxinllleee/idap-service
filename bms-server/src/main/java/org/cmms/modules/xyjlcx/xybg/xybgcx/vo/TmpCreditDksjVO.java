package org.cmms.modules.xyjlcx.xybg.xybgcx.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.modules.xyjlcx.xybg.qxmx.entity.Qxmx;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 贷款数据
 */

@Data
@ToString
public class TmpCreditDksjVO {
    /**机构代码*/
    @Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    private String jgdm;
    /**客户经理*/
    @Dict(dicCode = "khjlbh", dictTable = "hr_bas_staff", dicText = "ygxm")
    private String khjl;
    /**证件号码*/
    private String zjhm;
    /**账号类型*/
    private String zhlx;
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
    /**贷款金额*/
    private java.math.BigDecimal dkje;
    /**核心余额*/
    private java.math.BigDecimal hxye;
    /**最新欠息日*/
    private String zxqxr;
    //最早起息日格式化
    private String zxqxrShow;
    /**五级分类标识*/
    @Dict(dicCode = "wjflbz")
    private String wjflbz;
    //五级分类标识名称
    private String wjflbzShow;
    /**累计欠息次数*/
    private Integer ljqxcs;
    /**业务种类*/
    @Dict(dicCode = "dkzl")
    private String ywzl;
    /**账号状态*/
    @Dict(dicCode = "account_status")
    private String zhzt;
    //贷款账号状态名称
    private String dkzhztShow;
    //贷款种类名称
    private String dkzlShow;
    /***/
    private Integer qxwjq;
    /**欠息合计*/
    private java.math.BigDecimal qxhj;
    //利息回收登记薄
    private List<Qxmx> dkqxdjbTable;
}

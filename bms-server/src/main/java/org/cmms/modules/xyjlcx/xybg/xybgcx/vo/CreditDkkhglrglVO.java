package org.cmms.modules.xyjlcx.xybg.xybgcx.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
public class CreditDkkhglrglVO {

    /**借款人证件号码*/
    @ApiModelProperty(value = "借款人证件号码")
    @ExcelVerify(notNull = true)
    private String jkrzjhm;
    /**借款人名称*/
    @ApiModelProperty(value = "借款人名称")
    @ExcelVerify(notNull = true)
    private String jkrmc;
    /**关联人证件号码*/
    @ApiModelProperty(value = "关联人证件号码")
    @ExcelVerify(notNull = true)
    private String glrzjhm;
    /**关联人证件类型*/
    @ApiModelProperty(value = "关联人证件类型")
    @Dict(dicCode = "dkjkpt_zjlx")
    @ExcelVerify(notNull = true)
    private String glrzjlx;
    private String glrZjlxDictText;
    /**关联人姓名*/
    @ApiModelProperty(value = "关联人姓名")
    @ExcelVerify(notNull = true)
    private String glrxm;
    /**关联关系*/
    @ApiModelProperty(value = "关联关系")
    @ExcelVerify(notNull = true)
    @Dict(dicCode = "dkjkpt_glgx")
    private Integer glgx;
    private String glrGlgxDictText;
    /**关联人工作单位*/
    @ApiModelProperty(value = "关联人工作单位")
    private String glrgzdw;
    /**关联人联系电话*/
    @ApiModelProperty(value = "关联人联系电话")
    @ExcelVerify(interHandler = true)
    private String glrlxdh;
    /**录入人*/
    @ApiModelProperty(value = "录入人")
    private String lrr;
    /**录入标识*/
    @ApiModelProperty(value = "录入标识")
    @Dict(dicCode = "lrbz")
    private Integer lrbz;
    /**录入时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
    private Date lrsj;

}

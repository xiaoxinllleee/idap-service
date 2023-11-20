package org.cmms.modules.automission.execlog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 贷款当月日平参数设置表VO
 */
@Data
@ToString
public class ErpBasWyxcsszVO {
    /**参数编码*/
    @ApiModelProperty(value = "参数编码")
    private String csbm;
    /**参数名称*/
    @ApiModelProperty(value = "参数名称")
    private String csmc;
    /**开始日期*/
    @ApiModelProperty(value = "开始日期")
    private String ksrq;
    /**结束日期*/
    @ApiModelProperty(value = "结束日期")
    private String jsrq;
    /**启用标识*/
    @ApiModelProperty(value = "启用标识")
    @Dict(dicCode = "qybz1")
    private Integer qybz;
    /**是否跨月*/
    @ApiModelProperty(value = "是否跨月")
    @Dict(dicCode = "sfky")
    private Integer sfky;
    /**录入标志*/
    @ApiModelProperty(value = "lrbz")
    @Dict(dicCode = "lrbz")
    private Integer lrbz;
    /**录入人*/
    @ApiModelProperty(value = "录入人")
    private String lrr;
    /**录入时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
    private Date lrsj;
}

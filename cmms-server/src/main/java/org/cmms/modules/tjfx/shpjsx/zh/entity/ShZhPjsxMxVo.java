package org.cmms.modules.tjfx.shpjsx.zh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 龚辉
 * @date 2023/9/9 13:21 周六
 */
@Data
public class ShZhPjsxMxVo {
    /**所属支行*/
    @Excel(name = "归属机构", width = 15,dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
    @ApiModelProperty(value = "归属机构")
    @Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
    private String sszh;
    /**归属网格*/
    @Excel(name = "归属网格", width = 15,dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
    @ApiModelProperty(value = "归属网格")
    @Dict( dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
    private String ssyxdy;
    /**商户名称*/
    @Excel(name = "商户名称", width = 15)
    @ApiModelProperty(value = "商户名称")
    private String shmc;
    /**系统测算授信额度*/
    @Excel(name = "系统测算授信额度", width = 15)
    @ApiModelProperty(value = "系统测算授信额度")
    private java.math.BigDecimal xtcsed;
    /**客户经理评定等级*/
    @Excel(name = "客户经理评定等级", width = 15,dicCode = "pddj")
    @ApiModelProperty(value = "客户经理评定等级")
    @Dict(dicCode = "pddj")
    private String khjlpddj;
    /**客户经理授信额度*/
    @Excel(name = "客户经理授信额度", width = 15)
    @ApiModelProperty(value = "客户经理授信额度")
    private String khjlsxed;
    /**录入人*/
    @Excel(name = "评级人工号")
    @ApiModelProperty(value = "评级人")
    private String lrr;
    /**录入人姓名*/
    @Excel(name = "评级人")
    @ApiModelProperty(value = "评级人")
    private String lrrxm;
    /**录入时间*/
    @Excel(name = "评级时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评级时间")
    private Date lrsj;
}

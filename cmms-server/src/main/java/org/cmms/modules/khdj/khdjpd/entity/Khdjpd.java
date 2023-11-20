package org.cmms.modules.khdj.khdjpd.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.validation.constraints.Max;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-09-20
 * @Version: V1.0
 */
@Data
@TableName("KHDJ_KHDJPD")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHDJ_KHDJPD对象", description="1")
public class Khdjpd {

    /**评定周期*/
    @Excel(name = "评定周期", width = 15, dicCode = "rqwd")
    @ApiModelProperty(value = "评定周期")
    @Dict(dicCode = "rqwd")
    @ExcelVerify(notNull = true)
    private String pdzq;
	/**评定日期*/
    @Excel(name = "评定日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评定日期")
    @ExcelVerify(notNull = true)
	private Date pdrq;
    /**组织标识*//*
    @ApiModelProperty(value = "组织标识")
    @Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
    private String zzbz;
    *//**机构代码*//*
    @ApiModelProperty(value = "机构代码")
    @Dict(dicCode="YWJGDM",dictTable="HR_BAS_ORGANIZATION",dicText="ZZJC")
    private String jgdm;
    *//**所属营销单元*//*
    @ApiModelProperty(value = "所属营销单元")
    @Dict(dicCode = "EJDYBH", dictTable = "V_KHHMC_SSYXDY", dicText = "XZMC || CMC")
    private String ssyxdy;*/
	/**客户名称*/
    @Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
    @ExcelVerify(notNull = true)
	private String khmc;
	/**证件号码*/
    @Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
    @ExcelVerify(notNull = true, interHandler = true)
	private String zjhm;
    /**客户类型*/
    @Excel(name = "客户类型", width = 15, dicCode = "khdjpd_khlx")
    @ApiModelProperty(value = "客户类型")
    @Dict(dicCode = "khdjpd_khlx")
    private String khlx;
    /**联系方式*/
    @Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
    private String lxfs;
	/**评定等级*/
    @Excel(name = "评定等级", width = 15, dicCode = "DJBH", dictTable = "KHDJ_KHDJSZ", dicText = "DJMC")
    @ApiModelProperty(value = "评定等级")
    @Dict(dicCode = "DJBH", dictTable = "KHDJ_KHDJSZ", dicText = "DJMC")
    @ExcelVerify(notNull = true)
	private String pddj;
    /**上期评定日期*/
    @Excel(name = "上期评定日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "上期评定日期")
    private Date sqpdrq;
	/**上期评定等级*/
    @Excel(name = "上期评定等级", width = 15, dicCode = "DJBH", dictTable = "KHDJ_KHDJSZ", dicText = "DJMC")
    @ApiModelProperty(value = "上期评定等级")
    @Dict(dicCode = "djbh", dictTable = "KHDJ_KHDJSZ", dicText = "djmc")
	private String sqpddj;
}

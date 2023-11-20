package org.cmms.modules.ywgl.dqdk.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 到期贷款
 * @Author: jeecg-boot
 * @Date:   2022-03-11
 * @Version: V1.0
 */
@Data
@TableName("APP_tjfx_dkdqkh")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="APP_tjfx_dkdqkh对象", description="到期贷款")
public class AppTjfxDkdqkh {

	/**客户性别*/
	@Excel(name = "客户性别", width = 15)
	@ApiModelProperty(value = "客户性别")
	private Integer khxb;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
	@ApiModelProperty(value = "客户编号")
	private String khbh;
	/**yggh*/
	@Excel(name = "yggh", width = 15)
    @ApiModelProperty(value = "yggh")
	private String yggh;
	/**jgdm*/
	@Excel(name = "jgdm", width = 15)
    @ApiModelProperty(value = "jgdm")
	private String jgdm;
	/**khmc*/
	@Excel(name = "khmc", width = 15)
    @ApiModelProperty(value = "khmc")
	private String khmc;
	/**dkzh*/
	@Excel(name = "dkzh", width = 15)
    @ApiModelProperty(value = "dkzh")
	private String dkzh;
	/**ffrq*/
	@Excel(name = "ffrq", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "ffrq")
	private Date ffrq;
	/**dqrq*/
	@Excel(name = "dqrq", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dqrq")
	private Date dqrq;
	/**dkje*/
	@Excel(name = "dkje", width = 15)
    @ApiModelProperty(value = "dkje")
	private java.math.BigDecimal dkje;
	/**dkye*/
	@Excel(name = "dkye", width = 15)
    @ApiModelProperty(value = "dkye")
	private java.math.BigDecimal dkye;
	/**ywzl*/
	@Excel(name = "ywzl", width = 15)
    @ApiModelProperty(value = "ywzl")
	private String ywzl;
	/**wjflbz*/
	@Excel(name = "wjflbz", width = 15)
    @ApiModelProperty(value = "wjflbz")
	@Dict(dicCode = "wjflbz")
	private Integer wjflbz;
	/**ll*/
	@Excel(name = "ll", width = 15)
    @ApiModelProperty(value = "ll")
	private java.math.BigDecimal ll;
	/**dkqx*/
	@Excel(name = "dkqx", width = 15)
    @ApiModelProperty(value = "dkqx")
	private String dkqx;
	/**lxfs*/
	@Excel(name = "lxfs", width = 15)
    @ApiModelProperty(value = "lxfs")
	private String lxfs;
	/**dz*/
	@Excel(name = "dz", width = 15)
    @ApiModelProperty(value = "dz")
	private String dz;
	/**khlx*/
	@Excel(name = "khlx", width = 15)
    @ApiModelProperty(value = "khlx")
	private Integer khlx;
	/**lrbz*/
	@Excel(name = "lrbz", width = 15)
    @ApiModelProperty(value = "lrbz")
	private Integer lrbz;
	/**lrsj*/
	@Excel(name = "lrsj", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "lrsj")
	private Date lrsj;
	/**lrr*/
	@Excel(name = "lrr", width = 15)
    @ApiModelProperty(value = "lrr")
	private String lrr;
	/**age*/
	@Excel(name = "age", width = 15)
    @ApiModelProperty(value = "age")
	private String age;
	/**endDate*/
	@Excel(name = "endDate", width = 15)
    @ApiModelProperty(value = "endDate")
	private Integer endDate;
	/**cq*/
	@Excel(name = "cq", width = 15)
    @ApiModelProperty(value = "cq")
	private String cq;
}

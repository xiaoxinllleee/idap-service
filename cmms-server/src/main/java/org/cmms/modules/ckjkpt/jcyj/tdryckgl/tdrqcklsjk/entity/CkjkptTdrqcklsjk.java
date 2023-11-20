package org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqcklsjk.entity;

import java.io.Serializable;
import java.util.Date;
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
 * @Description: 特定人群存款流水监控
 * @Author: cmms
 * @Date:   2019-10-10
 * @Version: V1.0
 */
@Data
@TableName("CKJKPT_TDRQLSJK")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CKJKPT_TDRQLSJK对象", description="特定人群存款流水监控")
public class CkjkptTdrqcklsjk {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**开户机构*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "开户机构")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String khxm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**电话号码*/
	@Excel(name = "电话号码", width = 15)
    @ApiModelProperty(value = "电话号码")
	private String dhhm;
	/**存款账号*/
	@Excel(name = "存款账号", width = 15)
    @ApiModelProperty(value = "存款账号")
	private String ckzh;
	/**对方账号*/
	@Excel(name = "对方账号", width = 15)
    @ApiModelProperty(value = "对方账号")
	private String dfzh;
	/**对方户名*/
	@Excel(name = "对方户名", width = 15)
    @ApiModelProperty(value = "对方户名")
	private String dfhm;
	/**对方机构号*/
	@Excel(name = "对方机构号", width = 15)
    @ApiModelProperty(value = "对方机构号")
	private String dfjg;
	/**对方机构名称*/
	@Excel(name = "对方机构名称", width = 15)
    @ApiModelProperty(value = "对方机构名称")
	private String dfjgmc;
	/**流出金额*/
	@Excel(name = "流出金额", width = 15)
    @ApiModelProperty(value = "流出金额")
	private java.math.BigDecimal lcje;
	/**流入金额*/
	@Excel(name = "流入金额", width = 15)
    @ApiModelProperty(value = "流入金额")
	private java.math.BigDecimal lrje;
	/**交易净流水*/
	@Excel(name = "交易净流水", width = 15)
    @ApiModelProperty(value = "交易净流水")
	private java.math.BigDecimal jylsA;
	/**交易总流水*/
	@Excel(name = "交易总流水", width = 15)
    @ApiModelProperty(value = "交易总流水")
	private java.math.BigDecimal jylsB;
}

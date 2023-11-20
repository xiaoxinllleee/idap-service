package org.cmms.modules.report.sgtzgl.cwbblrbRmb.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 财务报表利润表-人民币
 * @Author: jeecg-boot
 * @Date:   2022-10-28
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_cwxyslrb_rmb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_cwxyslrb_rmb对象", description="财务报表利润表-人民币")
public class SgtzCwbblrbRmb {

	/**主键ID*/
//	@TableId(type = IdType.ASSIGN_ID)
//	@ApiModelProperty(value = "主键ID")
//	private String id;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
	@ApiModelProperty(value = "数据日期")
	private String sjrq;
	/**项目*/
	@Excel(name = "项目", width = 15)
	@ApiModelProperty(value = "项目")
	private String xm;
	/**行次*/
	@Excel(name = "行次", width = 15)
	@ApiModelProperty(value = "行次")
	private String hc;
	/**本期金额*/
	@Excel(name = "本期金额", width = 15)
	@ApiModelProperty(value = "本期金额")
	private java.math.BigDecimal bqje;
	/**上年同期金额*/
	@Excel(name = "上年同期金额", width = 15)
	@ApiModelProperty(value = "上年同期金额")
	//@ExcelVerify(interHandler = true)
	private java.math.BigDecimal sntqje;
	/**创建人*/
/*	@ApiModelProperty(value = "创建人")
	private java.lang.String createBy;*/
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
	/**修改人*/
/*	@ApiModelProperty(value = "修改人")
	private java.lang.String updateBy;
	*//**修改时间*//*
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "修改时间")
	private java.util.Date updateTime;*/
}

package org.cmms.modules.report.sgtzgl.bhjymxb.entity;

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
 * @Description: 保函结余明细表
 * @Author: jeecg-boot
 * @Date:   2022-08-27
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_bhdjb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_bhdjb对象", description="保函结余明细表")
public class SgtzglBhjymxb {

	/**主键ID*/
/*	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
	private String id;*/
	/**序号*/
	@Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号")
	private String xh;
	/**序号*/
	@Excel(name = "数据日期", width = 15)
	@ApiModelProperty(value = "数据日期")
	private String fiscalDate;
	/**支行*/
	@Excel(name = "支行", width = 15)
	@ApiModelProperty(value = "支行")
	private String zh;
	/**客户经理*/
	@Excel(name = "客户经理", width = 15)
	@ApiModelProperty(value = "客户经理")
	private String khjl;
	/**出具日期*/
	@Excel(name = "出具日期", width = 15)
	@ApiModelProperty(value = "出具日期")
	private String cjrq;
	/**生效日期*/
	@Excel(name = "生效日期", width = 15)
	@ApiModelProperty(value = "生效日期")
	private String sxrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
	@ApiModelProperty(value = "到期日期")
	private String dqrq;
	/**基础日期*/
	@Excel(name = "基础日期", width = 15)
	@ApiModelProperty(value = "基础日期")
	private String jcrq;
	/**剩余天数*/
	@Excel(name = "剩余天数", width = 15)
	@ApiModelProperty(value = "剩余天数")
	private String syts;
	/**申请人*/
	@Excel(name = "申请人", width = 15)
    @ApiModelProperty(value = "申请人")
	private String sqr;
	/**收益人*/
	@Excel(name = "收益人", width = 15)
    @ApiModelProperty(value = "收益人")
	private String syr;
	/**保函金额*/
	@Excel(name = "保函金额", width = 15)
    @ApiModelProperty(value = "保函金额")
	private java.math.BigDecimal bhje;
	/**保证金*/
	@Excel(name = "保证金", width = 15)
    @ApiModelProperty(value = "保证金")
	private java.math.BigDecimal bzj;
	/**手续费*/
	@Excel(name = "手续费", width = 15)
    @ApiModelProperty(value = "手续费")
	private java.math.BigDecimal sxf;
	/**保函种类*/
	@Excel(name = "保函种类", width = 15)
    @ApiModelProperty(value = "保函种类")
	private String bhzl;
	/**保函编号*/
	@Excel(name = "保函编号", width = 15)
    @ApiModelProperty(value = "保函编号")
	private String bhbh;
	/**退回日期*/
	@Excel(name = "退回日期", width = 15)
    @ApiModelProperty(value = "退回日期")
	private String thrq;
	/**经办*/
	@Excel(name = "经办", width = 15)
    @ApiModelProperty(value = "经办")
	private String jb;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**企业规模*/
	@Excel(name = "企业规模", width = 15)
    @ApiModelProperty(value = "企业规模")
	private String qygm;
	/**行业*/
	@Excel(name = "行业", width = 15)
    @ApiModelProperty(value = "行业")
	//@ExcelVerify(interHandler = true)
	private String hy;
	/**创建人*/
/*	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;*/
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
/*	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	*//**修改时间*//*
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;*/
}

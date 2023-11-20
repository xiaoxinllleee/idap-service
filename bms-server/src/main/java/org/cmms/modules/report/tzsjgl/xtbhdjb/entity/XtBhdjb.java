package org.cmms.modules.report.tzsjgl.xtbhdjb.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 保函登记簿
 * @Author: jeecg-boot
 * @Date:   2022-08-23
 * @Version: V1.0
 */
@Data
@TableName("REP_TZGL_XT_BHDJB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="REP_TZGL_XT_BHDJB对象", description="保函登记簿")
public class XtBhdjb {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
	private String id;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15,format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据日期")
	private java.util.Date sjrq;
	/**序号*/
	@Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号 ")
	private java.lang.String xh;
	/**机构编码*/
	@Excel(name = "机构名称", width = 15)
    @ApiModelProperty(value = "机构名称 ")
	private java.lang.String jgbm;
	/**保函账号*/
	@Excel(name = "保函或有账户账号", width = 15)
    @ApiModelProperty(value = "保函或有账户账号 ")
	private java.lang.String bhzh;
	/**保函类型*/
	@Excel(name = "保函类型", width = 15)
    @ApiModelProperty(value = "保函类型 ")
	private java.lang.String bhlx;
	/**保函金额*/
	@Excel(name = "保函金额(元)", width = 15)
    @ApiModelProperty(value = "保函金额(元) ")
	private java.math.BigDecimal bhje;
	/**签发日期*/
	@Excel(name = "签发日期", width = 15)
	/*@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")*/
    @ApiModelProperty(value = "签发日期 ")
	private String qfrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期 ")
	private java.util.Date dqrq;
	/**报表日期*/
	@Excel(name = "报表日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "报表日期 ")
	private java.util.Date bbrq;
	/**天数*/
	@Excel(name = "天数", width = 15)
    @ApiModelProperty(value = "天数 ")
	private java.lang.String ts;
	/**申请人名称*/
	@Excel(name = "申请人名称", width = 15)
    @ApiModelProperty(value = "申请人名称 ")
	private java.lang.String sqrmc;
	/**申请人账号*/
	@Excel(name = "申请人账号", width = 15)
    @ApiModelProperty(value = "申请人账号 ")
	private java.lang.String sqrzh;
	/**保证金账号*/
	@Excel(name = "保证金账号", width = 15)
    @ApiModelProperty(value = "保证金账号 ")
	private java.lang.String bzjzh;
	/**保证金金额*/
	@Excel(name = "保证金金额(元)", width = 15)
    @ApiModelProperty(value = "保证金金额(元) ")
	private java.math.BigDecimal bzjje;
	/**收费比例*/
	@Excel(name = "收费比例（万分之）", width = 15)
    @ApiModelProperty(value = "收费比例（万分之） ")
	private java.math.BigDecimal sfbl;
	/**收费金额*/
	@Excel(name = "收费金额（元）", width = 15)
    @ApiModelProperty(value = "收费金额（元） ")
	private java.math.BigDecimal sfje;
	/**收款人名称*/
	@Excel(name = "收款人名称", width = 15)
    @ApiModelProperty(value = "收款人名称 ")
	private java.lang.String skrmc;
	/**收款人账号*/
	@Excel(name = "收款人账号", width = 15)
    @ApiModelProperty(value = "收款人账号 ")
	private java.lang.String skrzh;
	/**收款人行号*/
	@Excel(name = "收款人行号", width = 15)
    @ApiModelProperty(value = "收款人行号 ")
	private java.lang.String skrhh;
	/**柜员*/
	@Excel(name = "柜员", width = 15)
    @ApiModelProperty(value = "柜员 ")
	private java.lang.String gy;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15)
    @ApiModelProperty(value = "第一责任人 ")
	private java.lang.String dyzrr;
	/**是否发生垫款*/
	@Excel(name = "是否发生垫款", width = 15)
    @ApiModelProperty(value = "是否发生垫款 ")
	private java.lang.String sffsdk;
	/**垫款账号*/
	@Excel(name = "垫款账号", width = 15)
    @ApiModelProperty(value = "垫款账号 ")
	private java.lang.String dkzh;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "业务编号 ")
	private java.lang.String ywbh;
	/**余额*/
	@Excel(name = "余额", width = 15)
    @ApiModelProperty(value = "余额 ")
	private java.math.BigDecimal ye;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态 ")
	private java.lang.String zt;

}

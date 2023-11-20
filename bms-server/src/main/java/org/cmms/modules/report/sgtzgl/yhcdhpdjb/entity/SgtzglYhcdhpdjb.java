package org.cmms.modules.report.sgtzgl.yhcdhpdjb.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 银行承兑汇票登记薄
 * @Author: jeecg-boot
 * @Date:   2022-09-30
 * @Version: V1.0
 */
@Data
@TableName("ADS_REP_SGTZ_YHCDHPDJB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ADS_REP_SGTZ_YHCDHPDJB对象", description="银行承兑汇票登记薄")
public class SgtzglYhcdhpdjb {

	/**主键ID*/
//	@TableId(type = IdType.ASSIGN_ID)
//    @ApiModelProperty(value = "主键ID")
//	private String id;
	/**序号*/
	@Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号")
	private String xh;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
	@ApiModelProperty(value = "数据日期")
	private String fiscalDate;
	/**签发机构名称*/
	@Excel(name = "签发机构名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "签发机构名称")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String qfjgmc;
	/**银行承兑汇票或有账户账号*/
	@Excel(name = "银行承兑汇票或有账户账号", width = 15)
    @ApiModelProperty(value = "银行承兑汇票或有账户账号")
	private String yhcdhphyzhzh;
	/**出票人名称*/
	@Excel(name = "出票人名称", width = 15)
    @ApiModelProperty(value = "出票人名称")
	private String cprmc;
	/**出票人账号*/
	@Excel(name = "出票人账号", width = 15)
    @ApiModelProperty(value = "出票人账号")
	private String cprzh;
	/**票面金额(元)*/
	@Excel(name = "票面金额(元)", width = 15)
    @ApiModelProperty(value = "票面金额(元)")
	private java.math.BigDecimal pmje;
	/**凭证号码*/
	@Excel(name = "凭证号码", width = 15)
    @ApiModelProperty(value = "凭证号码")
	private String pzhm;
	/**签发日期*/
	@Excel(name = "签发日期", width = 15)
    @ApiModelProperty(value = "签发日期")
	private String qfrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
    @ApiModelProperty(value = "到期日期")
	private String dqrq;
	/**未用注销日期*/
	@Excel(name = "未用注销日期", width = 15)
    @ApiModelProperty(value = "未用注销日期")
	private String wyzxrq;
	/**当前状态*/
	@Excel(name = "当前状态", width = 15)
    @ApiModelProperty(value = "当前状态")
	private String dqzt;
	/**新银行承兑汇票号*/
	@Excel(name = "新银行承兑汇票号", width = 15)
    @ApiModelProperty(value = "新银行承兑汇票号")
	private String xyhcdhph;
	/**兑付日期*/
	@Excel(name = "兑付日期", width = 15)
    @ApiModelProperty(value = "兑付日期")
	private String dfrq;
	/**保证金账号*/
	@Excel(name = "保证金账号", width = 15)
    @ApiModelProperty(value = "保证金账号")
	private String bzjzh;
	/**保证金比例（%）*/
	@Excel(name = "保证金比例（%）", width = 15)
    @ApiModelProperty(value = "保证金比例（%）")
	private java.math.BigDecimal bzjbl;
	/**保证金金额(元)*/
	@Excel(name = "保证金金额(元)", width = 15)
    @ApiModelProperty(value = "保证金金额(元)")
	private java.math.BigDecimal bzjje;
	/**收费比例（‰）*/
	@Excel(name = "收费比例（‰）", width = 15)
    @ApiModelProperty(value = "收费比例（‰）")
	private java.math.BigDecimal sfbl;
	/**收费金额(元)*/
	@Excel(name = "收费金额(元)", width = 15)
    @ApiModelProperty(value = "收费金额(元)")
	private java.math.BigDecimal sfje;
	/**收款人名称*/
	@Excel(name = "收款人名称", width = 15)
    @ApiModelProperty(value = "收款人名称")
	private String skrmc;
	/**收款人账号*/
	@Excel(name = "收款人账号", width = 15)
    @ApiModelProperty(value = "收款人账号")
	private String skrzh;
	/**收款人行号*/
	@Excel(name = "收款人行号", width = 15)
    @ApiModelProperty(value = "收款人行号")
	private String skrhh;
	/**经办柜员*/
	@Excel(name = "经办柜员", width = 15)
    @ApiModelProperty(value = "经办柜员")
	private String jbgy;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15)
    @ApiModelProperty(value = "第一责任人")
	private String dyzrr;
	/**是否发生垫款*/
	@Excel(name = "是否发生垫款", width = 15)
    @ApiModelProperty(value = "是否发生垫款")
	private String sffsdk;
	/**垫款账号*/
	@Excel(name = "垫款账号", width = 15)
    @ApiModelProperty(value = "垫款账号")
	private String dkzh;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "业务编号")
	//@ExcelVerify(interHandler = true)
	private String ywbh;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;

}

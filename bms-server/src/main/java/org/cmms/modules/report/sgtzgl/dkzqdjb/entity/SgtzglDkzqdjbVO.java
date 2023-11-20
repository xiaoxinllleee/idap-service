package org.cmms.modules.report.sgtzgl.dkzqdjb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 贷款展期登记簿
 * @Author: jeecg-boot
 * @Date:   2022-09-30
 * @Version: V1.0
 */
@Data
@TableName("ADS_REP_SGTZ_DKZQDJB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ADS_REP_SGTZ_DKZQDJB对象", description="贷款展期登记簿")
public class SgtzglDkzqdjbVO {


	/**序号*/
	@Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号")
	private String xh;
	/**开户机构*/
	@Excel(name = "开户机构", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "开户机构")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String khjg;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String zh;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15)
    @ApiModelProperty(value = "借款日期")
	private String jkrq;
	/**原到期日*/
	@Excel(name = "原到期日", width = 15)
    @ApiModelProperty(value = "原到期日")
	private String ydqr;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String jyrq;
	/**展期到期日*/
	@Excel(name = "展期到期日", width = 15)
    @ApiModelProperty(value = "展期到期日")
	private String zqdqr;
	/**原产品名称*/
	@Excel(name = "原产品名称", width = 15)
    @ApiModelProperty(value = "原产品名称")
	private String ycpmc;
	/**展期后产品名称*/
	@Excel(name = "展期后产品名称", width = 15)
    @ApiModelProperty(value = "展期后产品名称")
	private String zqhcpmc;
	/**原利率（%）*/
	@Excel(name = "原利率（%）", width = 15)
    @ApiModelProperty(value = "原利率（%）")
	private java.math.BigDecimal yll;
	/**展期利率（%）*/
	@Excel(name = "展期利率（%）", width = 15)
    @ApiModelProperty(value = "展期利率（%）")
	private java.math.BigDecimal zqll;
	/**贷款余额（元）*/
	@Excel(name = "贷款余额（元）", width = 15)
    @ApiModelProperty(value = "贷款余额（元）")
	private java.math.BigDecimal dkye;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15)
    @ApiModelProperty(value = "第一责任人")
	//@ExcelVerify(interHandler = true)
	private String dyzrr;

}

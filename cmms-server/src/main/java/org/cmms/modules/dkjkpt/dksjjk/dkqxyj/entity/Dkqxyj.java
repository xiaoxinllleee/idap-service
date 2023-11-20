package org.cmms.modules.dkjkpt.dksjjk.dkqxyj.entity;

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
 * @Description: 贷款欠息预警
 * @Author: jeecg-boot
 * @Date:   2022-09-15
 * @Version: V1.0
 */
@Data
@TableName("DKJKPT_DKQXYJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DKJKPT_DKQXYJ对象", description="贷款欠息预警")
public class Dkqxyj {
    
	/**统计月份*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	private String jgdm;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
    @ApiModelProperty(value = "机构名称")
	private String jgmc;
	/**欠息（到本月21号）*/
	@Excel(name = "欠息（到本月21号）", width = 15)
    @ApiModelProperty(value = "欠息（到本月21号）")
	private java.math.BigDecimal qx;
	/**收息账号金额是否能还款*/
	@Excel(name = "收息账号金额是否能还款", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "收息账号金额是否能还款")
	@Dict(dicCode = "sfbz")
	private String sxzhjesfnhk;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String khmc;
	/**身份证*/
	@Excel(name = "身份证", width = 15)
    @ApiModelProperty(value = "身份证")
	private String zjhm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15,dicCode = "dkjkpt_khlx")
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "dkjkpt_khlx")
	private String khlx;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String dz;
	/**电话号码*/
	@Excel(name = "电话号码", width = 15)
    @ApiModelProperty(value = "电话号码")
	private String dhhm;
	/**存款账号（收息账号）*/
	@Excel(name = "存款账号（收息账号）", width = 15)
    @ApiModelProperty(value = "存款账号（收息账号）")
	private String ckzh;
	/**存款金额（收息账号）*/
	@Excel(name = "存款金额（收息账号）", width = 15)
    @ApiModelProperty(value = "存款金额（收息账号）")
	private java.math.BigDecimal ckje;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**表内应计利息*/
	@Excel(name = "表内应计利息", width = 15)
    @ApiModelProperty(value = "表内应计利息")
	private java.math.BigDecimal bnyjlx;
	/**表内应收利息*/
	@Excel(name = "表内应收利息", width = 15)
    @ApiModelProperty(value = "表内应收利息")
	private java.math.BigDecimal blyslx;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "借款日期")
	private Date jkrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private Date dqrq;
	/**起息日*/
	@Excel(name = "起息日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "起息日")
	private Date qxr;
	/**结息日*/
	@Excel(name = "结息日", width = 15)
    @ApiModelProperty(value = "结息日")
	private Integer jxr;
	/**贷款利率(%)*/
	@Excel(name = "贷款利率(%)", width = 15)
    @ApiModelProperty(value = "贷款利率(%)")
	private java.math.BigDecimal dkll;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15)
    @ApiModelProperty(value = "第一责任人")
	private String dyzrr;
	/**创建人*/
//	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
//	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
}

package org.cmms.modules.dkjkpt.dksjjk.dksjahtj.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 贷款余额表
 * @Author: Penghr
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Data
@TableName("DKJKPT_BNDKSJMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DKJKPT_BNDKSJMX对象", description="贷款余额表")
public class Dkyeb {
    
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjyf;
	/**机构代码*/
	@Excel(name = "机构名称", width = 15, dicCode = "YWJGDM", dictTable = "V_HR_BAS_ORGANIZATION_CMMSZH", dicText = "ZZJC")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "YWJGDM", dictTable = "V_HR_BAS_ORGANIZATION_CMMSZH", dicText = "ZZJC")
	private String jgdm;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15, dicCode = "dkjkpt_khlx")
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "dkjkpt_khlx")
	private String khlx;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String dz;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**便民卡卡号*/
	@Excel(name = "便民卡卡号", width = 15)
    @ApiModelProperty(value = "便民卡卡号")
	private String bmkkh;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
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
	/**贷款期限*/
	@Excel(name = "贷款期限", width = 15, dicCode = "dkqx")
    @ApiModelProperty(value = "贷款期限")
	@Dict(dicCode = "dkqx")
	private Integer dkqx;
	/**产品名称*/
	@Excel(name = "产品名称", width = 15)
    @ApiModelProperty(value = "产品名称")
	private String cpmc;
	/**剩余天数*/
	@Excel(name = "剩余天数", width = 15)
    @ApiModelProperty(value = "剩余天数")
	private Integer syts;
	/**欠息天数*/
	//@Excel(name = "欠息天数", width = 15)
    @ApiModelProperty(value = "欠息天数")
	private Integer qxts;
	/**表内应计利息*/
	//@Excel(name = "表内应计利息", width = 15)
    @ApiModelProperty(value = "表内应计利息")
	private java.math.BigDecimal bnyjlx;
	/**表内应收利息*/
	//@Excel(name = "表内应收利息", width = 15)
    @ApiModelProperty(value = "表内应收利息")
	private java.math.BigDecimal bnyslx;
	/**表外应计利息*/
	//@Excel(name = "表外应计利息", width = 15)
    @ApiModelProperty(value = "表外应计利息")
	private java.math.BigDecimal bwyjlx;
	/**表外应收利息*/
	//@Excel(name = "表外应收利息", width = 15)
    @ApiModelProperty(value = "表外应收利息")
	private java.math.BigDecimal bwyslx;
	/**表内外欠息和*/
	//@Excel(name = "表内外欠息和", width = 15)
    @ApiModelProperty(value = "表内外欠息和")
	private java.math.BigDecimal bnwqxh;
	/**贷款利率*/
	@Excel(name = "贷款利率(%)", width = 15)
    @ApiModelProperty(value = "贷款利率(%)")
	private java.math.BigDecimal dkll;
	/**担保方式*/
	@Excel(name = "担保方式", width = 15, dicCode = "dbfs")
    @ApiModelProperty(value = "担保方式")
	@Dict(dicCode = "dbfs")
	private String dbfs;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15)
    @ApiModelProperty(value = "第一责任人")
	private String dyzrr;
	/**主客户经理*/
	@Excel(name = "主客户经理", width = 15)
    @ApiModelProperty(value = "主客户经理")
	private String khjlbz;
	/**所属行业类型*/
	@Excel(name = "所属行业类型", width = 15, dicCode = "ID", dictTable = "DKJKPT_DKHYLX_SJZDB", dicText = "NAME", ds="ckjkpt")
    @ApiModelProperty(value = "所属行业类型")
	@Dict(dicCode = "ID", dictTable = "DKJKPT_DKHYLX_SJZDB", dicText = "NAME", ds="ckjkpt")
	private String khsshylx;
	/**贷款投向*/
	@Excel(name = "贷款投向", width = 15, dicCode = "ID", dictTable = "FPDK_DKTXDZB_1", dicText = "NAME", ds="ckjkpt")
    @ApiModelProperty(value = "贷款投向")
	@Dict(dicCode = "ID", dictTable = "FPDK_DKTXDZB_1", dicText = "NAME", ds="ckjkpt")
	private String dktx;
	/**贷款形态*/
	@Excel(name = "贷款形态", width = 15, dicCode = "dkxt")
    @ApiModelProperty(value = "贷款形态")
	@Dict(dicCode = "dkxt")
	private String dkxt;
	/**信贷贷款品种*/
	@Excel(name = "信贷贷款品种", width = 15, dicCode = "dkzl")
    @ApiModelProperty(value = "信贷贷款品种")
	@Dict(dicCode = "dkzl")
	private String xddkpz;
	/**到期情况监测*/
	//@Excel(name = "到期情况监测", width = 15)
    @ApiModelProperty(value = "到期情况监测")
	private String dqqkjc;
	/**客户经理员工工号*/
	//@Excel(name = "客户经理员工工号", width = 15)
    @ApiModelProperty(value = "客户经理员工工号")
	private String khjlyggh;
}

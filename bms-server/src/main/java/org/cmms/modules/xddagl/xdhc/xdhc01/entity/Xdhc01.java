package org.cmms.modules.xddagl.xdhc.xdhc01.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
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
 * @Description: 信贷T+1核查
 * @Author: jeecg-boot
 * @Date:   2022-01-18
 * @Version: V1.0
 */
@Data
@TableName("Xddagl_bndksjjktz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Xddagl_bndksjjktz对象", description="信贷T+1核查")
public class Xdhc01 {
	//贷款帐号,不良形成原因,责任界定,清收处置措施,清收处置时限,主要责任人,次要责任人,清收责任人
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
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
	@Excel(name = "客户类型", width = 15,dicCode = "dkkhlx")
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "dkkhlx")
	private String khlx;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String dz;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	@ExcelVerify(notNull = true)
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
	@Excel(name = "贷款期限", width = 15)
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
	@Excel(name = "欠息天数", width = 15)
    @ApiModelProperty(value = "欠息天数")
	private Integer qxts;
	/**表内应计利息*/
	@Excel(name = "表内应计利息", width = 15)
    @ApiModelProperty(value = "表内应计利息")
	private java.math.BigDecimal bnyjlx;
	/**表内应收利息*/
	@Excel(name = "表内应收利息", width = 15)
    @ApiModelProperty(value = "表内应收利息")
	private java.math.BigDecimal bnyslx;
	/**表外应计利息*/
	@Excel(name = "表外应计利息", width = 15)
    @ApiModelProperty(value = "表外应计利息")
	private java.math.BigDecimal bwyjlx;
	/**表外应收利息*/
	@Excel(name = "表外应收利息", width = 15)
    @ApiModelProperty(value = "表外应收利息")
	private java.math.BigDecimal bwyslx;
	/**表内外欠息和*/
	@Excel(name = "表内外欠息和", width = 15)
    @ApiModelProperty(value = "表内外欠息和")
	private java.math.BigDecimal bnwqxh;
	/**贷款利率*/
	@Excel(name = "贷款利率", width = 15)
    @ApiModelProperty(value = "贷款利率")
	private java.math.BigDecimal dkll;
	/**担保方式*/
	@Excel(name = "担保方式", width = 15)
    @ApiModelProperty(value = "担保方式")
	@Dict(dicCode = "gljydbfs")
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
	@Excel(name = "所属行业类型", width = 15)
    @ApiModelProperty(value = "所属行业类型")
	@Dict(dicCode = "id",dictTable = "DKJKPT_DKHYLX_SJZDB",dicText = "name",ds = "eweb")
	private String khsshylx;
	/**贷款投向*/
	@Excel(name = "贷款投向", width = 15)
    @ApiModelProperty(value = "贷款投向")
	@Dict(dicCode = "dmid",dictTable = "SYS_BAS_DMXX",dicText = "dmmc",ds = "eweb")
	private String dktx;
	/**贷款形态*/
	@Excel(name = "贷款形态", width = 15)
    @ApiModelProperty(value = "贷款形态")
	@Dict(dicCode = "wjflbz")
	private String dkxt;
	/**信贷贷款品种*/
	@Excel(name = "信贷贷款品种", width = 15)
    @ApiModelProperty(value = "信贷贷款品种")
	@Dict(dicCode = "dkzl")
	private String xddkpz;
	/**到期情况监测*/
	@Excel(name = "到期情况监测", width = 15)
    @ApiModelProperty(value = "到期情况监测")
	@Dict(dicCode = "dqqkjc")
	private String dqqkjc;
	/**不良形成原因*/
	@Excel(name = "不良形成原因", width = 15)
    @ApiModelProperty(value = "不良形成原因")
	@Dict(dicCode = "blxcyy")
	private String blxcyy;
	/**责任界定*/
	@Excel(name = "责任界定", width = 15)
    @ApiModelProperty(value = "责任界定")
	@Dict(dicCode = "zrjd")
	private String zrjd;
	/**清收处置措施*/
	@Excel(name = "清收处置措施", width = 15)
    @ApiModelProperty(value = "清收处置措施")
	@Dict(dicCode = "qsczcs")
	private String qsczcs;
	/**清收处置时限*/
	@Excel(name = "清收处置时限", width = 15)
    @ApiModelProperty(value = "清收处置时限")
	private String qsczsx;
	/**贷款责任人*/
	@Excel(name = "贷款责任人", width = 15)
    @ApiModelProperty(value = "贷款责任人")
	private String dkzrr;
	/**清收责任人*/
	@Excel(name = "清收责任人", width = 15)
    @ApiModelProperty(value = "清收责任人")
	private String qszrr;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标识（0：导入 1：录入 2：修改）*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入操作员*/
	@Excel(name = "录入操作员", width = 15)
    @ApiModelProperty(value = "录入操作员")
	private String lrr;
	/**客户经理员工工号*/
	@Excel(name = "客户经理员工工号", width = 15)
    @ApiModelProperty(value = "客户经理员工工号")
	private String khjlyggh;
	/**zyzrr*/
	@Excel(name = "主要负责人", width = 15)
    @ApiModelProperty(value = "主要负责人")
	private String zyzrr;
	/**cyzrr*/
	@Excel(name = "次要责任人", width = 15)
    @ApiModelProperty(value = "次要责任人")
	@ExcelVerify(interHandler = true)
	private String cyzrr;
	/**xwqy*/
	@Excel(name = "xwqy", width = 15)
    @ApiModelProperty(value = "xwqy")
	private String xwqy;
	/**电话号码*/
	@Excel(name = "电话号码", width = 15)
    @ApiModelProperty(value = "电话号码")
	private String dhhm;
	/**审核状态*/
	@Excel(name = "审核状态", width = 15)
    @ApiModelProperty(value = "审核状态")
	@Dict(dicCode = "dkjkpt_shzt")
	private Integer shzt;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
	private String hth;
}

package org.cmms.modules.xddaglxt.dksjgl.dksjjktz.entity;

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
 * @Description: 贷款数据监控台账
 * @Author: jeecg-boot
 * @Date:   2021-11-22
 * @Version: V1.0
 */
@Data
@TableName("XDDAGL_BNDKSJJKTZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="XDDAGL_BNDKSJJKTZ对象", description="贷款数据监控台账")
public class Dksjjktz {

	/**数据月份*/
	@Excel(name = "数据月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据月份")
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
	@Excel(name = "客户类型",width = 15)
	@ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "dkjkpt_khlx")
	private String khlx;
	/**住址*/
	@Excel(name = "住址", width = 15)
	@ApiModelProperty(value = "住址")
	private String dz;

	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
	private String hth;
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
	@Excel(name = "贷款利率(%)", width = 15)
	@ApiModelProperty(value = "贷款利率(%)")
	private java.math.BigDecimal dkll;
	/**担保方式*/
	@Excel(name = "担保方式", width = 15)
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
	@Excel(name = "所属行业类型", width = 15)
	@ApiModelProperty(value = "所属行业类型")
	private String khsshylx;
	/**贷款投向*/
	@Excel(name = "贷款投向", width = 15)
	@ApiModelProperty(value = "贷款投向")
	private String dktx;
	/**贷款形态*/
	@Excel(name = "贷款形态", width = 15)
	@ApiModelProperty(value = "贷款形态")
	@Dict(dicCode = "wjflbz")
	private String dkxt;
	/**信贷贷款品种*/
	@Excel(name = "信贷贷款品种", width = 15,dicCode = "dkzl")
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
	/**主要责任人*/
	@Excel(name = "主要责任人", width = 15)
	@ApiModelProperty(value = "主要责任人")
	private String zyzrr;
	/**次要责任人*/
	@Excel(name = "次要责任人", width = 15)
	@ApiModelProperty(value = "次要责任人")
	private String cyzrr;
	/**清收责任人*/
	@Excel(name = "清收责任人", width = 15)
	@ApiModelProperty(value = "清收责任人")
	private String qszrr;
	/**审核状态*/
	@Excel(name = "审核状态", width = 15)
	@ApiModelProperty(value = "审核状态")
	@Dict(dicCode = "dkjkpt_shzt")
	private Integer shzt;


	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String bz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
	@ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;

	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
	@ApiModelProperty(value = "贷款账号")
	@ExcelVerify(notNull = true,interHandler = true)
	private String dkzh;


	/**贷款责任人*/
    @ApiModelProperty(value = "贷款责任人")
	private String dkzrr;
	/**客户经理员工工号*/
    @ApiModelProperty(value = "客户经理员工工号")
	private String khjlyggh;
	/**xwqy*/
    @ApiModelProperty(value = "xwqy")
	private String xwqy;
	/**电话号码*/
    @ApiModelProperty(value = "电话号码")
	private String dhhm;

}

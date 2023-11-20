package org.cmms.modules.report.sgtzgl.dkqmx.entity;

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
 * @Description: 贷款全明细
 * @Author: jeecg-boot
 * @Date:   2022-09-30
 * @Version: V1.0
 */
@Data
@TableName("ADS_REP_SGTZ_DKQMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ADS_REP_SGTZ_DKQMX对象", description="贷款全明细")
public class SgtzglDkqmx {

	/**主键ID*/
//	@TableId(type = IdType.ASSIGN_ID)
//    @ApiModelProperty(value = "主键ID")
//	private String id;
	/**数据日期*/
	@Excel(name = "会计日期", width = 15)
	@ApiModelProperty(value = "会计日期")
	private String fiscalDate;
	/**网点名称*/
	@Excel(name = "网点名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "网点名称")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String wdmc;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**借款人名称*/
	@Excel(name = "借款人名称", width = 15)
    @ApiModelProperty(value = "借款人名称")
	private String jkrmc;
	/**借款人证件号码*/
	@Excel(name = "借款人证件号码", width = 15)
    @ApiModelProperty(value = "借款人证件号码")
	private String jkrzjhm;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**贷款日期*/
	@Excel(name = "贷款日期", width = 15)
    @ApiModelProperty(value = "贷款日期")
	private String dkrq;
	/**展期日期*/
	@Excel(name = "展期日期", width = 15)
    @ApiModelProperty(value = "展期日期")
	private String zqrq;
	/**贷款到期日*/
	@Excel(name = "贷款到期日", width = 15)
    @ApiModelProperty(value = "贷款到期日")
	private String dkdqr;
	/**上次结息日*/
	@Excel(name = "上次结息日", width = 15)
    @ApiModelProperty(value = "上次结息日")
	private String scjxr;
	/**贷款利率*/
	@Excel(name = "贷款利率", width = 15)
    @ApiModelProperty(value = "贷款利率")
	private java.math.BigDecimal dkll;
	/**五级形态*/
	@Excel(name = "五级形态", width = 15)
    @ApiModelProperty(value = "五级形态")
	private String wjfl;
	/**客户经理*/
	@Excel(name = "客户经理", width = 15)
    @ApiModelProperty(value = "客户经理")
	private String khjl;
	/**主客户经理*/
	@Excel(name = "主客户经理", width = 15)
    @ApiModelProperty(value = "主客户经理")
	private String zkhjl;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15)
    @ApiModelProperty(value = "第一责任人")
	private String dyzrr;
	/**行政村组*/
	@Excel(name = "行政村组", width = 15)
    @ApiModelProperty(value = "行政村组")
	private String xzcz;
	/**客户详细地址*/
	@Excel(name = "客户详细地址", width = 15)
    @ApiModelProperty(value = "客户详细地址")
	private String khxxdz;
	/**客户联系方式*/
	@Excel(name = "客户联系方式", width = 15)
    @ApiModelProperty(value = "客户联系方式")
	private String khlxfs;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	private String khlx;
	/**客户类型1*/
	@Excel(name = "客户类型1", width = 15)
    @ApiModelProperty(value = "客户类型1")
	private String khlx1;
	/**客户类型2*/
	@Excel(name = "客户类型2", width = 15)
    @ApiModelProperty(value = "客户类型2")
	private String khlx2;
	/**客户类型3*/
	@Excel(name = "客户类型3", width = 15)
    @ApiModelProperty(value = "客户类型3")
	private String khlx3;
	/**企业规模*/
	@Excel(name = "企业规模", width = 15)
    @ApiModelProperty(value = "企业规模")
	private String qygm;
	/**企业规模1*/
	@Excel(name = "企业规模1", width = 15)
    @ApiModelProperty(value = "企业规模1")
	private String qygm1;
	/**业务品种*/
	@Excel(name = "业务品种", width = 15)
    @ApiModelProperty(value = "业务品种")
	private String ywpz;
	/**贷款原始期限*/
	@Excel(name = "贷款原始期限", width = 15)
    @ApiModelProperty(value = "贷款原始期限")
	private String dkysqx;
	/**担保方式*/
	@Excel(name = "担保方式", width = 15)
    @ApiModelProperty(value = "担保方式")
	private String dbfs;
	/**贷款投向*/
	@Excel(name = "贷款投向", width = 15)
    @ApiModelProperty(value = "贷款投向")
	private String dktx;
	/**贷款投向1*/
	@Excel(name = "贷款投向1", width = 15)
    @ApiModelProperty(value = "贷款投向1")
	private String dktx1;
	/**贷款投向2*/
	@Excel(name = "贷款投向2", width = 15)
    @ApiModelProperty(value = "贷款投向2")
	private String dktx2;
	/**贷款投向3*/
	@Excel(name = "贷款投向3", width = 15)
    @ApiModelProperty(value = "贷款投向3")
	private String dktx3;
	/**贷款投向4*/
	@Excel(name = "贷款投向4", width = 15)
    @ApiModelProperty(value = "贷款投向4")
	private String dktx4;
	/**贷款投向5*/
	@Excel(name = "贷款投向5", width = 15)
    @ApiModelProperty(value = "贷款投向5")
	private String dktx5;
	/**贷款投向6*/
	@Excel(name = "贷款投向6", width = 15)
    @ApiModelProperty(value = "贷款投向6")
	private String dktx6;
	/**贷款投向7*/
	@Excel(name = "贷款投向7", width = 15)
    @ApiModelProperty(value = "贷款投向7")
	private String dktx7;
	/**贷款投向8*/
	@Excel(name = "贷款投向8", width = 15)
    @ApiModelProperty(value = "贷款投向8")
	private String dktx8;
	/**是否财政贴息*/
	@Excel(name = "是否财政贴息", width = 15)
    @ApiModelProperty(value = "是否财政贴息")
	private String sfcztx;
	/**贷款其他类型*/
	@Excel(name = "贷款其他类型", width = 15)
    @ApiModelProperty(value = "贷款其他类型")
	private String dkqtlx;
	/**备注1*/
	@Excel(name = "备注1", width = 15)
    @ApiModelProperty(value = "备注1")
	private String bz1;
	/**备注2*/
	@Excel(name = "备注2", width = 15)
    @ApiModelProperty(value = "备注2")
	private String bz2;
	/**固定资产类型*/
	@Excel(name = "固定资产类型", width = 15)
    @ApiModelProperty(value = "固定资产类型")
	private String gdzclx;
	/**固定资产类型1*/
	@Excel(name = "固定资产类型1", width = 15)
    @ApiModelProperty(value = "固定资产类型1")
	private String gdzclx1;
	/**固定资产类型2*/
	@Excel(name = "固定资产类型2", width = 15)
    @ApiModelProperty(value = "固定资产类型2")
	private String gdzclx2;
	/**固定资产类型3*/
	@Excel(name = "固定资产类型3", width = 15)
    @ApiModelProperty(value = "固定资产类型3")
	private String gdzclx3;
	/**固定资产类型4*/
	@Excel(name = "固定资产类型4", width = 15)
    @ApiModelProperty(value = "固定资产类型4")
	private String gdzclx4;
	/**借款人所属行业门类*/
	@Excel(name = "借款人所属行业门类", width = 15)
    @ApiModelProperty(value = "借款人所属行业门类")
	private String jkrsshyml;
	/**借款人所属行业大类*/
	@Excel(name = "借款人所属行业大类", width = 15)
    @ApiModelProperty(value = "借款人所属行业大类")
	private String jkrsshydl;
	/**借款人所属行业中类*/
	@Excel(name = "借款人所属行业中类", width = 15)
    @ApiModelProperty(value = "借款人所属行业中类")
	private String jkrsshyzl;
	/**借款人所属行业小类*/
	@Excel(name = "借款人所属行业小类", width = 15)
    @ApiModelProperty(value = "借款人所属行业小类")
	private String jkrsshyxl;
	/**逾期日期*/
	@Excel(name = "逾期日期", width = 15)
    @ApiModelProperty(value = "逾期日期")
	private String yqrq;
	/**合同编号*/
	@Excel(name = "合同编号", width = 15)
    @ApiModelProperty(value = "合同编号")
//	@ExcelVerify(interHandler = true)
	private String htbh;
	/**创建人*/
/*	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	*//**创建时间*//*
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	*//**修改人*//*
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	*//**修改时间*//*
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;*/


}

package org.cmms.modules.tjfx.jcsjgl.dkye.entity;

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
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-08-10
 * @Version: V1.0
 */
@Data
@TableName("TJFX_XDDKYEB_XDXTSJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_XDDKYEB_XDXTSJ对象", description="贷款余额表")
public class TjfxXddkyebXdxtsj {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**机构名称*/
	@Excel(name = "机构代码", width = 15, dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String khmc;
	/**注册证件号*/
	@Excel(name = "注册证件号", width = 15)
	@ApiModelProperty(value = "注册证件号")
	private String zjhm;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**本金金额*/
	@Excel(name = "本金金额", width = 15)
    @ApiModelProperty(value = "本金金额")
	private java.math.BigDecimal bjye;
	/**贷款日期*/
	@Excel(name = "贷款日期", width = 15)
	@ApiModelProperty(value = "贷款日期")
	private String dkrq;
	/**展期日期*/
	@Excel(name = "展期日期", width = 15)
    @ApiModelProperty(value = "展期日期")
	private String zqrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
    @ApiModelProperty(value = "到期日期")
	private String dqrq;
	/**上次结息日*/
	@Excel(name = "上次结息日", width = 15)
    @ApiModelProperty(value = "上次结息日")
	private String scjxr;
	/**贷款到期前利率 %*/
	@Excel(name = "贷款到期前利率 %", width = 15)
    @ApiModelProperty(value = "贷款到期前利率 %")
	private java.math.BigDecimal dkdqqlv;
	/**五级分类状态*/
	@Excel(name = "五级分类状态", width = 15)
    @ApiModelProperty(value = "五级分类状态")
	private String wjflzt;
	/**客户经理*/
	@Excel(name = "客户经理", width = 15, dicCode="yggh", dictTable="HR_BAS_STAFF", dicText="ygxm")
    @ApiModelProperty(value = "客户经理")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String khjl;
	/**主客户经理*/
	@Excel(name = "主客户经理", width = 15, dicCode="yggh", dictTable="HR_BAS_STAFF", dicText="ygxm")
    @ApiModelProperty(value = "主客户经理")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String zkhjl;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15, dicCode="yggh", dictTable="HR_BAS_STAFF", dicText="ygxm")
    @ApiModelProperty(value = "第一责任人")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String dyzrr;
	/**行政村组*/
	@Excel(name = "行政村组", width = 15)
    @ApiModelProperty(value = "行政村组")
	private String xzcz;
	/**详细地址*/
	@Excel(name = "详细地址", width = 15)
    @ApiModelProperty(value = "详细地址")
	private String xxdz;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
	private String lxdh;
	/**借款人性质分类*/
	@Excel(name = "借款人性质分类", width = 15)
    @ApiModelProperty(value = "借款人性质分类")
	private String jkrxzfl;
	/**借款人性质分类1*/
	@Excel(name = "借款人性质分类1", width = 15)
    @ApiModelProperty(value = "借款人性质分类1")
	private String jkrxzfl1;
	/**借款人性质分类2*/
	@Excel(name = "借款人性质分类2", width = 15)
    @ApiModelProperty(value = "借款人性质分类2")
	private String jkrxzfl2;
	/**借款人性质分类3*/
	@Excel(name = "借款人性质分类3", width = 15)
    @ApiModelProperty(value = "借款人性质分类3")
	private String jkrxzfl3;
	/**企业规模*/
	@Excel(name = "企业规模", width = 15)
    @ApiModelProperty(value = "企业规模")
	private String qygm;
	/**企业规模1*/
	@Excel(name = "企业规模1", width = 15)
    @ApiModelProperty(value = "企业规模1")
	private String qygm1;
	/**贷款品种*/
	@Excel(name = "贷款品种", width = 15,dicCode = "ywpz")
    @ApiModelProperty(value = "贷款品种")
	@Dict(dicCode = "ywpz")
	private String dkpz;
	/**期限分类*/
	@Excel(name = "期限分类", width = 15)
    @ApiModelProperty(value = "期限分类")
	private String qxfl;
	/**担保方式*/
	@Excel(name = "担保方式", width = 15,dicCode = "dbfs")
    @ApiModelProperty(value = "担保方式")
	@Dict(dicCode = "dbfs")
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
	/**贷款其他类型1*/
	@Excel(name = "贷款其他类型1", width = 15)
    @ApiModelProperty(value = "贷款其他类型1")
	private String dkqtlx1;
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
	/**固定资产类型4(面积)*/
	@Excel(name = "固定资产类型4(面积)", width = 15)
    @ApiModelProperty(value = "固定资产类型4(面积)")
	private String gdzclx4;
	/**借款人行业分类*/
	@Excel(name = "借款人行业分类", width = 15)
    @ApiModelProperty(value = "借款人行业分类")
	private String jkrhyfl;
	/**借款人行业分类1*/
	@Excel(name = "借款人行业分类1", width = 15)
    @ApiModelProperty(value = "借款人行业分类1")
	private String jkrhyfl1;
	/**借款人行业分类2*/
	@Excel(name = "借款人行业分类2", width = 15)
    @ApiModelProperty(value = "借款人行业分类2")
	private String jkrhyfl2;
	/**借款人行业分类3*/
	@Excel(name = "借款人行业分类3", width = 15)
    @ApiModelProperty(value = "借款人行业分类3")
	private String jkrhyfl3;
	/**利率类型*/
	@Excel(name = "利率类型", width = 15)
    @ApiModelProperty(value = "利率类型")
	private String lvlx;
	/**启用LPR*/
	@Excel(name = "启用LPR", width = 15)
    @ApiModelProperty(value = "启用LPR")
	private String qylpr;
	/**基点（BP）*/
	@Excel(name = "基点（BP）", width = 15)
    @ApiModelProperty(value = "基点（BP）")
	private java.math.BigDecimal jdbp;
}

package org.cmms.modules.report.tzsjgl.nydkqmx.entity;

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
 * @Description: 手工台账_宁远_贷款全明细
 * @Author: jeecg-boot
 * @Date:   2023-10-31
 * @Version: V1.0
 */
@Data
@TableName("REP_TZGL_NY_DKQMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="REP_TZGL_NY_DKQMX对象", description="手工台账_宁远_贷款全明细")
public class RepTzglNyDkqmx {
    
	/**数据日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date sjrq;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构名称")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgmc;
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
	private String zczjh;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
	private Integer hth;
	/**合同额度*/
	@Excel(name = "合同额度", width = 15)
    @ApiModelProperty(value = "合同额度")
	private java.math.BigDecimal hted;
	/**贷款规模*/
	@Excel(name = "贷款规模", width = 15)
    @ApiModelProperty(value = "贷款规模")
	private String dkgm;
	/**对比贷款规模*/
	@Excel(name = "对比贷款规模", width = 15)
    @ApiModelProperty(value = "对比贷款规模")
	private String dbdkgm;
	/**小微企业*/
	@Excel(name = "小微企业", width = 15)
    @ApiModelProperty(value = "小微企业")
	private String xwqy;
	/**新放*/
	@Excel(name = "新放", width = 15)
    @ApiModelProperty(value = "新放")
	private String xf;
	/**按揭*/
	@Excel(name = "按揭", width = 15)
    @ApiModelProperty(value = "按揭")
	private String aj;
	/**商业用房*/
	@Excel(name = "商业用房", width = 15)
    @ApiModelProperty(value = "商业用房")
	private String syyf;
	/**到期天数*/
	@Excel(name = "到期天数", width = 15)
    @ApiModelProperty(value = "到期天数")
	private Integer dqts;
	/**融资担保*/
	@Excel(name = "融资担保", width = 15)
    @ApiModelProperty(value = "融资担保")
	private String rzdb;
	/**贷款投向0*/
	@Excel(name = "贷款投向0", width = 15)
    @ApiModelProperty(value = "贷款投向0")
	private String dktx0;
	/**经营性*/
	@Excel(name = "经营性", width = 15)
    @ApiModelProperty(value = "经营性")
	private String jyx;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**本金余额*/
	@Excel(name = "本金余额", width = 15)
    @ApiModelProperty(value = "本金余额")
	private java.math.BigDecimal bjye;
	/**贷款日期*/
	@Excel(name = "贷款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "贷款日期")
	private Date dkrq;
	/**展期日期*/
	@Excel(name = "展期日期", width = 15)
    @ApiModelProperty(value = "展期日期")
	private String zqrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private Date dqrq;
	/**上次结息日*/
	@Excel(name = "上次结息日", width = 15)
    @ApiModelProperty(value = "上次结息日")
	private String scjxr;
	/**年化利息收益*/
	@Excel(name = "年化利息收益", width = 15)
    @ApiModelProperty(value = "年化利息收益")
	private java.math.BigDecimal nhlxsy;
	/**贷款到期前利率*/
	@Excel(name = "贷款到期前利率", width = 15)
    @ApiModelProperty(value = "贷款到期前利率")
	private java.math.BigDecimal dkdqqll;
	/**五级分类状态*/
	@Excel(name = "五级分类状态", width = 15)
    @ApiModelProperty(value = "五级分类状态")
	private String wjflzt;
	/**年初五级分类*/
	@Excel(name = "年初五级分类", width = 15)
    @ApiModelProperty(value = "年初五级分类")
	private String ncwjfl;
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
	private String dyzzr;
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
	@Excel(name = "贷款品种", width = 15)
    @ApiModelProperty(value = "贷款品种")
	private String dkpz;
	/**期限分类*/
	@Excel(name = "期限分类", width = 15)
    @ApiModelProperty(value = "期限分类")
	private String qxfl;
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
	/**固定资产类型4*/
	@Excel(name = "固定资产类型4", width = 15)
    @ApiModelProperty(value = "固定资产类型4")
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
	private String lllx;
	/**统计日期*/
	@Excel(name = "统计日期", width = 15)
    @ApiModelProperty(value = "统计日期")
	private Integer tjrq;
	/**是否职工*/
	@Excel(name = "是否职工", width = 15)
    @ApiModelProperty(value = "是否职工")
	private String sfzg;
	/**农户经营性*/
	@Excel(name = "农户经营性", width = 15)
    @ApiModelProperty(value = "农户经营性")
	private String nhjyx;
	/**普惠涉农*/
	@Excel(name = "普惠涉农", width = 15)
    @ApiModelProperty(value = "普惠涉农")
	private String phsn;
	/**涉农*/
	@Excel(name = "涉农", width = 15)
    @ApiModelProperty(value = "涉农")
	private String sn;
}

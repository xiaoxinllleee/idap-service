package org.cmms.modules.report.tzsjgl.sgtzgrsyyfaj.entity;

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
 * @Description: 个人商业用房按揭
 * @Author: jeecg-boot
 * @Date:   2023-03-07
 * @Version: V1.0
 */
@Data
@TableName("rep_tzgl_xt_grsyyfaj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rep_tzgl_xt_grsyyfaj对象", description="个人商业用房按揭")
public class SgtzGrsyyfaj {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15,format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据日期")
	private java.util.Date sjrq;
	/**机构名称*/
	@Excel(name = "支行", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "支行")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**贷款帐号*/
	@Excel(name = "贷款帐号", width = 15)
    @ApiModelProperty(value = "贷款帐号")
	private String dkzh;
	/**客户名称*/
	@Excel(name = "借款人名称", width = 15)
    @ApiModelProperty(value = "借款人名称")
	private String khmz;
	/**注册证件号*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zczjhm;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**本金余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal bjye;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
	private Integer nl;
	/**逾期天数*/
	@Excel(name = "逾期天数", width = 15)
	@ApiModelProperty(value = "逾期天数")
	private java.math.BigDecimal yqts;
	/**贷款日期*/
	@Excel(name = "贷款日期", width = 15,format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "贷款日期")
	private java.util.Date dkrq;
	/**展期日期*/
	@Excel(name = "展期日期", width = 15,format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "展期日期")
	private java.util.Date zqrq;
	/**到期日期*/
	@Excel(name = "贷款到期日", width = 15,format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "贷款到期日")
	private java.util.Date dqrq;
	/**上次结息日*/
	@Excel(name = "上次结息日", width = 15,format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "上次结息日")
	private java.util.Date scjxq;
	/**贷款年利率%*/
	@Excel(name = "贷款利率", width = 15)
    @ApiModelProperty(value = "贷款利率")
	private java.lang.String dknll;

	@Excel(name = "年利率", width = 15)
    @ApiModelProperty(value = "年利率")
	private java.math.BigDecimal nll;

	@Excel(name = "年化收益", width = 15)
    @ApiModelProperty(value = "年化收益")
	private java.math.BigDecimal nhsy;

	/**五级分类状态*/
	@Excel(name = "五级形态", width = 15)
    @ApiModelProperty(value = "五级形态")
	private java.lang.String wjfl;

	/**客户经理*/
	@Excel(name = "客户经理", width = 15)
    @ApiModelProperty(value = "客户经理")
	private java.lang.String khjl;
	/**主客户经理*/
	@Excel(name = "主客户经理", width = 15)
    @ApiModelProperty(value = "主客户经理 ")
	private java.lang.String zkhjl;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15)
    @ApiModelProperty(value = "第一责任人")
	private java.lang.String dyzrr;
	/**行政村组*/
	@Excel(name = "行政村组", width = 15)
    @ApiModelProperty(value = "行政村组")
	private java.lang.String xzcz;
	/**详细地址*/
	@Excel(name = "客户详细地址", width = 15)
    @ApiModelProperty(value = "客户详细地址")
	private java.lang.String xxdz;
	/**联系电话*/
	@Excel(name = "客户联系方式", width = 15)
    @ApiModelProperty(value = "客户联系方式")
	private java.lang.String lxdh;
	/**借款人性质分类*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	private java.lang.String jkrxzfl;
	/**借款人性质分类1*/
	@Excel(name = "客户类型1", width = 15)
    @ApiModelProperty(value = "客户类型1")
	private java.lang.String jkrxzfl1;
	/**借款人性质分类2*/
	@Excel(name = "客户类型2", width = 15)
    @ApiModelProperty(value = "客户类型2")
	private java.lang.String jkrxzfl2;
	/**借款人性质分类3*/
	@Excel(name = "客户类型3", width = 15)
    @ApiModelProperty(value = "客户类型3")
	private java.lang.String jkrxzfl3;
	/**企业规模*/
	@Excel(name = "企业规模", width = 15)
    @ApiModelProperty(value = "企业规模")
	private java.lang.String qygm;
	/**企业规模1*/
	@Excel(name = "企业规模1", width = 15)
    @ApiModelProperty(value = "企业规模1")
	private java.lang.String qygm1;
	/**贷款品种*/
	@Excel(name = "业务品种", width = 15)
    @ApiModelProperty(value = "业务品种")
	private java.lang.String dkpz;
	/**期限分类*/
	@Excel(name = "期限", width = 15)
    @ApiModelProperty(value = "期限")
	private java.lang.String qxfl;
	/**担保方式*/
	@Excel(name = "担保方式", width = 15)
    @ApiModelProperty(value = "担保方式")
	private java.lang.String dbfs;
	/**贷款投向*/
	@Excel(name = "贷款投向", width = 15)
    @ApiModelProperty(value = "贷款投向")
	private java.lang.String dktx;
	/**贷款投向1*/
	@Excel(name = "贷款投向1", width = 15)
    @ApiModelProperty(value = "贷款投向1")
	private java.lang.String dktx1;
	/**贷款投向2*/
	@Excel(name = "贷款投向2", width = 15)
    @ApiModelProperty(value = "贷款投向2")
	private java.lang.String dktx2;
	/**贷款投向3*/
	@Excel(name = "贷款投向3", width = 15)
    @ApiModelProperty(value = "贷款投向3")
	private java.lang.String dktx3;
	/**贷款投向4*/
	@Excel(name = "贷款投向4", width = 15)
    @ApiModelProperty(value = "贷款投向4")
	private java.lang.String dktx4;
	/**借款人行业分类*/
	@Excel(name = "借款人所属行业（门类）", width = 15)
    @ApiModelProperty(value = "借款人所属行业（门类）")
	private java.lang.String jkrhyfl;
	/**借款人行业分类1*/
	@Excel(name = "借款人所属行业（大类）", width = 15)
    @ApiModelProperty(value = "借款人所属行业（大类）")
	private java.lang.String jkrhyfl1;
	/**借款人行业分类2*/
	@Excel(name = "借款人所属行业（中类）", width = 15)
    @ApiModelProperty(value = "借款人所属行业（中类）")
	private java.lang.String jkrhyfl2;
	/**借款人行业分类3*/
	@Excel(name = "借款人所属行业（小类）", width = 15)
    @ApiModelProperty(value = "借款人所属行业（小类）")
	private java.lang.String jkrhyfl3;
	/**逾期日期*/
	@Excel(name = "逾期日期", width = 15)
    @ApiModelProperty(value = "逾期日期")
	private java.lang.String yqrq;
	/**借据号*/
	@Excel(name = "借据号", width = 15)
    @ApiModelProperty(value = "借据号")
	private java.lang.String jhj;
}

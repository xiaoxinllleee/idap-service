package org.cmms.modules.dkjkpt.dksjjk.dkjkptxdlfxzctz.entity;

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
 * @Description: 信贷类风险资产台账
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
@Data
@TableName("DKJKPT_XDLFXZCTZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DKJKPT_XDLFXZCTZ对象", description="信贷类风险资产台账")
public class DkjkptXdlfxzctz {

	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String xh;
	/**市州*/
	@Excel(name = "市州", width = 15)
    @ApiModelProperty(value = "市州")
	private String sz;
	/**农商银行*/
	@Excel(name = "农商银行", width = 15)
    @ApiModelProperty(value = "农商银行")
	private String nsyh;
	/**网点（支行）*/
	@Excel(name = "网点（支行）", width = 15,dicCode="YWJGDM",dictTable="HR_BAS_ORGANIZATION",dicText="ZZJC")
    @ApiModelProperty(value = "网点（支行）")
	@Dict(dicCode="YWJGDM",dictTable="HR_BAS_ORGANIZATION",dicText="ZZJC")
	private String wd;
	/**户名*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String hm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15)
    @ApiModelProperty(value = "借款日期")
	private String jkrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
    @ApiModelProperty(value = "到期日期")
	private String dqrq;
	/**贷款期限（月）*/
	@Excel(name = "贷款期限（月）", width = 15)
    @ApiModelProperty(value = "贷款期限（月）")
	private String dkqx;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**担保方式*/
	@Excel(name = "担保方式", width = 15,dicCode = "dbfs")
    @ApiModelProperty(value = "担保方式")
	@Dict(dicCode = "dbfs")
	private String dbfs;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15)
    @ApiModelProperty(value = "第一责任人")
	private String dyzrr;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	private String khlx;
	/**贷款投向*/
	@Excel(name = "贷款投向", width = 15)
    @ApiModelProperty(value = "贷款投向")
	private String dktx;
	/**贷款形态*/
	@Excel(name = "贷款形态", width = 15,dicCode = "dkxt")
    @ApiModelProperty(value = "贷款形态")
	@Dict(dicCode = "dkxt")
	private String dkxt;
	/**贷款类型*/
	@Excel(name = "贷款类型", width = 15)
    @ApiModelProperty(value = "贷款类型")
	private String dklx;
	/**利率（%）*/
	@Excel(name = "利率（%）", width = 15)
    @ApiModelProperty(value = "利率（%）")
	private java.math.BigDecimal ll;
	/**还款期限（月/季/年）*/
	@Excel(name = "还款期限（月/季/年）", width = 15)
    @ApiModelProperty(value = "还款期限（月/季/年）")
	private String hkqx;
	/**观察期贷款*/
	@Excel(name = "观察期贷款", width = 15)
    @ApiModelProperty(value = "观察期贷款")
	private String gcqdk;
	/**模拟观察期形态*/
	@Excel(name = "模拟观察期形态", width = 15,groupName = "观察期贷款")
    @ApiModelProperty(value = "模拟观察期形态")
	private String mngcqxt;
	/**出不良日期*/
	@Excel(name = "出不良日期", width = 15,groupName = "观察期贷款")
    @ApiModelProperty(value = "出不良日期")
	private String cblrq;
	/**观察期到期日*/
	@Excel(name = "观察期到期日", width = 15,groupName = "观察期贷款")
    @ApiModelProperty(value = "观察期到期日")
	private String gcqdqr;
	/**是否属于调减利息*/
	@Excel(name = "是否属于调减利息", width = 15,groupName = "调减利息")
    @ApiModelProperty(value = "是否属于调减利息")
	private String sfsytjlx;
	/**利息调整前*/
	@Excel(name = "利息调整前", width = 15,groupName = "调减利息")
    @ApiModelProperty(value = "利息调整前")
	private String lxtzq;
	/**利息调整后*/
	@Excel(name = "利息调整后", width = 15,groupName = "调减利息")
    @ApiModelProperty(value = "利息调整后")
	private String lxtzh;
	/**一年或两个还款周期内累计调减利息额度*/
	@Excel(name = "一年或两个还款周期内累计调减利息额度", width = 15,groupName = "调减利息")
    @ApiModelProperty(value = "一年或两个还款周期内累计调减利息额度")
	private String ljtjlxed;
	/**一年或两个还款周期内累计调减利息次数*/
	@Excel(name = "一年或两个还款周期内累计调减利息次数", width = 15,groupName = "调减利息")
    @ApiModelProperty(value = "一年或两个还款周期内累计调减利息次数")
	private String ljtjlxcs;
	/**利息调减日期*/
	@Excel(name = "利息调减日期", width = 15,groupName = "调减利息")
    @ApiModelProperty(value = "利息调减日期")
	private String lxtjrq;
	/**预计退出日期*/
	@Excel(name = "预计退出日期", width = 15,groupName = "调减利息")
    @ApiModelProperty(value = "预计退出日期")
	private String yqtcrq;
	/**是否属于延期付息*/
	@Excel(name = "是否属于延期付息", width = 15,groupName = "延期付息")
    @ApiModelProperty(value = "是否属于延期付息")
	private String sfsyyqfx;
	/**延期付息日期*/
	@Excel(name = "延期付息日期", width = 15,groupName = "延期付息")
    @ApiModelProperty(value = "延期付息日期")
	private String yqfxrq;
	/**延期付息天数*/
	@Excel(name = "延期付息天数", width = 15,groupName = "延期付息")
    @ApiModelProperty(value = "延期付息天数")
	private String yqfxts;
	/**延期到期后欠本日期*/
	@Excel(name = "延期到期后欠本日期", width = 15,groupName = "延期付息")
    @ApiModelProperty(value = "延期到期后欠本日期")
	private String yqdqhqbrq;
	/**延期到期后欠息日期*/
	@Excel(name = "延期到期后欠息日期", width = 15,groupName = "延期付息")
    @ApiModelProperty(value = "延期到期后欠息日期")
	private String yqdqhqxrq;
	/**是否属于低息贷款*/
	@Excel(name = "是否属于低息贷款", width = 15)
    @ApiModelProperty(value = "是否属于低息贷款")
	private String sfsydxdk;
	/**是否属于一户多形态*/
	@Excel(name = "是否属于一户多形态", width = 15,groupName = "一户多形态")
    @ApiModelProperty(value = "是否属于一户多形态")
	private String sfsyyhdxt;
	/**本人表外不良贷款余额*/
	@Excel(name = "本人表外不良贷款余额", width = 15,groupName = "一户多形态")
    @ApiModelProperty(value = "本人表外不良贷款余额")
	private java.math.BigDecimal brbwbldkye;
	/**本人表外不良贷款归属机构*/
	@Excel(name = "本人表外不良贷款归属机构", width = 15,groupName = "一户多形态")
    @ApiModelProperty(value = "本人表外不良贷款归属机构")
	private String brbwbldkgsjg;
	/**本人表内不良贷款余额*/
	@Excel(name = "本人表内不良贷款余额", width = 15,groupName = "一户多形态")
    @ApiModelProperty(value = "本人表内不良贷款余额")
	private java.math.BigDecimal brbnbldkye;
	/**本人表内不良贷款归属机构*/
	@Excel(name = "本人表内不良贷款归属机构", width = 15,groupName = "一户多形态")
    @ApiModelProperty(value = "本人表内不良贷款归属机构")
	private String brbnbldkgsjg;
	/**配偶表外不良贷款余额*/
	@Excel(name = "配偶表外不良贷款余额", width = 15,groupName = "一户多形态")
    @ApiModelProperty(value = "配偶表外不良贷款余额")
	private java.math.BigDecimal pobwbldkye;
	/**配偶表外不良贷款归属机构*/
	@Excel(name = "配偶表外不良贷款归属机构", width = 15,groupName = "一户多形态")
    @ApiModelProperty(value = "配偶表外不良贷款归属机构")
	private String pobwbldkgsjg;
	/**配偶表内不良贷款余额*/
	@Excel(name = "配偶表内不良贷款余额", width = 15,groupName = "一户多形态")
    @ApiModelProperty(value = "配偶表内不良贷款余额")
	private java.math.BigDecimal pobnbldkye;
	/**配偶表内不良贷款归属机构*/
	@Excel(name = "配偶表内不良贷款归属机构", width = 15,groupName = "一户多形态")
    @ApiModelProperty(value = "配偶表内不良贷款归属机构")
	private String pobnbldkgsjg;
	/**是否逾期贷款*/
	@Excel(name = "是否逾期贷款", width = 15,groupName = "是否逾期贷款")
    @ApiModelProperty(value = "是否逾期贷款")
	private String sfyqdk;
	/**逾期天数*/
	@Excel(name = "逾期天数", width = 15,groupName = "是否逾期贷款")
    @ApiModelProperty(value = "逾期天数")
	private String yqts;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String sjrq;
}

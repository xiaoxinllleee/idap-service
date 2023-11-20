package org.cmms.modules.report.sgtzgl.zqhzdjb.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 手工台账-债券汇总登记簿
 * @Author: jeecg-boot
 * @Date:   2023-08-07
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_zqhzdjb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_zqhzdjb对象", description="手工台账-债券汇总登记簿")
public class SgtzglZqhzdjb {

	/**会计日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String fiscalDate;
	/**序号*/
	@Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号")
	private String xh;
	/**申请编号*/
	@Excel(name = "申请编号", width = 15)
	@ApiModelProperty(value = "申请编号")
	private String sqbh;
	/**内部账号*/
	@Excel(name = "内部账号", width = 15)
	@ApiModelProperty(value = "内部账号")
	private String nbzh;
	/**成交编号*/
	@Excel(name = "成交编号", width = 15)
	@ApiModelProperty(value = "成交编号")
	private String cjbh;
	/**品种*/
	@Excel(name = "品种", width = 15)
	@ApiModelProperty(value = "品种")
	private String pz;
	/**债券（存单）代码*/
	@Excel(name = "债券（存单）代码", width = 15)
	@ApiModelProperty(value = "债券（存单）代码")
	private String dm;
	/**债券（存单）简称*/
	@Excel(name = "债券（存单）简称", width = 15)
	@ApiModelProperty(value = "债券（存单）简称")
	private String jc;
	/**发行主体*/
	@Excel(name = "发行主体", width = 15)
	@ApiModelProperty(value = "发行主体")
	private String fxzt;
	/**是否含权*/
	@Excel(name = "是否含权", width = 15)
	@ApiModelProperty(value = "是否含权")
	private String sfhq;
	/**期限*/
	@Excel(name = "期限(日）", width = 15)
	@ApiModelProperty(value = "期限(日）")
	private String qx;
	/**起息日*/
	@Excel(name = "起息", width = 15)
	@ApiModelProperty(value = "起息")
	private String qxr;
	/**到期日*/
	@Excel(name = "到期", width = 15)
	@ApiModelProperty(value = "到期")
	private String dqx;
	/**起息日*/
	@Excel(name = "起息1", width = 15)
	@ApiModelProperty(value = "起息")
	private String qxr1;
	/**到期日*/
	@Excel(name = "到期1", width = 15,format = "yyyy-MM-dd")
	@ApiModelProperty(value = "到期")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dqx1;
	/**计息方式*/
	@Excel(name = "计息方式", width = 15)
	@ApiModelProperty(value = "计息方式")
	private String jxfss;
	/**结息方式*/
	@Excel(name = "结息方式", width = 15)
	@ApiModelProperty(value = "结息方式")
	private String jxfs;
	/**买入时主体评级*/
	@Excel(name = "买入时主体评级", width = 15)
	@ApiModelProperty(value = "买入时主体评级")
	private String mrsztpj;
	/**买入时债项评级*/
	@Excel(name = "买入时债项评级", width = 15)
	@ApiModelProperty(value = "买入时债项评级")
	private String mrszxpj;
	/**购入日*/
	@Excel(name = "购入", width = 15)
	@ApiModelProperty(value = "购入")
	private String grr;
	/**购入日*/
	@Excel(name = "购入期", width = 15)
	@ApiModelProperty(value = "购入期")
	private String grr1;
	/**券面总额(元)*/
	@Excel(name = "券面总额(元)", width = 15)
	@ApiModelProperty(value = "券面总额(元)")
	private java.math.BigDecimal qmze;
	/**券面总额(万元)*/
	@Excel(name = "券面总额(万元)", width = 15)
	@ApiModelProperty(value = "券面总额(万元)")
	private java.math.BigDecimal qmze1;
	/**首次应计利息(元)*/
	@Excel(name = "首次应计利息(元)", width = 15)
	@ApiModelProperty(value = "首次应计利息(元)")
	private java.math.BigDecimal scyjlx;
	/**初始购入全价（元/百元）*/
	@Excel(name = "初始购入全价（元/百元）", width = 15)
	@ApiModelProperty(value = "初始购入全价（元/百元）")
	private java.math.BigDecimal csgrqj;
	/**初始购入净价（元/百元）*/
	@Excel(name = "初始购入净价（元/百元）", width = 15)
	@ApiModelProperty(value = "初始购入净价（元/百元）")
	private java.math.BigDecimal csgrjj;
	/**持有至到期收益率（%）*/
	@Excel(name = "持有至到期收益率（%）", width = 15)
	@ApiModelProperty(value = "持有至到期收益率（%）")
	private String cyzdqsyl;
	/**交易类型*/
	@Excel(name = "交易类型", width = 15)
	@ApiModelProperty(value = "交易类型")
	private String jylx;
	/**交易对手*/
	@Excel(name = "交易对手", width = 15)
	@ApiModelProperty(value = "交易对手")
	private String jyds;
	/**金融资产分类*/
	@Excel(name = "金融资产分类", width = 15)
	@ApiModelProperty(value = "金融资产分类")
	private String jrzcfl;
	/**账面余额*/
	@Excel(name = "账面余额", width = 15)
	@ApiModelProperty(value = "账面余额")
	private java.math.BigDecimal zmye;
	/**账面价值*/
	@Excel(name = "账面价值", width = 15)
	@ApiModelProperty(value = "账面价值")
	private java.math.BigDecimal zmjz;
	/**含减值余额*/
	@Excel(name = "含减值余额", width = 15)
	@ApiModelProperty(value = "含减值余额")
	private java.math.BigDecimal hjzye;
	/**含减值余额（万元）*/
	@Excel(name = "含减值余额（万元）", width = 15)
	@ApiModelProperty(value = "含减值余额（万元）")
	private java.math.BigDecimal hjzyewy;
	/**剩余面值*/
	@Excel(name = "剩余面值", width = 15)
	@ApiModelProperty(value = "剩余面值")
	private java.math.BigDecimal symz;
	/**公允价值变动余额（元）*/
	@Excel(name = "公允价值变动余额（元）", width = 15)
	@ApiModelProperty(value = "公允价值变动余额（元）")
	private java.math.BigDecimal gyjzbdye;
	/**利息调整余额(元)*/
	@Excel(name = "利息调整余额(元)", width = 15)
	@ApiModelProperty(value = "利息调整余额(元)")
	private java.math.BigDecimal lxtzye;
	/**应收利息余额（元）*/
	@Excel(name = "应收利息余额（元）", width = 15)
	@ApiModelProperty(value = "应收利息余额（元）")
	private java.math.BigDecimal yslxye;
	/**减值准备(元)*/
	@Excel(name = "减值准备(元)", width = 15)
	@ApiModelProperty(value = "减值准备(元)")
	private java.math.BigDecimal jzzb;
	/**当期票面（%）*/
	@Excel(name = "当期票面（%）", width = 15)
	@ApiModelProperty(value = "当期票面（%）")
	private String dqpm;
	/**剩余年限*/
	@Excel(name = "剩余年限", width = 15)
	@ApiModelProperty(value = "剩余年限")
	private java.math.BigDecimal synx;
	/**剩余月限*/
	@Excel(name = "剩余月限", width = 15)
	@ApiModelProperty(value = "剩余月限")
	private java.math.BigDecimal syyx;
	/**剩余天数*/
	@Excel(name = "剩余天数", width = 15)
	@ApiModelProperty(value = "剩余天数")
	private java.math.BigDecimal syts;
	/**实时主体评级*/
	@Excel(name = "实时主体评级", width = 15)
	@ApiModelProperty(value = "实时主体评级")
	private String ssztpj;
	/**实时债项评级*/
	@Excel(name = "实时债项评级", width = 15)
	@ApiModelProperty(value = "实时债项评级")
	private String sszxpj;
	/**净价估值（元/百元）*/
	@Excel(name = "净价估值（元/百元）", width = 15)
	@ApiModelProperty(value = "净价估值（元/百元）")
	private java.math.BigDecimal jjgz;
	/**估值市值*/
	@Excel(name = "估值市值", width = 15)
	@ApiModelProperty(value = "估值市值")
	private java.math.BigDecimal gzsz;
	/**浮动盈亏（元）*/
	@Excel(name = "浮动盈亏（元）", width = 15)
	@ApiModelProperty(value = "浮动盈亏（元）")
	private java.math.BigDecimal fdyk;
	/**浮动盈亏（万元）*/
	@Excel(name = "浮动盈亏（万元）", width = 15)
	@ApiModelProperty(value = "浮动盈亏（万元）")
	private java.math.BigDecimal fdykwy;
	/**浮动盈亏比例（%）*/
	@Excel(name = "浮动盈亏比例（%）", width = 15)
	@ApiModelProperty(value = "浮动盈亏比例（%）")
	private String fdykbl;
	/**部分卖出金额(元)*/
	@Excel(name = "部分卖出金额(元)", width = 15)
	@ApiModelProperty(value = "部分卖出金额(元)")
	private java.math.BigDecimal bfmcje;
	/**部分卖出利息(元)*/
	@Excel(name = "部分卖出利息(元)", width = 15)
	@ApiModelProperty(value = "部分卖出利息(元)")
	private java.math.BigDecimal bfmclx;
	/**累计结息金额(元)*/
	@Excel(name = "累计结息金额(元)", width = 15)
	@ApiModelProperty(value = "累计结息金额(元)")
	private java.math.BigDecimal ljjxje;
	/**业务状态*/
	@Excel(name = "业务状态", width = 15)
	@ApiModelProperty(value = "业务状态")
	private String ywzt;
	/**五级分类*/
	@Excel(name = "五级分类", width = 15)
	@ApiModelProperty(value = "五级分类")
	private String wjfl;
	/**特殊标识*/
	@Excel(name = "特殊标识", width = 15)
	@ApiModelProperty(value = "特殊标识")
	private String tsbz;
	/**投组*/
	@Excel(name = "投组", width = 15)
	@ApiModelProperty(value = "投组")
	private String tz;
	/**托管机构*/
	@Excel(name = "托管机构", width = 15)
	@ApiModelProperty(value = "托管机构")
	private String tgjg;
	/**机构号*/
	@Excel(name = "机构号", width = 15)
	@ApiModelProperty(value = "机构号")
	private String jgh;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
	@ApiModelProperty(value = "机构名称")
	private String jgmc;
	/**实收利息(元)*/
	@Excel(name = "实收利息(元)", width = 15)
	@ApiModelProperty(value = "实收利息(元)")
	private java.math.BigDecimal sslx;
	/**资产等级*/
	@Excel(name = "资产等级", width = 15)
	@ApiModelProperty(value = "资产等级")
	private String zcdj;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date createTime;
}

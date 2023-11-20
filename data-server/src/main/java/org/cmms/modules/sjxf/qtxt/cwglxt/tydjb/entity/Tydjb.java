package org.cmms.modules.sjxf.qtxt.cwglxt.tydjb.entity;

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
 * @Description: 同业登记簿
 * @Author: jeecg-boot
 * @Date:   2021-12-14
 * @Version: V1.0
 */
@Data
@TableName("Ebss_industry_reg")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_industry_reg对象", description="同业登记簿")
public class Tydjb {

	/**审批编号*/
	@Excel(name = "审批编号", width = 15)
    @ApiModelProperty(value = "审批编号")
	private String appNo;
	/**成交单编号*/
	@Excel(name = "成交单编号", width = 15)
    @ApiModelProperty(value = "成交单编号")
	private String contractNo;
	/**交易笔次*/
	@Excel(name = "交易笔次", width = 15)
    @ApiModelProperty(value = "交易笔次")
	private String txCnt;
	/**交易类型*/
	@Excel(name = "交易类型", width = 15)
    @ApiModelProperty(value = "交易类型")
	private String tradeType;
	/**交易对手编号*/
	@Excel(name = "交易对手编号", width = 15)
    @ApiModelProperty(value = "交易对手编号")
	private String ctpyNo;
	/**交易对手版本号*/
	@Excel(name = "交易对手版本号", width = 15)
    @ApiModelProperty(value = "交易对手版本号")
	private String version;
	/**交易对手名称*/
	@Excel(name = "交易对手名称", width = 15)
    @ApiModelProperty(value = "交易对手名称")
	private String ctpyName;
	/**资金账户户名*/
	@Excel(name = "资金账户户名", width = 15)
    @ApiModelProperty(value = "资金账户户名")
	private String accountName;
	/**资金开户行名*/
	@Excel(name = "资金开户行名", width = 15)
    @ApiModelProperty(value = "资金开户行名")
	private String bankName;
	/**资金账号*/
	@Excel(name = "资金账号", width = 15)
    @ApiModelProperty(value = "资金账号")
	private String bankAcno;
	/**支付系统行号*/
	@Excel(name = "支付系统行号", width = 15)
    @ApiModelProperty(value = "支付系统行号")
	private String bankNo;
	/**同业申请金额(元)*/
	@Excel(name = "同业申请金额(元)", width = 15)
    @ApiModelProperty(value = "同业申请金额(元)")
	private java.math.BigDecimal industryAmt;
	/**起息日*/
	@Excel(name = "起息日", width = 15)
    @ApiModelProperty(value = "起息日")
	private String icDate;
	/**期限(天)*/
	@Excel(name = "期限(天)", width = 15)
    @ApiModelProperty(value = "期限(天)")
	private String term;
	/**到期日*/
	@Excel(name = "到期日", width = 15)
    @ApiModelProperty(value = "到期日")
	private String mtrDate;
	/**利率(%)*/
	@Excel(name = "利率(%)", width = 15)
    @ApiModelProperty(value = "利率(%)")
	private java.math.BigDecimal rate;
	/**逾期利率*/
	@Excel(name = "逾期利率", width = 15)
    @ApiModelProperty(value = "逾期利率")
	private java.math.BigDecimal overRate;
	/**业务类型*/
	@Excel(name = "业务类型", width = 15)
    @ApiModelProperty(value = "业务类型")
	private String ywNo;
	/**利率性质*/
	@Excel(name = "利率性质", width = 15)
    @ApiModelProperty(value = "利率性质")
	private String rateProperty;
	/**利率类型*/
	@Excel(name = "利率类型", width = 15)
    @ApiModelProperty(value = "利率类型")
	private String rateType;
	/**日利率计算方式*/
	@Excel(name = "日利率计算方式", width = 15)
    @ApiModelProperty(value = "日利率计算方式")
	private String dateRateNum;
	/**利息结算方式*/
	@Excel(name = "利息结算方式", width = 15)
    @ApiModelProperty(value = "利息结算方式")
	private String lxjsType;
	/**利息结算周期*/
	@Excel(name = "利息结算周期", width = 15)
    @ApiModelProperty(value = "利息结算周期")
	private String lxjsCyc;
	/**浮动利率变动周期*/
	@Excel(name = "浮动利率变动周期", width = 15)
    @ApiModelProperty(value = "浮动利率变动周期")
	private String fdllCyc;
	/**计提利息周期*/
	@Excel(name = "计提利息周期", width = 15)
    @ApiModelProperty(value = "计提利息周期")
	private String jtlxCyc;
	/**计提利息方式*/
	@Excel(name = "计提利息方式", width = 15)
    @ApiModelProperty(value = "计提利息方式")
	private String jtlxType;
	/**上次计提日期*/
	@Excel(name = "上次计提日期", width = 15)
    @ApiModelProperty(value = "上次计提日期")
	private String lJtDate;
	/**是否允许提前提取*/
	@Excel(name = "是否允许提前提取", width = 15)
    @ApiModelProperty(value = "是否允许提前提取")
	private String tqzqType;
	/**提前支取利息来源*/
	@Excel(name = "提前支取利息来源", width = 15)
    @ApiModelProperty(value = "提前支取利息来源")
	private String tqzqRateSour;
	/**提前支取利率(%)*/
	@Excel(name = "提前支取利率(%)", width = 15)
    @ApiModelProperty(value = "提前支取利率(%)")
	private java.math.BigDecimal tqzqRate;
	/**提前支取利率生效日*/
	@Excel(name = "提前支取利率生效日", width = 15)
    @ApiModelProperty(value = "提前支取利率生效日")
	private String tqzqBeginDate;
	/**是否到期转存*/
	@Excel(name = "是否到期转存", width = 15)
    @ApiModelProperty(value = "是否到期转存")
	private String isDeposit;
	/**到期转存方式*/
	@Excel(name = "到期转存方式", width = 15)
    @ApiModelProperty(value = "到期转存方式")
	private String depositType;
	/**转存操作类型*/
	@Excel(name = "转存操作类型", width = 15)
    @ApiModelProperty(value = "转存操作类型")
	private String depositDotype;
	/**到期申请编号*/
	@Excel(name = "到期申请编号", width = 15)
    @ApiModelProperty(value = "到期申请编号")
	private String dappNo;
	/**转存新增金额(元)*/
	@Excel(name = "转存新增金额(元)", width = 15)
    @ApiModelProperty(value = "转存新增金额(元)")
	private java.math.BigDecimal addAmt;
	/**内部帐AC_NO*/
	@Excel(name = "内部帐AC_NO", width = 15)
    @ApiModelProperty(value = "内部帐AC_NO")
	private String acNo;
	/**内部帐AC_ID*/
	@Excel(name = "内部帐AC_ID", width = 15)
    @ApiModelProperty(value = "内部帐AC_ID")
	private String acId;
	/**五级分类结果*/
	@Excel(name = "五级分类结果", width = 15)
    @ApiModelProperty(value = "五级分类结果")
	private String lvlFive;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String sts;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
    @ApiModelProperty(value = "所属部门")
	private String depNo;
	/**所属账务机构*/
	@Excel(name = "所属账务机构", width = 15)
    @ApiModelProperty(value = "所属账务机构")
	private String brNo;
	/**所属联社号*/
	@Excel(name = "所属联社号", width = 15)
    @ApiModelProperty(value = "所属联社号")
	private String upBrNo;
	/**计划编号*/
	@Excel(name = "计划编号", width = 15)
    @ApiModelProperty(value = "计划编号")
	private String planNo;
	/**计划日期*/
	@Excel(name = "计划日期", width = 15)
    @ApiModelProperty(value = "计划日期")
	private String chnlInd;
	/**提前支取利率类型*/
	@Excel(name = "提前支取利率类型", width = 15)
    @ApiModelProperty(value = "提前支取利率类型")
	private String tqzqRateType;
	/**提前支取日利率计算方式*/
	@Excel(name = "提前支取日利率计算方式", width = 15)
    @ApiModelProperty(value = "提前支取日利率计算方式")
	private String tqzqDateRateNum;
	/**本方经办人*/
	@Excel(name = "本方经办人", width = 15)
    @ApiModelProperty(value = "本方经办人")
	private String trader;
	/**对方经办人*/
	@Excel(name = "对方经办人", width = 15)
    @ApiModelProperty(value = "对方经办人")
	private String ctpyTrader;
	/**摘要*/
	@Excel(name = "摘要", width = 15)
    @ApiModelProperty(value = "摘要")
	private String brf;
	/**定活标志*/
	@Excel(name = "定活标志", width = 15)
    @ApiModelProperty(value = "定活标志")
	private String dhType;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String curNo;
	/**本方编号*/
	@Excel(name = "本方编号", width = 15)
    @ApiModelProperty(value = "本方编号")
	private String bctpyNo;
	/**本方版本号*/
	@Excel(name = "本方版本号", width = 15)
    @ApiModelProperty(value = "本方版本号")
	private String bversion;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
	/**天入库表编号-对不同的表名唯一*/
	/*@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;
	*//**dttime*//*
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}

package org.cmms.modules.sjxf.qtxt.cwglxt.zjgcdjb.entity;

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
 * @Description: 在建工程登记簿
 * @Author: jeecg-boot
 * @Date:   2021-12-14
 * @Version: V1.0
 */
@Data
@TableName("Ebss_project_mst")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_project_mst对象", description="在建工程登记簿")
public class Zjgcdjb {
    
	/**在建工程立项编号*/
	@Excel(name = "在建工程立项编号", width = 15)
    @ApiModelProperty(value = "在建工程立项编号")
	private String projectAppNo;
	/**工程编号（卡片代号）*/
	@Excel(name = "工程编号（卡片代号）", width = 15)
    @ApiModelProperty(value = "工程编号（卡片代号）")
	private String projectNo;
	/**工程名称*/
	@Excel(name = "工程名称", width = 15)
    @ApiModelProperty(value = "工程名称")
	private String projectName;
	/**登记日期*/
	@Excel(name = "登记日期", width = 15)
    @ApiModelProperty(value = "登记日期")
	private Integer openDate;
	/**工程分摊项目数*/
	@Excel(name = "工程分摊项目数", width = 15)
    @ApiModelProperty(value = "工程分摊项目数")
	private Integer appCnt;
	/**总成本*/
	@Excel(name = "总成本", width = 15)
    @ApiModelProperty(value = "总成本")
	private java.math.BigDecimal totCost;
	/**总预算成本*/
	@Excel(name = "总预算成本", width = 15)
    @ApiModelProperty(value = "总预算成本")
	private java.math.BigDecimal totBal;
	/**在建工程类型*/
	@Excel(name = "在建工程类型", width = 15)
    @ApiModelProperty(value = "在建工程类型")
	private String projectKnd;
	/**取得方式*/
	@Excel(name = "取得方式", width = 15)
    @ApiModelProperty(value = "取得方式")
	private String rootIn;
	/**其他费用*/
	@Excel(name = "其他费用", width = 15)
    @ApiModelProperty(value = "其他费用")
	private java.math.BigDecimal feeAmt;
	/**分期付款次数*/
	@Excel(name = "分期付款次数", width = 15)
    @ApiModelProperty(value = "分期付款次数")
	private Integer payTerm;
	/**已付分期数*/
	@Excel(name = "已付分期数", width = 15)
    @ApiModelProperty(value = "已付分期数")
	private Integer useTerm;
	/**首付*/
	@Excel(name = "首付", width = 15)
    @ApiModelProperty(value = "首付")
	private java.math.BigDecimal firstPay;
	/**分期付款额*/
	@Excel(name = "分期付款额", width = 15)
    @ApiModelProperty(value = "分期付款额")
	private java.math.BigDecimal instalments;
	/**购买价的现值*/
	@Excel(name = "购买价的现值", width = 15)
    @ApiModelProperty(value = "购买价的现值")
	private java.math.BigDecimal nowBal;
	/**确认的融资费用*/
	@Excel(name = "确认的融资费用", width = 15)
    @ApiModelProperty(value = "确认的融资费用")
	private java.math.BigDecimal knowBal;
	/**未确认的融资金额*/
	@Excel(name = "未确认的融资金额", width = 15)
    @ApiModelProperty(value = "未确认的融资金额")
	private java.math.BigDecimal unknowBal;
	/**应付本金减少额*/
	@Excel(name = "应付本金减少额", width = 15)
    @ApiModelProperty(value = "应付本金减少额")
	private java.math.BigDecimal payBal;
	/**应付本金余额*/
	@Excel(name = "应付本金余额", width = 15)
    @ApiModelProperty(value = "应付本金余额")
	private java.math.BigDecimal unPayBal;
	/**折现率*/
	@Excel(name = "折现率", width = 15)
    @ApiModelProperty(value = "折现率")
	private java.math.BigDecimal leaveRate;
	/**实际利率(%)*/
	@Excel(name = "实际利率(%)", width = 15)
    @ApiModelProperty(value = "实际利率(%)")
	private java.math.BigDecimal actRate;
	/**上次减值额*/
	@Excel(name = "上次减值额", width = 15)
    @ApiModelProperty(value = "上次减值额")
	private java.math.BigDecimal evedepamt;
	/**上次交易日*/
	@Excel(name = "上次交易日", width = 15)
    @ApiModelProperty(value = "上次交易日")
	private Integer lstDate;
	/**今日交易笔数*/
	@Excel(name = "今日交易笔数", width = 15)
    @ApiModelProperty(value = "今日交易笔数")
	private Integer tdCnt;
	/**减值金额*/
	@Excel(name = "减值金额", width = 15)
    @ApiModelProperty(value = "减值金额")
	private java.math.BigDecimal devalue;
	/**分期周期*/
	@Excel(name = "分期周期", width = 15)
    @ApiModelProperty(value = "分期周期")
	private String projectCyc;
	/**操作员号*/
	@Excel(name = "操作员号", width = 15)
    @ApiModelProperty(value = "操作员号")
	private String tel;
	/**开户机构号*/
	@Excel(name = "开户机构号", width = 15)
    @ApiModelProperty(value = "开户机构号")
	private String opnBrNo;
	/**工程状态*/
	@Excel(name = "工程状态", width = 15)
    @ApiModelProperty(value = "工程状态")
	private String projectSts;
	/**地点*/
	@Excel(name = "地点", width = 15)
    @ApiModelProperty(value = "地点")
	private String addr;
	/**盘盈盘亏金额*/
	@Excel(name = "盘盈盘亏金额", width = 15)
    @ApiModelProperty(value = "盘盈盘亏金额")
	private java.math.BigDecimal profitLostBal;
	/**分期付款方法*/
	@Excel(name = "分期付款方法", width = 15)
    @ApiModelProperty(value = "分期付款方法")
	private String instalmentsType;
	/**完工日期*/
	@Excel(name = "完工日期", width = 15)
    @ApiModelProperty(value = "完工日期")
	private Integer finishDay;
	/**合同笔数*/
	@Excel(name = "合同笔数", width = 15)
    @ApiModelProperty(value = "合同笔数")
	private Integer loanCnt;
	/**启用日期*/
	@Excel(name = "启用日期", width = 15)
    @ApiModelProperty(value = "启用日期")
	private Integer useDate;
	/**摘要*/
	@Excel(name = "摘要", width = 15)
    @ApiModelProperty(value = "摘要")
	private String brf;
	/**增加批准文号*/
	@Excel(name = "增加批准文号", width = 15)
    @ApiModelProperty(value = "增加批准文号")
	private String addAuthNo;
	/**减少批准文号*/
	@Excel(name = "减少批准文号", width = 15)
    @ApiModelProperty(value = "减少批准文号")
	private String redAuthNo;
	/**注销日期*/
	@Excel(name = "注销日期", width = 15)
    @ApiModelProperty(value = "注销日期")
	private Integer redDate;
	/**合同编号*/
	@Excel(name = "合同编号", width = 15)
    @ApiModelProperty(value = "合同编号")
	private String contractNo;
	/**资产类型*/
	@Excel(name = "资产类型", width = 15)
    @ApiModelProperty(value = "资产类型")
	private String capCode;
	/**所属事项编号*/
	@Excel(name = "所属事项编号", width = 15)
    @ApiModelProperty(value = "所属事项编号")
	private String projectCfmNo;
	/**工程总预算（暂未使用）*/
	@Excel(name = "工程总预算（暂未使用）", width = 15)
    @ApiModelProperty(value = "工程总预算（暂未使用）")
	private java.math.BigDecimal projectBudg;
	/**资金来源*/
	@Excel(name = "资金来源", width = 15)
    @ApiModelProperty(value = "资金来源")
	private String fundSrc;
	/**在建工程状况*/
	@Excel(name = "在建工程状况", width = 15)
    @ApiModelProperty(value = "在建工程状况")
	private String projectCond;
	/**在建工程账户*/
	@Excel(name = "在建工程账户", width = 15)
    @ApiModelProperty(value = "在建工程账户")
	private String proAcNo;
	/**记账标志*/
	@Excel(name = "记账标志", width = 15)
    @ApiModelProperty(value = "记账标志")
	private String jzFlag;
	/**事后确认子序号*/
	@Excel(name = "事后确认子序号", width = 15)
    @ApiModelProperty(value = "事后确认子序号")
	private Integer projectCfmSeqn;
	/**产品号*/
	@Excel(name = "产品号", width = 15)
    @ApiModelProperty(value = "产品号")
	private String prdtNo;
	/**原资产编号*/
	@Excel(name = "原资产编号", width = 15)
    @ApiModelProperty(value = "原资产编号")
	private String oCapNo;
	/**股东号*/
	@Excel(name = "股东号", width = 15)
    @ApiModelProperty(value = "股东号")
	private String shareholderNo;
	/**入股股数*/
	@Excel(name = "入股股数", width = 15)
    @ApiModelProperty(value = "入股股数")
	private java.math.BigDecimal shareSize;
	/**每股票面价格*/
	@Excel(name = "每股票面价格", width = 15)
    @ApiModelProperty(value = "每股票面价格")
	private java.math.BigDecimal sharePrice;
	/**资本溢价金额*/
	@Excel(name = "资本溢价金额", width = 15)
    @ApiModelProperty(value = "资本溢价金额")
	private java.math.BigDecimal premiumBal;
	/**财务流水号*/
	@Excel(name = "财务流水号", width = 15)
    @ApiModelProperty(value = "财务流水号")
	private Integer traceNo;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private Integer txDate;
	/**法人联社号*/
	@Excel(name = "法人联社号", width = 15)
    @ApiModelProperty(value = "法人联社号")
	private String upBrNo;
	/**初始取得资产编号*/
	@Excel(name = "初始取得资产编号", width = 15)
    @ApiModelProperty(value = "初始取得资产编号")
	private String srcCapNo;
	/**初始取得资产来源*/
	@Excel(name = "初始取得资产来源", width = 15)
    @ApiModelProperty(value = "初始取得资产来源")
	private String oRootIn;
	/**五级分类结果*/
	@Excel(name = "五级分类结果", width = 15)
    @ApiModelProperty(value = "五级分类结果")
	private String lvlFive;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String curNo;
	/**附件编号*/
	@Excel(name = "附件编号", width = 15)
    @ApiModelProperty(value = "附件编号")
	private String annexNo;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
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
	private Integer dtnum;*/
	/**dttime*/
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}

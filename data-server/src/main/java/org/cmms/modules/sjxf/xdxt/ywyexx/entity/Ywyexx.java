package org.cmms.modules.sjxf.xdxt.ywyexx.entity;

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
 * @Description: 业务余额信息
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
@Data
@TableName("Cms_businfo")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_businfo对象", description="业务余额信息")
public class Ywyexx {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String accNo;
	/**未收利息*/
	@Excel(name = "未收利息", width = 15)
    @ApiModelProperty(value = "未收利息")
	private java.math.BigDecimal accruedIuterest;
	/**贷款帐号/汇票号*/
	@Excel(name = "贷款帐号/汇票号", width = 15)
    @ApiModelProperty(value = "贷款帐号/汇票号")
	private String acctNo;
	/**实际还款金额*/
	@Excel(name = "实际还款金额", width = 15)
    @ApiModelProperty(value = "实际还款金额")
	private java.math.BigDecimal actualPayMonthSum;
	/**呆帐金额*/
	@Excel(name = "呆帐金额", width = 15)
    @ApiModelProperty(value = "呆帐金额")
	private java.math.BigDecimal badbal;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal balance;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "业务编号")
	private String businessNo;
	/**交易编码*/
	@Excel(name = "交易编码", width = 15)
    @ApiModelProperty(value = "交易编码")
	private String businessPhase;
	/**贷款类别*/
	@Excel(name = "贷款类别", width = 15)
    @ApiModelProperty(value = "贷款类别")
	private String businessType;
	/**冲销标志*/
	@Excel(name = "冲销标志", width = 15)
    @ApiModelProperty(value = "冲销标志")
	private String cancelFlag;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String currency;
	/**信贷客户号*/
	@Excel(name = "信贷客户号", width = 15)
    @ApiModelProperty(value = "信贷客户号")
	private String custId;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String custName;
	/**核心客户号*/
	@Excel(name = "核心客户号", width = 15)
    @ApiModelProperty(value = "核心客户号")
	private String custNo;
	/**贷款合同号码*/
	@Excel(name = "贷款合同号码", width = 15)
    @ApiModelProperty(value = "贷款合同号码")
	private String dkhth;
	/**实收利息*/
	@Excel(name = "实收利息", width = 15)
    @ApiModelProperty(value = "实收利息")
	private java.math.BigDecimal dueLuterest;
	/**呆滞金额*/
	@Excel(name = "呆滞金额", width = 15)
    @ApiModelProperty(value = "呆滞金额")
	private java.math.BigDecimal dullbal;
	/**罚息金额*/
	@Excel(name = "罚息金额", width = 15)
    @ApiModelProperty(value = "罚息金额")
	private java.math.BigDecimal finebal;
	/**销户日期*/
	@Excel(name = "销户日期", width = 15)
    @ApiModelProperty(value = "销户日期")
	private String finishDate;
	/**放款人员*/
	@Excel(name = "放款人员", width = 15)
    @ApiModelProperty(value = "放款人员")
	private String fkId;
	/**获取数据时间*/
	@Excel(name = "获取数据时间", width = 15)
    @ApiModelProperty(value = "获取数据时间")
	private String getDate;
	/**收息账户余额*/
	@Excel(name = "收息账户余额", width = 15)
    @ApiModelProperty(value = "收息账户余额")
	private java.math.BigDecimal houldPayMonthSum;
	/**表内欠息余额*/
	@Excel(name = "表内欠息余额", width = 15)
    @ApiModelProperty(value = "表内欠息余额")
	private java.math.BigDecimal intbal1;
	/**表外欠息余额*/
	@Excel(name = "表外欠息余额", width = 15)
    @ApiModelProperty(value = "表外欠息余额")
	private java.math.BigDecimal intbal2;
	/**贷款类型0贷款1票据2贴现*/
	@Excel(name = "贷款类型0贷款1票据2贴现", width = 15)
    @ApiModelProperty(value = "贷款类型0贷款1票据2贴现")
	private String kind;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
    @ApiModelProperty(value = "到期日期")
	private String maturity;
	/**本月利息累收*/
	@Excel(name = "本月利息累收", width = 15)
    @ApiModelProperty(value = "本月利息累收")
	private java.math.BigDecimal monthIntbalPileAccept;
	/**本月本金累收*/
	@Excel(name = "本月本金累收", width = 15)
    @ApiModelProperty(value = "本月本金累收")
	private java.math.BigDecimal monthOwePileAccept;
	/**本月本金累放*/
	@Excel(name = "本月本金累放", width = 15)
    @ApiModelProperty(value = "本月本金累放")
	private java.math.BigDecimal monthOwePilePut;
	/**交易社网点号*/
	@Excel(name = "交易社网点号", width = 15)
    @ApiModelProperty(value = "交易社网点号")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String org;
	/**机构号*/
	@Excel(name = "机构号", width = 15)
    @ApiModelProperty(value = "机构号")
	private String orgId;
	/**逾期金额*/
	@Excel(name = "逾期金额", width = 15)
    @ApiModelProperty(value = "逾期金额")
	private java.math.BigDecimal overDuebal;
	/**逾期期数*/
	@Excel(name = "逾期期数", width = 15)
    @ApiModelProperty(value = "逾期期数")
	private String overDuebalTerm;
	/**欠本金额*/
	@Excel(name = "欠本金额", width = 15)
    @ApiModelProperty(value = "欠本金额")
	private java.math.BigDecimal oweBalance;
	/**结算账号*/
	@Excel(name = "结算账号", width = 15)
    @ApiModelProperty(value = "结算账号")
	private String payAcctno;
	/**还款月数*/
	@Excel(name = "还款月数", width = 15)
    @ApiModelProperty(value = "还款月数")
	private String payTermMonth;
	/**贷款日期*/
	@Excel(name = "贷款日期", width = 15)
    @ApiModelProperty(value = "贷款日期")
	private String putOutDate;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal putoutSum;
	/**利率*/
	@Excel(name = "利率", width = 15)
    @ApiModelProperty(value = "利率")
	private java.math.BigDecimal rate;
	/**本月应还款金额*/
	@Excel(name = "本月应还款金额", width = 15)
    @ApiModelProperty(value = "本月应还款金额")
	private java.math.BigDecimal shouldPayMonthSum;
	/**应还款期数*/
	@Excel(name = "应还款期数", width = 15)
    @ApiModelProperty(value = "应还款期数")
	private String shouldPayTerm;
	/**贷款期限*/
	@Excel(name = "贷款期限", width = 15)
    @ApiModelProperty(value = "贷款期限")
	private String term;
	/**借据号/汇票号*/
	@Excel(name = "借据号/汇票号", width = 15)
    @ApiModelProperty(value = "借据号/汇票号")
	private String voucherNo;
	/**本年利息累收*/
	@Excel(name = "本年利息累收", width = 15)
    @ApiModelProperty(value = "本年利息累收")
	private java.math.BigDecimal yearIntbalPileAccept;
	/**本年本金累收*/
	@Excel(name = "本年本金累收", width = 15)
    @ApiModelProperty(value = "本年本金累收")
	private java.math.BigDecimal yearOwePileAccept;
	/**本年本金累放*/
	@Excel(name = "本年本金累放", width = 15)
    @ApiModelProperty(value = "本年本金累放")
	private java.math.BigDecimal yearOwePilePut;
	/**逾期利率*/
	@Excel(name = "逾期利率", width = 15)
    @ApiModelProperty(value = "逾期利率")
	private java.math.BigDecimal yqll;
	/**罚息利率*/
	@Excel(name = "罚息利率", width = 15)
    @ApiModelProperty(value = "罚息利率")
	private java.math.BigDecimal fxll;
	/**展期利率*/
	@Excel(name = "展期利率", width = 15)
    @ApiModelProperty(value = "展期利率")
	private java.math.BigDecimal zqll;
	/**展期利率1*/
	@Excel(name = "展期利率1", width = 15)
    @ApiModelProperty(value = "展期利率1")
	private java.math.BigDecimal zqll1;
	/**展期金额*/
	@Excel(name = "展期金额", width = 15)
    @ApiModelProperty(value = "展期金额")
	private java.math.BigDecimal zqje;
	/**收息账号*/
	@Excel(name = "收息账号", width = 15)
    @ApiModelProperty(value = "收息账号")
	private String sxzh;
	/**挂息账号*/
	@Excel(name = "挂息账号", width = 15)
    @ApiModelProperty(value = "挂息账号")
	private String gxzh;
	/**表外账号*/
	@Excel(name = "表外账号", width = 15)
    @ApiModelProperty(value = "表外账号")
	private String bwzh;
	/**暂末用*/
	@Excel(name = "暂末用", width = 15)
    @ApiModelProperty(value = "暂末用")
	private String bjflh;
	/**应收利率累计*/
	@Excel(name = "应收利率累计", width = 15)
    @ApiModelProperty(value = "应收利率累计")
	private java.math.BigDecimal yslxlj;
	/**发放累计*/
	@Excel(name = "发放累计", width = 15)
    @ApiModelProperty(value = "发放累计")
	private java.math.BigDecimal fflj;
	/**回收累计*/
	@Excel(name = "回收累计", width = 15)
    @ApiModelProperty(value = "回收累计")
	private java.math.BigDecimal hslj;
	/**贷款用途(核心)*/
	@Excel(name = "贷款用途(核心)", width = 15)
    @ApiModelProperty(value = "贷款用途(核心)")
	private String dkyt;
	/**计账科目*/
	@Excel(name = "计账科目", width = 15)
    @ApiModelProperty(value = "计账科目")
	private String jzkm;
	/**起息日期*/
	@Excel(name = "起息日期", width = 15)
    @ApiModelProperty(value = "起息日期")
	private String qxrDate;
	/**四级分类状态*/
	@Excel(name = "四级分类状态", width = 15)
    @ApiModelProperty(value = "四级分类状态")
	private String fourClassState;
	/**核心借据号*/
	@Excel(name = "核心借据号", width = 15)
    @ApiModelProperty(value = "核心借据号")
	private String hxjjh;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String zjlx;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**记息标志*/
	@Excel(name = "记息标志", width = 15)
    @ApiModelProperty(value = "记息标志")
	private String jxbz;
	/**最近一次还款日期*/
	@Excel(name = "最近一次还款日期", width = 15)
    @ApiModelProperty(value = "最近一次还款日期")
	private String zjychkrq;
	/**担保方式*/
	@Excel(name = "担保方式", width = 15)
    @ApiModelProperty(value = "担保方式")
	private String dbfs;
	/**信贷证件类型*/
	@Excel(name = "信贷证件类型", width = 15)
    @ApiModelProperty(value = "信贷证件类型")
	private String xdzjlx;
	/**信贷证件号码*/
	@Excel(name = "信贷证件号码", width = 15)
    @ApiModelProperty(value = "信贷证件号码")
	private String xdzjhm;
	/**总余额*/
	@Excel(name = "总余额", width = 15)
    @ApiModelProperty(value = "总余额")
	private java.math.BigDecimal totleBalance;
	/**总减值金额*/
	@Excel(name = "总减值金额", width = 15)
    @ApiModelProperty(value = "总减值金额")
	private java.math.BigDecimal zjzje;
	/**减值标志*/
	@Excel(name = "减值标志", width = 15)
    @ApiModelProperty(value = "减值标志")
	private String jzbz;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15)
    @ApiModelProperty(value = "更新日期")
	private String updateDate;
	/**逾期罚息余额*/
	@Excel(name = "逾期罚息余额", width = 15)
    @ApiModelProperty(value = "逾期罚息余额")
	private java.math.BigDecimal fineBalance1;
	/**分期业务欠本金*/
	@Excel(name = "分期业务欠本金", width = 15)
    @ApiModelProperty(value = "分期业务欠本金")
	private java.math.BigDecimal taBalance;
	/**分期业务欠利息*/
	@Excel(name = "分期业务欠利息", width = 15)
    @ApiModelProperty(value = "分期业务欠利息")
	private java.math.BigDecimal taInterestBalance;
	/**累计欠款期数*/
	@Excel(name = "累计欠款期数", width = 15)
    @ApiModelProperty(value = "累计欠款期数")
	private String taTimes;
	/**连续欠款期数*/
	@Excel(name = "连续欠款期数", width = 15)
    @ApiModelProperty(value = "连续欠款期数")
	private String lcaTimes;
	/**展期次数*/
	@Excel(name = "展期次数", width = 15)
    @ApiModelProperty(value = "展期次数")
	private String extendTimes;
	/**垫款标志*/
	@Excel(name = "垫款标志", width = 15)
    @ApiModelProperty(value = "垫款标志")
	private String advanceFlag;
	/**还款频率*/
	@Excel(name = "还款频率", width = 15)
    @ApiModelProperty(value = "还款频率")
	private String termsFreq;
	/**业务状态*/
	@Excel(name = "业务状态", width = 15)
    @ApiModelProperty(value = "业务状态")
	private String businessStat;
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
	private Integer dtnum;*/
	/**dttime*/
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}

package org.cmms.modules.sjxf.hxxt.jycsb2.entity;

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
 * @Description: 交易参数表2
 * @Author: jeecg-boot
 * @Date:   2021-12-08
 * @Version: V1.0
 */
@Data
@TableName("Cbs_ed2p")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_ed2p对象", description="交易参数表2")
public class Jycsb2 {
    
	/**银行号*/
	@Excel(name = "银行号", width = 15)
    @ApiModelProperty(value = "银行号")
	private String instNo;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	private String tranCode;
	/**助记码*/
	@Excel(name = "助记码", width = 15)
    @ApiModelProperty(value = "助记码")
	private String mnemonic;
	/**现金总额*/
	@Excel(name = "现金总额", width = 15)
    @ApiModelProperty(value = "现金总额")
	private String cashTots;
	/**状态汇总*/
	@Excel(name = "状态汇总", width = 15)
    @ApiModelProperty(value = "状态汇总")
	private String stTots;
	/**查询代号*/
	@Excel(name = "查询代号", width = 15)
    @ApiModelProperty(value = "查询代号")
	private String enqCode;
	/**分类码代号*/
	@Excel(name = "分类码代号", width = 15)
    @ApiModelProperty(value = "分类码代号")
	private String ledgerCode;
	/**柜员级别*/
	@Excel(name = "柜员级别", width = 15)
    @ApiModelProperty(value = "柜员级别")
	private String txCap;
	/**交易描述*/
	@Excel(name = "交易描述", width = 15)
    @ApiModelProperty(value = "交易描述")
	private String txDesc;
	/**费用矩阵*/
	@Excel(name = "费用矩阵", width = 15)
    @ApiModelProperty(value = "费用矩阵")
	private String feeMask;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
	/**多货币统计*/
	@Excel(name = "多货币统计", width = 15)
    @ApiModelProperty(value = "多货币统计")
	private String mcurrStTots;
	/**本交易内部分行统计第一网点*/
	@Excel(name = "本交易内部分行统计第一网点", width = 15)
    @ApiModelProperty(value = "本交易内部分行统计第一网点")
	private String ibStTots;
	/**多货币内部分行统计*/
	@Excel(name = "多货币内部分行统计", width = 15)
    @ApiModelProperty(value = "多货币内部分行统计")
	private String mcIbStTots;
	/**有关本交易对应要求标识*/
	@Excel(name = "有关本交易对应要求标识", width = 15)
    @ApiModelProperty(value = "有关本交易对应要求标识")
	private String correspondenceReq;
	/**过账层级*/
	@Excel(name = "过账层级", width = 15)
    @ApiModelProperty(value = "过账层级")
	private String postingLevel;
	/**CGL组件1*/
	@Excel(name = "CGL组件1", width = 15)
    @ApiModelProperty(value = "CGL组件1")
	@TableField(value = "CGL_COMPONENT_1_SB")
	private String cglComponent1Sb;
	/**CGL组件2*/
	@Excel(name = "CGL组件2", width = 15)
    @ApiModelProperty(value = "CGL组件2")
	@TableField(value = "CGL_COMPONENT_2_SB")
	private String cglComponent2Sb;
	/**记账标识*/
	@Excel(name = "记账标识", width = 15)
    @ApiModelProperty(value = "记账标识")
	private String cglPostingIndSb;
	/**CGL组件1*/
	@Excel(name = "CGL组件1", width = 15)
    @ApiModelProperty(value = "CGL组件1")
	@TableField(value = "CGL_COMPONENT_1_CC")
	private String cglComponent1Cc;
	/**CGL组件2*/
	@Excel(name = "CGL组件2", width = 15)
    @ApiModelProperty(value = "CGL组件2")
	@TableField(value = "CGL_COMPONENT_2_CC")
	private String cglComponent2Cc;
	/**记账标识*/
	@Excel(name = "记账标识", width = 15)
    @ApiModelProperty(value = "记账标识")
	private String cglPostingIndCc;
	/**CGL组件1*/
	@Excel(name = "CGL组件1", width = 15)
    @ApiModelProperty(value = "CGL组件1")
	@TableField(value = "CGL_COMPONENT_1_IB")
	private String cglComponent1Ib;
	/**CGL组件2*/
	@Excel(name = "CGL组件2", width = 15)
    @ApiModelProperty(value = "CGL组件2")
	@TableField(value = "CGL_COMPONENT_2_IB")
	private String cglComponent2Ib;
	/**记账标识*/
	@Excel(name = "记账标识", width = 15)
    @ApiModelProperty(value = "记账标识")
	private String cglPostingIndIb;
	/**限制指示器*/
	@Excel(name = "限制指示器", width = 15)
    @ApiModelProperty(value = "限制指示器")
	private String cglQualifier;
	/**逆转符号*/
	@Excel(name = "逆转符号", width = 15)
    @ApiModelProperty(value = "逆转符号")
	private String cglSignReversal;
	/**报表解释序列号*/
	@Excel(name = "报表解释序列号", width = 15)
    @ApiModelProperty(value = "报表解释序列号")
	private Integer tcnarrSeqNo;
	/**报表解释交易类型*/
	@Excel(name = "报表解释交易类型", width = 15)
    @ApiModelProperty(value = "报表解释交易类型")
	private String narrTxtType;
	/**还款优先级*/
	@Excel(name = "还款优先级", width = 15)
    @ApiModelProperty(value = "还款优先级")
	private String priorityCode;
	/**夜间允许标志*/
	@Excel(name = "夜间允许标志", width = 15)
    @ApiModelProperty(value = "夜间允许标志")
	private String nightEnable;
	/**OFFSITE_BRH_FLG*/
	@Excel(name = "OFFSITE_BRH_FLG", width = 15)
    @ApiModelProperty(value = "OFFSITE_BRH_FLG")
	private String offsiteBrhFlg;
	/**是否允许跨机构交易*/
	@Excel(name = "是否允许跨机构交易", width = 15)
    @ApiModelProperty(value = "是否允许跨机构交易")
	private String ibAllowedFlag;
	/**SUPR_OVERRIDE_FLG*/
	@Excel(name = "SUPR_OVERRIDE_FLG", width = 15)
    @ApiModelProperty(value = "SUPR_OVERRIDE_FLG")
	private String suprOverrideFlg;
	/**凭证打印标志*/
	@Excel(name = "凭证打印标志", width = 15)
    @ApiModelProperty(value = "凭证打印标志")
	private String voucherReqInd;
	/**凭证格式1*/
	@Excel(name = "凭证格式1", width = 15)
    @ApiModelProperty(value = "凭证格式1")
	@TableField(value = "VOUCHER_FORM_1")
	private String voucherForm1;
	/**凭证格式2*/
	@Excel(name = "凭证格式2", width = 15)
    @ApiModelProperty(value = "凭证格式2")
	@TableField(value = "VOUCHER_FORM_2")
	private String voucherForm2;
	/**凭证输出代码*/
	@Excel(name = "凭证输出代码", width = 15)
    @ApiModelProperty(value = "凭证输出代码")
	private String vchrOutputCode;
	/**刷新标识*/
	@Excel(name = "刷新标识", width = 15)
    @ApiModelProperty(value = "刷新标识")
	private String refreshMask;
	/**交易描述的记忆码*/
	@Excel(name = "交易描述的记忆码", width = 15)
    @ApiModelProperty(value = "交易描述的记忆码")
	private String longMnemonic;
	/**跨机构标志*/
	@Excel(name = "跨机构标志", width = 15)
    @ApiModelProperty(value = "跨机构标志")
	private String interBranch;
	/**授权类型*/
	@Excel(name = "授权类型", width = 15)
    @ApiModelProperty(value = "授权类型")
	private String overriderType;
	/**交易金额限制标志*/
	@Excel(name = "交易金额限制标志", width = 15)
    @ApiModelProperty(value = "交易金额限制标志")
	private String txnAmtLimitInd;
	/**限制金额*/
	@Excel(name = "限制金额", width = 15)
    @ApiModelProperty(value = "限制金额")
	private java.math.BigDecimal txnLimitAmt;
	/**CARD_NO_REQD*/
	@Excel(name = "CARD_NO_REQD", width = 15)
    @ApiModelProperty(value = "CARD_NO_REQD")
	private String cardNoReqd;
	/**费用矩阵*/
	@Excel(name = "费用矩阵", width = 15)
    @ApiModelProperty(value = "费用矩阵")
	private String feeMatrix;
	/**自动/半自动收费标志*/
	@Excel(name = "自动/半自动收费标志", width = 15)
    @ApiModelProperty(value = "自动/半自动收费标志")
	private String autoInd;
	/**现金收费的账号*/
	@Excel(name = "现金收费的账号", width = 15)
    @ApiModelProperty(value = "现金收费的账号")
	private String semiCashBglacct;
	/**FEE_SUBMOD*/
	@Excel(name = "FEE_SUBMOD", width = 15)
    @ApiModelProperty(value = "FEE_SUBMOD")
	private String feeSubmod;
	/**反洗钱标志*/
	@Excel(name = "反洗钱标志", width = 15)
    @ApiModelProperty(value = "反洗钱标志")
	private String amlFlag;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
	private Date loadDate;
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

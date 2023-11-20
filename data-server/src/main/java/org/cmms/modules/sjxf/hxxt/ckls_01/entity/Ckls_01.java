package org.cmms.modules.sjxf.hxxt.ckls_01.entity;

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
 * @Description: 存款流水_01
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Data
@TableName("Cbs_inct_tran_01")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_inct_tran_01对象", description="存款流水_01")
public class Ckls_01 {

	/**机构号*/
	@Excel(name = "机构号", width = 15)
    @ApiModelProperty(value = "机构号")
	private String instNo;
	/**帐号*/
	@Excel(name = "帐号", width = 15)
    @ApiModelProperty(value = "帐号")
	private String acctNo;
	/**记录号*/
	@Excel(name = "记录号", width = 15)
    @ApiModelProperty(value = "记录号")
	private String recNo;
	/**交易类型*/
	@Excel(name = "交易类型", width = 15)
    @ApiModelProperty(value = "交易类型")
	private String tranType;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String stat;
	/**该条数据记录日期*/
	@Excel(name = "该条数据记录日期", width = 15)
    @ApiModelProperty(value = "该条数据记录日期")
	private Integer postDate;
	/**交易发生日期*/
	@Excel(name = "交易发生日期", width = 15)
    @ApiModelProperty(value = "交易发生日期")
	private Integer trnDate;
	/**系统日期*/
	@Excel(name = "系统日期", width = 15)
    @ApiModelProperty(value = "系统日期")
	private Integer systemDate;
	/**系统时间*/
	@Excel(name = "系统时间", width = 15)
    @ApiModelProperty(value = "系统时间")
	@TableField(value = "system_time")
	private Integer systemTime;
	/**柜员号和网点*/
	@Excel(name = "柜员号和网点", width = 15)
    @ApiModelProperty(value = "柜员号和网点")
	private Long tellAndBr;
	/**流水号*/
	@Excel(name = "流水号", width = 15)
    @ApiModelProperty(value = "流水号")
	private Integer jrnlNo;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	private Integer trnCode;
	/**终端号*/
	@Excel(name = "终端号", width = 15)
    @ApiModelProperty(value = "终端号")
	private Integer brterm;
	/**渠道*/
	@Excel(name = "渠道", width = 15)
    @ApiModelProperty(value = "渠道")
	private String channel;
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String deli;
	/**支票有效天数*/
	@Excel(name = "支票有效天数", width = 15)
    @ApiModelProperty(value = "支票有效天数")
	private String chqNoDays;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private String amount;
	/**账户余额*/
	@Excel(name = "账户余额", width = 15)
    @ApiModelProperty(value = "账户余额")
	private String balance;
	/**批次号*/
	@Excel(name = "批次号", width = 15)
    @ApiModelProperty(value = "批次号")
	private String batchNo;
	/**利息推迟标志(根据FF自定)*/
	@Excel(name = "利息推迟标志(根据FF自定)", width = 15)
    @ApiModelProperty(value = "利息推迟标志(根据FF自定)")
	private String dayfileFlag;
	/**冲正标志*/
	@Excel(name = "冲正标志", width = 15)
    @ApiModelProperty(value = "冲正标志")
	private String correction;
	/**国家代码*/
	@Excel(name = "国家代码", width = 15)
    @ApiModelProperty(value = "国家代码")
	private String merchCountryCode;
	/**利息延迟天数*/
	@Excel(name = "利息延迟天数", width = 15)
    @ApiModelProperty(value = "利息延迟天数")
	private String deferDays;
	/**借贷标识*/
	@Excel(name = "借贷标识", width = 15)
    @ApiModelProperty(value = "借贷标识")
	private String miscDrCrInd;
	/**记账时间*/
	@Excel(name = "记账时间", width = 15)
    @ApiModelProperty(value = "记账时间")
	private String repostTime;
	/**记账序列*/
	@Excel(name = "记账序列", width = 15)
    @ApiModelProperty(value = "记账序列")
	private String postSeq;
	/**交易标识*/
	@Excel(name = "交易标识", width = 15)
    @ApiModelProperty(value = "交易标识")
	private String txnInd;
	/**交易所属系统*/
	@Excel(name = "交易所属系统", width = 15)
    @ApiModelProperty(value = "交易所属系统")
	private String tranSystem;
	/**转账交易标识*/
	@Excel(name = "转账交易标识", width = 15)
    @ApiModelProperty(value = "转账交易标识")
	private String tranDesc;
	/**转账交易的转账账号*/
	@Excel(name = "转账交易的转账账号", width = 15)
    @ApiModelProperty(value = "转账交易的转账账号")
	private String tranAcct;
	/**一本通存折标识*/
	@Excel(name = "一本通存折标识", width = 15)
    @ApiModelProperty(value = "一本通存折标识")
	private String mcaPwdlStat;
	/**定期一本通部提后册号*/
	@Excel(name = "定期一本通部提后册号", width = 15)
    @ApiModelProperty(value = "定期一本通部提后册号")
	private String mcaPwdlVolumeNo;
	/**定期一本通部提后序号*/
	@Excel(name = "定期一本通部提后序号", width = 15)
    @ApiModelProperty(value = "定期一本通部提后序号")
	private String mcaPwdlSeqNo;
	/**零存计数(对零存整取产品,默认存入一次后该值即为1)*/
	@Excel(name = "零存计数(对零存整取产品,默认存入一次后该值即为1)", width = 15)
    @ApiModelProperty(value = "零存计数(对零存整取产品,默认存入一次后该值即为1)")
	private String thisDepCnt;
	/**标识零存是否违约 (零存产品)*/
	@Excel(name = "标识零存是否违约 (零存产品)", width = 15)
    @ApiModelProperty(value = "标识零存是否违约 (零存产品)")
	private String brkRuleindBefimg;
	/**标识是否更新当前余额(零存户违约时)*/
	@Excel(name = "标识是否更新当前余额(零存户违约时)", width = 15)
    @ApiModelProperty(value = "标识是否更新当前余额(零存户违约时)")
	private String updCurrBalInd;
	/**子账户产品子类*/
	@Excel(name = "子账户产品子类", width = 15)
    @ApiModelProperty(value = "子账户产品子类")
	private String subAcctType;
	/**存折册号*/
	@Excel(name = "存折册号", width = 15)
    @ApiModelProperty(value = "存折册号")
	private String volume;
	/**存折序号*/
	@Excel(name = "存折序号", width = 15)
    @ApiModelProperty(value = "存折序号")
	private String sequNum;
	/**支取方式*/
	@Excel(name = "支取方式", width = 15)
    @ApiModelProperty(value = "支取方式")
	private String withdrMeth;
	/**标识是否为教育储蓄(关户交易对应的教育储蓄标识)*/
	@Excel(name = "标识是否为教育储蓄(关户交易对应的教育储蓄标识)", width = 15)
    @ApiModelProperty(value = "标识是否为教育储蓄(关户交易对应的教育储蓄标识)")
	private String eduInd;
	/**标识是否强制到期(对应关户交易的强制到期标识)*/
	@Excel(name = "标识是否强制到期(对应关户交易的强制到期标识)", width = 15)
    @ApiModelProperty(value = "标识是否强制到期(对应关户交易的强制到期标识)")
	private String maturityFlg;
	/**凭证挂失日期 (对应关户交易的凭证挂失日)*/
	@Excel(name = "凭证挂失日期 (对应关户交易的凭证挂失日)", width = 15)
    @ApiModelProperty(value = "凭证挂失日期 (对应关户交易的凭证挂失日)")
	private String lostDte;
	/**挂失登记号(对应关户交易的挂失登记号)*/
	@Excel(name = "挂失登记号(对应关户交易的挂失登记号)", width = 15)
    @ApiModelProperty(value = "挂失登记号(对应关户交易的挂失登记号)")
	private String lostLogNum;
	/**支票签发日期*/
	@Excel(name = "支票签发日期", width = 15)
    @ApiModelProperty(value = "支票签发日期")
	private Integer issueDate;
	/**转代销户标识 (1041页面)*/
	@Excel(name = "转代销户标识 (1041页面)", width = 15)
    @ApiModelProperty(value = "转代销户标识 (1041页面)")
	private String crChangeToSuspClo;
	/**交易币种*/
	@Excel(name = "交易币种", width = 15)
    @ApiModelProperty(value = "交易币种")
	private String curr;
	/**提示码(仅用于一本通子账户的关户交易)*/
	@Excel(name = "提示码(仅用于一本通子账户的关户交易)", width = 15)
    @ApiModelProperty(value = "提示码(仅用于一本通子账户的关户交易)")
	private String promoCode;
	/**现金统计分析码*/
	@Excel(name = "现金统计分析码", width = 15)
    @ApiModelProperty(value = "现金统计分析码")
	private String cshAnalyNum;
	/**原流水号 (用于冲正交易)*/
	@Excel(name = "原流水号 (用于冲正交易)", width = 15)
    @ApiModelProperty(value = "原流水号 (用于冲正交易)")
	private String origJrnl;
	/**授权柜员 (仅用于冻结.止付和关户交易)*/
	@Excel(name = "授权柜员 (仅用于冻结.止付和关户交易)", width = 15)
    @ApiModelProperty(value = "授权柜员 (仅用于冻结.止付和关户交易)")
	private String supervisorid;
	/**部提或关户结息金额*/
	@Excel(name = "部提或关户结息金额", width = 15)
    @ApiModelProperty(value = "部提或关户结息金额")
	private String pwdlIntAmount;
	/**子渠道*/
	@Excel(name = "子渠道", width = 15)
    @ApiModelProperty(value = "子渠道")
	private String subChannel;
	/**filler2*/
	@Excel(name = "filler2", width = 15)
    @ApiModelProperty(value = "filler2")
	@TableField(value = "filler_2")
	private String filler2;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
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

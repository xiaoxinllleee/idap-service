package org.cmms.modules.sjxf.hxxt.dkls_50.entity;

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
 * @Description: 贷款流水_50
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Data
@TableName("Cbs_boct_tran_50")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_boct_tran_50对象", description="贷款流水_50")
public class Dkls_50 {
    
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
	/**交易状态*/
	@Excel(name = "交易状态", width = 15)
    @ApiModelProperty(value = "交易状态")
	private String stat;
	/**提交日期*/
	@Excel(name = "提交日期", width = 15)
    @ApiModelProperty(value = "提交日期")
	private String postDate;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String trnDate;
	/**系统日期*/
	@Excel(name = "系统日期", width = 15)
    @ApiModelProperty(value = "系统日期")
	private String systemDate;
	/**系统时间*/
	@Excel(name = "系统时间", width = 15)
    @ApiModelProperty(value = "系统时间")
	@TableField(value = "system_time")
	private String systemTime;
	/**柜员和分行号*/
	@Excel(name = "柜员和分行号", width = 15)
    @ApiModelProperty(value = "柜员和分行号")
	private String tellAndBr;
	/**流水号*/
	@Excel(name = "流水号", width = 15)
    @ApiModelProperty(value = "流水号")
	private String jrnlNo;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	private String trnCode;
	/**终端号*/
	@Excel(name = "终端号", width = 15)
    @ApiModelProperty(value = "终端号")
	private String brterm;
	/**渠道*/
	@Excel(name = "渠道", width = 15)
    @ApiModelProperty(value = "渠道")
	private String channel;
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String deli;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private String loanBalance;
	/**应还利息*/
	@Excel(name = "应还利息", width = 15)
    @ApiModelProperty(value = "应还利息")
	private String intDue;
	/**计提利息*/
	@Excel(name = "计提利息", width = 15)
    @ApiModelProperty(value = "计提利息")
	private String intAccr;
	/**坏账金额*/
	@Excel(name = "坏账金额", width = 15)
    @ApiModelProperty(value = "坏账金额")
	private String badDebtAmt;
	/**拖欠标识*/
	@Excel(name = "拖欠标识", width = 15)
    @ApiModelProperty(value = "拖欠标识")
	private String arrearsInd;
	/**上次金融交易日*/
	@Excel(name = "上次金融交易日", width = 15)
    @ApiModelProperty(value = "上次金融交易日")
	private String lstFinDte;
	/**浮动日期*/
	@Excel(name = "浮动日期", width = 15)
    @ApiModelProperty(value = "浮动日期")
	private String floatDate;
	/**利息增量*/
	@Excel(name = "利息增量", width = 15)
    @ApiModelProperty(value = "利息增量")
	private String intIncr;
	/**本金罚息增量*/
	@Excel(name = "本金罚息增量", width = 15)
    @ApiModelProperty(value = "本金罚息增量")
	private String arrIntIncr;
	/**利息罚息计提增量*/
	@Excel(name = "利息罚息计提增量", width = 15)
    @ApiModelProperty(value = "利息罚息计提增量")
	private String fine2IntIncr;
	/**复利应计利息增量*/
	@Excel(name = "复利应计利息增量", width = 15)
    @ApiModelProperty(value = "复利应计利息增量")
	private String fine4IntIncr;
	/**理论的余额，应该的余额*/
	@Excel(name = "理论的余额，应该的余额", width = 15)
    @ApiModelProperty(value = "理论的余额，应该的余额")
	private String theoLoanBal;
	/**结息日期*/
	@Excel(name = "结息日期", width = 15)
    @ApiModelProperty(value = "结息日期")
	private String intValDate;
	/**首次浮动日期*/
	@Excel(name = "首次浮动日期", width = 15)
    @ApiModelProperty(value = "首次浮动日期")
	private String fstFloatDate;
	/**上次浮动日期*/
	@Excel(name = "上次浮动日期", width = 15)
    @ApiModelProperty(value = "上次浮动日期")
	private String lstFloatDate;
	/**filler*/
	@Excel(name = "filler", width = 15)
    @ApiModelProperty(value = "filler")
	private String filler;
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

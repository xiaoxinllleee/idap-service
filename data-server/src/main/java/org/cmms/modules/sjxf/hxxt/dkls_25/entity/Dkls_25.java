package org.cmms.modules.sjxf.hxxt.dkls_25.entity;

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
 * @Description: 贷款流水_25
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Data
@TableName("Cbs_boct_tran_25")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_boct_tran_25对象", description="贷款流水_25")
public class Dkls_25 {
    
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
	/**支票结算天数*/
	@Excel(name = "支票结算天数", width = 15)
    @ApiModelProperty(value = "支票结算天数")
	private String chqDays;
	/**冲正标识*/
	@Excel(name = "冲正标识", width = 15)
    @ApiModelProperty(value = "冲正标识")
	private String amount;
	/**余额*/
	@Excel(name = "余额", width = 15)
    @ApiModelProperty(value = "余额")
	private String balance;
	/**利息推迟标志(根据FF自定)*/
	@Excel(name = "利息推迟标志(根据FF自定)", width = 15)
    @ApiModelProperty(value = "利息推迟标志(根据FF自定)")
	private String dayfile;
	/**拖欠金额*/
	@Excel(name = "拖欠金额", width = 15)
    @ApiModelProperty(value = "拖欠金额")
	private String arrears;
	/**分账码*/
	@Excel(name = "分账码", width = 15)
    @ApiModelProperty(value = "分账码")
	private String subLedgCode;
	/**交易重新提交时间*/
	@Excel(name = "交易重新提交时间", width = 15)
    @ApiModelProperty(value = "交易重新提交时间")
	private String repostTime;
	/**延迟天数*/
	@Excel(name = "延迟天数", width = 15)
    @ApiModelProperty(value = "延迟天数")
	private String deferDays;
	/**提示码*/
	@Excel(name = "提示码", width = 15)
    @ApiModelProperty(value = "提示码")
	private String promoNo;
	/**期数*/
	@Excel(name = "期数", width = 15)
    @ApiModelProperty(value = "期数")
	private String termNo;
	/**坏账标识*/
	@Excel(name = "坏账标识", width = 15)
    @ApiModelProperty(value = "坏账标识")
	private String badDebtInd;
	/**原交易流水号*/
	@Excel(name = "原交易流水号", width = 15)
    @ApiModelProperty(value = "原交易流水号")
	private String oriJrnlNo;
	/**现金分类码*/
	@Excel(name = "现金分类码", width = 15)
    @ApiModelProperty(value = "现金分类码")
	private String cashClassCode;
	/**逾期状态*/
	@Excel(name = "逾期状态", width = 15)
    @ApiModelProperty(value = "逾期状态")
	private String overdueStat;
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

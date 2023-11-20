package org.cmms.modules.sjxf.hxxt.dkjykb.entity;

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
 * @Description: 贷款交易宽表
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Data
@TableName("Cbs_boct_base")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_boct_base对象", description="贷款交易宽表")
public class Dkjykb {
    
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**数据生成时间*/
    @ApiModelProperty(value = "数据生成时间")
	private Date loadDate;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String acctNo;
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
	/**系统时间*/
	@Excel(name = "系统时间", width = 15)
    @ApiModelProperty(value = "系统时间")
	private String trnTime;
	/**交易机构号*/
	@Excel(name = "交易机构号", width = 15)
    @ApiModelProperty(value = "交易机构号")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String brNo;
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
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private String amount;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private String balance;
	/**利息推迟标志*/
	@Excel(name = "利息推迟标志", width = 15)
    @ApiModelProperty(value = "利息推迟标志")
	private String dayfile;
	/**交易后本金欠款额*/
	@Excel(name = "交易后本金欠款额", width = 15)
    @ApiModelProperty(value = "交易后本金欠款额")
	private String arrears;
	/**入账时间*/
	@Excel(name = "入账时间", width = 15)
    @ApiModelProperty(value = "入账时间")
	private String repostTime;
	/**消息码*/
	@Excel(name = "消息码", width = 15)
    @ApiModelProperty(value = "消息码")
	private String messageNo;
	/**期数*/
	@Excel(name = "期数", width = 15)
    @ApiModelProperty(value = "期数")
	private String termNo;
	/**坏账标识*/
	@Excel(name = "坏账标识", width = 15)
    @ApiModelProperty(value = "坏账标识")
	private String badDebtInd;
	/**交易柜员*/
	@Excel(name = "交易柜员", width = 15)
    @ApiModelProperty(value = "交易柜员")
	private String tellNo;
	/**交易柜员名称*/
	@Excel(name = "交易柜员名称", width = 15)
    @ApiModelProperty(value = "交易柜员名称")
	private String tellName;
	/**原利率*/
	@Excel(name = "原利率", width = 15)
    @ApiModelProperty(value = "原利率")
	private String oldRate;
	/**新利率*/
	@Excel(name = "新利率", width = 15)
    @ApiModelProperty(value = "新利率")
	private String newRate;
	/**交易机构名称*/
	@Excel(name = "交易机构名称", width = 15)
    @ApiModelProperty(value = "交易机构名称")
	private String brName;
	/**交易码名称*/
	@Excel(name = "交易码名称", width = 15)
    @ApiModelProperty(value = "交易码名称")
	private String trnName;
	/**现金总额*/
	@Excel(name = "现金总额", width = 15)
    @ApiModelProperty(value = "现金总额")
	private String cashTots;
	/**简述*/
	@Excel(name = "简述", width = 15)
    @ApiModelProperty(value = "简述")
	private String text;
	/**消息内容*/
	@Excel(name = "消息内容", width = 15)
    @ApiModelProperty(value = "消息内容")
	private String freeMsg;
	/**旧分行号*/
	@Excel(name = "旧分行号", width = 15)
    @ApiModelProperty(value = "旧分行号")
	private String oldBranch;
	/**新分行号*/
	@Excel(name = "新分行号", width = 15)
    @ApiModelProperty(value = "新分行号")
	private String newBranch;
	/**旧产品大类*/
	@Excel(name = "旧产品大类", width = 15)
    @ApiModelProperty(value = "旧产品大类")
	private String oldAcctType;
	/**新产品大类*/
	@Excel(name = "新产品大类", width = 15)
    @ApiModelProperty(value = "新产品大类")
	private String newAcctType;
	/**原非应计核销状态*/
	@Excel(name = "原非应计核销状态", width = 15)
    @ApiModelProperty(value = "原非应计核销状态")
	private String prevStatus;
	/**新非应计核销状态*/
	@Excel(name = "新非应计核销状态", width = 15)
    @ApiModelProperty(value = "新非应计核销状态")
	private String newStatus;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String comments;
	/**原流水号*/
	@Excel(name = "原流水号", width = 15)
    @ApiModelProperty(value = "原流水号")
	private String oriJrnlNo;
	/**编辑掩码*/
	@Excel(name = "编辑掩码", width = 15)
    @ApiModelProperty(value = "编辑掩码")
	private String editMask;
	/**桶子码*/
	@Excel(name = "桶子码", width = 15)
    @ApiModelProperty(value = "桶子码")
	private String priorityCode;
	/**BTRN桶子码*/
	@Excel(name = "BTRN桶子码", width = 15)
    @ApiModelProperty(value = "BTRN桶子码")
	private String prCode;
	/**记录号*/
	@Excel(name = "记录号", width = 15)
    @ApiModelProperty(value = "记录号")
	private String recNo;
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

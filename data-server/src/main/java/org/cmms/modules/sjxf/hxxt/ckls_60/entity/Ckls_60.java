package org.cmms.modules.sjxf.hxxt.ckls_60.entity;

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
 * @Description: 存款流水_60
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Data
@TableName("Cbs_inct_tran_60")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_inct_tran_60对象", description="存款流水_60")
public class Ckls_60 {

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
	private String postDate;
	/**交易发生日期*/
	@Excel(name = "交易发生日期", width = 15)
    @ApiModelProperty(value = "交易发生日期")
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
	/**柜员号和网点*/
	@Excel(name = "柜员号和网点", width = 15)
    @ApiModelProperty(value = "柜员号和网点")
	private String tellAndBr;
	/**流水号*/
	@Excel(name = "流水号", width = 15)
    @ApiModelProperty(value = "流水号")
	private String jrnlNo;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	private Integer trnCode;
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
	/**类型*/
	@Excel(name = "类型", width = 15)
    @ApiModelProperty(value = "类型")
	private String type;
	/**状态1*/
	@Excel(name = "状态1", width = 15)
    @ApiModelProperty(value = "状态1")
	private String stat1;
	/**POST_DATE1*/
	@Excel(name = "POST_DATE1", width = 15)
    @ApiModelProperty(value = "POST_DATE1")
	private Integer postDate1;
	/**filler01*/
	@Excel(name = "filler01", width = 15)
    @ApiModelProperty(value = "filler01")
	@TableField(value = "filler_01")
	private String filler01;
	/**交易码1*/
	@Excel(name = "交易码1", width = 15)
    @ApiModelProperty(value = "交易码1")
	private Integer trnCode1;
	/**流水号1*/
	@Excel(name = "流水号1", width = 15)
    @ApiModelProperty(value = "流水号1")
	private Integer jrnlNo1;
	/**处理通知的日期*/
	@Excel(name = "处理通知的日期", width = 15)
    @ApiModelProperty(value = "处理通知的日期")
	private Integer procDate;
	/**支取关户标识*/
	@Excel(name = "支取关户标识", width = 15)
    @ApiModelProperty(value = "支取关户标识")
	private String wdlClseInd;
	/**标识通知结束时是否转账处理*/
	@Excel(name = "标识通知结束时是否转账处理", width = 15)
    @ApiModelProperty(value = "标识通知结束时是否转账处理")
	private String autoInd;
	/**支取交易金额*/
	@Excel(name = "支取交易金额", width = 15)
    @ApiModelProperty(value = "支取交易金额")
	private String amount;
	/**通知天数*/
	@Excel(name = "通知天数", width = 15)
    @ApiModelProperty(value = "通知天数")
	private Integer daysNotice;
	/**转账系统*/
	@Excel(name = "转账系统", width = 15)
    @ApiModelProperty(value = "转账系统")
	private String trfSys;
	/**转入帐号*/
	@Excel(name = "转入帐号", width = 15)
    @ApiModelProperty(value = "转入帐号")
	private String account;
	/**跨机构转账使用的排序代码*/
	@Excel(name = "跨机构转账使用的排序代码", width = 15)
    @ApiModelProperty(value = "跨机构转账使用的排序代码")
	private Integer extSortCode;
	/**通知存款到期日*/
	@Excel(name = "通知存款到期日", width = 15)
    @ApiModelProperty(value = "通知存款到期日")
	private Integer expiryDate;
	/**标识是否被删除*/
	@Excel(name = "标识是否被删除", width = 15)
    @ApiModelProperty(value = "标识是否被删除")
	private String delete1;
	/**交易发起终端号1*/
	@Excel(name = "交易发起终端号1", width = 15)
    @ApiModelProperty(value = "交易发起终端号1")
	private Integer brterm1;
	/**提款类别*/
	@Excel(name = "提款类别", width = 15)
    @ApiModelProperty(value = "提款类别")
	private String wtype;
	/**建立通知的机构*/
	@Excel(name = "建立通知的机构", width = 15)
    @ApiModelProperty(value = "建立通知的机构")
	private String branch;
	/**取消通知的日期*/
	@Excel(name = "取消通知的日期", width = 15)
    @ApiModelProperty(value = "取消通知的日期")
	private Integer cancelDate;
	/**取消通知的机构*/
	@Excel(name = "取消通知的机构", width = 15)
    @ApiModelProperty(value = "取消通知的机构")
	private String cancelBrh;
	/**罚息标识*/
	@Excel(name = "罚息标识", width = 15)
    @ApiModelProperty(value = "罚息标识")
	private String penaltyFlag;
	/**交易授权柜员*/
	@Excel(name = "交易授权柜员", width = 15)
    @ApiModelProperty(value = "交易授权柜员")
	private String supervisorid;
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
	/**delete*/
	@Excel(name = "\"DELETE\"", width = 15)
    @ApiModelProperty(value = "\"DELETE\"")
	@TableField(value = "\"DELETE\"")
	private String delete;
}

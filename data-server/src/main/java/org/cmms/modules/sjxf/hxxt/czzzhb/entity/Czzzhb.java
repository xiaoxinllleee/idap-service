package org.cmms.modules.sjxf.hxxt.czzzhb.entity;

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
 * @Description: 财政子账户表
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
@Data
@TableName("Cbs_invd")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_invd对象", description="财政子账户表")
public class Czzzhb {
    
	/**银行号*/
	@Excel(name = "银行号", width = 15)
    @ApiModelProperty(value = "银行号")
	private String socNo;
	/**主账号*/
	@Excel(name = "主账号", width = 15)
    @ApiModelProperty(value = "主账号")
	private String mastAcct;
	/**顺序号*/
	@Excel(name = "顺序号", width = 15)
    @ApiModelProperty(value = "顺序号")
	private String sequenceNo;
	/**子账号*/
	@Excel(name = "子账号", width = 15)
    @ApiModelProperty(value = "子账号")
	private String subAcctNo;
	/**subAcLinkFlag*/
	@Excel(name = "subAcLinkFlag", width = 15)
    @ApiModelProperty(value = "subAcLinkFlag")
	private String subAcLinkFlag;
	/**subAcActiveFlag*/
	@Excel(name = "subAcActiveFlag", width = 15)
    @ApiModelProperty(value = "subAcActiveFlag")
	private String subAcActiveFlag;
	/**signedBrch*/
	@Excel(name = "signedBrch", width = 15)
    @ApiModelProperty(value = "signedBrch")
	private String signedBrch;
	/**signedDate*/
	@Excel(name = "signedDate", width = 15)
    @ApiModelProperty(value = "signedDate")
	private String signedDate;
	/**lastSeqNo*/
	@Excel(name = "lastSeqNo", width = 15)
    @ApiModelProperty(value = "lastSeqNo")
	private String lastSeqNo;
	/**填充字段*/
	@Excel(name = "填充字段", width = 15)
    @ApiModelProperty(value = "填充字段")
	private String fil01;
	/**最近交易日期*/
	@Excel(name = "最近交易日期", width = 15)
    @ApiModelProperty(value = "最近交易日期")
	private String lastMaintDate;
	/**最近交易状态*/
	@Excel(name = "最近交易状态", width = 15)
    @ApiModelProperty(value = "最近交易状态")
	private String lastMaintStat;
	/**开始日期*/
	@Excel(name = "开始日期", width = 15)
    @ApiModelProperty(value = "开始日期")
	private String sDate;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15)
    @ApiModelProperty(value = "结束日期")
	private String eDate;
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

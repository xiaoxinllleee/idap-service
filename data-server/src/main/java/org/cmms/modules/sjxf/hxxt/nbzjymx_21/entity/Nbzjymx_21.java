package org.cmms.modules.sjxf.hxxt.nbzjymx_21.entity;

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
 * @Description: 内部帐交易明细_21
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Data
@TableName("Cbs_gect_tran_21")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_gect_tran_21对象", description="内部帐交易明细_21")
public class Nbzjymx_21 {
    
	/**金融序号*/
	@Excel(name = "金融序号", width = 15)
    @ApiModelProperty(value = "金融序号")
	private String instNo;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String acctNo;
	/**顺序号*/
	@Excel(name = "顺序号", width = 15)
    @ApiModelProperty(value = "顺序号")
	private String recNo;
	/**交易类型*/
	@Excel(name = "交易类型", width = 15)
    @ApiModelProperty(value = "交易类型")
	private String tranType;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String stat;
	/**过帐日期*/
	@Excel(name = "过帐日期", width = 15)
    @ApiModelProperty(value = "过帐日期")
	private Integer postDate;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
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
	/**机构柜员号*/
	@Excel(name = "机构柜员号", width = 15)
    @ApiModelProperty(value = "机构柜员号")
	private Long tellAndBr;
	/**流水号*/
	@Excel(name = "流水号", width = 15)
    @ApiModelProperty(value = "流水号")
	private Integer jrnlNo;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	private Integer trnCode;
	/**交易终端*/
	@Excel(name = "交易终端", width = 15)
    @ApiModelProperty(value = "交易终端")
	private Integer brterm;
	/**渠道类型*/
	@Excel(name = "渠道类型", width = 15)
    @ApiModelProperty(value = "渠道类型")
	private String channel;
	/**删除标志 1-Yes；0-No*/
	@Excel(name = "删除标志 1-Yes；0-No", width = 15)
    @ApiModelProperty(value = "删除标志 1-Yes；0-No")
	private String deli;
	/**交易状态*/
	@Excel(name = "交易状态", width = 15)
    @ApiModelProperty(value = "交易状态")
	private String tranStatus;
	/**交易分行*/
	@Excel(name = "交易分行", width = 15)
    @ApiModelProperty(value = "交易分行")
	private Integer branch;
	/**交易终端*/
	@Excel(name = "交易终端", width = 15)
    @ApiModelProperty(value = "交易终端")
	private Integer branchTerm;
	/**交易柜员*/
	@Excel(name = "交易柜员", width = 15)
    @ApiModelProperty(value = "交易柜员")
	private Integer teller;
	/**信息号*/
	@Excel(name = "信息号", width = 15)
    @ApiModelProperty(value = "信息号")
	private String messageNo;
	/**信息内容*/
	@Excel(name = "信息内容", width = 15)
    @ApiModelProperty(value = "信息内容")
	private String messageText;
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

package org.cmms.modules.sjxf.hxxt.ckls_55.entity;

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
 * @Description: 存款流水_55
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Data
@TableName("Cbs_inct_tran_55")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_inct_tran_55对象", description="存款流水_55")
public class Ckls_55 {
    
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
	/**交易码1*/
	@Excel(name = "交易码1", width = 15)
    @ApiModelProperty(value = "交易码1")
	private Integer trnCode1;
	/**流水号1*/
	@Excel(name = "流水号1", width = 15)
    @ApiModelProperty(value = "流水号1")
	private Integer jrnlNo1;
	/**存折版本号或存单号*/
	@Excel(name = "存折版本号或存单号", width = 15)
    @ApiModelProperty(value = "存折版本号或存单号")
	private String psbkVerNo;
	/**修改后的存折状态*/
	@Excel(name = "修改后的存折状态", width = 15)
    @ApiModelProperty(value = "修改后的存折状态")
	private String psbkStatus;
	/**印鉴状态*/
	@Excel(name = "印鉴状态", width = 15)
    @ApiModelProperty(value = "印鉴状态")
	private String chopStatus;
	/**存折号*/
	@Excel(name = "存折号", width = 15)
    @ApiModelProperty(value = "存折号")
	private String pbSerialNo;
	/**交易发起终端号1*/
	@Excel(name = "交易发起终端号1", width = 15)
    @ApiModelProperty(value = "交易发起终端号1")
	private Integer brterm1;
	/**原因代码*/
	@Excel(name = "原因代码", width = 15)
    @ApiModelProperty(value = "原因代码")
	private String reasonCd;
	/**旧凭证类型*/
	@Excel(name = "旧凭证类型", width = 15)
    @ApiModelProperty(value = "旧凭证类型")
	private String oldVchType;
	/**旧凭证号*/
	@Excel(name = "旧凭证号", width = 15)
    @ApiModelProperty(value = "旧凭证号")
	private String oldVchNo;
	/**新凭证类型*/
	@Excel(name = "新凭证类型", width = 15)
    @ApiModelProperty(value = "新凭证类型")
	private String newVchType;
	/**新凭证号*/
	@Excel(name = "新凭证号", width = 15)
    @ApiModelProperty(value = "新凭证号")
	private String newVchNo;
	/**凭证支取方式*/
	@Excel(name = "凭证支取方式", width = 15)
    @ApiModelProperty(value = "凭证支取方式")
	private String wldType;
	/**凭证挂失日期*/
	@Excel(name = "凭证挂失日期", width = 15)
    @ApiModelProperty(value = "凭证挂失日期")
	private Integer rptLostDt;
	/**凭证挂失流水号*/
	@Excel(name = "凭证挂失流水号", width = 15)
    @ApiModelProperty(value = "凭证挂失流水号")
	private Integer rptLostJnl;
	/**交易授权柜员*/
	@Excel(name = "交易授权柜员", width = 15)
    @ApiModelProperty(value = "交易授权柜员")
	private String supervisorid;
	/**代理名称*/
	@Excel(name = "代理名称", width = 15)
    @ApiModelProperty(value = "代理名称")
	private String agentName;
	/**代理证件类型*/
	@Excel(name = "代理证件类型", width = 15)
    @ApiModelProperty(value = "代理证件类型")
	private String agentIdType;
	/**代理证件号码*/
	@Excel(name = "代理证件号码", width = 15)
    @ApiModelProperty(value = "代理证件号码")
	private String agentIdNo;
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

package org.cmms.modules.sjxf.hxxt.ckls_31.entity;

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
 * @Description: 存款流水_31
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Data
@TableName("Cbs_inct_tran_31")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_inct_tran_31对象", description="存款流水_31")
public class Ckls_31 {
    
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
	/**冻结原因*/
	@Excel(name = "冻结原因", width = 15)
    @ApiModelProperty(value = "冻结原因")
	private String holdReason;
	/**冻结金额*/
	@Excel(name = "冻结金额", width = 15)
    @ApiModelProperty(value = "冻结金额")
	private String holdVal;
	/**解冻金额*/
	@Excel(name = "解冻金额", width = 15)
    @ApiModelProperty(value = "解冻金额")
	private String removedHoldAmt;
	/**原冻结记录的流水号*/
	@Excel(name = "原冻结记录的流水号", width = 15)
    @ApiModelProperty(value = "原冻结记录的流水号")
	private String originalHoldJrnlNo;
	/**定期冻结的旧原因代码*/
	@Excel(name = "定期冻结的旧原因代码", width = 15)
    @ApiModelProperty(value = "定期冻结的旧原因代码")
	private String holdOld;
	/**定期冻结的新原因代码*/
	@Excel(name = "定期冻结的新原因代码", width = 15)
    @ApiModelProperty(value = "定期冻结的新原因代码")
	private String holdNew;
	/**定期冻结的修改日期*/
	@Excel(name = "定期冻结的修改日期", width = 15)
    @ApiModelProperty(value = "定期冻结的修改日期")
	private String amndDate;
	/**标识是否冲正*/
	@Excel(name = "标识是否冲正", width = 15)
    @ApiModelProperty(value = "标识是否冲正")
	private String correction;
	/**GRP*/
	@Excel(name = "GRP", width = 15)
    @ApiModelProperty(value = "GRP")
	private String grp;
	/**交易记录修改日期*/
	@Excel(name = "交易记录修改日期", width = 15)
    @ApiModelProperty(value = "交易记录修改日期")
	private String logDate;
	/**冻结到期日*/
	@Excel(name = "冻结到期日", width = 15)
    @ApiModelProperty(value = "冻结到期日")
	private String expiryDt;
	/**冻结备注*/
	@Excel(name = "冻结备注", width = 15)
    @ApiModelProperty(value = "冻结备注")
	private String holdRemark;
	/**交易授权柜员*/
	@Excel(name = "交易授权柜员", width = 15)
    @ApiModelProperty(value = "交易授权柜员")
	private String supervisorid;
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

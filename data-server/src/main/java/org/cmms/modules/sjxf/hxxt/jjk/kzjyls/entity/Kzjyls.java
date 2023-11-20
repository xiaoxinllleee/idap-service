package org.cmms.modules.sjxf.hxxt.jjk.kzjyls.entity;

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
 * @Description: 卡账交易流水
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@Data
@TableName("Cbsc_journal")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbsc_journal对象", description="卡账交易流水")
public class Kzjyls {
    
	/**Clusternodeidentifier*/
	@Excel(name = "Clusternodeidentifier", width = 15)
    @ApiModelProperty(value = "Clusternodeidentifier")
	private Integer node;
	/**卡流水号*/
	@Excel(name = "卡流水号", width = 15)
    @ApiModelProperty(value = "卡流水号")
	private Integer jnlno;
	/**处理进程名称*/
	@Excel(name = "处理进程名称", width = 15)
    @ApiModelProperty(value = "处理进程名称")
	private String process;
	/**流水类型*/
	@Excel(name = "流水类型", width = 15)
    @ApiModelProperty(value = "流水类型")
	private String type;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	private Integer txnCode;
	/**主账号*/
	@Excel(name = "主账号", width = 15)
    @ApiModelProperty(value = "主账号")
	private String txnAccount;
	/**交易发起时间*/
    @ApiModelProperty(value = "交易发起时间")
	private Date txnDate;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private java.math.BigDecimal txnAmount;
	/**交易机构索引*/
	@Excel(name = "交易机构索引", width = 15)
    @ApiModelProperty(value = "交易机构索引")
	private Integer txnInst;
	/**交易网点*/
	@Excel(name = "交易网点", width = 15)
    @ApiModelProperty(value = "交易网点")
	private Integer txnBranch;
	/**交易受理柜员*/
	@Excel(name = "交易受理柜员", width = 15)
    @ApiModelProperty(value = "交易受理柜员")
	private Integer txnTeller;
	/**交易终端*/
	@Excel(name = "交易终端", width = 15)
    @ApiModelProperty(value = "交易终端")
	private Integer txnTerminal;
	/**交易接收时间*/
    @ApiModelProperty(value = "交易接收时间")
	private Date systemDate;
	/**清算日期*/
    @ApiModelProperty(value = "清算日期")
	private Date settleDate;
	/**主机业务处理日期*/
    @ApiModelProperty(value = "主机业务处理日期")
	private Date businessDate;
	/**是否冲正成功标志*/
	@Excel(name = "是否冲正成功标志", width = 15)
    @ApiModelProperty(value = "是否冲正成功标志")
	private String suspended;
	/**交易授权方*/
	@Excel(name = "交易授权方", width = 15)
    @ApiModelProperty(value = "交易授权方")
	private String authorised;
	/**是否是通知冲正类交易*/
	@Excel(name = "是否是通知冲正类交易", width = 15)
    @ApiModelProperty(value = "是否是通知冲正类交易")
	private String advice;
	/**交易是否已冲正*/
	@Excel(name = "交易是否已冲正", width = 15)
    @ApiModelProperty(value = "交易是否已冲正")
	private String corrected;
	/**跟踪号(第11域)*/
	@Excel(name = "跟踪号(第11域)", width = 15)
    @ApiModelProperty(value = "跟踪号(第11域)")
	private Integer trace;
	/**交易错误码*/
	@Excel(name = "交易错误码", width = 15)
    @ApiModelProperty(value = "交易错误码")
	private Integer error;
	/**受理机构(第32域)*/
	@Excel(name = "受理机构(第32域)", width = 15)
    @ApiModelProperty(value = "受理机构(第32域)")
	private String acquirer;
	/**交易源名称*/
	@Excel(name = "交易源名称", width = 15)
    @ApiModelProperty(value = "交易源名称")
	private String source;
	/**发送交易的PORTAL进程名称*/
	@Excel(name = "发送交易的PORTAL进程名称", width = 15)
    @ApiModelProperty(value = "发送交易的PORTAL进程名称")
	private String sender;
	/**二进制大字段*/
	@Excel(name = "二进制大字段", width = 15)
    @ApiModelProperty(value = "二进制大字段")
	private String data;
	/**主机处理交易的时间*/
	@Excel(name = "主机处理交易的时间", width = 15)
    @ApiModelProperty(value = "主机处理交易的时间")
	private java.math.BigDecimal procTime;
	/**处理交易的TPROC进程实例编号*/
	@Excel(name = "处理交易的TPROC进程实例编号", width = 15)
    @ApiModelProperty(value = "处理交易的TPROC进程实例编号")
	private Integer procInstance;
	/**线程编号*/
	@Excel(name = "线程编号", width = 15)
    @ApiModelProperty(value = "线程编号")
	private Integer procThread;
	/**主机授权号(卡系统产生)*/
	@Excel(name = "主机授权号(卡系统产生)", width = 15)
    @ApiModelProperty(value = "主机授权号(卡系统产生)")
	private String authResponse;
	/**主机流水*/
	@Excel(name = "主机流水", width = 15)
    @ApiModelProperty(value = "主机流水")
	private Integer hostJournal;
	/**交易唯一标识码*/
	@Excel(name = "交易唯一标识码", width = 15)
    @ApiModelProperty(value = "交易唯一标识码")
	private String uuid;
	/**原交易唯一标识码*/
	@Excel(name = "原交易唯一标识码", width = 15)
    @ApiModelProperty(value = "原交易唯一标识码")
	private String uuidOriginal;
	/**现在同字段UUID,存储122域前32位UUID*/
	@Excel(name = "现在同字段UUID,存储122域前32位UUID", width = 15)
    @ApiModelProperty(value = "现在同字段UUID,存储122域前32位UUID")
	private String acqinstUid;
	/**是否被调账*/
	@Excel(name = "是否被调账", width = 15)
    @ApiModelProperty(value = "是否被调账")
	private String reconFlag;
	/**最近维护日期*/
    @ApiModelProperty(value = "最近维护日期")
	private Date lastMaintainDate;
	/**最近维护标识*/
	@Excel(name = "最近维护标识", width = 15)
    @ApiModelProperty(value = "最近维护标识")
	private String lastMaintainFlag;
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

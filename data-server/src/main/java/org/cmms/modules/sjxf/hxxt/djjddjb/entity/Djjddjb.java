package org.cmms.modules.sjxf.hxxt.djjddjb.entity;

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
 * @Description: 冻结解冻登记簿
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
@Data
@TableName("Cbs_frim")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_frim对象", description="冻结解冻登记簿")
public class Djjddjb {
    
	/**机构号*/
	@Excel(name = "机构号", width = 15)
    @ApiModelProperty(value = "机构号")
	private String socNo;
	/**帐号*/
	@Excel(name = "帐号", width = 15)
    @ApiModelProperty(value = "帐号")
	private String acctNo;
	/**入账日期*/
	@Excel(name = "入账日期", width = 15)
    @ApiModelProperty(value = "入账日期")
	private Integer postDate;
	/**流水号*/
	@Excel(name = "流水号", width = 15)
    @ApiModelProperty(value = "流水号")
	private Integer jrnlNo;
	/**业务类型*/
	@Excel(name = "业务类型", width = 15)
    @ApiModelProperty(value = "业务类型")
	private String businessType;
	/**入账时间*/
	@Excel(name = "入账时间", width = 15)
    @ApiModelProperty(value = "入账时间")
	private Integer postTime;
	/**序列号*/
	@Excel(name = "序列号", width = 15)
    @ApiModelProperty(value = "序列号")
	private Long seqNo;
	/**柜员号*/
	@Excel(name = "柜员号", width = 15)
    @ApiModelProperty(value = "柜员号")
	private String tellerNo;
	/**机构号*/
	@Excel(name = "机构号", width = 15)
    @ApiModelProperty(value = "机构号")
	private String branchNo;
	/**授权柜员*/
	@Excel(name = "授权柜员", width = 15)
    @ApiModelProperty(value = "授权柜员")
	private String supTellerNo;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String customerName;
	/**政府机构*/
	@Excel(name = "政府机构", width = 15)
    @ApiModelProperty(value = "政府机构")
	private String governmentAgency;
	/**法律实施*/
	@Excel(name = "法律实施", width = 15)
    @ApiModelProperty(value = "法律实施")
	private String lawEnforceOfir;
	/**办公人员名*/
	@Excel(name = "办公人员名", width = 15)
    @ApiModelProperty(value = "办公人员名")
	private String officerIdName;
	/**办公人员ID*/
	@Excel(name = "办公人员ID", width = 15)
    @ApiModelProperty(value = "办公人员ID")
	private String officerIdNo;
	/**法律文书*/
	@Excel(name = "法律文书", width = 15)
    @ApiModelProperty(value = "法律文书")
	private String legalDocName;
	/**通知号*/
	@Excel(name = "通知号", width = 15)
    @ApiModelProperty(value = "通知号")
	private String noticeNo;
	/**冻结类型*/
	@Excel(name = "冻结类型", width = 15)
    @ApiModelProperty(value = "冻结类型")
	private String freezeType;
	/**冻结原因*/
	@Excel(name = "冻结原因", width = 15)
    @ApiModelProperty(value = "冻结原因")
	private String freezeReason;
	/**透支标志*/
	@Excel(name = "透支标志", width = 15)
    @ApiModelProperty(value = "透支标志")
	private String odInd;
	/**冻结金额*/
	@Excel(name = "冻结金额", width = 15)
    @ApiModelProperty(value = "冻结金额")
	private java.math.BigDecimal freezeAmount;
	/**原冻结流水号*/
	@Excel(name = "原冻结流水号", width = 15)
    @ApiModelProperty(value = "原冻结流水号")
	private Integer oriFreezeJrnlNo;
	/**原冻结日期*/
	@Excel(name = "原冻结日期", width = 15)
    @ApiModelProperty(value = "原冻结日期")
	private Integer oriFreezeDate;
	/**冻结日期*/
	@Excel(name = "冻结日期", width = 15)
    @ApiModelProperty(value = "冻结日期")
	private Integer freezeMatDate;
	/**转账类型*/
	@Excel(name = "转账类型", width = 15)
    @ApiModelProperty(value = "转账类型")
	private String trfType;
	/**转账金额*/
	@Excel(name = "转账金额", width = 15)
    @ApiModelProperty(value = "转账金额")
	private java.math.BigDecimal trfAmount;
	/**接收账号*/
	@Excel(name = "接收账号", width = 15)
    @ApiModelProperty(value = "接收账号")
	private String receiveAcctNo;
	/**接收姓名*/
	@Excel(name = "接收姓名", width = 15)
    @ApiModelProperty(value = "接收姓名")
	private String receiveCustName;
	/**接收银行*/
	@Excel(name = "接收银行", width = 15)
    @ApiModelProperty(value = "接收银行")
	private String receiveBankName;
	/**转账SRN号*/
	@Excel(name = "转账SRN号", width = 15)
    @ApiModelProperty(value = "转账SRN号")
	private String trfSrnNo;
	/**查询内容*/
	@Excel(name = "查询内容", width = 15)
    @ApiModelProperty(value = "查询内容")
	private String enquiryContent;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**子类型*/
	@Excel(name = "子类型", width = 15)
    @ApiModelProperty(value = "子类型")
	private String subType;
	/**册号*/
	@Excel(name = "册号", width = 15)
    @ApiModelProperty(value = "册号")
	private String volumeNo;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String sequenceNo;
	/**优先级*/
	@Excel(name = "优先级", width = 15)
    @ApiModelProperty(value = "优先级")
	private String priorityNo;
	/**账户余额*/
	@Excel(name = "账户余额", width = 15)
    @ApiModelProperty(value = "账户余额")
	private java.math.BigDecimal acctBalance;
	/**可用金额*/
	@Excel(name = "可用金额", width = 15)
    @ApiModelProperty(value = "可用金额")
	private java.math.BigDecimal availAmt;
	/**othGovFreeAmt*/
	@Excel(name = "othGovFreeAmt", width = 15)
    @ApiModelProperty(value = "othGovFreeAmt")
	private java.math.BigDecimal othGovFreeAmt;
	/**marFreeAmt*/
	@Excel(name = "marFreeAmt", width = 15)
    @ApiModelProperty(value = "marFreeAmt")
	private java.math.BigDecimal marFreeAmt;
	/**pleFreeFlg*/
	@Excel(name = "pleFreeFlg", width = 15)
    @ApiModelProperty(value = "pleFreeFlg")
	private String pleFreeFlg;
	/**实际冻结金额*/
	@Excel(name = "实际冻结金额", width = 15)
    @ApiModelProperty(value = "实际冻结金额")
	private java.math.BigDecimal realFreezeAmt;
	/**原始冻结金额*/
	@Excel(name = "原始冻结金额", width = 15)
    @ApiModelProperty(value = "原始冻结金额")
	private java.math.BigDecimal origFreezeAmt;
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

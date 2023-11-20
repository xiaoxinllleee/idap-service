package org.cmms.modules.sjxf.hxxt.ckjykb2.entity;

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
 * @Description: 存款交易宽表2
 * @Author: jeecg-boot
 * @Date:   2021-12-08
 * @Version: V1.0
 */
@Data
@TableName("V_sjxf_ckjykb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_sjxf_ckjykb对象", description="存款交易宽表2")
public class Ckjykb2 {
    
	/**机构代码*/
	//@Excel(name = "机构代码", width = 20)
    @ApiModelProperty(value = "机构代码")
	private String branchNo;
	/**dataDate*/
	//@Excel(name = "交易日期", width = 20)
    @ApiModelProperty(value = "交易日期")
	private String dataDate;
	/**loadDate*/
    @ApiModelProperty(value = "loadDate")
	private Date loadDate;
	/**交易日期*/
	@Excel(name = "交易日期", width = 10)
    @ApiModelProperty(value = "交易日期")
	private String trnDate;
	/**trnTime*/
	//@Excel(name = "trnTime", width = 20)
    @ApiModelProperty(value = "trnTime")
	private String trnTime;
	/**jrnlNo*/
	//@Excel(name = "jrnlNo", w wi)
    @ApiModelProperty(value = "jrnlNo")
	private String jrnlNo;
	/**brNo*/
	@Excel(name = "机构号", width = 20)
    @ApiModelProperty(value = "brNo")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String brNo;
	/**brName*/
	@Excel(name = "机构名称", width = 20)
    @ApiModelProperty(value = "brName")
	private String brName;
	/**tellNo*/
	//@Excel(name = "tellNo", width = 20)
    @ApiModelProperty(value = "tellNo")
	private String tellNo;
	/**账号*/
	@Excel(name = "账号", width = 20)
    @ApiModelProperty(value = "账号")
	private String accNo;
	/**户名*/
	@Excel(name = "户名", width = 20)
    @ApiModelProperty(value = "户名")
	private String accName;
	/**对方账号*/
	@Excel(name = "对方账号", width = 20)
    @ApiModelProperty(value = "对方账号")
	private String oppAccNo;
	/**对方户名*/
	@Excel(name = "对方户名", width = 20)
    @ApiModelProperty(value = "对方户名")
	private String oppAccName;
	/**oppBrNo*/
	@Excel(name = "对方机构号", width = 20)
    @ApiModelProperty(value = "oppBrNo")
	private String oppBrNo;
	/**对方机构名称*/
	@Excel(name = "对方机构名称", width = 20)
    @ApiModelProperty(value = "对方机构名称")
	private String oppBrName;
	/**curr*/
	//@Excel(name = "curr", width = 20)
    @ApiModelProperty(value = "curr")
	private String curr;
	/**trxCode*/
	@Excel(name = "交易码", width = 10)
    @ApiModelProperty(value = "trxCode")
	private String trxCode;
	/**交易码名称*/
	@Excel(name = "交易码名称", width = 20)
    @ApiModelProperty(value = "交易码名称")
	private String trxName;
	/**dbFlag*/
	//@Excel(name = "dbFlag", width = 20)
    @ApiModelProperty(value = "dbFlag")
	private String dbFlag;
	/**金额*/
	@Excel(name = "交易金额", width = 20)
    @ApiModelProperty(value = "交易金额")
	private java.math.BigDecimal amount;
	/**voucherType*/
	//@Excel(name = "voucherType", width = 20)
    @ApiModelProperty(value = "voucherType")
	private String voucherType;
	/**voucherNo*/
	//@Excel(name = "voucherNo", width = 20)
    @ApiModelProperty(value = "voucherNo")
	private String voucherNo;
	/**authTell*/
	//@Excel(name = "authTell", width = 20)
    @ApiModelProperty(value = "authTell")
	private String authTell;
	/**postDate*/
	//@Excel(name = "postDate", width = 20)
    @ApiModelProperty(value = "postDate")
	private String postDate;
	/**terminalNo*/
	//@Excel(name = "terminalNo", width = 20)
    @ApiModelProperty(value = "terminalNo")
	private String terminalNo;
	/**txStatus*/
	//@Excel(name = "txStatus", width = 20)
    @ApiModelProperty(value = "txStatus")
	private String txStatus;
	/**账号余额*/
	@Excel(name = "账号余额", width = 20)
    @ApiModelProperty(value = "账号余额")
	private java.math.BigDecimal balance;
	/**cashTots*/
	//@Excel(name = "cashTots", width = 20)
    @ApiModelProperty(value = "cashTots")
	private String cashTots;
	/**authTellName*/
	//@Excel(name = "authTellName", width = 20)
    @ApiModelProperty(value = "authTellName")
	private String authTellName;
	/**channel*/
	//@Excel(name = "channel", width = 20)
    @ApiModelProperty(value = "channel")
	private String channel;
	/**correction*/
	//@Excel(name = "correction", width = 20)
    @ApiModelProperty(value = "correction")
	private String correction;
	/**备注*/
	@Excel(name = "备注", width = 20)
    @ApiModelProperty(value = "备注")
	private String comments;
	/**柜员名称*/
	@Excel(name = "柜员名称", width = 20)
    @ApiModelProperty(value = "柜员名称")
	private String tellName;
	/**origJrnl*/
	//@Excel(name = "origJrnl", width = 20)
    @ApiModelProperty(value = "origJrnl")
	private String origJrnl;
	/**recNo*/
	//@Excel(name = "recNo", width = 20)
    @ApiModelProperty(value = "recNo")
	private String recNo;
	/**tranChannel*/
	//@Excel(name = "tranChannel", width = 20)
    @ApiModelProperty(value = "tranChannel")
	private String tranChannel;
	/**legalNo*/
	//@Excel(name = "legalNo", width = 20)
    @ApiModelProperty(value = "legalNo")
	private String legalNo;
	/**dtnum*/
	/*@Excel(name = "dtnum", width = 20)
    @ApiModelProperty(value = "dtnum")
	private Integer dtnum;*/
	/**batchNo*/
	//@Excel(name = "batchNo", width = 20)
    @ApiModelProperty(value = "batchNo")
	private String batchNo;
	/**mnemonic*/
	//@Excel(name = "mnemonic", width = 20)
    @ApiModelProperty(value = "mnemonic")
	private String mnemonic;
	/**startSerialNo*/
	//@Excel(name = "startSerialNo", width = 20)
    @ApiModelProperty(value = "startSerialNo")
	private String startSerialNo;
	/**endSerialNo*/
	//@Excel(name = "endSerialNo", width = 20)
    @ApiModelProperty(value = "endSerialNo")
	private String endSerialNo;
}

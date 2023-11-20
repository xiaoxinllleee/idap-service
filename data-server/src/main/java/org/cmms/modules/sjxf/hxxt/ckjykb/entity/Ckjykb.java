package org.cmms.modules.sjxf.hxxt.ckjykb.entity;

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
 * @Description: 存款交易宽表
 * @Author: jeecg-boot
 * @Date:   2021-12-08
 * @Version: V1.0
 */
@Data
@TableName("V_sjxf_ckjykb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_sjxf_ckjykb对象", description="存款交易宽表")
public class Ckjykb {

	/**机构代码*/
	@Excel(name = "机构代码", width = 20)
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String branchNo;
	/**dataDate*/
	@Excel(name = "数据日期", width = 10)
    @ApiModelProperty(value = "dataDate")
	private String dataDate;
	/**loadDate*/
	@Excel(name = "生成日期", width = 20)
    @ApiModelProperty(value = "loadDate")
	private Date loadDate;
	/**交易日期*/
	@Excel(name = "交易日期", width = 10)
    @ApiModelProperty(value = "交易日期")
	private String trnDate;
	/**trnTime*/
	@Excel(name = "交易时间", width = 20)
    @ApiModelProperty(value = "trnTime")
	private String trnTime;
	/**jrnlNo*/
	@Excel(name = "流水号", width = 20)
    @ApiModelProperty(value = "jrnlNo")
	private String jrnlNo;
	/**brNo*/
	@Excel(name = "机构号", width = 20)
    @ApiModelProperty(value = "机构号")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String brNo;
	/**brName*/
	@Excel(name = "机构名称", width = 20)
    @ApiModelProperty(value = "brName")
	private String brName;
	/**tellNo*/
	@Excel(name = "交易柜员", width = 20)
    @ApiModelProperty(value = "tellNo")
	private String tellNo;
	/**账号*/
	@Excel(name = "账号", width = 20)
    @ApiModelProperty(value = "账号")
	private String accNo;
	/**卡号*/
	@TableField(exist = false)
	private String cardNo;
	/**户名*/
	@Excel(name = "户名", width = 27)
    @ApiModelProperty(value = "户名")
	private String accName;
	/**对方账号*/
	@Excel(name = "对方账号", width = 20)
    @ApiModelProperty(value = "对方账号")
	private String oppAccNo;
	/**对方户名*/
	@Excel(name = "对方户名", width = 27)
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
	@Excel(name = "币种", width = 20)
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
	@Excel(name = "借款标志", width = 20)
    @ApiModelProperty(value = "dbFlag")
	private String dbFlag;
	/**金额*/
	@Excel(name = "金额", width = 12)
    @ApiModelProperty(value = "金额")
	private java.math.BigDecimal amount;
	/**voucherType*/
	@Excel(name = "凭证类型", width = 20)
    @ApiModelProperty(value = "voucherType")
	private String voucherType;
	/**voucherNo*/
	@Excel(name = "凭证号", width = 20)
    @ApiModelProperty(value = "voucherNo")
	private String voucherNo;
	/**authTell*/
	@Excel(name = "授权号", width = 20)
    @ApiModelProperty(value = "authTell")
	private String authTell;
	/**postDate*/
	@Excel(name = "过账日期", width = 20)
    @ApiModelProperty(value = "postDate")
	private String postDate;
	/**terminalNo*/
	@Excel(name = "终端号", width = 20)
    @ApiModelProperty(value = "terminalNo")
	private String terminalNo;
	/**txStatus*/
	@Excel(name = "交易状态", width = 20)
    @ApiModelProperty(value = "txStatus")
	private String txStatus;
	/**账号余额*/
	@Excel(name = "账号余额", width = 12)
    @ApiModelProperty(value = "账号余额")
	private java.math.BigDecimal balance;
	/**cashTots*/
	@Excel(name = "现金总额", width = 20)
    @ApiModelProperty(value = "cashTots")
	private String cashTots;
	/**authTellName*/
	@Excel(name = "授权柜员名称", width = 20)
    @ApiModelProperty(value = "authTellName")
	private String authTellName;
	/**channel*/
	@Excel(name = "渠道", width = 20)
    @ApiModelProperty(value = "channel")
	private String channel;
	/**correction*/
	@Excel(name = "冲正标志", width = 20)
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
	@Excel(name = "原流水号", width = 20)
    @ApiModelProperty(value = "origJrnl")
	private String origJrnl;
	/**recNo*/
	@Excel(name = "记录号", width = 20)
    @ApiModelProperty(value = "recNo")
	private String recNo;
	/**tranChannel*/
	@Excel(name = "交易渠道", width = 20)
    @ApiModelProperty(value = "tranChannel")
	private String tranChannel;
	/**startSerialNo*/
	@Excel(name = "凭证起始号码", width = 20)
	@ApiModelProperty(value = "startSerialNo")
	private String startSerialNo;
	/**凭证终止号码*/
	@Excel(name = "凭证终止号码", width = 20)
	@ApiModelProperty(value = "endSerialNo")
	private String endSerialNo;
	/**dtnum*/
/*	@Excel(name = "dtnum", width = 20)
    @ApiModelProperty(value = "dtnum")
	private Integer dtnum;*/
	/**batchNo*/
	@Excel(name = "提示码", width = 20)
    @ApiModelProperty(value = "batchNo")
	private String batchNo;
	/**mnemonic*/
	@Excel(name = "摘要", width = 20)
    @ApiModelProperty(value = "mnemonic")
	private String mnemonic;
	/**legalNo*/
	@Excel(name = "法人标志", width = 20)
	@ApiModelProperty(value = "legalNo")
	private String legalNo;

}

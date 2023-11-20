package org.cmms.modules.sjxf.qtxt.ednxyxt.xyfkqylsb.entity;

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
 * @Description: 协议付款签约流水表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Tgacs_nps_zfxygl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tgacs_nps_zfxygl对象", description="协议付款签约流水表")
public class Xyfkqylsb {
    
	/**txDate*/
	@Excel(name = "txDate", width = 15)
    @ApiModelProperty(value = "txDate")
	private String txDate;
	/**txTime*/
	@Excel(name = "txTime", width = 15)
    @ApiModelProperty(value = "txTime")
	private String txTime;
	/**traceNo*/
	@Excel(name = "traceNo", width = 15)
    @ApiModelProperty(value = "traceNo")
	private Integer traceNo;
	/**npsDate*/
	@Excel(name = "npsDate", width = 15)
    @ApiModelProperty(value = "npsDate")
	private String npsDate;
	/**msgid*/
	@Excel(name = "msgid", width = 15)
    @ApiModelProperty(value = "msgid")
	private String msgid;
	/**brno*/
	@Excel(name = "brno", width = 15)
    @ApiModelProperty(value = "brno")
	private String brno;
	/**tel*/
	@Excel(name = "tel", width = 15)
    @ApiModelProperty(value = "tel")
	private String tel;
	/**sndNpsBrno*/
	@Excel(name = "sndNpsBrno", width = 15)
    @ApiModelProperty(value = "sndNpsBrno")
	private String sndNpsBrno;
	/**rcvNpsBrno*/
	@Excel(name = "rcvNpsBrno", width = 15)
    @ApiModelProperty(value = "rcvNpsBrno")
	private String rcvNpsBrno;
	/**sndNpsName*/
	@Excel(name = "sndNpsName", width = 15)
    @ApiModelProperty(value = "sndNpsName")
	private String sndNpsName;
	/**rcvNpsName*/
	@Excel(name = "rcvNpsName", width = 15)
    @ApiModelProperty(value = "rcvNpsName")
	private String rcvNpsName;
	/**manageType*/
	@Excel(name = "manageType", width = 15)
    @ApiModelProperty(value = "manageType")
	private String manageType;
	/**protocolno*/
	@Excel(name = "protocolno", width = 15)
    @ApiModelProperty(value = "protocolno")
	private String protocolno;
	/**customerNum*/
	@Excel(name = "customerNum", width = 15)
    @ApiModelProperty(value = "customerNum")
	private String customerNum;
	/**merchant*/
	@Excel(name = "merchant", width = 15)
    @ApiModelProperty(value = "merchant")
	private String merchant;
	/**channel*/
	@Excel(name = "channel", width = 15)
    @ApiModelProperty(value = "channel")
	private String channel;
	/**flag*/
	@Excel(name = "flag", width = 15)
    @ApiModelProperty(value = "flag")
	private String flag;
	/**payAcc*/
	@Excel(name = "payAcc", width = 15)
    @ApiModelProperty(value = "payAcc")
	private String payAcc;
	/**payAccName*/
	@Excel(name = "payAccName", width = 15)
    @ApiModelProperty(value = "payAccName")
	private String payAccName;
	/**payAccType*/
	@Excel(name = "payAccType", width = 15)
    @ApiModelProperty(value = "payAccType")
	private String payAccType;
	/**payNpsBrno*/
	@Excel(name = "payNpsBrno", width = 15)
    @ApiModelProperty(value = "payNpsBrno")
	private String payNpsBrno;
	/**payNpsName*/
	@Excel(name = "payNpsName", width = 15)
    @ApiModelProperty(value = "payNpsName")
	private String payNpsName;
	/**certtype*/
	@Excel(name = "certtype", width = 15)
    @ApiModelProperty(value = "certtype")
	private String certtype;
	/**certno*/
	@Excel(name = "certno", width = 15)
    @ApiModelProperty(value = "certno")
	private String certno;
	/**phone*/
	@Excel(name = "phone", width = 15)
    @ApiModelProperty(value = "phone")
	private String phone;
	/**cardvaildate*/
	@Excel(name = "cardvaildate", width = 15)
    @ApiModelProperty(value = "cardvaildate")
	private String cardvaildate;
	/**cvv2*/
	@Excel(name = "cvv2", width = 15)
    @ApiModelProperty(value = "cvv2")
	private String cvv2;
	/**messageid*/
	@Excel(name = "messageid", width = 15)
    @ApiModelProperty(value = "messageid")
	private String messageid;
	/**msgverifycode*/
	@Excel(name = "msgverifycode", width = 15)
    @ApiModelProperty(value = "msgverifycode")
	private String msgverifycode;
	/**pyeAcc*/
	@Excel(name = "pyeAcc", width = 15)
    @ApiModelProperty(value = "pyeAcc")
	private String pyeAcc;
	/**pyeAccName*/
	@Excel(name = "pyeAccName", width = 15)
    @ApiModelProperty(value = "pyeAccName")
	private String pyeAccName;
	/**pyeNpsBrno*/
	@Excel(name = "pyeNpsBrno", width = 15)
    @ApiModelProperty(value = "pyeNpsBrno")
	private String pyeNpsBrno;
	/**pyeNpsName*/
	@Excel(name = "pyeNpsName", width = 15)
    @ApiModelProperty(value = "pyeNpsName")
	private String pyeNpsName;
	/**customerId*/
	@Excel(name = "customerId", width = 15)
    @ApiModelProperty(value = "customerId")
	private String customerId;
	/**busType*/
	@Excel(name = "busType", width = 15)
    @ApiModelProperty(value = "busType")
	private String busType;
	/**singletranslimit*/
	@Excel(name = "singletranslimit", width = 15)
    @ApiModelProperty(value = "singletranslimit")
	private java.math.BigDecimal singletranslimit;
	/**daytotalcount*/
	@Excel(name = "daytotalcount", width = 15)
    @ApiModelProperty(value = "daytotalcount")
	private String daytotalcount;
	/**daytotallimit*/
	@Excel(name = "daytotallimit", width = 15)
    @ApiModelProperty(value = "daytotallimit")
	private java.math.BigDecimal daytotallimit;
	/**protocolBeginDate*/
	@Excel(name = "protocolBeginDate", width = 15)
    @ApiModelProperty(value = "protocolBeginDate")
	private String protocolBeginDate;
	/**protocolEndDate*/
	@Excel(name = "protocolEndDate", width = 15)
    @ApiModelProperty(value = "protocolEndDate")
	private String protocolEndDate;
	/**sttlDate*/
	@Excel(name = "sttlDate", width = 15)
    @ApiModelProperty(value = "sttlDate")
	private String sttlDate;
	/**stat*/
	@Excel(name = "stat", width = 15)
    @ApiModelProperty(value = "stat")
	private String stat;
	/**rejRespcd*/
	@Excel(name = "rejRespcd", width = 15)
    @ApiModelProperty(value = "rejRespcd")
	private String rejRespcd;
	/**rejReason*/
	@Excel(name = "rejReason", width = 15)
    @ApiModelProperty(value = "rejReason")
	private String rejReason;
	/**addinfo*/
	@Excel(name = "addinfo", width = 15)
    @ApiModelProperty(value = "addinfo")
	private String addinfo;
	/**purpose*/
	@Excel(name = "purpose", width = 15)
    @ApiModelProperty(value = "purpose")
	private String purpose;
	/**reserve1*/
	@Excel(name = "reserve1", width = 15)
    @ApiModelProperty(value = "reserve1")
	private String reserve1;
	/**reserve2*/
	@Excel(name = "reserve2", width = 15)
    @ApiModelProperty(value = "reserve2")
	private String reserve2;
	/**sDate*/
	@Excel(name = "sDate", width = 15)
    @ApiModelProperty(value = "sDate")
	private String sDate;
	/**eDate*/
	@Excel(name = "eDate", width = 15)
    @ApiModelProperty(value = "eDate")
	private String eDate;
	/**loadDate*/
    @ApiModelProperty(value = "loadDate")
	private Date loadDate;
	/**legalNo*/
	@Excel(name = "legalNo", width = 15)
    @ApiModelProperty(value = "legalNo")
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

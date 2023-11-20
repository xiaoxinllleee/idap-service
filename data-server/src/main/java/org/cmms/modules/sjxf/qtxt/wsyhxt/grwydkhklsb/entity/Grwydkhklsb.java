package org.cmms.modules.sjxf.qtxt.wsyhxt.grwydkhklsb.entity;

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
 * @Description: 个人网银贷款还款流水表
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Ibss_pb_loanflow")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibss_pb_loanflow对象", description="个人网银贷款还款流水表")
public class Grwydkhklsb {
    
	/**网银交易流水号*/
	@Excel(name = "网银交易流水号", width = 15)
    @ApiModelProperty(value = "网银交易流水号")
	private String plfFlowno;
	/**网银客户号*/
	@Excel(name = "网银客户号", width = 15)
    @ApiModelProperty(value = "网银客户号")
	private String plfCstno;
	/**贷款序号*/
	@Excel(name = "贷款序号", width = 15)
    @ApiModelProperty(value = "贷款序号")
	private String plfLoanseq;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String plfLoanacc;
	/**贷款户名*/
	@Excel(name = "贷款户名", width = 15)
    @ApiModelProperty(value = "贷款户名")
	private String plfLoanname;
	/**贷款账号开户行*/
	@Excel(name = "贷款账号开户行", width = 15)
    @ApiModelProperty(value = "贷款账号开户行")
	private String plfLoanaccopennode;
	/**贷款网点名称*/
	@Excel(name = "贷款网点名称", width = 15)
    @ApiModelProperty(value = "贷款网点名称")
	private String plfLoanbranchname;
	/**还款账号*/
	@Excel(name = "还款账号", width = 15)
    @ApiModelProperty(value = "还款账号")
	private String plfRcvacc;
	/**还款户名*/
	@Excel(name = "还款户名", width = 15)
    @ApiModelProperty(value = "还款户名")
	private String plfRcvname;
	/**还款开户行*/
	@Excel(name = "还款开户行", width = 15)
    @ApiModelProperty(value = "还款开户行")
	private String plfRcvbank;
	/**还款开户行名称*/
	@Excel(name = "还款开户行名称", width = 15)
    @ApiModelProperty(value = "还款开户行名称")
	private String plfRcvaccname;
	/**贷款、还款金额*/
	@Excel(name = "贷款、还款金额", width = 15)
    @ApiModelProperty(value = "贷款、还款金额")
	private java.math.BigDecimal plfLoanamt;
	/**计息方式*/
	@Excel(name = "计息方式", width = 15)
    @ApiModelProperty(value = "计息方式")
	private String plfIntrsttype;
	/**计息方式*/
	@Excel(name = "计息方式", width = 15)
    @ApiModelProperty(value = "计息方式")
	private String plfPayflag;
	/**类型*/
	@Excel(name = "类型", width = 15)
    @ApiModelProperty(value = "类型")
	private String plfTrantype;
	/**手续费*/
	@Excel(name = "手续费", width = 15)
    @ApiModelProperty(value = "手续费")
	private java.math.BigDecimal plfFee;
	/**用户提交时间*/
	@Excel(name = "用户提交时间", width = 15)
    @ApiModelProperty(value = "用户提交时间")
	private String plfSubtime;
	/**交易状态*/
	@Excel(name = "交易状态", width = 15)
    @ApiModelProperty(value = "交易状态")
	private String plfState;
	/**开户机构号*/
	@Excel(name = "开户机构号", width = 15)
    @ApiModelProperty(value = "开户机构号")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String cifOpennode;
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

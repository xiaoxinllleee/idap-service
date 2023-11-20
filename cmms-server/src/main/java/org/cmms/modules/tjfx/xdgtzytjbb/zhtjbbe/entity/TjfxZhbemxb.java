package org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.entity;

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
 * @Description: 支行表二明细表
 * @Author: jeecg-boot
 * @Date:   2020-02-14
 * @Version: V1.0
 */
@Data
@TableName("TJFX_ZHBEMXB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_ZHBEMXB对象", description="支行表二明细表")
public class TjfxZhbemxb {
    
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**存量日期*/
	@Excel(name = "存量日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "存量日期")
	private Date clrq;
	/**挂片领导*/
	@Excel(name = "挂片领导", width = 15)
    @ApiModelProperty(value = "挂片领导")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String gpld;
	/**责任人*/
	@Excel(name = "责任人", width = 15)
    @ApiModelProperty(value = "责任人")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String zkhjl;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String sszh;
	/**行政村*/
	@Excel(name = "行政村", width = 15)
    @ApiModelProperty(value = "行政村")
	private String xzc;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**存款净增余额*/
	@Excel(name = "存款净增余额", width = 15)
    @ApiModelProperty(value = "存款净增余额")
	private java.math.BigDecimal ckjzye;
	/**存量存款余额*/
	@Excel(name = "存量存款余额", width = 15)
    @ApiModelProperty(value = "存量存款余额")
	private java.math.BigDecimal clckye;
	/**存量户数*/
	@Excel(name = "存量户数", width = 15)
    @ApiModelProperty(value = "存量户数")
	private Long clhs;
	/**当前存款余额*/
	@Excel(name = "当前存款余额", width = 15)
    @ApiModelProperty(value = "当前存款余额")
	private java.math.BigDecimal dyckye;
	/**当前存款户数*/
	@Excel(name = "当前存款户数", width = 15)
    @ApiModelProperty(value = "当前存款户数")
	private Long dyhs;
	/**贷款净增余额*/
	@Excel(name = "贷款净增余额", width = 15)
    @ApiModelProperty(value = "贷款净增余额")
	private java.math.BigDecimal dkjzye;
	/**当前贷款余额*/
	@Excel(name = "当前贷款余额", width = 15)
    @ApiModelProperty(value = "当前贷款余额")
	private java.math.BigDecimal dydkye;
	/**当前不良贷款余额*/
	@Excel(name = "当前不良贷款余额", width = 15)
    @ApiModelProperty(value = "当前不良贷款余额")
	private java.math.BigDecimal dybldkye;
	/**当前贷款户数*/
	@Excel(name = "当前贷款户数", width = 15)
    @ApiModelProperty(value = "当前贷款户数")
	private Long dydkhs;
	/**不良下降金额*/
	@Excel(name = "不良下降金额", width = 15)
    @ApiModelProperty(value = "不良下降金额")
	private java.math.BigDecimal blxjje;
	/**存量贷款余额*/
	@Excel(name = "存量贷款余额", width = 15)
    @ApiModelProperty(value = "存量贷款余额")
	private java.math.BigDecimal cldkye;
	/**存量不良贷款余额*/
	@Excel(name = "存量不良贷款余额", width = 15)
    @ApiModelProperty(value = "存量不良贷款余额")
	private java.math.BigDecimal clbldkye;
	/**存量贷款户数*/
	@Excel(name = "存量贷款户数", width = 15)
    @ApiModelProperty(value = "存量贷款户数")
	private Long cldkhs;
	/**手机银行户数*/
	@Excel(name = "手机银行户数", width = 15)
    @ApiModelProperty(value = "手机银行户数")
	private Long dysjyhhs;
	/**存量手机银行户数*/
	@Excel(name = "存量手机银行户数", width = 15)
    @ApiModelProperty(value = "存量手机银行户数")
	private Long clsjyhhs;
	/**当前etc户数*/
	@Excel(name = "当前etc户数", width = 15)
    @ApiModelProperty(value = "当前etc户数")
	private Long dyetchs;
	/**存量etc户数*/
	@Excel(name = "存量etc户数", width = 15)
    @ApiModelProperty(value = "存量etc户数")
	private Long cletchs;
	/**存量表外不良贷款余额*/
	@Excel(name = "存量表外不良贷款余额", width = 15)
    @ApiModelProperty(value = "存量表外不良贷款余额")
	private Long clbwbldkye;
	/**当前表外不良贷款余额*/
	@Excel(name = "当前表外不良贷款余额", width = 15)
    @ApiModelProperty(value = "当前表外不良贷款余额")
	private Long dybwbldkye;
	/**手机银行开户日期*/
	@Excel(name = "手机银行开户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "手机银行开户日期")
	private Date sjyhkhrq;
	/**etc开户日期*/
	@Excel(name = "etc开户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "etc开户日期")
	private Date zzetckhrq;
	/**最早借款日期*/
	@Excel(name = "最早借款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最早借款日期")
	private Date zzjkrq;
	/**最早开户日期*/
	@Excel(name = "最早开户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最早开户日期")
	private Date zzkhrq;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15)
    @ApiModelProperty(value = "是否户主")
	private String sfhz;
	/**区分标识*/
	@Excel(name = "区分标识", width = 15)
    @ApiModelProperty(value = "区分标识")
	private String qfbs;
	/**表外不良下降金额*/
	@Excel(name = "表外不良下降金额", width = 15)
    @ApiModelProperty(value = "表外不良下降金额")
	private java.math.BigDecimal bwblxjje;
}

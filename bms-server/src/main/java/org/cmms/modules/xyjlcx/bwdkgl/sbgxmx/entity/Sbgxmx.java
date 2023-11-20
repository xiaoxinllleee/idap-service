package org.cmms.modules.xyjlcx.bwdkgl.sbgxmx.entity;

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
 * @Description: 收本挂息明细
 * @Author: jeecg-boot
 * @Date:   2021-08-16
 * @Version: V1.0
 */
@Data
@TableName("Credit_sbgxmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Credit_sbgxmx对象", description="收本挂息明细")
public class Sbgxmx {
    
	/**业务机构*/
	@Excel(name = "业务机构", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "业务机构")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String ywjg;
	/**填报日期*/
	@Excel(name = "填报日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "填报日期")
	private Date tbrq;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String zh;
	/**借款人姓名*/
	@Excel(name = "借款人姓名", width = 15)
    @ApiModelProperty(value = "借款人姓名")
	private String jkrxm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "借款日期")
	private Date jkrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private Date dqrq;
	/**手工计息挂息金额*/
	@Excel(name = "手工计息挂息金额", width = 15)
    @ApiModelProperty(value = "手工计息挂息金额")
	private java.math.BigDecimal gxje;
	/**手工计息挂息余额*/
	@Excel(name = "手工计息挂息余额", width = 15)
    @ApiModelProperty(value = "手工计息挂息余额")
	private java.math.BigDecimal gxye;
	/**核销利息挂息余额*/
	@Excel(name = "核销利息挂息金额", width = 15)
    @ApiModelProperty(value = "核销利息挂息金额")
	private java.math.BigDecimal hxlxgxje;
	/**hxlxgxye*/
	@Excel(name = "核销利息挂息余额", width = 15)
    @ApiModelProperty(value = "核销利息挂息余额")
	private java.math.BigDecimal hxlxgxye;
	/**挂息金额合计*/
	@Excel(name = "挂息金额合计", width = 15)
    @ApiModelProperty(value = "挂息金额合计")
	private java.math.BigDecimal gxjehj;
	/**挂息余额合计*/
	@Excel(name = "挂息余额合计", width = 15)
    @ApiModelProperty(value = "挂息余额合计")
	private java.math.BigDecimal gxyehj;
	/**情况说明*/
	@Excel(name = "情况说明", width = 15)
    @ApiModelProperty(value = "情况说明")
	private String qksm;
	/**录入人*/
	@ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标识*/
	@ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
}

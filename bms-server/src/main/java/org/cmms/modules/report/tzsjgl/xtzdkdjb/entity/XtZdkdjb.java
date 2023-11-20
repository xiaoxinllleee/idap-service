package org.cmms.modules.report.tzsjgl.xtzdkdjb.entity;

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
 * @Description: 再贷款登记簿
 * @Author: jeecg-boot
 * @Date:   2022-08-23
 * @Version: V1.0
 */
@Data
@TableName("REP_TZGL_XT_ZDKDJB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="REP_TZGL_XT_ZDKDJB对象", description="再贷款登记簿")
public class XtZdkdjb {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
	private String id;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据日期")
	private java.util.Date sjrq;
	/**发放日期*/
	@Excel(name = "发放日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "发放日期 ")
	private java.util.Date ffrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期 ")
	private java.util.Date dqrq;
	/**发放金额*/
	@Excel(name = "发放金额", width = 15)
    @ApiModelProperty(value = "发放金额 ")
	private java.math.BigDecimal ffje;
	/**余额*/
	@Excel(name = "余额", width = 15)
    @ApiModelProperty(value = "余额 ")
	private java.math.BigDecimal ye;
	/**再贷款利率*/
	@Excel(name = "再贷款利率", width = 15)
    @ApiModelProperty(value = "再贷款利率 ")
	private java.math.BigDecimal zdkll;
	/**年利率上限*/
	@Excel(name = "（我行贷款发放）年利率上限", width = 15)
    @ApiModelProperty(value = "（我行贷款发放）年利率上限 ")
	private java.math.BigDecimal nllsx;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号 ")
	private java.lang.String hth;
	/**是否归还*/
	@Excel(name = "是否归还", width = 15)
    @ApiModelProperty(value = "是否归还 ")
	private java.lang.String sfgh;
	/**押品*/
	@Excel(name = "押品", width = 15)
    @ApiModelProperty(value = "押品 ")
	private java.lang.String xp;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注 ")
	private java.lang.String bz;
	/**本月日均*/
	@Excel(name = "本月日均", width = 15)
    @ApiModelProperty(value = "本月日均 ")
	private java.math.BigDecimal byrj;
	/**备注*/
	@Excel(name = "备注1", width = 15)
	@ApiModelProperty(value = "备注1 ")
	private java.lang.String bz1;
	/**基准日期*/
	@Excel(name = "基准日期", width = 15,format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "基准日期")
	private java.util.Date jzrq;
	/**剩余天数*/
	@Excel(name = "剩余天数", width = 15)
    @ApiModelProperty(value = "剩余天数 ")
	private java.lang.String syts;

}

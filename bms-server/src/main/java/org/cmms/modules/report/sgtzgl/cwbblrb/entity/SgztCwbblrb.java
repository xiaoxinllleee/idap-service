package org.cmms.modules.report.sgtzgl.cwbblrb.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 财务报表利润表
 * @Author: jeecg-boot
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_cwxyslrb_bwb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_cwxyslrb_bwb对象", description="财务报表利润表-本外币")
public class SgztCwbblrb {



	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
	@ApiModelProperty(value = "数据日期")
	private String sjrq;
	/**项目*/
	@Excel(name = "项目", width = 15)
    @ApiModelProperty(value = "项目")
	private String xm;
	/**行次*/
	@Excel(name = "行次", width = 15)
    @ApiModelProperty(value = "行次")
	private String hc;
	/**本期金额*/
	@Excel(name = "本期金额", width = 15)
    @ApiModelProperty(value = "本期金额")
	private java.math.BigDecimal bqje;
	/**上年同期金额*/
	@Excel(name = "上年同期金额", width = 15)
    @ApiModelProperty(value = "上年同期金额")
	//@ExcelVerify(interHandler = true)
	private java.math.BigDecimal sntqje;

	/**创建时间*/
	@Excel(name = "创建时间", width = 15)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;

}

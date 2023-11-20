package org.cmms.modules.tjbb.ckywfx.dqfdtj.entity;

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
 * @Description: 定期分段统计
 * @Author: jeecg-boot
 * @Date:   2021-08-20
 * @Version: V1.0
 */
@Data
@TableName("TJBB_CKYW_DQFDTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJBB_CKYW_DQFDTJ对象", description="定期分段统计")
public class Dqfdtj {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**(0-10000]余额*/
	@Excel(name = "0-1万(含)余额", width = 15)
    @ApiModelProperty(value = "0-1万(含)余额")
	private java.math.BigDecimal fd1ye;
	/**(0-10000]笔数*/
	@Excel(name = "0-1万(含)笔数", width = 15)
    @ApiModelProperty(value = "0-1万(含)笔数")
	private Integer fd1bs;
	/**(10000-20000]余额*/
	@Excel(name = "1万-2万(含)余额", width = 15)
    @ApiModelProperty(value = "1万-2万(含)余额")
	private java.math.BigDecimal fd2ye;
	/**(10000-20000]笔数*/
	@Excel(name = "1万-2万(含)笔数", width = 15)
    @ApiModelProperty(value = "1万-2万(含)笔数")
	private Integer fd2bs;
	/**(20000-50000]余额*/
	@Excel(name = "2万-5万(含)余额", width = 15)
    @ApiModelProperty(value = "2万-5万(含)余额")
	private java.math.BigDecimal fd3ye;
	/**(20000-50000]笔数*/
	@Excel(name = "2万-5万(含)笔数", width = 15)
    @ApiModelProperty(value = "2万-5万(含)笔数")
	private Integer fd3bs;
	/**(50000-100000]余额*/
	@Excel(name = "5万-10万(含)余额", width = 15)
    @ApiModelProperty(value = "5万-10万(含)余额")
	private java.math.BigDecimal fd4ye;
	/**(50000-100000]笔数*/
	@Excel(name = "5万-10万(含)笔数", width = 15)
    @ApiModelProperty(value = "5万-10万(含)笔数")
	private Integer fd4bs;
	/**(100000-200000]余额*/
	@Excel(name = "10万-20万(含)余额", width = 15)
    @ApiModelProperty(value = "10万-20万(含)余额")
	private java.math.BigDecimal fd5ye;
	/**(100000-200000]笔数*/
	@Excel(name = "10万-20万(含)笔数", width = 15)
    @ApiModelProperty(value = "10万-20万(含)笔数")
	private Integer fd5bs;
	/**(200000-500000]余额*/
	@Excel(name = "20万-50万(含)余额", width = 15)
    @ApiModelProperty(value = "20万-50万(含)余额")
	private java.math.BigDecimal fd6ye;
	/**(200000-500000]笔数*/
	@Excel(name = "20万-50万(含)笔数", width = 15)
    @ApiModelProperty(value = "20万-50万(含)笔数")
	private Integer fd6bs;
	/**(500000-1000000]余额*/
	@Excel(name = "50万-100万(含)余额", width = 15)
    @ApiModelProperty(value = "50万-100万(含)余额")
	private java.math.BigDecimal fd7ye;
	/**(500000-1000000]笔数*/
	@Excel(name = "50万-100万(含)笔数", width = 15)
    @ApiModelProperty(value = "50万-100万(含)笔数")
	private Integer fd7bs;
	/**1000000以上余额*/
	@Excel(name = "100万以上余额", width = 15)
    @ApiModelProperty(value = "100万以上余额")
	private java.math.BigDecimal fd8ye;
	/**1000000以上笔数*/
	@Excel(name = "100万以上笔数", width = 15)
    @ApiModelProperty(value = "100万以上笔数")
	private Integer fd8bs;
	/**录入标识*/
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
	@ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
}

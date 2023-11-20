package org.cmms.modules.tjbb.dkywfx.dkkhfxb.entity;

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
 * @Description: 贷款客户分析表
 * @Author: jeecg-boot
 * @Date:   2021-08-24
 * @Version: V1.0
 */
@Data
@TableName("TJBB_DKYW_DKKHFXB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJBB_DKYW_DKKHFXB对象", description="贷款客户分析表")
public class Dkkhfxb {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**贷款客户类型(1-对私;2-对公)*/
	@Excel(name = "客户类型", width = 15,dicCode = "zhlx")
	@ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "zhlx")
	private Integer dkkhlx;
	/**户数*/
	@Excel(name = "户数", width = 15)
	@ApiModelProperty(value = "户数")
	private Integer dkhs;
	/**余额*/
	@Excel(name = "余额", width = 15)
	@ApiModelProperty(value = "余额")
	private java.math.BigDecimal dkye;
	/**户数较上月增减*/
	/**户数较年初增减*/
	@Excel(name = "户数较年初增减", width = 15)
	@ApiModelProperty(value = "户数较年初增减")
	private Integer hsjncZj;
	/**户数较年初占比*/
	@Excel(name = "户数较年初占比", width = 15)
	@ApiModelProperty(value = "户数较年初占比")
	private java.math.BigDecimal hsjncZb;
	/**余额较年初增减*/
	@Excel(name = "余额较年初增减", width = 15)
	@ApiModelProperty(value = "余额较年初增减")
	private Integer yejncZj;
	/**余额较年初占比*/
	@Excel(name = "余额较年初占比", width = 15)
	@ApiModelProperty(value = "余额较年初占比")
	private java.math.BigDecimal yejncZb;
	@Excel(name = "户数较上月增减", width = 15)
    @ApiModelProperty(value = "户数较上月增减")
	private Integer hsjsyZj;
	/**户数较上月占比*/
	@Excel(name = "户数较上月占比", width = 15)
    @ApiModelProperty(value = "户数较上月占比")
	private java.math.BigDecimal hsjsyZb;
	/**余额较上月增减*/
	@Excel(name = "余额较上月增减", width = 15)
    @ApiModelProperty(value = "余额较上月增减")
	private Integer yejsyZj;
	/**余额较上月占比*/
	@Excel(name = "余额较上月占比", width = 15)
    @ApiModelProperty(value = "余额较上月占比")
	private java.math.BigDecimal yejsyZb;
	/**录入标识(0-导入;1-录入;2-修改)*/
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

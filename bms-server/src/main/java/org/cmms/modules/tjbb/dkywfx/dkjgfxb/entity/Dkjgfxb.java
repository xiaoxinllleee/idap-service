package org.cmms.modules.tjbb.dkywfx.dkjgfxb.entity;

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
 * @Description: 贷款结构分析表
 * @Author: jeecg-boot
 * @Date:   2021-08-23
 * @Version: V1.0
 */
@Data
@TableName("TJBB_DKYW_DKJGFXB_ZH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJBB_DKYW_DKJGFXB_ZH对象", description="贷款结构分析表")
public class Dkjgfxb {
    
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**金额区间*/
	@Excel(name = "金额区间", width = 15,dicCode = "dkjgfx_jeqj")
    @ApiModelProperty(value = "金额区间")
	@Dict(dicCode = "dkjgfx_jeqj")
	private Integer jeqj;
	/**上年年底户数*/
	@Excel(name = "上年年底户数", width = 15)
    @ApiModelProperty(value = "上年年底户数")
	private Integer snndHs;
	/**上年年底金额*/
	@Excel(name = "上年年底金额", width = 15)
    @ApiModelProperty(value = "上年年底金额")
	private java.math.BigDecimal snndJe;
	/**上年年底余额*/
	@Excel(name = "上年年底余额", width = 15)
    @ApiModelProperty(value = "上年年底余额")
	private java.math.BigDecimal snndYe;
	/**当日户数*/
	@Excel(name = "当日户数", width = 15)
    @ApiModelProperty(value = "当日户数")
	private Integer drHs;
	/**当日金额*/
	@Excel(name = "当日金额", width = 15)
    @ApiModelProperty(value = "当日金额")
	private java.math.BigDecimal drJe;
	/**当日余额*/
	@Excel(name = "当日余额", width = 15)
    @ApiModelProperty(value = "当日余额")
	private java.math.BigDecimal drYe;
	/**户数较年初增减*/
	@Excel(name = "户数较年初增减", width = 15)
    @ApiModelProperty(value = "户数较年初增减")
	private Integer hsjncZj;
	/**户数较年初占比*/
	@Excel(name = "户数较年初占比(%)", width = 15)
    @ApiModelProperty(value = "户数较年初占比(%)")
	private java.math.BigDecimal hsjncZb;
	/**余额较年初增减*/
	@Excel(name = "余额较年初增减", width = 15)
    @ApiModelProperty(value = "余额较年初增减")
	private Integer yejncZj;
	/**余额较年初占比*/
	@Excel(name = "余额较年初占比(%)", width = 15)
    @ApiModelProperty(value = "余额较年初占比(%)")
	private java.math.BigDecimal yejncZb;
	/**户数较上月增减*/
	@Excel(name = "户数较上月增减", width = 15)
    @ApiModelProperty(value = "户数较上月增减")
	private Integer hsjsyZj;
	/**户数较上月占比*/
	@Excel(name = "户数较上月占比(%)", width = 15)
    @ApiModelProperty(value = "户数较上月占比(%)")
	private java.math.BigDecimal hsjsyZb;
	/**余额较上月增减*/
	@Excel(name = "余额较上月增减", width = 15)
    @ApiModelProperty(value = "余额较上月增减")
	private Integer yejsyZj;
	/**余额较上月占比*/
	@Excel(name = "余额较上月占比(%)", width = 15)
    @ApiModelProperty(value = "余额较上月占比(%)")
	private java.math.BigDecimal yejsyZb;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标识(0-导入;1-录入;2-修改)*/
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
}

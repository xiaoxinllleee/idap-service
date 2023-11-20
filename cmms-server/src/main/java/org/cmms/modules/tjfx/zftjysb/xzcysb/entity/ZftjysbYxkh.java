package org.cmms.modules.tjfx.zftjysb.xzcysb.entity;

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
 * @Description: 走访统计验收表-用信客户
 * @Author: jeecg-boot
 * @Date:   2021-09-02
 * @Version: V1.0
 */
@Data
@TableName("tjfx_zftjysb_yxkh")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_zftjysb_yxkh对象", description="走访统计验收表-用信客户")
public class ZftjysbYxkh {
    
	/**所属乡镇*/
	@Excel(name = "所属乡镇", width = 15, dicCode="dybh", dictTable="YXDYGL_YJYXDYGL", dicText="dymc")
    @ApiModelProperty(value = "所属乡镇")
	@Dict( dicCode="dybh", dictTable="YXDYGL_YJYXDYGL", dicText="dymc")
	private String ssxz;
	/**行政村*/
	@Excel(name = "行政村", width = 15, dicCode="dybh", dictTable="YXDYGL_EJYXDYGL", dicText="dymc")
    @ApiModelProperty(value = "行政村")
	@Dict( dicCode="dybh", dictTable="YXDYGL_EJYXDYGL", dicText="dymc")
	private String xzc;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;

	/**年初用信户数*//*
	@Excel(name = "年初用信户数", width = 15)
	@ApiModelProperty(value = "年初用信户数")
	private Integer clhs;
	*//**用信金额*//*
	@Excel(name = "用信金额", width = 15)
	@ApiModelProperty(value = "用信金额")
	private java.math.BigDecimal yxje;

	*//**存量用信金额*//*
	@Excel(name = "存量用信金额", width = 15)
	@ApiModelProperty(value = "存量用信金额")
	private java.math.BigDecimal clyxje;

	*//**增量用信金额*//*
	@Excel(name = "增量用信金额", width = 15)
	@ApiModelProperty(value = "增量用信金额")
	private java.math.BigDecimal zlyxje;*/

	/**录入标志*/
//	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private String lrbz;
	/**录入时间*/
//	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
//	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
}

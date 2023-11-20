package org.cmms.modules.tjfx.zfsjtj.nhzfjdtj.entity;

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
 * @Description: 农户走访进度统计
 * @Author: jeecg-boot
 * @Date:   2023-02-22
 * @Version: V1.0
 */
@Data
@TableName("v_tjfx_zfjdtj_nh")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_tjfx_zfjdtj_nh对象", description="农户走访进度统计")
public class Nhzfjdtj {
    
	/**tjrq*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**zzbz*/
	@Excel(name = "组织标志", width = 15, dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
    @ApiModelProperty(value = "组织标志")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String zzbz;
	/**qnljhs*/
	@Excel(name = "前年累计户数", width = 15)
	@ApiModelProperty(value = "前年累计户数")
	private Integer qnljhs;
	/**snljhs*/
	@Excel(name = "去年累计户数", width = 15)
    @ApiModelProperty(value = "去年累计户数")
	private Integer snljhs;
	/**yxzfhs*/
	@Excel(name = "较年初", width = 15)
	@ApiModelProperty(value = "较年初")
	private Integer yxzfhs;
	/**dqljhs*/
	@Excel(name = "当前累计户数", width = 15)
    @ApiModelProperty(value = "当前累计户数")
	private Integer dqljhs;
	/**rw*/
	@Excel(name = "当年任务", width = 15)
    @ApiModelProperty(value = "当年任务")
	private java.math.BigDecimal rw;
	/**wcl*/
	@Excel(name = "完成率(%)", width = 15)
    @ApiModelProperty(value = "完成率(%)")
	private Integer wcl;
}

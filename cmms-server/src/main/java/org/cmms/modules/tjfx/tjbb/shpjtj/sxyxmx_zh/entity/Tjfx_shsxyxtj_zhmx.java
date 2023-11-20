package org.cmms.modules.tjfx.tjbb.shpjtj.sxyxmx_zh.entity;

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
 * @Description: 支行授信用信统计
 * @Author: jeecg-boot
 * @Date:   2020-11-05
 * @Version: V1.0
 */
@Data
@TableName("TJFX_SHSXYXTJ_ZHMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_SHSXYXTJ_ZHMX对象", description="支行授信用信统计")
public class Tjfx_shsxyxtj_zhmx {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String sszh;
	/**授信额度*/
	@Excel(name = "授信额度", width = 15)
    @ApiModelProperty(value = "授信额度")
	private java.math.BigDecimal sxed;
	/**用信额度*/
	@Excel(name = "用信额度", width = 15)
    @ApiModelProperty(value = "用信额度")
	private java.math.BigDecimal yxed;
	/**预授信额度*/
	@Excel(name = "预授信额度", width = 15)
    @ApiModelProperty(value = "预授信额度")
	private java.math.BigDecimal ysxed;
	/**预授信户数*/
	@Excel(name = "预授信户数", width = 15)
    @ApiModelProperty(value = "预授信户数")
	private Long ysxhs;
	/**用信户数*/
	@Excel(name = "用信户数", width = 15)
    @ApiModelProperty(value = "用信户数")
	private Long yxhs;
	/**总户数*/
	@Excel(name = "总户数", width = 15)
    @ApiModelProperty(value = "总户数")
	private Long zhs;
	/**授信户数*/
	@Excel(name = "授信户数", width = 15)
    @ApiModelProperty(value = "授信户数")
	private Long sxhs;
	/**授信工作评议人数*/
	@Excel(name = "授信工作评议人数", width = 15)
    @ApiModelProperty(value = "授信工作评议人数")
	private Long sxgzpyrs;
}

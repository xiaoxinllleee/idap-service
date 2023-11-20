package org.cmms.modules.tjfx.xdxtsxsjmx.xdxtkhsxtjqh.entity;

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
 * @Description: 全行授信用信统计
 * @Author: jeecg-boot
 * @Date:   2020-08-11
 * @Version: V1.0
 */
@Data
@TableName("TJFX_XDXTKHSXYXTJ_QH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_XDXTKHSXYXTJ_QH对象", description="全行授信用信统计")
public class TjfxXdxtkhsxyctjQh {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**授信额度*/
	@Excel(name = "授信额度", width = 15)
    @ApiModelProperty(value = "授信额度")
	private java.math.BigDecimal sxed;
	/**用信额度*/
	@Excel(name = "用信额度", width = 15)
    @ApiModelProperty(value = "用信额度")
	private java.math.BigDecimal yxed;
	/**用信户数*/
	@Excel(name = "用信户数", width = 15)
    @ApiModelProperty(value = "用信户数")
	private Long yxhs;
	/**总户数*/
	@Excel(name = "总户数", width = 15)
    @ApiModelProperty(value = "总户数")
	private Long zhs;
	/**合同可用金额*/
	@Excel(name = "合同可用金额", width = 15)
    @ApiModelProperty(value = "合同可用金额")
	private java.math.BigDecimal htkyje;
	/**授信户数*/
	@Excel(name = "授信户数", width = 15)
    @ApiModelProperty(value = "授信户数")
	private Long sxhs;
}

package org.cmms.modules.tjfx.zfsjtj.sxyxtj.shsxmx.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 商户授信明细
 * @Author: jeecg-boot
 * @Date:   2022-06-07
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_sxmx_sh对象", description="商户授信明细")
public class ShsxmxExp {

	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**网格编号*/
	@Excel(name = "网格名称", width = 15, dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	@ApiModelProperty(value = "网格编号")
	private String wgbh;
	/**机构代码*/
	@Excel(name = "机构名称", width = 15, dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**shmc*/
	@Excel(name = "商户名称", width = 15)
	@ApiModelProperty(value = "商户名称")
	private String shmc;
	/**tyshxydm*/
	@Excel(name = "统一社会信用代码", width = 15)
	@ApiModelProperty(value = "统一社会信用代码")
	private String tyshxydm;
	/**fddbr*/
	@Excel(name = "法定代表人", width = 15)
	@ApiModelProperty(value = "法定代表人")
	private String fddbr;
	/**授信金额*/
	@Excel(name = "授信金额", width = 15)
	@ApiModelProperty(value = "授信金额")
	private java.math.BigDecimal sxje;

}

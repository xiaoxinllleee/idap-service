package org.cmms.modules.tjfx.zftjysb.rwsz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @Description: 走访统计验收表-任务设置
 * @Author: jeecg-boot
 * @Date:   2021-09-01
 * @Version: V1.0
 */
@Data
@TableName("tjfx_zftjysb_rwsz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_zftjysb_rwsz对象", description="走访统计验收表-任务设置")
public class ZftjysbRwszImport {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**组织标志*/
	@Excel(name = "所属支行", width = 15, dicCode="zzbz", dictTable="v_hr_bas_organization_cmms", dicText="zzjc")
	@ApiModelProperty(value = "组织标志")
	@Dict(dicCode="zzbz", dictTable="v_hr_bas_organization_cmms", dicText="zzjc")
	private String zzbz;
	/**行政村*/
	@Excel(name = "行政村", width = 15, dicCode = "wgbh",dictTable = "yxdygl_main", dicText = "wgmc")
    @ApiModelProperty(value = "行政村")
	@Dict(dicCode = "wgbh",dictTable = "yxdygl_main", dicText = "wgmc")
	private String xzc;
}

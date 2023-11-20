package org.cmms.modules.sbxj.fxzdxj.entity;

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
 * @Description: 福祥站点主表
 * @Author: jeecg-boot
 * @Date:   2023-06-29
 * @Version: V1.0
 */
@Data
@TableName("khywxx_fxzdxj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khywxx_fxzdxj对象", description="福祥站点主表")
public class KhywxxFxzdxjImportVO {


	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String sszh;

	/**站点名称*/
	@Excel(name = "站点名称", width = 15)
    @ApiModelProperty(value = "站点名称")
	private String zdmc;
	/**入网时间*/
	@Excel(name = "入网时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "入网时间")
	private Date rwsj;

	/**站点负责人*/
	@Excel(name = "站点负责人", width = 15)
    @ApiModelProperty(value = "站点负责人")
	private String zdfzr;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
	private String lxdh;
	/**地址*/
	@Excel(name = "地址", width = 15)
	@ApiModelProperty(value = "地址")
	private String dz;
}

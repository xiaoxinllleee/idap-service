package org.cmms.modules.tjfx.khjdfgl.entity;

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
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-12-09
 * @Version: V1.0
 */
@Data
@TableName("TJFX_JDFGLTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_JDFGLTJ对象", description="1")
public class Tjfx_jdfgltj {

    /**统计月份*/
    @Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
    private Date tjyf;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15, dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
    @ApiModelProperty(value = "组织标志")
	@Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String zzbz;
	/**营销单元名称*/
	@Excel(name = "营销单元名称", width = 15, dicCode="QYBM", dictTable="YXDYGL_CZXXGL", dicText="VILLAGE || ORGANIZE")
    @ApiModelProperty(value = "营销单元名称")
	@Dict(dicCode="QYBM", dictTable="YXDYGL_CZXXGL", dicText="VILLAGE || ORGANIZE")
	private String yxdymc;
	/**建档月份*/
	@Excel(name = "建档月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "建档月份")
	private Date jdyf;
	/**月建档人数*/
	@Excel(name = "月建档人数", width = 15)
    @ApiModelProperty(value = "月建档人数")
	private Long yjdrs;
	/**累计建档人数*/
	@Excel(name = "累计建档人数", width = 15)
    @ApiModelProperty(value = "累计建档人数")
	private Long ljjdrs;
	/**单元总人数*/
	@Excel(name = "单元总人数", width = 15)
    @ApiModelProperty(value = "单元总人数")
	private Long dyzrs;
	/**累计建档覆盖率*/
	@Excel(name = "累计建档覆盖率", width = 15)
    @ApiModelProperty(value = "累计建档覆盖率")
	private java.math.BigDecimal ljjdfgl;
}

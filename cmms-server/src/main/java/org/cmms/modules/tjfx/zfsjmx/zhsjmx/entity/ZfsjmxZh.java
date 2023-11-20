package org.cmms.modules.tjfx.zfsjmx.zhsjmx.entity;

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
 * @Description: 支行走访数据明细统计
 * @Author: jeecg-boot
 * @Date:   2020-03-20
 * @Version: V1.0
 */
@Data
@TableName("TJFX_ZFSJMX_ZH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_ZFSJMX_ZH对象", description="支行走访数据明细统计")
public class ZfsjmxZh {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
    @ApiModelProperty(value = "所属支行")
    @Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String sszh;
	/**有效走访户数*/
	@Excel(name = "有效走访户数", width = 15)
    @ApiModelProperty(value = "有效走访户数")
	private Long yxzfhs;
	/**总户数*/
	@Excel(name = "总户数", width = 15)
    @ApiModelProperty(value = "总户数")
	private Long zhs;
	/**走访覆盖率*/
	@Excel(name = "走访覆盖率", width = 15)
    @ApiModelProperty(value = "走访覆盖率")
	private java.math.BigDecimal zffgl;
	/**累计走访户数*/
	@Excel(name = "累计走访户数", width = 15)
	@ApiModelProperty(value = "累计走访户数")
	private Long ljzfhs;
	/**涉及走访户数*/
	@Excel(name = "涉及走访户数", width = 15)
	@ApiModelProperty(value = "涉及走访户数")
	private Long sjzfhs;
	/**有效走访户数排名*/
	@Excel(name = "有效走访户数排名", width = 15)
	@ApiModelProperty(value = "有效走访户数排名")
	private Long yxzfhspm;

}

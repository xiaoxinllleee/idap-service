package org.cmms.modules.tjbb.tjfz.sgtzdr.dklxsr.entity;

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
 * @Description: 贷款利息收入
 * @Author: Penghr
 * @Date:   2022-12-28
 * @Version: V1.0
 */
@Data
@TableName("tjbb_sgtz_dklxsr")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjbb_sgtz_dklxsr对象", description="贷款利息收入")
public class Dklxsr {

	/**数据年份*/
	@Excel(name = "数据月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据月份")
	private Date dataDate;
	/**机构编码*/
	@Excel(name = "网点名称", width = 15, dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构编码")
	@Dict(dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	private String jgbm;
	/**机构名称*/
	//@Excel(name = "机构名称", width = 15)
	@ApiModelProperty(value = "机构名称")
	private String jgmc;

	/**1132应收贷款利息*/
	@Excel(name = "1132应收贷款利息", width = 15)
    @ApiModelProperty(value = "1132应收贷款利息")
	private java.math.BigDecimal ysdklx;
	/**6011利息收入*/
	@Excel(name = "6011利息收入", width = 15)
    @ApiModelProperty(value = "6011利息收入")
	private java.math.BigDecimal lxsr;

	/**录入标识(0 导入 1 录入 2 修改)*/
	//@Excel(name = "录入标识(0 导入 1 录入 2 修改)", width = 15)
    @ApiModelProperty(value = "录入标识(0 导入 1 录入 2 修改)")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
	//@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	//@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}

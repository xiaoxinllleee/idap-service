package org.cmms.modules.jylrhs.jylrsj.fysjbl.entity;

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
 * @Description: 经营利润核算（费用数据补录）
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
@Data
@TableName("jylrhs_fysjbl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="jylrhs_fysjbl对象", description="经营利润核算（费用数据补录）")
public class JylrhsFysjbl {

	/**会计日期*/
	@Excel(name = "会计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "会计日期")
	private Date fiscalDate;
	/**业务机构*/
	@Excel(name = "业务机构", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	@ApiModelProperty(value = "业务机构")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**指标ID*/
	@Excel(name = "指标名称", width = 15, dicCode = "zbid", dictTable = "jylrhs_zbk", dicText = "zbmc", ds = "jylrhs")
    @ApiModelProperty(value = "指标ID")
	@Dict(dicCode = "zbid", dictTable = "jylrhs_zbk", dicText = "zbmc", ds = "jylrhs")
	private String zbid;
	/**金额*/
	@Excel(name = "金额", width = 15)
    @ApiModelProperty(value = "金额")
	private java.math.BigDecimal je;
	/**调整类型*/
	@Excel(name = "调整类型", width = 15, dicCode = "tzlx")
    @ApiModelProperty(value = "调整类型")
	@Dict(dicCode = "tzlx")
	private String tzlx;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**操作员*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "操作员")
	private String operator;
	/**操作类型*/
	@Excel(name = "操作类型", width = 15, dicCode = "lrbz")
	@ApiModelProperty(value = "操作类型")
	@Dict(dicCode = "lrbz")
	private String oprationType;
	/**操作时间*/
	@Excel(name = "录入/修改时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "操作时间")
	private Date oprationTime;
}

package org.cmms.modules.jylrhs.csgl.khsz.entity;

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
 * @Description: 经营利润核算（考核设置）
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
@Data
@TableName("jylrhs_khsz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="jylrhs_khsz对象", description="经营利润核算（考核设置）")
public class JylrhsKhsz {

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
	/**考核周期*/
	@Excel(name = "考核周期", width = 15, dicCode = "rqwd")
	@ApiModelProperty(value = "考核周期")
	@Dict(dicCode = "rqwd")
	private String khzq;
	/**记账类型*/
	//@Excel(name = "记账类型", width = 15)
    @ApiModelProperty(value = "记账类型")
	private String jzlx;
	/**计提比率*/
	@Excel(name = "计提比率", width = 15)
    @ApiModelProperty(value = "计提比率")
	private java.math.BigDecimal jtbl;
	/**参考利率*/
	@Excel(name = "参考利率", width = 15)
    @ApiModelProperty(value = "参考利率")
	private java.math.BigDecimal ckll;
	/**调剂系数*/
	@Excel(name = "调剂系数", width = 15)
    @ApiModelProperty(value = "调剂系数")
	private java.math.BigDecimal tjxs;
	/**操作类型*/
	//@Excel(name = "操作类型", width = 15, dicCode = "lrbz")
	@ApiModelProperty(value = "操作类型")
	@Dict(dicCode = "lrbz")
	private String oprationType;
	/**操作员*/
	//@Excel(name = "操作员", width = 15)
	@ApiModelProperty(value = "操作员")
	private String operator;
	/**操作时间*/
	//@Excel(name = "录入/修改时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "操作时间")
	private Date oprationTime;
}

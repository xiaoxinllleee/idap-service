package org.cmms.modules.ywgl.cdkfx.khjldkffhstj.entity;

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
 * @Description: 客户经理贷款发放收回统计
 * @Author: jeecg-boot
 * @Date:   2021-06-29
 * @Version: V1.0
 */
@Data
@TableName("v_V_KHJLKH_DKFFSHQK")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_V_KHJLKH_DKFFSHQK对象", description="客户经理贷款发放收回统计")
public class KhjLkhdkffshqkVo {

	@Excel(name = "支行代码",width = 15)
	@Dict(dicCode = "ywjgdm",dictTable = "v_hr_bas_organization",dicText = "ZZJC")
	private String zhjgdm;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
	@ApiModelProperty(value = "客户经理标识")
	@Dict(dicCode = "khjlbh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String custid;
	/**tjyf*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "tjyf")
	private Date tjyf;
	/**beginday*/
	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "beginday")
	private Date beginday;
	/**endday*/
	@Excel(name = "endday", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "endday")
	private Date endday;
	/**dnffysh*/
	@Excel(name = "dnffysh", width = 15)
    @ApiModelProperty(value = "dnffysh")
	private java.math.BigDecimal dnffysh;
	/**dnffwsh*/
	@Excel(name = "dnffwsh", width = 15)
    @ApiModelProperty(value = "dnffwsh")
	private java.math.BigDecimal dnffwsh;
	/**dnffyish*/
	@Excel(name = "dnffyish", width = 15)
    @ApiModelProperty(value = "dnffyish")
	private java.math.BigDecimal dnffyish;
	/**dnffdqshl*/
	@Excel(name = "dnffdqshl", width = 15)
    @ApiModelProperty(value = "dnffdqshl")
	private java.math.BigDecimal dnffdqshl;
	/**yqnffysh*/
	@Excel(name = "yqnffysh", width = 15)
    @ApiModelProperty(value = "yqnffysh")
	private java.math.BigDecimal yqnffysh;
	/**yqnffwsh*/
	@Excel(name = "yqnffwsh", width = 15)
    @ApiModelProperty(value = "yqnffwsh")
	private java.math.BigDecimal yqnffwsh;
	/**yqnffyish*/
	@Excel(name = "yqnffyish", width = 15)
    @ApiModelProperty(value = "yqnffyish")
	private java.math.BigDecimal yqnffyish;
	/**yqnffdqshl*/
	@Excel(name = "yqnffdqshl", width = 15)
    @ApiModelProperty(value = "yqnffdqshl")
	private java.math.BigDecimal yqnffdqshl;
	/**lrsj*/
	@Excel(name = "lrsj", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "lrsj")
	private Date lrsj;
	/**lrbz*/
	@Excel(name = "lrbz", width = 15)
    @ApiModelProperty(value = "lrbz")
	private Integer lrbz;
	/**lrczy*/
	@Excel(name = "lrczy", width = 15)
    @ApiModelProperty(value = "lrczy")
	private String lrczy;
	/**ffysh*/
	@Excel(name = "ffysh", width = 15)
    @ApiModelProperty(value = "ffysh")
	private java.math.BigDecimal ffysh;
	/**ffwsh*/
	@Excel(name = "ffwsh", width = 15)
    @ApiModelProperty(value = "ffwsh")
	private java.math.BigDecimal ffwsh;
	/**ffyish*/
	@Excel(name = "ffyish", width = 15)
    @ApiModelProperty(value = "ffyish")
	private java.math.BigDecimal ffyish;
	/**ffdqshl*/
	@Excel(name = "ffdqshl", width = 15)
    @ApiModelProperty(value = "ffdqshl")
	private java.math.BigDecimal ffdqshl;

}

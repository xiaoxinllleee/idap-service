package org.cmms.modules.ywgl.ywl.gyywltjtz.entity;

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
 * @Description: 柜员业务量统计调整
 * @Author: jeecg-boot
 * @Date:   2021-09-30
 * @Version: V1.0
 */
@Data
@TableName("ERP_YWL_GYYWLTJ_TZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_YWL_GYYWLTJ_TZ对象", description="柜员业务量统计调整")
public class Gyywltjtz {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15)
    @ApiModelProperty(value = "组织标志")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**岗位标志*/
	@Excel(name = "岗位标志", width = 15)
    @ApiModelProperty(value = "岗位标志")
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private Integer gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String yggh;
	/**网点情况*/
	@Excel(name = "网点情况", width = 15)
    @ApiModelProperty(value = "网点情况")
	private String wdqk;
	/**所在网点月平均业务量*/
	@Excel(name = "所在网点月平均业务量", width = 15)
    @ApiModelProperty(value = "所在网点月平均业务量")
	private java.math.BigDecimal szwdpjywl;
	/**柜员月累计业务量*/
	@Excel(name = "柜员月累计业务量", width = 15)
    @ApiModelProperty(value = "柜员月累计业务量")
	private java.math.BigDecimal gyyljywl;
	/**手工调整业务量*/
	@Excel(name = "手工调整业务量", width = 15)
    @ApiModelProperty(value = "手工调整业务量")
	private java.math.BigDecimal sgtzywl;
	/**所在网点月平均现金流量*/
	@Excel(name = "所在网点月平均现金流量", width = 15)
    @ApiModelProperty(value = "所在网点月平均现金流量")
	private java.math.BigDecimal szwdypjxjll;
	/**柜员月累计现金流量*/
	@Excel(name = "柜员月累计现金流量", width = 15)
    @ApiModelProperty(value = "柜员月累计现金流量")
	private java.math.BigDecimal gyyljxjll;
	/**手工调整现金流量*/
	@Excel(name = "手工调整现金流量", width = 15)
    @ApiModelProperty(value = "手工调整现金流量")
	private java.math.BigDecimal sgtzxjll;
	/**lrbz*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**lrr*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**lrsj*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}

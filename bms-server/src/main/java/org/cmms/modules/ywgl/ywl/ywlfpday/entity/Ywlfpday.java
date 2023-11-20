package org.cmms.modules.ywgl.ywl.ywlfpday.entity;

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
 * @Description: 业务量分配
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@Data
@TableName("ERP_ASSESS_YWLMX_JGDFP")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_ASSESS_YWLMX_JGDFP对象", description="业务量分配")
public class Ywlfpday {
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "组织标志")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**待分配ATM业务笔数*/
	@Excel(name = "待分配ATM业务笔数", width = 15)
	@ApiModelProperty(value = "待分配ATM业务笔数")
	private java.math.BigDecimal atmywbs;
	/**待分配ATM现金流量*/
	@Excel(name = "待分配ATM现金流量", width = 15)
	@ApiModelProperty(value = "待分配ATM现金流量")
	private java.math.BigDecimal atmxjll;
	/**待分配其它业务笔数*/
	@Excel(name = "待分配其它业务笔数", width = 15)
	@ApiModelProperty(value = "待分配其它业务笔数")
	private java.math.BigDecimal qtywbs;
	/**待分配其它现金流量*/
	@Excel(name = "待分配其它现金流量", width = 15)
	@ApiModelProperty(value = "待分配其它现金流量")
	private java.math.BigDecimal qtxjll;
	/**分配状态*/
	@Excel(name = "分配状态", width = 15,dicCode = "fpzt")
	@ApiModelProperty(value = "分配状态")
	@Dict(dicCode = "fpzt")
	private Integer fpzt;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;



	/**分配数据ID*/
    @ApiModelProperty(value = "分配数据ID")
	private Long fpid;
	/**机构代码*/
    @ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**分配时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "分配时间")
	private Date fpsj;
	/**分配操作员*/
    @ApiModelProperty(value = "分配操作员")
	private String fpczy;
}

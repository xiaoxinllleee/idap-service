package org.cmms.modules.ywgl.ywl.ywltqytj.entity;

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
 * @Description: 业务量提取与统计
 * @Author: jeecg-boot
 * @Date:   2021-09-29
 * @Version: V1.0
 */
@Data
@TableName("ERP_ASSESS_YWLTJ_YG")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_ASSESS_YWLTJ_YG对象", description="业务量提取与统计")
public class Ywltqytj {

	/**组织标志*/
	@Excel(name = "组织名称", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "组织名称")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**员工姓名*/
	@Excel(name = "员工姓名", width = 15,dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
    @ApiModelProperty(value = "员工姓名")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String yggh;
	/**员工岗位*/
	@Excel(name = "岗位名称", width = 15,dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
    @ApiModelProperty(value = "岗位名称")
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private Integer yggw;
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**业务量组织标志*/
	@Excel(name = "业务量组织标志", width = 15)
    @ApiModelProperty(value = "业务量组织标志")
	private String ywlzzbz;
	/**自身业务笔数*/
	@Excel(name = "自身业务笔数", width = 15)
    @ApiModelProperty(value = "自身业务笔数")
	private java.math.BigDecimal zsywbs;
	/**原始分配现金流量*/
	@Excel(name = "原始分配现金流量", width = 15)
	@ApiModelProperty(value = "原始分配现金流量")
	private java.math.BigDecimal ysfpxjll;
	/**原始分配业务笔数*/
	@Excel(name = "原始分配业务笔数", width = 15)
    @ApiModelProperty(value = "原始分配业务笔数")
	private java.math.BigDecimal ysfpywbs;
	/**机构分配业务笔数*/
	@Excel(name = "机构分配业务笔数", width = 15)
	@ApiModelProperty(value = "机构分配业务笔数")
	private java.math.BigDecimal jgfpywbs;
	/**机构分配现金流量*/
	@Excel(name = "机构分配现金流量", width = 15)
	@ApiModelProperty(value = "机构分配现金流量")
	private java.math.BigDecimal jgfpxjll;
	/**ATM分配业务笔数*/
	@Excel(name = "ATM分配业务笔数", width = 15)
    @ApiModelProperty(value = "ATM分配业务笔数")
	private java.math.BigDecimal atmfpywbs;
	/**ATM分配现金流量*/
	@Excel(name = "ATM分配现金流量", width = 15)
    @ApiModelProperty(value = "ATM分配现金流量")
	private java.math.BigDecimal atmfpxjll;
	/**总业务笔数*/
	@Excel(name = "总业务笔数", width = 15)
    @ApiModelProperty(value = "总业务笔数")
	private java.math.BigDecimal zywbs;
	/**总现金流量*/
	@Excel(name = "总现金流量", width = 15)
    @ApiModelProperty(value = "总现金流量")
	private java.math.BigDecimal zxjll;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**自身现金流量*/
	@ApiModelProperty(value = "自身现金流量")
	private java.math.BigDecimal zsxjll;

}

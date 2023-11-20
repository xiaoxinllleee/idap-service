package org.cmms.modules.ywgl.ywl.ywlfp.entity;

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
 * @Date:   2021-09-30
 * @Version: V1.0
 */
@Data
@TableName("ERP_ASSESS_YWLMX_JGFPMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_ASSESS_YWLMX_JGFPMX对象", description="业务量分配")
public class Ywlfp {
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**组织标志*/
	@Excel(name = "组织名称", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "组织名称")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**员工工号*/
	@Excel(name = "员工姓名", width = 15,dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
    @ApiModelProperty(value = "员工姓名")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String yggh;
	/**员工岗位*/
	@Excel(name = "员工岗位", width = 15,dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	@ApiModelProperty(value = "员工岗位")
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private String yggw;
	/**机构分配笔数*/
	@Excel(name = "机构分配笔数", width = 15)
	@ApiModelProperty(value = "机构分配笔数")
	private java.math.BigDecimal jgfpywbs;
	/**机构分配现金流量*/
	@Excel(name = "机构分配现金流量", width = 15)
	@ApiModelProperty(value = "机构分配现金流量")
	private java.math.BigDecimal jgfpxjll;
	/**ATM分配笔数*/
	@Excel(name = "ATM分配笔数", width = 15)
	@ApiModelProperty(value = "ATM分配笔数")
	private java.math.BigDecimal atmfpywbs;
	/**ATM分配现金流量*/
	@Excel(name = "ATM分配现金流量", width = 15)
	@ApiModelProperty(value = "ATM分配现金流量")
	private java.math.BigDecimal atmfpxjll;
	/**分配操作员*/
	@Excel(name = "分配操作员", width = 15)
	@ApiModelProperty(value = "分配操作员")
	private String fpczy;
	/**分配时间*/
	@Excel(name = "分配时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "分配时间")
	private Date fpsj;


	/**机构代码*/
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**员工的组织标志*/
	@Excel(name = "员工的组织标志", width = 15)
    @ApiModelProperty(value = "员工的组织标志")
	private String ygzzbz;
	/**分配数据ID*/
    @ApiModelProperty(value = "分配数据ID")
	private Long fpid;
}

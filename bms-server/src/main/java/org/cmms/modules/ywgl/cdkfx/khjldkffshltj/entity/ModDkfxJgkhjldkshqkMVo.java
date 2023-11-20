package org.cmms.modules.ywgl.cdkfx.khjldkffshltj.entity;

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
 * @Date:   2021-07-07
 * @Version: V1.0
 */
@Data
@TableName("V_KHJLKH_DKFFSHQK")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_Mod_dkfx_jgkhjldkshqk_m对象", description="客户经理贷款发放收回统计")
public class ModDkfxJgkhjldkshqkMVo {

	@Excel(name = "支行名称",width = 15,dicCode = "ywjgdm",dictTable = "v_hr_bas_organization",dicText = "ZZJC")
	@Dict(dicCode = "ywjgdm",dictTable = "v_hr_bas_organization",dicText = "ZZJC")
	private String zhjgdm;
	/**机构代码*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**客户经理标识*/
	@Excel(name = "客户经理姓名", width = 15,dicCode = "khjlbh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	@ApiModelProperty(value = "客户经理标识")
	@Dict(dicCode = "khjlbh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String custid;
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "tjyf")
	private Date tjyf;
	/**开始日期*/
	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始日期")
	private Date beginday;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束日期")
	private Date endday;
	/**贷款发放金额(元)*/
	@Excel(name = "贷款发放金额(元)", width = 15)
    @ApiModelProperty(value = "dkffje")
	private java.math.BigDecimal dkffje;
	/**贷款发放笔数*/
	@Excel(name = "贷款发放笔数", width = 15)
    @ApiModelProperty(value = "贷款发放笔数")
	private Integer dkffbs;
	/**贷款发放户数*/
	@Excel(name = "贷款发放户数", width = 15)
    @ApiModelProperty(value = "贷款发放户数")
	private Integer dkffhs;
	/**贷款收回本金（元）*/
	@Excel(name = "贷款收回本金（元）", width = 15)
	@ApiModelProperty(value = "贷款收回本金（元）")
	private java.math.BigDecimal shbj;
	/**贷款收回利息（元）*/
	@Excel(name = "贷款收回利息（元）", width = 15)
    @ApiModelProperty(value = "贷款收回利息（元）")
	private java.math.BigDecimal shlx;
	/**贷款收回笔数*/
	@Excel(name = "贷款收回笔数", width = 15)
    @ApiModelProperty(value = "贷款收回笔数")
	private Integer shbs;
	/**贷款收回户数*/
	@Excel(name = "贷款收回户数", width = 15)
    @ApiModelProperty(value = "贷款收回户数")
	private Integer shhs;
}

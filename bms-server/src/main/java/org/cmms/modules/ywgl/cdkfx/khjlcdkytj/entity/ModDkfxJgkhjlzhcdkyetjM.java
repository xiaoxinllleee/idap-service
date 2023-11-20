package org.cmms.modules.ywgl.cdkfx.khjlcdkytj.entity;

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
 * @Description: 客户经理存贷款月统计
 * @Author: jeecg-boot
 * @Date:   2021-06-25
 * @Version: V1.0
 */
@Data
@TableName("MOD_DKFX_JGKHJLZHCDKYETJ_M")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MOD_DKFX_JGKHJLZHCDKYETJ_M对象", description="客户经理存贷款月统计")
public class ModDkfxJgkhjlzhcdkyetjM {
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	@Excel(name = "支行名称",width = 15,dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
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
	/**统计天数*/
	@Excel(name = "统计天数", width = 15)
    @ApiModelProperty(value = "统计天数")
	private java.math.BigDecimal tjts;
	/**存款余额(元)*/
	@Excel(name = "存款余额(元)", width = 15)
    @ApiModelProperty(value = "存款余额(元)")
	private java.math.BigDecimal ckyezh;
	/**存款日平余额(元)*/
	@Excel(name = "存款日平余额(元)", width = 15)
    @ApiModelProperty(value = "存款日平余额(元)")
	private java.math.BigDecimal ckrpye;
	/**贷款余额(元)*/
	@Excel(name = "贷款余额(元)", width = 15)
    @ApiModelProperty(value = "贷款余额(元)")
	private java.math.BigDecimal dkyezh;
	/**贷款日平余额(元)*/
	@Excel(name = "贷款日平余额(元)", width = 15)
    @ApiModelProperty(value = "贷款日平余额(元)")
	private java.math.BigDecimal dkrpye;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15,dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入操作员*/
	@Excel(name = "录入操作员", width = 15)
    @ApiModelProperty(value = "录入操作员")
	private String lrczy;
	/**贷款当月日平*/
    @ApiModelProperty(value = "贷款当月日平")
	private java.math.BigDecimal dkdyrp;
	/**员工工号*/
	@ApiModelProperty(value = "员工工号")
	private String yggh;
}

package org.cmms.modules.ywgl.cdkfx.khjldkdqshltj.entity;

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
 * @Description: 客户经理贷款到期收回率统计
 * @Author: jeecg-boot
 * @Date:   2021-06-16
 * @Version: V1.0
 */
@Data
@TableName("MOD_DKFX_JGKHJLDKDQSHLKH_M")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MOD_DKFX_JGKHJLDKDQSHLKH_M对象", description="客户经理贷款到期收回率统计")
public class ModDkfxJgkhjldkdqshlkhM {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**支行代码*/
	@Excel(name = "支行名称",width = 15,dicCode = "ywjgdm",dictTable = "v_hr_bas_organization",dicText = "ZZJC")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm",dictTable = "v_hr_bas_organization",dicText = "ZZJC")
	private	String zhjgdm;
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

	/**本年到期贷款*/
	@Excel(name = "本年到期贷款", width = 15)
	@ApiModelProperty(value = "本年到期贷款")
	private java.math.BigDecimal ffysh;
	/**本年到期贷款/本年发放*/
	@Excel(name = "本年发放", width = 15,groupName = "其中")
	@ApiModelProperty(value = "本年发放")
	private java.math.BigDecimal dnffysh;
	/**本年到期贷款/本年前发放*/
	@Excel(name = "本年前发放", width = 15,groupName = "其中")
	@ApiModelProperty(value = "本年前发放")
	private java.math.BigDecimal yqnffysh;

	/**本年到期收回*/
	@Excel(name = "本年到期收回", width = 15)
	@ApiModelProperty(value = "本年到期收回")
	private java.math.BigDecimal ffyish;
	/**本年到期收回/本年发放*/
	@Excel(name = "本年发放", width = 15,groupName = "其中")
	@ApiModelProperty(value = "本年发放")
	private java.math.BigDecimal dnffyish;
	/**本年到期收回/本年前发放*/
	@Excel(name = "本年前发放", width = 15,groupName = "其中")
	@ApiModelProperty(value = "本年前发放")
	private java.math.BigDecimal yqnffyish;

	/**到期贷款收回率*/
	@Excel(name = "到期贷款收回率", width = 15)
	@ApiModelProperty(value = "到期贷款收回率")
	private java.math.BigDecimal ffdqshl;
	/**到期贷款收回率/本年发放*/
	@Excel(name = "本年发放", width = 15,groupName = "其中")
	@ApiModelProperty(value = "本年发放")
	private java.math.BigDecimal dnffdqshl;
	/**到期贷款收回率/其中:本年前发放*/
	@Excel(name = "本年前发放", width = 15,groupName = "其中")
	@ApiModelProperty(value = "本年前发放")
	private java.math.BigDecimal yqnffdqshl;

	/**当年到期收回率扣款*/
	@Excel(name = "当年到期收回率扣款", width = 15)
	@ApiModelProperty(value = "当年到期收回率扣款")
	private java.math.BigDecimal dndqshlkk;
	/**以前年度发放到期收回率扣款*/
	@Excel(name = "以前年度发放到期收回率扣款", width = 15)
	@ApiModelProperty(value = "以前年度发放到期收回率扣款")
	private java.math.BigDecimal yqndffdqshlkk;


	/**beginday*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "beginday")
	private Date beginday;
	/**endday*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "endday")
	private Date endday;
	/**当年发放未收回*/
	@ApiModelProperty(value = "当年发放未收回")
	private java.math.BigDecimal dnffwsh;
	/**其中本年前发放*/
	//@Excel(name = "", width = 15)
	//@ApiModelProperty(value = "其中本年前发放")
	//private java.math.BigDecimal yqnffdqshl;
	/**发放未收回*/
	@ApiModelProperty(value = "发放未收回")
	private java.math.BigDecimal ffwsh;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标志*/
	@ApiModelProperty(value = "录入标志")
	private Integer lrbz;
	/**录入操作员*/
	@ApiModelProperty(value = "录入操作员")
	private String lrczy;


}

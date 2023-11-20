package org.cmms.modules.ywgl.cdkfx.dqhslkh.entity;

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
 * @Description: 到期收回率考核
 * @Author: jeecg-boot
 * @Date:   2021-06-29
 * @Version: V1.0
 */
@Data
@TableName("v_MOD_DKFX_KHJLDKDQSHLKH_M_v")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_MOD_DKFX_KHJLDKDQSHLKH_M_v对象", description="到期收回率考核")
public class VModDkfxKhjldkdqshlkhMV {

	@Excel(name = "支行代码",width = 15)
	@Dict(dicCode = "ywjgdm",dictTable = "v_hr_bas_organization",dicText = "ZZJC")
	private String sjywjgdm;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**custid*/
	@Excel(name = "客户经理标识", width = 15)
	@ApiModelProperty(value = "客户经理标识")
	@Dict(dicCode = "khjlbh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String custid;
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
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
	/**当年发放应收回(元)*/
	@Excel(name = "当年发放应收回(元)", width = 15)
	@ApiModelProperty(value = "当年发放应收回(元)")
	private java.math.BigDecimal dnffysh;
	/**当年发放未收回(元)*/
	@Excel(name = "当年发放未收回(元)", width = 15)
	@ApiModelProperty(value = "当年发放未收回(元)")
	private java.math.BigDecimal dnffwsh;
	/**当年发放已收回(元)*/
	@Excel(name = "当年发放已收回(元)", width = 15)
	@ApiModelProperty(value = "当年发放已收回(元)")
	private java.math.BigDecimal dnffyish;
	/**当年发放到期收回率(%)*/
	@Excel(name = "当年发放到期收回率(%)", width = 15)
	@ApiModelProperty(value = "当年发放到期收回率(%)")
	private java.math.BigDecimal dnffdqshl;
	/**以前年发放应收回(元)*/
	@Excel(name = "以前年发放应收回(元)", width = 15)
	@ApiModelProperty(value = "以前年发放应收回(元)")
	private java.math.BigDecimal yqnffysh;
	/**以前年发放未收回(元)*/
	@Excel(name = "以前年发放未收回(元)", width = 15)
	@ApiModelProperty(value = "以前年发放未收回(元)")
	private java.math.BigDecimal yqnffwsh;
	/**以前年发放已收回(元)*/
	@Excel(name = "以前年发放已收回(元)", width = 15)
	@ApiModelProperty(value = "以前年发放已收回(元)")
	private java.math.BigDecimal yqnffyish;
	/**以前年发放到期收回率(%)*/
	@Excel(name = "以前年发放到期收回率(%)", width = 15)
	@ApiModelProperty(value = "以前年发放到期收回率(%)")
	private java.math.BigDecimal yqnffdqshl;
	/**lrsj*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "lrsj")
	private Date lrsj;
	/**lrbz*/
	@ApiModelProperty(value = "lrbz")
	private Integer lrbz;
	/**lrczy*/
	@ApiModelProperty(value = "lrczy")
	private String lrczy;
	/**发放应收回(元)*/
	@Excel(name = "发放应收回(元)", width = 15)
	@ApiModelProperty(value = "发放应收回(元)")
	private java.math.BigDecimal ffysh;
	/**发放未收回(元)*/
	@Excel(name = "发放未收回(元)", width = 15)
	@ApiModelProperty(value = "发放未收回(元)")
	private java.math.BigDecimal ffwsh;
	/**发放已收回(元)*/
	@Excel(name = "发放已收回(元)", width = 15)
	@ApiModelProperty(value = "发放已收回(元)")
	private java.math.BigDecimal ffyish;
	/**发放到期收回率(%)*/
	@Excel(name = "发放到期收回率(%)", width = 15)
	@ApiModelProperty(value = "发放到期收回率(%)")
	private java.math.BigDecimal ffdqshl;
}

package org.cmms.modules.ckjkpt.jcyj.ckyebd.entity;

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
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@Data
@TableName("ckjkpt_khdebdckjc")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ckjkpt_khdebdckjc对象", description="1")
public class CkjkptCkyebd {

	/**时间起*/
	@Excel(name = "时间起", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "时间起")
	private Date tjyfB;
	/**时间止*/
	@Excel(name = "时间止", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "时间止")
	private Date tjyfE;
	/**机构代码*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**员工工号*/
	@Excel(name = "员工姓名", width = 15,dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	@ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String yggh;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
	@ApiModelProperty(value = "客户姓名")
	private String khxm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**电话号码*/
	@Excel(name = "电话号码", width = 15)
    @ApiModelProperty(value = "电话号码")
	private String dhhm;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String dz;
	/**昨日余额*/
	@Excel(name = "余额(起)", width = 15)
    @ApiModelProperty(value = "昨日余额")
	private java.math.BigDecimal zrye;
	/**当日余额*/
	@Excel(name = "余额(止)", width = 15)
    @ApiModelProperty(value = "当日余额")
	private java.math.BigDecimal drye;
	/**流动金额*/
	@Excel(name = "余额净增", width = 15)
    @ApiModelProperty(value = "流动金额")
	private java.math.BigDecimal ldje;
	/**流动比例*/
	@Excel(name = "余额比（%）", width = 15)
    @ApiModelProperty(value = "流动比例")
	private java.math.BigDecimal ldbl;
	/**增减标识*/
	//@Excel(name = "增减标识", width = 15,dicCode = "zjbz")
    @ApiModelProperty(value = "增减标识")
	@Dict(dicCode = "zjbz")
	private Integer zjbs;
	/**流动标识*/
	@Excel(name = "增减标识", width = 15, dicCode = "zjbz")
    @ApiModelProperty(value = "流动标识")
	@Dict(dicCode = "zjbz")
	private Integer ldbs;
}

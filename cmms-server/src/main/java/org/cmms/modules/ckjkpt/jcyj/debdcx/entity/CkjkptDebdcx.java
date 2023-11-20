package org.cmms.modules.ckjkpt.jcyj.debdcx.entity;

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
 * @Description: 大额变动查询
 * @Author: cmms
 * @Date:   2019-10-10
 * @Version: V1.0
 */
@Data
@TableName("ckjkpt_khjylscx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ckjkpt_khjylscx对象", description="大额变动查询")
public class CkjkptDebdcx {

	/**统计日期*/
	@Excel(name = "交易日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
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
	/**流出金额*/
	@Excel(name = "流出金额", width = 15)
    @ApiModelProperty(value = "流出金额")
	private java.math.BigDecimal lcje;
	/**流入金额*/
	@Excel(name = "流入金额", width = 15)
    @ApiModelProperty(value = "流入金额")
	private java.math.BigDecimal lrje;
	/**净现金流*/
	@Excel(name = "净现金流", width = 15)
	@ApiModelProperty(value = "净现金流")
	private java.math.BigDecimal jxjl;
	/**总现金流*/
	@Excel(name = "总现金流", width = 15)
    @ApiModelProperty(value = "总现金流")
	private java.math.BigDecimal zxjl;
}

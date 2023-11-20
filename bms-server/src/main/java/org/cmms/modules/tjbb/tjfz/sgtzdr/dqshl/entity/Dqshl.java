package org.cmms.modules.tjbb.tjfz.sgtzdr.dqshl.entity;

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
 * @Description: 到期收回率
 * @Author: Penghr
 * @Date:   2022-12-28
 * @Version: V1.0
 */
@Data
@TableName("tjbb_sgtz_dqshl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjbb_sgtz_dqshl对象", description="到期收回率")
public class Dqshl {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date dataDate;
	/**机构编码*/
	@Excel(name = "机构名称", width = 15, dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构编码")
	@Dict(dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	private String jgbm;
	/**机构名称*/
	//@Excel(name = "机构名称", width = 15)
	@ApiModelProperty(value = "机构名称")
	private String jgmc;
	/**当期到期应收回贷款总额*/
	@Excel(name = "当期到期应收回贷款总额", width = 15)
    @ApiModelProperty(value = "当期到期应收回贷款总额")
	private java.math.BigDecimal dqyshdkze;
	/**当期未收回贷款余额*/
	@Excel(name = "当期未收回贷款余额", width = 15)
    @ApiModelProperty(value = "当期未收回贷款余额")
	private java.math.BigDecimal wshdkye;
	/**当期到期贷款收回率(%)*/
	@Excel(name = "当期到期贷款收回率(%)", width = 15)
    @ApiModelProperty(value = "当期到期贷款收回率(%)")
	private java.math.BigDecimal dqdkshl;
	/**录入标识(0 导入 1 录入 2 修改)*/
//	@Excel(name = "录入标识(0 导入 1 录入 2 修改)", width = 15)
    @ApiModelProperty(value = "录入标识(0 导入 1 录入 2 修改)")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
//	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
//	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}

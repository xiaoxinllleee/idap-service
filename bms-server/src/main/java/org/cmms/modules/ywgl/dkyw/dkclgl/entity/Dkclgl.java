package org.cmms.modules.ywgl.dkyw.dkclgl.entity;

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
 * @Description: 贷款存量管理
 * @Author: jeecg-boot
 * @Date:   2021-09-26
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_DKCLGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_DKCLGL对象", description="贷款存量管理")
public class Dkclgl {

	/**存量日期*/
	@Excel(name = "存量日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "存量日期")
	private Date clrq;
	/**不良余额*/
	@Excel(name = "不良余额", width = 15)
    @ApiModelProperty(value = "不良余额")
	private java.math.BigDecimal blye;
	/**季贷款日平余额*/
	@Excel(name = "季贷款日平余额", width = 15)
    @ApiModelProperty(value = "季贷款日平余额")
	private java.math.BigDecimal qdkrpye;
	/**年贷款日平余额*/
	@Excel(name = "年贷款日平余额", width = 15)
    @ApiModelProperty(value = "年贷款日平余额")
	private java.math.BigDecimal ydkrpye;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private Integer lrbz;
	/** 户数*/
	@Excel(name = " 户数", width = 15)
    @ApiModelProperty(value = " 户数")
	private java.math.BigDecimal hs;
	/**组织标识*/
	@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String yggh;
	/**存量年份*/
	@Excel(name = "存量年份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "存量年份")
	private Date clnf;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**月贷款日平余额*/
	@Excel(name = "月贷款日平余额", width = 15)
    @ApiModelProperty(value = "月贷款日平余额")
	private java.math.BigDecimal mdkrpye;
}

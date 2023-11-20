package org.cmms.modules.ywgl.cdkfx.wdcdktj.entity;

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
 * @Description: 网点存贷款统计
 * @Author: jeecg-boot
 * @Date:   2021-06-11
 * @Version: V1.0
 */
@Data
@TableName("Erp_yljc_wdcdktj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Erp_yljc_wdcdktj对象", description="网点存贷款统计")
public class ErpYljcWdcdktj {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**机构代码*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**存款日平余额*/
	@Excel(name = "存款日平余额", width = 15)
    @ApiModelProperty(value = "存款日平余额")
	private java.math.BigDecimal ckrpye;
	/**贷款日平余额*/
	@Excel(name = "贷款日平余额", width = 15)
    @ApiModelProperty(value = "贷款日平余额")
	private java.math.BigDecimal dkrpye;
	/**存款年日平余额*/
	@Excel(name = "存款年日平余额", width = 15)
    @ApiModelProperty(value = "存款年日平余额")
	private java.math.BigDecimal cknrpye;
	/**贷款年日平余额*/
	@Excel(name = "贷款年日平余额", width = 15)
    @ApiModelProperty(value = "贷款年日平余额")
	private java.math.BigDecimal dknrpye;
	/**上期存款余额*/
	@Excel(name = "上期存款余额", width = 15)
    @ApiModelProperty(value = "上期存款余额")
	private java.math.BigDecimal sqckye;
	/**上期贷款余额*/
	@Excel(name = "上期贷款余额", width = 15)
    @ApiModelProperty(value = "上期贷款余额")
	private java.math.BigDecimal sqdkye;
	/**上期存款日平余额*/
	@Excel(name = "上期存款日平余额", width = 15)
    @ApiModelProperty(value = "上期存款日平余额")
	private java.math.BigDecimal sqckrpye;
	/**上期贷款日平余额*/
	@Excel(name = "上期贷款日平余额", width = 15)
    @ApiModelProperty(value = "上期贷款日平余额")
	private java.math.BigDecimal sqdkrpye;
	/**上期存款年日平余额*/
	@Excel(name = "上期存款年日平余额", width = 15)
    @ApiModelProperty(value = "上期存款年日平余额")
	private java.math.BigDecimal sqcknrpye;
	/**上期贷款年日平余额*/
	@Excel(name = "上期贷款年日平余额", width = 15)
    @ApiModelProperty(value = "上期贷款年日平余额")
	private java.math.BigDecimal sqdknrpye;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标志*/
    @ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
    @ApiModelProperty(value = "录入人")
	private String lrr;
}

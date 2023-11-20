package org.cmms.modules.jx.common.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 机构贷款数据
 * @Author: jeecg-boot
 * @Date:   2021-05-28
 * @Version: V1.0
 */
@Data
@TableName("tb_tjfx_jgdksj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tb_tjfx_jgdksj对象", description="机构贷款数据")
public class TbJjfxJgdksj {
    
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date tjrq;
	/**组织标识*/
	@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	private java.lang.String zzbz;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private java.lang.String jgdm;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
    @ApiModelProperty(value = "机构名称")
	private java.lang.String jgmc;
	/**期末贷款户数*/
	@Excel(name = "期末贷款户数", width = 15)
    @ApiModelProperty(value = "期末贷款户数")
	private java.lang.Long qmdkhs;
	/**年初贷款户数*/
	@Excel(name = "年初贷款户数", width = 15)
    @ApiModelProperty(value = "年初贷款户数")
	private java.lang.Long ncdkhs;
	/**新增贷款户数*/
	@Excel(name = "新增贷款户数", width = 15)
    @ApiModelProperty(value = "新增贷款户数")
	private java.lang.Long xzdkhs;
	/**净增贷款户数*/
	@Excel(name = "净增贷款户数", width = 15)
    @ApiModelProperty(value = "净增贷款户数")
	private java.lang.Long jzdkhs;
	/**期末贷款金额*/
	@Excel(name = "期末贷款金额", width = 15)
    @ApiModelProperty(value = "期末贷款金额")
	private java.math.BigDecimal qmdkje;
	/**年初贷款金额*/
	@Excel(name = "年初贷款金额", width = 15)
    @ApiModelProperty(value = "年初贷款金额")
	private java.math.BigDecimal ncdkje;
	/**新增贷款金额*/
	@Excel(name = "新增贷款金额", width = 15)
    @ApiModelProperty(value = "新增贷款金额")
	private java.math.BigDecimal xzdkje;
	/**期末贷款余额*/
	@Excel(name = "期末贷款余额", width = 15)
    @ApiModelProperty(value = "期末贷款余额")
	private java.math.BigDecimal qmdkye;
	/**年初贷款余额*/
	@Excel(name = "年初贷款余额", width = 15)
    @ApiModelProperty(value = "年初贷款余额")
	private java.math.BigDecimal ncdkye;
	/**新增贷款余额*/
	@Excel(name = "新增贷款余额", width = 15)
    @ApiModelProperty(value = "新增贷款余额")
	private java.math.BigDecimal xzdkye;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	private java.lang.Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private java.util.Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private java.lang.String lrr;
}

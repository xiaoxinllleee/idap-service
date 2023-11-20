package org.cmms.modules.dkjkpt.dksjjk.zhdkshtj.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @Description: 支行贷款收回统计
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@Data
@TableName("DKJKPT_ZHDKSHTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DKJKPT_ZHDKSHTJ对象", description="支行贷款收回统计")
public class Zhdkshtj {

    /**统计月份*/
    @Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
    private Date tjyf;

    /**机构代码*/
    @Excel(name = "机构代码", width = 15,dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
    @ApiModelProperty(value = "机构代码")
    @Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
    private String jgdm;

    /**收回本金*/
    @Excel(name = "收回本金", width = 15)
    @ApiModelProperty(value = "收回本金")
    private java.math.BigDecimal shbj;
    /**收回本金笔数*/
    @Excel(name = "收回本金笔数", width = 15)
    @ApiModelProperty(value = "收回本金笔数")
    private Integer shbjbs;
    /**收回本金户数*/
    @Excel(name = "收回本金户数", width = 15)
    @ApiModelProperty(value = "收回本金户数")
    private Integer shbjhs;
    /**收回利息*/
    @Excel(name = "收回利息", width = 15)
    @ApiModelProperty(value = "收回利息")
    private java.math.BigDecimal shlx;
    /**收回利息笔数*/
    @Excel(name = "收回利息笔数", width = 15)
    @ApiModelProperty(value = "收回利息笔数")
    private Integer shlxbs;
    /**收回利息户数*/
    @Excel(name = "收回利息户数", width = 15)
    @ApiModelProperty(value = "收回利息户数")
    private Integer shlxhs;
	/**收回罚息*/
	@Excel(name = "收回罚息", width = 15)
    @ApiModelProperty(value = "收回罚息")
	private java.math.BigDecimal shfx;
	/**收回罚息笔数*/
	@Excel(name = "收回罚息笔数", width = 15)
    @ApiModelProperty(value = "收回罚息笔数")
	private Integer shfxbs;
	/**收回罚息户数*/
	@Excel(name = "收回罚息户数", width = 15)
    @ApiModelProperty(value = "收回罚息户数")
	private Integer shfxhs;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	private String lrbz;
	/**录入操作员*/
	@Excel(name = "录入操作员", width = 15)
    @ApiModelProperty(value = "录入操作员")
	private String lrr;

}

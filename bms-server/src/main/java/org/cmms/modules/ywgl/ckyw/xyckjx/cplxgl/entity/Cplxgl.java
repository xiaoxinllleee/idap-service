package org.cmms.modules.ywgl.ckyw.xyckjx.cplxgl.entity;

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
 * @Description: 产品类型管理
 * @Author: jeecg-boot
 * @Date:   2021-10-28
 * @Version: V1.0
 */
@Data
@TableName("XYCKJX_CPLXGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="XYCKJX_CPLXGL对象", description="产品类型管理")
public class Cplxgl {
    
	/**产品编号*/
	@Excel(name = "产品编号", width = 15)
    @ApiModelProperty(value = "产品编号")
	private String cpbh;
	/**产品名称*/
	@Excel(name = "产品名称", width = 15)
    @ApiModelProperty(value = "产品名称")
	private String cpmc;
	/**利率名称*/
	@Excel(name = "利率名称", width = 15)
    @ApiModelProperty(value = "利率名称")
	private String xgllmc;
	/**协议利率*/
	@Excel(name = "产品利率", width = 15)
    @ApiModelProperty(value = "产品利率")
	private java.math.BigDecimal xyll;
	/**计息周期*/
	@Excel(name = "计息周期", width = 15)
    @ApiModelProperty(value = "计息周期")
	private String jxzq;
	/**余额基准*/
	@Excel(name = "余额基准", width = 15)
    @ApiModelProperty(value = "余额基准")
	private java.math.BigDecimal yejz;
	/**录入标识(0-导入/1-录入/2-修改)*/
	@Excel(name = "录入标识", width = 15,dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
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
}

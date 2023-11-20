package org.cmms.modules.ywgl.nxt.cssz.entity;

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
 * @Description: 商户评级参数设置
 * @Author: jeecg-boot
 * @Date:   2021-09-22
 * @Version: V1.0
 */
@Data
@TableName("ERP_SHPJ_SHPJCSSZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_SHPJ_SHPJCSSZ对象", description="商户评级参数设置")
public class Shpjcssz {
    
	/**评定类型*/
	@Excel(name = "评定类型", width = 15,dicCode = "pdlx")
    @ApiModelProperty(value = "评定类型")
	@Dict(dicCode = "pdlx")
	private String pdlx;
	/**参数编号*/
	@Excel(name = "参数编号", width = 15)
    @ApiModelProperty(value = "参数编号")
	private String csbh;
	/**参数名称*/
	@Excel(name = "参数名称", width = 15)
    @ApiModelProperty(value = "参数名称")
	private String csmc;
	/**参数值起*/
	@Excel(name = "参数值起", width = 15)
    @ApiModelProperty(value = "参数值起")
	private java.math.BigDecimal cszq;
	/**参数值止*/
	@Excel(name = "参数值止", width = 15)
    @ApiModelProperty(value = "参数值止")
	private java.math.BigDecimal cszz;
}

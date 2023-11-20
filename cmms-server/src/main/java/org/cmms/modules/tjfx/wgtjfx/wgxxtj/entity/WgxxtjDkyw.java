package org.cmms.modules.tjfx.wgtjfx.wgxxtj.entity;

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
 * @Description: 贷款业务
 * @Author: jeecg-boot
 * @Date:   2023-06-29
 * @Version: V1.0
 */
@Data
@TableName("khxxgl_tjfx_wgywtj_dkyw")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khxxgl_tjfx_wgywtj_dkyw对象", description="贷款业务")
public class WgxxtjDkyw {
    
	/**wgbh*/
	@Excel(name = "wgbh", width = 15)
    @ApiModelProperty(value = "wgbh")
	private java.lang.String wgbh;
	/**dkhs*/
	@Excel(name = "dkhs", width = 15)
    @ApiModelProperty(value = "dkhs")
	private java.lang.Integer zhndkhs;
	/**dkje*/
	@Excel(name = "dkje", width = 15)
    @ApiModelProperty(value = "dkje")
	private java.math.BigDecimal zhndkje;
	/**dkye*/
	@Excel(name = "dkye", width = 15)
    @ApiModelProperty(value = "dkye")
	private java.math.BigDecimal zhndkye;
	/**dkje2*/
	@Excel(name = "dkje2", width = 15)
    @ApiModelProperty(value = "dkje2")
	private java.math.BigDecimal zhwdkje;
	/**dkye2*/
	@Excel(name = "dkye2", width = 15)
    @ApiModelProperty(value = "dkye2")
	private java.math.BigDecimal zhwdkye;
	/**qthhs*/
	@Excel(name = "qthhs", width = 15)
    @ApiModelProperty(value = "qthhs")
	private java.lang.Integer zhwdkhs;
}

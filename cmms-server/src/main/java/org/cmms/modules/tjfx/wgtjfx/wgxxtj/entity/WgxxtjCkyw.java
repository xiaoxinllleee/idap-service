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
 * @Description: 存款业务
 * @Author: jeecg-boot
 * @Date:   2023-06-29
 * @Version: V1.0
 */
@Data
@TableName("khxxgl_tjfx_wgywtj_ckyw")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khxxgl_tjfx_wgywtj_ckyw对象", description="存款业务")
public class WgxxtjCkyw {
    
	/**wgbh*/
	@Excel(name = "wgbh", width = 15)
    @ApiModelProperty(value = "wgbh")
	private java.lang.String wgbh;
	/**zhnckkhs*/
	@Excel(name = "zhnckkhs", width = 15)
    @ApiModelProperty(value = "ckhs")
	private java.lang.Integer zhnckkhs;
	/**zhnckye*/
	@Excel(name = "zhnckye", width = 15)
    @ApiModelProperty(value = "zhnckye")
	private java.math.BigDecimal zhnckye;
	/**ckye2*/
	@Excel(name = "zhwckye", width = 15)
    @ApiModelProperty(value = "zhwckye")
	private java.math.BigDecimal zhwckye;
	/**qthhs*/
	@Excel(name = "zhwkhs", width = 15)
    @ApiModelProperty(value = "zhwkhs")
	private java.lang.Integer zhwkhs;
}

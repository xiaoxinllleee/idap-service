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
 * @Description: 表内不良贷款
 * @Author: jeecg-boot
 * @Date:   2023-06-29
 * @Version: V1.0
 */
@Data
@TableName("khxxgl_tjfx_wgywtj_bwbldk")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khxxgl_tjfx_wgywtj_bwbldk对象", description="表内不良贷款")
public class WgxxtjBwbldk {
    
	/**wgbh*/
	@Excel(name = "wgbh", width = 15)
    @ApiModelProperty(value = "wgbh")
	private java.lang.String wgbh;
	/**dkhs*/

	@Excel(name = "表外不良贷款户数", width = 15)
	@ApiModelProperty(value = "表外不良贷款户数")
	private java.lang.Integer bwbldkhs;

	@Excel(name = "表外不良贷款余额", width = 15)
	@ApiModelProperty(value = "表外不良贷款余额")
	private java.lang.Integer bwbldkye;


	@Excel(name = "支行内表外不良贷款户数", width = 15)
    @ApiModelProperty(value = "支行内表外不良贷款户数")
	private java.lang.Integer zhnbwbldkhs;
	/**dkye*/
	@Excel(name = "支行内表外不良贷款余额", width = 15)
    @ApiModelProperty(value = "支行内表外不良贷款余额")
	private java.math.BigDecimal zhnbwbldkye;
	/**dkye2*/
	@Excel(name = "支行外表外不良贷款余额", width = 15)
    @ApiModelProperty(value = "支行外表外不良贷款余额")
	private java.math.BigDecimal zhwbwbldkye;
	/**qthhs*/
	@Excel(name = "支行外表外不良贷款户数", width = 15)
    @ApiModelProperty(value = "支行外表外不良贷款户数")
	private java.lang.Integer zhwbwbldkhs;
}

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
@TableName("khxxgl_tjfx_wgywtj_bnbldk")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khxxgl_tjfx_wgywtj_bnbldk对象", description="表内不良贷款")
public class WgxxtjBnbldk {
    
	/**wgbh*/
	@Excel(name = "wgbh", width = 15)
    @ApiModelProperty(value = "wgbh")
	private java.lang.String wgbh;

	@Excel(name = "表内不良贷款户数", width = 15)
	@ApiModelProperty(value = "表内不良贷款户数")
	private java.lang.Integer bnbldkhs;

	@Excel(name = "表内不良贷款余额", width = 15)
	@ApiModelProperty(value = "表内不良贷款余额")
	private java.lang.Integer bnbldkye;


	/**支行内表内不良贷款户数*/
	@Excel(name = "支行内表内不良贷款户数", width = 15)
    @ApiModelProperty(value = "支行内表内不良贷款户数")
	private java.lang.Integer zhnbnbldkhs;
	/**支行内表内不良贷款余额*/
	@Excel(name = "支行内表内不良贷款余额", width = 15)
    @ApiModelProperty(value = "支行内表内不良贷款余额")
	private java.math.BigDecimal zhnbnbldkye;
	/**支行外表内不良贷款余额*/
	@Excel(name = "支行外表内不良贷款余额", width = 15)
    @ApiModelProperty(value = "支行外表内不良贷款余额")
	private java.math.BigDecimal zhwbnbldkye;
	/**支行外表内不良贷款户数*/
	@Excel(name = "支行外表内不良贷款户数", width = 15)
    @ApiModelProperty(value = "支行外表内不良贷款户数")
	private java.lang.Integer zhwbnbldkhs;
}

package org.cmms.modules.ywgl.cdkfx.sysbascfg.entity;

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
 * @Description: 系统配置参数表
 * @Author: jeecg-boot
 * @Date:   2022-02-22
 * @Version: V1.0
 */
@Data
@TableName("sys_bas_cfg")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="sys_bas_cfg对象", description="系统配置参数表")
public class SysBasCfg {

	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**业务参数代码*/
	@Excel(name = "业务参数代码", width = 15)
    @ApiModelProperty(value = "业务参数代码")
	private String cfgcode;
	/**业务参数名称*/
	@Excel(name = "业务参数名称", width = 15)
    @ApiModelProperty(value = "业务参数名称")
	private String cfgname;
	/**业务参数值*/
	@Excel(name = "业务参数值", width = 15)
    @ApiModelProperty(value = "业务参数值")
	private String cfgvalue;
	/**业务参数值数值*/
	@Excel(name = "业务参数值数值", width = 15)
    @ApiModelProperty(value = "业务参数值数值")
	private java.math.BigDecimal cfgvaluenumber;
	/**业务参数值日期*/
	@Excel(name = "业务参数值日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "业务参数值日期")
	private Date cfgvaluedate;
	/**有效期起*/
	@Excel(name = "有效期起", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "有效期起")
	private Date yxqq;
	/**有效期止，为空时永久有效*/
	@Excel(name = "有效期止，为空时永久有效", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "有效期止，为空时永久有效")
	private Date yxqz;
}

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
 * @Description: 贷款管户汇总信息
 * @Author: jeecg-boot
 * @Date:   2021-05-28
 * @Version: V1.0
 */
@Data
@TableName("tb_tjfx_dkghhzxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tb_tjfx_dkghhzxx对象", description="贷款管户汇总信息")
public class TbTjfxDkghhzxx {
    
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private java.lang.String lrr;
	/**净增管户数*/
	@Excel(name = "净增管户数", width = 15)
    @ApiModelProperty(value = "净增管户数")
	private java.lang.Integer jzghs;
	/**YYYYMMDD按天存储数据*/
	@Excel(name = "YYYYMMDD按天存储数据", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "YYYYMMDD按天存储数据")
	private java.util.Date tjrq;
	/**组织标识*/
	@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	private java.lang.String zzbz;
	/**岗位标识*/
	@Excel(name = "岗位标识", width = 15)
    @ApiModelProperty(value = "岗位标识")
	private java.lang.Integer gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private java.lang.String yggh;
	/**管户数*/
	@Excel(name = "管户数", width = 15)
    @ApiModelProperty(value = "管户数")
	private java.lang.Integer ghs;
	/**月初管户数*/
	@Excel(name = "月初管户数", width = 15)
    @ApiModelProperty(value = "月初管户数")
	private java.lang.Integer ycghs;
	/**年初管户数*/
	@Excel(name = "年初管户数", width = 15)
    @ApiModelProperty(value = "年初管户数")
	private java.lang.Integer ncghs;
	/**管户笔数*/
	private java.lang.Integer ghbs;
	/**月初管户笔数*/
	@Excel(name = "月初管户笔数", width = 15)
    @ApiModelProperty(value = "月初管户笔数")
	private java.lang.Integer ycghbs;
	/**年初管户笔数*/
	@Excel(name = "年初管户笔数", width = 15)
    @ApiModelProperty(value = "年初管户笔数")
	private java.lang.Integer ncghbs;
	/**管户金额*/
	@Excel(name = "管户金额", width = 15)
    @ApiModelProperty(value = "管户金额")
	private java.math.BigDecimal ghje;
	/**月初管户金额*/
	@Excel(name = "月初管户金额", width = 15)
    @ApiModelProperty(value = "月初管户金额")
	private java.math.BigDecimal ycghje;
	/**年初管户金额*/
	@Excel(name = "年初管户金额", width = 15)
    @ApiModelProperty(value = "年初管户金额")
	private java.math.BigDecimal ncghje;
	/**管户余额*/
	@Excel(name = "管户余额", width = 15)
    @ApiModelProperty(value = "管户余额")
	private java.math.BigDecimal ghye;
	/**月初管户余额*/
	@Excel(name = "月初管户余额", width = 15)
    @ApiModelProperty(value = "月初管户余额")
	private java.math.BigDecimal ycghye;
	/**年初管户余额*/
	@Excel(name = "年初管户余额", width = 15)
    @ApiModelProperty(value = "年初管户余额")
	private java.math.BigDecimal ncghye;
	/**管户不良笔数*/
	@Excel(name = "管户不良笔数", width = 15)
    @ApiModelProperty(value = "管户不良笔数")
	private java.lang.Integer ghblbs;
	/**月初管户不良笔数*/
	@Excel(name = "月初管户不良笔数", width = 15)
    @ApiModelProperty(value = "月初管户不良笔数")
	private java.lang.Integer ycghblbs;
	/**年初管户不良笔数*/
	@Excel(name = "年初管户不良笔数", width = 15)
    @ApiModelProperty(value = "年初管户不良笔数")
	private java.lang.Integer ncghblbs;
	/**管户不良余额*/
	@Excel(name = "管户不良余额", width = 15)
    @ApiModelProperty(value = "管户不良余额")
	private java.math.BigDecimal ghblye;
	/**月初管户不良余额*/
	@Excel(name = "月初管户不良余额", width = 15)
    @ApiModelProperty(value = "月初管户不良余额")
	private java.math.BigDecimal ycghblye;
	/**年初管户不良余额*/
	@Excel(name = "年初管户不良余额", width = 15)
    @ApiModelProperty(value = "年初管户不良余额")
	private java.math.BigDecimal ncghblye;
	/**管户不良占比*/
	@Excel(name = "管户不良占比", width = 15)
    @ApiModelProperty(value = "管户不良占比")
	private java.math.BigDecimal ghblzb;
	/**月初管户不良占比*/
	@Excel(name = "月初管户不良占比", width = 15)
    @ApiModelProperty(value = "月初管户不良占比")
	private java.math.BigDecimal ycghblzb;
	/**年初管户不良占比*/
	@Excel(name = "年初管户不良占比", width = 15)
    @ApiModelProperty(value = "年初管户不良占比")
	private java.math.BigDecimal ncghblzb;
	/**表外不良笔数*/
	@Excel(name = "表外不良笔数", width = 15)
    @ApiModelProperty(value = "表外不良笔数")
	private java.lang.Integer bwblbs;
	/**月初表外不良笔数*/
	@Excel(name = "月初表外不良笔数", width = 15)
    @ApiModelProperty(value = "月初表外不良笔数")
	private java.lang.Integer ycbwblbs;
	/**年初表外不良笔数*/
	@Excel(name = "年初表外不良笔数", width = 15)
    @ApiModelProperty(value = "年初表外不良笔数")
	private java.lang.Integer ncbwblbs;
	/**表外不良余额*/
	@Excel(name = "表外不良余额", width = 15)
    @ApiModelProperty(value = "表外不良余额")
	private java.math.BigDecimal bwblye;
	/**年初表外不良余额*/
	@Excel(name = "年初表外不良余额", width = 15)
    @ApiModelProperty(value = "年初表外不良余额")
	private java.math.BigDecimal ncbwblye;
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
}

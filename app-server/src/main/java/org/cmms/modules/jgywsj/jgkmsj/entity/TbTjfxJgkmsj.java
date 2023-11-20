package org.cmms.modules.jgywsj.jgkmsj.entity;

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
 * @Description: 机构科目数据
 * @Author: jeecg-boot
 * @Date:   2021-05-14
 * @Version: V1.0
 */
@Data
@TableName("tb_tjfx_jgkmsj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tb_tjfx_jgkmsj对象", description="机构科目数据")
public class TbTjfxJgkmsj {
    
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**组织标识*/
	@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	private String zzbz;
	/**统计标识*/
	@Excel(name = "统计标识", width = 15)
    @ApiModelProperty(value = "统计标识")
	private String tjbs;
	/**当期余额*/
	@Excel(name = "当期余额", width = 15)
    @ApiModelProperty(value = "当期余额")
	private java.math.BigDecimal dqye;
	/**当期日平余额*/
	@Excel(name = "当期日平余额", width = 15)
    @ApiModelProperty(value = "当期日平余额")
	private java.math.BigDecimal dqrpye;
	/**年初余额*/
	@Excel(name = "年初余额", width = 15)
    @ApiModelProperty(value = "年初余额")
	private java.math.BigDecimal ncye;
	/**年初日平余额*/
	@Excel(name = "年初日平余额", width = 15)
    @ApiModelProperty(value = "年初日平余额")
	private java.math.BigDecimal ncrpye;
	/**低息存款余额*/
	@Excel(name = "低息存款余额", width = 15)
    @ApiModelProperty(value = "低息存款余额")
	private java.math.BigDecimal dxckye;
	/**低息存款余额*/
	@Excel(name = "低息存款余额", width = 15)
    @ApiModelProperty(value = "低息存款余额")
	private java.math.BigDecimal dxckrpye;
	/**录入标识（0：导入 1：录入 2：修改）*/
	@Excel(name = "录入标识（0：导入 1：录入 2：修改）", width = 15)
    @ApiModelProperty(value = "录入标识（0：导入 1：录入 2：修改）")
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

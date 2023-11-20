package org.cmms.modules.tjfx.qhywjd.qhckqk.entity;

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
 * @Description: 全行存款情况
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
@Data
@TableName("TJFX_QHCKQK")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_QHCKQK对象", description="全行存款情况")
public class TjfxQhckqk {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date sjrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String sszh;
	/**当期存款总额*/
	@Excel(name = "当期存款总额", width = 15)
    @ApiModelProperty(value = "当期存款总额")
	private java.math.BigDecimal dqckze;
	/**上月底存款总额*/
	@Excel(name = "上月底存款总额", width = 15)
    @ApiModelProperty(value = "上月底存款总额")
	private java.math.BigDecimal sydckze;
	/**比上月净增减*/
	@Excel(name = "比上月净增减", width = 15)
	@ApiModelProperty(value = "比上月净增减")
	private java.math.BigDecimal bsyjzj;
	/**年初存款总额*/
	@Excel(name = "年初存款总额", width = 15)
    @ApiModelProperty(value = "年初存款总额")
	private java.math.BigDecimal ncckze;
	/**比年初净增减*/
	@Excel(name = "比年初净增减", width = 15)
    @ApiModelProperty(value = "比年初净增减")
	private java.math.BigDecimal bncjzj;
	/**日平*/
	@Excel(name = "日平", width = 15)
    @ApiModelProperty(value = "日平")
	private java.math.BigDecimal rp;
	/**月初日平*/
	@Excel(name = "月初日平", width = 15)
    @ApiModelProperty(value = "月初日平")
	private java.math.BigDecimal ycrp;
	/**较月初日平增减*/
	@Excel(name = "较月初日平增减", width = 15)
    @ApiModelProperty(value = "较月初日平增减")
	private java.math.BigDecimal jycrpzj;
	/**年初日平*/
	@Excel(name = "年初日平", width = 15)
    @ApiModelProperty(value = "年初日平")
	private java.math.BigDecimal ncrp;
	/**较年初日平增减*/
	@Excel(name = "较年初日平增减", width = 15)
    @ApiModelProperty(value = "较年初日平增减")
	private java.math.BigDecimal jncrpzj;
	/**创建者*/
    @ApiModelProperty(value = "创建者")
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
}

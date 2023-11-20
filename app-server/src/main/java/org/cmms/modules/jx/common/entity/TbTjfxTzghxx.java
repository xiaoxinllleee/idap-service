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
 * @Description: 员工拓展管户信息
 * @Author: jeecg-boot
 * @Date:   2021-05-31
 * @Version: V1.0
 */
@Data
@TableName("tb_tjfx_tzghxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tb_tjfx_tzghxx对象", description="员工拓展管户信息")
public class TbTjfxTzghxx {
    
	/**组织标识*/
	@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	private String zzbz;
	/**岗位标识*/
	@Excel(name = "岗位标识", width = 15)
    @ApiModelProperty(value = "岗位标识")
	private Integer gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;
	/**拓展户数*/
	@Excel(name = "拓展户数", width = 15)
    @ApiModelProperty(value = "拓展户数")
	private java.math.BigDecimal tzhs;
	/**拓展笔数*/
	@Excel(name = "拓展笔数", width = 15)
    @ApiModelProperty(value = "拓展笔数")
	private java.math.BigDecimal tzbs;
	/**拓展定期户数*/
	@Excel(name = "拓展定期户数", width = 15)
    @ApiModelProperty(value = "拓展定期户数")
	private java.math.BigDecimal tzdqhs;
	/**拓展定期笔数*/
	@Excel(name = "拓展定期笔数", width = 15)
    @ApiModelProperty(value = "拓展定期笔数")
	private java.math.BigDecimal tzdqbs;
	/**拓展活期户数*/
	@Excel(name = "拓展活期户数", width = 15)
    @ApiModelProperty(value = "拓展活期户数")
	private java.math.BigDecimal tzhqhs;
	/**拓展活期笔数*/
	@Excel(name = "拓展活期笔数", width = 15)
    @ApiModelProperty(value = "拓展活期笔数")
	private java.math.BigDecimal tzhqbs;
	/**拓展余额*/
	@Excel(name = "拓展余额", width = 15)
    @ApiModelProperty(value = "拓展余额")
	private java.math.BigDecimal tzye;
	/**拓展日平*/
	@Excel(name = "拓展日平", width = 15)
    @ApiModelProperty(value = "拓展日平")
	private java.math.BigDecimal tzrp;
	/**拓展定期余额*/
	@Excel(name = "拓展定期余额", width = 15)
    @ApiModelProperty(value = "拓展定期余额")
	private java.math.BigDecimal tzdqye;
	/**拓展活期余额*/
	@Excel(name = "拓展活期余额", width = 15)
    @ApiModelProperty(value = "拓展活期余额")
	private java.math.BigDecimal tzhqye;
	/**拓展定期日平*/
	@Excel(name = "拓展定期日平", width = 15)
    @ApiModelProperty(value = "拓展定期日平")
	private java.math.BigDecimal tzdqrp;
	/**拓展活期日平*/
	@Excel(name = "拓展活期日平", width = 15)
    @ApiModelProperty(value = "拓展活期日平")
	private java.math.BigDecimal tzhqrp;
	/**管户数*/
	@Excel(name = "管户数", width = 15)
    @ApiModelProperty(value = "管户数")
	private java.math.BigDecimal ghs;
	/**管户笔数*/
	@Excel(name = "管户笔数", width = 15)
    @ApiModelProperty(value = "管户笔数")
	private java.math.BigDecimal ghbs;
	/**定期管户数*/
	@Excel(name = "定期管户数", width = 15)
    @ApiModelProperty(value = "定期管户数")
	private java.math.BigDecimal dqghs;
	/**活期管户数*/
	@Excel(name = "活期管户数", width = 15)
    @ApiModelProperty(value = "活期管户数")
	private java.math.BigDecimal hqghs;
	/**定期管户笔数*/
	@Excel(name = "定期管户笔数", width = 15)
    @ApiModelProperty(value = "定期管户笔数")
	private java.math.BigDecimal dqghbs;
	/**活期管户笔数*/
	@Excel(name = "活期管户笔数", width = 15)
    @ApiModelProperty(value = "活期管户笔数")
	private java.math.BigDecimal hqghbs;
	/**管户余额*/
	@Excel(name = "管户余额", width = 15)
    @ApiModelProperty(value = "管户余额")
	private java.math.BigDecimal ghye;
	/**管户日平*/
	@Excel(name = "管户日平", width = 15)
    @ApiModelProperty(value = "管户日平")
	private java.math.BigDecimal ghrp;
	/**管户定期余额*/
	@Excel(name = "管户定期余额", width = 15)
    @ApiModelProperty(value = "管户定期余额")
	private java.math.BigDecimal ghdqye;
	/**管户活期余额*/
	@Excel(name = "管户活期余额", width = 15)
    @ApiModelProperty(value = "管户活期余额")
	private java.math.BigDecimal ghhqye;
	/**管户定期日平*/
	@Excel(name = "管户定期日平", width = 15)
    @ApiModelProperty(value = "管户定期日平")
	private java.math.BigDecimal ghdqrp;
	/**管户活期日平*/
	@Excel(name = "管户活期日平", width = 15)
    @ApiModelProperty(value = "管户活期日平")
	private java.math.BigDecimal ghhqrp;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
}

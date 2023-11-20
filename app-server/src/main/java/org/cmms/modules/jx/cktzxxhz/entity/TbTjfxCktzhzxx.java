package org.cmms.modules.jx.cktzxxhz.entity;

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
 * @Description: 存款拓展汇总表
 * @Author: jeecg-boot
 * @Date:   2021-05-25
 * @Version: V1.0
 */
@Data
@TableName("tb_tjfx_cktzhzxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tb_tjfx_cktzhzxx对象", description="存款拓展汇总表")
public class TbTjfxCktzhzxx {
    
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
	/**定期笔数*/
	@Excel(name = "定期笔数", width = 15)
    @ApiModelProperty(value = "定期笔数")
	private java.lang.Integer dqbs;
	/**年初定期笔数*/
	@Excel(name = "年初定期笔数", width = 15)
    @ApiModelProperty(value = "年初定期笔数")
	private java.lang.Integer ncdqbs;
	/**月初定期笔数*/
	@Excel(name = "月初定期笔数", width = 15)
    @ApiModelProperty(value = "月初定期笔数")
	private java.lang.Integer ycdqbs;
	/**上日定期笔数*/
	@Excel(name = "上日定期笔数", width = 15)
    @ApiModelProperty(value = "上日定期笔数")
	private java.lang.Integer srdqbs;
	/**活期笔数*/
	@Excel(name = "活期笔数", width = 15)
    @ApiModelProperty(value = "活期笔数")
	private java.lang.Integer hqbs;
	/**年初活期笔数*/
	@Excel(name = "年初活期笔数", width = 15)
    @ApiModelProperty(value = "年初活期笔数")
	private java.lang.Integer nchqbs;
	/**月初活期笔数*/
	@Excel(name = "月初活期笔数", width = 15)
    @ApiModelProperty(value = "月初活期笔数")
	private java.lang.Integer ychqbs;
	/**上日活期笔数*/
	@Excel(name = "上日活期笔数", width = 15)
    @ApiModelProperty(value = "上日活期笔数")
	private java.lang.Integer srhqbs;
	/**定期余额*/
	@Excel(name = "定期余额", width = 15)
    @ApiModelProperty(value = "定期余额")
	private java.math.BigDecimal dqye;
	/**年初定期余额*/
	@Excel(name = "年初定期余额", width = 15)
    @ApiModelProperty(value = "年初定期余额")
	private java.math.BigDecimal ncdqye;
	/**月初定期余额*/
	@Excel(name = "月初定期余额", width = 15)
    @ApiModelProperty(value = "月初定期余额")
	private java.math.BigDecimal ycdqye;
	/**上日定期余额*/
	@Excel(name = "上日定期余额", width = 15)
    @ApiModelProperty(value = "上日定期余额")
	private java.math.BigDecimal srdqye;
	/**活期余额*/
	@Excel(name = "活期余额", width = 15)
    @ApiModelProperty(value = "活期余额")
	private java.math.BigDecimal hqye;
	/**年初活期余额*/
	@Excel(name = "年初活期余额", width = 15)
    @ApiModelProperty(value = "年初活期余额")
	private java.math.BigDecimal nchqye;
	/**月初活期余额*/
	@Excel(name = "月初活期余额", width = 15)
    @ApiModelProperty(value = "月初活期余额")
	private java.math.BigDecimal ychqye;
	/**上日活期余额*/
	@Excel(name = "上日活期余额", width = 15)
    @ApiModelProperty(value = "上日活期余额")
	private java.math.BigDecimal srhqye;
	/**定期日平*/
	@Excel(name = "定期日平", width = 15)
    @ApiModelProperty(value = "定期日平")
	private java.math.BigDecimal dqrp;
	/**年初定期日平*/
	@Excel(name = "年初定期日平", width = 15)
    @ApiModelProperty(value = "年初定期日平")
	private java.math.BigDecimal ncdqrp;
	/**月初定期日平*/
	@Excel(name = "月初定期日平", width = 15)
    @ApiModelProperty(value = "月初定期日平")
	private java.math.BigDecimal ycdqrp;
	/**上日定期日平*/
	@Excel(name = "上日定期日平", width = 15)
    @ApiModelProperty(value = "上日定期日平")
	private java.math.BigDecimal srdqrp;
	/**活期日平*/
	@Excel(name = "活期日平", width = 15)
    @ApiModelProperty(value = "活期日平")
	private java.math.BigDecimal hqrp;
	/**年初活期日平*/
	@Excel(name = "年初活期日平", width = 15)
    @ApiModelProperty(value = "年初活期日平")
	private java.math.BigDecimal nchqrq;
	/**月初活期日平*/
	@Excel(name = "月初活期日平", width = 15)
    @ApiModelProperty(value = "月初活期日平")
	private java.math.BigDecimal ychqrp;
	/**上日活期日平*/
	@Excel(name = "上日活期日平", width = 15)
    @ApiModelProperty(value = "上日活期日平")
	private java.math.BigDecimal srhqrq;
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
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private java.lang.String lrr;
}

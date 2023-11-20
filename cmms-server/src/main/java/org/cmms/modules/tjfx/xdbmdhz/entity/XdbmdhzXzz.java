package org.cmms.modules.tjfx.xdbmdhz.entity;

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
 * @Description: 信贷白名单汇总
 * @Author: jeecg-boot
 * @Date:   2022-07-05
 * @Version: V1.0
 */
@Data
@TableName("tjfx_xdbmdhz_xzz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_xdbmdhz_xzz对象", description="信贷白名单汇总")
public class XdbmdhzXzz {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**网格编号*/
	@Excel(name = "所属网格", width = 15,dicCode="ID",dictTable="V_YXDYGL_MAIN",dicText="WGMC_SHOW")
	@ApiModelProperty(value = "所属网格")
	@Dict(dicCode="ID",dictTable="V_YXDYGL_MAIN",dicText="WGMC_SHOW")
	private String wgbh;
	/**总户数*/
	@Excel(name = "总户数", width = 15)
    @ApiModelProperty(value = "总户数")
	private Integer zhs;
	/**贷款户数*/
	@Excel(name = "贷款户数", width = 15)
    @ApiModelProperty(value = "贷款户数")
	private Integer dkhs;
	/**贷款户数比例*/
	@Excel(name = "贷款户数比例", width = 15)
    @ApiModelProperty(value = "贷款户数比例")
	private java.math.BigDecimal dkhsBl;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**表外贷款户数*/
	@Excel(name = "表外贷款户数", width = 15)
    @ApiModelProperty(value = "表外贷款户数")
	private Integer bwdkhs;
	/**表外贷款余额*/
	@Excel(name = "表外贷款余额", width = 15)
    @ApiModelProperty(value = "表外贷款余额")
	private java.math.BigDecimal bwdkye;
	/**便民卡户数*/
	@Excel(name = "便民卡户数", width = 15)
    @ApiModelProperty(value = "便民卡户数")
	private Integer bmkhs;
	/**便民卡余额*/
	@Excel(name = "便民卡余额", width = 15)
    @ApiModelProperty(value = "便民卡余额")
	private java.math.BigDecimal bmkye;
	/**村组白名单户数*/
	@Excel(name = "村组白名单户数", width = 15)
    @ApiModelProperty(value = "村组白名单户数")
	private Integer czbmdhs;
	/**潜在营销户数*/
	@Excel(name = "潜在营销户数", width = 15)
    @ApiModelProperty(value = "潜在营销户数")
	private Integer qzyxhs;
	/**潜在营销户数比例*/
	@Excel(name = "潜在营销户数比例", width = 15)
    @ApiModelProperty(value = "潜在营销户数比例")
	private java.math.BigDecimal qzyxhsBl;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
}

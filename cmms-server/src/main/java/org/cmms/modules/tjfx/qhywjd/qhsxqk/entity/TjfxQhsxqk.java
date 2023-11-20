package org.cmms.modules.tjfx.qhywjd.qhsxqk.entity;

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
 * @Description: 全行授信情况
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
@Data
@TableName("TJFX_QHSXQK")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_QHSXQK对象", description="全行授信情况")
public class TjfxQhsxqk {

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
	/**授信-当前授信客户数*/
	@Excel(name = "当前授信客户数", width = 15,groupName = "授信")
    @ApiModelProperty(value = "授信-当前授信客户数")
	private Integer sxDqkhs;
	/**授信-年初授信客户数*/
	@Excel(name = "年初授信客户数", width = 15,groupName = "授信")
    @ApiModelProperty(value = "授信-年初授信客户数")
	private Integer sxNckhs;
	/**授信-月初授信客户数*/
	@Excel(name = "月初授信客户数", width = 15,groupName = "授信")
    @ApiModelProperty(value = "授信-月初授信客户数")
	private Integer sxYckhs;
	/**授信-较年初授信客户数*/
	@Excel(name = "较年初授信客户数", width = 15,groupName = "授信")
    @ApiModelProperty(value = "授信-较年初授信客户数")
	private Integer sxJnckhs;
	/**授信-较月初授信客户数*/
	@Excel(name = "较月初授信客户数", width = 15,groupName = "授信")
    @ApiModelProperty(value = "授信-较月初授信客户数")
	private Integer sxJyckh;
	/**授信-当前授信额度*/
	@Excel(name = "当前授信额度", width = 15,groupName = "授信")
    @ApiModelProperty(value = "授信-当前授信额度")
	private java.math.BigDecimal sxDqed;
	/**授信-年初授信额度*/
	@Excel(name = "年初授信额度", width = 15,groupName = "授信")
    @ApiModelProperty(value = "授信-年初授信额度")
	private java.math.BigDecimal sxNced;
	/**授信-月初授信额度*/
	@Excel(name = "月初授信额度", width = 15,groupName = "授信")
    @ApiModelProperty(value = "授信-月初授信额度")
	private java.math.BigDecimal sxYced;
	/**授信-较年初授信额度*/
	@Excel(name = "较年初授信额度", width = 15,groupName = "授信")
    @ApiModelProperty(value = "授信-较年初授信额度")
	private java.math.BigDecimal sxJnced;
	/**授信-较月初授信额度*/
	@Excel(name = "较月初授信额度", width = 15,groupName = "授信")
    @ApiModelProperty(value = "授信-较月初授信额度")
	private java.math.BigDecimal sxJyced;
	/**授信已用信-当前授信已用信客户数*/
	@Excel(name = "当前授信已用信客户数", width = 15,groupName = "授信已用信")
    @ApiModelProperty(value = "授信已用信-当前授信已用信客户数")
	private Integer sxyyxDqkhs;
	/**授信已用信-年初授信已用信客户数*/
	@Excel(name = "年初授信已用信客户数", width = 15,groupName = "授信已用信")
    @ApiModelProperty(value = "授信已用信-年初授信已用信客户数")
	private Integer sxyyxNckhs;
	/**授信已用信-月初授信已用信客户数*/
	@Excel(name = "月初授信已用信客户数", width = 15,groupName = "授信已用信")
    @ApiModelProperty(value = "授信已用信-月初授信已用信客户数")
	private Integer sxyyxYckhs;
	/**授信已用信-较年初授信已用信客户数*/
	@Excel(name = "较年初授信已用信客户数", width = 15,groupName = "授信已用信")
    @ApiModelProperty(value = "授信已用信-较年初授信已用信客户数")
	private Integer sxyyxJnckhs;
	/**授信已用信-较月初授信已用信客户数*/
	@Excel(name = "较月初授信已用信客户数", width = 15,groupName = "授信已用信")
    @ApiModelProperty(value = "授信已用信-较月初授信已用信客户数")
	private Integer sxyyxJyckhs;
	/**授信已用信-当前授信已用信额度*/
	@Excel(name = "当前授信已用信额度", width = 15,groupName = "授信已用信")
    @ApiModelProperty(value = "授信已用信-当前授信已用信额度")
	private java.math.BigDecimal sxyyxDqed;
	/**授信已用信-年初授信已用信额度*/
	@Excel(name = "年初授信已用信额度", width = 15,groupName = "授信已用信")
    @ApiModelProperty(value = "授信已用信-年初授信已用信额度")
	private java.math.BigDecimal sxyyxNced;
	/**授信已用信-月初授信已用信额度*/
	@Excel(name = "月初授信已用信额度", width = 15,groupName = "授信已用信")
    @ApiModelProperty(value = "授信已用信-月初授信已用信额度")
	private java.math.BigDecimal sxyyxYced;
	/**授信已用信-较年初授信已用信额度*/
	@Excel(name = "较年初授信已用信额度", width = 15,groupName = "授信已用信")
    @ApiModelProperty(value = "授信已用信-较年初授信已用信额度")
	private java.math.BigDecimal sxyyxJnced;
	/**授信已用信-较年初授信已用信额度*/
	@Excel(name = "较年初授信已用信额度", width = 15,groupName = "授信已用信")
    @ApiModelProperty(value = "授信已用信-较年初授信已用信额度")
	private java.math.BigDecimal sxyyxJyced;
	/**创建者*/
    @ApiModelProperty(value = "创建者")
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
}

package org.cmms.modules.tjfx.wgtjfx.bkbpytjzh.entity;

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
 * @Description: 背靠背评议统计_支行
 * @Author: jeecg-boot
 * @Date:   2022-05-09
 * @Version: V1.0
 */
@Data
@TableName("khxxgl_tjfx_bkbpytj_zh")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khxxgl_tjfx_bkbpytj_zh对象", description="背靠背评议统计_支行")
public class BkbpytjZh {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String sszh;
	@TableField(exist = false)
	private String jgdm;
	/**总户数*/
	@Excel(name = "总户数", width = 15)
    @ApiModelProperty(value = "总户数")
	private Integer zhs;
	/**未评议户数*/
	@Excel(name = "未评议户数", width = 15)
    @ApiModelProperty(value = "未评议户数")
	private Integer wpyhs;
	/**不予授信户数*/
	@Excel(name = "不予授信户数", width = 15)
    @ApiModelProperty(value = "不予授信户数")
	private Integer bysxhs;
	/**授信未用信户数*/
	@Excel(name = "授信未用信户数", width = 15)
    @ApiModelProperty(value = "授信未用信户数")
	private Integer sxwyxhs;
	/**授信已用信户数*/
	@Excel(name = "授信已用信户数", width = 15)
    @ApiModelProperty(value = "授信已用信户数")
	private Integer sxyyxhs;
	/**已评议户数*/
	@Excel(name = "已评议户数", width = 15)
    @ApiModelProperty(value = "已评议户数")
	private Integer ypyhs;
	/**已评议金额*/
	@Excel(name = "已评议金额", width = 15)
    @ApiModelProperty(value = "已评议金额")
	private java.math.BigDecimal ypyje;
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
	/**updateBy*/
	@Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
}

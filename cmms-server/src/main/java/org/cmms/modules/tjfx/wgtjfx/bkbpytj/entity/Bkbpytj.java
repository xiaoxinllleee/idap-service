package org.cmms.modules.tjfx.wgtjfx.bkbpytj.entity;

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
 * @Description: 背靠背评议统计
 * @Author: jeecg-boot
 * @Date:   2022-04-18
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_TJFX_BKBPYTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_TJFX_BKBPYTJ对象", description="背靠背评议统计")
public class Bkbpytj {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**统计日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String sszh;
	/**网格编号*/
	@Excel(name = "所属网格", width = 15, dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
    @ApiModelProperty(value = "网格编号")
	@Dict(dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String wgbh;
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
	@Excel(name = "风控筛选户数", width = 15)
	@ApiModelProperty(value = "风控筛选户数")
	private java.math.BigDecimal fksxhs;
	/**createBy*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updateBy*/
//	@Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**updateTime*/
//	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
}

package org.cmms.modules.tjfx.plpyczhzb.entity;

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
 * @Description: 批量评议村组汇总表
 * @Author: jeecg-boot
 * @Date:   2023-07-07
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_TJFX_PLPYCZHZB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_TJFX_PLPYCZHZB对象", description="批量评议村组汇总表")
public class TjfxPlpyczhzb {
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String sszh;
	/**村组编码*/
	@Excel(name = "村组名称", width = 15,dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
    @ApiModelProperty(value = "村组名称")
	@Dict(dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String czbm;
	/**村组户数*/
	@Excel(name = "村组户数", width = 15)
    @ApiModelProperty(value = "村组户数")
	private Integer czhs;
	/**可评议户数*/
	@Excel(name = "可评议户数", width = 15)
    @ApiModelProperty(value = "可评议户数")
	private Integer kpyhs;
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
	private Integer sxyyhs;
	/**应评议户数*/
	@Excel(name = "应评议户数", width = 15)
    @ApiModelProperty(value = "应评议户数")
	private Integer ypyhs;
	/**第一轮评议户数*/
	@Excel(name = "第一轮评议户数", width = 15)
    @ApiModelProperty(value = "第一轮评议户数")
	private Integer dylpyhs;
	/**第一轮建议授信户数*/
	@Excel(name = "第一轮建议授信户数", width = 15)
    @ApiModelProperty(value = "第一轮建议授信户数")
	private Integer dyljysxhs;
	/**第二轮评议户数*/
	@Excel(name = "第二轮评议户数", width = 15)
    @ApiModelProperty(value = "第二轮评议户数")
	private Integer delpyhs;
	/**第二轮建议授信户数*/
	@Excel(name = "第二轮建议授信户数", width = 15)
    @ApiModelProperty(value = "第二轮建议授信户数")
	private Integer deljysxhs;
	/**第三轮评议户数*/
	@Excel(name = "第三轮评议户数", width = 15)
    @ApiModelProperty(value = "第三轮评议户数")
	private Integer dslpyhs;
	/**第三轮建议授信户数*/
	@Excel(name = "第三轮建议授信户数", width = 15)
    @ApiModelProperty(value = "第三轮建议授信户数")
	private Integer dsljysxhs;
	/**综合评议户数*/
	@Excel(name = "综合评议户数", width = 15)
    @ApiModelProperty(value = "综合评议户数")
	private Integer zhpyhs;
	/**综合评议建议授信户数*/
	@Excel(name = "综合评议建议授信户数", width = 15)
    @ApiModelProperty(value = "综合评议建议授信户数")
	private Integer zhpyjysxhs;
	/**评议比例*/
	@Excel(name = "评议比例", width = 15)
    @ApiModelProperty(value = "评议比例")
	private Integer pybl;
}

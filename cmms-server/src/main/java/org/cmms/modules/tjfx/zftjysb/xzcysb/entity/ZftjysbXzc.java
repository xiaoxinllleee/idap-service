package org.cmms.modules.tjfx.zftjysb.xzcysb.entity;

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
 * @Description: 走访统计验收表-行政村
 * @Author: jeecg-boot
 * @Date:   2021-05-26
 * @Version: V1.0
 */
@Data
@TableName("tjfx_zftjysb_xzc")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_zftjysb_xzc对象", description="走访统计验收表-行政村")
public class ZftjysbXzc {

	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode="zzbz", dictTable="v_hr_bas_organization_cmms", dicText="zzjc")
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz", dictTable="v_hr_bas_organization_cmms", dicText="zzjc")
	private String sszh;
	/**所属乡镇*/
	@Excel(name = "所属乡镇", width = 15, dicCode = "wgbh",dictTable = "yxdygl_main", dicText = "wgmc")
    @ApiModelProperty(value = "所属乡镇")
	@Dict(dicCode = "wgbh",dictTable = "yxdygl_main", dicText = "wgmc")
	private String ssxz;
	/**行政村*/
	@Excel(name = "行政村", width = 15, dicCode = "wgbh",dictTable = "yxdygl_main", dicText = "wgmc")
    @ApiModelProperty(value = "行政村")
	@Dict(dicCode = "wgbh",dictTable = "yxdygl_main", dicText = "wgmc")
	private String xzc;
	/**总户数*/
	@Excel(name = "总户数", width = 15)
    @ApiModelProperty(value = "总户数")
	private Integer zhs;
	/**黑名单户数*/
	@Excel(name = "黑名单户数", width = 15)
    @ApiModelProperty(value = "黑名单户数")
	private Integer heimdhs;
	/**灰名单户数*/
	@Excel(name = "灰名单户数", width = 15)
    @ApiModelProperty(value = "灰名单户数")
	private Integer huimdhs;
	/**背靠背评议户数*/
	@Excel(name = "背靠背评议户数", width = 15)
    @ApiModelProperty(value = "背靠背评议户数")
	private Integer bkbpyhs;
	/**走访户数*/
	@Excel(name = "走访户数", width = 15)
    @ApiModelProperty(value = "走访户数")
	private Integer zfhs;
	/**授信户数*/
	@Excel(name = "授信户数", width = 15)
    @ApiModelProperty(value = "授信户数")
	private Integer sxhs;
	/**用信户数*/
	@Excel(name = "用信户数", width = 15)
    @ApiModelProperty(value = "用信户数")
	private Integer yxhs;
	/**白名单录入户数*/
	@Excel(name = "白名单录入户数", width = 15)
	@ApiModelProperty(value = "白名单录入户数")
	private Integer bmdlrhs;
	/**建档覆盖率*/
	@Excel(name = "建档覆盖率", width = 15)
	@ApiModelProperty(value = "建档覆盖率")
	private Integer jdfgl;
	/**背靠背评议得分*/
	@Excel(name = "背靠背评议得分", width = 15)
    @ApiModelProperty(value = "背靠背评议得分")
	private java.math.BigDecimal bkbpydf;
	/**走访得分*/
	@Excel(name = "走访得分", width = 15)
    @ApiModelProperty(value = "走访得分")
	private java.math.BigDecimal zfdf;
	/**授信得分*/
	@Excel(name = "授信得分", width = 15)
    @ApiModelProperty(value = "授信得分")
	private java.math.BigDecimal sxdf;
	/**用信得分*/
	@Excel(name = "用信得分", width = 15)
    @ApiModelProperty(value = "用信得分")
	private java.math.BigDecimal yxdf;
	/**白名单录入户数得分*/
	@Excel(name = "白名单录入户数得分", width = 15)
	@ApiModelProperty(value = "白名单录入户数得分")
	private java.math.BigDecimal bmdlrhsdf;
	/**建档覆盖率得分*/
	@Excel(name = "建档覆盖率得分", width = 15)
	@ApiModelProperty(value = "建档覆盖率得分")
	private java.math.BigDecimal jdfgldf;
	/**总得分*/
	@Excel(name = "总得分", width = 15)
    @ApiModelProperty(value = "总得分")
	private java.math.BigDecimal zdf;

	/**年初用信户数*/
	@Excel(name = "年初用信户数", width = 15)
	@ApiModelProperty(value = "年初用信户数")
	private Integer clhs;

	/**增量用信户数*/
	@Excel(name = "增量用信户数", width = 15)
	@ApiModelProperty(value = "增量用信户数")
	private Integer zlhs;

	/**用信金额*/
	@Excel(name = "用信金额", width = 15)
	@ApiModelProperty(value = "用信金额")
	private java.math.BigDecimal yxje;

	/**存量用信金额*/
	@Excel(name = "存量用信金额", width = 15)
	@ApiModelProperty(value = "存量用信金额")
	private java.math.BigDecimal clyxje;



	/**增量用信金额*/
	@Excel(name = "增量用信金额", width = 15)
	@ApiModelProperty(value = "增量用信金额")
	private java.math.BigDecimal zlyxje;

	/**授信金额*/
	@Excel(name = "授信金额", width = 15)
	@ApiModelProperty(value = "授信金额")
	private java.math.BigDecimal nhzzsxed;
	/**增量授信金额*/
	@Excel(name = "增量授信金额", width = 15)
	@ApiModelProperty(value = "增量授信金额")
	private java.math.BigDecimal zlsxje;

	/**增量授信户数*/
	@Excel(name = "增量授信户数", width = 15)
	@ApiModelProperty(value = "增量授信户数")
	private Integer zlsxhs;

	/**录入标志*/
	@Excel(name = "录入标志", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private String lrbz;
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

package org.cmms.modules.tjfx.xdgtzytjbb.nshby.entity;

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
 * @Description: 农商行统计表一
 * @Author: cmms
 * @Date:   2019-12-09
 * @Version: V1.0
 */
@Data
@TableName("TJFX_NSHBY")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_NSHBY对象", description="农商行统计表一")
public class TjfxNshby {
    
	/**结束日期*/
	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束日期")
	private Date jsrq;
	/**行政村（居委会）个数*/
	@Excel(name = "行政村（居委会）个数", width = 15)
    @ApiModelProperty(value = "行政村（居委会）个数")
	private Long xzcgs;
	/**本周整村评级授信完成个数*/
	@Excel(name = "本周整村评级授信完成个数", width = 15)
    @ApiModelProperty(value = "本周整村评级授信完成个数")
	private Long bzzcpjsxwcgs;
	/**累计整村评级授信完成个数*/
	@Excel(name = "累计整村评级授信完成个数", width = 15)
    @ApiModelProperty(value = "累计整村评级授信完成个数")
	private Long ljzcpjsxwcgs;
	/**开始日期*/
	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始日期")
	private Date ksrq;
	/**挂片领导*/
	@Excel(name = "挂片领导", width = 15,dicCode="yggh",dictTable="HR_BAS_STAFF",dicText="ygxm")
	@ApiModelProperty(value = "挂片领导")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String gpld;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String jgdm;
	/**村民（居民）小组个数*/
	@Excel(name = "村民（居民）小组个数", width = 15)
    @ApiModelProperty(value = "村民（居民）小组个数")
	private String cmxzgs;
	/**辖内农户（居民）户数*/
	@Excel(name = "辖内农户（居民）户数", width = 15)
    @ApiModelProperty(value = "辖内农户（居民）户数")
	private String xnnhhs;
	/**辖内商户户数*/
	@Excel(name = "辖内商户户数", width = 15)
    @ApiModelProperty(value = "辖内商户户数")
	private String xnshhs;
	/**辖内企业户数*/
	@Excel(name = "辖内企业户数", width = 15)
    @ApiModelProperty(value = "辖内企业户数")
	private String xnqyhs;
	/**本周农户（居民）户数*/
	@Excel(name = "本周农户（居民）户数", width = 15)
    @ApiModelProperty(value = "本周农户（居民）户数")
	private String bznhhs;
	/**累计农户（居民）户数*/
	@Excel(name = "累计农户（居民）户数", width = 15)
    @ApiModelProperty(value = "累计农户（居民）户数")
	private String ljnhhs;
	/**本周商户户数*/
	@Excel(name = "本周商户户数", width = 15)
    @ApiModelProperty(value = "本周商户户数")
	private String bzshhs;
	/**累计商户户数*/
	@Excel(name = "累计商户户数", width = 15)
    @ApiModelProperty(value = "累计商户户数")
	private String ljshhs;
	/**本周企业户数*/
	@Excel(name = "本周企业户数", width = 15)
    @ApiModelProperty(value = "本周企业户数")
	private String bzqyhs;
	/**累计企业户数*/
	@Excel(name = "累计企业户数", width = 15)
    @ApiModelProperty(value = "累计企业户数")
	private String ljqyhs;
	/**本周合计户数*/
	@Excel(name = "本周合计户数", width = 15)
    @ApiModelProperty(value = "本周合计户数")
	private String bzhjhs;
	/**累计合计户数*/
	@Excel(name = "累计合计户数", width = 15)
    @ApiModelProperty(value = "累计合计户数")
	private String ljhjhs;
	/**本周采集信息*/
	@Excel(name = "本周采集信息", width = 15)
    @ApiModelProperty(value = "本周采集信息")
	private String bzcjxx;
	/**累计采集信息*/
	@Excel(name = "累计采集信息", width = 15)
    @ApiModelProperty(value = "累计采集信息")
	private String ljcjxx;
	/**本周评级户数*/
	@Excel(name = "本周评级户数", width = 15)
    @ApiModelProperty(value = "本周评级户数")
	private String bzpjhs;
	/**累计评级户数*/
	@Excel(name = "累计评级户数", width = 15)
    @ApiModelProperty(value = "累计评级户数")
	private String ljpjhs;
	/**本周授信金额*/
	@Excel(name = "本周授信金额", width = 15)
    @ApiModelProperty(value = "本周授信金额")
	private java.math.BigDecimal bzsxje;
	/**累计授信金额*/
	@Excel(name = "累计授信金额", width = 15)
    @ApiModelProperty(value = "累计授信金额")
	private java.math.BigDecimal ljsxje;
	/**本周用信金额*/
	@Excel(name = "本周用信金额", width = 15)
    @ApiModelProperty(value = "本周用信金额")
	private java.math.BigDecimal bzyxje;
	/**累计用信金额*/
	@Excel(name = "累计用信金额", width = 15)
    @ApiModelProperty(value = "累计用信金额")
	private java.math.BigDecimal ljyxje;
	/**本周整组评级授信完成个数*/
	@Excel(name = "本周整组评级授信完成个数", width = 15)
    @ApiModelProperty(value = "本周整组评级授信完成个数")
	private String bzzzpjsxwcgs;
	/**累计整组评级授信完成个数*/
	@Excel(name = "累计整组评级授信完成个数", width = 15)
    @ApiModelProperty(value = "累计整组评级授信完成个数")
	private String ljzzpjsxwcgs;
}

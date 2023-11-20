package org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 支行统计报表一
 * @Author: cmms
 * @Date:   2019-12-18
 * @Version: V1.0
 */
@Data
@TableName("TJFX_ZHBY")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_ZHBY对象", description="支行统计报表一")
public class TjfxZhbyImport {
    
	/**开始日期*/
	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始日期")
	private String ksrq;
	/**责任人*/
	@Excel(name = "责任人", width = 15)
    @ApiModelProperty(value = "责任人")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String zrre;
	/**行政村（居委会）*/
	@Excel(name = "行政村（居委会）", width = 15)
    @ApiModelProperty(value = "行政村（居委会）")
	private String xzc;
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
	/**结束日期*/
	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束日期")
	private String jsrq;
	/**组信息*/
	@Excel(name = "组信息", width = 15)
    @ApiModelProperty(value = "组信息")
	@Dict(dicCode="qybm",dictTable="yxdygl_czxxgl",dicText="organize")
	private String zxx;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String jgdm;
}
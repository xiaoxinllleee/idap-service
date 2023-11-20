package org.cmms.modules.tjfx.xdgtzyb1.zgt.entity;

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
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-16
 * @Version: V1.0
 */
@Data
@TableName("tjfx_zhbymxb_zmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_zhbymxb_zmx对象", description="1")
public class Zgt {

	/**累计走访商户户数*/
	@Excel(name = "累计走访商户户数", width = 15)
    @ApiModelProperty(value = "累计走访商户户数")
	private String ljshhs;
	/**本周走访企业户数*/
	@Excel(name = "本周走访企业户数", width = 15)
    @ApiModelProperty(value = "本周走访企业户数")
	private String bzqyhs;
	/**累计走访企业户数*/
	@Excel(name = "累计走访企业户数", width = 15)
    @ApiModelProperty(value = "累计走访企业户数")
	private String ljqyhs;
	/**本周合计走访户数*/
	@Excel(name = "本周合计走访户数", width = 15)
    @ApiModelProperty(value = "本周合计走访户数")
	private String bzhjhs;
	/**累计合计走访户数*/
	@Excel(name = "累计合计走访户数", width = 15)
    @ApiModelProperty(value = "累计合计走访户数")
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
	/**开始日期*/
	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始日期")
	private Date ksrq;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束日期")
	private Date jsrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String sszh;
	/**行政村*/
	@Excel(name = "行政村", width = 15)
    @ApiModelProperty(value = "行政村")
	private String xzc;
	/**组*/
	@Excel(name = "组", width = 15)
    @ApiModelProperty(value = "组")
	@Dict(dicCode="qybm",dictTable="yxdygl_czxxgl",dicText="ORGANIZE")
	private String xzz;
	/**责任人*/
	@Excel(name = "责任人", width = 15)
    @ApiModelProperty(value = "责任人")
	@Dict(dicCode="yggh",dictTable = "hr_bas_staff", dicText = "ygxm")
	private String zkhjl;
	/**辖内农户户数*/
	@Excel(name = "辖内农户户数", width = 15)
    @ApiModelProperty(value = "辖内农户户数")
	private String xnnhhs;
	/**辖内商户户数*/
	@Excel(name = "辖内商户户数", width = 15)
    @ApiModelProperty(value = "辖内商户户数")
	private String xnshhs;
	/**辖内企业户数*/
	@Excel(name = "辖内企业户数", width = 15)
    @ApiModelProperty(value = "辖内企业户数")
	private String xnqyhs;
	/**本周走访农户户数*/
	@Excel(name = "本周走访农户户数", width = 15)
    @ApiModelProperty(value = "本周走访农户户数")
	private String bznhhs;
	/**累计走访农户户数*/
	@Excel(name = "累计走访农户户数", width = 15)
    @ApiModelProperty(value = "累计走访农户户数")
	private String ljnhhs;
	/**本周走访商户户数*/
	@Excel(name = "本周走访商户户数", width = 15)
    @ApiModelProperty(value = "本周走访商户户数")
	private String bzshhs;
}

package org.cmms.modules.tjfx.xdgtzyb1.hgt.entity;

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
 * @Date:   2020-03-17
 * @Version: V1.0
 */
@Data
@TableName("tjfx_zhbymxb_dh")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_zhbymxb_dh对象", description="1")
public class Tjfx_zhbymxb_dh {

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
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**户主名称*/
	@Excel(name = "户主名称", width = 15)
    @ApiModelProperty(value = "户主名称")
	private String hzmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**是否本周走访（0：否，1：是）*/
	@Excel(name = "是否本周走访（0：否，1：是）", width = 15)
    @ApiModelProperty(value = "是否本周走访（0：否，1：是）")
	@Dict(dicCode = "sfbz_ex")
	private Long sfbzzf;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "khlx")
	private String khlx;
	/**是否已走访（0：否，1：是）*/
	@Excel(name = "是否已走访（0：否，1：是）", width = 15)
    @ApiModelProperty(value = "是否已走访（0：否，1：是）")
	@Dict(dicCode = "sfbz_ex")
	private Long sfyjzf;
	/**是否本周评级（0：否，1：是）*/
	@Excel(name = "是否本周评级（0：否，1：是）", width = 15)
    @ApiModelProperty(value = "是否本周评级（0：否，1：是）")
	@Dict(dicCode = "sfbz_ex")
	private Long sfbzpj;
	/**单户人数*/
	@Excel(name = "单户人数", width = 15)
    @ApiModelProperty(value = "单户人数")
	private Long dhrs;
	/**本周授信金额*/
	@Excel(name = "本周授信金额", width = 15)
    @ApiModelProperty(value = "本周授信金额")
	private java.math.BigDecimal bzsxje;
	/**是否已评级（0：否，1：是）*/
	@Excel(name = "是否已评级（0：否，1：是）", width = 15)
    @ApiModelProperty(value = "是否已评级（0：否，1：是）")
	@Dict(dicCode = "sfbz_ex")
	private Long sfyjpj;
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
}

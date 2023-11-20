package org.cmms.modules.khgl.grkhgl.entity;

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
 * @Description: 客户花名册
 * @Author: jeecg-boot
 * @Date:   2020-07-06
 * @Version: V1.0
 */
@Data
@TableName("KHGL_KHHMCXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGL_KHHMCXX对象", description="客户花名册")
public class Khhmcxx {
    
	/**系统评定说明*/
	@Excel(name = "系统评定说明", width = 15)
    @ApiModelProperty(value = "系统评定说明")
	private String xtpdsm;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date xgsj;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String xgr;
	/**是否吸毒人员*/
	@Excel(name = "是否吸毒人员", width = 15)
    @ApiModelProperty(value = "是否吸毒人员")
	private String sfxdry;
	/**是否贫困户*/
	@Excel(name = "是否贫困户", width = 15)
    @ApiModelProperty(value = "是否贫困户")
	private String sfpkh;
	/**是否低保*/
	@Excel(name = "是否低保", width = 15)
    @ApiModelProperty(value = "是否低保")
	private String sfdb;
	/**从事职业*/
	@Excel(name = "从事职业", width = 15)
    @ApiModelProperty(value = "从事职业")
	@Dict(dicCode = "cszy")
	private String cszy;
	/**是否公职人员*/
	@Excel(name = "是否公职人员", width = 15)
    @ApiModelProperty(value = "是否公职人员")
	private String sfgzry;
	/**是否非法集资*/
	@Excel(name = "是否非法集资", width = 15)
    @ApiModelProperty(value = "是否非法集资")
	private String sfffjz;
	/**患病记录*/
	@Excel(name = "患病记录", width = 15)
    @ApiModelProperty(value = "患病记录")
	private String hbjl;
	/**建档完整度*/
	@Excel(name = "建档完整度", width = 15)
    @ApiModelProperty(value = "建档完整度")
	private java.math.BigDecimal infoRate;
	/**出生年月*/
	@Excel(name = "出生年月", width = 15)
    @ApiModelProperty(value = "出生年月")
	private String csny;
	/**是否领取社保卡（1：是 2：否）*/
	@Excel(name = "是否领取社保卡（1：是 2：否）", width = 15)
    @ApiModelProperty(value = "是否领取社保卡（1：是 2：否）")
	private String sflqsbk;
	/**是否开通社保卡（1：是 2：否）*/
	@Excel(name = "是否开通社保卡（1：是 2：否）", width = 15)
    @ApiModelProperty(value = "是否开通社保卡（1：是 2：否）")
	private String sfktsbk;
	/**原所属乡镇*/
	@Excel(name = "原所属乡镇", width = 15)
    @ApiModelProperty(value = "原所属乡镇")
	private String yssxz;
	/**原行政村*/
	@Excel(name = "原行政村", width = 15)
    @ApiModelProperty(value = "原行政村")
	private String yxzc;
	/**系统评定结果（1：灰名单 2：白名单 3：黑名单）*/
	@Excel(name = "系统评定结果（1：灰名单 2：白名单 3：黑名单）", width = 15)
    @ApiModelProperty(value = "系统评定结果（1：灰名单 2：白名单 3：黑名单）")
	private Integer xtpdjg;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	private String sszh;
	/**所属营销单元*/
	@Excel(name = "所属营销单元", width = 15)
    @ApiModelProperty(value = "所属营销单元")
	private String ssyxdy;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**与户主关系*/
	@Excel(name = "与户主关系", width = 15)
    @ApiModelProperty(value = "与户主关系")
	@Dict(dicCode = "yhzgx")
	private String yhzgx;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15)
    @ApiModelProperty(value = "是否户主")
	private String sfhz;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	private String khlx;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private String lxfs;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String dz;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
	private String xb;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
	private String nl;
	/**民族*/
	@Excel(name = "民族", width = 15)
    @ApiModelProperty(value = "民族")
	private String mz;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15)
    @ApiModelProperty(value = "婚姻状况")
	private String hyzk;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	private String lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
}

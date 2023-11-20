package org.cmms.modules.pad.nhxxgl.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-07-24
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_NHJBXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_NHJBXX对象", description="1")
public class CamsZcsxNhjbxxPad {
    
	/**返乡时间*/
	@Excel(name = "返乡时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "返乡时间")
	private Date fxsj;
	/**不良贷款余额*/
	@Excel(name = "不良贷款余额", width = 15)
    @ApiModelProperty(value = "不良贷款余额")
	private java.math.BigDecimal bldkye;
	/**表外不良贷款余额*/
	@Excel(name = "表外不良贷款余额", width = 15)
    @ApiModelProperty(value = "表外不良贷款余额")
	private java.math.BigDecimal bwbldkye;
	/**是否授信（1：是 2：否）*/
	@Excel(name = "是否授信（1：是 2：否）", width = 15)
    @ApiModelProperty(value = "是否授信（1：是 2：否）")
	private Integer sfsx;
	/**评议信息有效标识：0-无效，1-有效*/
	@Excel(name = "评议信息有效标识：0-无效，1-有效", width = 15)
    @ApiModelProperty(value = "评议信息有效标识：0-无效，1-有效")
	private String pyxxFlag;
	/**客户基础信息有效标识：0-无效，1-有效*/
	@Excel(name = "客户基础信息有效标识：0-无效，1-有效", width = 15)
    @ApiModelProperty(value = "客户基础信息有效标识：0-无效，1-有效")
	private String khxxFlag;
	/**客户管理有效标识：0-无效，1-有效*/
	@Excel(name = "客户管理有效标识：0-无效，1-有效", width = 15)
    @ApiModelProperty(value = "客户管理有效标识：0-无效，1-有效")
	private String khglFlag;
	/**客户签名标识：0-无效，1-有效*/
	@Excel(name = "客户签名标识：0-无效，1-有效", width = 15)
    @ApiModelProperty(value = "客户签名标识：0-无效，1-有效")
	private String signFlag;
	/**区域代码*/
	@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
	private String qydm;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	private String sszh;
	/**所属村编码*/
	@Excel(name = "所属村编码", width = 15)
    @ApiModelProperty(value = "所属村编码")
	private String sscbm;
	/**所属村名称*/
	@Excel(name = "所属村名称", width = 15)
    @ApiModelProperty(value = "所属村名称")
	private String sscmc;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**是否户主（1：是 2：否）*/
	@Excel(name = "是否户主（1：是 2：否）", width = 15)
    @ApiModelProperty(value = "是否户主（1：是 2：否）")
	private Integer sfhz;
	/**与户主关系：1-户主本人，2-配偶，3-父与子，4-母与子，5-兄弟，6-姐妹，7-祖父母与孙子女，8-其他*/
	@Excel(name = "与户主关系：1-户主本人，2-配偶，3-父与子，4-母与子，5-兄弟，6-姐妹，7-祖父母与孙子女，8-其他", width = 15)
    @ApiModelProperty(value = "与户主关系：1-户主本人，2-配偶，3-父与子，4-母与子，5-兄弟，6-姐妹，7-祖父母与孙子女，8-其他")
	private String yhzgx;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
	private Integer nl;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
	private Integer xb;
	/**民族*/
	@Excel(name = "民族", width = 15)
    @ApiModelProperty(value = "民族")
	private Integer mz;
	/**文化程度*/
	@Excel(name = "文化程度", width = 15)
    @ApiModelProperty(value = "文化程度")
	private Integer whcd;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15)
    @ApiModelProperty(value = "婚姻状况")
	private Integer hyzk;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	private String sjhm;
	/**户口性质*/
	@Excel(name = "户口性质", width = 15)
    @ApiModelProperty(value = "户口性质")
	private Integer hkxz;
	/**户籍地址*/
	@Excel(name = "户籍地址", width = 15)
    @ApiModelProperty(value = "户籍地址")
	private String hjdz;
	/**住址*/
	@Excel(name = "住址", width = 15)
    @ApiModelProperty(value = "住址")
	private String zz;
	/**住址1*/
	@Excel(name = "住址1", width = 15)
    @ApiModelProperty(value = "住址1")
	private String zz1;
	/**住址2*/
	@Excel(name = "住址2", width = 15)
    @ApiModelProperty(value = "住址2")
	private String zz2;
	/**是否不良贷款户*/
	@Excel(name = "是否不良贷款户", width = 15)
    @ApiModelProperty(value = "是否不良贷款户")
	private Integer sfbldkh;
	/**是否贫困户*/
	@Excel(name = "是否贫困户", width = 15)
    @ApiModelProperty(value = "是否贫困户")
	private Integer sfpkh;
	/**是否低保户*/
	@Excel(name = "是否低保户", width = 15)
    @ApiModelProperty(value = "是否低保户")
	private Integer sfdbh;
	/**系统评定结果：1-灰名单，2-白名单，3-黑名单*/
	@Excel(name = "系统评定结果：1-灰名单，2-白名单，3-黑名单", width = 15)
    @ApiModelProperty(value = "系统评定结果：1-灰名单，2-白名单，3-黑名单")
	private Integer xtpdjg;
	/**系统评定说明*/
	@Excel(name = "系统评定说明", width = 15)
    @ApiModelProperty(value = "系统评定说明")
	private String xtpdsm;
	/**关键人评定结果*/
	@Excel(name = "关键人评定结果", width = 15)
    @ApiModelProperty(value = "关键人评定结果")
	private Integer gjrpdjg;
	/**关键人评定说明*/
	@Excel(name = "关键人评定说明", width = 15)
    @ApiModelProperty(value = "关键人评定说明")
	private String gjrpdsm;
	/**最终评定结果*/
	@Excel(name = "最终评定结果", width = 15)
    @ApiModelProperty(value = "最终评定结果")
	private Integer zzpdjg;
	/**最终评定说明*/
	@Excel(name = "最终评定说明", width = 15)
    @ApiModelProperty(value = "最终评定说明")
	private String zzpdsm;
	/**从事行业或工作单位*/
	@Excel(name = "从事行业或工作单位", width = 15)
    @ApiModelProperty(value = "从事行业或工作单位")
	private String cshygz;
	/**是否外出务工*/
	@Excel(name = "是否外出务工", width = 15)
    @ApiModelProperty(value = "是否外出务工")
	private Integer sfycdg;
	/**客户重要程度*/
	@Excel(name = "客户重要程度", width = 15)
    @ApiModelProperty(value = "客户重要程度")
	private Integer kfyyqk;
	/**客户潜在业务*/
	@Excel(name = "客户潜在业务", width = 15)
    @ApiModelProperty(value = "客户潜在业务")
	private String kcqzyw;
	/**客户授信情况*/
	@Excel(name = "客户授信情况", width = 15)
    @ApiModelProperty(value = "客户授信情况")
	private Integer khsxqk;
	/**经度*/
	@Excel(name = "经度", width = 15)
    @ApiModelProperty(value = "经度")
	private String longitude;
	/**纬度*/
	@Excel(name = "纬度", width = 15)
    @ApiModelProperty(value = "纬度")
	private String latitude;
	/**电子签名1*/
	@Excel(name = "电子签名1", width = 15)
    @ApiModelProperty(value = "电子签名1")
	private String sign1;
	/**电子签名2*/
	@Excel(name = "电子签名2", width = 15)
    @ApiModelProperty(value = "电子签名2")
	private String sign2;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	private Integer lrbz;
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
	/**是否开通存款业务*/
	@Excel(name = "是否开通存款业务", width = 15)
    @ApiModelProperty(value = "是否开通存款业务")
	private Integer sfktckyw;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**存款日平余额*/
	@Excel(name = "存款日平余额", width = 15)
    @ApiModelProperty(value = "存款日平余额")
	private java.math.BigDecimal ckrpye;
	/**存款年日平余额*/
	@Excel(name = "存款年日平余额", width = 15)
    @ApiModelProperty(value = "存款年日平余额")
	private java.math.BigDecimal cknrpye;
	/**是否开通贷款业务*/
	@Excel(name = "是否开通贷款业务", width = 15)
    @ApiModelProperty(value = "是否开通贷款业务")
	private Integer sfktdkyw;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**证件签发日期*/
	@Excel(name = "证件签发日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "证件签发日期")
	private Date zjqfrq;
	/**证件到期日期*/
	@Excel(name = "证件到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "证件到期日期")
	private Date zjdqrq;
}

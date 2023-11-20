package org.cmms.modules.dklldj.lldjgl.lldjsqNy.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.modules.dklldj.lldjgl.lldjsqNy.entity.RateDbxxglNy;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 利率定价申请
 * @Author: jeecg-boot
 * @Date:   2020-04-03
 * @Version: V1.0
 */
@Data
@TableName("rate_djsqxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rate_djsqxx对象", description="利率定价申请")
public class LldjsqNyVO {

	/**组织标识*/
	private String zzbz;
	/**定价年份*/
	private String djnf;
	/**证件号码*/
	private String zjhm;
	/**客户名称*/
	private String khmc;
	/**客户类型*/
	private Integer khlx;
	/**法人代表*/
	private String frdb;
	/**贷款期限*/
	private Integer dkqx;
	/**信贷贷款品种*/
	private String xddkpz;
	/**综合授信额度*/
	private BigDecimal zhsxed;
	/**其中：贷款授信+承兑敞口*/
	private Double cdck;
	/**上年授信额度*/
	private BigDecimal snsxed;
	/**上年执行利率*/
	private BigDecimal snzxll;
	/**是否便民卡*/
	private Integer sfbmk;
	/**是否保证保险贷款*/
	private Integer sfbzbxdk;
	/**上年贷款基点(加/减)BP*/
	private BigDecimal sndkjdbp;

	@ApiModelProperty(value = "KH00001")
	@JsonProperty("KH00001")
	private String KH00001;

	@ApiModelProperty(value = "GZ00008")
	@JsonProperty("GZ00008")
	private String GZ00008;

	@ApiModelProperty(value = "GZ00009")
	@JsonProperty("GZ00009")
	private String GZ00009;

	@ApiModelProperty(value = "GZ00010")
	@JsonProperty("GZ00010")
	private String GZ00010;

	@ApiModelProperty(value = "GZ00011")
	@JsonProperty("GZ00011")
	private String GZ00011;

	@ApiModelProperty(value = "GZ00012")
	@JsonProperty("GZ00012")
	private String GZ00012;

	@ApiModelProperty(value = "GZ00013")
	@JsonProperty("GZ00013")
	private String GZ00013;

	@ApiModelProperty(value = "GZ00014")
	@JsonProperty("GZ00014")
	private String GZ00014;

	@ApiModelProperty(value = "GZ00015")
	@JsonProperty("GZ00015")
	private String GZ00015;

	@ApiModelProperty(value = "GZ00016")
	@JsonProperty("GZ00016")
	private String GZ00016;

	@ApiModelProperty(value = "GZ00017")
	@JsonProperty("GZ00017")
	private String GZ00017;

	@ApiModelProperty(value = "GZ00018")
	@JsonProperty("GZ00018")
	private String GZ00018;

	@ApiModelProperty(value = "GZ00019")
	@JsonProperty("GZ00019")
	private String GZ00019;

	@ApiModelProperty(value = "GZ00020")
	@JsonProperty("GZ00020")
	private String GZ00020;

	@ApiModelProperty(value = "GZ00021")
	@JsonProperty("GZ00021")
	private String GZ00021;

	@ApiModelProperty(value = "GZ00022")
	@JsonProperty("GZ00022")
	private String GZ00022;

	@ApiModelProperty(value = "GZ00023")
	@JsonProperty("GZ00023")
	private String GZ00023;

	@ApiModelProperty(value = "GZ00024")
	@JsonProperty("GZ00024")
	private String GZ00024;

	@ApiModelProperty(value = "GZ00025")
	@JsonProperty("GZ00025")
	private String GZ00025;

	@ApiModelProperty(value = "GZ00026")
	@JsonProperty("GZ00026")
	private String GZ00026;

	@ApiModelProperty(value = "GZ00027")
	@JsonProperty("GZ00027")
	private String GZ00027;

	@ApiModelProperty(value = "GZ00028")
	@JsonProperty("GZ00028")
	private String GZ00028;

	@ApiModelProperty(value = "GZ00029")
	@JsonProperty("GZ00029")
	private String GZ00029;

	@ApiModelProperty(value = "GZ00030")
	@JsonProperty("GZ00030")
	private String GZ00030;

	@ApiModelProperty(value = "GZ00031")
	@JsonProperty("GZ00031")
	private String GZ00031;

	@ApiModelProperty(value = "GZ00032")
	@JsonProperty("GZ00032")
	private String GZ00032;

	// 以下不纳入保存逻辑
	@ApiModelProperty(value = "GZ00033")
	@JsonProperty("GZ00033")
	private String GZ00033;

	@ApiModelProperty(value = "GZ00034")
	@JsonProperty("GZ00034")
	private String GZ00034;

	@ApiModelProperty(value = "GZ00035")
	@JsonProperty("GZ00035")
	private String GZ00035;

	@ApiModelProperty(value = "GZ00036")
	@JsonProperty("GZ00036")
	private String GZ00036;
	// 以上不纳入保存逻辑

	@ApiModelProperty(value = "GZ00037")
	@JsonProperty("GZ00037")
	private String GZ00037;

	@ApiModelProperty(value = "GZ00038")
	@JsonProperty("GZ00038")
	private String GZ00038;

	@ApiModelProperty(value = "GZ00039")
	@JsonProperty("GZ00039")
	private String GZ00039;

	@ApiModelProperty(value = "sndkrp")
	@JsonProperty("sndkrp")
	private BigDecimal sndkrp;

	// 客户担保信息
	@TableField(exist = false)
	private List<RateDbxxglNy> khdbxxList;
}

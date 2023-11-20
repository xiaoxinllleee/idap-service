package org.cmms.modules.dklldj.lldjgl.lldjsqHj.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

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
public class LldjsqVO {

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
	/**担保方式*/
	private String xddkpz;
	/**综合授信额度*/
	private java.math.BigDecimal zhsxed;
	/**其中：贷款授信+承兑敞口*/
	private Double cdck;
	/**上年授信额度*/
	private java.math.BigDecimal snsxed;
	/**上年执行利率*/
	private java.math.BigDecimal snzxll;
	/**是否便民卡*/
	private Integer sfbmk;
	/**是否保证保险贷款*/
	private Integer sfbzbxdk;

	/**上年贷款基点(加/减)BP*/
	private java.math.BigDecimal sndkjdbp;

	/**客户最新存款日平余额总和*/
	private BigDecimal ckrpye;

	/**资金归行率*/
	@ApiModelProperty(value = "GZ00001")
	@JsonProperty("GZ00001")
	private String GZ00001;
	/**利息贡献度*/
	@ApiModelProperty(value = "GZ00002")
	@JsonProperty("GZ00002")
	private String GZ00002;
	/**合作时长*/
	@ApiModelProperty(value = "GZ00003")
	@JsonProperty("GZ00003")
	private String GZ00003;
	/**产品使用*/
	@ApiModelProperty(value = "GZ00004")
	@JsonProperty("GZ00004")
	private String GZ00004;
	/**客户征信记录*/
	@ApiModelProperty(value = "GZ00005")
	@JsonProperty("GZ00005")
	private String GZ00005;
	/**非恶意产生的贷款本息（含信用卡）逾期期数*/
	@ApiModelProperty(value = "GZ00006")
	@JsonProperty("GZ00006")
	private String GZ00006;
	/**借款主体为小微企业(含个体工商户)*/
	@ApiModelProperty(value = "GZ00007")
	@JsonProperty("GZ00007")
	private String GZ00007;
	/**单笔贷款期限1年以下*/
	@ApiModelProperty(value = "GZ00008")
	@JsonProperty("GZ00008")
	private String GZ00008;
	/**还款方式选择按月结息*/
	@ApiModelProperty(value = "GZ00009")
	@JsonProperty("GZ00009")
	private String GZ00009;


}

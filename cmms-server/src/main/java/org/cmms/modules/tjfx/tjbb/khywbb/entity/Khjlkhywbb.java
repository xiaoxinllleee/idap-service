package org.cmms.modules.tjfx.tjbb.khywbb.entity;

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
 * @Description: 客户业务信息(客户经理)
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0
 */
@Data
@TableName("TJFX_KHYWBB_KHJL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_KHYWBB_KHJL对象", description="客户业务信息(客户经理)")
public class Khjlkhywbb {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String sszh;
	/**客户经理*/
	@Excel(name = "客户经理", width = 15)
	@ApiModelProperty(value = "客户经理")
	private String zkhjl;
	/**办理贷款户数*/
	@Excel(name = "办理贷款户数", width = 15)
    @ApiModelProperty(value = "办理贷款户数")
	private java.math.BigDecimal bldkhs;
	/**办理存款户数*/
	@Excel(name = "办理存款户数", width = 15)
    @ApiModelProperty(value = "办理存款户数")
	private java.math.BigDecimal blckhs;
	/**办理网上银行户数*/
	@Excel(name = "办理网上银行户数", width = 15)
    @ApiModelProperty(value = "办理网上银行户数")
	private java.math.BigDecimal blwsyhhs;
	/**办理手机银行户数*/
	@Excel(name = "办理手机银行户数", width = 15)
    @ApiModelProperty(value = "办理手机银行户数")
	private java.math.BigDecimal blsjyhhs;
	/**办理ETC户数*/
	@Excel(name = "办理ETC户数", width = 15)
    @ApiModelProperty(value = "办理ETC户数")
	private java.math.BigDecimal bletchs;
	/**办理E支付户数*/
	@Excel(name = "办理E支付户数", width = 15)
    @ApiModelProperty(value = "办理E支付户数")
	private java.math.BigDecimal blezfhs;
	/**办理E缴费户数*/
	@Excel(name = "办理E缴费户数", width = 15)
    @ApiModelProperty(value = "办理E缴费户数")
	private java.math.BigDecimal blejfhs;
	/**办理POS机户数*/
	@Excel(name = "办理POS机户数", width = 15)
    @ApiModelProperty(value = "办理POS机户数")
	private java.math.BigDecimal blposhs;
	/**办理助农终端户数*/
	@Excel(name = "办理助农终端户数", width = 15)
    @ApiModelProperty(value = "办理助农终端户数")
	private java.math.BigDecimal blznzdhs;
	/**办理理财业务户数*/
	@Excel(name = "办理理财业务户数", width = 15)
    @ApiModelProperty(value = "办理理财业务户数")
	private java.math.BigDecimal bllcywhs;
	/**办理保险业务户数*/
	@Excel(name = "办理保险业务户数", width = 15)
    @ApiModelProperty(value = "办理保险业务户数")
	private java.math.BigDecimal blbxywhs;
	/**关注我行公众号户数*/
	@Excel(name = "关注我行公众号户数", width = 15)
    @ApiModelProperty(value = "关注我行公众号户数")
	private java.math.BigDecimal gzwhgzhhs;
	/**办理扫码付户数*/
	@Excel(name = "办理扫码付户数", width = 15)
    @ApiModelProperty(value = "办理扫码付户数")
	private java.math.BigDecimal blsmfhs;
	/**办理社保卡户数*/
	@Excel(name = "办理社保卡户数", width = 15)
    @ApiModelProperty(value = "办理社保卡户数")
	private java.math.BigDecimal blsbkhs;
	/**办理信用卡户数*/
	@Excel(name = "办理信用卡户数", width = 15)
    @ApiModelProperty(value = "办理信用卡户数")
	private java.math.BigDecimal blxykhs;
	/**办理福民卡户数*/
	@Excel(name = "办理福民卡户数", width = 15)
    @ApiModelProperty(value = "办理福民卡户数")
	private java.math.BigDecimal blfmkhs;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**活期存款余额*/
	@Excel(name = "活期存款余额", width = 15)
    @ApiModelProperty(value = "活期存款余额")
	private java.math.BigDecimal hqckye;
	/**定期存款余额*/
	@Excel(name = "定期存款余额", width = 15)
    @ApiModelProperty(value = "定期存款余额")
	private java.math.BigDecimal dqckye;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**表内不良贷款*/
	@Excel(name = "表内不良贷款", width = 15)
    @ApiModelProperty(value = "表内不良贷款")
	private java.math.BigDecimal blbldk;
	/**表外不良贷款*/
	@Excel(name = "表外不良贷款", width = 15)
    @ApiModelProperty(value = "表外不良贷款")
	private java.math.BigDecimal bwbldk;
	/**存款余额占比*/
	@Excel(name = "存款余额占比", width = 15)
    @ApiModelProperty(value = "存款余额占比")
	private java.math.BigDecimal ckyezb;
	/**贷款余额占比*/
	@Excel(name = "贷款余额占比", width = 15)
    @ApiModelProperty(value = "贷款余额占比")
	private java.math.BigDecimal dkyezb;

	/**表内不良户数*/
	@Excel(name = "表内不良户数", width = 15)
	@ApiModelProperty(value = "表内不良户数")
	private java.math.BigDecimal bnblhs;
	/**表外不良户数*/
	@Excel(name = "表外不良户数", width = 15)
	@ApiModelProperty(value = "表外不良户数")
	private java.math.BigDecimal bwblhs;
	/**表内不良贷款占比*/
	@Excel(name = "表内不良贷款占比", width = 15)
	@ApiModelProperty(value = "表内不良贷款占比")
	private java.math.BigDecimal bnbldkzb;
	/**表外不良贷款占比*/
	@Excel(name = "表外不良贷款占比", width = 15)
	@ApiModelProperty(value = "表外不良贷款占比")
	private java.math.BigDecimal bwbldkzb;

}

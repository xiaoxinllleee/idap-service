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
 * @Description: 客户业务报表(明细)
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0
 */
@Data
@TableName("TJFX_KHYWBB_GRMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_KHYWBB_GRMX对象", description="客户业务报表(明细)")
public class Grmxkhywbb {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**所属营销单元*/
	@Excel(name = "所属营销单元", width = 15)
	@ApiModelProperty(value = "所属营销单元")
	private String ssyxdy;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String sszh;
	/**乡镇*/
	@Excel(name = "乡镇", width = 15)
	@ApiModelProperty(value = "乡镇")
	private String xz;
	/**村*/
	@Excel(name = "村", width = 15)
	@ApiModelProperty(value = "村")
	private String xzc;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15, dicCode = "khlx")
	@ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "khlx")
	private String khlx;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
	@ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否户主")
	@Dict(dicCode = "sfbz")
	private String sfhz;
	/**客户经理*/
	@Excel(name = "客户经理", width = 15)
	@ApiModelProperty(value = "客户经理")
	private String zkhjl;

	/**是否办理贷款*/
	@Excel(name = "是否办理贷款", width = 15)
	@ApiModelProperty(value = "是否办理贷款")
	private String sfbldk;
	/**是否办理存款*/
	@Excel(name = "是否办理存款", width = 15)
	@ApiModelProperty(value = "是否办理存款")
	private String sfblck;
	/**是否办理网上银行*/
	@Excel(name = "是否办理网上银行", width = 15)
	@ApiModelProperty(value = "是否办理网上银行")
	private String sfblwsyh;
	/**是否办理手机银行*/
	@Excel(name = "是否办理手机银行", width = 15)
	@ApiModelProperty(value = "是否办理手机银行")
	private String sfblsjyh;
	/**是否办理ETC*/
	@Excel(name = "是否办理ETC", width = 15)
	@ApiModelProperty(value = "是否办理ETC")
	private String sfbletc;
	/**是否办理E支付*/
	@Excel(name = "是否办理E支付", width = 15)
	@ApiModelProperty(value = "是否办理E支付")
	private String sfblezf;
	/**是否办理E缴费*/
	@Excel(name = "是否办理E缴费", width = 15)
	@ApiModelProperty(value = "是否办理E缴费")
	private String sfblejf;
	/**是否办理POS机*/
	@Excel(name = "是否办理POS机", width = 15)
	@ApiModelProperty(value = "是否办理POS机")
	private String sfblpos;
	/**是否办理助农终端*/
	@Excel(name = "是否办理助农终端", width = 15)
	@ApiModelProperty(value = "是否办理助农终端")
	private String sfblznzd;
	/**是否办理理财业务*/
	@Excel(name = "是否办理理财业务", width = 15)
	@ApiModelProperty(value = "是否办理理财业务")
	private String sfbllcyw;
	/**是否办理保险业务*/
	@Excel(name = "是否办理保险业务", width = 15)
	@ApiModelProperty(value = "是否办理保险业务")
	private String sfblbxyw;
	/**是否关注我行公众号*/
	@Excel(name = "是否关注我行公众号", width = 15)
	@ApiModelProperty(value = "是否关注我行公众号")
	private String sfgzwhgzh;
	/**是否办理扫码付*/
	@Excel(name = "是否办理扫码付", width = 15)
    @ApiModelProperty(value = "是否办理扫码付")
	private String sfblsmf;
	/**是否办理社保卡*/
	@Excel(name = "是否办理社保卡", width = 15)
    @ApiModelProperty(value = "是否办理社保卡")
	private String sfblsbk;
	/**是否办理信用卡*/
	@Excel(name = "是否办理信用卡", width = 15)
    @ApiModelProperty(value = "是否办理信用卡")
	private String sfblxyk;
	/**是否办理福民卡*/
	@Excel(name = "是否办理福民卡", width = 15)
    @ApiModelProperty(value = "是否办理福民卡")
	private String sfblfmk;
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
}

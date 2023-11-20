package org.cmms.modules.khxxgl.khflgl.bbpyinfo.bbpyjcxx.entity;

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
 * @Description: 背靠背评议基础信息
 * @Author: jeecg-boot
 * @Date:   2022-03-20
 * @Version: V1.0
 */
@Data
@TableName("v_khxxgl_bkbpy_jcxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_khxxgl_bkbpy_jcxx对象", description="背靠背评议基础信息")
public class BkbpyJcxx {
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "ID")
	private String id;
	/**归属网格*/
	@Excel(name = "归属网格", width = 15,dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
	@ApiModelProperty(value = "归属网格")
	@Dict(dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
	private String wgbh;

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
	/**与户主关系*/
	@Excel(name = "与户主关系", width = 15)
    @ApiModelProperty(value = "与户主关系")
	@Dict(dicCode = "yhzgx")
	private String yhzgx;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
	@Dict(dicCode = "sex")
	private String xb;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
	private String nl;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	private String sjhm;
	/**住址*/
	@Excel(name = "住址", width = 15)
	@ApiModelProperty(value = "住址")
	private String zz;
	/**是否不予授信*/
	@Excel(name = "是否不予授信", width = 15)
	@ApiModelProperty(value = "是否不予授信")
	private String sfbysx;
	/**是否授信*/
	@Excel(name = "是否授信", width = 15)
	@ApiModelProperty(value = "是否授信")
	private String sfsx;
	/**不予授信情形*/
	@Excel(name = "不予授信情形", width = 15)
	@ApiModelProperty(value = "不予授信情形")
	private String bysxqx;
	/**正常贷款余额*/
	@Excel(name = "正常贷款余额", width = 15)
    @ApiModelProperty(value = "正常贷款余额")
	private java.math.BigDecimal zcdkye;
	/**表内不良余额*/
	@Excel(name = "表内不良余额", width = 15)
    @ApiModelProperty(value = "表内不良余额")
	private java.math.BigDecimal bnbldkye;
	/**表外不良余额*/
	@Excel(name = "表外不良余额", width = 15)
    @ApiModelProperty(value = "表外不良余额")
	private java.math.BigDecimal bwbldkye;
	/**是否诉讼*/
	@Excel(name = "是否诉讼", width = 15)
    @ApiModelProperty(value = "是否诉讼")
	@Dict(dicCode = "sfbz")
	private String sfss;
	/**是否服刑*/
	@Excel(name = "是否服刑", width = 15)
    @ApiModelProperty(value = "是否服刑")
	@Dict(dicCode = "sfbz")
	private String sffx;
	/**是否涉毒*/
	@Excel(name = "是否涉毒", width = 15)
    @ApiModelProperty(value = "是否涉毒")
	@Dict(dicCode = "sfbz")
	private String sfsd;
	/**是否五保低保户*/
	@Excel(name = "是否五保低保户", width = 15)
    @ApiModelProperty(value = "是否五保低保户")
	@Dict(dicCode = "sfbz")
	private String sfwbdbh;
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
	/**是否开通存款业务*/
	@Excel(name = "是否开通存款业务", width = 15)
	@ApiModelProperty(value = "是否开通存款业务")
	@Dict(dicCode = "sfbz")
	private String sfktckyw;
	/**是否开通贷款业务*/
	@Excel(name = "是否开通贷款业务", width = 15)
	@ApiModelProperty(value = "是否开通贷款业务")
	@Dict(dicCode = "sfbz")
	private String sfktdkyw;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
	@ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckyeGr;
	/**存款日平余额*/
	@Excel(name = "存款日平余额", width = 15)
	@ApiModelProperty(value = "存款日平余额")
	private java.math.BigDecimal ckrpyeGr;
	/**存款年日平余额*/
	@Excel(name = "存款年日平余额", width = 15)
	@ApiModelProperty(value = "存款年日平余额")
	private java.math.BigDecimal cknrpyeGr;
	/**是否开通手机银行业务*/
	@Excel(name = "是否开通手机银行业务", width = 15)
	@ApiModelProperty(value = "是否开通手机银行业务")
	@Dict(dicCode = "sfbz")
	private String sfktsjyhyw;
	/**是否开通社保卡*/
	@Excel(name = "是否开通社保卡", width = 15)
	@ApiModelProperty(value = "是否开通社保卡")
	@Dict(dicCode = "sfbz")
	private String sfktsbk;
	/**授信额度*/
	@Excel(name = "授信额度", width = 15)
	@ApiModelProperty(value = "授信额度")
	private java.math.BigDecimal sxed;
	/**授信开始日期*/
	@Excel(name = "授信开始日期", width = 15)
	@ApiModelProperty(value = "授信开始日期")
	private String sxksrq;
	/**授信到期日期*/
	@Excel(name = "授信到期日期", width = 15)
	@ApiModelProperty(value = "授信到期日期")
	private String sxdqrq;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
	@ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
	@ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
	@ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "xtpdjg")
	private String khlx;
}

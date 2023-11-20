package org.cmms.modules.khgl.wyxkhxx.entity;

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
 * @Description: 未用信客户信息
 * @Author: jeecg-boot
 * @Date:   2019-09-29
 * @Version: V1.0
 */
@Data
@TableName("khgl_wyxkhxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khgl_wyxkhxx对象", description="未用信客户信息")
public class Wyxkhxx {

	/**组织标识*/
	//@Excel(name = "组织标识", width = 15, dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
    @ApiModelProperty(value = "组织标识")
    @Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String zzbz;
	/**所属营销单元*/
	//@Excel(name = "所属营销单元", width = 15, dicCode="QYBM", dictTable="YXDYGL_CZXXGL", dicText="VILLAGE || ORGANIZE")
    @ApiModelProperty(value = "所属营销单元")
    @Dict(dicCode="QYBM", dictTable="YXDYGL_CZXXGL", dicText="VILLAGE || ORGANIZE")
	private String ssyxdy;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String custid;
	/**户主客户编号*/
	@Excel(name = "户主客户编号", width = 15)
    @ApiModelProperty(value = "户主客户编号")
	private String hzcustid;
	/**与户主关系*/
	@Excel(name = "与户主关系", width = 15, dicCode = "yhzgx")
    @ApiModelProperty(value = "与户主关系")
	@Dict(dicCode = "yhzgx")
	private String yhzgx;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否户主")
	@Dict(dicCode = "sfbz")
	private String sfhz;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@TableId(type = IdType.INPUT)
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
    @ApiModelProperty(value = "性别")
	@Dict(dicCode = "sex")
	private String xb;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
	private String nl;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private String lxfs;
	/**住址*/
	@Excel(name = "住址", width = 15)
    @ApiModelProperty(value = "住址")
	private String zz;
	/**是否用信*/
	@Excel(name = "是否用信", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否用信")
	@Dict(dicCode = "sfbz")
	private String sfyx;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**不良贷款余额*/
	@Excel(name = "不良贷款余额", width = 15)
	@ApiModelProperty(value = "不良贷款余额")
	private java.math.BigDecimal bldkye;
    /**表外不良贷款余额*/
    @Excel(name = "表外不良贷款余额", width = 15)
    @ApiModelProperty(value = "表外不良贷款余额")
    private java.math.BigDecimal bwbldkye;
	/**是否开通存款业务*/
	@Excel(name = "是否开通存款业务", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否开通存款业务")
	@Dict(dicCode = "sfbz")
	private String sfktckyw;
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
	/**录入标识*/
	@Excel(name = "录入标识", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识")
    @Dict(dicCode = "lrbz")
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

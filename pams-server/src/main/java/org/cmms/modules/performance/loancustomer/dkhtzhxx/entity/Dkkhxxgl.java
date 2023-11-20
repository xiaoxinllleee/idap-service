package org.cmms.modules.performance.loancustomer.dkhtzhxx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @Description: 贷款客户综合信息
 * @Author: jeecg-boot
 * @Date:   2023-04-03
 * @Version: V1.0
 */
@Data
@TableName("KHGXGL_DKKHXXGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGXGL_DKKHXXGL对象", description="贷款合同综合信息")
public class Dkkhxxgl {

	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private java.lang.String id;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private java.lang.String jgdm;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private java.lang.String khbh;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
	private java.lang.String hth;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private java.lang.String khmc;
	/**证件类型*/
	/*
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private java.lang.String zjlx;*/
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private java.lang.String zjhm;
	/**客户类型*/
	@Excel(name = "客户类型（1：对公客户 2：个人客户 3：金融机构客户 4：其他企业客户 5：其他个人客户）", width = 15, dicCode = "cust_type")
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "cust_type")
	private java.lang.Integer khlx;
	/**营销类型*/
	/*@Excel(name = "营销类型", width = 15)
    @ApiModelProperty(value = "营销类型")
	private java.lang.Integer yxlx;*/
	/**合同金额*/
	@Excel(name = "合同金额", width = 15)
    @ApiModelProperty(value = "合同金额")
	private java.math.BigDecimal htje;
	/**合同余额*/
	@Excel(name = "合同余额", width = 15)
    @ApiModelProperty(value = "合同余额")
	private java.math.BigDecimal htye;
	/**最早合同发放日期*/
	@Excel(name = "最早合同发放日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最早合同发放日期")
	private java.util.Date zzhtffrq;
	/**最早合同到期日期*/
	@Excel(name = "最早合同到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最早合同到期日期")
	private java.util.Date zzhtdqrq;
	/**合同发放日期*/
	@Excel(name = "合同发放日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "合同发放日期")
	private java.util.Date htffrq;
	/**合同发放日期*/
	@Excel(name = "合同发放日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "合同发放日期")
	private java.util.Date htdqrq;
	/**产品信息*/
	@Excel(name = "产品信息", width = 15)
    @ApiModelProperty(value = "产品信息")
	private java.lang.String cpxx;
	/**营销人*/
	@Excel(name = "营销人", width = 15, dicCode = "yggh", dictTable = "hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "营销人")
	@Dict(dicCode = "yggh", dictTable = "hr_bas_staff", dicText = "ygxm")
	private java.lang.String yxr;
	/**营销比例*/
	@Excel(name = "营销比例", width = 15)
    @ApiModelProperty(value = "营销比例")
	private java.lang.String yxbl;
	/**管户人*/
	@Excel(name = "管户人", width = 15, dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "管户人")
	@Dict(dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	private java.lang.String ghr;
	/**管户比例*/
	@Excel(name = "管户比例", width = 15)
    @ApiModelProperty(value = "管户比例")
	private java.lang.String ghbl;
	/**包收人*/
	@Excel(name = "包收人", width = 15, dicCode = "yggh", dictTable = "hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "包收人")
	@Dict(dicCode = "yggh", dictTable = "hr_bas_staff", dicText = "ygxm")
	private java.lang.String bsr;
	/**包收比例*/
	@Excel(name = "包收比例", width = 15)
    @ApiModelProperty(value = "包收比例")
	private java.lang.String bsbl;
	/**审批人*/
	@Excel(name = "审批人", width = 15, dicCode = "yggh", dictTable = "hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "审批人")
	@Dict(dicCode = "yggh", dictTable = "hr_bas_staff", dicText = "ygxm")
	private java.lang.String spr;
	/**审批比例*/
	@Excel(name = "审批比例", width = 15)
    @ApiModelProperty(value = "审批比例")
	private java.lang.String spbl;
	/**调查人*/
	@Excel(name = "调查人", width = 15, dicCode = "yggh", dictTable = "hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "调查人")
	@Dict(dicCode = "yggh", dictTable = "hr_bas_staff", dicText = "ygxm")
	private java.lang.String dcr;
	/**调查比例*/
	@Excel(name = "调查比例", width = 15)
    @ApiModelProperty(value = "调查比例")
	private java.lang.String dcbl;
	/**录入标识（0：导入 1：录入 2：修改）*/
	@Excel(name = "录入标识", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private java.lang.Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private java.lang.String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private java.util.Date lrsj;
}

package org.cmms.modules.khgl.xyk.entity;

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
 * @Description: 信用卡_慈利
 * @Author: jeecg-boot
 * @Date:   2022-11-14
 * @Version: V1.0
 */
@Data
@TableName("erp_dzyhgl_xyk")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="erp_dzyhgl_xyk对象", description="信用卡_慈利")
public class KhglXyk {

	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**卡号*/
	@Excel(name = "卡号", width = 15)
    @ApiModelProperty(value = "卡号")
	private String kh;
	/**卡种*/
	@Excel(name = "卡种", width = 15)
    @ApiModelProperty(value = "卡种")
	private String kz;
	/**推广人员工号*/
	@Excel(name = "推广人员工号", width = 15)
    @ApiModelProperty(value = "推广人员工号")
	private String yggh;
	/**卡片状态*/
	@Excel(name = "卡片状态", width = 15)
    @ApiModelProperty(value = "卡片状态")
	private String kpzt;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15,dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "组织标志")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String tgryssdw;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String xm;
	/**单位名称*/
	@Excel(name = "单位名称", width = 15)
    @ApiModelProperty(value = "单位名称")
	private String dwmc;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	private String sjhm;
	/**账单透支金额*/
	@Excel(name = "账单透支金额", width = 15)
    @ApiModelProperty(value = "账单透支金额")
	private java.math.BigDecimal zdtzje;
	/**账单逾期金额*/
	@Excel(name = "账单逾期金额", width = 15)
    @ApiModelProperty(value = "账单逾期金额")
	private java.math.BigDecimal zdyqje;
	/**一期逾期金额*/
	@Excel(name = "一期逾期金额", width = 15)
    @ApiModelProperty(value = "一期逾期金额")
	private java.math.BigDecimal yqje1;
	/**二期逾期金额*/
	@Excel(name = "二期逾期金额", width = 15)
    @ApiModelProperty(value = "二期逾期金额")
	private java.math.BigDecimal yqje2;
	/**三期逾期金额*/
	@Excel(name = "三期逾期金额", width = 15)
    @ApiModelProperty(value = "三期逾期金额")
	private java.math.BigDecimal yqje3;
	/**四期逾期金额*/
	@Excel(name = "四期逾期金额", width = 15)
    @ApiModelProperty(value = "四期逾期金额")
	private java.math.BigDecimal yqje4;
	/**五期逾期金额*/
	@Excel(name = "五期逾期金额", width = 15)
    @ApiModelProperty(value = "五期逾期金额")
	private java.math.BigDecimal yqje5;
	/**六期逾期金额*/
	@Excel(name = "六期逾期金额", width = 15)
    @ApiModelProperty(value = "六期逾期金额")
	private java.math.BigDecimal yqje6;
	/**其他逾期金额*/
	@Excel(name = "其他逾期金额", width = 15)
    @ApiModelProperty(value = "其他逾期金额")
	private java.math.BigDecimal qtyqje;
	/**透支金额*/
	@Excel(name = "透支金额", width = 15)
    @ApiModelProperty(value = "透支金额")
	private java.math.BigDecimal tzje;
	/**激活日期*/
	@Excel(name = "激活日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "激活日期")
	private Date jhrq;
	/**发卡日期*/
	@Excel(name = "发卡日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "发卡日期")
	private Date fkrq;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private String lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**推广人员姓名*/
	@Excel(name = "推广人员姓名", width = 15)
    @ApiModelProperty(value = "推广人员姓名")
	private String ygxm;

	/*@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")*/
	@TableField(exist = false)
	private String bz;
	/**是否催收(1:已催收2:未催收)*/
	//@Excel(name = "是否催收", width = 15)
	@ApiModelProperty(value = "是否催收")
	private String sfcs ;
}

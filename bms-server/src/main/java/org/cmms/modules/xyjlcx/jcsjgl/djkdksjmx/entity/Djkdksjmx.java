package org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 贷记卡贷款数据明细
 * @Author: jeecg-boot
 * @Date:   2021-08-10
 * @Version: V1.0
 */
@Data
@TableName("CREDIT_DJKDKSJMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CREDIT_DJKDKSJMX对象", description="贷记卡贷款数据明细")
public class Djkdksjmx {

	/**业务机构*/
	@Excel(name = "业务机构", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "业务机构")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String ywjg;
	/**卡号*/
	@Excel(name = "卡号", width = 15)
	@ApiModelProperty(value = "卡号")
	@ExcelVerify(notNull = true)
	private String kh;
	/**卡种类*/
	@Excel(name = "卡种类", width = 15,dicCode = "kzl")
	@ApiModelProperty(value = "卡种类")
	@Dict(dicCode = "kzl")
	@ExcelVerify(notNull = true)
	private String kzl;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15,dicCode = "dkjkpt_zjlx")
	@ApiModelProperty(value = "证件类型")
	@Dict(dicCode = "dkjkpt_zjlx")
	@ExcelVerify(notNull = true)
	private Integer zjlx;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	@ExcelVerify(notNull = true)
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	@ExcelVerify(notNull = true)
	private String khmc;
	/**性别*/
	@Excel(name = "性别", width = 15,dicCode = "sex_djk")
	@ApiModelProperty(value = "性别")
	@Dict(dicCode = "sex_djk")
	private String xb;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15,dicCode = "hyzk_ly")
	@ApiModelProperty(value = "婚姻状况")
	@Dict(dicCode = "hyzk_ly")
	private Integer hyzk;
	/**家庭住址*/
	@Excel(name = "家庭住址", width = 15)
	@ApiModelProperty(value = "家庭住址")
	private String jtzz;
	/**手机号码*/
	@Excel(name = "联系电话", width = 15)
	@ApiModelProperty(value = "联系电话")
	private String sjhm;

	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "到期日期")
	private Date dqrq;
	/**授信金额*/
	@Excel(name = "授信金额", width = 15)
	@ApiModelProperty(value = "授信金额")
	private java.math.BigDecimal sxje;
	/**透支本金*/
	@Excel(name = "透支本金", width = 15)
	@ApiModelProperty(value = "透支本金")
	private java.math.BigDecimal tzbj;
	/**透支余额*/
	@Excel(name = "透支余额", width = 15)
	@ApiModelProperty(value = "透支余额")
	private java.math.BigDecimal tzye;
	/**发卡日期*/
	@Excel(name = "发放日期", width = 15, format = "yyyyMMdd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "发放日期")
	@ExcelVerify(notNull = true)
	private Date fkrq;
	/**推广人工号*/
	@Excel(name = "推广人工号", width = 15)
	@ApiModelProperty(value = "推广人工号")
	@ExcelVerify(notNull = true)
	private String tgrgh;
	/**卡状态标志*/
	@Excel(name = "卡状态标志", width = 15,dicCode = "djkzl")
	@ApiModelProperty(value = "卡状态标志")
	@Dict(dicCode = "djkzl")
	@ExcelVerify(interHandler = true)
	private String kztbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15,dicCode = "lrbz")
	@ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;

	/**员工工号*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "员工工号")
	private String yggh;

	//机构名称
	@TableField(exist = false)
	private String jgmc;
	//卡状态标识名称
	@TableField(exist = false)
	private String kztbzShow;
	//卡种类名称
	@TableField(exist = false)
	private String kzlShow;
	//员工姓名
	@TableField(exist = false)
	private String ygxm;
	//透支余额录入时间
	@TableField(exist = false)
	private String tzyeLrsj;
	//逾期期数
	@TableField(exist = false)
	private Integer yqqs;
	//逾期期数录入时间
	@TableField(exist = false)
	private String yqqsLrsj;
}

package org.cmms.modules.xddagl.dkdagl.dkdahtsjglbc.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * @Description: 贷款合同数据管理(补充)
 * @Author: jeecg-boot
 * @Date:   2022-01-07
 * @Version: V1.0
 */
@Data
@TableName("Xddagl_dkhtsjxx_bc")
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
@ApiModel(value="Xddagl_dkhtsjxx_bc对象", description="贷款合同数据管理(补充)")
public class Dkdahtsjglbc {

	/**档案编号*/
	@Excel(name = "档案编号", width = 15)
	@ApiModelProperty(value = "档案编号")
	private String dabh;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm",dictTable = "HR_BAS_ORGANIZATION",dicText = "zzjc")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm",dictTable = "HR_BAS_ORGANIZATION",dicText = "zzjc")
	private String jgdm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15,dicCode = "xddagl_khlx")
	@ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "xddagl_khlx")
	private String khlx;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
	@ApiModelProperty(value = "联系电话")
	private String lxdh;
	/**联系地址*/
	@Excel(name = "联系地址", width = 15)
	@ApiModelProperty(value = "联系地址")
	private String lxdz;
	/**签约日期*/
	@Excel(name = "签约日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "签约日期")
	private Date qyrq;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
	@ApiModelProperty(value = "合同号")
	@ExcelVerify(notNull = true)
	private String hth;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
	@ApiModelProperty(value = "业务编号")
	private String ywbh;
	/**签约期限*/
	@Excel(name = "签约期限", width = 15)
	@ApiModelProperty(value = "签约期限")
	private String qyqx;
	/**签约金额*/
	@Excel(name = "合同金额", width = 15)
	@ApiModelProperty(value = "合同金额")
	private java.math.BigDecimal qyje;
	/**贷款责任人*/
	@Excel(name = "贷款责任人工号",dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	@ApiModelProperty(value = "贷款责任人工号")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	@ExcelVerify(interHandler = true)
	private String dkzrr;
	/**是否上传档案*/
	@Excel(name = "是否上传档案", width = 15)
	@ApiModelProperty(value = "是否上传档案")
	@Dict(dicCode = "sfbz")
	private String sfscda;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
	@ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
}

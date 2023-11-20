package org.cmms.modules.ywgl.nxt.shpj.shdjpd.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 商户等级评定
 * @Author: jeecg-boot
 * @Date:   2021-09-23
 * @Version: V1.0
 */
@Data
@TableName("erp_nxt_shdjpd")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="erp_nxt_shdjpd对象", description="商户等级评定")
public class Shdjpd {

	/**评定类型*/
	@Excel(name = "评定类型", width = 15,dicCode = "pdlx")
    @ApiModelProperty(value = "评定类型")
	@Dict(dicCode = "pdlx")
	@ExcelVerify(notNull = true)
	private String pdlx;
	/**评定周期*/
	@Excel(name = "评定周期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评定周期")
	@ExcelVerify(notNull = true)
	private Date pdzq;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ExcelVerify(notNull = true)
	private String jgdm;
	/**商户编码*/
	@Excel(name = "商户编码", width = 15)
    @ApiModelProperty(value = "商户编码")
	@ExcelVerify(notNull = true)
	private String shbm;
	/**商户名称*/
	@Excel(name = "商户名称", width = 15)
    @ApiModelProperty(value = "商户名称")
	@ExcelVerify(notNull = true)
	private String shmc;
	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
	@ApiModelProperty(value = "客户经理标识")
	@ExcelVerify(notNull = true)
	private String khjlbz;
	/**上期评定日平*/
	@Excel(name = "上期评定日平", width = 15)
	@ApiModelProperty(value = "上期评定日平")
	@ExcelVerify(notNull = true)
	private java.math.BigDecimal sqpdrp;
	/**上期评定等级*/
	@Excel(name = "上期评定等级", width = 15,dicCode = "csbh", dictTable = "ERP_SHPJ_SHPJCSSZ", dicText = "csmc", ds = "cdkyw")
	@ApiModelProperty(value = "上期评定等级")
	@Dict(dicCode = "csbh", dictTable = "ERP_SHPJ_SHPJCSSZ", dicText = "csmc", ds = "cdkyw")
	private String sqpddj;
	/**本期评定日平*/
	@Excel(name = "本期评定日平", width = 15)
	@ApiModelProperty(value = "本期评定日平")
	private java.math.BigDecimal bqpdrp;
	/**本期评定等级*/
	@Excel(name = "本期评定等级", width = 15,dicCode = "csbh", dictTable = "ERP_SHPJ_SHPJCSSZ", dicText = "csmc", ds = "cdkyw")
	@ApiModelProperty(value = "本期评定等级")
	@Dict(dicCode = "csbh", dictTable = "ERP_SHPJ_SHPJCSSZ", dicText = "csmc", ds = "cdkyw")
	private String bqpddj;
	/**评定日期*/
	@Excel(name = "评定日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "评定日期")
	private Date pdrq;

	/**录入标志*/
	@Excel(name = "录入标志", width = 15,dicCode = "lrbz")
	@ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入日期*/
	@Excel(name = "录入日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入日期")
	private Date lrrq;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
	@ApiModelProperty(value = "修改人")
	private String xgr;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "修改时间")
	private Date xgrq;
	/**联系电话*/
    @ApiModelProperty(value = "联系电话")
	private String lxdh;
	/**关联账户（户名-账号,户名-账号...)*/
    @ApiModelProperty(value = "关联账户")
	private String glzh;

	/**等级是否有调整*/
	@Excel(name = "等级是否有调整", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "等级是否有调整")
	@Dict(dicCode = "sfbz")
	@ExcelVerify(notNull = true,interHandler = true)
	private Integer djsfytz;
}

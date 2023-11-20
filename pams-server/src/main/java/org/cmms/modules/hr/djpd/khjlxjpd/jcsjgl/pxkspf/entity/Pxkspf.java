package org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.pxkspf.entity;

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
 * @Description: 培训考试评分
 * @Author: jeecg-boot
 * @Date:   2021-09-07
 * @Version: V1.0
 */
@Data
@TableName("GRADE_CUST_PXKSPF")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GRADE_CUST_PXKSPF对象", description="培训考试评分")
public class Pxkspf {
	/**组织标识*/
	@Excel(name = "机构名称", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构名称")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	@ExcelVerify(notNull = true)
	/**岗位标识*/
	@Excel(name = "岗位标识", width = 15,dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
    @ApiModelProperty(value = "岗位标识")
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private Integer gwbz;
	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
	@ApiModelProperty(value = "客户经理标识")
	@ExcelVerify(notNull = true)
	private String khjlbz;
	/**员工工号*/
	@Excel(name = "客户经理名称", width = 15,dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
    @ApiModelProperty(value = "客户经理名称")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	@ExcelVerify(notNull = true)
	private String yggh;
	/**考试时间*/
	@Excel(name = "考试时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "考试时间")
	@ExcelVerify(notNull = true)
	private Date kssj;
	/**考试名称*/
	@Excel(name = "考试名称", width = 15)
	@ApiModelProperty(value = "考试名称")
	private String ksmc;
	/**考试描述*/
	@Excel(name = "考试描述", width = 15)
	@ApiModelProperty(value = "考试描述")
	private String ksms;
	/**考试得分*/
	@Excel(name = "考试得分", width = 15)
	@ApiModelProperty(value = "考试得分")
	@ExcelVerify(notNull = true)
	private java.math.BigDecimal ksdf;
	/**考试类型（1：开卷 2：闭卷）*/
	@Excel(name = "考试类型", width = 15,dicCode = "kslx")
	@ApiModelProperty(value = "考试类型")
	@Dict(dicCode = "kslx")
	@ExcelVerify(notNull = true,interHandler = true)
	private Integer kslx;


	/**录入标识（0：导入 1：录入 2：修改）*/
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
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrr;
}

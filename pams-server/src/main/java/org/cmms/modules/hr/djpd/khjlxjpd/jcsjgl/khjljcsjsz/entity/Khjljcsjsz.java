package org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.entity;

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
 * @Description: 客户经理基础数据设置
 * @Author: jeecg-boot
 * @Date:   2021-09-07
 * @Version: V1.0
 */
@Data
@TableName("GRADE_CUST_STARSTAFF")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GRADE_CUST_STARSTAFF对象", description="客户经理基础数据设置")
public class Khjljcsjsz {

	/**评定周期*/
	@Excel(name = "评定周期", width = 15,dicCode = "rqwd")
    @ApiModelProperty(value = "评定周期")
	@Dict(dicCode = "rqwd")
	@ExcelVerify(notNull = true)
	private String pdzq;
	/**评定日期*/
	@Excel(name = "评定日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评定日期")
	@ExcelVerify(notNull = true)
	private Date pdrq;
	/**组织标识*/
	@Excel(name = "机构名称", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构名称")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ExcelVerify(notNull = true)
	private String zzbz;
	/**岗位标识*/
	@Excel(name = "岗位标识", width = 15,dicCode = "gwbz", dictTable = "Hr_bas_post", dicText = "gwmc")
    @ApiModelProperty(value = "岗位标识")
	@Dict(dicCode = "gwbz", dictTable = "Hr_bas_post", dicText = "gwmc")
	private Integer gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;
	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
    @ApiModelProperty(value = "客户经理标识")
	private String khjlbz;
	/**员工姓名*/
	@Excel(name = "客户经理名称", width = 15)
	@ApiModelProperty(value = "客户经理名称")
	@ExcelVerify(notNull = true)
	private String ygxm;
	/**身份证号码*/
	@Excel(name = "身份证号码", width = 15)
    @ApiModelProperty(value = "身份证号码")
	private String sfzhm;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
	private Integer age;
	/**工龄*/
	@Excel(name = "工龄", width = 15)
    @ApiModelProperty(value = "工龄")
	private Integer gl;
	/**电话*/
	@Excel(name = "电话", width = 15)
    @ApiModelProperty(value = "电话")
	private String phone;
	/**入行时间*/
	@Excel(name = "入行时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "入行时间")
	private Date rhsj;
	/**从事客户经理时间*/
	@Excel(name = "从事客户经理时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "从事客户经理时间")
	private Date cskhjlsj;
	/**是否参与评定（1：是 2：不参与评定 3：不参与评定与统计）*/
	@Excel(name = "是否参与评定", width = 15,dicCode = "sfcypd")
	@ApiModelProperty(value = "是否参与评定")
	@Dict(dicCode = "sfcypd")
	@ExcelVerify(interHandler = true)
	private Integer sfcypd;
	/**录入标识（0：导入 1：录入 2：修改）*/
	@Excel(name = "录入标识", width = 15)
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

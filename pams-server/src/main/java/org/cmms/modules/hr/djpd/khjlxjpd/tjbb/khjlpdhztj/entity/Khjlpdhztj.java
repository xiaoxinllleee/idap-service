package org.cmms.modules.hr.djpd.khjlxjpd.tjbb.khjlpdhztj.entity;

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
 * @Description: 客户经理评定汇总统计
 * @Author: jeecg-boot
 * @Date:   2021-09-17
 * @Version: V1.0
 */
@Data
@TableName("V_GRADE_CUSTSTAR_HZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_GRADE_CUSTSTAR_HZ对象", description="客户经理评定汇总统计")
public class Khjlpdhztj {
	/**pdzq*/
	@Excel(name = "评定周期", width = 15,dicCode = "rqwd")
	@ApiModelProperty(value = "评定周期")
	@Dict(dicCode = "rqwd")
	private String pdzq;
	/**pdrq*/
	@Excel(name = "评定日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "评定日期")
	private Date pdrq;
	/**djbh*/
	@Excel(name = "支行级别", width = 15,dicCode = "djbh", dictTable = "v_grade_custstar_zhdj", dicText = "djms",ds = "eweb")
	@ApiModelProperty(value = "支行级别")
	//@Dict(dicCode = "zhjb")
	@Dict(dicCode = "djbh", dictTable = "v_grade_custstar_zhdj", dicText = "djms",ds = "eweb")
	private String djbh;


	/**wxjtzdjbh*/
	//@Excel(name = "wxjtzdjbh", width = 15)
    @ApiModelProperty(value = "wxjtzdjbh")
	private String wxjtzdjbh;


	/**zzbz*/
	@Excel(name = "支行名称", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "支行名称")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**sjzzbz*/
	@Excel(name = "机构名称", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "机构名称")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String sjzzbz;
	/**ygxm*/
	@Excel(name = "客户经理名称", width = 15)
	@ApiModelProperty(value = "客户经理名称")
	private String ygxm;
	/**pjdf*/
	@Excel(name = "总得分", width = 15)
	@ApiModelProperty(value = "总得分")
	private java.math.BigDecimal pjdf;
	/**dfpddjbh*/
	@Excel(name = "按分评定等级", width = 15)
	@ApiModelProperty(value = "按分评定等级")
	private String dfpddjbh;
	/**age*/
	@Excel(name = "年龄", width = 15)
	@ApiModelProperty(value = "年龄")
	private Integer age;
	/**gl*/
	@Excel(name = "工龄", width = 15)
	@ApiModelProperty(value = "工龄")
	private Integer gl;
	/**nlgltzdjbh*/
	@Excel(name = "因年龄和工龄调整", width = 15)
	@ApiModelProperty(value = "因年龄和工龄调整")
	private String nlgltzdjbh;
	/**bldkzbJg*/
	@Excel(name = "不良贷款占比", width = 15)
	@ApiModelProperty(value = "不良贷款占比")
	private Integer bldkzbJg;
	/**yqndqshlJg*/
	@Excel(name = "以前年度发放当年到期贷款收回率", width = 15)
	@ApiModelProperty(value = "以前年度发放当年到期贷款收回率")
	private Integer yqndqshlJg;


	/**djms*/
	//@Excel(name = "djms", width = 15)
    @ApiModelProperty(value = "djms")
	private String djms;
	/**gwbz*/
	//@Excel(name = "岗位标识", width = 15)
    @ApiModelProperty(value = "岗位标识")
	private Integer gwbz;
	/**yggh*/
	//@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;

	/**dndqshlJg*/
	@Excel(name = "当年发放当年到期贷款收回率", width = 15)
    @ApiModelProperty(value = "当年发放当年到期贷款收回率")
	private Integer dndqshlJg;
	/**bltzdjbh*/
	@Excel(name = "因不良调整", width = 15)
    @ApiModelProperty(value = "因不良调整")
	private String bltzdjbh;
	/**dxbfdf*/
	@Excel(name = "定性部分得分", width = 15)
    @ApiModelProperty(value = "定性部分得分")
	private Integer dxbfdf;
	/**zhzhpjjg*/
	@Excel(name = "支行评价结果", width = 15,dicCode = "pjjg")
    @ApiModelProperty(value = "支行评价结果")
	@Dict(dicCode = "pjjg")
	private Integer zhzhpjjg;
	/**zbzhpjJg*/
	@Excel(name = "总部评价优秀得票率", width = 15)
    @ApiModelProperty(value = "总部评价优秀得票率")
	private Integer zbzhpjJg;
	/**dxpjtzdjbh*/
	@Excel(name = "按定性和评价调整", width = 15)
    @ApiModelProperty(value = "按定性和评价调整")
	private String dxpjtzdjbh;
	/**gwzzDf*/
	@Excel(name = "岗位资质得分", width = 15)
	@ApiModelProperty(value = "岗位资质得分")
	private Integer gwzzDf;
	/**kspm*/
	@Excel(name = "考试成绩排名", width = 15)
	@ApiModelProperty(value = "考试成绩排名")
	private Integer kspm;
	/**tzhdjbh*/
	@Excel(name = "调整后等级", width = 15,dicCode = "csbh",dictTable = "Grade_cust_cssz",dicText = "csmc",ds = "eweb")
	@ApiModelProperty(value = "调整后等级")
	@Dict(dicCode = "csbh",dictTable = "Grade_cust_cssz",dicText = "csmc",ds = "eweb")
	private Integer tzhdjbh;
	/**sndjbh*/
	@Excel(name = "上年等级", width = 15,dicCode = "csbh",dictTable = "Grade_cust_cssz",dicText = "csmc",ds = "eweb")
	@ApiModelProperty(value = "上年等级")
	@Dict(dicCode = "csbh",dictTable = "Grade_cust_cssz",dicText = "csmc",ds = "eweb")
	private Integer sndjbh;
}

package org.cmms.modules.hr.djpd.khjlxjpd.tjbb.khjlpdpjywtj.entity;

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
 * @Description: 客户经理评定平均业务统计
 * @Author: jeecg-boot
 * @Date:   2021-09-18
 * @Version: V1.0
 */
@Data
@TableName("V_GRADE_CUSTSTAR_PJYWTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_GRADE_CUSTSTAR_PJYWTJ对象", description="客户经理评定平均业务统计")
public class Khjlpdpjywtj {

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
	@Excel(name = "支行级别", width = 15,dicCode = "djbn", dictTable = "v_grade_custstar_zhdj", dicText = "djms")
    @ApiModelProperty(value = "支行级别")
	@Dict(dicCode = "djbh", dictTable = "v_grade_custstar_zhdj", dicText = "djms",ds = "eweb")
	private String djbh;
	/**djms*/
	//@Excel(name = "支行级别", width = 15,dicCode = "djbh", dictTable = "v_grade_custstar_zhdj", dicText = "djms",ds = "eweb")
    @ApiModelProperty(value = "支行级别")

	private String djms;
	/**cknrp*/
	@Excel(name = "存款日平", width = 15)
    @ApiModelProperty(value = "存款日平")
	private Integer cknrp;
	/**dknrp*/
	@Excel(name = "贷款日平", width = 15)
    @ApiModelProperty(value = "贷款日平")
	private Integer dknrp;
	/**dkhs*/
	@Excel(name = "贷款户数", width = 15)
    @ApiModelProperty(value = "贷款户数")
	private Integer dkhs;
	/**bldkzb*/
	@Excel(name = "不良贷款占比", width = 15)
    @ApiModelProperty(value = "不良贷款占比")
	private Integer bldkzb;
	/**djkhys*/
	@Excel(name = "贷记卡管理数", width = 15)
    @ApiModelProperty(value = "贷记卡管理数")
	private Integer djkhys;
	/**bwbldkshzb*/
	@Excel(name = "表外不良收回占比", width = 15)
    @ApiModelProperty(value = "表外不良收回占比")
	private Integer bwbldkshzb;
	/**sjyh*/
	@Excel(name = "手机银行", width = 15)
    @ApiModelProperty(value = "手机银行")
	private Integer sjyh;
	/**kdlq*/
	@Excel(name = "口袋零钱", width = 15)
    @ApiModelProperty(value = "口袋零钱")
	private Integer kdlq;
	/**pos*/
	@Excel(name = "pos机", width = 15)
    @ApiModelProperty(value = "pos机")
	private Integer pos;
}

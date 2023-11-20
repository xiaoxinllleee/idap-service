package org.cmms.modules.hr.djpd.khjlxjpd.tjbb.khjlpdxjtj.entity;

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
 * @Description: 客户经理评定星级统计
 * @Author: jeecg-boot
 * @Date:   2021-09-22
 * @Version: V1.0
 */
@Data
@TableName("V_GRADE_CUSTSTAR_XJTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_GRADE_CUSTSTAR_XJTJ对象", description="客户经理评定星级统计")
public class Khjlpdxjtj {

	/**pdzq*/
	@Excel(name = "评定周期", width = 15)
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
	@Dict(dicCode = "djbh", dictTable = "v_grade_custstar_zhdj", dicText = "djms",ds = "eweb")
	private String djbh;
	/**djms*/
	//@Excel(name = "支行级别", width = 15,dicCode = "djbh", dictTable = "v_grade_custstar_zhdj", dicText = "djms",ds = "eweb")
    @ApiModelProperty(value = "支行级别")
	private String djms;
	/**khjlrs*/
	@Excel(name = "客户经理人数", width = 15)
    @ApiModelProperty(value = "客户经理人数")
	private Integer khjlrs;
	/**wxjrs*/
	@Excel(name = "五星级", width = 15)
    @ApiModelProperty(value = "五星级")
	private Integer wxjrs;
	/**sixjrs*/
	@Excel(name = "四星级", width = 15)
    @ApiModelProperty(value = "四星级")
	private Integer sixjrs;
	/**sxjrs*/
	@Excel(name = "三星级", width = 15)
    @ApiModelProperty(value = "三星级")
	private Integer sxjrs;
	/**exjrs*/
	@Excel(name = "二星级", width = 15)
    @ApiModelProperty(value = "二星级")
	private Integer exjrs;
	/**yxjrs*/
	@Excel(name = "一星级", width = 15)
    @ApiModelProperty(value = "一星级")
	private Integer yxjrs;
	/**bcypdrs*/
	@Excel(name = "不参与评定人员", width = 15)
    @ApiModelProperty(value = "不参与评定人员")
	private Integer bcypdrs;
}

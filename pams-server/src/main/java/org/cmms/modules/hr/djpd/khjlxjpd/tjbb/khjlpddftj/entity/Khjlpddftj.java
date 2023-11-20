package org.cmms.modules.hr.djpd.khjlxjpd.tjbb.khjlpddftj.entity;

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
 * @Description: 客户经理评定得分统计
 * @Author: jeecg-boot
 * @Date:   2021-09-18
 * @Version: V1.0
 */
@Data
@TableName("V_GRADE_CUSTSTAR_DFTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_GRADE_CUSTSTAR_DFTJ对象", description="客户经理评定得分统计")
public class Khjlpddftj {

	/**bcypdrs*/
	@Excel(name = "不参与评定人员", width = 15)
    @ApiModelProperty(value = "不参与评定人员")
	private Integer bcypdrs;
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
	//@Excel(name = "djbh", width = 15)
    @ApiModelProperty(value = "djbh")
	private String djbh;
	/**djms*/
	@Excel(name = "支行级别", width = 15,dicCode = "djbh", dictTable = "v_grade_custstar_zhdj", dicText = "djms",ds = "eweb")
    @ApiModelProperty(value = "支行级别")
	@Dict(dicCode = "djms", dictTable = "v_grade_custstar_zhdj", dicText = "djbh",ds = "eweb")
	private String djms;
	/**khjlrs*/
	@Excel(name = "客户经理人数", width = 15)
    @ApiModelProperty(value = "客户经理人数")
	private Integer khjlrs;
	/**dfqj1*/
	@Excel(name = "100分以上", width = 15)
    @ApiModelProperty(value = "100分以上")
	private Integer dfqj1;
	/**dfqj2*/
	@Excel(name = "95分-100分", width = 15)
    @ApiModelProperty(value = "95分-100分")
	private Integer dfqj2;
	/**dfqj3*/
	@Excel(name = "85分-95分", width = 15)
    @ApiModelProperty(value = "85分-95分")
	private Integer dfqj3;
	/**dfqj4*/
	@Excel(name = "75分-85分", width = 15)
    @ApiModelProperty(value = "75分-85分")
	private Integer dfqj4;
	/**dfqj5*/
	@Excel(name = "75分以下", width = 15)
    @ApiModelProperty(value = "75分以下")
	private Integer dfqj5;
}

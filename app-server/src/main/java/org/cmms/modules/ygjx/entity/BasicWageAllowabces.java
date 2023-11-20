package org.cmms.modules.ygjx.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 津贴
 * @Author: jeecg-boot
 * @Date:   2022-03-02
 * @Version: V1.0
 */
@Data
@TableName("BASIC_WAGE_ALLOWABCES")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BASIC_WAGE_ALLOWABCES对象", description="津贴")
public class BasicWageAllowabces {
    
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**组织标识*/
	@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	private String zzbz;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;
	/**员工姓名*/
	@Excel(name = "员工姓名", width = 15)
    @ApiModelProperty(value = "员工姓名")
	private String ygxm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**岗位标识*/
	@Excel(name = "岗位标识", width = 15)
    @ApiModelProperty(value = "岗位标识")
	private String gwbz;
	/**工资合计*/
	@Excel(name = "工资合计", width = 15)
    @ApiModelProperty(value = "工资合计")
	private java.math.BigDecimal totalWage;
	/**防暑降温费用*/
	@Excel(name = "防暑降温费用", width = 15)
    @ApiModelProperty(value = "防暑降温费用")
	private java.math.BigDecimal fsjwAllowance;
	/**退伍军人慰问*/
	@Excel(name = "退伍军人慰问", width = 15)
    @ApiModelProperty(value = "退伍军人慰问")
	private java.math.BigDecimal veteranAllowance;
	/**残疾补助*/
	@Excel(name = "残疾补助", width = 15)
    @ApiModelProperty(value = "残疾补助")
	private java.math.BigDecimal disabilityAllowance;
	/**荣誉奖金*/
	@Excel(name = "荣誉奖金", width = 15)
    @ApiModelProperty(value = "荣誉奖金")
	private java.math.BigDecimal honorBonus;
	/**交通补助*/
	@Excel(name = "交通补助", width = 15)
    @ApiModelProperty(value = "交通补助")
	private java.math.BigDecimal transportAllowance;
	/**其它津贴*/
	@Excel(name = "其它津贴", width = 15)
    @ApiModelProperty(value = "其它津贴")
	private java.math.BigDecimal otherAllowance;
	/**录入标识:0 导入 1 录入 2 修改*/
	@Excel(name = "录入标识:0 导入 1 录入 2 修改", width = 15)
    @ApiModelProperty(value = "录入标识:0 导入 1 录入 2 修改")
	private Integer lrbz;
	/**录入操作员*/
	@Excel(name = "录入操作员", width = 15)
    @ApiModelProperty(value = "录入操作员")
	private String lrczy;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**(网点\分理处)上级组织标识*/
	@Excel(name = "(网点分理处)上级组织标识", width = 15)
    @ApiModelProperty(value = "(网点分理处)上级组织标识")
	private String sjzzbz;
}

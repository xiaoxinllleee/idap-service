package org.cmms.modules.activiti.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 小额农贷审批历史
 * @Author: jeecg-boot
 * @Date:   2020-09-06
 * @Version: V1.0
 */
@Data
@TableName("ACT_HMD_SPLS")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ACT_HMD_SPLS对象", description="黑名单审批历史")
public class ActHmdSpls {
    
	/**审批id*/
	@Excel(name = "审批id", width = 15)
    @ApiModelProperty(value = "审批id")
	@TableId(type = IdType.ASSIGN_ID)
	private String spid;
	/**流程id*/
	@Excel(name = "流程id", width = 15)
    @ApiModelProperty(value = "流程id")
	private String procInstId;

	/**流程id*/
	@Excel(name = "实例id", width = 15)
	@ApiModelProperty(value = "实例id")
	private String taskid;

	/**节点人*/
	@Excel(name = "节点人", width = 15)
    @ApiModelProperty(value = "节点人")
	private String jdr;
	/**用户id*/
	@Excel(name = "用户id", width = 15)
    @ApiModelProperty(value = "用户id")
	private String userid;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String yggh;
	/**审批建议*/
	@Excel(name = "审批建议", width = 15)
    @ApiModelProperty(value = "审批建议")
	private String spyj;

	@Excel(name = "评定等级", width = 15)
	@ApiModelProperty(value = "评定等级")
	@Dict(dicCode = "grdk_pddj")
	private String pddj;
	/**建议额度*/
	@Excel(name = "建议额度", width = 15)
    @ApiModelProperty(value = "建议额度")
	private BigDecimal jyed;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;

	/**创建人*/
	@Excel(name = "创建人", width = 15)
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
	@ApiModelProperty(value = "更新人")
	private String updateBy;

	@Dict(dicCode = "ly_spyj")
	private String status;
	@Dict(dicCode = "ly_xend_cpzl")
	private String cpzl;
	//产品种类利率
	private BigDecimal cpzlll;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
}

package org.cmms.modules.xddaglxt.dksjgl.dascspxxbc.entity;

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
 * @Description: 档案删除审批信息(补充)
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
@Data
@TableName("Xddagl_dascspxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Xddagl_dascspxx对象", description="档案删除审批信息(补充)")
public class Dascspxxbc {
    
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
	@ApiModelProperty(value = "合同号")
	private String hth;
	/**合同金额*/
	@Excel(name = "合同金额", width = 15)
	@ApiModelProperty(value = "合同金额")
	private java.math.BigDecimal htje;
	/**贷款责任人工号*/
	@Excel(name = "贷款责任人工号", width = 15,dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "贷款责任人工号")
	@Dict(dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	private String dkzrr;
	/**业务编号*/
	@Excel(name = "流程业务编号", width = 15)
    @ApiModelProperty(value = "流程业务编号")
	private String businessnumber;
	/**流程状态*/
	@Excel(name = "流程审批节点", width = 15)
    @ApiModelProperty(value = "流程审批节点")
	private String lczt;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;


	/**合同余额*/
	@ApiModelProperty(value = "合同余额")
	private java.math.BigDecimal htye;
	/**贷款品种*/
	@ApiModelProperty(value = "贷款品种")
	private String dkpz;
	/**申请说明*/
    @ApiModelProperty(value = "申请说明")
	private String sqsm;
	/**录入标识*/
    @ApiModelProperty(value = "录入标识")
	private Integer lrbz;
	/**文件id*/
    @ApiModelProperty(value = "文件id")
	private Long wjid;
	/**流程编号*/
	@ApiModelProperty(value = "流程编号")
	private String processid;
}

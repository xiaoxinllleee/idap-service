package org.cmms.modules.performance.loancustomer.dkkhspxx.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @Description: 贷款客户审批信息
 * @Author: jeecg-boot
 * @Date:   2023-04-03
 * @Version: V1.0
 */
@Data
@TableName("KHGXGL_DKKHSPXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGXGL_DKKHSPXX对象", description="贷款客户审批信息")
public class Dkkhspxx {
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "ID")
	private java.lang.String id;


	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private java.lang.String jgdm;

	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private java.lang.String khbh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private java.lang.String khmc;
	/**证件类型*//*
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private java.lang.String zjlx;*/
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private java.lang.String zjhm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "cust_type")
	private java.lang.Integer khlx;

	/**产品信息*/
	@Excel(name = "产品信息", width = 15)
    @ApiModelProperty(value = "产品信息")
	private java.lang.String cpxx;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
	private java.lang.String hth;
	/**合同金额*/
	@Excel(name = "合同金额", width = 15)
    @ApiModelProperty(value = "合同金额")
	private java.math.BigDecimal htje;
	/**合同余额*/
	@Excel(name = "合同余额", width = 15)
    @ApiModelProperty(value = "合同余额")
	private java.math.BigDecimal htye;
	/**最早合同发放日期*/
	@Excel(name = "最早合同发放日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最早合同发放日期")
	private java.util.Date zzhtffrq;
	/**最早合同到期日期*/
	@Excel(name = "最早合同到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最早合同到期日期")
	private java.util.Date zzhtdqrq;
	/**业务类型（1：管户认领 2：管户移交 3：包收认领 4：包收移交）*/
	@Excel(name = "业务类型", width = 15)
    @ApiModelProperty(value = "业务类型")
	@Dict(dicCode = "business_type")
	private java.lang.Integer ywlx;
	/**原管户人*/
	@Excel(name = "原管户人", width = 15)
    @ApiModelProperty(value = "原管户人")
	@Dict(dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	private java.lang.String yghr;
	/**原管户比例*/
	@Excel(name = "原管户比例", width = 15)
    @ApiModelProperty(value = "原管户比例")
	private java.math.BigDecimal yghbl;
	/**管户人*/
	@Excel(name = "管户人", width = 15)
    @ApiModelProperty(value = "管户人")
	@Dict(dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	private java.lang.String ghr;
	/**管户比例*/
	@Excel(name = "管户比例", width = 15)
    @ApiModelProperty(value = "管户比例")
	private java.math.BigDecimal ghbl;
	/**流程业务ID*/
	@Excel(name = "流程业务ID", width = 15)
    @ApiModelProperty(value = "流程业务ID")
	private java.lang.String tableId;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "业务编号")
	private java.lang.String businessNumber;
	/**流程编号*/
	@Excel(name = "流程编号", width = 15)
    @ApiModelProperty(value = "流程编号")
	private java.lang.String processId;
	/**流程状态(0 未提交 1 处理中 2 成功 3 失败)*/
	@Excel(name = "流程状态", width = 15)
    @ApiModelProperty(value = "流程状态")
	private java.lang.String processStatus;
	/**流程说明*/
	@Excel(name = "流程说明", width = 15)
    @ApiModelProperty(value = "流程说明")
	private java.lang.String processInfo;
	/**移交日期*/
	@Excel(name = "移交日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "移交日期")
	private java.util.Date yjrq;
	/**申请说明*/
	@Excel(name = "申请说明", width = 15)
    @ApiModelProperty(value = "申请说明")
	private java.lang.String sqsm;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	private java.lang.String lrbz;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private java.lang.String createBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private java.util.Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private java.lang.String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private java.util.Date updateTime;

}

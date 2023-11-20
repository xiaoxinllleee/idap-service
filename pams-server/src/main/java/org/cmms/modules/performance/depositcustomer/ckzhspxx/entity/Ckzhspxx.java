package org.cmms.modules.performance.depositcustomer.ckzhspxx.entity;

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
 * @Description: 存款账户审批信息
 * @Author: jeecg-boot
 * @Date:   2023-03-31
 * @Version: V1.0
 */
@Data
@TableName("KHGXGL_CKZHSPXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGXGL_CKZHSPXX对象", description="存款账户审批信息")
public class Ckzhspxx {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private java.lang.String id;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;

	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private java.lang.String khbh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private java.lang.String khmc;
	/**证件类型(0 其他 1 身份证)*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private java.lang.String zjlx;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private java.lang.String zjhm;
	/**营销类型(1 主动营销 2 自然增长)*/
	@Excel(name = "营销类型", width = 15,dicCode = "yxlx")
    @ApiModelProperty(value = "营销类型")
	@Dict(dicCode = "yxlx")
	private java.lang.String yxlx;
	/**存款账号*/
	@Excel(name = "存款账号", width = 15)
    @ApiModelProperty(value = "存款账号")
	private java.lang.String ckzh;
	/**账户类型*/
	@Excel(name = "账户类型", width = 15,dicCode = "ckzhlx")
    @ApiModelProperty(value = "账户类型")
	@Dict(dicCode = "ckzhlx")
	private java.lang.String zhlx;
	/**开户日期*/
	@Excel(name = "开户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开户日期")
	private java.util.Date khrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private java.util.Date dqrq;
	/**原拓展人*/
	@Excel(name = "原拓展人", width = 15, dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
    @ApiModelProperty(value = "原拓展人")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private java.lang.String ytzr;
	/**原拓展比例*/
	@Excel(name = "原拓展比例", width = 15)
    @ApiModelProperty(value = "原拓展比例")
	private java.math.BigDecimal ytzbl;
	/**拓展人*/
	@Excel(name = "拓展人", width = 15, dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
    @ApiModelProperty(value = "拓展人")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private java.lang.String tzr;
	/**拓展比例*/
	@Excel(name = "拓展比例", width = 15)
    @ApiModelProperty(value = "拓展比例")
	private java.math.BigDecimal tzbl;
	/**业务类型(1 认领 2 移交)*/
	@Excel(name = "业务类型", width = 15)
    @ApiModelProperty(value = "业务类型")
	private java.lang.String ywlx;
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
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private java.util.Date updateTime;


	/**录入标识(0 导入 1 录入 2 修改)*/
	@Excel(name = "录入标识", width = 15)
	@ApiModelProperty(value = "录入标识")
	private String lrbz;
}

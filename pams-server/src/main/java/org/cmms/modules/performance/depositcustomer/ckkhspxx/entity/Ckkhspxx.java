package org.cmms.modules.performance.depositcustomer.ckkhspxx.entity;

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
 * @Description: 存款客户审批信息
 * @Author: jeecg-boot
 * @Date:   2023-03-30
 * @Version: V1.0
 */
@Data
@TableName("Khgxgl_ckkhspxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Khgxgl_ckkhspxx对象", description="存款客户审批信息")
public class Ckkhspxx {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String khbh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件类型(0 其他 1 身份证)*/
	@Excel(name = "证件类型(0 其他 1 身份证)", width = 15, dicCode = "jx_zjlx")
    @ApiModelProperty(value = "证件类型(0 其他 1 身份证)")
	@Dict(dicCode = "jx_zjlx")
	private String zjlx;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户类型(1 对公客户 2 个人客户 3 金融机构客户 4 其他企业客户 5 其他个人客户)*/
	@Excel(name = "客户类型", width = 15, dicCode = "jx_khlx")
    @ApiModelProperty(value = "客户类型(1 对公客户 2 个人客户 3 金融机构客户 4 其他企业客户 5 其他个人客户)")
	@Dict(dicCode = "jx_khlx")
	private String khlx;
	/**营销类型(1 主动营销 2 自然增长)*/
	@Excel(name = "营销类型", width = 15, dicCode = "yxlx")
    @ApiModelProperty(value = "营销类型(1 主动营销 2 自然增长)")
	@Dict(dicCode = "yxlx")
	private String yxlx;
	/**产品信息*/
	@Excel(name = "产品信息", width = 15)
    @ApiModelProperty(value = "产品信息")
	private String cpxx;
	/**最早开户日期*/
	@Excel(name = "最早开户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最早开户日期")
	private Date zzkhrq;
	/**业务类型(1 认领 2 移交)*/
	@Excel(name = "业务类型(1 认领 2 移交)", width = 15)
    @ApiModelProperty(value = "业务类型(1 认领 2 移交)")
	private String ywlx;
	/**原管户人*/
	@Excel(name = "原管户人", width = 15, dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
    @ApiModelProperty(value = "原管户人")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String yghr;
	/**原管户比例*/
	@Excel(name = "原管户比例", width = 15)
    @ApiModelProperty(value = "原管户比例")
	private java.math.BigDecimal yghbl;
	/**现管户人*/
	@Excel(name = "现管户人", width = 15, dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
    @ApiModelProperty(value = "现管户人")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String ghr;
	/**现管户比例*/
	@Excel(name = "现管户比例", width = 15)
    @ApiModelProperty(value = "现管户比例")
	private java.math.BigDecimal ghbl;
	/**流程业务ID*/
	@Excel(name = "流程业务ID", width = 15)
    @ApiModelProperty(value = "流程业务ID")
	private String tableId;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "业务编号")
	private String businessNumber;
	/**流程编号*/
	@Excel(name = "流程编号", width = 15)
    @ApiModelProperty(value = "流程编号")
	private String processId;
	/**流程状态(0 未提交 1 处理中 2 已结束)*/
	@Excel(name = "流程状态", width = 15)
    @ApiModelProperty(value = "流程状态(0 未提交 1 处理中 2 成功 3 失败)")
	private String processStatus;
	/**流程说明*/
	@Excel(name = "流程说明", width = 15)
	@ApiModelProperty(value = "流程说明")
	private String processInfo;
	/**移交日期*/
	@Excel(name = "移交日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "移交日期")
	private Date yjrq;
	/**申请说明*/
	@Excel(name = "申请说明", width = 15)
    @ApiModelProperty(value = "申请说明")
	private String sqsm;
	/**录入标识(0 导入 1 录入 2 修改)*/
	@Excel(name = "录入标识(0 导入 1 录入 2 修改)", width = 15)
    @ApiModelProperty(value = "录入标识(0 导入 1 录入 2 修改)")
	private String lrbz;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private Date updateTime;
}

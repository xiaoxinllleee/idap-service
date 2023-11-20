package org.cmms.modules.rwzx.rwmxsj.entity;

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
 * @Description: 自主任务明细
 * @Author: jeecg-boot
 * @Date:   2023-10-18
 * @Version: V1.0
 */
@Data
@TableName("v_task_rwmx_zzrw")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_task_rwmx_zzrw对象", description="自主任务明细")
public class TaskRwmxZzrw {
	/**rwid*/
	@Excel(name = "rwid", width = 15)
	@ApiModelProperty(value = "rwid")
	private java.lang.String rwid;


	/**对象类型*/
	@Excel(name = "对象类型", width = 15,dicCode = "dxlx")
	@ApiModelProperty(value = "对象类型")
	@Dict(dicCode = "dxlx")
	private String dxlx;

	/**任务名称*/
	@Excel(name = "任务名称", width = 15)
	@ApiModelProperty(value = "任务名称")
	private java.lang.String rwmc;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private java.lang.String sszh;
	/**所属网格*/
	@Excel(name = "所属网格", width = 15,dicCode ="wgbh",dictTable="V_YXDYGL_MAIN",dicText="WGMC_SHOW")
	@ApiModelProperty(value = "所属网格")
	@Dict(dicCode ="wgbh",dictTable="V_YXDYGL_MAIN",dicText="WGMC_SHOW")
	private java.lang.String sswg;
	/**业务所属支行*/
	@Excel(name = "业务所属支行", width = 15,dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	@ApiModelProperty(value = "业务所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private java.lang.String ywsszh;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
	@ApiModelProperty(value = "户号编码")
	private String hhbm;

	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private java.lang.String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private java.lang.String zjhm;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
	@ApiModelProperty(value = "联系方式")
	private java.lang.String lxfs;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15,dicCode = "khlx1")
	@ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "khlx1")
	private java.lang.String khlx;
	/**客户类型2*/
	@Excel(name = "客户类型2", width = 15,dicCode = "yx_khlx2")
	@ApiModelProperty(value = "客户类型2")
	@Dict(dicCode = "yx_khlx2")
	private java.lang.String khlx2;
	/**客户等级*/
	@Excel(name = "客户等级", width = 15,dicCode = "khdj_yx")
	@ApiModelProperty(value = "客户等级")
	@Dict(dicCode = "khdj_yx")
	private java.lang.String khdj;
	/**创建人*/
//	@Excel(name = "创建人", width = 15)
	@ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**修改人*/
//	@Excel(name = "修改人", width = 15)
	@ApiModelProperty(value = "修改人")
	private java.lang.String updateBy;
	/**创建时间*/
//	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
	/**更新时间*/
//	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "更新时间")
	private java.util.Date updateTime;

	@ApiModelProperty(value = "派发次数")
	private java.lang.Integer pfcs;


	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "不做处理回收日期")
	private java.util.Date bzclhsrq;


	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "失败回收日期")
	private java.util.Date sbhsrq;


	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "成功回收日期")
	private java.util.Date cghsrq;


	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "不做处理重发日期")
	private java.util.Date bzclcfrq;

	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "成功重发日期")
	private java.util.Date cgcfrq;


	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "失败重发日期")
	private java.util.Date sbcfrq;


	@ApiModelProperty(value = "对象id")
	private String dxid;





	/**dxid2*//*
	@Excel(name = "dxid2", width = 15)
	@ApiModelProperty(value = "dxid2")
	private java.lang.String dxid2;*/

	/**yxlx*/
	@Excel(name = "yxlx", width = 15)
	@ApiModelProperty(value = "yxlx")
	private java.lang.String yxlx;
}

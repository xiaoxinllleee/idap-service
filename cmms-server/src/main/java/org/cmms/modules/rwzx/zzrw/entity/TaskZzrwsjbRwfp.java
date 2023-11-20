package org.cmms.modules.rwzx.zzrw.entity;

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
 * @Description: 任务分配数据明细
 * @Author: jeecg-boot
 * @Date:   2023-08-08
 * @Version: V1.0
 */
@Data
@TableName("TASK_ZZRWSJB_RWFP")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TASK_ZZRWSJB_RWFP对象", description="任务分配数据明细")
public class TaskZzrwsjbRwfp {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**任务ID*/
	@Excel(name = "任务ID", width = 15)
    @ApiModelProperty(value = "任务ID")
	private String rwid;
	/**数据ID*/
	@Excel(name = "数据ID", width = 15)
    @ApiModelProperty(value = "数据ID")
	private String sjid;
	/**任务名称*/
	@Excel(name = "任务名称", width = 15)
    @ApiModelProperty(value = "任务名称")
	private String rwmc;

	/**营销类型*/
	@Excel(name = "营销类型", width = 15,dicCode = "marketing_type")
	@ApiModelProperty(value = "营销类型")
	@Dict(dicCode = "marketing_type")
	private String yxlx;

	/**对象ID*/
	@Excel(name = "对象ID", width = 15)
    @ApiModelProperty(value = "对象ID")
	private String dxid;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String sszh;

	/**所属营销单元*/
	@Excel(name = "所属营销单元", width = 15, dicCode="wgbh",dictTable="V_YXDYGL_MAIN",dicText="wgmc_show")
	@ApiModelProperty(value = "所属营销单元")
	@Dict(dicCode="wgbh", dictTable="V_YXDYGL_MAIN", dicText="wgmc_show")
	private String sswg;

	@Excel(name = "业务所属支行", width = 15, dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "业务所属支行")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String ywsszh;

	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
	@ApiModelProperty(value = "户号编码")
	private String hhbm;


	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private String lxfs;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15,dicCode = "khlx1")
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "khlx1")
	private String khlx;

	/**客户类型2*/
	@Excel(name = "客户类型2", width = 15,dicCode = "yx_khlx2")
    @ApiModelProperty(value = "客户类型2")
    @Dict(dicCode = "yx_khlx2")
	private String khlx2;

	/**客户等级*/
	@Excel(name = "客户等级", width = 15,dicCode = "khdj_yx")
    @ApiModelProperty(value = "客户等级")
	@Dict(dicCode = "khdj_yx")
	private String khdj;
	/**任务状态*/
	@Excel(name = "任务状态", width = 15)
    @ApiModelProperty(value = "任务状态")
	private String rwzt;
	/**任务开始日期*/
	@Excel(name = "任务开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "任务开始日期")
	private Date rwksrq;
	/**营销日期*/
	@Excel(name = "营销日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "营销日期")
	private Date yxrq;




}

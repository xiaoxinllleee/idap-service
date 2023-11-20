package org.cmms.modules.rwzx.zfyxmx.entity;

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
 * @Description: 走访营销明细
 * @Author: jeecg-boot
 * @Date:   2023-07-17
 * @Version: V1.0
 */
@Data
@TableName("TASK_ZFYXMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TASK_ZFYXMX对象", description="走访营销明细")
public class TaskZfyxmx {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private java.lang.String id;
	/**任务id*/
    @ApiModelProperty(value = "任务id")
	private java.lang.String rwid;
	/**任务id*/
	@Excel(name = "任务名称", width = 15)
	@ApiModelProperty(value = "任务名称")
	private java.lang.String rwmc;

	/**任务类型*/
	@Excel(name = "任务类型", width = 15)
	@ApiModelProperty(value = "任务类型")
	private java.lang.String rwlx;

	/**明细数据id*/
    @ApiModelProperty(value = "明细数据id")
	private java.lang.String mxsjid;

	/**营销类型*/
	@Excel(name = "营销类型", width = 15,dicCode = "zfyxlx")
	@ApiModelProperty(value = "营销类型")
	@Dict(dicCode = "zfyxlx")
	private java.lang.String yxlx;

	/**营销人走访人*/
	@Excel(name = "营销人走访人", width = 15)
	@ApiModelProperty(value = "营销人走访人")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private java.lang.String yxzfr;

	@Excel(name = "营销时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "营销时间")
	private java.util.Date createTime;

	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private java.lang.String khmc;
	/**备注*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private java.lang.String zjhm;

	/**营销方式*/
	@Excel(name = "营销方式", width = 15,dicCode = "zfyxfs")
    @ApiModelProperty(value = "营销方式")
	@Dict(dicCode = "zfyxfs")
	private java.lang.String yxfs;
	/**营销结果*/
	@Excel(name = "营销结果", width = 15,dicCode = "yxjg")
    @ApiModelProperty(value = "营销结果")
	@Dict(dicCode = "yxjg")
	private java.lang.String yxjg;
	/**营销产品*/
	@Excel(name = "营销产品", width = 15,dicCode = "fxd_yxcp")
    @ApiModelProperty(value = "营销产品")
	@Dict(dicCode = "fxd_yxcp")
	private java.lang.String yxcp;
	/**营销失败原因*/
	@Excel(name = "营销失败原因", width = 15)
    @ApiModelProperty(value = "营销失败原因")
	private java.lang.String yxsbyy;
	/**客户意向*/
	@Excel(name = "客户意向", width = 15,dicCode = "fxd_yxcp")
    @ApiModelProperty(value = "客户意向")
	@Dict(dicCode = "fxd_yxcp")
	private java.lang.String khyx;
	/**经度*/
    @ApiModelProperty(value = "经度")
	private java.lang.String jd;
	/**维度*/
    @ApiModelProperty(value = "维度")
	private java.lang.String wd;
	@Excel(name = "地址", width = 15)
	@ApiModelProperty(value = "地址")
	private java.lang.String dz;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private java.lang.String bz;



	/**创建人*/
    @ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**更新人*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新人")
	private java.util.Date updateTime;
	/**更新时间*/
    @ApiModelProperty(value = "更新时间")
	private java.lang.String updateBy;
	/**客户类型2*/
	@ApiModelProperty(value = "客户类型2")
	@Dict(dicCode = "yx_khlx2")
	private java.lang.String khlx2;

	/**是否有业务新增*/
	@ApiModelProperty(value = "是否有业务新增")
	@Dict(dicCode = "sfbz")
	private java.lang.String sfyywxz;
	/**是否有营销产品新增*/
	@ApiModelProperty(value = "是否有营销产品新增")
	@Dict(dicCode = "sfbz")
	private java.lang.String sfyyxcpxz;
	/**营销人所在支行是否和业务产生支行是否同一个*/
	@ApiModelProperty(value = "营销人所在支行是否和业务产生支行是否同一个")
	@Dict(dicCode = "sfbz")
	private java.lang.String ywzhsfyz;
}

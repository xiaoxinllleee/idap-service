package org.cmms.modules.yxdygl.pqzrrgl.entity;

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
 * @Description: 片区责任人管理
 * @Author: jeecg-boot
 * @Date:   2020-06-30
 * @Version: V1.0
 */
@Data
@TableName("YXDYGL_PQZRRGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="YXDYGL_PQZRRGL对象", description="片区责任人管理")
public class YxdyglPqzrrgl {

	/**主键id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键id")
	private String id;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode="ZZBZ", dictTable="V_HR_BAS_ORGANIZATION_CMMS", dicText="ZZJC")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="ZZBZ", dictTable="V_HR_BAS_ORGANIZATION_CMMS", dicText="ZZJC")
	private String sszh;
	/**区域代码*/
	@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
	private String qydm;
	/**客户经理*/
	@Excel(name = "客户经理", width = 15, dicCode="yggh", dictTable="HR_BAS_STAFF", dicText="ygxm")
	@ApiModelProperty(value = "客户经理")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String khjl;
	/**是否主客户经理*/
	@Excel(name = "是否主客户经理", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否主客户经理")
	@Dict(dicCode = "sfbz")
	private String sfzkhjl;
	/**一级营销单元编号*/
	@Excel(name = "一级营销单元编号", width = 15,dicCode="DYBH", dictTable="YXDYGL_YJYXDYGL", dicText="DYMC")
	@ApiModelProperty(value = "一级营销单元编号")
	@Dict(dicCode="DYBH", dictTable="YXDYGL_YJYXDYGL", dicText="DYMC")
	private String yjyxdybh;
	/**二级营销单元编号*/
	@Excel(name = "二级营销单元编号", width = 15,dicCode="DYBH", dictTable="YXDYGL_EJYXDYGL", dicText="DYMC")
	@ApiModelProperty(value = "二级营销单元编号")
	@Dict(dicCode="DYBH", dictTable="YXDYGL_EJYXDYGL", dicText="DYMC")
	private String ejyxdybh;
	/**三级营销单元编号*/
	@Excel(name = "三级营销单元编号", width = 15,dicCode="DYBH", dictTable="YXDYGL_SJYXDYGL", dicText="DYMC")
	@ApiModelProperty(value = "三级营销单元编号")
	@Dict(dicCode="DYBH", dictTable="YXDYGL_SJYXDYGL", dicText="DYMC")
	private String sjyxdybh;
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
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;

}

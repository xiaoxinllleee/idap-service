package org.cmms.modules.khlc.jczbgl.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
 * @Description: 基础指标树包含指标
 * @Author: jeecg-boot
 * @Date:   2021-01-26
 * @Version: V1.0
 */
@Data
@TableName("V_PMA_F_BASE_INDEX_TYPE")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_PMA_F_BASE_INDEX_TYPE对象", description="基础指标树包含指标")
public class VpmaFBaseIndexType {

	/**类型编号*/
	@TableId(type = IdType.INPUT)
	@ApiModelProperty(value = "类型编号")
	private java.lang.String  id;
	/**类型名称*/
	@Excel(name = "类型名称", width = 15)
	@ApiModelProperty(value = "类型名称")
	private java.lang.String typeName;
	/**上级类型编号*/
	@Excel(name = "上级类型编号", width = 15)
	@ApiModelProperty(value = "上级类型编号")
	private java.lang.String  parentId;
	/**叶节点标志*/
	@Excel(name = "叶节点标志", width = 15)
	@ApiModelProperty(value = "叶节点标志")
	private java.lang.String leafFlag;
	/**层级*/
	@Excel(name = "层级", width = 15)
	@ApiModelProperty(value = "层级")
	private java.lang.Integer level0;
	/**类型标识*/
	@Excel(name = "类型标识", width = 15)
	@ApiModelProperty(value = "类型标识")
	private java.lang.String dirType;
	/**业务条线编号*/
	@Excel(name = "业务条线编号", width = 15)
	@ApiModelProperty(value = "业务条线编号")
	private java.lang.String bussSysNo;
	/**所属机构*/
	@Excel(name = "所属机构", width = 15)
	@ApiModelProperty(value = "所属机构")
	private java.lang.String orgId;
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

	@TableField(exist=false)
	private List<VpmaFBaseIndexType> child;
	@TableField(exist=false)
	private Boolean isLeaf;
}

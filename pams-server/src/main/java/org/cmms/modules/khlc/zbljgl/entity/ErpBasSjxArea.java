package org.cmms.modules.khlc.zbljgl.entity;

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
 * @Description: 指标逻辑管理
 * @Author: jeecg-boot
 * @Date:   2023-02-22
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_SJX_AREA")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_SJX_AREA对象", description="指标逻辑管理")
public class ErpBasSjxArea {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "ID")
	private java.lang.String id;
	/**指标id*/
	@Excel(name = "指标id", width = 15, dicCode = "zbid", dictTable = "erp_bas_zbk", dicText = "zbmc")
	@ApiModelProperty(value = "指标id")
	@Dict(dicCode = "zbid", dictTable = "erp_bas_zbk", dicText = "zbmc")
	private java.lang.String zbid;
	/**指标维度*/
	@Excel(name = "指标维度", width = 15)
	@ApiModelProperty(value = "指标维度")
	@Dict(dicCode = "zbwd")
	private java.lang.String zbwd;
	/**指标类型(1 机构 2 部门 3 岗位 6 扣减项)*/
	@Excel(name = "指标类型", width = 15)
	@ApiModelProperty(value = "指标类型")
	@Dict(dicCode = "jx_zblx")
	private java.lang.Integer zblx;
	/**考核方式(1 平衡计分卡 2 贡献率 3 按量计酬)*/
	@Excel(name = "考核方式", width = 15)
	@ApiModelProperty(value = "考核方式")
	@Dict(dicCode = "khfs")
	private java.lang.Integer khfs;
	/**区域代码*/
	@Excel(name = "区域代码", width = 15)
	@ApiModelProperty(value = "区域代码")
	private java.lang.String qydm;
	/**明细计算sql*/
	@Excel(name = "明细计算sql", width = 15)
	@ApiModelProperty(value = "明细计算sql")
	private java.lang.String mxjssql;
	/**机构计算sql*/
	@Excel(name = "结果计算sql", width = 15)
	@ApiModelProperty(value = "结果计算sql")
	private java.lang.String jgjssql;
	/**得分计算SQL*/
	@Excel(name = "得分计算SQL", width = 15)
	@ApiModelProperty(value = "得分计算SQL")
	private java.lang.String dfjssql;
	/**工资计算SQL*/
	@Excel(name = "工资计算SQL", width = 15)
	@ApiModelProperty(value = "工资计算SQL")
	private java.lang.String gzjssql;
	/**明细计算说明*/
	@Excel(name = "明细计算说明", width = 15)
	@ApiModelProperty(value = "明细计算说明")
	private java.lang.String mxjssm;
	/**结果计算说明*/
	@Excel(name = "结果计算说明", width = 15)
	@ApiModelProperty(value = "结果计算说明")
	private java.lang.String jgjssm;
	/**得分计算说明*/
	@Excel(name = "得分计算说明", width = 15)
	@ApiModelProperty(value = "得分计算说明")
	private java.lang.String dfjssm;
	/**工资计算说明*/
	@Excel(name = "工资计算说明", width = 15)
	@ApiModelProperty(value = "工资计算说明")
	private java.lang.String gzsjsm;
	/**是否启用*/
	@Excel(name = "是否启用", width = 15)
	@ApiModelProperty(value = "是否启用")
	@Dict(dicCode = "sfqy")
	private java.lang.String sfqy;
	/**执行顺序*/
	@Excel(name = "执行顺序", width = 15)
	@ApiModelProperty(value = "执行顺序")
	private java.lang.Integer zxsx;
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
	@TableField(exist = false)
	private java.lang.String zbbm;
}

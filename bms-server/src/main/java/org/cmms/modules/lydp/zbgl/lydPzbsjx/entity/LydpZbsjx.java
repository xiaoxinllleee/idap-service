package org.cmms.modules.lydp.zbgl.lydPzbsjx.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 浏阳大屏指标数据项
 * @Author: jeecg-boot
 * @Date:   2023-02-15
 * @Version: V1.0
 */
@Data
@TableName("REP_INDEX_SJX_LYDP")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="REP_INDEX_SJX_LYDP对象", description="浏阳大屏指标数据项")
public class LydpZbsjx {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**指标id*/
	@Excel(name = "指标id", width = 15)
    @ApiModelProperty(value = "指标id")
	private String zbid;
	/**指标名称*/
	@Excel(name = "指标名称", width = 15)
    @ApiModelProperty(value = "指标名称")
	private String zbmc;
	/**指标维度*/
	@Excel(name = "指标维度", width = 15,dicCode = "zbwd")
    @ApiModelProperty(value = "指标维度")
	@Dict(dicCode = "zbwd")
	private String zbwd;
	/**指标类型:0普通指标,1多维指标*/
	@Excel(name = "指标类型", width = 15,dicCode = "zblx")
    @ApiModelProperty(value = "指标类型")
	@Dict(dicCode = "zblx")
	private String zblx;
	/**计算sql*/
	@Excel(name = "计算sql", width = 15)
    @ApiModelProperty(value = "计算sql")
	private String jssql;
	/**数据来源:0系统取数.1手工录入*/
	@Excel(name = "数据来源", width = 15,dicCode = "sjxsjly")
    @ApiModelProperty(value = "数据来源")
	@Dict(dicCode = "sjxsjly")
	private String sjly;
	/**执行顺序*/
	@Excel(name = "执行顺序", width = 15)
    @ApiModelProperty(value = "执行顺序")
	private String zxsx;
	/**执行批次*/
	@Excel(name = "执行批次", width = 15)
    @ApiModelProperty(value = "执行批次")
	private String zxpc;
	/**是否启用*/
	@Excel(name = "是否启用", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否启用")
	@Dict(dicCode = "sfbz")
	private String sfqy;
	/**关联表名*/
	@Excel(name = "关联表名", width = 15)
	@ApiModelProperty(value = "关联表名")
	@ExcelVerify(interHandler = true)
	private String glbm;
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

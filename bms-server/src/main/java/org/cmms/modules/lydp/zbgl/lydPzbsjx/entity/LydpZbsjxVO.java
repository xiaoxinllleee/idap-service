package org.cmms.modules.lydp.zbgl.lydPzbsjx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 浏阳大屏指标数据项
 * @Author: jeecg-boot
 * @Date:   2023-02-15
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="REP_INDEX_SJX_LYDP对象", description="浏阳大屏指标数据项")
public class LydpZbsjxVO {


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
	private String glbm;


}

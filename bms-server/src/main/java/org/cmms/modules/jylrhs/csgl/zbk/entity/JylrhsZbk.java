package org.cmms.modules.jylrhs.csgl.zbk.entity;

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
 * @Description: 经营利润核算（指标库）
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
@Data
@TableName("jylrhs_zbk")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="jylrhs_zbk对象", description="经营利润核算（指标库）")
public class JylrhsZbk {

	/**指标ID*/
	@Excel(name = "指标ID", width = 15)
    @ApiModelProperty(value = "指标ID")
	private String zbid;
	/**指标名称*/
	@Excel(name = "指标名称", width = 15)
    @ApiModelProperty(value = "指标名称")
	private String zbmc;
	/**指标大类*/
	@Excel(name = "指标大类", width = 15, dicCode = "zbdl")
    @ApiModelProperty(value = "指标大类")
	@Dict(dicCode = "zbdl")
	private String zbdl;
	/**指标小类*/
	@Excel(name = "指标小类", width = 15, dicCode = "zbxl")
    @ApiModelProperty(value = "指标小类")
	@Dict(dicCode = "zbxl")
	private String zbxl;
	/**函数*/
	//@Excel(name = "函数", width = 15)
    @ApiModelProperty(value = "函数")
	private String fun;
	/**说明*/
	@Excel(name = "说明", width = 15)
    @ApiModelProperty(value = "说明")
	private String remark;
	/**数据来源*/
	@Excel(name = "数据来源", width = 15, dicCode = "data_source")
	@ApiModelProperty(value = "数据来源")
	@Dict(dicCode = "data_source")
	private Integer sjly;
	/**开关*/
	@Excel(name = "开关", width = 15, dicCode = "sfqy")
	@ApiModelProperty(value = "开关")
	@Dict(dicCode = "sfqy")
	private Integer kg;
	/**操作类型*/
	@Excel(name = "操作类型", width = 15, dicCode = "lrbz")
	@ApiModelProperty(value = "操作类型")
	@Dict(dicCode = "lrbz")
	private String oprationType;
	/**操作员*/
	@Excel(name = "操作员", width = 15)
	@ApiModelProperty(value = "操作员")
	private String operator;
	/**操作时间*/
	@Excel(name = "录入/修改时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "操作时间")
	private Date oprationTime;
}

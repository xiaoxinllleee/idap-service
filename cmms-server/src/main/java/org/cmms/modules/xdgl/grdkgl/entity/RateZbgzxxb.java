package org.cmms.modules.xdgl.grdkgl.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 指标工资信息表
 * @Author: jeecg-boot
 * @Date:   2020-08-28
 * @Version: V1.0
 */
@Data
@TableName("Rate_zbgzxxb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Rate_zbgzxxb对象", description="指标工资信息表")
public class RateZbgzxxb {

	/**客户类型(1.个人/2.企业)*/
	@Excel(name = "客户类型(1.个人/2.企业)", width = 15)
    @ApiModelProperty(value = "客户类型(1.个人/2.企业)")
	private String khlx;
	/**指标ID*/
	@Excel(name = "指标ID", width = 15)
    @ApiModelProperty(value = "指标ID")
	private String zbid;
	/**指标规则ID*/
	@Excel(name = "指标规则ID", width = 15)
    @ApiModelProperty(value = "指标规则ID")
	private String zbgzid;
	/**指标规则名称*/
	@Excel(name = "指标规则名称", width = 15)
    @ApiModelProperty(value = "指标规则名称")
	private String zbgzmc;
	/**计分符合*/
	@Excel(name = "计分符合", width = 15)
    @ApiModelProperty(value = "计分符合")
	private String zbabs;
	/**指标规则分值*/
	@Excel(name = "指标规则分值", width = 15)
    @ApiModelProperty(value = "指标规则分值")
	private double zbgzfz;
	/**指标结果*/
	@Excel(name = "指标结果", width = 15)
    @ApiModelProperty(value = "指标结果")
	private String zbjg;
	/**是否只读*/
	@Excel(name = "是否只读", width = 15)
    @ApiModelProperty(value = "是否只读")
	private String readonly;
	/**区域代码*/
	@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
	private String qydm;
	/**排序序号*/
	@Excel(name = "排序序号", width = 15)
    @ApiModelProperty(value = "排序序号")
	private Integer pxxh;
}

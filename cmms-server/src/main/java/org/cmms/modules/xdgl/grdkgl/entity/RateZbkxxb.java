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
 * @Description: 指标库信息表
 * @Author: jeecg-boot
 * @Date:   2020-08-28
 * @Version: V1.0
 */
@Data
@TableName("Rate_zbkxxb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Rate_zbkxxb对象", description="指标库信息表")
public class RateZbkxxb {
	/**指标编码 (主键)*/
	@Excel(name = "指标编码 (主键)", width = 15)
	@ApiModelProperty(value = "指标编码 (主键)")
	private String zbid;

	/**指标名称*/
	@Excel(name = "指标名称", width = 15)
	@ApiModelProperty(value = "指标名称")
	private String zbmc;


	/**录入方式(1-单选/2-文本框/3-多选)*/
	@Excel(name = "录入方式(1-单选/2-文本框/3-多选)", width = 15)
	@ApiModelProperty(value = "录入方式(1-单选/2-文本框/3-多选)")
	private String llfs;

	/**区域代码*/
	@Excel(name = "区域代码", width = 15)
	@ApiModelProperty(value = "区域代码")
	private String qydm;

	/**排序序号*/
	@Excel(name = "排序序号", width = 15)
    @ApiModelProperty(value = "排序序号")
	private Integer pxxh;

	/**客户类型(1.个人/2.企业)*/
	@Excel(name = "客户类型(1.个人/2.企业)", width = 15)
    @ApiModelProperty(value = "客户类型(1.个人/2.企业)")
	private String khlx;



}

package org.cmms.modules.khgl.nh.entity;

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
 * @Description: 批量评议验收附件
 * @Author: jeecg-boot
 * @Date:   2022-04-28
 * @Version: V1.0
 */
@Data
@TableName("CAMS_PLPY_YSFJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_PLPY_YSFJ对象", description="批量评议验收附件")
public class CamsPlpyYsfj {
    
	/**资料编号*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "资料编号")
	private String id;
	/**资料路径*/
	@Excel(name = "资料路径", width = 15)
    @ApiModelProperty(value = "资料路径")
	private String zllj;
	/**访问路径*/
	@Excel(name = "访问路径", width = 15)
    @ApiModelProperty(value = "访问路径")
	private String fwlj;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
	private Date updateTime;
	/**验收id*/
	@Excel(name = "验收id", width = 15)
    @ApiModelProperty(value = "验收id")
	private String otherId;
	// 1合影 2签字
	private String zllx;
}

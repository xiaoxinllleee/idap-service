package org.cmms.modules.khlc.csgl.csml.entity;

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
 * @Description: 参数目录
 * @Author: jeecg-boot
 * @Date:   2021-03-23
 * @Version: V1.0
 */
@Data
@TableName("PMA_F_PARAM_MENU")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PMA_F_PARAM_MENU对象", description="参数目录")
public class PmaFParamMenu {
    
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private Date updateTime;
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**目录或类型名称*/
	@Excel(name = "目录或类型名称", width = 15)
    @ApiModelProperty(value = "目录或类型名称")
	private String dirName;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
	private String remark;
	/**目录或类型所属机构编号*/
	@Excel(name = "目录或类型所属机构编号", width = 15)
    @ApiModelProperty(value = "目录或类型所属机构编号")
	private String orgId;
	/**约束条件*/
	@Excel(name = "约束条件", width = 15)
    @ApiModelProperty(value = "约束条件")
	private String constr;
	/**数据删除标志（00-未删除，02已删除）*/
	@Excel(name = "数据删除标志（00-未删除，02已删除）", width = 15)
    @ApiModelProperty(value = "数据删除标志（00-未删除，02已删除）")
	private String statFlag;
	/**上级目录编号*/
	@Excel(name = "上级目录编号", width = 15)
    @ApiModelProperty(value = "上级目录编号")
	private String parentDirId;
	/**类型标识*/
	@Excel(name = "类型标识", width = 15)
    @ApiModelProperty(value = "类型标识")
	private String dirType;
	/**生效范围(0-本机构,1-辖内机构)*/
	@Excel(name = "生效范围(0-本机构,1-辖内机构)", width = 15)
    @ApiModelProperty(value = "生效范围(0-本机构,1-辖内机构)")
	private String area;
	/**业务条线*/
	@Excel(name = "业务条线", width = 15)
    @ApiModelProperty(value = "业务条线")
	private String bussSysNo;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
}

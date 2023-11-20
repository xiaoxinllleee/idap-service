package org.cmms.modules.khlc.khfagl.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 考核方案目录
 * @Author: jeecg-boot
 * @Date:   2021-01-29
 * @Version: V1.0
 */
@Data
@TableName("PMA_A_SCHEME_MENU")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PMA_A_SCHEME_MENU对象", description="考核方案目录")
public class PmaASchemeMenu {

	/**考核方案目录编号*/
	@Excel(name = "考核方案目录编号", width = 15)
	@ApiModelProperty(value = "考核方案目录编号")
	@TableId(type = IdType.ASSIGN_ID)
	private String menuId;

	/**考核方案目录名称*/
	@Excel(name = "考核方案目录名称", width = 15)
    @ApiModelProperty(value = "考核方案目录名称")
	private String menuName;
	/**父方案目录编号*/
	@Excel(name = "父方案目录编号", width = 15)
    @ApiModelProperty(value = "父方案目录编号")
	private String parentMenuId;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
	private String remark;

	/**生效日期*/
	@Excel(name = "生效日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "生效日期")
	private Date validDate;
	/**失效日期*/
	@Excel(name = "失效日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "失效日期")
	private Date invalidDate;

	/**附件路径*/
	@ApiModelProperty(value = "附件路径")
	private String fjlj;

	/**附件名称*/
	@ApiModelProperty(value = "附件名称")
	private String fjmc;

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


}

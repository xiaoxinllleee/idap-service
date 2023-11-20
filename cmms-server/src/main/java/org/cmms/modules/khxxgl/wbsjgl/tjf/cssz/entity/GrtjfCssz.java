package org.cmms.modules.khxxgl.wbsjgl.tjf.cssz.entity;

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
 * @Description: 个人碳积分参数设置
 * @Author: jeecg-boot
 * @Date:   2022-11-21
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_GRTJF_CSSZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_GRTJF_CSSZ对象", description="个人碳积分参数设置")
public class GrtjfCssz {
    
	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
	private String id;
	/**碳积分类型*/
	@Excel(name = "碳积分类型", width = 15,dicCode = "grtjf_lx")
	@ApiModelProperty(value = "碳积分类型")
	@Dict(dicCode = "grtjf_lx")
	private String tjflx;
	/**积分*/
	@Excel(name = "积分", width = 15)
    @ApiModelProperty(value = "积分")
	private String jf;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**创建人*/
//	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
//	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
}

package org.cmms.modules.system.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
 * @Description: 快速导航
 * @Author: jeecg-boot
 * @Date:   2022-01-12
 * @Version: V1.0
 */
@Data
@TableName("SYS_PERMISSION_NAV")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SYS_PERMISSION_NAV对象", description="快速导航")
public class SysPermissionNav {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**用户名*/
	@Excel(name = "用户名", width = 15)
    @ApiModelProperty(value = "用户名")
	private String username;
	/**权限id*/
	@Excel(name = "权限id", width = 15)
    @ApiModelProperty(value = "权限id")
	@Dict(dicCode = "id", dictTable = "SYS_PERMISSION", dicText = "name")
	private String permissionId;
	@Dict(dicCode = "id", dictTable = "SYS_PERMISSION", dicText = "url")
	private String permissionIdPath;
	private String sfly;
	private String subSystemId;
	/**排序*/
	@Excel(name = "排序", width = 15)
    @ApiModelProperty(value = "排序")
	private Integer sort;

	@TableField(exist = false)
	List<String> permissionIds;
}

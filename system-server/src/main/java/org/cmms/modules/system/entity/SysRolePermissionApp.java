package org.cmms.modules.system.entity;

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
 * @Description: app权限
 * @Author: jeecg-boot
 * @Date:   2022-06-20
 * @Version: V1.0
 */
@Data
@TableName("SYS_ROLE_PERMISSION_APP")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SYS_ROLE_PERMISSION_APP对象", description="app权限")
public class SysRolePermissionApp {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**roleId*/
	@Excel(name = "roleId", width = 15)
    @ApiModelProperty(value = "roleId")
	private String roleId;
	/**permissionId*/
	@Excel(name = "permissionId", width = 15)
    @ApiModelProperty(value = "permissionId")
	private String permissionId;
	/**dataRuleIds*/
	@Excel(name = "dataRuleIds", width = 15)
    @ApiModelProperty(value = "dataRuleIds")
	private String dataRuleIds;
}

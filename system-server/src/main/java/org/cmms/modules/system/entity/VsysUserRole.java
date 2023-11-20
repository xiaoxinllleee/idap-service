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
 * @Description: CCC
 * @Author: jeecg-boot
 * @Date:   2020-07-02
 * @Version: V1.0
 */
@Data
@TableName("V_sys_user_role")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_sys_user_role对象", description="CCC")
public class VsysUserRole {
    
	/**roleName*/
	@Excel(name = "roleName", width = 15)
    @ApiModelProperty(value = "roleName")
	private String roleName;
	/**roleCode*/
	@Excel(name = "roleCode", width = 15)
    @ApiModelProperty(value = "roleCode")
	private String roleCode;
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**userId*/
	@Excel(name = "userId", width = 15)
    @ApiModelProperty(value = "userId")
	private String userId;
	/**roleId*/
	@Excel(name = "roleId", width = 15)
    @ApiModelProperty(value = "roleId")
	private String roleId;
}

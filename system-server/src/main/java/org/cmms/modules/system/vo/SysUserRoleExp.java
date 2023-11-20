package org.cmms.modules.system.vo;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @Author scott
 * @since 2018-12-19
 */
@Data
public class SysUserRoleExp {

    @Excel(name = "账号", width = 15)
    @ExcelVerify(notNull = true,interHandler = true)
    private String username;
    /**
     * 角色名称
     */
    @Excel(name="角色名",width=15)
    @ExcelVerify(notNull = true,interHandler = true)
    private String roleName;
    



}

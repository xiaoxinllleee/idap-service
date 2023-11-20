package org.cmms.modules.system.model;

import lombok.Data;
import org.cmms.modules.system.entity.SysDepart;
import org.cmms.modules.system.entity.SysUser;

/**
 * 包含 SysUser 和 SysDepart 的 Model
 *
 * @author sunjianlei
 */
@Data
public class SysUserSysDepartModel {

    private SysUser sysUser;
    private SysDepart sysDepart;

}

package org.cmms.modules.system.mapper;

import org.cmms.modules.system.entity.SysRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色权限表 Mapper 接口
 * </p>
 *
 * @Author scott
 * @since 2018-12-21
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {
    public List<SysRolePermission> getRolePermission(String roleId, String subSystemId);
}

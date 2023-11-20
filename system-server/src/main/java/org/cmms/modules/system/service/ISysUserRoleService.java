package org.cmms.modules.system.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.system.entity.SysRole;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.entity.SysUserRole;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.system.entity.VsysUserRole;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @Author scott
 * @since 2018-12-21
 */
public interface ISysUserRoleService extends IService<SysUserRole> {
	
	/**
	 * 查询所有的用户角色信息
	 * @return
	 */
	Map<String,String> queryUserRole();

	List<SysUserRole> findByRoleId(String roleid);

	List<VsysUserRole> findByUserId(String userid);

	List<VsysUserRole> findByUserIdAndRoleCode(String userid,String rolecode);

	List<SysRole> getUserRoleCode(String roleid);

	List<String> getRoleByUserName(String username);

	List<String> getRoleIdByUserName(String username);

}

package org.cmms.modules.system.service.impl;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.system.entity.SysRole;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.entity.SysUserRole;
import org.cmms.modules.system.entity.VsysUserRole;
import org.cmms.modules.system.mapper.SysUserRoleMapper;
import org.cmms.modules.system.service.ISysRoleService;
import org.cmms.modules.system.service.ISysUserRoleService;
import org.cmms.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @Author scott
 * @since 2018-12-21
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

	@Autowired
	private ISysUserService userService;
	@Autowired
	private ISysRoleService roleService;

	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	/**
	 * 查询所有用户对应的角色信息
	 */
	@Override
	public Map<String,String> queryUserRole() {
		List<SysUserRole> uRoleList = this.list();
		List<SysUser> userList = userService.list();
		List<SysRole> roleList = roleService.list();
		Map<String,String> map = new IdentityHashMap<>();
		String userId = "";
		String roleId = "";
		String roleName = "";
		if(uRoleList != null && uRoleList.size() > 0) {
			for(SysUserRole uRole : uRoleList) {
				roleId = uRole.getRoleId();
				for(SysUser user : userList) {
					userId = user.getId();
					if(uRole.getUserId().equals(userId)) {
						roleName = this.searchByRoleId(roleList,roleId);
						map.put(userId, roleName);
					}
				}
			}
			return map;
		}
		return map;
	}
	
	/**
	 * queryUserRole调用的方法
	 * @param roleList
	 * @param roleId
	 * @return
	 */
	private String searchByRoleId(List<SysRole> roleList, String roleId) {
		while(true) {
			for(SysRole role : roleList) {
				if(roleId.equals(role.getId())) {
					return role.getRoleName();
				}
			}
		}
	}

	public List<SysUserRole> findByRoleId(String roleid){
		return  sysUserRoleMapper.findByRoleId(roleid);

	}


	public List<VsysUserRole> findByUserId(String userid){
		return  sysUserRoleMapper.findByUserId(userid);

	}
	public List<VsysUserRole> findByUserIdAndRoleCode(String userid,String rolecode){
		return  sysUserRoleMapper.findByUserIdAndRoleCode(userid,rolecode);

	}

	public List<SysRole> getUserRoleCode(String userid){
		return  sysUserRoleMapper.getUserRoleCode(userid);

	}

	public List<String> getRoleByUserName(String username) {
		return sysUserRoleMapper.getRoleByUserName(username);
	}

	public List<String> getRoleIdByUserName(String username) {
		return sysUserRoleMapper.getRoleIdByUserName(username);
	}
}

package org.cmms.modules.system.service;


import java.util.List;

import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.entity.SysUserDepart;
import org.cmms.modules.system.model.DepartIdHrModel;
import org.cmms.modules.system.model.DepartIdModel;


import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * SysUserDpeart用户组织机构service
 * </p>
 * @Author ZhiLin
 *
 */
public interface ISysUserDepartService extends IService<SysUserDepart> {
	

	/**
	 * 根据指定用户id查询部门信息
	 * @param userId
	 * @return
	 */
	List<DepartIdModel> queryDepartIdsOfUser(String userId);

	List<DepartIdHrModel> queryHrDepartIdsOfUser(String userId);

	/**
	 * 根据部门id查询用户信息
	 * @param depId
	 * @return
	 */
	List<SysUser> queryUserByDepId(String depId);
  	/**
	 * 根据部门code，查询当前部门和下级部门的用户信息
	 */
	public List<SysUser> queryUserByDepCode(String depCode);
}

package org.cmms.modules.system.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.common.exception.JeecgBootException;
import org.cmms.modules.system.entity.SysPermission;
import org.cmms.modules.system.entity.SysSubSystem;
import org.cmms.modules.system.model.TreeModel;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @Author scott
 * @since 2018-12-21
 */
public interface ISysPermissionService extends IService<SysPermission> {
	
	public List<TreeModel> queryListByParentId(String parentId);
	
	/**真实删除*/
	public void deletePermission(String id) throws JeecgBootException;
	/**逻辑删除*/
	public void deletePermissionLogical(String id) throws JeecgBootException;
	
	public void addPermission(SysPermission sysPermission) throws JeecgBootException;
	
	public void editPermission(SysPermission sysPermission) throws JeecgBootException;
	
	public List<SysPermission> queryByUser(String username, String subSystemId);

	public List<SysPermission> queryAllPermissionByUser(String username, String subSystemList);

	public List<SysSubSystem> querySubSystemByUser(String username);
	
	/**
	 * 根据permissionId删除其关联的SysPermissionDataRule表中的数据
	 * 
	 * @param id
	 * @return
	 */
	public void deletePermRuleByPermId(String id);
	
	/**
	  * 查询出带有特殊符号的菜单地址的集合
	 * @return
	 */
	public List<String> queryPermissionUrlWithStar();

	/**
	 * 查询出设置的首页
	 * */
	public List<SysPermission> queryHomePage();
	public List<SysPermission> queryHomePageByUserName(String username);

	/**
	 * 返回满足条件的菜单  是子菜单 且 叶子节点
	 * */
	public List<String> queryNavList(List<String> list);

	public List<SysPermission> queryPadRouters(String username);
}

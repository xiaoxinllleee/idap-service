package org.cmms.modules.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.cmms.modules.system.entity.SysPermission;
import org.cmms.modules.system.entity.SysSubSystem;
import org.cmms.modules.system.model.TreeModel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @Author scott
 * @since 2018-12-21
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
	/**
	   * 通过父菜单ID查询子菜单
	 * @param parentId
	 * @return
	 */
	public List<TreeModel> queryListByParentId(@Param("parentId") String parentId);
	
	/**
	  *   根据用户查询用户权限
	 */
	public List<SysPermission> queryByUser(@Param("username") String username, @Param("subSystemId") String subSystemId);

	/**
	 *   根据用户查询用户所有系统的权限
	 */
	public List<SysPermission> queryAllPermissionByUser(@Param("username") String username, @Param("subSystemList") String subSystemList);

	/**
	 *   根据用户查询用户系统权限
	 */
	public List<SysSubSystem> querySubSystemByUser(@Param("username") String username);
	
	/**
	 *   修改菜单状态字段： 是否子节点
	 */
	@Update("update sys_permission set is_leaf=#{leaf} where id = #{id}")
	public int setMenuLeaf(@Param("id") String id,@Param("leaf") int leaf);
	
	/**
	  *   获取模糊匹配规则的数据权限URL
	 */
	@Select("SELECT url FROM sys_permission WHERE del_flag = 0 and menu_type = 2 and url like '%*%'")
    public List<String> queryPermissionUrlWithStar();

	public List<SysPermission> queryHomePage();

	public List<String> queryNavList();

	List<SysPermission> queryHomePageByUserName(String username);

	public List<SysPermission> queryPadRouters(String username);
}

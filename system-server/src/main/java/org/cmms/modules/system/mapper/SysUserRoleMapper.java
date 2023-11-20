package org.cmms.modules.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.cmms.modules.system.entity.SysRole;
import org.cmms.modules.system.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.system.entity.VsysUserRole;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @Author scott
 * @since 2018-12-21
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

	@Select("select role_code from sys_role where id in (select role_id from sys_user_role where user_id = (select id from sys_user where username=#{username}))")
	List<String> getRoleByUserName(@Param("username") String username);

	@Select("select id from sys_role where id in (select role_id from sys_user_role where user_id = (select id from sys_user where username=#{username}))")
	List<String> getRoleIdByUserName(@Param("username") String username);


	@Select("select * from sys_user_role where role_id =#{roleid}")
	List<SysUserRole> findByRoleId(@Param("roleid") String roleid);

	@Select("select * from v_sys_user_role where user_id =#{userid}")
	List<VsysUserRole> findByUserId(@Param("userid") String userid);

	@Select("select * from v_sys_user_role where user_id =#{userid} and role_code=#{rolecode}")
	List<VsysUserRole> findByUserIdAndRoleCode(@Param("userid") String userid,@Param("rolecode") String rolecode);

	@Select("select  * From sys_role  where id in (select role_id From sys_user_role where user_id=#{userid})")
	List<SysRole> getUserRoleCode(@Param("userid") String userid);

}

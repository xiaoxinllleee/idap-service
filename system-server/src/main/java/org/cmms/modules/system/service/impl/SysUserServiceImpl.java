package org.cmms.modules.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CacheConstant;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.vo.SearchVo;
import org.cmms.modules.system.entity.*;
import org.cmms.modules.system.mapper.*;
import org.cmms.modules.system.model.SysUserSysDepartModel;
import org.cmms.modules.system.service.ISysBasUserService;
import org.cmms.modules.system.service.ISysLogService;
import org.cmms.modules.system.service.ISysUserService;
import org.cmms.modules.system.vo.SysUserDepVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @Author: scott
 * @Date: 2018-12-20
 */
@Service
@Slf4j

public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private SysPermissionMapper sysPermissionMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysUserDepartMapper sysUserDepartMapper;
	@Autowired
	private ISysLogService sysLogService;
	@Autowired
	private ISysBasUserService sysBasUserService;
	@Value(value = "${common.userLogin.errNumMax}")
	private Integer errNumMax;
	@Value(value = "${common.userLogin.errLimitTime}")
	private Integer errLimitTime;

	@Value(value = "${common.userLogin.resetTime:9999}")
	private Integer resetTime;

	@Value(value = "${common.userLogin.validateRule:^\\S{1,}$}")
	private String validateRule;


	@Value(value = "${common.userLogin.validatePasswordMessage:输入密码不对,需要重新输入}")
	private String validatePasswordMessage;



    @Override
    @CacheEvict(value = {CacheConstant.SYS_USERS_CACHE}, allEntries = true)
    public Result<?> resetPassword(String username, String oldpassword, String newpassword, String confirmpassword) {
        SysUser user = userMapper.getUserByName(username);
        //密码改为前端MD5加密
//        String passwordEncode = PasswordUtil.encrypt(username, oldpassword, user.getSalt());
        if (!user.getPassword().toUpperCase().equals(oldpassword)) {
            return Result.error("旧密码输入错误!");
        }
        if (oConvertUtils.isEmpty(newpassword)) {
            return Result.error("新密码不允许为空!");
        }
        if (!newpassword.equals(confirmpassword)) {
            return Result.error("两次输入密码不一致!");
        }
//        String password = PasswordUtil.encrypt(username, newpassword, user.getSalt());\
		this.sysBasUserService.updatePassword(user.getId(),newpassword);
		//this.userMapper.update(new SysUser().setPassword(newpassword), new LambdaQueryWrapper<SysUser>().eq(SysUser::getId, user.getId()));
        return Result.ok("密码重置成功!");
    }

    @Override
    @CacheEvict(value = {CacheConstant.SYS_USERS_CACHE}, allEntries = true)
    public Result<?> changePassword(SysUser sysUser) {
        /*String salt = oConvertUtils.randomGen(8);
        sysUser.setSalt(salt);
        String password = sysUser.getPassword();
        String passwordEncode = PasswordUtil.encrypt(sysUser.getUsername(), password, salt);
        sysUser.setPassword(passwordEncode);
        this.userMapper.updateById(sysUser);*/
		//this.userMapper.update(new SysUser().setPassword(sysUser.getPassword()), new LambdaQueryWrapper<SysUser>().eq(SysUser::getId, sysUser.getId()));
		this.sysBasUserService.updatePassword(sysUser.getId(), sysUser.getPassword());
		return Result.ok("密码修改成功!");
    }

    @Override
    @CacheEvict(value={CacheConstant.SYS_USERS_CACHE}, allEntries=true)
	@Transactional(rollbackFor = Exception.class)
	public boolean deleteUser(String userId) {
		//1.删除用户
		//this.removeById(userId);
		baseMapper.deleteUserById(userId);
		//2.删除用户部门关联关系
		LambdaQueryWrapper<SysUserDepart> query = new LambdaQueryWrapper<SysUserDepart>();
		query.eq(SysUserDepart::getUserId, userId);
		sysUserDepartMapper.delete(query);
		//3.删除用户角色关联关系
		//TODO
		return false;
	}

	@Override
    @CacheEvict(value={CacheConstant.SYS_USERS_CACHE}, allEntries=true)
	@Transactional(rollbackFor = Exception.class)
	public boolean deleteBatchUsers(String userIds) {
		//1.删除用户
		//this.removeByIds(Arrays.asList(userIds.split(",")));
		baseMapper.deleteUserByIds(Arrays.asList(userIds.split(",")));
		//2.删除用户部门关系
		LambdaQueryWrapper<SysUserDepart> query = new LambdaQueryWrapper<SysUserDepart>();
		for(String id : userIds.split(",")) {
			query.eq(SysUserDepart::getUserId, id);
			this.sysUserDepartMapper.delete(query);
		}
		//3.删除用户角色关系
		//TODO
		return false;
	}

	@Override
	public SysUser getUserByName(String username) {
		return userMapper.getUserByName(username);
	}


	@Override
	@Transactional
	public void addUserWithRole(SysUser user, String roles) {
//		this.save(user);
		//保存到sys_bas_user表
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		SysBasUser sysBasUser = new SysBasUser();
		BeanUtils.copyProperties(user, sysBasUser);
		sysBasUser.setUserid(user.getId());
		sysBasUser.setJgdm(user.getOrgCode());
		sysBasUser.setTellid(user.getGyh());
		sysBasUser.setStatus("1");
		sysBasUser.setDelFlag(0);
		sysBasUser.setCreateuser(loginUser.getUsername());
		sysBasUser.setCreatetime(new Date());
		sysBasUser.setUpdatepasswordtime(new Date());
		sysBasUserService.save(sysBasUser);
		if(oConvertUtils.isNotEmpty(roles)) {
			String[] arr = roles.split(",");
			for (String roleId : arr) {
				SysUserRole userRole = new SysUserRole(user.getId(), roleId);
				sysUserRoleMapper.insert(userRole);
			}
		}
	}

	@Override
	@CacheEvict(value= {CacheConstant.SYS_USERS_CACHE}, allEntries=true)
	@Transactional
	public void editUserWithRole(SysUser user, String roles) {
//		this.updateById(user);
		//先删后加
		sysUserRoleMapper.delete(new QueryWrapper<SysUserRole>().lambda().eq(SysUserRole::getUserId, user.getId()));
		if(oConvertUtils.isNotEmpty(roles)) {
			String[] arr = roles.split(",");
			for (String roleId : arr) {
				SysUserRole userRole = new SysUserRole(user.getId(), roleId);
				sysUserRoleMapper.insert(userRole);
			}
		}
	}


	@Override
	public List<String> getRole(String username) {
		return sysUserRoleMapper.getRoleByUserName(username);
	}

	/**
	 * 通过用户名获取用户角色集合
	 * @param username 用户名
     * @return 角色集合
	 */
	@Override
	public Set<String> getUserRolesSet(String username) {
		// 查询用户拥有的角色集合
		List<String> roles = sysUserRoleMapper.getRoleByUserName(username);
		log.info("-------通过数据库读取用户拥有的角色Rules------username： " + username + ",Roles size: " + (roles == null ? 0 : roles.size()));
		return new HashSet<>(roles);
	}

	/**
	 * 通过用户名获取用户权限集合
	 *
	 * @param username 用户名
	 * @return 权限集合
	 */
	@Override
	public Set<String> getUserPermissionsSet(String username) {
		Set<String> permissionSet = new HashSet<>();
		List<SysPermission> permissionList = sysPermissionMapper.queryByUser(username, null);
		for (SysPermission po : permissionList) {
//			// TODO URL规则有问题？
//			if (oConvertUtils.isNotEmpty(po.getUrl())) {
//				permissionSet.add(po.getUrl());
//			}
			if (oConvertUtils.isNotEmpty(po.getPerms())) {
				permissionSet.add(po.getPerms());
			}
		}
		log.info("-------通过数据库读取用户拥有的权限Perms------username： "+ username+",Perms size: "+ (permissionSet==null?0:permissionSet.size()) );
		return permissionSet;
	}

	// 根据部门Id查询
	@Override
	public IPage<SysUser> getUserByDepId(Page<SysUser> page, String departId,String username) {
		return userMapper.getUserByDepId(page, departId,username);
	}

	@Override
	public IPage<SysUser> getUserByDepIds(Page<SysUser> page, List<String> departIds, String username) {
		return userMapper.getUserByDepIds(page, departIds,username);
	}

	@Override
	public Map<String, String> getDepNamesByUserIds(List<String> userIds) {
		List<SysUserDepVo> list = this.baseMapper.getDepNamesByUserIds(userIds);

		Map<String, String> res = new HashMap<String, String>();
		list.forEach(item -> {
					if (res.get(item.getUserId()) == null) {
						res.put(item.getUserId(), item.getDepartName());
					} else {
						res.put(item.getUserId(), res.get(item.getUserId()) + "," + item.getDepartName());
					}
				}
		);
		return res;
	}

	@Override
	public IPage<SysUser> getUserByDepartIdAndQueryWrapper(Page<SysUser> page, String departId, QueryWrapper<SysUser> queryWrapper) {
		LambdaQueryWrapper<SysUser> lambdaQueryWrapper = queryWrapper.lambda();

		lambdaQueryWrapper.eq(SysUser::getDelFlag, "0");
        lambdaQueryWrapper.inSql(SysUser::getId, "SELECT user_id FROM sys_user_depart WHERE dep_id = '" + departId + "'");

        return userMapper.selectPage(page, lambdaQueryWrapper);
	}

	@Override
	public IPage<SysUserSysDepartModel> queryUserByOrgCode(String orgCode, SysUser userParams, IPage page) {
		List<SysUserSysDepartModel> list = baseMapper.getUserByOrgCode(page, orgCode, userParams);
		Integer total = baseMapper.getUserByOrgCodeTotal(orgCode, userParams);

		IPage<SysUserSysDepartModel> result = new Page<>(page.getCurrent(), page.getSize(), total);
		result.setRecords(list);

		return result;
	}

	// 根据角色Id查询
	@Override
	public IPage<SysUser> getUserByRoleId(Page<SysUser> page, String roleId, String username) {
		return userMapper.getUserByRoleId(page,roleId,username);
	}


	@Override
	@CacheEvict(value= {CacheConstant.SYS_USERS_CACHE}, key="#username")
	public void updateUserDepart(String username,String orgCode) {
		baseMapper.updateUserDepart(username, orgCode);
	}


	@Override
	public SysUser getUserByPhone(String phone) {
		return userMapper.getUserByPhone(phone);
	}


	@Override
	public SysUser getUserByEmail(String email) {
		return userMapper.getUserByEmail(email);
	}

	@Override
	@Transactional
	public void addUserWithDepart(SysUser user, String selectedParts) {
//		this.save(user);  //保存角色的时候已经添加过一次了
		if(oConvertUtils.isNotEmpty(selectedParts)) {
			String[] arr = selectedParts.split(",");
			for (String deaprtId : arr) {
				SysUserDepart userDeaprt = new SysUserDepart(user.getId(), deaprtId);
				sysUserDepartMapper.insert(userDeaprt);
			}
		}
	}


	@Override
	@Transactional
	@CacheEvict(value={CacheConstant.SYS_USERS_CACHE}, allEntries=true)
	public void editUserWithDepart(SysUser user, String departs) {
		//this.updateById(user);  //更新角色的时候已经更新了一次了，可以再跟新一次
		//先删后加
		sysUserDepartMapper.delete(new QueryWrapper<SysUserDepart>().lambda().eq(SysUserDepart::getUserId, user.getId()));
		if(oConvertUtils.isNotEmpty(departs)) {
			String[] arr = departs.split(",");
			for (String departId : arr) {
				SysUserDepart userDepart = new SysUserDepart(user.getId(), departId);
				sysUserDepartMapper.insert(userDepart);
			}
		}
	}


	/**
	   * 校验用户是否有效
	 * @param sysUser
	 * @return
	 */
	@Override
	public Result<?> checkUserIsEffective(SysUser sysUser) {
		Result<?> result = new Result<Object>();
		//情况1：根据用户信息查询，该用户不存在
		if (sysUser == null) {
//			result.error500("该用户不存在，请联系系统管理员确认！");
			result.error500("用户名或密码不正确！");
			sysLogService.addLog("用户登录失败，用户不存在！", CommonConstant.LOG_TYPE_1, null);
			return result;
		}
		//情况2：根据用户信息查询，该用户已注销
		if (CommonConstant.DEL_FLAG_1.toString().equals(sysUser.getDelFlag())) {
			sysLogService.addLog("用户登录失败，用户名:" + sysUser.getUsername() + "已注销！", CommonConstant.LOG_TYPE_1, null);
//			result.error500("该用户已注销");
			result.error500("用户名或密码不正确！");
			return result;
		}
		//情况3：根据用户信息查询，该用户已冻结
		if (CommonConstant.USER_FREEZE.equals(sysUser.getStatus())) {
			sysLogService.addLog("用户登录失败，用户名:" + sysUser.getUsername() + "已冻结！", CommonConstant.LOG_TYPE_1, null);
			result.error500("该用户已冻结");
			return result;
		}
		return result;
	}

	/**
	 * 校验用户登录密码
	 * @param sysUser
	 * @param password
	 * @return
	 */
	@Override
	public Result checkUserPassword(SysUser sysUser, String password) {
		Result<?> result = new Result<Object>();
		//前端已经进行MD5加密，直接与数据库中的密码进行比对即可
		String syspassword = sysUser.getPassword().toUpperCase();
//		int errNumMaxInt = Integer.parseInt(errNumMax); //密码最大错误次数
//		int errLimitTimeInt = Integer.parseInt(errLimitTime); //用户登录达到密码错误最大次数之后，限制登录的时间（单位：分钟）

		String pwdFreeTm = StringUtils.nvl(sysUser.getPwdFreeTm(), ""); //密码释放时间
		int pwdErrNum = sysUser.getPwdErrNum() == null ? 0 : sysUser.getPwdErrNum(); //密码错误次数
		String pwdErrTm = StringUtils.nvl(sysUser.getPwdErrTm(), ""); //密码首次错误时间

		Date updatepasswordtime=sysUser.getUpdatepasswordtime();
		Date createTime=sysUser.getCreateTime();
		Date nowDate = DateUtil.parseDateFormat(DateUtil.formatDateTime("yyyy-MM-dd HH:mm"),"yyyy-MM-dd HH:mm");
		if (!StringUtils.isEmpty(pwdFreeTm)) {
			Date pwdFreeDate = DateUtil.parseDateFormat(pwdFreeTm, "yyyy-MM-dd HH:mm");
			//还未到密码释放时间
			if (pwdFreeDate.compareTo(nowDate) > 0) {
				int diff = (int)DateUtil.getDiffMinutes(pwdFreeDate, nowDate);
				result.error500("由于密码错误次数过多，此用户已暂时被锁定，请在" + diff + "分钟之后重试！");
//				result.error500("用户名或密码不正确！");
				return result;
			} else {
				//重新开始计算次数
				pwdErrNum = 0;
				pwdErrTm = "";
				pwdFreeTm = "";
			}
		}
		Long yuefencha=0l;
		if (!syspassword.equals(password)) {
			//记录密码失败次数，如果超过次数，则限制此用户进行登录
			//如果首次错误时间与当前错误时间间隔超过最大限制时间，重新计算
			if(!StringUtils.isEmpty(pwdErrTm)) {
				Date pwdErrDate = DateUtil.parseDateFormat(pwdErrTm, "yyyy-MM-dd HH:mm");
				int interval = (int)DateUtil.getDiffMinutes(nowDate, pwdErrDate);
				if (interval > errLimitTime) {
					pwdErrNum = 0;
				}
			}
			pwdErrNum++;
			if(pwdErrNum == 1) {
//				result.error500("用户名或密码错误。再输错" + (errNumMax - pwdErrNum) + "次此账号将被锁定！");
				result.error500("用户名或密码不正确！");
				//更新首次错误时间与错误次数
				pwdErrTm = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm");
			} else if (pwdErrNum < errNumMax) {
//				result.error500("用户名或密码错误。再输错" + (errNumMax - pwdErrNum) + "次此账号将被锁定！");
				result.error500("用户名或密码不正确！");
			} else {
				//超过限定次数，更新密码释放时间
				result.error500("由于密码错误次数过多，此用户已暂时被锁定，请在" + errLimitTime + "分钟之后重试！");
//				result.error500("用户名或密码不正确！");
				pwdFreeTm = DateUtil.format(DateUtil.addMinutes(new Date(), errLimitTime), "yyyy-MM-dd HH:mm");
			}
		} else {
			if(updatepasswordtime!=null)
			{
				 String updateTimeString=new SimpleDateFormat("yyyy-MM-dd").format(updatepasswordtime);
				 yuefencha=DateUtil.computerTime(updateTimeString,null);

			}


			//登录成功，清空密码失败次数与限制时间
			pwdErrNum = 0;
			pwdErrTm = "";
			pwdFreeTm = "";
		}
		//
		sysUser.setPwdErrNum(pwdErrNum);
		sysUser.setPwdErrTm(pwdErrTm);
		sysUser.setPwdFreeTm(pwdFreeTm);
		this.updateUserPasswordVerify(sysUser);
		if(yuefencha>resetTime)
		{

			result.setMessage("已超过六个月没修改密码,请修改密码");
		}
		return result;
	}

	@Override
	public void updateUserPasswordVerify(SysUser sysUser) {
		this.userMapper.updateUserPasswordVerify(sysUser);
	}

	@Override
	public List<SysUser> queryLogicDeleted() {
		return this.queryLogicDeleted(null);
	}

	@Override
	public List<SysUser> queryLogicDeleted(LambdaQueryWrapper<SysUser> wrapper) {
		if (wrapper == null) {
			wrapper = new LambdaQueryWrapper<>();
		}
		wrapper.eq(SysUser::getDelFlag, "1");
		return userMapper.selectLogicDeleted(wrapper);
	}

	@Override
	public boolean revertLogicDeleted(List<String> userIds, SysUser updateEntity) {
		String ids = String.format("'%s'", String.join("','", userIds));
		return userMapper.revertLogicDeleted(ids, updateEntity) > 0;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean removeLogicDeleted(List<String> userIds) {
		String ids = String.format("'%s'", String.join("','", userIds));
		// 1. 删除用户
		int line = userMapper.deleteLogicDeleted(ids);
		// 2. 删除用户部门关系
		line += sysUserDepartMapper.delete(new LambdaQueryWrapper<SysUserDepart>().in(SysUserDepart::getUserId, userIds));
		//3. 删除用户角色关系
		line += sysUserRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>().in(SysUserRole::getUserId, userIds));
		return line != 0;
	}


	@Override
	public List<SysUser> findUserByRoleId(String roleid,String org) {
		return  userMapper.findUserByRoleId(roleid,org);

	}

	@Override
	public List<SysUser> findUserByPostAndOrg(Integer gwbz, String org) {
		return userMapper.findUserByPostAndOrg(gwbz, org);
	}

	@Override
	public org.springframework.data.domain.Page<SysUser> findByCondition(SysUser user, SearchVo searchVo, Pageable pageable) {
		return null;
	}

	@Override
	public Result<JSONObject> selectPassWordVaildateRule() {
		Result<JSONObject> result=new Result<>();
		JSONObject view=new JSONObject();
		view.put("validateRule",validateRule);
		view.put("validatePasswordMessage",validatePasswordMessage);
		result.setResult(view);
		return result;
	}

	@Override
	public List<SysUser> findUsersByRoleId(String roleid) {
		return userMapper.findUsersByRoleId(roleid);
	}

	@Override
	public SysUser findIdapUserByRoleIdAndSszh(String roleId, String sszh) {
		List<SysUser> idapUserByRoleIdAndSszh = baseMapper.findIdapUserByRoleIdAndSszh(roleId, sszh);
		if (CollUtil.isNotEmpty(idapUserByRoleIdAndSszh))
			return idapUserByRoleIdAndSszh.get(0);
		return null;
	}
}

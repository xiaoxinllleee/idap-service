package org.cmms.modules.system.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.constant.DictConstant;
import org.cmms.common.system.api.ISysBaseAPI;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.util.JwtUtil;
import org.cmms.common.system.vo.DictModel;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.MD5Util;
import org.cmms.common.util.PasswordUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.encryption.RSAEncryptUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.ExcelVerifyUtil;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.system.entity.*;
import org.cmms.modules.system.model.DepartIdHrModel;
import org.cmms.modules.system.model.SysUserSysDepartModel;
import org.cmms.modules.system.service.*;
import org.cmms.modules.system.verify.SysUserImpVerify;
import org.cmms.modules.system.vo.SysDepartUsersVO;
import org.cmms.modules.system.vo.SysUserExp;
import org.cmms.modules.system.vo.SysUserRoleVO;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @Author scott
 * @since 2018-12-20
 */
@Slf4j
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends JeecgController<SysUser,ISysUserService> {
	@Autowired
	private ISysBaseAPI sysBaseAPI;

	@Autowired
	private ISysUserService sysUserService;

    @Autowired
    private ISysDepartService sysDepartService;

	@Autowired
	private ISysUserRoleService sysUserRoleService;

	@Autowired
	private ISysUserDepartService sysUserDepartService;

	@Autowired
	private ISysUserRoleService userRoleService;

	@Autowired
	private RedisUtil redisUtil;

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysLogService sysLogService;

    @Autowired
    private ISysBasUserService sysBasUserService;

    @Autowired
    SysUserImpVerify sysUserImpVerify;
    @Autowired
    ISysDictService sysDictService;
    @Autowired
    IHrBasStaffService hrBasStaffService;
    @Autowired
    IHrBasStaffPostService hrBasStaffPostService;
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result<IPage<SysUser>> queryPageList(SysUser user,@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,HttpServletRequest req) {
		Result<IPage<SysUser>> result = new Result<IPage<SysUser>>();
		QueryWrapper<SysUser> queryWrapper = QueryGenerator.initQueryWrapper(user, req.getParameterMap());
		Page<SysUser> page = new Page<SysUser>(pageNo, pageSize);
		IPage<SysUser> pageList = sysUserService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@RequiresPermissions("user:add")
	public Result<SysUser> add(@RequestBody JSONObject jsonObject) {
		Result<SysUser> result = new Result<SysUser>();
		String selectedRoles = jsonObject.getString("selectedroles");
		String selectedDeparts = jsonObject.getString("selecteddeparts");
		try {
			SysUser user = JSON.parseObject(jsonObject.toJSONString(), SysUser.class);
            String userId = IdUtil.fastSimpleUUID();
            user.setId(userId);
            user.setCreateTime(new Date());//设置创建时间
			String salt = oConvertUtils.randomGen(8);
			user.setSalt(salt);
			String passwordEncode = MD5Util.MD5(user.getPassword()).toUpperCase();//PasswordUtil.encrypt(user.getUsername(), user.getPassword(), salt);
			user.setPassword(passwordEncode);
			user.setStatus(1);
			user.setDelFlag("0");
            String roles = jsonObject.getString("selectedroles");
            String departs = jsonObject.getString("selecteddeparts");
            StringBuffer roleStr =new StringBuffer("");
            if(oConvertUtils.isNotEmpty(roles)) {
                String[] arr = roles.split(",");
                for (String roleId : arr) {
                  SysRole role =  sysRoleService.getById(roleId);
                    roleStr.append(role.getRoleCode()+",");
                }
            }
            user.setRoles(roleStr.toString().trim().equals("")?"":roleStr.toString().substring(0,roleStr.length()-1));
            user.setDepartIds(departs);
            if (StringUtils.isNotEmpty(departs)) {
                String[] arr = departs.split(",");
                user.setOrgCode(arr[0]);
            }
			sysUserService.addUserWithRole(user, selectedRoles);
            sysUserService.addUserWithDepart(user, selectedDeparts);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.error500("操作失败");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	//@RequiresPermissions("user:edit")
	public Result<SysUser> edit(@RequestBody JSONObject jsonObject) {
		Result<SysUser> result = new Result<SysUser>();
		try {
			SysUser sysUser = sysUserService.getById(jsonObject.getString("id"));
			SysBasUser sysBasUser = sysBasUserService.getByUserId(jsonObject.getString("id"));
            sysLogService.addLog("编辑用户，id： " +jsonObject.getString("id") ,CommonConstant.LOG_TYPE_2, 2);
			if(sysUser==null) {
				result.error500("未找到对应实体");
			}else {
				SysUser user = JSON.parseObject(jsonObject.toJSONString(), SysUser.class);
                String departs = jsonObject.getString("selecteddeparts");
                if (StringUtils.isNotBlank(departs)){
                    if (departs.contains(",")){
                        sysBasUser.setJgdm(departs.split(",")[0]);
                    }else {
                        sysBasUser.setJgdm(departs);
                    }
                }
                //更新用户信息
                sysBasUser.setUsername(user.getUsername());
                sysBasUser.setRealname(user.getRealname());
                sysBasUser.setTellid(user.getGyh());
                sysBasUser.setPhone(user.getPhone());
                sysBasUser.setEmail(user.getEmail());
                sysBasUser.setUpdatetime(new Date());
                sysBasUser.setUpdatepasswordtime(new Date());
                UpdateWrapper<SysBasUser> userUpdateWrapper = new UpdateWrapper<>();
                userUpdateWrapper.eq("userid", sysBasUser.getUserid());
                sysBasUserService.update(sysBasUser, userUpdateWrapper);
				user.setUpdateTime(new Date());
				//String passwordEncode = PasswordUtil.encrypt(user.getUsername(), user.getPassword(), sysUser.getSalt());
				user.setPassword(sysUser.getPassword());
				String roles = jsonObject.getString("selectedroles");
                StringBuffer roleStr =new StringBuffer("");
                if(oConvertUtils.isNotEmpty(roles)) {
                    String[] arr = roles.split(",");
                    for (String roleId : arr) {
                        SysRole role =  sysRoleService.getById(roleId);
                        roleStr.append(role.getRoleCode()+",");
                    }
                }
                user.setRoles(roleStr.toString().trim().equals("")?"":roleStr.toString().substring(0,roleStr.length()-1));
                user.setDepartIds(departs);
				sysUserService.editUserWithRole(user, roles);
                sysUserService.editUserWithDepart(user, departs);
				result.success("修改成功!");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.error500("操作失败");
		}
		return result;
	}

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.PUT)
    public Result<?> getUserInfo(@RequestBody JSONObject jsonObject) {
        Result<SysUser> result = new Result<SysUser>();
        SysUser sysUser;
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
             sysUser = sysUserService.getById(loginUser.getId());
            if(sysUser==null) {
                return result.error500("未找到对应实体");
            }else {
                return result.ok(sysUser);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return  result;
    }

    @RequestMapping(value = "/getUserRole", method = RequestMethod.PUT)
    public Result<?> getUserRole(@RequestBody JSONObject jsonObject) {
        Result<VsysUserRole> result = new Result<VsysUserRole>();
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            List<VsysUserRole> VsysUserRole = sysUserRoleService.findByUserIdAndRoleCode(loginUser.getId(),"khjl");
            if(VsysUserRole==null||VsysUserRole.size()==0) {
                return result.success("false");
            }else {
                return result.success("true");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return  result;
    }


    @RequestMapping(value = "/getUserhzRole", method = RequestMethod.PUT)
    public Result<?> getUserhzRole(@RequestBody JSONObject jsonObject) {
        Result<VsysUserRole> result = new Result<VsysUserRole>();
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            List<VsysUserRole> VsysUserRole = sysUserRoleService.findByUserIdAndRoleCode(loginUser.getId(),"zhhz");
            if(VsysUserRole==null||VsysUserRole.size()==0) {
                VsysUserRole = sysUserRoleService.findByUserIdAndRoleCode(loginUser.getId(),"ZHHZ");
                if(VsysUserRole==null||VsysUserRole.size()==0) {
                    return result.success("false");
                }else {
                    return result.success("true");
                }
            }else {
                return result.success("true");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return  result;
    }
    @RequestMapping(value = "/getUserRoleCode", method = RequestMethod.PUT)
    public Result<?> getUserRoleCode(@RequestBody JSONObject jsonObject) {
        Result<VsysUserRole> result = new Result<VsysUserRole>();
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            List<SysRole> sysRoles= sysUserRoleService.getUserRoleCode(loginUser.getId());
            if(sysRoles==null||sysRoles.size()==0) {
                return result.success("false");
            }else {
                return result.success(sysRoles.get(0).getRoleCode());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return  result;
    }

    @RequestMapping(value = "/getUserFxjlRole", method = RequestMethod.PUT)
    public Result<?> getUserFxjlRole(@RequestBody JSONObject jsonObject) {
        Result<VsysUserRole> result = new Result<VsysUserRole>();
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            List<VsysUserRole> VsysUserRole = sysUserRoleService.findByUserIdAndRoleCode(loginUser.getId(),"fxjl");
            if(VsysUserRole==null||VsysUserRole.size()==0) {
                return result.success("false");
            }else {
                return result.success("true");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return  result;
    }
	/**
	 * 删除用户
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
        sysLogService.addLog("删除用户，id： " +id ,CommonConstant.LOG_TYPE_2, 3);
		this.sysUserService.deleteUser(id);
		return Result.ok("删除用户成功");
	}

	/**
	 * 批量删除用户
	 */
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.DELETE)
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        sysLogService.addLog("批量删除用户， ids： " +ids ,CommonConstant.LOG_TYPE_2, 3);
		this.sysUserService.deleteBatchUsers(ids);
		return Result.ok("批量删除用户成功");
	}

	/**
	  * 冻结&解冻用户
	 * @param jsonObject
	 * @return
	 */
	@RequestMapping(value = "/frozenBatch", method = RequestMethod.PUT)
	public Result<SysUser> frozenBatch(@RequestBody JSONObject jsonObject) {
		Result<SysUser> result = new Result<SysUser>();
		try {
			String ids = jsonObject.getString("ids");
			String status = jsonObject.getString("status");
			String[] arr = ids.split(",");
			for (String id : arr) {
				if(oConvertUtils.isNotEmpty(id)) {
					this.sysBasUserService.update(new SysBasUser().setStatus(status),
							new UpdateWrapper<SysBasUser>().lambda().eq(SysBasUser::getUserid,id));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.error500("操作失败"+e.getMessage());
		}
		result.success("操作成功!");
		return result;

    }


    /**
     * 冻结&解冻用户
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/pcBatch", method = RequestMethod.PUT)
    public Result<SysUser> pcBatch(@RequestBody JSONObject jsonObject) {
        Result<SysUser> result = new Result<SysUser>();
        try {
            String id = jsonObject.getString("id");
            String pcFlag = jsonObject.getString("pcFlag");
            if(oConvertUtils.isNotEmpty(id)) {
                this.sysUserService.update(new SysUser().setPcFlag(Integer.parseInt(pcFlag)),
                        new UpdateWrapper<SysUser>().lambda().eq(SysUser::getId,id));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败"+e.getMessage());
        }
        result.success("操作成功!");
        return result;
    }

    /**
     * 冻结&解冻用户
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/appBatch", method = RequestMethod.PUT)
    public Result<SysUser> appBatch(@RequestBody JSONObject jsonObject) {
        Result<SysUser> result = new Result<SysUser>();
        try {
            String id = jsonObject.getString("id");
            String appFlag = jsonObject.getString("appFlag");
            if(oConvertUtils.isNotEmpty(id)) {
                this.sysUserService.update(new SysUser().setAppFlag(Integer.parseInt(appFlag)),
                        new UpdateWrapper<SysUser>().lambda().eq(SysUser::getId,id));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败"+e.getMessage());
        }
        result.success("操作成功!");
        return result;

    }


    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public Result<SysUser> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<SysUser> result = new Result<SysUser>();
        SysUser sysUser = sysUserService.getById(id);
        if (sysUser == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(sysUser);
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping(value = "/queryUserRole", method = RequestMethod.GET)
    public Result<List<String>> queryUserRole(@RequestParam(name = "userid", required = true) String userid) {
        Result<List<String>> result = new Result<>();
        List<String> list = new ArrayList<String>();
        List<SysUserRole> userRole = sysUserRoleService.list(new QueryWrapper<SysUserRole>().lambda().eq(SysUserRole::getUserId, userid));
        if (userRole == null || userRole.size() <= 0) {
            result.error500("未找到用户相关角色信息");
        } else {
            for (SysUserRole sysUserRole : userRole) {
                list.add(sysUserRole.getRoleId());
            }
            result.setSuccess(true);
            result.setResult(list);
        }
        return result;
    }


    /**
	  *  校验用户账号是否唯一<br>
	  *  可以校验其他 需要检验什么就传什么。。。
     *
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "/checkOnlyUser", method = RequestMethod.GET)
    public Result<Boolean> checkOnlyUser(SysUser sysUser) {
        Result<Boolean> result = new Result<>();
        //如果此参数为false则程序发生异常
        result.setResult(true);
        try {
            //通过传入信息查询新的用户信息
            SysUser user = sysUserService.getOne(new QueryWrapper<SysUser>(sysUser));
            if (user != null) {
                result.setSuccess(false);
                result.setMessage("用户账号已存在");
                return result;
            }

        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            return result;
        }
        result.setSuccess(true);
        return result;
    }

    /**
     * 修改密码
     */
//    @RequiresRoles({"admin"})
    @RequestMapping(value = "/changePassword", method = RequestMethod.PUT)
    public Result<?> changePassword(@RequestBody SysUser sysUser) {
        SysUser u = this.sysUserService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, sysUser.getUsername()));
        if (u == null) {
            return Result.error("用户不存在！");
        }
        sysUser.setId(u.getId());
        return sysUserService.changePassword(sysUser);
    }

    /**
     * 查询指定用户和部门关联的数据
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/userDepartList", method = RequestMethod.GET)
    public Result<List<DepartIdHrModel>> getUserDepartsList(@RequestParam(name = "userId", required = true) String userId) {
        Result<List<DepartIdHrModel>> result = new Result<>();
        try {
            List<DepartIdHrModel> depIdModelList = this.sysUserDepartService.queryHrDepartIdsOfUser(userId);
            if (depIdModelList != null && depIdModelList.size() > 0) {
                result.setSuccess(true);
                result.setMessage("查找成功");
                result.setResult(depIdModelList);
            } else {
                result.setSuccess(false);
                result.setMessage("查找失败");
            }
            return result;
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage("查找过程中出现了异常: " + e.getMessage());
            return result;
        }

    }

    /**
     * 生成在添加用户情况下没有主键的问题,返回给前端,根据该id绑定部门数据
     *
     * @return
     */
    @RequestMapping(value = "/generateUserId", method = RequestMethod.GET)
    public Result<String> generateUserId() {
        Result<String> result = new Result<>();
        System.out.println("我执行了,生成用户ID==============================");
        String userId = UUID.randomUUID().toString().replace("-", "");
        result.setSuccess(true);
        result.setResult(userId);
        return result;
    }

    /**
     * 根据部门id查询用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryUserByDepId", method = RequestMethod.GET)
    public Result<List<SysUser>> queryUserByDepId(@RequestParam(name = "id", required = true) String id) {
        Result<List<SysUser>> result = new Result<>();
        List<SysUser> userList = sysUserDepartService.queryUserByDepId(id);
        try {
            result.setSuccess(true);
            result.setResult(userList);
            return result;
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
            result.setSuccess(false);
            return result;
        }
    }

    /**
     * 查询所有用户所对应的角色信息
     *
     * @return
     */
    @RequestMapping(value = "/queryUserRoleMap", method = RequestMethod.GET)
    public Result<Map<String, String>> queryUserRole() {
        Result<Map<String, String>> result = new Result<>();
        Map<String, String> map = userRoleService.queryUserRole();
        result.setResult(map);
        result.setSuccess(true);
        return result;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(SysUser sysUser,HttpServletRequest request) {
        // Step.1 组装查询条件
        QueryWrapper<SysUser> queryWrapper = QueryGenerator.initQueryWrapper(sysUser, request.getParameterMap());
        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        //update-begin--Author:kangxiaolin  Date:20180825 for：[03]用户导出，如果选择数据则只导出相关数据--------------------
        String selections = request.getParameter("selections");
       if(!oConvertUtils.isEmpty(selections)){
           queryWrapper.in("id",selections.split(","));
       }
        //update-end--Author:kangxiaolin  Date:20180825 for：[03]用户导出，如果选择数据则只导出相关数据----------------------
        List<SysUser> pageList = sysUserService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "用户列表");
        mv.addObject(NormalExcelConstants.CLASS, SysUser.class);
		LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("用户列表数据", "导出人:"+user.getRealname(), "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    /*@RequiresPermissions("user:import")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<SysUser> listSysUsers = ExcelImportUtil.importExcel(file.getInputStream(), SysUser.class, params);
                for (SysUser sysUserExcel : listSysUsers) {
                    if (sysUserExcel.getPassword() == null) {
                        // 密码默认为“123456”
                        sysUserExcel.setPassword("123456");
                    }
                    sysUserService.save(sysUserExcel);
                }
                return Result.ok("文件导入成功！数据行数：" + listSysUsers.size());
            } catch (Exception e) {
                log.error(e.getMessage(),e);
                return Result.error("抱歉! 您导入的数据中用户名已经存在.");
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                	log.error(e.getMessage(), e);
                }
            }
        }
        return Result.error("文件导入失败！");
    }*/

    /**
	 * @功能：根据id 批量查询
	 * @param userIds
	 * @return
	 */
	@RequestMapping(value = "/queryByIds", method = RequestMethod.GET)
	public Result<Collection<SysUser>> queryByIds(@RequestParam String userIds) {
		Result<Collection<SysUser>> result = new Result<>();
		String[] userId = userIds.split(",");
		Collection<String> idList = Arrays.asList(userId);
		Collection<SysUser> userRole = sysUserService.listByIds(idList);
		result.setSuccess(true);
		result.setResult(userRole);
		return result;
	}

	/**
	 * 首页用户重置密码
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
	public Result<?> changPassword(@RequestBody JSONObject json) {
		String username = json.getString("username");
		String oldpassword = json.getString("oldpassword").toUpperCase();
		String password = json.getString("password").toUpperCase();
		String confirmpassword = json.getString("confirmpassword").toUpperCase();
		SysUser user = this.sysUserService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
		if(user==null) {
			return Result.error("用户不存在！");
		}
		return sysUserService.resetPassword(username,oldpassword,password,confirmpassword);
	}

    @RequestMapping(value = "/userRoleList", method = RequestMethod.GET)
    public Result<IPage<SysUser>> userRoleList(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                               @RequestParam(name="pageSize", defaultValue="10") Integer pageSize, HttpServletRequest req) {
        Result<IPage<SysUser>> result = new Result<IPage<SysUser>>();
        Page<SysUser> page = new Page<SysUser>(pageNo, pageSize);
        String roleId = req.getParameter("roleId");
        String username = req.getParameter("username");
        IPage<SysUser> pageList = sysUserService.getUserByRoleId(page,roleId,username);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 给指定角色添加用户
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/addSysUserRole", method = RequestMethod.POST)
    public Result<String> addSysUserRole(@RequestBody SysUserRoleVO sysUserRoleVO) {
        Result<String> result = new Result<String>();
        try {
            String sysRoleId = sysUserRoleVO.getRoleId();
            for(String sysUserId:sysUserRoleVO.getUserIdList()) {
                SysUserRole sysUserRole = new SysUserRole(sysUserId,sysRoleId);
                QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<SysUserRole>();
                queryWrapper.eq("role_id", sysRoleId).eq("user_id",sysUserId);
                SysUserRole one = sysUserRoleService.getOne(queryWrapper);
                if(one==null){
                    sysUserRoleService.save(sysUserRole);
                }

            }
            result.setMessage("添加成功!");
            result.setSuccess(true);
            return result;
        }catch(Exception e) {
            log.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage("出错了: " + e.getMessage());
            return result;
        }
    }
    /**
     *   删除指定角色的用户关系
     * @param
     * @return
     */
    @RequestMapping(value = "/deleteUserRole", method = RequestMethod.DELETE)
    public Result<SysUserRole> deleteUserRole(@RequestParam(name="roleId") String roleId,
                                                    @RequestParam(name="userId",required=true) String userId
    ) {
        Result<SysUserRole> result = new Result<SysUserRole>();
        try {
            QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<SysUserRole>();
            queryWrapper.eq("role_id", roleId).eq("user_id",userId);
            sysUserRoleService.remove(queryWrapper);
            result.success("删除成功!");
        }catch(Exception e) {
            log.error(e.getMessage(), e);
            result.error500("删除失败！");
        }
        return result;
    }

    /**
     * 批量删除指定角色的用户关系
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/deleteUserRoleBatch", method = RequestMethod.DELETE)
    public Result<SysUserRole> deleteUserRoleBatch(
            @RequestParam(name="roleId") String roleId,
            @RequestParam(name="userIds",required=true) String userIds) {
        Result<SysUserRole> result = new Result<SysUserRole>();
        try {
            QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<SysUserRole>();
            queryWrapper.eq("role_id", roleId).in("user_id",Arrays.asList(userIds.split(",")));
            sysUserRoleService.remove(queryWrapper);
            result.success("删除成功!");
        }catch(Exception e) {
            log.error(e.getMessage(), e);
            result.error500("删除失败！");
        }
        return result;
    }

    /**
     * 部门用户列表
     */
    @RequestMapping(value = "/departUserList", method = RequestMethod.GET)
    public Result<IPage<SysUser>> departUserList(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize, HttpServletRequest req) {
        Result<IPage<SysUser>> result = new Result<IPage<SysUser>>();
        Page<SysUser> page = new Page<SysUser>(pageNo, pageSize);
        String depId = req.getParameter("depId");
        String username = req.getParameter("username");
        IPage<SysUser> pageList = sysUserService.getUserByDepId(page,depId,username);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }


    /**
     * 根据 orgCode 查询用户，包括子部门下的用户
     * 若某个用户包含多个部门，则会显示多条记录，可自行处理成单条记录
     */
    @GetMapping("/queryByOrgCode")
    public Result<?> queryByDepartId(
            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "orgCode") String orgCode,
            SysUser userParams
    ) {
        IPage<SysUserSysDepartModel> pageList = sysUserService.queryUserByOrgCode(orgCode, userParams, new Page(pageNo, pageSize));
        return Result.ok(pageList);
    }

    /**
     * 根据 orgCode 查询用户，包括子部门下的用户
     * 针对通讯录模块做的接口，将多个部门的用户合并成一条记录，并转成对前端友好的格式
     */
    @GetMapping("/queryByOrgCodeForAddressList")
    public Result<?> queryByOrgCodeForAddressList(
            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "orgCode") String orgCode,
            SysUser userParams
    ) {
        IPage page = new Page(pageNo, pageSize);
        IPage<SysUserSysDepartModel> pageList = sysUserService.queryUserByOrgCode(orgCode, userParams, page);
        List<SysUserSysDepartModel> list = pageList.getRecords();

        // 记录所有出现过的 user, key = userId
        Map<String, JSONObject> hasUser = new HashMap<>(list.size());

        JSONArray resultJson = new JSONArray(list.size());

        for (SysUserSysDepartModel item : list) {
            String userId = item.getSysUser().getId();
            // userId
            JSONObject getModel = hasUser.get(userId);
            // 之前已存在过该用户，直接合并数据
            if (getModel != null) {
                String departName = getModel.get("departName").toString();
                getModel.put("departName", (departName + " | " + item.getSysDepart().getDepartName()));
            } else {
                // 将用户对象转换为json格式，并将部门信息合并到 json 中
                JSONObject json = JSON.parseObject(JSON.toJSONString(item.getSysUser()));
                json.remove("id");
                json.put("userId", userId);
                json.put("departId", item.getSysDepart().getId());
                json.put("departName", item.getSysDepart().getDepartName());

                resultJson.add(json);
                hasUser.put(userId, json);
            }
        }

        IPage<JSONObject> result = new Page<>(pageNo, pageSize, pageList.getTotal());
        result.setRecords(resultJson.toJavaList(JSONObject.class));
        return Result.ok(result);
    }

    /**
     * 给指定部门添加对应的用户
     */
    @RequestMapping(value = "/editSysDepartWithUser", method = RequestMethod.POST)
    public Result<String> editSysDepartWithUser(@RequestBody SysDepartUsersVO sysDepartUsersVO) {
        Result<String> result = new Result<String>();
        try {
            String sysDepId = sysDepartUsersVO.getDepId();
            for(String sysUserId:sysDepartUsersVO.getUserIdList()) {
                SysUserDepart sysUserDepart = new SysUserDepart(null,sysUserId,sysDepId);
                QueryWrapper<SysUserDepart> queryWrapper = new QueryWrapper<SysUserDepart>();
                queryWrapper.eq("dep_id", sysDepId).eq("user_id",sysUserId);
                SysUserDepart one = sysUserDepartService.getOne(queryWrapper);
                if(one==null){
                    sysUserDepartService.save(sysUserDepart);
                }
            }
            result.setMessage("添加成功!");
            result.setSuccess(true);
            return result;
        }catch(Exception e) {
            log.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage("出错了: " + e.getMessage());
            return result;
        }
    }

    /**
     *   删除指定机构的用户关系
     */
    @RequestMapping(value = "/deleteUserInDepart", method = RequestMethod.DELETE)
    public Result<SysUserDepart> deleteUserInDepart(@RequestParam(name="depId") String depId,
                                                    @RequestParam(name="userId",required=true) String userId
    ) {
        Result<SysUserDepart> result = new Result<SysUserDepart>();
        try {
            QueryWrapper<SysUserDepart> queryWrapper = new QueryWrapper<SysUserDepart>();
            queryWrapper.eq("dep_id", depId).eq("user_id",userId);
            boolean b = sysUserDepartService.remove(queryWrapper);
            if(b){
                result.success("删除成功!");
            }else{
                result.error500("当前选中部门与用户无关联关系!");
            }
        }catch(Exception e) {
            log.error(e.getMessage(), e);
            result.error500("删除失败！");
        }
        return result;
    }

    /**
     * 批量删除指定机构的用户关系
     */
    @RequestMapping(value = "/deleteUserInDepartBatch", method = RequestMethod.DELETE)
    public Result<SysUserDepart> deleteUserInDepartBatch(
            @RequestParam(name="depId") String depId,
            @RequestParam(name="userIds",required=true) String userIds) {
        Result<SysUserDepart> result = new Result<SysUserDepart>();
        try {
            QueryWrapper<SysUserDepart> queryWrapper = new QueryWrapper<SysUserDepart>();
            queryWrapper.eq("dep_id", depId).in("user_id",Arrays.asList(userIds.split(",")));
            sysUserDepartService.remove(queryWrapper);
            result.success("删除成功!");
        }catch(Exception e) {
            log.error(e.getMessage(), e);
            result.error500("删除失败！");
        }
        return result;
    }

    /**
         *  查询当前用户的所有部门/当前部门编码
     * @return
     */
    @RequestMapping(value = "/getCurrentUserDeparts", method = RequestMethod.GET)
    public Result<Map<String,Object>> getCurrentUserDeparts() {
        Result<Map<String,Object>> result = new Result<Map<String,Object>>();
        try {
        	LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
            List<HrBasOrganization> list = this.sysDepartService.queryUserDeparts(sysUser.getId());
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("list", list);
            map.put("orgCode", sysUser.getOrgCode());
            result.setSuccess(true);
            result.setResult(map);
        }catch(Exception e) {
            log.error(e.getMessage(), e);
            result.error500("查询失败！");
        }
        return result;
    }




	/**
	 * 用户注册接口
	 *
	 * @param jsonObject
	 * @param user
	 * @return
	 */
	@PostMapping("/register")
	public Result<JSONObject> userRegister(@RequestBody JSONObject jsonObject, SysUser user) {
		Result<JSONObject> result = new Result<JSONObject>();
		String phone = jsonObject.getString("phone");
		String smscode = jsonObject.getString("smscode");
		Object code = redisUtil.get(phone);
		String username = jsonObject.getString("username");
		//未设置用户名，则用手机号作为用户名
		if(oConvertUtils.isEmpty(username)){
            username = phone;
        }
        //未设置密码，则随机生成一个密码
		String password = jsonObject.getString("password");
		if(oConvertUtils.isEmpty(password)){
            password = RandomUtil.randomString(8);
        }
		String email = jsonObject.getString("email");
		SysUser sysUser1 = sysUserService.getUserByName(username);
		if (sysUser1 != null) {
			result.setMessage("用户名已注册");
			result.setSuccess(false);
			return result;
		}
		SysUser sysUser2 = sysUserService.getUserByPhone(phone);
		if (sysUser2 != null) {
			result.setMessage("该手机号已注册");
			result.setSuccess(false);
			return result;
		}

		if(oConvertUtils.isNotEmpty(email)){
            SysUser sysUser3 = sysUserService.getUserByEmail(email);
            if (sysUser3 != null) {
                result.setMessage("邮箱已被注册");
                result.setSuccess(false);
                return result;
            }
        }

		if (!smscode.equals(code)) {
			result.setMessage("手机验证码错误");
			result.setSuccess(false);
			return result;
		}

		try {
			user.setCreateTime(new Date());// 设置创建时间
			String salt = oConvertUtils.randomGen(8);
			String passwordEncode = PasswordUtil.encrypt(username, password, salt);
			user.setSalt(salt);
			user.setUsername(username);
			user.setRealname(username);
			user.setPassword(passwordEncode);
			user.setEmail(email);
			user.setPhone(phone);
			user.setStatus(1);
			user.setDelFlag(CommonConstant.DEL_FLAG_0.toString());
			user.setActivitiSync(CommonConstant.ACT_SYNC_1);
			sysUserService.addUserWithRole(user,"ee8626f80f7c2619917b6236f3a7f02b");//默认临时角色 test
			result.success("注册成功");
		} catch (Exception e) {
			result.error500("注册失败");
		}
		return result;
	}

	/**
	 *
	 * @param //根据用户名或手机号查询用户信息
	 * @return
	 */
	@GetMapping("/querySysUser")
	public Result<Map<String, Object>> querySysUser(SysUser sysUser) {
		String phone = sysUser.getPhone();
		String username = sysUser.getUsername();
		Result<Map<String, Object>> result = new Result<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		if (oConvertUtils.isNotEmpty(phone)) {
			SysUser user = sysUserService.getUserByPhone(phone);
			if(user!=null) {
				map.put("username",user.getUsername());
				map.put("phone",user.getPhone());
				result.setSuccess(true);
				result.setResult(map);
				return result;
			}
		}
		if (oConvertUtils.isNotEmpty(username)) {
			SysUser user = sysUserService.getUserByName(username);
			if(user!=null) {
				map.put("username",user.getUsername());
				map.put("phone",user.getPhone());
				result.setSuccess(true);
				result.setResult(map);
				return result;
			}
		}
		result.setSuccess(false);
		result.setMessage("验证失败");
		return result;
	}

	/**
	 * 用户手机号验证
	 */
	@PostMapping("/phoneVerification")
	public Result<String> phoneVerification(@RequestBody JSONObject jsonObject) {
		Result<String> result = new Result<String>();
		String phone = jsonObject.getString("phone");
		String smscode = jsonObject.getString("smscode");
		Object code = redisUtil.get(phone);
		if (!smscode.equals(code)) {
			result.setMessage("手机验证码错误");
			result.setSuccess(false);
			return result;
		}
		redisUtil.set(phone, smscode);
		result.setResult(smscode);
		result.setSuccess(true);
		return result;
	}



	/**
	 * 用户更改密码
	 */
	@GetMapping("/passwordChange")
	public Result<SysUser> passwordChange(@RequestParam(name="username")String username,
										  @RequestParam(name="password")String password,
			                              @RequestParam(name="smscode")String smscode,
			                              @RequestParam(name="phone") String phone) {
        Result<SysUser> result = new Result<SysUser>();
        if(oConvertUtils.isEmpty(username) || oConvertUtils.isEmpty(password) || oConvertUtils.isEmpty(smscode)  || oConvertUtils.isEmpty(phone) ) {
            result.setMessage("重置密码失败！");
            result.setSuccess(false);
            return result;
        }

        SysUser sysUser=new SysUser();
        Object object= redisUtil.get(phone);
        if(null==object) {
        	result.setMessage("短信验证码失效！");
            result.setSuccess(false);
            return result;
        }
        if(!smscode.equals(object)) {
        	result.setMessage("短信验证码不匹配！");
            result.setSuccess(false);
            return result;
        }
        sysUser = this.sysUserService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername,username).eq(SysUser::getPhone,phone));
        if (sysUser == null) {
            result.setMessage("未找到用户！");
            result.setSuccess(false);
            return result;
        } else {
            String salt = oConvertUtils.randomGen(8);
            sysUser.setSalt(salt);
            String passwordEncode = PasswordUtil.encrypt(sysUser.getUsername(), password, salt);
            sysUser.setPassword(passwordEncode);
            this.sysUserService.updateById(sysUser);
            result.setSuccess(true);
            result.setMessage("密码重置完成！");
            return result;
        }
    }


	/**
	 * 根据TOKEN获取用户的部分信息（返回的数据是可供表单设计器使用的数据）
	 *
	 * @return
	 */
	@GetMapping("/getUserSectionInfoByToken")
	public Result<?> getUserSectionInfoByToken(HttpServletRequest request, @RequestParam(name = "token", required = false) String token) {
		try {
			String username = null;
			// 如果没有传递token，就从header中获取token并获取用户信息
			if (oConvertUtils.isEmpty(token)) {
				 username = JwtUtil.getUserNameByToken(request);
			} else {
				 username = JwtUtil.getUsername(token);
			}

			log.info(" ------ 通过令牌获取部分用户信息，当前用户： " + username);

			// 根据用户名查询用户信息
			SysUser sysUser = sysUserService.getUserByName(username);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sysUserId", sysUser.getId());
			map.put("sysUserCode", sysUser.getUsername()); // 当前登录用户登录账号
			map.put("sysUserName", sysUser.getRealname()); // 当前登录用户真实名称
			map.put("sysOrgCode", sysUser.getOrgCode()); // 当前登录用户部门编号

			log.info(" ------ 通过令牌获取部分用户信息，已获取的用户信息： " + map);

			return Result.ok(map);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Result.error(500, "查询失败:" + e.getMessage());
		}
	}

	/**
	 * 获取用户列表  根据用户名和真实名 模糊匹配
	 * @param keyword
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/appUserList")
	public Result<?> appUserList(@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
			@RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
		try {
			//TODO 从查询效率上将不要用mp的封装的page分页查询 建议自己写分页语句
			LambdaQueryWrapper<SysUser> query = new LambdaQueryWrapper<SysUser>();
			query.eq(SysUser::getActivitiSync, "1");
			query.eq(SysUser::getDelFlag,"0");
			query.and(i -> i.like(SysUser::getUsername, keyword).or().like(SysUser::getRealname, keyword));

			Page<SysUser> page = new Page<>(pageNo, pageSize);
			IPage<SysUser> res = this.sysUserService.page(page, query);
			return Result.ok(res);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Result.error(500, "查询失败:" + e.getMessage());
		}

	}

    /**
     * 获取被逻辑删除的用户列表，无分页
     *
     * @return logicDeletedUserList
     */
    @GetMapping("/recycleBin")
    public Result getRecycleBin() {
        List<SysUser> logicDeletedUserList = sysUserService.queryLogicDeleted();
        if (logicDeletedUserList.size() > 0) {
            // 批量查询用户的所属部门
            // step.1 先拿到全部的 userIds
            List<String> userIds = logicDeletedUserList.stream().map(SysUser::getId).collect(Collectors.toList());
            // step.2 通过 userIds，一次性查询用户的所属部门名字
            Map<String, String> useDepNames = sysUserService.getDepNamesByUserIds(userIds);
            logicDeletedUserList.forEach(item -> item.setOrgCode(useDepNames.get(item.getId())));
        }
        return Result.ok(logicDeletedUserList);
    }

    /**
     * 还原被逻辑删除的用户
     *
     * @param userIds 被还原的用户ID，是个 list 集合
     * @return
     */
    @PutMapping("/recycleBin")
    public Result putRecycleBin(@RequestBody List<String> userIds, HttpServletRequest request) {
        if (userIds != null && userIds.size() > 0) {
            SysUser updateUser = new SysUser();
            updateUser.setUpdateBy(JwtUtil.getUserNameByToken(request));
            updateUser.setUpdateTime(new Date());
            sysUserService.revertLogicDeleted(userIds, updateUser);
        }
        return Result.ok("还原成功");
    }

    /**
     * 彻底删除用户
     *
     * @param userIds 被删除的用户ID，多个id用半角逗号分割
     * @return
     */
    @DeleteMapping("/recycleBin")
    public Result deleteRecycleBin(@RequestParam("userIds") String userIds) {
        if (StringUtils.isNotBlank(userIds)) {
            sysUserService.removeLogicDeleted(Arrays.asList(userIds.split(",")));
        }
        return Result.ok("删除成功");
    }

    /**
     * 导出模板excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        // AutoPoi 导出Excel
        ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "用户管理");
        modelAndView.addObject(NormalExcelConstants.CLASS, SysUserExp.class);
        ExportParams exportParams = new ExportParams("用户管理", "模板信息");
        modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
        modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
        return modelAndView;
    }

    /**
     * 通过excel导入数据
     *
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(@RequestBody JSONObject jsonObject) {
        String filePaths = jsonObject.getString("filePaths");
        if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
            return Result.error("请先上传文件！");
        }
        String[] filePathList = filePaths.split(",");
        JSONObject obj = new JSONObject();
        for (String filePath : filePathList) {
            String baseFilePath =  uploadpath + File.separator + filePath;
            File file = new File(baseFilePath);
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            params.setHeadRows(1);
            params.setNeedSave(false);
            params.setVerifyHanlder(sysUserImpVerify);
            FileOutputStream fos = null;
            try {
                ExcelImportResult<SysUserExp> importResult = ExcelImportUtil.importExcelVerify(file, SysUserExp.class, params);
                List<SysUserExp> list = importResult.getList();
                if (CollUtil.isNotEmpty(list)){
                    //List<DictModel> dictModels = sysDictService.queryDictItemsByCode(DictConstant.DICT_YGLX);
                    //List<DictModel> ygzts = sysDictService.queryDictItemsByCode(DictConstant.DICT_YGZT);
                    List<DictModel> roles = sysDictService.queryTableDictItemsByCode(DictConstant.SYS_ROLE, "id", "role_name");
                    List<DictModel> zzbzs = sysDictService.queryTableDictItemsByCode(DictConstant.HR_BAS_ORGANIZATION, "zzbz", "zzjc");
                    LambdaQueryWrapper<SysBasUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                    List<SysBasUser> sysUsers = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++)
                    {
                        String id = IdUtil.fastSimpleUUID();
                        SysUserExp sysUserExp = list.get(i);
                        lambdaQueryWrapper.eq(SysBasUser::getUsername,sysUserExp.getUsername());
                        Long count = sysBasUserService.count(lambdaQueryWrapper);
                        if (count>0){
                            log.info("该用户名已存在",sysUserExp.getUsername());
                            continue;
                        }

                        SysBasUser save = new SysBasUser();
                        save.setUserid(id);
                        BeanUtil.copyProperties(sysUserExp,save);

                        LambdaQueryWrapper<HrBasStaff> staffLambdaQueryWrapper = new LambdaQueryWrapper<>();
                        staffLambdaQueryWrapper.eq(HrBasStaff::getYggh,save.getUsername());
                        HrBasStaff one = hrBasStaffService.getOne(staffLambdaQueryWrapper);
                        if (one != null && StringUtils.isNotBlank(one.getGyh())){
                            save.setTellid(one.getGyh());
                        }

                        /*String valueByKey = ExcelVerifyUtil.getValueByKey(sysUserExp.getYglx(), dictModels);
                        if (StringUtils.isNotBlank(valueByKey))
                            save.setYglx(valueByKey);

                        String valueByKey1 = ExcelVerifyUtil.getValueByKey(sysUserExp.getYgzt(), ygzts);
                        if (StringUtils.isNotBlank(valueByKey1))
                            save.setYglx(valueByKey1);*/

                        String valueByKey2 = ExcelVerifyUtil.getValueByKey(sysUserExp.getRoles(), roles);
                        if (StringUtils.isNotBlank(valueByKey2)){
                            SysUserRole sysUserRole = new SysUserRole();
                            sysUserRole.setUserId(id);
                            sysUserRole.setRoleId(valueByKey2);
                            sysUserRoleService.save(sysUserRole);
                        }

                        String valueByKey3 = ExcelVerifyUtil.getValueByKey(sysUserExp.getOrgCode(), zzbzs);
                        if (StringUtils.isNotBlank(valueByKey3)){
                            save.setOrgCode(valueByKey3);
                            SysUserDepart sysUserDepart = new SysUserDepart();
                            sysUserDepart.setUserId(id);
                            sysUserDepart.setDepId(valueByKey3);
                            sysUserDepartService.save(sysUserDepart);
                        }

                        save.setCreatetime(new Date());//设置创建时间
                        String salt = oConvertUtils.randomGen(8);
                        save.setSalt(salt);
                        save.setCreateuser(getUsername());
                        save.setStatus("1");
                        save.setDelFlag(0);
                        if (StringUtils.isNotBlank(sysUserExp.getPassword())){
                            String passwordEncode = MD5Util.MD5(sysUserExp.getPassword()).toUpperCase();//PasswordUtil.encrypt(user.getUsername(), user.getPassword(), salt);
                            save.setPassword(passwordEncode);
                        }else {
                            String passwordEncode = MD5Util.MD5(DictConstant.PASSWORD_DEFAULT).toUpperCase();//PasswordUtil.encrypt(user.getUsername(), user.getPassword(), salt);
                            save.setPassword(passwordEncode);
                        }

                        sysUsers.add(save);
                    }

                    sysBasUserService.saveBatch(sysUsers);
                }
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();
                return Result.ok("文件导入完成！", obj);
            } catch (Exception e) {
                log.error(e.getMessage(),e);
                return Result.error("文件导入失败:"+e.getMessage());
            } finally {
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入失败！");
    }


    /**
     * idap-clent-pad 修改密码
     *
     * */
    @RequestMapping("/resetPassword")
    public Result<?> resetPassword(@RequestBody UpdatePassWordVO updatePassWordVO){
        System.out.println(updatePassWordVO);
        String userName = RSAEncryptUtil.desEncrypt(updatePassWordVO.getUsername());
        String s = RSAEncryptUtil.desEncrypt(updatePassWordVO.getOldPasswordEncrypt()).toUpperCase();
        String s2 = RSAEncryptUtil.desEncrypt(updatePassWordVO.getConfirmPasswordEncrypt()).toUpperCase();
        LambdaQueryWrapper<SysBasUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysBasUser::getUsername,userName);
//        lambdaQueryWrapper.eq(SysBasUser::getDelFlag,0);
        List<SysBasUser> list = sysBasUserService.list(lambdaQueryWrapper);
        if (CollUtil.isNotEmpty(list)){
            SysBasUser sysBasUser = list.get(0);
            if (s.equals(sysBasUser.getPassword())){
                sysBasUser.setPassword(s2);
                sysBasUserService.updatePassword(sysBasUser.getUserid(),s2);
                return Result.ok();
            } else {
                return Result.error("修改失败，旧密码不正确");
            }
        } else {
            return Result.error("修改失败，未找到对应的用户信息");
        }
    }


    @RequestMapping("/getByOrgCode")
    public Result<?> getByOrgCode(){
        String orgCode = getLoginUser().getOrgCode();
        if (StringUtils.isNotBlank(orgCode)){
            LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(SysUser::getOrgCode,orgCode);
            List<SysUser> list = sysUserService.list(lambdaQueryWrapper);
            return Result.ok(list);
        }
        return Result.ok(sysUserService.list(null));
    }

    /**
     * 天意-精准营销-支行陪访人
     * @return
     */
    @RequestMapping("/getByOrgCodeJzyx")
    public Result<?> getByOrgCodeJzyx() {
        String orgCode = getLoginUser().getOrgCode();
        if (StringUtils.isNotBlank(orgCode)) {
            //获取用户权限机构及分理处
            List<HrBasOrganization> hrBasOrganizationList=hrBasOrganizationService.queryZzxxTreeByZzbz(orgCode);
            if (CollUtil.isNotEmpty(hrBasOrganizationList)) {
                LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.inSql(SysUser::getWorkNo, "select yggh from (select gwbz,yggh,row_number() over(partition by yggh order by rgrq desc) pk from hr_bas_staff_post where rgrq<=trunc(sysdate,'DD') and (lgrq is null or lgrq>=trunc(sysdate,'DD')))where pk=1 and gwbz <>'111'");
                lambdaQueryWrapper.in(SysUser::getOrgCode,hrBasOrganizationList.stream().map(HrBasOrganization::getZzbz).collect(Collectors.toList()));
                lambdaQueryWrapper.notLike(SysUser::getRealname,"虚拟");
                lambdaQueryWrapper.notLike(SysUser::getRealname,"托管");
                List<SysUser> list = sysUserService.list(lambdaQueryWrapper);
                return Result.ok(list);
            }
        }
        return Result.ok(sysUserService.list(null));
    }

    /**
     * 天意-同步入岗管理的zzbz到用户的组织权限
     *
     * @return
     */
    @PostMapping("/syncGwxxZzbz")
    public Result<?> syncGwxxZzbz() {
        //获取所有用户列表
        List<SysBasUser> sysBasUserList = sysBasUserService.list();
        //获取所有最新入岗信息（离岗日期不为空）
        QueryWrapper<HrBasStaffPost> queryWrapper = new QueryWrapper<HrBasStaffPost>();
        queryWrapper.isNull("lgrq");
        List<HrBasStaffPost> hrBasStaffPostList = hrBasStaffPostService.list(queryWrapper);
        try {
            sysBasUserList.forEach(e -> {
                if(StringUtils.isBlank(e.getTellid())){
                    return;
                }
                List<HrBasStaffPost> hrBasStaffPost1 = hrBasStaffPostList.stream().filter(hr -> e.getTellid().equals(hr.getYggh())).collect(Collectors.toList());
                //如果没有入岗信息或者用户的组织权限和入岗信息的zzbz相同就不用同步
                if (CollUtil.isEmpty(hrBasStaffPost1) || hrBasStaffPost1.size() != 1 || e.getJgdm().equals(hrBasStaffPost1.get(0).getYggh())) {
                    return;
                }
                e.setJgdm(hrBasStaffPost1.get(0).getZzbz());
                e.setUpdatetime(new Date());
                //更新用户表jgdm
                UpdateWrapper<SysBasUser> userUpdateWrapper = new UpdateWrapper<>();
                userUpdateWrapper.eq("userid", e.getUserid());
                sysBasUserService.update(e, userUpdateWrapper);
                //同步权限表sys_user_depart
                SysUser user = sysUserService.getById(e.getUserid());
                user.setUpdateTime(new Date());
                user.setDepartIds(hrBasStaffPost1.get(0).getZzbz());
                sysUserService.editUserWithDepart(user, hrBasStaffPost1.get(0).getZzbz());
            });
            return Result.ok("同步成功!");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("操作失败");
        }
    }

    @RequestMapping("/queryByCode")
    public Result<?> queryByCode(String type){
        String roleId = null;
        if ("1".equals(type)){
            Object o1 = redisUtil.get("fxjlid");
            if (o1 != null){
                roleId = o1.toString();
            }else {
                LambdaQueryWrapper<SysRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(SysRole::getRoleName,"风险经理");
                SysRole one = sysRoleService.getOne(lambdaQueryWrapper);
                if (one != null && StringUtils.isNotBlank(one.getId())){
                    redisUtil.set("fxjlid",one.getId());
                    roleId = one.getId();
                }
            }
        }
        if ("2".equals(type)){
            Object o1 = redisUtil.get("zhhzid");
            if (o1 != null){
                roleId = o1.toString();
            }else {
                LambdaQueryWrapper<SysRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(SysRole::getRoleName,"支行行长");
                SysRole one = sysRoleService.getOne(lambdaQueryWrapper);
                if (one != null && StringUtils.isNotBlank(one.getId())){
                    redisUtil.set("zhhzid",one.getId());
                    roleId = one.getId();
                }
            }
        }

        if (StringUtils.isNotBlank(roleId)){
            LambdaQueryWrapper<SysUser> sysUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
            sysUserLambdaQueryWrapper.eq(SysUser::getOrgCode,getLoginUser().getOrgCode());
            sysUserLambdaQueryWrapper.like(SysUser::getRoles,roleId);
            List<SysUser> list = sysUserService.list(sysUserLambdaQueryWrapper);

            if (CollUtil.isNotEmpty(list)){
                String s = "";
                for (int i = 0; i < list.size(); i++) {
                    SysUser sysUser = list.get(i);
                    if (i == list.size() - 1){
                        s += sysUser.getWorkNo()+" - " + sysUser.getRealname();
                    }else {
                        s += sysUser.getWorkNo()+" - " + sysUser.getRealname() + " , ";
                    }
                }
                return Result.ok(s);
            }
        }
        return Result.ok();
    }
}

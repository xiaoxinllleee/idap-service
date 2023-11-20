package org.cmms.modules.system.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.constant.CacheConstant;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.util.JwtUtil;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
import org.cmms.modules.system.entity.AppHrBasOrganization;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysUserDepart;
import org.cmms.modules.system.model.DepartIdHrModel;
import org.cmms.modules.system.model.HrBasOrganizationTreeModel;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;

import org.cmms.modules.system.service.ISysUserDepartService;
import org.cmms.modules.system.verify.HrbasOrganizationImportVerify;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;

/**
 * @Description: 组织机构管理
 * @Author: cmms
 * @Date: 2019-09-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "组织机构管理")
@RestController
@RequestMapping("/sys/hrBasOrganization")
public class HrBasOrganizationController extends JeecgController<HrBasOrganization, IHrBasOrganizationService> {
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;

    @Autowired
    private IVhrbasstaffpostService vhrbasstaffpostService;
    @Autowired
    private HrbasOrganizationImportVerify importVerify;
    @Autowired
    private ISysUserDepartService sysUserDepartService;

    @RequestMapping(value = "/queryTreeList", method = RequestMethod.GET)
    public Result<List<HrBasOrganizationTreeModel>> queryTreeList(@RequestParam(name = "ywjgQuery", required = false, defaultValue = "2") String ywjgQuery) {
        Result<List<HrBasOrganizationTreeModel>> result = new Result<>();
        try {
            // 从内存中读取
//			List<SysDepartTreeModel> list =FindsDepartsChildrenUtil.getSysDepartTreeList();
//			if (CollectionUtils.isEmpty(list)) {
//				list = hrBasOrganizationService.queryTreeList();
//			}
            List<HrBasOrganizationTreeModel> list = hrBasOrganizationService.queryTreeList(ywjgQuery);
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@list==" + list);
            result.setResult(list);
            result.setCode(200);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 分页列表查询
     *
     * @param hrBasOrganization
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "组织机构管理-分页列表查询")
    @ApiOperation(value = "组织机构管理-分页列表查询", notes = "组织机构管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<HrBasOrganization>> queryPageList(HrBasOrganization hrBasOrganization,
                                                          @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                          HttpServletRequest req) {
        Result<IPage<HrBasOrganization>> result = new Result<IPage<HrBasOrganization>>();
        QueryWrapper<HrBasOrganization> queryWrapper = QueryGenerator.initQueryWrapper(hrBasOrganization, req.getParameterMap());
        queryWrapper.last(" order by to_number(pxxh)");
        Page<HrBasOrganization> page = new Page<HrBasOrganization>(pageNo, pageSize);
        IPage<HrBasOrganization> pageList = hrBasOrganizationService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    @GetMapping(value = "/info")
    public Result<HrBasOrganization> info() {
        Result<HrBasOrganization> result = new Result<HrBasOrganization>();
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        try {
            Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.selectByYggh(loginUser.getWorkNo());
            if (vhrbasstaffpost != null) {
                HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(vhrbasstaffpost.getZzbz());
                result.setResult(hrBasOrganization);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }

    @GetMapping(value = "/getAuthOrgList")
    public Result<List<HrBasOrganization>> getAuthOrgList() {
        Result<List<HrBasOrganization>> result = new Result<List<HrBasOrganization>>();
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        try {
            Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.selectByYggh(loginUser.getWorkNo());
            if (vhrbasstaffpost != null) {
                List<HrBasOrganization> organizationList = hrBasOrganizationService.queryAuthOrgList(vhrbasstaffpost.getZzbz());
                result.setResult(organizationList);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }

    /**
     * 添加
     *
     * @param hrBasOrganization
     * @return
     */
    @AutoLog(value = "组织机构管理-添加")
    @ApiOperation(value = "组织机构管理-添加", notes = "组织机构管理-添加")
    @PostMapping(value = "/add")
    public Result<HrBasOrganization> add(@RequestBody HrBasOrganization hrBasOrganization) {
        Result<HrBasOrganization> result = new Result<HrBasOrganization>();
        try {
            hrBasOrganizationService.save(hrBasOrganization);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }


    /**
     * 编辑
     *
     * @param hrBasOrganization
     * @return
     */
    @AutoLog(value = "组织机构管理-编辑")
    @ApiOperation(value = "组织机构管理-编辑", notes = "组织机构管理-编辑")
    @PutMapping(value = "/edit")
    public Result<HrBasOrganization> edit(@RequestBody HrBasOrganization hrBasOrganization) {
        Result<HrBasOrganization> result = new Result<HrBasOrganization>();
        HrBasOrganization hrBasOrganizationEntity = hrBasOrganizationService.getById(hrBasOrganization.getZzbz());
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@hrBasOrganizationEntity:" + hrBasOrganizationEntity);
        if (hrBasOrganizationEntity == null) {
            result.error500("未找到对应实体");
        } else {
            boolean ok = hrBasOrganizationService.updateById(hrBasOrganization);
            //TODO 返回false说明什么？
            if (ok) {
                result.success("修改成功!");
            }
        }

        return result;
    }


    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "组织机构管理-通过id删除")
    @ApiOperation(value = "组织机构管理-通过id删除", notes = "组织机构管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        try {
            hrBasOrganizationService.removeById(id);
        } catch (Exception e) {
            log.error("删除失败", e.getMessage());
            return Result.error("删除失败!");
        }
        return Result.ok("删除成功!");
    }


    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "组织机构管理-批量删除")
    @ApiOperation(value = "组织机构管理-批量删除", notes = "组织机构管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<HrBasOrganization> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<HrBasOrganization> result = new Result<HrBasOrganization>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.hrBasOrganizationService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "组织机构管理-通过id查询")
    @ApiOperation(value = "组织机构管理-通过id查询", notes = "组织机构管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<HrBasOrganization> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<HrBasOrganization> result = new Result<HrBasOrganization>();
        HrBasOrganization hrBasOrganization = hrBasOrganizationService.getById(id);
        if (hrBasOrganization == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(hrBasOrganization);
            result.setSuccess(true);
        }
        return result;
    }

    /**
     * 查询数据 添加或编辑页面对该方法发起请求,以树结构形式加载所有部门的名称,方便用户的操作
     *
     * @return
     */
    @RequestMapping(value = "/queryIdTree", method = RequestMethod.GET)
    public Result<List<DepartIdHrModel>> queryIdTree() {
//		Result<List<DepartIdModel>> result = new Result<List<DepartIdModel>>();
//		List<DepartIdModel> idList;
//		try {
//			idList = FindsDepartsChildrenUtil.wrapDepartIdModel();
//			if (idList != null && idList.size() > 0) {
//				result.setResult(idList);
//				result.setSuccess(true);
//			} else {
//				hrBasOrganizationService.queryTreeList();
//				idList = FindsDepartsChildrenUtil.wrapDepartIdModel();
//				result.setResult(idList);
//				result.setSuccess(true);
//			}
//			return result;
//		} catch (Exception e) {
//			log.error(e.getMessage(),e);
//			result.setSuccess(false);
//			return result;
//		}
        Result<List<DepartIdHrModel>> result = new Result<>();
        try {
            List<DepartIdHrModel> list = hrBasOrganizationService.queryDepartIdTreeList();
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@list1111==" + list);
            result.setResult(list);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * <p>
     * 部门搜索功能方法,根据关键字模糊搜索相关部门
     * </p>
     *
     * @param keyWord
     * @return
     */
    @RequestMapping(value = "/searchBy", method = RequestMethod.GET)
    public Result<List<HrBasOrganizationTreeModel>> searchBy(@RequestParam(name = "keyWord", required = true) String keyWord) {
        Result<List<HrBasOrganizationTreeModel>> result = new Result<List<HrBasOrganizationTreeModel>>();
        try {
            List<HrBasOrganizationTreeModel> treeList = this.hrBasOrganizationService.searhBy(keyWord);
            if (treeList.size() == 0 || treeList == null) {
                throw new Exception();
            }
            result.setSuccess(true);
            result.setResult(treeList);
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
            result.setSuccess(false);
            result.setMessage("查询失败或没有您想要的任何数据!");
            return result;
        }
    }


    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        // Step.1 组装查询条件
        QueryWrapper<HrBasOrganization> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                HrBasOrganization hrBasOrganization = JSON.parseObject(deString, HrBasOrganization.class);
                queryWrapper = QueryGenerator.initQueryWrapper(hrBasOrganization, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<HrBasOrganization> pageList = hrBasOrganizationService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "组织机构管理列表");
        mv.addObject(NormalExcelConstants.CLASS, HrBasOrganization.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("组织机构管理列表数据", "导出人:" + getLoginUser().getRealname(), "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    /**
     * 导出模板excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        return super.exportTemplateXls(HrBasOrganization.class, "组织机构管理导入模板");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        return super.importExcelByTemplate(jsonObject, request, response, HrBasOrganization.class, importVerify);
    }

    /**
     * 通过yggh查询
     *
     * @param yggh
     * @return
     */
    @AutoLog(value = "员工组织信息-通过yggh查询")
    @ApiOperation(value = "员工组织信息-通过yggh查询", notes = "员工组织信息-通过yggh查询")
    @GetMapping(value = "/queryZzxxTreeByYggh")
    public Result<?> queryZzxxTreeByYggh(@RequestParam(name = "yggh", required = false) String yggh) {
        if (StringUtils.isEmpty(yggh)) {
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            yggh = sysUser.getWorkNo();
        }
        Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.selectByYggh(yggh);
        List<HrBasOrganization> list = hrBasOrganizationService.queryZzxxTreeByZzbz(vhrbasstaffpost.getZzbz());
        return Result.ok(list);
    }

    @RequestMapping("/getAppHrBasOrganizationList")
    public Result<?> getAppHrBasOrganizationList(String type, String sfjgdm) {
        List<AppHrBasOrganization> appHrBasOrganizationList = service.getAppHrBasOrganizationList(type, sfjgdm);
        return Result.ok(appHrBasOrganizationList);
    }

    /**
     * 获取登陆用户的权限组织机构树
     *
     * @return
     */
    @GetMapping("/getTreeData")
    public Result<?> getTreeData() {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        List<HrBasOrganization> organizationList = service.getTreeData(sysUser.getId());
        return Result.ok(organizationList);
    }

    @GetMapping("/queryZzbzZh")
    public Result<?> queryZzbzZh() {
        return Result.ok(service.queryZzbzZh());
    }

    @GetMapping("/queryZzbzZhByUserId")
    public Result<?> queryZzbzZhByUserId() {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        QueryWrapper<SysUserDepart> queryWrapper = new QueryWrapper<SysUserDepart>();
        queryWrapper.eq("user_id", sysUser.getId());
        List<String> depIdList = sysUserDepartService.list(queryWrapper).stream().map(SysUserDepart::getDepId).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(depIdList)&&!depIdList.contains("1")){
            return Result.ok(service.queryZzbzZh().stream().filter(item->depIdList.contains(item.getZzbz())));
        }
        return Result.ok(service.queryZzbzZh());
    }
}

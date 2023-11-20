package org.cmms.modules.hr.yggl.ygrggl.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.*;
import org.cmms.common.utils.SnowFlakeUtil;
import org.cmms.modules.base.service.DictService;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPostVo;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.hr.yggl.ygrggl.verify.HrBasStaffPostImportVerify;
import org.cmms.modules.hr.yggl.ygrggl.verify.HrBasStaffPostTransferImportVerify;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.hr.zzgl.zzgwgl.service.IHrBasOrganPostService;
import org.cmms.modules.system.entity.*;
import org.cmms.modules.system.service.*;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 员工入岗管理
 * @Author: jeecg-boot
 * @Date: 2020-11-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "员工入岗管理")
@RestController
@RequestMapping("/ygrggl/hrBasStaffPost")
public class HrBasStaffPostController extends JeecgController<HrBasStaffPost, IHrBasStaffPostService> {
    @Autowired
    private IHrBasStaffPostService hrBasStaffPostService;
    @Autowired
    IHrBasStaffService hrBasStaffService;
    @Autowired
    IHrBasOrganPostService hrBasOrganPostService;
    @Autowired
    HrBasStaffPostImportVerify importVerify;
    @Autowired
    HrBasStaffPostTransferImportVerify transferImportVerify;
    @Autowired
    private ISysUserRoleService sysUserRoleService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysBasUserService sysBasUserService;
    @Autowired
    private ISysUserDepartService sysUserDepartService;
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;

    /**
     * 分页列表查询
     *
     * @param hrBasStaffPost
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "员工入岗管理-分页列表查询")
    @ApiOperation(value = "员工入岗管理-分页列表查询", notes = "员工入岗管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(HrBasStaffPost hrBasStaffPost,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   String ygxm,
                                   String yf,
                                   HttpServletRequest req) {
        List<HrBasStaff> staffList = new ArrayList<>();
        if (StringUtils.isNotBlank(ygxm) && StringUtils.isBlank(hrBasStaffPost.getYggh())) {
            QueryWrapper ygxmQueryWrapper = new QueryWrapper();
            ygxmQueryWrapper.like("ygxm", ygxm);
            staffList = hrBasStaffService.list(ygxmQueryWrapper);
            if (staffList.isEmpty()) {
                return Result.error("无数据！");
            }
        }
        QueryWrapper<HrBasStaffPost> queryWrapper = QueryGenerator.initQueryWrapper(hrBasStaffPost, req.getParameterMap());
        if (CollUtil.isNotEmpty(staffList)) {
            List<String> ygghList = staffList.stream().map(HrBasStaff::getYggh).collect(Collectors.toList());
            queryWrapper.in("yggh", ygghList);
        }
        if (StringUtils.isNotBlank(yf)) {
            System.out.println(yf.substring(0, 7));
            yf = yf.substring(1, 8).concat("-01");
            System.out.println(yf);
            Date beginOfMouthByString = DateUtils.getBeginOfMouthByString(yf);
            DateTime dateTime = DateUtil.endOfMonth(beginOfMouthByString);
            System.out.println(beginOfMouthByString);
            System.out.println(dateTime);
            queryWrapper.le("rgrq", dateTime);
            queryWrapper.apply("  (lgrq is null or lgrq >= {0} )", beginOfMouthByString);
        }

        Page<HrBasStaffPost> page = new Page<HrBasStaffPost>(pageNo, pageSize);
        queryWrapper.orderByAsc("zzbz");
        queryWrapper.orderByAsc("yggh");
        queryWrapper.orderByDesc("rgrq");
        IPage<HrBasStaffPost> pageList = hrBasStaffPostService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 查询当前在岗的员工信息
     *
     * @param hrBasStaffPost
     * @param req
     * @return
     */
    @AutoLog(value = "员工入岗管理-列表查询")
    @ApiOperation(value = "员工入岗管理-列表查询", notes = "员工入岗管理-列表查询")
    @GetMapping(value = "/queryList")
    public Result<?> queryList(HrBasStaffPost hrBasStaffPost,
                                   HttpServletRequest req) {
        QueryWrapper<HrBasStaffPost> queryWrapper = QueryGenerator.initQueryWrapper(hrBasStaffPost, req.getParameterMap());
        queryWrapper.le("rgrq", new Date());
        queryWrapper.apply("  (lgrq is null or lgrq >= {0} )", new Date());

        List<HrBasStaffPost> list = hrBasStaffPostService.list(queryWrapper);
        return Result.ok(list);
    }

    /**
     * 添加
     *
     * @param hrBasStaffPost
     * @return
     */
    @AutoLog(value = "员工入岗管理-添加")
    @ApiOperation(value = "员工入岗管理-添加", notes = "员工入岗管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody HrBasStaffPost hrBasStaffPost) {
        HrBasStaffPost check = new HrBasStaffPost();
        BeanUtil.copyPropertiesIgnoreNull(hrBasStaffPost, check);
        if (check.getLgrq() == null) {
            try {
                check.setLgrq(DateUtils.parseDate("20991231", "yyyyMMdd"));
            } catch (ParseException ex) {

            }
        }
        boolean b = hrBasStaffPostService.ifExistByYgghAndRgrqAndLgrq(check);
        if (hrBasStaffPost.getRglx() != 1) {
            b = true;
        }
        if (b) {
            //去除组织岗位的限制
            //Boolean aBoolean = hrBasOrganPostService.ifExistByGwbzAndZzbz(hrBasStaffPost.getGwbz(), hrBasStaffPost.getZzbz());
            LambdaQueryWrapper<HrBasStaff> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(HrBasStaff::getYggh, hrBasStaffPost.getYggh());
            HrBasStaff one = hrBasStaffService.getOne(lambdaQueryWrapper);
            if (one != null) {
                if (StringUtils.isNotBlank(one.getGyh()))
                    hrBasStaffPost.setGyh(one.getGyh());
                if (StringUtils.isNotBlank(one.getKhjlbh()))
                    hrBasStaffPost.setKhjlbz(one.getKhjlbh());
            }
            hrBasStaffPost.setId(service.getId());
            boolean save = hrBasStaffPostService.save(hrBasStaffPost);
            if (save) {
                return Result.ok("添加成功！");
            } else {
                return Result.ok("添加失败！");
            }
        } else {
            return Result.error("已存在此日期区间的有效入岗信息，不能重复入岗！");
        }
    }

    /**
     * 编辑
     *
     * @param hrBasStaffPost
     * @return
     */
    @AutoLog(value = "员工入岗管理-编辑")
    @ApiOperation(value = "员工入岗管理-编辑", notes = "员工入岗管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody HrBasStaffPost hrBasStaffPost) {
        HrBasStaffPost check = new HrBasStaffPost();
        BeanUtil.copyPropertiesIgnoreNull(hrBasStaffPost, check);
        if (check.getLgrq() == null) {
            try {
                check.setLgrq(DateUtils.parseDate("20991231", "yyyyMMdd"));
            } catch (ParseException ex) {

            }
        }
        boolean b = hrBasStaffPostService.ifExistByYgghAndRgrqAndLgrq(check);
        if (hrBasStaffPost.getRglx() != 1) {
            b = true;
        }
        if (b) {

            hrBasStaffPostService.updateById(hrBasStaffPost);
        } else {
            return Result.error("已存在此日期区间的有效入岗信息，请确认！");
        }
        return Result.ok("编辑成功!");
    }

    @AutoLog(value = "员工入岗管理-编辑")
    @ApiOperation(value = "员工入岗管理-编辑", notes = "员工入岗管理-编辑")
    @PutMapping(value = "/reedit")
    public Result<?> reedit(@RequestBody HrBasStaffPost hrBasStaffPost) {
//        Boolean aBoolean = hrBasOrganPostService.ifExistByGwbzAndZzbz(hrBasStaffPost.getGwbz(), hrBasStaffPost.getZzbz());
        //先更新时间
        Date rgrq = hrBasStaffPost.getRgrq();
        DateTime dateTime = DateUtil.offsetDay(rgrq, -1);

        HrBasStaffPost old = hrBasStaffPostService.getById(hrBasStaffPost.getId());
        Date rgrq1 = old.getRgrq();
        if (DateUtil.compare(rgrq, rgrq1) < 0)
            return Result.error("调入日期必须大于入岗日期");

        old.setLgrq(dateTime);
        boolean b = hrBasStaffPostService.updateById(old);
        old.setZzbz(hrBasStaffPost.getZzbz());
        old.setGwbz(hrBasStaffPost.getGwbz());
        old.setRgrq(hrBasStaffPost.getRgrq());
        old.setRglx(hrBasStaffPost.getRglx());
        old.setSfcykh(hrBasStaffPost.getSfcykh());
        old.setId(service.getId());
        old.setLgrq(null);
        if (StringUtils.isNotBlank(hrBasStaffPost.getBz())) {
            old.setBz(hrBasStaffPost.getBz());
        } else {
            old.setBz(null);
        }
        boolean save = hrBasStaffPostService.save(old);
        return Result.ok("编辑成功!");
    }

    @AutoLog(value = "员工入岗管理-编辑")
    @ApiOperation(value = "员工入岗管理-编辑", notes = "员工入岗管理-编辑")
    @PutMapping(value = "/lgedit")
    public Result<?> lgedit(@RequestBody HrBasStaffPost hrBasStaffPost) {
        if (hrBasStaffPost.getLgrq() == null)
            return Result.error("离岗日期不能为空");
        Date rgrq = hrBasStaffPost.getRgrq();
        Date lgrq = hrBasStaffPost.getLgrq();
        if (DateUtil.compare(rgrq, lgrq) > 0)
            return Result.error("离岗日期不能小于入岗日期");
        HrBasStaffPost old = new HrBasStaffPost();
        old.setId(hrBasStaffPost.getId());
        old.setLgrq(lgrq);
        if (StringUtils.isNotBlank(hrBasStaffPost.getBz()))
            old.setBz(hrBasStaffPost.getBz());
        boolean b = service.updateById(old);
        if (b)
            return Result.ok("编辑成功!");
        return Result.error("编辑失败!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "员工入岗管理-通过id删除")
    @ApiOperation(value = "员工入岗管理-通过id删除", notes = "员工入岗管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        hrBasStaffPostService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "员工入岗管理-批量删除")
    @ApiOperation(value = "员工入岗管理-批量删除", notes = "员工入岗管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.hrBasStaffPostService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "员工入岗管理-通过id查询")
    @ApiOperation(value = "员工入岗管理-通过id查询", notes = "员工入岗管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        HrBasStaffPost hrBasStaffPost = hrBasStaffPostService.getById(id);
        return Result.ok(hrBasStaffPost);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param hrBasStaffPost
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HrBasStaffPost hrBasStaffPost) {
        return super.exportXls(request, hrBasStaffPost, HrBasStaffPost.class, "员工入岗管理");
    }

    /**
     * 导出模板excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        return super.exportTemplateXls(HrBasStaffPost.class, "员工岗位信息导入模板");
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
        return super.importExcelByTemplate(jsonObject, request, response, HrBasStaffPost.class, importVerify);
    }


    @RequestMapping("/dqzg")
    public Result<?> dqzg() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("yggh", getUsername());
        queryWrapper.isNull("lgrq");
        queryWrapper.orderByDesc("rgrq");
        Page page = service.page(new Page<>(), queryWrapper);
        return Result.ok(page);
    }

    /**
     * app任职历史
     *
     * @param hrBasStaffPost
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "员工入岗管理-分页列表查询")
    @ApiOperation(value = "员工入岗管理-分页列表查询", notes = "员工入岗管理-分页列表查询")
    @GetMapping(value = "/appList")
    public Result<?> queryPageList(HrBasStaffPost hrBasStaffPost,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<HrBasStaffPost> queryWrapper = new QueryWrapper<HrBasStaffPost>();
        System.out.println(getWorkNo());
        queryWrapper.eq("yggh", getWorkNo());
        queryWrapper.orderByDesc("rgrq");
        Page<HrBasStaffPost> page = new Page<HrBasStaffPost>(pageNo, pageSize);
        IPage<HrBasStaffPost> pageList = hrBasStaffPostService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 通过excel导入员工入岗数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel2", method = RequestMethod.POST)
    public Result<?> importExcel2(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String filePaths = jsonObject.getString("filePaths");
        if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
            return Result.error("请先上传文件！");
        }
        String[] filePathList = filePaths.split(",");
        JSONObject obj = new JSONObject();
        for (String filePath : filePathList) {
            String baseFilePath = uploadpath + File.separator + filePath;
            File file = new File(baseFilePath);
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            params.setHeadRows(1);
            params.setNeedSave(false);
            params.setVerifyHanlder(transferImportVerify);
            FileOutputStream fos = null;
            try {
                ExcelImportResult<HrBasStaffPostVo> importResult = ExcelImportUtil.importExcelVerify(file, HrBasStaffPostVo.class, params);
                List<HrBasStaffPostVo> list = importResult.getList();
                for (HrBasStaffPostVo ygddxx : list) {
                    HrBasStaffPost oldHrBasStaffPost = null;
                    QueryWrapper<HrBasStaffPost> queryWrapper2 = new QueryWrapper<>();
                    //queryWrapper2.eq("zzbz", ygddxx.getYsszhbs()).eq("yggh", ygddxx.getYggh()).eq("gwbz", Integer.valueOf(ygddxx.getYgwbs()));
                    queryWrapper2.eq("zzbz",ygddxx.getYsszhbs());
                    queryWrapper2.eq("yggh",ygddxx.getYggh());
                    queryWrapper2.eq("gwbz",ygddxx.getYgwbs());

                    List<HrBasStaffPost> oldHrBasStaffPostList = hrBasStaffPostService.list(queryWrapper2);
                    if (CollUtil.isNotEmpty(oldHrBasStaffPostList)) {
                        //如果有离岗日期为空的，就取这个；没有就取离岗日期最新的
                        List<HrBasStaffPost> oldHrBasStaffPostList1 = oldHrBasStaffPostList.stream().filter(item -> item.getLgrq() == null).collect(Collectors.toList());
                        if (CollUtil.isEmpty(oldHrBasStaffPostList1)) {
                            oldHrBasStaffPost = oldHrBasStaffPostList.stream()
                                    .sorted(Comparator.comparing(HrBasStaffPost::getLgrq).reversed())
                                    .distinct()
                                    .limit(1)
                                    .collect(Collectors.toList()).get(0);
                        } else {
                            oldHrBasStaffPost = oldHrBasStaffPostList1.get(0);
                        }
                    }

                    QueryWrapper<SysBasUser> queryWrapper3 = new QueryWrapper<>();
                    queryWrapper3.eq("tellid", ygddxx.getYggh());
                    SysBasUser sysBasUser = sysBasUserService.getOne(queryWrapper3);

                    QueryWrapper<SysUser> queryWrapper4 = new QueryWrapper<>();
                    queryWrapper4.eq("work_no", ygddxx.getYggh());
                    SysUser sysUser1 = sysUserService.getOne(queryWrapper4);

                    //离岗：更新原岗位记录信息，更新离职时间
                    if (oldHrBasStaffPost != null && ygddxx.getLgrq() != null && ygddxx.getRgrq() == null) {
                        oldHrBasStaffPost.setLgrq(ygddxx.getLgrq());
                        hrBasStaffPostService.updateById(oldHrBasStaffPost);
                    }
                    //创建新的一条员工入岗信息
                    QueryWrapper<HrBasStaff> hrBasStaffQueryWrapper = new QueryWrapper<>();
                    hrBasStaffQueryWrapper.eq("yggh", ygddxx.getYggh());
                    HrBasStaff hrBasStaff = hrBasStaffService.getOne(hrBasStaffQueryWrapper);
                    //入岗：如果有岗位信息调动就创建岗位调动信息
                    if (StringUtils.isNotBlank(ygddxx.getDrzz()) && ygddxx.getRgrq() != null && StringUtils.isNotBlank(ygddxx.getXgwbs())
                            && hrBasStaff != null) {
                        HrBasStaffPost hrBasStaffPost2 = new HrBasStaffPost();
                        hrBasStaffPost2.setZzbz(ygddxx.getDrzz());
                        hrBasStaffPost2.setYggh(ygddxx.getYggh());
                        hrBasStaffPost2.setGyh(ygddxx.getGyh() == null ? oldHrBasStaffPost.getGyh() : ygddxx.getGyh());
                        hrBasStaffPost2.setId(hrBasStaffPostService.getId());
                        hrBasStaffPost2.setRgrq(ygddxx.getRgrq());
                        hrBasStaffPost2.setGwbz(Integer.valueOf(ygddxx.getXgwbs()));
                        hrBasStaffPost2.setKhjlbz(hrBasStaff.getKhjlbh() == null ? "" : hrBasStaff.getKhjlbh());
                        hrBasStaffPost2.setRglx(oldHrBasStaffPost.getRglx());
                        hrBasStaffPost2.setSfltx(oldHrBasStaffPost.getSfltx());
                        hrBasStaffPost2.setSfcykh(oldHrBasStaffPost.getSfcykh());
                        hrBasStaffPostService.save(hrBasStaffPost2);
                        DateTime dateTime = DateUtil.offsetDay(ygddxx.getRgrq(), -1);
                        oldHrBasStaffPost.setLgrq(dateTime);
                        hrBasStaffPostService.updateById(oldHrBasStaffPost);
                    }

                    //更新角色
                    if (ygddxx.getXjsfp() != null && sysBasUser != null) {
                        QueryWrapper<SysUserRole> sysRoleQueryWrapper = new QueryWrapper<>();
                        sysRoleQueryWrapper.eq("user_id", sysBasUser.getUserid());
                        sysUserRoleService.remove(sysRoleQueryWrapper);
                        sysUserRoleService.save(new SysUserRole().setId(UUIDGenerator.generate()).setUserId(sysBasUser.getUserid()).setRoleId(ygddxx.getXjsfp()));
                    }
                    //更新密码
                    if (StringUtils.isNotBlank(ygddxx.getXdlmm()) && sysBasUser != null){
                        sysBasUser.setPassword(ygddxx.getXdlmm() == null ? sysBasUser.getPassword() : MD5Util.MD5(ygddxx.getXdlmm()).toUpperCase());
                    }
                    //更新权限组织
                    if (ygddxx.getXqxzz() != null && sysUser1 != null) {
                        sysUserService.editUserWithDepart(sysUser1, ygddxx.getXqxzz());
                        sysBasUser.setJgdm(ygddxx.getXqxzz());
                        sysBasUser.setOrgCode(ygddxx.getXqxzz());
                    }
                    if (sysBasUser != null) {
                        sysBasUserService.update(sysBasUser, queryWrapper3);
                    }
                }
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();
                return Result.ok("文件导入完成！", list);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入失败！");
    }

    /**
     * 批量导入员工调岗模板excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportTemplateXls2")
    public ModelAndView exportTemplateXls2(HttpServletRequest request, HttpServletResponse response) {
        return super.exportTemplateXls(HrBasStaffPostVo.class, "员工岗位调动信息导入模板");
    }

    /**
     * 导出调动人员信息excel
     *
     * @param request
     * @param hrBasStaffPost
     */
    @RequestMapping(value = "/exportXls2")
    public ModelAndView exportXls2(HttpServletRequest request, HrBasStaffPost hrBasStaffPost) {
        System.out.println(hrBasStaffPost.toString());
        QueryWrapper<HrBasStaffPost> queryWrapper = QueryGenerator.initQueryWrapper(hrBasStaffPost, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String selections = request.getParameter("selections");
        String rowKey = request.getParameter("rowKey");

        if (oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            if (oConvertUtils.isNotEmpty(rowKey)) {
                queryWrapper.in(rowKey, selectionList);
            } else {
                queryWrapper.in("ID", selectionList);
            }
        }
//        List<HrBasStaffPost> hrBasStaffPostList = service.list(queryWrapper);
        Map<String, List<HrBasStaffPost>> hrBasStaffPostMap = service.list(queryWrapper).stream().collect(Collectors.groupingBy(HrBasStaffPost::getYggh));
        List<HrBasStaffPostVo> exportList = new ArrayList<>();
        hrBasStaffPostMap.forEach((k, v) -> {
            //获取员工入岗信息
            HrBasStaffPost hrBasStaffPost1 = null;
            if (v.size() == 1) {
                hrBasStaffPost1 = v.get(0);
            } else {
                //员工入岗信息为多条时，首先取离岗日期为空的，若没有,择取离岗日期最新的
                List<HrBasStaffPost> hrBasStaffPostList = v.stream().filter(a -> a.getLgrq() == null).collect(Collectors.toList());
                if (CollUtil.isNotEmpty(hrBasStaffPostList)) {
                    hrBasStaffPost1 = hrBasStaffPostList.get(0);
                } else {
                    hrBasStaffPost1 = v.stream()
                            .sorted(Comparator.comparing(HrBasStaffPost::getLgrq).reversed())
                            .distinct()
                            .limit(1)
                            .collect(Collectors.toList()).get(0);
                }
            }

            HrBasStaffPostVo hrBasStaffPostVo = new HrBasStaffPostVo();
            //获取员工角色和权限
            QueryWrapper<SysBasUser> queryWrapper3 = new QueryWrapper<>();
            queryWrapper3.eq("tellid", k);
            SysBasUser sysBasUser = sysBasUserService.getOne(queryWrapper3);

            if (sysBasUser!=null){
                QueryWrapper<SysUserRole> sysRoleQueryWrapper = new QueryWrapper<>();
                sysRoleQueryWrapper.eq("user_id", sysBasUser.getUserid());
                SysUserRole sysUserRole=sysUserRoleService.getOne(sysRoleQueryWrapper);

                QueryWrapper<SysUserDepart> sysDepartRoleQueryWrapper = new QueryWrapper<>();
                sysDepartRoleQueryWrapper.eq("user_id", sysBasUser.getUserid());
                SysUserDepart sysUserDepart=sysUserDepartService.getOne(sysDepartRoleQueryWrapper);

                hrBasStaffPostVo.setYqxzz(sysUserDepart==null ? null:sysUserDepart.getDepId());
                hrBasStaffPostVo.setYjsfp(sysUserRole==null? null:sysUserRole.getRoleId());
            }

            //获取员工姓名
            QueryWrapper<HrBasStaff> hrBasStaffQueryWrapper = new QueryWrapper<>();
            hrBasStaffQueryWrapper.eq("yggh", k);
            HrBasStaff hrBasStaff = hrBasStaffService.getOne(hrBasStaffQueryWrapper);

            //创建导出信息
            hrBasStaffPostVo.setYggh(hrBasStaffPost1.getYggh());
            hrBasStaffPostVo.setGyh(hrBasStaffPost1.getGyh());
            hrBasStaffPostVo.setYgxm(hrBasStaff.getYgxm() == null ? null : hrBasStaff.getYgxm());
            hrBasStaffPostVo.setYsszhbs(hrBasStaffPost1.getZzbz());
            hrBasStaffPostVo.setYgwbs(String.valueOf(hrBasStaffPost1.getGwbz()));
            exportList.add(hrBasStaffPostVo);

        });

        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "员工调动信息管理");
        mv.addObject(NormalExcelConstants.CLASS, HrBasStaffPostVo.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("员工调动信息管理" + "报表", null, "员工调动信息管理"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }



    /**
     * 通过员工工号查询部门机构信息
     *
     * @param yggh 员工工号
     * @return
     */
    @AutoLog(value = "组织机构管理-通过员工工号查询部门机构信息")
    @ApiOperation(value = "组织机构管理-通过员工工号查询部门机构信息", notes = "组织机构管理-通过员工工号查询部门机构信息")
    @GetMapping(value = "/queryJgdmByYggh")
    public Result<HrBasOrganization> queryByZzbz(@RequestParam(name = "yggh", required = true) String yggh) {
        Result<HrBasOrganization> result = new Result<HrBasOrganization>();
        QueryWrapper<HrBasStaffPost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("yggh",yggh);
        HrBasStaffPost hrBasStaffPost = hrBasStaffPostService.getOne(queryWrapper,false);
        if (hrBasStaffPost == null) {
            result.error500("未找到当前操作员入岗信息！");
        } else {
            QueryWrapper<HrBasOrganization> orgQueryWrapper = new QueryWrapper<>();
            orgQueryWrapper.eq("zzbz",hrBasStaffPost.getZzbz());
            HrBasOrganization hrBasOrganization = hrBasOrganizationService.getOne(orgQueryWrapper,false);
            if (hrBasOrganization == null) {
                result.error500("未找到当前操作员入岗部门机构信息！");
            } else {
                result.setResult(hrBasOrganization);
                result.setSuccess(true);
            }
        }
        return result;
    }
}

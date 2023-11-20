package org.cmms.modules.jylrhs.jylrsj.jylrhz.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.jylrhs.jylrsj.jylrhz.entity.JylrhsJylrhz;
import org.cmms.modules.jylrhs.jylrsj.jylrhz.service.IJylrhsJylrhzService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.entity.SysRole;
import org.cmms.modules.system.service.ISysRoleService;
import org.cmms.modules.util.EtlUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 经营利润核算（经营利润汇总）
 * @Author: jeecg-boot
 * @Date: 2023-06-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "经营利润核算（经营利润汇总）")
@RestController
@RequestMapping("/jylrhs/jylrsj/jylrhz")
public class JylrhsJylrhzController extends JeecgController<JylrhsJylrhz, IJylrhsJylrhzService> {
    @Autowired
    private IJylrhsJylrhzService jylrhsJylrhzService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;
    @Value("${liuyang.testsystem:false}")
    private String testsystem;
    @Autowired
    private ISysRoleService roleService;

    /**
     * 分页列表查询
     *
     * @param jylrhsJylrhz
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "经营利润核算（经营利润汇总）-分页列表查询")
    @ApiOperation(value = "经营利润核算（经营利润汇总）-分页列表查询", notes = "经营利润核算（经营利润汇总）-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(JylrhsJylrhz jylrhsJylrhz,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<JylrhsJylrhz> queryWrapper = QueryGenerator.initQueryWrapper(jylrhsJylrhz, req.getParameterMap());

        boolean admin = false;
        String oprRoleId = getLoginUser().getRoles();
        String[] roleIds = oprRoleId.split(",");
        QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();
        sysRoleQueryWrapper.in("id",roleIds);
        List<SysRole> roles = roleService.list(sysRoleQueryWrapper);
        if (roles != null) {
            for (SysRole role : roles) {
                // 管理员/总行系统管理员/财务主管
                if ("admin".equals(role.getRoleCode().toLowerCase()) ||
                        "zhxtgly".equals(role.getRoleCode().toLowerCase()) ||
                        "dms_cwzg".equals(role.getRoleCode().toLowerCase())) {
                    admin = true;
                }
            }
        }
        if (!admin) {
            // 非管理员角色，只允许查看已审核的数据
            queryWrapper.eq("oprationType","2");
        }

        IPage pageList = PageUtil.toPage(IJylrhsJylrhzService.class, jylrhsJylrhzService, pageNo, pageSize, queryWrapper, "fiscal_date", "jgdm");
        return Result.ok(pageList);
    }

    /**
     * 统计
     *
     * @return jsonObject 经营利润汇总信息
     */
    @AutoLog(value = "经营利润核算（经营利润汇总）-统计")
    @ApiOperation(value = "经营利润核算（经营利润汇总）-统计", notes = "经营利润核算（经营利润汇总）-统计")
    @RequestMapping(value = "/initData")
    public Result<?> initData(@RequestBody JylrhsJylrhz record) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String fiscal_date = sdf.format(record.getFiscalDate());
            log.info("当前统计数据日期："+fiscal_date);
            String operator = getLoginUser().getUsername();
            log.info("当前操作人员用户名："+operator);
            if ("true".equals(sfdsjpt)) {
                HashMap<String, String> params = new HashMap<>();
                params.put("fiscal_date",fiscal_date);
                params.put("operator",operator);
                String app_imp = "jylrhs_imp";
                String app_test = "jylrhs_test";
                if ("true".equalsIgnoreCase(testsystem)) {
                    //浏阳测试环境打开此配置
                    params.put("app",app_test);
                } else {
                    //浏阳生产环境打开此配置
                    params.put("app",app_imp);
                }
                params.put("etl_task", "kiss.domain.application.jylrhs.proc_jylrhs_zbhz");
                //boolean flag = EtlUtil.callEtl("jylrhs_common_init", params, 15);
                boolean flag = EtlUtil.callEtl("cdkyw_common_init", params, 15);
                log.info("经营利润核算-经营利润汇总-统计-是否成功？-"+flag);
                if (flag) {
                    return Result.ok("统计成功！");
                } else {
                    return Result.error("统计失败，请联系管理员处理！");
                }
            } else {
                log.info("未做Oracle数据统计！");
                return Result.ok();
            }
        } catch (Throwable e) {
            log.info("经营利润核算（经营利润汇总）-统计失败！"+e.getMessage());
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    /**
     * 批量审核
     *
     * @param jylrhsJylrhz
     * @return
     */
    @AutoLog(value = "经营利润核算（经营利润汇总）-批量审核")
    @ApiOperation(value = "经营利润核算（经营利润汇总）-批量审核", notes = "经营利润核算（经营利润汇总）-批量审核")
    @PutMapping(value = "/batchReviews")
    public Result<?> batchReviews(@RequestBody JylrhsJylrhz jylrhsJylrhz) {
        try {
            log.info("待审核数据日期："+jylrhsJylrhz.getFiscalDate());
            UpdateWrapper<JylrhsJylrhz> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("fiscal_date", jylrhsJylrhz.getFiscalDate());
            jylrhsJylrhz.setFiscalDate(null);
            jylrhsJylrhz.setJgdm(null);
            jylrhsJylrhz.setOperator(getUsername());
            // 更新状态为已审核
            jylrhsJylrhz.setOprationType("2");
            jylrhsJylrhz.setOprationTime(new Date());
            jylrhsJylrhzService.update(jylrhsJylrhz,updateWrapper);
            return Result.ok("批量审核成功！");
        } catch (Exception e) {
            log.info("经营利润核算（经营利润汇总）-批量审核失败！"+e.getMessage());
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param jylrhsJylrhz
     * @return
     */
    @AutoLog(value = "经营利润核算（经营利润汇总）-添加")
    @ApiOperation(value = "经营利润核算（经营利润汇总）-添加", notes = "经营利润核算（经营利润汇总）-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody JylrhsJylrhz jylrhsJylrhz) {
        jylrhsJylrhzService.save(jylrhsJylrhz);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param jylrhsJylrhz
     * @return
     */
    @AutoLog(value = "经营利润核算（经营利润汇总）-编辑")
    @ApiOperation(value = "经营利润核算（经营利润汇总）-编辑", notes = "经营利润核算（经营利润汇总）-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody JylrhsJylrhz jylrhsJylrhz) {
        jylrhsJylrhzService.updateById(jylrhsJylrhz);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "经营利润核算（经营利润汇总）-通过id删除")
    @ApiOperation(value = "经营利润核算（经营利润汇总）-通过id删除", notes = "经营利润核算（经营利润汇总）-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        jylrhsJylrhzService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "经营利润核算（经营利润汇总）-批量删除")
    @ApiOperation(value = "经营利润核算（经营利润汇总）-批量删除", notes = "经营利润核算（经营利润汇总）-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.jylrhsJylrhzService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "经营利润核算（经营利润汇总）-通过id查询")
    @ApiOperation(value = "经营利润核算（经营利润汇总）-通过id查询", notes = "经营利润核算（经营利润汇总）-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        JylrhsJylrhz jylrhsJylrhz = jylrhsJylrhzService.getById(id);
        return Result.ok(jylrhsJylrhz);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param jylrhsJylrhz
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, JylrhsJylrhz jylrhsJylrhz) {
        return super.exportXls(request, jylrhsJylrhz, JylrhsJylrhz.class, "支行经营利润汇总");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, JylrhsJylrhz.class);
    }

}

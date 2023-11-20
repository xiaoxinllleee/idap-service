package org.cmms.modules.jylrhs.jylrsj.fyftjz.controller;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.jylrhs.csgl.jgkmsz.entity.JylrhsKmszJg;
import org.cmms.modules.jylrhs.csgl.jgkmsz.service.IJylrhsKmszJgService;
import org.cmms.modules.jylrhs.csgl.jgzfyxz.entity.JylrhsZfyxzJg;
import org.cmms.modules.jylrhs.csgl.jgzfyxz.service.IJylrhsZfyxzJgService;
import org.cmms.modules.jylrhs.jylrsj.fyftjz.entity.JgfysxJehj;
import org.cmms.modules.jylrhs.jylrsj.fyftjz.entity.JylrhsFyftjz;
import org.cmms.modules.jylrhs.jylrsj.fyftjz.entity.JylrhsFyftjzVO;
import org.cmms.modules.jylrhs.jylrsj.fyftjz.entity.VJylrhsFyftjz;
import org.cmms.modules.jylrhs.jylrsj.fyftjz.service.IJgfysxJehjService;
import org.cmms.modules.jylrhs.jylrsj.fyftjz.service.IJylrhsFyftjzService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.jylrhs.jylrsj.fyftjz.service.IVJylrhsFyftjzService;
import org.cmms.modules.jylrhs.jylrsj.fyftjz.verify.FyftjzImportVerify;
import org.cmms.modules.jylrhs.jylrsj.fyftjz.verify.FyftjzImportVerifys;
import org.cmms.modules.system.entity.SysRole;
import org.cmms.modules.system.service.ISysRoleService;
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
 * @Description: 经营利润核算（费用分摊记账）
 * @Author: jeecg-boot
 * @Date: 2023-06-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "经营利润核算（费用分摊记账）")
@RestController
@RequestMapping("/jylrhs/jylrsj/fyftjz")
public class JylrhsFyftjzController extends JeecgController<VJylrhsFyftjz, IVJylrhsFyftjzService> {
    @Autowired
    private IJylrhsFyftjzService jylrhsFyftjzService;
    @Autowired
    private IVJylrhsFyftjzService iVJylrhsFyftjzService;
    @Autowired
    private IJylrhsZfyxzJgService zfyxzJgService;
    @Autowired
    private IJylrhsKmszJgService kmszJgService;
    @Autowired
    private FyftjzImportVerify importVerify;
    @Autowired
    private FyftjzImportVerifys importVerifys;
    @Autowired
    private ISysRoleService roleService;

    /**
     * 分页列表查询
     *
     * @param vJylrhsFyftjz
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "经营利润核算（费用分摊记账）-分页列表查询")
    @ApiOperation(value = "经营利润核算（费用分摊记账）-分页列表查询", notes = "经营利润核算（费用分摊记账）-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(VJylrhsFyftjz vJylrhsFyftjz,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<VJylrhsFyftjz> queryWrapper = QueryGenerator.initQueryWrapper(vJylrhsFyftjz, req.getParameterMap());
        IPage pageList = PageUtil.toPage(IVJylrhsFyftjzService.class, iVJylrhsFyftjzService, pageNo, pageSize, queryWrapper, "fiscal_date", "jgdm", "jzfl", "jzkm");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param jylrhsFyftjz
     * @return
     */
    @AutoLog(value = "经营利润核算（费用分摊记账）-添加")
    @ApiOperation(value = "经营利润核算（费用分摊记账）-添加", notes = "经营利润核算（费用分摊记账）-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody JylrhsFyftjz jylrhsFyftjz) {
        try {
            //当前机构、科目号上限额度
            BigDecimal currentJgKmEd = BigDecimal.ZERO;
            QueryWrapper<JylrhsKmszJg> jgkmszQueryWrapper = new QueryWrapper<>();
            jgkmszQueryWrapper.eq("jgdm",jylrhsFyftjz.getJgdm());
            jgkmszQueryWrapper.eq("subject_no2",jylrhsFyftjz.getJzkm());
            JylrhsKmszJg kmszJg = kmszJgService.getOne(jgkmszQueryWrapper,false);
            if (kmszJg != null) {
                currentJgKmEd = kmszJg.getSxed().setScale(2,BigDecimal.ROUND_HALF_UP);
                if (jylrhsFyftjz.getJe().compareTo(currentJgKmEd) > 0) {
                    return Result.error("当前机构分摊记账金额已超出机构科目设置上限金额，请核实！");
                }
            } else {
                return Result.error("当前机构没有当前记账科目的录入权限，请核实！");
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            String year = sdf.format(jylrhsFyftjz.getFiscalDate());
            // 当前机构费用上限额度
            BigDecimal jgfysx = BigDecimal.ZERO;
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, Integer.parseInt(year));
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date firstDayOfYear = DateUtil.string2Date(sdf.format(cal.getTime()),"yyyy-MM-dd");//当前会计日期-年初
            Date endDayOfYear = DateUtils.parseDate(year+"-12-31","yyyy-MM-dd");
            log.info("前会计日期-年初"+firstDayOfYear);
            log.info("前会计日期-年末"+endDayOfYear);

            QueryWrapper<JylrhsZfyxzJg> zfyxzJgQueryWrapper = new QueryWrapper<>();
            zfyxzJgQueryWrapper.eq("jgdm",jylrhsFyftjz.getJgdm());
            zfyxzJgQueryWrapper.eq("jznf",firstDayOfYear);
            JylrhsZfyxzJg zfyxzJg = zfyxzJgService.getOne(zfyxzJgQueryWrapper,false);
            if (zfyxzJg != null) {
                // 以万元为单位
                jgfysx = zfyxzJg.getSxed().setScale(2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("10000"));
            }

            QueryWrapper<JylrhsFyftjz> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("fiscal_date",jylrhsFyftjz.getFiscalDate());
            queryWrapper.eq("jgdm",jylrhsFyftjz.getJgdm());
            queryWrapper.eq("jzfl",jylrhsFyftjz.getJzfl());
            queryWrapper.eq("jzkm",jylrhsFyftjz.getJzkm());
            JylrhsFyftjz record = jylrhsFyftjzService.getOne(queryWrapper,false);
            if (record == null) {
                log.info("当前机构"+jylrhsFyftjz.getJgdm());
                log.info("年初日期"+sdf.format(firstDayOfYear));
                log.info("年末日期"+sdf.format(endDayOfYear));
                // 当前机构当年限制金额总和
                BigDecimal total = BigDecimal.ZERO;
                Double totalD = 0d;
                queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("jgdm",jylrhsFyftjz.getJgdm());
                queryWrapper.ge("fiscal_date",firstDayOfYear);
                queryWrapper.le("fiscal_date",endDayOfYear);
                List<JylrhsFyftjz> records = jylrhsFyftjzService.list(queryWrapper);
                if (records.size() > 0) {
                    for (JylrhsFyftjz row : records) {
                        Double je = row.getJe() == null ? 0d : Double.parseDouble(row.getJe().toString());
                        // 累加本年度设置的分摊记账金额
                        totalD += je;
                    }
                    // 加上本次设置的分摊记账金额
                    totalD += Double.parseDouble(jylrhsFyftjz.getJe().toString());
                    total = BigDecimal.valueOf(totalD);
                } else {
                    // 当年未添加当前机构分摊记账金额
                    total.add(jylrhsFyftjz.getJe());
                }
                log.info("当前机构费用上限额度："+jgfysx);
                log.info("当前机构当年限制金额总和："+total);
                if (total.compareTo(jgfysx) > 0) {
                    return Result.error("当前机构分摊记账金额已超出今年上限，请核实！");
                } else {
                    jylrhsFyftjz.setOprationType("1");
                    jylrhsFyftjz.setOprationTime(new Date());
                    jylrhsFyftjz.setOperator(getLoginUser().getUsername());
                    jylrhsFyftjzService.save(jylrhsFyftjz);
                    return Result.ok("添加成功！");
                }
            } else {
                return Result.error("该费用分摊记账已存在，请核实！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加失败！"+e.getMessage());
            return Result.error("添加失败！");
        }
    }

    /**
     * 编辑
     *
     * @param jylrhsFyftjz
     * @return
     */
    @AutoLog(value = "经营利润核算（费用分摊记账）-编辑")
    @ApiOperation(value = "经营利润核算（费用分摊记账）-编辑", notes = "经营利润核算（费用分摊记账）-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody JylrhsFyftjz jylrhsFyftjz) {
        try {
            //当前机构、科目号上限额度
            BigDecimal currentJgKmEd = BigDecimal.ZERO;
            QueryWrapper<JylrhsKmszJg> jgkmszQueryWrapper = new QueryWrapper<>();
            jgkmszQueryWrapper.eq("jgdm",jylrhsFyftjz.getJgdm());
            jgkmszQueryWrapper.eq("subject_no2",jylrhsFyftjz.getJzkm());
            JylrhsKmszJg kmszJg = kmszJgService.getOne(jgkmszQueryWrapper,false);
            if (kmszJg != null) {
                currentJgKmEd = kmszJg.getSxed().setScale(2,BigDecimal.ROUND_HALF_UP);
                if (jylrhsFyftjz.getJe().compareTo(currentJgKmEd) > 0) {
                    return Result.error("当前机构分摊记账金额已超出机构科目设置上限金额("+currentJgKmEd+" 元)，请核实！");
                }
            } else {
                return Result.error("当前机构没有当前记账科目的录入权限，请核实！");
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            String year = sdf.format(jylrhsFyftjz.getFiscalDate());
            // 当前机构费用上限额度
            BigDecimal jgfysx = BigDecimal.ZERO;
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, Integer.parseInt(year));
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date firstDayOfYear = DateUtil.string2Date(sdf.format(cal.getTime()),"yyyy-MM-dd");//当前会计日期-年初
            Date endDayOfYear = DateUtils.parseDate(year + "-12-31", "yyyy-MM-dd");
//            log.info("前会计日期-年初" + firstDayOfYear);
//            log.info("前会计日期-年末" + endDayOfYear);

            QueryWrapper<JylrhsZfyxzJg> zfyxzJgQueryWrapper = new QueryWrapper<>();
            zfyxzJgQueryWrapper.eq("jgdm",jylrhsFyftjz.getJgdm());
            zfyxzJgQueryWrapper.eq("jznf",firstDayOfYear);
            JylrhsZfyxzJg zfyxzJg = zfyxzJgService.getOne(zfyxzJgQueryWrapper,false);
            if (zfyxzJg != null) {
                jgfysx = zfyxzJg.getSxed().setScale(2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("10000"));
            }
            log.info("当前机构"+jylrhsFyftjz.getJgdm());
            log.info("年初日期"+sdf.format(firstDayOfYear));
            log.info("年末日期"+sdf.format(endDayOfYear));
            log.info("当前机构费用上限额度："+jgfysx);
            // 当前机构当年限制金额总和
            BigDecimal total = BigDecimal.ZERO;
            Double totalD = 0d;
            QueryWrapper<JylrhsFyftjz> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("jgdm",jylrhsFyftjz.getJgdm());
            queryWrapper.ge("fiscal_date",firstDayOfYear);
            queryWrapper.le("fiscal_date",endDayOfYear);
            List<JylrhsFyftjz> records = jylrhsFyftjzService.list(queryWrapper);
            if (records.size() > 0) {
                for (JylrhsFyftjz row : records) {
                    Double je = row.getJe() == null ? 0d : Double.parseDouble(row.getJe().toString());
                    // 累加本年度设置的分摊记账金额
                    totalD += je;
//                    log.info("----记账日期----"+sdf.format(row.getFiscalDate())+" ---- "+sdf.format(jylrhsFyftjz.getFiscalDate()));
//                    log.info("----业务机构----"+row.getJgdm()+" ---- "+jylrhsFyftjz.getJgdm());
//                    log.info("----记账分类----"+row.getJzfl()+" ---- "+jylrhsFyftjz.getJzfl());
//                    log.info("----记账科目----"+row.getJzkm()+" ---- "+jylrhsFyftjz.getJzkm());
//                    log.info("-------------------------------------------------------------");
                    if (row.getFiscalDate().compareTo(jylrhsFyftjz.getFiscalDate()) == 0 &&
                        row.getJgdm().equalsIgnoreCase(jylrhsFyftjz.getJgdm()) &&
                        row.getJzfl().equalsIgnoreCase(jylrhsFyftjz.getJzfl()) &&
                        row.getJzkm().equalsIgnoreCase(jylrhsFyftjz.getJzkm())) {
//                        log.info("----分摊金额----"+row.getJe()+" ---- "+jylrhsFyftjz.getJe());
                        // 减去本条记录之前的分摊记账金额
                        totalD -= je;
                    }
                }
                // 加上本次新设置的分摊记账金额
                totalD += Double.parseDouble(jylrhsFyftjz.getJe().toString());
                total = BigDecimal.valueOf(totalD);
            } else {
                // 当年未添加当前机构分摊记账金额
                total.add(jylrhsFyftjz.getJe());
            }
            log.info("当前机构当年限制金额总和："+total);
            if (total.compareTo(jgfysx) > 0) {
                return Result.error("当前机构分摊记账金额已超出今年上限("+jgfysx+" 万元)，请核实！");
            } else {
                UpdateWrapper<JylrhsFyftjz> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("fiscal_date", jylrhsFyftjz.getFiscalDate());
                updateWrapper.eq("jgdm", jylrhsFyftjz.getJgdm());
                updateWrapper.eq("jzfl", jylrhsFyftjz.getJzfl());
                updateWrapper.eq("jzkm", jylrhsFyftjz.getJzkm());
                jylrhsFyftjz.setFiscalDate(null);
                jylrhsFyftjz.setJgdm(null);
                jylrhsFyftjz.setJzfl(null);
                jylrhsFyftjz.setJzkm(null);
                jylrhsFyftjz.setOprationType("2");
                jylrhsFyftjz.setOprationTime(new Date());
                jylrhsFyftjz.setOperator(getLoginUser().getUsername());
                jylrhsFyftjzService.update(jylrhsFyftjz, updateWrapper);
                // TODO 编辑保存最新费用分摊记账后，同步修改机构费用上限金额明细
                return Result.ok("编辑成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("编辑失败！"+e.getMessage());
            return Result.error("编辑失败!");
        }
    }

    /**
     * 删除
     *
     * @param FiscalDate 记账日期
     * @param jgdm       业务机构
     * @param jzfl       记账分类
     * @param jzkm       记账科目
     * @return
     */
    @AutoLog(value = "经营利润核算（费用分摊记账）-删除")
    @ApiOperation(value = "经营利润核算（费用分摊记账）-删除", notes = "经营利润核算（费用分摊记账）-删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "fiscal_date", required = true) String FiscalDate,
                            @RequestParam(name = "jgdm", required = true) String jgdm,
                            @RequestParam(name = "jzfl", required = true) String jzfl,
                            @RequestParam(name = "jzkm", required = true) String jzkm) {
        UpdateWrapper<JylrhsFyftjz> deleteWrapper = new UpdateWrapper<>();
        deleteWrapper.eq("fiscal_date", FiscalDate);
        deleteWrapper.eq("jgdm", jgdm);
        deleteWrapper.eq("jzfl", jzfl);
        deleteWrapper.eq("jzkm", jzkm);
        jylrhsFyftjzService.remove(deleteWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "经营利润核算（费用分摊记账）-批量删除")
    @ApiOperation(value = "经营利润核算（费用分摊记账）-批量删除", notes = "经营利润核算（费用分摊记账）-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.jylrhsFyftjzService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "经营利润核算（费用分摊记账）-通过id查询")
    @ApiOperation(value = "经营利润核算（费用分摊记账）-通过id查询", notes = "经营利润核算（费用分摊记账）-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        JylrhsFyftjz jylrhsFyftjz = jylrhsFyftjzService.getById(id);
        return Result.ok(jylrhsFyftjz);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param vJylrhsFyftjz
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, VJylrhsFyftjz vJylrhsFyftjz) {
        return super.exportXls(request, vJylrhsFyftjz, VJylrhsFyftjz.class, "经营利润核算（费用分摊记账）");
    }

    /**
     * 数据导入模板下载
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        // AutoPoi 导出Excel
        ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "经营利润核算（费用分摊记账）导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, JylrhsFyftjzVO.class);
        ExportParams exportParams = new ExportParams("经营利润核算（费用分摊记账）", "费用分摊记账");
        modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
        modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
        return modelAndView;
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
        //return super.importExcel(request, response, JylrhsFyftjz.class);
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

        JSONObject obj = new JSONObject();
        String filePaths = jsonObject.getString("filePaths");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
            return Result.error("请先上传文件！");
        }
        String[] filePathList = filePaths.split(",");
        for (String filePath : filePathList) {
            String baseFilePath = uploadpath + File.separator + filePath;
            File file = new File(baseFilePath);
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            params.setHeadRows(1);
            params.setNeedSave(false);
            if (admin) {
                if (importVerify != null) {
                    params.setVerifyHanlder(importVerify);
                }
            } else {
                // 非管理员 需要限制不允许导入本机构外的数据
                if (importVerifys != null) {
                    params.setVerifyHanlder(importVerifys);
                }
            }
            FileInputStream fis = null;
            FileOutputStream fos = null;
            try {
                fis = new FileInputStream(file);
//                boolean checkResult = ExcelImportCheckUtil.check(fis, JylrhsFyftjzVO.class, params, 1.0);
//                if (!checkResult) {
//                    return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
//                }
                ExcelImportResult<JylrhsFyftjzVO> importResult = ExcelImportUtil.importExcelVerify(file, JylrhsFyftjzVO.class, JylrhsFyftjzVO.class, params);
                List<JylrhsFyftjz> records = new ArrayList<>();
                List<JylrhsFyftjzVO> list = importResult.getList();
                for (JylrhsFyftjzVO fyftjz : list) {
                    JylrhsFyftjz record = new JylrhsFyftjz();
                    BeanUtil.copyPropertiesIgnoreNull(fyftjz, record);
                    if (record.getJe() == null) {
                        record.setJe(BigDecimal.ZERO);
                    }
                    record.setOprationType("0");
                    record.setOprationTime(new Date());
                    record.setOperator(getLoginUser().getUsername());
                    records.add(record);
                }
                if (!records.isEmpty()) {
                    jylrhsFyftjzService.saveBatch(records);
                }
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();
                return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
                IoUtil.close(fis);
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入成功！");
    }

}

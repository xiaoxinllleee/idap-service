package org.cmms.modules.xyjlcx.hmdgl.hmdgl.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hmef.dev.HMEFDumper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.EtlUtilOld;
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.xyjlcx.hmdgl.hmdgl.entity.Hmdgl;
import org.cmms.modules.xyjlcx.hmdgl.hmdgl.entity.HmdglVO;
import org.cmms.modules.xyjlcx.hmdgl.hmdgl.mapper.HmdglMapper;
import org.cmms.modules.xyjlcx.hmdgl.hmdgl.service.IHmdglService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.xyjlcx.hmdgl.hmdgl.verify.HmdglImportVerify;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 黑名单管理
 * @Author: jeecg-boot
 * @Date: 2021-08-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "黑名单管理")
@RestController
@RequestMapping("/hmdgl/hmdgl")
public class HmdglController extends JeecgController<Hmdgl, IHmdglService> {
    @Autowired
    private IHmdglService hmdglService;
    @Autowired
    private ISysDictService iSysDictService;
    @Autowired
    private HmdglImportVerify hmdglImportVerify;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param hmdgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "黑名单管理-分页列表查询")
    @ApiOperation(value = "黑名单管理-分页列表查询", notes = "黑名单管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Hmdgl hmdgl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        //Page<Hmdgl> page = new Page<>(pageNo,pageSize);
       // Result<IPage<Hmdgl>> result = new Result<IPage<Hmdgl>>();
        QueryWrapper<Hmdgl> queryWrapper = QueryGenerator.initQueryWrapper(hmdgl, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IHmdglService.class, hmdglService, pageNo, pageSize, queryWrapper, "zjhm");
        return Result.ok(pageList);

        //result.setSuccess(true);
        //result.setResult(pageList);
        //Page<Hmdgl> hmdglPage = service.page(page, queryWrapper);
        //return Result.ok(hmdglPage);
    }

    /**
     * 黑名单管理 / 审批
     *
     * @param jsonObject
     * @return
     */
    @AutoLog(value = "黑名单管理-审批")
    @ApiOperation(value = "黑名单管理-审批", notes = "黑名单管理-审批")
    @RequestMapping(value = "/sp")
    public Result<?> sp(@RequestBody JSONObject jsonObject) {
        String spjg = jsonObject.getString("spjg");
        Hmdgl hmdgl = jsonObject.toJavaObject(Hmdgl.class);
        QueryWrapper<Hmdgl> queryWrapper = new QueryWrapper<Hmdgl>();
        queryWrapper.eq("zjhm",hmdgl.getZjhm());
        queryWrapper.eq("bljlxwms",hmdgl.getBljlxwms());
        if ("1".equals(spjg)) {
            //表主键zjhm，bljlxwms不能更新（Kudu）
            hmdgl.setZjhm(null);
            hmdgl.setBljlxwms(null);
            hmdgl.setYxbz(2);
            hmdgl.setLrbz(2);
            hmdgl.setLrr(getLoginUser().getUsername());
            hmdgl.setLrsj(new Timestamp(System.currentTimeMillis()));
            hmdgl.setSpr(getLoginUser().getRealname());
            hmdgl.setSpsj(new Date());
            hmdglService.update(hmdgl,queryWrapper);
        } else {
            //表主键zjhm，bljlxwms不能更新（Kudu）
            hmdgl.setZjhm(null);
            hmdgl.setBljlxwms(null);
            hmdgl.setYxbz(1);
            hmdgl.setLrbz(2);
            hmdgl.setLrr(getLoginUser().getUsername());
            hmdgl.setLrsj(new Timestamp(System.currentTimeMillis()));
            hmdgl.setSpr(getLoginUser().getRealname());
            hmdgl.setSpsj(new Date());
            hmdglService.update(hmdgl,queryWrapper);
        }
        return Result.ok("审批成功！");
    }

    /**
     * 黑名单管理 / 提取
     *
     * @return
     */
    @AutoLog(value = "黑名单管理-提取")
    @ApiOperation(value = "黑名单管理-提取", notes = "黑名单管理-提取")
    @RequestMapping(value = "/init")
    public Result<?> init() {
        Result result = new Result<>();
        // `浏阳`调用ETL工具类执行ETL调度
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            // `参数1`:`任务调用code`，`参数2`:`ETL调度存储过程参数值`，`参数3`:`ETL任务预计执行时间(可根据实际执行时间酌情延长)`
            HashMap<String,String> params = new HashMap<>();
            params.put("etl_task","kiss.domain.application.zx.proc_credit_hmdtq");
            boolean completionSignal = EtlUtil.callEtl("zx_common_init", params, 20);
            result.setSuccess(completionSignal);
        } else {
            try {
                hmdglService.pHmdgl();
                result.setSuccess(true);
                return result;
            } catch (Throwable e) {
                log.error("信用记录查询 / 黑名单管理 / 提取失败！"+e.getMessage());
                result.setSuccess(false);
            }
        }
        return result;
    }

    /**
     * 黑名单管理 / 抹除
     *
     * @param hmdgl
     * @return
     */
    @AutoLog(value = "黑名单管理-抹除")
    @ApiOperation(value = "黑名单管理-抹除", notes = "黑名单管理-抹除")
    @PutMapping(value = "/erase")
    public Result<?> erase(@RequestBody Hmdgl hmdgl) {
        QueryWrapper<Hmdgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bljlxwms",hmdgl.getBljlxwms());
        queryWrapper.eq("zjhm",hmdgl.getZjhm());
        //表主键zjhm，bljlxwms不能更新（Kudu）
        hmdgl.setZjhm(null);
        hmdgl.setBljlxwms(null);
        hmdgl.setYxbz(3);
        hmdgl.setLrbz(2);
        hmdgl.setLrr(getLoginUser().getUsername());
        hmdgl.setLrsj(new Timestamp(System.currentTimeMillis()));
        hmdgl.setMcr(getLoginUser().getRealname());
        hmdgl.setMcsj(new Date());
        hmdgl.setBljlmcrq(new Date());
        hmdglService.update(hmdgl, queryWrapper);
        return Result.ok("抹除成功");
    }

    /**
     * 黑名单管理 / 添加
     *
     * @param hmdgl
     * @return
     */
    @AutoLog(value = "黑名单管理-添加")
    @ApiOperation(value = "黑名单管理-添加", notes = "黑名单管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Hmdgl hmdgl) {
        QueryWrapper<Hmdgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zjhm",hmdgl.getZjhm());
        queryWrapper.eq("bljlxwms",hmdgl.getBljlxwms());
        List<Hmdgl> hmdgl1 = hmdglService.list(queryWrapper);
        if (!hmdgl1.isEmpty()) {
            if ("1".equals(hmdgl1.get(0).getYxbz() + "")) {
                return Result.error("该客户已被列入黑名单，请勿重复添加！");
            } else {
                Hmdgl updateHmdgl = hmdgl1.get(0);
                UpdateWrapper<Hmdgl> hmdglUpdateWrapper = new UpdateWrapper<>();
                hmdglUpdateWrapper.eq("zjhm", hmdgl.getZjhm());
                hmdglUpdateWrapper.eq("bljlxwms", hmdgl.getBljlxwms());
                //表主键不能更新（Kudu）
                updateHmdgl.setZjhm(null);
                updateHmdgl.setBljlxwms(null);
                updateHmdgl.setBljlxwms(hmdgl.getBljlxwms());
                updateHmdgl.setYxbz(1);
                updateHmdgl.setDjrq(hmdgl.getDjrq());
                updateHmdgl.setBlxwjsrq(hmdgl.getBlxwjsrq());
                updateHmdgl.setQksm(hmdgl.getQksm());
                updateHmdgl.setLrr(getLoginUser().getRealname());
                updateHmdgl.setLrsj(new Timestamp(System.currentTimeMillis()));
                updateHmdgl.setLrbz(1);
                hmdglService.update(updateHmdgl, hmdglUpdateWrapper);
                return Result.ok("添加成功！");
            }
        } else {
            hmdgl.setYxbz(1);
            hmdgl.setLrbz(1);
            hmdgl.setLrsj(new Timestamp(System.currentTimeMillis()));
            hmdgl.setLrr(getLoginUser().getRealname());
            hmdglService.save(hmdgl);
        }
        return Result.ok("添加成功！");
    }

    /**
     * 黑名单管理 / 编辑
     *
     * @param hmdgl
     * @return
     */
    @AutoLog(value = "黑名单管理-编辑")
    @ApiOperation(value = "黑名单管理-编辑", notes = "黑名单管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Hmdgl hmdgl) {
        QueryWrapper<Hmdgl> queryWrapper = new QueryWrapper<Hmdgl>();
        queryWrapper.eq("zjhm", hmdgl.getZjhm());
        queryWrapper.eq("bljlxwms", hmdgl.getBljlxwms());
        List<Hmdgl> hmdglList = hmdglService.list(queryWrapper);
        if (hmdglList.isEmpty()){
            return Result.error("该客户未被列入黑名单，请核实！");
        }
        Hmdgl updateHmdgl = hmdglList.get(0);
        //表主键zjhm，bljlxwms不能更新（Kudu）
        updateHmdgl.setZjhm(null);
        updateHmdgl.setBljlxwms(null);
        updateHmdgl.setQksm(hmdgl.getQksm());
        updateHmdgl.setDjrq(hmdgl.getDjrq());
        updateHmdgl.setLrbz(2);
        updateHmdgl.setLrsj(new Timestamp(System.currentTimeMillis()));
        updateHmdgl.setLrr(getLoginUser().getRealname());
        hmdglService.update(updateHmdgl,queryWrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 黑名单管理 / 删除
     *
     * @param
     * @return
     */
    @AutoLog(value = "黑名单管理-通过id删除")
    @ApiOperation(value = "黑名单管理-通过id删除", notes = "黑名单管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@Param("zjhm") String zjhm,
                            @Param("bljlxwms") String bljlxwms) {
        QueryWrapper<Hmdgl> queryWrapper = new QueryWrapper<Hmdgl>();
        queryWrapper.eq("zjhm", zjhm);
        queryWrapper.eq("bljlxwms", Integer.parseInt(bljlxwms));
        hmdglService.remove(queryWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 导出excel
     *
     * @param request
     * @param hmdgl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Hmdgl hmdgl) {
        return super.exportXls(request, hmdgl, Hmdgl.class, "黑名单管理");
    }

    /**
     * 导出模板
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        //return super.exportTemplateXls(SsglVO.class, "诉讼管理导入模板");
        // AutoPoi 导出Excel
        ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "黑名单管理导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, HmdglVO.class);
        ExportParams exportParams = new ExportParams("黑名单管理导入模板", "模板信息");
        modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
        modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
        return modelAndView;
    }

    /**
     * 通过excel导入数据
     *
     * @param jsonObject
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        return super.importExcelByTemplate(jsonObject, request, response, Hmdgl.class,HmdglVO.class, hmdglImportVerify);
    }

    @RequestMapping(value = "/importExcel2", method = RequestMethod.POST)
    public Result<?> importExcel2(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            String originalFilename = file.getOriginalFilename();
            boolean isExcel2003 = true;
            if (file.getName().matches("^.+\\.(?i)(xlsx)$")) {
                isExcel2003 = false;
            }
            Workbook wb = null;
            try {
                if (isExcel2003) {
                    wb = new XSSFWorkbook(file.getInputStream());
                } else {
                    wb = new HSSFWorkbook(file.getInputStream());
                }
                Sheet sheet = wb.getSheetAt(0);
                int lastRowNum = sheet.getLastRowNum();
                System.out.println(lastRowNum);
                if (lastRowNum < 4)
                    return Result.error("导入文件缺少内容");
                Row row1 = sheet.getRow(1);
                DateTime yyyyMMdd = null;
                //验证时间
                if (row1.getCell(0) != null) {
                    String cell = row1.getCell(0).getStringCellValue();
                    String s1 = cell.replace("认定时间", "");
                    String s2 = s1.replace(":", "");
                    String s3 = s2.replace("：", "");
                    String s4 = s3.replace("年", "");
                    String s5 = s4.replace("月", "");
                    String s6 = s5.replace("日", "");
                    String s7 = s6.replace(" ", "");
                    System.out.println(s7);
                    yyyyMMdd = DateUtil.parse(s7, "yyyyMMdd");
                }
            for (int r = 3; r < sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                String khmc = null;
                String zjhm = null;
                Double rzly = 1d;
                String bz = null;
                if (row.getCell(0) != null) {
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    khmc = row.getCell(0).getStringCellValue().trim();
                } else {
                    log.error("导入失败(第" + (r + 1) + "行,客户名称未填写)");
                    continue;
                }

                if (row.getCell(1) != null) {
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    zjhm = row.getCell(1).getStringCellValue().trim();
                    QueryWrapper queryWrapper = new QueryWrapper();
                    queryWrapper.eq("zjhm",zjhm);
                    List list = service.list(queryWrapper);
                    if (CollUtil.isNotEmpty(list)){
                        log.error("导入失败(第" + (r + 1) + "行,该证件号码已经存在)");
                        continue;
                    }
                } else {
                    log.error("导入失败(第" + (r + 1) + "行,证件号码未填写)");
                    continue;
                }

                if (row.getCell(3) != null) {
                    row.getCell(3).setCellType(Cell.CELL_TYPE_NUMERIC);
                    rzly = row.getCell(3).getNumericCellValue();
                } else {
                    log.error("导入失败(第" + (r + 1) + "行,认定理由未填写)");
                    continue;
                }

                if (row.getCell(4) != null) {
                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                    bz = row.getCell(4).getStringCellValue().trim();
                }

                Hmdgl hmdgl = new Hmdgl();
                hmdgl.setZjhm(zjhm);
                hmdgl.setBljlxwms(rzly.intValue());
                hmdgl.setKhmc(khmc);
                hmdgl.setQksm(bz);
                hmdgl.setDjrq(yyyyMMdd);
                hmdgl.setYxbz(1);
                hmdgl.setLrbz(0);
                hmdgl.setLrr(getWorkNo());
                System.out.println(hmdgl);
                service.save(hmdgl);
            }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return Result.ok("文件导入成功！");

    }
}


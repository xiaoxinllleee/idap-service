package org.cmms.modules.tjbb.tjfz.sgtzdr.dklxsr.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.tjbb.tjfz.sgtzdr.dklxsr.entity.Dklxsr;
import org.cmms.modules.tjbb.tjfz.sgtzdr.dklxsr.service.IDklxsrService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjbb.tjfz.sgtzdr.dklxsr.verify.DklxsrImportVerify;
import org.cmms.modules.tjbb.tjfz.sgtzdr.dklxsr.vo.DklxsrImportVO;
import org.cmms.modules.util.EtlUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
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
 * @Description: 贷款利息收入
 * @Author: Penghr
 * @Date: 2022-12-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "贷款利息收入")
@RestController
@RequestMapping("/tjbb/tjfz/sgtzdr/dklxsr")
public class DklxsrController extends JeecgController<Dklxsr, IDklxsrService> {
    @Autowired
    private IDklxsrService dklxsrService;
    @Autowired
    private DklxsrImportVerify importVerify;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param dklxsr
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "贷款利息收入-分页列表查询")
    @ApiOperation(value = "贷款利息收入-分页列表查询", notes = "贷款利息收入-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Dklxsr dklxsr,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Dklxsr> queryWrapper = QueryGenerator.initQueryWrapper(dklxsr, req.getParameterMap());
        IPage<Dklxsr> pageList = PageUtil.toPage(IDklxsrService.class, dklxsrService, pageNo, pageSize, queryWrapper, "data_date");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param dklxsr
     * @return
     */
//    @AutoLog(value = "贷款利息收入-添加")
//    @ApiOperation(value = "贷款利息收入-添加", notes = "贷款利息收入-添加")
//    @PostMapping(value = "/add")
//    public Result<?> add(@RequestBody Dklxsr dklxsr) {
//        dklxsrService.save(dklxsr);
//        return Result.ok("添加成功！");
//    }

    /**
     * 编辑
     *
     * @param dklxsr
     * @return
     */
    @AutoLog(value = "贷款利息收入-编辑")
    @ApiOperation(value = "贷款利息收入-编辑", notes = "贷款利息收入-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Dklxsr dklxsr) {
        UpdateWrapper<Dklxsr> wrapper = new UpdateWrapper<>();
        wrapper.eq("data_date", dklxsr.getDataDate());
        wrapper.eq("jgbm", dklxsr.getJgbm());
        dklxsr.setDataDate(null);
        dklxsr.setJgbm(null);
        dklxsr.setLrbz(2);
        dklxsr.setLrr(getLoginUser().getUsername());
        dklxsr.setLrsj(new Date());
        dklxsrService.update(dklxsr, wrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过dataDate、jgbm删除
     *
     * @param date 数据日期
     * @param jgbm 机构编码
     * @return
     */
    @AutoLog(value = "贷款利息收入-通过dataDate、jgbm删除")
    @ApiOperation(value = "贷款利息收入-通过dataDate、jgbm删除", notes = "贷款利息收入-通过dataDate、jgbm删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "dataDate", required = true) String date,
                            @RequestParam(name = "jgbm", required = true) String jgbm) {
        Date dataDate = DateUtil.string2Date(date,"yyyy-MM-dd");
        UpdateWrapper<Dklxsr> wrapper = new UpdateWrapper<>();
        wrapper.eq("data_date", dataDate);
        wrapper.eq("jgbm", jgbm);
        dklxsrService.remove(wrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
//    @AutoLog(value = "贷款利息收入-批量删除")
//    @ApiOperation(value = "贷款利息收入-批量删除", notes = "贷款利息收入-批量删除")
//    @DeleteMapping(value = "/deleteBatch")
//    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
//        this.dklxsrService.removeByIds(Arrays.asList(ids.split(",")));
//        return Result.ok("批量删除成功！");
//    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "贷款利息收入-通过id查询")
    @ApiOperation(value = "贷款利息收入-通过id查询", notes = "贷款利息收入-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Dklxsr dklxsr = dklxsrService.getById(id);
        return Result.ok(dklxsr);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param dklxsr
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Dklxsr dklxsr) {
        //return super.exportXls(request, dklxsr, Dklxsr.class, "贷款利息收入");
        QueryWrapper<Dklxsr> queryWrapper = new QueryWrapper<>();
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                Dklxsr form = JSON.parseObject(deString, Dklxsr.class);
                queryWrapper = QueryGenerator.initQueryWrapper(form, request.getParameterMap());
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("导出错误！贷款利息收入："+throwable.getMessage());
        }
        queryWrapper.orderByAsc("data_date","jgbm");
        List<Dklxsr> pageList = dklxsrService.list(queryWrapper);
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "贷款利息收入");
        mv.addObject(NormalExcelConstants.CLASS, Dklxsr.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("新邵农村商业银行贷款利息收入", "导出人:" + getLoginUser().getRealname() + "（单位/万元）", "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    /**
     * 导出模板Excel
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "贷款利息收入");
        mv.addObject(NormalExcelConstants.CLASS, DklxsrImportVO.class);
        ExportParams exportParams = new ExportParams("贷款利息收入导入模板", "贷款利息收入");
        mv.addObject(NormalExcelConstants.PARAMS, exportParams);
        mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
        return mv;
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
        //return super.importExcel(request, response, Dklxsr.class);
        //Date fiscalDate = DateUtil.string2Date(request.getParameter("fiscalDate"),"yyyy-MM-dd");
        String filePaths = jsonObject.getString("filePaths");
        if (StringUtils.isEmpty(filePaths)) {
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
            if (importVerify != null) {
                params.setVerifyHanlder(importVerify);
            }
            FileInputStream fis = null;
            FileOutputStream fos = null;
            try {
                fis = new FileInputStream(file);
                boolean checkResult = ExcelImportCheckUtil.check(fis, DklxsrImportVO.class, params, 1.0);
                ExcelImportResult<DklxsrImportVO> importResult = ExcelImportUtil.importExcelVerify(file, DklxsrImportVO.class, params);
                List<DklxsrImportVO> list = importResult.getList();
                List<Dklxsr> dklxsrList = new ArrayList<>();
                for (DklxsrImportVO dklxsrImportVO : list) {
                    Dklxsr dklxsr = new Dklxsr();
                    BeanUtil.copyPropertiesIgnoreNull(dklxsrImportVO, dklxsr);
                    //dklxsr.setDataDate(fiscalDate);
                    dklxsr.setLrbz(0);
                    dklxsr.setLrr(getLoginUser().getUsername());
                    dklxsr.setLrsj(new Date());
                    dklxsrList.add(dklxsr);
                }
                if (!dklxsrList.isEmpty()) {
                    //保存以前删除当月数据，以防止重复导入
                    UpdateWrapper<Dklxsr> wrapper = new UpdateWrapper<>();
                    wrapper.eq("data_date",dklxsrList.get(0).getDataDate());
                    dklxsrService.remove(wrapper);
                    // 保存最新当月数据
                    dklxsrService.saveBatch(dklxsrList);
                }
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();

                //导入贷款利息收入成功以后，根据导入的机构名称自动更新机构编码
                if ("true".equals(sfdsjpt)) {
                    HashMap<String, String> param = new HashMap<>();
                    param.put("etl_task", "kiss.domain.application.tjbb.proc_tjbb_sgtz_dklxsr");
                    boolean flag = EtlUtil.callEtl("tjbb_common_init", param, 15);
                    log.info("贷款利息收入-机构编码处理逻辑-是否成功？-"+flag);
                } else {
                    log.info("贷款利息收入-导入-非大数据应用平台操作-未添加处理逻辑");
                }

                return Result.ok("文件导入完成！成功导入数据行数: " + list.size(), obj);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage(),e);
                return Result.error("文件导入失败:"+e.getMessage());
            } finally {
                IoUtil.close(fis);
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入失败！");
    }

}

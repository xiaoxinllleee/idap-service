package org.cmms.modules.tjbb.tjfz.sgtzdr.dkyejzrwfp.controller;

import java.io.*;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.tjbb.tjfz.sgtzdr.dkyejzrwfp.entity.Dkyejzrwfp;
import org.cmms.modules.tjbb.tjfz.sgtzdr.dkyejzrwfp.service.IDkyejzrwfpService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjbb.tjfz.sgtzdr.dkyejzrwfp.vo.DkyejzrwfpImportVO;
import org.cmms.modules.tjbb.tjfz.sgtzdr.dkyejzrwfp.verify.DkyejzrwfpImportVerify;
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
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 贷款余额净增任务分配
 * @Author: Penghr
 * @Date: 2022-12-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "贷款余额净增任务分配")
@RestController
@RequestMapping("/tjbb/tjfz/sgtzdr/dkyejzrwfp")
public class DkyejzrwfpController extends JeecgController<Dkyejzrwfp, IDkyejzrwfpService> {
    @Autowired
    private IDkyejzrwfpService dkyejzrwfpService;
    @Autowired
    private DkyejzrwfpImportVerify importVerify;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param dkyejzrwfp
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "贷款余额净增任务分配-分页列表查询")
    @ApiOperation(value = "贷款余额净增任务分配-分页列表查询", notes = "贷款余额净增任务分配-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Dkyejzrwfp dkyejzrwfp,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Dkyejzrwfp> queryWrapper = QueryGenerator.initQueryWrapper(dkyejzrwfp, req.getParameterMap());
        IPage<Dkyejzrwfp> pageList = PageUtil.toPage(IDkyejzrwfpService.class, dkyejzrwfpService, pageNo, pageSize, queryWrapper, "data_date");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param dkyejzrwfp
     * @return
     */
//    @AutoLog(value = "贷款余额净增任务分配-添加")
//    @ApiOperation(value = "贷款余额净增任务分配-添加", notes = "贷款余额净增任务分配-添加")
//    @PostMapping(value = "/add")
//    public Result<?> add(@RequestBody Dkyejzrwfp dkyejzrwfp) {
//        dkyejzrwfpService.save(dkyejzrwfp);
//        return Result.ok("添加成功！");
//    }

    /**
     * 编辑
     *
     * @param dkyejzrwfp
     * @return
     */
    @AutoLog(value = "贷款余额净增任务分配-编辑")
    @ApiOperation(value = "贷款余额净增任务分配-编辑", notes = "贷款余额净增任务分配-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Dkyejzrwfp dkyejzrwfp) {
        UpdateWrapper<Dkyejzrwfp> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("data_date", dkyejzrwfp.getDataDate());
        updateWrapper.eq("jgbm", dkyejzrwfp.getJgbm());
        dkyejzrwfp.setDataDate(null);
        dkyejzrwfp.setJgbm(null);
        dkyejzrwfp.setLrbz(2);
        dkyejzrwfp.setLrr(getLoginUser().getUsername());
        dkyejzrwfp.setLrsj(new Date());
        dkyejzrwfpService.update(dkyejzrwfp, updateWrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过dataDate、jgbm删除
     *
     * @param date
     * @param jgbm
     * @return
     */
    @AutoLog(value = "贷款余额净增任务分配-通过dataDate、jgbm删除")
    @ApiOperation(value = "贷款余额净增任务分配-通过dataDate、jgbm删除", notes = "贷款余额净增任务分配-通过dataDate、jgbm删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "dataDate", required = true) String date,
                            @RequestParam(name = "jgbm", required = true) String jgbm) {
        Date dataDate = DateUtil.string2Date(date,"yyyy-MM-dd");
        UpdateWrapper<Dkyejzrwfp> wrapper = new UpdateWrapper<>();
        wrapper.eq("data_date", dataDate);
        wrapper.eq("jgbm", jgbm);
        dkyejzrwfpService.remove(wrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
//    @AutoLog(value = "贷款余额净增任务分配-批量删除")
//    @ApiOperation(value = "贷款余额净增任务分配-批量删除", notes = "贷款余额净增任务分配-批量删除")
//    @DeleteMapping(value = "/deleteBatch")
//    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
//        this.dkyejzrwfpService.removeByIds(Arrays.asList(ids.split(",")));
//        return Result.ok("批量删除成功！");
//    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "贷款余额净增任务分配-通过id查询")
    @ApiOperation(value = "贷款余额净增任务分配-通过id查询", notes = "贷款余额净增任务分配-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Dkyejzrwfp dkyejzrwfp = dkyejzrwfpService.getById(id);
        return Result.ok(dkyejzrwfp);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param dkyejzrwfp
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Dkyejzrwfp dkyejzrwfp) {
        // return super.exportXls(request, gwdrwfp, Gwdrwfp.class, "贷款余额净增任务分配");
        QueryWrapper<Dkyejzrwfp> queryWrapper = new QueryWrapper<>();
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                Dkyejzrwfp form = JSON.parseObject(deString, Dkyejzrwfp.class);
                queryWrapper = QueryGenerator.initQueryWrapper(form, request.getParameterMap());
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("导出错误！贷款余额净增任务分配："+throwable.getMessage());
        }
        queryWrapper.orderByAsc("data_date","jgbm");
        List<Dkyejzrwfp> pageList = dkyejzrwfpService.list(queryWrapper);
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "贷款余额净增任务分配表");
        mv.addObject(NormalExcelConstants.CLASS, Dkyejzrwfp.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("新邵农村商业银行贷款余额净增任务分配", "导出人:" + getLoginUser().getRealname() + "（单位/万元）", "导出信息"));
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
        mv.addObject(NormalExcelConstants.FILE_NAME, "贷款余额净增任务分配导入模板");
        mv.addObject(NormalExcelConstants.CLASS, DkyejzrwfpImportVO.class);
        ExportParams exportParams = new ExportParams("贷款余额净增任务分配导入模板", "贷款余额净增任务分配");
        mv.addObject(NormalExcelConstants.PARAMS, exportParams);
        mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
        return mv;
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
        // return super.importExcel(request, response, Gwdrwfp.class);
        Date fiscalDate = DateUtil.string2Date(request.getParameter("fiscalDate"),"yyyy-MM-dd");
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
                boolean checkResult = ExcelImportCheckUtil.check(fis, DkyejzrwfpImportVO.class, params, 1.0);
                ExcelImportResult<DkyejzrwfpImportVO> importResult = ExcelImportUtil.importExcelVerify(file, DkyejzrwfpImportVO.class, params);
                List<DkyejzrwfpImportVO> list = importResult.getList();
                List<Dkyejzrwfp> dkyejzrwfpList = new ArrayList<>();
                for (DkyejzrwfpImportVO dkyejzrwfpImportVO : list) {
                    Dkyejzrwfp dkyejzrwfp = new Dkyejzrwfp();
                    BeanUtil.copyPropertiesIgnoreNull(dkyejzrwfpImportVO, dkyejzrwfp);
                    dkyejzrwfp.setDataDate(fiscalDate);
                    dkyejzrwfp.setLrbz(0);
                    dkyejzrwfp.setLrr(getLoginUser().getUsername());
                    dkyejzrwfp.setLrsj(new Date());
                    dkyejzrwfpList.add(dkyejzrwfp);
                }
                if (!dkyejzrwfpList.isEmpty()) {
                    //保存以前删除当月数据，以防止重复导入
                    UpdateWrapper<Dkyejzrwfp> wrapper = new UpdateWrapper<>();
                    wrapper.eq("data_date", fiscalDate);
                    dkyejzrwfpService.remove(wrapper);
                    // 保存最新当月数据
                    dkyejzrwfpService.saveBatch(dkyejzrwfpList);
                }
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();

                //导入贷款余额净增任务分配成功以后，根据导入的机构名称自动更新机构编码
                if ("true".equals(sfdsjpt)) {
                    HashMap<String, String> param = new HashMap<>();
                    param.put("etl_task", "kiss.domain.application.tjbb.proc_tjbb_sgtz_dkyejzrwfp");
                    boolean flag = EtlUtil.callEtl("tjbb_common_init", param, 15);
                    log.info("贷款余额净增任务分配-机构编码处理逻辑-是否成功？-"+flag);
                } else {
                    log.info("贷款余额净增任务分配-导入-非大数据应用平台操作-未添加处理逻辑");
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

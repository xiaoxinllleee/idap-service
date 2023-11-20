package org.cmms.modules.tjbb.tjfz.sgtzdr.rjqkmb.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
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
import org.cmms.modules.tjbb.tjfz.sgtzdr.rjqkmb.entity.RjQkmb;
import org.cmms.modules.tjbb.tjfz.sgtzdr.rjqkmb.service.IRjQkmbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.tjbb.tjfz.sgtzdr.rjqkmb.verify.RjQkmbImportVerify;
import org.cmms.modules.tjbb.tjfz.sgtzdr.rjqkmb.vo.RjQkmbImportVO;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 日均全科目表
 * @Author: Penghr
 * @Date: 2022-12-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "日均全科目表")
@RestController
@RequestMapping("/tjbb/tjfz/sgtzdr/qkmb")
public class RjQkmbController extends JeecgController<RjQkmb, IRjQkmbService> {
    @Autowired
    private IRjQkmbService rjQkmbService;
    @Autowired
	private RjQkmbImportVerify importVerify;

    /**
     * 分页列表查询
     *
     * @param qkmb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "日均全科目表-分页列表查询")
    @ApiOperation(value = "日均全科目表-分页列表查询", notes = "日均全科目表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(RjQkmb qkmb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<RjQkmb> queryWrapper = QueryGenerator.initQueryWrapper(qkmb, req.getParameterMap());
        IPage<RjQkmb> pageList = PageUtil.toPage(IRjQkmbService.class, rjQkmbService, pageNo, pageSize, queryWrapper, "data_date");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param qkmb
     * @return
     */
//    @AutoLog(value = "日均全科目表-添加")
//    @ApiOperation(value = "日均全科目表-添加", notes = "日均全科目表-添加")
//    @PostMapping(value = "/add")
//    public Result<?> add(@RequestBody RjQkmb qkmb) {
//        rjQkmbService.save(qkmb);
//        return Result.ok("添加成功！");
//    }

    /**
     * 编辑
     *
     * @param qkmb
     * @return
     */
    @AutoLog(value = "日均全科目表-编辑")
    @ApiOperation(value = "日均全科目表-编辑", notes = "日均全科目表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody RjQkmb qkmb) {
        UpdateWrapper<RjQkmb> wrapper = new UpdateWrapper<>();
        wrapper.eq("data_date", qkmb.getDataDate());
        wrapper.eq("xmdh", qkmb.getXmdh());
        qkmb.setDataDate(null);
        qkmb.setXmdh(null);
        qkmb.setLrbz(2);
        qkmb.setLrr(getLoginUser().getUsername());
        qkmb.setLrsj(new Date());
        rjQkmbService.update(qkmb, wrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过dataDate、xmdh删除
     *
     * @param date 数据日期
     * @param xmdh 项目代号
     * @return
     */
    @AutoLog(value = "日均全科目表-通过dataDate、xmdh删除")
    @ApiOperation(value = "日均全科目表-通过dataDate、xmdh删除", notes = "日均全科目表-通过dataDate、xmdh删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "dataDate", required = true) String date,
                            @RequestParam(name = "xmdh", required = true) String xmdh) {
        Date dataDate = DateUtil.string2Date(date, "yyyy-MM-dd");
        UpdateWrapper<RjQkmb> wrapper = new UpdateWrapper<>();
        wrapper.eq("data_date", dataDate);
        wrapper.eq("xmdh", xmdh);
        rjQkmbService.remove(wrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
//    @AutoLog(value = "日均全科目表-批量删除")
//    @ApiOperation(value = "日均全科目表-批量删除", notes = "日均全科目表-批量删除")
//    @DeleteMapping(value = "/deleteBatch")
//    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
//        this.rjQkmbService.removeByIds(Arrays.asList(ids.split(",")));
//        return Result.ok("批量删除成功！");
//    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "日均全科目表-通过id查询")
    @ApiOperation(value = "日均全科目表-通过id查询", notes = "日均全科目表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        RjQkmb qkmb = rjQkmbService.getById(id);
        return Result.ok(qkmb);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param qkmb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RjQkmb qkmb) {
        //return super.exportXls(request, qkmb, RjQkmb.class, "日均全科目表");
        QueryWrapper<RjQkmb> queryWrapper = new QueryWrapper<>();
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                RjQkmb form = JSON.parseObject(deString, RjQkmb.class);
                queryWrapper = QueryGenerator.initQueryWrapper(form, request.getParameterMap());
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("导出错误！日均全科目表：" + throwable.getMessage());
        }
        queryWrapper.orderByAsc("data_date", "xmdh");
        List<RjQkmb> pageList = rjQkmbService.list(queryWrapper);
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "日均全科目表");
        mv.addObject(NormalExcelConstants.CLASS, RjQkmb.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("新邵农村商业银行日均全科目表", "导出人:" + getLoginUser().getRealname() + "（单位/万元）", "导出信息"));
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
        mv.addObject(NormalExcelConstants.FILE_NAME, "日均全科目表");
        mv.addObject(NormalExcelConstants.CLASS, RjQkmbImportVO.class);
        ExportParams exportParams = new ExportParams("日均全科目表导入模板", "日均全科目表");
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
        //return super.importExcel(request, response, RjQkmb.class);
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
                boolean checkResult = ExcelImportCheckUtil.check(fis, RjQkmbImportVO.class, params, 1.0);
                ExcelImportResult<RjQkmbImportVO> importResult = ExcelImportUtil.importExcelVerify(file, RjQkmbImportVO.class, params);
                List<RjQkmbImportVO> list = importResult.getList();
                List<RjQkmb> rjQkmbList = new ArrayList<>();
                for (RjQkmbImportVO rjQkmbImportVO : list) {
                    RjQkmb rjQkmb = new RjQkmb();
                    BeanUtil.copyPropertiesIgnoreNull(rjQkmbImportVO, rjQkmb);
                    rjQkmb.setDataDate(fiscalDate);
                    rjQkmb.setXmdh(rjQkmbImportVO.getXmdhT());
                    rjQkmb.setLrbz(0);
                    rjQkmb.setLrr(getLoginUser().getUsername());
                    rjQkmb.setLrsj(new Date());
                    rjQkmbList.add(rjQkmb);
                }
                if (!rjQkmbList.isEmpty()) {
                    //保存以前删除当月数据，以防止重复导入
                    UpdateWrapper<RjQkmb> wrapper = new UpdateWrapper<>();
                    wrapper.eq("data_date", fiscalDate);
                    rjQkmbService.remove(wrapper);
                    // 保存最新当月数据
                    rjQkmbService.saveBatch(rjQkmbList);
                }
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();
                return Result.ok("文件导入完成！成功导入数据行数: " + list.size(), obj);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
                IoUtil.close(fis);
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入失败！");
    }

}

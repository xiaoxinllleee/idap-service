package org.cmms.modules.report.tzsjgl.xtyhcdhptz.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.modules.report.tzsjgl.xtyhcdhptz.entity.XtYhcdhptz;
import org.cmms.modules.report.tzsjgl.xtyhcdhptz.service.XtYhcdhptzService;

import org.cmms.modules.report.tzsjgl.xtyhcdhptz.verify.XtYhcdhptzImportVerify;
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
 * @Description: 湘潭银行承兑汇票台账
 * @Author: jeecg-boot
 * @Date: 2022-08-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "湘潭银行承兑汇票台账")
@RestController
@RequestMapping("/xtyhcdhptz/pepTzglXtYhcdhptz")
public class XtYhcdhptzController extends JeecgController<XtYhcdhptz, XtYhcdhptzService> {
    @Autowired
    private XtYhcdhptzService XtYhcdhptzService;
    @Autowired
    private XtYhcdhptzImportVerify xtYhcdhptzImportVerify;


    /**
     * 分页列表查询
     *
     * @param xtYhcdhptz
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "湘潭银行承兑汇票台账-分页列表查询")
    @ApiOperation(value = "湘潭银行承兑汇票台账-分页列表查询", notes = "湘潭银行承兑汇票台账-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(XtYhcdhptz xtYhcdhptz,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<XtYhcdhptz> queryWrapper = QueryGenerator.initQueryWrapper(xtYhcdhptz, req.getParameterMap());
        Page<XtYhcdhptz> page = new Page<XtYhcdhptz>(pageNo, pageSize);
        IPage<XtYhcdhptz> pageList = XtYhcdhptzService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param xtYhcdhptz
     * @return
     */
    @AutoLog(value = "湘潭银行承兑汇票台账-添加")
    @ApiOperation(value = "湘潭银行承兑汇票台账-添加", notes = "湘潭银行承兑汇票台账-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody XtYhcdhptz xtYhcdhptz) {
    	xtYhcdhptz.setId(UUIDGenerator.generate());
        XtYhcdhptzService.save(xtYhcdhptz);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param xtYhcdhptz
     * @return
     */
    @AutoLog(value = "湘潭银行承兑汇票台账-编辑")
    @ApiOperation(value = "湘潭银行承兑汇票台账-编辑", notes = "湘潭银行承兑汇票台账-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody XtYhcdhptz xtYhcdhptz) {
        XtYhcdhptzService.updateById(xtYhcdhptz);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过sjrq删除
     *
     * @param
     * @return
     */
    @AutoLog(value = "湘潭银行承兑汇票台账-通过sjrq删除")
    @ApiOperation(value = "湘潭银行承兑汇票台账-通过sjrq删除", notes = "湘潭银行承兑汇票台账-通过sjrq删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "sjrq", required = true) String sjrq) {
        QueryWrapper<XtYhcdhptz> queryWrapper = new QueryWrapper<>();
        DateTime parse = DateUtil.parse(sjrq);
        queryWrapper.eq("sjrq", parse);
        XtYhcdhptzService.remove(queryWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 通过id删除
     */
    @AutoLog(value = "湘潭银行承兑汇票台账-通过id删除")
    @ApiOperation(value = "湘潭银行承兑汇票台账-通过id删除", notes = "湘潭银行承兑汇票台账-通过id删除")
    @DeleteMapping(value = "/deleteById")
    public Result<?> deleteById(@RequestParam(name = "id",required = true) String id){
        XtYhcdhptzService.removeById(id);
        return Result.ok("删除成功!");
    }
    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "湘潭银行承兑汇票台账-批量删除")
    @ApiOperation(value = "湘潭银行承兑汇票台账-批量删除", notes = "湘潭银行承兑汇票台账-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.XtYhcdhptzService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "湘潭银行承兑汇票台账-通过id查询")
    @ApiOperation(value = "湘潭银行承兑汇票台账-通过id查询", notes = "湘潭银行承兑汇票台账-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        XtYhcdhptz xtYhcdhptz = XtYhcdhptzService.getById(id);
        return Result.ok(xtYhcdhptz);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param xtYhcdhptz
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, XtYhcdhptz xtYhcdhptz) {
        return super.exportXls(request, xtYhcdhptz, XtYhcdhptz.class, "银行承兑汇票台账");
    }

    /**
     * 导入模板
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
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "银行承兑汇票台账表导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, XtYhcdhptz.class);
        ExportParams exportParams = new ExportParams("银行承兑汇票台账表导入模板", "模板信息");
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
        String sjrq = request.getParameter("sjrq");
        System.out.println(sjrq + "----sjrq----");
        Date parse = DateUtil.parse(sjrq);
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
            if (xtYhcdhptzImportVerify != null) {
                params.setVerifyHanlder(xtYhcdhptzImportVerify);
            }
            FileOutputStream fos = null;
            try {
                ExcelImportResult<XtYhcdhptz> importResult = ExcelImportUtil.importExcelVerify(file, XtYhcdhptz.class, params);
                List<XtYhcdhptz> list = importResult.getList();
                List<XtYhcdhptz> qkmbList = new ArrayList<>();
                for (XtYhcdhptz ywzkb : list) {
                    ywzkb.setSjrq(parse);
                    ywzkb.setId(UUIDGenerator.generate());
                    qkmbList.add(ywzkb);
                }
                service.saveBatch(qkmbList);
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
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入失败！");
    }

}

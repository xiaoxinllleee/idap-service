package org.cmms.modules.qtsjdrjk.xyk.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.qtsjdrjk.xyk.entity.ShywxxXyk;
import org.cmms.modules.qtsjdrjk.xyk.service.IShywxxXykService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.jcsjgl.bmk.entity.TjfxBmkhtbXdxtsj;
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
 * @Description: 信用卡
 * @Author: jeecg-boot
 * @Date: 2020-11-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "信用卡")
@RestController
@RequestMapping("/SHYWXX_XYK/shywxxXyk")
public class ShywxxXykController extends JeecgController<ShywxxXyk, IShywxxXykService> {
    @Autowired
    private IShywxxXykService shywxxXykService;
    @Value(value = "${common.path.upload}")
    private String uploadpath;

    /**
     * 分页列表查询
     *
     * @param shywxxXyk
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "信用卡-分页列表查询")
    @ApiOperation(value = "信用卡-分页列表查询", notes = "信用卡-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(ShywxxXyk shywxxXyk,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<ShywxxXyk> queryWrapper = QueryGenerator.initQueryWrapper(shywxxXyk, req.getParameterMap());
        Page<ShywxxXyk> page = new Page<ShywxxXyk>(pageNo, pageSize);
        IPage<ShywxxXyk> pageList = shywxxXykService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param shywxxXyk
     * @return
     */
    @AutoLog(value = "信用卡-添加")
    @ApiOperation(value = "信用卡-添加", notes = "信用卡-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody ShywxxXyk shywxxXyk) {
        shywxxXykService.save(shywxxXyk);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param shywxxXyk
     * @return
     */
    @AutoLog(value = "信用卡-编辑")
    @ApiOperation(value = "信用卡-编辑", notes = "信用卡-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody ShywxxXyk shywxxXyk) {
        shywxxXykService.updateById(shywxxXyk);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "信用卡-通过id删除")
    @ApiOperation(value = "信用卡-通过id删除", notes = "信用卡-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        shywxxXykService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "信用卡-批量删除")
    @ApiOperation(value = "信用卡-批量删除", notes = "信用卡-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.shywxxXykService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "信用卡-通过id查询")
    @ApiOperation(value = "信用卡-通过id查询", notes = "信用卡-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        ShywxxXyk shywxxXyk = shywxxXykService.getById(id);
        return Result.ok(shywxxXyk);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param shywxxXyk
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ShywxxXyk shywxxXyk) {
        // Step.1 组装查询条件
        QueryWrapper<ShywxxXyk> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
				ShywxxXyk dkjkpt_zhbldkftjk_bsy = JSON.parseObject(deString, ShywxxXyk.class);
                queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_zhbldkftjk_bsy, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<ShywxxXyk> pageList = shywxxXykService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "信用卡数据导出");
        mv.addObject(NormalExcelConstants.CLASS, ShywxxXyk.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("信用卡数据导出", "导出人:Jeecg", "导出信息"));
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
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        String filePaths = jsonObject.getString("filePaths");
        if (StringUtils.isEmpty(filePaths)) {
            return Result.error("请先上传文件！");
        }
        String[] filePathList = filePaths.split(",");
        JSONObject obj = new JSONObject();
        ShywxxXyk shywxxXyk = new ShywxxXyk();
        for (String filePath : filePathList) {
            String baseFilePath = uploadpath + File.separator + filePath;
            File file = new File(baseFilePath);
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            params.setHeadRows(1);
            params.setNeedSave(true);
            InputStream fis = null;
            HSSFWorkbook newBook = null;
            try {
                List<ShywxxXyk> listKhhmcs = ExcelImportUtil.importExcel(file, ShywxxXyk.class, params);
                System.out.println("listKhhmcs----"+listKhhmcs);
                List<String> ids = new ArrayList<String>();
                List<ShywxxXyk> insertList = new ArrayList<ShywxxXyk>();
                fis = new FileInputStream(baseFilePath);
                newBook = new HSSFWorkbook(new POIFSFileSystem(fis));
                HSSFSheet sheet = newBook.getSheetAt(0);
                HSSFRow hssfRow = null;
                int rCi = 0, rCii = 0;
                int i = 2;
                for (ShywxxXyk khhmc : listKhhmcs) {
                    hssfRow = sheet.getRow(i++);
                    if (rCi == 0) {
                        rCi = hssfRow.getLastCellNum();
                        rCii = rCi + 1;
                    }
                    HSSFCell resultCell = hssfRow.getCell(rCi);
                    if (resultCell == null) resultCell = hssfRow.createCell(rCi);
                    HSSFCell resultCellInfo = hssfRow.getCell(rCii);
                    if (resultCellInfo == null) resultCellInfo = hssfRow.createCell(rCii);

                    String result = "导入成功";
                    String resultInfo = "";
                    resultCell.setCellValue(result);
                    resultCellInfo.setCellValue(resultInfo);
                    insertList.add(khhmc);
                    shywxxXykService.deleteAll();
                }
                obj.put("filePath", filePath);
                shywxxXykService.saveBatch(insertList);
                FileOutputStream fos = new FileOutputStream(baseFilePath);
                newBook.write(fos);
                fos.flush();
                fos.close();
                return Result.ok("文件导入成功！数据行数:" + listKhhmcs.size() + "，导入成功行数：" + insertList.size() + "，失败行数：" + (listKhhmcs.size() - insertList.size()), obj);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            }
        }
        return Result.ok("文件导入失败！");
    }

    /**
     * 导出模板excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        //AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "信用卡数据导入模板");
        mv.addObject(NormalExcelConstants.CLASS, ShywxxXyk.class);
        ExportParams exportParams = new ExportParams("信用卡数据导入模板", "模板信息");
        mv.addObject(NormalExcelConstants.PARAMS, exportParams);
        mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<ShywxxXyk>());
        return mv;
    }



}

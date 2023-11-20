package org.cmms.modules.tjfx.jcsjgl.bmk.controller;

import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
import org.cmms.modules.tjfx.jcsjgl.bmk.entity.TjfxBmkhtbXdxtsj;
import org.cmms.modules.tjfx.jcsjgl.bmk.service.ITjfxBmkhtbXdxtsjService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.tjfx.jcsjgl.dkye.entity.TjfxXddkyebXdxtsj;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date: 2020-08-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "1")
@RestController
@RequestMapping("/TjfxBmkhtbXdxtsj/tjfxBmkhtbXdxtsj")
public class TjfxBmkhtbXdxtsjController extends JeecgController<TjfxBmkhtbXdxtsj, ITjfxBmkhtbXdxtsjService> {
    @Autowired
    private ITjfxBmkhtbXdxtsjService tjfxBmkhtbXdxtsjService;
    @Value(value = "${common.path.upload}")
    private String uploadpath;

    /**
     * 分页列表查询
     *
     * @param tjfxBmkhtbXdxtsj
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "1-分页列表查询")
    @ApiOperation(value = "1-分页列表查询", notes = "1-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(TjfxBmkhtbXdxtsj tjfxBmkhtbXdxtsj,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<TjfxBmkhtbXdxtsj> queryWrapper = QueryGenerator.initQueryWrapper(tjfxBmkhtbXdxtsj, req.getParameterMap());
        Page<TjfxBmkhtbXdxtsj> page = new Page<TjfxBmkhtbXdxtsj>(pageNo, pageSize);
        IPage<TjfxBmkhtbXdxtsj> pageList = tjfxBmkhtbXdxtsjService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param tjfxBmkhtbXdxtsj
     * @return
     */
    @AutoLog(value = "1-添加")
    @ApiOperation(value = "1-添加", notes = "1-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody TjfxBmkhtbXdxtsj tjfxBmkhtbXdxtsj) {
        tjfxBmkhtbXdxtsjService.save(tjfxBmkhtbXdxtsj);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param tjfxBmkhtbXdxtsj
     * @return
     */
    @AutoLog(value = "1-编辑")
    @ApiOperation(value = "1-编辑", notes = "1-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody TjfxBmkhtbXdxtsj tjfxBmkhtbXdxtsj) {
        tjfxBmkhtbXdxtsjService.updateById(tjfxBmkhtbXdxtsj);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "1-通过id删除")
    @ApiOperation(value = "1-通过id删除", notes = "1-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        tjfxBmkhtbXdxtsjService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "1-批量删除")
    @ApiOperation(value = "1-批量删除", notes = "1-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.tjfxBmkhtbXdxtsjService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "1-通过id查询")
    @ApiOperation(value = "1-通过id查询", notes = "1-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        TjfxBmkhtbXdxtsj tjfxBmkhtbXdxtsj = tjfxBmkhtbXdxtsjService.getById(id);
        return Result.ok(tjfxBmkhtbXdxtsj);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param tjfxBmkhtbXdxtsj
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TjfxBmkhtbXdxtsj tjfxBmkhtbXdxtsj) {
        // Step.1 组装查询条件
        QueryWrapper<TjfxBmkhtbXdxtsj> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                TjfxBmkhtbXdxtsj dkjkpt_zhbldkftjk_bsy = JSON.parseObject(deString, TjfxBmkhtbXdxtsj.class);
                queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_zhbldkftjk_bsy, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<TjfxBmkhtbXdxtsj> pageList = tjfxBmkhtbXdxtsjService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "信贷系统便民卡数据导出");
        mv.addObject(NormalExcelConstants.CLASS, TjfxBmkhtbXdxtsj.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("信贷系统便民卡数据导出", "导出人:Jeecg", "导出信息"));
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
        TjfxBmkhtbXdxtsj tjfxBmkhtbXdxtsj = new TjfxBmkhtbXdxtsj();
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
                List<TjfxBmkhtbXdxtsj> listKhhmcs = ExcelImportUtil.importExcel(file, TjfxBmkhtbXdxtsj.class, params);
                List<String> ids = new ArrayList<String>();
                List<TjfxBmkhtbXdxtsj> insertList = new ArrayList<TjfxBmkhtbXdxtsj>();
                fis = new FileInputStream(baseFilePath);
                newBook = new HSSFWorkbook(new POIFSFileSystem(fis));
                HSSFSheet sheet = newBook.getSheetAt(0);
                HSSFRow hssfRow = null;
                int rCi = 0, rCii = 0;
                int i = 2;
                for (TjfxBmkhtbXdxtsj khhmc : listKhhmcs) {
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
                    if (khhmc.getTjyf() == null) {
                        result = "导入失败";
                        resultInfo = "统计月份不能为空！";
                        resultCell.setCellValue(result);
                        resultCellInfo.setCellValue(resultInfo);
                        continue;
                    }
//                    if (StringUtils.isEmpty(khhmc.getZjhm())) {
//                        result = "导入失败";
//                        resultInfo = "证件号码不能为空！";
//                        resultCell.setCellValue(result);
//                        resultCellInfo.setCellValue(resultInfo);
//                        continue;
//                    }
//					if (StringUtils.isEmpty(khhmc.getKhmc())) {
//						result = "导入失败";
//						resultInfo = "客户名称不能为空！";
//						resultCell.setCellValue(result);
//						resultCellInfo.setCellValue(resultInfo);
//						continue;
//					}

                    //判断数据库是否存在重复的记录
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
//                    String t = simpleDateFormat.format(khhmc.getTjyf());
//                    TjfxBmkhtbXdxtsj tjfxBmkhtbXdxtsj = tjfxBmkhtbXdxtsjService.queryMsg(khhmc.getZjhm(), khhmc.getJgdm(), t);
//                    if (tjfxBmkhtbXdxtsj != null) {
//                        String tjyf = simpleDateFormat.format(tjfxBmkhtbXdxtsj.getTjyf());
//                        ids.add(tjfxBmkhtbXdxtsj.getZjhm() + "-" + tjfxBmkhtbXdxtsj.getJgdm() + "-" + tjyf + "-" + tjfxBmkhtbXdxtsj.getKh() + "-" + tjfxBmkhtbXdxtsj.getHtksrq());
//                    }
                    QueryWrapper<TjfxBmkhtbXdxtsj> queryWrapper = new QueryWrapper<>();
                    resultCell.setCellValue(result);
                    resultCellInfo.setCellValue(resultInfo);
                    insertList.add(khhmc);
//                    System.out.println("-----------"+simpleDateFormat.format(khhmc.getTjyf()));
                    //tjfxBmkhtbXdxtsjService.deleteMsg(simpleDateFormat.format(khhmc.getTjyf()));
                    tjfxBmkhtbXdxtsj.setTjyf(khhmc.getTjyf());
                }
                 tjfxBmkhtbXdxtsjService.deletemian(tjfxBmkhtbXdxtsj.getTjyf());

                //先删除已经存在的数据
//                if (!ids.isEmpty()) {
//                    for (String id : ids) {
//                        try {
//                            String[] split = id.split("-");
//                            UpdateWrapper<TjfxBmkhtbXdxtsj> userUpdateWrapper = new UpdateWrapper<>();
//                            userUpdateWrapper.eq("zjhm", split[0]).eq("jgdm", split[1]).eq("tjyf", split[2]);
//                            tjfxBmkhtbXdxtsjService.deleteMsg(split[0], split[1], split[2]);
//                        } catch (Exception e) {
//                            log.error("删除失败", e.getMessage());
//                            return Result.error("删除失败!");
//                        }
//                    }
//                }
                obj.put("filePath", filePath);
                tjfxBmkhtbXdxtsjService.saveBatch(insertList);
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
        mv.addObject(NormalExcelConstants.FILE_NAME, "信贷系统便民卡数据导入模板");
        mv.addObject(NormalExcelConstants.CLASS, TjfxBmkhtbXdxtsj.class);
        ExportParams exportParams = new ExportParams("信贷系统便民卡数据导入模板", "模板信息");
        mv.addObject(NormalExcelConstants.PARAMS, exportParams);
        mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<TjfxBmkhtbXdxtsj>());
        return mv;
    }
}

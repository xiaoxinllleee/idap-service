package org.cmms.modules.khlc.yxdkedcs.controller;

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
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khlc.yxdkedcs.entity.Yxdkedcs;
import org.cmms.modules.khlc.yxdkedcs.service.IYxdkedcsService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khlc.yxdkedcs.verify.YxdkedcsImportVerify;
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
 * @Description: 营销贷款额度参数
 * @Author: penghr
 * @Date: 2023-03-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "营销贷款额度参数")
@RestController
@RequestMapping("/yxdkedcs")
public class YxdkedcsController extends JeecgController<Yxdkedcs, IYxdkedcsService> {
    @Autowired
    private IYxdkedcsService yxdkedcsService;
    @Autowired
    private YxdkedcsImportVerify importVerify;

    /**
     * 分页列表查询
     *
     * @param yxdkedcs
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "营销贷款额度参数-分页列表查询")
    @ApiOperation(value = "营销贷款额度参数-分页列表查询", notes = "营销贷款额度参数-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Yxdkedcs yxdkedcs,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Yxdkedcs> queryWrapper = QueryGenerator.initQueryWrapper(yxdkedcs, req.getParameterMap());
        Page<Yxdkedcs> page = new Page<Yxdkedcs>(pageNo, pageSize);
        IPage<Yxdkedcs> pageList = yxdkedcsService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param yxdkedcs
     * @return
     */
    @AutoLog(value = "营销贷款额度参数-添加")
    @ApiOperation(value = "营销贷款额度参数-添加", notes = "营销贷款额度参数-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Yxdkedcs yxdkedcs) {
        yxdkedcsService.save(yxdkedcs);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param yxdkedcs
     * @return
     */
    @AutoLog(value = "营销贷款额度参数-编辑")
    @ApiOperation(value = "营销贷款额度参数-编辑", notes = "营销贷款额度参数-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Yxdkedcs yxdkedcs) {
        UpdateWrapper<Yxdkedcs> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("csbh",yxdkedcs.getCsbh());
        yxdkedcsService.update(yxdkedcs,updateWrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过参数编号删除
     *
     * @param csbh
     * @return
     */
    @AutoLog(value = "营销贷款额度参数-通过csbh删除")
    @ApiOperation(value = "营销贷款额度参数-通过csbh删除", notes = "营销贷款额度参数-通过csbh删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "csbh", required = true) String csbh) {
        UpdateWrapper<Yxdkedcs> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("csbh",csbh);
        yxdkedcsService.remove(updateWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    /*@AutoLog(value = "营销贷款额度参数-批量删除")
    @ApiOperation(value = "营销贷款额度参数-批量删除", notes = "营销贷款额度参数-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.yxdkedcsService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }*/

    /**
     * 通过参数编号查询
     *
     * @param csbh
     * @return
     */
    @AutoLog(value = "营销贷款额度参数-通过csbh查询")
    @ApiOperation(value = "营销贷款额度参数-通过csbh查询", notes = "营销贷款额度参数-通过csbh查询")
    @GetMapping(value = "/queryByCsbh")
    public Result<?> queryByCsbh(@RequestParam(name = "csbh", required = true) String csbh) {
        QueryWrapper<Yxdkedcs> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("csbh",csbh);
        Yxdkedcs yxdkedcs = yxdkedcsService.getOne(queryWrapper,false);
        return Result.ok(yxdkedcs);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param yxdkedcs
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Yxdkedcs yxdkedcs) {
        return super.exportXls(request, yxdkedcs, Yxdkedcs.class, "营销贷款额度参数");
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
        mv.addObject(NormalExcelConstants.FILE_NAME, "营销贷款额度参数");
        mv.addObject(NormalExcelConstants.CLASS, Yxdkedcs.class);
        ExportParams exportParams = new ExportParams("营销贷款额度参数导入模板", "营销贷款额度参数");
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
                boolean checkResult = ExcelImportCheckUtil.check(fis, Yxdkedcs.class, params, 1.0);
                if (!checkResult) {
                    return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
                }
                ExcelImportResult<Yxdkedcs> importResult = ExcelImportUtil.importExcelVerify(file, Yxdkedcs.class, params);
                List<Yxdkedcs> list = importResult.getList();
                List<Yxdkedcs> yxdkedcsList = new ArrayList<>();
                for (Yxdkedcs tsdkglT : list) {
                    Yxdkedcs tsdkgl = new Yxdkedcs();
                    BeanUtil.copyPropertiesIgnoreNull(tsdkglT,tsdkgl);
                    tsdkgl.setLrbz(0);
                    tsdkgl.setLrr(getLoginUser().getUsername());
                    tsdkgl.setLrsj(new Date());
                    yxdkedcsList.add(tsdkgl);
                    // 相同贷款账号的删除
                    UpdateWrapper<Yxdkedcs> updateWrapper = new UpdateWrapper<>();
                    updateWrapper.eq("csbh",tsdkgl.getCsbh());
                    yxdkedcsService.remove(updateWrapper);
                }
                if (!yxdkedcsList.isEmpty()) {
                    yxdkedcsService.saveBatch(yxdkedcsList);
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

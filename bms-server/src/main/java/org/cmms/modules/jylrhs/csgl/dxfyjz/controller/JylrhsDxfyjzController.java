package org.cmms.modules.jylrhs.csgl.dxfyjz.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.jylrhs.csgl.dxfyjz.entity.JylrhsDxfyjz;
import org.cmms.modules.jylrhs.csgl.dxfyjz.entity.JylrhsDxfyjzVO;
import org.cmms.modules.jylrhs.csgl.dxfyjz.service.IJylrhsDxfyjzService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.jylrhs.csgl.dxfyjz.verify.JylrhsDxfyjzImportVerify;
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
 * @Description: 经营利润核算（单项费用记账）
 * @Author: jeecg-boot
 * @Date: 2023-08-21
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "经营利润核算（单项费用记账）")
@RestController
@RequestMapping("/jylrhs/csgl/dxfyjz")
public class JylrhsDxfyjzController extends JeecgController<JylrhsDxfyjz, IJylrhsDxfyjzService> {
    @Autowired
    private IJylrhsDxfyjzService jylrhsDxfyjzService;
    @Autowired
    private JylrhsDxfyjzImportVerify importVerify;

    /**
     * 分页列表查询
     *
     * @param jylrhsDxfyjz
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "经营利润核算（单项费用记账）-分页列表查询")
    @ApiOperation(value = "经营利润核算（单项费用记账）-分页列表查询", notes = "经营利润核算（单项费用记账）-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(JylrhsDxfyjz jylrhsDxfyjz,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<JylrhsDxfyjz> queryWrapper = QueryGenerator.initQueryWrapper(jylrhsDxfyjz, req.getParameterMap());
        //Page<JylrhsDxfyjz> page = new Page<JylrhsDxfyjz>(pageNo, pageSize);
        //IPage<JylrhsDxfyjz> pageList = jylrhsDxfyjzService.page(page, queryWrapper);
        IPage pageList = PageUtil.toPage(IJylrhsDxfyjzService.class, jylrhsDxfyjzService, pageNo, pageSize, queryWrapper, "jgdm","jzrq");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param jylrhsDxfyjz
     * @return
     */
    @AutoLog(value = "经营利润核算（单项费用记账）-添加")
    @ApiOperation(value = "经营利润核算（单项费用记账）-添加", notes = "经营利润核算（单项费用记账）-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody JylrhsDxfyjz jylrhsDxfyjz) {
        //jylrhsDxfyjzService.save(jylrhsDxfyjz);
        //return Result.ok("添加成功！");
        try {
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().replace("-", "");
            jylrhsDxfyjz.setJzid(id);
            jylrhsDxfyjz.setOprationType("1");
            jylrhsDxfyjz.setOperator(getLoginUser().getUsername());
            jylrhsDxfyjz.setOprationTime(new Date());
            jylrhsDxfyjzService.save(jylrhsDxfyjz);
            return Result.ok("添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加失败！"+e.getMessage());
            return Result.error("添加失败！");
        }
    }

    /**
     * 编辑
     *
     * @param jylrhsDxfyjz
     * @return
     */
    @AutoLog(value = "经营利润核算（单项费用记账）-编辑")
    @ApiOperation(value = "经营利润核算（单项费用记账）-编辑", notes = "经营利润核算（单项费用记账）-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody JylrhsDxfyjz jylrhsDxfyjz) {
        UpdateWrapper<JylrhsDxfyjz> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("jzid",jylrhsDxfyjz.getJzid());
        jylrhsDxfyjz.setJzid(null);
        jylrhsDxfyjz.setOprationType("2");
        jylrhsDxfyjz.setOprationTime(new Date());
        jylrhsDxfyjz.setOperator(getLoginUser().getUsername());
        jylrhsDxfyjzService.update(jylrhsDxfyjz,updateWrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "经营利润核算（单项费用记账）-通过id删除")
    @ApiOperation(value = "经营利润核算（单项费用记账）-通过id删除", notes = "经营利润核算（单项费用记账）-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        UpdateWrapper<JylrhsDxfyjz> deleteWrapper = new UpdateWrapper<>();
        deleteWrapper.eq("jzid",id);
        jylrhsDxfyjzService.remove(deleteWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "经营利润核算（单项费用记账）-批量删除")
    @ApiOperation(value = "经营利润核算（单项费用记账）-批量删除", notes = "经营利润核算（单项费用记账）-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.jylrhsDxfyjzService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "经营利润核算（单项费用记账）-通过id查询")
    @ApiOperation(value = "经营利润核算（单项费用记账）-通过id查询", notes = "经营利润核算（单项费用记账）-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        JylrhsDxfyjz jylrhsDxfyjz = jylrhsDxfyjzService.getById(id);
        return Result.ok(jylrhsDxfyjz);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param jylrhsDxfyjz
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, JylrhsDxfyjz jylrhsDxfyjz) {
        return super.exportXls(request, jylrhsDxfyjz, JylrhsDxfyjz.class, "经营利润核算（单项费用记账）");
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
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "经营利润核算（单项费用记账）导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, JylrhsDxfyjzVO.class);
        ExportParams exportParams = new ExportParams("经营利润核算（单项费用记账）", "单项费用记账");
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
        //return super.importExcel(request, response, JylrhsDxfyjz.class);
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
            if (importVerify != null) {
                params.setVerifyHanlder(importVerify);
            }
            FileInputStream fis = null;
            FileOutputStream fos = null;
            try {
                fis = new FileInputStream(file);
                ExcelImportResult<JylrhsDxfyjzVO> importResult = ExcelImportUtil.importExcelVerify(file, JylrhsDxfyjzVO.class, JylrhsDxfyjzVO.class, params);
                List<JylrhsDxfyjz> records = new ArrayList<>();
                List<JylrhsDxfyjzVO> list = importResult.getList();
                for (JylrhsDxfyjzVO kmsz : list) {
                    JylrhsDxfyjz record = new JylrhsDxfyjz();
                    BeanUtil.copyPropertiesIgnoreNull(kmsz, record);
                    UUID uuid = UUID.randomUUID();
                    String id = uuid.toString().replace("-", "");
                    record.setJzid(id);
                    record.setOprationType("0");
                    record.setOprationTime(new Date());
                    record.setOperator(getLoginUser().getUsername());
                    records.add(record);
                }
                if (!records.isEmpty()) {
                    jylrhsDxfyjzService.saveBatch(records);
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

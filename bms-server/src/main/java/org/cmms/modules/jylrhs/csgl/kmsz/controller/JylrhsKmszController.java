package org.cmms.modules.jylrhs.csgl.kmsz.controller;

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
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.jylrhs.csgl.jgkmsz.entity.JylrhsKmszJg;
import org.cmms.modules.jylrhs.csgl.jgkmsz.service.IJylrhsKmszJgService;
import org.cmms.modules.jylrhs.csgl.kmsz.entity.JylrhsKmsz;
import org.cmms.modules.jylrhs.csgl.kmsz.entity.JylrhsKmszVO;
import org.cmms.modules.jylrhs.csgl.kmsz.service.IJylrhsKmszService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.jylrhs.csgl.kmsz.verify.JylrhsKmszImportVerify;
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
 * @Description: 经营利润核算（科目设置）
 * @Author: jeecg-boot
 * @Date: 2023-06-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "经营利润核算（科目设置）")
@RestController
@RequestMapping("/jylrhs/csgl/kmsz")
public class JylrhsKmszController extends JeecgController<JylrhsKmsz, IJylrhsKmszService> {
    @Autowired
    private IJylrhsKmszService jylrhsKmszService;
    @Autowired
    private IJylrhsKmszJgService kmszJgService;
    @Autowired
    private JylrhsKmszImportVerify importVerify;

    /**
     * 分页列表查询
     *
     * @param jylrhsKmsz
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "经营利润核算（科目设置）-分页列表查询")
    @ApiOperation(value = "经营利润核算（科目设置）-分页列表查询", notes = "经营利润核算（科目设置）-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(JylrhsKmsz jylrhsKmsz,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<JylrhsKmsz> queryWrapper = QueryGenerator.initQueryWrapper(jylrhsKmsz, req.getParameterMap());
        IPage pageList = PageUtil.toPage(IJylrhsKmszService.class, jylrhsKmszService, pageNo, pageSize, queryWrapper, "subject_no1", "subject_no2");
        return Result.ok(pageList);
    }

    /**
     * 费用分摊记账-机构科目设置-权限内查询
     *
     * @param jylrhsKmsz
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "经营利润核算（科目设置）-分页列表查询")
    @ApiOperation(value = "经营利润核算（科目设置）-分页列表查询", notes = "经营利润核算（科目设置）-分页列表查询")
    @GetMapping(value = "/listLookup")
    public Result<?> queryPageListLookup(JylrhsKmsz jylrhsKmsz,
                                         @RequestParam(name = "jgdm") String jgdm,
                                         @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                         HttpServletRequest req) {
        QueryWrapper<JylrhsKmszJg> kmszJgQueryWrapper = new QueryWrapper<>();
        kmszJgQueryWrapper.eq("jgdm",jgdm);
        List<JylrhsKmszJg> kmszJgList = kmszJgService.list(kmszJgQueryWrapper);
        QueryWrapper<JylrhsKmsz> queryWrapper = QueryGenerator.initQueryWrapper(jylrhsKmsz, req.getParameterMap());
        List SubjectNo2List = new ArrayList();
        for (JylrhsKmszJg kmszJg : kmszJgList) {
            SubjectNo2List.add(kmszJg.getSubjectNo2());
        }
        if (!SubjectNo2List.isEmpty()) {
            queryWrapper.in("subject_no2",SubjectNo2List);
        }
        IPage pageList = PageUtil.toPage(IJylrhsKmszService.class, jylrhsKmszService, pageNo, pageSize, queryWrapper, "subject_no1", "subject_no2");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param jylrhsKmsz
     * @return
     */
    @AutoLog(value = "经营利润核算（科目设置）-添加")
    @ApiOperation(value = "经营利润核算（科目设置）-添加", notes = "经营利润核算（科目设置）-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody JylrhsKmsz jylrhsKmsz) {
        try {
            log.info("----经营利润核算 科目设置 主键重复校验----"+jylrhsKmsz.getSubjectNo1()+"；"+jylrhsKmsz.getSubjectNo2());
            /*QueryWrapper<JylrhsKmsz> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("subject_no1",jylrhsKmsz.getSubjectNo1());
            JylrhsKmsz record = jylrhsKmszService.getOne(queryWrapper,false);
            if (record != null) {
                return Result.error("该一级科目号已存在，请核实！");
            }*/
            QueryWrapper<JylrhsKmsz> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("subject_no2",jylrhsKmsz.getSubjectNo2());
            JylrhsKmsz record = jylrhsKmszService.getOne(queryWrapper,false);
            if (record != null) {
                return Result.error("该二级科目号已存在，请核实！");
            }
            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("subject_no1",jylrhsKmsz.getSubjectNo1());
            queryWrapper.eq("subject_no2",jylrhsKmsz.getSubjectNo2());
            record = jylrhsKmszService.getOne(queryWrapper,false);
            if (record != null) {
                return Result.error("该科目设置已存在，请核实！");
            }
            jylrhsKmsz.setOprationType("1");
            jylrhsKmsz.setOperator(getLoginUser().getUsername());
            jylrhsKmsz.setOprationTime(new Date());
            jylrhsKmszService.save(jylrhsKmsz);
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
     * @param jylrhsKmsz
     * @return
     */
    @AutoLog(value = "经营利润核算（科目设置）-编辑")
    @ApiOperation(value = "经营利润核算（科目设置）-编辑", notes = "经营利润核算（科目设置）-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody JylrhsKmsz jylrhsKmsz) {
        UpdateWrapper<JylrhsKmsz> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.eq("szfl", jylrhsKmsz.getSzfl());
//        updateWrapper.eq("tjfl", jylrhsKmsz.getTjfl());
        updateWrapper.eq("subject_no1", jylrhsKmsz.getSubjectNo1());
        updateWrapper.eq("subject_no2", jylrhsKmsz.getSubjectNo2());
//        jylrhsKmsz.setSzfl(null);
//        jylrhsKmsz.setTjfl(null);
        jylrhsKmsz.setSubjectNo1(null);
        jylrhsKmsz.setSubjectNo2(null);
        jylrhsKmsz.setOprationType("2");
        jylrhsKmsz.setOprationTime(new Date());
        jylrhsKmsz.setOperator(getLoginUser().getUsername());
        jylrhsKmszService.update(jylrhsKmsz,updateWrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 删除
     *
     * @param szfl 收支分类
     * @param tjfl 统计分类
     * @param subjectNo1 一级科目号
     * @param subjectNo2 二级科目号
     * @return
     */
    @AutoLog(value = "经营利润核算（科目设置）-删除")
    @ApiOperation(value = "经营利润核算（科目设置）-删除", notes = "经营利润核算（科目设置）-删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "szfl", required = true) String szfl,
                            @RequestParam(name = "tjfl", required = true) String tjfl,
                            @RequestParam(name = "subjectNo1", required = true) String subjectNo1,
                            @RequestParam(name = "subjectNo2", required = true) String subjectNo2) {
        UpdateWrapper<JylrhsKmsz> deleteWrapper = new UpdateWrapper();
//        deleteWrapper.eq("szfl",szfl);
//        deleteWrapper.eq("tjfl",tjfl);
        deleteWrapper.eq("subject_no1",subjectNo1);
        deleteWrapper.eq("subject_no2",subjectNo2);
        jylrhsKmszService.remove(deleteWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "经营利润核算（科目设置）-批量删除")
    @ApiOperation(value = "经营利润核算（科目设置）-批量删除", notes = "经营利润核算（科目设置）-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.jylrhsKmszService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "经营利润核算（科目设置）-通过id查询")
    @ApiOperation(value = "经营利润核算（科目设置）-通过id查询", notes = "经营利润核算（科目设置）-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        JylrhsKmsz jylrhsKmsz = jylrhsKmszService.getById(id);
        return Result.ok(jylrhsKmsz);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param jylrhsKmsz
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, JylrhsKmsz jylrhsKmsz) {
        return super.exportXls(request, jylrhsKmsz, JylrhsKmsz.class, "经营利润核算（科目设置）");
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
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "经营利润核算（科目设置）导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, JylrhsKmszVO.class);
        ExportParams exportParams = new ExportParams("经营利润核算（科目设置）", "科目设置");
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
        //return super.importExcel(request, response, JylrhsKmsz.class);
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
//                boolean checkResult = ExcelImportCheckUtil.check(fis, JylrhsKmszVO.class, params, 1.0);
//                if (!checkResult) {
//                    return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
//                }
                ExcelImportResult<JylrhsKmszVO> importResult = ExcelImportUtil.importExcelVerify(file, JylrhsKmszVO.class, JylrhsKmszVO.class, params);
                List<JylrhsKmsz> records = new ArrayList<>();
                List<JylrhsKmszVO> list = importResult.getList();
                for (JylrhsKmszVO kmsz : list) {
                    JylrhsKmsz record = new JylrhsKmsz();
                    BeanUtil.copyPropertiesIgnoreNull(kmsz, record);
                    record.setOprationType("0");
                    record.setOprationTime(new Date());
                    record.setOperator(getLoginUser().getUsername());
                    records.add(record);
                }
                if (!records.isEmpty()) {
                    /*for (JylrhsKmsz record : records) {
                        UpdateWrapper<JylrhsKmsz> deleteWrapper = new UpdateWrapper<>();
                        deleteWrapper.eq("subject_no1", record.getSubjectNo1());
                        deleteWrapper.eq("subject_no2", record.getSubjectNo2());
                        jylrhsKmszService.remove(deleteWrapper);
                    }*/
                    jylrhsKmszService.saveBatch(records);
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

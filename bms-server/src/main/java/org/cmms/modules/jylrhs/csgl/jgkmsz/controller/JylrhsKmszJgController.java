package org.cmms.modules.jylrhs.csgl.jgkmsz.controller;

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
import org.cmms.modules.jylrhs.csgl.jgkmsz.entity.JylrhsKmszJgVO;
import org.cmms.modules.jylrhs.csgl.jgkmsz.service.IJylrhsKmszJgService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.jylrhs.csgl.jgkmsz.verify.JylrhsJgkmszImportVerify;
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
 * @Description: 机构科目设置
 * @Author: jeecg-boot
 * @Date: 2023-06-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "机构科目设置")
@RestController
@RequestMapping("/jylrhs/csgl/jgkmsz")
public class JylrhsKmszJgController extends JeecgController<JylrhsKmszJg, IJylrhsKmszJgService> {
    @Autowired
    private IJylrhsKmszJgService jylrhsKmszJgService;
    @Autowired
    private JylrhsJgkmszImportVerify importVerify;

    /**
     * 分页列表查询
     *
     * @param jylrhsKmszJg
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "机构科目设置-分页列表查询")
    @ApiOperation(value = "机构科目设置-分页列表查询", notes = "机构科目设置-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(JylrhsKmszJg jylrhsKmszJg,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<JylrhsKmszJg> queryWrapper = QueryGenerator.initQueryWrapper(jylrhsKmszJg, req.getParameterMap());
        IPage pageList = PageUtil.toPage(IJylrhsKmszJgService.class, jylrhsKmszJgService, pageNo, pageSize, queryWrapper, "jgdm", "subject_no1", "subject_no2");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param jylrhsKmszJg
     * @return
     */
    @AutoLog(value = "机构科目设置-添加")
    @ApiOperation(value = "机构科目设置-添加", notes = "机构科目设置-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody JylrhsKmszJg jylrhsKmszJg) {
        try {
            QueryWrapper<JylrhsKmszJg> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("jgdm", jylrhsKmszJg.getJgdm());
            queryWrapper.eq("subject_no1", jylrhsKmszJg.getSubjectNo1());
            queryWrapper.eq("subject_no2", jylrhsKmszJg.getSubjectNo2());
            JylrhsKmszJg record = jylrhsKmszJgService.getOne(queryWrapper, false);
            if (record == null) {
                jylrhsKmszJg.setOperator(getLoginUser().getUsername());
                jylrhsKmszJg.setOprationType("1");
                jylrhsKmszJg.setOprationTime(new Date());
                jylrhsKmszJgService.save(jylrhsKmszJg);
                return Result.ok("添加成功！");
            } else {
                return Result.error("该机构科目设置已存在，请核实！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加失败！"+e.getMessage());
            return Result.error("添加失败！");
        }
    }

    /**
     * 校验数据是否在系统中是否存在
     *
     * @param jgdm       业务机构
     * @param subjectNo1 一级科目号
     * @param subjectNo2 二级科目号
     * @return
     */
    @AutoLog(value = "经营利润核算（机构科目设置）-添加数据校验")
    @ApiOperation(value = "经营利润核算（机构科目设置）-添加数据校验", notes = "经营利润核算（机构科目设置）-添加数据校验")
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Result<?> doDuplicateCheck(@RequestParam(name = "jgdm", required = true) String jgdm,
                                      @RequestParam(name = "subjectNo1", required = true) String subjectNo1,
                                      @RequestParam(name = "subjectNo2", required = true) String subjectNo2) {

        log.info("----duplicate check------：" + jgdm + "；" + subjectNo1 + "；" + subjectNo2);

        QueryWrapper<JylrhsKmszJg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("jgdm", jgdm);
        queryWrapper.eq("subject_no1", subjectNo1);
        queryWrapper.eq("subject_no2", subjectNo2);
        JylrhsKmszJg record = jylrhsKmszJgService.getOne(queryWrapper, false);
        if (record == null) {
            return Result.ok("该机构科目设置可用！");
        } else {
            return Result.error("该机构科目设置不可用，系统中已存在！");
        }
    }

    /**
     * 编辑
     *
     * @param jylrhsKmszJg
     * @return
     */
    @AutoLog(value = "机构科目设置-编辑")
    @ApiOperation(value = "机构科目设置-编辑", notes = "机构科目设置-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody JylrhsKmszJg jylrhsKmszJg) {
        UpdateWrapper<JylrhsKmszJg> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("jgdm", jylrhsKmszJg.getJgdm());
        updateWrapper.eq("subject_no1", jylrhsKmszJg.getSubjectNo1());
        updateWrapper.eq("subject_no2", jylrhsKmszJg.getSubjectNo2());
        jylrhsKmszJg.setJgdm(null);
        jylrhsKmszJg.setSubjectNo1(null);
        jylrhsKmszJg.setSubjectNo2(null);
        jylrhsKmszJg.setOprationType("2");
        jylrhsKmszJg.setOprationTime(new Date());
        jylrhsKmszJg.setOperator(getLoginUser().getUsername());
        jylrhsKmszJgService.update(jylrhsKmszJg, updateWrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 删除
     *
     * @param jgdm
     * @param subjectNo1
     * @param subjectNo2
     * @return
     */
    @AutoLog(value = "机构科目设置-删除")
    @ApiOperation(value = "机构科目设置-删除", notes = "机构科目设置-删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "jgdm", required = true) String jgdm,
                            @RequestParam(name = "subjectNo1", required = true) String subjectNo1,
                            @RequestParam(name = "subjectNo2", required = true) String subjectNo2) {
        UpdateWrapper<JylrhsKmszJg> deleteWrapper = new UpdateWrapper<>();
        deleteWrapper.eq("jgdm", jgdm);
        deleteWrapper.eq("subject_no1", subjectNo1);
        deleteWrapper.eq("subject_no2", subjectNo2);
        jylrhsKmszJgService.remove(deleteWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "机构科目设置-批量删除")
    @ApiOperation(value = "机构科目设置-批量删除", notes = "机构科目设置-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.jylrhsKmszJgService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "机构科目设置-通过id查询")
    @ApiOperation(value = "机构科目设置-通过id查询", notes = "机构科目设置-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        JylrhsKmszJg jylrhsKmszJg = jylrhsKmszJgService.getById(id);
        return Result.ok(jylrhsKmszJg);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param jylrhsKmszJg
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, JylrhsKmszJg jylrhsKmszJg) {
        return super.exportXls(request, jylrhsKmszJg, JylrhsKmszJg.class, "机构科目设置");
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
		modelAndView.addObject(NormalExcelConstants.FILE_NAME, "经营利润核算（机构科目设置）导入模板");
		modelAndView.addObject(NormalExcelConstants.CLASS, JylrhsKmszJgVO.class);
		ExportParams exportParams = new ExportParams("经营利润核算（机构科目设置）", "机构科目设置");
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
        //return super.importExcel(request, response, JylrhsKmszJg.class);
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
//                boolean checkResult = ExcelImportCheckUtil.check(fis, JylrhsKmszJgVO.class, params, 1.0);
//                if (!checkResult) {
//                    return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
//                }
                ExcelImportResult<JylrhsKmszJgVO> importResult = ExcelImportUtil.importExcelVerify(file, JylrhsKmszJgVO.class, JylrhsKmszJgVO.class, params);
                List<JylrhsKmszJg> records = new ArrayList<>();
                List<JylrhsKmszJgVO> list = importResult.getList();
                for (JylrhsKmszJgVO jgkmsz : list) {
                    JylrhsKmszJg record = new JylrhsKmszJg();
                    BeanUtil.copyPropertiesIgnoreNull(jgkmsz, record);
                    record.setOprationType("0");
                    record.setOprationTime(new Date());
                    record.setOperator(getLoginUser().getUsername());
                    records.add(record);
                }
                if (!records.isEmpty()) {
                    jylrhsKmszJgService.saveBatch(records);
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

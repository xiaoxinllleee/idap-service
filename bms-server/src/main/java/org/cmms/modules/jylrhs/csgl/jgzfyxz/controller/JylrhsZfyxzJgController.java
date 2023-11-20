package org.cmms.modules.jylrhs.csgl.jgzfyxz.controller;

import java.io.*;
import java.text.ParseException;
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
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.jylrhs.csgl.jgzfyxz.entity.JylrhsZfyxzJg;
import org.cmms.modules.jylrhs.csgl.jgzfyxz.entity.JylrhsZfyxzJgVO;
import org.cmms.modules.jylrhs.csgl.jgzfyxz.service.IJylrhsZfyxzJgService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.jylrhs.csgl.jgzfyxz.verify.JylrhsJgzfyxzImportVerify;
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
 * @Description: 机构费用上限
 * @Author: jeecg-boot
 * @Date: 2023-06-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "机构费用上限")
@RestController
@RequestMapping("/jylrhs/csgl/jgzfyxz")
public class JylrhsZfyxzJgController extends JeecgController<JylrhsZfyxzJg, IJylrhsZfyxzJgService> {
    @Autowired
    private IJylrhsZfyxzJgService jylrhsZfyxzJgService;
    @Autowired
    private JylrhsJgzfyxzImportVerify importVerify;

    /**
     * 分页列表查询
     *
     * @param jylrhsZfyxzJg
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "机构费用上限-分页列表查询")
    @ApiOperation(value = "机构费用上限-分页列表查询", notes = "机构费用上限-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(JylrhsZfyxzJg jylrhsZfyxzJg,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<JylrhsZfyxzJg> queryWrapper = QueryGenerator.initQueryWrapper(jylrhsZfyxzJg, req.getParameterMap());
        IPage pageList = PageUtil.toPage(IJylrhsZfyxzJgService.class, jylrhsZfyxzJgService, pageNo, pageSize, queryWrapper, "jgdm", "jznf");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param jylrhsZfyxzJg
     * @return
     */
    @AutoLog(value = "机构费用上限-添加")
    @ApiOperation(value = "机构费用上限-添加", notes = "机构费用上限-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody JylrhsZfyxzJg jylrhsZfyxzJg) {
        try {
            QueryWrapper<JylrhsZfyxzJg> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("jgdm", jylrhsZfyxzJg.getJgdm());
            queryWrapper.eq("jznf", jylrhsZfyxzJg.getJznf());
            JylrhsZfyxzJg record = jylrhsZfyxzJgService.getOne(queryWrapper, false);
            if (record == null) {
                jylrhsZfyxzJg.setOperator(getLoginUser().getUsername());
                jylrhsZfyxzJg.setOprationType("1");
                jylrhsZfyxzJg.setOprationTime(new Date());
                jylrhsZfyxzJgService.save(jylrhsZfyxzJg);
                return Result.ok("添加成功！");
            } else {
                return Result.error("该机构费用上限已存在，请核实！");
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
     * @param jgdm 业务机构
     * @param jznf 记账年份
     * @return
     */
    @AutoLog(value = "经营利润核算（机构费用上限）-添加数据校验")
    @ApiOperation(value = "经营利润核算（机构费用上限）-添加数据校验", notes = "经营利润核算（机构费用上限）-添加数据校验")
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Result<?> doDuplicateCheck(@RequestParam(name = "jgdm", required = true) String jgdm,
                                      @RequestParam(name = "jznf", required = true) String jznf) {

        log.info("----duplicate check------：" + jgdm + "；" + jznf);

        QueryWrapper<JylrhsZfyxzJg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("jgdm", jgdm);
        queryWrapper.eq("jznf", jznf);
        JylrhsZfyxzJg record = jylrhsZfyxzJgService.getOne(queryWrapper, false);
        if (record == null) {
            return Result.ok("该机构总费用限制可用！");
        } else {
            return Result.error("该机构总费用限制不可用，系统中已存在！");
        }
    }

    /**
     * 编辑
     *
     * @param jylrhsZfyxzJg
     * @return
     */
    @AutoLog(value = "机构费用上限-编辑")
    @ApiOperation(value = "机构费用上限-编辑", notes = "机构费用上限-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody JylrhsZfyxzJg jylrhsZfyxzJg) {
        UpdateWrapper<JylrhsZfyxzJg> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("jgdm", jylrhsZfyxzJg.getJgdm());
        updateWrapper.eq("jznf", jylrhsZfyxzJg.getJznf());
        jylrhsZfyxzJg.setJgdm(null);
        jylrhsZfyxzJg.setJznf(null);
        jylrhsZfyxzJg.setOprationType("2");
        jylrhsZfyxzJg.setOprationTime(new Date());
        jylrhsZfyxzJg.setOperator(getLoginUser().getUsername());
        jylrhsZfyxzJgService.update(jylrhsZfyxzJg,updateWrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 删除
     *
     * @param jgdm
     * @param jznf
     * @return
     */
    @AutoLog(value = "机构费用上限-删除")
    @ApiOperation(value = "机构费用上限-删除", notes = "机构费用上限-删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "jgdm", required = true) String jgdm,
                            @RequestParam(name = "jznf", required = true) String jznf) {
        UpdateWrapper<JylrhsZfyxzJg> deleteWrapper = new UpdateWrapper<>();
        deleteWrapper.eq("jgdm", jgdm);
        deleteWrapper.eq("jznf", jznf);
        jylrhsZfyxzJgService.remove(deleteWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "机构费用上限-批量删除")
    @ApiOperation(value = "机构费用上限-批量删除", notes = "机构费用上限-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.jylrhsZfyxzJgService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "机构费用上限-通过id查询")
    @ApiOperation(value = "机构费用上限-通过id查询", notes = "机构费用上限-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        JylrhsZfyxzJg jylrhsZfyxzJg = jylrhsZfyxzJgService.getById(id);
        return Result.ok(jylrhsZfyxzJg);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param jylrhsZfyxzJg
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, JylrhsZfyxzJg jylrhsZfyxzJg) {
        return super.exportXls(request, jylrhsZfyxzJg, JylrhsZfyxzJg.class, "机构费用上限");
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
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "经营利润核算（机构费用上限）导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, JylrhsZfyxzJgVO.class);
        ExportParams exportParams = new ExportParams("经营利润核算（机构费用上限）", "机构费用上限");
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
        // return super.importExcel(request, response, JylrhsZfyxzJg.class);
        JSONObject obj = new JSONObject();
        String filePaths = jsonObject.getString("filePaths");
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
//                boolean checkResult = ExcelImportCheckUtil.check(fis, JylrhsZfyxzJgVO.class, params, 1.0);
//                if (!checkResult) {
//                    return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
//                }
                ExcelImportResult<JylrhsZfyxzJgVO> importResult = ExcelImportUtil.importExcelVerify(file, JylrhsZfyxzJgVO.class, JylrhsZfyxzJgVO.class, params);
                List<JylrhsZfyxzJg> records = new ArrayList<>();
                List<JylrhsZfyxzJgVO> list = importResult.getList();
                for (JylrhsZfyxzJgVO jgkmsz : list) {
                    JylrhsZfyxzJg record = new JylrhsZfyxzJg();
                    BeanUtil.copyPropertiesIgnoreNull(jgkmsz, record);
                    record.setOprationType("0");
                    record.setOprationTime(new Date());
                    record.setOperator(getLoginUser().getUsername());
                    records.add(record);
                }
                if (!records.isEmpty()) {
                    jylrhsZfyxzJgService.saveBatch(records);
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

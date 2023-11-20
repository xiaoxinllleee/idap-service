package org.cmms.modules.jylrhs.csgl.zbk.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
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
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.jylrhs.csgl.zbk.entity.JylrhsZbk;
import org.cmms.modules.jylrhs.csgl.zbk.entity.JylrhsZbkVO;
import org.cmms.modules.jylrhs.csgl.zbk.service.IJylrhsZbkService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.jylrhs.csgl.zbk.verify.JylrhsZbkImportVerify;
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
 * @Description: 经营利润核算（指标库）
 * @Author: jeecg-boot
 * @Date: 2023-06-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "经营利润核算（指标库）")
@RestController
@RequestMapping("/jylrhs/csgl/zbk")
public class JylrhsZbkController extends JeecgController<JylrhsZbk, IJylrhsZbkService> {
    @Autowired
    private IJylrhsZbkService jylrhsZbkService;
    @Autowired
    private JylrhsZbkImportVerify importVerify;

    /**
     * 分页列表查询
     *
     * @param jylrhsZbk
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "经营利润核算（指标库）-分页列表查询")
    @ApiOperation(value = "经营利润核算（指标库）-分页列表查询", notes = "经营利润核算（指标库）-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(JylrhsZbk jylrhsZbk,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<JylrhsZbk> queryWrapper = QueryGenerator.initQueryWrapper(jylrhsZbk, req.getParameterMap());
        IPage pageList = PageUtil.toPage(IJylrhsZbkService.class, jylrhsZbkService, pageNo, pageSize, queryWrapper,"zbid");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param jylrhsZbk
     * @return
     */
    @AutoLog(value = "经营利润核算（指标库）-添加")
    @ApiOperation(value = "经营利润核算（指标库）-添加", notes = "经营利润核算（指标库）-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody JylrhsZbk jylrhsZbk) {
        try {
            //log.info("----经营利润核算 指标库 主键重复校验----"+jylrhsZbk.getZbid());
            QueryWrapper<JylrhsZbk> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zbid",jylrhsZbk.getZbid());
            JylrhsZbk record = jylrhsZbkService.getOne(queryWrapper,false);
            if (record == null) {
                jylrhsZbk.setOprationType("1");
                jylrhsZbk.setOperator(getLoginUser().getUsername());
                jylrhsZbk.setOprationTime(new Date());
                jylrhsZbkService.save(jylrhsZbk);
                return Result.ok("添加成功！");
            } else {
                return Result.error("该指标已存在，请核实！");
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
     * @param zbid
     * @return
     */
    @AutoLog(value = "经营利润核算（指标库）-添加数据校验")
    @ApiOperation(value = "经营利润核算（指标库）-添加数据校验", notes = "经营利润核算（指标库）-添加数据校验")
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Result<?> doDuplicateCheck(@RequestParam(name = "zbid") String zbid) {
        Long num = null;

        //String id = jsonObject.getString("id");
        log.info("----duplicate check------："+ zbid);

        num = jylrhsZbkService.duplicateCheckCount(zbid);
        if (num == null || num == 0) {
            // 该值可用
            return Result.ok("该值可用！");
        } else {
            // 该值不可用
//            log.info("该值不可用，系统中已存在！");
            return Result.error("该值不可用，系统中已存在！");
        }
    }

    /**
     * 编辑
     *
     * @param jylrhsZbk
     * @return
     */
    @AutoLog(value = "经营利润核算（指标库）-编辑")
    @ApiOperation(value = "经营利润核算（指标库）-编辑", notes = "经营利润核算（指标库）-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody JylrhsZbk jylrhsZbk) {
        UpdateWrapper<JylrhsZbk> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("zbid", jylrhsZbk.getZbid());
        jylrhsZbk.setZbid(null);
        jylrhsZbk.setOprationType("2");
        jylrhsZbk.setOprationTime(new Date());
        jylrhsZbk.setOperator(getLoginUser().getUsername());
        jylrhsZbkService.update(jylrhsZbk, updateWrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 启用/停用 状态编辑
     *
     * @param jylrhsZbk
     * @return
     */
    @AutoLog(value = "经营利润核算（指标库）-启用/停用")
    @ApiOperation(value = "经营利润核算（指标库）-启用/停用", notes = "经营利润核算（指标库）-启用/停用")
    @PutMapping(value = "/editEnable")
    public Result<?> editEnabel(@RequestBody JylrhsZbk jylrhsZbk) {
        UpdateWrapper<JylrhsZbk> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("zbid", jylrhsZbk.getZbid());
        jylrhsZbk.setZbid(null);
        jylrhsZbk.setKg(jylrhsZbk.getKg() == 1 ? 2 : 1);
        jylrhsZbk.setOprationType("2");
        jylrhsZbk.setOprationTime(new Date());
        jylrhsZbk.setOperator(getLoginUser().getUsername());
        jylrhsZbkService.update(jylrhsZbk, updateWrapper);
        return Result.ok("操作成功!");
    }

    /**
     * 通过id删除
     *
     * @param id 指标ID
     * @return
     */
    @AutoLog(value = "经营利润核算（指标库）-通过id删除")
    @ApiOperation(value = "经营利润核算（指标库）-通过id删除", notes = "经营利润核算（指标库）-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        UpdateWrapper<JylrhsZbk> deleteWrapper = new UpdateWrapper<>();
        deleteWrapper.eq("zbid", id);
        jylrhsZbkService.remove(deleteWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "经营利润核算（指标库）-批量删除")
    @ApiOperation(value = "经营利润核算（指标库）-批量删除", notes = "经营利润核算（指标库）-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.jylrhsZbkService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "经营利润核算（指标库）-通过id查询")
    @ApiOperation(value = "经营利润核算（指标库）-通过id查询", notes = "经营利润核算（指标库）-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        JylrhsZbk jylrhsZbk = jylrhsZbkService.getById(id);
        return Result.ok(jylrhsZbk);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param jylrhsZbk
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, JylrhsZbk jylrhsZbk) {
        return super.exportXls(request, jylrhsZbk, JylrhsZbk.class, "经营利润核算（指标库）");
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
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "经营利润核算（指标库）导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, JylrhsZbkVO.class);
        ExportParams exportParams = new ExportParams("经营利润核算（指标库）", "指标信息");
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
        //return super.importExcel(request, response, JylrhsZbk.class);
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
//                boolean checkResult = ExcelImportCheckUtil.check(fis, JylrhsZbkVO.class, params, 1.0);
//                if (!checkResult) {
//                    return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
//                }
                ExcelImportResult<JylrhsZbkVO> importResult = ExcelImportUtil.importExcelVerify(file, JylrhsZbkVO.class, JylrhsZbkVO.class, params);
                List<JylrhsZbk> records = new ArrayList<>();
                List<JylrhsZbkVO> list = importResult.getList();
                for (JylrhsZbkVO zbk : list) {
                    JylrhsZbk record = new JylrhsZbk();
                    BeanUtil.copyPropertiesIgnoreNull(zbk, record);
                    record.setKg(1);
                    record.setOprationType("0");
                    record.setOprationTime(new Date());
                    record.setOperator(getLoginUser().getUsername());
                    records.add(record);
                }
                if (!records.isEmpty()) {
                    for (JylrhsZbk record : records) {
                        UpdateWrapper<JylrhsZbk> deleteWrapper = new UpdateWrapper<>();
                        //TODO 暂时以指标ID为表主键，后续业务拓展再修改此处逻辑
                        deleteWrapper.eq("zbid", record.getZbid());
                        jylrhsZbkService.remove(deleteWrapper);
                    }
                    jylrhsZbkService.saveBatch(records);
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

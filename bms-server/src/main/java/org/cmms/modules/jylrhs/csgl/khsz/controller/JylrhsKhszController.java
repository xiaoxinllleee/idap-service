package org.cmms.modules.jylrhs.csgl.khsz.controller;

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
import org.cmms.modules.jylrhs.csgl.khsz.entity.JylrhsKhsz;
import org.cmms.modules.jylrhs.csgl.khsz.entity.JylrhsKhszVO;
import org.cmms.modules.jylrhs.csgl.khsz.service.IJylrhsKhszService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.jylrhs.csgl.khsz.verify.JylrhsKhszImportVerify;
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
 * @Description: 经营利润核算（考核设置）
 * @Author: jeecg-boot
 * @Date: 2023-06-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "经营利润核算（考核设置）")
@RestController
@RequestMapping("/jylrhs/csgl/khsz")
public class JylrhsKhszController extends JeecgController<JylrhsKhsz, IJylrhsKhszService> {
    @Autowired
    private IJylrhsKhszService jylrhsKhszService;
    @Autowired
    private JylrhsKhszImportVerify importVerify;

    /**
     * 分页列表查询
     *
     * @param jylrhsKhsz
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "经营利润核算（考核设置）-分页列表查询")
    @ApiOperation(value = "经营利润核算（考核设置）-分页列表查询", notes = "经营利润核算（考核设置）-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(JylrhsKhsz jylrhsKhsz,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<JylrhsKhsz> queryWrapper = QueryGenerator.initQueryWrapper(jylrhsKhsz, req.getParameterMap());
        IPage pageList = PageUtil.toPage(IJylrhsKhszService.class, jylrhsKhszService, pageNo, pageSize, queryWrapper,"jgdm","zbid","khzq");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param jylrhsKhsz
     * @return
     */
    @AutoLog(value = "经营利润核算（考核设置）-添加")
    @ApiOperation(value = "经营利润核算（考核设置）-添加", notes = "经营利润核算（考核设置）-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody JylrhsKhsz jylrhsKhsz) {
        try {
            QueryWrapper<JylrhsKhsz> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("jgdm",jylrhsKhsz.getJgdm());
            queryWrapper.eq("zbid",jylrhsKhsz.getZbid());
            queryWrapper.eq("khzq",jylrhsKhsz.getKhzq());
            JylrhsKhsz record = jylrhsKhszService.getOne(queryWrapper,false);
            if (record == null) {
                jylrhsKhsz.setOprationType("1");
                jylrhsKhsz.setOperator(getLoginUser().getUsername());
                jylrhsKhsz.setOprationTime(new Date());
                jylrhsKhszService.save(jylrhsKhsz);
                return Result.ok("添加成功！");
            } else {
                return Result.error("该考核设置已存在，请核实！");
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
     * @param zbid 指标
     * @param khzq 考核周期
     * @return
     */
    @AutoLog(value = "经营利润核算（考核设置）-添加数据校验")
    @ApiOperation(value = "经营利润核算（考核设置）-添加数据校验", notes = "经营利润核算（考核设置）-添加数据校验")
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Result<?> doDuplicateCheck(@RequestParam(name = "jgdm", required = true) String jgdm,
                                      @RequestParam(name = "zbid", required = true) String zbid,
                                      @RequestParam(name = "khzq", required = true) String khzq) {

        log.info("----duplicate check------："+ jgdm + "；" + zbid + "；" + khzq);

        QueryWrapper<JylrhsKhsz> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("jgdm",jgdm);
        queryWrapper.eq("zbid",zbid);
        queryWrapper.eq("khzq",khzq);
        JylrhsKhsz record = jylrhsKhszService.getOne(queryWrapper,false);
        if (record == null) {
            return Result.ok("该考核设置可用！");
        } else {
            return Result.error("该考核设置不可用，系统中已存在！");
        }
    }

    /**
     * 编辑
     *
     * @param jylrhsKhsz
     * @return
     */
    @AutoLog(value = "经营利润核算（考核设置）-编辑")
    @ApiOperation(value = "经营利润核算（考核设置）-编辑", notes = "经营利润核算（考核设置）-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody JylrhsKhsz jylrhsKhsz) {
        UpdateWrapper<JylrhsKhsz> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("jgdm", jylrhsKhsz.getJgdm());
        updateWrapper.eq("zbid", jylrhsKhsz.getZbid());
        //TODO 记账类型暂时摒弃使用 2023年6月7日
        //updateWrapper.eq("jzlx", jylrhsKhsz.getJzlx());
        updateWrapper.eq("khzq", jylrhsKhsz.getKhzq());
        jylrhsKhsz.setJgdm(null);
        jylrhsKhsz.setZbid(null);
        jylrhsKhsz.setKhzq(null);
        jylrhsKhsz.setOprationType("2");
        jylrhsKhsz.setOprationTime(new Date());
        jylrhsKhsz.setOperator(getLoginUser().getUsername());
        jylrhsKhszService.update(jylrhsKhsz,updateWrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 删除
     *
     * @param jgdm 业务机构
     * @param zbid 指标ID
     * @param khzq 考核周期
     * @return
     */
    @AutoLog(value = "经营利润核算（考核设置）-删除")
    @ApiOperation(value = "经营利润核算（考核设置）-删除", notes = "经营利润核算（考核设置）-删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "jgdm", required = true) String jgdm,
                            @RequestParam(name = "zbid", required = true) String zbid,
                            @RequestParam(name = "khzq", required = true) String khzq) {
        UpdateWrapper<JylrhsKhsz> deleteWrapper = new UpdateWrapper<>();
        deleteWrapper.eq("jgdm", jgdm);
        deleteWrapper.eq("zbid", zbid);
        deleteWrapper.eq("khzq", khzq);
        jylrhsKhszService.remove(deleteWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "经营利润核算（考核设置）-批量删除")
    @ApiOperation(value = "经营利润核算（考核设置）-批量删除", notes = "经营利润核算（考核设置）-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.jylrhsKhszService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "经营利润核算（考核设置）-通过id查询")
    @ApiOperation(value = "经营利润核算（考核设置）-通过id查询", notes = "经营利润核算（考核设置）-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        JylrhsKhsz jylrhsKhsz = jylrhsKhszService.getById(id);
        return Result.ok(jylrhsKhsz);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param jylrhsKhsz
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, JylrhsKhsz jylrhsKhsz) {
        return super.exportXls(request, jylrhsKhsz, JylrhsKhsz.class, "经营利润核算（考核设置）");
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
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "经营利润核算（考核设置）导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, JylrhsKhszVO.class);
        ExportParams exportParams = new ExportParams("经营利润核算（考核设置）", "考核设置");
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
        //return super.importExcel(request, response, JylrhsKhsz.class);
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
//                boolean checkResult = ExcelImportCheckUtil.check(fis, JylrhsKhszVO.class, params, 1.0);
//                if (!checkResult) {
//                    return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
//                }
                ExcelImportResult<JylrhsKhszVO> importResult = ExcelImportUtil.importExcelVerify(file, JylrhsKhszVO.class, JylrhsKhszVO.class, params);
                List<JylrhsKhsz> records = new ArrayList<>();
                List<JylrhsKhszVO> list = importResult.getList();
                for (JylrhsKhszVO khsz : list) {
                    JylrhsKhsz record = new JylrhsKhsz();
                    BeanUtil.copyPropertiesIgnoreNull(khsz, record);
                    record.setOprationType("0");
                    record.setOprationTime(new Date());
                    record.setOperator(getLoginUser().getUsername());
                    records.add(record);
                }
                if (!records.isEmpty()) {
                    for (JylrhsKhsz record : records) {
                        UpdateWrapper<JylrhsKhsz> deleteWrapper = new UpdateWrapper<>();
                        deleteWrapper.eq("jgdm", record.getJgdm());
                        deleteWrapper.eq("zbid", record.getZbid());
                        deleteWrapper.eq("khzq", record.getKhzq());
                        jylrhsKhszService.remove(deleteWrapper);
                    }
                    jylrhsKhszService.saveBatch(records);
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

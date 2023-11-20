package org.cmms.modules.jylrhs.jylrsj.fysjbl.controller;

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
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.jylrhs.jylrsj.fyftjz.verify.FyftjzImportVerifys;
import org.cmms.modules.jylrhs.jylrsj.fysjbl.entity.JylrhsFysjbl;
import org.cmms.modules.jylrhs.jylrsj.fysjbl.entity.JylrhsFysjblVO;
import org.cmms.modules.jylrhs.jylrsj.fysjbl.service.IJylrhsFysjblService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.jylrhs.jylrsj.fysjbl.verify.FysjblmportVerify;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysRole;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysRoleService;
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
 * @Description: 经营利润核算（费用数据补录）
 * @Author: jeecg-boot
 * @Date: 2023-06-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "经营利润核算（费用数据补录）")
@RestController
@RequestMapping("/jylrhs/jylrsj/fysjbl")
public class JylrhsFysjblController extends JeecgController<JylrhsFysjbl, IJylrhsFysjblService> {
    @Autowired
    private IJylrhsFysjblService jylrhsFysjblService;
    @Autowired
    private FysjblmportVerify importVerify;

    /**
     * 分页列表查询
     *
     * @param jylrhsFysjbl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "经营利润核算（费用数据补录）-分页列表查询")
    @ApiOperation(value = "经营利润核算（费用数据补录）-分页列表查询", notes = "经营利润核算（费用数据补录）-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(JylrhsFysjbl jylrhsFysjbl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<JylrhsFysjbl> queryWrapper = QueryGenerator.initQueryWrapper(jylrhsFysjbl, req.getParameterMap());
        IPage pageList = PageUtil.toPage(IJylrhsFysjblService.class, jylrhsFysjblService, pageNo, pageSize, queryWrapper, "fiscal_date", "jgdm", "zbid");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param jylrhsFysjbl
     * @return
     */
    @AutoLog(value = "经营利润核算（费用数据补录）-添加")
    @ApiOperation(value = "经营利润核算（费用数据补录）-添加", notes = "经营利润核算（费用数据补录）-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody JylrhsFysjbl jylrhsFysjbl) {
        try {
//            log.info("----经营利润核算 费用数据补录 主键重复校验----"+
//                    jylrhsFysjbl.getFiscalDate()+"；"+
//                    jylrhsFysjbl.getJgdm()+"；"+
//                    jylrhsFysjbl.getZbid());
            QueryWrapper<JylrhsFysjbl> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("fiscal_date",jylrhsFysjbl.getFiscalDate());
            queryWrapper.eq("jgdm",jylrhsFysjbl.getJgdm());
            queryWrapper.eq("zbid",jylrhsFysjbl.getZbid());
            JylrhsFysjbl record = jylrhsFysjblService.getOne(queryWrapper,false);
            if (record == null) {
                jylrhsFysjbl.setOprationType("1");
                jylrhsFysjbl.setOprationTime(new Date());
                jylrhsFysjbl.setOperator(getLoginUser().getUsername());
                jylrhsFysjblService.save(jylrhsFysjbl);
                return Result.ok("添加成功！");
            } else {
                return Result.error("该费用数据补录已存在，请核实！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加失败！"+e.getMessage());
            return Result.error("添加失败！");
        }
    }

    /**
     * 编辑
     *
     * @param jylrhsFysjbl
     * @return
     */
    @AutoLog(value = "经营利润核算（费用数据补录）-编辑")
    @ApiOperation(value = "经营利润核算（费用数据补录）-编辑", notes = "经营利润核算（费用数据补录）-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody JylrhsFysjbl jylrhsFysjbl) {
        UpdateWrapper<JylrhsFysjbl> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("fiscal_date", jylrhsFysjbl.getFiscalDate());
        updateWrapper.eq("jgdm", jylrhsFysjbl.getJgdm());
        updateWrapper.eq("zbid", jylrhsFysjbl.getZbid());
//        updateWrapper.eq("tzlx", jylrhsFysjbl.getTzlx());
        jylrhsFysjbl.setFiscalDate(null);
        jylrhsFysjbl.setJgdm(null);
        jylrhsFysjbl.setZbid(null);
//        jylrhsFysjbl.setTzlx(null);
        jylrhsFysjbl.setOprationType("2");
        jylrhsFysjbl.setOprationTime(new Date());
        jylrhsFysjbl.setOperator(getLoginUser().getUsername());
        jylrhsFysjblService.update(jylrhsFysjbl,updateWrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 删除
     *
     * @param fiscalDate 会计日期
     * @param jgdm 业务机构
     * @param zbid 指标ID
     * @param tzlx 调整类型
     * @return
     */
    @AutoLog(value = "经营利润核算（费用数据补录）-删除")
    @ApiOperation(value = "经营利润核算（费用数据补录）-删除", notes = "经营利润核算（费用数据补录）-删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "fiscalDate", required = true) String fiscalDate,
                            @RequestParam(name = "jgdm", required = true) String jgdm,
                            @RequestParam(name = "zbid", required = true) String zbid,
                            @RequestParam(name = "tzlx", required = true) String tzlx) {
        UpdateWrapper<JylrhsFysjbl> deleteWrapper = new UpdateWrapper<>();
        deleteWrapper.eq("fiscal_date", fiscalDate);
        deleteWrapper.eq("jgdm", jgdm);
        deleteWrapper.eq("zbid", zbid);
//        deleteWrapper.eq("tzlx", tzlx);
        jylrhsFysjblService.remove(deleteWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "经营利润核算（费用数据补录）-批量删除")
    @ApiOperation(value = "经营利润核算（费用数据补录）-批量删除", notes = "经营利润核算（费用数据补录）-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.jylrhsFysjblService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "经营利润核算（费用数据补录）-通过id查询")
    @ApiOperation(value = "经营利润核算（费用数据补录）-通过id查询", notes = "经营利润核算（费用数据补录）-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        JylrhsFysjbl jylrhsFysjbl = jylrhsFysjblService.getById(id);
        return Result.ok(jylrhsFysjbl);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param jylrhsFysjbl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, JylrhsFysjbl jylrhsFysjbl) {
        return super.exportXls(request, jylrhsFysjbl, JylrhsFysjbl.class, "经营利润核算（费用数据补录）");
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
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "经营利润核算（费用数据补录）导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, JylrhsFysjblVO.class);
        ExportParams exportParams = new ExportParams("经营利润核算（费用数据补录）", "费用数据补录");
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
        //return super.importExcel(request, response, JylrhsFysjbl.class);
        JSONObject obj = new JSONObject();
        String filePaths = jsonObject.getString("filePaths");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        if (StringUtils.isEmpty(filePaths)) {
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
//                boolean checkResult = ExcelImportCheckUtil.check(fis, JylrhsFysjblVO.class, params, 1.0);
//                if (!checkResult) {
//                    return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
//                }
                ExcelImportResult<JylrhsFysjblVO> importResult = ExcelImportUtil.importExcelVerify(file, JylrhsFysjblVO.class, JylrhsFysjblVO.class, params);
                List<JylrhsFysjbl> records = new ArrayList<>();
                List<JylrhsFysjblVO> list = importResult.getList();
                for (JylrhsFysjblVO fysjbl : list) {
                    JylrhsFysjbl record = new JylrhsFysjbl();
                    BeanUtil.copyPropertiesIgnoreNull(fysjbl, record);
                    record.setOprationType("0");
                    record.setOprationTime(new Date());
                    record.setOperator(getLoginUser().getUsername());
                    records.add(record);
                }
                if (!records.isEmpty()) {
                    for (JylrhsFysjbl record : records) {
                        UpdateWrapper<JylrhsFysjbl> deleteWrapper = new UpdateWrapper<>();
                        deleteWrapper.eq("fiscal_date", record.getFiscalDate());
                        deleteWrapper.eq("jgdm", record.getJgdm());
                        deleteWrapper.eq("zbid", record.getZbid());
//                        deleteWrapper.eq("tzlx", record.getTzlx());
                        jylrhsFysjblService.remove(deleteWrapper);
                    }
                    jylrhsFysjblService.saveBatch(records);
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

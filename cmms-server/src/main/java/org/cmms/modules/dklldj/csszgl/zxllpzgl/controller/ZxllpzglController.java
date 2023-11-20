package org.cmms.modules.dklldj.csszgl.zxllpzgl.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.dklldj.csszgl.zxllpzgl.entity.Zxllpzgl;
import org.cmms.modules.dklldj.csszgl.zxllpzgl.service.IZxllpzglService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.dklldj.csszgl.zxllpzgl.verify.ZxllpzglVerify;
import org.cmms.modules.dklldj.csszgl.zxllpzgl.vo.ZxllpzglVO;
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
 * @Description: 执行利率配置管理
 * @Author: Penghr
 * @Date: 2022-11-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "执行利率配置管理")
@RestController
@RequestMapping("/csszgl/zxllpzgl")
public class ZxllpzglController extends JeecgController<Zxllpzgl, IZxllpzglService> {
    @Autowired
    private IZxllpzglService iZxllpzglService;
    @Autowired
    private ZxllpzglVerify importVerify;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 分页列表查询
     *
     * @param zxllpzgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "执行利率配置管理-分页列表查询")
    @ApiOperation(value = "执行利率配置管理-分页列表查询", notes = "执行利率配置管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Zxllpzgl zxllpzgl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Zxllpzgl> queryWrapper = QueryGenerator.initQueryWrapper(zxllpzgl, req.getParameterMap());
        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
        log.info("当前地区法人行社编码（区域代码）："+qydm);
        qydm = "405";
        queryWrapper.eq("qydm",qydm);
        Page<Zxllpzgl> page = new Page<Zxllpzgl>(pageNo, pageSize);
        IPage<Zxllpzgl> pageList = iZxllpzglService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param zxllpzgl
     * @return
     */
    @AutoLog(value = "执行利率配置管理-添加")
    @ApiOperation(value = "执行利率配置管理-添加", notes = "执行利率配置管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Zxllpzgl zxllpzgl) {
        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
        //qydm = "405";
        zxllpzgl.setQydm(qydm);
        zxllpzgl.setLrbz(1);
        zxllpzgl.setLrr(getLoginUser().getUsername());
        zxllpzgl.setLrsj(new Date());
        iZxllpzglService.save(zxllpzgl);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param zxllpzgl
     * @return
     */
    @AutoLog(value = "执行利率配置管理-编辑")
    @ApiOperation(value = "执行利率配置管理-编辑", notes = "执行利率配置管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Zxllpzgl zxllpzgl) {
        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
        //qydm = "405";
        zxllpzgl.setQydm(qydm);
        zxllpzgl.setLrbz(2);
        zxllpzgl.setXgr(getLoginUser().getUsername());
        zxllpzgl.setXgsj(new Date());
        iZxllpzglService.updateById(zxllpzgl);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "执行利率配置管理-通过id删除")
    @ApiOperation(value = "执行利率配置管理-通过id删除", notes = "执行利率配置管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        iZxllpzglService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "执行利率配置管理-批量删除")
    @ApiOperation(value = "执行利率配置管理-批量删除", notes = "执行利率配置管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.iZxllpzglService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "执行利率配置管理-通过id查询")
    @ApiOperation(value = "执行利率配置管理-通过id查询", notes = "执行利率配置管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Zxllpzgl zxllpzgl = iZxllpzglService.getById(id);
        return Result.ok(zxllpzgl);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param zxllpzgl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Zxllpzgl zxllpzgl) {
        return super.exportXls(request, zxllpzgl, Zxllpzgl.class, "执行利率配置管理");
    }

    /**
     * 导出Excel导入模板
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "执行利率配置导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, ZxllpzglVO.class);
        ExportParams exportParams = new ExportParams("执行利率配置导入模板", "执行利率配置工作表");
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
        //return super.importExcel(request, response, Zxllpzgl.class);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String filePaths = jsonObject.getString("filePaths");
        String fiscal_date = "";
        if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
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
                boolean checkResult = ExcelImportCheckUtil.check(fis, ZxllpzglVO.class, params, 1.0);
                if (!checkResult) {
                    return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
                }
                ExcelImportResult<ZxllpzglVO> importResult = ExcelImportUtil.importExcelVerify(file, ZxllpzglVO.class, params);
                List<ZxllpzglVO> list = importResult.getList();
                List<Zxllpzgl> zxllpzglList = new ArrayList<>();
                for (ZxllpzglVO zxllpzglVO : list) {
                    Zxllpzgl zxllpzgl = new Zxllpzgl();
                    BeanUtil.copyPropertiesIgnoreNull(zxllpzglVO,zxllpzgl);
                    zxllpzglList.add(zxllpzgl);
                }
                if (!zxllpzglList.isEmpty()) {
                    iZxllpzglService.saveBatch(zxllpzglList);
                }
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();
                return Result.ok("文件导入完成！成功导入数据行数: " + list.size(), obj);
            } catch (Exception e) {
                log.error(e.getMessage(),e);
                return Result.error("文件导入失败:"+e.getMessage());
            } finally {
                IoUtil.close(fis);
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入失败！");
    }

}

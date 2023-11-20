package org.cmms.modules.report.sgtzgl.zfjsywtjbs.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.checkerframework.checker.units.qual.A;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.dkqmxgtgsh.entity.SgtzglDkqmxGtgsh;
import org.cmms.modules.report.sgtzgl.dkqmxgtgsh.entity.SgtzglDkqmxGtgshVo;
import org.cmms.modules.report.sgtzgl.zfjsywtjbs.entity.AdsRepSgtzZfjsywtjbs;
import org.cmms.modules.report.sgtzgl.zfjsywtjbs.entity.AdsRepSgtzZfjsywtjbsVo;
import org.cmms.modules.report.sgtzgl.zfjsywtjbs.service.IAdsRepSgtzZfjsywtjbsService;
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
 * @Description: 1104-手工台帐-支付结算业务统计表四
 * @Author: jeecg-boot
 * @Date: 2023-11-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "1104-手工台帐-支付结算业务统计表四")
@RestController
@RequestMapping("/zfjsywtjbs/adsRepSgtzZfjsywtjbs")
public class AdsRepSgtzZfjsywtjbsController extends JeecgController<AdsRepSgtzZfjsywtjbs, IAdsRepSgtzZfjsywtjbsService> {
    @Autowired
    private IAdsRepSgtzZfjsywtjbsService adsRepSgtzZfjsywtjbsService;

    /**
     * 分页列表查询
     *
     * @param adsRepSgtzZfjsywtjbs
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "1104-手工台帐-支付结算业务统计表四-分页列表查询")
    @ApiOperation(value = "1104-手工台帐-支付结算业务统计表四-分页列表查询", notes = "1104-手工台帐-支付结算业务统计表四-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(AdsRepSgtzZfjsywtjbs adsRepSgtzZfjsywtjbs,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<AdsRepSgtzZfjsywtjbs> queryWrapper = QueryGenerator.initQueryWrapper(adsRepSgtzZfjsywtjbs, req.getParameterMap());
        Page<AdsRepSgtzZfjsywtjbs> page = new Page<AdsRepSgtzZfjsywtjbs>(pageNo, pageSize);
        IPage<AdsRepSgtzZfjsywtjbs> pageList = PageUtil.toPage(IAdsRepSgtzZfjsywtjbsService.class, adsRepSgtzZfjsywtjbsService, pageNo, pageSize, queryWrapper, "fiscal_date", "xm");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param adsRepSgtzZfjsywtjbs
     * @return
     */
    @AutoLog(value = "1104-手工台帐-支付结算业务统计表四-添加")
    @ApiOperation(value = "1104-手工台帐-支付结算业务统计表四-添加", notes = "1104-手工台帐-支付结算业务统计表四-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody AdsRepSgtzZfjsywtjbs adsRepSgtzZfjsywtjbs) {
        adsRepSgtzZfjsywtjbsService.save(adsRepSgtzZfjsywtjbs);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param adsRepSgtzZfjsywtjbs
     * @return
     */
    @AutoLog(value = "1104-手工台帐-支付结算业务统计表四-编辑")
    @ApiOperation(value = "1104-手工台帐-支付结算业务统计表四-编辑", notes = "1104-手工台帐-支付结算业务统计表四-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody AdsRepSgtzZfjsywtjbs adsRepSgtzZfjsywtjbs) {
        adsRepSgtzZfjsywtjbsService.updateById(adsRepSgtzZfjsywtjbs);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "1104-手工台帐-支付结算业务统计表四-通过id删除")
    @ApiOperation(value = "1104-手工台帐-支付结算业务统计表四-通过id删除", notes = "1104-手工台帐-支付结算业务统计表四-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        adsRepSgtzZfjsywtjbsService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "1104-手工台帐-支付结算业务统计表四-批量删除")
    @ApiOperation(value = "1104-手工台帐-支付结算业务统计表四-批量删除", notes = "1104-手工台帐-支付结算业务统计表四-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.adsRepSgtzZfjsywtjbsService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "1104-手工台帐-支付结算业务统计表四-通过id查询")
    @ApiOperation(value = "1104-手工台帐-支付结算业务统计表四-通过id查询", notes = "1104-手工台帐-支付结算业务统计表四-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        AdsRepSgtzZfjsywtjbs adsRepSgtzZfjsywtjbs = adsRepSgtzZfjsywtjbsService.getById(id);
        return Result.ok(adsRepSgtzZfjsywtjbs);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param adsRepSgtzZfjsywtjbs
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AdsRepSgtzZfjsywtjbs adsRepSgtzZfjsywtjbs) {
        return super.exportXls(request, adsRepSgtzZfjsywtjbs, AdsRepSgtzZfjsywtjbs.class, "1104-手工台帐-支付结算业务统计表四");
    }

    /**
     * 导入模板
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
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "支付结算业务统计表四导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, AdsRepSgtzZfjsywtjbsVo.class);
        ExportParams exportParams = new ExportParams("支付结算业务统计表四导入模板", "模板信息");
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
        String fiscalDate = request.getParameter("fiscalDate");
        System.out.println(fiscalDate + "----sjrq----");
        if (StringUtils.isNotBlank(fiscalDate)){
        	QueryWrapper<AdsRepSgtzZfjsywtjbs> queryWrapper=new QueryWrapper<>();
        	queryWrapper.eq("fiscal_date",fiscalDate.replace("-",""));
        	adsRepSgtzZfjsywtjbsService.remove(queryWrapper);
		}
        String filePaths = jsonObject.getString("filePaths");
        if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
            return Result.error("请先上传文件！");
        }
        String[] filePathList = filePaths.split(",");
        JSONObject obj = new JSONObject();
        for (String filePath : filePathList) {
            String baseFilePath = uploadpath + File.separator + filePath;
//          MultipartFile file = entity.getValue();// 获取上传文件对象
            File file = new File(baseFilePath);
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            params.setHeadRows(1);
            params.setNeedSave(false);
            FileOutputStream fos = null;
            try {
                ExcelImportResult<AdsRepSgtzZfjsywtjbs> importResult = ExcelImportUtil.importExcelVerify(file, AdsRepSgtzZfjsywtjbs.class, AdsRepSgtzZfjsywtjbsVo.class, params);
                List<AdsRepSgtzZfjsywtjbs> list = importResult.getList();
                List<AdsRepSgtzZfjsywtjbs> qkmbList = new ArrayList<>();
                for (AdsRepSgtzZfjsywtjbs qkmb : list) {
                    Boolean con1 = StringUtils.isBlank(qkmb.getXm());
                    Boolean con2 = StringUtils.isNotBlank(qkmb.getXm()) && (qkmb.getXm().contains("合计") || qkmb.getXm().contains("条数"));
                    if (con1 || con2) {
                        continue;
                    }
                    qkmb.setFiscalDate(fiscalDate);
                    qkmbList.add(qkmb);
                }
                service.saveBatch(qkmbList);
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();
                return Result.ok("文件导入完成！成功导入数据行数:" + qkmbList.size(), obj);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入失败！");
    }


}

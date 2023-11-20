package org.cmms.modules.jylrhs.jylrsj.zhbpftfy.controller;

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
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.jylrhs.jylrsj.zhbpftfy.entity.JylrhsZhbpftfy;
import org.cmms.modules.jylrhs.jylrsj.zhbpftfy.entity.JylrhsZhbpftfyVO;
import org.cmms.modules.jylrhs.jylrsj.zhbpftfy.service.IJylrhsZhbpftfyService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.jylrhs.jylrsj.zhbpftfy.verify.ZhbpftfyImportVerify;
import org.cmms.modules.util.EtlUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 机关费用及支行报批分摊费用
 * @Author: jeecg-boot
 * @Date: 2023-09-12
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "机关费用及支行报批分摊费用")
@RestController
@RequestMapping("/jylrhs/jylrsj/zhbpftfy")
public class JylrhsZhbpftfyController extends JeecgController<JylrhsZhbpftfy, IJylrhsZhbpftfyService> {
    @Autowired
    private IJylrhsZhbpftfyService jylrhsZhbpftfyService;
    @Autowired
    private ZhbpftfyImportVerify importVerify;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;
    @Value("${liuyang.testsystem:false}")
    private String testsystem;

    /**
     * 分页列表查询
     *
     * @param jylrhsZhbpftfy
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "机关费用及支行报批分摊费用-分页列表查询")
    @ApiOperation(value = "机关费用及支行报批分摊费用-分页列表查询", notes = "机关费用及支行报批分摊费用-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(JylrhsZhbpftfy jylrhsZhbpftfy,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<JylrhsZhbpftfy> queryWrapper = QueryGenerator.initQueryWrapper(jylrhsZhbpftfy, req.getParameterMap());
        // Page<JylrhsZhbpftfy> page = new Page<JylrhsZhbpftfy>(pageNo, pageSize);
        // IPage<JylrhsZhbpftfy> pageList = jylrhsZhbpftfyService.page(page, queryWrapper);
        IPage pageList = PageUtil.toPage(IJylrhsZhbpftfyService.class, jylrhsZhbpftfyService, pageNo, pageSize, queryWrapper, "fiscal_date","ywjg");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param jylrhsZhbpftfy
     * @return
     */
    @AutoLog(value = "机关费用及支行报批分摊费用-添加")
    @ApiOperation(value = "机关费用及支行报批分摊费用-添加", notes = "机关费用及支行报批分摊费用-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody JylrhsZhbpftfy jylrhsZhbpftfy) {
        jylrhsZhbpftfyService.save(jylrhsZhbpftfy);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param jylrhsZhbpftfy
     * @return
     */
    @AutoLog(value = "机关费用及支行报批分摊费用-编辑")
    @ApiOperation(value = "机关费用及支行报批分摊费用-编辑", notes = "机关费用及支行报批分摊费用-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody JylrhsZhbpftfy jylrhsZhbpftfy) {
        jylrhsZhbpftfyService.updateById(jylrhsZhbpftfy);
        return Result.ok("编辑成功!");
    }

    /**
     * 删除
     *
     * @param fiscalDate 会计/记账日期
     * @param ywjg       业务机构(组织简称)
     * @return
     */
    @AutoLog(value = "机关费用及支行报批分摊费用-删除")
    @ApiOperation(value = "机关费用及支行报批分摊费用-删除", notes = "机关费用及支行报批分摊费用-删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "fiscal_date", required = true) String fiscalDate,
                            @RequestParam(name = "ywjg", required = true) String ywjg) {
        Date fiscal_date = DateUtil.string2Date(fiscalDate, "yyyy-MM-dd");
        UpdateWrapper deleteWrapper = new UpdateWrapper();
        deleteWrapper.eq("fiscal_date", fiscal_date);
        deleteWrapper.eq("ywjg", ywjg);
        jylrhsZhbpftfyService.remove(deleteWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "机关费用及支行报批分摊费用-批量删除")
    @ApiOperation(value = "机关费用及支行报批分摊费用-批量删除", notes = "机关费用及支行报批分摊费用-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.jylrhsZhbpftfyService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "机关费用及支行报批分摊费用-通过id查询")
    @ApiOperation(value = "机关费用及支行报批分摊费用-通过id查询", notes = "机关费用及支行报批分摊费用-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        JylrhsZhbpftfy jylrhsZhbpftfy = jylrhsZhbpftfyService.getById(id);
        return Result.ok(jylrhsZhbpftfy);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param jylrhsZhbpftfy
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, JylrhsZhbpftfy jylrhsZhbpftfy) {
        return super.exportXls(request, jylrhsZhbpftfy, JylrhsZhbpftfy.class, "机关费用及支行报批分摊费用");
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
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "机关费用及支行报批分摊费用导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, JylrhsZhbpftfyVO.class);
        ExportParams exportParams = new ExportParams("机关费用及支行报批分摊费用", "报批分摊费用");
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
//         return super.importExcel(request, response, JylrhsZhbpftfy.class);
//        String fiscalDateStr = request.getParameter("fiscalDate");
//        Date fiscalDate = DateUtils.str2Date(fiscalDateStr,new SimpleDateFormat("yyyy-MM-dd"));
//        log.info("@@@@@ 当前导入会计/记账日期："+fiscalDate);
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
                ExcelImportResult<JylrhsZhbpftfyVO> importResult = ExcelImportUtil.importExcelVerify(file, JylrhsZhbpftfyVO.class, JylrhsZhbpftfyVO.class, params);
                List<JylrhsZhbpftfy> records = new ArrayList<>();
                List<JylrhsZhbpftfyVO> list = importResult.getList();

                for (JylrhsZhbpftfyVO zhbpftfy : list) {
                    JylrhsZhbpftfy record = new JylrhsZhbpftfy();
                    BeanUtil.copyPropertiesIgnoreNull(zhbpftfy, record);
//                    record.setFiscalDate(fiscalDate);
                    record.setOprationType("0");
                    record.setOprationTime(new Date());
                    record.setOperator(getLoginUser().getUsername());
                    records.add(record);
                }
                String fiscal_date = "";
                if (!records.isEmpty()) {
                    jylrhsZhbpftfyService.saveBatch(records);
                    // 当月数据日期
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    fiscal_date = sdf.format(records.get(0).getFiscalDate());
                }
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();

                log.info("报批分摊费用-导入-会计/记账日期: "+fiscal_date);
                String operator = getLoginUser().getUsername();
                log.info("当前操作人员用户名：" + operator);
                // 导入报批分摊费用成功以后，自动更新当月业务机构代码，及报批分摊费用汇总，费用分摊记账抽取
                if ("true".equals(sfdsjpt)) {
                    HashMap<String, String> param = new HashMap<>();
                    param.put("fiscal_date", fiscal_date);
                    param.put("operator", operator);
                    String app_imp = "jylrhs_imp";
                    String app_test = "jylrhs_test";
                    if ("true".equalsIgnoreCase(testsystem)) {
                        //浏阳测试环境打开此配置
                        param.put("app",app_test);
                    } else {
                        //浏阳生产环境打开此配置
                        param.put("app",app_imp);
                    }
                    param.put("etl_task", "kiss.domain.application.jylrhs.proc_jylrhs_zhbpftfy_imp");
//                  boolean flag = EtlUtil.callEtl("jylrhs_common_init", params, 15);
                    boolean flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
                    log.info("报批分摊费用-业务机构代码转换、数据汇总、抽取-是否成功？-"+flag);
                } else {
                    log.info("报批分摊费用-导入-非大数据应用平台操作-未添加处理逻辑");
                }

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

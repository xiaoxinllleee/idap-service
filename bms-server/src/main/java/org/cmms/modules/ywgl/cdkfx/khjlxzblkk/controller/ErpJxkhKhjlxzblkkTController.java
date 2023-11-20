package org.cmms.modules.ywgl.cdkfx.khjlxzblkk.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.ywgl.cdkfx.khjlxzblkk.entity.ErpJxkhKhjlxzblkkImportVo;
import org.cmms.modules.ywgl.cdkfx.khjlxzblkk.entity.ErpJxkhKhjlxzblkkT;
import org.cmms.modules.ywgl.cdkfx.khjlxzblkk.service.IErpJxkhKhjlxzblkkTService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ywgl.cdkfx.khjlxzblkk.verify.KhjlxzblkkImportVerify;
import org.cmms.modules.ywgl.cdkfx.util.mapper.CallToolMapper;
import org.cmms.modules.ywgl.djkyw.djkxxgl.entity.Djkxxgl;
import org.cmms.modules.ywgl.djkyw.djkxxgl.entity.DjkxxglImportVo;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
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
 * @Description: 客户经理新增不良扣款
 * @Author: jeecg-boot
 * @Date: 2021-06-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "客户经理新增不良扣款")
@RestController
@RequestMapping("/khjlxzblkk/erpJxkhKhjlxzblkkT")
public class ErpJxkhKhjlxzblkkTController extends JeecgController<ErpJxkhKhjlxzblkkT, IErpJxkhKhjlxzblkkTService> {
    @Autowired
    private IErpJxkhKhjlxzblkkTService erpJxkhKhjlxzblkkTService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;
    @Value(value = "${common.path.upload}")
    private String uploadpath;

    @Autowired
    private KhjlxzblkkImportVerify khjlxzblkkImportVerify;

    /**
     * 分页列表查询
     *
     * @param erpJxkhKhjlxzblkkT
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "客户经理新增不良扣款-分页列表查询")
    @ApiOperation(value = "客户经理新增不良扣款-分页列表查询", notes = "客户经理新增不良扣款-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(ErpJxkhKhjlxzblkkT erpJxkhKhjlxzblkkT,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<ErpJxkhKhjlxzblkkT> queryWrapper = QueryGenerator.initQueryWrapper(erpJxkhKhjlxzblkkT, req.getParameterMap());
        IPage pageList = PageUtil.toPage(IErpJxkhKhjlxzblkkTService.class,erpJxkhKhjlxzblkkTService, pageNo, pageSize, queryWrapper, "jgdm","yggh");
        return Result.ok(pageList);
    }

    /**
     * 提取
     */
    @RequestMapping(value = "/count")
    public Result<?> count(String tjyf) {
        Result result = new Result<>();
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            HashMap<String, String> parm = new HashMap<>();
            parm.put("i_gzyf", tjyf.replaceAll("-", ""));
            parm.put("etl_task","kiss.domain.application.cdkyw.proc_ywgl_cdkfx_jxkh_khjlxzblkk_two");
            // count_ywgl_cdkfx_jxkh_khjlxzblkk_two
            boolean falg = EtlUtil.callEtl("cdkyw_common_init", parm, 15);
            result.setSuccess(falg);
        } else {
            try {
                erpJxkhKhjlxzblkkTService.pJxkhKhjlxzblkkTwo(tjyf);
                result.setSuccess(true);
                return result;
            } catch (Exception e) {
                System.out.println(e);
                log.error("提取失败", e.getMessage());
                result.setSuccess(false);
            }
        }
        return result;
    }

    /**
     * 添加
     *
     * @param erpJxkhKhjlxzblkkT
     * @return
     */
    @AutoLog(value = "客户经理新增不良扣款-添加")
    @ApiOperation(value = "客户经理新增不良扣款-添加", notes = "客户经理新增不良扣款-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody ErpJxkhKhjlxzblkkT erpJxkhKhjlxzblkkT) {
        erpJxkhKhjlxzblkkTService.save(erpJxkhKhjlxzblkkT);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param erpJxkhKhjlxzblkkT
     * @return
     */
    @AutoLog(value = "客户经理新增不良扣款-编辑")
    @ApiOperation(value = "客户经理新增不良扣款-编辑", notes = "客户经理新增不良扣款-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody ErpJxkhKhjlxzblkkT erpJxkhKhjlxzblkkT) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("JGDM", erpJxkhKhjlxzblkkT.getJgdm());
        queryWrapper.eq("YGGH", erpJxkhKhjlxzblkkT.getYggh());
        queryWrapper.eq("KHYF", erpJxkhKhjlxzblkkT.getKhyf());
        queryWrapper.eq("KHJLBZ", erpJxkhKhjlxzblkkT.getKhjlbz());
        erpJxkhKhjlxzblkkT.setLrsj(new Timestamp(System.currentTimeMillis()));
        erpJxkhKhjlxzblkkT.setLrbz(2);
        erpJxkhKhjlxzblkkT.setJgdm(null);
        erpJxkhKhjlxzblkkT.setYggh(null);
        erpJxkhKhjlxzblkkT.setGwbz(null);
        erpJxkhKhjlxzblkkT.setKhyf(null);
        erpJxkhKhjlxzblkkT.setKhjlbz(null);
        boolean update = erpJxkhKhjlxzblkkTService.update(erpJxkhKhjlxzblkkT, queryWrapper);
        if (!update) return Result.error("信息修改失败!");
        return Result.ok("信息修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "客户经理新增不良扣款-通过id删除")
    @ApiOperation(value = "客户经理新增不良扣款-通过id删除", notes = "客户经理新增不良扣款-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        erpJxkhKhjlxzblkkTService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "客户经理新增不良扣款-批量删除")
    @ApiOperation(value = "客户经理新增不良扣款-批量删除", notes = "客户经理新增不良扣款-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.erpJxkhKhjlxzblkkTService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "客户经理新增不良扣款-通过id查询")
    @ApiOperation(value = "客户经理新增不良扣款-通过id查询", notes = "客户经理新增不良扣款-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        ErpJxkhKhjlxzblkkT erpJxkhKhjlxzblkkT = erpJxkhKhjlxzblkkTService.getById(id);
        return Result.ok(erpJxkhKhjlxzblkkT);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param erpJxkhKhjlxzblkkT
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ErpJxkhKhjlxzblkkT erpJxkhKhjlxzblkkT) {
        return super.exportXls(request, erpJxkhKhjlxzblkkT, ErpJxkhKhjlxzblkkT.class, "客户经理新增不良扣款");
    }


    /**
     * 导出模板excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "客户经理新增不良扣款导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, ErpJxkhKhjlxzblkkImportVo.class);
        ExportParams exportParams = new ExportParams("客户经理新增不良扣款导入模板", "模板信息");
        modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
        modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
        return modelAndView;

    }


    /**
     * 通过excel导入数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        return importExcelByTemplate(jsonObject, request, response, ErpJxkhKhjlxzblkkT.class,ErpJxkhKhjlxzblkkImportVo.class, khjlxzblkkImportVerify);
    }


    public Result<?> importExcelByTemplate(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response, Class<ErpJxkhKhjlxzblkkT> clazz, IExcelVerifyHandler verifyHandler) {
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
            params.setVerifyHanlder(verifyHandler);
            FileOutputStream fos = null;
            try {
                ExcelImportResult<ErpJxkhKhjlxzblkkT> importResult = ExcelImportUtil.importExcelVerify(file, clazz, params);
                List<ErpJxkhKhjlxzblkkT> list = importResult.getList();
                //service.saveBatch(list);
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();
                return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
            } catch (Exception e) {
                log.error(e.getMessage(),e);
                return Result.error("文件导入失败:"+e.getMessage());
            } finally {
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入失败！");
    }
}

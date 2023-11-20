package org.cmms.modules.ywgl.djkyw.djkwdgl.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.ywgl.djkyw.djkrygl.entity.Djkrygl;
import org.cmms.modules.ywgl.djkyw.djkrygl.entity.DjkryglImportVo;
import org.cmms.modules.ywgl.djkyw.djkrygl.verify.DjkryglImportVerify;
import org.cmms.modules.ywgl.djkyw.djkwdgl.entity.Djkwdgl;
import org.cmms.modules.ywgl.djkyw.djkwdgl.entity.DjkwdglImportVo;
import org.cmms.modules.ywgl.djkyw.djkwdgl.service.IDjkwdglService;
import org.cmms.modules.ywgl.djkyw.djkwdgl.verify.DjkwdglImportVerify;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
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
 * @Description: 贷记卡网点关联
 * @Author: jeecg-boot
 * @Date: 2021-12-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "贷记卡网点关联")
@RestController
@RequestMapping("/djkwdgl/djkwdgl")
public class DjkwdglController extends JeecgController<Djkwdgl, IDjkwdglService> {
    @Autowired
    private IDjkwdglService djkwdglService;
    @Autowired
    private DjkwdglImportVerify djkwdglImportVerify;
    @Autowired
    private ISysDictService iSysDictService;

    /**
     * 分页列表查询
     *
     * @param djkwdgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "贷记卡网点关联-分页列表查询")
    @ApiOperation(value = "贷记卡网点关联-分页列表查询", notes = "贷记卡网点关联-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Djkwdgl djkwdgl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Djkwdgl> queryWrapper = QueryGenerator.initQueryWrapper(djkwdgl, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IDjkwdglService.class, djkwdglService, pageNo, pageSize, queryWrapper, "jgdm", "tgjgbh");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param djkwdgl
     * @return
     */
    @AutoLog(value = "贷记卡网点关联-添加")
    @ApiOperation(value = "贷记卡网点关联-添加", notes = "贷记卡网点关联-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Djkwdgl djkwdgl) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        QueryWrapper<Djkwdgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("jgdm", djkwdgl.getJgdm());
        queryWrapper.eq("tgjgbh", djkwdgl.getTgjgbh());
        Djkwdgl one = djkwdglService.getOne(queryWrapper);
        if (one != null) {
            return Result.error("数据已存在，请勿重复添加！");
        }
        String jgmc = iSysDictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION", "ZZJC", "YWJGDM", djkwdgl.getJgdm());
        if (jgmc == null || jgmc.equals("")) {
            return Result.error("机构信息不存在！");
        }
        djkwdgl.setLrbz(1);
        djkwdgl.setLrczy(loginUser.getUsername());
        djkwdgl.setLrsj(new Date());
        djkwdglService.save(djkwdgl);
        return Result.ok("添加成功！");
    }

    /**
     * 贷记卡网点关联 / 通过机构代码、推广机构编号删除
     *
     * @param
     * @return
     */
    @AutoLog(value = "贷记卡网点关联-通过机构代码、推广机构编号删除")
    @ApiOperation(value = "贷记卡网点关联-通过机构代码、推广机构编号删除", notes = "贷记卡网点关联-通过机构代码、推广机构编号删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@Param("jgdm") String jgdm, @Param("tgjgbh") String tgjgbh) {
        QueryWrapper<Djkwdgl> queryWrapper = new QueryWrapper<Djkwdgl>();
        queryWrapper.eq("jgdm", jgdm);
        queryWrapper.eq("tgjgbh", tgjgbh);
        djkwdglService.remove(queryWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "贷记卡网点关联-通过id查询")
    @ApiOperation(value = "贷记卡网点关联-通过id查询", notes = "贷记卡网点关联-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Djkwdgl djkwdgl = djkwdglService.getById(id);
        return Result.ok(djkwdgl);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param djkwdgl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Djkwdgl djkwdgl) {
        return super.exportXls(request, djkwdgl, Djkwdgl.class, "贷记卡网点关联");
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
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "贷记卡网点关联导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, DjkwdglImportVo.class);
        ExportParams exportParams = new ExportParams("贷记卡网点关联导入模板", "模板信息");
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
        return super.importExcelByTemplate(jsonObject, request, response, Djkwdgl.class,DjkwdglImportVo.class, djkwdglImportVerify);
    }

}

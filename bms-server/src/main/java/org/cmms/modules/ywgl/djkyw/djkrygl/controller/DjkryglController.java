package org.cmms.modules.ywgl.djkyw.djkrygl.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasStaffPostVo;
import org.cmms.modules.hr.zzgl.gwxxgl.service.IHrBasPostService;
import org.cmms.modules.ywgl.djkyw.djkrygl.entity.Djkrygl;
import org.cmms.modules.ywgl.djkyw.djkrygl.entity.DjkryglImportVo;
import org.cmms.modules.ywgl.djkyw.djkrygl.service.IDjkryglService;
import org.cmms.modules.ywgl.djkyw.djkrygl.verify.DjkryglImportVerify;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description: 贷记卡人员关联
 * @Author: jeecg-boot
 * @Date: 2021-12-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "贷记卡人员关联")
@RestController
@RequestMapping("/djkrygl/djkrygl")
public class DjkryglController extends JeecgController<Djkrygl, IDjkryglService> {
    @Autowired
    private IDjkryglService djkryglService;
    @Autowired
    IHrBasPostService iHrBasPostService;
    @Autowired
    private IHrBasStaffService hrBasStaffService;
    @Autowired
    private DjkryglImportVerify dkzhzyImportVerify;

    /**
     * 分页列表查询
     *
     * @param djkrygl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "贷记卡人员关联-分页列表查询")
    @ApiOperation(value = "贷记卡人员关联-分页列表查询", notes = "贷记卡人员关联-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Djkrygl djkrygl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Djkrygl> queryWrapper = QueryGenerator.initQueryWrapper(djkrygl, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IDjkryglService.class, djkryglService, pageNo, pageSize, queryWrapper, "yggh", "tgrybh");
        return Result.ok(pageList);
    }

    /**
     * 查找带回
     */
    @AutoLog(value = "查找带回")
    @ApiOperation(value = "查找带回", notes = "查找带回")
    @PostMapping(value = "/getListClaim")
    public Result<?> getListClaim(@RequestBody JSONObject jsonObject) {
        String yggh = jsonObject.getString("yggh");
        String gyh = jsonObject.getString("gyh");
        String khjlbz = jsonObject.getString("khjlbz");
        List<HrBasStaffPostVo> list = iHrBasPostService.getYggwxx(yggh, gyh, khjlbz);
        return Result.ok(list);
    }

    /**
     * 添加
     *
     * @param djkrygl
     * @return
     */
    @AutoLog(value = "贷记卡人员关联-添加")
    @ApiOperation(value = "贷记卡人员关联-添加", notes = "贷记卡人员关联-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Djkrygl djkrygl) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        QueryWrapper<Djkrygl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("yggh", djkrygl.getYggh());
        queryWrapper.eq("tgrybh", djkrygl.getTgrybh());
        Djkrygl one = djkryglService.getOne(queryWrapper);
        if (one != null) {
            return Result.error("数据已存在，请勿重复添加！");
        }

        QueryWrapper queryWrapperYggh = new QueryWrapper();
        queryWrapperYggh.eq("yggh", djkrygl.getYggh());
        HrBasStaff staff = hrBasStaffService.getOne(queryWrapperYggh);
        if (staff == null) {
            return Result.error("员工信息不存在！");
        }


        djkrygl.setLrczy(loginUser.getUsername());
        djkrygl.setLrbz(1);
        djkrygl.setLrsj(new Date());

        djkryglService.save(djkrygl);
        return Result.ok("添加成功！");
    }

    /**
     * 贷记卡人员关联 / 通过员工工号、推广人员编号删除
     *
     * @param
     * @return
     */
    @AutoLog(value = "贷记卡人员关联-通过员工工号、推广人员编号删除")
    @ApiOperation(value = "贷记卡人员关联-通过员工工号、推广人员编号删除", notes = "贷记卡人员关联-通过员工工号、推广人员编号删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@Param("yggh") String yggh, @Param("tgrybh") String tgrybh) {
        QueryWrapper<Djkrygl> queryWrapper = new QueryWrapper<Djkrygl>();
        queryWrapper.eq("yggh", yggh);
        queryWrapper.eq("tgrybh", tgrybh);
        djkryglService.remove(queryWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "贷记卡人员关联-通过id查询")
    @ApiOperation(value = "贷记卡人员关联-通过id查询", notes = "贷记卡人员关联-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Djkrygl djkrygl = djkryglService.getById(id);
        return Result.ok(djkrygl);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param djkrygl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Djkrygl djkrygl) {
        return super.exportXls(request, djkrygl, Djkrygl.class, "贷记卡人员关联");
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
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "贷记卡人员关联导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, DjkryglImportVo.class);
        ExportParams exportParams = new ExportParams("贷记卡人员关联导入模板", "模板信息");
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
        return super.importExcelByTemplate(jsonObject, request, response, Djkrygl.class,DjkryglImportVo.class, dkzhzyImportVerify);
    }
}

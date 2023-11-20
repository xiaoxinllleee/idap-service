package org.cmms.modules.ywgl.djkyw.djkkhzr.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.ywgl.djkyw.djkkhzr.entity.Djkkhzr;
import org.cmms.modules.ywgl.djkyw.djkkhzr.entity.DjkkhzrImportVo;
import org.cmms.modules.ywgl.djkyw.djkkhzr.service.IDjkkhzrService;
import org.cmms.modules.ywgl.djkyw.djkkhzr.verify.DjkhzrImportVerify;
import org.cmms.modules.ywgl.djkyw.djkxxgl.entity.Djkxxgl;
import org.cmms.modules.ywgl.djkyw.djkxxgl.entity.DjkxxglImportVo;
import org.cmms.modules.ywgl.djkyw.djkxxgl.service.IDjkxxglService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
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
 * @Description: 贷记卡考核责任
 * @Author: jeecg-boot
 * @Date: 2021-12-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "贷记卡考核责任")
@RestController
@RequestMapping("/djkkhzr/djkkhzr")
public class DjkkhzrController extends JeecgController<Djkkhzr, IDjkkhzrService> {
    @Autowired
    private IDjkkhzrService djkkhzrService;
    @Autowired
    private IDjkxxglService djkxxglService;
    @Autowired
    private IHrBasStaffService hrBasStaffService;
    @Autowired
    private DjkhzrImportVerify djkxxlImportVerify;
    @Autowired
    private RedisUtil redisUtil;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param djkkhzr
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "贷记卡考核责任-分页列表查询")
    @ApiOperation(value = "贷记卡考核责任-分页列表查询", notes = "贷记卡考核责任-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Djkkhzr djkkhzr,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Djkkhzr> queryWrapper = QueryGenerator.initQueryWrapper(djkkhzr, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IDjkkhzrService.class,djkkhzrService, pageNo, pageSize, queryWrapper, "jgdm", "jobnumber", "djkkh");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param djkkhzr
     * @return
     */
    @AutoLog(value = "贷记卡考核责任-添加")
    @ApiOperation(value = "贷记卡考核责任-添加", notes = "贷记卡考核责任-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Djkkhzr djkkhzr) {
        if (StringUtils.isEmpty(djkkhzr.getDjkkh())) {
            return Result.error("贷记卡卡号不能为空!");
        }
        QueryWrapper<Djkkhzr> djkkhzrQueryWrapper = new QueryWrapper<>();
        djkkhzrQueryWrapper.eq("djkkh", djkkhzr.getDjkkh());
        List<Djkkhzr> djkkhzrList = djkkhzrService.list(djkkhzrQueryWrapper);
        if (!djkkhzrList.isEmpty()) {
            return Result.error("贷记卡卡号已经存在!");
        }

        QueryWrapper<Djkxxgl> djkxxglQueryWrapper = new QueryWrapper<>();
        djkxxglQueryWrapper.eq("acct_no", djkkhzr.getDjkkh());
        List<Djkxxgl> djkxxglList = djkxxglService.list(djkxxglQueryWrapper);
        if (djkxxglList.isEmpty()) {
            return Result.error("贷记卡卡号在贷记卡信息中不存在!");
        }
        Djkxxgl djkxxgl = djkxxglList.get(0);

        Djkkhzr djkkhzrInsert = new Djkkhzr();
        djkkhzrInsert.setDjkkh(djkkhzr.getDjkkh());
        djkkhzrInsert.setKhmc(djkxxgl.getCustName());
        djkkhzrInsert.setJgdm(djkxxgl.getOrg());
        djkkhzrInsert.setZjhm(djkxxgl.getCtfcCd());
        djkkhzrInsert.setCustid(djkxxgl.getCustManagerId());

        if (!StringUtils.isEmpty(djkxxgl.getCustManagerId())) {
            QueryWrapper<HrBasStaff> staffQueryWrapper = new QueryWrapper<>();
            staffQueryWrapper.eq("khjlbh", djkxxgl.getCustManagerId());
            List<HrBasStaff> staffList = hrBasStaffService.list(staffQueryWrapper);
            if (!staffList.isEmpty()) {
                djkkhzrInsert.setJobnumber(staffList.get(0).getYggh());
            }
        }
        if (djkkhzr.getZzbz() != null) {
            djkkhzrInsert.setZzbz(djkkhzr.getZzbz());
            djkkhzrInsert.setZzczy(getUsername());
            djkkhzrInsert.setZzrq(djkkhzr.getZzrq());
        }

        if (!StringUtils.isEmpty(djkkhzr.getCustidzr())) {
            djkkhzrInsert.setCustidzr(djkkhzr.getCustidzr());
            djkkhzrInsert.setJobnumberzr(djkkhzr.getJobnumberzr());
            QueryWrapper<HrBasStaff> staffQueryWrapper = new QueryWrapper<>();
            staffQueryWrapper.eq("khjlbh", djkkhzr.getCustidzr());
            List<HrBasStaff> staffList = hrBasStaffService.list(staffQueryWrapper);
            if (!staffList.isEmpty()) {
                djkkhzrInsert.setJobnumberzr(staffList.get(0).getYggh());
            } else {
                return Result.error("员工信息表中没有客户经理标识【" + djkkhzr.getCustidzr() + "】的员工信息！");
            }
        }
        djkkhzrInsert.setLrbz(1);
        djkkhzrInsert.setLrczy(getUsername());
        djkkhzrInsert.setLrsj(new Date());
        djkkhzrInsert.setDflag(0);
        djkkhzrService.save(djkkhzrInsert);

        //调用存储更新贷款责任人
        String tjrq = DateUtil.formatDateTime("yyyyMMdd", DateUtil.getFrontDayTime(7));
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            HashMap<String, String> param = new HashMap<>();
            param.put("i_tjyf", tjrq);
            param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_modm_dkyeb_upzrr");
            // count_cdkfx_p_modm_dkyeb_upzrr
            boolean flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            if (flag) {
                tjrq = DateUtil.formatDateTime("yyyyMMdd");
                param = new HashMap<>();
                param.put("i_tjyf", tjrq);
                param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_modm_dkyeb_upzrr");
                // count_cdkfx_p_modm_dkyeb_upzrr
                flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            }
            Result.ok(flag);
        } else {
            djkkhzrService.CallPkgUpdateDkzrr(tjrq);
            tjrq = DateUtil.formatDateTime("yyyyMMdd");
            djkkhzrService.CallPkgUpdateDkzrr(tjrq);
        }
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param djkkhzr
     * @return
     */
    @AutoLog(value = "贷记卡考核责任-编辑")
    @ApiOperation(value = "贷记卡考核责任-编辑", notes = "贷记卡考核责任-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Djkkhzr djkkhzr) {
        if (StringUtils.isEmpty(djkkhzr.getDjkkh())) {
            return Result.error("贷记卡卡号不能为空!");
        }
        QueryWrapper<Djkkhzr> djkkhzrQueryWrapper = new QueryWrapper<>();
        djkkhzrQueryWrapper.eq("djkkh", djkkhzr.getDjkkh());
        List<Djkkhzr> djkkhzrList = djkkhzrService.list(djkkhzrQueryWrapper);
        if (djkkhzrList.isEmpty()) {
            return Result.error("卡号不存在!");
        }
        Djkkhzr djkkhzrUpdate = djkkhzrList.get(0);
        if (djkkhzr.getZzbz() != null) {
            djkkhzrUpdate.setZzczy(getUsername());
            djkkhzrUpdate.setZzrq(djkkhzr.getZzrq());
        } else {
            djkkhzrUpdate.setZzczy("");
            djkkhzrUpdate.setZzrq(null);
        }
        djkkhzrUpdate.setZzbz(djkkhzr.getZzbz());
        if (StringUtils.isEmpty(djkkhzr.getCustidzr())) {
            djkkhzrUpdate.setCustidzr("");
            djkkhzrUpdate.setJobnumberzr("");
        } else {
            djkkhzrUpdate.setCustidzr(djkkhzr.getCustidzr());
            djkkhzrUpdate.setJobnumberzr(djkkhzr.getJobnumberzr());
            QueryWrapper<HrBasStaff> staffQueryWrapper = new QueryWrapper<>();
            staffQueryWrapper.eq("khjlbh", djkkhzr.getCustidzr());
            List<HrBasStaff> staffList = hrBasStaffService.list(staffQueryWrapper);
            if (!staffList.isEmpty()) {
                djkkhzrUpdate.setJobnumberzr(staffList.get(0).getYggh());
            } else {
                return Result.error("员工信息表中没有客户经理标识【" + djkkhzr.getCustidzr() +
                        "】的员工信息！");
            }
        }
        UpdateWrapper<Djkkhzr> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("djkkh", djkkhzr.getDjkkh());
        djkkhzrUpdate.setLrbz(2);
        djkkhzrUpdate.setLrczy(getUsername());
        djkkhzrUpdate.setLrsj(new Date());
        djkkhzrService.update(djkkhzrUpdate, updateWrapper);

        //调用存储更新贷款责任人
        String tjrq = DateUtil.formatDateTime("yyyyMMdd", DateUtil.getFrontDayTime(7));
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            HashMap<String, String> param = new HashMap<>();
            param.put("i_tjyf", tjrq);
            param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_modm_dkyeb_upzrr");
            // count_cdkfx_p_modm_dkyeb_upzrr
            boolean flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            if (flag) {
                tjrq = DateUtil.formatDateTime("yyyyMMdd");
                param = new HashMap<>();
                param.put("i_tjyf", tjrq);
                param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_modm_dkyeb_upzrr");
                // count_cdkfx_p_modm_dkyeb_upzrr
                flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            }
            Result.ok(flag);
        } else {
            djkkhzrService.CallPkgUpdateDkzrr(tjrq);
            tjrq = DateUtil.formatDateTime("yyyyMMdd");
            djkkhzrService.CallPkgUpdateDkzrr(tjrq);
        }
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param djkkh
     * @return
     */
    @AutoLog(value = "贷记卡考核责任-通过id删除")
    @ApiOperation(value = "贷记卡考核责任-通过id删除", notes = "贷记卡考核责任-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "djkkh", required = true) String djkkh) {
        QueryWrapper<Djkkhzr> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("djkkh", djkkh);
        djkkhzrService.remove(queryWrapper);

        //调用存储更新贷款责任人
        String tjrq = DateUtil.formatDateTime("yyyyMMdd", DateUtil.getFrontDayTime(7));
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            HashMap<String, String> param = new HashMap<>();
            param.put("i_tjyf", tjrq);
            param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_modm_dkyeb_upzrr");
            // count_cdkfx_p_modm_dkyeb_upzrr
            boolean flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            if (flag) {
                tjrq = DateUtil.formatDateTime("yyyyMMdd");
                param = new HashMap<>();
                param.put("i_tjyf", tjrq);
                param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_modm_dkyeb_upzrr");
                // count_cdkfx_p_modm_dkyeb_upzrr
                flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            }
            Result.ok(flag);
        } else {
            djkkhzrService.CallPkgUpdateDkzrr(tjrq);
            tjrq = DateUtil.formatDateTime("yyyyMMdd");
            djkkhzrService.CallPkgUpdateDkzrr(tjrq);
        }
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "贷记卡考核责任-批量删除")
    @ApiOperation(value = "贷记卡考核责任-批量删除", notes = "贷记卡考核责任-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.djkkhzrService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "贷记卡考核责任-通过id查询")
    @ApiOperation(value = "贷记卡考核责任-通过id查询", notes = "贷记卡考核责任-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Djkkhzr djkkhzr = djkkhzrService.getById(id);
        return Result.ok(djkkhzr);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param djkkhzr
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Djkkhzr djkkhzr) {
        return super.exportXls(request, djkkhzr, Djkkhzr.class, "贷记卡考核责任");
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
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "贷记卡考核责任导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, DjkkhzrImportVo.class);
        ExportParams exportParams = new ExportParams("贷记卡考核责任导入模板", "模板信息");
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
        return super.importExcelByTemplate(jsonObject, request, response, Djkkhzr.class,DjkkhzrImportVo.class, djkxxlImportVerify);
    }


}

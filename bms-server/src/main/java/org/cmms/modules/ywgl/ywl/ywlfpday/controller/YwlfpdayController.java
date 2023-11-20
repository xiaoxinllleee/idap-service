package org.cmms.modules.ywgl.ywl.ywlfpday.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasPost;
import org.cmms.modules.hr.zzgl.gwxxgl.service.IHrBasPostService;
import org.cmms.modules.util.DateUtil;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.ywgl.ywl.ywlfp.entity.Ywlfp;
import org.cmms.modules.ywgl.ywl.ywlfp.service.IYwlfpService;
import org.cmms.modules.ywgl.ywl.ywlfpday.entity.YwlfpVo;
import org.cmms.modules.ywgl.ywl.ywlfpday.entity.Ywlfpday;
import org.cmms.modules.ywgl.ywl.ywlfpday.service.IYwlfpdayService;
import org.cmms.modules.ywgl.ywl.ywltqytj.service.IYwltqytjService;
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
 * @Description: 业务量分配
 * @Author: jeecg-boot
 * @Date: 2021-12-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "业务量分配")
@RestController
@RequestMapping("/ywlfpday/ywlfpday")
public class YwlfpdayController extends JeecgController<Ywlfpday, IYwlfpdayService> {
    @Autowired
    private IYwlfpdayService ywlfpdayService;
    @Autowired
    private IHrBasStaffPostService hrBasStaffPostService;
    @Autowired
    private IYwlfpService ywlfpService;
    @Autowired
    private IHrBasPostService hrBasPostService;
    @Autowired
    private IYwltqytjService ywltqytjService;

    /**
     * 分页列表查询
     *
     * @param ywlfpday
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "业务量分配-分页列表查询")
    @ApiOperation(value = "业务量分配-分页列表查询", notes = "业务量分配-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Ywlfpday ywlfpday,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Ywlfpday> queryWrapper = QueryGenerator.initQueryWrapper(ywlfpday, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IYwlfpdayService.class, ywlfpdayService, pageNo, pageSize, queryWrapper, "fpid");
        return Result.ok(pageList);
    }

    /**
     * 员工入岗管理查询
     */
    @AutoLog(value = "员工入岗管理-分页列表查询")
    @ApiOperation(value = "员工入岗管理-分页列表查询", notes = "员工入岗管理-分页列表查询")
    @GetMapping(value = "/yglist")
    public Result<?> queryYgList(HrBasStaffPost hrBasStaffPost,
                                 @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                 String zzbz, String tjrq,
                                 HttpServletRequest req) throws ParseException {
        QueryWrapper<HrBasStaffPost> queryWrapper = QueryGenerator.initQueryWrapper(hrBasStaffPost, req.getParameterMap());
        queryWrapper.eq("zzbz", zzbz);
        queryWrapper.apply("(rgrq<=to_date(" + tjrq.replace("-", "") + ",'yyyymmdd'))");
        queryWrapper.apply("(lgrq is null or lgrq>=to_date(" + tjrq.replace("-", "") + ",'yyyymmdd'))");
        queryWrapper.eq("sfcykh", "1");
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IHrBasStaffPostService.class, hrBasStaffPostService, pageNo, pageSize, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 查詢岗位信息
     */
    @GetMapping("/gwlist")
    public Result<?> gwlist(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        QueryWrapper<HrBasPost> queryWrapper = new QueryWrapper();
        queryWrapper.eq("sfqy", "1");
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IHrBasPostService.class, hrBasPostService, pageNo, pageSize, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 撤回分配
     */
    @AutoLog(value = "业务量分配-撤回分配")
    @ApiOperation(value = "业务量分配-撤回分配", notes = "业务量分配-撤回分配")
    @PostMapping(value = "/rollback")
    public Result<?> rollback(@RequestBody JSONObject jsonObject) {
        String fpid = jsonObject.getString("fpid");
        String zzbz= jsonObject.getString("zzbz");
        String fprq= jsonObject.getString("fprq");
        if (StringUtils.isNotBlank(fpid)) {
            //查询业务量分配明细
            QueryWrapper<Ywlfp> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("fpid", fpid);
            List<Ywlfp> ywlfpList = ywlfpService.list(queryWrapper);
            if (CollUtil.isNotEmpty(ywlfpList)) {
                //删除业务量分配明细
                ywlfpService.remove(queryWrapper);
                //更新待分配表的待分配标志为未分配
                ywlfpdayService.updateStatus(fpid);
                //重新统计员工业务量
                HashMap<String, String> param = new HashMap<>();
                param.put("i_tjyf", fprq.replace("-",""));
                param.put("i_zzbz", zzbz);
                param.put("etl_task","kiss.domain.application.cdkyw.proc_ywltq_p_tj_ygywl");
                // count_ywltq_p_tj_ygywl
                log.info("---------------------开始执行员工业务量统计---------------------");
                Boolean flag=EtlUtil.callEtl("cdkyw_common_init", param, 15);
                log.info("---------------------执行员工业务量统计结束，结果："+flag+"---------------------");
                return Result.ok();
            }
            return Result.error("没有选择日期的业务量分配信息!!!");
        }
        return Result.error("参数错误!!!");
    }

    /**
     * 获取按月统计业务量
     */
    @GetMapping("/getFpxxByMonth")
    public Result<?> getFpxxByMonth(@RequestParam("zzbz") String zzbz,
                                    @RequestParam("tjrq") String tjrq) {
        if (StringUtils.isNotBlank(zzbz) && StringUtils.isNotBlank(tjrq)) {
            Ywlfpday ywlfpday = ywlfpdayService.getFpxxByMonth(zzbz, tjrq.replace("-", ""));
            return Result.ok(ywlfpday);
        }
        return Result.error("参数错误!!!");
    }

    /**
     * 添加
     *
     * @param ywlfpday
     * @return
     */
    @AutoLog(value = "业务量分配-添加")
    @ApiOperation(value = "业务量分配-添加", notes = "业务量分配-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Ywlfpday ywlfpday) {
        ywlfpdayService.save(ywlfpday);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     * lx 1：分配到天 2：按月分配到全行 3;按月分配到支行
     */
    @AutoLog(value = "业务量分配-编辑")
    @ApiOperation(value = "业务量分配-编辑", notes = "业务量分配-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody YwlfpVo ywlfpVo) {
        String atmYwbs = ywltqytjService.getAssessParamValue("P90056");
        String atmXjll = ywltqytjService.getAssessParamValue("P90057");
        String qtYwbs = ywltqytjService.getAssessParamValue("P90058");
        String qtXjll = ywltqytjService.getAssessParamValue("P90059");
        if ("1".equals(ywlfpVo.getLx())) {
            if (CollUtil.isEmpty(ywlfpVo.getYgghList())) {
                return Result.error("参数错误!平均分配时请至少选择一位员工！");
            }
            if ("1".equals(atmYwbs)) {
                ywlfpVo.setAtmywbs(new BigDecimal("0"));
            }
            if ("1".equals(atmXjll)) {
                ywlfpVo.setAtmxjll(new BigDecimal("0"));
            }
            if ("1".equals(qtYwbs)) {
                ywlfpVo.setQtywbs(new BigDecimal("0"));
            }
            if ("1".equals(qtXjll)) {
                ywlfpVo.setQtxjll(new BigDecimal("0"));
            }
            BigDecimal ygrs = BigDecimal.valueOf((int) ywlfpVo.getYgghList().size());
            ywlfpVo.setAtmywbs(ywlfpVo.getAtmywbs().divide(ygrs));
            ywlfpVo.setAtmxjll(ywlfpVo.getAtmxjll().divide(ygrs));
            ywlfpVo.setQtywbs(ywlfpVo.getQtywbs().divide(ygrs));
            ywlfpVo.setQtxjll(ywlfpVo.getQtxjll().divide(ygrs));
            ywlfpVo.setYgghListString(String.join(",", ywlfpVo.getYgghList()));
            ywlfpVo.setFprq(ywlfpVo.getFprq().replace("-", ""));
            ywlfpVo.setFpczy(getUsername());
            //将分配结果存入分配明细表中
            ywlfpService.insertYwlmxBYday(ywlfpVo);
            //更新业务分配表状态
            ywlfpdayService.updateYwlfpStatus(ywlfpVo.getFpid(), null, getUsername(), "1", null);
        } else if ("2".equals(ywlfpVo.getLx())) {
            if (CollUtil.isEmpty(ywlfpVo.getGwxxList())) {
                return Result.error("参数错误!平均分配时请至少选择一个岗位！");
            }
            //将分配结果存入分配明细表中
            ywlfpService.insertYwlmxQhBYMonth(atmYwbs, atmXjll, qtYwbs, qtXjll, getUsername(), String.join(",", ywlfpVo.getGwxxList()), ywlfpVo.getFprq().replace("-", ""));
            //更新业务分配表状态
            ywlfpdayService.updateYwlfpStatus(null, ywlfpVo.getFprq().replace("-",""), getUsername(), "2", null);
            ywlfpVo.setZzbz(null);
        } else if ("3".equals(ywlfpVo.getLx())) {
            if (CollUtil.isEmpty(ywlfpVo.getYgghList())) {
                return Result.error("参数错误!平均分配时请至少选择一位员工！");
            }
            //将分配结果存入分配明细表中
            ywlfpService.insertYwlmxZhBYMonth(atmYwbs, atmXjll, qtYwbs, qtXjll, ywlfpVo.getYgghList().size(), getUsername(), ywlfpVo.getZzbz(), ywlfpVo.getFprq().replace("-", ""),String.join(",", ywlfpVo.getYgghList()));
            //更新业务分配表状态
            ywlfpdayService.updateYwlfpStatus(null, ywlfpVo.getFprq().replace("-",""), getUsername(), "3", ywlfpVo.getZzbz());
        }
        HashMap<String, String> param = new HashMap<>();
        param.put("i_tjyf", ywlfpVo.getFprq().replace("-",""));
        param.put("i_zzbz", ywlfpVo.getZzbz());
        param.put("etl_task","kiss.domain.application.cdkyw.proc_ywltq_p_tj_ygywl");
        // count_ywltq_p_tj_ygywl
        log.info("---------------------开始执行员工业务量统计---------------------");
        Boolean flag=EtlUtil.callEtl("cdkyw_common_init", param, 15);
        log.info("---------------------执行员工业务量统计结束，结果："+flag+"---------------------");
        return Result.ok("分配成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "业务量分配-通过id删除")
    @ApiOperation(value = "业务量分配-通过id删除", notes = "业务量分配-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        ywlfpdayService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "业务量分配-批量删除")
    @ApiOperation(value = "业务量分配-批量删除", notes = "业务量分配-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.ywlfpdayService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "业务量分配-通过id查询")
    @ApiOperation(value = "业务量分配-通过id查询", notes = "业务量分配-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Ywlfpday ywlfpday = ywlfpdayService.getById(id);
        return Result.ok(ywlfpday);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param ywlfpday
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Ywlfpday ywlfpday) {
        return super.exportXls(request, ywlfpday, Ywlfpday.class, "业务量分配");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Ywlfpday.class);
    }

}

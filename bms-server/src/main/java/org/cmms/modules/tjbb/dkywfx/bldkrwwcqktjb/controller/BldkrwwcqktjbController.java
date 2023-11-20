package org.cmms.modules.tjbb.dkywfx.bldkrwwcqktjb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.tjbb.dkywfx.bldkrwwcqktjb.entity.Bldkrwwcqktjb;
import org.cmms.modules.tjbb.dkywfx.bldkrwwcqktjb.service.IBldkrwwcqktjbService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.util.EtlUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: 不良贷款任务完成情况统计表
 * @Author: Penghr
 * @Date: 2022-12-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "不良贷款任务完成情况统计表")
@RestController
@RequestMapping("/tjbb/dkywfx/bldkwwcqktjb")
public class BldkrwwcqktjbController extends JeecgController<Bldkrwwcqktjb, IBldkrwwcqktjbService> {
    @Autowired
    private IBldkrwwcqktjbService bldkwwcqktjbService;
    @Value("${com.etl.sfdsjpt:false}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param bldkrwwcqktjb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "不良贷款任务完成情况统计表-分页列表查询")
    @ApiOperation(value = "不良贷款任务完成情况统计表-分页列表查询", notes = "不良贷款任务完成情况统计表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Bldkrwwcqktjb bldkrwwcqktjb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Bldkrwwcqktjb> queryWrapper = QueryGenerator.initQueryWrapper(bldkrwwcqktjb, req.getParameterMap());
        IPage<Bldkrwwcqktjb> pageList = PageUtil.toPage(IBldkrwwcqktjbService.class, bldkwwcqktjbService, pageNo, pageSize, queryWrapper, "data_date");
        return Result.ok(pageList);
    }

    /**
     * 不良贷款任务完成情况统计表-数据统计
     *
     * @param requestbody
     * @return
     */
    @AutoLog(value = "不良贷款任务完成情况统计表-数据统计")
    @ApiOperation(value = "不良贷款任务完成情况统计表-数据统计", notes = "不良贷款任务完成情况统计表-数据统计")
    @RequestMapping(value = "/statistics")
    public Result<?> statistics(@RequestBody Bldkrwwcqktjb requestbody) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            int lastDay = c.getActualMinimum(Calendar.DAY_OF_MONTH);//获取月最大天数
            //本期日期
            String bqrq = simpleDateFormat.format(requestbody.getDataDate());
            log.info("信贷资产结构质量统计表（一）-数据统计-本期日期：" + bqrq);
            //年初日期
//            c.setTime(requestbody.getDataDate());
//            c.clear(Calendar.MONTH);
//            c.set(Calendar.DAY_OF_MONTH, 1);
//            String ncrq = simpleDateFormat.format(c.getTime());
//            log.info("信贷资产结构质量统计表（一）-数据统计-年初日期：" + ncrq);
            //上月月底
//            c.set(Calendar.YEAR, Integer.parseInt(bqrq.substring(0, 4)));
//            c.set(Calendar.MONTH, Integer.parseInt(bqrq.substring(5, 7)) - 1);
//            c.set(Calendar.DAY_OF_MONTH, lastDay);
//            c.add(Calendar.DATE, -1);
//            String syyd = simpleDateFormat.format(c.getTime());
//            log.info("信贷资产结构质量统计表（一）-数据统计-上月日期：" + syyd);

            if ("true".equals(sfdsjpt)) {
                HashMap<String, String> params = new HashMap<>();
                params.put("fiscal_date", bqrq);
//                params.put("ncrq", ncrq);
//                params.put("syyd", syyd);
                params.put("etl_task", "kiss.domain.application.tjbb.proc_tjbb_fxb_bldkrwwcqktj");
                log.info("数据统计ETL参数传递：" + params);
                boolean flag = EtlUtil.callEtl("tjbb_common_init", params, 15);
                log.info("不良贷款任务完成情况统计表-数据统计-是否成功？-" + flag);
                if (flag) {
                    return Result.ok("数据统计成功！");
                } else {
                    return Result.error("数据统计失败，请联系管理员处理！");
                }
            } else {
                log.info("不良贷款任务完成情况统计表-数据统计-未做Oracle数据提取");
                return Result.ok();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("数据统计失败！" + throwable.getMessage());
            return Result.error("数据统计失败，请联系系统管理员！");
        }
    }

    /**
     * 添加
     *
     * @param bldkwwcqktjb
     * @return
     */
//    @AutoLog(value = "不良贷款任务完成情况统计表-添加")
//    @ApiOperation(value = "不良贷款任务完成情况统计表-添加", notes = "不良贷款任务完成情况统计表-添加")
//    @PostMapping(value = "/add")
//    public Result<?> add(@RequestBody Bldkwwcqktjb bldkwwcqktjb) {
//        bldkwwcqktjbService.save(bldkwwcqktjb);
//        return Result.ok("添加成功！");
//    }

    /**
     * 编辑
     *
     * @param bldkwwcqktjb
     * @return
     */
//    @AutoLog(value = "不良贷款任务完成情况统计表-编辑")
//    @ApiOperation(value = "不良贷款任务完成情况统计表-编辑", notes = "不良贷款任务完成情况统计表-编辑")
//    @PutMapping(value = "/edit")
//    public Result<?> edit(@RequestBody Bldkwwcqktjb bldkwwcqktjb) {
//        bldkwwcqktjbService.updateById(bldkwwcqktjb);
//        return Result.ok("编辑成功!");
//    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
//    @AutoLog(value = "不良贷款任务完成情况统计表-通过id删除")
//    @ApiOperation(value = "不良贷款任务完成情况统计表-通过id删除", notes = "不良贷款任务完成情况统计表-通过id删除")
//    @DeleteMapping(value = "/delete")
//    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
//        bldkwwcqktjbService.removeById(id);
//        return Result.ok("删除成功!");
//    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
//    @AutoLog(value = "不良贷款任务完成情况统计表-批量删除")
//    @ApiOperation(value = "不良贷款任务完成情况统计表-批量删除", notes = "不良贷款任务完成情况统计表-批量删除")
//    @DeleteMapping(value = "/deleteBatch")
//    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
//        this.bldkwwcqktjbService.removeByIds(Arrays.asList(ids.split(",")));
//        return Result.ok("批量删除成功！");
//    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
//    @AutoLog(value = "不良贷款任务完成情况统计表-通过id查询")
//    @ApiOperation(value = "不良贷款任务完成情况统计表-通过id查询", notes = "不良贷款任务完成情况统计表-通过id查询")
//    @GetMapping(value = "/queryById")
//    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
//        Bldkwwcqktjb bldkwwcqktjb = bldkwwcqktjbService.getById(id);
//        return Result.ok(bldkwwcqktjb);
//    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        // return super.exportXls(request, bldkrwwcqktjb, Bldkrwwcqktjb.class, "不良贷款任务完成情况统计表");
        QueryWrapper<Bldkrwwcqktjb> queryWrapper = new QueryWrapper<>();
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                Bldkrwwcqktjb bldkrwwcqktjb = JSON.parseObject(deString, Bldkrwwcqktjb.class);
                queryWrapper = QueryGenerator.initQueryWrapper(bldkrwwcqktjb, request.getParameterMap());
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("导出错误！不良贷款任务完成情况统计表："+throwable.getMessage());
        }
        queryWrapper.orderByAsc("data_date", "jgbm");
        List<Bldkrwwcqktjb> pageList = bldkwwcqktjbService.list(queryWrapper);

        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "新邵农商银行不良贷款任务完成情况统计表");
        mv.addObject(NormalExcelConstants.CLASS, Bldkrwwcqktjb.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("不良贷款任务完成情况统计表", "导出人:" + getLoginUser().getRealname() + "（单位/万元、%）", "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
//    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
//    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//        return super.importExcel(request, response, Bldkwwcqktjb.class);
//    }

}

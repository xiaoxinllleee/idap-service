package org.cmms.modules.jylrhs.tjbb.fyfthz.zh.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.jylrhs.jylrsj.jylrhz.entity.JylrhsJylrhz;
import org.cmms.modules.jylrhs.tjbb.fyfthz.zh.entity.JylrhsFyfthzZh;
import org.cmms.modules.jylrhs.tjbb.fyfthz.zh.service.IJylrhsFyfthzZhService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.util.EtlUtil;
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
 * @Description: 费用分摊（支行汇总）
 * @Author: jeecg-boot
 * @Date: 2023-06-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "费用分摊（支行汇总）")
@RestController
@RequestMapping("/jylrhs/tjbb/fyfthz/zh")
public class JylrhsFyfthzZhController extends JeecgController<JylrhsFyfthzZh, IJylrhsFyfthzZhService> {
    @Autowired
    private IJylrhsFyfthzZhService jylrhsFyfthzZhService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;
    @Value("${liuyang.testsystem:false}")
    private String testsystem;

    /**
     * 分页列表查询
     *
     * @param jylrhsFyfthzZh
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "费用分摊（支行汇总）-分页列表查询")
    @ApiOperation(value = "费用分摊（支行汇总）-分页列表查询", notes = "费用分摊（支行汇总）-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(JylrhsFyfthzZh jylrhsFyfthzZh,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<JylrhsFyfthzZh> queryWrapper = QueryGenerator.initQueryWrapper(jylrhsFyfthzZh, req.getParameterMap());
        IPage pageList = PageUtil.toPage(IJylrhsFyfthzZhService.class, jylrhsFyfthzZhService, pageNo, pageSize, queryWrapper, "fiscal_date", "jgdm");
        return Result.ok(pageList);
    }

    /**
     * 统计
     *
     * @return
     */
    @AutoLog(value = "经营利润核算（费用分摊（支行汇总））-统计")
    @ApiOperation(value = "经营利润核算（费用分摊（支行汇总））-统计", notes = "经营利润核算（费用分摊（支行汇总））-统计")
    @RequestMapping(value = "/initData")
    public Result<?> initData(@RequestBody JylrhsFyfthzZh fyfthzZh) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String fiscal_date = sdf.format(fyfthzZh.getFiscalDate());
            log.info("当前统计数据日期：" + fiscal_date);
            String operator = getLoginUser().getUsername();
            log.info("当前操作人员用户名：" + operator);
            if ("true".equals(sfdsjpt)) {
                HashMap<String, String> params = new HashMap<>();
                params.put("fiscal_date", fiscal_date);
                params.put("operator", operator);
                String app_imp = "jylrhs_imp";
                String app_test = "jylrhs_test";
                if ("true".equalsIgnoreCase(testsystem)) {
                    //浏阳测试环境打开此配置
                    params.put("app",app_test);
                } else {
                    //浏阳生产环境打开此配置
                    params.put("app",app_imp);
                }
                params.put("etl_task", "kiss.domain.application.jylrhs.proc_jylrhs_fyfthz_zh");
//                boolean flag = EtlUtil.callEtl("jylrhs_common_init", params, 15);
                boolean flag = EtlUtil.callEtl("cdkyw_common_init", params, 15);
                log.info("经营利润核算-费用分摊（支行汇总）-统计-是否成功？-" + flag);
                if (flag) {
                    return Result.ok("统计成功！");
                } else {
                    return Result.error("统计失败，请联系管理员处理！");
                }
            } else {
                log.info("未做Oracle数据统计！");
                return Result.ok();
            }
        } catch (Throwable e) {
            log.info("经营利润核算（费用分摊（支行汇总））-统计失败！" + e.getMessage());
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param jylrhsFyfthzZh
     * @return
     */
    @AutoLog(value = "费用分摊（支行汇总）-添加")
    @ApiOperation(value = "费用分摊（支行汇总）-添加", notes = "费用分摊（支行汇总）-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody JylrhsFyfthzZh jylrhsFyfthzZh) {
        jylrhsFyfthzZhService.save(jylrhsFyfthzZh);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param jylrhsFyfthzZh
     * @return
     */
    @AutoLog(value = "费用分摊（支行汇总）-编辑")
    @ApiOperation(value = "费用分摊（支行汇总）-编辑", notes = "费用分摊（支行汇总）-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody JylrhsFyfthzZh jylrhsFyfthzZh) {
        jylrhsFyfthzZhService.updateById(jylrhsFyfthzZh);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "费用分摊（支行汇总）-通过id删除")
    @ApiOperation(value = "费用分摊（支行汇总）-通过id删除", notes = "费用分摊（支行汇总）-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        jylrhsFyfthzZhService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "费用分摊（支行汇总）-批量删除")
    @ApiOperation(value = "费用分摊（支行汇总）-批量删除", notes = "费用分摊（支行汇总）-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.jylrhsFyfthzZhService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "费用分摊（支行汇总）-通过id查询")
    @ApiOperation(value = "费用分摊（支行汇总）-通过id查询", notes = "费用分摊（支行汇总）-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        JylrhsFyfthzZh jylrhsFyfthzZh = jylrhsFyfthzZhService.getById(id);
        return Result.ok(jylrhsFyfthzZh);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param jylrhsFyfthzZh
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, JylrhsFyfthzZh jylrhsFyfthzZh) {
        return super.exportXls(request, jylrhsFyfthzZh, JylrhsFyfthzZh.class, "费用分摊（支行汇总）");
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
        return super.importExcel(request, response, JylrhsFyfthzZh.class);
    }

}

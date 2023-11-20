package org.cmms.modules.jylrhs.csgl.zhdjpd.controller;

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
import org.cmms.modules.jylrhs.csgl.zhdjpd.entity.JylrhsYwjgRank;
import org.cmms.modules.jylrhs.csgl.zhdjpd.service.IJylrhsYwjgRankService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.sjxf.qtxt.zzxt.zzkmsjwj.service.IZzkmsjwjService;
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
 * @Description: 支行等级评定
 * @Author: jeecg-boot
 * @Date: 2023-09-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "支行等级评定")
@RestController
@RequestMapping("/jylrhs/csgl/zhdjpd")
public class JylrhsYwjgRankController extends JeecgController<JylrhsYwjgRank, IJylrhsYwjgRankService> {
    @Autowired
    private IJylrhsYwjgRankService jylrhsYwjgRankService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;
    @Autowired
    private IZzkmsjwjService zzkmsjwjService;
    @Value("${liuyang.testsystem:false}")
    private String testsystem;

    /**
     * 分页列表查询
     *
     * @param jylrhsYwjgRank
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "支行等级评定-分页列表查询")
    @ApiOperation(value = "支行等级评定-分页列表查询", notes = "支行等级评定-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(JylrhsYwjgRank jylrhsYwjgRank,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<JylrhsYwjgRank> queryWrapper = QueryGenerator.initQueryWrapper(jylrhsYwjgRank, req.getParameterMap());
        // Page<JylrhsYwjgRank> page = new Page<JylrhsYwjgRank>(pageNo, pageSize);
        // IPage<JylrhsYwjgRank> pageList = jylrhsYwjgRankService.page(page, queryWrapper);
        IPage pageList = PageUtil.toPage(IJylrhsYwjgRankService.class, jylrhsYwjgRankService, pageNo, pageSize, queryWrapper, "djpdnf","rank");
        return Result.ok(pageList);
    }

    /**
     * 统计
     *
     * @return
     */
    @AutoLog(value = "经营利润核算（支行等级评定）-统计")
    @ApiOperation(value = "经营利润核算（支行等级评定）-统计", notes = "经营利润核算（支行等级评定）-统计")
    @RequestMapping(value = "/initData")
    public Result<?> initData(@RequestBody JylrhsYwjgRank ywjgRank) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String fiscal_date = sdf.format(ywjgRank.getDjpdnf());
            log.info("当前等级评定年份 yyyyMMdd：" + fiscal_date);
            String pdnf = fiscal_date.substring(0,4).trim();
            log.info("当前等级评定年份 YYYY：" + pdnf);
            Calendar cal = Calendar.getInstance();
            String year = String.valueOf(cal.get(Calendar.YEAR));
            log.info("当前系统年份 YYYY：" + year);
            String data_date = "";
            if (!pdnf.equalsIgnoreCase(year)) {
                // 所选等级评定年份，不是当前年份，数据日期为当年末（例如：20221231）
                data_date = pdnf+"1231";
            } else {
                // 所选等级评定年份，是当前年份，数据日期为总账科目表最大数据日期
                // 获取总账科目表最大数据日期
                data_date = zzkmsjwjService.getMaxDataDateImpala();
                // if ("true".equalsIgnoreCase(sfdsjpt)) {
                //     data_date = zzkmsjwjService.getMaxDataDateImpala();
                // } else {
                //     data_date = zzkmsjwjService.getMaxDataDateOracle();
                // }
            }
            String operator = getLoginUser().getUsername();
            log.info("当前操作人员用户名：" + operator);
            if ("true".equals(sfdsjpt)) {
                HashMap<String, String> params = new HashMap<>();
                params.put("fiscal_date", fiscal_date);
                params.put("data_date", data_date);
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
                params.put("etl_task", "kiss.domain.application.jylrhs.proc_jylrhs_ywjg_rank");
//                boolean flag = EtlUtil.callEtl("jylrhs_common_init", params, 15);
                boolean flag = EtlUtil.callEtl("cdkyw_common_init", params, 15);
                log.info("经营利润核算-支行等级评定-统计-是否成功？-" + flag);
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
            log.info("经营利润核算（支行等级评定）-统计失败！" + e.getMessage());
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param jylrhsYwjgRank
     * @return
     */
    @AutoLog(value = "支行等级评定-添加")
    @ApiOperation(value = "支行等级评定-添加", notes = "支行等级评定-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody JylrhsYwjgRank jylrhsYwjgRank) {
        jylrhsYwjgRankService.save(jylrhsYwjgRank);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param jylrhsYwjgRank
     * @return
     */
    @AutoLog(value = "支行等级评定-编辑")
    @ApiOperation(value = "支行等级评定-编辑", notes = "支行等级评定-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody JylrhsYwjgRank jylrhsYwjgRank) {
        jylrhsYwjgRankService.updateById(jylrhsYwjgRank);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "支行等级评定-通过id删除")
    @ApiOperation(value = "支行等级评定-通过id删除", notes = "支行等级评定-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        jylrhsYwjgRankService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "支行等级评定-批量删除")
    @ApiOperation(value = "支行等级评定-批量删除", notes = "支行等级评定-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.jylrhsYwjgRankService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "支行等级评定-通过id查询")
    @ApiOperation(value = "支行等级评定-通过id查询", notes = "支行等级评定-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        JylrhsYwjgRank jylrhsYwjgRank = jylrhsYwjgRankService.getById(id);
        return Result.ok(jylrhsYwjgRank);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param jylrhsYwjgRank
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, JylrhsYwjgRank jylrhsYwjgRank) {
        return super.exportXls(request, jylrhsYwjgRank, JylrhsYwjgRank.class, "支行等级评定");
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
        return super.importExcel(request, response, JylrhsYwjgRank.class);
    }

}

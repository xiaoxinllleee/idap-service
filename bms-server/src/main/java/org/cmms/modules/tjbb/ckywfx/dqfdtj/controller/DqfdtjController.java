package org.cmms.modules.tjbb.ckywfx.dqfdtj.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.service.ISysLogService;
import org.cmms.modules.tjbb.ckywfx.dqfdtj.entity.Dqfdtj;
import org.cmms.modules.tjbb.ckywfx.dqfdtj.mapper.DqfdtjMapper;
import org.cmms.modules.tjbb.ckywfx.dqfdtj.service.IDqfdtjService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjbb.ckywfx.qhckjgfxb.entity.Qhckjgfxb;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.PageUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 定期分段统计
 * @Author: jeecg-boot
 * @Date: 2021-08-20
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "定期分段统计")
@RestController
@RequestMapping("/dqfdtj/dqfdtj")
public class DqfdtjController extends JeecgController<Dqfdtj, IDqfdtjService> {
    @Autowired
    private IDqfdtjService dqfdtjService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ISysDictService iSysDictService;
    @Autowired
    private ISysLogService sysLogService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param dqfdtj
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "定期分段统计-分页列表查询")
    @ApiOperation(value = "定期分段统计-分页列表查询", notes = "定期分段统计-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Dqfdtj dqfdtj,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(dqfdtj, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(IDqfdtjService.class,dqfdtjService,pageNo,pageSize,queryWrapper,"tjyf");
        return Result.ok(pageList);
    }

    /**
     * 提取
     */
    @RequestMapping(value = "/init")
    public Result<?> init(@RequestBody JSONObject jsonObject) {
        String tjyf = jsonObject.getString("tjyf");

        if (-1 == DateUtil.getCurrentTS().compareTo(DateUtil.parseDateFormat(tjyf, "yyyy-MM-dd"))) {
            return Result.error("选择月份必须小于当前系统月份！");
        }
        Result result = new Result<>();
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            Date cksjrq = DateUtil.string2Date(sysLogService.cksjrqBig(),"yyyyMMdd");
            tjyf = org.cmms.modules.util.DateUtil.getSjQmrq(tjyf, cksjrq, "yyyy-MM-dd");
            HashMap<String, String> params = new HashMap<>();
            params.put("fiscal_date", tjyf);
            params.put("etl_task","kiss.domain.application.tjbb.proc_tjbb_ckyw_dqfdtj");
            // count_tjbb_ckyw_dqfdtj
            boolean flag = EtlUtil.callEtl("tjbb_common_init", params, 15);
            result.setSuccess(flag);
        } else {
            try {
                dqfdtjService.pDqfdtj(tjyf.replace("-", ""));
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
     * @param dqfdtj
     * @return
     */
    @AutoLog(value = "定期分段统计-添加")
    @ApiOperation(value = "定期分段统计-添加", notes = "定期分段统计-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Dqfdtj dqfdtj) {
        dqfdtjService.save(dqfdtj);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param dqfdtj
     * @return
     */
    @AutoLog(value = "定期分段统计-编辑")
    @ApiOperation(value = "定期分段统计-编辑", notes = "定期分段统计-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Dqfdtj dqfdtj) {
        dqfdtjService.updateById(dqfdtj);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "定期分段统计-通过id删除")
    @ApiOperation(value = "定期分段统计-通过id删除", notes = "定期分段统计-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        dqfdtjService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "定期分段统计-批量删除")
    @ApiOperation(value = "定期分段统计-批量删除", notes = "定期分段统计-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.dqfdtjService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "定期分段统计-通过id查询")
    @ApiOperation(value = "定期分段统计-通过id查询", notes = "定期分段统计-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Dqfdtj dqfdtj = dqfdtjService.getById(id);
        return Result.ok(dqfdtj);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request,Dqfdtj dqfdtj) {
        return super.exportXls(request, dqfdtj, Dqfdtj.class, "定期分段统计");
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
        return super.importExcel(request, response, Dqfdtj.class);
    }

}

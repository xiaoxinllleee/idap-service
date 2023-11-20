package org.cmms.modules.tjbb.ckywfx.dqhzrpcx.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.service.ISysLogService;
import org.cmms.modules.tjbb.ckywfx.dqhzrpcx.entity.Dqhzrpcx;
import org.cmms.modules.tjbb.ckywfx.dqhzrpcx.mapper.DqhzrpcxMapper;
import org.cmms.modules.tjbb.ckywfx.dqhzrpcx.service.IDqhzrpcxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 定期汇总日平查询
 * @Author: jeecg-boot
 * @Date: 2021-08-20
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "定期汇总日平查询")
@RestController
@RequestMapping("/dqhzrpcx/dqhzrpcx")
public class DqhzrpcxController extends JeecgController<Dqhzrpcx, IDqhzrpcxService> {
    @Autowired
    private IDqhzrpcxService dqhzrpcxService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ISysLogService sysLogService;

    /**
     * 分页列表查询
     *
     * @param dqhzrpcx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "定期汇总日平查询-分页列表查询")
    @ApiOperation(value = "定期汇总日平查询-分页列表查询", notes = "定期汇总日平查询-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Dqhzrpcx dqhzrpcx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(dqhzrpcx, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(IDqhzrpcxService.class,dqhzrpcxService,pageNo,pageSize,queryWrapper,"tjyf","jgdm","zjhm");
        return Result.ok(pageList);
    }

    /**
     * 提取
     */
    @RequestMapping(value = "/init")
    public Result<?> init(@RequestBody JSONObject jsonObject) {
        String tjyf = jsonObject.getString("tjyf");
        Result result = new Result<>();
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            Date cksjrq = DateUtil.string2Date(sysLogService.cksjrqBig(),"yyyyMMdd");
            tjyf = org.cmms.modules.util.DateUtil.getSjQmrq(tjyf, cksjrq, "yyyy-MM-dd");
            HashMap<String, String> params = new HashMap<>();
            params.put("fiscal_date", tjyf);
            params.put("etl_task","kiss.domain.application.tjbb.proc_tjbb_ckyw_dqhzrpcx");
            // count_tjbb_ckyw_dqhzrpcxtjbb_ckyw_dqfdtj
            boolean falg = EtlUtil.callEtl("tjbb_common_init", params, 15);
            result.setSuccess(falg);
        } else {
            try {
                dqhzrpcxService.pDqhzrpcx(tjyf.replace("-", ""));
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
     * @param dqhzrpcx
     * @return
     */
    @AutoLog(value = "定期汇总日平查询-添加")
    @ApiOperation(value = "定期汇总日平查询-添加", notes = "定期汇总日平查询-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Dqhzrpcx dqhzrpcx) {
        dqhzrpcxService.save(dqhzrpcx);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param dqhzrpcx
     * @return
     */
    @AutoLog(value = "定期汇总日平查询-编辑")
    @ApiOperation(value = "定期汇总日平查询-编辑", notes = "定期汇总日平查询-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Dqhzrpcx dqhzrpcx) {
        dqhzrpcxService.updateById(dqhzrpcx);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "定期汇总日平查询-通过id删除")
    @ApiOperation(value = "定期汇总日平查询-通过id删除", notes = "定期汇总日平查询-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        dqhzrpcxService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "定期汇总日平查询-批量删除")
    @ApiOperation(value = "定期汇总日平查询-批量删除", notes = "定期汇总日平查询-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.dqhzrpcxService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "定期汇总日平查询-通过id查询")
    @ApiOperation(value = "定期汇总日平查询-通过id查询", notes = "定期汇总日平查询-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Dqhzrpcx dqhzrpcx = dqhzrpcxService.getById(id);
        return Result.ok(dqhzrpcx);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param dqhzrpcx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Dqhzrpcx dqhzrpcx) {
        return super.exportXls(request, dqhzrpcx, Dqhzrpcx.class, "定期汇总日平查询");
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
        return super.importExcel(request, response, Dqhzrpcx.class);
    }

}

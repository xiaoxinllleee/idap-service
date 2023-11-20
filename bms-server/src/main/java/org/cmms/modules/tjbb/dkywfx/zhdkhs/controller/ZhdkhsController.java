package org.cmms.modules.tjbb.dkywfx.zhdkhs.controller;

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
import org.cmms.modules.system.service.ISysLogService;
import org.cmms.modules.tjbb.dkywfx.zhdkhs.entity.Zhdkhs;
import org.cmms.modules.tjbb.dkywfx.zhdkhs.mapper.ZhdkhsMapper;
import org.cmms.modules.tjbb.dkywfx.zhdkhs.service.IZhdkhsService;
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
 * @Description: 支行贷款户数
 * @Author: jeecg-boot
 * @Date: 2021-08-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "支行贷款户数")
@RestController
@RequestMapping("/zhdkhs/zhdkhs")
public class ZhdkhsController extends JeecgController<Zhdkhs, IZhdkhsService> {
    @Autowired
    private IZhdkhsService zhdkhsService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ISysLogService sysLogService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param zhdkhs
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "支行贷款户数-分页列表查询")
    @ApiOperation(value = "支行贷款户数-分页列表查询", notes = "支行贷款户数-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Zhdkhs zhdkhs,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(zhdkhs, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(IZhdkhsService.class,zhdkhsService,pageNo,pageSize,queryWrapper,"tjyf","jgdm");
        return Result.ok(pageList);
    }

    /**
     * 提取
     */
    @RequestMapping(value = "/init")
    public Result<?> init(@RequestBody JSONObject jsonObject) {
        String tjyf = jsonObject.getString("tjyf");
        if (-1 == DateUtil.getCurrentTS().compareTo(DateUtil.parseDateFormat(tjyf, "yyyy-MM-dd"))) {
            return Result.error("所选月份必须小于当前系统月份！");
        }
        Result result = new Result<>();
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            Date dksjrq = DateUtil.string2Date(sysLogService.dksjrqBig(),"yyyyMMdd");
            tjyf = org.cmms.modules.util.DateUtil.getSjQmrq(tjyf, dksjrq, "yyyy-MM-dd");
            HashMap<String, String> params = new HashMap<>();
            params.put("fiscal_date", tjyf);
            params.put("etl_task","kiss.domain.application.tjbb.proc_tjbb_dkyw_zhdkhs");
            // count_tjbb_dkyw_zhdkhs
            boolean falg = EtlUtil.callEtl("tjbb_common_init", params, 15);
            result.setSuccess(falg);
        } else {
            try {
                zhdkhsService.pZhdkhs(tjyf.replace("-", ""));
                result.setSuccess(true);
                return result;
            } catch (Exception e) {
                log.error("提取失败", e.getMessage());
                result.setMessage(e.getMessage());
                result.setSuccess(false);
            }
        }
        return result;
    }

    /**
     * 添加
     *
     * @param zhdkhs
     * @return
     */
    @AutoLog(value = "支行贷款户数-添加")
    @ApiOperation(value = "支行贷款户数-添加", notes = "支行贷款户数-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Zhdkhs zhdkhs) {
        zhdkhsService.save(zhdkhs);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param zhdkhs
     * @return
     */
    @AutoLog(value = "支行贷款户数-编辑")
    @ApiOperation(value = "支行贷款户数-编辑", notes = "支行贷款户数-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Zhdkhs zhdkhs) {
        zhdkhsService.updateById(zhdkhs);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "支行贷款户数-通过id删除")
    @ApiOperation(value = "支行贷款户数-通过id删除", notes = "支行贷款户数-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        zhdkhsService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "支行贷款户数-批量删除")
    @ApiOperation(value = "支行贷款户数-批量删除", notes = "支行贷款户数-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.zhdkhsService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "支行贷款户数-通过id查询")
    @ApiOperation(value = "支行贷款户数-通过id查询", notes = "支行贷款户数-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Zhdkhs zhdkhs = zhdkhsService.getById(id);
        return Result.ok(zhdkhs);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param zhdkhs
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Zhdkhs zhdkhs) {
        return super.exportXls(request, zhdkhs, Zhdkhs.class, "支行贷款户数");
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
        return super.importExcel(request, response, Zhdkhs.class);
    }

}

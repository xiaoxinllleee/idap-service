package org.cmms.modules.tjbb.kmcx.kmrptj.controller;

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
import org.cmms.modules.tjbb.kmcx.kmrptj.entity.Kmrptj;
import org.cmms.modules.tjbb.kmcx.kmrptj.mapper.KmrptjMapper;
import org.cmms.modules.tjbb.kmcx.kmrptj.service.IKmrptjService;
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
 * @Description: 科目日平统计
 * @Author: jeecg-boot
 * @Date: 2021-08-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "科目日平统计")
@RestController
@RequestMapping("/kmrptj/kmrptj")
public class KmrptjController extends JeecgController<Kmrptj, IKmrptjService> {
    @Autowired
    private IKmrptjService kmrptjService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ISysLogService sysLogService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param kmrptj
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "科目日平统计-分页列表查询")
    @ApiOperation(value = "科目日平统计-分页列表查询", notes = "科目日平统计-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Kmrptj kmrptj,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(kmrptj, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(IKmrptjService.class,kmrptjService,pageNo,pageSize,queryWrapper,"tjyf","kmbm");
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
            params.put("etl_task","kiss.domain.application.tjbb.proc_tjbb_kmcx_kmrptj");
            // count_tjbb_kmcx_kmrptj
            boolean falg = EtlUtil.callEtl("tjbb_common_init", params, 15);
            result.setSuccess(falg);
        } else {
            try {
                tjyf = tjyf.replaceAll("-", "");
                kmrptjService.pKmrptj(tjyf);
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
     * @param kmrptj
     * @return
     */
    @AutoLog(value = "科目日平统计-添加")
    @ApiOperation(value = "科目日平统计-添加", notes = "科目日平统计-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Kmrptj kmrptj) {
        kmrptjService.save(kmrptj);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param kmrptj
     * @return
     */
    @AutoLog(value = "科目日平统计-编辑")
    @ApiOperation(value = "科目日平统计-编辑", notes = "科目日平统计-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Kmrptj kmrptj) {
        kmrptjService.updateById(kmrptj);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "科目日平统计-通过id删除")
    @ApiOperation(value = "科目日平统计-通过id删除", notes = "科目日平统计-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        kmrptjService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "科目日平统计-批量删除")
    @ApiOperation(value = "科目日平统计-批量删除", notes = "科目日平统计-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.kmrptjService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "科目日平统计-通过id查询")
    @ApiOperation(value = "科目日平统计-通过id查询", notes = "科目日平统计-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Kmrptj kmrptj = kmrptjService.getById(id);
        return Result.ok(kmrptj);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param kmrptj
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Kmrptj kmrptj) {
        return super.exportXls(request, kmrptj, Kmrptj.class, "科目日平统计");
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
        return super.importExcel(request, response, Kmrptj.class);
    }

}

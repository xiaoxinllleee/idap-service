package org.cmms.modules.tjbb.dkywfx.dklxmx.khlxmx.controller;

import java.util.Arrays;
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
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.tjbb.dkywfx.dklxmx.khlxmx.entity.Khlxmx;
import org.cmms.modules.tjbb.dkywfx.dklxmx.khlxmx.mapper.KhlxmxMapper;
import org.cmms.modules.tjbb.dkywfx.dklxmx.khlxmx.service.IKhlxmxService;
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
 * @Description: 客户利息明细
 * @Author: jeecg-boot
 * @Date: 2021-08-21
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "客户利息明细")
@RestController
@RequestMapping("/khlxmx/khlxmx")
public class KhlxmxController extends JeecgController<Khlxmx, IKhlxmxService> {
    @Autowired
    private IKhlxmxService khlxmxService;
    @Autowired
    private RedisUtil redisUtil;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param khlxmx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "客户利息明细-分页列表查询")
    @ApiOperation(value = "客户利息明细-分页列表查询", notes = "客户利息明细-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Khlxmx khlxmx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(khlxmx, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(IKhlxmxService.class,khlxmxService,pageNo,pageSize,queryWrapper,"jgdm","zjhm","dkzh");
        return Result.ok(pageList);
    }

    /**
     * 提取
     */
    @RequestMapping(value = "/init")
    public Result<?> init(@RequestBody JSONObject jsonObject) {
        String ksrq = jsonObject.getString("ksrq");
        String jsrq = jsonObject.getString("jsrq");
        ksrq = ksrq.replaceAll("-", "");
        jsrq = jsrq.replaceAll("-", "");
        Result result = new Result<>();
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            HashMap<String, String> params = new HashMap<>();
            params.put("p_ksrq", ksrq);
            params.put("p_jsrq", jsrq);
            params.put("etl_task","kiss.domain.application.tjbb.proc_tjbb_dkyw_khlxmx");
            // count_tjbb_dkyw_khlxmx
            boolean falg = EtlUtil.callEtl("tjbb_common_init", params, 15);
            result.setSuccess(falg);
        } else {
            try {
                khlxmxService.pKhlxmx(ksrq, jsrq);
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
     * @param khlxmx
     * @return
     */
    @AutoLog(value = "客户利息明细-添加")
    @ApiOperation(value = "客户利息明细-添加", notes = "客户利息明细-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Khlxmx khlxmx) {
        khlxmxService.save(khlxmx);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param khlxmx
     * @return
     */
    @AutoLog(value = "客户利息明细-编辑")
    @ApiOperation(value = "客户利息明细-编辑", notes = "客户利息明细-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Khlxmx khlxmx) {
        khlxmxService.updateById(khlxmx);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "客户利息明细-通过id删除")
    @ApiOperation(value = "客户利息明细-通过id删除", notes = "客户利息明细-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        khlxmxService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "客户利息明细-批量删除")
    @ApiOperation(value = "客户利息明细-批量删除", notes = "客户利息明细-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.khlxmxService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "客户利息明细-通过id查询")
    @ApiOperation(value = "客户利息明细-通过id查询", notes = "客户利息明细-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Khlxmx khlxmx = khlxmxService.getById(id);
        return Result.ok(khlxmx);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param khlxmx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Khlxmx khlxmx) {
        return super.exportXls(request, khlxmx, Khlxmx.class, "客户利息明细");
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
        return super.importExcel(request, response, Khlxmx.class);
    }

}

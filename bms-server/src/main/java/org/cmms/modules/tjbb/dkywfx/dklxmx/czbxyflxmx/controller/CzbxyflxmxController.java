package org.cmms.modules.tjbb.dkywfx.dklxmx.czbxyflxmx.controller;

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
import org.cmms.modules.tjbb.dkywfx.dklxmx.czbxyflxmx.entity.Czbxyflxmx;
import org.cmms.modules.tjbb.dkywfx.dklxmx.czbxyflxmx.mapper.CzbxyflxmxMapper;
import org.cmms.modules.tjbb.dkywfx.dklxmx.czbxyflxmx.service.ICzbxyflxmxService;
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
 * @Description: 财政保险应付利息明细
 * @Author: jeecg-boot
 * @Date: 2021-08-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "财政保险应付利息明细")
@RestController
@RequestMapping("/czbxyflxmx/czbxyflxmx")
public class CzbxyflxmxController extends JeecgController<Czbxyflxmx, ICzbxyflxmxService> {
    @Autowired
    private ICzbxyflxmxService czbxyflxmxService;
    @Autowired
    private RedisUtil redisUtil;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param czbxyflxmx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "财政保险应付利息明细-分页列表查询")
    @ApiOperation(value = "财政保险应付利息明细-分页列表查询", notes = "财政保险应付利息明细-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Czbxyflxmx czbxyflxmx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(czbxyflxmx, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICzbxyflxmxService.class,czbxyflxmxService,pageNo,pageSize,queryWrapper,"ksrq","jsrq","ckzh");
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
            params.put("etl_task","kiss.domain.application.tjbb.proc_tjbb_dkyw_czbxyflxmx");
            // count_tjbb_dkyw_czbxyflxmx
            boolean falg = EtlUtil.callEtl("tjbb_common_init", params, 15);
            result.setSuccess(falg);
        } else {
            try {
                czbxyflxmxService.pCzbxyflxmx(ksrq, jsrq);
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
     * @param czbxyflxmx
     * @return
     */
    @AutoLog(value = "财政保险应付利息明细-添加")
    @ApiOperation(value = "财政保险应付利息明细-添加", notes = "财政保险应付利息明细-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Czbxyflxmx czbxyflxmx) {
        czbxyflxmxService.save(czbxyflxmx);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param czbxyflxmx
     * @return
     */
    @AutoLog(value = "财政保险应付利息明细-编辑")
    @ApiOperation(value = "财政保险应付利息明细-编辑", notes = "财政保险应付利息明细-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Czbxyflxmx czbxyflxmx) {
        czbxyflxmxService.updateById(czbxyflxmx);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "财政保险应付利息明细-通过id删除")
    @ApiOperation(value = "财政保险应付利息明细-通过id删除", notes = "财政保险应付利息明细-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        czbxyflxmxService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "财政保险应付利息明细-批量删除")
    @ApiOperation(value = "财政保险应付利息明细-批量删除", notes = "财政保险应付利息明细-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.czbxyflxmxService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "财政保险应付利息明细-通过id查询")
    @ApiOperation(value = "财政保险应付利息明细-通过id查询", notes = "财政保险应付利息明细-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Czbxyflxmx czbxyflxmx = czbxyflxmxService.getById(id);
        return Result.ok(czbxyflxmx);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param czbxyflxmx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Czbxyflxmx czbxyflxmx) {
        return super.exportXls(request, czbxyflxmx, Czbxyflxmx.class, "财政保险应付利息明细");
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
        return super.importExcel(request, response, Czbxyflxmx.class);
    }

}

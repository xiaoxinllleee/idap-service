package org.cmms.modules.tjbb.ckywfx.ckjgfxb.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
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
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.tjbb.ckywfx.ckjgfxb.entity.Ckjgfxb;
import org.cmms.modules.tjbb.ckywfx.ckjgfxb.mapper.CkjgfxbMapper;
import org.cmms.modules.tjbb.ckywfx.ckjgfxb.service.ICkjgfxbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.zh.zhzftjhz.entity.Zhzftjhz;
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
 * @Description: 存款结构分析表
 * @Author: jeecg-boot
 * @Date: 2021-08-20
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "存款结构分析表")
@RestController
@RequestMapping("/ckjgfxb/ckjgfxb")
public class CkjgfxbController extends JeecgController<Ckjgfxb, ICkjgfxbService> {
    @Autowired
    private ICkjgfxbService ckjgfxbService;
    @Autowired
    private RedisUtil redisUtil;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param ckjgfxb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "存款结构分析表-分页列表查询")
    @ApiOperation(value = "存款结构分析表-分页列表查询", notes = "存款结构分析表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Ckjgfxb ckjgfxb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(ckjgfxb, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkjgfxbService.class,ckjgfxbService,pageNo,pageSize,queryWrapper,"jgdm");
        return Result.ok(pageList);
    }

    /**
     * 提取
     */
    @RequestMapping(value = "/init")
    public Result<?> init(@RequestBody JSONObject jsonObject) {
        String tjrq = jsonObject.getString("tjrq");
        if (-1 == DateUtil.getCurrentTS().compareTo(DateUtil.parseDateFormat(tjrq, "yyyy-MM-dd"))) {
            return Result.error("统计日期必须小于当前系统日期！");
        }
        Result result = new Result<>();
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            HashMap<String, String> params = new HashMap<>();
            params.put("fiscal_date", tjrq);
            params.put("etl_task","kiss.domain.application.tjbb.proc_tjbb_ckyw_ckjgfxb_zh");
            // count_tjbb_ckyw_ckjgfxb_zh
            boolean falg = EtlUtil.callEtl("tjbb_common_init", params, 15);
            result.setSuccess(falg);
        } else {
            try {
                ckjgfxbService.pCkjgfx(tjrq.replace("-",""));
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
     * @param ckjgfxb
     * @return
     */
    @AutoLog(value = "存款结构分析表-添加")
    @ApiOperation(value = "存款结构分析表-添加", notes = "存款结构分析表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Ckjgfxb ckjgfxb) {
        ckjgfxbService.save(ckjgfxb);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param ckjgfxb
     * @return
     */
    @AutoLog(value = "存款结构分析表-编辑")
    @ApiOperation(value = "存款结构分析表-编辑", notes = "存款结构分析表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Ckjgfxb ckjgfxb) {
        ckjgfxbService.updateById(ckjgfxb);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "存款结构分析表-通过id删除")
    @ApiOperation(value = "存款结构分析表-通过id删除", notes = "存款结构分析表-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        ckjgfxbService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "存款结构分析表-批量删除")
    @ApiOperation(value = "存款结构分析表-批量删除", notes = "存款结构分析表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.ckjgfxbService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "存款结构分析表-通过id查询")
    @ApiOperation(value = "存款结构分析表-通过id查询", notes = "存款结构分析表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Ckjgfxb ckjgfxb = ckjgfxbService.getById(id);
        return Result.ok(ckjgfxb);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Ckjgfxb ckjgfxb) {
        return super.exportXls(request, ckjgfxb, Ckjgfxb.class, "存款结构分析表");
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
        return super.importExcel(request, response, Ckjgfxb.class);
    }

}

package org.cmms.modules.dkjkpt.tjcx.zhajdktj.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dkjkpt.tjcx.zhajdktj.entity.ZhajdktjNb;
import org.cmms.modules.dkjkpt.tjcx.zhajdktj.service.IZhajdktjNbService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 支行按揭贷款统计_年报
 * @Author: jeecg-boot
 * @Date: 2022-09-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "支行按揭贷款统计_年报")
@RestController
@RequestMapping("/zhajdktj/zhajdktjNb")
public class ZhajdktjNbController extends JeecgController<ZhajdktjNb, IZhajdktjNbService> {
    @Autowired
    private IZhajdktjNbService zhajdktjNbService;

    /**
     * 分页列表查询
     *
     * @param zhajdktjNb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "支行按揭贷款统计_年报-分页列表查询")
    @ApiOperation(value = "支行按揭贷款统计_年报-分页列表查询", notes = "支行按揭贷款统计_年报-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(ZhajdktjNb zhajdktjNb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<ZhajdktjNb>> result = new Result<IPage<ZhajdktjNb>>();
        QueryWrapper<ZhajdktjNb> queryWrapper = QueryGenerator.initQueryWrapper(zhajdktjNb, req.getParameterMap());
        Page<ZhajdktjNb> page = new Page<ZhajdktjNb>(pageNo, pageSize);
        IPage<ZhajdktjNb> pageList = zhajdktjNbService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 添加
     *
     * @param zhajdktjNb
     * @return
     */
    @AutoLog(value = "支行按揭贷款统计_年报-添加")
    @ApiOperation(value = "支行按揭贷款统计_年报-添加", notes = "支行按揭贷款统计_年报-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody ZhajdktjNb zhajdktjNb) {
        zhajdktjNbService.save(zhajdktjNb);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param zhajdktjNb
     * @return
     */
    @AutoLog(value = "支行按揭贷款统计_年报-编辑")
    @ApiOperation(value = "支行按揭贷款统计_年报-编辑", notes = "支行按揭贷款统计_年报-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody ZhajdktjNb zhajdktjNb) {
        zhajdktjNbService.updateById(zhajdktjNb);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "支行按揭贷款统计_年报-通过id删除")
    @ApiOperation(value = "支行按揭贷款统计_年报-通过id删除", notes = "支行按揭贷款统计_年报-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        zhajdktjNbService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "支行按揭贷款统计_年报-批量删除")
    @ApiOperation(value = "支行按揭贷款统计_年报-批量删除", notes = "支行按揭贷款统计_年报-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.zhajdktjNbService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "支行按揭贷款统计_年报-通过id查询")
    @ApiOperation(value = "支行按揭贷款统计_年报-通过id查询", notes = "支行按揭贷款统计_年报-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        ZhajdktjNb zhajdktjNb = zhajdktjNbService.getById(id);
        return Result.ok(zhajdktjNb);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        // Step.1 组装查询条件
        QueryWrapper<ZhajdktjNb> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                ZhajdktjNb zhajdktjNb = JSON.parseObject(deString, ZhajdktjNb.class);
                queryWrapper = QueryGenerator.initQueryWrapper(zhajdktjNb, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<ZhajdktjNb> pageList = zhajdktjNbService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "支行按揭贷款统计(年报)列表");
        mv.addObject(NormalExcelConstants.CLASS, ZhajdktjNb.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("支行按揭贷款统计(年报)列表数据", "导出人:Jeecg", "导出信息"));
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
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ZhajdktjNb.class);
    }

    /**
     * 提取
     *
     * @param jsonObject
     * @return
     */
    @PutMapping(value = "/extract")
    public Result<?> extract1(@RequestBody JSONObject jsonObject) {
        /*Map<String,String>parm = new HashMap<>();*/
        try {
            Map<String, String> parm = new HashMap<>();
            parm.put("tjyf", jsonObject.getString("tjyf"));
            parm.put("type", "3");
            parm.put("tjwd", "");
            zhajdktjNbService.extract(parm);
        } catch (Exception e) {
            log.error(e.getMessage(), "提取失败");
            return Result.error(e.getMessage());
        }
        return Result.ok("提取成功");


    }
}

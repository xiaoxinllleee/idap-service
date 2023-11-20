package org.cmms.modules.tjfx.shpjsx.zh.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.shpjsx.wg.entity.ShPjsxMxVo;
import org.cmms.modules.tjfx.shpjsx.zh.entity.ShZhPjsxMxVo;
import org.cmms.modules.tjfx.shpjsx.zh.entity.TjfxZhpjsxtjbbSh;
import org.cmms.modules.tjfx.shpjsx.zh.service.ITjfxZhpjsxtjbbShService;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
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
 * @Description: 支行评级授信统计-商户
 * @Author: jeecg-boot
 * @Date: 2023-09-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "支行评级授信统计-商户")
@RestController
@RequestMapping("/shpjsx/zh/tjfxZhpjsxtjbbSh")
public class TjfxZhpjsxtjbbShController extends JeecgController<TjfxZhpjsxtjbbSh, ITjfxZhpjsxtjbbShService> {
    @Autowired
    private ITjfxZhpjsxtjbbShService tjfxZhpjsxtjbbShService;

    /**
     * 分页列表查询
     *
     * @param tjfxZhpjsxtjbbSh
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "支行评级授信统计-商户-分页列表查询")
    @ApiOperation(value = "支行评级授信统计-商户-分页列表查询", notes = "支行评级授信统计-商户-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(TjfxZhpjsxtjbbSh tjfxZhpjsxtjbbSh,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<TjfxZhpjsxtjbbSh> queryWrapper = QueryGenerator.initQueryWrapper(tjfxZhpjsxtjbbSh, req.getParameterMap());
        queryWrapper.orderByDesc("sjrq");
        Page<TjfxZhpjsxtjbbSh> page = new Page<TjfxZhpjsxtjbbSh>(pageNo, pageSize);
        IPage<TjfxZhpjsxtjbbSh> pageList = tjfxZhpjsxtjbbShService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @GetMapping("/queryPageListMx")
    public Result<?> queryPageListMx(@RequestParam("sjrq") String sjrq,
                                     @RequestParam("sszh") String sszh,
                                     @RequestParam("type") String type,
                                     @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                     HttpServletRequest req) {
        Page<ShZhPjsxMxVo> page = new Page<>(pageNo, pageSize);
        IPage<ShZhPjsxMxVo> pageList = tjfxZhpjsxtjbbShService.queryPageListMx(page, sjrq.replace("-", ""), sszh, type);
        return Result.ok(pageList);
    }

    @RequestMapping(value = "/exportShPjsxMxXls")
    public ModelAndView exportZfsjmxXls(@RequestParam("sszh") String sszh,
                                        @RequestParam("sjrq") String sjrq,
                                        @RequestParam("type") String type,
                                        HttpServletRequest request) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        List<ShZhPjsxMxVo> exportList = service.queryListMx(sjrq.replace("-", ""), sszh, type);
        String title = "商户评级授信统计";
        if ("1".equals(type)){
            title= "支行商户评级授信评级户数统计";
        }else{
            title= "支行商户评级授信授信户数统计";
        }
        // Step.3 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, ShZhPjsxMxVo.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 添加
     *
     * @param tjfxZhpjsxtjbbSh
     * @return
     */
    @AutoLog(value = "支行评级授信统计-商户-添加")
    @ApiOperation(value = "支行评级授信统计-商户-添加", notes = "支行评级授信统计-商户-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody TjfxZhpjsxtjbbSh tjfxZhpjsxtjbbSh) {
        tjfxZhpjsxtjbbShService.save(tjfxZhpjsxtjbbSh);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param tjfxZhpjsxtjbbSh
     * @return
     */
    @AutoLog(value = "支行评级授信统计-商户-编辑")
    @ApiOperation(value = "支行评级授信统计-商户-编辑", notes = "支行评级授信统计-商户-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody TjfxZhpjsxtjbbSh tjfxZhpjsxtjbbSh) {
        tjfxZhpjsxtjbbShService.updateById(tjfxZhpjsxtjbbSh);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "支行评级授信统计-商户-通过id删除")
    @ApiOperation(value = "支行评级授信统计-商户-通过id删除", notes = "支行评级授信统计-商户-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        tjfxZhpjsxtjbbShService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "支行评级授信统计-商户-批量删除")
    @ApiOperation(value = "支行评级授信统计-商户-批量删除", notes = "支行评级授信统计-商户-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.tjfxZhpjsxtjbbShService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "支行评级授信统计-商户-通过id查询")
    @ApiOperation(value = "支行评级授信统计-商户-通过id查询", notes = "支行评级授信统计-商户-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        TjfxZhpjsxtjbbSh tjfxZhpjsxtjbbSh = tjfxZhpjsxtjbbShService.getById(id);
        return Result.ok(tjfxZhpjsxtjbbSh);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param tjfxZhpjsxtjbbSh
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TjfxZhpjsxtjbbSh tjfxZhpjsxtjbbSh) {
        return super.exportXls(request, tjfxZhpjsxtjbbSh, TjfxZhpjsxtjbbSh.class, "支行评级授信统计-商户");
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
        return super.importExcel(request, response, TjfxZhpjsxtjbbSh.class);
    }

}

package org.cmms.modules.tjfx.wgtjfx.wgywtj.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.nh.entity.Nhbkbpy;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.entity.Wgywtj;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.service.IWgywtjService;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.service.IWgywtjZnzdService;
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
 * @Description: 网格业务统计
 * @Author: jeecg-boot
 * @Date: 2022-03-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "网格业务统计")
@RestController
@RequestMapping("/wgtjfx/wgywtj")
public class WgywtjController extends JeecgController<Wgywtj, IWgywtjService> {
    @Autowired
    private IWgywtjService wgywtjService;
    @Autowired
    private IWgywtjZnzdService wgywtjZnzdService;

    /**
     * 分页列表查询
     *
     * @param wgywtj
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "网格业务统计-分页列表查询")
    @ApiOperation(value = "网格业务统计-分页列表查询", notes = "网格业务统计-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Wgywtj wgywtj,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Wgywtj> queryWrapper = QueryGenerator.initQueryWrapper(wgywtj, req.getParameterMap());
        Page<Wgywtj> page = new Page<Wgywtj>(pageNo, pageSize);
        IPage<Wgywtj> pageList = wgywtjService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param wgywtj
     * @return
     */
    @AutoLog(value = "网格业务统计-添加")
    @ApiOperation(value = "网格业务统计-添加", notes = "网格业务统计-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Wgywtj wgywtj) {
        wgywtjService.save(wgywtj);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param wgywtj
     * @return
     */
    @AutoLog(value = "网格业务统计-编辑")
    @ApiOperation(value = "网格业务统计-编辑", notes = "网格业务统计-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Wgywtj wgywtj) {
        wgywtjService.updateById(wgywtj);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "网格业务统计-通过id删除")
    @ApiOperation(value = "网格业务统计-通过id删除", notes = "网格业务统计-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        wgywtjService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "网格业务统计-批量删除")
    @ApiOperation(value = "网格业务统计-批量删除", notes = "网格业务统计-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.wgywtjService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "网格业务统计-通过id查询")
    @ApiOperation(value = "网格业务统计-通过id查询", notes = "网格业务统计-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Wgywtj wgywtj = wgywtjService.getById(id);
        return Result.ok(wgywtj);
    }

    /**
     * 通过wgbh查询
     *
     * @param wgbh
     * @return
     */
    @GetMapping(value = "/queryByWgbh")
    public Result<?> queryByWgbh(@RequestParam(name = "wgbh", required = true) String wgbh) {
        if (StringUtils.isNotBlank(wgbh)) {
            String qydm = getRedisQydm();
            Wgywtj wgywtj = null;
            if (StringUtils.isNotBlank(qydm) && qydm.equals(QydmEnums.TIANYI.getQydmCode())) {
                wgywtj = wgywtjService.getWgywxxByWgbhTy(Arrays.asList(wgbh.split(",")));
            } else {
                wgywtj = wgywtjService.getWgywxxByWgbh(wgbh);
            }
            return Result.ok(wgywtj);
        }
        return Result.ok(null);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param wgywtj
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Wgywtj wgywtj) {
        return super.exportXls(request, wgywtj, Wgywtj.class, "网格业务统计");
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
        return super.importExcel(request, response, Wgywtj.class);
    }

    /**
     * 天易-获取相关网格信息（福祥e、评议信息、助农终端）
     * @param wgbh
     * @return
     */
    @GetMapping("/getWgxxOtherInfo")
    public Result<?> getWgxxOtherInfo(@RequestParam("wgbh") String wgbh) {
        Map resultMap = new HashedMap();
        resultMap.put("fxezf", wgywtjService.getFxezf(Arrays.asList(wgbh.split(","))));
        resultMap.put("pyxx", wgywtjService.getPyxx(Arrays.asList(wgbh.split(","))));
        String tableName = wgywtjZnzdService.getNnzdTableName();
        resultMap.put("znzd", StringUtils.isNotBlank(tableName) ? wgywtjZnzdService.getZnzdHs(tableName, Arrays.asList(wgbh.split(","))) : 0);
        resultMap.put("wgsl",wgywtjService.getWgxxToNum(Arrays.asList(wgbh.split(","))));
        resultMap.put("cjdxrs",wgywtjService.getCjdxrsToWgxx(Arrays.asList(wgbh.split(","))));
        return Result.ok(resultMap);
    }

    /**
     * 天易-网格信息-获取网格净增
     * @param wgbh
     * @return
     */
    @GetMapping("/getDzyhByjz")
    public Result<?> getDzyhByjz(@RequestParam("wgbh") String wgbh) {
        return Result.ok(wgywtjService.getDzyhByjz(Arrays.asList(wgbh.split(","))));
    }
}

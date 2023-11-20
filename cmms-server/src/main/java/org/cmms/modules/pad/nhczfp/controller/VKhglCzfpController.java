package org.cmms.modules.pad.nhczfp.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.pad.nhczfp.entity.VKhglCzfp;
import org.cmms.modules.pad.nhczfp.service.IVKhglCzfpService;

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
 * @Description: 村组复评
 * @Author: jeecg-boot
 * @Date: 2023-03-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "村组复评")
@RestController
@RequestMapping("/nhczfp/vKhglCzfp")
public class VKhglCzfpController extends JeecgController<VKhglCzfp, IVKhglCzfpService> {
    @Autowired
    private IVKhglCzfpService vKhglCzfpService;

    /**
     * 分页列表查询
     *
     * @param vKhglCzfp
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "村组复评-分页列表查询")
    @ApiOperation(value = "村组复评-分页列表查询", notes = "村组复评-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(VKhglCzfp vKhglCzfp,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   @RequestParam(name = "zuinfo", required = false) String zuinfo,
                                   @RequestParam(name = "fpwg") String fpwg,
                                   HttpServletRequest req) {
        QueryWrapper<VKhglCzfp> queryWrapper = QueryGenerator.initQueryWrapper(vKhglCzfp, req.getParameterMap());
        if (StringUtils.isNotEmpty(fpwg)) {
            queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main where wgbh in ('" + fpwg + "') or parent_id in ('" + fpwg + "')");
        }
        if (StringUtils.isNotBlank(zuinfo)) {
            String[] split = zuinfo.split(",");
            queryWrapper.in("wgbh", split);
        }

        Page<VKhglCzfp> page = new Page<VKhglCzfp>(pageNo, pageSize);
        IPage<VKhglCzfp> pageList = vKhglCzfpService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @AutoLog(value = "支行审定-分页列表查询")
    @ApiOperation(value = "支行审定-分页列表查询", notes = "支行审定-分页列表查询")
    @GetMapping(value = "/list2")
    public Result<?> queryPageList2(VKhglCzfp vKhglCzfp,
                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                    @RequestParam(name = "sdwg") String sdwg,
                                    @RequestParam(name = "zhsdlx") String zhsdlx,
                                    HttpServletRequest req) {
        QueryWrapper<VKhglCzfp> queryWrapper = QueryGenerator.initQueryWrapper(vKhglCzfp, req.getParameterMap());
        if (StringUtils.isNotEmpty(sdwg)) {
            queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main where wgbh in ('" + sdwg + "') or parent_id in ('" + sdwg + "')");
        }
        if (StringUtils.isNotBlank(zhsdlx)) {
//            if ("1".equals(zhsdlx)) {
//                queryWrapper.apply("(jcmxcs>0 or fped>0)");
//            } else {
//                queryWrapper.apply("jcmxcs=0 and (fped=0 or fped is null)");
//            }
            queryWrapper.eq("pylx", zhsdlx);
        }
        queryWrapper.orderByDesc("wgbh");

        Page<VKhglCzfp> page = new Page<VKhglCzfp>(pageNo, pageSize);
        IPage<VKhglCzfp> pageList = vKhglCzfpService.page(page, queryWrapper);
        List<VKhglCzfp> vKhglCzfpList = pageList.getRecords();
        vKhglCzfpList.forEach(item -> {
            if (item.getSdje()==null) {
                item.setFped(item.getFped() == null ? 0 : item.getFped());
                item.setJcmxcs(item.getJcmxcs() == null ? 0 : item.getJcmxcs());
                item.setSdje(item.getJcmxcs() > item.getFped() ? item.getJcmxcs() / 10000 : item.getFped() / 10000);
            }else{
                item.setSdje(item.getSdje()/10000);
            }
        });
        pageList.setRecords(vKhglCzfpList);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param vKhglCzfp
     * @return
     */
    @AutoLog(value = "村组复评-添加")
    @ApiOperation(value = "村组复评-添加", notes = "村组复评-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody VKhglCzfp vKhglCzfp) {
        vKhglCzfpService.save(vKhglCzfp);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param vKhglCzfp
     * @return
     */
    @AutoLog(value = "村组复评-编辑")
    @ApiOperation(value = "村组复评-编辑", notes = "村组复评-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody VKhglCzfp vKhglCzfp) {
        vKhglCzfpService.updateById(vKhglCzfp);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "村组复评-通过id删除")
    @ApiOperation(value = "村组复评-通过id删除", notes = "村组复评-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        vKhglCzfpService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "村组复评-批量删除")
    @ApiOperation(value = "村组复评-批量删除", notes = "村组复评-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.vKhglCzfpService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "村组复评-通过id查询")
    @ApiOperation(value = "村组复评-通过id查询", notes = "村组复评-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        VKhglCzfp vKhglCzfp = vKhglCzfpService.getById(id);
        return Result.ok(vKhglCzfp);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param vKhglCzfp
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, VKhglCzfp vKhglCzfp) {
        return super.exportXls(request, vKhglCzfp, VKhglCzfp.class, "村组复评");
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
        return super.importExcel(request, response, VKhglCzfp.class);
    }

}

package org.cmms.modules.xddagl.xtgl.xddaglsjzdgl.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.xddagl.xtgl.xddaglsjzdgl.entity.Sjzdglpz;
import org.cmms.modules.xddagl.xtgl.xddaglsjzdgl.entity.Xddaglsjzdgl;
import org.cmms.modules.xddagl.xtgl.xddaglsjzdgl.service.ISjzdglpzService;
import org.cmms.modules.xddagl.xtgl.xddaglsjzdgl.service.IXddaglsjzdglService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.xddaglxt.dksjgl.dksjjktz.entity.Dksjjktz;
import org.cmms.modules.xddaglxt.xtgl.sjzdgl.entity.Sjzdgl;
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
 * @Description: 数据字典管理
 * @Author: jeecg-boot
 * @Date: 2022-01-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "数据字典管理")
@RestController
@RequestMapping("/xddaglsjzdgl/xddaglsjzdgl")
public class XddaglsjzdglController extends JeecgController<Xddaglsjzdgl, IXddaglsjzdglService> {
    @Autowired
    private IXddaglsjzdglService xddaglsjzdglService;
     @Autowired
    private ISjzdglpzService sjzdglpzService;

    /**
     * 分页列表查询
     *
     * @param xddaglsjzdgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "数据字典管理-分页列表查询")
    @ApiOperation(value = "数据字典管理-分页列表查询", notes = "数据字典管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Xddaglsjzdgl xddaglsjzdgl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Xddaglsjzdgl> queryWrapper = QueryGenerator.initQueryWrapper(xddaglsjzdgl, req.getParameterMap());
        Page<Xddaglsjzdgl> page = new Page<Xddaglsjzdgl>(pageNo, pageSize);
        IPage<Xddaglsjzdgl> pageList = xddaglsjzdglService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param xddaglsjzdgl
     * @return
     */
    @AutoLog(value = "数据字典管理-添加")
    @ApiOperation(value = "数据字典管理-添加", notes = "数据字典管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Xddaglsjzdgl xddaglsjzdgl) {
        xddaglsjzdglService.save(xddaglsjzdgl);
        return Result.ok("添加成功！");
    }
    /**
     * 编辑
     *
     * @param xddaglsjzdgl
     * @return
     */
    @AutoLog(value = "数据字典管理-编辑")
    @ApiOperation(value = "数据字典管理-编辑", notes = "数据字典管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Xddaglsjzdgl xddaglsjzdgl) {

        LambdaQueryWrapper<Sjzdglpz> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Sjzdglpz::getDictCode,xddaglsjzdgl.getDictCode());
        sjzdglpzService.remove(lambdaQueryWrapper);

        if (CollUtil.isNotEmpty(xddaglsjzdgl.getSjzdglpzList())){
            List<Sjzdglpz> sjzdglpzList = new ArrayList<>();
            for (int i = 0; i < xddaglsjzdgl.getSjzdglpzList().size(); i++) {
                Sjzdglpz sjzdglpz = xddaglsjzdgl.getSjzdglpzList().get(i);
                sjzdglpz.setDictCode(xddaglsjzdgl.getDictCode());
                sjzdglpzList.add(sjzdglpz);
            }

            sjzdglpzService.saveBatch(sjzdglpzList);
        }
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param
     * @return
     */
    @AutoLog(value = "数据字典管理-通过id删除")
    @ApiOperation(value = "数据字典管理-通过id删除", notes = "数据字典管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@Param("dictCode") String dictCode) {
        QueryWrapper<Xddaglsjzdgl> queryWrapper = new QueryWrapper<Xddaglsjzdgl>();
        queryWrapper.eq("dict_code", dictCode);
        xddaglsjzdglService.remove(queryWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "数据字典管理-批量删除")
    @ApiOperation(value = "数据字典管理-批量删除", notes = "数据字典管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.xddaglsjzdglService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "数据字典管理-通过id查询")
    @ApiOperation(value = "数据字典管理-通过id查询", notes = "数据字典管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Xddaglsjzdgl xddaglsjzdgl = xddaglsjzdglService.getById(id);
        return Result.ok(xddaglsjzdgl);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param xddaglsjzdgl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Xddaglsjzdgl xddaglsjzdgl) {
        return super.exportXls(request, xddaglsjzdgl, Xddaglsjzdgl.class, "数据字典管理");
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
        return super.importExcel(request, response, Xddaglsjzdgl.class);
    }

    @RequestMapping("/listByDictCode")
    public Result<?> listByDictCode(String dictCode){
        LambdaQueryWrapper<Sjzdglpz> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Sjzdglpz::getDictCode,dictCode);
        return Result.ok(sjzdglpzService.list(lambdaQueryWrapper));
    }

}

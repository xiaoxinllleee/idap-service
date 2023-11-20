package org.cmms.modules.dkjkpt.dksjjk.dkqxyj.controller;

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
import org.cmms.modules.dkjkpt.dksjjk.dkqxyj.entity.Dkqxyj;
import org.cmms.modules.dkjkpt.dksjjk.dkqxyj.service.IDkqxyjService;

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
 * @Description: 贷款欠息预警
 * @Author: jeecg-boot
 * @Date: 2022-09-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "贷款欠息预警")
@RestController
@RequestMapping("/dkjkpt/dkqxyj")
public class DkqxyjController extends JeecgController<Dkqxyj, IDkqxyjService> {
    @Autowired
    private IDkqxyjService dkqxyjService;

    /**
     * 分页列表查询
     *
     * @param dkqxyj
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "贷款欠息预警-分页列表查询")
    @ApiOperation(value = "贷款欠息预警-分页列表查询", notes = "贷款欠息预警-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Dkqxyj dkqxyj,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Dkqxyj> queryWrapper = QueryGenerator.initQueryWrapper(dkqxyj, req.getParameterMap());
        Page<Dkqxyj> page = new Page<Dkqxyj>(pageNo, pageSize);
        IPage<Dkqxyj> pageList = dkqxyjService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param dkqxyj
     * @return
     */
    @AutoLog(value = "贷款欠息预警-添加")
    @ApiOperation(value = "贷款欠息预警-添加", notes = "贷款欠息预警-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Dkqxyj dkqxyj) {
        dkqxyjService.save(dkqxyj);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param dkqxyj
     * @return
     */
    @AutoLog(value = "贷款欠息预警-编辑")
    @ApiOperation(value = "贷款欠息预警-编辑", notes = "贷款欠息预警-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Dkqxyj dkqxyj) {
        dkqxyjService.updateById(dkqxyj);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "贷款欠息预警-通过id删除")
    @ApiOperation(value = "贷款欠息预警-通过id删除", notes = "贷款欠息预警-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        dkqxyjService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "贷款欠息预警-批量删除")
    @ApiOperation(value = "贷款欠息预警-批量删除", notes = "贷款欠息预警-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.dkqxyjService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "贷款欠息预警-通过id查询")
    @ApiOperation(value = "贷款欠息预警-通过id查询", notes = "贷款欠息预警-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Dkqxyj dkqxyj = dkqxyjService.getById(id);
        return Result.ok(dkqxyj);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param dkqxyj
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Dkqxyj dkqxyj) {
        return super.exportXls(request, dkqxyj, Dkqxyj.class, "贷款欠息预警");
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
        return super.importExcel(request, response, Dkqxyj.class);
    }

    /**
     * 提取
     *
     * @return
     */
    @RequestMapping(value = "/init", method = RequestMethod.PUT)
    public Result<?> initData() {
        try {
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			dkqxyjService.initData(sysUser.getUsername());
        } catch (Exception e) {
            log.error("提取失败！", e.getMessage());
            return Result.error(e.getMessage());
        }
        return Result.ok("提取成功！");
    }

}

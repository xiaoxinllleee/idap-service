package org.cmms.modules.zhgl.jxtj.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.zhgl.jxtj.entity.EepWageGzhzbCl2;
import org.cmms.modules.zhgl.jxtj.service.IEepWageGzhzbCl2Service;

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
 * @Description: 慈利工资表
 * @Author: jeecg-boot
 * @Date: 2023-02-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "慈利工资表" )
@RestController
@RequestMapping("/jxtj/eepWageGzhzbCl2" )
public class EepWageGzhzbCl2Controller extends JeecgController<EepWageGzhzbCl2, IEepWageGzhzbCl2Service> {
    @Autowired
    private IEepWageGzhzbCl2Service eepWageGzhzbCl2Service;

    /**
     * 分页列表查询
     *
     * @param eepWageGzhzbCl2
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "慈利工资表-分页列表查询" )
    @ApiOperation(value = "慈利工资表-分页列表查询" , notes = "慈利工资表-分页列表查询" )
    @GetMapping(value = "/list" )
    public Result<?> queryPageList(EepWageGzhzbCl2 eepWageGzhzbCl2,
                                   @RequestParam(name = "pageNo" , defaultValue = "1" ) Integer pageNo,
                                   @RequestParam(name = "pageSize" , defaultValue = "10" ) Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<EepWageGzhzbCl2> queryWrapper = QueryGenerator.initQueryWrapper(eepWageGzhzbCl2, req.getParameterMap());
        Page<EepWageGzhzbCl2> page = new Page<EepWageGzhzbCl2>(pageNo, pageSize);
        IPage<EepWageGzhzbCl2> pageList = eepWageGzhzbCl2Service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @GetMapping(value = "/list2" )
    public Result<?> queryPageList2(EepWageGzhzbCl2 eepWageGzhzbCl2,
                                    @RequestParam(name = "pageNo" , defaultValue = "1" ) Integer pageNo,
                                    @RequestParam(name = "pageSize" , defaultValue = "10" ) Integer pageSize,
                                    HttpServletRequest req) {
        QueryWrapper<EepWageGzhzbCl2> queryWrapper = QueryGenerator.initQueryWrapper(eepWageGzhzbCl2, req.getParameterMap());
        queryWrapper.eq("yggh" , getWorkNo());
        queryWrapper.orderByDesc("gzrq" );
        Page<EepWageGzhzbCl2> page = new Page<EepWageGzhzbCl2>(pageNo, pageSize);
        IPage<EepWageGzhzbCl2> pageList = eepWageGzhzbCl2Service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param eepWageGzhzbCl2
     * @return
     */
    @AutoLog(value = "慈利工资表-添加" )
    @ApiOperation(value = "慈利工资表-添加" , notes = "慈利工资表-添加" )
    @PostMapping(value = "/add" )
    public Result<?> add(@RequestBody EepWageGzhzbCl2 eepWageGzhzbCl2) {
        eepWageGzhzbCl2Service.save(eepWageGzhzbCl2);
        return Result.ok("添加成功！" );
    }

    /**
     * 编辑
     *
     * @param eepWageGzhzbCl2
     * @return
     */
    @AutoLog(value = "慈利工资表-编辑" )
    @ApiOperation(value = "慈利工资表-编辑" , notes = "慈利工资表-编辑" )
    @PutMapping(value = "/edit" )
    public Result<?> edit(@RequestBody EepWageGzhzbCl2 eepWageGzhzbCl2) {
        eepWageGzhzbCl2Service.updateById(eepWageGzhzbCl2);
        return Result.ok("编辑成功!" );
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "慈利工资表-通过id删除" )
    @ApiOperation(value = "慈利工资表-通过id删除" , notes = "慈利工资表-通过id删除" )
    @DeleteMapping(value = "/delete" )
    public Result<?> delete(@RequestParam(name = "id" , required = true) String id) {
        eepWageGzhzbCl2Service.removeById(id);
        return Result.ok("删除成功!" );
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "慈利工资表-批量删除" )
    @ApiOperation(value = "慈利工资表-批量删除" , notes = "慈利工资表-批量删除" )
    @DeleteMapping(value = "/deleteBatch" )
    public Result<?> deleteBatch(@RequestParam(name = "ids" , required = true) String ids) {
        this.eepWageGzhzbCl2Service.removeByIds(Arrays.asList(ids.split("," )));
        return Result.ok("批量删除成功！" );
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "慈利工资表-通过id查询" )
    @ApiOperation(value = "慈利工资表-通过id查询" , notes = "慈利工资表-通过id查询" )
    @GetMapping(value = "/queryById" )
    public Result<?> queryById(@RequestParam(name = "id" , required = true) String id) {
        EepWageGzhzbCl2 eepWageGzhzbCl2 = eepWageGzhzbCl2Service.getById(id);
        return Result.ok(eepWageGzhzbCl2);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param eepWageGzhzbCl2
     */
    @RequestMapping(value = "/exportXls" )
    public ModelAndView exportXls(HttpServletRequest request, EepWageGzhzbCl2 eepWageGzhzbCl2) {
        return super.exportXls(request, eepWageGzhzbCl2, EepWageGzhzbCl2.class, "慈利工资表" );
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel" , method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, EepWageGzhzbCl2.class);
    }

    @GetMapping(value = "/getMaxImpday" )
    public Result<?> getMaxImpday() {
        Date maxImpday = service.getMaxImpday();
        if (maxImpday != null){
            String format = DateUtil.format(maxImpday, DatePattern.CHINESE_DATE_PATTERN);
            return Result.ok(format);
        }
        return Result.ok();
    }
}

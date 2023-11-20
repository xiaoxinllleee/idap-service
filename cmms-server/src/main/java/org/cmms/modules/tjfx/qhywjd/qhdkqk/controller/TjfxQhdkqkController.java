package org.cmms.modules.tjfx.qhywjd.qhdkqk.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.qhywjd.qhdkqk.entity.TjfxQhdkqk;
import org.cmms.modules.tjfx.qhywjd.qhdkqk.service.ITjfxQhdkqkService;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.util.DateUtil;
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
 * @Description: 全行贷款情况
 * @Author: jeecg-boot
 * @Date: 2023-08-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "全行贷款情况")
@RestController
@RequestMapping("/qhdkqk/tjfxqhdkqk")
public class TjfxQhdkqkController extends JeecgController<TjfxQhdkqk, ITjfxQhdkqkService> {
    @Autowired
    private ITjfxQhdkqkService tjfxQhdkqkService;

    /**
     * 分页列表查询
     *
     * @param tjfxQhdkqk
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "全行贷款情况-分页列表查询")
    @ApiOperation(value = "全行贷款情况-分页列表查询", notes = "全行贷款情况-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(TjfxQhdkqk tjfxQhdkqk,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<TjfxQhdkqk> queryWrapper = QueryGenerator.initQueryWrapper(tjfxQhdkqk, req.getParameterMap());
        queryWrapper.orderByAsc("to_number(sszh)");
        Page<TjfxQhdkqk> page = new Page<TjfxQhdkqk>(pageNo, pageSize);
        IPage<TjfxQhdkqk> pageList = tjfxQhdkqkService.page(page, queryWrapper);
        if (pageList.getPages()==pageNo){
            String sjrq=tjfxQhdkqk.getSjrq()==null?null: DateUtil.dateToString(tjfxQhdkqk.getSjrq(),"yyyy-MM-dd").replace("-","");
            TjfxQhdkqk newTjfxQhdkqk=tjfxQhdkqkService.getHjDate(sjrq,tjfxQhdkqk.getSszh());
            List<TjfxQhdkqk> list=pageList.getRecords();
            list.add(newTjfxQhdkqk);
            pageList.setRecords(list);
        }
        return Result.ok(pageList);
    }

    /**
     * 获取表内最大日期
     */
    @GetMapping("getMaxDate")
    public Result<?> getMaxDate(){
        return Result.ok(tjfxQhdkqkService.getMaxDate());
    }

    /**
     * 添加
     *
     * @param tjfxQhdkqk
     * @return
     */
    @AutoLog(value = "全行贷款情况-添加")
    @ApiOperation(value = "全行贷款情况-添加", notes = "全行贷款情况-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody TjfxQhdkqk tjfxQhdkqk) {
        tjfxQhdkqkService.save(tjfxQhdkqk);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param tjfxQhdkqk
     * @return
     */
    @AutoLog(value = "全行贷款情况-编辑")
    @ApiOperation(value = "全行贷款情况-编辑", notes = "全行贷款情况-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody TjfxQhdkqk tjfxQhdkqk) {
        tjfxQhdkqkService.updateById(tjfxQhdkqk);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "全行贷款情况-通过id删除")
    @ApiOperation(value = "全行贷款情况-通过id删除", notes = "全行贷款情况-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        tjfxQhdkqkService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "全行贷款情况-批量删除")
    @ApiOperation(value = "全行贷款情况-批量删除", notes = "全行贷款情况-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.tjfxQhdkqkService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "全行贷款情况-通过id查询")
    @ApiOperation(value = "全行贷款情况-通过id查询", notes = "全行贷款情况-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        TjfxQhdkqk tjfxQhdkqk = tjfxQhdkqkService.getById(id);
        return Result.ok(tjfxQhdkqk);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param tjfxQhdkqk
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TjfxQhdkqk tjfxQhdkqk) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        QueryWrapper<TjfxQhdkqk> queryWrapper = QueryGenerator.initQueryWrapper(tjfxQhdkqk, request.getParameterMap());
        queryWrapper.orderByAsc("to_number(sszh)");
        List<TjfxQhdkqk> exportList = service.list(queryWrapper);

        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "全行贷款情况");
        mv.addObject(NormalExcelConstants.CLASS, TjfxQhdkqk.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("全行贷款情况" + "报表", "导出人:" + sysUser.getRealname(), "全行贷款情况"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
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
        return super.importExcel(request, response, TjfxQhdkqk.class);
    }

    /**
     * 提取数据
     */
    @RequestMapping(value = "/initData", method = RequestMethod.PUT)
    public Result<?> initData(@RequestBody JSONObject object) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String sjrq=object.getString("sjrq");
        if (StringUtils.isBlank(sjrq)){
            return Result.error("数据提取日期不能为空！");
        }
        try {
            service.initData(sjrq.replace("-",""),loginUser.getWorkNo());
        } catch (Exception e) {
            log.error("提取失败！", e.getMessage());
            return Result.error(e.getMessage());
        }
        return Result.ok("提取成功！");
    }

}

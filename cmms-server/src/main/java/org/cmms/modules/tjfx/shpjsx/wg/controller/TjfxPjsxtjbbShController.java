package org.cmms.modules.tjfx.shpjsx.wg.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.tjfx.pjsxtjbb.vo.NhPjsxxxMx;
import org.cmms.modules.tjfx.shpjsx.wg.entity.ShPjsxMxVo;
import org.cmms.modules.tjfx.shpjsx.wg.entity.TjfxPjsxtjbbSh;
import org.cmms.modules.tjfx.shpjsx.wg.service.ITjfxPjsxtjbbShService;
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
 * @Description: 评级授信统计报表-商户
 * @Author: jeecg-boot
 * @Date: 2023-09-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "评级授信统计报表-商户")
@RestController
@RequestMapping("/shpjsx/wg/tjfxPjsxtjbbSh")
public class TjfxPjsxtjbbShController extends JeecgController<TjfxPjsxtjbbSh, ITjfxPjsxtjbbShService> {
    @Autowired
    private ITjfxPjsxtjbbShService tjfxPjsxtjbbShService;

    /**
     * 分页列表查询
     *
     * @param tjfxPjsxtjbbSh
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "评级授信统计报表-商户-分页列表查询")
    @ApiOperation(value = "评级授信统计报表-商户-分页列表查询", notes = "评级授信统计报表-商户-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(TjfxPjsxtjbbSh tjfxPjsxtjbbSh,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<TjfxPjsxtjbbSh> queryWrapper = QueryGenerator.initQueryWrapper(tjfxPjsxtjbbSh, req.getParameterMap());
        queryWrapper.orderByDesc("sjrq");
        Page<TjfxPjsxtjbbSh> page = new Page<TjfxPjsxtjbbSh>(pageNo, pageSize);
        IPage<TjfxPjsxtjbbSh> pageList = tjfxPjsxtjbbShService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @GetMapping("/queryPageListMx")
    public Result<?> queryPageListMx(@RequestParam("sjrq")String sjrq,
                                     @RequestParam("sswg")String sswg,
                                     @RequestParam("type")String type,
                                     @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                     @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                     HttpServletRequest req){
        Page<ShPjsxMxVo> page = new Page<>(pageNo, pageSize);
        IPage<ShPjsxMxVo> pageList=tjfxPjsxtjbbShService.queryPageListMx(page,sjrq.replace("-",""),sswg,type);
        return Result.ok(pageList);
    }

    @RequestMapping(value = "/exportShPjsxMxXls")
    public ModelAndView exportZfsjmxXls( String sswg,String sjrq,String type,
                                         HttpServletRequest request) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        List<ShPjsxMxVo> exportList=service.queryListMx(sjrq.replace("-",""),sswg,type);

        String title = "商户评级授信统计";
        if ("1".equals(type)){
            title= "商户评级授信评级户数统计";
        }else{
            title= "商户评级授信授信户数统计";
        }
        // Step.3 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, ShPjsxMxVo.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 添加
     *
     * @param tjfxPjsxtjbbSh
     * @return
     */
    @AutoLog(value = "评级授信统计报表-商户-添加")
    @ApiOperation(value = "评级授信统计报表-商户-添加", notes = "评级授信统计报表-商户-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody TjfxPjsxtjbbSh tjfxPjsxtjbbSh) {
        tjfxPjsxtjbbShService.save(tjfxPjsxtjbbSh);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param tjfxPjsxtjbbSh
     * @return
     */
    @AutoLog(value = "评级授信统计报表-商户-编辑")
    @ApiOperation(value = "评级授信统计报表-商户-编辑", notes = "评级授信统计报表-商户-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody TjfxPjsxtjbbSh tjfxPjsxtjbbSh) {
        tjfxPjsxtjbbShService.updateById(tjfxPjsxtjbbSh);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "评级授信统计报表-商户-通过id删除")
    @ApiOperation(value = "评级授信统计报表-商户-通过id删除", notes = "评级授信统计报表-商户-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        tjfxPjsxtjbbShService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "评级授信统计报表-商户-批量删除")
    @ApiOperation(value = "评级授信统计报表-商户-批量删除", notes = "评级授信统计报表-商户-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.tjfxPjsxtjbbShService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "评级授信统计报表-商户-通过id查询")
    @ApiOperation(value = "评级授信统计报表-商户-通过id查询", notes = "评级授信统计报表-商户-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        TjfxPjsxtjbbSh tjfxPjsxtjbbSh = tjfxPjsxtjbbShService.getById(id);
        return Result.ok(tjfxPjsxtjbbSh);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param tjfxPjsxtjbbSh
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TjfxPjsxtjbbSh tjfxPjsxtjbbSh) {
        return super.exportXls(request, tjfxPjsxtjbbSh, TjfxPjsxtjbbSh.class, "评级授信统计报表-商户");
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
        return super.importExcel(request, response, TjfxPjsxtjbbSh.class);
    }

    @PostMapping("/initData")
    public Result<?> initData(@RequestBody JSONObject jsonObject) {
        String sjrq = jsonObject.getString("sjrq");
        System.out.println(sjrq + "========sjrq");
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        Result result = new Result<>();
        try {
            tjfxPjsxtjbbShService.initData(sjrq.replace("-", ""), loginUser.getWorkNo());
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            System.out.println(e);
            log.error("提取失败", e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

}

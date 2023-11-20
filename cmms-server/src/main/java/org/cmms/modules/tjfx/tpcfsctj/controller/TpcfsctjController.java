package org.cmms.modules.tjfx.tpcfsctj.controller;

import java.io.File;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzxxgl;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzzllb;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhhzxxglService;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhhzzllbService;
import org.cmms.modules.tjfx.tpcfsctj.entity.*;
import org.cmms.modules.tjfx.tpcfsctj.service.*;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 图片重复上传统计
 * @Author: jeecg-boot
 * @Date: 2021-06-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "图片重复上传统计")
@RestController
@RequestMapping("/tjfx/tpcfsctj")
public class TpcfsctjController extends JeecgController<Tpcfsctj, ITpcfsctjService> {
    @Autowired
    private ITpcfsctjService tpcfsctjService;
    @Autowired
    private IDhtpcfsctjService dhtpcfsctjService;
    @Autowired
    private IXzctpcfsctjService xzctpcfsctjService;
    @Autowired
    private IKhglNhhzzllbService khglNhhzzllbService;
    @Autowired
    private IKhglNhhzxxglService khglNhhzxxglService;
    @Autowired
    private IVTpcfsctjService vTpcfsctjService;
    @Autowired
    private IVDhtpcfsctjService vDhtpcfsctjService;

    /**
     * 分页列表查询
     *
     * @param tpcfsctj
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "图片重复上传统计-分页列表查询")
    @ApiOperation(value = "图片重复上传统计-分页列表查询", notes = "图片重复上传统计-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Tpcfsctj tpcfsctj,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Tpcfsctj> queryWrapper = QueryGenerator.initQueryWrapper(tpcfsctj, req.getParameterMap());
        Page<Tpcfsctj> page = new Page<Tpcfsctj>(pageNo, pageSize);
        IPage<Tpcfsctj> pageList = tpcfsctjService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 分页列表查询
     *
     * @param dhtpcfsctj
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "图片重复上传统计-分页列表查询")
    @ApiOperation(value = "图片重复上传统计-分页列表查询", notes = "图片重复上传统计-分页列表查询")
    @GetMapping(value = "/dhlist")
    public Result<?> queryDhPageList(Dhtpcfsctj dhtpcfsctj,
                                     @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                     HttpServletRequest req) {
        QueryWrapper<Dhtpcfsctj> queryWrapper = QueryGenerator.initQueryWrapper(dhtpcfsctj, req.getParameterMap());
        Page<Dhtpcfsctj> page = new Page<Dhtpcfsctj>(pageNo, pageSize);
        IPage<Dhtpcfsctj> pageList = dhtpcfsctjService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 分页列表查询
     *
     * @param xzctpcfsctj
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "行政村图片重复上传统计-分页列表查询")
    @ApiOperation(value = "行政村图片重复上传统计-分页列表查询", notes = "行政村图片重复上传统计-分页列表查询")
    @GetMapping(value = "/xzclist")
    public Result<?> queryXzcPageList(Xzctpcfsctj xzctpcfsctj,
                                      @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                      HttpServletRequest req) {
        QueryWrapper<Xzctpcfsctj> queryWrapper = QueryGenerator.initQueryWrapper(xzctpcfsctj, req.getParameterMap());
        Page<Xzctpcfsctj> page = new Page<Xzctpcfsctj>(pageNo, pageSize);
        IPage<Xzctpcfsctj> pageList = xzctpcfsctjService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param tpcfsctj
     * @return
     */
    @AutoLog(value = "图片重复上传统计-添加")
    @ApiOperation(value = "图片重复上传统计-添加", notes = "图片重复上传统计-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Tpcfsctj tpcfsctj) {
        tpcfsctjService.save(tpcfsctj);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param tpcfsctj
     * @return
     */
    @AutoLog(value = "图片重复上传统计-编辑")
    @ApiOperation(value = "图片重复上传统计-编辑", notes = "图片重复上传统计-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Tpcfsctj tpcfsctj) {
        tpcfsctjService.updateById(tpcfsctj);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "图片重复上传统计-通过id删除")
    @ApiOperation(value = "图片重复上传统计-通过id删除", notes = "图片重复上传统计-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        tpcfsctjService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "图片重复上传统计-批量删除")
    @ApiOperation(value = "图片重复上传统计-批量删除", notes = "图片重复上传统计-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.tpcfsctjService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "图片重复上传统计-通过id查询")
    @ApiOperation(value = "图片重复上传统计-通过id查询", notes = "图片重复上传统计-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Tpcfsctj tpcfsctj = tpcfsctjService.getById(id);
        return Result.ok(tpcfsctj);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param tpcfsctj
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Tpcfsctj tpcfsctj) {
        return super.exportXls(request, tpcfsctj, Tpcfsctj.class, "图片重复上传统计");
    }

    /**
     * 导出excel
     *
     * @param request
     * @param vDhtpcfsctj
     */
    @RequestMapping(value = "/exportDhXls")
    public ModelAndView exportDhXls(HttpServletRequest request, VDhtpcfsctj vDhtpcfsctj) {
        // Step.1 组装查询条件
        QueryWrapper<VDhtpcfsctj> queryWrapper = QueryGenerator.initQueryWrapper(vDhtpcfsctj, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        // Step.2 获取导出数据
        List<VDhtpcfsctj> exportList = vDhtpcfsctjService.list(queryWrapper);

        // Step.3 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "单户图片重复上传"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, VDhtpcfsctj.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("单户图片重复上传报表", "导出人:" + sysUser.getRealname(), "单户图片重复上传"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param vTpcfsctj
     */
    @RequestMapping(value = "/exportQhXls")
    public ModelAndView exportQhXls(HttpServletRequest request, VTpcfsctj vTpcfsctj) {
        //获取全行重复图片上传的明细数据
        // Step.1 组装查询条件
        QueryWrapper<VTpcfsctj> queryWrapper = QueryGenerator.initQueryWrapper(vTpcfsctj, request.getParameterMap());
        queryWrapper.orderByAsc("md5");
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        // Step.2 获取导出数据

        List<VTpcfsctj> tpcfsctjList = vTpcfsctjService.list(queryWrapper);

        // Step.3 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "全行图片重复上传详情"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, VTpcfsctj.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("全行图片重复上传详情", "导出人:" + sysUser.getRealname(), "全行图片重复上传详情"));
        mv.addObject(NormalExcelConstants.DATA_LIST, tpcfsctjList);
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
        return super.importExcel(request, response, Tpcfsctj.class);
    }

    @GetMapping(value = "/init")
    public Result<?> init() {
        try {
            //计算图片的MD5值
            QueryWrapper<KhglNhhzzllb> queryWrapper = new QueryWrapper<>();
            queryWrapper.isNull("md5");
            List<KhglNhhzzllb> list = khglNhhzzllbService.list(queryWrapper);
            for (KhglNhhzzllb khglNhhzzllb : list) {
                String fwlj = khglNhhzzllb.getFwlj();
                String filePath = uploadpath + File.separator + fwlj;
                File file = new File(filePath);
                if (!file.exists()) {
                    continue;
                }
                String md5 = DigestUtil.md5Hex(file);
                khglNhhzzllb.setMd5(md5);
                UpdateWrapper<KhglNhhzzllb> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("zlbh", khglNhhzzllb.getZlbh());
                khglNhhzzllbService.update(khglNhhzzllb, updateWrapper);
            }
            tpcfsctjService.init();
        } catch (Exception e) {
            log.error(e.getMessage(), "提取失败！");
            return Result.error(e.getMessage());
        }
        return Result.ok("提取成功！");
    }

    /**
     * 查看详情
     *
     * @param md5
     * @return
     */
    @AutoLog(value = "图片重复上传统计-查看详情")
    @ApiOperation(value = "图片重复上传统计-查看详情", notes = "图片重复上传统计-查看详情")
    @GetMapping(value = "/viewDetail")
    public Result<?> viewDetail(@RequestParam(name = "md5") String md5,
                                @RequestParam(name = "hhbm", required = false) String hhbm,
                                @RequestParam(name = "sszh", required = false) String sszh,
                                @RequestParam(name = "xzc", required = false) String xzc) {
        return Result.ok(getDetail(md5, hhbm, sszh, xzc));
    }

    private List<TpcfsctjExport> getDetail(String md5, String hhbm, String sszh, String xzc) {
        QueryWrapper<KhglNhhzzllb> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        if (!StringUtils.isEmpty(hhbm)) {
            queryWrapper.eq("hhbm", hhbm);
        }
        List<KhglNhhzzllb> list = khglNhhzzllbService.list(queryWrapper);
        if (!StringUtils.isEmpty(sszh)) {
            //根据所属支行与行政村过滤
            List<String> hhbmList = list.stream().map(KhglNhhzzllb::getHhbm).collect(Collectors.toList());
            QueryWrapper<KhglNhhzxxgl> nhhzxxglQueryWrapper = new QueryWrapper<>();
            nhhzxxglQueryWrapper.eq("sszh", sszh);
            nhhzxxglQueryWrapper.eq("xzc", xzc);
            nhhzxxglQueryWrapper.in("hhbm", hhbmList);
            List<KhglNhhzxxgl> nhhzxxglList = khglNhhzxxglService.list(nhhzxxglQueryWrapper);
            List<String> nhhzHhbmList = nhhzxxglList.stream().map(KhglNhhzxxgl::getHhbm).collect(Collectors.toList());
            list = list.stream().filter(item -> nhhzHhbmList.contains(item.getHhbm())).collect(Collectors.toList());
        }
        List<TpcfsctjExport> resultList = new ArrayList<>();
        //查询信息
        for (KhglNhhzzllb khglNhhzzllb : list) {
            boolean exists = resultList.stream().anyMatch(tpcfsctjExport -> tpcfsctjExport.getHhbm().equals(khglNhhzzllb.getHhbm()));
            if (exists && StringUtils.isEmpty(hhbm)) {
                continue;
            }
            QueryWrapper<KhglNhhzxxgl> nhhzxxglQueryWrapper = new QueryWrapper<>();
            nhhzxxglQueryWrapper.eq("hhbm", khglNhhzzllb.getHhbm());
            List<KhglNhhzxxgl> nhhzxxglList = khglNhhzxxglService.list(nhhzxxglQueryWrapper);

            if (!nhhzxxglList.isEmpty()) {
                TpcfsctjExport tpcfsctjExport = new TpcfsctjExport();
                BeanUtils.copyProperties(nhhzxxglList.get(0), tpcfsctjExport);
                tpcfsctjExport.setScr(khglNhhzzllb.getScr());
                tpcfsctjExport.setScsj(khglNhhzzllb.getScsj());
                resultList.add(tpcfsctjExport);
            }
        }
        return resultList;
    }

    /**
     * 导出详情
     *
     * @param md5
     * @return
     */
    @AutoLog(value = "图片重复上传统计-导出详情")
    @ApiOperation(value = "图片重复上传统计-导出详情", notes = "图片重复上传统计-导出详情")
    @GetMapping(value = "/exportDetail")
    public ModelAndView exportDetail(@RequestParam(name = "md5") String md5,
                                     @RequestParam(name = "hhbm", required = false) String hhbm,
                                     @RequestParam(name = "sszh", required = false) String sszh,
                                     @RequestParam(name = "xzc", required = false) String xzc) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        String title = "图片重复上传详情";
        List<TpcfsctjExport> list = getDetail(md5, hhbm, sszh, xzc);
        mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, TpcfsctjExport.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
        mv.addObject(NormalExcelConstants.DATA_LIST, list);
        return mv;
    }
}

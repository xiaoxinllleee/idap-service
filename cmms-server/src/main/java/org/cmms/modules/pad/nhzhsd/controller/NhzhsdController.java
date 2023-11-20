package org.cmms.modules.pad.nhzhsd.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.utils.Base64Util;
import org.cmms.modules.pad.nhczfp.entity.Nhczfp;
import org.cmms.modules.pad.nhczfp.service.INhczfpService;
import org.cmms.modules.pad.nhzhsd.entity.Nhzhsd;
import org.cmms.modules.pad.nhzhsd.service.INhzhsdService;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.service.IYxdyglMainService;
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
 * @Description: 农户支行审定
 * @Author: jeecg-boot
 * @Date: 2023-03-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "农户支行审定")
@RestController
@RequestMapping("/pad/bkbpy/nhzhsd")
public class NhzhsdController extends JeecgController<Nhzhsd, INhzhsdService> {
    @Autowired
    private INhzhsdService nhzhsdService;
    @Autowired
    private INhczfpService nhczfpService;
    @Autowired
    private IYxdyglMainService yxdyglMainService;

    /**
     * 分页列表查询
     *
     * @param nhzhsd
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "农户支行审定-分页列表查询")
    @ApiOperation(value = "农户支行审定-分页列表查询", notes = "农户支行审定-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Nhzhsd nhzhsd,
                                   @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Nhzhsd> queryWrapper = QueryGenerator.initQueryWrapper(nhzhsd, req.getParameterMap());
        if (StringUtils.isNotEmpty(getWorkNo())) {
            queryWrapper.eq("sdkhjl", getWorkNo());
        } else {
            queryWrapper.isNull("sdkhjl");
        }
        queryWrapper.orderByDesc("sfwcsd", "create_time");
        Page<Nhzhsd> page = new Page<Nhzhsd>(currentPage, pageSize);
        IPage<Nhzhsd> pageList = nhzhsdService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 查询不符合条件-支行审定的网格
     */
    @GetMapping("/getYsdwgxx")
    public Result<?> getYsdwgxx() {
        //已经审定过的
        List<String> nhzhsdList = nhzhsdService.list().stream().map(Nhzhsd::getSdwg).distinct().collect(Collectors.toList());

        List<String> nhzhsdWgList = new ArrayList<>();
        for (int i = 0; i < nhzhsdList.size(); i++) {
            String nhzhsdWg = nhzhsdList.get(i);
            if (StringUtils.isNotEmpty(nhzhsdWg)) {
                nhzhsdWgList.addAll(Arrays.asList(nhzhsdWg.split(",")));
            }
        }
        nhzhsdWgList = nhzhsdWgList.stream().distinct().collect(Collectors.toList());

        //该网格下的组是否全部复评完，没有复评完的不允许支行审定
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        List<String> nhczfpWgList =nhzhsdService.getWwcCzfpWg(sysUser.getWorkNo());
        List<String> disableZhsdList = new ArrayList<>();
        if (CollUtil.isNotEmpty(nhzhsdWgList)) {
            disableZhsdList.addAll(nhzhsdWgList);
        }
        if (CollUtil.isNotEmpty(nhczfpWgList)) {
            disableZhsdList.addAll(nhczfpWgList);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nhzhsdWgList", nhzhsdWgList);
        jsonObject.put("disableZhsdList", disableZhsdList.stream().distinct().collect(Collectors.toList()));
        return Result.ok(jsonObject);
    }

    /**
     * 添加
     *
     * @param jsonObject
     * @return
     */
    @AutoLog(value = "农户支行审定-添加")
    @ApiOperation(value = "农户支行审定-添加", notes = "农户支行审定-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody JSONObject jsonObject) {
        Nhzhsd nhzhsd = JSONObject.toJavaObject(jsonObject, Nhzhsd.class);
        QueryWrapper<Nhzhsd> existCheck = new QueryWrapper<>();
        existCheck.eq("sdwg", nhzhsd.getSdwg());
        List<Nhzhsd> list = nhzhsdService.list(existCheck);
        if (!list.isEmpty()) {
            return Result.error("此网格已存在支行审定数据，请确认！");
        }
        nhzhsd.setSdkhjl(getWorkNo());
        nhzhsd.setSfwcsd("2");
        String fwlj = "/sign/nhplpy/";
        if (!FileUtil.isDirectory(uploadpath + fwlj)) {
            FileUtil.mkdir(uploadpath + fwlj);
        }
        String qz = "data:image/png;base64,";
        if (StringUtils.isNotEmpty(nhzhsd.getSdzhzdzqm()) && nhzhsd.getSdzhzdzqm().startsWith(qz)) {
            String fileName = IdUtil.fastSimpleUUID() + ".png";
            String wjlj = uploadpath + fwlj + fileName;
            Base64Util.toImage(nhzhsd.getSdzhzdzqm(), wjlj);
            nhzhsd.setSdzhzdzqm(fwlj + fileName);
        }
        if (StringUtils.isNotEmpty(nhzhsd.getSdfhzdzqm()) && nhzhsd.getSdfhzdzqm().startsWith(qz)) {
            String fileName = IdUtil.fastSimpleUUID() + ".png";
            String wjlj = uploadpath + fwlj + fileName;
            Base64Util.toImage(nhzhsd.getSdfhzdzqm(), wjlj);
            nhzhsd.setSdfhzdzqm(fwlj + fileName);
        }
        if (StringUtils.isNotEmpty(nhzhsd.getSdkhjldzqm()) && nhzhsd.getSdkhjldzqm().startsWith(qz)) {
            String fileName = IdUtil.fastSimpleUUID() + ".png";
            String wjlj = uploadpath + fwlj + fileName;
            Base64Util.toImage(nhzhsd.getSdkhjldzqm(), wjlj);
            nhzhsd.setSdkhjldzqm(fwlj + fileName);
        }
        String id = IdUtil.simpleUUID();
        nhzhsd.setId(id);
        nhzhsdService.save(nhzhsd);
        return Result.ok(id);
    }

    /**
     * 编辑
     *
     * @param nhzhsd
     * @return
     */
    @AutoLog(value = "农户支行审定-编辑")
    @ApiOperation(value = "农户支行审定-编辑", notes = "农户支行审定-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Nhzhsd nhzhsd) {
        nhzhsdService.updateById(nhzhsd);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "农户支行审定-通过id删除")
    @ApiOperation(value = "农户支行审定-通过id删除", notes = "农户支行审定-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        nhzhsdService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "农户支行审定-批量删除")
    @ApiOperation(value = "农户支行审定-批量删除", notes = "农户支行审定-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.nhzhsdService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "农户支行审定-通过id查询")
    @ApiOperation(value = "农户支行审定-通过id查询", notes = "农户支行审定-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Nhzhsd nhzhsd = nhzhsdService.getById(id);
        return Result.ok(nhzhsd);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param nhzhsd
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Nhzhsd nhzhsd) {
        return super.exportXls(request, nhzhsd, Nhzhsd.class, "农户支行审定");
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
        return super.importExcel(request, response, Nhzhsd.class);
    }

}

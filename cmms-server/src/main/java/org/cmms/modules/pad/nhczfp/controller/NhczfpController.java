package org.cmms.modules.pad.nhczfp.controller;

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
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
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
import org.cmms.modules.khgl.nh.entity.Nhbkbpy;
import org.cmms.modules.khgl.nh.service.INhbkbpyService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.pad.nhczfp.entity.Nhczfp;
import org.cmms.modules.pad.nhczfp.entity.NhczfpVo;
import org.cmms.modules.pad.nhczfp.entity.VKhglCzfp;
import org.cmms.modules.pad.nhczfp.service.INhczfpService;
import org.cmms.modules.pad.nhczfp.service.IVKhglCzfpService;
import org.cmms.modules.pad.pyxx.entity.VKhglNhplpy;
import org.cmms.modules.pad.pyxx.service.IVKhglNhplpyService;
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
 * @Description: 农户村组复评
 * @Author: jeecg-boot
 * @Date: 2023-03-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "农户村组复评")
@RestController
@RequestMapping("/pad/bkbpy/nhczfp")
public class NhczfpController extends JeecgController<Nhczfp, INhczfpService> {
    @Autowired
    private INhczfpService nhczfpService;
    @Autowired
    private INhxqService nhxqService;
    @Autowired
    private IVKhglCzfpService ivKhglCzfpService;
    @Autowired
    private IYxdyglMainService yxdyglMainService;

    /**
     * 分页列表查询
     *
     * @param nhczfp
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "农户村组复评-分页列表查询")
    @ApiOperation(value = "农户村组复评-分页列表查询", notes = "农户村组复评-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Nhczfp nhczfp,
                                   @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Nhczfp> queryWrapper = QueryGenerator.initQueryWrapper(nhczfp, req.getParameterMap());
        if (StringUtils.isNotEmpty(getWorkNo())) {
            queryWrapper.eq("fpkhjl", getWorkNo());
        } else {
            queryWrapper.isNull("fpkhjl");
        }
        queryWrapper.orderByDesc("sfwcfp", "create_time");
        Page<Nhczfp> page = new Page<Nhczfp>(currentPage, pageSize);
        IPage<Nhczfp> pageList = nhczfpService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 查询是否符合复评条件
     */
    @GetMapping("/ableToCzfp")
    public Result<?> ableToCzfp(@RequestParam("fpwg") String fpwg) {
        //条件1是否已经复评过
        QueryWrapper<Nhczfp> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(fpwg)) {
            queryWrapper.eq("fpwg", fpwg);
        }
        List<String> nhczfpList = nhczfpService.list(queryWrapper).stream().map(Nhczfp::getFpwgChild).distinct().collect(Collectors.toList());

        List<String> nhczfpWgList = new ArrayList<>();
        for (int i = 0; i < nhczfpList.size(); i++) {
            String nhczfpWg = nhczfpList.get(i);
            if (StringUtils.isNotEmpty(nhczfpWg)) {
                nhczfpWgList.addAll(Arrays.asList(nhczfpWg.split(",")));
            }
        }
        nhczfpWgList = nhczfpWgList.stream().distinct().collect(Collectors.toList());
        List<NhczfpVo> list = nhczfpService.getYwcbkbpyList(fpwg);
        List<String> nhczfpVoList = list.stream().filter(item->item.getBmdhs()!=item.getBmdypyhs()).map(NhczfpVo::getSsyxdy).collect(Collectors.toList());
        List<String> ywccpList = list.stream().filter(item->item.getBmdhs()==item.getBmdypyhs()).map(NhczfpVo::getSsyxdy).collect(Collectors.toList());
        List<String> disabledList=new ArrayList<>();
        if (CollUtil.isNotEmpty(nhczfpWgList)){
            disabledList.addAll(nhczfpWgList);
        }
        if (CollUtil.isNotEmpty(nhczfpVoList)){
            disabledList.addAll(nhczfpVoList);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("disabledList", disabledList.stream().distinct().collect(Collectors.toList()));
        jsonObject.put("ywcfpList", nhczfpWgList); //已完成复评
        jsonObject.put("ywccpList", ywccpList); //已完成初评
        return Result.ok(jsonObject);
    }

    /**
     * 添加
     *
     * @param jsonObject
     * @return
     */
    @AutoLog(value = "农户村组复评-添加")
    @ApiOperation(value = "农户村组复评-添加", notes = "农户村组复评-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody JSONObject jsonObject) {
        Nhczfp nhczfp = JSONObject.toJavaObject(jsonObject, Nhczfp.class);
        String fprKhid1 = jsonObject.getString("fprKhid1");
        String fprKhid2 = jsonObject.getString("fprKhid2");
        String fprKhid3 = jsonObject.getString("fprKhid3");
        String fprKhid4 = jsonObject.getString("fprKhid4");
        if (StringUtils.isNotEmpty(fprKhid1)) {
            Nhxq nhxq = nhxqService.getById(fprKhid1);
            nhczfp.setFprzjhm1(nhxq.getZjhm());
        }
        if (StringUtils.isNotEmpty(fprKhid2)) {
            Nhxq nhxq = nhxqService.getById(fprKhid2);
            nhczfp.setFprzjhm2(nhxq.getZjhm());
        }
        if (StringUtils.isNotEmpty(fprKhid3)) {
            Nhxq nhxq = nhxqService.getById(fprKhid3);
            nhczfp.setFprzjhm3(nhxq.getZjhm());
        }
        if (StringUtils.isNotEmpty(fprKhid4)) {
            Nhxq nhxq = nhxqService.getById(fprKhid4);
            nhczfp.setFprzjhm4(nhxq.getZjhm());
        }
        nhczfp.setFpkhjl(getWorkNo());
        nhczfp.setSfwcfp("2");

        String fwlj = "/sign/nhplpy/";
        if (!FileUtil.isDirectory(uploadpath + fwlj)) {
            FileUtil.mkdir(uploadpath + fwlj);
        }
        String qz = "data:image/png;base64,";

        if (StringUtils.isNotEmpty(nhczfp.getFprdzqm1()) && nhczfp.getFprdzqm1().startsWith(qz)) {
            String fileName = IdUtil.fastSimpleUUID() + ".png";
            String wjlj = uploadpath + fwlj + fileName;
            Base64Util.toImage(nhczfp.getFprdzqm1(), wjlj);
            nhczfp.setFprdzqm1(fwlj + fileName);
        }
        if (StringUtils.isNotEmpty(nhczfp.getFprdzqm2()) && nhczfp.getFprdzqm2().startsWith(qz)) {
            String fileName = IdUtil.fastSimpleUUID() + ".png";
            String wjlj = uploadpath + fwlj + fileName;
            Base64Util.toImage(nhczfp.getFprdzqm2(), wjlj);
            nhczfp.setFprdzqm2(fwlj + fileName);
        }
        if (StringUtils.isNotEmpty(nhczfp.getFprdzqm3()) && nhczfp.getFprdzqm3().startsWith(qz)) {
            String fileName = IdUtil.fastSimpleUUID() + ".png";
            String wjlj = uploadpath + fwlj + fileName;
            Base64Util.toImage(nhczfp.getFprdzqm3(), wjlj);
            nhczfp.setFprdzqm3(fwlj + fileName);
        }
        if (StringUtils.isNotEmpty(nhczfp.getFprdzqm4()) && nhczfp.getFprdzqm4().startsWith(qz)) {
            String fileName = IdUtil.fastSimpleUUID() + ".png";
            String wjlj = uploadpath + fwlj + fileName;
            Base64Util.toImage(nhczfp.getFprdzqm4(), wjlj);
            nhczfp.setFprdzqm4(fwlj + fileName);
        }
        if (StringUtils.isNotEmpty(nhczfp.getFpkhjldzqm()) && nhczfp.getFpkhjldzqm().startsWith(qz)) {
            String fileName = IdUtil.fastSimpleUUID() + ".png";
            String wjlj = uploadpath + fwlj + fileName;
            Base64Util.toImage(nhczfp.getFpkhjldzqm(), wjlj);
            nhczfp.setFpkhjldzqm(fwlj + fileName);
        }
        String id = IdUtil.simpleUUID();
        nhczfp.setId(id);
        nhczfpService.save(nhczfp);
        return Result.ok(id);
    }

    /**
     * 编辑
     *
     * @param nhczfp
     * @return
     */
    @AutoLog(value = "农户村组复评-编辑")
    @ApiOperation(value = "农户村组复评-编辑", notes = "农户村组复评-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Nhczfp nhczfp) {
        nhczfpService.updateById(nhczfp);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "农户村组复评-通过id删除")
    @ApiOperation(value = "农户村组复评-通过id删除", notes = "农户村组复评-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        nhczfpService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "农户村组复评-批量删除")
    @ApiOperation(value = "农户村组复评-批量删除", notes = "农户村组复评-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.nhczfpService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "农户村组复评-通过id查询")
    @ApiOperation(value = "农户村组复评-通过id查询", notes = "农户村组复评-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Nhczfp nhczfp = nhczfpService.getById(id);
        return Result.ok(nhczfp);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param nhczfp
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Nhczfp nhczfp) {
        return super.exportXls(request, nhczfp, Nhczfp.class, "农户村组复评");
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
        return super.importExcel(request, response, Nhczfp.class);
    }

}

package org.cmms.modules.khxxgl.khflgl.nhxq.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.khgl.nh.entity.CamsPlpyYsfj;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.XxnyztLyCjqk;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IXxnyztLyCjqkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 采集情况
 * @Author: jeecg-boot
 * @Date: 2023-07-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "采集情况")
@RestController
@RequestMapping("/xxnyzt/xxnyztLyCjqk")
public class XxnyztLyCjqkController extends JeecgController<XxnyztLyCjqk, IXxnyztLyCjqkService> {

    /**
     * 分页列表查询
     *
     * @param xxnyztLyCjqk
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "采集情况-分页列表查询")
    @ApiOperation(value = "采集情况-分页列表查询", notes = "采集情况-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(XxnyztLyCjqk xxnyztLyCjqk,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<XxnyztLyCjqk> queryWrapper = QueryGenerator.initQueryWrapper(xxnyztLyCjqk, req.getParameterMap());
        Page<XxnyztLyCjqk> page = new Page<XxnyztLyCjqk>(pageNo, pageSize);
        IPage<XxnyztLyCjqk> pageList = service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param xxnyztLyCjqk
     * @return
     */
    @AutoLog(value = "采集情况-添加")
    @ApiOperation(value = "采集情况-添加", notes = "采集情况-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody XxnyztLyCjqk xxnyztLyCjqk) {
        service.save(xxnyztLyCjqk);
        return Result.ok("添加成功！");
    }

    @PostMapping(value = "/add2")
    public Result<?> add(@RequestBody JSONObject jsonObject) {
        System.out.println(jsonObject);
        String zzpz = jsonObject.getString("zzpz");
        String jylb = jsonObject.getString("jylb");
        String ztid = jsonObject.getString("ztid");



        if (StringUtils.isNotBlank(jylb) && jylb.contains(",")){
            String[] split = jylb.split(",");
            LambdaQueryWrapper<XxnyztLyCjqk> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.in(XxnyztLyCjqk::getJylb,split);
            lambdaQueryWrapper.eq(XxnyztLyCjqk::getZtid,ztid);
            service.remove(lambdaQueryWrapper);
        }else if (StringUtils.isNotBlank(jylb)){
            LambdaQueryWrapper<XxnyztLyCjqk> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.in(XxnyztLyCjqk::getJylb,jylb);
            lambdaQueryWrapper.eq(XxnyztLyCjqk::getZtid,ztid);
            service.remove(lambdaQueryWrapper);
        }
        if (zzpz.contains(",")){
            String[] split = zzpz.split(",");
            String[] split1 = jylb.split(",");
            for (int i = 0; i < split.length; i++) {
                if (StringUtils.isBlank(split1[i]))
                    continue;
                XxnyztLyCjqk xxnyztLyCjqk = new XxnyztLyCjqk();
                xxnyztLyCjqk.setZzpz(split[i]);
                xxnyztLyCjqk.setJylb(split1[i]);
                xxnyztLyCjqk.setZtid(ztid);
                xxnyztLyCjqk.setCreator(getWorkNo());
                xxnyztLyCjqk.setCreateTime(new Date());
                service.save(xxnyztLyCjqk);
            }
        }else if (StringUtils.isNotBlank(zzpz)){
            XxnyztLyCjqk xxnyztLyCjqk = new XxnyztLyCjqk();
            xxnyztLyCjqk.setZzpz(zzpz);
            xxnyztLyCjqk.setJylb(jylb);
            xxnyztLyCjqk.setZtid(ztid);
            xxnyztLyCjqk.setCreator(getWorkNo());
            xxnyztLyCjqk.setCreateTime(new Date());
            service.save(xxnyztLyCjqk);
        }
        return Result.ok("添加成功！");
    }


    @PostMapping(value = "/addList")
    public Result<?> addList(@RequestBody List<XxnyztLyCjqk> xxnyztLyCjqks) {
        if (CollUtil.isNotEmpty(xxnyztLyCjqks)) {
            XxnyztLyCjqk xxnyztLyCjqk = xxnyztLyCjqks.get(0);
            if (StringUtils.isNotBlank(xxnyztLyCjqk.getZtid())) {
                LambdaQueryWrapper<XxnyztLyCjqk> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.in(XxnyztLyCjqk::getJylb,"1","2","3");
                lambdaQueryWrapper.eq(XxnyztLyCjqk::getZtid, xxnyztLyCjqk.getZtid());
                service.remove(lambdaQueryWrapper);
            }

            for (int i = 0; i < xxnyztLyCjqks.size(); i++) {
                XxnyztLyCjqk cjqk = xxnyztLyCjqks.get(i);
                service.save(cjqk);
            }
        }
        return Result.ok("添加成功！");
    }


    @RequestMapping(value = "/getListByZtid")
    public Result<?> getListByZtid(String ztid) {
        if (StringUtils.isBlank(ztid))
            return Result.ok();
        JSONObject object = new JSONObject();
        LambdaQueryWrapper<XxnyztLyCjqk> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(XxnyztLyCjqk::getZtid, ztid);
        List<XxnyztLyCjqk> list = service.list(lambdaQueryWrapper);
        if (CollUtil.isNotEmpty(list)){
            List<XxnyztLyCjqk> lyCjqks = new ArrayList<>();
            List<XxnyztLyCjqk> lyCjqks2 = new ArrayList<>();
            List<XxnyztLyCjqk> lyCjqks3 = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                XxnyztLyCjqk xxnyztLyCjqk = list.get(i);
                if (StringUtils.isNotBlank(xxnyztLyCjqk.getJylb())){
                    if ("1".equals(xxnyztLyCjqk.getJylb())){
                        lyCjqks.add(xxnyztLyCjqk);
                    }else if ("2".equals(xxnyztLyCjqk.getJylb())){
                        lyCjqks2.add(xxnyztLyCjqk);
                    }else if ("3".equals(xxnyztLyCjqk.getJylb())){
                        lyCjqks3.add(xxnyztLyCjqk);
                    }
                }
            }
            object.put("mjList",lyCjqks);
            object.put("sbList",lyCjqks2);
            object.put("qtList",lyCjqks3);
        }
        return Result.ok(object);
    }

    /**
     * 编辑
     *
     * @param xxnyztLyCjqk
     * @return
     */
    @AutoLog(value = "采集情况-编辑")
    @ApiOperation(value = "采集情况-编辑", notes = "采集情况-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody XxnyztLyCjqk xxnyztLyCjqk) {
        service.updateById(xxnyztLyCjqk);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "采集情况-通过id删除")
    @ApiOperation(value = "采集情况-通过id删除", notes = "采集情况-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        service.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "采集情况-批量删除")
    @ApiOperation(value = "采集情况-批量删除", notes = "采集情况-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "采集情况-通过id查询")
    @ApiOperation(value = "采集情况-通过id查询", notes = "采集情况-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        XxnyztLyCjqk xxnyztLyCjqk = service.getById(id);
        return Result.ok(xxnyztLyCjqk);
    }

    @GetMapping(value = "/queryFjById")
    public Result<?> queryFjById(String ztid) {
        if (StringUtils.isBlank(ztid))
            return Result.ok();
        LambdaQueryWrapper<XxnyztLyCjqk> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(XxnyztLyCjqk::getZtid,ztid);
        lambdaQueryWrapper.in(XxnyztLyCjqk::getJylb,"5","4");
        return Result.ok(service.list(lambdaQueryWrapper));
    }

    /**
     * 导出excel
     *
     * @param request
     * @param xxnyztLyCjqk
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, XxnyztLyCjqk xxnyztLyCjqk) {
        return super.exportXls(request, xxnyztLyCjqk, XxnyztLyCjqk.class, "采集情况");
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
        return super.importExcel(request, response, XxnyztLyCjqk.class);
    }

}

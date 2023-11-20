package org.cmms.modules.pad.nhxxgl.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.pad.nhxxgl.entity.Nhzzzlxxb;
import org.cmms.modules.pad.nhxxgl.service.INhzzzlxxbService;

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
 * @Description: 农户佐证资料（待审批）
 * @Author: jeecg-boot
 * @Date: 2023-07-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "农户佐证资料（待审批）")
@RestController
@RequestMapping("/nhxxgl/nhzzzlxxb")
public class NhzzzlxxbController extends JeecgController<Nhzzzlxxb, INhzzzlxxbService> {
    @Autowired
    private INhzzzlxxbService nhzzzlxxbService;

    /**
     * 分页列表查询
     *
     * @param nhzzzlxxb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "农户佐证资料（待审批）-分页列表查询")
    @ApiOperation(value = "农户佐证资料（待审批）-分页列表查询", notes = "农户佐证资料（待审批）-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Nhzzzlxxb nhzzzlxxb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Nhzzzlxxb> queryWrapper = QueryGenerator.initQueryWrapper(nhzzzlxxb, req.getParameterMap());
        Page<Nhzzzlxxb> page = new Page<Nhzzzlxxb>(pageNo, pageSize);
        IPage<Nhzzzlxxb> pageList = nhzzzlxxbService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 查询 农户佐证资料
     *
     * @param zzlx 佐证类型（1 户主 2 家庭成员）
     * @param id 待审核信息ID
     * @return
     */
    @RequestMapping(value = "/queryNhZzfjxx",method = RequestMethod.GET)
    public Result<?> queryNhZzfjxx(@RequestParam(name = "zzlx") String zzlx,
                                   @RequestParam(name = "id") String id) {
        try {
            QueryWrapper<Nhzzzlxxb> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zzlx", zzlx);
            queryWrapper.eq("shid", id);
            List<Nhzzzlxxb> nhzzzlxxbList = nhzzzlxxbService.list(queryWrapper);
            if (nhzzzlxxbList != null && nhzzzlxxbList.size() > 0) {
                for (Nhzzzlxxb nhzzzlxxb : nhzzzlxxbList) {
                    nhzzzlxxb.setZllj(null);
                }
                return Result.ok(nhzzzlxxbList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("----农 户 佐 证 资 料 查 询 失 败！----"+e.getMessage());
            return Result.error(e.toString());
        }
        return Result.ok("查询成功");
    }

    /**
     * 添加
     *
     * @param nhzzzlxxbList
     * @return
     */
    @AutoLog(value = "农户佐证资料（待审批）-添加")
    @ApiOperation(value = "农户佐证资料（待审批）-添加", notes = "农户佐证资料（待审批）-添加")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<?> add(@RequestBody List<Nhzzzlxxb> nhzzzlxxbList) {
        try {
            if (nhzzzlxxbList.size() > 0) {
                for (Nhzzzlxxb nhzzzlxxb : nhzzzlxxbList) {
                    if (StringUtils.isEmpty(nhzzzlxxb.getId()) &&
                        StringUtils.isNotEmpty(nhzzzlxxb.getFwlj())) {
                        Nhzzzlxxb record = new Nhzzzlxxb();
                        record.setQydm(nhzzzlxxb.getQydm());
                        record.setHhbm(nhzzzlxxb.getHhbm());
                        record.setZlbh(IdUtil.simpleUUID());
                        record.setZllx(nhzzzlxxb.getZllx());
                        record.setZldx(nhzzzlxxb.getZldx());
                        record.setFwlj(nhzzzlxxb.getFwlj());
                        record.setZlmc(nhzzzlxxb.getZlmc());
                        record.setZllj(uploadpath + "/" + nhzzzlxxb.getFwlj());
                        record.setScsj(new Date());
                        record.setScr(getLoginUser().getUsername());
                        record.setLrsj(new Date());
                        record.setLrr(getLoginUser().getUsername());
                        //nhzzzlxxbService.save(record);
                    }
                }
            }
            return Result.ok("添加成功！");
        } catch (Exception e){
            log.error("----附 件 保 存 失 败！----"+e.getMessage());
            return  Result.error("附件保存失败！");
        }
    }

    /**
     * 删除
     *
     * @param nhzzzlxxbList
     * @return
     */
    @AutoLog(value = "农户佐证资料（待审批）-删除")
    @ApiOperation(value = "农户佐证资料（待审批）-删除", notes = "农户佐证资料（待审批）-删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result<?> delete(@RequestBody List<Nhzzzlxxb> nhzzzlxxbList) {
        try {
            if (nhzzzlxxbList.size() > 0) {
                for (Nhzzzlxxb nhzzzlxxb : nhzzzlxxbList) {
                    if (StringUtils.isNotEmpty(nhzzzlxxb.getZlbh())) {
                        UpdateWrapper<Nhzzzlxxb> updateWrapper = new UpdateWrapper<>();
                        updateWrapper.eq("zlbh",nhzzzlxxb.getZlbh());
                        //nhzzzlxxbService.remove(updateWrapper);
                    }
                }
            }
            return Result.ok("删除成功!");
        } catch (Exception e){
            log.error("----附 件 删 除 失 败！----"+e.getMessage());
            return Result.error("附件删除失败！");
        }
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "农户佐证资料（待审批）-通过id查询")
    @ApiOperation(value = "农户佐证资料（待审批）-通过id查询", notes = "农户佐证资料（待审批）-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Nhzzzlxxb nhzzzlxxb = nhzzzlxxbService.getById(id);
        return Result.ok(nhzzzlxxb);
    }

}

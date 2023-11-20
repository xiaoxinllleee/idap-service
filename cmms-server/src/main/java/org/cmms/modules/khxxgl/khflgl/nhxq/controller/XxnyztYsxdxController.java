package org.cmms.modules.khxxgl.khflgl.nhxq.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.khgl.nh.service.IYwhywwlxxService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.XxnyztYsxdx;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IXxnyztYsxdxService;
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
 * @Description: 已授信对象
 * @Author: jeecg-boot
 * @Date: 2022-12-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "已授信对象")
@RestController
@RequestMapping("/khxxgl/xxnyztYsxdx")
public class XxnyztYsxdxController extends JeecgController<XxnyztYsxdx, IXxnyztYsxdxService> {
    @Autowired
    private IXxnyztYsxdxService xxnyztYsxdxService;

    @Autowired
    private IYwhywwlxxService ywhywwlxxService;

    /**
     * 分页列表查询
     *
     * @param xxnyztYsxdx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "已授信对象-分页列表查询")
    @ApiOperation(value = "已授信对象-分页列表查询", notes = "已授信对象-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(XxnyztYsxdx xxnyztYsxdx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<XxnyztYsxdx> queryWrapper = QueryGenerator.initQueryWrapper(xxnyztYsxdx, req.getParameterMap());
        Page<XxnyztYsxdx> page = new Page<XxnyztYsxdx>(pageNo, pageSize);
        IPage<XxnyztYsxdx> pageList = xxnyztYsxdxService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param xxnyztYsxdx
     * @return
     */
    @AutoLog(value = "已授信对象-添加")
    @ApiOperation(value = "已授信对象-添加", notes = "已授信对象-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody XxnyztYsxdx xxnyztYsxdx) {
        xxnyztYsxdxService.save(xxnyztYsxdx);
        return Result.ok("添加成功！");
    }

    @PostMapping(value = "/add2")
    public Result<?> add2(@RequestBody JSONObject jsonObject) {
        System.out.println(jsonObject);
        String ysxdx = jsonObject.getString("ysxdx");
        String ysxdxzjhm = jsonObject.getString("ysxdxzjhm");
        String ysxed = jsonObject.getString("ysxeds");
        String xxnyztId = jsonObject.getString("xxnyztId");
        String yyxed = jsonObject.getString("yyxed");
        LambdaQueryWrapper<XxnyztYsxdx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(XxnyztYsxdx::getXxnyztId, xxnyztId);
        service.remove(lambdaQueryWrapper);
        String[] split = ysxdx.split(",");
        String[] split1 = ysxdxzjhm.split(",");
        String[] split2 = ysxed.split(",");
        String[] split3 = yyxed.split(",");

        for (int i = 0; i < split.length; i++) {
            XxnyztYsxdx xxnyztYsxdx = new XxnyztYsxdx();
            xxnyztYsxdx.setCreator(getWorkNo());
            xxnyztYsxdx.setCreateTime(new Date());
            xxnyztYsxdx.setLrbz("1");
            xxnyztYsxdx.setYsxdx(split[i]);
            xxnyztYsxdx.setYsxdxzjhm(split1[i]);
            xxnyztYsxdx.setYsxed(new BigDecimal(split2[i]));
            xxnyztYsxdx.setYyxed(new BigDecimal(split3[i]));
            xxnyztYsxdx.setXxnyztId(xxnyztId);
            System.out.println("1111");
            System.out.println(split1[i]);
            //要去查dkye
            List<Ywhywwlxx> ywhywwlxxes = ywhywwlxxService.selectByMainId(split1[i]);
            if (ywhywwlxxes != null && ywhywwlxxes.size() > 0) {
                System.out.println("1111");
                //要筛选出来
                Ywhywwlxx ywhywwlxx = ywhywwlxxes.get(0);
                if (ywhywwlxx.getDkye() != null) {
                    xxnyztYsxdx.setYyxed(ywhywwlxx.getDkye());
                }

                if (ywhywwlxx.getKhmc() != null) {
                    xxnyztYsxdx.setYsxdx(ywhywwlxx.getKhmc());
                }

                if (ywhywwlxx.getDkje() != null) {
                    xxnyztYsxdx.setYsxed(ywhywwlxx.getDkje());
                }
            }

            service.save(xxnyztYsxdx);
        }

        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param xxnyztYsxdx
     * @return
     */
    @AutoLog(value = "已授信对象-编辑")
    @ApiOperation(value = "已授信对象-编辑", notes = "已授信对象-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody XxnyztYsxdx xxnyztYsxdx) {
        xxnyztYsxdxService.updateById(xxnyztYsxdx);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "已授信对象-通过id删除")
    @ApiOperation(value = "已授信对象-通过id删除", notes = "已授信对象-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        xxnyztYsxdxService.removeById(id);
        return Result.ok("删除成功!");
    }

    @RequestMapping(value = "/del")
    public Result<?> del(String xxnyztId) {
        LambdaQueryWrapper<XxnyztYsxdx> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(XxnyztYsxdx::getXxnyztId, xxnyztId);
        xxnyztYsxdxService.remove(lambdaQueryWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "已授信对象-批量删除")
    @ApiOperation(value = "已授信对象-批量删除", notes = "已授信对象-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.xxnyztYsxdxService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "已授信对象-通过id查询")
    @ApiOperation(value = "已授信对象-通过id查询", notes = "已授信对象-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        XxnyztYsxdx xxnyztYsxdx = xxnyztYsxdxService.getById(id);
        return Result.ok(xxnyztYsxdx);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param xxnyztYsxdx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, XxnyztYsxdx xxnyztYsxdx) {
        return super.exportXls(request, xxnyztYsxdx, XxnyztYsxdx.class, "已授信对象");
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
        return super.importExcel(request, response, XxnyztYsxdx.class);
    }


    @GetMapping(value = "/queryClkhByZjhm")
    public Result<?> queryClkhByZjhm(@Param("zjhm") String zjhm,
                                     @Param("id") String id) {
        LambdaQueryWrapper<XxnyztYsxdx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.inSql(XxnyztYsxdx::getYsxdxzjhm," select zjhm from  KHXXGL_KHXQ_NH where hhbm in ( select max(hhbm) from KHXXGL_KHXQ_NH where zjhm ='"+zjhm+"')");
        lambdaQueryWrapper.or().eq(XxnyztYsxdx::getXxnyztId,id);
        List<XxnyztYsxdx> list = service.list(lambdaQueryWrapper);
        return Result.ok(list);
    }

}

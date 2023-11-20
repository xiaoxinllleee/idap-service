package org.cmms.modules.dklldj.csszgl.fdcsdzb.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.dklldj.csszgl.fdcsdzb.entity.Fdcsdzb;
import org.cmms.modules.dklldj.csszgl.fdcsdzb.entity.FdcsdzbVo;
import org.cmms.modules.dklldj.csszgl.fdcsdzb.service.IFdcsdzbService;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dklldj.csszgl.fdcsdzb.verify.FdcsdzbVerify;
import org.cmms.modules.dklldj.lldjgl.lldjjs.service.IRateZxlldjxxService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

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
 * @Description: 浮动查算对照表
 * @Author: jeecg-boot
 * @Date: 2022-03-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "浮动查算对照表")
@RestController
@RequestMapping("/fdcsdzb/fdcsdzb")
public class FdcsdzbController extends JeecgController<Fdcsdzb, IFdcsdzbService> {
    @Autowired
    private IFdcsdzbService fdcsdzbService;
    @Autowired
    private IRateZxlldjxxService iRateZxlldjxxService;
    @Autowired
    private FdcsdzbVerify fdcsdzbVerify;
    @Autowired
    IDictValueQuery iDictValueQuery;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param fdcsdzb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "浮动查算对照表-分页列表查询")
    @ApiOperation(value = "浮动查算对照表-分页列表查询", notes = "浮动查算对照表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Fdcsdzb fdcsdzb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<Fdcsdzb>> result = new Result<>();
        QueryWrapper<Fdcsdzb> queryWrapper = QueryGenerator.initQueryWrapper(fdcsdzb, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IFdcsdzbService.class, fdcsdzbService, pageNo, pageSize, queryWrapper, "djdf", "dkqx");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 添加
     *
     * @param fdcsdzb
     * @return
     */
    @AutoLog(value = "浮动查算对照表-添加")
    @ApiOperation(value = "浮动查算对照表-添加", notes = "浮动查算对照表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Fdcsdzb fdcsdzb) {
        QueryWrapper<Fdcsdzb> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("djdf", fdcsdzb.getDjdf());
        queryWrapper.eq("dkqx", fdcsdzb.getDkqx());
        queryWrapper.eq("dksxje_begin", fdcsdzb.getDksxjeBegin());
        queryWrapper.eq("dksxje_end", fdcsdzb.getDksxjeEnd());
        queryWrapper.eq("dyfdfd", fdcsdzb.getDyfdfd());
        queryWrapper.eq("dyjdbp", fdcsdzb.getDyjdbp());
        Fdcsdzb check = fdcsdzbService.getOne(queryWrapper);
        if (check != null) {
            return Result.error("该数据已存在，请核实！");
        }
        if ("true".equals(sfdsjpt)) {
            fdcsdzb.setId(iRateZxlldjxxService.getMaxDjidHive());
        } else {
            fdcsdzb.setId(iDictValueQuery.getSeqRateZxlldjbDjidNextval("SEQ_PUBLIC_ID.nextval"));
        }
        fdcsdzb.setLrbz(1);
        fdcsdzb.setLrr(getUsername());
        fdcsdzb.setLrsj(new Date());
        fdcsdzbService.save(fdcsdzb);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param fdcsdzb
     * @return
     */
    @AutoLog(value = "浮动查算对照表-编辑")
    @ApiOperation(value = "浮动查算对照表-编辑", notes = "浮动查算对照表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Fdcsdzb fdcsdzb) {
        fdcsdzb.setLrbz(2);
        fdcsdzb.setLrr(getUsername());
        fdcsdzb.setLrsj(new Date());
        fdcsdzbService.updateById(fdcsdzb);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "浮动查算对照表-通过id删除")
    @ApiOperation(value = "浮动查算对照表-通过id删除", notes = "浮动查算对照表-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        fdcsdzbService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "浮动查算对照表-通过id查询")
    @ApiOperation(value = "浮动查算对照表-通过id查询", notes = "浮动查算对照表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Fdcsdzb fdcsdzb = fdcsdzbService.getById(id);
        return Result.ok(fdcsdzb);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param fdcsdzb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Fdcsdzb fdcsdzb) {
        return super.exportXls(request, fdcsdzb, Fdcsdzb.class, "浮动查算对照表");
    }


    /**
     * 导出模板excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        return super.exportTemplateXls(FdcsdzbVo.class, "浮动查算对照表导入模板");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        return super.importExcelByTemplate(jsonObject, request, response, Fdcsdzb.class,FdcsdzbVo.class, fdcsdzbVerify);
    }

}

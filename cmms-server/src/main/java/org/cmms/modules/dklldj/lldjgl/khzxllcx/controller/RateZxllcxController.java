package org.cmms.modules.dklldj.lldjgl.khzxllcx.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dklldj.lldjgl.khzxllcx.entity.RateZxllcx;
import org.cmms.modules.dklldj.lldjgl.khzxllcx.service.IRateZxllcxService;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.util.PageUtil;
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
 * @Description: 客户执行利率查询
 * @Author: jeecg-boot
 * @Date: 2020-03-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "客户执行利率查询")
@RestController
@RequestMapping("/rateZxllcx/rateZxllcx")
public class RateZxllcxController extends JeecgController<RateZxllcx, IRateZxllcxService> {
    @Autowired
    private IRateZxllcxService rateZxllcxService;

    /**
     * 分页列表查询
     *
     * @param rateZxllcx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "客户执行利率查询-分页列表查询")
    @ApiOperation(value = "客户执行利率查询-分页列表查询", notes = "客户执行利率查询-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(RateZxllcx rateZxllcx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<RateZxllcx>> result = new Result<IPage<RateZxllcx>>();
        QueryWrapper<RateZxllcx> queryWrapper = QueryGenerator.initQueryWrapper(rateZxllcx, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IRateZxllcxService.class, rateZxllcxService, pageNo, pageSize, queryWrapper, "djnf", "zjhm");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param rateZxllcx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RateZxllcx rateZxllcx) {
        return super.exportXls(request, rateZxllcx, RateZxllcx.class, "客户执行利率查询");
    }

}

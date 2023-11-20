package org.cmms.modules.dklldj.jbxxgl.khzyjl.controller;

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
import org.cmms.modules.dklldj.jbxxgl.khzyjl.entity.RateKhjbxxbLs;
import org.cmms.modules.dklldj.jbxxgl.khzyjl.service.IRateKhjbxxbLsService;

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
 * @Description: 客户转移记录
 * @Author: jeecg-boot
 * @Date: 2020-03-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "客户转移记录")
@RestController
@RequestMapping("/rateKhjbxxbLs/rateKhjbxxbLs")
public class RateKhjbxxbLsController extends JeecgController<RateKhjbxxbLs, IRateKhjbxxbLsService> {
    @Autowired
    private IRateKhjbxxbLsService rateKhjbxxbLsService;

    /**
     * 分页列表查询
     *
     * @param rateKhjbxxbLs
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "客户转移记录-分页列表查询")
    @ApiOperation(value = "客户转移记录-分页列表查询", notes = "客户转移记录-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(RateKhjbxxbLs rateKhjbxxbLs,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<RateKhjbxxbLs>> result = new Result<IPage<RateKhjbxxbLs>>();
        QueryWrapper<RateKhjbxxbLs> queryWrapper = QueryGenerator.initQueryWrapper(rateKhjbxxbLs, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IRateKhjbxxbLsService.class, rateKhjbxxbLsService, pageNo, pageSize, queryWrapper, "zjhm");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param rateKhjbxxbLs
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RateKhjbxxbLs rateKhjbxxbLs) {
        return super.exportXls(request, rateKhjbxxbLs, RateKhjbxxbLs.class, "客户转移记录");
    }

}

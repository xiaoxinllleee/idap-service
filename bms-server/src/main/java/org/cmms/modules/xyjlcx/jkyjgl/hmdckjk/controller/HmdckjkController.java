package org.cmms.modules.xyjlcx.jkyjgl.hmdckjk.controller;

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
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.xyjlcx.jkyjgl.hmdckjk.entity.Hmdckjk;
import org.cmms.modules.xyjlcx.jkyjgl.hmdckjk.service.IHmdckjkService;

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
 * @Description: 黑名单存款监控
 * @Author: jeecg-boot
 * @Date: 2021-08-12
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "黑名单存款监控")
@RestController
@RequestMapping("/hmdckjk/hmdckjk")
public class HmdckjkController extends JeecgController<Hmdckjk, IHmdckjkService> {
    @Autowired
    private IHmdckjkService hmdckjkService;

    /**
     * 分页列表查询
     *
     * @param hmdckjk
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "黑名单存款监控-分页列表查询")
    @ApiOperation(value = "黑名单存款监控-分页列表查询", notes = "黑名单存款监控-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Hmdckjk hmdckjk,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {

        Result<IPage<Hmdckjk>> result = new Result<IPage<Hmdckjk>>();
        QueryWrapper<Hmdckjk> queryWrapper = QueryGenerator.initQueryWrapper(hmdckjk, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IHmdckjkService.class, hmdckjkService, pageNo, pageSize, queryWrapper, "zjhm");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param hmdckjk
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Hmdckjk hmdckjk) {
        return super.exportXls(request, hmdckjk, Hmdckjk.class, "黑名单存款监控");
    }

}

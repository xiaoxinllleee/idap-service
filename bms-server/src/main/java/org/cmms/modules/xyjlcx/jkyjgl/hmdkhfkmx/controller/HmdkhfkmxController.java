package org.cmms.modules.xyjlcx.jkyjgl.hmdkhfkmx.controller;

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
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.xyjlcx.jkyjgl.hmdkhfkmx.entity.Hmdkhfkmx;
import org.cmms.modules.xyjlcx.jkyjgl.hmdkhfkmx.service.IHmdkhfkmxService;

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
 * @Description: 黑名单客户放款明细
 * @Author: jeecg-boot
 * @Date: 2021-08-12
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "黑名单客户放款明细")
@RestController
@RequestMapping("/hmdkhfkmx/hmdkhfkmx")
public class HmdkhfkmxController extends JeecgController<Hmdkhfkmx, IHmdkhfkmxService> {
    @Autowired
    private IHmdkhfkmxService hmdkhfkmxService;

    /**
     * 分页列表查询
     *
     * @param hmdkhfkmx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "黑名单客户放款明细-分页列表查询")
    @ApiOperation(value = "黑名单客户放款明细-分页列表查询", notes = "黑名单客户放款明细-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Hmdkhfkmx hmdkhfkmx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<Hmdkhfkmx>> result = new Result<IPage<Hmdkhfkmx>>();
        QueryWrapper<Hmdkhfkmx> queryWrapper = QueryGenerator.initQueryWrapper(hmdkhfkmx, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IHmdkhfkmxService.class, hmdkhfkmxService, pageNo, pageSize, queryWrapper, "zjhm", "dkzh");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param hmdkhfkmx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Hmdkhfkmx hmdkhfkmx) {
        return super.exportXls(request, hmdkhfkmx, Hmdkhfkmx.class, "黑名单客户放款明细");
    }

}

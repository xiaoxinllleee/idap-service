package org.cmms.modules.xyjlcx.xybg.cxjltz.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.*;
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
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.xyjlcx.xybg.cxjltz.entity.Cxjltz;
import org.cmms.modules.xyjlcx.xybg.cxjltz.service.ICxjltzService;

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
 * @Description: 查询记录台账
 * @Author: jeecg-boot
 * @Date: 2021-08-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "查询记录台账")
@RestController
@RequestMapping("/cxjltz/cxjltz")
public class CxjltzController extends JeecgController<Cxjltz, ICxjltzService> {
    @Autowired
    private ICxjltzService cxjltzService;

    /**
     * 分页列表查询
     *
     * @param cxjltz
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "查询记录台账-分页列表查询")
    @ApiOperation(value = "查询记录台账-分页列表查询", notes = "查询记录台账-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Cxjltz cxjltz,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<Cxjltz>> result = new Result<IPage<Cxjltz>>();
        QueryWrapper<Cxjltz> queryWrapper = QueryGenerator.initQueryWrapper(cxjltz, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(ICxjltzService.class, cxjltzService, pageNo, pageSize, queryWrapper, "cxrq", "bcxrzjhm", "lrsj");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param cxjltz
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Cxjltz cxjltz) {
        return super.exportXls(request, cxjltz, Cxjltz.class, "查询记录台账");
    }

}

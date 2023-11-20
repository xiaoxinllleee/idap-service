package org.cmms.modules.dklldj.tjfxgl.wdsftj.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dklldj.tjfxgl.wdsftj.entity.Wdsftj;
import org.cmms.modules.dklldj.tjfxgl.wdsftj.service.IWdsftjService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.EtlUtilOld;
import org.cmms.modules.util.PageUtil;
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
 * @Description: 网点上浮统计
 * @Author: Penghr
 * @Date: 2020-03-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "网点上浮统计")
@RestController
@RequestMapping("/tjfxgl/wdsftj")
public class WdsftjController extends JeecgController<Wdsftj, IWdsftjService> {
    @Autowired
    private IWdsftjService wdsftjService;
    @Autowired
    private ISysDictService iSysDictService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param wdsftj
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "网点上浮统计-分页列表查询")
    @ApiOperation(value = "网点上浮统计-分页列表查询", notes = "网点上浮统计-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Wdsftj wdsftj,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        //wdsftj.setZzlb(1);
        Result<IPage<Wdsftj>> result = new Result<IPage<Wdsftj>>();
        QueryWrapper<Wdsftj> queryWrapper = QueryGenerator.initQueryWrapper(wdsftj, req.getParameterMap());
        queryWrapper.eq("zzlb", 1);
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IWdsftjService.class, wdsftjService, pageNo, pageSize, queryWrapper, "tjyf", "zzbz");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param wdsftj
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Wdsftj wdsftj) {
        wdsftj.setZzlb(1);
        return super.exportXls(request, wdsftj, Wdsftj.class, "网点上浮统计");
    }

    /**
     * 网点上浮统计-提取
     *
     * @return
     */
    @RequestMapping(value = "/init", method = RequestMethod.PUT)
    public Result<?> dataExtract() {
        // Result<List<Wdsftj>> result = new Result<>();
		String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC", "VALUE", "CODE", "101001");
		Result result = new Result<>();
		if ("true".equalsIgnoreCase(sfdsjpt)) {
			// `参数1`:`任务调用code`，`参数2`:`ETL调度存储过程参数值`，`参数3`:`ETL任务预计执行时间(可根据实际执行时间酌情延长)`
            HashMap<String, String> params = new HashMap<>();
            params.put("etl_task","kiss.domain.application.rate.proc_rate_wdsf_tj");
            // count_rate_wdsf_tj
			boolean completionSignal = EtlUtil.callEtl("rate_common_init", params, 15);
			result.setSuccess(completionSignal);
		} else {
			try {
				wdsftjService.init();
				result.setMessage("提取成功！");
				result.setSuccess(true);
			} catch (Exception e) {
				log.error("提取失败！", e.getMessage());
				result.setMessage("提取失败！" + e.getMessage());
				result.setSuccess(false);
			}
		}
        return result;
    }

}

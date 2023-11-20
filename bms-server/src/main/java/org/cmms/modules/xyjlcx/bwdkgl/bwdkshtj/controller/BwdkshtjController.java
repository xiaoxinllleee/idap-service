package org.cmms.modules.xyjlcx.bwdkgl.bwdkshtj.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.EtlUtilOld;
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.xyjlcx.bwdkgl.bwdkshtj.entity.Bwdkshtj;
import org.cmms.modules.xyjlcx.bwdkgl.bwdkshtj.mapper.BwdkshtjMapper;
import org.cmms.modules.xyjlcx.bwdkgl.bwdkshtj.service.IBwdkshtjService;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 表外贷款收回统计
 * @Author: jeecg-boot
 * @Date: 2021-08-13
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "表外贷款收回统计")
@RestController
@RequestMapping("/bwdkshtj/bwdkshtj")
public class BwdkshtjController extends JeecgController<Bwdkshtj, IBwdkshtjService> {
    @Autowired
    private IBwdkshtjService bwdkshtjService;
    @Autowired
    private ISysDictService iSysDictService;
    @Autowired(required = false)
    private BwdkshtjMapper bwdkshtjMapper;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 分页列表查询
     *
     * @param bwdkshtj
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "表外贷款收回统计-分页列表查询")
    @ApiOperation(value = "表外贷款收回统计-分页列表查询", notes = "表外贷款收回统计-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Bwdkshtj bwdkshtj,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<Bwdkshtj>> result = new Result<IPage<Bwdkshtj>>();
        QueryWrapper<Bwdkshtj> queryWrapper = QueryGenerator.initQueryWrapper(bwdkshtj, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IBwdkshtjService.class, bwdkshtjService, pageNo, pageSize, queryWrapper, "rzwd", "tjrqq", "tjrqz", "tjlx");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 表外贷款收回统计 / 统计
     *
     * @param jsonObject
     * @return
     */
    @AutoLog(value = "表外贷款收回统计-统计")
    @ApiOperation(value = "表外贷款收回统计-统计", notes = "表外贷款收回统计-统计")
    @RequestMapping(value = "/init")
    public Result<?> init(@RequestBody JSONObject jsonObject) {
        String tjrqq = jsonObject.getString("tjrqq");
        String tjrqz = jsonObject.getString("tjrqz");
        String rzwd  = jsonObject.getString("rzwd");
        Result result = new Result<>();
        tjrqq = tjrqq.replaceAll("-", "");
        tjrqz = tjrqz.replaceAll("-", "");
        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            HashMap<String, String> params = new HashMap<>();
            boolean completionSignal;
            params.put("begin_date", tjrqq);
            params.put("end_date", tjrqz);
            params.put("operator", getLoginUser().getUsername());
            params.put("qydm", qydm);
            params.put("etl_task","kiss.domain.application.zx.proc_credit_bwdkshtj");
            // `参数1`:`任务调用code`，`参数2`:`ETL调度存储过程参数值`，`参数3`:`ETL任务预计执行时间(可根据实际执行时间酌情延长)`
            completionSignal = EtlUtil.callEtl("zx_common_init", params, 20);
            result.setSuccess(completionSignal);
        } else {
            try {
                bwdkshtjMapper.pBwdkshtj(tjrqq, tjrqz, getLoginUser().getRealname(), rzwd);
                result.setSuccess(true);
                return result;
            } catch (Throwable e) {
                log.error("信用记录查询 / 表外贷款收回统计 / 提取失败!"+e.getMessage());
                result.setSuccess(false);
            }
        }
        return result;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param bwdkshtj
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Bwdkshtj bwdkshtj) {
        return super.exportXls(request, bwdkshtj, Bwdkshtj.class, "表外贷款收回统计");
    }

}

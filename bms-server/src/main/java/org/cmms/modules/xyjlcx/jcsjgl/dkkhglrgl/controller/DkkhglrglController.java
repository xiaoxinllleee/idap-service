package org.cmms.modules.xyjlcx.jcsjgl.dkkhglrgl.controller;

import java.sql.Timestamp;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.EtlUtilOld;
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.xyjlcx.jcsjgl.dkkhglrgl.entity.Dkkhglrgl;
import org.cmms.modules.xyjlcx.jcsjgl.dkkhglrgl.entity.DkkhglrglImportVo;
import org.cmms.modules.xyjlcx.jcsjgl.dkkhglrgl.mapper.DkkhglrglMapper;
import org.cmms.modules.xyjlcx.jcsjgl.dkkhglrgl.service.IDkkhglrglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.xyjlcx.jcsjgl.dkkhglrgl.verify.DkkhglrglImportVerify;
import org.cmms.modules.ywgl.djkyw.djkkhzr.entity.Djkkhzr;
import org.cmms.modules.ywgl.djkyw.djkkhzr.entity.DjkkhzrImportVo;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 贷款客户关联人管理
 * @Author: jeecg-boot
 * @Date: 2021-08-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "贷款客户关联人管理")
@RestController
@RequestMapping("/dkkhglrgl/dkkhglrgl")
public class DkkhglrglController extends JeecgController<Dkkhglrgl, IDkkhglrglService> {
    @Autowired
    private IDkkhglrglService dkkhglrglService;
    @Autowired
    private ISysDictService iSysDictService;
    @Autowired
    private DkkhglrglImportVerify dkkhglrglImportVerify;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param dkkhglrgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "贷款客户关联人管理-分页列表查询")
    @ApiOperation(value = "贷款客户关联人管理-分页列表查询", notes = "贷款客户关联人管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Dkkhglrgl dkkhglrgl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<Dkkhglrgl>> result = new Result<IPage<Dkkhglrgl>>();
        QueryWrapper<Dkkhglrgl> queryWrapper = QueryGenerator.initQueryWrapper(dkkhglrgl, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(
                IDkkhglrglService.class,
                dkkhglrglService,
                pageNo,
                pageSize,
                queryWrapper,
                "jkrzjhm", "glrzjhm");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 贷款客户关联人 / 提取
     *
     * @return
     */
    @RequestMapping(value = "/init")
    public Result<?> init() {
        Result result = new Result<>();
        // `浏阳`调用ETL工具类执行ETL调度
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            // `参数1`:`任务调用code`，`参数2`:`ETL调度存储过程参数值`，`参数3`:`ETL任务预计执行时间(可根据实际执行时间酌情延长)`
            HashMap<String, String> params = new HashMap<>();
            params.put("etl_task","kiss.domain.application.zx.proc_credit_dkkhglrgl");
            boolean completionSignal = EtlUtil.callEtl("zx_common_init", params, 20);
            result.setSuccess(completionSignal);
        } else {
            try {
                dkkhglrglService.pDkkhglrgl();
                result.setSuccess(true);
                return result;
            } catch (Throwable e) {
                log.error("信用记录查询 / 贷款客户关联人管理 / 提取失败！"+e.getMessage());
                result.setSuccess(false);
            }
        }
        return result;
    }

    /**
     * 贷款客户关联人管理 / 添加
     *
     * @param dkkhglrgl
     * @return
     */
    @AutoLog(value = "贷款客户关联人管理-添加")
    @ApiOperation(value = "贷款客户关联人管理-添加", notes = "贷款客户关联人管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Dkkhglrgl dkkhglrgl) {
        try {
            QueryWrapper<Dkkhglrgl> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("jkrzjhm", dkkhglrgl.getJkrzjhm());
            queryWrapper.eq("glrzjhm", dkkhglrgl.getGlrzjhm());
            Dkkhglrgl check = dkkhglrglService.getOne(queryWrapper,false);
            if (check != null) {
                return Result.error("该关联人证件号码已经存在关联，请勿重复关联！");
            }
            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("jkrzjhm", dkkhglrgl.getJkrzjhm());
            List<Dkkhglrgl> list = dkkhglrglService.list(queryWrapper);
            if (list != null && list.size() > 0) {
                if (!dkkhglrgl.getJkrmc().equals(list.get(0).getJkrmc())) {
                    return Result.error("输入的借款人名称与借款人证件号码不匹配！");
                }
            }
            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("glrzjhm", dkkhglrgl.getGlrzjhm());
            List<Dkkhglrgl> list1 = dkkhglrglService.list(queryWrapper);
            if (list1 != null && list1.size() > 0) {
                if (!dkkhglrgl.getGlrxm().equals(list1.get(0).getGlrxm())) {
                    return Result.error("输入的关联人姓名与关联人证件号码不匹配！");
                }
            }
            dkkhglrgl.setLrbz(1);
            dkkhglrgl.setLrsj(new Timestamp(System.currentTimeMillis()));
            dkkhglrgl.setLrr(getLoginUser().getUsername());
            dkkhglrglService.save(dkkhglrgl);
            return Result.ok("添加成功！");
        } catch (Throwable tx) {
            log.error("信用记录查询 / 贷款客户关联人管理 / 添加失败！", tx);
            return Result.error("添加失败！" + tx.getMessage());
        }
    }

    /**
     * 贷款客户关联人管理 / 编辑
     *
     * @param dkkhglrgl
     * @return
     */
    @AutoLog(value = "贷款客户关联人管理-编辑")
    @ApiOperation(value = "贷款客户关联人管理-编辑", notes = "贷款客户关联人管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Dkkhglrgl dkkhglrgl) {
        try {
            QueryWrapper<Dkkhglrgl> queryWrapper = new QueryWrapper<Dkkhglrgl>();
            queryWrapper.eq("jkrzjhm", dkkhglrgl.getJkrzjhm());
            queryWrapper.eq("glrzjhm", dkkhglrgl.getGlrzjhm());
            //表主键不能更新（Kudu）
            dkkhglrgl.setJkrzjhm(null);
            dkkhglrgl.setGlrzjhm(null);
            dkkhglrgl.setLrbz(2);
            dkkhglrgl.setLrsj(new Timestamp(System.currentTimeMillis()));
            dkkhglrgl.setLrr(getLoginUser().getUsername());
            dkkhglrglService.update(dkkhglrgl, queryWrapper);
            return Result.ok("编辑成功！");
        } catch (Throwable tx) {
            log.error("信用记录查询 / 贷款客户关联人管理 / 编辑失败！" + tx);
            return Result.error("编辑失败！" + tx.getMessage());
        }
    }

    /**
     * 贷款客户关联人管理 / 删除
     * @param
     * @return
     */
    @AutoLog(value = "贷款客户关联人管理-删除")
    @ApiOperation(value = "贷款客户关联人管理-删除", notes = "贷款客户关联人管理-删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@Param("jkrzjhm") String jkrzjhm,
                            @Param("glrzjhm") String glrzjhm) {
        try {
            QueryWrapper<Dkkhglrgl> queryWrapper = new QueryWrapper<Dkkhglrgl>();
            queryWrapper.eq("jkrzjhm", jkrzjhm);
            queryWrapper.eq("glrzjhm", glrzjhm);
            dkkhglrglService.remove(queryWrapper);
            return Result.ok("删除成功！");
        } catch (Throwable tx) {
            log.error("信用记录查询 / 贷款客户关联人管理 / 删除失败！" + tx);
            return Result.error("删除失败！" + tx.getMessage());
        }
    }

    /**
     * 导出excel
     *
     * @param request
     * @param dkkhglrgl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Dkkhglrgl dkkhglrgl) {
        return super.exportXls(request, dkkhglrgl, Dkkhglrgl.class, "贷款客户关联人管理");
    }

    /**
     * 导出模板excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "贷款客户关联人导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, DkkhglrglImportVo.class);
        ExportParams exportParams = new ExportParams("贷款客户关联人导入模板", "模板信息");
        modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
        modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
        return modelAndView;
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
        return super.importExcelByTemplate(jsonObject, request, response, Dkkhglrgl.class,DkkhglrglImportVo.class, dkkhglrglImportVerify);
    }
}

package org.cmms.modules.ywgl.cdkfx.zhxzbldkhz.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.ywgl.cdkfx.sysbascfg.entity.SysBasCfg;
import org.cmms.modules.ywgl.cdkfx.sysbascfg.service.ISysBasCfgService;
import org.cmms.modules.ywgl.cdkfx.util.mapper.CallToolMapper;
import org.cmms.modules.ywgl.cdkfx.zhxzbldkhz.entity.ErpJxkhZhxzbldkhz;
import org.cmms.modules.ywgl.cdkfx.zhxzbldkhz.service.IErpJxkhZhxzbldkhzService;
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
 * @Description: 支行新增不良贷款汇总
 * @Author: jeecg-boot
 * @Date: 2021-06-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "支行新增不良贷款汇总")
@RestController
@RequestMapping("/zhxzbldkhz/erpJxkhZhxzbldkhz")
public class ErpJxkhZhxzbldkhzController extends JeecgController<ErpJxkhZhxzbldkhz, IErpJxkhZhxzbldkhzService> {
    @Autowired
    private IErpJxkhZhxzbldkhzService erpJxkhZhxzbldkhzService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;
    @Autowired
    private ISysBasCfgService iSysBasCfgService;
    /**
     * 分页列表查询
     *
     * @param erpJxkhZhxzbldkhz
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "支行新增不良贷款汇总-分页列表查询")
    @ApiOperation(value = "支行新增不良贷款汇总-分页列表查询", notes = "支行新增不良贷款汇总-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(ErpJxkhZhxzbldkhz erpJxkhZhxzbldkhz,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<ErpJxkhZhxzbldkhz> queryWrapper = QueryGenerator.initQueryWrapper(erpJxkhZhxzbldkhz, req.getParameterMap());
        IPage pageList = PageUtil.toPage(IErpJxkhZhxzbldkhzService.class,erpJxkhZhxzbldkhzService, pageNo, pageSize, queryWrapper, "jgdm");
        return Result.ok(pageList);
    }

    /**
     * 提取
     */
    @RequestMapping(value = "/count")
    public Result<?> count(String tjyf) {
        tjyf = tjyf.replaceAll("-", "");
        Result result = new Result<>();
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            QueryWrapper queryWrapper=new QueryWrapper();
             queryWrapper.eq("cfgcode", "200000002");
            SysBasCfg one = iSysBasCfgService.getOne(queryWrapper);
            HashMap<String, String> parm = new HashMap<>();
            parm.put("i_tjyf", tjyf);
            parm.put("ld_startday", one.getCfgvalue());
            parm.put("etl_task","kiss.domain.application.cdkyw.proc_ywgl_cdkfx_jxkh_zhxzbldkhz");
            // count_ywgl_cdkfx_jxkh_zhxzbldkhz
            boolean falg = EtlUtil.callEtl("cdkyw_common_init", parm, 15);
            result.setSuccess(falg);
        } else {
            try {
                erpJxkhZhxzbldkhzService.pJxkhZhxzbldkhz(tjyf);
                result.setSuccess(true);
                return result;
            } catch (Exception e) {
                System.out.println(e);
                log.error("提取失败", e.getMessage());
                result.setSuccess(false);
            }
        }
        return result;
    }

    /**
     * 添加
     *
     * @param erpJxkhZhxzbldkhz
     * @return
     */
    @AutoLog(value = "支行新增不良贷款汇总-添加")
    @ApiOperation(value = "支行新增不良贷款汇总-添加", notes = "支行新增不良贷款汇总-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody ErpJxkhZhxzbldkhz erpJxkhZhxzbldkhz) {
        erpJxkhZhxzbldkhzService.save(erpJxkhZhxzbldkhz);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param erpJxkhZhxzbldkhz
     * @return
     */
    @AutoLog(value = "支行新增不良贷款汇总-编辑")
    @ApiOperation(value = "支行新增不良贷款汇总-编辑", notes = "支行新增不良贷款汇总-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody ErpJxkhZhxzbldkhz erpJxkhZhxzbldkhz) {
        erpJxkhZhxzbldkhzService.updateById(erpJxkhZhxzbldkhz);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "支行新增不良贷款汇总-通过id删除")
    @ApiOperation(value = "支行新增不良贷款汇总-通过id删除", notes = "支行新增不良贷款汇总-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        erpJxkhZhxzbldkhzService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "支行新增不良贷款汇总-批量删除")
    @ApiOperation(value = "支行新增不良贷款汇总-批量删除", notes = "支行新增不良贷款汇总-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.erpJxkhZhxzbldkhzService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "支行新增不良贷款汇总-通过id查询")
    @ApiOperation(value = "支行新增不良贷款汇总-通过id查询", notes = "支行新增不良贷款汇总-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        ErpJxkhZhxzbldkhz erpJxkhZhxzbldkhz = erpJxkhZhxzbldkhzService.getById(id);
        return Result.ok(erpJxkhZhxzbldkhz);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param erpJxkhZhxzbldkhz
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ErpJxkhZhxzbldkhz erpJxkhZhxzbldkhz) {
        return super.exportXls(request, erpJxkhZhxzbldkhz, ErpJxkhZhxzbldkhz.class, "支行新增不良贷款汇总");
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
        return super.importExcel(request, response, ErpJxkhZhxzbldkhz.class);
    }

}

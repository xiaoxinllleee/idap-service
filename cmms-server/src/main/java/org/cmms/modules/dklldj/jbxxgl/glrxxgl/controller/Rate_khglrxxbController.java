package org.cmms.modules.dklldj.jbxxgl.glrxxgl.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.dklldj.jbxxgl.glrxxgl.entity.Rate_khglrxxb;
import org.cmms.modules.dklldj.jbxxgl.glrxxgl.service.IRate_khglrxxbService;
import org.cmms.modules.dklldj.lldjgl.glzhgl.entity.rateKhzhglxxb;
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

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Description: 关联人信息管理
 * @Author: jeecg-boot
 * @Date: 2020-03-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "关联人信息管理")
@RestController
@RequestMapping("/dklldj.jbxxgl.glrxxgl/rate_khglrxxb")
public class Rate_khglrxxbController extends JeecgController<Rate_khglrxxb, IRate_khglrxxbService> {
    @Autowired
    private IRate_khglrxxbService rate_khglrxxbService;
    @Autowired
    private ISysDictService iSysDictService;
    @Value(value = "${common.path.upload}")
    private String uploadPath;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param rate_khglrxxb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "关联人信息管理-分页列表查询")
    @ApiOperation(value = "关联人信息管理-分页列表查询", notes = "关联人信息管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Rate_khglrxxb rate_khglrxxb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<Rate_khglrxxb>> result = new Result<IPage<Rate_khglrxxb>>();
        QueryWrapper<Rate_khglrxxb> queryWrapper = QueryGenerator.initQueryWrapper(rate_khglrxxb, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IRate_khglrxxbService.class, rate_khglrxxbService, pageNo, pageSize, queryWrapper, "zjhm", "glrzjhm");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 关联人信息管理 / 添加
     *
     * @param rate_khglrxxb
     * @return
     */
    @AutoLog(value = "关联人信息管理-添加")
    @ApiOperation(value = "关联人信息管理-添加", notes = "关联人信息管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Rate_khglrxxb rate_khglrxxb) {
        try {
            rate_khglrxxb.setScbz(0);
            rate_khglrxxb.setLrczy(getLoginUser().getUsername());
            rate_khglrxxb.setLrbz(1);
            rate_khglrxxb.setLrsj(new Date());
            rate_khglrxxbService.save(rate_khglrxxb);
            return Result.ok("添加成功！");
        } catch (Throwable throwable) {
            log.error("贷款利率定价 / 关联人信息管理 / 添加失败！"+throwable.getMessage());
            return Result.ok("添加失败！"+throwable.getMessage());
        }
    }

    /**
     * 关联人信息管理 / 编辑
     *
     * @param rate_khglrxxb
     * @return
     */
    @AutoLog(value = "关联人信息管理-编辑")
    @ApiOperation(value = "关联人信息管理-编辑", notes = "关联人信息管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Rate_khglrxxb rate_khglrxxb) {
        try {
            QueryWrapper<Rate_khglrxxb> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zjhm", rate_khglrxxb.getZjhm());
            queryWrapper.eq("glrzjhm", rate_khglrxxb.getGlrzjhm());
            //主键不能更新
            rate_khglrxxb.setZjhm(null);
            rate_khglrxxb.setGlrzjhm(null);
            rate_khglrxxb.setLrbz(2);
            rate_khglrxxb.setLrsj(new Timestamp(System.currentTimeMillis()));
            rate_khglrxxb.setLrczy(getLoginUser().getUsername());
            rate_khglrxxbService.update(rate_khglrxxb, queryWrapper);
            return Result.ok("编辑成功!");
        } catch (Throwable throwable) {
            log.error("贷款利率定价 / 关联人信息管理 / 编辑失败！"+throwable.getMessage());
            return Result.ok("编辑失败！"+throwable.getMessage());
        }
    }

    /**
     * 关联人信息管理 / 删除
     *
     * @param glrzjhm
     * @return
     */
    @AutoLog(value = "关联人信息管理-删除")
    @ApiOperation(value = "关联人信息管理-删除", notes = "关联人信息管理-删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "zjhm", required = true) String zjhm,
                            @RequestParam(name = "glrzjhm", required = true) String glrzjhm) {
        try {
            QueryWrapper<Rate_khglrxxb> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zjhm", zjhm);
            queryWrapper.eq("glrzjhm", glrzjhm);
            rate_khglrxxbService.remove(queryWrapper);
            return Result.ok("删除成功!");
        } catch (Throwable throwable) {
            log.error("贷款利率定价 / 关联人信息管理 / 删除失败！"+throwable.getMessage());
            return Result.ok("删除失败!"+throwable.getMessage());
        }
    }

    /**
     * 关联人信息管理 / 提取
     *
     * @return
     */
    @RequestMapping(value = "/extract", method = RequestMethod.PUT)
    public Result<?> extract() {
        Result result = new Result<>();
        //String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC", "VALUE", "CODE", "101001");
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            HashMap<String, String> params = new HashMap<>();
            params.put("etl_task","kiss.domain.application.rate.proc_rate_khglrxx_tj");
            // count_rate_khglrxx
            boolean falg = EtlUtil.callEtl("rate_common_init", params, 15);
            result.setSuccess(falg);
        } else {
            try {
                rate_khglrxxbService.extract();
                result.setSuccess(true);
                return result;
            } catch (Exception e) {
                System.out.println(e);
                log.error("提取失败"+e.getMessage());
                result.setSuccess(false);
            }
        }
        return result;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param rate_khglrxxb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Rate_khglrxxb rate_khglrxxb) {
        return super.exportXls(request, rate_khglrxxb, Rate_khglrxxb.class, "客户关联人信息");
    }

    /*
     * 导出Excel模板
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        // AutoPoi 导出Excel
        ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "客户关联人信息导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, Rate_khglrxxb.class);
        ExportParams exportParams = new ExportParams("客户关联人信息导入模板", "模板信息");
        modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
        modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<Rate_khglrxxb>());
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
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams importParams = new ImportParams();
            importParams.setTitleRows(2);
            importParams.setHeadRows(1);
            importParams.setNeedSave(true);
            try {
                List<Rate_khglrxxb> Rate_khjbxxbList = ExcelImportUtil.importExcel(file.getInputStream(), Rate_khglrxxb.class, importParams);
                for (Rate_khglrxxb rate_khjbxxb : Rate_khjbxxbList) {
                    if (rate_khjbxxb.getZjhm().contains(".")) {
                        rate_khjbxxb.setZjhm(rate_khjbxxb.getZjhm().split("\\.")[0]);
                    }
                    if (rate_khjbxxb.getGlrzjhm().contains(".")) {
                        rate_khjbxxb.setGlrzjhm(rate_khjbxxb.getGlrzjhm().split("\\.")[0]);
                    }
                    rate_khjbxxb.setLrbz(1);
                    rate_khjbxxb.setLrsj(new Date());
                    LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    rate_khjbxxb.setLrczy(sysUser.getUsername());
                    // rate_khjbxxb.setKhlx(iSysDictService.queryTableDictTextByKey("SYS_DICT_ITEM","item_value","item_text",rate_khjbxxb.getKhlx()));
                }
                rate_khglrxxbService.saveBatch(Rate_khjbxxbList);
                return Result.ok("文件导入成功！共[ " + Rate_khjbxxbList.size() + " ]条数据！");
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败！" + e.getMessage());
            }
        }
        return Result.ok("文件导入失败！");
    }
}

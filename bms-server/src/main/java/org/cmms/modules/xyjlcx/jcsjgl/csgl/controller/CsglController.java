package org.cmms.modules.xyjlcx.jcsjgl.csgl.controller;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.xyjlcx.jcsjgl.csgl.entity.Csgl;
import org.cmms.modules.xyjlcx.jcsjgl.csgl.service.ICsglService;
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
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 参数管理
 * @Author: jeecg-boot
 * @Date: 2021-08-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "参数管理")
@RestController
@RequestMapping("/csgl/csgl")
public class CsglController extends JeecgController<Csgl, ICsglService> {
    @Autowired
    private ICsglService csglService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param csgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "参数管理-分页列表查询")
    @ApiOperation(value = "参数管理-分页列表查询", notes = "参数管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Csgl csgl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<Csgl>> result = new Result<IPage<Csgl>>();
        QueryWrapper<Csgl> queryWrapper = QueryGenerator.initQueryWrapper(csgl, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(ICsglService.class, csglService, pageNo, pageSize, queryWrapper, "csbm");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 参数管理 / 添加
     *
     * @param csgl
     * @return
     */
    @AutoLog(value = "参数管理-添加")
    @ApiOperation(value = "参数管理-添加", notes = "参数管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Csgl csgl) {
        try {
            QueryWrapper<Csgl> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("csbm", csgl.getCsbm());
            Csgl record = csglService.getOne(queryWrapper,false);
            if (record != null) {
                return Result.error("参数编码已经存在，请核实！");
            }
            csgl.setLrbz(1);
            csgl.setLrsj(new Timestamp(System.currentTimeMillis()));
            csgl.setLrr(getLoginUser().getRealname());
            csglService.save(csgl);
            return Result.ok("添加成功！");
        } catch (Throwable throwable) {
            log.error("信用记录查询 / 参数管理 / 添加失败！" + throwable.getMessage());
            return Result.error("添加失败，请联系系统管理员！");
        }
    }

    /**
     * 参数管理 / 编辑
     *
     * @param csgl
     * @return
     */
    @AutoLog(value = "参数管理-编辑")
    @ApiOperation(value = "参数管理-编辑", notes = "参数管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Csgl csgl) {
        try {
            QueryWrapper<Csgl> queryWrapper = new QueryWrapper<Csgl>();
            queryWrapper.eq("csbm", csgl.getCsbm());
            //表主键不能更新（Kudu）
            csgl.setCsbm(null);
            csgl.setLrbz(2);
            csgl.setLrsj(new Timestamp(System.currentTimeMillis()));
            csgl.setLrr(getLoginUser().getRealname());
            csglService.update(csgl, queryWrapper);
            return Result.ok("编辑成功！");
        } catch (Throwable throwable) {
            log.error("信用记录查询 / 参数管理 / 编辑失败！" + throwable.getMessage());
            return Result.error("编辑失败，请联系系统管理员！");
        }
    }

    /**
     * 参数管理 / 删除
     *
     * @param
     * @return
     */
    @AutoLog(value = "参数管理-删除")
    @ApiOperation(value = "参数管理-删除", notes = "参数管理-删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@Param("csbm") String csbm) {
        try {
			QueryWrapper<Csgl> queryWrapper = new QueryWrapper<Csgl>();
			queryWrapper.eq("csbm", csbm);
			csglService.remove(queryWrapper);
            return Result.ok("删除成功！");
		} catch (Throwable throwable) {
			log.error("信用记录查询 / 参数管理 / 删除失败！" + throwable.getMessage());
			return Result.error("删除失败！");
		}
    }

    /**
     * 导出excel
     *
     * @param request
     * @param csgl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Csgl csgl) {
        return super.exportXls(request, csgl, Csgl.class, "参数管理");
    }

}

package org.cmms.modules.ckjkpt.ckjkptdqckdqyj.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.ckjkpt.ckjkptdqckdqyj.entity.VckjkptCkdqyj;
import org.cmms.modules.ckjkpt.ckjkptdqckdqyj.service.IVckjkptCkdqyjService;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
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
 * @Description: 存款到期预警
 * @Author: cmms
 * @Date: 2019-10-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "存款到期预警")
@RestController
@RequestMapping("/ckjkpt.ckjkptdqckdqyj/vckjkptCkdqyj")
public class VckjkptCkdqyjController {
    @Autowired
    private IVckjkptCkdqyjService vckjkptCkdqyjService;
    @Autowired
    private ITjfxZhbyService tjfxZhbyService;

    /**
     * 分页列表查询
     *
     * @param vckjkptCkdqyj
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "存款到期预警-分页列表查询")
    @ApiOperation(value = "存款到期预警-分页列表查询", notes = "存款到期预警-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(VckjkptCkdqyj vckjkptCkdqyj,
                                                      @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                      HttpServletRequest req) {
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(vckjkptCkdqyj, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(IVckjkptCkdqyjService.class,vckjkptCkdqyjService,pageNo,pageSize,queryWrapper,"jgdm","ckzh","zjhm");
        return Result.ok(pageList);
    }

    /**
     * 分页列表查询
     *
     * @param vckjkptCkdqyj
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "存款到期预警-分页列表查询")
    @ApiOperation(value = "存款到期预警-分页列表查询", notes = "存款到期预警-分页列表查询")
    @GetMapping(value = "/ipadlist")
    public Result<?> queryIpadList(VckjkptCkdqyj vckjkptCkdqyj,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        String sszh = "";
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if (sysUser.getOrgCode() != null && sysUser.getOrgCode().equals("1")) {
            vckjkptCkdqyj.setJgdm(vckjkptCkdqyj.getJgdm());
        } else {
            vckjkptCkdqyj.setJgdm(sysUser.getOrgCode() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization", "zzbz", "ywjgdm", sysUser.getOrgCode()));
        }
//        QueryWrapper<VckjkptCkdqyj> queryWrapper = QueryGenerator.initQueryWrapper(vckjkptCkdqyj, req.getParameterMap());
//        Page<VckjkptCkdqyj> page = new Page<VckjkptCkdqyj>(pageNo, pageSize);
//        IPage<VckjkptCkdqyj> pageList = vckjkptCkdqyjService.page(page, queryWrapper);
//        return Result.ok(pageList);
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(vckjkptCkdqyj, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(IVckjkptCkdqyjService.class,vckjkptCkdqyjService,pageNo,pageSize,queryWrapper,"jgdm","zjhm");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param vckjkptCkdqyj
     * @return
     */
    @AutoLog(value = "存款到期预警-添加")
    @ApiOperation(value = "存款到期预警-添加", notes = "存款到期预警-添加")
    @PostMapping(value = "/add")
    public Result<VckjkptCkdqyj> add(@RequestBody VckjkptCkdqyj vckjkptCkdqyj) {
        Result<VckjkptCkdqyj> result = new Result<VckjkptCkdqyj>();
        try {
            vckjkptCkdqyjService.save(vckjkptCkdqyj);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }

    /**
     *  编辑
     * @param vckjkptCkdqyj
     * @return
     */
	/*@AutoLog(value = "存款到期预警-编辑")
	@ApiOperation(value="存款到期预警-编辑", notes="存款到期预警-编辑")
	@PutMapping(value = "/edit")
	public Result<VckjkptCkdqyj> edit(@RequestBody VckjkptCkdqyj vckjkptCkdqyj) {
		Result<VckjkptCkdqyj> result = new Result<VckjkptCkdqyj>();
		VckjkptCkdqyj vckjkptCkdqyjEntity = vckjkptCkdqyjService.getById(vckjkptCkdqyj.getId());
		if(vckjkptCkdqyjEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = vckjkptCkdqyjService.updateById(vckjkptCkdqyj);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}

		return result;
	}*/

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "存款到期预警-通过id删除")
    @ApiOperation(value = "存款到期预警-通过id删除", notes = "存款到期预警-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        try {
            vckjkptCkdqyjService.removeById(id);
        } catch (Exception e) {
            log.error("删除失败", e.getMessage());
            return Result.error("删除失败!");
        }
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "存款到期预警-批量删除")
    @ApiOperation(value = "存款到期预警-批量删除", notes = "存款到期预警-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<VckjkptCkdqyj> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<VckjkptCkdqyj> result = new Result<VckjkptCkdqyj>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.vckjkptCkdqyjService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "存款到期预警-通过id查询")
    @ApiOperation(value = "存款到期预警-通过id查询", notes = "存款到期预警-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<VckjkptCkdqyj> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<VckjkptCkdqyj> result = new Result<VckjkptCkdqyj>();
        VckjkptCkdqyj vckjkptCkdqyj = vckjkptCkdqyjService.getById(id);
        if (vckjkptCkdqyj == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(vckjkptCkdqyj);
            result.setSuccess(true);
        }
        return result;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        // Step.1 组装查询条件
        QueryWrapper<VckjkptCkdqyj> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                VckjkptCkdqyj vckjkptCkdqyj = JSON.parseObject(deString, VckjkptCkdqyj.class);
                queryWrapper = QueryGenerator.initQueryWrapper(vckjkptCkdqyj, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<VckjkptCkdqyj> pageList = vckjkptCkdqyjService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "存款到期预警列表");
        mv.addObject(NormalExcelConstants.CLASS, VckjkptCkdqyj.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("存款到期预警列表数据", "导出人:"+sysUser.getRealname(), "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
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
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<VckjkptCkdqyj> listVckjkptCkdqyjs = ExcelImportUtil.importExcel(file.getInputStream(), VckjkptCkdqyj.class, params);
                vckjkptCkdqyjService.saveBatch(listVckjkptCkdqyjs);
                return Result.ok("文件导入成功！数据行数:" + listVckjkptCkdqyjs.size());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.ok("文件导入失败！");
    }

}

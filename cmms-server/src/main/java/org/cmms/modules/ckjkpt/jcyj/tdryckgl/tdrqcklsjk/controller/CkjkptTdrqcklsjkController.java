package org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqcklsjk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqcklsjk.entity.CkjkptTdrqcklsjk;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqcklsjk.service.ICkjkptTdrqcklsjkService;
import org.cmms.modules.util.EtlUtil;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 1
 * @Author: cmms
 * @Date: 2019-10-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "特定人群存款流水监控")
@RestController
@RequestMapping("/CkjkptTdrqcklsjk/ckjkptTdrqcklsjk")
public class CkjkptTdrqcklsjkController {
    @Autowired
    private ICkjkptTdrqcklsjkService ckjkptTdrqcklsjkService;
    @Autowired
    private RedisUtil redisUtil;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param ckjkptTdrqcklsjk
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "特定人群存款流水监控-分页列表查询")
    @ApiOperation(value = "特定人群存款流水监控-分页列表查询", notes = "特定人群存款流水监控-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CkjkptTdrqcklsjk ckjkptTdrqcklsjk,
                                                         @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                         HttpServletRequest req) {
//        QueryWrapper<CkjkptTdrqcklsjk> queryWrapper = QueryGenerator.initQueryWrapper(ckjkptTdrqcklsjk, req.getParameterMap());
//        queryWrapper.orderByDesc("tjyf").orderByDesc("jgdm").orderByDesc("zjhm");
//        List<CkjkptTdrqcklsjk> list = ckjkptTdrqcklsjkService.list(queryWrapper);
//        IPage<CkjkptTdrqcklsjk> pageList = PageUtil.getPages(list, list == null ? 0 : list.size(), pageSize, pageNo);
//        return Result.ok(pageList);
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(ckjkptTdrqcklsjk, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkjkptTdrqcklsjkService.class,ckjkptTdrqcklsjkService,pageNo,pageSize,queryWrapper,"tjyf","jgdm","zjhm");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param ckjkptTdrqcklsjk
     * @return
     */
    @AutoLog(value = "特定人群存款流水监控-添加")
    @ApiOperation(value = "特定人群存款流水监控-添加", notes = "特定人群存款流水监控-添加")
    @PostMapping(value = "/add")
    public Result<CkjkptTdrqcklsjk> add(@RequestBody CkjkptTdrqcklsjk ckjkptTdrqcklsjk) {
        Result<CkjkptTdrqcklsjk> result = new Result<CkjkptTdrqcklsjk>();
        try {
            ckjkptTdrqcklsjkService.save(ckjkptTdrqcklsjk);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }

    /**
     *  编辑
     * @param ckjkptTdrqcklsjk
     * @return
     *//*
	@AutoLog(value = "特定人群存款流水监控-编辑")
	@ApiOperation(value="特定人群存款流水监控-编辑", notes="特定人群存款流水监控-编辑")
	@PutMapping(value = "/edit")
	public Result<CkjkptTdrqcklsjk> edit(@RequestBody CkjkptTdrqcklsjk ckjkptTdrqcklsjk) {
		Result<CkjkptTdrqcklsjk> result = new Result<CkjkptTdrqcklsjk>();
		CkjkptTdrqcklsjk ckjkptTdrqcklsjkEntity = ckjkptTdrqcklsjkService.getById(ckjkptTdrqcklsjk.getId());
		if(ckjkptTdrqcklsjkEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = ckjkptTdrqcklsjkService.updateById(ckjkptTdrqcklsjk);
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
    @AutoLog(value = "特定人群存款流水监控-通过id删除")
    @ApiOperation(value = "特定人群存款流水监控-通过id删除", notes = "特定人群存款流水监控-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        try {
            ckjkptTdrqcklsjkService.removeById(id);
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
    @AutoLog(value = "特定人群存款流水监控-批量删除")
    @ApiOperation(value = "特定人群存款流水监控-批量删除", notes = "特定人群存款流水监控-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<CkjkptTdrqcklsjk> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<CkjkptTdrqcklsjk> result = new Result<CkjkptTdrqcklsjk>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.ckjkptTdrqcklsjkService.removeByIds(Arrays.asList(ids.split(",")));
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
    @AutoLog(value = "特定人群存款流水监控-通过id查询")
    @ApiOperation(value = "特定人群存款流水监控-通过id查询", notes = "特定人群存款流水监控-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<CkjkptTdrqcklsjk> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<CkjkptTdrqcklsjk> result = new Result<CkjkptTdrqcklsjk>();
        CkjkptTdrqcklsjk ckjkptTdrqcklsjk = ckjkptTdrqcklsjkService.getById(id);
        if (ckjkptTdrqcklsjk == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(ckjkptTdrqcklsjk);
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
        QueryWrapper<CkjkptTdrqcklsjk> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                CkjkptTdrqcklsjk ckjkptTdrqcklsjk = JSON.parseObject(deString, CkjkptTdrqcklsjk.class);
                queryWrapper = QueryGenerator.initQueryWrapper(ckjkptTdrqcklsjk, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<CkjkptTdrqcklsjk> pageList = ckjkptTdrqcklsjkService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "特定人群存款流水监控列表");
        mv.addObject(NormalExcelConstants.CLASS, CkjkptTdrqcklsjk.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("特定人群存款流水监控列表数据", "导出人:"+sysUser.getRealname(), "导出信息"));
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
                List<CkjkptTdrqcklsjk> listCkjkptTdrqcklsjks = ExcelImportUtil.importExcel(file.getInputStream(), CkjkptTdrqcklsjk.class, params);
                ckjkptTdrqcklsjkService.saveBatch(listCkjkptTdrqcklsjks);
                return Result.ok("文件导入成功！数据行数:" + listCkjkptTdrqcklsjks.size());
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

	/**
	 * 特定人群存款流水监控 - 提取
	 * @param jsonObject
	 * @return
	 */
    @RequestMapping(value = "/extract")
    public Result<?> extract(@RequestBody JSONObject jsonObject) {
        Result result = new Result<>();
        String tjyf = jsonObject.getString("tjyf").replace("-","");
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            HashMap<String, String> params = new HashMap<>();
            params.put("i_tjyf", tjyf);
            params.put("etl_task","kiss.domain.application.ckjkpt.proc_ckjkpt_tdrqcklsjk");
            // count_ckjkpt_tdrqcklsjk
            boolean falg = EtlUtil.callEtl("ckjkpt_common_init", params, 15);
            result.setSuccess(falg);
        } else {
            try {
                ckjkptTdrqcklsjkService.extract(tjyf);
                result.setSuccess(true);
            } catch (Exception e) {
                System.out.println(e);
                log.error("提取失败", e.getMessage());
                result.setSuccess(false);
            }
        }
        return result;
    }
}

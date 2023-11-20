package org.cmms.modules.ckjkpt.jcyj.zhckpldyj.controller;

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
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.ckjkpt.jcyj.zhckpldyj.entity.CkjkptZhckpldgl;
import org.cmms.modules.ckjkpt.jcyj.zhckpldyj.service.ICkjkptZhckpldglService;
import org.cmms.modules.system.service.ISysLogService;
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
import java.util.*;

/**
 * @Description: 支行存款偏离度管理
 * @Author: cmms
 * @Date: 2019-10-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "支行存款偏离度管理")
@RestController
@RequestMapping("/CkjkptZhckpldgl/ckjkptZhckpldgl")
public class CkjkptZhckpldglController {
    @Autowired
    private ICkjkptZhckpldglService ckjkptZhckpldglService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ISysLogService sysLogService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param ckjkptZhckpldgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "支行存款偏离度管理-分页列表查询")
    @ApiOperation(value = "支行存款偏离度管理-分页列表查询", notes = "支行存款偏离度管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CkjkptZhckpldgl ckjkptZhckpldgl,
                                                        @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                        HttpServletRequest req) {
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(ckjkptZhckpldgl, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkjkptZhckpldglService.class,ckjkptZhckpldglService,pageNo,pageSize,queryWrapper,"tjyf","jgdm","tjbs");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param ckjkptZhckpldgl
     * @return
     */
    @AutoLog(value = "支行存款偏离度管理-添加")
    @ApiOperation(value = "支行存款偏离度管理-添加", notes = "支行存款偏离度管理-添加")
    @PostMapping(value = "/add")
    public Result<CkjkptZhckpldgl> add(@RequestBody CkjkptZhckpldgl ckjkptZhckpldgl) {
        Result<CkjkptZhckpldgl> result = new Result<CkjkptZhckpldgl>();
        try {
            ckjkptZhckpldglService.save(ckjkptZhckpldgl);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }

    /**
     *  编辑
     * @param ckjkptZhckpldgl
     * @return
     *//*
	@AutoLog(value = "支行存款偏离度管理-编辑")
	@ApiOperation(value="支行存款偏离度管理-编辑", notes="支行存款偏离度管理-编辑")
	@PutMapping(value = "/edit")
	public Result<CkjkptZhckpldgl> edit(@RequestBody CkjkptZhckpldgl ckjkptZhckpldgl) {
		Result<CkjkptZhckpldgl> result = new Result<CkjkptZhckpldgl>();
		CkjkptZhckpldgl ckjkptZhckpldglEntity = ckjkptZhckpldglService.getById(ckjkptZhckpldgl.getId());
		if(ckjkptZhckpldglEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = ckjkptZhckpldglService.updateById(ckjkptZhckpldgl);
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
    @AutoLog(value = "支行存款偏离度管理-通过id删除")
    @ApiOperation(value = "支行存款偏离度管理-通过id删除", notes = "支行存款偏离度管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        try {
            ckjkptZhckpldglService.removeById(id);
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
    @AutoLog(value = "支行存款偏离度管理-批量删除")
    @ApiOperation(value = "支行存款偏离度管理-批量删除", notes = "支行存款偏离度管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<CkjkptZhckpldgl> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<CkjkptZhckpldgl> result = new Result<CkjkptZhckpldgl>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.ckjkptZhckpldglService.removeByIds(Arrays.asList(ids.split(",")));
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
    @AutoLog(value = "支行存款偏离度管理-通过id查询")
    @ApiOperation(value = "支行存款偏离度管理-通过id查询", notes = "支行存款偏离度管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<CkjkptZhckpldgl> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<CkjkptZhckpldgl> result = new Result<CkjkptZhckpldgl>();
        CkjkptZhckpldgl ckjkptZhckpldgl = ckjkptZhckpldglService.getById(id);
        if (ckjkptZhckpldgl == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(ckjkptZhckpldgl);
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
        QueryWrapper<CkjkptZhckpldgl> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                CkjkptZhckpldgl ckjkptZhckpldgl = JSON.parseObject(deString, CkjkptZhckpldgl.class);
                queryWrapper = QueryGenerator.initQueryWrapper(ckjkptZhckpldgl, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<CkjkptZhckpldgl> pageList = ckjkptZhckpldglService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "支行存款偏离度管理列表");
        mv.addObject(NormalExcelConstants.CLASS, CkjkptZhckpldgl.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("支行存款偏离度管理列表数据", "导出人:"+sysUser.getRealname(), "导出信息"));
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
                List<CkjkptZhckpldgl> listCkjkptZhckpldgls = ExcelImportUtil.importExcel(file.getInputStream(), CkjkptZhckpldgl.class, params);
                ckjkptZhckpldglService.saveBatch(listCkjkptZhckpldgls);
                return Result.ok("文件导入成功！数据行数:" + listCkjkptZhckpldgls.size());
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
	 * 支行存款偏离度管理-提取
	 * @param jsonObject
	 * @return
	 */
	@RequestMapping(value = "/extract")
    public Result<?> extract(@RequestBody JSONObject jsonObject) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
        Result result = new Result<>();
        String tjyf = jsonObject.getString("tjyf");
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            Date cksjrq = DateUtil.string2Date(sysLogService.cksjrqBig(),"yyyyMMdd");
            tjyf=org.cmms.modules.util.DateUtil.getSjQmrq(tjyf,cksjrq,"yyyy-MM-dd").replace("-","");
            HashMap<String, String> params = new HashMap<>();
            params.put("i_day", tjyf);
            params.put("etl_task","kiss.domain.application.ckjkpt.proc_ckjkpt_zhckpldyj");
            // count_ckjkpt_zhckpldyj
            boolean falg = EtlUtil.callEtl("ckjkpt_common_init", params, 15);
            result.setSuccess(falg);
        } else {
            try {
                ckjkptZhckpldglService.extract(tjyf.replace("-",""));
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
}

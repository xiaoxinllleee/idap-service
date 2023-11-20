package org.cmms.modules.ckjkpt.jcyj.khckrpjc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.checkerframework.checker.units.qual.A;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.ckjkpt.csgl.cssz.service.ICkjkptCsglService;
import org.cmms.modules.ckjkpt.jcyj.khckrpjc.entity.CkjkptKhckrpjc;
import org.cmms.modules.ckjkpt.jcyj.khckrpjc.service.ICkjkptKhckrpjcService;
import org.cmms.modules.ckjkpt.util.mapper.CkjkptCallMapper;
import org.cmms.modules.system.service.ISysLogService;
import org.cmms.modules.util.DateUtil;
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
import java.text.ParseException;
import java.util.*;

/**
 * @Description: 客户存款日平监测查询
 * @Author: cmms
 * @Date: 2019-10-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "客户存款日平监测查询")
@RestController
@RequestMapping("/CkjkptKhckrpjc/ckjkptKhckrpjc")
public class CkjkptKhckrpjcController {
    @Autowired
    private ICkjkptKhckrpjcService ckjkptKhckrpjcService;
    @Autowired
    private ICkjkptCsglService ckjkptCsglService;
    @Autowired
    private ISysLogService sysLogService;
    @Autowired
    private RedisUtil redisUtil;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param ckjkptKhckrpjc
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "客户存款日平监测查询-分页列表查询")
    @ApiOperation(value = "客户存款日平监测查询-分页列表查询", notes = "客户存款日平监测查询-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CkjkptKhckrpjc ckjkptKhckrpjc,
                                                       @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                       HttpServletRequest req) {
//        QueryWrapper<CkjkptKhckrpjc> queryWrapper = QueryGenerator.initQueryWrapper(ckjkptKhckrpjc, req.getParameterMap());
//        queryWrapper.orderByDesc("tjyf").orderByDesc("jgdm").orderByDesc("zjhm");
//		List<CkjkptKhckrpjc> list = ckjkptKhckrpjcService.list(queryWrapper);
//		IPage<CkjkptKhckrpjc> pageList = PageUtil.getPages(list, list == null ? 0 : list.size(), pageSize, pageNo);
//		return Result.ok(pageList);
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(ckjkptKhckrpjc, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkjkptKhckrpjcService.class,ckjkptKhckrpjcService,pageNo,pageSize,queryWrapper,"tjyf","jgdm","zjhm");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param ckjkptKhckrpjc
     * @return
     */
    @AutoLog(value = "客户存款日平监测查询-添加")
    @ApiOperation(value = "客户存款日平监测查询-添加", notes = "客户存款日平监测查询-添加")
    @PostMapping(value = "/add")
    public Result<CkjkptKhckrpjc> add(@RequestBody CkjkptKhckrpjc ckjkptKhckrpjc) {
        Result<CkjkptKhckrpjc> result = new Result<CkjkptKhckrpjc>();
        try {
            ckjkptKhckrpjcService.save(ckjkptKhckrpjc);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }

    /**
     *  编辑
     * @param ckjkptKhckrpjc
     * @return
     *//*
	@AutoLog(value = "客户存款日平监测查询-编辑")
	@ApiOperation(value="客户存款日平监测查询-编辑", notes="客户存款日平监测查询-编辑")
	@PutMapping(value = "/edit")
	public Result<CkjkptKhckrpjc> edit(@RequestBody CkjkptKhckrpjc ckjkptKhckrpjc) {
		Result<CkjkptKhckrpjc> result = new Result<CkjkptKhckrpjc>();
		CkjkptKhckrpjc ckjkptKhckrpjcEntity = ckjkptKhckrpjcService.getById(ckjkptKhckrpjc.getId());
		if(ckjkptKhckrpjcEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = ckjkptKhckrpjcService.updateById(ckjkptKhckrpjc);
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
    @AutoLog(value = "客户存款日平监测查询-通过id删除")
    @ApiOperation(value = "客户存款日平监测查询-通过id删除", notes = "客户存款日平监测查询-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        try {
            ckjkptKhckrpjcService.removeById(id);
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
    @AutoLog(value = "客户存款日平监测查询-批量删除")
    @ApiOperation(value = "客户存款日平监测查询-批量删除", notes = "客户存款日平监测查询-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<CkjkptKhckrpjc> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<CkjkptKhckrpjc> result = new Result<CkjkptKhckrpjc>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.ckjkptKhckrpjcService.removeByIds(Arrays.asList(ids.split(",")));
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
    @AutoLog(value = "客户存款日平监测查询-通过id查询")
    @ApiOperation(value = "客户存款日平监测查询-通过id查询", notes = "客户存款日平监测查询-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<CkjkptKhckrpjc> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<CkjkptKhckrpjc> result = new Result<CkjkptKhckrpjc>();
        CkjkptKhckrpjc ckjkptKhckrpjc = ckjkptKhckrpjcService.getById(id);
        if (ckjkptKhckrpjc == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(ckjkptKhckrpjc);
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
        QueryWrapper<CkjkptKhckrpjc> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                CkjkptKhckrpjc ckjkptKhckrpjc = JSON.parseObject(deString, CkjkptKhckrpjc.class);
                queryWrapper = QueryGenerator.initQueryWrapper(ckjkptKhckrpjc, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<CkjkptKhckrpjc> pageList = ckjkptKhckrpjcService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "客户存款日平监测列表");
        mv.addObject(NormalExcelConstants.CLASS, CkjkptKhckrpjc.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户存款日平监测列表数据", "导出人:"+sysUser.getRealname(), "导出信息"));
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
                List<CkjkptKhckrpjc> listCkjkptKhckrpjcs = ExcelImportUtil.importExcel(file.getInputStream(), CkjkptKhckrpjc.class, params);
                ckjkptKhckrpjcService.saveBatch(listCkjkptKhckrpjcs);
                return Result.ok("文件导入成功！数据行数:" + listCkjkptKhckrpjcs.size());
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
     * 客户存款日平监测-提取
     *
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/extract")
    public Result<?> extract(@RequestBody JSONObject jsonObject) {
        Result result = new Result<>();
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
        String tjyf = jsonObject.getString("tjyf");
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            HashMap<String, String> params = new HashMap<>();
            // 获取存款day表最大数据日期
            Date invmMaxDataDate = DateUtil.string2Date(sysLogService.cksjrqBig(),"yyyyMMdd");
            tjyf = DateUtil.getSjQmrq(tjyf, invmMaxDataDate, "yyyy-MM-dd").replace("-","");
            params.put("i_day", tjyf);
            String lv_yrpb_ss = ckjkptCsglService.queryParamValue("CS0003");
            String lv_yrpb_xj = ckjkptCsglService.queryParamValue("CS0004");
            params.put("lv_yrpb_ss", lv_yrpb_ss);
            params.put("lv_yrpb_xj", lv_yrpb_xj);
            params.put("etl_task","kiss.domain.application.ckjkpt.proc_ckjkpt_khckrpjc");
            // count_ckjkpt_khckrpjc
            boolean falg = EtlUtil.callEtl("ckjkpt_common_init", params, 20);
            result.setSuccess(falg);
        } else {
            try {
                tjyf = tjyf.replace("-", "");
                ckjkptKhckrpjcService.extract(tjyf);
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

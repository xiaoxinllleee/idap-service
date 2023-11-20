package org.cmms.modules.ckjkpt.jcyj.khckyejc.controller;

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
import org.cmms.modules.ckjkpt.csgl.cssz.service.ICkjkptCsglService;
import org.cmms.modules.ckjkpt.jcyj.khckrpjc.entity.CkjkptKhckrpjc;
import org.cmms.modules.ckjkpt.jcyj.khckyejc.entity.CkjkptKhyeckjc;
import org.cmms.modules.ckjkpt.jcyj.khckyejc.service.ICkjkptKhyeckjcService;
import org.cmms.modules.ckjkpt.util.mapper.CkjkptCallMapper;
import org.cmms.modules.gzap.grrwsz.entity.GzapGrrwsz;
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
import java.util.*;

/**
 * @Description: 客户余额存款监测查询
 * @Author: cmms
 * @Date: 2019-10-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "客户余额存款监测查询")
@RestController
@RequestMapping("/CkjkptKhyeckjc/ckjkptKhyeckjc")
public class CkjkptKhyeckjcController {
    @Autowired
    private ICkjkptKhyeckjcService ckjkptKhyeckjcService;
    @Autowired
    private ICkjkptCsglService ckjkptCsglService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ISysLogService sysLogService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param ckjkptKhyeckjc
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "客户余额存款监测查询-分页列表查询")
    @ApiOperation(value = "客户余额存款监测查询-分页列表查询", notes = "客户余额存款监测查询-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CkjkptKhyeckjc ckjkptKhyeckjc,
                                                       @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                       HttpServletRequest req) {
//        QueryWrapper<CkjkptKhyeckjc> queryWrapper = QueryGenerator.initQueryWrapper(ckjkptKhyeckjc, req.getParameterMap());
//        queryWrapper.orderByDesc("tjrq").orderByDesc("jgdm").orderByDesc("yggh").orderByDesc("zjhm");
//        List<CkjkptKhyeckjc> list = ckjkptKhyeckjcService.list(queryWrapper);
//        IPage<CkjkptKhyeckjc> pageList = PageUtil.getPages(list, list == null ? 0 : list.size(), pageSize, pageNo);
//        return Result.ok(pageList);
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(ckjkptKhyeckjc, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkjkptKhyeckjcService.class,ckjkptKhyeckjcService,pageNo,pageSize,queryWrapper,"tjrq","jgdm","yggh","zjhm");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param ckjkptKhyeckjc
     * @return
     */
    @AutoLog(value = "客户余额存款监测查询-添加")
    @ApiOperation(value = "客户余额存款监测查询-添加", notes = "客户余额存款监测查询-添加")
    @PostMapping(value = "/add")
    public Result<CkjkptKhyeckjc> add(@RequestBody CkjkptKhyeckjc ckjkptKhyeckjc) {
        Result<CkjkptKhyeckjc> result = new Result<CkjkptKhyeckjc>();
        try {
            ckjkptKhyeckjcService.save(ckjkptKhyeckjc);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }

    /**
     *  编辑
     * @param ckjkptKhyeckjc
     * @return
     *//*
	@AutoLog(value = "客户余额存款监测查询-编辑")
	@ApiOperation(value="客户余额存款监测查询-编辑", notes="客户余额存款监测查询-编辑")
	@PutMapping(value = "/edit")
	public Result<CkjkptKhyeckjc> edit(@RequestBody CkjkptKhyeckjc ckjkptKhyeckjc) {
		Result<CkjkptKhyeckjc> result = new Result<CkjkptKhyeckjc>();
		CkjkptKhyeckjc ckjkptKhyeckjcEntity = ckjkptKhyeckjcService.getById(ckjkptKhyeckjc.getId());
		if(ckjkptKhyeckjcEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = ckjkptKhyeckjcService.updateById(ckjkptKhyeckjc);
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
    @AutoLog(value = "客户余额存款监测查询-通过id删除")
    @ApiOperation(value = "客户余额存款监测查询-通过id删除", notes = "客户余额存款监测查询-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        try {
            ckjkptKhyeckjcService.removeById(id);
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
    @AutoLog(value = "客户余额存款监测查询-批量删除")
    @ApiOperation(value = "客户余额存款监测查询-批量删除", notes = "客户余额存款监测查询-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<CkjkptKhyeckjc> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<CkjkptKhyeckjc> result = new Result<CkjkptKhyeckjc>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.ckjkptKhyeckjcService.removeByIds(Arrays.asList(ids.split(",")));
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
    @AutoLog(value = "客户余额存款监测查询-通过id查询")
    @ApiOperation(value = "客户余额存款监测查询-通过id查询", notes = "客户余额存款监测查询-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<CkjkptKhyeckjc> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<CkjkptKhyeckjc> result = new Result<CkjkptKhyeckjc>();
        CkjkptKhyeckjc ckjkptKhyeckjc = ckjkptKhyeckjcService.getById(id);
        if (ckjkptKhyeckjc == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(ckjkptKhyeckjc);
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
        QueryWrapper<CkjkptKhyeckjc> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                CkjkptKhyeckjc ckjkptKhyeckjc = JSON.parseObject(deString, CkjkptKhyeckjc.class);
                queryWrapper = QueryGenerator.initQueryWrapper(ckjkptKhyeckjc, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<CkjkptKhyeckjc> pageList = ckjkptKhyeckjcService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "客户存款余额监测列表");
        mv.addObject(NormalExcelConstants.CLASS, CkjkptKhyeckjc.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户存款余额监测列表数据", "导出人:"+sysUser.getRealname(), "导出信息"));
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
                List<CkjkptKhyeckjc> listCkjkptKhyeckjcs = ExcelImportUtil.importExcel(file.getInputStream(), CkjkptKhyeckjc.class, params);
                ckjkptKhyeckjcService.saveBatch(listCkjkptKhyeckjcs);
                return Result.ok("文件导入成功！数据行数:" + listCkjkptKhyeckjcs.size());
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
	 * 客户存款余额监测-提取
	 * @param jsonObject
	 * @return
	 */
    @RequestMapping(value = "/extract")
    public Result<?> extract(@RequestBody JSONObject jsonObject) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
		String tjyf = jsonObject.getString("tjyf");
        Result result = new Result<>();
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            HashMap<String, String> params = new HashMap<>();
            String lv_yrpb_ss = ckjkptCsglService.queryParamValue("CS0005");
            String lv_nrpb_ss = ckjkptCsglService.queryParamValue("CS0006");
            String lv_yrpb_xj = ckjkptCsglService.queryParamValue("CS0007");
            String lv_nrpb_xj = ckjkptCsglService.queryParamValue("CS0008");
            params.put("lv_yrpb_ss", lv_yrpb_ss);
            params.put("lv_nrpb_ss", lv_nrpb_ss);
            params.put("lv_yrpb_xj", lv_yrpb_xj);
            params.put("lv_nrpb_xj", lv_nrpb_xj);
            // 获取存款day表最大数据日期
            Date invmMaxDataDate = DateUtil.string2Date(sysLogService.cksjrqBig(),"yyyyMMdd");
            //tjyf = DateUtil.getSjQmrq(tjyf, invmMaxDataDate, "yyyy-MM-dd").replace("-","");
            tjyf = tjyf.replace("-","");
            params.put("i_day", tjyf);
            params.put("etl_task","kiss.domain.application.ckjkpt.proc_ckjkpt_khckyejc");
            // count_ckjkpt_khckyejc
            boolean falg = EtlUtil.callEtl("ckjkpt_common_init", params, 20);
            result.setSuccess(falg);
        } else {
            try {
                tjyf = tjyf.replace("-", "");
                ckjkptKhyeckjcService.extract(tjyf);
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

package org.cmms.modules.ckjkpt.jcyj.ckyebd.controller;

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
import org.cmms.modules.ckjkpt.csgl.cssz.service.impl.CkjkptCsglServiceImpl;
import org.cmms.modules.ckjkpt.jcyj.ckyebd.entity.CkjkptCkyebd;
import org.cmms.modules.ckjkpt.jcyj.ckyebd.service.ICkjkptCkyebdService;
import org.cmms.modules.ckjkpt.util.mapper.CkjkptCallMapper;
import org.cmms.modules.khgl.nh.service.IFjglService;
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
 * @Description: 存款余额变动查询
 * @Author: cmms
 * @Date: 2019-10-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "存款余额变动查询")
@RestController
@RequestMapping("/CkjkptCkyebd/ckjkptCkyebd")
public class CkjkptCkyebdController {
    @Autowired
    private ICkjkptCkyebdService ckjkptCkyebdService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ICkjkptCsglService ckjkptCsglService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param ckjkptCkyebd
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "存款余额变动查询-分页列表查询")
    @ApiOperation(value = "存款余额变动查询-分页列表查询", notes = "存款余额变动查询-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CkjkptCkyebd ckjkptCkyebd,
                                                     @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                     HttpServletRequest req) {
        /*QueryWrapper<CkjkptCkyebd> queryWrapper = QueryGenerator.initQueryWrapper(ckjkptCkyebd, req.getParameterMap());
        List<CkjkptCkyebd> list = ckjkptCkyebdService.list(queryWrapper);
        IPage<CkjkptCkyebd> pageList = PageUtil.getPages(list, list == null ? 0 : list.size(), pageSize, pageNo);
        return Result.ok(pageList);*/
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(ckjkptCkyebd, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkjkptCkyebdService.class,ckjkptCkyebdService,pageNo,pageSize,queryWrapper,"zjhm");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param ckjkptCkyebd
     * @return
     */
    @AutoLog(value = "存款余额变动查询-添加")
    @ApiOperation(value = "存款余额变动查询-添加", notes = "存款余额变动查询-添加")
    @PostMapping(value = "/add")
    public Result<CkjkptCkyebd> add(@RequestBody CkjkptCkyebd ckjkptCkyebd) {
        Result<CkjkptCkyebd> result = new Result<CkjkptCkyebd>();
        try {
            ckjkptCkyebdService.save(ckjkptCkyebd);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }

    /**
     *  编辑
     * @param ckjkptCkyebd
     * @return
     *//*
	@AutoLog(value = "存款余额变动查询-编辑")
	@ApiOperation(value="存款余额变动查询-编辑", notes="存款余额变动查询-编辑")
	@PutMapping(value = "/edit")
	public Result<CkjkptCkyebd> edit(@RequestBody CkjkptCkyebd ckjkptCkyebd) {
		Result<CkjkptCkyebd> result = new Result<CkjkptCkyebd>();
		CkjkptCkyebd ckjkptCkyebdEntity = ckjkptCkyebdService.getById(ckjkptCkyebd.getId());
		if(ckjkptCkyebdEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = ckjkptCkyebdService.updateById(ckjkptCkyebd);
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
    @AutoLog(value = "存款余额变动查询-通过id删除")
    @ApiOperation(value = "存款余额变动查询-通过id删除", notes = "存款余额变动查询-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        try {
            ckjkptCkyebdService.removeById(id);
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
    @AutoLog(value = "存款余额变动查询-批量删除")
    @ApiOperation(value = "存款余额变动查询-批量删除", notes = "存款余额变动查询-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<CkjkptCkyebd> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<CkjkptCkyebd> result = new Result<CkjkptCkyebd>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.ckjkptCkyebdService.removeByIds(Arrays.asList(ids.split(",")));
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
    @AutoLog(value = "存款余额变动查询-通过id查询")
    @ApiOperation(value = "存款余额变动查询-通过id查询", notes = "存款余额变动查询-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<CkjkptCkyebd> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<CkjkptCkyebd> result = new Result<CkjkptCkyebd>();
        CkjkptCkyebd ckjkptCkyebd = ckjkptCkyebdService.getById(id);
        if (ckjkptCkyebd == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(ckjkptCkyebd);
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
        QueryWrapper<CkjkptCkyebd> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                CkjkptCkyebd ckjkptCkyebd = JSON.parseObject(deString, CkjkptCkyebd.class);
                queryWrapper = QueryGenerator.initQueryWrapper(ckjkptCkyebd, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<CkjkptCkyebd> pageList = ckjkptCkyebdService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "存款余额变动列表");
        mv.addObject(NormalExcelConstants.CLASS, CkjkptCkyebd.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("存款余额变动列表数据", "导出人:"+sysUser.getRealname(), "导出信息"));
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
                List<CkjkptCkyebd> listCkjkptCkyebds = ExcelImportUtil.importExcel(file.getInputStream(), CkjkptCkyebd.class, params);
                ckjkptCkyebdService.saveBatch(listCkjkptCkyebds);
                return Result.ok("文件导入成功！数据行数:" + listCkjkptCkyebds.size());
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
	 * 客户存款余额变动-提取
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/extract")
    public Result<?> extract(@RequestBody JSONObject json) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
        String beginDate = json.getString("ksrq").replace("-","");
		String endDate = json.getString("jsrq").replace("-","");
        Result result = new Result<>();
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            HashMap<String, String> parm = new HashMap<>();
            String lv_ye_up = ckjkptCsglService.queryParamValue("CS0009");
            String lv_ye_down = ckjkptCsglService.queryParamValue("CS0010");
            parm.put("lv_ye_up", lv_ye_up);
            parm.put("lv_ye_down", lv_ye_down);
            parm.put("i_day_b", beginDate);
            parm.put("i_day_e", endDate);
            parm.put("etl_task","kiss.domain.application.ckjkpt.proc_ckjkpt_khdebdjc");
            // count_ckjkpt_khdebdjc
            boolean falg = EtlUtil.callEtl("ckjkpt_common_init", parm, 15);
            result.setSuccess(falg);
        } else {
            try {
                Map<String, String> parm = new HashMap<String, String>();
                String ksrq = json.getString("ksrq").replace("-", "");
                String jsrq = json.getString("jsrq").replace("-", "");
                parm.put("ksrq", ksrq);
                parm.put("jsrq", jsrq);
                ckjkptCkyebdService.extract(parm);
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

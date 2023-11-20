package org.cmms.modules.ckjkpt.jcyj.debdcx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
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
import org.cmms.modules.ckjkpt.jcyj.debdcx.entity.CkjkptDebdcx;
import org.cmms.modules.ckjkpt.jcyj.debdcx.mapper.CkjkptDebdcxMapper;
import org.cmms.modules.ckjkpt.jcyj.debdcx.service.ICkjkptDebdcxService;
import org.cmms.modules.ckjkpt.util.mapper.CkjkptCallMapper;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.PageUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 大额变动查询
 * @Author: cmms
 * @Date: 2019-10-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "大额变动查询")
@RestController
@RequestMapping("/CkjkptDebdcx/ckjkptDebdcx")
public class CkjkptDebdcxController {
    @Autowired
    private ICkjkptDebdcxService ckjkptDebdcxService;
    @Autowired
    private ITjfxZhbyService tjfxZhbyService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ICkjkptCsglService ckjkptCsglService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param ckjkptDebdcx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "大额变动查询-分页列表查询")
    @ApiOperation(value = "大额变动查询-分页列表查询", notes = "大额变动查询-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CkjkptDebdcx ckjkptDebdcx,
                                                     @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                     HttpServletRequest req) {
//        QueryWrapper<CkjkptDebdcx> queryWrapper = QueryGenerator.initQueryWrapper(ckjkptDebdcx, req.getParameterMap());
//        queryWrapper.orderByDesc("tjyf").orderByDesc("jgdm").orderByDesc("zjhm");
//        List<CkjkptDebdcx> list = ckjkptDebdcxService.list(queryWrapper);
//        IPage<CkjkptDebdcx> pageList = PageUtil.getPages(list, list == null ? 0 : list.size(), pageSize, pageNo);
//        return Result.ok(pageList);
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(ckjkptDebdcx, req.getParameterMap());
        queryWrapper.isNotNull("jgdm");
        queryWrapper.isNotNull("khxm");
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkjkptDebdcxService.class,ckjkptDebdcxService,pageNo,pageSize,queryWrapper,"tjrq","zjhm","jgdm");
        return Result.ok(pageList);
    }

    /**
     * 分页列表查询
     *
     * @param ckjkptDebdcx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "大额变动查询-分页列表查询")
    @ApiOperation(value = "大额变动查询-分页列表查询", notes = "大额变动查询-分页列表查询")
    @GetMapping(value = "/ipadlist")
    public Result<?> queryIpadList(CkjkptDebdcx ckjkptDebdcx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<CkjkptDebdcx>> result = new Result<IPage<CkjkptDebdcx>>();
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        ckjkptDebdcx.setJgdm(sysUser.getOrgCode() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization", "ywjgdm", "zzbz", sysUser.getOrgCode()));
        QueryWrapper<CkjkptDebdcx> queryWrapper = QueryGenerator.initQueryWrapper(ckjkptDebdcx, req.getParameterMap());
        Page<CkjkptDebdcx> page = new Page<CkjkptDebdcx>(pageNo, pageSize);
        IPage<CkjkptDebdcx> pageList = ckjkptDebdcxService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param ckjkptDebdcx
     * @return
     */
    @AutoLog(value = "大额变动查询-添加")
    @ApiOperation(value = "大额变动查询-添加", notes = "大额变动查询-添加")
    @PostMapping(value = "/add")
    public Result<CkjkptDebdcx> add(@RequestBody CkjkptDebdcx ckjkptDebdcx) {
        Result<CkjkptDebdcx> result = new Result<CkjkptDebdcx>();
        try {
            ckjkptDebdcxService.save(ckjkptDebdcx);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }

    /**
     *  编辑
     * @param ckjkptDebdcx
     * @return
     *//*
	@AutoLog(value = "大额变动查询-编辑")
	@ApiOperation(value="大额变动查询-编辑", notes="大额变动查询-编辑")
	@PutMapping(value = "/edit")
	public Result<CkjkptDebdcx> edit(@RequestBody CkjkptDebdcx ckjkptDebdcx) {
		Result<CkjkptDebdcx> result = new Result<CkjkptDebdcx>();
		CkjkptDebdcx ckjkptDebdcxEntity = ckjkptDebdcxService.getById(ckjkptDebdcx.getId());
		if(ckjkptDebdcxEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = ckjkptDebdcxService.updateById(ckjkptDebdcx);
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
    @AutoLog(value = "大额变动查询-通过id删除")
    @ApiOperation(value = "大额变动查询-通过id删除", notes = "大额变动查询-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        try {
            ckjkptDebdcxService.removeById(id);
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
    @AutoLog(value = "大额变动查询-批量删除")
    @ApiOperation(value = "大额变动查询-批量删除", notes = "大额变动查询-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<CkjkptDebdcx> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<CkjkptDebdcx> result = new Result<CkjkptDebdcx>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.ckjkptDebdcxService.removeByIds(Arrays.asList(ids.split(",")));
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
    @AutoLog(value = "大额变动查询-通过id查询")
    @ApiOperation(value = "大额变动查询-通过id查询", notes = "大额变动查询-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<CkjkptDebdcx> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<CkjkptDebdcx> result = new Result<CkjkptDebdcx>();
        CkjkptDebdcx ckjkptDebdcx = ckjkptDebdcxService.getById(id);
        if (ckjkptDebdcx == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(ckjkptDebdcx);
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
        QueryWrapper<CkjkptDebdcx> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                CkjkptDebdcx ckjkptDebdcx = JSON.parseObject(deString, CkjkptDebdcx.class);
                queryWrapper = QueryGenerator.initQueryWrapper(ckjkptDebdcx, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        queryWrapper.orderByDesc("zxjl");
        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<CkjkptDebdcx> pageList = ckjkptDebdcxService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "大额变动查询列表");
        mv.addObject(NormalExcelConstants.CLASS, CkjkptDebdcx.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("大额变动查询列表数据", "导出人:"+sysUser.getRealname(), "导出信息"));
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
                List<CkjkptDebdcx> listCkjkptDebdcxs = ExcelImportUtil.importExcel(file.getInputStream(), CkjkptDebdcx.class, params);
                ckjkptDebdcxService.saveBatch(listCkjkptDebdcxs);
                return Result.ok("文件导入成功！数据行数:" + listCkjkptDebdcxs.size());
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
	 * 大额变动查询-提取
	 * @param jsonObject
	 * @return
	 */
    @RequestMapping(value = "/extract")
    public Result<?> extract(@RequestBody JSONObject jsonObject) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
        String tjrq = jsonObject.getString("jyrq").replace("-","");
        Result result = new Result<>();
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            HashMap<String, String> params = new HashMap<>();
            String lv_xjljc = ckjkptCsglService.queryParamValue("CS0009");
            params.put("iv_day", tjrq);
            params.put("lv_xjljc", lv_xjljc);
            params.put("etl_task","kiss.domain.application.ckjkpt.proc_ckjkpt_khjylscx");
            // count_ckjkpt_khjylscx
            boolean falg = EtlUtil.callEtl("ckjkpt_common_init", params, 15);
            result.setSuccess(falg);
        } else {
            try {
                ckjkptDebdcxService.extract(tjrq);
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

    @RequestMapping(value = "ipdextract", method = RequestMethod.GET)
    public Result<?> ipdextract(@RequestParam(name = "tjrq") String tjrq) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());

        String[] tjyf = tjrq.split("-");
        String rq = tjyf[0] + tjyf[1] + tjyf[2];
        Result result = new Result<>();
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            HashMap<String, String> params = new HashMap<>();
            String lv_xjljc = ckjkptCsglService.queryParamValue("CS0009");
            params.put("iv_day", rq);
            params.put("lv_xjljc", lv_xjljc);
            params.put("etl_task","kiss.domain.application.ckjkpt.proc_ckjkpt_khjylscx");
            // count_ckjkpt_khjylscx
            boolean falg = EtlUtil.callEtl("ckjkpt_common_init", params, 15);
            result.setSuccess(falg);
        } else {
            try {
                ckjkptDebdcxService.extract(rq);
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

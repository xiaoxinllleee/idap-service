package org.cmms.modules.xyjlcx.jcsjgl.wdjxzhgl.controller;

import java.sql.Timestamp;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.xyjlcx.jcsjgl.wdjxzhgl.entity.Wdjxzhgl;
import org.cmms.modules.xyjlcx.jcsjgl.wdjxzhgl.entity.WdjxzhglVO;
import org.cmms.modules.xyjlcx.jcsjgl.wdjxzhgl.service.IWdjxzhglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.xyjlcx.jcsjgl.wdjxzhgl.verify.WdjxzhglImportVerify;
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
 * @Description: 网点计息账号管理
 * @Author: jeecg-boot
 * @Date: 2021-08-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "网点计息账号管理")
@RestController
@RequestMapping("/wdjxzhgl/wdjxzhgl")
public class WdjxzhglController extends JeecgController<Wdjxzhgl, IWdjxzhglService> {
	@Autowired
	private IWdjxzhglService wdjxzhglService;
	@Autowired
	private WdjxzhglImportVerify wdjxzhglImportVerify;
	@Value("${com.etl.sfdsjpt}")
	private String sfdsjpt;

	/**
	 * 分页列表查询
	 * @param wdjxzhgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "网点计息账号管理-分页列表查询")
	@ApiOperation(value = "网点计息账号管理-分页列表查询", notes = "网点计息账号管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Wdjxzhgl wdjxzhgl,
								   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
								   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
								   HttpServletRequest req) {
		Result<IPage<Wdjxzhgl>> result = new Result<IPage<Wdjxzhgl>>();
		QueryWrapper<Wdjxzhgl> queryWrapper = QueryGenerator.initQueryWrapper(wdjxzhgl, req.getParameterMap());
		IPage pageList = org.cmms.common.utils.PageUtil.toPage(
				IWdjxzhglService.class,
				wdjxzhglService,
				pageNo,
				pageSize,
				queryWrapper,
				"jgdm");
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	 * 网点计息账号管理 / 添加
	 *
	 * @param wdjxzhgl
	 * @return
	 */
	@AutoLog(value = "网点计息账号管理-添加")
	@ApiOperation(value = "网点计息账号管理-添加", notes = "网点计息账号管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Wdjxzhgl wdjxzhgl) {
		try {
			QueryWrapper<Wdjxzhgl> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("jgdm", wdjxzhgl.getJgdm());
			Wdjxzhgl check = wdjxzhglService.getOne(queryWrapper);
			if (check != null) {
				return Result.error("该网点已存在！");
			}
			wdjxzhgl.setLrbz(1);
			wdjxzhgl.setLrr(getLoginUser().getRealname());
			wdjxzhgl.setLrsj(new Timestamp(System.currentTimeMillis()));
			wdjxzhglService.save(wdjxzhgl);
			return Result.ok("添加成功！");
		} catch (Throwable tx) {
			log.error("信用记录查询 / 网点计息账号管理 / 添加失败！", tx);
			return Result.error("添加失败！" + tx.getMessage());
		}
	}

	/**
	 * 网点计息账号管理 / 编辑
	 *
	 * @param wdjxzhgl
	 * @return
	 */
	@AutoLog(value = "网点计息账号管理-编辑")
	@ApiOperation(value = "网点计息账号管理-编辑", notes = "网点计息账号管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Wdjxzhgl wdjxzhgl) {
		try {
			QueryWrapper<Wdjxzhgl> queryWrapper = new QueryWrapper<Wdjxzhgl>();
			queryWrapper.eq("jgdm", wdjxzhgl.getJgdm());
			//表主键不能修改（Kudu）
			wdjxzhgl.setJgdm(null);
			wdjxzhgl.setLrbz(2);
			wdjxzhgl.setLrr(getLoginUser().getRealname());
			wdjxzhgl.setLrsj(new Timestamp(System.currentTimeMillis()));
			wdjxzhglService.update(wdjxzhgl, queryWrapper);
			return Result.ok("编辑成功！");
		} catch (Throwable tx) {
			log.error("信用记录查询 / 网点计息账号管理 / 编辑失败！", tx);
			return Result.error("编辑失败！" + tx.getMessage());
		}
	}

	/**
	 * 网点计息账号管理 / 删除
	 * @param
	 * @return
	 */
	@AutoLog(value = "网点计息账号管理-删除")
	@ApiOperation(value = "网点计息账号管理-删除", notes = "网点计息账号管理-删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("jgdm") String jgdm) {
		try {
			QueryWrapper<Wdjxzhgl> queryWrapper = new QueryWrapper<Wdjxzhgl>();
			queryWrapper.eq("jgdm", jgdm);
			wdjxzhglService.remove(queryWrapper);
			return Result.ok("删除成功！");
		} catch (Throwable tx) {
			log.error("信用记录查询 / 网点计息账号管理 / 删除失败！", tx.getMessage());
			return Result.error("删除失败！" + tx.getMessage());
		}
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param wdjxzhgl
	 */
	@RequestMapping(value = "/exportXls")
	public ModelAndView exportXls(HttpServletRequest request, Wdjxzhgl wdjxzhgl) {
		return super.exportXls(request, wdjxzhgl, Wdjxzhgl.class, "网点计息账号管理");
	}

	/**
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
		modelAndView.addObject(NormalExcelConstants.FILE_NAME, "网点计息账号管理导入模板");
		modelAndView.addObject(NormalExcelConstants.CLASS, WdjxzhglVO.class);
		ExportParams exportParams = new ExportParams("网点计息账号管理导入模板", "模板信息");
		modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
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
	public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		return super.importExcelByTemplate(jsonObject, request, response, Wdjxzhgl.class,WdjxzhglVO.class, wdjxzhglImportVerify);
	}

}

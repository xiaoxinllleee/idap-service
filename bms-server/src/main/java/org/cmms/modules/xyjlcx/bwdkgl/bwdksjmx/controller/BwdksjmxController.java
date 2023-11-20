package org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.controller;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.EtlUtilOld;
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.entity.Bwdksjmx;
import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.entity.BwdksjmxVO;
import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.mapper.BwdksjmxMapper;
import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.service.IBwdksjmxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.verify.BwdksjmxImportVerify;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 表外贷款数据明细
 * @Author: jeecg-boot
 * @Date:   2021-08-13
 * @Version: V1.0
 */
@Slf4j
@Api(tags="表外贷款数据明细")
@RestController
@RequestMapping("/bwdksjmx/bwdksjmx")
public class BwdksjmxController extends JeecgController<Bwdksjmx, IBwdksjmxService> {
	 @Autowired
	 private IBwdksjmxService bwdksjmxService;
	 @Autowired
	 private ISysDictService iSysDictService;
	 @Autowired
	 private BwdksjmxImportVerify bwdksjmxImportVerify;
	 @Value("${com.etl.sfdsjpt}")
	 private String sfdsjpt;

	 /**
	  * 分页列表查询
	  *
	  * @param bwdksjmx
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "表外贷款数据明细-分页列表查询")
	 @ApiOperation(value = "表外贷款数据明细-分页列表查询", notes = "表外贷款数据明细-分页列表查询")
	 @GetMapping(value = "/list")
	 public Result<?> queryPageList(Bwdksjmx bwdksjmx,
									@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
									@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
									HttpServletRequest req) {
		 Result<IPage<Bwdksjmx>> result = new Result<IPage<Bwdksjmx>>();
		 QueryWrapper<Bwdksjmx> queryWrapper = QueryGenerator.initQueryWrapper(bwdksjmx, req.getParameterMap());
		 IPage pageList = org.cmms.common.utils.PageUtil.toPage(IBwdksjmxService.class, bwdksjmxService, pageNo, pageSize, queryWrapper, "dkzh");
		 result.setSuccess(true);
		 result.setResult(pageList);
		 return result;
	 }

	 /**
	  * 表外贷款数据明细 / 提取
	  *
	  * @return
	  */
	 @AutoLog(value = "表外贷款数据明细-提取")
	 @ApiOperation(value = "表外贷款数据明细-提取", notes = "表外贷款数据明细-提取")
	 @RequestMapping(value = "/init")
	 public Result<?> init() {
		 Result result = new Result<>();
		 if ("true".equalsIgnoreCase(sfdsjpt)) {
			 // `参数1`:`任务调用code`，`参数2`:`ETL调度存储过程参数值`，`参数3`:`ETL任务预计执行时间(可根据实际执行时间酌情延长)`
			 HashMap<String, String> params = new HashMap<>();
			 params.put("etl_task","kiss.domain.application.zx.proc_credit_bwdksjmx");
			 boolean completionSignal = EtlUtil.callEtl("zx_common_init", params, 20);
			 result.setSuccess(completionSignal);
		 } else {
			 try {
				 bwdksjmxService.pBwdksjmx();
			 } catch (Throwable e) {
				 log.error("信用记录查询 / 表外贷款数据明细 / 提取失败！"+e.getMessage());
				 result.setSuccess(false);
			 }
		 }
		 return result;
	 }

	/**
	 * 表外贷款数据明细 / 添加
	 *
	 * @param bwdksjmx
	 * @return
	 */
	@AutoLog(value = "表外贷款数据明细-添加")
	@ApiOperation(value="表外贷款数据明细-添加", notes="表外贷款数据明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Bwdksjmx bwdksjmx) {
		bwdksjmx.setLrbz(1);
		bwdksjmx.setLrr(getLoginUser().getUsername());
		bwdksjmx.setLrsj(new Timestamp(System.currentTimeMillis()));
		bwdksjmxService.save(bwdksjmx);
		return Result.ok("添加成功！");
	}

	/**
	 * 表外贷款数据明细 / 编辑
	 *
	 * @param bwdksjmx
	 * @return
	 */
	@AutoLog(value = "表外贷款数据明细-编辑")
	@ApiOperation(value="表外贷款数据明细-编辑", notes="表外贷款数据明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Bwdksjmx bwdksjmx) {
		try {
			QueryWrapper<Bwdksjmx> queryWrapper = new QueryWrapper<Bwdksjmx>();
			queryWrapper.eq("dkzh",bwdksjmx.getDkzh());
			//表主键不能更新
			bwdksjmx.setDkzh(null);
			bwdksjmx.setLrbz(2);
			bwdksjmx.setLrr(getLoginUser().getUsername());
			bwdksjmx.setLrsj(new Timestamp(System.currentTimeMillis()));
			bwdksjmxService.update(bwdksjmx,queryWrapper);
			return Result.ok("编辑成功!");
		} catch (Throwable throwable) {
			log.error("信用记录查询 / 表外贷款数据明细 / 编辑失败！"+throwable.getMessage());
			return Result.error("编辑失败!");
		}
	}

	/**
	 * 表外贷款数据明细 / 删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "表外贷款数据明细-删除")
	@ApiOperation(value="表外贷款数据明细-删除", notes="表外贷款数据明细-删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("dkzh") String dkzh) {
		QueryWrapper<Bwdksjmx> queryWrapper = new QueryWrapper<Bwdksjmx>();
		queryWrapper.eq("dkzh",dkzh);
		bwdksjmxService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	 /**
	  * 导出excel
	  *
	  * @param request
	  * @param bwdksjmx
	  */
	 @RequestMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, Bwdksjmx bwdksjmx) {
		 return super.exportXls(request, bwdksjmx, Bwdksjmx.class, "表外贷款数据明细");
	 }

	 /**
	  * 导出模板
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "表外贷款数据明细导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, BwdksjmxVO.class);
		 ExportParams exportParams = new ExportParams("表外贷款数据明细导入模板", "模板信息");
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
		 return super.importExcelByTemplate(jsonObject, request, response, Bwdksjmx.class,BwdksjmxVO.class, bwdksjmxImportVerify);
	 }

}

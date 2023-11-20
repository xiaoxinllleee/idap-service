package org.cmms.modules.khxxgl.wbsjgl.tpjchmd.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.wbsjgl.tpjchmd.entity.Tpjchmd;
import org.cmms.modules.khxxgl.wbsjgl.tpjchmd.service.ITpjchmdService;
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
 * @Description: 脱贫户及监测户名单
 * @Author: jeecg-boot
 * @Date:   2022-02-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="脱贫户及监测户名单")
@RestController
@RequestMapping("/wbsjgl/tpjchmd")
public class TpjchmdController extends JeecgController<Tpjchmd, ITpjchmdService> {
	@Autowired
	private ITpjchmdService tpjchmdService;
	 @Autowired
	 private INhxqService nhxqService;
	/**
	 * 分页列表查询
	 *
	 * @param tpjchmd
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "脱贫户及监测户名单-分页列表查询")
	@ApiOperation(value="脱贫户及监测户名单-分页列表查询", notes="脱贫户及监测户名单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Tpjchmd tpjchmd,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Tpjchmd> queryWrapper = QueryGenerator.initQueryWrapper(tpjchmd, req.getParameterMap());
		Page<Tpjchmd> page = new Page<Tpjchmd>(pageNo, pageSize);
		IPage<Tpjchmd> pageList = tpjchmdService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param tpjchmd
	 * @return
	 */
	@AutoLog(value = "脱贫户及监测户名单-添加")
	@ApiOperation(value="脱贫户及监测户名单-添加", notes="脱贫户及监测户名单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Tpjchmd tpjchmd) {
		tpjchmdService.save(tpjchmd);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param tpjchmd
	 * @return
	 */
	@AutoLog(value = "脱贫户及监测户名单-编辑")
	@ApiOperation(value="脱贫户及监测户名单-编辑", notes="脱贫户及监测户名单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Tpjchmd tpjchmd) {
		tpjchmdService.updateById(tpjchmd);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "脱贫户及监测户名单-通过id删除")
	@ApiOperation(value="脱贫户及监测户名单-通过id删除", notes="脱贫户及监测户名单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tpjchmdService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "脱贫户及监测户名单-批量删除")
	@ApiOperation(value="脱贫户及监测户名单-批量删除", notes="脱贫户及监测户名单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tpjchmdService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "脱贫户及监测户名单-通过id查询")
	@ApiOperation(value="脱贫户及监测户名单-通过id查询", notes="脱贫户及监测户名单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Tpjchmd tpjchmd = tpjchmdService.getById(id);
		return Result.ok(tpjchmd);
	}

	 /**
	  * 通过zjhm查询
	  *
	  * @param zjhm
	  * @return
	  */
	 @AutoLog(value = "脱贫户及监测户名单-通过zjhm查询")
	 @ApiOperation(value="脱贫户及监测户名单-通过zjhm查询", notes="脱贫户及监测户名单-通过zjhm查询")
	 @GetMapping(value = "/queryByZjhm")
	 public Result<?> queryByZjhm(@RequestParam(name="zjhm",required=true) String zjhm) {
	 	 QueryWrapper<Tpjchmd> queryWrapper = new QueryWrapper<>();
	 	 queryWrapper.eq("zjhm", zjhm);
	 	 List<Tpjchmd> tpjchmdList = tpjchmdService.list(queryWrapper);
		 return Result.ok(tpjchmdList);
	 }

	 /**
	  * 通过khid查询
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "脱贫户及监测户名单-通过Khid查询")
	 @ApiOperation(value="脱贫户及监测户名单-通过Khid查询", notes="脱贫户及监测户名单-通过Khid查询")
	 @GetMapping(value = "/queryByKhid")
	 public Result<?> queryByKhid(@RequestParam(name="id",required=true) String id) {
	 	 //先根据客户id查询客户证件号码
		 QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
		 nhxqQueryWrapper.eq("id", id);
		 Nhxq nhxq = nhxqService.getOne(nhxqQueryWrapper,false);
		 if (nhxq == null) {
		 	return Result.error("未找到客户信息");
		 }
		 QueryWrapper<Tpjchmd> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("zjhm", nhxq.getZjhm());
		 List<Tpjchmd> tpjchmdList = tpjchmdService.list(queryWrapper);
		 return Result.ok(tpjchmdList);
	 }

  /**
   * 导出excel
   *
   * @param request
   * @param tpjchmd
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Tpjchmd tpjchmd) {
      return super.exportXls(request, tpjchmd, Tpjchmd.class, "脱贫户及监测户名单");
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
		 return super.importExcelByTemplate(jsonObject, request, response, Tpjchmd.class, null);
	 }

	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 return super.exportTemplateXls(Tpjchmd.class, "脱贫户及监测户名单导入模板");
	 }

}

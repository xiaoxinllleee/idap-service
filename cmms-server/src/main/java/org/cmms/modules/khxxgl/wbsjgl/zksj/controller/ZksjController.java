package org.cmms.modules.khxxgl.wbsjgl.zksj.controller;

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
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.wbsjgl.zksj.entity.Zksj;
import org.cmms.modules.khxxgl.wbsjgl.zksj.service.IZksjService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
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
 * @Description: 制卡数据
 * @Author: jeecg-boot
 * @Date:   2022-02-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="制卡数据")
@RestController
@RequestMapping("/wbsjgl/zksj")
public class ZksjController extends JeecgController<Zksj, IZksjService> {
	@Autowired
	private IZksjService zksjService;
	@Autowired
	private INhxqService nhxqService;
	/**
	 * 分页列表查询
	 *
	 * @param zksj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "制卡数据-分页列表查询")
	@ApiOperation(value="制卡数据-分页列表查询", notes="制卡数据-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zksj zksj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zksj> queryWrapper = QueryGenerator.initQueryWrapper(zksj, req.getParameterMap());
		Page<Zksj> page = new Page<Zksj>(pageNo, pageSize);
		IPage<Zksj> pageList = zksjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param zksj
	 * @return
	 */
	@AutoLog(value = "制卡数据-添加")
	@ApiOperation(value="制卡数据-添加", notes="制卡数据-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zksj zksj) {
		zksjService.save(zksj);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param zksj
	 * @return
	 */
	@AutoLog(value = "制卡数据-编辑")
	@ApiOperation(value="制卡数据-编辑", notes="制卡数据-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zksj zksj) {
		zksjService.updateById(zksj);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "制卡数据-通过id删除")
	@ApiOperation(value="制卡数据-通过id删除", notes="制卡数据-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zksjService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "制卡数据-批量删除")
	@ApiOperation(value="制卡数据-批量删除", notes="制卡数据-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zksjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "制卡数据-通过id查询")
	@ApiOperation(value="制卡数据-通过id查询", notes="制卡数据-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zksj zksj = zksjService.getById(id);
		return Result.ok(zksj);
	}

	 /**
	  * 通过zjhm查询
	  *
	  * @param zjhm
	  * @return
	  */
	 @AutoLog(value = "制卡数据-通过zjhm查询")
	 @ApiOperation(value="制卡数据-通过zjhm查询", notes="制卡数据-通过zjhm查询")
	 @GetMapping(value = "/queryByZjhm")
	 public Result<?> queryByZjhm(@RequestParam(name="zjhm",required=true) String zjhm) {
	 	 QueryWrapper<Zksj> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("zjhm", zjhm);
		 List<Zksj> zksjList = zksjService.list(queryWrapper);
		 return Result.ok(zksjList);
	 }

	 /**
	  * 通过khid查询
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "制卡数据-通过Khid查询")
	 @ApiOperation(value="制卡数据-通过Khid查询", notes="制卡数据-通过Khid查询")
	 @GetMapping(value = "/queryByKhid")
	 public Result<?> queryByKhid(@RequestParam(name="id",required=true) String id) {
		 //先根据客户id查询客户证件号码
		 QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
		 nhxqQueryWrapper.eq("id", id);
		 Nhxq nhxq = nhxqService.getOne(nhxqQueryWrapper,false);
		 if (nhxq == null) {
			 return Result.error("未找到客户信息");
		 }
		 QueryWrapper<Zksj> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("zjhm", nhxq.getZjhm());
		 List<Zksj> zksjList = zksjService.list(queryWrapper);
		 return Result.ok(zksjList);
	 }

  /**
   * 导出excel
   *
   * @param request
   * @param zksj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zksj zksj) {
      return super.exportXls(request, zksj, Zksj.class, "制卡数据");
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
		 return super.importExcelByTemplate(jsonObject, request, response, Zksj.class, null);
	 }

	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 return super.exportTemplateXls(Zksj.class, "制卡数据导入模板");
	 }

}

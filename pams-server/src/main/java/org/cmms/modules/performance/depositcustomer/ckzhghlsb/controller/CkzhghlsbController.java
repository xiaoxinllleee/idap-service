package org.cmms.modules.performance.depositcustomer.ckzhghlsb.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.cmms.modules.performance.depositcustomer.ckzhghlsb.entity.Ckzhghlsb;
import org.cmms.modules.performance.depositcustomer.ckzhghlsb.service.ICkzhghlsbService;
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
 * @Description: 存款账号管户历史表
 * @Author: jeecg-boot
 * @Date:   2023-04-20
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款账号管户历史表")
@RestController
@RequestMapping("/ckzhghlsb/ckzhghlsb")
public class CkzhghlsbController extends JeecgController<Ckzhghlsb, ICkzhghlsbService> {
	@Autowired
	private ICkzhghlsbService ckzhghlsbService;
	
	/**
	 * 分页列表查询
	 *
	 * @param ckzhghlsb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存款账号管户历史表-分页列表查询")
	@ApiOperation(value="存款账号管户历史表-分页列表查询", notes="存款账号管户历史表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ckzhghlsb ckzhghlsb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Ckzhghlsb> queryWrapper = QueryGenerator.initQueryWrapper(ckzhghlsb, req.getParameterMap());
		Page<Ckzhghlsb> page = new Page<Ckzhghlsb>(pageNo, pageSize);
		IPage<Ckzhghlsb> pageList = ckzhghlsbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 @GetMapping(value = "/getListByCkzh")
	 public Result<?> getListByKhbh(String jgdm, String khbh, String ckzh) {
		 QueryWrapper<Ckzhghlsb> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("jgdm", jgdm);
		 queryWrapper.eq("khbh", khbh);
		 queryWrapper.eq("ckzh", ckzh);
		 queryWrapper.orderByDesc("jsrq");
		 List<Ckzhghlsb> list = ckzhghlsbService.list(queryWrapper);
		 return Result.ok(list);
	 }
	
	/**
	 * 添加
	 *
	 * @param ckzhghlsb
	 * @return
	 */
	@AutoLog(value = "存款账号管户历史表-添加")
	@ApiOperation(value="存款账号管户历史表-添加", notes="存款账号管户历史表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ckzhghlsb ckzhghlsb) {
		ckzhghlsbService.save(ckzhghlsb);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param ckzhghlsb
	 * @return
	 */
	@AutoLog(value = "存款账号管户历史表-编辑")
	@ApiOperation(value="存款账号管户历史表-编辑", notes="存款账号管户历史表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ckzhghlsb ckzhghlsb) {
		ckzhghlsbService.updateById(ckzhghlsb);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款账号管户历史表-通过id删除")
	@ApiOperation(value="存款账号管户历史表-通过id删除", notes="存款账号管户历史表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ckzhghlsbService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "存款账号管户历史表-批量删除")
	@ApiOperation(value="存款账号管户历史表-批量删除", notes="存款账号管户历史表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckzhghlsbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款账号管户历史表-通过id查询")
	@ApiOperation(value="存款账号管户历史表-通过id查询", notes="存款账号管户历史表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ckzhghlsb ckzhghlsb = ckzhghlsbService.getById(id);
		return Result.ok(ckzhghlsb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ckzhghlsb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ckzhghlsb ckzhghlsb) {
      return super.exportXls(request, ckzhghlsb, Ckzhghlsb.class, "存款账号管户历史表");
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
      return super.importExcel(request, response, Ckzhghlsb.class);
  }

}

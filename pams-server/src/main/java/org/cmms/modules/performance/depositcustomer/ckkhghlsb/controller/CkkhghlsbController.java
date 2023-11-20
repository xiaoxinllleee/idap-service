package org.cmms.modules.performance.depositcustomer.ckkhghlsb.controller;

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
import org.cmms.modules.performance.depositcustomer.ckkhghlsb.entity.Ckkhghlsb;
import org.cmms.modules.performance.depositcustomer.ckkhghlsb.service.ICkkhghlsbService;
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
 * @Description: 存款客户管户历史表
 * @Author: jeecg-boot
 * @Date:   2023-03-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款客户管户历史表")
@RestController
@RequestMapping("/ckkhghlsb/ckkhghlsb")
public class CkkhghlsbController extends JeecgController<Ckkhghlsb, ICkkhghlsbService> {
	@Autowired
	private ICkkhghlsbService ckkhghlsbService;
	
	/**
	 * 分页列表查询
	 *
	 * @param ckkhghlsb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存款客户管户历史表-分页列表查询")
	@ApiOperation(value="存款客户管户历史表-分页列表查询", notes="存款客户管户历史表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ckkhghlsb ckkhghlsb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Ckkhghlsb> queryWrapper = QueryGenerator.initQueryWrapper(ckkhghlsb, req.getParameterMap());
		Page<Ckkhghlsb> page = new Page<Ckkhghlsb>(pageNo, pageSize);
		IPage<Ckkhghlsb> pageList = ckkhghlsbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 @GetMapping(value = "/getListByKhbh")
	 public Result<?> getListByKhbh(String jgdm, String khbh) {
		 QueryWrapper<Ckkhghlsb> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("jgdm", jgdm);
		 queryWrapper.eq("khbh", khbh);
		 queryWrapper.orderByDesc("jsrq");
		 List<Ckkhghlsb> list = ckkhghlsbService.list(queryWrapper);
		 return Result.ok(list);
	 }

	/**
	 * 添加
	 *
	 * @param ckkhghlsb
	 * @return
	 */
	@AutoLog(value = "存款客户管户历史表-添加")
	@ApiOperation(value="存款客户管户历史表-添加", notes="存款客户管户历史表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ckkhghlsb ckkhghlsb) {
		ckkhghlsbService.save(ckkhghlsb);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param ckkhghlsb
	 * @return
	 */
	@AutoLog(value = "存款客户管户历史表-编辑")
	@ApiOperation(value="存款客户管户历史表-编辑", notes="存款客户管户历史表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ckkhghlsb ckkhghlsb) {
		ckkhghlsbService.updateById(ckkhghlsb);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款客户管户历史表-通过id删除")
	@ApiOperation(value="存款客户管户历史表-通过id删除", notes="存款客户管户历史表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ckkhghlsbService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "存款客户管户历史表-批量删除")
	@ApiOperation(value="存款客户管户历史表-批量删除", notes="存款客户管户历史表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckkhghlsbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款客户管户历史表-通过id查询")
	@ApiOperation(value="存款客户管户历史表-通过id查询", notes="存款客户管户历史表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ckkhghlsb ckkhghlsb = ckkhghlsbService.getById(id);
		return Result.ok(ckkhghlsb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ckkhghlsb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ckkhghlsb ckkhghlsb) {
      return super.exportXls(request, ckkhghlsb, Ckkhghlsb.class, "存款客户管户历史表");
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
      return super.importExcel(request, response, Ckkhghlsb.class);
  }

}

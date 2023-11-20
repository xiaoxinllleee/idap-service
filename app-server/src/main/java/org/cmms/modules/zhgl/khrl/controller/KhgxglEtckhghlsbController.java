package org.cmms.modules.zhgl.khrl.controller;

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
import org.cmms.modules.zhgl.khrl.entity.KhgxglEtckhghlsb;
import org.cmms.modules.zhgl.khrl.service.IKhgxglEtckhghlsbService;
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
 * @Description: ETC客户管户历史表
 * @Author: jeecg-boot
 * @Date:   2022-03-21
 * @Version: V1.0
 */
@Slf4j
@Api(tags="ETC客户管户历史表")
@RestController
@RequestMapping("/khrl/khgxglEtckhghlsb")
public class KhgxglEtckhghlsbController extends JeecgController<KhgxglEtckhghlsb, IKhgxglEtckhghlsbService> {
	@Autowired
	private IKhgxglEtckhghlsbService khgxglEtckhghlsbService;
	
	/**
	 * 分页列表查询
	 *
	 * @param khgxglEtckhghlsb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "ETC客户管户历史表-分页列表查询")
	@ApiOperation(value="ETC客户管户历史表-分页列表查询", notes="ETC客户管户历史表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhgxglEtckhghlsb khgxglEtckhghlsb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<KhgxglEtckhghlsb> queryWrapper = QueryGenerator.initQueryWrapper(khgxglEtckhghlsb, req.getParameterMap());
		Page<KhgxglEtckhghlsb> page = new Page<KhgxglEtckhghlsb>(pageNo, pageSize);
		IPage<KhgxglEtckhghlsb> pageList = khgxglEtckhghlsbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param khgxglEtckhghlsb
	 * @return
	 */
	@AutoLog(value = "ETC客户管户历史表-添加")
	@ApiOperation(value="ETC客户管户历史表-添加", notes="ETC客户管户历史表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhgxglEtckhghlsb khgxglEtckhghlsb) {
		khgxglEtckhghlsbService.save(khgxglEtckhghlsb);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param khgxglEtckhghlsb
	 * @return
	 */
	@AutoLog(value = "ETC客户管户历史表-编辑")
	@ApiOperation(value="ETC客户管户历史表-编辑", notes="ETC客户管户历史表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhgxglEtckhghlsb khgxglEtckhghlsb) {
		khgxglEtckhghlsbService.updateById(khgxglEtckhghlsb);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ETC客户管户历史表-通过id删除")
	@ApiOperation(value="ETC客户管户历史表-通过id删除", notes="ETC客户管户历史表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khgxglEtckhghlsbService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "ETC客户管户历史表-批量删除")
	@ApiOperation(value="ETC客户管户历史表-批量删除", notes="ETC客户管户历史表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khgxglEtckhghlsbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ETC客户管户历史表-通过id查询")
	@ApiOperation(value="ETC客户管户历史表-通过id查询", notes="ETC客户管户历史表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		KhgxglEtckhghlsb khgxglEtckhghlsb = khgxglEtckhghlsbService.getById(id);
		return Result.ok(khgxglEtckhghlsb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khgxglEtckhghlsb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KhgxglEtckhghlsb khgxglEtckhghlsb) {
      return super.exportXls(request, khgxglEtckhghlsb, KhgxglEtckhghlsb.class, "ETC客户管户历史表");
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
      return super.importExcel(request, response, KhgxglEtckhghlsb.class);
  }

}

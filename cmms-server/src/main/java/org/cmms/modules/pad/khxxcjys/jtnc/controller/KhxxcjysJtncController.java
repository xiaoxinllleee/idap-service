package org.cmms.modules.pad.khxxcjys.jtnc.controller;

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
import org.cmms.modules.pad.khxxcjys.jtnc.entity.KhxxcjysJtnc;
import org.cmms.modules.pad.khxxcjys.jtnc.service.IKhxxcjysJtncService;
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
 * @Description: 客户信息采集要素_家庭农场
 * @Author: jeecg-boot
 * @Date:   2022-06-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户信息采集要素_家庭农场")
@RestController
@RequestMapping("/khxxcjysjtnc/khxxcjysJtnc")
public class KhxxcjysJtncController extends JeecgController<KhxxcjysJtnc, IKhxxcjysJtncService> {
	@Autowired
	private IKhxxcjysJtncService khxxcjysJtncService;
	
	/**
	 * 分页列表查询
	 *
	 * @param khxxcjysJtnc
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户信息采集要素_家庭农场-分页列表查询")
	@ApiOperation(value="客户信息采集要素_家庭农场-分页列表查询", notes="客户信息采集要素_家庭农场-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhxxcjysJtnc khxxcjysJtnc,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<KhxxcjysJtnc> queryWrapper = QueryGenerator.initQueryWrapper(khxxcjysJtnc, req.getParameterMap());
		queryWrapper.orderByDesc("lrsj");
		Page<KhxxcjysJtnc> page = new Page<KhxxcjysJtnc>(pageNo, pageSize);
		IPage<KhxxcjysJtnc> pageList = khxxcjysJtncService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param khxxcjysJtnc
	 * @return
	 */
	@AutoLog(value = "客户信息采集要素_家庭农场-添加")
	@ApiOperation(value="客户信息采集要素_家庭农场-添加", notes="客户信息采集要素_家庭农场-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhxxcjysJtnc khxxcjysJtnc) {
		khxxcjysJtncService.save(khxxcjysJtnc);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param khxxcjysJtnc
	 * @return
	 */
	@AutoLog(value = "客户信息采集要素_家庭农场-编辑")
	@ApiOperation(value="客户信息采集要素_家庭农场-编辑", notes="客户信息采集要素_家庭农场-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhxxcjysJtnc khxxcjysJtnc) {
		khxxcjysJtncService.updateById(khxxcjysJtnc);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户信息采集要素_家庭农场-通过id删除")
	@ApiOperation(value="客户信息采集要素_家庭农场-通过id删除", notes="客户信息采集要素_家庭农场-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khxxcjysJtncService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户信息采集要素_家庭农场-批量删除")
	@ApiOperation(value="客户信息采集要素_家庭农场-批量删除", notes="客户信息采集要素_家庭农场-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khxxcjysJtncService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户信息采集要素_家庭农场-通过id查询")
	@ApiOperation(value="客户信息采集要素_家庭农场-通过id查询", notes="客户信息采集要素_家庭农场-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		KhxxcjysJtnc khxxcjysJtnc = khxxcjysJtncService.getById(id);
		return Result.ok(khxxcjysJtnc);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khxxcjysJtnc
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KhxxcjysJtnc khxxcjysJtnc) {
      return super.exportXls(request, khxxcjysJtnc, KhxxcjysJtnc.class, "客户信息采集要素_家庭农场");
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
      return super.importExcel(request, response, KhxxcjysJtnc.class);
  }

}

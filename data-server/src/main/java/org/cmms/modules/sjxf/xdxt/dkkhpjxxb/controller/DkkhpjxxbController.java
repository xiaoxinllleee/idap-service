package org.cmms.modules.sjxf.xdxt.dkkhpjxxb.controller;

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
import org.cmms.modules.sjxf.xdxt.dkkhpjxxb.entity.Dkkhpjxxb;
import org.cmms.modules.sjxf.xdxt.dkkhpjxxb.service.IDkkhpjxxbService;
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
 * @Description: 贷款客户评级信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款客户评级信息表")
@RestController
@RequestMapping("/dkkhpjxxb/dkkhpjxxb")
public class DkkhpjxxbController extends JeecgController<Dkkhpjxxb, IDkkhpjxxbService> {
	@Autowired
	private IDkkhpjxxbService dkkhpjxxbService;

	/**
	 * 分页列表查询
	 *
	 * @param dkkhpjxxb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款客户评级信息表-分页列表查询")
	@ApiOperation(value="贷款客户评级信息表-分页列表查询", notes="贷款客户评级信息表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dkkhpjxxb dkkhpjxxb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dkkhpjxxb> queryWrapper = QueryGenerator.initQueryWrapper(dkkhpjxxb, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IDkkhpjxxbService.class,dkkhpjxxbService,pageNo,pageSize,queryWrapper,"eval_flow_id");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkkhpjxxb
	 * @return
	 */
	@AutoLog(value = "贷款客户评级信息表-添加")
	@ApiOperation(value="贷款客户评级信息表-添加", notes="贷款客户评级信息表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dkkhpjxxb dkkhpjxxb) {
		dkkhpjxxbService.save(dkkhpjxxb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkkhpjxxb
	 * @return
	 */
	@AutoLog(value = "贷款客户评级信息表-编辑")
	@ApiOperation(value="贷款客户评级信息表-编辑", notes="贷款客户评级信息表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dkkhpjxxb dkkhpjxxb) {
		dkkhpjxxbService.updateById(dkkhpjxxb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款客户评级信息表-通过id删除")
	@ApiOperation(value="贷款客户评级信息表-通过id删除", notes="贷款客户评级信息表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkkhpjxxbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款客户评级信息表-批量删除")
	@ApiOperation(value="贷款客户评级信息表-批量删除", notes="贷款客户评级信息表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkkhpjxxbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款客户评级信息表-通过id查询")
	@ApiOperation(value="贷款客户评级信息表-通过id查询", notes="贷款客户评级信息表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dkkhpjxxb dkkhpjxxb = dkkhpjxxbService.getById(id);
		return Result.ok(dkkhpjxxb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkkhpjxxb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dkkhpjxxb dkkhpjxxb) {
      return super.exportXls(request, dkkhpjxxb, Dkkhpjxxb.class, "贷款客户评级信息表");
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
      return super.importExcel(request, response, Dkkhpjxxb.class);
  }

}

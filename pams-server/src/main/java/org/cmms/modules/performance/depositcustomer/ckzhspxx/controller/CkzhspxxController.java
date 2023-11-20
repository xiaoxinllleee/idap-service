package org.cmms.modules.performance.depositcustomer.ckzhspxx.controller;

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
import org.cmms.modules.performance.depositcustomer.ckzhspxx.entity.Ckzhspxx;
import org.cmms.modules.performance.depositcustomer.ckzhspxx.service.ICkzhspxxService;
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
 * @Description: 存款账户审批信息
 * @Author: jeecg-boot
 * @Date:   2023-03-31
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款账户审批信息")
@RestController
@RequestMapping("/performance/ckzhspxx")
public class CkzhspxxController extends JeecgController<Ckzhspxx, ICkzhspxxService> {
	@Autowired
	private ICkzhspxxService ckzhspxxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param ckzhspxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存款账户审批信息-分页列表查询")
	@ApiOperation(value="存款账户审批信息-分页列表查询", notes="存款账户审批信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ckzhspxx ckzhspxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Ckzhspxx> queryWrapper = QueryGenerator.initQueryWrapper(ckzhspxx, req.getParameterMap());
		Page<Ckzhspxx> page = new Page<Ckzhspxx>(pageNo, pageSize);
		IPage<Ckzhspxx> pageList = ckzhspxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param ckzhspxx
	 * @return
	 */
	@AutoLog(value = "存款账户审批信息-添加")
	@ApiOperation(value="存款账户审批信息-添加", notes="存款账户审批信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ckzhspxx ckzhspxx) {
		ckzhspxxService.save(ckzhspxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param ckzhspxx
	 * @return
	 */
	@AutoLog(value = "存款账户审批信息-编辑")
	@ApiOperation(value="存款账户审批信息-编辑", notes="存款账户审批信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ckzhspxx ckzhspxx) {
		ckzhspxxService.updateById(ckzhspxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款账户审批信息-通过id删除")
	@ApiOperation(value="存款账户审批信息-通过id删除", notes="存款账户审批信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ckzhspxxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "存款账户审批信息-批量删除")
	@ApiOperation(value="存款账户审批信息-批量删除", notes="存款账户审批信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckzhspxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款账户审批信息-通过id查询")
	@ApiOperation(value="存款账户审批信息-通过id查询", notes="存款账户审批信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ckzhspxx ckzhspxx = ckzhspxxService.getById(id);
		return Result.ok(ckzhspxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ckzhspxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ckzhspxx ckzhspxx) {
      return super.exportXls(request, ckzhspxx, Ckzhspxx.class, "存款账户审批信息");
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
      return super.importExcel(request, response, Ckzhspxx.class);
  }

}

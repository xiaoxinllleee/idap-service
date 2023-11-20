package org.cmms.modules.sjxf.xdxt.czyxx.controller;

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
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.sjxf.xdxt.czyxx.entity.Czyxx;
import org.cmms.modules.sjxf.xdxt.czyxx.service.ICzyxxService;
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
 * @Description: 操作员信息
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Slf4j
@Api(tags="操作员信息")
@RestController
@RequestMapping("/czyxx/czyxx")
public class CzyxxController extends JeecgController<Czyxx, ICzyxxService> {
	@Autowired
	private ICzyxxService czyxxService;

	/**
	 * 分页列表查询
	 *
	 * @param czyxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "操作员信息-分页列表查询")
	@ApiOperation(value="操作员信息-分页列表查询", notes="操作员信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Czyxx czyxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		//QueryWrapper<Czyxx> queryWrapper = QueryGenerator.initQueryWrapper(czyxx, req.getParameterMap());
		QueryWrapper<Czyxx> queryWrapper = new QueryWrapper<>();
		if (StringUtils.isNotEmpty(czyxx.getCustId())){
			queryWrapper.eq("cust_id",czyxx.getCustId());
		}
		if (StringUtils.isNotEmpty(czyxx.getFrontCd())){
			queryWrapper.eq("front_cd",czyxx.getFrontCd());
		}
		if (StringUtils.isNotEmpty(czyxx.getUserStatus())) {
			queryWrapper.eq("user_status", czyxx.getUserStatus());
		}
		if (StringUtils.isNotEmpty(czyxx.getUserType())) {
			queryWrapper.eq("user_type", czyxx.getUserType());
		}
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICzyxxService.class,czyxxService,pageNo,pageSize,queryWrapper,"user_id");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param czyxx
	 * @return
	 */
	@AutoLog(value = "操作员信息-添加")
	@ApiOperation(value="操作员信息-添加", notes="操作员信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Czyxx czyxx) {
		czyxxService.save(czyxx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param czyxx
	 * @return
	 */
	@AutoLog(value = "操作员信息-编辑")
	@ApiOperation(value="操作员信息-编辑", notes="操作员信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Czyxx czyxx) {
		czyxxService.updateById(czyxx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "操作员信息-通过id删除")
	@ApiOperation(value="操作员信息-通过id删除", notes="操作员信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		czyxxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "操作员信息-批量删除")
	@ApiOperation(value="操作员信息-批量删除", notes="操作员信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.czyxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "操作员信息-通过id查询")
	@ApiOperation(value="操作员信息-通过id查询", notes="操作员信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Czyxx czyxx = czyxxService.getById(id);
		return Result.ok(czyxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param czyxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Czyxx czyxx) {
      return super.exportXls(request, czyxx, Czyxx.class, "操作员信息");
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
      return super.importExcel(request, response, Czyxx.class);
  }

}

package org.cmms.modules.xddaglxt.dksjgl.dascspxx.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.cmms.modules.xddaglxt.dksjgl.dascspxx.entity.Dascspxx;
import org.cmms.modules.xddaglxt.dksjgl.dascspxx.service.IDascspxxService;
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
 * @Description: 档案删除审批信息
 * @Author: jeecg-boot
 * @Date:   2021-11-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="档案删除审批信息")
@RestController
@RequestMapping("/dascspxx/dascspxx")
public class DascspxxController extends JeecgController<Dascspxx, IDascspxxService> {
	@Autowired
	private IDascspxxService dascspxxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param dascspxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "档案删除审批信息-分页列表查询")
	@ApiOperation(value="档案删除审批信息-分页列表查询", notes="档案删除审批信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dascspxx dascspxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   String lrsjString,
								   HttpServletRequest req) {
		QueryWrapper<Dascspxx> queryWrapper = QueryGenerator.initQueryWrapper(dascspxx, req.getParameterMap());
		if (lrsjString != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] date = lrsjString.split(",");
			try {
				queryWrapper.between("lrsj",sdf.parse(date[0]),sdf.parse(date[1]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Page<Dascspxx> page = new Page<Dascspxx>(pageNo, pageSize);
		IPage<Dascspxx> pageList = dascspxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param dascspxx
	 * @return
	 */
	@AutoLog(value = "档案删除审批信息-添加")
	@ApiOperation(value="档案删除审批信息-添加", notes="档案删除审批信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dascspxx dascspxx) {
		dascspxxService.save(dascspxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param dascspxx
	 * @return
	 */
	@AutoLog(value = "档案删除审批信息-编辑")
	@ApiOperation(value="档案删除审批信息-编辑", notes="档案删除审批信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dascspxx dascspxx) {
		dascspxxService.updateById(dascspxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "档案删除审批信息-通过id删除")
	@ApiOperation(value="档案删除审批信息-通过id删除", notes="档案删除审批信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dascspxxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "档案删除审批信息-批量删除")
	@ApiOperation(value="档案删除审批信息-批量删除", notes="档案删除审批信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dascspxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "档案删除审批信息-通过id查询")
	@ApiOperation(value="档案删除审批信息-通过id查询", notes="档案删除审批信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dascspxx dascspxx = dascspxxService.getById(id);
		return Result.ok(dascspxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dascspxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dascspxx dascspxx) {
      return super.exportXls(request, dascspxx, Dascspxx.class, "档案删除审批信息");
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
      return super.importExcel(request, response, Dascspxx.class);
  }

}

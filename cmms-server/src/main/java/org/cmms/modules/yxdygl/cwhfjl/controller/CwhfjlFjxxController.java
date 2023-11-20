package org.cmms.modules.yxdygl.cwhfjl.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.yxdygl.cwhfjl.entity.CwhfjlFjxx;
import org.cmms.modules.yxdygl.cwhfjl.service.ICwhfjlFjxxService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.yxdygl.yxdyfjxx.entity.Yxdyfjxx;
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
 * @Description: 村委回访附件
 * @Author: jeecg-boot
 * @Date:   2023-06-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="村委回访附件")
@RestController
	@RequestMapping("/cwhfjl/cwhfjlFjxx")
public class CwhfjlFjxxController extends JeecgController<CwhfjlFjxx, ICwhfjlFjxxService> {
	@Autowired
	private ICwhfjlFjxxService cwhfjlFjxxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param cwhfjlFjxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "村委回访附件-分页列表查询")
	@ApiOperation(value="村委回访附件-分页列表查询", notes="村委回访附件-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CwhfjlFjxx cwhfjlFjxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CwhfjlFjxx> queryWrapper = QueryGenerator.initQueryWrapper(cwhfjlFjxx, req.getParameterMap());
		Page<CwhfjlFjxx> page = new Page<CwhfjlFjxx>(pageNo, pageSize);
		IPage<CwhfjlFjxx> pageList = cwhfjlFjxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}


	 /**
	  * 通过id查询
	  * @return
	  */
	 @GetMapping(value = "/queryCwhfFjxxByMainId")
	 public Result<?> queryCwhfFjxxByMainId(@RequestParam(name="id",required=true) String id) {
	 	 QueryWrapper<CwhfjlFjxx> queryWrapper=new QueryWrapper();
		 queryWrapper.eq("hf_id",id);
		 List<CwhfjlFjxx> yxdyglYxdyfjxxList = cwhfjlFjxxService.list(queryWrapper);
		 JSONArray jsonArray =new JSONArray();
		 for(CwhfjlFjxx cwhfjlFjxx:yxdyglYxdyfjxxList){
			 jsonArray.add(cwhfjlFjxx.getFjlj());
		 }
		 return Result.ok(jsonArray);
	 }

	/**
	 * 添加
	 *
	 * @param cwhfjlFjxx
	 * @return
	 */
	@AutoLog(value = "村委回访附件-添加")
	@ApiOperation(value="村委回访附件-添加", notes="村委回访附件-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CwhfjlFjxx cwhfjlFjxx) {
		cwhfjlFjxxService.save(cwhfjlFjxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param cwhfjlFjxx
	 * @return
	 */
	@AutoLog(value = "村委回访附件-编辑")
	@ApiOperation(value="村委回访附件-编辑", notes="村委回访附件-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CwhfjlFjxx cwhfjlFjxx) {
		cwhfjlFjxxService.updateById(cwhfjlFjxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "村委回访附件-通过id删除")
	@ApiOperation(value="村委回访附件-通过id删除", notes="村委回访附件-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		cwhfjlFjxxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "村委回访附件-批量删除")
	@ApiOperation(value="村委回访附件-批量删除", notes="村委回访附件-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cwhfjlFjxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "村委回访附件-通过id查询")
	@ApiOperation(value="村委回访附件-通过id查询", notes="村委回访附件-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CwhfjlFjxx cwhfjlFjxx = cwhfjlFjxxService.getById(id);
		return Result.ok(cwhfjlFjxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param cwhfjlFjxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, CwhfjlFjxx cwhfjlFjxx) {
      return super.exportXls(request, cwhfjlFjxx, CwhfjlFjxx.class, "村委回访附件");
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
      return super.importExcel(request, response, CwhfjlFjxx.class);
  }

}

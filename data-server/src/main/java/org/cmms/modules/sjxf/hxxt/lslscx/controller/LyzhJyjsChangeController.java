package org.cmms.modules.sjxf.hxxt.lslscx.controller;

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
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.sjxf.hxxt.lslscx.entity.LyzhJyjsChange;
import org.cmms.modules.sjxf.hxxt.lslscx.service.ILyzhJyjsChangeService;
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
 * @Description: 历史流水查询
 * @Author: jeecg-boot
 * @Date:   2022-12-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="历史流水查询")
@RestController
@RequestMapping("/sjxf/hxxt/lslscx/lyzhJyjsChange")
public class LyzhJyjsChangeController extends JeecgController<LyzhJyjsChange, ILyzhJyjsChangeService> {
	@Autowired
	private ILyzhJyjsChangeService lyzhJyjsChangeService;
	
	/**
	 * 分页列表查询
	 *
	 * @param lyzhJyjsChange
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "历史流水查询-分页列表查询")
	@ApiOperation(value="历史流水查询-分页列表查询", notes="历史流水查询-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(LyzhJyjsChange lyzhJyjsChange,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<LyzhJyjsChange> queryWrapper = QueryGenerator.initQueryWrapper(lyzhJyjsChange, req.getParameterMap());
		Page<LyzhJyjsChange> page = new Page<LyzhJyjsChange>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ILyzhJyjsChangeService.class,lyzhJyjsChangeService,pageNo,pageSize,queryWrapper,"jyrq","jysj");
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param lyzhJyjsChange
	 * @return
	 */
	@AutoLog(value = "历史流水查询-添加")
	@ApiOperation(value="历史流水查询-添加", notes="历史流水查询-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody LyzhJyjsChange lyzhJyjsChange) {
		lyzhJyjsChangeService.save(lyzhJyjsChange);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param lyzhJyjsChange
	 * @return
	 */
	@AutoLog(value = "历史流水查询-编辑")
	@ApiOperation(value="历史流水查询-编辑", notes="历史流水查询-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody LyzhJyjsChange lyzhJyjsChange) {
		lyzhJyjsChangeService.updateById(lyzhJyjsChange);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "历史流水查询-通过id删除")
	@ApiOperation(value="历史流水查询-通过id删除", notes="历史流水查询-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		lyzhJyjsChangeService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "历史流水查询-批量删除")
	@ApiOperation(value="历史流水查询-批量删除", notes="历史流水查询-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.lyzhJyjsChangeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "历史流水查询-通过id查询")
	@ApiOperation(value="历史流水查询-通过id查询", notes="历史流水查询-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		LyzhJyjsChange lyzhJyjsChange = lyzhJyjsChangeService.getById(id);
		return Result.ok(lyzhJyjsChange);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      //return super.exportXls(request, lyzhJyjsChange, LyzhJyjsChange.class, "历史流水查询");
      QueryWrapper<LyzhJyjsChange> queryWrapper = new QueryWrapper<>();
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              LyzhJyjsChange lyzhJyjsChange = JSON.parseObject(deString, LyzhJyjsChange.class);
              queryWrapper = QueryGenerator.initQueryWrapper(lyzhJyjsChange, request.getParameterMap());
          }
      } catch (Throwable throwable) {
          throwable.printStackTrace();
          log.error("导出错误！历史流水查询："+throwable.getMessage());
      }
      queryWrapper.orderByDesc("jyrq","jysj");
      List<LyzhJyjsChange> pageList = lyzhJyjsChangeService.list(queryWrapper);

      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "历史流水查询");
      mv.addObject(NormalExcelConstants.CLASS, LyzhJyjsChange.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("历史流水查询", "导出人:" + getLoginUser().getRealname(), "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
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
      return super.importExcel(request, response, LyzhJyjsChange.class);
  }

}

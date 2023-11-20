package org.cmms.modules.tjfx.sjhztj.zhhztj.controller;

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
import org.cmms.modules.tjfx.sjhztj.zhhztj.entity.VTjfxHztjZh;

import java.util.Date;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.sjhztj.zhhztj.service.IVTjfxHztjZhService;
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
 * @Description: 支行汇总统计数据
 * @Author: jeecg-boot
 * @Date:   2023-09-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行汇总统计数据")
@RestController
@RequestMapping("/sjhztj/zhhztj/vTjfxHztjZh")
public class VTjfxHztjZhController extends JeecgController<VTjfxHztjZh, IVTjfxHztjZhService> {
	@Autowired
	private IVTjfxHztjZhService vTjfxHztjZhService;
	
	/**
	 * 分页列表查询
	 *
	 * @param vTjfxHztjZh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行汇总统计数据-分页列表查询")
	@ApiOperation(value="支行汇总统计数据-分页列表查询", notes="支行汇总统计数据-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VTjfxHztjZh vTjfxHztjZh,
								   @RequestParam(name = "isPc",required = false) String isPc,
								   @RequestParam(name = "column",required = false) String column,
								   @RequestParam(name = "order",required = false) String order,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VTjfxHztjZh> queryWrapper = QueryGenerator.initQueryWrapper(vTjfxHztjZh, req.getParameterMap());
		if (StringUtils.isBlank(column) || StringUtils.isBlank(order)){
			queryWrapper.orderByDesc("bzyxzfs");
		}
		Page<VTjfxHztjZh> page = new Page<VTjfxHztjZh>(pageNo, pageSize);
		Integer[] arr = {1};
		arr[0]=arr[0]+(pageNo-1)*pageSize;
		if (StringUtils.isBlank(column) || StringUtils.isBlank(order)){
			queryWrapper.orderByDesc("bzyxzfs");
			if (StringUtils.isNotBlank(isPc) && "2".equals(isPc)) {
				IPage<VTjfxHztjZh> pageList = vTjfxHztjZhService.page(page, queryWrapper);
				if (pageNo==1) {
					List<VTjfxHztjZh> list = pageList.getRecords().stream().peek(e -> e.setPm(arr[0]++)).collect(Collectors.toList());
					List<VTjfxHztjZh> list1 = vTjfxHztjZhService.list(queryWrapper);
					list1.set(3, list1.get(list1.size() - 3));
					list1.set(4, list1.get(list1.size() - 2));
					list1.set(5, list1.get(list1.size() - 1));
					list.get(3).setPm(list1.size() - 3);
					list.get(4).setPm(list1.size() - 2);
					list.get(5).setPm(list1.size() - 1);
					pageList.setRecords(list);
				}else{
					Integer[] arr1 = {4};
					arr1[0]=arr1[0]+(pageNo-2)*pageSize;
					List<VTjfxHztjZh> list = pageList.getRecords().stream().peek(e -> e.setPm(arr1[0]++)).collect(Collectors.toList());
					pageList.setRecords(list);}
				return Result.ok(pageList);
			}
		}
		IPage<VTjfxHztjZh> pageList = vTjfxHztjZhService.page(page, queryWrapper);
		pageList.setRecords(pageList.getRecords().stream().peek(e->e.setPm(arr[0]++)).collect(Collectors.toList()));
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param vTjfxHztjZh
	 * @return
	 */
	@AutoLog(value = "支行汇总统计数据-添加")
	@ApiOperation(value="支行汇总统计数据-添加", notes="支行汇总统计数据-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VTjfxHztjZh vTjfxHztjZh) {
		vTjfxHztjZhService.save(vTjfxHztjZh);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param vTjfxHztjZh
	 * @return
	 */
	@AutoLog(value = "支行汇总统计数据-编辑")
	@ApiOperation(value="支行汇总统计数据-编辑", notes="支行汇总统计数据-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody VTjfxHztjZh vTjfxHztjZh) {
		vTjfxHztjZhService.updateById(vTjfxHztjZh);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行汇总统计数据-通过id删除")
	@ApiOperation(value="支行汇总统计数据-通过id删除", notes="支行汇总统计数据-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		vTjfxHztjZhService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "支行汇总统计数据-批量删除")
	@ApiOperation(value="支行汇总统计数据-批量删除", notes="支行汇总统计数据-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.vTjfxHztjZhService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行汇总统计数据-通过id查询")
	@ApiOperation(value="支行汇总统计数据-通过id查询", notes="支行汇总统计数据-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VTjfxHztjZh vTjfxHztjZh = vTjfxHztjZhService.getById(id);
		return Result.ok(vTjfxHztjZh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param vTjfxHztjZh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, VTjfxHztjZh vTjfxHztjZh) {
      return super.exportXls(request, vTjfxHztjZh, VTjfxHztjZh.class, "支行汇总统计数据");
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
      return super.importExcel(request, response, VTjfxHztjZh.class);
  }

}

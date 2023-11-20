package org.cmms.modules.kmtj.kmtjsz.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.kmtj.kmtjsz.entity.Kmtjsz;
import org.cmms.modules.kmtj.kmtjsz.service.IKmtjszService;
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
 * @Description: 科目统计设置
 * @Author: jeecg-boot
 * @Date:   2023-03-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="科目统计设置")
@RestController
@RequestMapping("/kmtjsz/kmtjsz")
public class KmtjszController extends JeecgController<Kmtjsz, IKmtjszService> {
	@Autowired
	private IKmtjszService kmtjszService;

	/**
	 * 分页列表查询
	 *
	 * @param kmtjsz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "科目统计设置-分页列表查询")
	@ApiOperation(value="科目统计设置-分页列表查询", notes="科目统计设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Kmtjsz kmtjsz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Kmtjsz> queryWrapper = QueryGenerator.initQueryWrapper(kmtjsz, req.getParameterMap());
		Page<Kmtjsz> page = new Page<Kmtjsz>(pageNo, pageSize);
		IPage<Kmtjsz> pageList = kmtjszService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param kmtjsz
	 * @return
	 */
	@AutoLog(value = "科目统计设置-添加")
	@ApiOperation(value="科目统计设置-添加", notes="科目统计设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Kmtjsz kmtjsz) {
		QueryWrapper<Kmtjsz> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("tjbs",kmtjsz.getTjbs());
		queryWrapper.eq("kmh",kmtjsz.getKmh());
		Kmtjsz check = kmtjszService.getOne(queryWrapper);
		if (check != null){
			return Result.error("统计标识["+kmtjsz.getTjbs()+"]、科目号["+kmtjsz.getKmh()+"]已存在！");
		}
		kmtjszService.save(kmtjsz);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param kmtjsz
	 * @return
	 */
	@AutoLog(value = "科目统计设置-编辑")
	@ApiOperation(value="科目统计设置-编辑", notes="科目统计设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Kmtjsz kmtjsz) {
		QueryWrapper<Kmtjsz> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("tjbs",kmtjsz.getTjbs());
		queryWrapper.eq("kmh",kmtjsz.getKmh());
		kmtjszService.update(kmtjsz,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "科目统计设置-通过id删除")
	@ApiOperation(value="科目统计设置-通过id删除", notes="科目统计设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("tjbs") String tjbs,@Param("kmh") String kmh) {
		QueryWrapper<Kmtjsz> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("tjbs",tjbs);
		queryWrapper.eq("kmh",kmh);
		kmtjszService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "科目统计设置-批量删除")
	@ApiOperation(value="科目统计设置-批量删除", notes="科目统计设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.kmtjszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "科目统计设置-通过id查询")
	@ApiOperation(value="科目统计设置-通过id查询", notes="科目统计设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Kmtjsz kmtjsz = kmtjszService.getById(id);
		return Result.ok(kmtjsz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param kmtjsz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Kmtjsz kmtjsz) {
      return super.exportXls(request, kmtjsz, Kmtjsz.class, "科目统计设置");
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
      return super.importExcel(request, response, Kmtjsz.class);
  }

}

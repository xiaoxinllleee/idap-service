package org.cmms.modules.khxxgl.khflgl.nhxq.controller;

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
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhycxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhycxqService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jsoup.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 农户移除表
 * @Author: jeecg-boot
 * @Date:   2023-10-12
 * @Version: V1.0
 */
@Slf4j
@Api(tags="农户移除表")
@RestController
@RequestMapping("/nhxq/nhycxq")
public class NhycxqController extends JeecgController<Nhycxq, INhycxqService> {
	@Autowired
	private INhycxqService nhycxqService;
	
	/**
	 * 分页列表查询
	 *
	 * @param nhycxq
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "农户移除表-分页列表查询")
	@ApiOperation(value="农户移除表-分页列表查询", notes="农户移除表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Nhycxq nhycxq,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Nhycxq> queryWrapper = QueryGenerator.initQueryWrapper(nhycxq, req.getParameterMap());
		Page<Nhycxq> page = new Page<Nhycxq>(pageNo, pageSize);
		IPage<Nhycxq> pageList = nhycxqService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	@GetMapping("/getAllYcNhxx")
	public Result<?> getAllYcNhxx(@RequestParam(name = "khmc",required = false)String khmc,
								  @RequestParam(name = "zjhm",required = false)String zjhm){
		QueryWrapper<Nhycxq> queryWrapper=new QueryWrapper<>();
		if (StringUtils.isNotBlank(khmc)){
			queryWrapper.eq("khmc",khmc);
		}
		if (StringUtils.isNotBlank(zjhm)){
			queryWrapper.eq("zjhm",zjhm);
		}
		List<Nhycxq> list =nhycxqService.list(queryWrapper);
		return Result.ok(list);
	}
	
	/**
	 * 添加
	 *
	 * @param nhycxq
	 * @return
	 */
	@AutoLog(value = "农户移除表-添加")
	@ApiOperation(value="农户移除表-添加", notes="农户移除表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Nhycxq nhycxq) {
		nhycxqService.save(nhycxq);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param nhycxq
	 * @return
	 */
	@AutoLog(value = "农户移除表-编辑")
	@ApiOperation(value="农户移除表-编辑", notes="农户移除表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Nhycxq nhycxq) {
		nhycxqService.updateById(nhycxq);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "农户移除表-通过id删除")
	@ApiOperation(value="农户移除表-通过id删除", notes="农户移除表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		nhycxqService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "农户移除表-批量删除")
	@ApiOperation(value="农户移除表-批量删除", notes="农户移除表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.nhycxqService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "农户移除表-通过id查询")
	@ApiOperation(value="农户移除表-通过id查询", notes="农户移除表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Nhycxq nhycxq = nhycxqService.getById(id);
		return Result.ok(nhycxq);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param nhycxq
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Nhycxq nhycxq) {
      return super.exportXls(request, nhycxq, Nhycxq.class, "农户移除表");
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
      return super.importExcel(request, response, Nhycxq.class);
  }

}

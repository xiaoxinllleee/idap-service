package org.cmms.modules.hr.djpd.khjlxjpd.csgl.pjcssz.controller;

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
import org.cmms.modules.hr.djpd.khjlxjpd.csgl.pjcssz.entity.Pjcssz;
import org.cmms.modules.hr.djpd.khjlxjpd.csgl.pjcssz.service.IPjcsszService;
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
 * @Description: 评级参数设置
 * @Author: jeecg-boot
 * @Date:   2021-09-02
 * @Version: V1.0
 */
@Slf4j
@Api(tags="评级参数设置")
@RestController
@RequestMapping("/pjcssz/pjcssz")
public class PjcsszController extends JeecgController<Pjcssz, IPjcsszService> {
	@Autowired
	private IPjcsszService pjcsszService;

	/**
	 * 分页列表查询
	 *
	 * @param pjcssz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "评级参数设置-分页列表查询")
	@ApiOperation(value="评级参数设置-分页列表查询", notes="评级参数设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Pjcssz pjcssz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Pjcssz> queryWrapper = QueryGenerator.initQueryWrapper(pjcssz, req.getParameterMap());
		Page<Pjcssz> page = new Page<Pjcssz>(pageNo, pageSize);
		IPage<Pjcssz> pageList = pjcsszService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param pjcssz
	 * @return
	 */
	@AutoLog(value = "评级参数设置-添加")
	@ApiOperation(value="评级参数设置-添加", notes="评级参数设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Pjcssz pjcssz) {
		QueryWrapper<Pjcssz> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("csbh",pjcssz.getCsbh());
		Pjcssz check = pjcsszService.getOne(queryWrapper);
		if (check != null){
			return Result.error("参数编号已经存在!");
		}
		pjcsszService.save(pjcssz);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param pjcssz
	 * @return
	 */
	@AutoLog(value = "评级参数设置-编辑")
	@ApiOperation(value="评级参数设置-编辑", notes="评级参数设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Pjcssz pjcssz) {
		QueryWrapper<Pjcssz> queryWrapper = new QueryWrapper<Pjcssz>();
		queryWrapper.eq("csbh",pjcssz.getCsbh());
		pjcsszService.update(pjcssz,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "评级参数设置-通过id删除")
	@ApiOperation(value="评级参数设置-通过id删除", notes="评级参数设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("csbh") String csbh) {
		QueryWrapper<Pjcssz> queryWrapper = new QueryWrapper<Pjcssz>();
		queryWrapper.eq("csbh",csbh);
		pjcsszService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "评级参数设置-批量删除")
	@ApiOperation(value="评级参数设置-批量删除", notes="评级参数设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pjcsszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评级参数设置-通过id查询")
	@ApiOperation(value="评级参数设置-通过id查询", notes="评级参数设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Pjcssz pjcssz = pjcsszService.getById(id);
		return Result.ok(pjcssz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param pjcssz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Pjcssz pjcssz) {
      return super.exportXls(request, pjcssz, Pjcssz.class, "评级参数设置");
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
      return super.importExcel(request, response, Pjcssz.class);
  }

}

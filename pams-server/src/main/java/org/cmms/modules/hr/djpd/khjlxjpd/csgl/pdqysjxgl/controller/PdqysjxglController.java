package org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdqysjxgl.controller;

import java.sql.Timestamp;
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
import org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdqysjxgl.entity.Pdqysjxgl;
import org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdqysjxgl.service.IPdqysjxglService;
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
 * @Description: 评定区域数据项管理
 * @Author: jeecg-boot
 * @Date:   2021-09-02
 * @Version: V1.0
 */
@Slf4j
@Api(tags="评定区域数据项管理")
@RestController
@RequestMapping("/pdqysjxgl/pdqysjxgl")
public class PdqysjxglController extends JeecgController<Pdqysjxgl, IPdqysjxglService> {
	@Autowired
	private IPdqysjxglService pdqysjxglService;

	/**
	 * 分页列表查询
	 *
	 * @param pdqysjxgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "评定区域数据项管理-分页列表查询")
	@ApiOperation(value="评定区域数据项管理-分页列表查询", notes="评定区域数据项管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Pdqysjxgl pdqysjxgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Pdqysjxgl> queryWrapper = QueryGenerator.initQueryWrapper(pdqysjxgl, req.getParameterMap());
		System.out.println(getRedisQydm());

		queryWrapper.eq("qydm",getRedisQydm());
		Page<Pdqysjxgl> page = new Page<Pdqysjxgl>(pageNo, pageSize);
		IPage<Pdqysjxgl> pageList = pdqysjxglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param pdqysjxgl
	 * @return
	 */
	@AutoLog(value = "评定区域数据项管理-添加")
	@ApiOperation(value="评定区域数据项管理-添加", notes="评定区域数据项管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Pdqysjxgl pdqysjxgl) {
		QueryWrapper<Pdqysjxgl> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("sjxno",pdqysjxgl.getSjxno());
		Pdqysjxgl one = pdqysjxglService.getOne(queryWrapper);
		if (one != null){
			return Result.error("数据编号已经存在，请核实!");
		}
		pdqysjxgl.setLrsj(new Date());
		pdqysjxgl.setLrr(getUsername());
		pdqysjxgl.setLrbz(1);
		pdqysjxglService.save(pdqysjxgl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param pdqysjxgl
	 * @return
	 */
	@AutoLog(value = "评定区域数据项管理-编辑")
	@ApiOperation(value="评定区域数据项管理-编辑", notes="评定区域数据项管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Pdqysjxgl pdqysjxgl) {
		QueryWrapper<Pdqysjxgl> queryWrapper = new QueryWrapper<Pdqysjxgl>();
		queryWrapper.eq("sjxno",pdqysjxgl.getSjxno());
		pdqysjxgl.setLrsj(new Date());
		pdqysjxgl.setLrr(getUsername());
		pdqysjxgl.setLrbz(1);
		pdqysjxglService.update(pdqysjxgl,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "评定区域数据项管理-通过id删除")
	@ApiOperation(value="评定区域数据项管理-通过id删除", notes="评定区域数据项管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("sjxno") String sjxno) {
		QueryWrapper<Pdqysjxgl> queryWrapper = new QueryWrapper<Pdqysjxgl>();
		queryWrapper.eq("sjxno",sjxno);
		pdqysjxglService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "评定区域数据项管理-批量删除")
	@ApiOperation(value="评定区域数据项管理-批量删除", notes="评定区域数据项管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pdqysjxglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评定区域数据项管理-通过id查询")
	@ApiOperation(value="评定区域数据项管理-通过id查询", notes="评定区域数据项管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Pdqysjxgl pdqysjxgl = pdqysjxglService.getById(id);
		return Result.ok(pdqysjxgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param pdqysjxgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Pdqysjxgl pdqysjxgl) {
      return super.exportXls(request, pdqysjxgl, Pdqysjxgl.class, "评定区域数据项管理");
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
      return super.importExcel(request, response, Pdqysjxgl.class);
  }

}

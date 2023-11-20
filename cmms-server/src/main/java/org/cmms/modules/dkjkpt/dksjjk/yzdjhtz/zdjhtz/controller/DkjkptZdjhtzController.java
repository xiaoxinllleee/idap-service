package org.cmms.modules.dkjkpt.dksjjk.yzdjhtz.zdjhtz.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
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
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity.DkjkptBndksjjktz;
import org.cmms.modules.dkjkpt.dksjjk.yzdjhtz.zdjhtz.entity.DkjkptZdjhtz;
import org.cmms.modules.dkjkpt.dksjjk.yzdjhtz.zdjhtz.service.IDkjkptZdjhtzService;
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
 * @Description: 已制定计划台账
 * @Author: jeecg-boot
 * @Date:   2023-08-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags="已制定计划台账")
@RestController
@RequestMapping("/zdjhtz/dkjkptZdjhtz")
public class DkjkptZdjhtzController extends JeecgController<DkjkptZdjhtz, IDkjkptZdjhtzService> {
	@Autowired
	private IDkjkptZdjhtzService dkjkptZdjhtzService;

	/**
	 * 分页列表查询
	 *
	 * @param dkjkptZdjhtz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "已制定计划台账-分页列表查询")
	@ApiOperation(value="已制定计划台账-分页列表查询", notes="已制定计划台账-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DkjkptZdjhtz dkjkptZdjhtz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DkjkptZdjhtz> queryWrapper = QueryGenerator.initQueryWrapper(dkjkptZdjhtz, req.getParameterMap());
		Page<DkjkptZdjhtz> page = new Page<DkjkptZdjhtz>(pageNo, pageSize);
		IPage<DkjkptZdjhtz> pageList = dkjkptZdjhtzService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkjkptZdjhtz
	 * @return
	 */
	@AutoLog(value = "已制定计划台账-添加")
	@ApiOperation(value="已制定计划台账-添加", notes="已制定计划台账-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DkjkptZdjhtz dkjkptZdjhtz) {
		dkjkptZdjhtzService.save(dkjkptZdjhtz);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkjkptZdjhtz
	 * @return
	 */
	@AutoLog(value = "已制定计划台账-编辑")
	@ApiOperation(value="已制定计划台账-编辑", notes="已制定计划台账-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DkjkptZdjhtz dkjkptZdjhtz) {
		QueryWrapper<DkjkptZdjhtz> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("sjrq",dkjkptZdjhtz.getSjrq());
		queryWrapper.eq("zjhm",dkjkptZdjhtz.getZjhm());
		queryWrapper.eq("qdlx",dkjkptZdjhtz.getQdlx());
		queryWrapper.eq("hkpl",dkjkptZdjhtz.getHkpl());
		dkjkptZdjhtzService.update(dkjkptZdjhtz,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "已制定计划台账-通过id删除")
	@ApiOperation(value="已制定计划台账-通过id删除", notes="已制定计划台账-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("sjrq") String sjrq,@Param("zjhm") String zjhm,@Param("qdlx") String qdlx,@Param("hkpl") String hkpl,@Param("jgdm") String jgdm) {
		QueryWrapper<DkjkptZdjhtz> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("sjrq", DateUtil.parse(sjrq));
		queryWrapper.eq("zjhm",zjhm);
		queryWrapper.eq("qdlx",qdlx);
		queryWrapper.eq("hkpl",hkpl);
		queryWrapper.eq("jgdm",jgdm);
		dkjkptZdjhtzService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "已制定计划台账-批量删除")
	@ApiOperation(value="已制定计划台账-批量删除", notes="已制定计划台账-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkjkptZdjhtzService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "已制定计划台账-通过id查询")
	@ApiOperation(value="已制定计划台账-通过id查询", notes="已制定计划台账-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DkjkptZdjhtz dkjkptZdjhtz = dkjkptZdjhtzService.getById(id);
		return Result.ok(dkjkptZdjhtz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkjkptZdjhtz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DkjkptZdjhtz dkjkptZdjhtz) {
      return super.exportXls(request, dkjkptZdjhtz, DkjkptZdjhtz.class, "已制定计划台账");
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
      return super.importExcel(request, response, DkjkptZdjhtz.class);
  }
	 /**
	  * 提取
	  * @return
	  */
	 @AutoLog(value = "提取")
	 @RequestMapping(value = "/init", method = RequestMethod.PUT)
	 public Result<?> init(@RequestBody JSONObject object) {
		 try {
			 String tjrq = object.getString("sjrq");
			 tjrq = tjrq.replace("-", "");
			 System.out.println(tjrq);
			 dkjkptZdjhtzService.init(tjrq);
		 } catch (Exception e) {
			 log.error("提取失败！",e.getMessage());
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功！");
	 }


}

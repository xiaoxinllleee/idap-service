package org.cmms.modules.khgl.ckdkkh.controller;

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
import org.cmms.modules.khgl.ckdkkh.entity.ClDkkh;
import org.cmms.modules.khgl.ckdkkh.service.IClDkkhService;
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
 * @Description: 存量贷款客户
 * @Author: jeecg-boot
 * @Date:   2022-09-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存量贷款客户")
@RestController
@RequestMapping("/khgl/clDkkh")
public class ClDkkhController extends JeecgController<ClDkkh, IClDkkhService> {
	
	/**
	 * 分页列表查询
	 *
	 * @param clDkkh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存量贷款客户-分页列表查询")
	@ApiOperation(value="存量贷款客户-分页列表查询", notes="存量贷款客户-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ClDkkh clDkkh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ClDkkh> queryWrapper = QueryGenerator.initQueryWrapper(clDkkh, req.getParameterMap());
		Page<ClDkkh> page = new Page<ClDkkh>(pageNo, pageSize);
		IPage<ClDkkh> pageList = service.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param clDkkh
	 * @return
	 */
	@AutoLog(value = "存量贷款客户-添加")
	@ApiOperation(value="存量贷款客户-添加", notes="存量贷款客户-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ClDkkh clDkkh) {
		service.save(clDkkh);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param clDkkh
	 * @return
	 */
	@AutoLog(value = "存量贷款客户-编辑")
	@ApiOperation(value="存量贷款客户-编辑", notes="存量贷款客户-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ClDkkh clDkkh) {
		service.updateById(clDkkh);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存量贷款客户-通过id删除")
	@ApiOperation(value="存量贷款客户-通过id删除", notes="存量贷款客户-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		service.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "存量贷款客户-批量删除")
	@ApiOperation(value="存量贷款客户-批量删除", notes="存量贷款客户-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存量贷款客户-通过id查询")
	@ApiOperation(value="存量贷款客户-通过id查询", notes="存量贷款客户-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ClDkkh clDkkh = service.getById(id);
		return Result.ok(clDkkh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param clDkkh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ClDkkh clDkkh) {
      return super.exportXls(request, clDkkh, ClDkkh.class, "存量贷款客户");
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
      return super.importExcel(request, response, ClDkkh.class);
  }


	 @RequestMapping(value = "/init")
	 public Result<?> init() {
		 try {
			 service.pClDkkh();
			 return Result.ok();
		 } catch (Throwable e) {
			 System.out.println(e);
			 log.error("提取失败", e.getMessage());
			 return Result.error("提取存储过程失败");
		 }
		 //return Result.error("提取失败");
	 }
}

package org.cmms.modules.pad.pyxx.controller;

import java.math.BigDecimal;
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
import org.cmms.modules.pad.pyxx.entity.Pydjcs;
import org.cmms.modules.pad.pyxx.entity.Pyyxx;
import org.cmms.modules.pad.pyxx.service.IPydjcsService;
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
 * @Description: 评议得分对应等级额度
 * @Author: jeecg-boot
 * @Date:   2020-07-31
 * @Version: V1.0
 */
@Slf4j
@Api(tags="评议得分对应等级额度")
@RestController
@RequestMapping("/pad/pyxx/pydjcs")
public class PydjcsController extends JeecgController<Pydjcs, IPydjcsService> {
	@Autowired
	private IPydjcsService pydjcsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param pydjcs
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "评议得分对应等级额度-分页列表查询")
	@ApiOperation(value="评议得分对应等级额度-分页列表查询", notes="评议得分对应等级额度-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Pydjcs pydjcs,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Pydjcs> queryWrapper = QueryGenerator.initQueryWrapper(pydjcs, req.getParameterMap());
		Page<Pydjcs> page = new Page<Pydjcs>(pageNo, pageSize);
		IPage<Pydjcs> pageList = pydjcsService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param pydjcs
	 * @return
	 */
	@AutoLog(value = "评议得分对应等级额度-添加")
	@ApiOperation(value="评议得分对应等级额度-添加", notes="评议得分对应等级额度-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Pydjcs pydjcs) {
		pydjcsService.save(pydjcs);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param pydjcs
	 * @return
	 */
	@AutoLog(value = "评议得分对应等级额度-编辑")
	@ApiOperation(value="评议得分对应等级额度-编辑", notes="评议得分对应等级额度-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Pydjcs pydjcs) {
		pydjcsService.updateById(pydjcs);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评议得分对应等级额度-通过id删除")
	@ApiOperation(value="评议得分对应等级额度-通过id删除", notes="评议得分对应等级额度-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pydjcsService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "评议得分对应等级额度-批量删除")
	@ApiOperation(value="评议得分对应等级额度-批量删除", notes="评议得分对应等级额度-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pydjcsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评议得分对应等级额度-通过id查询")
	@ApiOperation(value="评议得分对应等级额度-通过id查询", notes="评议得分对应等级额度-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Pydjcs pydjcs = pydjcsService.getById(id);
		return Result.ok(pydjcs);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param pydjcs
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Pydjcs pydjcs) {
      return super.exportXls(request, pydjcs, Pydjcs.class, "评议得分对应等级额度");
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
      return super.importExcel(request, response, Pydjcs.class);
  }


	 @PostMapping(value = "/getPddjAndJysxde")
	 public Result<?> getPddjAndJysxde(@Param("pydf") BigDecimal pydf) {
		 return Result.ok(pydjcsService.getPddjAndJysxde(pydf));
	 }

}

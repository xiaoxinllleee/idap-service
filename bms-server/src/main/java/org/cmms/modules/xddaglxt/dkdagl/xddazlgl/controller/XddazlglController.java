package org.cmms.modules.xddaglxt.dkdagl.xddazlgl.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.xddaglxt.dkdagl.xddazlgl.entity.Xddazlgl;
import org.cmms.modules.xddaglxt.dkdagl.xddazlgl.service.IXddazlglService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 信贷档案资料管理
 * @Author: jeecg-boot
 * @Date:   2021-11-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="信贷档案资料管理")
@RestController
@RequestMapping("/xddazlgl/xddazlgl")
public class XddazlglController extends JeecgController<Xddazlgl, IXddazlglService> {
	@Autowired
	private IXddazlglService xddazlglService;

	/**
	 * 分页列表查询
	 *
	 * @param xddazlgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "信贷档案资料管理-分页列表查询")
	@ApiOperation(value="信贷档案资料管理-分页列表查询", notes="信贷档案资料管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Xddazlgl xddazlgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   String lrsjString,
								   HttpServletRequest req) {
		QueryWrapper<Xddazlgl> queryWrapper = QueryGenerator.initQueryWrapper(xddazlgl, req.getParameterMap());
		if (lrsjString != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] date = lrsjString.split(",");
			try {
				queryWrapper.between("lrsj",sdf.parse(date[0]),sdf.parse(date[1]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Page<Xddazlgl> page = new Page<Xddazlgl>(pageNo, pageSize);
		IPage<Xddazlgl> pageList = xddazlglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "信贷档案资料管理-添加")
	@ApiOperation(value="信贷档案资料管理-添加", notes="信贷档案资料管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Xddazlgl xddazlgl) {
		xddazlglService.save(xddazlgl);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "信贷档案资料管理-编辑")
	@ApiOperation(value="信贷档案资料管理-编辑", notes="信贷档案资料管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Xddazlgl xddazlgl) {
		xddazlglService.updateById(xddazlgl);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "信贷档案资料管理-通过id删除")
	@ApiOperation(value="信贷档案资料管理-通过id删除", notes="信贷档案资料管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		xddazlglService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "信贷档案资料管理-批量删除")
	@ApiOperation(value="信贷档案资料管理-批量删除", notes="信贷档案资料管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.xddazlglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "信贷档案资料管理-通过id查询")
	@ApiOperation(value="信贷档案资料管理-通过id查询", notes="信贷档案资料管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Xddazlgl xddazlgl = xddazlglService.getById(id);
		return Result.ok(xddazlgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param xddazlgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Xddazlgl xddazlgl) {
      return super.exportXls(request, xddazlgl, Xddazlgl.class, "信贷档案资料管理");
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
      return super.importExcel(request, response, Xddazlgl.class);
  }

}

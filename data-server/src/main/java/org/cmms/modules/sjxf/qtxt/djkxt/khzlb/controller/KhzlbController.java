package org.cmms.modules.sjxf.qtxt.djkxt.khzlb.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdcardUtil;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.sjxf.qtxt.djkxt.khzlb.entity.Khzlb;
import org.cmms.modules.khgl.sjyh.entity.KhzlbVo;
import org.cmms.modules.sjxf.qtxt.djkxt.khzlb.service.IKhzlbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 客户资料表
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户资料表")
@RestController
@RequestMapping("/khzlb/khzlb")
public class KhzlbController extends JeecgController<Khzlb, IKhzlbService> {
	@Autowired
	private IKhzlbService khzlbService;

	/**
	 * 分页列表查询
	 *
	 * @param khzlb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户资料表-分页列表查询")
	@ApiOperation(value="客户资料表-分页列表查询", notes="客户资料表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Khzlb khzlb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Khzlb> queryWrapper = QueryGenerator.initQueryWrapper(khzlb, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IKhzlbService.class,khzlbService,pageNo,pageSize,queryWrapper,"custr_nbr","secur_nbr");
		return Result.ok(pageList);
	}


	/**
	 * 添加
	 *
	 * @param khzlb
	 * @return
	 */
	@AutoLog(value = "客户资料表-添加")
	@ApiOperation(value="客户资料表-添加", notes="客户资料表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Khzlb khzlb) {
		khzlbService.save(khzlb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param khzlb
	 * @return
	 */
	@AutoLog(value = "客户资料表-编辑")
	@ApiOperation(value="客户资料表-编辑", notes="客户资料表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Khzlb khzlb) {
		khzlbService.updateById(khzlb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户资料表-通过id删除")
	@ApiOperation(value="客户资料表-通过id删除", notes="客户资料表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khzlbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户资料表-批量删除")
	@ApiOperation(value="客户资料表-批量删除", notes="客户资料表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khzlbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户资料表-通过id查询")
	@ApiOperation(value="客户资料表-通过id查询", notes="客户资料表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Khzlb khzlb = khzlbService.getById(id);
		return Result.ok(khzlb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khzlb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Khzlb khzlb) {
      return super.exportXls(request, khzlb, Khzlb.class, "客户资料表");
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
      return super.importExcel(request, response, Khzlb.class);
  }

}

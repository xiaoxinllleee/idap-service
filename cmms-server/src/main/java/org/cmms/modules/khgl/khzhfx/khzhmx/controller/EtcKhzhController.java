package org.cmms.modules.khgl.khzhfx.khzhmx.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.khgl.khzhfx.khzhmx.entity.EtcKhzh;
import org.cmms.modules.khgl.khzhfx.khzhmx.service.IEtcKhzhService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: ETC信息明细
 * @Author: jeecg-boot
 * @Date:   2023-04-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="ETC信息明细")
@RestController
@RequestMapping("/khzhmx/etc")
public class EtcKhzhController extends JeecgController<EtcKhzh, IEtcKhzhService> {
	@Autowired
	private IEtcKhzhService etcService;
	
	/**
	 * 分页列表查询
	 *
	 * @param etc
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "ETC信息明细-分页列表查询")
	@ApiOperation(value="ETC信息明细-分页列表查询", notes="ETC信息明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(EtcKhzh etc,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<EtcKhzh> queryWrapper = QueryGenerator.initQueryWrapper(etc, req.getParameterMap());
		Page<EtcKhzh> page = new Page<EtcKhzh>(pageNo, pageSize);
		IPage<EtcKhzh> pageList = etcService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param etc
	 * @return
	 */
	@AutoLog(value = "ETC信息明细-添加")
	@ApiOperation(value="ETC信息明细-添加", notes="ETC信息明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody EtcKhzh etc) {
		etcService.save(etc);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param etc
	 * @return
	 */
	@AutoLog(value = "ETC信息明细-编辑")
	@ApiOperation(value="ETC信息明细-编辑", notes="ETC信息明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody EtcKhzh etc) {
		etcService.updateById(etc);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ETC信息明细-通过id删除")
	@ApiOperation(value="ETC信息明细-通过id删除", notes="ETC信息明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		etcService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "ETC信息明细-批量删除")
	@ApiOperation(value="ETC信息明细-批量删除", notes="ETC信息明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.etcService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ETC信息明细-通过id查询")
	@ApiOperation(value="ETC信息明细-通过id查询", notes="ETC信息明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		EtcKhzh etc = etcService.getById(id);
		return Result.ok(etc);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param etc
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, EtcKhzh etc) {
      return super.exportXls(request, etc, EtcKhzh.class, "ETC信息明细");
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
      return super.importExcel(request, response, EtcKhzh.class);
  }

}

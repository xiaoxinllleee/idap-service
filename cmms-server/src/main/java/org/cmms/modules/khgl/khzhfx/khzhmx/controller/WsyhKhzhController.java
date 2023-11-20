package org.cmms.modules.khgl.khzhfx.khzhmx.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.khgl.khzhfx.khzhmx.entity.WsyhKhzh;
import org.cmms.modules.khgl.khzhfx.khzhmx.service.IWsyhKhzhService;
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
 * @Description: 网上银行信息明细
 * @Author: jeecg-boot
 * @Date:   2023-04-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="网上银行信息明细")
@RestController
@RequestMapping("/khzhmx/wsyh")
public class WsyhKhzhController extends JeecgController<WsyhKhzh, IWsyhKhzhService> {
	@Autowired
	private IWsyhKhzhService wsyhService;
	
	/**
	 * 分页列表查询
	 *
	 * @param wsyh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "网上银行信息明细-分页列表查询")
	@ApiOperation(value="网上银行信息明细-分页列表查询", notes="网上银行信息明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(WsyhKhzh wsyh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<WsyhKhzh> queryWrapper = QueryGenerator.initQueryWrapper(wsyh, req.getParameterMap());
		Page<WsyhKhzh> page = new Page<WsyhKhzh>(pageNo, pageSize);
		IPage<WsyhKhzh> pageList = wsyhService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param wsyh
	 * @return
	 */
	@AutoLog(value = "网上银行信息明细-添加")
	@ApiOperation(value="网上银行信息明细-添加", notes="网上银行信息明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody WsyhKhzh wsyh) {
		wsyhService.save(wsyh);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param wsyh
	 * @return
	 */
	@AutoLog(value = "网上银行信息明细-编辑")
	@ApiOperation(value="网上银行信息明细-编辑", notes="网上银行信息明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody WsyhKhzh wsyh) {
		wsyhService.updateById(wsyh);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "网上银行信息明细-通过id删除")
	@ApiOperation(value="网上银行信息明细-通过id删除", notes="网上银行信息明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		wsyhService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "网上银行信息明细-批量删除")
	@ApiOperation(value="网上银行信息明细-批量删除", notes="网上银行信息明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.wsyhService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "网上银行信息明细-通过id查询")
	@ApiOperation(value="网上银行信息明细-通过id查询", notes="网上银行信息明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		WsyhKhzh wsyh = wsyhService.getById(id);
		return Result.ok(wsyh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param wsyh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, WsyhKhzh wsyh) {
      return super.exportXls(request, wsyh, WsyhKhzh.class, "网上银行信息明细");
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
      return super.importExcel(request, response, WsyhKhzh.class);
  }

}

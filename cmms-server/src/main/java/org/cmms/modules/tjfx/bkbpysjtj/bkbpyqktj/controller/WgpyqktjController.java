package org.cmms.modules.tjfx.bkbpysjtj.bkbpyqktj.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.modules.tjfx.bkbpysjtj.bkbpyqktj.entity.Wgpyqktj;
import org.cmms.modules.tjfx.bkbpysjtj.bkbpyqktj.service.IwgpyqktjService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.util.EtlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 评议情况统计(网格)
 * @Author: jeecg-boot
 * @Date:   2022-11-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="评议情况统计(网格)")
@RestController
@RequestMapping("/bkbpyqktj/wgpyqktj")
public class WgpyqktjController extends JeecgController<Wgpyqktj, IwgpyqktjService> {
	@Autowired
	private IwgpyqktjService wgpyqktjService;
	
	/**
	 * 分页列表查询
	 *
	 * @param wgpyqktj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "评议情况统计(网格)-分页列表查询")
	@ApiOperation(value="评议情况统计(网格)-分页列表查询", notes="评议情况统计(网格)-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Wgpyqktj wgpyqktj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Wgpyqktj> queryWrapper = QueryGenerator.initQueryWrapper(wgpyqktj, req.getParameterMap());
		Page<Wgpyqktj> page = new Page<Wgpyqktj>(pageNo, pageSize);
		IPage<Wgpyqktj> pageList = wgpyqktjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param wgpyqktj
	 * @return
	 */
	@AutoLog(value = "评议情况统计(网格)-添加")
	@ApiOperation(value="评议情况统计(网格)-添加", notes="评议情况统计(网格)-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Wgpyqktj wgpyqktj) {
		wgpyqktjService.save(wgpyqktj);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param wgpyqktj
	 * @return
	 */
	@AutoLog(value = "评议情况统计(网格)-编辑")
	@ApiOperation(value="评议情况统计(网格)-编辑", notes="评议情况统计(网格)-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Wgpyqktj wgpyqktj) {
		wgpyqktjService.updateById(wgpyqktj);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评议情况统计(网格)-通过id删除")
	@ApiOperation(value="评议情况统计(网格)-通过id删除", notes="评议情况统计(网格)-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		wgpyqktjService.removeById(id);
		return Result.ok("删除成功!");
	}


	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/init")
	 public Result<?> init(@RequestBody JSONObject jsonObject) {
		 String sjrq = jsonObject.getString("sjrq");
		 Result result = new Result<>();
		 try {
			 wgpyqktjService.initPyxx(sjrq.replace("-", ""));
			 result.setSuccess(true);
			 return result;
		 } catch (Exception e) {
			 System.out.println(e);
			 log.error("提取失败", e.getMessage());
			 result.setSuccess(false);
		 }
		 return result;
	 }

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "评议情况统计(网格)-批量删除")
	@ApiOperation(value="评议情况统计(网格)-批量删除", notes="评议情况统计(网格)-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.wgpyqktjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评议情况统计(网格)-通过id查询")
	@ApiOperation(value="评议情况统计(网格)-通过id查询", notes="评议情况统计(网格)-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Wgpyqktj wgpyqktj = wgpyqktjService.getById(id);
		return Result.ok(wgpyqktj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param wgpyqktj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Wgpyqktj wgpyqktj) {
      return super.exportXls(request, wgpyqktj, Wgpyqktj.class, "评议情况统计(网格)");
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
      return super.importExcel(request, response, Wgpyqktj.class);
  }

}

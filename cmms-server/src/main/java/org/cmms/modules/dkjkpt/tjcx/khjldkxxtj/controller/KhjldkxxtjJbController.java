package org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.entity.KhjldkxxtjJb;
import org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.service.IKhjldkxxtjJbService;
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
 * @Description: 客户经理贷款信息统计季报
 * @Author: jeecg-boot
 * @Date:   2022-09-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户经理贷款信息统计季报")
@RestController
@RequestMapping("/khjldkxxtj/khjldkxxtjjb")
public class KhjldkxxtjJbController extends JeecgController<KhjldkxxtjJb, IKhjldkxxtjJbService> {
	@Autowired
	private IKhjldkxxtjJbService khjldkxxtjjbService;

	/**
	 * 分页列表查询
	 *
	 * @param khjldkxxtjjb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户经理贷款信息统计季报-分页列表查询")
	@ApiOperation(value="客户经理贷款信息统计季报-分页列表查询", notes="客户经理贷款信息统计季报-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhjldkxxtjJb khjldkxxtjjb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<KhjldkxxtjJb> queryWrapper = QueryGenerator.initQueryWrapper(khjldkxxtjjb, req.getParameterMap());
		Page<KhjldkxxtjJb> page = new Page<KhjldkxxtjJb>(pageNo, pageSize);
		IPage<KhjldkxxtjJb> pageList = khjldkxxtjjbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param khjldkxxtjjb
	 * @return
	 */
	@AutoLog(value = "客户经理贷款信息统计季报-添加")
	@ApiOperation(value="客户经理贷款信息统计季报-添加", notes="客户经理贷款信息统计季报-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhjldkxxtjJb khjldkxxtjjb) {
		khjldkxxtjjbService.save(khjldkxxtjjb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param khjldkxxtjjb
	 * @return
	 */
	@AutoLog(value = "客户经理贷款信息统计季报-编辑")
	@ApiOperation(value="客户经理贷款信息统计季报-编辑", notes="客户经理贷款信息统计季报-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhjldkxxtjJb khjldkxxtjjb) {
		khjldkxxtjjbService.updateById(khjldkxxtjjb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理贷款信息统计季报-通过id删除")
	@ApiOperation(value="客户经理贷款信息统计季报-通过id删除", notes="客户经理贷款信息统计季报-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khjldkxxtjjbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户经理贷款信息统计季报-批量删除")
	@ApiOperation(value="客户经理贷款信息统计季报-批量删除", notes="客户经理贷款信息统计季报-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khjldkxxtjjbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理贷款信息统计季报-通过id查询")
	@ApiOperation(value="客户经理贷款信息统计季报-通过id查询", notes="客户经理贷款信息统计季报-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		KhjldkxxtjJb khjldkxxtjjb = khjldkxxtjjbService.getById(id);
		return Result.ok(khjldkxxtjjb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khjldkxxtjjb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KhjldkxxtjJb khjldkxxtjjb) {
      return super.exportXls(request, khjldkxxtjjb, KhjldkxxtjJb.class, "客户经理贷款信息统计季报");
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
      return super.importExcel(request, response, KhjldkxxtjJb.class);
  }

	 /**
	  * 提取
	  * @param object
	  * @return
	  */
	 @RequestMapping(value = "/init", method = RequestMethod.PUT)
	 public Result<?> InitData(@RequestBody JSONObject object) {
		 System.out.println("tjrq-----"+object.getString("tjrq"));
		 String tjrq = object.getString("tjrq");
		 tjrq = tjrq.replace("-", "");
		 try {
			 khjldkxxtjjbService.InitData(tjrq);
		 } catch (Exception e) {
			 log.error("提取失败！",e.getMessage());
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功！");
	 }
}

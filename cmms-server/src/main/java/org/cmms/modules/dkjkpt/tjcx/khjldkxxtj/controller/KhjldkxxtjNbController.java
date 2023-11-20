package org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.entity.KhjldkxxtjNb;
import org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.service.IKhjldkxxtjNbService;
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
 * @Description: 客户经理贷款信息统计年报
 * @Author: jeecg-boot
 * @Date:   2022-09-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户经理贷款信息统计年报")
@RestController
@RequestMapping("/khjldkxxtj/khjldkxxtjnb")
public class KhjldkxxtjNbController extends JeecgController<KhjldkxxtjNb, IKhjldkxxtjNbService> {
	@Autowired
	private IKhjldkxxtjNbService khjldkxxtjnbService;

	/**
	 * 分页列表查询
	 *
	 * @param khjldkxxtjnb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户经理贷款信息统计年报-分页列表查询")
	@ApiOperation(value="客户经理贷款信息统计年报-分页列表查询", notes="客户经理贷款信息统计年报-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhjldkxxtjNb khjldkxxtjnb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<KhjldkxxtjNb> queryWrapper = QueryGenerator.initQueryWrapper(khjldkxxtjnb, req.getParameterMap());
		Page<KhjldkxxtjNb> page = new Page<KhjldkxxtjNb>(pageNo, pageSize);
		IPage<KhjldkxxtjNb> pageList = khjldkxxtjnbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param khjldkxxtjnb
	 * @return
	 */
	@AutoLog(value = "客户经理贷款信息统计年报-添加")
	@ApiOperation(value="客户经理贷款信息统计年报-添加", notes="客户经理贷款信息统计年报-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhjldkxxtjNb khjldkxxtjnb) {
		khjldkxxtjnbService.save(khjldkxxtjnb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param khjldkxxtjnb
	 * @return
	 */
	@AutoLog(value = "客户经理贷款信息统计年报-编辑")
	@ApiOperation(value="客户经理贷款信息统计年报-编辑", notes="客户经理贷款信息统计年报-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhjldkxxtjNb khjldkxxtjnb) {
		khjldkxxtjnbService.updateById(khjldkxxtjnb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理贷款信息统计年报-通过id删除")
	@ApiOperation(value="客户经理贷款信息统计年报-通过id删除", notes="客户经理贷款信息统计年报-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khjldkxxtjnbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户经理贷款信息统计年报-批量删除")
	@ApiOperation(value="客户经理贷款信息统计年报-批量删除", notes="客户经理贷款信息统计年报-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khjldkxxtjnbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理贷款信息统计年报-通过id查询")
	@ApiOperation(value="客户经理贷款信息统计年报-通过id查询", notes="客户经理贷款信息统计年报-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		KhjldkxxtjNb khjldkxxtjnb = khjldkxxtjnbService.getById(id);
		return Result.ok(khjldkxxtjnb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khjldkxxtjnb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KhjldkxxtjNb khjldkxxtjnb) {
      return super.exportXls(request, khjldkxxtjnb, KhjldkxxtjNb.class, "客户经理贷款信息统计年报");
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
      return super.importExcel(request, response, KhjldkxxtjNb.class);
  }

	 /**
	  * 提取
	  * @param object
	  * @return
	  */
	 @RequestMapping(value = "/init", method = RequestMethod.PUT)
	 public Result<?> InitData(@RequestBody JSONObject object) {
		 System.out.println("tjnf-----"+object.getString("tjnf"));
		 String tjnf = object.getString("tjnf");
		 tjnf = tjnf.replace("-", "");
		 try {
			 khjldkxxtjnbService.InitData(tjnf);
		 } catch (Exception e) {
			 log.error("提取失败！",e.getMessage());
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功！");
	 }

}

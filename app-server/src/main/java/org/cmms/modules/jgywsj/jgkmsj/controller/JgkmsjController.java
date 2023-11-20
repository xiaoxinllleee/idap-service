package org.cmms.modules.jgywsj.jgkmsj.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.MoneyUtil;
import org.cmms.modules.jgywsj.jgkmsj.entity.JgkmsjCk;
import org.cmms.modules.jgywsj.jgkmsj.entity.JgkmsjCkResult;
import org.cmms.modules.jgywsj.jgkmsj.entity.TbTjfxJgkmsj;
import org.cmms.modules.jgywsj.jgkmsj.service.IJgkmsjService;
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
 * @Description: 机构科目数据
 * @Author: jeecg-boot
 * @Date:   2021-05-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags="机构科目数据")
@RestController
@RequestMapping("/mobile/jgkmsj")
public class JgkmsjController extends JeecgController<TbTjfxJgkmsj, IJgkmsjService> {
	@Autowired
	private IJgkmsjService jgkmsjService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tbTjfxJgkmsj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "机构科目数据-分页列表查询")
	@ApiOperation(value="机构科目数据-分页列表查询", notes="机构科目数据-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TbTjfxJgkmsj tbTjfxJgkmsj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TbTjfxJgkmsj> queryWrapper = QueryGenerator.initQueryWrapper(tbTjfxJgkmsj, req.getParameterMap());
		Page<TbTjfxJgkmsj> page = new Page<TbTjfxJgkmsj>(pageNo, pageSize);
		IPage<TbTjfxJgkmsj> pageList = jgkmsjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param tbTjfxJgkmsj
	 * @return
	 */
	@AutoLog(value = "机构科目数据-添加")
	@ApiOperation(value="机构科目数据-添加", notes="机构科目数据-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TbTjfxJgkmsj tbTjfxJgkmsj) {
		jgkmsjService.save(tbTjfxJgkmsj);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param tbTjfxJgkmsj
	 * @return
	 */
	@AutoLog(value = "机构科目数据-编辑")
	@ApiOperation(value="机构科目数据-编辑", notes="机构科目数据-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TbTjfxJgkmsj tbTjfxJgkmsj) {
		jgkmsjService.updateById(tbTjfxJgkmsj);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "机构科目数据-通过id删除")
	@ApiOperation(value="机构科目数据-通过id删除", notes="机构科目数据-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		jgkmsjService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "机构科目数据-批量删除")
	@ApiOperation(value="机构科目数据-批量删除", notes="机构科目数据-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.jgkmsjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "机构科目数据-通过id查询")
	@ApiOperation(value="机构科目数据-通过id查询", notes="机构科目数据-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TbTjfxJgkmsj tbTjfxJgkmsj = jgkmsjService.getById(id);
		return Result.ok(tbTjfxJgkmsj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tbTjfxJgkmsj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, TbTjfxJgkmsj tbTjfxJgkmsj) {
      return super.exportXls(request, tbTjfxJgkmsj, TbTjfxJgkmsj.class, "机构科目数据");
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
      return super.importExcel(request, response, TbTjfxJgkmsj.class);
  }

  @RequestMapping(value = "/getJgckkmxx", method = RequestMethod.GET)
  public Result<?> getJgckkmxx(@RequestParam(name="zzbz") String zzbz, HttpServletRequest request, HttpServletResponse response) {
  	  List<Date> latestTjrq = jgkmsjService.getLatestTjrq(zzbz);
	  if (latestTjrq.isEmpty()) {
	  	  return Result.error("未获取到任何数据");
	  }
	  Date maxDate = latestTjrq.get(0);

      //获取年初数据
	  String ncrq = DateUtil.formatDateTime("yyyy-MM-dd", DateUtil.addDays(DateUtil.getYBeginDay(maxDate), -1));
	  JgkmsjCk ncsj = jgkmsjService.queryCkxxByTjrqAndZzbz(ncrq, zzbz);

	  //获取月初数据
	  String ycrq = DateUtil.formatDateTime("yyyy-MM-dd", DateUtil.addDays(DateUtil.getMonthBeginDay(maxDate), -1));
	  JgkmsjCk ycsj = jgkmsjService.queryCkxxByTjrqAndZzbz(ycrq, zzbz);
	  JgkmsjCkResult jgkmsjCkResult = new JgkmsjCkResult();
	  jgkmsjCkResult.setZzbz(zzbz);
	  jgkmsjCkResult.setCkywNc(ncsj);
	  jgkmsjCkResult.setCkywYc(ycsj);
	  //获取最新日期前五天的数据
	  for (int i = 0; i < latestTjrq.size(); i++) {
	  	  Date date = latestTjrq.get(i);
		  JgkmsjCk jgkmsjCk = jgkmsjService.queryCkxxByTjrqAndZzbz(DateUtil.format(date, "yyyy-MM-dd"), zzbz);
		  switch(i) {
			  case 0 :
			  	  jgkmsjCkResult.setCkywTheDay(jgkmsjCk);
				  jgkmsjCkResult.setCkyj(MoneyUtil.format(jgkmsjCk.getCkye()));
			  	  break;
			  case 1 :
				  jgkmsjCkResult.setCkywFrontOneDay(jgkmsjCk);
				  break;
			  case 2 :
				  jgkmsjCkResult.setCkywFrontTwoDay(jgkmsjCk);
				  break;
			  case 3 :
				  jgkmsjCkResult.setCkywFrontThreeDay(jgkmsjCk);
				  break;
			  case 4 :
				  jgkmsjCkResult.setCkywFrontFourDay(jgkmsjCk);
				  break;
			  default:
				  break;
		  }
	  }
	  return Result.ok(jgkmsjCkResult);
  }

}

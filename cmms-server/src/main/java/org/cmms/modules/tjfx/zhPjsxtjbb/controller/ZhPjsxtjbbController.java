package org.cmms.modules.tjfx.zhPjsxtjbb.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.khgl.nh.entity.NhPjsxxx;
import org.cmms.modules.tjfx.pjsxtjbb.vo.NhPjsxxxMx;
import org.cmms.modules.tjfx.zhPjsxtjbb.Vo.ZhPjsxxxMx;
import org.cmms.modules.tjfx.zhPjsxtjbb.entity.ZhPjsxtjbb;
import org.cmms.modules.tjfx.zhPjsxtjbb.service.IZhPjsxtjbbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 支行评级授信统计报表
 * @Author: jeecg-boot
 * @Date:   2023-01-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行评级授信统计报表")
@RestController
@RequestMapping("/zhPjsxtjbb/zhPjsxtjbb")
public class ZhPjsxtjbbController extends JeecgController<ZhPjsxtjbb, IZhPjsxtjbbService> {
	@Autowired
	private IZhPjsxtjbbService zhPjsxtjbbService;

	/**
	 * 分页列表查询
	 *
	 * @param zhPjsxtjbb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行评级授信统计报表-分页列表查询")
	@ApiOperation(value="支行评级授信统计报表-分页列表查询", notes="支行评级授信统计报表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZhPjsxtjbb zhPjsxtjbb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZhPjsxtjbb> queryWrapper = QueryGenerator.initQueryWrapper(zhPjsxtjbb, req.getParameterMap());
		Page<ZhPjsxtjbb> page = new Page<ZhPjsxtjbb>(pageNo, pageSize);
		IPage<ZhPjsxtjbb> pageList = zhPjsxtjbbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}


	 /**
	  * 评级授信统计报表明细
	  * @param
	  * @param
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @GetMapping(value = "/queryPageListMx")
	 public Result<?> queryPageListMx( String sszh, String sjrq,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		 DateTime dateTime = DateUtil.endOfDay(DateUtil.parse(sjrq));
		 List<ZhPjsxxxMx> pageList = zhPjsxtjbbService.zhPjsxtjbbMx(sszh,dateTime.toString());
		 return Result.ok(pageList);
	 }


	 @RequestMapping(value = "/exportZfsjmxXls")
	 public ModelAndView exportZfsjmxXls( String sszh,String sjrq,
										  HttpServletRequest request) {
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 List<ZhPjsxxxMx> nhfcxxList = zhPjsxtjbbService.zhPjsxtjbbMx(sszh,sjrq);
		 List<ZhPjsxxxMx> exportList = new ArrayList<>();
		 nhfcxxList.forEach(e -> {
			 ZhPjsxxxMx zhPjsxxxMx = new ZhPjsxxxMx();
			 BeanUtil.copyProperties(e, zhPjsxxxMx);
			 exportList.add(zhPjsxxxMx);
		 });

		 String title = "支行评级授信统计";
		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, ZhPjsxxxMx.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
	 }



	/**
	 * 添加
	 *
	 * @param zhPjsxtjbb
	 * @return
	 */
	@AutoLog(value = "支行评级授信统计报表-添加")
	@ApiOperation(value="支行评级授信统计报表-添加", notes="支行评级授信统计报表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZhPjsxtjbb zhPjsxtjbb) {
		zhPjsxtjbbService.save(zhPjsxtjbb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zhPjsxtjbb
	 * @return
	 */
	@AutoLog(value = "支行评级授信统计报表-编辑")
	@ApiOperation(value="支行评级授信统计报表-编辑", notes="支行评级授信统计报表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZhPjsxtjbb zhPjsxtjbb) {
		zhPjsxtjbbService.updateById(zhPjsxtjbb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行评级授信统计报表-通过id删除")
	@ApiOperation(value="支行评级授信统计报表-通过id删除", notes="支行评级授信统计报表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zhPjsxtjbbService.removeById(id);
		return Result.ok("删除成功!");
	}


	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "支行评级授信统计报表-批量删除")
	@ApiOperation(value="支行评级授信统计报表-批量删除", notes="支行评级授信统计报表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zhPjsxtjbbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行评级授信统计报表-通过id查询")
	@ApiOperation(value="支行评级授信统计报表-通过id查询", notes="支行评级授信统计报表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZhPjsxtjbb zhPjsxtjbb = zhPjsxtjbbService.getById(id);
		return Result.ok(zhPjsxtjbb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zhPjsxtjbb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ZhPjsxtjbb zhPjsxtjbb) {
      return super.exportXls(request, zhPjsxtjbb, ZhPjsxtjbb.class, "支行评级授信统计");
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
      return super.importExcel(request, response, ZhPjsxtjbb.class);
  }

}

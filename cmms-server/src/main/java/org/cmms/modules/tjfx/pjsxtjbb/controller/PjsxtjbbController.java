package org.cmms.modules.tjfx.pjsxtjbb.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSONObject;
import com.mchange.lang.IntegerUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.SqlInjectionUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.nh.entity.NhPjsxxx;
import org.cmms.modules.khgl.nh.service.INhPjsxxxService;
import org.cmms.modules.tjfx.pjsxtjbb.entity.Pjsxtjbb;
import org.cmms.modules.tjfx.pjsxtjbb.service.IPjsxtjbbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.pjsxtjbb.vo.NhPjsxxxMx;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 评级授信统计报表
 * @Author: jeecg-boot
 * @Date:   2023-01-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="评级授信统计报表")
@RestController
@RequestMapping("/pjsxtjbb/pjsxtjbb")
public class PjsxtjbbController extends JeecgController<Pjsxtjbb, IPjsxtjbbService> {
	@Autowired
	private IPjsxtjbbService pjsxtjbbService;
	@Autowired
	private INhPjsxxxService nhfcxxService;

	/**
	 * 分页列表查询
	 *
	 * @param pjsxtjbb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "评级授信统计报表-分页列表查询")
	@ApiOperation(value="评级授信统计报表-分页列表查询", notes="评级授信统计报表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Pjsxtjbb pjsxtjbb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String sswg = pjsxtjbb.getSswg();
		pjsxtjbb.setSswg(null);
		QueryWrapper<Pjsxtjbb> queryWrapper = QueryGenerator.initQueryWrapper(pjsxtjbb, req.getParameterMap());
		if (StringUtils.isNotEmpty(sswg)) {
			String sqlSswg = "select wgbh from yxdygl_main t where wgbh ='" + sswg + "' or parent_id='" + sswg + "'";
			queryWrapper.inSql("sswg", sqlSswg);
		}
		Page<Pjsxtjbb> page = new Page<Pjsxtjbb>(pageNo, pageSize);
		IPage<Pjsxtjbb> pageList = pjsxtjbbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 评级授信统计报表明细
	  * @param nhPjsxxx
	  * @param sswg
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @GetMapping(value = "/queryPageListMx")
	 public Result<?> queryPageListMx(NhPjsxxx nhPjsxxx, String sswg,String sjrq,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		 Page<NhPjsxxxMx> page = new Page<NhPjsxxxMx>(pageNo, pageSize);
		 DateTime dateTime = DateUtil.endOfDay(DateUtil.parse(sjrq));
		 List<NhPjsxxxMx> pageList = pjsxtjbbService.pjsxtjbbExl(sswg,dateTime.toString());
		 return Result.ok(pageList);
	 }


	 @RequestMapping(value = "/exportZfsjmxXls")
	 public ModelAndView exportZfsjmxXls( String sswg,String sjrq,
										 HttpServletRequest request) {
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 List<NhPjsxxxMx> nhfcxxList = service.pjsxtjbbExl(sswg,sjrq);
		 List<NhPjsxxxMx> exportList = new ArrayList<>();
		 nhfcxxList.forEach(e -> {
			 NhPjsxxxMx nhPjsxxxMx = new NhPjsxxxMx();
			 BeanUtil.copyProperties(e, nhPjsxxxMx);
			 exportList.add(nhPjsxxxMx);
		 });

		 String title = "评级授信统计";
		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, NhPjsxxxMx.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
	 }
	/**
	 * 添加
	 *
	 * @param pjsxtjbb
	 * @return
	 */
	@AutoLog(value = "评级授信统计报表-添加")
	@ApiOperation(value="评级授信统计报表-添加", notes="评级授信统计报表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Pjsxtjbb pjsxtjbb) {
		pjsxtjbbService.save(pjsxtjbb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param pjsxtjbb
	 * @return
	 */
	@AutoLog(value = "评级授信统计报表-编辑")
	@ApiOperation(value="评级授信统计报表-编辑", notes="评级授信统计报表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Pjsxtjbb pjsxtjbb) {
		pjsxtjbbService.updateById(pjsxtjbb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评级授信统计报表-通过id删除")
	@ApiOperation(value="评级授信统计报表-通过id删除", notes="评级授信统计报表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pjsxtjbbService.removeById(id);
		return Result.ok("删除成功!");
	}
	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/init", method = RequestMethod.PUT)
	 public Result<?> init(@RequestBody JSONObject jsonObject) {
		 String sjrq = jsonObject.getString("sjrq");
		 System.out.println(sjrq+"========sjrq");
		 Result result = new Result<>();
		 try {
			 pjsxtjbbService.init(sjrq.replace("-", ""));
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
	@AutoLog(value = "评级授信统计报表-批量删除")
	@ApiOperation(value="评级授信统计报表-批量删除", notes="评级授信统计报表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pjsxtjbbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评级授信统计报表-通过id查询")
	@ApiOperation(value="评级授信统计报表-通过id查询", notes="评级授信统计报表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Pjsxtjbb pjsxtjbb = pjsxtjbbService.getById(id);
		return Result.ok(pjsxtjbb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param pjsxtjbb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Pjsxtjbb pjsxtjbb) {
      return super.exportXls(request, pjsxtjbb, Pjsxtjbb.class, "评级授信统计");
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
      return super.importExcel(request, response, Pjsxtjbb.class);
  }

}

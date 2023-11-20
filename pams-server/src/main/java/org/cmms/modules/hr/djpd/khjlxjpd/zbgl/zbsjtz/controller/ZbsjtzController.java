package org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjtz.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdsjxgl.entity.Pdsjxgl;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.entity.Khjljcsjsz;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjtz.entity.Zbsjtz;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjtz.entity.ZbsjtzVO;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjtz.service.IZbsjtzService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjtz.verify.ZbsjtzImportVerify;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 指标数据调整
 * @Author: jeecg-boot
 * @Date:   2021-09-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags="指标数据调整")
@RestController
@RequestMapping("/zbsjtz/zbsjtz")
public class ZbsjtzController extends JeecgController<Zbsjtz, IZbsjtzService> {
	@Autowired
	private IZbsjtzService zbsjtzService;
	@Autowired
	private ZbsjtzImportVerify zbsjtzImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param zbsjtz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "指标数据调整-分页列表查询")
	@ApiOperation(value="指标数据调整-分页列表查询", notes="指标数据调整-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zbsjtz zbsjtz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zbsjtz> queryWrapper = QueryGenerator.initQueryWrapper(zbsjtz, req.getParameterMap());
		Page<Zbsjtz> page = new Page<Zbsjtz>(pageNo, pageSize);
		IPage<Zbsjtz> pageList = zbsjtzService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param zbsjtz
	 * @return
	 */
	@AutoLog(value = "指标数据调整-添加")
	@ApiOperation(value="指标数据调整-添加", notes="指标数据调整-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zbsjtz zbsjtz) {
		QueryWrapper<Zbsjtz> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("pdzq",zbsjtz.getPdzq());
		queryWrapper.eq("pdrq",zbsjtz.getPdrq());
		queryWrapper.eq("zzbz",zbsjtz.getZzbz());
		queryWrapper.eq("yggh",zbsjtz.getYggh());
		queryWrapper.eq("gwbz",zbsjtz.getGwbz());
		queryWrapper.eq("sjxid",zbsjtz.getSjxid());
		Zbsjtz check = zbsjtzService.getOne(queryWrapper);
		if (check != null){
			return Result.error("该记录已经存在，不能重复添加！");
		}
		zbsjtz.setLrbz(1);
		zbsjtz.setLrr(getUsername());
		zbsjtz.setLrsj(new Date());
		zbsjtzService.save(zbsjtz);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zbsjtz
	 * @return
	 */
	@AutoLog(value = "指标数据调整-编辑")
	@ApiOperation(value="指标数据调整-编辑", notes="指标数据调整-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zbsjtz zbsjtz) {
		QueryWrapper<Zbsjtz> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("pdzq",zbsjtz.getPdzq());
		queryWrapper.eq("pdrq",zbsjtz.getPdrq());
		queryWrapper.eq("zzbz",zbsjtz.getZzbz());
		queryWrapper.eq("yggh",zbsjtz.getYggh());
		queryWrapper.eq("gwbz",zbsjtz.getGwbz());
		queryWrapper.eq("sjxid",zbsjtz.getSjxid());
		zbsjtz.setLrsj(new Date());
		zbsjtz.setLrr(getUsername());
		zbsjtz.setLrbz(2);
		zbsjtzService.update(zbsjtz,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "指标数据调整-通过id删除")
	@ApiOperation(value="指标数据调整-通过id删除", notes="指标数据调整-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("pdzq")String pdzq,@Param("pdrq")String pdrq,@Param("zzbz")String zzbz,
							@Param("yggh")String yggh,@Param("gwbz")String gwbz,@Param("sjxid")String sjxid) {
		QueryWrapper<Zbsjtz> queryWrapper = new QueryWrapper<Zbsjtz>();
		queryWrapper.eq("pdzq",pdzq);
		queryWrapper.eq("pdrq", DateUtil.parse(pdrq));
		queryWrapper.eq("zzbz",zzbz);
		queryWrapper.eq("yggh",yggh);
		queryWrapper.eq("gwbz",gwbz);
		queryWrapper.eq("sjxid",sjxid);
		zbsjtzService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}




	 /**
	  * 获取认领列表/查找带回基本信息
	  *
	  * @param jsonObject
	  * @return
	  */
	 @AutoLog(value = "认领列表")
	 @ApiOperation(value = "认领", notes = "待分配存款帐号管理")
	 @PostMapping(value = "/getListClaim")
	 public Result<?> getListClaim(@RequestBody JSONObject jsonObject) {
		 System.out.println(jsonObject);
		 String pdzq = jsonObject.getString("pdzq");
		 String pdrq = jsonObject.getString("pdrq");
		 String zzbz = jsonObject.getString("zzbz");
		 String yggh = jsonObject.getString("yggh");
		 String khjlbz = jsonObject.getString("khjlbz");
		 String ygxm = jsonObject.getString("ygxm");
		 List<Khjljcsjsz> list = service.getListClaim(pdzq, pdrq, zzbz,yggh,khjlbz,ygxm);
		 return Result.ok(list);
	 }

	 /**
	  * 获取认领列表/查找带回指标id
	  *
	  * @param jsonObject
	  * @return
	  */
	 @AutoLog(value = "查找带回")
	 @ApiOperation(value = "查找带回", notes = "查找带回")
	 @PostMapping(value = "/getListZbid")
	 public Result<?> getListZbid(@RequestBody JSONObject jsonObject) {
		 System.out.println(jsonObject);
		 String sjxid = jsonObject.getString("sjxid");
		 String sjxmc = jsonObject.getString("sjxmc");
		 String sjxwd = jsonObject.getString("sjxwd");
		 List<Pdsjxgl> list = service.getListZbid(sjxid, sjxmc, sjxwd);
		 return Result.ok(list);
	 }

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "指标数据调整-批量删除")
	@ApiOperation(value="指标数据调整-批量删除", notes="指标数据调整-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zbsjtzService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "指标数据调整-通过id查询")
	@ApiOperation(value="指标数据调整-通过id查询", notes="指标数据调整-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zbsjtz zbsjtz = zbsjtzService.getById(id);
		return Result.ok(zbsjtz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zbsjtz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zbsjtz zbsjtz) {
      return super.exportXls(request, zbsjtz, Zbsjtz.class, "指标数据调整");
  }


	 /**
	  * 导入模板
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "指标数据调整导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, ZbsjtzVO.class);
		 ExportParams exportParams = new ExportParams("指标数据调整导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
	 }



  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcelByTemplate(jsonObject, request, response, Zbsjtz.class,ZbsjtzVO.class,zbsjtzImportVerify);
	 }

}

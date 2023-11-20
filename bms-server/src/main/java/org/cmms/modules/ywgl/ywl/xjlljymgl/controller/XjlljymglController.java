package org.cmms.modules.ywgl.ywl.xjlljymgl.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ywgl.ywl.xjlljymgl.entity.Xjlljymgl;
import org.cmms.modules.ywgl.ywl.xjlljymgl.entity.XjlljymglVO;
import org.cmms.modules.ywgl.ywl.xjlljymgl.service.IXjlljymglService;
import org.cmms.modules.ywgl.ywl.xjlljymgl.verify.XjlljymglImportVerify;
import org.cmms.modules.ywgl.ywl.ywlbgl.entity.Ywlbgl;
import org.cmms.modules.ywgl.ywl.ywlbgl.service.IYwlbglService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 现金流量交易码管理
 * @Author: jeecg-boot
 * @Date:   2021-09-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="现金流量交易码管理")
@RestController
@RequestMapping("/xjlljymgl/xjlljymgl")
public class XjlljymglController extends JeecgController<Xjlljymgl, IXjlljymglService> {
	@Autowired
	private IXjlljymglService xjlljymglService;
	@Autowired
	private XjlljymglImportVerify xjlljymglImportVerify;

	 @Autowired
	 private IYwlbglService ywlbglService;

	/**
	 * 分页列表查询
	 *
	 * @param xjlljymgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "现金流量交易码管理-分页列表查询")
	@ApiOperation(value="现金流量交易码管理-分页列表查询", notes="现金流量交易码管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Xjlljymgl xjlljymgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Xjlljymgl> queryWrapper = QueryGenerator.initQueryWrapper(xjlljymgl, req.getParameterMap());
		Page<Xjlljymgl> page = new Page<Xjlljymgl>(pageNo, pageSize);
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IXjlljymglService.class,xjlljymglService,pageNo,pageSize,queryWrapper,"jym");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param xjlljymgl
	 * @return
	 */
	@AutoLog(value = "现金流量交易码管理-添加")
	@ApiOperation(value="现金流量交易码管理-添加", notes="现金流量交易码管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Xjlljymgl xjlljymgl) {
		QueryWrapper<Xjlljymgl> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("jym",xjlljymgl.getJym());
		Xjlljymgl xjlljymgl1 = xjlljymglService.getOne(queryWrapper);
		if (xjlljymgl1 != null){
			return Result.error("现金流量交易码已存在！");
		}
		QueryWrapper<Ywlbgl> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.eq("dyjym",xjlljymgl.getJym());
		List<Ywlbgl> list = ywlbglService.list(queryWrapper1);
		if (list.isEmpty()){
			return Result.error("对应交易码不存在！");
		}
		xjlljymgl.setLrbz(1);
		xjlljymglService.save(xjlljymgl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param xjlljymgl
	 * @return
	 */
	@AutoLog(value = "现金流量交易码管理-编辑")
	@ApiOperation(value="现金流量交易码管理-编辑", notes="现金流量交易码管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Xjlljymgl xjlljymgl) {
		xjlljymgl.setLrbz(2);
		QueryWrapper<Xjlljymgl> queryWrapper = new QueryWrapper<Xjlljymgl>();
		queryWrapper.eq("jym",xjlljymgl.getJym());
		xjlljymglService.update(xjlljymgl,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "现金流量交易码管理-通过id删除")
	@ApiOperation(value="现金流量交易码管理-通过id删除", notes="现金流量交易码管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("jym")String jym) {
		QueryWrapper<Xjlljymgl> queryWrapper = new QueryWrapper<Xjlljymgl>();
		queryWrapper.eq("jym",jym);
		xjlljymglService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "现金流量交易码管理-批量删除")
	@ApiOperation(value="现金流量交易码管理-批量删除", notes="现金流量交易码管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.xjlljymglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "现金流量交易码管理-通过id查询")
	@ApiOperation(value="现金流量交易码管理-通过id查询", notes="现金流量交易码管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Xjlljymgl xjlljymgl = xjlljymglService.getById(id);
		return Result.ok(xjlljymgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param xjlljymgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Xjlljymgl xjlljymgl) {
      return super.exportXls(request, xjlljymgl, Xjlljymgl.class, "现金流量交易码管理");
  }

	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 //return super.exportTemplateXls(SsglVO.class, "诉讼管理导入模板");
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "现金流量交易码管理导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, XjlljymglVO.class);
		 ExportParams exportParams = new ExportParams("现金流量交易码管理导入模板", "模板信息");
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
      return super.importExcelByTemplate(jsonObject, request, response, Xjlljymgl.class,XjlljymglVO.class,xjlljymglImportVerify);
  }

}

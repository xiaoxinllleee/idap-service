package org.cmms.modules.khlc.bmgpgl.controller;

import java.sql.Timestamp;
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
import org.cmms.modules.khlc.bmgpgl.entity.Bmgpgl;
import org.cmms.modules.khlc.bmgpgl.entity.BmgpglVO;
import org.cmms.modules.khlc.bmgpgl.entity.HrPostBmxx;
import org.cmms.modules.khlc.bmgpgl.service.IBmgpglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khlc.bmgpgl.verify.BmgpglImportVerify;
import org.cmms.modules.khlc.jczbgl.entity.HrPostOrg;
import org.cmms.modules.khlc.khfagl.entity.ErpAssessAljc;
import org.cmms.modules.khlc.khfagl.entity.ErpAssessAljcVO;
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
 * @Description: 部门挂片管理
 * @Author: jeecg-boot
 * @Date:   2023-03-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="部门挂片管理")
@RestController
@RequestMapping("/bmgpgl/bmgpgl")
public class BmgpglController extends JeecgController<Bmgpgl, IBmgpglService> {
	@Autowired
	private IBmgpglService bmgpglService;
	@Autowired
	private BmgpglImportVerify bmgpglImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param bmgpgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "部门挂片管理-分页列表查询")
	@ApiOperation(value="部门挂片管理-分页列表查询", notes="部门挂片管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Bmgpgl bmgpgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Bmgpgl> queryWrapper = QueryGenerator.initQueryWrapper(bmgpgl, req.getParameterMap());
		Page<Bmgpgl> page = new Page<Bmgpgl>(pageNo, pageSize);
		IPage<Bmgpgl> pageList = bmgpglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 @GetMapping(value = "/bmbzList")
	 public Result<?> bmbzList() {
		 List<HrPostBmxx> list = bmgpglService.bmxx();
		 return Result.ok(list);
	 }
	/**
	 * 添加
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "部门挂片管理-添加")
	@ApiOperation(value="部门挂片管理-添加", notes="部门挂片管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody JSONObject jsonObject) {
		Bmgpgl bmgpgl = new Bmgpgl();
		String zzbz = jsonObject.getString("zzbzList");
		String zzbz2 = zzbz.substring(1).replaceAll("]", "");
		String[] zzbzList = zzbz2.split(",");

		String bmbz = jsonObject.getString("bmbzList");
		String bmbz2 = bmbz.substring(1).replaceAll("]", "");
		String[] bmbzList = bmbz2.split(",");

		if (bmbzList != null && bmbzList.length > 0){
			//遍历组织标识
			for (String bm : bmbzList) {
				QueryWrapper<Bmgpgl> queryWrapper = new QueryWrapper<>();
				queryWrapper.eq("bmbz",bm);
				bmgpglService.remove(queryWrapper);
				if (zzbzList != null && zzbzList.length > 0) {
					for (String bz : zzbzList) {
						bmgpgl.setBmbz(bm);
						bmgpgl.setZzbz(bz);
						bmgpgl.setLrczy(getUsername());
						bmgpgl.setLrsj(new Timestamp(System.currentTimeMillis()));
						bmgpgl.setLrbz(1);
						bmgpglService.save(bmgpgl);

					}
				}
			}
		}
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param bmgpgl
	 * @return
	 */
	@AutoLog(value = "部门挂片管理-编辑")
	@ApiOperation(value="部门挂片管理-编辑", notes="部门挂片管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Bmgpgl bmgpgl) {
		bmgpglService.updateById(bmgpgl);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "部门挂片管理-通过id删除")
	@ApiOperation(value="部门挂片管理-通过id删除", notes="部门挂片管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("bmbz") String bmbz,@Param("zzbz")String zzbz) {
		QueryWrapper<Bmgpgl> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("bmbz",bmbz);
		queryWrapper.eq("zzbz",zzbz);
		bmgpglService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "部门挂片管理-批量删除")
	@ApiOperation(value="部门挂片管理-批量删除", notes="部门挂片管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bmgpglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "部门挂片管理-通过id查询")
	@ApiOperation(value="部门挂片管理-通过id查询", notes="部门挂片管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Bmgpgl bmgpgl = bmgpglService.getById(id);
		return Result.ok(bmgpgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param bmgpgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Bmgpgl bmgpgl) {
      return super.exportXls(request, bmgpgl, Bmgpgl.class, "部门挂片管理");
  }

	 /**
	  * 导出模板
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "部门挂片管理导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, BmgpglVO.class);
		 ExportParams exportParams = new ExportParams("部门挂片管理导入模板", "模板信息");
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
	  return super.importExcelByTemplate(jsonObject, request, response, Bmgpgl.class,BmgpglVO.class,bmgpglImportVerify);
  }

}

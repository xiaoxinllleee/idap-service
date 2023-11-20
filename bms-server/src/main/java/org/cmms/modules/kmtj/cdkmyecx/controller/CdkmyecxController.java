package org.cmms.modules.kmtj.cdkmyecx.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.kmtj.cdkmyecx.entity.Cdkmyecx;
import org.cmms.modules.kmtj.cdkmyecx.service.ICdkmyecxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
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
 * @Description: 存贷科目余额查询
 * @Author: jeecg-boot
 * @Date:   2023-03-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存贷科目余额查询")
@RestController
@RequestMapping("/cdkmyecx/cdkmyecx")
public class CdkmyecxController extends JeecgController<Cdkmyecx, ICdkmyecxService> {
	@Autowired
	private ICdkmyecxService cdkmyecxService;
	 @Autowired
	 private IHrBasOrganizationService hrBasOrganizationService;


	 /**
	 * 分页列表查询
	 *
	 * @param cdkmyecx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存贷科目余额查询-分页列表查询")
	@ApiOperation(value="存贷科目余额查询-分页列表查询", notes="存贷科目余额查询-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Cdkmyecx cdkmyecx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String jgmc = cdkmyecx.getShowjgdm();
		cdkmyecx.setShowjgdm(null);
		List ywjgdm = new ArrayList();
		QueryWrapper<Cdkmyecx> queryWrapper = QueryGenerator.initQueryWrapper(cdkmyecx, req.getParameterMap());
		Page<Cdkmyecx> page = new Page<Cdkmyecx>(pageNo, pageSize);
		if (StringUtils.isNotBlank(jgmc)){
			QueryWrapper<HrBasOrganization> wrapper = new QueryWrapper<>();
			wrapper.like("zzmc",jgmc);
			List<HrBasOrganization> list = hrBasOrganizationService.list(wrapper);
			if (CollUtil.isNotEmpty(list)){
				for (HrBasOrganization hrBasOrganization : list) {
					ywjgdm.add(hrBasOrganization.getYwjgdm());
					queryWrapper.in("jgdm",ywjgdm);
				}
			}
		}
		IPage<Cdkmyecx> pageList = cdkmyecxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/init")
	 public Result<?> init(@RequestBody JSONObject jsonObject){
		 String tjyf = jsonObject.getString("tjyf");
		 Result result = new Result<>();
		 tjyf = tjyf.replaceAll("-", "");
		 System.out.println(tjyf);
		 try{
			 cdkmyecxService.pYgmrscs(tjyf);
			 result.setSuccess(true);
			 return result;
		 }catch (Throwable e){
			 System.out.println(e);
			 log.error("提取失败",e.getMessage());
			 result.setSuccess(false);
		 }
		 return result;
	 }
	/**
	 * 添加
	 *
	 * @param cdkmyecx
	 * @return
	 */
	@AutoLog(value = "存贷科目余额查询-添加")
	@ApiOperation(value="存贷科目余额查询-添加", notes="存贷科目余额查询-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Cdkmyecx cdkmyecx) {
		cdkmyecxService.save(cdkmyecx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param cdkmyecx
	 * @return
	 */
	@AutoLog(value = "存贷科目余额查询-编辑")
	@ApiOperation(value="存贷科目余额查询-编辑", notes="存贷科目余额查询-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Cdkmyecx cdkmyecx) {
		cdkmyecxService.updateById(cdkmyecx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存贷科目余额查询-通过id删除")
	@ApiOperation(value="存贷科目余额查询-通过id删除", notes="存贷科目余额查询-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		cdkmyecxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "存贷科目余额查询-批量删除")
	@ApiOperation(value="存贷科目余额查询-批量删除", notes="存贷科目余额查询-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cdkmyecxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存贷科目余额查询-通过id查询")
	@ApiOperation(value="存贷科目余额查询-通过id查询", notes="存贷科目余额查询-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Cdkmyecx cdkmyecx = cdkmyecxService.getById(id);
		return Result.ok(cdkmyecx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param cdkmyecx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Cdkmyecx cdkmyecx) {
      return super.exportXls(request, cdkmyecx, Cdkmyecx.class, "存贷科目余额查询");
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
      return super.importExcel(request, response, Cdkmyecx.class);
  }

}

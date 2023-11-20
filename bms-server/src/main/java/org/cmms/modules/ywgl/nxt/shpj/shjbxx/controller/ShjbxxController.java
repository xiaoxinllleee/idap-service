package org.cmms.modules.ywgl.nxt.shpj.shjbxx.controller;

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
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasStaffPostVo;
import org.cmms.modules.hr.zzgl.gwxxgl.service.IHrBasPostService;
import org.cmms.modules.ywgl.djkyw.djkxxgl.entity.Djkxxgl;
import org.cmms.modules.ywgl.djkyw.djkxxgl.entity.DjkxxglImportVo;
import org.cmms.modules.ywgl.nxt.shpj.shjbxx.entity.Shjbxx;
import org.cmms.modules.ywgl.nxt.shpj.shjbxx.entity.ShjbxxImportVo;
import org.cmms.modules.ywgl.nxt.shpj.shjbxx.service.IShjbxxService;
import org.cmms.modules.ywgl.nxt.shpj.shjbxx.verify.ShjbxxImportVerify;
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
 * @Description: 商户基本信息
 * @Author: jeecg-boot
 * @Date:   2021-09-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="商户基本信息")
@RestController
@RequestMapping("/shjbxx/shjbxx")
public class ShjbxxController extends JeecgController<Shjbxx, IShjbxxService> {
	@Autowired
	private IShjbxxService shjbxxService;

	@Autowired
	private IHrBasPostService ihrBasPostService;
	 @Autowired
	 private ShjbxxImportVerify shjbxxImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param shjbxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "商户基本信息-分页列表查询")
	@ApiOperation(value="商户基本信息-分页列表查询", notes="商户基本信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Shjbxx shjbxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Shjbxx> queryWrapper = QueryGenerator.initQueryWrapper(shjbxx, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IShjbxxService.class,shjbxxService,pageNo,pageSize,queryWrapper,"jgdm","shbm","yggh");
		return Result.ok(pageList);
	}

	 /**
	  * 查找带回
	  */
	 @AutoLog(value = "查找带回")
	 @ApiOperation(value = "认领", notes = "商户基本信息")
	 @PostMapping(value = "/getListClaim")
	 public Result<?> getListClaim(@RequestBody JSONObject jsonObject) {
		 System.out.println(jsonObject);
		 String ywjgdm = jsonObject.getString("zzbz");
		 String khjlbz = jsonObject.getString("khjlbz");
		 String rglx = jsonObject.getString("rglx");
		 String gwbz = jsonObject.getString("gwbz");
		 String yggh = jsonObject.getString("yggh");
		 List<HrBasStaffPostVo> list = ihrBasPostService.getListClaim(ywjgdm, rglx, gwbz, khjlbz, yggh);
		 return Result.ok(list);
	 }

	/**
	 * 添加
	 *
	 * @param shjbxx
	 * @return
	 */
	@AutoLog(value = "商户基本信息-添加")
	@ApiOperation(value="商户基本信息-添加", notes="商户基本信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Shjbxx shjbxx) {

		QueryWrapper<Shjbxx> queryWrapper2 =new QueryWrapper();
		queryWrapper2.eq("shbm",shjbxx.getShbm());
		Shjbxx one = shjbxxService.getOne(queryWrapper2);
		if(one!=null){
			return Result.error("添加失败，数据已存在！");
		}
		shjbxx.setLrr(getLoginUser().getRealname());
		shjbxx.setLrrq(new Date());
		shjbxx.setLrbz(1);
		shjbxxService.save(shjbxx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param shjbxx
	 * @return
	 */
	@AutoLog(value = "商户基本信息-编辑")
	@ApiOperation(value="商户基本信息-编辑", notes="商户基本信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Shjbxx shjbxx) {
		shjbxx.setXgr(getLoginUser().getRealname());
		shjbxx.setXgrq(new Date());
		QueryWrapper<Shjbxx> queryWrapper = new QueryWrapper<Shjbxx>();
		queryWrapper.eq("shbm",shjbxx.getShbm());
		shjbxx.setShbm(null);
		shjbxxService.update(shjbxx,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "商户基本信息-通过id删除")
	@ApiOperation(value="商户基本信息-通过id删除", notes="商户基本信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("shbm")String shbm) {
		QueryWrapper<Shjbxx> queryWrapper = new QueryWrapper<Shjbxx>();
		queryWrapper.eq("shbm",shbm);
		shjbxxService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "商户基本信息-批量删除")
	@ApiOperation(value="商户基本信息-批量删除", notes="商户基本信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.shjbxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商户基本信息-通过id查询")
	@ApiOperation(value="商户基本信息-通过id查询", notes="商户基本信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Shjbxx shjbxx = shjbxxService.getById(id);
		return Result.ok(shjbxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param shjbxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Shjbxx shjbxx) {
      return super.exportXls(request, shjbxx, Shjbxx.class, "商户基本信息");
  }


	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {

		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "商户基本信息导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, ShjbxxImportVo.class);
		 ExportParams exportParams = new ExportParams("商户基本信息导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;

	 }


	 /**
	  * 通过excel导入数据
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcelByTemplate(jsonObject, request, response, Shjbxx.class,ShjbxxImportVo.class, shjbxxImportVerify);
	 }


 }

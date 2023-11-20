package org.cmms.modules.ywgl.ywl.ywltz.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.cmms.modules.hr.zzgl.gwxxgl.service.IHrBasPostService;
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasStaffPostVo;
import org.cmms.modules.ywgl.ywl.ywltz.entity.Ywltz;
import org.cmms.modules.ywgl.ywl.ywltz.entity.YwltzVO;
import org.cmms.modules.ywgl.ywl.ywltz.service.IYwltzService;
import org.cmms.modules.ywgl.ywl.ywltz.verify.YwltzImportVerify;
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
 * @Description: 业务量调整
 * @Author: jeecg-boot
 * @Date:   2021-09-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="业务量调整")
@RestController
@RequestMapping("/ywltz/ywltz")
public class YwltzController extends JeecgController<Ywltz, IYwltzService> {
	@Autowired
	private IYwltzService ywltzService;
	@Autowired
	private IHrBasPostService iHrBasPostService;
	@Autowired
	private YwltzImportVerify ywltzImportVerify;


	/**
	 * 分页列表查询
	 *
	 * @param ywltz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "业务量调整-分页列表查询")
	@ApiOperation(value="业务量调整-分页列表查询", notes="业务量调整-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ywltz ywltz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Ywltz> queryWrapper = QueryGenerator.initQueryWrapper(ywltz, req.getParameterMap());
		/*Page<Ywltz> page = new Page<Ywltz>(pageNo, pageSize);
		IPage<Ywltz> pageList = ywltzService.page(page, queryWrapper);
		return Result.ok(pageList);*/
		queryWrapper.orderByDesc("zzbz").orderByDesc("gwbz").orderByDesc("yggh").orderByDesc("tzyf");

		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IYwltzService.class,ywltzService,pageNo,pageSize,queryWrapper,"tzyf","zzbz","gwbz","yggh");
		return Result.ok(pageList);
	}

	 /**
	  * 查找带回
	  */
	 @AutoLog(value = "查找带回")
	 @ApiOperation(value = "查找带回",notes = "查找带回")
	 @PostMapping(value = "/getListClaim")
	 public Result<?> getListClaim(@RequestBody JSONObject jsonObject){
	 	 System.out.println(jsonObject);
		 String ywjgdm = jsonObject.getString("zzbz");
		 String rglx = jsonObject.getString("rglx");
		 String khjlbz = jsonObject.getString("khjlbz");
		 String gwbz = jsonObject.getString("gwbz");
		 String yggh = jsonObject.getString("yggh");
		 List<HrBasStaffPostVo> list = iHrBasPostService.getListClaim(ywjgdm, rglx, gwbz, khjlbz, yggh);
		 return Result.ok(list);
	 }

	/**
	 * 添加
	 *
	 * @param ywltz
	 * @return
	 */
	@AutoLog(value = "业务量调整-添加")
	@ApiOperation(value="业务量调整-添加", notes="业务量调整-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ywltz ywltz) {
		QueryWrapper<Ywltz> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("zzbz",ywltz.getZzbz());
		queryWrapper.eq("gwbz",ywltz.getGwbz());
		queryWrapper.eq("yggh",ywltz.getYggh());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String tzyf = sdf.format(ywltz.getTzyf()) + "-01";
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(tzyf);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		queryWrapper.eq("tzyf",date);
		Ywltz ywltz1 = ywltzService.getOne(queryWrapper);
		if (ywltz1 != null){
			return Result.error("本月业务量已添加,请勿重复添加！");
		}
		ywltz.setTzyf(date);
		ywltzService.save(ywltz);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ywltz
	 * @return
	 */
	@AutoLog(value = "业务量调整-编辑")
	@ApiOperation(value="业务量调整-编辑", notes="业务量调整-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ywltz ywltz) {
		QueryWrapper<Ywltz> queryWrapper = new QueryWrapper<Ywltz>();
		queryWrapper.eq("zzbz",ywltz.getZzbz());
		queryWrapper.eq("gwbz",ywltz.getGwbz());
		queryWrapper.eq("yggh",ywltz.getYggh());
		queryWrapper.eq("tzyf",ywltz.getTzyf());
		ywltzService.update(ywltz,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "业务量调整-通过id删除")
	@ApiOperation(value="业务量调整-通过id删除", notes="业务量调整-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("zzbz")String zzbz,@Param("gwbz")String gwbz,
							@Param("yggh")String yggh,@Param("tzyf")String tzyf) {
		QueryWrapper<Ywltz> queryWrapper = new QueryWrapper<Ywltz>();
		queryWrapper.eq("zzbz",zzbz);
		queryWrapper.eq("gwbz",gwbz);
		queryWrapper.eq("yggh",yggh);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			queryWrapper.eq("tzyf",sdf.parse(tzyf));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ywltzService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "业务量调整-批量删除")
	@ApiOperation(value="业务量调整-批量删除", notes="业务量调整-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ywltzService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "业务量调整-通过id查询")
	@ApiOperation(value="业务量调整-通过id查询", notes="业务量调整-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ywltz ywltz = ywltzService.getById(id);
		return Result.ok(ywltz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ywltz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ywltz ywltz) {
      return super.exportXls(request, ywltz, Ywltz.class, "业务量调整");
  }

	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "业务量调整导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, YwltzVO.class);
		 ExportParams exportParams = new ExportParams("业务量调整导入模板", "模板信息");
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
      return super.importExcelByTemplate(jsonObject, request, response, Ywltz.class,YwltzVO.class,ywltzImportVerify);
  }

}

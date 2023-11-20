package org.cmms.modules.khgl.csjl.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.csjl.entity.AppEtcCsjlVO;
import org.cmms.modules.khgl.csjl.entity.AppEtckhCsjl;
import org.cmms.modules.khgl.csjl.entity.AppEtckhCsjlVO;
import org.cmms.modules.khgl.csjl.service.IAppEtckhCsjlService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
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
 * @Description: etc客户催收记录
 * @Author: jeecg-boot
 * @Date:   2022-03-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags="etc客户催收记录")
@RestController
@RequestMapping("/csjl/appEtckhCsjl")
public class AppEtckhCsjlController extends JeecgController<AppEtckhCsjl, IAppEtckhCsjlService> {
	@Autowired
	private IAppEtckhCsjlService appEtckhCsjlService;
	
	/**
	 * 分页列表查询
	 *
	 * @param appEtckhCsjl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "etc客户催收记录-分页列表查询")
	@ApiOperation(value="etc客户催收记录-分页列表查询", notes="etc客户催收记录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(AppEtckhCsjl appEtckhCsjl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AppEtckhCsjl> queryWrapper = QueryGenerator.initQueryWrapper(appEtckhCsjl, req.getParameterMap());
		Page<AppEtckhCsjl> page = new Page<AppEtckhCsjl>(pageNo, pageSize);
		IPage<AppEtckhCsjl> pageList = appEtckhCsjlService.page(page, queryWrapper);
		return Result.ok(pageList);
	}


	 /**
	  * 修改后催收记录
	  */
	 @GetMapping(value = "/getEtcCsjlList")
	 public Result<?> getEtcCsjlList(AppEtcCsjlVO appEtcCsjlVO,
									 @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
									 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
									 String zjhm,
									 HttpServletRequest req){
		 Page<String> page = new Page<>(pageNo, pageSize);
		 IPage<AppEtcCsjlVO> csjlList = service.getCsjlList(page, zjhm);
		 return Result.ok(csjlList);
	 }

	 /**
	  * 催收记录
	  */
	 @GetMapping(value = "/getCsjlList")
	 public Result<?> getCsjlList(int start,int end,String namecn){
		 List<AppEtckhCsjlVO> list = appEtckhCsjlService.getCsxxList(start, end, namecn);
		 return Result.ok(list);
	 }

	 /**
	  * 修改后添加
	  */
	 @PostMapping(value = "/addCsjl")
	 public Result<?> addCsjl(@RequestBody JSONObject jsonObject){
		 String csxx = jsonObject.getString("csxx");
		 String zjhm = jsonObject.getString("zjhm");
		 Date csrq = jsonObject.getDate("csrq");

		 AppEtckhCsjl appEtckhCsjl = new AppEtckhCsjl();
		 appEtckhCsjl.setCsr(getRealname());
		 appEtckhCsjl.setZjhm(zjhm);
		 appEtckhCsjl.setCsxx(csxx);
		 appEtckhCsjl.setCsrq(csrq);

		 appEtckhCsjlService.save(appEtckhCsjl);
		 return Result.ok("添加成功！");
	 }

	 /**
	 * 添加
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "etc客户催收记录-添加")
	@ApiOperation(value="etc客户催收记录-添加", notes="etc客户催收记录-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody JSONObject jsonObject) {
		String hm = jsonObject.getString("hm");
		String dkrq = jsonObject.getString("dkrq");
		String dkje = jsonObject.getString("dkje");
		String hsrq = jsonObject.getString("hsrq");
		String csxx = jsonObject.getString("csxx");

		AppEtckhCsjl appEtckhCsjl = new AppEtckhCsjl();
		appEtckhCsjl.setCsr(getRealname());
		appEtckhCsjl.setKhxx(hm);
		appEtckhCsjl.setDkje(dkje);
		appEtckhCsjl.setCsxx(csxx);
		appEtckhCsjl.setDkrq(dkrq);
		appEtckhCsjl.setHsrq(hsrq);
		appEtckhCsjl.setTjrq(new Date());
		appEtckhCsjl.setCsrq(new Date());

		appEtckhCsjlService.save(appEtckhCsjl);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param appEtckhCsjl
	 * @return
	 */
	@AutoLog(value = "etc客户催收记录-编辑")
	@ApiOperation(value="etc客户催收记录-编辑", notes="etc客户催收记录-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AppEtckhCsjl appEtckhCsjl) {
		appEtckhCsjlService.updateById(appEtckhCsjl);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "etc客户催收记录-通过id删除")
	@ApiOperation(value="etc客户催收记录-通过id删除", notes="etc客户催收记录-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		appEtckhCsjlService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "etc客户催收记录-批量删除")
	@ApiOperation(value="etc客户催收记录-批量删除", notes="etc客户催收记录-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.appEtckhCsjlService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "etc客户催收记录-通过id查询")
	@ApiOperation(value="etc客户催收记录-通过id查询", notes="etc客户催收记录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AppEtckhCsjl appEtckhCsjl = appEtckhCsjlService.getById(id);
		return Result.ok(appEtckhCsjl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param appEtckhCsjl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, AppEtckhCsjl appEtckhCsjl) {
      return super.exportXls(request, appEtckhCsjl, AppEtckhCsjl.class, "etc客户催收记录");
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
      return super.importExcel(request, response, AppEtckhCsjl.class);
  }

}

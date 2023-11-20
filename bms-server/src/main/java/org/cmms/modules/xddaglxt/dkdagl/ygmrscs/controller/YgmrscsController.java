package org.cmms.modules.xddaglxt.dkdagl.ygmrscs.controller;

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
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.xddaglxt.dkdagl.ygmrscs.entity.Ygmrscs;
import org.cmms.modules.xddaglxt.dkdagl.ygmrscs.service.IYgmrscsService;
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
 * @Description: 员工每日上传数
 * @Author: jeecg-boot
 * @Date:   2021-12-01
 * @Version: V1.0
 */
@Slf4j
@Api(tags="员工每日上传数")
@RestController
@RequestMapping("/ygmrscs/ygmrscs")
public class YgmrscsController extends JeecgController<Ygmrscs, IYgmrscsService> {
	@Autowired
	private IYgmrscsService ygmrscsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param ygmrscs
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "员工每日上传数-分页列表查询")
	@ApiOperation(value="员工每日上传数-分页列表查询", notes="员工每日上传数-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ygmrscs ygmrscs,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   String tjrqString,
								   HttpServletRequest req) {
		QueryWrapper<Ygmrscs> queryWrapper = QueryGenerator.initQueryWrapper(ygmrscs, req.getParameterMap());
		if (tjrqString != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] date = tjrqString.split(",");
			try {
				queryWrapper.between("tjrq",sdf.parse(date[0]),sdf.parse(date[1]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Page<Ygmrscs> page = new Page<Ygmrscs>(pageNo, pageSize);
		IPage<Ygmrscs> pageList = ygmrscsService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/init")
	 public Result<?> init(@RequestBody JSONObject jsonObject){
		 String tjrqBegin = jsonObject.getString("tjrqBegin");
		 String tjrqEnd = jsonObject.getString("tjrqEnd");
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 Result result = new Result<>();
		 tjrqBegin = tjrqBegin.replaceAll("-", "");
		 tjrqEnd = tjrqEnd.replaceAll("-","");
		 try{
		 	ygmrscsService.pYgmrscs(tjrqBegin,tjrqEnd,sysUser.getRealname());
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
	 * @param ygmrscs
	 * @return
	 */
	@AutoLog(value = "员工每日上传数-添加")
	@ApiOperation(value="员工每日上传数-添加", notes="员工每日上传数-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ygmrscs ygmrscs) {
		ygmrscsService.save(ygmrscs);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param ygmrscs
	 * @return
	 */
	@AutoLog(value = "员工每日上传数-编辑")
	@ApiOperation(value="员工每日上传数-编辑", notes="员工每日上传数-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ygmrscs ygmrscs) {
		ygmrscsService.updateById(ygmrscs);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工每日上传数-通过id删除")
	@ApiOperation(value="员工每日上传数-通过id删除", notes="员工每日上传数-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ygmrscsService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "员工每日上传数-批量删除")
	@ApiOperation(value="员工每日上传数-批量删除", notes="员工每日上传数-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ygmrscsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工每日上传数-通过id查询")
	@ApiOperation(value="员工每日上传数-通过id查询", notes="员工每日上传数-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ygmrscs ygmrscs = ygmrscsService.getById(id);
		return Result.ok(ygmrscs);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ygmrscs
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ygmrscs ygmrscs) {
      return super.exportXls(request, ygmrscs, Ygmrscs.class, "员工每日上传数");
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
      return super.importExcel(request, response, Ygmrscs.class);
  }

}

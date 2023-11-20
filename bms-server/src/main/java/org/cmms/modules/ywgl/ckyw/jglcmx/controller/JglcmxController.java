package org.cmms.modules.ywgl.ckyw.jglcmx.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.ywgl.ckyw.jglcmx.entity.Jglcmx;
import org.cmms.modules.ywgl.ckyw.jglcmx.mapper.JglcmxMapper;
import org.cmms.modules.ywgl.ckyw.jglcmx.service.IJglcmxService;
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
 * @Description: 机关揽储明细
 * @Author: jeecg-boot
 * @Date:   2021-10-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="机关揽储明细")
@RestController
@RequestMapping("/jglcmx/jglcmx")
public class JglcmxController extends JeecgController<Jglcmx, IJglcmxService> {
	@Autowired
	private IJglcmxService jglcmxService;

	/**
	 * 分页列表查询
	 *
	 * @param jglcmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "机关揽储明细-分页列表查询")
	@ApiOperation(value="机关揽储明细-分页列表查询", notes="机关揽储明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Jglcmx jglcmx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   String ckrpyeS,String ckrpyeE,String nckrpyeS,String nckrpyeE,
								   String ckyeS,String ckyeE,
								   HttpServletRequest req) {
		QueryWrapper<Jglcmx> queryWrapper = QueryGenerator.initQueryWrapper(jglcmx, req.getParameterMap());
		if (StringUtils.isBlank(ckrpyeE))queryWrapper.ge("ckrpye",ckrpyeS);
		if (StringUtils.isBlank(ckrpyeE))queryWrapper.le("ckrpye",ckrpyeE);
		if (StringUtils.isBlank(nckrpyeE))queryWrapper.ge("nckrpye",nckrpyeS);
		if (StringUtils.isBlank(nckrpyeE))queryWrapper.le("nckrpye",nckrpyeE);
		if (StringUtils.isBlank(ckyeE))queryWrapper.ge("ckye",ckyeS);
		if (StringUtils.isBlank(ckyeE))queryWrapper.le("ckye",ckyeE);
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IJglcmxService.class,jglcmxService,pageNo,pageSize,queryWrapper,"tjyf","glid");
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  */
	 public Result<?> init(@RequestBody JSONObject jsonObject){
	 	String tjyf = jsonObject.getString("tjyf");
	 	//if (tjyf == null || tjyf == "")return Result.error("统计月份不能为空");
		 if (-1 == DateUtil.getCurrentTS().compareTo(DateUtil.parseDateFormat(tjyf,"yyyy-MM-dd")))
			 return Result.error("选择月份必须小于当前系统月份！");
		 tjyf = tjyf.replaceAll("-","");
		 try {
			 jglcmxService.pJglcmx(tjyf);
		 }catch (Throwable e){
		 	e.printStackTrace();
		 	return Result.error("提取失败，请查看系统监控日志信息");
		 }
		 return Result.ok("提取成功");
	 }
	
	/**
	 * 添加
	 *
	 * @param jglcmx
	 * @return
	 */
	@AutoLog(value = "机关揽储明细-添加")
	@ApiOperation(value="机关揽储明细-添加", notes="机关揽储明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Jglcmx jglcmx) {
		jglcmxService.save(jglcmx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param jglcmx
	 * @return
	 */
	@AutoLog(value = "机关揽储明细-编辑")
	@ApiOperation(value="机关揽储明细-编辑", notes="机关揽储明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Jglcmx jglcmx) {
		jglcmxService.updateById(jglcmx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "机关揽储明细-通过id删除")
	@ApiOperation(value="机关揽储明细-通过id删除", notes="机关揽储明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		jglcmxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "机关揽储明细-批量删除")
	@ApiOperation(value="机关揽储明细-批量删除", notes="机关揽储明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.jglcmxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "机关揽储明细-通过id查询")
	@ApiOperation(value="机关揽储明细-通过id查询", notes="机关揽储明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Jglcmx jglcmx = jglcmxService.getById(id);
		return Result.ok(jglcmx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param jglcmx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Jglcmx jglcmx) {
      return super.exportXls(request, jglcmx, Jglcmx.class, "机关揽储明细");
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
      return super.importExcel(request, response, Jglcmx.class);
  }

}

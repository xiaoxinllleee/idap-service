package org.cmms.modules.khjg.gztq.controller;

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
import io.minio.DateFormat;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khjg.gztq.entity.VDpJdrwgl;
import org.cmms.modules.khjg.gztq.service.IVDpJdrwglService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khjg.gztq.vo.GztqVo;
import org.cmms.modules.khlc.khfagl.entity.VPmaAScheme;
import org.cmms.modules.system.entity.DpJdrwgl;
import org.cmms.modules.system.service.IDpJdrwglService;
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
 * @Description: 基础数据加工日历
 * @Author: jeecg-boot
 * @Date:   2021-10-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="基础数据加工日历")
@RestController
@RequestMapping("/gztq/vDpJdrwgl")
public class VDpJdrwglController extends JeecgController<VDpJdrwgl, IVDpJdrwglService> {
	 @Autowired
	 private IVDpJdrwglService vDpJdrwglService;
	 @Autowired
	 private IDpJdrwglService dpJdrwglService;


	/**
	 * 分页列表查询
	 *
	 * @param vDpJdrwgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "基础数据加工日历-分页列表查询")
	@ApiOperation(value="基础数据加工日历-分页列表查询", notes="基础数据加工日历-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VDpJdrwgl vDpJdrwgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VDpJdrwgl> queryWrapper = QueryGenerator.initQueryWrapper(vDpJdrwgl, req.getParameterMap());
		queryWrapper.eq("sfqy",1);
		queryWrapper.orderByAsc("to_number(jdid)");
		Page<VDpJdrwgl> page = new Page<VDpJdrwgl>(pageNo, pageSize);
		IPage<VDpJdrwgl> pageList = vDpJdrwglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param vDpJdrwgl
	 * @return
	 */
	@AutoLog(value = "基础数据加工日历-添加")
	@ApiOperation(value="基础数据加工日历-添加", notes="基础数据加工日历-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VDpJdrwgl vDpJdrwgl) {
		vDpJdrwglService.save(vDpJdrwgl);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param vDpJdrwgl
	 * @return
	 */
	@AutoLog(value = "基础数据加工日历-编辑")
	@ApiOperation(value="基础数据加工日历-编辑", notes="基础数据加工日历-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody VDpJdrwgl vDpJdrwgl) {
		vDpJdrwglService.updateById(vDpJdrwgl);
		return Result.ok("编辑成功!");
	}

	 /**
	  * 提取工资
	  * @param gztqVo
	  * @return
	  */
	 /*@AutoLog(value = "基础数据加工日历-编辑")
	 @ApiOperation(value="基础数据加工日历-编辑", notes="基础数据加工日历-编辑")
	 @PutMapping(value = "/init")
	 public Result<?> init(@RequestBody GztqVo gztqVo) {
		 List<VDpJdrwgl> dpJdrwglList =gztqVo.getDpJdrwgl();
		 List<VPmaAScheme> pmaASchemesList =gztqVo.getPmaASchemes();
		 if(dpJdrwglList!=null&&dpJdrwglList.size()>0){
		 	  for(VDpJdrwgl dpJdrwgl:dpJdrwglList){
				  dpJdrwglService.updateBatchzt(DateUtil.getDateString(gztqVo.getTjrqBegin()),DateUtil.getDateString(gztqVo.getTjrqEnd()),dpJdrwgl.getId());
				  dpJdrwglService.extract(dpJdrwgl.getRwgc(),DateUtil.getDateString(gztqVo.getTjrqBegin()),DateUtil.getDateString(gztqVo.getTjrqEnd()),dpJdrwgl.getId());
			  }
		 }
		 if(pmaASchemesList!=null&&pmaASchemesList.size()>0){
			 for(VPmaAScheme pmaASchemeObj:pmaASchemesList){
				 JSONObject jsonObject=new JSONObject();
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				 jsonObject.put("beginDate",DateUtil.getDateString(gztqVo.getTjrqBegin(), sdf));
				 jsonObject.put("endDate",DateUtil.getDateString(gztqVo.getTjrqEnd(), sdf));
				 jsonObject.put("schemeId",pmaASchemeObj.getSchemeId());
				 Result<?> result=pmaFEngineSchemeStatController.execPlpszbjg(jsonObject);
				 if(!result.isSuccess()){
					 Result.error("方案:["+pmaASchemeObj.getSchemeName()+"]加工失败");
				 }
			 }
		 }
		 return Result.ok("提取成功!");
	 }*/

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "基础数据加工日历-通过id删除")
	@ApiOperation(value="基础数据加工日历-通过id删除", notes="基础数据加工日历-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		vDpJdrwglService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "基础数据加工日历-批量删除")
	@ApiOperation(value="基础数据加工日历-批量删除", notes="基础数据加工日历-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.vDpJdrwglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "基础数据加工日历-通过id查询")
	@ApiOperation(value="基础数据加工日历-通过id查询", notes="基础数据加工日历-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VDpJdrwgl vDpJdrwgl = vDpJdrwglService.getById(id);
		return Result.ok(vDpJdrwgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param vDpJdrwgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, VDpJdrwgl vDpJdrwgl) {
      return super.exportXls(request, vDpJdrwgl, VDpJdrwgl.class, "基础数据加工日历");
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
      return super.importExcel(request, response, VDpJdrwgl.class);
  }

}

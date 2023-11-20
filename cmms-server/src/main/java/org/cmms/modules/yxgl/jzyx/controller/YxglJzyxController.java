package org.cmms.modules.yxgl.jzyx.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.khhmc.entity.Khhmc;
import org.cmms.modules.yxgl.jzyx.entity.YxglJzyx;
import org.cmms.modules.yxgl.jzyx.service.IYxglJzyxService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

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
 * @Description: 精准营销
 * @Author: cmms
 * @Date:   2019-12-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="精准营销")
@RestController
@RequestMapping("/yxgl.jzyx/yxglJzyx")
public class YxglJzyxController {
	@Autowired
	private IYxglJzyxService yxglJzyxService;
	
	/**
	  * 分页列表查询
	 * @param yxglJzyx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "精准营销-分页列表查询")
	@ApiOperation(value="精准营销-分页列表查询", notes="精准营销-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<YxglJzyx>> queryPageList(YxglJzyx yxglJzyx,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<YxglJzyx>> result = new Result<IPage<YxglJzyx>>();
		QueryWrapper<YxglJzyx> queryWrapper = QueryGenerator.initQueryWrapper(yxglJzyx, req.getParameterMap());
		Page<YxglJzyx> page = new Page<YxglJzyx>(pageNo, pageSize);
		IPage<YxglJzyx> pageList = yxglJzyxService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param yxglJzyx
	 * @return
	 */
	@AutoLog(value = "精准营销-添加")
	@ApiOperation(value="精准营销-添加", notes="精准营销-添加")
	@PostMapping(value = "/add")
	public Result<YxglJzyx> add(@RequestBody YxglJzyx yxglJzyx) {
		Result<YxglJzyx> result = new Result<YxglJzyx>();
		try {
			yxglJzyxService.save(yxglJzyx);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param yxglJzyx
	 * @return
	 */
	@AutoLog(value = "精准营销-编辑")
	@ApiOperation(value="精准营销-编辑", notes="精准营销-编辑")
	@PutMapping(value = "/edit")
	public Result<YxglJzyx> edit(@RequestBody YxglJzyx yxglJzyx) {
		Result<YxglJzyx> result = new Result<YxglJzyx>();
		YxglJzyx yxglJzyxEntity = yxglJzyxService.getById(yxglJzyx.getZjhm());
		if(yxglJzyxEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = yxglJzyxService.updateById(yxglJzyx);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		
		return result;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "精准营销-通过id删除")
	@ApiOperation(value="精准营销-通过id删除", notes="精准营销-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			yxglJzyxService.removeById(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}
	
	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "精准营销-批量删除")
	@ApiOperation(value="精准营销-批量删除", notes="精准营销-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<YxglJzyx> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<YxglJzyx> result = new Result<YxglJzyx>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.yxglJzyxService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "精准营销-通过id查询")
	@ApiOperation(value="精准营销-通过id查询", notes="精准营销-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<YxglJzyx> queryById(@RequestParam(name="id",required=true) String id) {
		Result<YxglJzyx> result = new Result<YxglJzyx>();
		YxglJzyx yxglJzyx = yxglJzyxService.getById(id);
		if(yxglJzyx==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(yxglJzyx);
			result.setSuccess(true);
		}
		return result;
	}

  /**
      * 导出excel
   *
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<YxglJzyx> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              YxglJzyx yxglJzyx = JSON.parseObject(deString, YxglJzyx.class);
              queryWrapper = QueryGenerator.initQueryWrapper(yxglJzyx, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<YxglJzyx> pageList = yxglJzyxService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "精准营销列表");
      mv.addObject(NormalExcelConstants.CLASS, YxglJzyx.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("精准营销列表数据", "导出人:Jeecg", "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
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
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<YxglJzyx> listYxglJzyxs = ExcelImportUtil.importExcel(file.getInputStream(), YxglJzyx.class, params);
              yxglJzyxService.saveBatch(listYxglJzyxs);
              return Result.ok("文件导入成功！数据行数:" + listYxglJzyxs.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
  }
	 /**
	  * 提取
	  * @return
	  */
	 @AutoLog(value = "精准营销信息-提取")
	 @ApiOperation(value="精准营销信息-提取", notes="精准营销信息-提取")
	 @GetMapping(value = "/init")
	 public Result<YxglJzyx> queryById(HttpServletRequest request, HttpServletResponse response) {
		 Result<YxglJzyx> result = new Result<YxglJzyx>();
		 yxglJzyxService.init();
		 result.setSuccess(true);
		 return result;
	 }

}

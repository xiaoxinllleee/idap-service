package org.cmms.modules.gzap.zjrw.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.PermissionData;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.gzap.zjrw.entity.GzapRwxfZjrw;
import org.cmms.modules.gzap.zjrw.service.IGzapRwxfZjrwService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.cmms.modules.system.entity.SysUser;
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
 * @Description: 自建任务
 * @Author: cmms
 * @Date:   2019-09-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags="自建任务")
@RestController
@RequestMapping("/gzap/zjrw/gzapRwxfZjrw")
@PermissionData(pageComponent="gzap/zjrw/GzapRwxfZjrwList")
public class GzapRwxfZjrwController {
	@Autowired
	private IGzapRwxfZjrwService gzapRwxfZjrwService;

	/**
	  * 分页列表查询
	 * @param gzapRwxfZjrw
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "自建任务-分页列表查询")
	@ApiOperation(value="自建任务-分页列表查询", notes="自建任务-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent="gzap/zjrw/GzapRwxfZjrwList")
	public Result<IPage<GzapRwxfZjrw>> queryPageList(GzapRwxfZjrw gzapRwxfZjrw,
													 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
													 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
													 HttpServletRequest req) {
		Result<IPage<GzapRwxfZjrw>> result = new Result<IPage<GzapRwxfZjrw>>();
		QueryWrapper<GzapRwxfZjrw> queryWrapper = QueryGenerator.initQueryWrapper(gzapRwxfZjrw, req.getParameterMap());
		Page<GzapRwxfZjrw> page = new Page<GzapRwxfZjrw>(pageNo, pageSize);
		IPage<GzapRwxfZjrw> pageList = gzapRwxfZjrwService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param gzapRwxfZjrw
	 * @return
	 */
	@AutoLog(value = "自建任务-添加")
	@ApiOperation(value="自建任务-添加", notes="自建任务-添加")
	@PostMapping(value = "/add")
	public Result<GzapRwxfZjrw> add(@RequestBody GzapRwxfZjrw gzapRwxfZjrw) {
		Result<GzapRwxfZjrw> result = new Result<GzapRwxfZjrw>();
		try {
			gzapRwxfZjrw.setCreateTime(new Date());
			gzapRwxfZjrw.setZt("1");
			gzapRwxfZjrwService.save(gzapRwxfZjrw);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	 @RequestMapping(value = "/over", method = RequestMethod.PUT)
	 public Result<SysUser> changPassword(@RequestBody JSONObject json) {
		 Result<SysUser> result = new Result<SysUser>();
		 String id = json.getString("Id");

		 GzapRwxfZjrw gzapRwxfZjrwEntity = gzapRwxfZjrwService.getById(id);
		 gzapRwxfZjrwService.removeById(id);
		 if(gzapRwxfZjrwEntity==null) {
			 result.error500("未找到对应实体");
		 }else {
			 gzapRwxfZjrwEntity.setZt("2");
			 gzapRwxfZjrwService.save(gzapRwxfZjrwEntity);
			 result.success("修改成功!");

		 }
		 return result;
	 }


	/**
	  *  编辑
	 * @param gzapRwxfZjrw
	 * @return
	 */
	@AutoLog(value = "自建任务-编辑")
	@ApiOperation(value="自建任务-编辑", notes="自建任务-编辑")
	@PutMapping(value = "/edit")
	public Result<GzapRwxfZjrw> edit(@RequestBody GzapRwxfZjrw gzapRwxfZjrw) {
		Result<GzapRwxfZjrw> result = new Result<GzapRwxfZjrw>();
		GzapRwxfZjrw gzapRwxfZjrwEntity = gzapRwxfZjrwService.getById(gzapRwxfZjrw.getId());
		if(gzapRwxfZjrwEntity==null) {
			result.error500("未找到对应实体");
		}else {
			Date date = new Date();
			gzapRwxfZjrw.setWcsj(date);
			gzapRwxfZjrw.setZt("2");
			boolean ok = gzapRwxfZjrwService.updateById(gzapRwxfZjrw);
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
	@AutoLog(value = "自建任务-通过id删除")
	@ApiOperation(value="自建任务-通过id删除", notes="自建任务-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			gzapRwxfZjrwService.removeById(id);
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
	@AutoLog(value = "自建任务-批量删除")
	@ApiOperation(value="自建任务-批量删除", notes="自建任务-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<GzapRwxfZjrw> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<GzapRwxfZjrw> result = new Result<GzapRwxfZjrw>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.gzapRwxfZjrwService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "自建任务-通过id查询")
	@ApiOperation(value="自建任务-通过id查询", notes="自建任务-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<GzapRwxfZjrw> queryById(@RequestParam(name="id",required=true) String id) {
		Result<GzapRwxfZjrw> result = new Result<GzapRwxfZjrw>();
		GzapRwxfZjrw gzapRwxfZjrw = gzapRwxfZjrwService.getById(id);
		if(gzapRwxfZjrw==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(gzapRwxfZjrw);
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
      QueryWrapper<GzapRwxfZjrw> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              GzapRwxfZjrw gzapRwxfZjrw = JSON.parseObject(deString, GzapRwxfZjrw.class);
              queryWrapper = QueryGenerator.initQueryWrapper(gzapRwxfZjrw, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<GzapRwxfZjrw> pageList = gzapRwxfZjrwService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "自建任务列表");
      mv.addObject(NormalExcelConstants.CLASS, GzapRwxfZjrw.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("自建任务列表数据", "导出人:Jeecg", "导出信息"));
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
              List<GzapRwxfZjrw> listGzapRwxfZjrws = ExcelImportUtil.importExcel(file.getInputStream(), GzapRwxfZjrw.class, params);
              gzapRwxfZjrwService.saveBatch(listGzapRwxfZjrws);
              return Result.ok("文件导入成功！数据行数:" + listGzapRwxfZjrws.size());
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

}

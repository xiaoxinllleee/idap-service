package org.cmms.modules.gzap.gzrz.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.aspect.annotation.PermissionData;
import org.cmms.common.system.vo.LoginUser;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.gzap.gzrz.entity.GzapRzglKhgh;
import org.cmms.modules.gzap.gzrz.entity.GzapRzglJrjhzj;
import org.cmms.modules.gzap.gzrz.entity.GzapRzgl;
import org.cmms.modules.gzap.gzrz.vo.GzapRzglPage;
import org.cmms.modules.gzap.gzrz.service.IGzapRzglService;
import org.cmms.modules.gzap.gzrz.service.IGzapRzglKhghService;
import org.cmms.modules.gzap.gzrz.service.IGzapRzglJrjhzjService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;

 /**
 * @Description: 工作日志
 * @Author: cmms
 * @Date:   2019-09-08
 * @Version: V1.0
 */
@RestController
@RequestMapping("/gzap/gzrl/gzapRzgl")
@Slf4j
@PermissionData(pageComponent="gzap/gzrz/GzapRzglList")
public class GzapRzglController {
	@Autowired
	private IGzapRzglService gzapRzglService;
	@Autowired
	private IGzapRzglKhghService gzapRzglKhghService;
	@Autowired
	private IGzapRzglJrjhzjService gzapRzglJrjhzjService;
	
	/**
	  * 分页列表查询
	 * @param gzapRzgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	@PermissionData(pageComponent="gzap/gzrz/GzapRzglList")
	public Result<IPage<GzapRzgl>> queryPageList(GzapRzgl gzapRzgl,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<GzapRzgl>> result = new Result<IPage<GzapRzgl>>();
		QueryWrapper<GzapRzgl> queryWrapper = QueryGenerator.initQueryWrapper(gzapRzgl, req.getParameterMap());
		Page<GzapRzgl> page = new Page<GzapRzgl>(pageNo, pageSize);
		IPage<GzapRzgl> pageList = gzapRzglService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param gzapRzglPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<GzapRzgl> add(@RequestBody GzapRzglPage gzapRzglPage) {
		Result<GzapRzgl> result = new Result<GzapRzgl>();
		try {
			GzapRzgl gzapRzgl = new GzapRzgl();
			BeanUtils.copyProperties(gzapRzglPage, gzapRzgl);
			LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			gzapRzgl.setKhjl(loginUser.getRealname());
			gzapRzglService.saveMain(gzapRzgl, gzapRzglPage.getGzapRzglKhghList(),gzapRzglPage.getGzapRzglJrjhzjList());
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param gzapRzglPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<GzapRzgl> edit(@RequestBody GzapRzglPage gzapRzglPage) {
		Result<GzapRzgl> result = new Result<GzapRzgl>();
		GzapRzgl gzapRzgl = new GzapRzgl();
		BeanUtils.copyProperties(gzapRzglPage, gzapRzgl);
		GzapRzgl gzapRzglEntity = gzapRzglService.getById(gzapRzgl.getId());
		if(gzapRzglEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = gzapRzglService.updateById(gzapRzgl);
			gzapRzglService.updateMain(gzapRzgl, gzapRzglPage.getGzapRzglKhghList(),gzapRzglPage.getGzapRzglJrjhzjList());
			result.success("修改成功!");
		}
		
		return result;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			gzapRzglService.delMain(id);
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
	@DeleteMapping(value = "/deleteBatch")
	public Result<GzapRzgl> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<GzapRzgl> result = new Result<GzapRzgl>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.gzapRzglService.delBatchMain(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<GzapRzgl> queryById(@RequestParam(name="id",required=true) String id) {
		Result<GzapRzgl> result = new Result<GzapRzgl>();
		GzapRzgl gzapRzgl = gzapRzglService.getById(id);
		if(gzapRzgl==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(gzapRzgl);
			result.setSuccess(true);
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryGzapRzglKhghByMainId")
	public Result<List<GzapRzglKhgh>> queryGzapRzglKhghListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<GzapRzglKhgh>> result = new Result<List<GzapRzglKhgh>>();
		List<GzapRzglKhgh> gzapRzglKhghList = gzapRzglKhghService.selectByMainId(id);
		result.setResult(gzapRzglKhghList);
		result.setSuccess(true);
		return result;
	}
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryGzapRzglJrjhzjByMainId")
	public Result<List<GzapRzglJrjhzj>> queryGzapRzglJrjhzjListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<GzapRzglJrjhzj>> result = new Result<List<GzapRzglJrjhzj>>();
		List<GzapRzglJrjhzj> gzapRzglJrjhzjList = gzapRzglJrjhzjService.selectByMainId(id);
		result.setResult(gzapRzglJrjhzjList);
		result.setSuccess(true);
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
      QueryWrapper<GzapRzgl> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              GzapRzgl gzapRzgl = JSON.parseObject(deString, GzapRzgl.class);
              queryWrapper = QueryGenerator.initQueryWrapper(gzapRzgl, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<GzapRzglPage> pageList = new ArrayList<GzapRzglPage>();
      List<GzapRzgl> gzapRzglList = gzapRzglService.list(queryWrapper);
      for (GzapRzgl gzapRzgl : gzapRzglList) {
          GzapRzglPage vo = new GzapRzglPage();
          BeanUtils.copyProperties(gzapRzgl, vo);
          List<GzapRzglKhgh> gzapRzglKhghList = gzapRzglKhghService.selectByMainId(gzapRzgl.getId());
          vo.setGzapRzglKhghList(gzapRzglKhghList);
          List<GzapRzglJrjhzj> gzapRzglJrjhzjList = gzapRzglJrjhzjService.selectByMainId(gzapRzgl.getId());
          vo.setGzapRzglJrjhzjList(gzapRzglJrjhzjList);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "工作日志列表");
      mv.addObject(NormalExcelConstants.CLASS, GzapRzglPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("工作日志列表数据", "导出人:Jeecg", "导出信息"));
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
              List<GzapRzglPage> list = ExcelImportUtil.importExcel(file.getInputStream(), GzapRzglPage.class, params);
              for (GzapRzglPage page : list) {
                  GzapRzgl po = new GzapRzgl();
                  BeanUtils.copyProperties(page, po);
                  gzapRzglService.saveMain(po, page.getGzapRzglKhghList(),page.getGzapRzglJrjhzjList());
              }
              return Result.ok("文件导入成功！数据行数:" + list.size());
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

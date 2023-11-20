package org.cmms.modules.gzap.rwxf.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.modules.system.entity.SysUser;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.gzap.rwxf.entity.GzapRwxf_Rwgl;
import org.cmms.modules.gzap.rwxf.entity.GzapRwxfJxz;
import org.cmms.modules.gzap.rwxf.vo.GzapRwxfJxzPage;
import org.cmms.modules.gzap.rwxf.service.IGzapRwxfJxzService;
import org.cmms.modules.gzap.rwxf.service.IGzapRwxf_RwglService;
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
 * @Date:   2019-09-25
 * @Version: V1.0
 */
@RestController
@RequestMapping("/gzap.rwxf/gzapRwxfJxz")
@Slf4j
public class GzapRwxfJxzController {
	@Autowired
	private IGzapRwxfJxzService gzapRwxfJxzService;
	@Autowired
	private IGzapRwxf_RwglService gzapRwxf_RwglService;
	
	/**
	  * 分页列表查询
	 * @param gzapRwxfJxz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<GzapRwxfJxz>> queryPageList(GzapRwxfJxz gzapRwxfJxz,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<GzapRwxfJxz>> result = new Result<IPage<GzapRwxfJxz>>();
		QueryWrapper<GzapRwxfJxz> queryWrapper = QueryGenerator.initQueryWrapper(gzapRwxfJxz, req.getParameterMap());
		Page<GzapRwxfJxz> page = new Page<GzapRwxfJxz>(pageNo, pageSize);
		IPage<GzapRwxfJxz> pageList = gzapRwxfJxzService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}


	 @RequestMapping(value = "/over", method = RequestMethod.PUT)
	 public Result<SysUser> changPassword(@RequestBody JSONObject json) {
		 Result<SysUser> result = new Result<SysUser>();
		 String id = json.getString("Id");
		 GzapRwxfJxz gzapRwxfJxzEntity = gzapRwxfJxzService.getById(id);
		 gzapRwxfJxzService.removeById(id);
		 if(gzapRwxfJxzEntity==null) {
			 result.error500("未找到对应实体");
		 }else {
			 gzapRwxfJxzEntity.setZt("2");
			 gzapRwxfJxzService.save(gzapRwxfJxzEntity);
			 result.success("修改成功!");
		 }
		 return result;
	 }


	 /**
	  *   添加
	 * @param gzapRwxfJxzPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<GzapRwxfJxz> add(@RequestBody GzapRwxfJxzPage gzapRwxfJxzPage) {
		Result<GzapRwxfJxz> result = new Result<GzapRwxfJxz>();
		try {
			GzapRwxfJxz gzapRwxfJxz = new GzapRwxfJxz();
			BeanUtils.copyProperties(gzapRwxfJxzPage, gzapRwxfJxz);
			gzapRwxfJxz.setZt("1");
			gzapRwxfJxzService.saveMain(gzapRwxfJxz, gzapRwxfJxzPage.getGzapRwxf_RwglList());
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param gzapRwxfJxzPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<GzapRwxfJxz> edit(@RequestBody GzapRwxfJxzPage gzapRwxfJxzPage) {
		Result<GzapRwxfJxz> result = new Result<GzapRwxfJxz>();
		GzapRwxfJxz gzapRwxfJxz = new GzapRwxfJxz();
		BeanUtils.copyProperties(gzapRwxfJxzPage, gzapRwxfJxz);
		GzapRwxfJxz gzapRwxfJxzEntity = gzapRwxfJxzService.getById(gzapRwxfJxz.getId());
		if(gzapRwxfJxzEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = gzapRwxfJxzService.updateById(gzapRwxfJxz);
			gzapRwxfJxzService.updateMain(gzapRwxfJxz, gzapRwxfJxzPage.getGzapRwxf_RwglList());
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
			gzapRwxfJxzService.delMain(id);
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
	public Result<GzapRwxfJxz> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<GzapRwxfJxz> result = new Result<GzapRwxfJxz>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.gzapRwxfJxzService.delBatchMain(Arrays.asList(ids.split(",")));
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
	public Result<GzapRwxfJxz> queryById(@RequestParam(name="id",required=true) String id) {
		Result<GzapRwxfJxz> result = new Result<GzapRwxfJxz>();
		GzapRwxfJxz gzapRwxfJxz = gzapRwxfJxzService.getById(id);
		if(gzapRwxfJxz==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(gzapRwxfJxz);
			result.setSuccess(true);
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryGzapRwxf_RwglByMainId")
	public Result<List<GzapRwxf_Rwgl>> queryGzapRwxf_RwglListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<GzapRwxf_Rwgl>> result = new Result<List<GzapRwxf_Rwgl>>();
		List<GzapRwxf_Rwgl> gzapRwxf_RwglList = gzapRwxf_RwglService.selectByMainId(id);
		result.setResult(gzapRwxf_RwglList);
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
      QueryWrapper<GzapRwxfJxz> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              GzapRwxfJxz gzapRwxfJxz = JSON.parseObject(deString, GzapRwxfJxz.class);
              queryWrapper = QueryGenerator.initQueryWrapper(gzapRwxfJxz, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<GzapRwxfJxzPage> pageList = new ArrayList<GzapRwxfJxzPage>();
      List<GzapRwxfJxz> gzapRwxfJxzList = gzapRwxfJxzService.list(queryWrapper);
      for (GzapRwxfJxz gzapRwxfJxz : gzapRwxfJxzList) {
          GzapRwxfJxzPage vo = new GzapRwxfJxzPage();
          BeanUtils.copyProperties(gzapRwxfJxz, vo);
          List<GzapRwxf_Rwgl> gzapRwxf_RwglList = gzapRwxf_RwglService.selectByMainId(gzapRwxfJxz.getId());
          vo.setGzapRwxf_RwglList(gzapRwxf_RwglList);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "工作日志列表");
      mv.addObject(NormalExcelConstants.CLASS, GzapRwxfJxzPage.class);
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
              List<GzapRwxfJxzPage> list = ExcelImportUtil.importExcel(file.getInputStream(), GzapRwxfJxzPage.class, params);
              for (GzapRwxfJxzPage page : list) {
                  GzapRwxfJxz po = new GzapRwxfJxz();
                  BeanUtils.copyProperties(page, po);
                  gzapRwxfJxzService.saveMain(po, page.getGzapRwxf_RwglList());
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

  /* *//**
	  * 个人工作任务完成情况填写
	  * @param gzapRwxfJxzModel
	  * @return
	  *//*
     @PutMapping(value = "/editMyMission")
     public Result<GzapRwxfJxz> editMyMission(@RequestBody GzapRwxfJxz gzapRwxfJxzModel) {
         Result<GzapRwxfJxz> result = new Result<GzapRwxfJxz>();
         GzapRwxfJxz gzapRwxfJxzEntity = gzapRwxfJxzService.getById(gzapRwxfJxzModel.getId());
         if(gzapRwxfJxzEntity == null) {
             result.error500("未找到对应实体");
         } else {
             boolean ok = gzapRwxfJxzService.updateById(gzapRwxfJxzModel);
             if(ok) {
                 long rwz = gzapRwxfJxzModel.getRwz();
                 long wcz = gzapRwxfJxzModel.getWcz();
                 System.out.println("@@@@@@@@@@@@@@@@::任务值::"+rwz);
                 System.out.println("@@@@@@@@@@@@@@@@::完成值::"+wcz);
                 if(wcz < rwz) {
                     gzapRwxfJxzEntity.setSfdb("未达标");
                 } else if (wcz >= rwz) {
                     gzapRwxfJxzEntity.setSfdb("已达标");
                 }
                 gzapRwxfJxzEntity.setZt("2");
                 gzapRwxfJxzService.updateById(gzapRwxfJxzEntity);
                 result.success("修改成功!");
             }
         }
         return result;
     }*/

}

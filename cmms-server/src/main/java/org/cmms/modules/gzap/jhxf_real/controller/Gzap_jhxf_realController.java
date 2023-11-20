package org.cmms.modules.gzap.jhxf_real.controller;

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
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.gzap.jhxf_real.entity.Gzap_jhxf_khjl_real;
import org.cmms.modules.gzap.jhxf_real.entity.Gzap_jhxf_real;
import org.cmms.modules.gzap.jhxf_real.vo.Gzap_jhxf_realPage;
import org.cmms.modules.gzap.jhxf_real.service.IGzap_jhxf_realService;
import org.cmms.modules.gzap.jhxf_real.service.IGzap_jhxf_khjl_realService;
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
 * @Description: 计划下发
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
@RestController
@RequestMapping("/Gzap_jhxf/gzap_jhxf_real")
@Slf4j
public class Gzap_jhxf_realController {
	@Autowired
	private IGzap_jhxf_realService gzap_jhxf_realService;
	@Autowired
	private IGzap_jhxf_khjl_realService gzap_jhxf_khjl_realService;

	/**
	  * 分页列表查询
	 * @param gzap_jhxf_real
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<Gzap_jhxf_real>> queryPageList(Gzap_jhxf_real gzap_jhxf_real,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Gzap_jhxf_real>> result = new Result<IPage<Gzap_jhxf_real>>();
		QueryWrapper<Gzap_jhxf_real> queryWrapper = QueryGenerator.initQueryWrapper(gzap_jhxf_real, req.getParameterMap());
		Page<Gzap_jhxf_real> page = new Page<Gzap_jhxf_real>(pageNo, pageSize);
		IPage<Gzap_jhxf_real> pageList = gzap_jhxf_realService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}


//	 @RequestMapping(value = "/fomdataParse", method = RequestMethod.PUT)
//	 public Result<?> edit_XZC(@RequestBody JSONObject yxdygl_ejyxdygl) {
//		 Result<Yxdygl_ejyxdygl> result = new Result<Yxdygl_ejyxdygl>();
//		 System.out.println(yxdygl_ejyxdygl.getJSONArray("data"));
//		 System.out.println(yxdygl_ejyxdygl.getString("khmc"));
//		 return result;
//	 }




	 /**
	  *   添加
	 * @param gzap_jhxf_realPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<Gzap_jhxf_real> add(@RequestBody Gzap_jhxf_realPage gzap_jhxf_realPage) {
		Result<Gzap_jhxf_real> result = new Result<Gzap_jhxf_real>();
		try {
			Gzap_jhxf_real gzap_jhxf_real = new Gzap_jhxf_real();
			BeanUtils.copyProperties(gzap_jhxf_realPage, gzap_jhxf_real);

			gzap_jhxf_realService.saveMain(gzap_jhxf_real, gzap_jhxf_realPage.getGzap_jhxf_khjl_realList());
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param gzap_jhxf_realPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<Gzap_jhxf_real> edit(@RequestBody Gzap_jhxf_realPage gzap_jhxf_realPage) {
		Result<Gzap_jhxf_real> result = new Result<Gzap_jhxf_real>();
		Gzap_jhxf_real gzap_jhxf_real = new Gzap_jhxf_real();
		BeanUtils.copyProperties(gzap_jhxf_realPage, gzap_jhxf_real);
		Gzap_jhxf_real gzap_jhxf_realEntity = gzap_jhxf_realService.getById(gzap_jhxf_real.getId());
		if(gzap_jhxf_realEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = gzap_jhxf_realService.updateById(gzap_jhxf_real);
			gzap_jhxf_realService.updateMain(gzap_jhxf_real, gzap_jhxf_realPage.getGzap_jhxf_khjl_realList());
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
			gzap_jhxf_realService.delMain(id);
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
	public Result<Gzap_jhxf_real> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Gzap_jhxf_real> result = new Result<Gzap_jhxf_real>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.gzap_jhxf_realService.delBatchMain(Arrays.asList(ids.split(",")));
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
	public Result<Gzap_jhxf_real> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Gzap_jhxf_real> result = new Result<Gzap_jhxf_real>();
		Gzap_jhxf_real gzap_jhxf_real = gzap_jhxf_realService.getById(id);
		if(gzap_jhxf_real==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(gzap_jhxf_real);
			result.setSuccess(true);
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryGzap_jhxf_khjl_realByMainId")
	public Result<List<Gzap_jhxf_khjl_real>> queryGzap_jhxf_khjl_realListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<Gzap_jhxf_khjl_real>> result = new Result<List<Gzap_jhxf_khjl_real>>();
		List<Gzap_jhxf_khjl_real> gzap_jhxf_khjl_realList = gzap_jhxf_khjl_realService.selectByMainId(id);
		result.setResult(gzap_jhxf_khjl_realList);
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
      QueryWrapper<Gzap_jhxf_real> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Gzap_jhxf_real gzap_jhxf_real = JSON.parseObject(deString, Gzap_jhxf_real.class);
              queryWrapper = QueryGenerator.initQueryWrapper(gzap_jhxf_real, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Gzap_jhxf_realPage> pageList = new ArrayList<Gzap_jhxf_realPage>();
      List<Gzap_jhxf_real> gzap_jhxf_realList = gzap_jhxf_realService.list(queryWrapper);
      for (Gzap_jhxf_real gzap_jhxf_real : gzap_jhxf_realList) {
          Gzap_jhxf_realPage vo = new Gzap_jhxf_realPage();
          BeanUtils.copyProperties(gzap_jhxf_real, vo);
          List<Gzap_jhxf_khjl_real> gzap_jhxf_khjl_realList = gzap_jhxf_khjl_realService.selectByMainId(gzap_jhxf_real.getId());
          vo.setGzap_jhxf_khjl_realList(gzap_jhxf_khjl_realList);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "计划下发列表");
      mv.addObject(NormalExcelConstants.CLASS, Gzap_jhxf_realPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("计划下发列表数据", "导出人:Jeecg", "导出信息"));
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
              List<Gzap_jhxf_realPage> list = ExcelImportUtil.importExcel(file.getInputStream(), Gzap_jhxf_realPage.class, params);
              for (Gzap_jhxf_realPage page : list) {
                  Gzap_jhxf_real po = new Gzap_jhxf_real();
                  BeanUtils.copyProperties(page, po);
                  gzap_jhxf_realService.saveMain(po, page.getGzap_jhxf_khjl_realList());
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

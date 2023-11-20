package org.cmms.modules.gzap.jhxf.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.gzap.jhxf.entity.jhxf;
import org.cmms.modules.gzap.jhxf.entity.jhxf_khjl;
import org.cmms.modules.gzap.jhxf.service.IjhxfService;
import org.cmms.modules.gzap.jhxf.service.Ijhxf_khjlService;
import org.cmms.modules.gzap.jhxf.vo.jhxfPage;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

 /**
 * @Description: 计划下发
 * @Author: cmms
 * @Date:   2019-09-25
 * @Version: V1.0
 */
@RestController
@RequestMapping("/gzap_jhxf/jhxf")
@Slf4j
public class jhxfController {
	@Autowired
	private IjhxfService jhxfService;
	@Autowired
	private Ijhxf_khjlService jhxf_khjlService;

	/**
	  * 分页列表查询
	 * @param jhxf
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<jhxf>> queryPageList(jhxf jhxf,
											 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
											 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
											 HttpServletRequest req) {
		Result<IPage<jhxf>> result = new Result<IPage<jhxf>>();
		QueryWrapper<jhxf> queryWrapper = QueryGenerator.initQueryWrapper(jhxf, req.getParameterMap());
		Page<jhxf> page = new Page<jhxf>(pageNo, pageSize);
		IPage<jhxf> pageList = jhxfService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param jhxfPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<jhxf> add(@RequestBody jhxfPage jhxfPage) {
		Result<jhxf> result = new Result<jhxf>();
		try {
			jhxf jhxf = new jhxf();
			BeanUtils.copyProperties(jhxfPage, jhxf);

			jhxfService.saveMain(jhxf, jhxfPage.getJhxf_khjlList());
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param jhxfPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<jhxf> edit(@RequestBody jhxfPage jhxfPage) {
		Result<jhxf> result = new Result<jhxf>();
		jhxf jhxf = new jhxf();
		BeanUtils.copyProperties(jhxfPage, jhxf);
		jhxf jhxfEntity = jhxfService.getById(jhxf.getId());
		if(jhxfEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = jhxfService.updateById(jhxf);
			jhxfService.updateMain(jhxf, jhxfPage.getJhxf_khjlList());
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
			jhxfService.delMain(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}

	 /**
	  *   通过id审核
	  * @param
	  * @return
	  */
	 @DeleteMapping(value = "/check")
	 public Result<?> check(@RequestParam(name="id",required=true) String id) {
		 try {
			 jhxf jhxf = new jhxf();
             jhxf.setId(id);
             jhxf.setZt("2");
			 jhxfService.updateById(jhxf);
		 } catch (Exception e) {
			 log.error("结束失败",e.getMessage());
			 return Result.error("结束失败!");
		 }
		 return Result.ok("成功结束!");
	 }


	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<jhxf> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<jhxf> result = new Result<jhxf>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.jhxfService.delBatchMain(Arrays.asList(ids.split(",")));
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
	public Result<jhxf> queryById(@RequestParam(name="id",required=true) String id) {
		Result<jhxf> result = new Result<jhxf>();
		jhxf jhxf = jhxfService.getById(id);
		if(jhxf==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(jhxf);
			result.setSuccess(true);
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryjhxf_khjlByMainId")
	public Result<List<jhxf_khjl>> queryjhxf_khjlListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<jhxf_khjl>> result = new Result<List<jhxf_khjl>>();
		List<jhxf_khjl> jhxf_khjlList = jhxf_khjlService.selectByMainId(id);
		result.setResult(jhxf_khjlList);
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
      QueryWrapper<jhxf> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              jhxf jhxf = JSON.parseObject(deString, jhxf.class);
              queryWrapper = QueryGenerator.initQueryWrapper(jhxf, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<jhxfPage> pageList = new ArrayList<jhxfPage>();
      List<jhxf> jhxfList = jhxfService.list(queryWrapper);
      for (jhxf jhxf : jhxfList) {
          jhxfPage vo = new jhxfPage();
          BeanUtils.copyProperties(jhxf, vo);
          List<jhxf_khjl> jhxf_khjlList = jhxf_khjlService.selectByMainId(jhxf.getId());
          vo.setJhxf_khjlList(jhxf_khjlList);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "计划下发列表");
      mv.addObject(NormalExcelConstants.CLASS, jhxfPage.class);
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
              List<jhxfPage> list = ExcelImportUtil.importExcel(file.getInputStream(), jhxfPage.class, params);
              for (jhxfPage page : list) {
                  jhxf po = new jhxf();
                  BeanUtils.copyProperties(page, po);
                  jhxfService.saveMain(po, page.getJhxf_khjlList());
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

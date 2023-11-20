package org.cmms.modules.yxgl.khhhmx.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.yxgl.khhhmx.entity.YxglKhhffjxxb;
import org.cmms.modules.yxgl.khhhmx.entity.YxglKhhfmxb;
import org.cmms.modules.yxgl.khhhmx.vo.YxglKhhfmxbPage;
import org.cmms.modules.yxgl.khhhmx.service.IYxglKhhfmxbService;
import org.cmms.modules.yxgl.khhhmx.service.IYxglKhhffjxxbService;
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
 * @Description: 客户回访明细
 * @Author: cmms
 * @Date:   2019-12-23
 * @Version: V1.0
 */
@RestController
@RequestMapping("/yxgl.khhhmx/yxglKhhfmxb")
@Slf4j
public class YxglKhhfmxbController {
	@Autowired
	private IYxglKhhfmxbService yxglKhhfmxbService;
	@Autowired
	private IYxglKhhffjxxbService yxglKhhffjxxbService;
	
	/**
	  * 分页列表查询
	 * @param yxglKhhfmxb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<YxglKhhfmxb>> queryPageList(YxglKhhfmxb yxglKhhfmxb,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<YxglKhhfmxb>> result = new Result<IPage<YxglKhhfmxb>>();
		QueryWrapper<YxglKhhfmxb> queryWrapper = QueryGenerator.initQueryWrapper(yxglKhhfmxb, req.getParameterMap());
		Page<YxglKhhfmxb> page = new Page<YxglKhhfmxb>(pageNo, pageSize);
		IPage<YxglKhhfmxb> pageList = yxglKhhfmxbService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param yxglKhhfmxbPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<YxglKhhfmxb> add(@RequestBody YxglKhhfmxbPage yxglKhhfmxbPage) {
		Result<YxglKhhfmxb> result = new Result<YxglKhhfmxb>();
		try {
			YxglKhhfmxb yxglKhhfmxb = new YxglKhhfmxb();
			BeanUtils.copyProperties(yxglKhhfmxbPage, yxglKhhfmxb);
			yxglKhhfmxbService.saveMain(yxglKhhfmxb, yxglKhhfmxbPage.getYxglKhhffjxxbList());
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param yxglKhhfmxbPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<YxglKhhfmxb> edit(@RequestBody YxglKhhfmxbPage yxglKhhfmxbPage) {
		Result<YxglKhhfmxb> result = new Result<YxglKhhfmxb>();
		YxglKhhfmxb yxglKhhfmxb = new YxglKhhfmxb();
		BeanUtils.copyProperties(yxglKhhfmxbPage, yxglKhhfmxb);
		yxglKhhfmxb.setSfwchf("1");
		YxglKhhfmxb yxglKhhfmxbEntity = yxglKhhfmxbService.getById(yxglKhhfmxb.getId());
		if(yxglKhhfmxbEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = yxglKhhfmxbService.updateById(yxglKhhfmxb);
			yxglKhhfmxbService.updateMain(yxglKhhfmxb, yxglKhhfmxbPage.getYxglKhhffjxxbList());
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
			yxglKhhfmxbService.delMain(id);
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
	public Result<YxglKhhfmxb> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<YxglKhhfmxb> result = new Result<YxglKhhfmxb>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.yxglKhhfmxbService.delBatchMain(Arrays.asList(ids.split(",")));
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
	public Result<YxglKhhfmxb> queryById(@RequestParam(name="id",required=true) String id) {
		Result<YxglKhhfmxb> result = new Result<YxglKhhfmxb>();
		YxglKhhfmxb yxglKhhfmxb = yxglKhhfmxbService.getById(id);
		if(yxglKhhfmxb==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(yxglKhhfmxb);
			result.setSuccess(true);
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryYxglKhhffjxxbByMainId")
	public Result<Map<String,List<YxglKhhffjxxb>>> queryYxglKhhffjxxbListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<Map<String,List<YxglKhhffjxxb>>> result = new Result<Map<String,List<YxglKhhffjxxb>>>();
		List<YxglKhhffjxxb> yxglKhhffjxxbList = yxglKhhffjxxbService.selectByMainId(id);
		Map<String,List<YxglKhhffjxxb>> map = new HashMap<>();
		List<YxglKhhffjxxb> fjxxImage = new ArrayList<YxglKhhffjxxb>();
		List<YxglKhhffjxxb> fjxxVideo = new ArrayList<YxglKhhffjxxb>();
		List<YxglKhhffjxxb> fjxxAudio = new ArrayList<YxglKhhffjxxb>();
		for (YxglKhhffjxxb yxglKhhffjxxb : yxglKhhffjxxbList) {
			String fjlx = yxglKhhffjxxb.getFjlx();
			if ("1".equalsIgnoreCase(fjlx)) {
				fjxxImage.add(yxglKhhffjxxb);
			} else if ("2".equalsIgnoreCase(fjlx)) {
				fjxxVideo.add(yxglKhhffjxxb);
			} else if ("3".equalsIgnoreCase(fjlx)) {
				fjxxAudio.add(yxglKhhffjxxb);
			}
		}
		map.put("fjxxImage", fjxxImage);
		map.put("fjxxVideo", fjxxVideo);
		map.put("fjxxAudio", fjxxAudio);
		result.setResult(map);
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
      QueryWrapper<YxglKhhfmxb> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              YxglKhhfmxb yxglKhhfmxb = JSON.parseObject(deString, YxglKhhfmxb.class);
              queryWrapper = QueryGenerator.initQueryWrapper(yxglKhhfmxb, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<YxglKhhfmxbPage> pageList = new ArrayList<YxglKhhfmxbPage>();
      List<YxglKhhfmxb> yxglKhhfmxbList = yxglKhhfmxbService.list(queryWrapper);
      for (YxglKhhfmxb yxglKhhfmxb : yxglKhhfmxbList) {
          YxglKhhfmxbPage vo = new YxglKhhfmxbPage();
          BeanUtils.copyProperties(yxglKhhfmxb, vo);
          List<YxglKhhffjxxb> yxglKhhffjxxbList = yxglKhhffjxxbService.selectByMainId(yxglKhhfmxb.getId());
          vo.setYxglKhhffjxxbList(yxglKhhffjxxbList);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "客户回访明细列表");
      mv.addObject(NormalExcelConstants.CLASS, YxglKhhfmxbPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户回访明细列表数据", "导出人:Jeecg", "导出信息"));
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
              List<YxglKhhfmxbPage> list = ExcelImportUtil.importExcel(file.getInputStream(), YxglKhhfmxbPage.class, params);
              for (YxglKhhfmxbPage page : list) {
                  YxglKhhfmxb po = new YxglKhhfmxb();
                  BeanUtils.copyProperties(page, po);
                  yxglKhhfmxbService.saveMain(po, page.getYxglKhhffjxxbList());
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

package org.cmms.modules.yxgl.khhfjh.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.yxgl.khhfjh.entity.YxglKhhfjh;
import org.cmms.modules.yxgl.khhfjh.service.IYxglKhhfjhService;
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
 * @Description: 客户回访计划
 * @Author: cmms
 * @Date:   2019-12-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户回访计划")
@RestController
@RequestMapping("/yxgl.khhfjh/yxglKhhfjh")
public class YxglKhhfjhController {
	@Autowired
	private IYxglKhhfjhService yxglKhhfjhService;
	
	/**
	  * 分页列表查询
	 * @param yxglKhhfjh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户回访计划-分页列表查询")
	@ApiOperation(value="客户回访计划-分页列表查询", notes="客户回访计划-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<YxglKhhfjh>> queryPageList(YxglKhhfjh yxglKhhfjh,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<YxglKhhfjh>> result = new Result<IPage<YxglKhhfjh>>();
		QueryWrapper<YxglKhhfjh> queryWrapper = QueryGenerator.initQueryWrapper(yxglKhhfjh, req.getParameterMap());
		Page<YxglKhhfjh> page = new Page<YxglKhhfjh>(pageNo, pageSize);
		IPage<YxglKhhfjh> pageList = yxglKhhfjhService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param yxglKhhfjh
	 * @return
	 */
	@AutoLog(value = "客户回访计划-添加")
	@ApiOperation(value="客户回访计划-添加", notes="客户回访计划-添加")
	@PostMapping(value = "/add")
	public Result<YxglKhhfjh> add(@RequestBody YxglKhhfjh yxglKhhfjh) {
		Result<YxglKhhfjh> result = new Result<YxglKhhfjh>();
		try {

			YxglKhhfjh kg = new YxglKhhfjh();
			kg.setJhkssj(yxglKhhfjh.getJhkssj());
			kg.setJhjssj(yxglKhhfjh.getJhjssj());
			kg.setHfkhdjlx(yxglKhhfjh.getHfkhdjlx());
			kg.setKhjl(yxglKhhfjh.getKhjl());
			Map<String, String[]> map = new HashMap<>();
			QueryWrapper<YxglKhhfjh> queryWrapper = QueryGenerator.initQueryWrapper(kg, map);
			List<YxglKhhfjh> gzap_jhxf_khjl = yxglKhhfjhService.list(queryWrapper);
			if (gzap_jhxf_khjl.size()== 0) {
				LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
				yxglKhhfjh.setSfmxsc("2");
				yxglKhhfjh.setKhjl(sysUser.getUsername());
				yxglKhhfjhService.save(yxglKhhfjh);
				result.success("添加成功！");
			}else{
				result.error500("同一时间区间内,需要回访的客户类型已存在");
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param yxglKhhfjh
	 * @return
	 */
	@AutoLog(value = "客户回访计划-编辑")
	@ApiOperation(value="客户回访计划-编辑", notes="客户回访计划-编辑")
	@PutMapping(value = "/edit")
	public Result<YxglKhhfjh> edit(@RequestBody YxglKhhfjh yxglKhhfjh) {
		Result<YxglKhhfjh> result = new Result<YxglKhhfjh>();
		UpdateWrapper<YxglKhhfjh> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("khjl",yxglKhhfjh.getKhjl()).eq("jhkssj",yxglKhhfjh.getJhkssj()).eq("jhjssj",yxglKhhfjh.getJhjssj());
		yxglKhhfjhService.update(yxglKhhfjh,updateWrapper);
		result.success("修改成功!");
		return result;
	}

	 /**
	  *   通过id删除
	  * @return
	  */
	 @PutMapping(value = "/delete")
	 public Result<?> delete(@RequestBody YxglKhhfjh yxglKhhfjh) {
		 try {
			 UpdateWrapper<YxglKhhfjh> updateWrapper = new UpdateWrapper<>();
			 updateWrapper.eq("khjl",yxglKhhfjh.getKhjl()).eq("jhkssj",yxglKhhfjh.getJhkssj()).eq("jhjssj",yxglKhhfjh.getJhjssj());
			 yxglKhhfjhService.remove(updateWrapper);

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
	@AutoLog(value = "客户回访计划-批量删除")
	@ApiOperation(value="客户回访计划-批量删除", notes="客户回访计划-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<YxglKhhfjh> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<YxglKhhfjh> result = new Result<YxglKhhfjh>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.yxglKhhfjhService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户回访计划-通过id查询")
	@ApiOperation(value="客户回访计划-通过id查询", notes="客户回访计划-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<YxglKhhfjh> queryById(@RequestParam(name="id",required=true) String id) {
		Result<YxglKhhfjh> result = new Result<YxglKhhfjh>();
		YxglKhhfjh yxglKhhfjh = yxglKhhfjhService.getById(id);
		if(yxglKhhfjh==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(yxglKhhfjh);
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
      QueryWrapper<YxglKhhfjh> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              YxglKhhfjh yxglKhhfjh = JSON.parseObject(deString, YxglKhhfjh.class);
              queryWrapper = QueryGenerator.initQueryWrapper(yxglKhhfjh, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<YxglKhhfjh> pageList = yxglKhhfjhService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "客户回访计划列表");
      mv.addObject(NormalExcelConstants.CLASS, YxglKhhfjh.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户回访计划列表数据", "导出人:Jeecg", "导出信息"));
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
              List<YxglKhhfjh> listYxglKhhfjhs = ExcelImportUtil.importExcel(file.getInputStream(), YxglKhhfjh.class, params);
              yxglKhhfjhService.saveBatch(listYxglKhhfjhs);
              return Result.ok("文件导入成功！数据行数:" + listYxglKhhfjhs.size());
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
	  *   提取
	  * @return
	  */
	 @PutMapping(value = "/extract")
	 public Result<?> extract(@RequestBody JSONObject jsonObject) {
		 try {

			 SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			 String jhkssj = formatter.format(jsonObject.getDate("jhkssj"));
			 String jhjssj = formatter.format(jsonObject.getDate("jhjssj"));

			 Map<String,String> map = new HashMap<>();
			 map.put("khjl",jsonObject.getString("khjl"));
			 map.put("jhkssj",jhkssj);
			 map.put("jhjssj",jhjssj);
			 map.put("hfkhdjlx",jsonObject.getString("hfkhdjlx"));
			 yxglKhhfjhService.extract(map);
		 } catch (Exception e) {
			 log.error("明细生成失败",e.getMessage());
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("明细生成成功!");
	 }

}

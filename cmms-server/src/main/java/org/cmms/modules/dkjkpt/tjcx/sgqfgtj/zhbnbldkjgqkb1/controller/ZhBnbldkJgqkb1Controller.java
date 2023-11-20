package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhbnbldkjgqkb1.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.FileUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhzcdkjgqkb.entity.RepYwbbQhzcdkjgqkb;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhbnbldkjgqkb1.entity.ZhBnbldkJgqkb1;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhbnbldkjgqkb1.entity.ZhBnbldkJgqkb1Import;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhbnbldkjgqkb1.service.IZhBnbldkJgqkb1Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 支行表内不良贷款结构情况表1
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行表内不良贷款结构情况表1")
@RestController
@RequestMapping("/dkjkpt.tjcx.sgqfgtj.zhbnbldkjgqkb1/zhBnbldkJgqkb1")
public class ZhBnbldkJgqkb1Controller {
	@Autowired
	private IZhBnbldkJgqkb1Service zhBnbldkJgqkb1Service;

	 @Autowired
	 private ITjfxZhbyService tjfxZhbyService;
	
	/**
	  * 分页列表查询
	 * @param zhBnbldkJgqkb1
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行表内不良贷款结构情况表1-分页列表查询")
	@ApiOperation(value="支行表内不良贷款结构情况表1-分页列表查询", notes="支行表内不良贷款结构情况表1-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ZhBnbldkJgqkb1>> queryPageList(ZhBnbldkJgqkb1 zhBnbldkJgqkb1,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<ZhBnbldkJgqkb1>> result = new Result<IPage<ZhBnbldkJgqkb1>>();
		QueryWrapper<ZhBnbldkJgqkb1> queryWrapper = QueryGenerator.initQueryWrapper(zhBnbldkJgqkb1, req.getParameterMap());
		Page<ZhBnbldkJgqkb1> page = new Page<ZhBnbldkJgqkb1>(pageNo, pageSize);
		IPage<ZhBnbldkJgqkb1> pageList = zhBnbldkJgqkb1Service.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param zhBnbldkJgqkb1
	 * @return
	 */
	@AutoLog(value = "支行表内不良贷款结构情况表1-添加")
	@ApiOperation(value="支行表内不良贷款结构情况表1-添加", notes="支行表内不良贷款结构情况表1-添加")
	@PostMapping(value = "/add")
	public Result<ZhBnbldkJgqkb1> add(@RequestBody ZhBnbldkJgqkb1 zhBnbldkJgqkb1) {
		Result<ZhBnbldkJgqkb1> result = new Result<ZhBnbldkJgqkb1>();
		try {
			zhBnbldkJgqkb1Service.save(zhBnbldkJgqkb1);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param zhBnbldkJgqkb1
	 * @return
	 */
	/*@AutoLog(value = "支行表内不良贷款结构情况表1-编辑")
	@ApiOperation(value="支行表内不良贷款结构情况表1-编辑", notes="支行表内不良贷款结构情况表1-编辑")
	@PutMapping(value = "/edit")
	public Result<ZhBnbldkJgqkb1> edit(@RequestBody ZhBnbldkJgqkb1 zhBnbldkJgqkb1) {
		Result<ZhBnbldkJgqkb1> result = new Result<ZhBnbldkJgqkb1>();
		ZhBnbldkJgqkb1 zhBnbldkJgqkb1Entity = zhBnbldkJgqkb1Service.getById(zhBnbldkJgqkb1.getId());
		if(zhBnbldkJgqkb1Entity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = zhBnbldkJgqkb1Service.updateById(zhBnbldkJgqkb1);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		
		return result;
	}*/
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行表内不良贷款结构情况表1-通过id删除")
	@ApiOperation(value="支行表内不良贷款结构情况表1-通过id删除", notes="支行表内不良贷款结构情况表1-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			zhBnbldkJgqkb1Service.removeById(id);
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
	@AutoLog(value = "支行表内不良贷款结构情况表1-批量删除")
	@ApiOperation(value="支行表内不良贷款结构情况表1-批量删除", notes="支行表内不良贷款结构情况表1-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<ZhBnbldkJgqkb1> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<ZhBnbldkJgqkb1> result = new Result<ZhBnbldkJgqkb1>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.zhBnbldkJgqkb1Service.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行表内不良贷款结构情况表1-通过id查询")
	@ApiOperation(value="支行表内不良贷款结构情况表1-通过id查询", notes="支行表内不良贷款结构情况表1-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ZhBnbldkJgqkb1> queryById(@RequestParam(name="id",required=true) String id) {
		Result<ZhBnbldkJgqkb1> result = new Result<ZhBnbldkJgqkb1>();
		ZhBnbldkJgqkb1 zhBnbldkJgqkb1 = zhBnbldkJgqkb1Service.getById(id);
		if(zhBnbldkJgqkb1==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(zhBnbldkJgqkb1);
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
      QueryWrapper<ZhBnbldkJgqkb1> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              ZhBnbldkJgqkb1 zhBnbldkJgqkb1 = JSON.parseObject(deString, ZhBnbldkJgqkb1.class);
              queryWrapper = QueryGenerator.initQueryWrapper(zhBnbldkJgqkb1, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<ZhBnbldkJgqkb1> pageList = zhBnbldkJgqkb1Service.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "支行表内不良贷款结构情况表1列表");
      mv.addObject(NormalExcelConstants.CLASS, ZhBnbldkJgqkb1.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("支行表内不良贷款结构情况表1列表数据", "导出人:Jeecg", "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
  }


	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @Autowired
	 private Environment environment;
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(ZhBnbldkJgqkb1 zhBnbldkJgqkb1,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 // Step.1 组装查询条件
		 QueryWrapper<ZhBnbldkJgqkb1> queryWrapper = QueryGenerator.initQueryWrapper(zhBnbldkJgqkb1, request.getParameterMap());

		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new TemplateExcelView());
		 Map<String,Object> map = new HashMap<String, Object>();
		 map.put("tbsj", DateUtil.formatDateTime("yyyy-MM-dd"));
		 List<ZhBnbldkJgqkb1> pageList = zhBnbldkJgqkb1Service.list(queryWrapper);
		 List<ZhBnbldkJgqkb1Import> pageList1 = new ArrayList<>();
		 String port = environment.getProperty("common.path.export");
		 for (ZhBnbldkJgqkb1 bnbldkJgqkb1 : pageList) {
			 bnbldkJgqkb1.setJgdm(bnbldkJgqkb1.getJgdm()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","ywjgdm",bnbldkJgqkb1.getJgdm()));
			 ZhBnbldkJgqkb1Import import1 = new ZhBnbldkJgqkb1Import();
			 BeanUtil.copyProperties(bnbldkJgqkb1,import1);
			 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
			 String tjyf1 = sdf.format(bnbldkJgqkb1.getTjyf());
			 import1.setTjyf(tjyf1);
			 pageList1.add(import1);
		 }

		 map.put("list", pageList1);
		 //导出文件名称
		 mv.addObject(JxlsConstants.FILE_NAME, "支行表内不良贷款结构情况表1）");
		 mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("支行表内不良贷款结构情况表1_template.xls"));
		 mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/支行表内不良贷款结构情况表1.xls");
		 mv.addObject(JxlsConstants.MAP_DATA, map);
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
              List<ZhBnbldkJgqkb1> listZhBnbldkJgqkb1s = ExcelImportUtil.importExcel(file.getInputStream(), ZhBnbldkJgqkb1.class, params);
              zhBnbldkJgqkb1Service.saveBatch(listZhBnbldkJgqkb1s);
              return Result.ok("文件导入成功！数据行数:" + listZhBnbldkJgqkb1s.size());
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
	  *   通过tjyf提取
	  * @param tjyf
	  * @return
	  */
	 @RequestMapping(value = "/extract" , method = RequestMethod.PUT)
	 public Result<?> extract(@RequestParam(name = "TJYF") String tjyf) {
		 try {
			 zhBnbldkJgqkb1Service.extract(tjyf);
		 } catch (Exception e) {
			 log.error("提取失败",e.getMessage());
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功!");
	 }

}

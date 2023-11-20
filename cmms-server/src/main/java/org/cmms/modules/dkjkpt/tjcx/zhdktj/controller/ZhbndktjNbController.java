package org.cmms.modules.dkjkpt.tjcx.zhdktj.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dkjkpt.tjcx.zhdktj.entity.ZhbndktjNb;
import org.cmms.modules.dkjkpt.tjcx.zhdktj.service.IZhbndktjNbService;
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
 * @Description: 支行表内贷款统计(年报)
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行表内贷款统计(年报)")
@RestController
@RequestMapping("/zhbndktj_nb/zhbndktjNb")
public class ZhbndktjNbController {
	@Autowired
	private IZhbndktjNbService zhbndktjNbService;
	
	/**
	  * 分页列表查询
	 * @param zhbndktjNb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行表内贷款统计(年报)-分页列表查询")
	@ApiOperation(value="支行表内贷款统计(年报)-分页列表查询", notes="支行表内贷款统计(年报)-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ZhbndktjNb>> queryPageList(ZhbndktjNb zhbndktjNb,
                                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                   HttpServletRequest req) {
		Result<IPage<ZhbndktjNb>> result = new Result<IPage<ZhbndktjNb>>();
		QueryWrapper<ZhbndktjNb> queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjNb, req.getParameterMap());
		Page<ZhbndktjNb> page = new Page<ZhbndktjNb>(pageNo, pageSize);
		IPage<ZhbndktjNb> pageList = zhbndktjNbService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param zhbndktjNb
	 * @return
	 */
	@AutoLog(value = "支行表内贷款统计(年报)-添加")
	@ApiOperation(value="支行表内贷款统计(年报)-添加", notes="支行表内贷款统计(年报)-添加")
	@PostMapping(value = "/add")
	public Result<ZhbndktjNb> add(@RequestBody ZhbndktjNb zhbndktjNb) {
		Result<ZhbndktjNb> result = new Result<ZhbndktjNb>();
		try {
			zhbndktjNbService.save(zhbndktjNb);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param zhbndktjNb
	 * @return
	 */
	@AutoLog(value = "支行表内贷款统计(年报)-编辑")
	@ApiOperation(value="支行表内贷款统计(年报)-编辑", notes="支行表内贷款统计(年报)-编辑")
	@PutMapping(value = "/edit")
	public Result<ZhbndktjNb> edit(@RequestBody ZhbndktjNb zhbndktjNb) {
		Result<ZhbndktjNb> result = new Result<ZhbndktjNb>();
		ZhbndktjNb zhbndktjNbEntity = zhbndktjNbService.getById(zhbndktjNb.getJgdm());
		if(zhbndktjNbEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = zhbndktjNbService.updateById(zhbndktjNb);
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
	@AutoLog(value = "支行表内贷款统计(年报)-通过id删除")
	@ApiOperation(value="支行表内贷款统计(年报)-通过id删除", notes="支行表内贷款统计(年报)-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			zhbndktjNbService.removeById(id);
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
	@AutoLog(value = "支行表内贷款统计(年报)-批量删除")
	@ApiOperation(value="支行表内贷款统计(年报)-批量删除", notes="支行表内贷款统计(年报)-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<ZhbndktjNb> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<ZhbndktjNb> result = new Result<ZhbndktjNb>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.zhbndktjNbService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行表内贷款统计(年报)-通过id查询")
	@ApiOperation(value="支行表内贷款统计(年报)-通过id查询", notes="支行表内贷款统计(年报)-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ZhbndktjNb> queryById(@RequestParam(name="id",required=true) String id) {
		Result<ZhbndktjNb> result = new Result<ZhbndktjNb>();
		ZhbndktjNb zhbndktjNb = zhbndktjNbService.getById(id);
		if(zhbndktjNb==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(zhbndktjNb);
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
      QueryWrapper<ZhbndktjNb> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              ZhbndktjNb zhbndktjNb = JSON.parseObject(deString, ZhbndktjNb.class);
              queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjNb, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<ZhbndktjNb> pageList = zhbndktjNbService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "支行表内贷款统计(年报)列表");
      mv.addObject(NormalExcelConstants.CLASS, ZhbndktjNb.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("支行表内贷款统计(年报)列表数据", "导出人:Jeecg", "导出信息"));
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
              List<ZhbndktjNb> listZhbndktjNbs = ExcelImportUtil.importExcel(file.getInputStream(), ZhbndktjNb.class, params);
              zhbndktjNbService.saveBatch(listZhbndktjNbs);
              return Result.ok("文件导入成功！数据行数:" + listZhbndktjNbs.size());
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
	  * @param jsonObject
	  * @return
	  */
	 @PutMapping(value = "/extract")
	 public Result<?> extract1(@RequestBody JSONObject jsonObject) {
	 	/*Map<String,String>parm = new HashMap<>();*/
		 try {
			 Map<String,String>parm = new HashMap<>();
			 parm.put("tjyf", jsonObject.getString("tjyf"));
			 parm.put("type", "3");
			 parm.put("tjwd", "");
			 zhbndktjNbService.extract(parm);
		 }catch (Exception e) {
			 log.error(e.getMessage(), "提取失败");
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功");

	 }

}

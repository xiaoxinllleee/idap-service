package org.cmms.modules.dkjkpt.tjcx.qhdktj.controller;

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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.cmms.modules.dkjkpt.tjcx.qhdktj.entity.QhbndktjNb;
import org.cmms.modules.dkjkpt.tjcx.qhdktj.service.IQhbndktjNbService;
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
 * @Description: 全行表内贷款统计(年报)
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="全行表内贷款统计(年报)")
@RestController
@RequestMapping("/qhbndktj_nb/qhbndktjNb")
public class QhbndktjNbController {
	@Autowired
	private IQhbndktjNbService qhbndktjNbService;
	
	/**
	  * 分页列表查询
	 * @param qhbndktjNb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "全行表内贷款统计(年报)-分页列表查询")
	@ApiOperation(value="全行表内贷款统计(年报)-分页列表查询", notes="全行表内贷款统计(年报)-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<QhbndktjNb>> queryPageList(QhbndktjNb qhbndktjNb,
                                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                   HttpServletRequest req) {
		Result<IPage<QhbndktjNb>> result = new Result<IPage<QhbndktjNb>>();
		QueryWrapper<QhbndktjNb> queryWrapper = QueryGenerator.initQueryWrapper(qhbndktjNb, req.getParameterMap());
		Page<QhbndktjNb> page = new Page<QhbndktjNb>(pageNo, pageSize);
		IPage<QhbndktjNb> pageList = qhbndktjNbService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param qhbndktjNb
	 * @return
	 */
	@AutoLog(value = "全行表内贷款统计(年报)-添加")
	@ApiOperation(value="全行表内贷款统计(年报)-添加", notes="全行表内贷款统计(年报)-添加")
	@PostMapping(value = "/add")
	public Result<QhbndktjNb> add(@RequestBody QhbndktjNb qhbndktjNb) {
		Result<QhbndktjNb> result = new Result<QhbndktjNb>();
		try {
			qhbndktjNbService.save(qhbndktjNb);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param qhbndktjNb
	 * @return
	 */
	/*@AutoLog(value = "全行表内贷款统计(年报)-编辑")
	@ApiOperation(value="全行表内贷款统计(年报)-编辑", notes="全行表内贷款统计(年报)-编辑")
	@PutMapping(value = "/edit")
	public Result<QhbndktjNb> edit(@RequestBody QhbndktjNb qhbndktjNb) {
		Result<QhbndktjNb> result = new Result<QhbndktjNb>();
		QhbndktjNb qhbndktjNbEntity = qhbndktjNbService.getById(qhbndktjNb.getId());
		if(qhbndktjNbEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = qhbndktjNbService.updateById(qhbndktjNb);
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
	@AutoLog(value = "全行表内贷款统计(年报)-通过id删除")
	@ApiOperation(value="全行表内贷款统计(年报)-通过id删除", notes="全行表内贷款统计(年报)-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			qhbndktjNbService.removeById(id);
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
	@AutoLog(value = "全行表内贷款统计(年报)-批量删除")
	@ApiOperation(value="全行表内贷款统计(年报)-批量删除", notes="全行表内贷款统计(年报)-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<QhbndktjNb> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<QhbndktjNb> result = new Result<QhbndktjNb>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.qhbndktjNbService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行表内贷款统计(年报)-通过id查询")
	@ApiOperation(value="全行表内贷款统计(年报)-通过id查询", notes="全行表内贷款统计(年报)-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<QhbndktjNb> queryById(@RequestParam(name="id",required=true) String id) {
		Result<QhbndktjNb> result = new Result<QhbndktjNb>();
		QhbndktjNb qhbndktjNb = qhbndktjNbService.getById(id);
		if(qhbndktjNb==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(qhbndktjNb);
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
      QueryWrapper<QhbndktjNb> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              QhbndktjNb qhbndktjNb = JSON.parseObject(deString, QhbndktjNb.class);
              queryWrapper = QueryGenerator.initQueryWrapper(qhbndktjNb, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<QhbndktjNb> pageList = qhbndktjNbService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "全行表内贷款统计(年报)列表");
      mv.addObject(NormalExcelConstants.CLASS, QhbndktjNb.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("全行表内贷款统计(年报)列表数据", "导出人:Jeecg", "导出信息"));
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
              List<QhbndktjNb> listQhbndktjNbs = ExcelImportUtil.importExcel(file.getInputStream(), QhbndktjNb.class, params);
              qhbndktjNbService.saveBatch(listQhbndktjNbs);
              return Result.ok("文件导入成功！数据行数:" + listQhbndktjNbs.size());
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
			 String tjyf= jsonObject.getString("tjyf");
			 tjyf = tjyf.replace("-","");
			 Map<String,String>parm = new HashMap<>();
			 parm.put("tjyf", tjyf);
			 parm.put("type", "3");
			 parm.put("tjwd", "");
			 qhbndktjNbService.extract(parm);
		 }catch (Exception e) {
			 log.error(e.getMessage(), "提取失败");
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功");

	 }

}

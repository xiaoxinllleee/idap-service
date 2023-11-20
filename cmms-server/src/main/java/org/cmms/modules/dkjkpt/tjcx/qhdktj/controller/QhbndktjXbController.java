package org.cmms.modules.dkjkpt.tjcx.qhdktj.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.cmms.modules.dkjkpt.tjcx.qhdktj.entity.QhbndktjXb;
import org.cmms.modules.dkjkpt.tjcx.qhdktj.service.IQhbndktjXbService;
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
 * @Description: 全行表内贷款统计(旬报)
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="全行表内贷款统计(旬报)")
@RestController
@RequestMapping("/qhbndktj_xb/qhbndktjXb")
public class QhbndktjXbController {
	@Autowired
	private IQhbndktjXbService qhbndktjXbService;
	
	/**
	  * 分页列表查询
	 * @param qhbndktjXb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "全行表内贷款统计(旬报)-分页列表查询")
	@ApiOperation(value="全行表内贷款统计(旬报)-分页列表查询", notes="全行表内贷款统计(旬报)-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<QhbndktjXb>> queryPageList(QhbndktjXb qhbndktjXb,
                                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                   HttpServletRequest req) {
		Result<IPage<QhbndktjXb>> result = new Result<IPage<QhbndktjXb>>();
		QueryWrapper<QhbndktjXb> queryWrapper = QueryGenerator.initQueryWrapper(qhbndktjXb, req.getParameterMap());
		Page<QhbndktjXb> page = new Page<QhbndktjXb>(pageNo, pageSize);
		IPage<QhbndktjXb> pageList = qhbndktjXbService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param qhbndktjXb
	 * @return
	 */
	@AutoLog(value = "全行表内贷款统计(旬报)-添加")
	@ApiOperation(value="全行表内贷款统计(旬报)-添加", notes="全行表内贷款统计(旬报)-添加")
	@PostMapping(value = "/add")
	public Result<QhbndktjXb> add(@RequestBody QhbndktjXb qhbndktjXb) {
		Result<QhbndktjXb> result = new Result<QhbndktjXb>();
		try {
			qhbndktjXbService.save(qhbndktjXb);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param qhbndktjXb
	 * @return
	 */
	/*@AutoLog(value = "全行表内贷款统计(旬报)-编辑")
	@ApiOperation(value="全行表内贷款统计(旬报)-编辑", notes="全行表内贷款统计(旬报)-编辑")
	@PutMapping(value = "/edit")
	public Result<QhbndktjXb> edit(@RequestBody QhbndktjXb qhbndktjXb) {
		Result<QhbndktjXb> result = new Result<QhbndktjXb>();
		QhbndktjXb qhbndktjXbEntity = qhbndktjXbService.getById(qhbndktjXb.getId());
		if(qhbndktjXbEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = qhbndktjXbService.updateById(qhbndktjXb);
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
	@AutoLog(value = "全行表内贷款统计(旬报)-通过id删除")
	@ApiOperation(value="全行表内贷款统计(旬报)-通过id删除", notes="全行表内贷款统计(旬报)-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			qhbndktjXbService.removeById(id);
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
	@AutoLog(value = "全行表内贷款统计(旬报)-批量删除")
	@ApiOperation(value="全行表内贷款统计(旬报)-批量删除", notes="全行表内贷款统计(旬报)-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<QhbndktjXb> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<QhbndktjXb> result = new Result<QhbndktjXb>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.qhbndktjXbService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行表内贷款统计(旬报)-通过id查询")
	@ApiOperation(value="全行表内贷款统计(旬报)-通过id查询", notes="全行表内贷款统计(旬报)-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<QhbndktjXb> queryById(@RequestParam(name="id",required=true) String id) {
		Result<QhbndktjXb> result = new Result<QhbndktjXb>();
		QhbndktjXb qhbndktjXb = qhbndktjXbService.getById(id);
		if(qhbndktjXb==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(qhbndktjXb);
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
      QueryWrapper<QhbndktjXb> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              QhbndktjXb qhbndktjXb = JSON.parseObject(deString, QhbndktjXb.class);
              queryWrapper = QueryGenerator.initQueryWrapper(qhbndktjXb, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<QhbndktjXb> pageList = qhbndktjXbService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "全行表内贷款统计(旬报)列表");
      mv.addObject(NormalExcelConstants.CLASS, QhbndktjXb.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("全行表内贷款统计(旬报)列表数据", "导出人:Jeecg", "导出信息"));
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
              List<QhbndktjXb> listQhbndktjXbs = ExcelImportUtil.importExcel(file.getInputStream(), QhbndktjXb.class, params);
              qhbndktjXbService.saveBatch(listQhbndktjXbs);
              return Result.ok("文件导入成功！数据行数:" + listQhbndktjXbs.size());
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
		 String sjwd="";
		 String tjyf = jsonObject.getString("tjyf");
		 DateFormat format1 = new SimpleDateFormat("yyyyMMdd");

		 if(jsonObject.getString("sjwd").equals("1")){
			 sjwd="10";
			 tjyf=tjyf.replace("-","")+sjwd;
		 }else if(jsonObject.getString("sjwd").equals("2")){
			 sjwd="20";
			 tjyf=tjyf.replace("-","")+sjwd;
		 }else{
			 sjwd="30";
			 //tjyf= DateUtil.formatDateTime("yyyyMMdd", DateUtil.getMonthEndDay(date).getTime());
			 tjyf=tjyf.replace("-","")+sjwd;
		 }
		 try {
			 Map<String,String>parm = new HashMap<>();
			 parm.put("tjyf", tjyf);
			 parm.put("type", "1");
			 parm.put("tjwd", sjwd);
			 System.out.println("@@@@@@@parm="+parm);
			 qhbndktjXbService.extract(parm);
		 }catch (Exception e) {
			 log.error(e.getMessage(), "提取失败");
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功");

	 }

}

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
import org.cmms.modules.dkjkpt.tjcx.zhdktj.entity.ZhbndktjYb;
import org.cmms.modules.dkjkpt.tjcx.zhdktj.service.IZhbndktjYbService;
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
 * @Description: 支行表内贷款统计(月报)
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行表内贷款统计(月报)")
@RestController
@RequestMapping("/zhbndktj_yb/zhbndktjYb")
public class ZhbndktjYbController {
	@Autowired
	private IZhbndktjYbService zhbndktjYbService;
	
	/**
	  * 分页列表查询
	 * @param zhbndktjYb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行表内贷款统计(月报)-分页列表查询")
	@ApiOperation(value="支行表内贷款统计(月报)-分页列表查询", notes="支行表内贷款统计(月报)-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ZhbndktjYb>> queryPageList(ZhbndktjYb zhbndktjYb,
                                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                   HttpServletRequest req) {
		Result<IPage<ZhbndktjYb>> result = new Result<IPage<ZhbndktjYb>>();
		QueryWrapper<ZhbndktjYb> queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjYb, req.getParameterMap());
		Page<ZhbndktjYb> page = new Page<ZhbndktjYb>(pageNo, pageSize);
		IPage<ZhbndktjYb> pageList = zhbndktjYbService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param zhbndktjYb
	 * @return
	 */
	@AutoLog(value = "支行表内贷款统计(月报)-添加")
	@ApiOperation(value="支行表内贷款统计(月报)-添加", notes="支行表内贷款统计(月报)-添加")
	@PostMapping(value = "/add")
	public Result<ZhbndktjYb> add(@RequestBody ZhbndktjYb zhbndktjYb) {
		Result<ZhbndktjYb> result = new Result<ZhbndktjYb>();
		try {
			zhbndktjYbService.save(zhbndktjYb);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param zhbndktjYb
	 * @return
	 */
	@AutoLog(value = "支行表内贷款统计(月报)-编辑")
	@ApiOperation(value="支行表内贷款统计(月报)-编辑", notes="支行表内贷款统计(月报)-编辑")
	@PutMapping(value = "/edit")
	public Result<ZhbndktjYb> edit(@RequestBody ZhbndktjYb zhbndktjYb) {
		Result<ZhbndktjYb> result = new Result<ZhbndktjYb>();
		ZhbndktjYb zhbndktjYbEntity = zhbndktjYbService.getById(zhbndktjYb.getJgdm());
		if(zhbndktjYbEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = zhbndktjYbService.updateById(zhbndktjYb);
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
	@AutoLog(value = "支行表内贷款统计(月报)-通过id删除")
	@ApiOperation(value="支行表内贷款统计(月报)-通过id删除", notes="支行表内贷款统计(月报)-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			zhbndktjYbService.removeById(id);
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
	@AutoLog(value = "支行表内贷款统计(月报)-批量删除")
	@ApiOperation(value="支行表内贷款统计(月报)-批量删除", notes="支行表内贷款统计(月报)-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<ZhbndktjYb> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<ZhbndktjYb> result = new Result<ZhbndktjYb>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.zhbndktjYbService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行表内贷款统计(月报)-通过id查询")
	@ApiOperation(value="支行表内贷款统计(月报)-通过id查询", notes="支行表内贷款统计(月报)-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ZhbndktjYb> queryById(@RequestParam(name="id",required=true) String id) {
		Result<ZhbndktjYb> result = new Result<ZhbndktjYb>();
		ZhbndktjYb zhbndktjYb = zhbndktjYbService.getById(id);
		if(zhbndktjYb==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(zhbndktjYb);
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
      QueryWrapper<ZhbndktjYb> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              ZhbndktjYb zhbndktjYb = JSON.parseObject(deString, ZhbndktjYb.class);
              queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjYb, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<ZhbndktjYb> pageList = zhbndktjYbService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "支行表内贷款统计(月报)列表");
      mv.addObject(NormalExcelConstants.CLASS, ZhbndktjYb.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("支行表内贷款统计(月报)列表数据", "导出人:Jeecg", "导出信息"));
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
              List<ZhbndktjYb> listZhbndktjYbs = ExcelImportUtil.importExcel(file.getInputStream(), ZhbndktjYb.class, params);
              zhbndktjYbService.saveBatch(listZhbndktjYbs);
              return Result.ok("文件导入成功！数据行数:" + listZhbndktjYbs.size());
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
			 String tjyf = jsonObject.getString("tjyf");
			 tjyf = tjyf.replace("-","");
			 parm.put("tjyf", tjyf);
			 parm.put("type", "2");
			 parm.put("tjwd", "");
			 System.out.println("@@@@@@@parm="+parm);
			 zhbndktjYbService.extract(parm);
		 }catch (Exception e) {
			 log.error(e.getMessage(), "提取失败");
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功");

	 }
}

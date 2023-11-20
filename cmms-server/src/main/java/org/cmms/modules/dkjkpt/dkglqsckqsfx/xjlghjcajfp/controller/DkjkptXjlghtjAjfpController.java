package org.cmms.modules.dkjkpt.dkglqsckqsfx.xjlghjcajfp.controller;

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
import org.cmms.modules.dkjkpt.dkglqsckqsfx.xjlghjcajfp.entity.DkjkptXjlghtjAjfp;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.xjlghjcajfp.service.IDkjkptXjlghtjAjfpService;
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
 * @Description: 现金流归行监测（按揭、扶贫）
 * @Author: cmms
 * @Date:   2019-10-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="现金流归行监测（按揭、扶贫）")
@RestController
@RequestMapping("/dkjkpt.dkglqsckqsfx.xjlghjcajfp/dkjkptXjlghtjAjfp")
public class DkjkptXjlghtjAjfpController {
	@Autowired
	private IDkjkptXjlghtjAjfpService dkjkptXjlghtjAjfpService;
	@Autowired
	private IDkjkptXjlghtjAjfpService iDkjkptXjlghtjAjfpService;
	/**
	  * 分页列表查询
	 * @param dkjkptXjlghtjAjfp
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "现金流归行监测（按揭、扶贫）-分页列表查询")
	@ApiOperation(value="现金流归行监测（按揭、扶贫）-分页列表查询", notes="现金流归行监测（按揭、扶贫）-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<DkjkptXjlghtjAjfp>> queryPageList(DkjkptXjlghtjAjfp dkjkptXjlghtjAjfp,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<DkjkptXjlghtjAjfp>> result = new Result<IPage<DkjkptXjlghtjAjfp>>();
		QueryWrapper<DkjkptXjlghtjAjfp> queryWrapper = QueryGenerator.initQueryWrapper(dkjkptXjlghtjAjfp, req.getParameterMap());
		Page<DkjkptXjlghtjAjfp> page = new Page<DkjkptXjlghtjAjfp>(pageNo, pageSize);
		IPage<DkjkptXjlghtjAjfp> pageList = dkjkptXjlghtjAjfpService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param dkjkptXjlghtjAjfp
	 * @return
	 */
	@AutoLog(value = "现金流归行监测（按揭、扶贫）-添加")
	@ApiOperation(value="现金流归行监测（按揭、扶贫）-添加", notes="现金流归行监测（按揭、扶贫）-添加")
	@PostMapping(value = "/add")
	public Result<DkjkptXjlghtjAjfp> add(@RequestBody DkjkptXjlghtjAjfp dkjkptXjlghtjAjfp) {
		Result<DkjkptXjlghtjAjfp> result = new Result<DkjkptXjlghtjAjfp>();
		try {
			dkjkptXjlghtjAjfpService.save(dkjkptXjlghtjAjfp);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param dkjkptXjlghtjAjfp
	 * @return
	 */
	/*@AutoLog(value = "现金流归行监测（按揭、扶贫）-编辑")
	@ApiOperation(value="现金流归行监测（按揭、扶贫）-编辑", notes="现金流归行监测（按揭、扶贫）-编辑")
	@PutMapping(value = "/edit")
	public Result<DkjkptXjlghtjAjfp> edit(@RequestBody DkjkptXjlghtjAjfp dkjkptXjlghtjAjfp) {
		Result<DkjkptXjlghtjAjfp> result = new Result<DkjkptXjlghtjAjfp>();
		DkjkptXjlghtjAjfp dkjkptXjlghtjAjfpEntity = dkjkptXjlghtjAjfpService.getById(dkjkptXjlghtjAjfp.getId());
		if(dkjkptXjlghtjAjfpEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dkjkptXjlghtjAjfpService.updateById(dkjkptXjlghtjAjfp);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		
		return result;
	}
	*/
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "现金流归行监测（按揭、扶贫）-通过id删除")
	@ApiOperation(value="现金流归行监测（按揭、扶贫）-通过id删除", notes="现金流归行监测（按揭、扶贫）-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			dkjkptXjlghtjAjfpService.removeById(id);
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
	@AutoLog(value = "现金流归行监测（按揭、扶贫）-批量删除")
	@ApiOperation(value="现金流归行监测（按揭、扶贫）-批量删除", notes="现金流归行监测（按揭、扶贫）-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<DkjkptXjlghtjAjfp> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<DkjkptXjlghtjAjfp> result = new Result<DkjkptXjlghtjAjfp>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.dkjkptXjlghtjAjfpService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "现金流归行监测（按揭、扶贫）-通过id查询")
	@ApiOperation(value="现金流归行监测（按揭、扶贫）-通过id查询", notes="现金流归行监测（按揭、扶贫）-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<DkjkptXjlghtjAjfp> queryById(@RequestParam(name="id",required=true) String id) {
		Result<DkjkptXjlghtjAjfp> result = new Result<DkjkptXjlghtjAjfp>();
		DkjkptXjlghtjAjfp dkjkptXjlghtjAjfp = dkjkptXjlghtjAjfpService.getById(id);
		if(dkjkptXjlghtjAjfp==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dkjkptXjlghtjAjfp);
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
      QueryWrapper<DkjkptXjlghtjAjfp> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              DkjkptXjlghtjAjfp dkjkptXjlghtjAjfp = JSON.parseObject(deString, DkjkptXjlghtjAjfp.class);
              queryWrapper = QueryGenerator.initQueryWrapper(dkjkptXjlghtjAjfp, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<DkjkptXjlghtjAjfp> pageList = dkjkptXjlghtjAjfpService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "现金流归行监测（按揭、扶贫）列表");
      mv.addObject(NormalExcelConstants.CLASS, DkjkptXjlghtjAjfp.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("现金流归行监测（按揭、扶贫）列表数据", "导出人:Jeecg", "导出信息"));
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
              List<DkjkptXjlghtjAjfp> listDkjkptXjlghtjAjfps = ExcelImportUtil.importExcel(file.getInputStream(), DkjkptXjlghtjAjfp.class, params);
              dkjkptXjlghtjAjfpService.saveBatch(listDkjkptXjlghtjAjfps);
              return Result.ok("文件导入成功！数据行数:" + listDkjkptXjlghtjAjfps.size());
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
		 try {
			 Map<String,String > parm = new HashMap<String,String>();
			 parm.put("ksrq",jsonObject.getString("ksrq"));
			 parm.put("jsrq",jsonObject.getString("jsrq"));
			 iDkjkptXjlghtjAjfpService.extract(parm);
		 } catch (Exception e) {
			 log.error(e.getMessage(), "提取失败");
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功");
	 }

}

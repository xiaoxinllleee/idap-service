package org.cmms.modules.tjfx.khjbfctj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import org.cmms.modules.tjfx.khjbfctj.service.IKhjbfctjKhjlService;
import org.cmms.modules.tjfx.khjbfctj.entity.KhjbfctjKhjl;
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
 * @Description: 客户级别分层统计_客户经理
 * @Author: cmms
 * @Date:   2019-12-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户级别分层统计_客户经理")
@RestController
@RequestMapping("/khfctj/khfctjKhjl")
public class KhjbfctjKhjlController {
	@Autowired
	private IKhjbfctjKhjlService iKhjbfctjKhjlService;
	
	/**
	  * 分页列表查询
	 * @param khjbfctjKhjl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户级别分层统计_客户经理-分页列表查询")
	@ApiOperation(value="客户级别分层统计_客户经理-分页列表查询", notes="客户级别分层统计_客户经理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<KhjbfctjKhjl>> queryPageList(KhjbfctjKhjl khjbfctjKhjl,
                                                     @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                     @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                     HttpServletRequest req) {
		Result<IPage<KhjbfctjKhjl>> result = new Result<IPage<KhjbfctjKhjl>>();
		QueryWrapper<KhjbfctjKhjl> queryWrapper = QueryGenerator.initQueryWrapper(khjbfctjKhjl, req.getParameterMap());
		Page<KhjbfctjKhjl> page = new Page<KhjbfctjKhjl>(pageNo, pageSize);
		IPage<KhjbfctjKhjl> pageList = iKhjbfctjKhjlService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
     * 添加
	 * @param khfctjKhjl
	 * @return
	 */
	/*@AutoLog(value = "客户级别分层统计_客户经理-添加")
	@ApiOperation(value="客户级别分层统计_客户经理-添加", notes="客户级别分层统计_客户经理-添加")
	@PostMapping(value = "/add")
	public Result<KhfctjKhjl> add(@RequestBody KhfctjKhjl khfctjKhjl) {
		Result<KhfctjKhjl> result = new Result<KhfctjKhjl>();
		try {
			iKhjbfctjKhjlService.save(khfctjKhjl);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}*/
	
	/**
     * 编辑
	 * @param khfctjKhjl
	 * @return
	 */
	/*@AutoLog(value = "客户级别分层统计_客户经理-编辑")
	@ApiOperation(value="客户级别分层统计_客户经理-编辑", notes="客户级别分层统计_客户经理-编辑")
	@PutMapping(value = "/edit")
	public Result<KhfctjKhjl> edit(@RequestBody KhfctjKhjl khfctjKhjl) {
		Result<KhfctjKhjl> result = new Result<KhfctjKhjl>();
		KhfctjKhjl khfctjKhjlEntity = iKhjbfctjKhjlService.getById(khfctjKhjl.getId());
		if(khfctjKhjlEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = iKhjbfctjKhjlService.updateById(khfctjKhjl);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		return result;
	}*/
	
	/**
     * 通过id删除
	 * @param id
	 * @return
	 */
	/*@AutoLog(value = "客户级别分层统计_客户经理-通过id删除")
	@ApiOperation(value="客户级别分层统计_客户经理-通过id删除", notes="客户级别分层统计_客户经理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			iKhjbfctjKhjlService.removeById(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}*/
	
	/**
     * 批量删除
	 * @param ids
	 * @return
	 */
	/*@AutoLog(value = "客户级别分层统计_客户经理-批量删除")
	@ApiOperation(value="客户级别分层统计_客户经理-批量删除", notes="客户级别分层统计_客户经理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<KhfctjKhjl> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<KhfctjKhjl> result = new Result<KhfctjKhjl>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.iKhjbfctjKhjlService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}*/
	
	/**
     * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户级别分层统计_客户经理-通过id查询")
	@ApiOperation(value="客户级别分层统计_客户经理-通过id查询", notes="客户级别分层统计_客户经理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<KhjbfctjKhjl> queryById(@RequestParam(name="id",required=true) String id) {
		Result<KhjbfctjKhjl> result = new Result<KhjbfctjKhjl>();
		KhjbfctjKhjl khjbfctjKhjl = iKhjbfctjKhjlService.getById(id);
		if(khjbfctjKhjl ==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(khjbfctjKhjl);
			result.setSuccess(true);
		}
		return result;
	}

  /**
   * 导出excel
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<KhjbfctjKhjl> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              KhjbfctjKhjl khjbfctjKhjl = JSON.parseObject(deString, KhjbfctjKhjl.class);
              queryWrapper = QueryGenerator.initQueryWrapper(khjbfctjKhjl, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }
      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<KhjbfctjKhjl> pageList = iKhjbfctjKhjlService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "客户级别分层统计_客户经理");
      mv.addObject(NormalExcelConstants.CLASS, KhjbfctjKhjl.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户级别分层统计_客户经理数据", "导出人:Hnran", "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
  }

  /**
   * 通过excel导入数据
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
              List<KhjbfctjKhjl> listKhjbfctjKhjls = ExcelImportUtil.importExcel(file.getInputStream(), KhjbfctjKhjl.class, params);
              iKhjbfctjKhjlService.saveBatch(listKhjbfctjKhjls);
              return Result.ok("文件导入成功！数据行数:" + listKhjbfctjKhjls.size());
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
   * 客户级别分层数据提取
   * @param jsonObject
   * @return
   */
  @PutMapping(value = "/extract")
  public Result<?> extract(@RequestBody JSONObject jsonObject) {
      try {
          Map<String, String> param = new HashMap<>();
          param.put("tjwd", jsonObject.getString("tjwd"));
          param.put("tjrq", jsonObject.getString("tjrq"));
          System.out.println("客户级别分层数据提取 =>| tjwd："+jsonObject.getString("tjwd"));
          System.out.println("客户级别分层数据提取 =>| tjrq："+jsonObject.getString("tjrq"));
          iKhjbfctjKhjlService.extract(param);
      } catch (Exception exception) {
          log.error(exception.getMessage(),"统计失败！");
          return Result.error(exception.getMessage());
      }
      return Result.ok("统计成功！");
  }
}

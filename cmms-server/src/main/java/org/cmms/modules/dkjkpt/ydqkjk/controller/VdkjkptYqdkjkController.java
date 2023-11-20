package org.cmms.modules.dkjkpt.ydqkjk.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dkjkpt.ydqkjk.entity.VdkjkptYqdkjk;
import org.cmms.modules.dkjkpt.ydqkjk.service.IVdkjkptYqdkjkService;
import java.util.Date;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 逾期贷款监控
 * @Author: cmms
 * @Date:   2019-09-12
 * @Version: V1.0
 */
@Slf4j
@Api(tags="逾期贷款监控")
@RestController
@RequestMapping("/dkjkpt/ydqkjk/vdkjkptYqdkjk")
public class VdkjkptYqdkjkController {
	@Autowired
	private IVdkjkptYqdkjkService vdkjkptYqdkjkService;
	 @Autowired
	 private ITjfxZhbyService tjfxZhbyService;

	/**
	  * 分页列表查询
	 * @param vdkjkptYqdkjk
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "逾期贷款监控-分页列表查询")
	@ApiOperation(value="逾期贷款监控-分页列表查询", notes="逾期贷款监控-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<VdkjkptYqdkjk>> queryPageList(VdkjkptYqdkjk vdkjkptYqdkjk,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<VdkjkptYqdkjk>> result = new Result<IPage<VdkjkptYqdkjk>>();
		QueryWrapper<VdkjkptYqdkjk> queryWrapper = QueryGenerator.initQueryWrapper(vdkjkptYqdkjk, req.getParameterMap());
		Page<VdkjkptYqdkjk> page = new Page<VdkjkptYqdkjk>(pageNo, pageSize);
		IPage<VdkjkptYqdkjk> pageList = vdkjkptYqdkjkService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	 /**
	  * 分页列表查询
	  * @param vdkjkptYqdkjk
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "逾期贷款监控-分页列表查询")
	 @ApiOperation(value="逾期贷款监控-分页列表查询", notes="逾期贷款监控-分页列表查询")
	 @GetMapping(value = "/ipadlist")
	 public Result<?> queryIpadList(VdkjkptYqdkjk vdkjkptYqdkjk,
													   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
													   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
													   HttpServletRequest req) {
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 vdkjkptYqdkjk.setJgdm( sysUser.getOrgCode() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization", "ywjgdm", "ZZBZ", sysUser.getOrgCode()));
		 QueryWrapper<VdkjkptYqdkjk> queryWrapper = QueryGenerator.initQueryWrapper(vdkjkptYqdkjk, req.getParameterMap());
		 Page<VdkjkptYqdkjk> page = new Page<VdkjkptYqdkjk>(pageNo, pageSize);
		 IPage<VdkjkptYqdkjk> pageList = vdkjkptYqdkjkService.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }

	/**
	  *   添加
	 * @param vdkjkptYqdkjk
	 * @return
	 */
	@AutoLog(value = "逾期贷款监控-添加")
	@ApiOperation(value="逾期贷款监控-添加", notes="逾期贷款监控-添加")
	@PostMapping(value = "/add")
	public Result<VdkjkptYqdkjk> add(@RequestBody VdkjkptYqdkjk vdkjkptYqdkjk) {
		Result<VdkjkptYqdkjk> result = new Result<VdkjkptYqdkjk>();
		try {
			vdkjkptYqdkjkService.save(vdkjkptYqdkjk);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param vdkjkptYqdkjk
	 * @return
	 */
	@AutoLog(value = "逾期贷款监控-编辑")
	@ApiOperation(value="逾期贷款监控-编辑", notes="逾期贷款监控-编辑")
	@PutMapping(value = "/edit")
	public Result<VdkjkptYqdkjk> edit(@RequestBody VdkjkptYqdkjk vdkjkptYqdkjk) {
		Result<VdkjkptYqdkjk> result = new Result<VdkjkptYqdkjk>();
		VdkjkptYqdkjk vdkjkptYqdkjkEntity = vdkjkptYqdkjkService.getById(vdkjkptYqdkjk.getDkzh());
		if(vdkjkptYqdkjkEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = vdkjkptYqdkjkService.updateById(vdkjkptYqdkjk);
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
	@AutoLog(value = "逾期贷款监控-通过id删除")
	@ApiOperation(value="逾期贷款监控-通过id删除", notes="逾期贷款监控-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="dkzh",required=true) String id) {
		try {
			vdkjkptYqdkjkService.removeById(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}

	/**
	  *  批量删除
	 * @param dkzhs
	 * @return
	 */
	@AutoLog(value = "逾期贷款监控-批量删除")
	@ApiOperation(value="逾期贷款监控-批量删除", notes="逾期贷款监控-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<VdkjkptYqdkjk> deleteBatch(@RequestParam(name="dkzhs",required=true) String dkzhs) {
		Result<VdkjkptYqdkjk> result = new Result<VdkjkptYqdkjk>();
		if(dkzhs==null || "".equals(dkzhs.trim())) {
			result.error500("参数不识别！");
		}else {
			this.vdkjkptYqdkjkService.removeByIds(Arrays.asList(dkzhs.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "逾期贷款监控-通过id查询")
	@ApiOperation(value="逾期贷款监控-通过id查询", notes="逾期贷款监控-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<VdkjkptYqdkjk> queryById(@RequestParam(name="dkzh",required=true) String id) {
		Result<VdkjkptYqdkjk> result = new Result<VdkjkptYqdkjk>();
		VdkjkptYqdkjk vdkjkptYqdkjk = vdkjkptYqdkjkService.getById(id);
		if(vdkjkptYqdkjk==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(vdkjkptYqdkjk);
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
      QueryWrapper<VdkjkptYqdkjk> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              VdkjkptYqdkjk vdkjkptYqdkjk = JSON.parseObject(deString, VdkjkptYqdkjk.class);
              queryWrapper = QueryGenerator.initQueryWrapper(vdkjkptYqdkjk, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<VdkjkptYqdkjk> pageList = vdkjkptYqdkjkService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "逾期贷款监控列表");
      mv.addObject(NormalExcelConstants.CLASS, VdkjkptYqdkjk.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("逾期贷款监控列表数据", "导出人:Jeecg", "导出信息"));
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
              List<VdkjkptYqdkjk> listVdkjkptYqdkjks = ExcelImportUtil.importExcel(file.getInputStream(), VdkjkptYqdkjk.class, params);
              vdkjkptYqdkjkService.saveBatch(listVdkjkptYqdkjks);
              return Result.ok("文件导入成功！数据行数:" + listVdkjkptYqdkjks.size());
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

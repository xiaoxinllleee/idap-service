package org.cmms.modules.dkjkpt.dksjjk.dkdqyj.controller;

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
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.cmms.modules.dkjkpt.dksjjk.dkdqyj.entity.Dkdqyj;
import org.cmms.modules.dkjkpt.dksjjk.dkdqyj.service.IDkdqyjService;
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
 * @Description: 贷款到期预警
 * @Author: cmms
 * @Date:   2019-09-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款到期预警")
@RestController
@RequestMapping("/dkdqyj/dkdqyj")
public class DkdqyjController {
	@Autowired
	private IDkdqyjService dkdqyjService;
	 @Autowired
	 private ITjfxZhbyService tjfxZhbyService;

	/**
	  * 分页列表查询
	 * @param dkdqyj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款到期预警-分页列表查询")
	@ApiOperation(value="贷款到期预警-分页列表查询", notes="贷款到期预警-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Dkdqyj>> queryPageList(Dkdqyj dkdqyj,
                                               @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                               @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                               HttpServletRequest req) {
		Result<IPage<Dkdqyj>> result = new Result<IPage<Dkdqyj>>();
		QueryWrapper<Dkdqyj> queryWrapper = QueryGenerator.initQueryWrapper(dkdqyj, req.getParameterMap());
		queryWrapper.orderByAsc("dqrq");
		Page<Dkdqyj> page = new Page<Dkdqyj>(pageNo, pageSize);
		IPage<Dkdqyj> pageList = dkdqyjService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	 /**
	  * 分页列表查询
	  * @param dkdqyj
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "贷款到期预警-分页列表查询")
	 @ApiOperation(value="贷款到期预警-分页列表查询", notes="贷款到期预警-分页列表查询")
	 @GetMapping(value = "/ipadlist")
	 public Result<?> queryIpadList(Dkdqyj dkdqyj,
												@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
												HttpServletRequest req) {
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 dkdqyj.setJgdm( sysUser.getOrgCode() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization", "ywjgdm", "zzbz", sysUser.getOrgCode()));
		 QueryWrapper<Dkdqyj> queryWrapper = QueryGenerator.initQueryWrapper(dkdqyj, req.getParameterMap());
		 Page<Dkdqyj> page = new Page<Dkdqyj>(pageNo, pageSize);
		 IPage<Dkdqyj> pageList = dkdqyjService.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }

	/**
	  *   添加
	 * @param dkdqyj
	 * @return
	 */
	@AutoLog(value = "贷款到期预警-添加")
	@ApiOperation(value="贷款到期预警-添加", notes="贷款到期预警-添加")
	@PostMapping(value = "/add")
	public Result<Dkdqyj> add(@RequestBody Dkdqyj dkdqyj) {
		Result<Dkdqyj> result = new Result<Dkdqyj>();
		try {
			dkdqyjService.save(dkdqyj);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param dkdqyj
	 * @return
	 */
	@AutoLog(value = "贷款到期预警-编辑")
	@ApiOperation(value="贷款到期预警-编辑", notes="贷款到期预警-编辑")
	@PutMapping(value = "/edit")
	public Result<Dkdqyj> edit(@RequestBody Dkdqyj dkdqyj) {
		Result<Dkdqyj> result = new Result<Dkdqyj>();
		Dkdqyj dkdqyjEntity = dkdqyjService.getById(dkdqyj.getJgdm());
		if(dkdqyjEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dkdqyjService.updateById(dkdqyj);
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
	@AutoLog(value = "贷款到期预警-通过id删除")
	@ApiOperation(value="贷款到期预警-通过id删除", notes="贷款到期预警-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			dkdqyjService.removeById(id);
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
	@AutoLog(value = "贷款到期预警-批量删除")
	@ApiOperation(value="贷款到期预警-批量删除", notes="贷款到期预警-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<Dkdqyj> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Dkdqyj> result = new Result<Dkdqyj>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.dkdqyjService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款到期预警-通过id查询")
	@ApiOperation(value="贷款到期预警-通过id查询", notes="贷款到期预警-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Dkdqyj> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Dkdqyj> result = new Result<Dkdqyj>();
		Dkdqyj dkdqyj = dkdqyjService.getById(id);
		if(dkdqyj==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dkdqyj);
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
      QueryWrapper<Dkdqyj> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Dkdqyj dkdqyj = JSON.parseObject(deString, Dkdqyj.class);
              queryWrapper = QueryGenerator.initQueryWrapper(dkdqyj, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Dkdqyj> pageList = dkdqyjService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "贷款到期预警列表");
      mv.addObject(NormalExcelConstants.CLASS, Dkdqyj.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("贷款到期预警列表数据", "导出人:Jeecg", "导出信息"));
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
              List<Dkdqyj> listDkdqyjs = ExcelImportUtil.importExcel(file.getInputStream(), Dkdqyj.class, params);
              dkdqyjService.saveBatch(listDkdqyjs);
              return Result.ok("文件导入成功！数据行数:" + listDkdqyjs.size());
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

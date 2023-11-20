package org.cmms.modules.dkjkpt.dksjjk.dkhsdjb.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.cmms.modules.dkjkpt.dksjjk.dkhsdjb.entity.Dkhsdjbmx;
import org.cmms.modules.dkjkpt.dksjjk.dkhsdjb.service.IDkhsdjbmxService;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsjhz_qh.entity.TjfxJrphsjQh;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsjhz_qh.entity.TjfxJrphsjQhimport;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
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
 * @Description: 贷款回收登记簿明细
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款回收登记簿明细")
@RestController
@RequestMapping("/dkhsdjbmx/dkhsdjbmx")
public class DkhsdjbmxController {
	@Autowired
	private IDkhsdjbmxService dkhsdjbmxService;
	 @Autowired
	 private Environment environment;
	/**
	  * 分页列表查询
	 * @param dkhsdjbmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款回收登记簿明细-分页列表查询")
	@ApiOperation(value="贷款回收登记簿明细-分页列表查询", notes="贷款回收登记簿明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Dkhsdjbmx>> queryPageList(Dkhsdjbmx dkhsdjbmx,
                                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                  HttpServletRequest req) {
		Result<IPage<Dkhsdjbmx>> result = new Result<IPage<Dkhsdjbmx>>();
		QueryWrapper<Dkhsdjbmx> queryWrapper = QueryGenerator.initQueryWrapper(dkhsdjbmx, req.getParameterMap());
		Page<Dkhsdjbmx> page = new Page<Dkhsdjbmx>(pageNo, pageSize);
		IPage<Dkhsdjbmx> pageList = dkhsdjbmxService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param dkhsdjbmx
	 * @return
	 */
	@AutoLog(value = "贷款回收登记簿明细-添加")
	@ApiOperation(value="贷款回收登记簿明细-添加", notes="贷款回收登记簿明细-添加")
	@PostMapping(value = "/add")
	public Result<Dkhsdjbmx> add(@RequestBody Dkhsdjbmx dkhsdjbmx) {
		Result<Dkhsdjbmx> result = new Result<Dkhsdjbmx>();
		try {
			dkhsdjbmxService.save(dkhsdjbmx);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param dkhsdjbmx
	 * @return
	 */
	@AutoLog(value = "贷款回收登记簿明细-编辑")
	@ApiOperation(value="贷款回收登记簿明细-编辑", notes="贷款回收登记簿明细-编辑")
	@PutMapping(value = "/edit")
	public Result<Dkhsdjbmx> edit(@RequestBody Dkhsdjbmx dkhsdjbmx) {
		Result<Dkhsdjbmx> result = new Result<Dkhsdjbmx>();
		Dkhsdjbmx dkhsdjbmxEntity = dkhsdjbmxService.getById(dkhsdjbmx.getJgdm());
		if(dkhsdjbmxEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dkhsdjbmxService.updateById(dkhsdjbmx);
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
	@AutoLog(value = "贷款回收登记簿明细-通过id删除")
	@ApiOperation(value="贷款回收登记簿明细-通过id删除", notes="贷款回收登记簿明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			dkhsdjbmxService.removeById(id);
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
	@AutoLog(value = "贷款回收登记簿明细-批量删除")
	@ApiOperation(value="贷款回收登记簿明细-批量删除", notes="贷款回收登记簿明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<Dkhsdjbmx> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Dkhsdjbmx> result = new Result<Dkhsdjbmx>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.dkhsdjbmxService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款回收登记簿明细-通过id查询")
	@ApiOperation(value="贷款回收登记簿明细-通过id查询", notes="贷款回收登记簿明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Dkhsdjbmx> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Dkhsdjbmx> result = new Result<Dkhsdjbmx>();
		Dkhsdjbmx dkhsdjbmx = dkhsdjbmxService.getById(id);
		if(dkhsdjbmx==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dkhsdjbmx);
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
  public ModelAndView exportXls( HttpServletRequest request, HttpServletResponse response) {
	  System.out.println("jinlai");
      // Step.1 组装查询条件
      QueryWrapper<Dkhsdjbmx> queryWrapper = null;

      	Dkhsdjbmx dkhsdjbmx = new Dkhsdjbmx();
      	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//          String begin = request.getParameter("begin");
//		  String end = request.getParameter("end");
//		  dkhsdjbmx.setShrq_begin(begin);
//		  dkhsdjbmx.setShrq_end(end);
//		  System.out.println(begin+"----"+end);
//		  if (oConvertUtils.isNotEmpty(begin)) {
//              String deString = URLDecoder.decode(begin, "UTF-8");
//              Dkhsdjbmx dkhsdjbmx = JSON.parseObject(deString, Dkhsdjbmx.class);
              queryWrapper = QueryGenerator.initQueryWrapper(dkhsdjbmx, request.getParameterMap());
 //         }


      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Dkhsdjbmx> pageList = dkhsdjbmxService.list(queryWrapper);

	  // Step.1 组装查询条件
//	  QueryWrapper<Dkhsdjbmx> queryWrapper = QueryGenerator.initQueryWrapper(dkhsdjbmx, request.getParameterMap());
//	  //AutoPoi 导出Excel
//	  ModelAndView mv = new ModelAndView(new TemplateExcelView());
//	  Map<String,Object> map = new HashMap<String, Object>();
//	  List<Dkhsdjbmx> list = new ArrayList<>();
//	  List<Dkhsdjbmx> pageList = dkhsdjbmxService.list(queryWrapper);
//	  for (Dkhsdjbmx dkhsdjbmx1 : pageList) {
//		  Dkhsdjbmx dkhsdjbmx2 = new Dkhsdjbmx();
//		  BeanUtils.copyProperties(dkhsdjbmx1,dkhsdjbmx2);
//		  //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		  dkhsdjbmx2.setShrq_begin(dkhsdjbmx1.getShrq_begin());
//		  dkhsdjbmx2.setShrq_end(dkhsdjbmx1.getShrq_end());
//		  list.add(dkhsdjbmx2);
//	  }
//	  map.put("list",list);
//	  String port = environment.getProperty("common.path.export");
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "贷款回收登记簿明细列表");
      mv.addObject(NormalExcelConstants.CLASS, Dkhsdjbmx.class);
	  LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("贷款回收登记簿明细列表数据", "导出人:"+loginUser.getRealname(), "导出信息"));
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
              List<Dkhsdjbmx> listDkhsdjbmxs = ExcelImportUtil.importExcel(file.getInputStream(), Dkhsdjbmx.class, params);
              dkhsdjbmxService.saveBatch(listDkhsdjbmxs);
              return Result.ok("文件导入成功！数据行数:" + listDkhsdjbmxs.size());
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
	  * 提取花名册客户信息
	  * @return
	  */
	 @AutoLog(value = "提取")
	 @ApiOperation(value="提取", notes="提取")
	 @GetMapping(value = "/init")
	 public Result<Dkhsdjbmx> init(HttpServletRequest request, HttpServletResponse response) {
		 Result<Dkhsdjbmx> result = new Result<Dkhsdjbmx>();
		 try {
			 dkhsdjbmxService.init();
			 result.setSuccess(true);
			 result.setMessage("提取成功");
			 return result;
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.setSuccess(false);
			 result.setMessage("提取失败");
			 return result;
		 }

	 }
}

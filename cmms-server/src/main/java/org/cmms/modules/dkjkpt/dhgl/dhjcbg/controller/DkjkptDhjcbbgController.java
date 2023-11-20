package org.cmms.modules.dkjkpt.dhgl.dhjcbg.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cmms.modules.dkjkpt.dhgl.dhjcbg.entity.DkjkptDhjcbgfjxx;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.entity.VDkjkptDhjcbbg;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.vo.V_dkjkpt_dhjcbbgPage;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.service.IdkjkptDhjcbbgService;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.service.IDkjkptDhjcbgfjxxService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;

 /**
 * @Description: 贷后检查报告
 * @Author: cmms
 * @Date:   2019-09-10
 * @Version: V1.0
 */
@RestController
@RequestMapping("/dkjkpt/dhgl/dhjcbg/v_dkjkpt_dhjcbbg")
@Slf4j
public class DkjkptDhjcbbgController {
	@Autowired
	private IdkjkptDhjcbbgService dhjcbbgService;
	@Autowired
	private IDkjkptDhjcbgfjxxService dhjcbgfjxxService;
	
	/**
	  * 分页列表查询
	 * @param vdkjkptDhjcbbg
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<VDkjkptDhjcbbg>> queryPageList(VDkjkptDhjcbbg vdkjkptDhjcbbg,
													   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
													   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
													   HttpServletRequest req) {
		Result<IPage<VDkjkptDhjcbbg>> result = new Result<IPage<VDkjkptDhjcbbg>>();
		QueryWrapper<VDkjkptDhjcbbg> queryWrapper = QueryGenerator.initQueryWrapper(vdkjkptDhjcbbg, req.getParameterMap());
		Page<VDkjkptDhjcbbg> page = new Page<VDkjkptDhjcbbg>(pageNo, pageSize);
		IPage<VDkjkptDhjcbbg> pageList = dhjcbbgService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param v_dkjkpt_dhjcbbgPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<VDkjkptDhjcbbg> add(@RequestBody V_dkjkpt_dhjcbbgPage v_dkjkpt_dhjcbbgPage) {
		Result<VDkjkptDhjcbbg> result = new Result<VDkjkptDhjcbbg>();
		try {
			VDkjkptDhjcbbg vdkjkptDhjcbbg = new VDkjkptDhjcbbg();
			BeanUtils.copyProperties(v_dkjkpt_dhjcbbgPage, vdkjkptDhjcbbg);

			dhjcbbgService.saveMain(vdkjkptDhjcbbg, v_dkjkpt_dhjcbbgPage.getDkjkpt_dhjcbgfjxxList());
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param v_dkjkpt_dhjcbbgPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<VDkjkptDhjcbbg> edit(@RequestBody V_dkjkpt_dhjcbbgPage v_dkjkpt_dhjcbbgPage) {
		Result<VDkjkptDhjcbbg> result = new Result<VDkjkptDhjcbbg>();
		VDkjkptDhjcbbg vdkjkptDhjcbbg = new VDkjkptDhjcbbg();
		BeanUtils.copyProperties(v_dkjkpt_dhjcbbgPage, vdkjkptDhjcbbg);
		VDkjkptDhjcbbg vdkjkptDhjcbbgEntity = dhjcbbgService.getById(vdkjkptDhjcbbg.getZjhm());
		dhjcbbgService.updateMain(vdkjkptDhjcbbg, v_dkjkpt_dhjcbbgPage.getDkjkpt_dhjcbgfjxxList());
		result.success("修改成功!");
		return result;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			dhjcbbgService.delMain(id);
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
	@DeleteMapping(value = "/deleteBatch")
	public Result<VDkjkptDhjcbbg> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<VDkjkptDhjcbbg> result = new Result<VDkjkptDhjcbbg>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.dhjcbbgService.delBatchMain(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<VDkjkptDhjcbbg> queryById(@RequestParam(name="id",required=true) String id) {
		Result<VDkjkptDhjcbbg> result = new Result<VDkjkptDhjcbbg>();
		VDkjkptDhjcbbg vdkjkptDhjcbbg = dhjcbbgService.getById(id);
		if(vdkjkptDhjcbbg ==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(vdkjkptDhjcbbg);
			result.setSuccess(true);
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryDkjkpt_dhjcbgfjxxByMainId")
	public Result<List<VDkjkptDhjcbbg>> queryDkjkpt_dhjcbgfjxxListByMainId(@RequestParam(name="zjhm",required=true) String id) {
		Result<List<VDkjkptDhjcbbg>> result = new Result<List<VDkjkptDhjcbbg>>();
		List<VDkjkptDhjcbbg> dkjkpt_dhjcbgfjxxList = dhjcbbgService.selectByMainId(id);
		result.setResult(dkjkpt_dhjcbgfjxxList);
		result.setSuccess(true);
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
      QueryWrapper<VDkjkptDhjcbbg> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              VDkjkptDhjcbbg vdkjkptDhjcbbg = JSON.parseObject(deString, VDkjkptDhjcbbg.class);
              queryWrapper = QueryGenerator.initQueryWrapper(vdkjkptDhjcbbg, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<V_dkjkpt_dhjcbbgPage> pageList = new ArrayList<V_dkjkpt_dhjcbbgPage>();
      List<VDkjkptDhjcbbg> vdkjkptDhjcbbgList = dhjcbbgService.list(queryWrapper);
      for (VDkjkptDhjcbbg vdkjkptDhjcbbg : vdkjkptDhjcbbgList) {
          V_dkjkpt_dhjcbbgPage vo = new V_dkjkpt_dhjcbbgPage();
          BeanUtils.copyProperties(vdkjkptDhjcbbg, vo);
          List<DkjkptDhjcbgfjxx> dkjkpt_dhjcbgfjxxList = dhjcbgfjxxService.selectByMainId(vdkjkptDhjcbbg.getZjhm());
          vo.setDkjkpt_dhjcbgfjxxList(dkjkpt_dhjcbgfjxxList);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "贷后检查报告列表");
      mv.addObject(NormalExcelConstants.CLASS, V_dkjkpt_dhjcbbgPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("贷后检查报告列表数据", "导出人:Jeecg", "导出信息"));
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
              List<V_dkjkpt_dhjcbbgPage> list = ExcelImportUtil.importExcel(file.getInputStream(), V_dkjkpt_dhjcbbgPage.class, params);
              for (V_dkjkpt_dhjcbbgPage page : list) {
                  VDkjkptDhjcbbg po = new VDkjkptDhjcbbg();
                  BeanUtils.copyProperties(page, po);
                  dhjcbbgService.saveMain(po, page.getDkjkpt_dhjcbgfjxxList());
              }
              return Result.ok("文件导入成功！数据行数:" + list.size());
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

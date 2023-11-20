package org.cmms.modules.tjfx.khnlfctj.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.khnlfctj.entity.Khnlfctj_qh;
import org.cmms.modules.tjfx.khnlfctj.entity.Tjfxkhnlfctj;
import org.cmms.modules.tjfx.khnlfctj.service.IKhnlfctj_qhService;
import org.cmms.modules.tjfx.khnlfctj.service.ITjfxkhnlfctjService;
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
 * @Description: 客户年龄分层统计
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户年龄分层统计")
@RestController
@RequestMapping("/tjfx.khnlfctj/tjfxkhnlfctj")
public class TjfxkhnlfctjController extends JeecgController<Tjfxkhnlfctj, ITjfxkhnlfctjService> {
	@Autowired
	private ITjfxkhnlfctjService tjfxkhnlfctjService;
	 @Autowired
	 private ITjfxkhnlfctjService ITjfxkhnlfctjService;
	 @Autowired
	 private IKhnlfctj_qhService IKhnlfctjqhService;

	/**
	  * 分页列表查询
	 * @param tjfxkhnlfctj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户年龄分层统计-分页列表查询")
	@ApiOperation(value="客户年龄分层统计-分页列表查询", notes="客户年龄分层统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Tjfxkhnlfctj>> queryPageList(Tjfxkhnlfctj tjfxkhnlfctj,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Tjfxkhnlfctj>> result = new Result<IPage<Tjfxkhnlfctj>>();
		QueryWrapper<Tjfxkhnlfctj> queryWrapper = QueryGenerator.initQueryWrapper(tjfxkhnlfctj, req.getParameterMap());
		Page<Tjfxkhnlfctj> page = new Page<Tjfxkhnlfctj>(pageNo, pageSize);
		IPage<Tjfxkhnlfctj> pageList = tjfxkhnlfctjService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param tjfxkhnlfctj
	 * @return
	 */
	@AutoLog(value = "客户年龄分层统计-添加")
	@ApiOperation(value="客户年龄分层统计-添加", notes="客户年龄分层统计-添加")
	@PostMapping(value = "/add")
	public Result<Tjfxkhnlfctj> add(@RequestBody Tjfxkhnlfctj tjfxkhnlfctj) {
		Result<Tjfxkhnlfctj> result = new Result<Tjfxkhnlfctj>();
		try {
			tjfxkhnlfctjService.save(tjfxkhnlfctj);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param tjfxkhnlfctj
	 * @return
	 */
	@AutoLog(value = "客户年龄分层统计-编辑")
	@ApiOperation(value="客户年龄分层统计-编辑", notes="客户年龄分层统计-编辑")
	@PutMapping(value = "/edit")
	public Result<Tjfxkhnlfctj> edit(@RequestBody Tjfxkhnlfctj tjfxkhnlfctj) {
		Result<Tjfxkhnlfctj> result = new Result<Tjfxkhnlfctj>();
		Tjfxkhnlfctj tjfxkhnlfctjEntity = tjfxkhnlfctjService.getById(tjfxkhnlfctj.getZhmc());
		if(tjfxkhnlfctjEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = tjfxkhnlfctjService.updateById(tjfxkhnlfctj);
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
	@AutoLog(value = "客户年龄分层统计-通过id删除")
	@ApiOperation(value="客户年龄分层统计-通过id删除", notes="客户年龄分层统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			tjfxkhnlfctjService.removeById(id);
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
	@AutoLog(value = "客户年龄分层统计-批量删除")
	@ApiOperation(value="客户年龄分层统计-批量删除", notes="客户年龄分层统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<Tjfxkhnlfctj> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Tjfxkhnlfctj> result = new Result<Tjfxkhnlfctj>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.tjfxkhnlfctjService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户年龄分层统计-通过id查询")
	@ApiOperation(value="客户年龄分层统计-通过id查询", notes="客户年龄分层统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Tjfxkhnlfctj> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Tjfxkhnlfctj> result = new Result<Tjfxkhnlfctj>();
		Tjfxkhnlfctj tjfxkhnlfctj = tjfxkhnlfctjService.getById(id);
		if(tjfxkhnlfctj==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(tjfxkhnlfctj);
			result.setSuccess(true);
		}
		return result;
	}

  /**
   * 导出excel
   * @param request
   * @param khnlfc
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Tjfxkhnlfctj khnlfc) {
      return super.exportXls(request, khnlfc, Tjfxkhnlfctj.class, "客户年龄分层统计");
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
              List<Tjfxkhnlfctj> listTjfxkhnlfctjs = ExcelImportUtil.importExcel(file.getInputStream(), Tjfxkhnlfctj.class, params);
              tjfxkhnlfctjService.saveBatch(listTjfxkhnlfctjs);
              return Result.ok("文件导入成功！数据行数:" + listTjfxkhnlfctjs.size());
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
	  *   通过id删除
	  * @param
	  * @return
	  */
	 @RequestMapping(value = "/extract" , method = RequestMethod.PUT)
	 public Result<?> extract() {
		 Result<List<Khnlfctj_qh>> result = new Result<>();
		 try {
			ITjfxkhnlfctjService.extract("");
			 Khnlfctj_qh nhfcxx1=new Khnlfctj_qh();
			 Map<String, String[]> map=new HashMap<>();
			 QueryWrapper<Khnlfctj_qh> queryWrapper = QueryGenerator.initQueryWrapper(nhfcxx1,map);
			 List<Khnlfctj_qh> nhfcxx = IKhnlfctjqhService.list(queryWrapper);
			 if (nhfcxx.size() > 0) {
				 result.setResult(nhfcxx);
			 }
			 result.setSuccess(true);
		 } catch (Exception e) {
			 log.error("提取失败",e.getMessage());
			 result.setSuccess(false);
		 }
		 return  result;
	 }



}

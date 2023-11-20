package org.cmms.modules.tjfx.khzlwzdpfcssz.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.khzlwzdpfcssz.entity.TjfxKhzlwzdcssz;
import org.cmms.modules.tjfx.khzlwzdpfcssz.service.ITjfxKhzlwzdcsszService;
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
 * @Description: 客户建档完整度评分参数设置
 * @Author: cmms
 * @Date:   2019-12-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户建档完整度评分参数设置")
@RestController
@RequestMapping("/tjfx.khzlwzdpfcssz/tjfxKhzlwzdcssz")
public class TjfxKhzlwzdcsszController {
	@Autowired
	private ITjfxKhzlwzdcsszService tjfxKhzlwzdcsszService;

	/**
	  * 分页列表查询
	 * @param tjfxKhzlwzdcssz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户建档完整度评分参数设置-分页列表查询")
	@ApiOperation(value="客户建档完整度评分参数设置-分页列表查询", notes="客户建档完整度评分参数设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<TjfxKhzlwzdcssz>> queryPageList(TjfxKhzlwzdcssz tjfxKhzlwzdcssz,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<TjfxKhzlwzdcssz>> result = new Result<IPage<TjfxKhzlwzdcssz>>();
		QueryWrapper<TjfxKhzlwzdcssz> queryWrapper = QueryGenerator.initQueryWrapper(tjfxKhzlwzdcssz, req.getParameterMap());
		Page<TjfxKhzlwzdcssz> page = new Page<TjfxKhzlwzdcssz>(pageNo, pageSize);
		IPage<TjfxKhzlwzdcssz> pageList = tjfxKhzlwzdcsszService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param
	 * @return
	 */
	@AutoLog(value = "客户建档完整度评分参数设置-添加")
	@ApiOperation(value="客户建档完整度评分参数设置-添加", notes="客户建档完整度评分参数设置-添加")
	@RequestMapping(value = "/add", method = RequestMethod.PUT)
	public Result<TjfxKhzlwzdcssz> add(@RequestBody  JSONObject  json) {
		Result<TjfxKhzlwzdcssz> result = new Result<TjfxKhzlwzdcssz>();
		try {
			Object asd = json.get("asd");
			List<String> zdList = (ArrayList) json.get("zdList");
			ArrayList<Integer> data = (ArrayList<Integer>) json.get("ArrayIndex");
			ArrayList<String> fzList = (ArrayList<String>) json.get("fzList");
			for (int i = 0; i < zdList.size(); i++) {
                String[] split = zdList.get(i).split(data.get(i).toString());
				TjfxKhzlwzdcssz kh=new TjfxKhzlwzdcssz();
				System.out.println(zdList.get(i));
				System.out.println("---");
				System.out.println(data.get(i));
				System.out.println("---");
				System.out.println(fzList.get(i));
				kh.setCsbm(split[1]);
				kh.setCsm(split[0]);
				kh.setFz(Long.parseLong(fzList.get(i)));
				kh.setKhlx(json.getString("khlx"));
				tjfxKhzlwzdcsszService.save(kh);
            }
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param tjfxKhzlwzdcssz
	 * @return
	 */
	@AutoLog(value = "客户建档完整度评分参数设置-编辑")
	@ApiOperation(value="客户建档完整度评分参数设置-编辑", notes="客户建档完整度评分参数设置-编辑")
	@PutMapping(value = "/edit")
	public Result<TjfxKhzlwzdcssz> edit(@RequestBody TjfxKhzlwzdcssz tjfxKhzlwzdcssz) {
		Result<TjfxKhzlwzdcssz> result = new Result<TjfxKhzlwzdcssz>();
		UpdateWrapper updateWrapper = new UpdateWrapper();
		updateWrapper.eq("csbm",tjfxKhzlwzdcssz.getCsbm());
		tjfxKhzlwzdcsszService.update(tjfxKhzlwzdcssz,updateWrapper);
		result.success("修改成功!");
		return result;
	}

	 /**
	  *   通过id删除
	  * @return
	  */
	 @PutMapping(value = "/delete")
	public Result<?> delete(@RequestBody JSONObject jsonObject) {
		try {
			QueryWrapper queryWrapper = new QueryWrapper();
			queryWrapper.eq("csbm",jsonObject.getString("csbm"));
			tjfxKhzlwzdcsszService.remove(queryWrapper);
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
	@AutoLog(value = "客户建档完整度评分参数设置-批量删除")
	@ApiOperation(value="客户建档完整度评分参数设置-批量删除", notes="客户建档完整度评分参数设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<TjfxKhzlwzdcssz> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<TjfxKhzlwzdcssz> result = new Result<TjfxKhzlwzdcssz>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.tjfxKhzlwzdcsszService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户建档完整度评分参数设置-通过id查询")
	@ApiOperation(value="客户建档完整度评分参数设置-通过id查询", notes="客户建档完整度评分参数设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TjfxKhzlwzdcssz> queryById(@RequestParam(name="id",required=true) String id) {
		Result<TjfxKhzlwzdcssz> result = new Result<TjfxKhzlwzdcssz>();
		TjfxKhzlwzdcssz tjfxKhzlwzdcssz = tjfxKhzlwzdcsszService.getById(id);
		if(tjfxKhzlwzdcssz==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(tjfxKhzlwzdcssz);
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
      QueryWrapper<TjfxKhzlwzdcssz> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              TjfxKhzlwzdcssz tjfxKhzlwzdcssz = JSON.parseObject(deString, TjfxKhzlwzdcssz.class);
              queryWrapper = QueryGenerator.initQueryWrapper(tjfxKhzlwzdcssz, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<TjfxKhzlwzdcssz> pageList = tjfxKhzlwzdcsszService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "客户建档完整度评分参数设置列表");
      mv.addObject(NormalExcelConstants.CLASS, TjfxKhzlwzdcssz.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户建档完整度评分参数设置列表数据", "导出人:Jeecg", "导出信息"));
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
              List<TjfxKhzlwzdcssz> listTjfxKhzlwzdcsszs = ExcelImportUtil.importExcel(file.getInputStream(), TjfxKhzlwzdcssz.class, params);
              tjfxKhzlwzdcsszService.saveBatch(listTjfxKhzlwzdcsszs);
              return Result.ok("文件导入成功！数据行数:" + listTjfxKhzlwzdcsszs.size());
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
	  * 明细查看
	  * @param csbm
	  * @return
	  */
	 @RequestMapping(value = "/queryzlwzd", method = RequestMethod.PUT)
	 public Result<List<TjfxKhzlwzdcssz>> PymxData(@RequestBody JSONObject csbm) {
		 Result<List<TjfxKhzlwzdcssz>> result = new Result<List<TjfxKhzlwzdcssz>>();
		 TjfxKhzlwzdcssz tjfxKhzlwzdcssz = new TjfxKhzlwzdcssz();
		 tjfxKhzlwzdcssz.setCsbm(csbm.getString("csbm"));
		 Map<String, String[]> map = new HashMap<>();
		 QueryWrapper<TjfxKhzlwzdcssz> queryWrapper = QueryGenerator.initQueryWrapper(tjfxKhzlwzdcssz, map);
		 List<TjfxKhzlwzdcssz> gzap_jhxf_khjl = tjfxKhzlwzdcsszService.list(queryWrapper);
		 if (gzap_jhxf_khjl.size()==0) {
				 result.error500("未找到对应实体");
				 return null;
			 } else {
				 if (gzap_jhxf_khjl.get(0) == null) {
					 return null;
				 } else {
				 	result.setResult(gzap_jhxf_khjl);
					 return result;
				 }
			 }

	 }

	 /**
	  * 查询字段名
	  * @return
	  */
	 @AutoLog(value = "查询农户字段名")
	 @ApiOperation(value="查询字段名", notes="查询字段名")
	 @GetMapping(value = "/querytable")
	 public List queryById(HttpServletRequest request, HttpServletResponse response) {
		 Result<TjfxKhzlwzdcssz> result = new Result<TjfxKhzlwzdcssz>();
		 List<Map<String,String>>  TjfxKhzlwzdcssz =tjfxKhzlwzdcsszService.selectTable("CAMS_ZCSX_NHCJXX");
		 Map<String,Object> map = new HashMap<>();
		 map.put("list",TjfxKhzlwzdcssz);
		 result.setSuccess(true);
		 return TjfxKhzlwzdcssz;
	 }

	 /**
	  * 查询字段名
	  * @return
	  */
	 @AutoLog(value = "查询商户基本信息表字段名")
	 @ApiOperation(value="查询字段名", notes="查询字段名")
	 @GetMapping(value = "/queryshtable")
	 public List queryShTable(HttpServletRequest request, HttpServletResponse response) {
		 Result<TjfxKhzlwzdcssz> result = new Result<TjfxKhzlwzdcssz>();
		 List<Map<String,String>>  TjfxKhzlwzdcssz =tjfxKhzlwzdcsszService.selectTable("KHGL_SHJBXX");
		 Map<String,Object> map = new HashMap<>();
		 map.put("list",TjfxKhzlwzdcssz);
		 result.setSuccess(true);
		 return TjfxKhzlwzdcssz;
	 }

	 /**
	  * 查询字段名
	  * @return
	  */
	 @AutoLog(value = "查询城区居民表字段名")
	 @ApiOperation(value="查询字段名", notes="查询字段名")
	 @GetMapping(value = "/querycqjmtable")
	 public List queryCqjmTable(HttpServletRequest request, HttpServletResponse response) {
		 Result<TjfxKhzlwzdcssz> result = new Result<TjfxKhzlwzdcssz>();
		 List<Map<String,String>>  TjfxKhzlwzdcssz =tjfxKhzlwzdcsszService.selectTable("KHGL_CQJMJBXX");
		 Map<String,Object> map = new HashMap<>();
		 map.put("list",TjfxKhzlwzdcssz);
		 result.setSuccess(true);
		 return TjfxKhzlwzdcssz;
	 }


}

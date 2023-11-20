package org.cmms.modules.khgl.clkhxxgl.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.cmms.common.api.vo.ResultConstant;
import org.cmms.modules.khgl.clkhxxgl.entity.*;
import org.cmms.modules.khgl.clkhxxgl.service.*;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.khgl.cqjm.service.ICqjmHrStaffService;
//import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;;
import org.cmms.modules.system.service.ISysDictService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.clkhxxgl.vo.ClgrkhjbxxPage;
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
 * @Description: 存量个人客户基本信息
 * @Author: cmms
 * @Date:   2019-09-20
 * @Version: V1.0
 */
@RestController
@RequestMapping("/clgrkh/clgrkhjbxx")
@Slf4j
public class ClgrkhjbxxController {
	@Autowired
	private IClgrkhjbxxService clgrkhjbxxService;
	@Autowired
	private IClgrkhjtcyService clgrkhjtcyService;
	@Autowired
	private IClkhzlxxService clgrkhzlxxService;
	@Autowired
	private IClkhywwlxxService clgrkhywwlxxService;
	@Autowired
	private IClgrkhHfxxService iClgrkhHfxxService;
	@Autowired
    private ISysDictService iSysDictService;
	@Autowired
    private ICqjmHrStaffService iClgrkhHrStaffService;

	@Autowired
	IClgrkhHfxxService clgrkhHfxxService;
	/**
	  * 分页列表查询
	 * @param clgrkhjbxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<Clgrkhjbxx>> queryPageList(Clgrkhjbxx clgrkhjbxx,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Clgrkhjbxx>> result = new Result<IPage<Clgrkhjbxx>>();
		QueryWrapper<Clgrkhjbxx> queryWrapper = QueryGenerator.initQueryWrapper(clgrkhjbxx, req.getParameterMap());
		Page<Clgrkhjbxx> page = new Page<Clgrkhjbxx>(pageNo, pageSize);
		IPage<Clgrkhjbxx> pageList = clgrkhjbxxService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param clgrkhjbxxPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<Clgrkhjbxx> add(@RequestBody ClgrkhjbxxPage clgrkhjbxxPage) {
		Result<Clgrkhjbxx> result = new Result<Clgrkhjbxx>();
		try {
			Clgrkhjbxx clgrkhjbxx = new Clgrkhjbxx();
			BeanUtils.copyProperties(clgrkhjbxxPage, clgrkhjbxx);

			clgrkhjbxxService.saveMain(clgrkhjbxx, clgrkhjbxxPage.getClgrkhjtcyList(),clgrkhjbxxPage.getClgrkhzlxxList(),clgrkhjbxxPage.getClgrkhywwlxx());
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param clgrkhjbxxPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<Clgrkhjbxx> edit(@RequestBody ClgrkhjbxxPage clgrkhjbxxPage) {
		Result<Clgrkhjbxx> result = new Result<Clgrkhjbxx>();
		Clgrkhjbxx clgrkhjbxx = new Clgrkhjbxx();
		BeanUtils.copyProperties(clgrkhjbxxPage, clgrkhjbxx);
		Clgrkhjbxx clgrkhjbxxEntity = clgrkhjbxxService.getById(clgrkhjbxx.getId());
		if(clgrkhjbxxEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = clgrkhjbxxService.updateById(clgrkhjbxx);
			clgrkhjbxxService.updateMain(clgrkhjbxx, clgrkhjbxxPage.getClgrkhjtcyList(),clgrkhjbxxPage.getClgrkhzlxxList(),clgrkhjbxxPage.getClgrkhywwlxx());
			result.success("修改成功!");
		}

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
			clgrkhjbxxService.delMain(id);
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
	public Result<Clgrkhjbxx> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Clgrkhjbxx> result = new Result<Clgrkhjbxx>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.clgrkhjbxxService.delBatchMain(Arrays.asList(ids.split(",")));
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
	public Result<Clgrkhjbxx> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Clgrkhjbxx> result = new Result<Clgrkhjbxx>();
		Clgrkhjbxx clgrkhjbxx = clgrkhjbxxService.getById(id);
		if(clgrkhjbxx==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(clgrkhjbxx);
			result.setSuccess(true);
		}
		return result;
	}

	/**
	  * 通过id查询客户家庭成员信息
	 * @param id
	 * @return
	 */
	/*@GetMapping(value = "/queryClgrkhjtcyByMainId")
	public Result<List<Clgrkhjtcy>> queryClgrkhjtcyListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<Clgrkhjtcy>> result = new Result<List<Clgrkhjtcy>>();
		List<Clgrkhjtcy> clgrkhjtcyList = clgrkhjtcyService.selectByMainId(id);
		result.setResult(clgrkhjtcyList);
		result.setSuccess(true);
		return result;
	}*/
     /**
      * 通过存量个人客户ID查询家庭成员信息
      * @param object
      * @return 家庭成员信息
      */
     @ApiOperation(value = "通过存量个人客户ID查询家庭成员信息", notes = "通过存量个人客户ID查询家庭成员信息")
     @RequestMapping(value = "/queryClgrkhjtcyByMainId", method = RequestMethod.PUT)
     public Result<JSONArray> queryClgrkhjtcyByMainId(@RequestBody JSONObject object) {
         Result<JSONArray> result = new Result<>();
         Clgrkhjtcy clgrkhjtcy = new Clgrkhjtcy();
         clgrkhjtcy.setKhId(object.getString("id"));
         Map<String, String[]> map = new HashMap<>();
         QueryWrapper<Clgrkhjtcy> queryWrapper = QueryGenerator.initQueryWrapper(clgrkhjtcy, map);
         List<Clgrkhjtcy> clgrkhjtcyList = clgrkhjtcyService.list(queryWrapper);
         if (clgrkhjtcyList == null) {
             result.error500("未找到对应实体！");
         } else {
             JSONArray jsonArray = new JSONArray();
             for (Clgrkhjtcy jtcy : clgrkhjtcyList) {
                 JSONObject jsonObject = new JSONObject();
                 jsonObject.put("ykhgx", iSysDictService.queryDictTextByKey("yhzgx", jtcy.getYkhgx()));
                 jsonObject.put("cyxm", jtcy.getCyxm());
                 jsonObject.put("cyzjlx", iSysDictService.queryDictTextByKey("dkjkpt_zjlx", jtcy.getCyzjlx()));
                 jsonObject.put("cyzjhm", jtcy.getCyzjhm());
                 jsonObject.put("cylxdh", jtcy.getCylxdh());
                 jsonObject.put("bz", jtcy.getBz());
                 jsonArray.add(jsonObject);
             }
             result.setResult(jsonArray);
             result.setSuccess(true);
         }
         return result;
     }

	/**
	  * 通过id查询资料信息
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryClgrkhzlxxByMainId")
	public Result<Map<String,List<Clkhzlxx>>> queryClgrkhzlxxListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<Map<String,List<Clkhzlxx>>> result = new Result<Map<String,List<Clkhzlxx>>>();
		List<Clkhzlxx> clgrkhzlxxList = clgrkhzlxxService.selectByMainId(id);
		Map<String,List<Clkhzlxx>> map = new HashMap<String,List<Clkhzlxx>>();
		List<Clkhzlxx> zlxxImage = new ArrayList<Clkhzlxx>();
		List<Clkhzlxx> zlxxFile = new ArrayList<Clkhzlxx>();
		for (Clkhzlxx clgrkhzlxx : clgrkhzlxxList) {
			String zllx = clgrkhzlxx.getZllx();
			if ("1".equalsIgnoreCase(zllx)) {
				zlxxImage.add(clgrkhzlxx);
			} else if ("2".equalsIgnoreCase(zllx)) {
				zlxxFile.add(clgrkhzlxx);
			}
		}
		map.put("zlxxImage", zlxxImage);
		map.put("zlxxFile", zlxxFile);
		result.setResult(map);
		result.setSuccess(true);
		return result;
	}
	/**
	  * 通过id查询与我行业务往来信息
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryClgrkhywwlxxByMainId")
	public Result<Clkhywwlxx> queryClgrkhywwlxxListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<Clkhywwlxx> result = new Result<Clkhywwlxx>();
		Clkhywwlxx clgrkhywwlxx = clgrkhywwlxxService.selectByMainId(id);
		result.setResult(clgrkhywwlxx);
		result.setSuccess(true);
		return result;
	}

     /**
      * 通过个人客户证件号码查询回访信息
      * @param object
      * @return 回访信息
      */
	 /*@GetMapping(value = "/queryClgrkhhfxxByZjhm")
	 public Result<List<ClgrkhHfxx>> queryClgrkhhfxxByZjhm(@RequestParam(name="zjhm",required=true) String zjhm) {
		 Result<List<ClgrkhHfxx>> result = new Result<List<ClgrkhHfxx>>();
		 List<ClgrkhHfxx> clgrkhkhhfList = iClgrkhHfxxService.queryHfxxByZjhm(zjhm);
		 result.setResult(clgrkhkhhfList);
		 result.setSuccess(true);
		 return result;
	 }*/
     @ApiOperation(value = "通过个人客户证件号码查询回访信息", notes = "通过个人客户证件号码查询回访信息")
     @RequestMapping(value = "/queryClgrkhhfxxByZjhm", method = RequestMethod.PUT)
     public Result<JSONArray> queryClgrkhhfxxByZjhm(@RequestBody JSONObject object) {
         Result<JSONArray> result = new Result<>();
         ClgrkhHfxx clgrkhHfxx = new ClgrkhHfxx();
         clgrkhHfxx.setZjhm(object.getString("zjhm"));
         Map<String, String[]> map = new HashMap<>();
         QueryWrapper<ClgrkhHfxx>queryWrapper = QueryGenerator.initQueryWrapper(clgrkhHfxx, map);
         List<ClgrkhHfxx> clgrkhHfxxList = iClgrkhHfxxService.list(queryWrapper);
         if (clgrkhHfxxList == null) {
             result.error500("未找到对应实体！");
         } else {
             JSONArray jsonArray = new JSONArray();
             SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
             for (ClgrkhHfxx hfxx : clgrkhHfxxList) {
                 JSONObject jsonObject = new JSONObject();
                 jsonObject.put("hfrq", sdf.format(hfxx.getHfrq()));
				 HrBasStaff staff = new HrBasStaff();
                 staff.setYggh(hfxx.getYggh());
                 Map<String, String[]> hashMap = new HashMap<>();
                 QueryWrapper<HrBasStaff> staffQueryWrapper = QueryGenerator.initQueryWrapper(staff, hashMap);
                 List<HrBasStaff> hrBasStaffList = iClgrkhHrStaffService.list(staffQueryWrapper);
                 if (hrBasStaffList.size() != 0){
                     jsonObject.put("hfr", hrBasStaffList.get(0).getYgxm());
                 } else {
                     jsonObject.put("hfr", hfxx.getYggh());
                 }
                 jsonObject.put("hffs", iSysDictService.queryDictTextByKey("khhf_hffs", hfxx.getHffs()));
                 jsonObject.put("hfmd", iSysDictService.queryDictTextByKey("khhf_hfmd", hfxx.getHfmd()));
                 jsonObject.put("hfxqsm", hfxx.getHfxqsm());
                 jsonArray.add(jsonObject);
             }
             result.setResult(jsonArray);
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
      QueryWrapper<Clgrkhjbxx> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Clgrkhjbxx clgrkhjbxx = JSON.parseObject(deString, Clgrkhjbxx.class);
              queryWrapper = QueryGenerator.initQueryWrapper(clgrkhjbxx, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<ClgrkhjbxxPage> pageList = new ArrayList<ClgrkhjbxxPage>();
      List<Clgrkhjbxx> clgrkhjbxxList = clgrkhjbxxService.list(queryWrapper);
      for (Clgrkhjbxx clgrkhjbxx : clgrkhjbxxList) {
          ClgrkhjbxxPage vo = new ClgrkhjbxxPage();
          BeanUtils.copyProperties(clgrkhjbxx, vo);
          List<Clgrkhjtcy> clgrkhjtcyList = clgrkhjtcyService.selectByMainId(clgrkhjbxx.getId());
          vo.setClgrkhjtcyList(clgrkhjtcyList);
          List<Clkhzlxx> clgrkhzlxxList = clgrkhzlxxService.selectByMainId(clgrkhjbxx.getId());
          vo.setClgrkhzlxxList(clgrkhzlxxList);
          Clkhywwlxx clgrkhywwlxx = clgrkhywwlxxService.selectByMainId(clgrkhjbxx.getId());
          vo.setClgrkhywwlxx(clgrkhywwlxx);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "存量个人客户基本信息列表");
      mv.addObject(NormalExcelConstants.CLASS, ClgrkhjbxxPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("存量个人客户基本信息列表数据", "导出人:Jeecg", "导出信息"));
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
              List<ClgrkhjbxxPage> list = ExcelImportUtil.importExcel(file.getInputStream(), ClgrkhjbxxPage.class, params);
              for (ClgrkhjbxxPage page : list) {
                  Clgrkhjbxx po = new Clgrkhjbxx();
                  BeanUtils.copyProperties(page, po);
                  clgrkhjbxxService.saveMain(po, page.getClgrkhjtcyList(),page.getClgrkhzlxxList(),page.getClgrkhywwlxx());
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


  @RequestMapping("/clgrkhHfxxQuery")
  public Result<?> clgrkhHfxxQuery(String zjhm){
	  LambdaQueryWrapper<ClgrkhHfxx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
	  lambdaQueryWrapper.eq(ClgrkhHfxx::getZjhm,zjhm);
	  lambdaQueryWrapper.orderByDesc(ClgrkhHfxx::getLrsj);
	  List<ClgrkhHfxx> list = clgrkhHfxxService.list(lambdaQueryWrapper);
	  if (CollUtil.isNotEmpty(list))
		  return Result.ok(list);
	  return Result.error(ResultConstant.WCXDXGSJ);
  }

}

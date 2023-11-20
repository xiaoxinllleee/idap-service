package org.cmms.modules.khgl.clkhxxgl.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.cmms.modules.khgl.clkhxxgl.entity.ClgrkhHfxx;
import org.cmms.modules.khgl.clkhxxgl.service.IClgrkhHfxxService;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.khgl.cqjm.service.ICqjmHrStaffService;
import org.cmms.modules.system.service.ISysDictService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.clkhxxgl.entity.Clkhzlxx;
import org.cmms.modules.khgl.clkhxxgl.entity.Clkhywwlxx;
import org.cmms.modules.khgl.clkhxxgl.entity.Clqykhjbxx;
import org.cmms.modules.khgl.clkhxxgl.vo.ClqykhjbxxPage;
import org.cmms.modules.khgl.clkhxxgl.service.IClqykhjbxxService;
import org.cmms.modules.khgl.clkhxxgl.service.IClkhzlxxService;
import org.cmms.modules.khgl.clkhxxgl.service.IClkhywwlxxService;
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
 * @Description: 存量企业客户基本信息
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
@RestController
@RequestMapping("/clqykh/clqykhjbxx")
@Slf4j
public class ClqykhjbxxController {
	@Autowired
	private IClqykhjbxxService clqykhjbxxService;
	@Autowired
	private IClkhzlxxService clqykhzlxxService;
	@Autowired
	private IClkhywwlxxService clqykhywwlxxService;
	@Autowired
    private IClgrkhHfxxService iClqykhHfxxService;
	@Autowired
    private ISysDictService iSysDictService;
	@Autowired
    private ICqjmHrStaffService iClqykhHrStaffService;

	/**
	  * 分页列表查询
	 * @param clqykhjbxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<Clqykhjbxx>> queryPageList(Clqykhjbxx clqykhjbxx,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Clqykhjbxx>> result = new Result<IPage<Clqykhjbxx>>();
		QueryWrapper<Clqykhjbxx> queryWrapper = QueryGenerator.initQueryWrapper(clqykhjbxx, req.getParameterMap());
		Page<Clqykhjbxx> page = new Page<Clqykhjbxx>(pageNo, pageSize);
		IPage<Clqykhjbxx> pageList = clqykhjbxxService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param clqykhjbxxPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<Clqykhjbxx> add(@RequestBody ClqykhjbxxPage clqykhjbxxPage) {
		Result<Clqykhjbxx> result = new Result<Clqykhjbxx>();
		try {
			Clqykhjbxx clqykhjbxx = new Clqykhjbxx();
			BeanUtils.copyProperties(clqykhjbxxPage, clqykhjbxx);

			clqykhjbxxService.saveMain(clqykhjbxx, clqykhjbxxPage.getClqykhzlxxList(),clqykhjbxxPage.getClqykhywwlxx());
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param clqykhjbxxPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<Clqykhjbxx> edit(@RequestBody ClqykhjbxxPage clqykhjbxxPage) {
		Result<Clqykhjbxx> result = new Result<Clqykhjbxx>();
		Clqykhjbxx clqykhjbxx = new Clqykhjbxx();
		BeanUtils.copyProperties(clqykhjbxxPage, clqykhjbxx);
		Clqykhjbxx clqykhjbxxEntity = clqykhjbxxService.getById(clqykhjbxx.getId());
		if(clqykhjbxxEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = clqykhjbxxService.updateById(clqykhjbxx);
			clqykhjbxxService.updateMain(clqykhjbxx, clqykhjbxxPage.getClqykhzlxxList(),clqykhjbxxPage.getClqykhywwlxx());
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
			clqykhjbxxService.delMain(id);
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
	public Result<Clqykhjbxx> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Clqykhjbxx> result = new Result<Clqykhjbxx>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.clqykhjbxxService.delBatchMain(Arrays.asList(ids.split(",")));
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
	public Result<Clqykhjbxx> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Clqykhjbxx> result = new Result<Clqykhjbxx>();
		Clqykhjbxx clqykhjbxx = clqykhjbxxService.getById(id);
		if(clqykhjbxx==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(clqykhjbxx);
			result.setSuccess(true);
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryClqykhzlxxByMainId")
	public Result<List<Clkhzlxx>> queryClqykhzlxxListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<Clkhzlxx>> result = new Result<List<Clkhzlxx>>();
		List<Clkhzlxx> clkhzlxxList = clqykhzlxxService.selectByMainId(id);
		result.setResult(clkhzlxxList);
		result.setSuccess(true);
		return result;
	}
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryClqykhywwlxxByMainId")
	public Result<Clkhywwlxx> queryClqykhywwlxxListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<Clkhywwlxx> result = new Result<Clkhywwlxx>();
		Clkhywwlxx clkhywwlxx = clqykhywwlxxService.selectByMainId(id);
		result.setResult(clkhywwlxx);
		result.setSuccess(true);
		return result;
	}

     /**
      * 通过对公客户证件号码查询回访信息
      * @param object
      * @return 回访信息
      */
     @ApiOperation(value = "通过对公客户证件号码查询回访信息", notes = "通过对公客户证件号码查询回访信息")
     @RequestMapping(value = "/queryClqykhhfxxByZjhm", method = RequestMethod.PUT)
     public Result<JSONArray> queryClqykhhfxxByZjhm(@RequestBody JSONObject object) {
         Result<JSONArray> result = new Result<>();
         ClgrkhHfxx clqykhHfxx = new ClgrkhHfxx();
         clqykhHfxx.setZjhm(object.getString("zjhm"));
         Map<String, String[]> map = new HashMap<>();
         QueryWrapper<ClgrkhHfxx> queryWrapper= QueryGenerator.initQueryWrapper(clqykhHfxx, map);
         List<ClgrkhHfxx> clqykhHfxxList = iClqykhHfxxService.list(queryWrapper);
         if (clqykhHfxxList == null) {
             result.error500("未找到对应实体！");
         } else {
             JSONArray jsonArray = new JSONArray();
             SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
             for (ClgrkhHfxx hfxx : clqykhHfxxList) {
                 JSONObject jsonObject = new JSONObject();
                 jsonObject.put("hfrq", sdf.format(hfxx.getHfrq()));
				 HrBasStaff staff = new HrBasStaff();
                 staff.setYggh(hfxx.getYggh());
                 Map<String, String[]> hashMap = new HashMap<>();
                 QueryWrapper<HrBasStaff> staffQueryWrapper = QueryGenerator.initQueryWrapper(staff, hashMap);
                 List<HrBasStaff> hrBasStaffList = iClqykhHrStaffService.list(staffQueryWrapper);
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
   *
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<Clqykhjbxx> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Clqykhjbxx clqykhjbxx = JSON.parseObject(deString, Clqykhjbxx.class);
              queryWrapper = QueryGenerator.initQueryWrapper(clqykhjbxx, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<ClqykhjbxxPage> pageList = new ArrayList<ClqykhjbxxPage>();
      List<Clqykhjbxx> clqykhjbxxList = clqykhjbxxService.list(queryWrapper);
      for (Clqykhjbxx clqykhjbxx : clqykhjbxxList) {
          ClqykhjbxxPage vo = new ClqykhjbxxPage();
          BeanUtils.copyProperties(clqykhjbxx, vo);
          List<Clkhzlxx> clqykhzlxxList = clqykhzlxxService.selectByMainId(clqykhjbxx.getId());
          vo.setClqykhzlxxList(clqykhzlxxList);
		  Clkhywwlxx clkhywwlxx = clqykhywwlxxService.selectByMainId(clqykhjbxx.getId());
          vo.setClqykhywwlxx(clkhywwlxx);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "存量企业客户基本信息列表");
      mv.addObject(NormalExcelConstants.CLASS, ClqykhjbxxPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("存量企业客户基本信息列表数据", "导出人:Jeecg", "导出信息"));
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
              List<ClqykhjbxxPage> list = ExcelImportUtil.importExcel(file.getInputStream(), ClqykhjbxxPage.class, params);
              for (ClqykhjbxxPage page : list) {
                  Clqykhjbxx po = new Clqykhjbxx();
                  BeanUtils.copyProperties(page, po);
                  clqykhjbxxService.saveMain(po, page.getClqykhzlxxList(),page.getClqykhywwlxx());
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

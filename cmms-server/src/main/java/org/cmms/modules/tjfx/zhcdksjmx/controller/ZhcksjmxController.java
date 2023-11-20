package org.cmms.modules.tjfx.zhcdksjmx.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.system.service.ISysLogService;
import org.cmms.modules.tjfx.zhcdksjmx.entity.Zhcksjmx;
import org.cmms.modules.tjfx.zhcdksjmx.service.IZhcksjmxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 支行存款数据明细
 * @Author: cmms
 * @Date:   2019-09-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行存款数据明细")
@RestController
@RequestMapping("/tjfx/zhckshmx/zhcksjmx")
public class ZhcksjmxController implements Job {
	 @Autowired
	 private IZhcksjmxService zhcksjmxService;

	 @Autowired
	 private ISysLogService logService;
	/**
	  * 分页列表查询
	 * @param zhcksjmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行存款数据明细-分页列表查询")
	@ApiOperation(value="支行存款数据明细-分页列表查询", notes="支行存款数据明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Zhcksjmx>> queryPageList(Zhcksjmx zhcksjmx,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Zhcksjmx>> result = new Result<IPage<Zhcksjmx>>();
		QueryWrapper<Zhcksjmx> queryWrapper = QueryGenerator.initQueryWrapper(zhcksjmx, req.getParameterMap());
		Page<Zhcksjmx> page = new Page<Zhcksjmx>(pageNo, pageSize);
		IPage<Zhcksjmx> pageList = zhcksjmxService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param zhcksjmx
	 * @return
	 */
	@AutoLog(value = "支行存款数据明细-添加")
	@ApiOperation(value="支行存款数据明细-添加", notes="支行存款数据明细-添加")
	@PostMapping(value = "/add")
	public Result<Zhcksjmx> add(@RequestBody Zhcksjmx zhcksjmx) {
		Result<Zhcksjmx> result = new Result<Zhcksjmx>();
		try {
			zhcksjmxService.save(zhcksjmx);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param zhcksjmx
	 * @return
	 */
	/*@AutoLog(value = "支行存款数据明细-编辑")
	@ApiOperation(value="支行存款数据明细-编辑", notes="支行存款数据明细-编辑")
	@PutMapping(value = "/edit")
	public Result<Zhcksjmx> edit(@RequestBody Zhcksjmx zhcksjmx) {
		Result<Zhcksjmx> result = new Result<Zhcksjmx>();
		Zhcksjmx zhcksjmxEntity = zhcksjmxService.getById(zhcksjmx.getId());
		if(zhcksjmxEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = zhcksjmxService.updateById(zhcksjmx);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		return result;
	}*/

	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行存款数据明细-通过id删除")
	@ApiOperation(value="支行存款数据明细-通过id删除", notes="支行存款数据明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			zhcksjmxService.removeById(id);
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
	@AutoLog(value = "支行存款数据明细-批量删除")
	@ApiOperation(value="支行存款数据明细-批量删除", notes="支行存款数据明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<Zhcksjmx> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Zhcksjmx> result = new Result<Zhcksjmx>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.zhcksjmxService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行存款数据明细-通过id查询")
	@ApiOperation(value="支行存款数据明细-通过id查询", notes="支行存款数据明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Zhcksjmx> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Zhcksjmx> result = new Result<Zhcksjmx>();
		Zhcksjmx zhcksjmx = zhcksjmxService.getById(id);
		if(zhcksjmx==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(zhcksjmx);
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
      QueryWrapper<Zhcksjmx> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Zhcksjmx zhcksjmx = JSON.parseObject(deString, Zhcksjmx.class);
              queryWrapper = QueryGenerator.initQueryWrapper(zhcksjmx, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Zhcksjmx> pageList = zhcksjmxService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "支行存款数据明细列表");
      mv.addObject(NormalExcelConstants.CLASS, Zhcksjmx.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("支行存款数据明细列表数据", "导出人:Jeecg", "导出信息"));
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
              List<Zhcksjmx> listZhcksjmxs = ExcelImportUtil.importExcel(file.getInputStream(), Zhcksjmx.class, params);
              zhcksjmxService.saveBatch(listZhcksjmxs);
              return Result.ok("文件导入成功！数据行数:" + listZhcksjmxs.size());
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


	 @RequestMapping(value = "/getzhjynck", method = RequestMethod.GET)
	 public Result<JSONArray> getzhjynck(@RequestParam(name="zzbz", defaultValue="1") String zzbz) {
		 String ywjgdm=logService.getywjgdm(zzbz);
		 String zzlb=logService.getzzlb(zzbz);
		 Result<JSONArray> result = new Result<JSONArray>();
		 List<Map> list =null;
		 if(zzbz.equals("1")||zzlb.equals("3")){
			 list = zhcksjmxService.getzhjynck();
		 }else{
			 list = zhcksjmxService.getjynck(ywjgdm);
		 }
		 JSONArray json = new JSONArray();
		 for (int i =0;i< list.size();i++) {
			 Map map=list.get(i);
			 JSONObject jo = new JSONObject();
			 jo.put("type",map.get("TYPE"));
			 jo.put("余额", map.get("余额"));
			 jo.put("月日平", map.get("月日平"));
			 jo.put("年日平", map.get("年日平"));
			 json.add(jo);
		 }
		 //System.out.println(json);
		 result.success("获取成功!");
		 result.setResult(json);
		 return result;
	 }

	 @RequestMapping(value = "/getzhdqhqck", method = RequestMethod.GET)
	 public Result<JSONArray> getzhdqhqck(@RequestParam(name="zzbz", defaultValue="1") String zzbz) {
		 String ywjgdm=logService.getywjgdm(zzbz);
		 String sjzzbz=logService.getsjzzbz(zzbz);
		 String zzlb=logService.getzzlb(zzbz);
		 Result<JSONArray> result = new Result<JSONArray>();
		 List<Map> list =null;
		 if(zzbz.equals("1")||zzlb.equals("3")){
			 list = zhcksjmxService.getzhdqhqck();
		 }else{
			 list = zhcksjmxService.getdqhqck(ywjgdm);
		 }
		 //System.out.println("@@@@@@@@@@@@@@@@定期活期"+list);
		 JSONArray json = new JSONArray();
		 JSONObject jo = new JSONObject(new LinkedHashMap());
		 JSONObject jo1 = new JSONObject(new LinkedHashMap());
		 jo.put("type","活期");
		 jo1.put("type","定期");
		 for (int i =0;i< list.size();i++) {
			 Map map=list.get(i);
			 jo.put(map.get("TYPE").toString(),map.get("活期"));
			 jo1.put(map.get("TYPE").toString(),map.get("定期"));
		 }
		 json.add(jo);
		 json.add(jo1);
		 //System.out.println(json);
		 result.success("获取成功!");
		 result.setResult(json);

		 return result;
	 }


	 @RequestMapping(value = "/getzhdqhqcktype", method = RequestMethod.GET)
	 public Result<String[]> getzhdqhqcktype() {
		 Result<String[]> result = new Result<String[]>();
		 List<Map> list = zhcksjmxService.getzhdqhqck();
		 String [] type= new String [list.size()];
		 for (int i =0;i< list.size();i++) {
			 Map map=list.get(i);
			 type[i]=map.get("TYPE").toString();
		 }
		 result.success("获取成功!");
		 result.setResult(type);

		 return result;
	 }


	 @RequestMapping(value = "/getzhdsdgck", method = RequestMethod.GET)
	 public Result<JSONArray> getzhdsdgck(@RequestParam(name="zzbz", defaultValue="1") String zzbz) {
		 String ywjgdm=logService.getywjgdm(zzbz);
		 String sjzzbz=logService.getsjzzbz(zzbz);
		 String zzlb=logService.getzzlb(zzbz);
		 Result<JSONArray> result = new Result<JSONArray>();
		 List<Map> list =null;
		 if(zzbz.equals("1")||zzlb.equals("3")){
			 list = zhcksjmxService.getzhdgdsck();
		 }else{
			 list = zhcksjmxService.getdgdsck(ywjgdm);
		 }
		 JSONArray json = new JSONArray();
		 JSONObject jo = new JSONObject(new LinkedHashMap());
		 JSONObject jo1 = new JSONObject(new LinkedHashMap());
		 jo.put("type","对私");
		 jo1.put("type","对公");
		 for (int i =0;i< list.size();i++) {
			 Map map=list.get(i);
			 jo.put(map.get("TYPE").toString(),map.get("对私"));
			 jo1.put(map.get("TYPE").toString(),map.get("对公"));
		 }

		 json.add(jo);
		 json.add(jo1);
		 //System.out.println(json);
		 result.success("获取成功!");
		 result.setResult(json);

		 return result;
	 }


	 @RequestMapping(value = "/getzhdsdgcktype", method = RequestMethod.GET)
	 public Result<String[]> getzhdsdgcktype() {
		 Result<String[]> result = new Result<String[]>();
		 List<Map> list = zhcksjmxService.getzhdgdsck();
		 String [] type= new String [list.size()];
		 for (int i =0;i< list.size();i++) {
			 Map map=list.get(i);
			 type[i]=map.get("TYPE").toString();
		 }
		 result.success("获取成功!");
		 result.setResult(type);

		 return result;
	 }


	 @RequestMapping(value = "/getzhanlqjck", method = RequestMethod.GET)
	 public Result<JSONArray> getzhanlqjck(@RequestParam(name="zzbz", defaultValue="1") String zzbz) {
		 String ywjgdm=logService.getywjgdm(zzbz);
		 String sjzzbz=logService.getsjzzbz(zzbz);
		 String zzlb=logService.getzzlb(zzbz);
		 Result<JSONArray> result = new Result<JSONArray>();
		 List<Map> list =null;
		 if(zzbz.equals("1")||zzlb.equals("3")){
			 list = zhcksjmxService.getzhanlqjck(zhcksjmxService.getAnlqjcRq());
		 }else{
			 list = zhcksjmxService.getanlqjck(zhcksjmxService.getAnlqjcRq(),ywjgdm);
		 }
		 //System.out.println("@@@@@@@@@::::::::年龄区间存款=="+list);
		 JSONArray json = new JSONArray();
		 JSONObject jo = new JSONObject(new LinkedHashMap());
		 if(list.size()>0){
			 jo.put("name","年龄不详");
			 jo.put("value",list.get(0).get("BL"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","18岁以下");
			 jo.put("value",list.get(0).get("SBYX"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","19-26");
			 jo.put("value",list.get(0).get("SJDEL"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","27-30");
			 jo.put("value",list.get(0).get("EQDSL"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","31-40");
			 jo.put("value",list.get(0).get("SYDSL"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","41-55");
			 jo.put("value",list.get(0).get("SYDWW"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","56-65");
			 jo.put("value",list.get(0).get("WLDLW"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","66岁以上");
			 jo.put("value",list.get(0).get("LLYS"));
			 json.add(jo);
		 }
		 //System.out.println(json);
		 result.success("获取成功!");
		 result.setResult(json);
		 return result;
	 }

	 @RequestMapping(value = "/getzhdhckqj", method = RequestMethod.GET)
	 public Result<JSONArray> getzhdhckqj(@RequestParam(name="zzbz", defaultValue="1") String zzbz) {
		 String ywjgdm=logService.getywjgdm(zzbz);
		 String sjzzbz=logService.getsjzzbz(zzbz);
		 String zzjb=logService.getZzjb(zzbz);
		 Result<JSONArray> result = new Result<JSONArray>();
		 List<Map> list =null;
		 if(zzbz.equals("1")||zzjb.equals("3")){
			 list = zhcksjmxService.getzhdhckqj(zhcksjmxService.getAnlqjcRq());
		 }else{
			 list = zhcksjmxService.getdhckqj(zhcksjmxService.getAnlqjcRq(),ywjgdm);
		 }
		 //System.out.println("@@@@@@@@@::2222222222222222::::::存款区间户数明细=="+list);
		 JSONArray json = new JSONArray();
		 JSONObject jo = new JSONObject(new LinkedHashMap());
		 if(list.size()>0){
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","1万以下");
			 jo.put("value",list.get(0).get("YWYF"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","1万元至5万元");
			 jo.put("value",list.get(0).get("WWYX"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","5万元至10万元");
			 jo.put("value",list.get(0).get("SWYX"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","10万元至50万元");
			 jo.put("value",list.get(0).get("WSWYX"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","50万元以上");
			 jo.put("value",list.get(0).get("WSWYS"));
			 json.add(jo);
		 }

		 //System.out.println(json);
		 result.success("获取成功!");
		 result.setResult(json);
		 return result;
	 }


	 @RequestMapping(value = "/getckpm", method = RequestMethod.GET)
	 public Result<JSONArray> getckpm(@RequestParam(name="zzbz", defaultValue="1") String zzbz) {
		 Result<JSONArray> result = new Result<JSONArray>();
		 List<Map> 	list = zhcksjmxService.getckpm(zhcksjmxService.getAnlqjcRq());
		 //System.out.println("@@@@@@@@@::::::::存款排名=="+list+""+list.size());
		 JSONArray json = new JSONArray();
		 for (int i =0;i< list.size();i++) {
			 Map map=list.get(i);
			 JSONObject jo = new JSONObject(new LinkedHashMap());
			 jo.put("name",map.get("ZZJC"));
			 jo.put("total",map.get("YE"));
			 json.add(jo);

		 }
		 result.success("获取成功!");
		 result.setResult(json);
		 return result;
	 }

	 @RequestMapping(value = "/getckwclpm", method = RequestMethod.GET)
	 public Result<JSONArray> getckwclpm(@RequestParam(name="zzbz", defaultValue="1") String zzbz) {
		 Result<JSONArray> result = new Result<JSONArray>();
		 List<Map> 	list = zhcksjmxService.getckwclpm(zhcksjmxService.getAnlqjcRq());
		 //System.out.println("@@@@@@@@@::::::::存款排名=="+list+""+list.size());
		 JSONArray json = new JSONArray();
		 for (int i =0;i< list.size();i++) {
			 Map map=list.get(i);
			 JSONObject jo = new JSONObject(new LinkedHashMap());
			 jo.put("name",map.get("ZZJC"));
			 jo.put("total",map.get("CKWCL"));
			 jo.put("ye",map.get("YE"));
			 jo.put("rw",map.get("RW"));
			 json.add(jo);

		 }
		 result.success("获取成功!");
		 result.setResult(json);
		 return result;
	 }


	 @RequestMapping(value = "/getzhjyndksj", method = RequestMethod.GET)
	 public Result<JSONArray> getzhjyndksj(@RequestParam(name="zzbz", defaultValue="1") String zzbz) {
		 String ywjgdm=logService.getywjgdm(zzbz);
		 String zzlb=logService.getzzlb(zzbz);
		 Result<JSONArray> result = new Result<JSONArray>();
		 List<Map> list =null;
		 if(zzbz.equals("1")||zzlb.equals("3")){
			 list = zhcksjmxService.getzhjyndksj();
		 }else{
			 list = zhcksjmxService.getjyndksj(ywjgdm);
		 }
		 //System.out.println("getzhjyndksj="+list);

		 JSONArray json = new JSONArray();
		 for (int i =0;i< list.size();i++) {
			 Map map=list.get(i);
			 JSONObject jo = new JSONObject();
			 jo.put("type",map.get("TYPE"));
			 jo.put("余额", map.get("余额"));
			 jo.put("月日平", map.get("月日平"));
			 jo.put("年日平", map.get("年日平"));
			 json.add(jo);
		 }
		 //System.out.println(json);
		 result.success("获取成功!");
		 result.setResult(json);
		 return result;
	 }



	 @RequestMapping(value = "/getzhwjfldk", method = RequestMethod.GET)
	 public Result<JSONArray> getzhwjfldk(@RequestParam(name="zzbz", defaultValue="1") String zzbz) {
		 String ywjgdm=logService.getywjgdm(zzbz);
		 String sjzzbz=logService.getsjzzbz(zzbz);
		 String zzlb=logService.getzzlb(zzbz);
		 Result<JSONArray> result = new Result<JSONArray>();
		 List<Map> list =null;
		 if(zzbz.equals("1")||zzlb.equals("3")){
			 list = zhcksjmxService.getzhwjfldk(zhcksjmxService.getZhdksjrq());
		 }else{
			 list = zhcksjmxService.getwjfldk(zhcksjmxService.getZhdksjrq(),ywjgdm);
		 }
		 //System.out.println("@@@@@@@@@::::::::五级分类=="+list);
		 JSONArray json = new JSONArray();
		 JSONObject jo = new JSONObject(new LinkedHashMap());
		 if(list.size()>0){
			 jo.put("name","正常");
			 jo.put("value",list.get(0).get("ZCYE"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","关注");
			 jo.put("value",list.get(0).get("GZYE"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","次级");
			 jo.put("value",list.get(0).get("CJYE"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","可疑");
			 jo.put("value",list.get(0).get("KYYE"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","损失");
			 jo.put("value",list.get(0).get("SSYE"));
			 json.add(jo);
		 }
		 //System.out.println(json);
		 result.success("获取成功!");
		 result.setResult(json);
		 return result;
	 }



	 @RequestMapping(value = "/getzhanlqjdk", method = RequestMethod.GET)
	 public Result<JSONArray> getzhanlqjdk(@RequestParam(name="zzbz", defaultValue="1") String zzbz) {
		 String ywjgdm=logService.getywjgdm(zzbz);
		 String sjzzbz=logService.getsjzzbz(zzbz);
		 String zzlb=logService.getzzlb(zzbz);
		 Result<JSONArray> result = new Result<JSONArray>();
		 List<Map> list =null;
		 if(zzbz.equals("1")||zzlb.equals("3")){
			 list = zhcksjmxService.getzhanlqjdk(zhcksjmxService.getAnlqjcRqDk());
		 }else{
			 list = zhcksjmxService.getanlqjdk(zhcksjmxService.getAnlqjcRqDk(),ywjgdm);
		 }
		 //System.out.println("@@@@@@@@@::::::::贷款余额按年龄分析=="+list);
		 JSONArray json = new JSONArray();
		 JSONObject jo = new JSONObject(new LinkedHashMap());
		 if(list.size()>0){
			 jo.put("name","年龄不详");
			 jo.put("value",list.get(0).get("BL"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","18岁以下");
			 jo.put("value",list.get(0).get("SBYX"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","19-26");
			 jo.put("value",list.get(0).get("SJDEL"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","27-30");
			 jo.put("value",list.get(0).get("EQDSL"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","31-40");
			 jo.put("value",list.get(0).get("SYDSL"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","41-55");
			 jo.put("value",list.get(0).get("SYDWW"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","56-65");
			 jo.put("value",list.get(0).get("WLDLW"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","66岁以上");
			 jo.put("value",list.get(0).get("LLYS"));
			 json.add(jo);
		 }
		 //System.out.println(json);
		 result.success("获取成功!");
		 result.setResult(json);
		 return result;
	 }


	 @RequestMapping(value = "/getzhdhdkqj", method = RequestMethod.GET)
	 public Result<JSONArray> getzhdhdkqj(@RequestParam(name="zzbz", defaultValue="1") String zzbz) {
		 String ywjgdm=logService.getywjgdm(zzbz);
		 String sjzzbz=logService.getsjzzbz(zzbz);
		 String zzlb=logService.getzzlb(zzbz);
		 Result<JSONArray> result = new Result<JSONArray>();
		 List<Map> list =null;
		 if(zzbz.equals("1")||zzlb.equals("3")){
			 list = zhcksjmxService.getzhdhdkqj(zhcksjmxService.getAnlqjcRqDk());
		 }else{
			 list = zhcksjmxService.getdhdkqj(zhcksjmxService.getAnlqjcRqDk(),ywjgdm);
		 }
		 //System.out.println("@@@@@@@@@::2222222222222222::::::贷款区间户数=="+list);
		 JSONArray json = new JSONArray();
		 JSONObject jo = new JSONObject(new LinkedHashMap());
		 if(list.size()>0){
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","1万以下");
			 jo.put("value",list.get(0).get("YWYF"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","1万元至5万元");
			 jo.put("value",list.get(0).get("WWYX"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","5万元至10万元");
			 jo.put("value",list.get(0).get("SWYX"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","10万元至50万元");
			 jo.put("value",list.get(0).get("WSWYX"));
			 json.add(jo);
			 jo = new JSONObject(new LinkedHashMap());
			 jo.put("name","50万元以上");
			 jo.put("value",list.get(0).get("WSWYS"));
			 json.add(jo);
		 }

		 //System.out.println(json);
		 result.success("获取成功!");
		 result.setResult(json);
		 return result;
	 }

	 @RequestMapping(value = "/getdkpm", method = RequestMethod.GET)
	 public Result<JSONArray> getdkpm(@RequestParam(name="zzbz", defaultValue="1") String zzbz) {
		 Result<JSONArray> result = new Result<JSONArray>();
		 List<Map> 	list = zhcksjmxService.getdkpm(zhcksjmxService.getAnlqjcRqDk());
		 //System.out.println("@@@@@@@@@::::::::贷款排名=="+list+""+list.size());
		 JSONArray json = new JSONArray();
		 for (int i =0;i< list.size();i++) {
			 Map map=list.get(i);
			 JSONObject jo = new JSONObject(new LinkedHashMap());
			 jo.put("name",map.get("ZZJC"));
			 jo.put("total",map.get("DKYE"));
			 json.add(jo);

		 }
		 result.success("获取成功!");
		 result.setResult(json);
		 return result;
	 }


	 @RequestMapping(value = "/getdkwclpm", method = RequestMethod.GET)
	 public Result<JSONArray> getdkwclpm(@RequestParam(name="zzbz", defaultValue="1") String zzbz) {
		 Result<JSONArray> result = new Result<JSONArray>();
		 List<Map> 	list = zhcksjmxService.getdkwclpm(zhcksjmxService.getAnlqjcRqDk());
		 //System.out.println("@@@@@@@@@::::::::贷款排名=="+list+""+list.size());
		 JSONArray json = new JSONArray();
		 for (int i =0;i< list.size();i++) {
			 Map map=list.get(i);
			 JSONObject jo = new JSONObject(new LinkedHashMap());
			 jo.put("name",map.get("ZZJC"));
			 jo.put("total",map.get("DKWCL"));
			 jo.put("ye",map.get("DKYE"));
			 jo.put("rw",map.get("RW"));
			 json.add(jo);

		 }
		 result.success("获取成功!");
		 result.setResult(json);
		 return result;
	 }


	 @Override
	 public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		 log.info(String.format("自动执行首页页面数据图表所需数据准备开始begin-"+ DateUtils.getTimestamp()));

		 zhcksjmxService.callSysjmx();
		 log.info(String.format("自动执行首页页面数据图表所需数据准备结束end-" + DateUtils.getTimestamp()));



	 }


}

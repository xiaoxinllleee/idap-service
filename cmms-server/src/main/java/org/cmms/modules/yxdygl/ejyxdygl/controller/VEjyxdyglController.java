package org.cmms.modules.yxdygl.ejyxdygl.controller;

import java.io.*;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.hutool.poi.word.WordUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.FileUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.AreaUtil;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.WordUtils;
import org.cmms.modules.yxdygl.ejyxdygl.entity.Cqtj;
import org.cmms.modules.yxdygl.ejyxdygl.entity.Ejyxdygl;
import org.cmms.modules.yxdygl.ejyxdygl.entity.VEjyxdygl;
import org.cmms.modules.yxdygl.ejyxdygl.service.ICqtjService;
import org.cmms.modules.yxdygl.ejyxdygl.service.IEjyxdyglService;
import org.cmms.modules.yxdygl.ejyxdygl.service.IVEjyxdyglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.yxdygl.ejyxdygl.vo.EjyxdyglPage;
import org.cmms.modules.yxdygl.sjyxdygl.entity.Sjyxdygl;
import org.cmms.modules.yxdygl.sjyxdygl.service.ISjyxdyglService;
import org.cmms.modules.yxdygl.yxdyfjxx.entity.Yxdyfjxx;
import org.cmms.modules.yxdygl.yxdyfjxx.service.IYxdyfjxxService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 二级营销单元管理
 * @Author: Penghr
 * @Date:   2020-07-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="二级营销单元管理")
@RestController
@RequestMapping("/yxdygl/ejyxdygl")
public class VEjyxdyglController extends JeecgController<VEjyxdygl, IVEjyxdyglService> {
	@Autowired
	private IVEjyxdyglService vEjyxdyglService;
	@Autowired
	private IEjyxdyglService iEjyxdyglService;
	@Autowired
	private AreaUtil areaUtil;
	@Autowired
	private IYxdyfjxxService yxdyfjxxService;
	@Autowired
	private ICqtjService cqtjService;
	@Autowired
	private ISysDictService sysDictService;
	@Autowired
	private ISjyxdyglService sjyxdyglService;
	@Value(value = "${common.path.upload}")
	private String uploadPath;
	@Value(value = "${common.path.export}")
	private String exportpath;

	/**
	 * 分页列表查询
	 *
	 * @param vEjyxdygl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "二级营销单元管理-分页列表查询")
	@ApiOperation(value="二级营销单元管理-分页列表查询", notes="二级营销单元管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VEjyxdygl vEjyxdygl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VEjyxdygl> queryWrapper = QueryGenerator.initQueryWrapper(vEjyxdygl, req.getParameterMap());
		/*LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String username = sysUser.getUsername();
		if (!"admin".equals(username)) {
			queryWrapper.eq("yggh", sysUser.getWorkNo());
			queryWrapper.exists("select 1 from (" +
					"    select t3.dybh,t3.dymc" +
					"    from sys_bas_user t1,yxdygl_pqzrrgl t2,yxdygl_ejyxdygl t3" +
					"    where t1.tellid=t2.khjl and t1.username = '" + username + "' and t2.ejyxdybh=t3.dybh" +
					"    group by t3.dybh,t3.dymc) n1 where dybh=n1.dybh");
		}*/
		Page<VEjyxdygl> page = new Page<VEjyxdygl>(pageNo, pageSize);
		IPage<VEjyxdygl> pageList = vEjyxdyglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}


	 /**
	  * 分页列表查询
	  *
	  * @param ejyxdygl
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "二级营销单元管理-分页列表查询")
	 @ApiOperation(value="二级营销单元管理-分页列表查询", notes="二级营销单元管理-分页列表查询")
	 @GetMapping(value = "/querlist")
	 public Result<?> queryPageList(Ejyxdygl ejyxdygl,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 QueryWrapper<Ejyxdygl> queryWrapper = QueryGenerator.initQueryWrapper(ejyxdygl, req.getParameterMap());
		 /*LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String username = sysUser.getUsername();
		 if (!"admin".equals(username)) {
			 //queryWrapper.eq("yggh", sysUser.getWorkNo());
			 queryWrapper.exists("select 1 from (" +
					 "    select t3.dybh,t3.dymc" +
					 "    from sys_bas_user t1,yxdygl_pqzrrgl t2,yxdygl_ejyxdygl t3" +
					 "    where t1.tellid=t2.khjl and t1.username = '" + username + "' and t2.ejyxdybh=t3.dybh" +
					 "    group by t3.dybh,t3.dymc) n1 where dybh=n1.dybh");
		 }*/

		 /*if (!"admin".equals(username)) {
			 queryWrapper.eq("yggh", sysUser.getWorkNo());
			 Map<Object, Object> map = areaUtil.getEjyxdyqx(req);
			 if (map != null && map.size() > 0) {
				 queryWrapper.in("dybh", map.keySet());
			 } else {
				 queryWrapper.isNull("dybh");
			 }
		 }*/
		 Page<Ejyxdygl> page = new Page<Ejyxdygl>(pageNo, pageSize);
		 IPage<Ejyxdygl> pageList = iEjyxdyglService.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }

	 /**
	  * 分页列表查询
	  *
	  * @param vEjyxdygl
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "二级营销单元管理-列表查询")
	 @ApiOperation(value="二级营销单元管理-列表查询", notes="二级营销单元管理-列表查询")
	 @GetMapping(value = "/listAll")
	 public Result<?> queryListAll(VEjyxdygl vEjyxdygl,
								   @RequestParam(name="qfbs", defaultValue = "1", required = false) String qfbs,
								   HttpServletRequest req) {
		 QueryWrapper<VEjyxdygl> queryWrapper = QueryGenerator.initQueryWrapper(vEjyxdygl, req.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String username = sysUser.getUsername();
		 if (!"admin".equals(username)) {
			 queryWrapper.eq("yggh", sysUser.getWorkNo());
			 StringBuffer inSql = new StringBuffer("select dybh from (" +
					 "    select t3.dybh,t3.dymc" +
					 "    from sys_bas_user t1,yxdygl_pqzrrgl t2,yxdygl_ejyxdygl t3" +
					 "    where t1.tellid=t2.khjl and t1.username = '" + username + "' and t2.ejyxdybh=t3.dybh");
			 if (!StringUtils.isEmpty(qfbs) && "2".equalsIgnoreCase(qfbs)) {
			 	//商户区域
				 inSql.append("   and (t2.qfbs = 2)");
			 } else {
				 inSql.append("   and (t2.qfbs = 1 or t2.qfbs is null )");
			 }
			 inSql.append("  group by t3.dybh,t3.dymc)");
			 queryWrapper.inSql("dybh", inSql.toString());
//			 queryWrapper.inSql("dybh","select dybh from (" +
//					 "    select t3.dybh,t3.dymc" +
//					 "    from sys_user t1,yxdygl_pqzrrgl t2,yxdygl_ejyxdygl t3" +
//					 "    where t1.work_no=t2.khjl and t1.username = '" + username + "' and t2.ejyxdybh=t3.dybh" +
//					 "    and (t2.qfbs = 1 or t2.qfbs is null )  group by t3.dybh,t3.dymc)");
			 /*queryWrapper.exists("select 1 from (" +
					 "    select t3.dybh,t3.dymc" +
					 "    from sys_bas_user t1,yxdygl_pqzrrgl t2,yxdygl_ejyxdygl t3" +
					 "    where t1.tellid=t2.khjl and t1.username = '" + username + "' and t2.ejyxdybh=t3.dybh" +
					 "    and (t2.qfbs = 1 or t2.qfbs is null )  group by t3.dybh,t3.dymc) n1 where dybh=n1.dybh");*/
		 }
		 /*if (!"admin".equals(username)) {
			 queryWrapper.eq("yggh", sysUser.getWorkNo());
			 Map<Object, Object> map = areaUtil.getEjyxdyqx(req);
			 if (map != null && map.size() > 0) {
				 queryWrapper.in("dybh", map.keySet());
			 } else {
				 queryWrapper.isNull("dybh");
			 }
		 }*/
		 List<VEjyxdygl> pageList = vEjyxdyglService.list(queryWrapper);
		 List<VEjyxdygl> returnList = new ArrayList<>();
		 for (VEjyxdygl vEjyjxdygl : pageList) {
		 	 boolean exists = false;
		 	 for (Iterator it = returnList.iterator(); it.hasNext(); ) {
				 VEjyxdygl ve = (VEjyxdygl)it.next();
				 if (ve.getDybh().equalsIgnoreCase(vEjyjxdygl.getDybh())) {
					 exists = true;
					 break;
				 }
			 }
			 if (!exists) {
				 returnList.add(vEjyjxdygl);
			 }
		 }
		 return Result.ok(returnList);
	 }

	/**
	 * 添加
	 *
	 * @param ejyxdygl
	 * @return
	 */
	@AutoLog(value = "二级营销单元管理-添加")
	@ApiOperation(value="二级营销单元管理-添加", notes="二级营销单元管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ejyxdygl ejyxdygl) {
		iEjyxdyglService.save(ejyxdygl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ejyxdyglPage
	 * @return
	 */
	@AutoLog(value = "二级营销单元管理-编辑")
	@ApiOperation(value="二级营销单元管理-编辑", notes="二级营销单元管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody EjyxdyglPage ejyxdyglPage, HttpServletRequest req) {
		Result<Ejyxdygl> result = new Result<Ejyxdygl>();
		Ejyxdygl ejyxdygl = new Ejyxdygl();
		BeanUtils.copyProperties(ejyxdyglPage, ejyxdygl);
		Ejyxdygl ejyxdyglEntity = iEjyxdyglService.getById(ejyxdygl.getId());
		List<Yxdyfjxx> yxdyfjxxList = ejyxdyglPage.getYxdyfjxxList();
		if (ejyxdyglEntity == null) {
			result.error500("未找到对应实体");
		} else {
			iEjyxdyglService.updateById(ejyxdygl);
//			boolean ok = iYjyxdyglService.updateById(yjyxdygl);
			/*iEjyxdyglService.updateMain(ejyxdygl, ejyxdyglPage.getYxdyfjxxList());*/
			/*List<String> deleteList = ejyxdyglPage.getDeleteIds();
			if (!deleteList.isEmpty()) {
				yxdyfjxxService.removeByIds(deleteList);
			}*/
			result.success("修改成功!");
		}
		return result;
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "二级营销单元管理-通过id删除")
	@ApiOperation(value="二级营销单元管理-通过id删除", notes="二级营销单元管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		iEjyxdyglService.delMain(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "二级营销单元管理-批量删除")
	@ApiOperation(value="二级营销单元管理-批量删除", notes="二级营销单元管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.iEjyxdyglService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "二级营销单元管理-通过id查询")
	@ApiOperation(value="二级营销单元管理-通过id查询", notes="二级营销单元管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ejyxdygl ejyxdygl = iEjyxdyglService.getById(id);
		return Result.ok(ejyxdygl);
	}

	  /**
	   * 导出excel
	   *
	   * @param request
	   * @param vEjyxdygl
	   */
	@RequestMapping(value = "/exportXls")
	  public ModelAndView exportXls(HttpServletRequest request, VEjyxdygl vEjyxdygl) {
		  return super.exportXls(request, vEjyxdygl, VEjyxdygl.class, "二级营销单元管理");
	}

	 /**
	  * 导出二级营销单元导入模板
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView templateExportXls() {
		 // AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "二级营销单元导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, Ejyxdygl.class);
		 ExportParams exportParams = new ExportParams("二级营销单元导入模板","二级营销单元导入信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<Ejyxdygl>());
		 return mv;
	 }

	 @GetMapping(value = "/getCqtj")
	 public Result<?> getCqtj(@RequestParam(name="dybh") String dybh) {
		 JSONObject jsonObject = new JSONObject();
	 	 QueryWrapper<Ejyxdygl> ejyxdyglQueryWrapper = new QueryWrapper<>();
	 	 ejyxdyglQueryWrapper.eq("dybh", dybh);
		 Ejyxdygl ejyxdygl = iEjyxdyglService.getOne(ejyxdyglQueryWrapper);
		 if (ejyxdygl == null) {
		 	return Result.error("未找到对应的营销单元信息！");
		 }
		 jsonObject.put("ejyxdygl", ejyxdygl);
		 String ssxz_dictText = sysDictService.queryTableDictTextByKey("v_yxdygl_ejyxdygl", "yjyxdymc", "dybh", ejyxdygl.getDybh());
		 String xzc_dictText = sysDictService.queryTableDictTextByKey("v_yxdygl_ejyxdygl", "dymc", "dybh", ejyxdygl.getDybh());
		 jsonObject.put("ssxz_dictText", ssxz_dictText);
		 jsonObject.put("xzc_dictText", xzc_dictText);
		 //获取村情统计信息
		 QueryWrapper<Cqtj> cqtjQueryWrapper = new QueryWrapper<>();
		 cqtjQueryWrapper.eq("xzc", dybh);
		 cqtjQueryWrapper.orderByDesc("tjyf");
		 List<Cqtj> cqtjList = cqtjService.list(cqtjQueryWrapper);
		 if (!cqtjList.isEmpty()) {
		 	Cqtj cqtj = cqtjList.get(0);
			 jsonObject.put("cqtj", cqtj);
		 }
		 //获取组信息
		 QueryWrapper<Sjyxdygl> sjyxdyglQueryWrapper = new QueryWrapper<>();
		 sjyxdyglQueryWrapper.eq("ejyxdybh", dybh);
		 sjyxdyglQueryWrapper.orderByAsc("dybh");
		 List<Sjyxdygl> sjyxdyglList = sjyxdyglService.list(sjyxdyglQueryWrapper);
		 jsonObject.put("sjyxdysl", sjyxdyglList.size());
		 StringBuffer sjyxdymcCombine = new StringBuffer();
		 for (Sjyxdygl sjyxdygl : sjyxdyglList) {
		 	String sjyxdymc = sjyxdygl.getDymc();
		 	sjyxdymcCombine.append(sjyxdymc).append("、");
		 }
		 if (sjyxdymcCombine.length() > 0) {
		 	sjyxdymcCombine.deleteCharAt(sjyxdymcCombine.length()-1);
		 }
		 jsonObject.put("sjyxdymc", sjyxdymcCombine.toString());
		 return Result.ok(jsonObject);
	 }

	 @GetMapping(value = "/download")
	 public void download(HttpServletRequest request, HttpServletResponse response, @RequestParam(name="dybh") String dybh) {
		 InputStream inputStream = null;
		 OutputStream outputStream = null;
		 try {

		 	 Map<String, Object> data = new HashMap<>();

			 QueryWrapper<Ejyxdygl> ejyxdyglQueryWrapper = new QueryWrapper<>();
			 ejyxdyglQueryWrapper.eq("dybh", dybh);
			 Ejyxdygl ejyxdygl = iEjyxdyglService.getOne(ejyxdyglQueryWrapper);
			 data.put("ejyxdygl", ejyxdygl);
			 String ssxz_dictText = sysDictService.queryTableDictTextByKey("v_yxdygl_ejyxdygl", "yjyxdymc", "dybh", ejyxdygl.getDybh());
			 String xzc_dictText = sysDictService.queryTableDictTextByKey("v_yxdygl_ejyxdygl", "dymc", "dybh", ejyxdygl.getDybh());
			 data.put("ssxz_dictText", ssxz_dictText);
			 data.put("xzc_dictText", xzc_dictText);
			 //获取村情统计信息
			 QueryWrapper<Cqtj> cqtjQueryWrapper = new QueryWrapper<>();
			 cqtjQueryWrapper.eq("xzc", dybh);
			 cqtjQueryWrapper.orderByDesc("tjyf");
			 List<Cqtj> cqtjList = cqtjService.list(cqtjQueryWrapper);
			 if (!cqtjList.isEmpty()) {
				 Cqtj cqtj = cqtjList.get(0);
				 data.put("cqtj", cqtj);
			 }
			 //获取组信息
			 QueryWrapper<Sjyxdygl> sjyxdyglQueryWrapper = new QueryWrapper<>();
			 sjyxdyglQueryWrapper.eq("ejyxdybh", dybh);
			 sjyxdyglQueryWrapper.orderByAsc("dybh");
			 List<Sjyxdygl> sjyxdyglList = sjyxdyglService.list(sjyxdyglQueryWrapper);
			 data.put("sjyxdysl", sjyxdyglList.size());
			 StringBuffer sjyxdymcCombine = new StringBuffer();
			 for (Sjyxdygl sjyxdygl : sjyxdyglList) {
				 String sjyxdymc = sjyxdygl.getDymc();
				 sjyxdymcCombine.append(sjyxdymc).append("、");
			 }
			 if (sjyxdymcCombine.length() > 0) {
				 sjyxdymcCombine.deleteCharAt(sjyxdymcCombine.length()-1);
			 }
			 data.put("sjyxdymc", sjyxdymcCombine.toString());
			 String fileName = ssxz_dictText + xzc_dictText + "村情分析报告.doc";
			 outputStream = response.getOutputStream();
			 String exportFilePath = exportpath + File.separator + fileName;
			 WordUtils.generateWord(data, exportFilePath, "村情分析报告.ftl");
			 FileInputStream fileInputStream = new FileInputStream(exportFilePath);
			 byte[] bys = new byte[fileInputStream.available()];
			 fileInputStream.read(bys);
			 response.setContentType("application/force-download");// 设置强制下载不打开            
			 response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"),"iso-8859-1"));
			 outputStream.write(bys);
			 outputStream.flush();
			 outputStream.close();
		 } catch (Exception e) {
			 log.info("文件下载失败" + e.getMessage());
			 // e.printStackTrace();
		 } finally {
			 if (inputStream != null) {
				 try {
					 inputStream.close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
			 if (outputStream != null) {
				 try {
					 outputStream.close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
	 }

	 /**
	  * 通过excel导入数据
	  *
	  * @param jsonObject
	  * @return
	  */
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject) {
		 String filePaths = jsonObject.getString("filePaths");
		 if (StringUtils.isEmpty(filePaths)) {
			 return Result.error("请先上传导入文件!");
		 }
		 String[] filePathList = filePaths.split(",");
		 for (String filePath : filePathList) {
			 filePath = uploadPath + File.separator + filePath;
			 File file = new File(filePath);
			 ImportParams importParams = new ImportParams();
			 importParams.setTitleRows(1);
			 importParams.setHeadRows(1);
			 importParams.setNeedSave(true);
			 try {
				 List<Ejyxdygl> ejyxdyglList = ExcelImportUtil.importExcel(file, Ejyxdygl.class, importParams);
				 List<String> stringList = new ArrayList<>();
				 for (Ejyxdygl ejyxdygl : ejyxdyglList) {
					 Map<String,String> params = new HashMap<>();
					 params.put("dybh", ejyxdygl.getDybh());
					 Ejyxdygl ejyxdyglEntity = iEjyxdyglService.queryDataByDybh(params);
					 if (ejyxdyglEntity != null) {
						 stringList.add(ejyxdygl.getDybh());
					 }
				 }
				 if (!stringList.isEmpty()) {
					 for (String bh : stringList) {
						 String dybh = bh;
						 UpdateWrapper<Ejyxdygl> updateWrapper = new UpdateWrapper<>();
						 updateWrapper.eq("dybh", dybh);
						 iEjyxdyglService.remove(updateWrapper);
					 }
				 }
				 iEjyxdyglService.saveBatch(ejyxdyglList);
				 return Result.ok("文件导入成功！共[ "+ejyxdyglList.size()+" ]条数据！");
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败！"+e.getMessage());
			 }
		 }
		 return Result.ok("文件导入成功!");
	 }

}

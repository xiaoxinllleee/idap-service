package org.cmms.modules.yxdygl.yxdyglmain.controller;

import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dklldj.csszgl.fdcsdzb.service.IFdcsdzbService;
import org.cmms.modules.khdj.khdjpd.entity.Khdjpd;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.impl.HrBasOrganizationServiceImpl;
import org.cmms.modules.system.util.RandImageUtil;
import org.cmms.modules.tjfx.wgtjfx.wgxxtj.controller.WgxxtjController;
import org.cmms.modules.tjfx.wgtjfx.wgxxtj.entity.WgxqtjVo;
import org.cmms.modules.util.GeneratePieChartUtil;
import org.cmms.modules.util.JFreeChartUtil;
import org.cmms.modules.util.WordUtils;
import org.cmms.modules.yxdygl.ejyxdygl.entity.Cqtj;
import org.cmms.modules.yxdygl.ejyxdygl.entity.Ejyxdygl;
import org.cmms.modules.yxdygl.ejyxdygl.service.ICqtjService;
import org.cmms.modules.yxdygl.sjyxdygl.entity.Sjyxdygl;
import org.cmms.modules.yxdygl.yxdyglmain.entity.VYxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMainExp;
import org.cmms.modules.yxdygl.yxdyglmain.service.IVYxdyglMainService;
import org.cmms.modules.yxdygl.yxdyglmain.service.IYxdyglMainService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.yxdygl.yxdyglmain.verify.YxdyglImpVerify;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jfree.data.general.DatasetGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Description: 营销单元管理
 * @Author: jeecg-boot
 * @Date:   2021-11-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags="营销单元管理")
@RestController
@RequestMapping("/yxdyglmain/yxdyglMain")
public class YxdyglMainController extends JeecgController<YxdyglMain, IYxdyglMainService> {
	@Autowired
	private IYxdyglMainService yxdyglMainService;
	@Value(value = "${common.path.upload}")
	private String uploadpath;
	@Value(value = "${common.path.export}")
	private String exportpath;

	@Autowired
	private YxdyglImpVerify yxdyglImpVerify;
	@Autowired
	IHrBasOrganizationService hrBasOrganizationService;
	@Autowired
	RedisUtil redisUtil;
	@Autowired
	private ICqtjService cqtjService;
	@Autowired
	private IVYxdyglMainService vYxdyglMainService;
	@Autowired
	private WgxxtjController wgxxtjController;



	/**
	 * 分页列表查询
	 *
	 * @param yxdyglMain
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "营销单元管理-分页列表查询")
	@ApiOperation(value="营销单元管理-分页列表查询", notes="营销单元管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(YxdyglMain yxdyglMain,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		//不把该条件放到参数中init
		String id = null;
		if (StringUtils.isNotBlank(yxdyglMain.getParentId())){
			id = yxdyglMain.getParentId();
			yxdyglMain.setParentId(null);
		}
		QueryWrapper<YxdyglMain> queryWrapper = QueryGenerator.initQueryWrapper(yxdyglMain, req.getParameterMap());
		if(!getUsername().equals("admin")) {
			queryWrapper.inSql("wgbh", "select menu_id from YXDYGL_PQQXGL t where khjl =" + "'" + getLoginUser().getWorkNo() + "'");
		}
		if (StringUtils.isNotBlank(id)){
			queryWrapper.last(" start with id= '"+id+"' connect by prior id = parent_id  ");
		}
		Page<YxdyglMain> page = new Page<YxdyglMain>(pageNo, pageSize);
		IPage<YxdyglMain> pageList = yxdyglMainService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	/**
	 * 添加
	 *
	 * @param yxdyglMain
	 * @return
	 */
	@AutoLog(value = "营销单元管理-添加")
	@ApiOperation(value="营销单元管理-添加", notes="营销单元管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody YxdyglMain yxdyglMain) {
		YxdyglMain byId = service.getById(yxdyglMain.getWgbh());
		if (byId != null)
			return Result.error("网格编号已存在");
		yxdyglMain.setId(yxdyglMain.getWgbh());
		yxdyglMainService.save(yxdyglMain);
		return Result.ok("添加成功！");
	}
	/**
	 * 编辑
	 *
	 * @param yxdyglMain
	 * @return
	 */
	@AutoLog(value = "营销单元管理-编辑")
	@ApiOperation(value="营销单元管理-编辑", notes="营销单元管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody YxdyglMain yxdyglMain) {
		yxdyglMainService.updateById(yxdyglMain);
		return Result.ok("编辑成功!");
	}
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "营销单元管理-通过id删除")
	@ApiOperation(value="营销单元管理-通过id删除", notes="营销单元管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		LambdaQueryWrapper<YxdyglMain> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(YxdyglMain::getParentId,id);
		List<YxdyglMain> list = service.list(lambdaQueryWrapper);
		if (CollUtil.isNotEmpty(list))
			return Result.error("请先删除子网格!");
		yxdyglMainService.removeById(id);
		return Result.ok("删除成功!");
	}
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "营销单元管理-批量删除")
	@ApiOperation(value="营销单元管理-批量删除", notes="营销单元管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.yxdyglMainService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "营销单元管理-通过id查询")
	@ApiOperation(value="营销单元管理-通过id查询", notes="营销单元管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		YxdyglMain yxdyglMain = yxdyglMainService.getById(id);
		return Result.ok(yxdyglMain);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param yxdyglMain
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, YxdyglMain yxdyglMain) {
	  //不把该条件放到参数中init
	  String id = null;
	  if (StringUtils.isNotBlank(yxdyglMain.getParentId())){
		  id = yxdyglMain.getParentId();
		  yxdyglMain.setParentId(null);
	  }

	  // Step.1 组装查询条件
	  QueryWrapper<YxdyglMain> queryWrapper = QueryGenerator.initQueryWrapper(yxdyglMain, request.getParameterMap());
	  if(!getUsername().equals("admin")) {
		  queryWrapper.inSql("wgbh", "select menu_id from YXDYGL_PQQXGL t where khjl =" + "'" + getLoginUser().getWorkNo() + "'");
	  }
	  if (StringUtils.isNotBlank(id)){
		  queryWrapper.last(" start with id= '"+id+"' connect by prior id = parent_id  ");
	  }
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  String selections = request.getParameter("selections");
	  String rowKey = request.getParameter("rowKey");

	  //20211201 过滤选中数据
	  //20211201 BY LIUXIANGQUN 过滤选择的数据改为执行接sql  in  查询
	  if (oConvertUtils.isNotEmpty(selections)) {
		  List<String> selectionList = Arrays.asList(selections.split(","));
		  if(oConvertUtils.isNotEmpty(rowKey)){
			  queryWrapper.in(rowKey,selectionList);
		  }else{
			  queryWrapper.in("ID",selectionList);
		  }
	  }

	  // Step.2 获取导出数据
	  //List<T> pageList = service.list(queryWrapper);
	  //List<T> exportList = null;
	  List<YxdyglMain> exportList = service.list(queryWrapper);
	  String title = "网格基本信息";
	  // Step.3 AutoPoi 导出Excel
	  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
	  mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
	  mv.addObject(NormalExcelConstants.CLASS, YxdyglMain.class);
	  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
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
  public Result<?> importExcel(@RequestBody JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response) {
	  String filePaths = jsonObject.getString("filePaths");
	  if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
		  return Result.error("请先上传文件！");
	  }
	  String[] filePathList = filePaths.split(",");
	  JSONObject obj = new JSONObject();
	  for (String filePath : filePathList) {
		  String baseFilePath = uploadpath + File.separator + filePath;
//          MultipartFile file = entity.getValue();// 获取上传文件对象
		  File file = new File(baseFilePath);
		  ImportParams params = new ImportParams();
		  params.setTitleRows(1);
		  params.setHeadRows(1);
		  params.setNeedSave(false);
		  params.setVerifyHanlder(yxdyglImpVerify);
		  FileOutputStream fos = null;
		  try {
			  ExcelImportResult<YxdyglMainExp> importResult = ExcelImportUtil.importExcelVerify(file, YxdyglMainExp.class, params);
			  List<YxdyglMainExp> list = importResult.getList();
			  int count = 0;
			  if (CollUtil.isNotEmpty(list)){
				  Map<String, String> zzbzByZzjc = hrBasOrganizationService.getZzbzByZzjc();

				  for (int i = 0; i < list.size(); i++) {
					  YxdyglMainExp yxdyglMainExp = list.get(i);
					  LambdaQueryWrapper<YxdyglMain> lambdaQueryWrapper = new LambdaQueryWrapper<>();
					  lambdaQueryWrapper.eq(YxdyglMain::getWgbh,yxdyglMainExp.getParentId());
					  List<YxdyglMain> list1 = service.list(lambdaQueryWrapper);
					  if (CollUtil.isNotEmpty(list1)){
						  YxdyglMain yxdyglMain = list1.get(0);

						  YxdyglMain insert = new YxdyglMain();
						  insert.setParentId(yxdyglMain.getId());
						  insert.setWgmc(yxdyglMainExp.getWgmc());
						  if (StringUtils.isNotBlank(yxdyglMainExp.getWgbh())){
							  insert.setWgbh(yxdyglMainExp.getWgbh());
							  insert.setId(yxdyglMainExp.getWgbh());
						  }
						  insert.setWgxz(yxdyglMainExp.getWgxz());
						  if (CollUtil.isNotEmpty(zzbzByZzjc) && StringUtils.isNotEmpty(yxdyglMainExp.getZzbz())){
							  String s = zzbzByZzjc.get(yxdyglMainExp.getZzbz());
							  insert.setZzbz(s);
						  }
						  boolean save = service.save(insert);
						  if (save){
							  count++;
						  }
					  }

				  }
			  }
			  obj.put("filePath", filePath);
			  fos = new FileOutputStream(baseFilePath);
			  importResult.getWorkbook().write(fos);
			  fos.flush();
			  fos.close();
			  return Result.ok("文件导入完成！成功导入数据行数:" + count++, obj);
		  } catch (Exception e) {
			  log.error(e.getMessage(),e);
			  return Result.error("文件导入失败:"+e.getMessage());
		  } finally {
			  IoUtil.close(fos);
		  }
	  }
	  return Result.ok("文件导入失败！");
  }

	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
			 // AutoPoi 导出Excel
			 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
			 // 导出文件名称
			 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "网格基本信息");
			 modelAndView.addObject(NormalExcelConstants.CLASS, YxdyglMainExp.class);
			 ExportParams exportParams = new ExportParams("网格基本信息", "模板信息");
			 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
			 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
			 return modelAndView;
	 }


	 @RequestMapping(value = "getYxdyglMaimTreeDate")
	 public Result<?> getYxdyglMaimTreeDate(String khjl, String previousLevel, String pId, String queryOrganize){
		 return Result.ok(service.listTree(khjl, previousLevel, pId, queryOrganize));
	 }

	 @GetMapping(value = "/getYxdyglMainTreeByKhjl")
	 public Result<?> getYxdyglMainTreeByKhjl(String khjl, String pId) {
	 	 if(getUsername().equals("admin")) {
	 	 	return Result.ok(service.listTreeAll(pId));
		 } else {
			 if (StringUtils.isEmpty(khjl)) {
				 khjl = getLoginUser().getWorkNo();
			 }
			 return Result.ok(service.listTreeByKhjl(khjl, pId));
		 }
	 }

	 Map<String,List<YxdyglMain>> map;

	 @GetMapping(value = "/getWgxxTreeDateBySskhjl")
	 public Result<?> getWgxxTreeDateBySskhjl(@RequestParam(name="maxLevel", required = false, defaultValue = "999") Integer maxLevel,
											  String wgxz, String wgxzType, String disableSelect,String minLevel,@RequestParam(name = "zzbz",required = false) String zzbz ){
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 log.info("===当前查询人是{},区域代码是{}===",getWorkNo(),getRedisQydm());
		 if (getRedisQydm().equals(QydmEnums.LIUYANG.getQydmCode())){
			 if (map != null && map.get("wgxxTreeDate") != null){
				 log.info("===返回的缓存数据1===");
				 return Result.ok(map.get("wgxxTreeDate"));
			 }else {
				 map = new HashMap<>();
				 if (map.get("wgxxTreeDate") != null){
					 log.info("===返回的缓存数据2===");
					 return Result.ok(map.get("wgxxTreeDate"));
				 }else {
					 log.info("===直接查网格===");
					 List<YxdyglMain> wgxxTreeDate = service.getWgxxTreeDate(maxLevel, disableSelect, minLevel);
					 map.put("wgxxTreeDate",wgxxTreeDate);
					 return Result.ok(wgxxTreeDate);
				 }
			 }
		 }else {
			 log.info("===非020的网格查询===");
			 List<YxdyglMain> wgxxTreeDateBySskhjl = service.getWgxxTreeDateBySskhjl(sysUser.getWorkNo(), maxLevel, wgxz, wgxzType, disableSelect,minLevel,zzbz);
			 return Result.ok(wgxxTreeDateBySskhjl);
		 }

	 }
	 @GetMapping(value = "/getWgxxTreeDateAll")
	 public Result<?> getWgxxTreeDateAll(@RequestParam(name="maxLevel", required = false, defaultValue = "999") Integer maxLevel,
											  String wgxz, String wgxzType, String disableSelect){
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//		 Object o = redisUtil.get(sysUser.getWorkNo() + ":wgxxTreeDateBySskhjl");
//		 if (o != null)
//			 return Result.ok(o);
		 List<YxdyglMain> wgxxTreeDateBySskhjl = service.getWgxxTreeDateBySskhjl(sysUser.getWorkNo(), maxLevel, wgxz, wgxzType, disableSelect,null,null);
		 //redisUtil.set(sysUser.getWorkNo() + ":wgxxTreeDateBySskhjl",wgxxTreeDateBySskhjl);
		 return Result.ok(wgxxTreeDateBySskhjl);
	 }


	 @GetMapping(value = "/getSswgSszh")
	 public Result<?> getSswgSszh(String sswg){
		 YxdyglMain yxdyglMain = service.getById(sswg);
		 JSONObject jsonObject =new JSONObject();
		 if(yxdyglMain==null||yxdyglMain.getZzbz()==null){
			 jsonObject.put("zzbz","");
			 jsonObject.put("zzjc","");
		 }else{
			 jsonObject.put("zzbz",yxdyglMain.getZzbz());

			 HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(yxdyglMain.getZzbz());
			 jsonObject.put("zzjc",hrBasOrganization.getZzjc());
		 }
		 return Result.ok(jsonObject);
	 }


	 @GetMapping(value = "/getZuByCun")
	 public Result<?> getZuByCun(String wgbh){
		 if (StringUtils.isBlank(wgbh))
			 return Result.error("网格编号不能为空！");
		 LambdaQueryWrapper<YxdyglMain> lambdaQueryWrapper = new LambdaQueryWrapper();
		 lambdaQueryWrapper.eq(YxdyglMain::getParentId,wgbh);
		 List<YxdyglMain> list = service.list(lambdaQueryWrapper);
		 return Result.ok(list);
	 }

	 @GetMapping("/getParentIdByWgbh")
	 public Result<?> getParentIdByWgbh(String wgbh){
		 if (StringUtils.isBlank(wgbh))
			 return Result.error("网格编号不能为空！");
		 LambdaQueryWrapper<YxdyglMain> lambdaQueryWrapper = new LambdaQueryWrapper();
		 lambdaQueryWrapper.in(YxdyglMain::getParentId,wgbh);
		 List<YxdyglMain> list = service.list(lambdaQueryWrapper);
		 return Result.ok(list);
	 }

	 /**
	  * 下拉树查询
	  * 走访验收统计表-行政村 / 获取当前所属支行下辖网格信息
	  * @param yxdyglMain
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "营销单元管理-下拉树查询")
	 @ApiOperation(value="营销单元管理-下拉树查询", notes="营销单元管理-下拉树查询")
	 @GetMapping(value = "/querlist")
	 public Result<?> querlist(YxdyglMain yxdyglMain,
							   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
							   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
							   HttpServletRequest req) {
		 QueryWrapper<YxdyglMain> queryWrapper = QueryGenerator.initQueryWrapper(yxdyglMain, req.getParameterMap());
		 Page<YxdyglMain> page = new Page<YxdyglMain>(pageNo, pageSize);
		 IPage<YxdyglMain> pageList = yxdyglMainService.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }




	 @GetMapping(value = "/download")
	 public void download(HttpServletRequest request, HttpServletResponse response, @RequestParam(name="wgbh") String wgbh) {
		 InputStream inputStream = null;
		 OutputStream outputStream = null;
		 try {
			 Map<String, Object> data = new HashMap<>();
			 QueryWrapper mainQueryWrapper = new QueryWrapper<>();
			 mainQueryWrapper.eq("wgbh", wgbh);
			 YxdyglMain yxdyglMain = yxdyglMainService.getOne(mainQueryWrapper);
			 data.put("yxdyglMain", yxdyglMain);
			 VYxdyglMain vYxdyglMain = vYxdyglMainService.getOne(mainQueryWrapper);
			 String wgmcShow = vYxdyglMain.getWgmcShow();
			 data.put("wgmcShow", wgmcShow);

			 WgxqtjVo wgxqtjVo = (WgxqtjVo)wgxxtjController.queryWgxqByWgbh(wgbh).getResult();
			 if(wgxqtjVo!=null){
				 if(wgxqtjVo.getDkkhs()!=null&&wgxqtjVo.getZhndkhs()!=null){
					 if(wgxqtjVo.getDkkhs()==0||wgxqtjVo.getZhndkhs()==0){
						 wgxqtjVo.setZhndkhszb(BigDecimal.valueOf(0));
					 }else{
						 BigDecimal bigDecimal = BigDecimal.valueOf(wgxqtjVo.getZhndkhs()/wgxqtjVo.getDkkhs()*100);
						 BigDecimal zhndkhszb = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
						 wgxqtjVo.setZhndkhszb(zhndkhszb);
					 }
				 }
				 if(wgxqtjVo.getDkkhs()!=null&&wgxqtjVo.getZhwdkhs()!=null){
					 if(wgxqtjVo.getDkkhs()==0||wgxqtjVo.getZhwdkhs()==0){
						 wgxqtjVo.setZhwdkhszb(BigDecimal.valueOf(0));
					 }else{
						 BigDecimal bigDecimal = BigDecimal.valueOf(wgxqtjVo.getZhwdkhs()/wgxqtjVo.getDkkhs()*100);
						 BigDecimal zhwdkhszb = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
						 wgxqtjVo.setZhwdkhszb(zhwdkhszb);
					 }
				 }
				 if(wgxqtjVo.getCknrp()!=null&&wgxqtjVo.getZhndkye()!=null){
					 if(wgxqtjVo.getCknrp().intValue()==0){
						 wgxqtjVo.setZjghl(BigDecimal.valueOf(0));
					 }else{
						 if(wgxqtjVo.getZhndkye().intValue()==0){
							 wgxqtjVo.setZjghl(BigDecimal.valueOf(100));
						 }else{
							 BigDecimal bigDecimal = BigDecimal.valueOf(wgxqtjVo.getCknrp().doubleValue() / wgxqtjVo.getZhndkye().doubleValue() * 100);
							 BigDecimal zjghl = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
							 wgxqtjVo.setZjghl(zjghl);
						 }
					 }
				 }
				 data.put("wgxq", wgxqtjVo);
			 }else {
				 data.put("wgxq", null);

			 }
			 //获取村情统计信息
			 QueryWrapper<Cqtj> cqtjQueryWrapper = new QueryWrapper<>();
			 cqtjQueryWrapper.eq("xzc", wgbh);
			 cqtjQueryWrapper.orderByDesc("tjyf");
			 List<Cqtj> cqtjList = cqtjService.list(cqtjQueryWrapper);
			 if (!cqtjList.isEmpty()) {
				 Cqtj cqtj = cqtjList.get(0);
				 data.put("cqtj", cqtj);
				 List<String> legendNameList = Arrays.asList("30岁以下", "30岁-50岁", "50岁-60岁", "60岁以上");
				 List<Object> dataList = Arrays.asList(cqtj.getCkrj0z30(), cqtj.getCkrj30z50(), cqtj.getCkrj50z60(), cqtj.getCkrj61());
				 List<Color> legendColorList = Arrays.asList(Color.GRAY, Color.green, Color.cyan, Color.ORANGE);
				 List<Double> explodePercentList = Arrays.asList(0.0, 0.0, 0.0, 0.0);
				 String imageck1 = getPieImageBaseString(legendNameList, dataList, legendColorList, explodePercentList, "存款日均年龄分布");
				 data.put("imageck1", imageck1);

				 List<String> legendNameList2 = Arrays.asList("30岁以下", "30岁-50岁", "50岁-60岁", "60岁以上");
				 List<Object> dataList2 = Arrays.asList(cqtj.getCkkhs0z30(), cqtj.getCkkhs30z50(), cqtj.getCkkhs50z60(), cqtj.getCkkhs61());
				 List<Color> legendColorList2 = Arrays.asList(Color.GRAY, Color.green, Color.cyan, Color.ORANGE);
				 List<Double> explodePercentList2 = Arrays.asList(0.0, 0.0, 0.0, 0.0);
				 String imageck2 = getPieImageBaseString(legendNameList2, dataList2, legendColorList2, explodePercentList2, "存款户数年龄分布");
				 data.put("imageck2", imageck2);
			 }

			 //获取组信息
			 QueryWrapper<YxdyglMain> queryWrapper = new QueryWrapper<>();
			 queryWrapper.eq("parent_id", wgbh);
			 queryWrapper.orderByAsc("wgbh");
			 List<YxdyglMain> sjyxdyglList = yxdyglMainService.list(queryWrapper);
			 data.put("sjyxdysl", sjyxdyglList.size());

			 StringBuffer sjyxdymcCombine = new StringBuffer();
			 for (YxdyglMain sjyxdygl : sjyxdyglList) {
				 String sjyxdymc = sjyxdygl.getWgmc();
				 sjyxdymcCombine.append(sjyxdymc).append("、");
			 }
			 if (sjyxdymcCombine.length() > 0) {
				 sjyxdymcCombine.deleteCharAt(sjyxdymcCombine.length()-1);
			 }
			 data.put("sjyxdymc", sjyxdymcCombine.toString());



			 String fileName = wgmcShow+ "村情分析报告.doc";
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
			  e.printStackTrace();
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


	 public  String getPieImageBaseString(List<String> legendNameList,List<Object> dataList ,List<Color> legendColorList ,List<Double> explodePercentList,String title){

		 try {
			 //写入饼图
			 //List<String> legendNameList = new ArrayList<>(Arrays.asList("30岁以下", "30岁-50岁","50岁-60岁","60岁以上"));
			 //数据列表
			 //List<Object> dataList = new ArrayList<>(Arrays.asList(cqtj.getCkrj0z30(), cqtj.getCkrj30z50(),cqtj.getCkrj50z60(), cqtj.getCkrj61()));
			 //图例背景颜色列表
			 //List<Color> legendColorList = new ArrayList<>(Arrays.asList(Color.GRAY, Color.green, Color.cyan, Color.ORANGE));
			 //偏离百分比数据
			 //List<Double> explodePercentList = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.0));
			 JFreeChart chart = GeneratePieChartUtil.createPieChart(title, legendNameList, dataList
					 , JFreeChartUtil.createChartTheme("宋体"), legendColorList, explodePercentList);
			 //在D盘目录下生成图片
			 File p = new File(uploadpath+"/wordImage");
			 if (!p.exists()) {
				 p.mkdirs();
			 }
			 String imageName = "饼图1.jpeg";
			 File file = new File(p.getPath() + "/" + imageName);
			 try {
				 if(file.exists()) {
					 file.delete();
				 }
				 ChartUtils.saveChartAsJPEG(file, chart, 400, 300);
				 String imageBase64String = RandImageUtil.getImageBase64String(file.getPath());
				 return imageBase64String;
			 } catch (IOException e) {
				 e.printStackTrace();
			 }

		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 return "";
	 }
	 public static void main(String[] args) {
		 BigDecimal bigDecimal = BigDecimal.valueOf(12000.22 / 333.33 * 100);
		 BigDecimal bd1 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		 System.out.println(bd1);

	 }

	 @RequestMapping("/resetMap")
	 public void resetMap(){
		 log.info("===清除020 map缓存开始 目前有{}条缓存===",map.size());
		 this.map = new HashMap<>();
		 log.info("===清除020 map缓存结束 目前有{}条缓存===",map.size());
	 }
 }

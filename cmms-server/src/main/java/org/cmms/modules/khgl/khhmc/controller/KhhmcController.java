package org.cmms.modules.khgl.khhmc.controller;

import java.io.*;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.IdcardUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.IdentConstant;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.grkhgl.entity.vKhglKrkhgl;
import org.cmms.modules.khgl.grkhgl.service.IvKhglKrkhglService;
import org.cmms.modules.khgl.khhmc.entity.Khhmc;
import org.cmms.modules.khgl.khhmc.entity.KhhmcImport;
import org.cmms.modules.khgl.khhmc.entity.VKhglKhhmc;
import org.cmms.modules.khgl.khhmc.service.IKhhmcService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.cmms.modules.khgl.khhmc.service.IVKhglKhhmcService;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzxxgl;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhhzxxglService;
import org.cmms.modules.system.controller.IdentEncryptionController;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.yxdygl.czxxgl.entity.Yxdygl_czxxgl;
import org.cmms.modules.yxdygl.czxxgl.service.IYxdygl_czxxglService;
import org.cmms.modules.yxdygl.ejyxdygl.entity.Ejyxdygl;
import org.cmms.modules.yxdygl.ejyxdygl.service.IEjyxdyglService;
import org.cmms.modules.tjfx.jcsjgl.cssz.service.ITjfxCsszService;
import org.cmms.modules.yxdygl.sjyxdygl.entity.Sjyxdygl;
import org.cmms.modules.yxdygl.sjyxdygl.service.ISjyxdyglService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 客户花名册
 * @Author: jeecg-boot
 * @Date:   2019-09-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户花名册")
@RestController
@RequestMapping("/khgl/khhmc")
public class KhhmcController  extends JeecgController<VKhglKhhmc, IVKhglKhhmcService> {
	@Autowired
	private IKhhmcService khhmcService;
	@Autowired
	private IYxdygl_czxxglService czxxglService;
	@Autowired
	private IEjyxdyglService iEjyxdyglService;
	@Autowired
	private ISjyxdyglService iSjyxdyglService;
	@Autowired
	private IHrBasOrganizationService hrBasOrganizationService;
	@Autowired
	private IVKhglKhhmcService vKhglKhhmcService;
	@Autowired
	private ITjfxCsszService iTjfxcsszService;
	@Value(value = "${common.path.upload}")
	private String uploadpath;
	@Autowired
	private ISysDictService sysDictService;
	@Autowired
	private IKhglNhhzxxglService khglNhhzxxglService;

	/**
	  * 分页列表查询
	 * @param vKhglKhhmc
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户花名册-分页列表查询")
	@ApiOperation(value="客户花名册-分页列表查询", notes="客户花名册-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VKhglKhhmc vKhglKhhmc,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		Result<IPage<VKhglKhhmc>> result = new Result	<IPage<VKhglKhhmc>>();
		QueryWrapper<VKhglKhhmc> queryWrapper = QueryGenerator.initQueryWrapper(vKhglKhhmc, req.getParameterMap());
		String cmc = req.getParameter("cmc");
		if (!StringUtils.isEmpty(cmc)) {
			List<Yxdygl_czxxgl> czxxglList = czxxglService.queryByCmc("%" + cmc + "%");
			if (czxxglList.isEmpty()) {
				result.setSuccess(true);
				Page<VKhglKhhmc> page = new Page<VKhglKhhmc>(pageNo, pageSize);
				page.setRecords(new ArrayList<VKhglKhhmc>());
				page.setTotal(0);
				result.setResult(page);
				return result;
			}
			List<String> qybmList = new ArrayList<String>();
			for (Yxdygl_czxxgl czxxgl : czxxglList) {
				qybmList.add(czxxgl.getQybm());
			}
			queryWrapper.in("ssyxdy", qybmList);
		}
		Page<VKhglKhhmc> page = new Page<VKhglKhhmc>(pageNo, pageSize);
		IPage<VKhglKhhmc> pageList = vKhglKhhmcService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setCode(200);
		result.setResult(pageList);
		return result;
	}


	/**
	  *   添加
	 * @param vKhglKhhmc
	 * @return
	 */
	@AutoLog(value = "客户花名册-添加")
	@ApiOperation(value="客户花名册-添加", notes="客户花名册-添加")
	@PostMapping(value = "/add")
	public Result<Khhmc> add(@RequestBody VKhglKhhmc vKhglKhhmc) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		Khhmc khhmc = new Khhmc();
		BeanUtils.copyProperties(vKhglKhhmc, khhmc);
		Result<Khhmc> result = new Result<Khhmc>();
		try {
			Khhmc khhmcExist = khhmcService.queryByZjhm(khhmc.getZjhm());
			if (khhmcExist != null) {
				return result.error500("已经存在的证件号码，不能重复添加！");
			}
			khhmcService.save(khhmc);
			//如果添加户主，同步到户主表中
			if ("1".equalsIgnoreCase(khhmc.getYhzgx())) {
				QueryWrapper<Khhmc> khhmcQueryWrapper = new QueryWrapper<>();
				khhmcQueryWrapper.eq("hhbm", khhmc.getHhbm());
				khhmcQueryWrapper.eq("yhzgx", "1");
				khhmcQueryWrapper.ne("zjhm", khhmc.getZjhm());
				List<Khhmc> khhmcList = khhmcService.list(khhmcQueryWrapper);

				if (!khhmcList.isEmpty()) {
					//存在户主
					return result.error500("本户已经存在户主(" + khhmcList.get(0).getKhmc() + ")，不能再修改为户主");
				} else {
					KhglNhhzxxgl khglNhhzxxgl = new KhglNhhzxxgl();
					khglNhhzxxgl.setSszh(khhmc.getSszh());
					khglNhhzxxgl.setSsyxdy(khhmc.getSsyxdy());
					khglNhhzxxgl.setHhbm(khhmc.getHhbm());
					khglNhhzxxgl.setHzxm(khhmc.getKhmc());
					khglNhhzxxgl.setKhlx(khhmc.getKhlx());
					khglNhhzxxgl.setHzzjhm(khhmc.getZjhm());
					khglNhhzxxgl.setLrr(sysUser.getUsername());
					khglNhhzxxglService.save(khglNhhzxxgl);
				}
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
	 * @param vKhglKhhmc
	 * @return
	 */
	@AutoLog(value = "客户花名册-编辑")
	@ApiOperation(value="客户花名册-编辑", notes="客户花名册-编辑")
	@PutMapping(value = "/edit")
	public Result<Khhmc> edit(@RequestBody VKhglKhhmc vKhglKhhmc) {
		Result<Khhmc> result = new Result<Khhmc>();
		Khhmc khhmc = new Khhmc();
		BeanUtils.copyProperties(vKhglKhhmc, khhmc);
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		khhmc.setXgr(sysUser.getUsername());
		khhmc.setLrbz("2");
		khhmc.setXgsj(new Date());
		Khhmc khhmcEntity = khhmcService.getById(khhmc.getId());
		if(khhmcEntity==null) {
			result.error500("未找到对应实体");
		}else {
			//如果修改为户主，判断是否已经存在户主
			if ("1".equalsIgnoreCase(khhmc.getYhzgx())) {
				QueryWrapper<Khhmc> khhmcQueryWrapper = new QueryWrapper<>();
				khhmcQueryWrapper.eq("hhbm", khhmc.getHhbm());
				khhmcQueryWrapper.eq("yhzgx", "1");
				khhmcQueryWrapper.ne("zjhm", khhmc.getZjhm());
				List<Khhmc> khhmcList = khhmcService.list(khhmcQueryWrapper);

				if (!khhmcList.isEmpty()) {
					//存在户主
					return result.error500("本户已经存在户主(" + khhmcList.get(0).getKhmc() + ")，不能再修改为户主");
				} else {
					//不存在户主
					//将信息存入户主信息表
					KhglNhhzxxgl khglNhhzxxgl = new KhglNhhzxxgl();
					khglNhhzxxgl.setSszh(khhmc.getSszh());
					khglNhhzxxgl.setSsyxdy(khhmc.getSsyxdy());
					khglNhhzxxgl.setHhbm(khhmc.getHhbm());
					khglNhhzxxgl.setHzxm(khhmc.getKhmc());
					khglNhhzxxgl.setKhlx(khhmc.getKhlx());
					khglNhhzxxgl.setHzzjhm(khhmc.getZjhm());
					khglNhhzxxgl.setLrr(sysUser.getUsername());
					khglNhhzxxglService.save(khglNhhzxxgl);
				}
			}
			boolean ok = khhmcService.updateById(khhmc);
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
	@AutoLog(value = "客户花名册-通过id删除")
	@ApiOperation(value="客户花名册-通过id删除", notes="客户花名册-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			khhmcService.removeById(id);
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
	@AutoLog(value = "客户花名册-批量删除")
	@ApiOperation(value="客户花名册-批量删除", notes="客户花名册-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<Khhmc> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Khhmc> result = new Result<Khhmc>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.khhmcService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户花名册-通过id查询")
	@ApiOperation(value="客户花名册-通过id查询", notes="客户花名册-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Khhmc> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Khhmc> result = new Result<Khhmc>();
		Khhmc khhmc = khhmcService.getById(id);
		if(khhmc==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(khhmc);
			result.setSuccess(true);
		}
		return result;
	}

  /**
      * 导出excel
   *
   * @param request
   */
 /* @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<VKhglKhhmc> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
			  VKhglKhhmc khhmc = JSON.parseObject(deString, VKhglKhhmc.class);
              queryWrapper = QueryGenerator.initQueryWrapper(khhmc, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
	  ModelAndView   mv = new ModelAndView(new IdentEncryptionController());
      List<VKhglKhhmc> pageList = vKhglKhhmcService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "客户花名册列表");
      mv.addObject(NormalExcelConstants.CLASS, VKhglKhhmc.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户花名册列表数据", "导出人:Jeecg", "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
  }*/

	 /**
	  * 导出excel
	  *
	  * @param request
	  */
	 @RequestMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, VKhglKhhmc vKhglKhhmc) {
		 return super.exportXls(request, vKhglKhhmc, VKhglKhhmc.class, "客户花名册导出");
	 }

	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "客户花名册导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, KhhmcImport.class);
		 ExportParams exportParams = new ExportParams("客户花名册导入模板", "模板信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<KhhmcImport>());
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
  public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
	  String filePaths = jsonObject.getString("filePaths");
	  if (StringUtils.isEmpty(filePaths)) {
		  return Result.error("请先上传文件！");
	  }
	  String[] filePathList = filePaths.split(",");
	  JSONObject obj = new JSONObject();
	  for (String filePath : filePathList) {
		  String baseFilePath = uploadpath + File.separator + filePath;
		  File file = new File(baseFilePath);
		  ImportParams params = new ImportParams();
		  params.setTitleRows(1);
		  params.setHeadRows(1);
		  params.setNeedSave(true);

		  InputStream fis = null;
		  Workbook workbook = null;
		  HSSFWorkbook newBook = null;
		  try {
		  	  log.info("开始导入：" + DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss"));
			  List<Khhmc> listKhhmcs = ExcelImportUtil.importExcel(file, Khhmc.class, params);
			  log.info("导入完成：" + DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss"));
			  List<String> ids = new ArrayList<String>();
			  List<Khhmc> insertList = new ArrayList<Khhmc>();
			  fis = new FileInputStream(baseFilePath);

			  newBook = new HSSFWorkbook(new POIFSFileSystem(fis));
			  HSSFSheet sheet = newBook.getSheetAt(0);
			  HSSFRow hssfRow = null;
			  int rCi = 0, rCii = 0;
			  int i = 2;
			  Map<String, String> insertMap = new HashMap<String, String>();
			  for (Khhmc khhmc : listKhhmcs) {
				  hssfRow = sheet.getRow(i++);
				  if (rCi == 0) {
					  rCi = hssfRow.getLastCellNum();
					  rCii = rCi + 1;
				  }
				  HSSFCell resultCell = hssfRow.getCell(rCi);
				  if (resultCell == null) resultCell = hssfRow.createCell(rCi);
				  HSSFCell resultCellInfo = hssfRow.getCell(rCii);
				  if (resultCellInfo == null) resultCellInfo = hssfRow.createCell(rCii);

				  String result = "导入成功";
				  String resultInfo = "";
				  //校验证件号码是否符合规则
				  if (StringUtils.isEmpty(khhmc.getZjhm())) {
				  	 result = "导入失败";
				  	 resultInfo = "证件号码不能为空！";
				  	 resultCell.setCellValue(result);
				  	 resultCellInfo.setCellValue(resultInfo);
				  	 continue;
				  }
				  if (!IdcardUtil.isValidCard(khhmc.getZjhm())) {
				  	 result = "导入失败";
				  	 resultInfo = "证件号码不正确！";
				  	 resultCell.setCellValue(result);
				  	 resultCellInfo.setCellValue(resultInfo);
				  	 continue;
				  }
				  log.info("校验区域编码是否存在：" + DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss"));
				  //校验区域编码是否存在
				  String csValue = "";
				  String qybm = "";
				  boolean flag = true;
				  csValue = iTjfxcsszService.queryCszByCsbm("CS0004");
				  if ("1".equals(csValue)) {
					  flag = true;
				  } else {
					  flag = false;
				  }
				  if (flag) {
					  qybm = iSjyxdyglService.queryDybhBySsyxdy(khhmc.getSsyxdy());
				  } else {
					  qybm = iEjyxdyglService.queryDybhBySsyxdy(khhmc.getSsyxdy());
				  }
				  //Yxdygl_czxxgl czxxgl = czxxglService.queryByQybm(khhmc.getSsyxdy());
				  if (qybm == null) {
					  result = "导入失败";
					  resultInfo = "区域编码不正确！";
					  resultCell.setCellValue(result);
					  resultCellInfo.setCellValue(resultInfo);
					  continue;
				  }
				  log.info("校验机构代码是否正确：" + DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss"));
				  //校验机构代码是否正确
				  HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByYwjgdm(khhmc.getJgdm());
				  if (hrBasOrganization == null) {
					  result = "导入失败";
					  resultInfo = "机构代码不正确！";
					  resultCell.setCellValue(result);
					  resultCellInfo.setCellValue(resultInfo);
					  continue;
				  } else {
					  khhmc.setSszh(hrBasOrganization.getZzbz());
				  }
				  log.info("校验证件号码是否存在重复的记录：" + DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss"));
				  //判断数据库是否存在重复的记录
				  Khhmc zjhmExist = khhmcService.queryByZjhm(khhmc.getZjhm());
				  if (zjhmExist != null) {
					  if (StringUtils.isEmpty(khhmc.getBz()) || "管理中".equalsIgnoreCase(zjhmExist.getBz())) {
						  result = "导入失败";
						  resultInfo = "已经存在的证件号码！";
						  resultCell.setCellValue(result);
						  resultCellInfo.setCellValue(resultInfo);
						  continue;
					  }
					  if ("管理中".equalsIgnoreCase(khhmc.getBz())) {
						  ids.add(zjhmExist.getId());
					  }
				  }
				  //判断Excel中是否存在重复的记录
				  log.info("判断Excel中是否存在重复的记录：" + DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss"));
				  if (insertMap.containsKey(khhmc.getZjhm())) {
					  if (StringUtils.isEmpty(khhmc.getBz()) || "管理中".equalsIgnoreCase(insertMap.get(khhmc.getZjhm()))) {
						  result = "导入失败";
						  resultInfo = "已经存在的证件号码！";
						  resultCell.setCellValue(result);
						  resultCellInfo.setCellValue(resultInfo);
						  continue;
					  }
					  if ("管理中".equalsIgnoreCase(khhmc.getBz())) {
						  insertMap.remove(khhmc.getZjhm());
						  insertList.removeIf(khhmc1 -> khhmc1.getZjhm().equalsIgnoreCase(khhmc.getZjhm()));
					  }
				  }
				  resultCell.setCellValue(result);
				  resultCellInfo.setCellValue(resultInfo);
				  insertList.add(khhmc);
				  insertMap.put(khhmc.getZjhm(), khhmc.getBz());
				  log.info("校验完成：" + DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss"));
//				  Khhmc khhmcExist = khhmcService.queryByZjhmAndHhbm(khhmc.getZjhm(), khhmc.getHhbm());
//				  if (khhmcExist != null) {
//					  ids.add(khhmcExist.getId());
//				  }

				  //如果存在证件号码与户号编码都一致的数据，则删掉已经存在的数据，以导入为准
				  //如果存在证件号码一致但是户号编码不一致的数据，则提示错误

			  }
			  obj.put("filePath", filePath);
			  log.info("删除已经存在的数据：" + DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss"));
			  //先删除已经存在的数据
			  if (!ids.isEmpty()) {
				  khhmcService.removeByIds(ids);
			  }
			  log.info("保存数据：" + DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss"));
			  FileOutputStream fos = new FileOutputStream(baseFilePath);
			  newBook.write(fos);
			  fos.flush();
			  fos.close();

			  khhmcService.saveBatch(insertList);
			  log.info("保存数据完成：" + DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss"));
			  //提取数据
			  //khhmcService.initKhhmcxx();

			  return Result.ok("文件导入成功！数据行数:" + listKhhmcs.size() + "，导入成功行数：" + insertList.size() + "，失败行数：" + (listKhhmcs.size()-insertList.size()), obj);
		  } catch (Exception e) {
			  log.error(e.getMessage(),e);
			  return Result.error("文件导入失败:"+e.getMessage());
		  } finally {
			  if (fis != null) {
				  try {
					  fis.close();
				  } catch (Throwable ignored) {
				  }
			  }
		  }
	  }
//      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
//          MultipartFile file = entity.getValue();// 获取上传文件对象
//          ImportParams params = new ImportParams();
//          params.setTitleRows(2);
//          params.setHeadRows(1);
//          params.setNeedSave(true);
//          try {
//              List<Khhmc> listKhhmcs = ExcelImportUtil.importExcel(file.getInputStream(), Khhmc.class, params);
//              khhmcService.saveBatch(listKhhmcs);
//              return Result.ok("文件导入成功！数据行数:" + listKhhmcs.size());
//          } catch (Exception e) {
//              log.error(e.getMessage(),e);
//              return Result.error("文件导入失败:"+e.getMessage());
//          } finally {
//              try {
//                  file.getInputStream().close();
//              } catch (IOException e) {
//                  e.printStackTrace();
//              }
//          }
//      }
      return Result.ok("文件导入失败！");
  }

	 /**
	  * 提取花名册客户信息
	  * @return
	  */
	 @AutoLog(value = "花名册客户信息-提取")
	 @ApiOperation(value="花名册客户信息-提取", notes="花名册客户信息-提取")
	 @GetMapping(value = "/init")
	 public Result<Khhmc> queryById(HttpServletRequest request, HttpServletResponse response) {
		 Result<Khhmc> result = new Result<Khhmc>();
		 khhmcService.initKhhmcxx();
		 result.setSuccess(true);
		 return result;
	 }

}

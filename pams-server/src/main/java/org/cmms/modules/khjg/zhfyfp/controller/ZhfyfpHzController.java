package org.cmms.modules.khjg.zhfyfp.controller;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.util.LangUtil;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPostZhzgry;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.hr.zzgl.zzgwgl.service.IHrBasOrganPostService;
import org.cmms.modules.khjg.zhfyfp.entity.ZhfyfpFytq;
import org.cmms.modules.khjg.zhfyfp.entity.ZhfyfpHz;
import org.cmms.modules.khjg.zhfyfp.entity.ZhfyfpHzVO;
import org.cmms.modules.khjg.zhfyfp.entity.ZhfyfpMx;
import org.cmms.modules.khjg.zhfyfp.service.IZhfyfpFytqService;
import org.cmms.modules.khjg.zhfyfp.service.IZhfyfpHzService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khjg.zhfyfp.service.IZhfyfpMxService;
import org.cmms.modules.khjg.zhfyfp.verify.ZhfyfpHzImportVerify;
import org.cmms.modules.khlc.khfagl.entity.ErpAssessPhjfk;
import org.cmms.modules.khlc.sjjg.entity.PmaADataExeLog;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 支行费用分配
 * @Author: jeecg-boot
 * @Date:   2023-04-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行费用分配")
@RestController
@RequestMapping("/zhfyfp/zhfyfpHz")
public class ZhfyfpHzController extends JeecgController<ZhfyfpHz, IZhfyfpHzService> {
	@Autowired
	private IZhfyfpHzService zhfyfpHzService;

	@Autowired
	private ZhfyfpHzImportVerify zhfyfpHzImportVerify;

	 @Autowired
	 IHrBasStaffPostService hrBasStaffPostService;
	 @Autowired
	 IZhfyfpMxService zhfyfpMxService;

	 @Autowired
	 private IZhfyfpFytqService zhfyfpFytqService;

	 /**
	 * 分页列表查询
	 *
	 * @param zhfyfpHz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行费用分配-分页列表查询")
	@ApiOperation(value="支行费用分配-分页列表查询", notes="支行费用分配-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZhfyfpHz zhfyfpHz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZhfyfpHz> queryWrapper = QueryGenerator.initQueryWrapper(zhfyfpHz, req.getParameterMap());
		Page<ZhfyfpHz> page = new Page<ZhfyfpHz>(pageNo, pageSize);
		IPage<ZhfyfpHz> pageList = zhfyfpHzService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "支行费用分配-添加")
	@ApiOperation(value="支行费用分配-添加", notes="支行费用分配-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody JSONObject jsonObject) {
		JSONArray jsonArray = jsonObject.getJSONArray("list");
		String js = JSONObject.toJSONString(jsonArray);
		String yfpje = jsonObject.getString("yfpje");
		String wfpje = jsonObject.getString("wfpje");
		String zzbz = jsonObject.getString("zzbz");
		String fylx = jsonObject.getString("fylx");
		String fpyf = jsonObject.getString("fpyf");
		DateTime parse = DateUtil.parse(fpyf);
		QueryWrapper<ZhfyfpHz> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("fpyf",parse);
		queryWrapper.eq("zzbz",zzbz);
		queryWrapper.eq("fylx",Integer.valueOf(fylx));
		ZhfyfpHz check = zhfyfpHzService.getOne(queryWrapper);
		ZhfyfpHz form = new ZhfyfpHz();
		form.setYfpje(new BigDecimal(Integer.valueOf(yfpje)));
		form.setDfpje(new BigDecimal(Integer.valueOf(wfpje)));
		form.setFpzt(1);
		form.setTjzt(0);
		form.setUpdateBy(getUsername());
		form.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		if (check != null){
			zhfyfpHzService.update(form,queryWrapper);
		}

		ZhfyfpMx settingTable = new ZhfyfpMx();
		List<ZhfyfpMx> zhfyfpMxes = JSONObject.parseArray(js, ZhfyfpMx.class);
		System.out.println(zhfyfpMxes+"111111111111111");
		QueryWrapper<ZhfyfpMx> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.eq("fpyf",parse);
		queryWrapper1.eq("fylx",Integer.valueOf(fylx));
		queryWrapper1.eq("zzbz",zzbz);
		zhfyfpMxService.remove(queryWrapper1);
		for (ZhfyfpMx mx : zhfyfpMxes) {
			System.out.println(mx);
			settingTable.setFpyf(parse);
			settingTable.setZzbz(mx.getZzbz());
			if (mx.getJe() == null) {
				mx.setJe(new BigDecimal(0));
			}
			settingTable.setId(IdUtil.simpleUUID());
			settingTable.setFylx(Integer.valueOf(fylx));
			settingTable.setGwbz(mx.getGwbz());
			settingTable.setYggh(mx.getYggh());
			settingTable.setJe(mx.getJe());
			settingTable.setBz(mx.getBz());
			settingTable.setCreateBy(getUsername());
			settingTable.setCreateTime(new Timestamp(System.currentTimeMillis()));
			zhfyfpMxService.save(settingTable);
		}
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zhfyfpHz
	 * @return
	 */
	@AutoLog(value = "支行费用分配-分配")
	@ApiOperation(value="支行费用分配-分配", notes="支行费用分配-分配")
	@PostMapping(value = "/set")
	public Result<?> set(@RequestBody ZhfyfpHz zhfyfpHz) {
		QueryWrapper<ZhfyfpMx> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("fpyf",zhfyfpHz.getFpyf());
		queryWrapper.eq("fylx",zhfyfpHz.getFylx());
		queryWrapper.eq("zzbz",zhfyfpHz.getZzbz());
		List<ZhfyfpMx> list = zhfyfpMxService.list();
		if (CollUtil.isNotEmpty(list)){
			List<HrBasStaffPostZhzgry> zhzgry =zhfyfpMxService.getZhfpry(zhfyfpHz.getFpyf(),zhfyfpHz.getFylx(),zhfyfpHz.getZzbz());
			return Result.ok(zhzgry);
		}else {
			List<HrBasStaffPostZhzgry> zhzgry = hrBasStaffPostService.getZhzgry(DateUtil.format(zhfyfpHz.getFpyf(),"yyyyMMdd"), zhfyfpHz.getZzbz());
			return Result.ok(zhzgry);
		}
	}


	 /**
	  * 编辑
	  *
	  * @param zhfyfpHz
	  * @return
	  */
	 @AutoLog(value = "支行费用分配-编辑")
	 @ApiOperation(value="支行费用分配-编辑", notes="支行费用分配-编辑")
	 @PutMapping(value = "/edit")
	 public Result<?> edit(@RequestBody ZhfyfpHz zhfyfpHz) {
		 zhfyfpHzService.updateById(zhfyfpHz);
		 return Result.ok("编辑成功!");
	 }


	 /**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行费用分配-通过id删除")
	@ApiOperation(value="支行费用分配-通过id删除", notes="支行费用分配-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zhfyfpHzService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "支行费用分配-批量删除")
	@ApiOperation(value="支行费用分配-批量删除", notes="支行费用分配-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zhfyfpHzService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行费用分配-通过id查询")
	@ApiOperation(value="支行费用分配-通过id查询", notes="支行费用分配-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZhfyfpHz zhfyfpHz = zhfyfpHzService.getById(id);
		return Result.ok(zhfyfpHz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zhfyfpHz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ZhfyfpHz zhfyfpHz) {
      return super.exportXls(request, zhfyfpHz, ZhfyfpHz.class, "支行费用分配");
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
			 if (zhfyfpHzImportVerify != null) {
				 params.setVerifyHanlder(zhfyfpHzImportVerify);
			 }
			 FileOutputStream fos = null;
			 FileInputStream fis = null;
			 try {
				 fis = new FileInputStream(file);
				 boolean checkResult = ExcelImportCheckUtil.check(fis, ZhfyfpHzVO.class, params);
				 ExcelImportResult<ZhfyfpHz> importResult = ExcelImportUtil.importExcelVerify(file, ZhfyfpHz.class,ZhfyfpHzVO.class, params);
				 List<ZhfyfpHz> list = importResult.getList();
				 service.saveBatch(list);
				 obj.put("filePath", filePath);
				 fos = new FileOutputStream(baseFilePath);
				 importResult.getWorkbook().write(fos);
				 fos.flush();
				 fos.close();
				 return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
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
	  * 导出模板
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "支行分配导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, ZhfyfpHzVO.class);
		 ExportParams exportParams = new ExportParams("支行分配导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
	 }

	 /**
	  * 提取绩效数据
	  * @param jsonObject
	  * @return
	  */
	 @RequestMapping(value = "/extract", method = RequestMethod.PUT)
	 public Result<?> extract(@RequestBody JSONObject jsonObject) {
		 String fpyf = jsonObject.getString("fpyf").replace("-","");
		 String fylx = jsonObject.getString("fylx");

		 QueryWrapper queryWrapper=new QueryWrapper();
		 queryWrapper.eq("fylx",fylx);
		 ZhfyfpFytq fytq = zhfyfpFytqService.getOne(queryWrapper);
		 if(fytq!=null&&!"".equals(fytq.getJssql())){
			 String jssql = fytq.getJssql();
			 String sql = jssql;

			 if (jssql.contains("${fpyf}")) {
				 sql= LangUtil.replace(sql, "${fpyf}", fpyf);
			 }

			 if (jssql.contains("${fylx}")) {
				 sql= LangUtil.replace(sql, "${fylx}", fylx);
			 }
			 zhfyfpHzService.extract(sql,fpyf,fylx);
		 }else{
			 Result.error("改类型的费用暂未配置提取脚本");
		 }


	 	return Result.ok("提取成功！");
	 }
}

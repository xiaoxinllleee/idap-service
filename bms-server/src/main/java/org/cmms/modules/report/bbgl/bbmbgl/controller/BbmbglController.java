package org.cmms.modules.report.bbgl.bbmbgl.controller;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExlsReport;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.ExcelUtils;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.bbgl.bbmbgl.entity.Bbmbgl;
import org.cmms.modules.report.bbgl.bbmbgl.service.IBbmbglService;
import org.cmms.modules.report.bbgl.bbmbglls.entity.BbmbglLs;
import org.cmms.modules.report.bbgl.bbmbglls.service.IBbmbglLsService;
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
 * @Description: 报表模板管理
 * @Author: jeecg-boot
 * @Date:   2022-03-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="报表模板管理")
@RestController
@RequestMapping("/bbgl/bbmbgl")
public class BbmbglController extends JeecgController<Bbmbgl, IBbmbglService> {
	@Autowired
	private IBbmbglService bbmbglService;
	@Autowired
	private IBbmbglLsService bbmbglLsService;
	 @Autowired
	 private ExlsReport exlsReport;
	 @Value(value = "${common.path.upload}")
	 private String uploadpath;
	
	/**
	 * 分页列表查询
	 *
	 * @param bbmbgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "报表模板管理-分页列表查询")
	@ApiOperation(value="报表模板管理-分页列表查询", notes="报表模板管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Bbmbgl bbmbgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Bbmbgl> queryWrapper = QueryGenerator.initQueryWrapper(bbmbgl, req.getParameterMap());
		Page<Bbmbgl> page = new Page<Bbmbgl>(pageNo, pageSize);
		IPage<Bbmbgl> pageList = bbmbglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param bbmbgl
	 * @return
	 */
	@AutoLog(value = "报表模板管理-添加")
	@ApiOperation(value="报表模板管理-添加", notes="报表模板管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Bbmbgl bbmbgl) {
		QueryWrapper<Bbmbgl> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("bbbh", bbmbgl.getBbbh());
		Bbmbgl bbmbglExist = bbmbglService.getOne(queryWrapper);
		if (bbmbglExist != null) {
			return Result.error("报表编号已经存在，请确认");
		}
		bbmbgl.setBbh("1");
		bbmbglService.save(bbmbgl);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param bbmbgl
	 * @return
	 */
	@AutoLog(value = "报表模板管理-编辑")
	@ApiOperation(value="报表模板管理-编辑", notes="报表模板管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Bbmbgl bbmbgl) {
		//如果重新上传文件，则更新版本号
		if (StringUtils.isNotEmpty(bbmbgl.getBblj())) {
			QueryWrapper<Bbmbgl> bbmbglQueryWrapper = new QueryWrapper<>();
			bbmbglQueryWrapper.eq("bbbh", bbmbgl.getBbbh());
			Bbmbgl bbmbglHistory = bbmbglService.getOne(bbmbglQueryWrapper);
			if (bbmbgl == null) {
				return Result.error("报表模板数据不存在");
			}
			//将现在的存入历史表
			BbmbglLs bbmbglLs = new BbmbglLs();
			BeanUtils.copyProperties(bbmbglHistory, bbmbglLs);
			bbmbglLs.setId(IdUtil.simpleUUID());
			bbmbglLs.setCreateBy(getUsername());
			bbmbglLs.setCreateTime(new Date());
			bbmbglLsService.save(bbmbglLs);
			int bbh = Integer.parseInt(bbmbgl.getBbh());
			bbmbgl.setBbh(bbh + 1 + "");
		}
		bbmbglService.updateById(bbmbgl);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "报表模板管理-通过id删除")
	@ApiOperation(value="报表模板管理-通过id删除", notes="报表模板管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		bbmbglService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "报表模板管理-批量删除")
	@ApiOperation(value="报表模板管理-批量删除", notes="报表模板管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bbmbglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "报表模板管理-通过id查询")
	@ApiOperation(value="报表模板管理-通过id查询", notes="报表模板管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Bbmbgl bbmbgl = bbmbglService.getById(id);
		return Result.ok(bbmbgl);
	}

	 /**
	  * 根据报表ID获取模板文件HTML
	  * @return
	  */
	 @GetMapping("/getReportHtml")
	 public Result<JSONObject> getReportHtml(@RequestParam(name="id") String id) {
		 Bbmbgl bbmbgl = bbmbglService.getById(id);
		 String filePath = uploadpath + File.separator + bbmbgl.getBblj();
		 Result<JSONObject> result = new Result<>();
		 JSONObject jsonObject = new JSONObject();
		 jsonObject.put("reportHtml", exlsReport.loadReport(filePath, bbmbgl.getRnum() == null ? 0 : bbmbgl.getRnum(), bbmbgl.getCnum() == null ? 0 : bbmbgl.getCnum()));
		 result.setSuccess(true);
		 result.setResult(jsonObject);
		 return result;
	 }

	 @PostMapping("/editCell")
	 public Result<?> editCell(@RequestBody JSONObject jsonObject) {
		 InputStream inputStream = null;
		 Workbook workbook = null;
		 try {
			 //获取修改列表，将改动同步到excel文件中
			 String bblj = jsonObject.getString("bblj");
			 String wjmc = jsonObject.getString("wjmc");
			 String bbh = jsonObject.getString("bbh");
			 String fullPath = uploadpath + File.separator + bblj;
			 inputStream = new FileInputStream(fullPath);
			 workbook = WorkbookFactory.create(inputStream);
			 Sheet sheet = workbook.getSheetAt(0);
//			 Sheet sheet = ExcelUtils.getExcelSheet(inputStream, wjmc);

			 JSONArray cellEditInfoArray = jsonObject.getJSONArray("cellEditInfo");
			 if (!cellEditInfoArray.isEmpty()) {
			 	 //将源文件备份
				 String bakFilePath =  fullPath.substring(0, fullPath.lastIndexOf(".")) + "_" + bbh + fullPath.substring(fullPath.indexOf("."));
				 FileUtil.copy(fullPath, bakFilePath, true);

				 for (int i = 0; i < cellEditInfoArray.size(); i++) {
					 JSONObject cellEditInfo = cellEditInfoArray.getJSONObject(i);
					 String cell = cellEditInfo.getString("cell");
					 String qsgz = cellEditInfo.getString("qsgz");
					 Cell excelCell = sheet.getRow(ExcelUtils.getCellRow(cell)).getCell(ExcelUtils.getCellCol(cell));
					 excelCell.setCellValue(qsgz);
				 }

				 FileOutputStream fos = new FileOutputStream(fullPath);
				 workbook.write(fos);
				 fos.flush();
				 fos.close();
			 }

		 } catch (Throwable tx) {
		 	return Result.error("系统异常:" + tx.getMessage());
		 } finally {
			 IoUtil.close(inputStream);
		 }
	     return Result.ok();
	 }
  /**
   * 导出excel
   *
   * @param request
   * @param bbmbgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Bbmbgl bbmbgl) {
      return super.exportXls(request, bbmbgl, Bbmbgl.class, "报表模板管理");
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
      return super.importExcel(request, response, Bbmbgl.class);
  }

}

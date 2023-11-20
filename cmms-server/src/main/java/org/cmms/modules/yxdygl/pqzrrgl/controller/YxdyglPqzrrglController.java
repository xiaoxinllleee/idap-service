package org.cmms.modules.yxdygl.pqzrrgl.controller;

import java.io.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.PermissionData;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.tjfx.jcsjgl.cssz.service.ITjfxCsszService;
import org.cmms.modules.yxdygl.pqzrrgl.entity.Tjfxcssz;
import org.cmms.modules.yxdygl.pqzrrgl.entity.YxdyglPqzrrgl;
import org.cmms.modules.yxdygl.pqzrrgl.entity.YxdyglPqzrrglImport;
import org.cmms.modules.yxdygl.pqzrrgl.service.ITjfxcsszService;
import org.cmms.modules.yxdygl.pqzrrgl.service.IYxdyglPqzrrglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.yxdygl.sjyxdygl.entity.Sjyxdygl;
import org.cmms.modules.yxdygl.sjyxdygl.mapper.SjyxdyglMapper;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 片区责任人管理
 * @Author: jeecg-boot
 * @Date:   2020-06-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="片区责任人管理")
@RestController
@RequestMapping("/yxdygl/yxdyglPqzrrgl")
public class YxdyglPqzrrglController extends JeecgController<YxdyglPqzrrgl, IYxdyglPqzrrglService> {
	@Autowired
	private IYxdyglPqzrrglService yxdyglPqzrrglService;
	@Autowired
	private ITjfxcsszService tjfxcsszService;
	@Autowired
	private ITjfxCsszService iTjfxCsszService;
	@Value(value = "${common.path.upload}")
	private String uploadpath;
	@Autowired
	SjyxdyglMapper sjyxdyglMapper;

	/**
	 * 分页列表查询
	 *
	 * @param yxdyglPqzrrgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "片区责任人管理-分页列表查询")
	@ApiOperation(value="片区责任人管理-分页列表查询", notes="片区责任人管理-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent = "yxdygl/pqzrrgl/YxdyglPqzrrglList")
	public Result<?> queryPageList(YxdyglPqzrrgl yxdyglPqzrrgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<YxdyglPqzrrgl> queryWrapper = QueryGenerator.initQueryWrapper(yxdyglPqzrrgl, req.getParameterMap());
		Page<YxdyglPqzrrgl> page = new Page<YxdyglPqzrrgl>(pageNo, pageSize);
		IPage<YxdyglPqzrrgl> pageList = yxdyglPqzrrglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param yxdyglPqzrrgl
	 * @return
	 */
	@AutoLog(value = "片区责任人管理-添加")
	@ApiOperation(value="片区责任人管理-添加", notes="片区责任人管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody YxdyglPqzrrgl yxdyglPqzrrgl) {
		if (StringUtils.isNotBlank(yxdyglPqzrrgl.getSjyxdybh()) && yxdyglPqzrrgl.getSjyxdybh().contains(",")){
			String[] sj = yxdyglPqzrrgl.getSjyxdybh().split(",");
			//遍历次数
			int n = sj.length;
			String[] ejyxdybh = yxdyglPqzrrgl.getEjyxdybh().split(",");
			String[] yjyxdybh = yxdyglPqzrrgl.getYjyxdybh().split(",");
			String[] qydm = yxdyglPqzrrgl.getQydm().split(",");
			String[] sszh = yxdyglPqzrrgl.getSszh().split(",");
			String sfzkhjl = yxdyglPqzrrgl.getSfzkhjl();
			String khjl = yxdyglPqzrrgl.getKhjl();

			for (int i = 0; i < n; i++) {
				YxdyglPqzrrgl insertDao = new YxdyglPqzrrgl();
				insertDao.setSszh(sszh[i]);
				insertDao.setQydm(qydm[i]);
				insertDao.setSfzkhjl(sfzkhjl);
				insertDao.setYjyxdybh(yjyxdybh[i]);
				insertDao.setEjyxdybh(ejyxdybh[i]);
				insertDao.setSjyxdybh(sj[i]);
				insertDao.setKhjl(khjl);
				yxdyglPqzrrglService.save(insertDao);
			}
		}else {

			//判断是不是加村
			if (StringUtils.isNotBlank(yxdyglPqzrrgl.getEjyxdybh())){
				//判断是不是多选村
				if (yxdyglPqzrrgl.getEjyxdybh().contains(",")){
					String[] ejyxdybhs = yxdyglPqzrrgl.getEjyxdybh().split(",");
					String[] yjyxdybhs = yxdyglPqzrrgl.getYjyxdybh().split(",");
					String[] qydms = yxdyglPqzrrgl.getQydm().split(",");
					String[] sszhs = yxdyglPqzrrgl.getSszh().split(",");
					String sfzkhjl = yxdyglPqzrrgl.getSfzkhjl();
					String khjl = yxdyglPqzrrgl.getKhjl();
					int n = ejyxdybhs.length;
					for (int i = 0; i < n; i++) {
						List<Sjyxdygl> sjyxdygls = sjyxdyglMapper.queryDataByEjAndYjAndSszh(yjyxdybhs[i], ejyxdybhs[i], sszhs[i]);
						if (CollUtil.isNotEmpty(sjyxdygls)){
							for (int j = 0; j < sjyxdygls.size(); j++) {
									YxdyglPqzrrgl insertDao = new YxdyglPqzrrgl();
									insertDao.setSszh(sszhs[i]);
									insertDao.setQydm(qydms[i]);
									insertDao.setSfzkhjl(sfzkhjl);
									insertDao.setYjyxdybh(yjyxdybhs[i]);
									insertDao.setEjyxdybh(ejyxdybhs[i]);
									insertDao.setSjyxdybh(sjyxdygls.get(j).getDybh());
									insertDao.setKhjl(khjl);
								yxdyglPqzrrglService.save(insertDao);
								}
						}
					}

				}else {
					List<Sjyxdygl> sjyxdygls = sjyxdyglMapper.queryDataByEjAndYjAndSszh(yxdyglPqzrrgl.getYjyxdybh(), yxdyglPqzrrgl.getEjyxdybh(), yxdyglPqzrrgl.getSszh());
					if (CollUtil.isNotEmpty(sjyxdygls)){
						for (int i = 0; i < sjyxdygls.size(); i++) {
							yxdyglPqzrrgl.setSjyxdybh(sjyxdygls.get(i).getDybh());
							yxdyglPqzrrglService.save(yxdyglPqzrrgl);
						}
					}

				}


			}else {

				yxdyglPqzrrglService.save(yxdyglPqzrrgl);
			}

		}
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param yxdyglPqzrrgl
	 * @return
	 */
	@AutoLog(value = "片区责任人管理-编辑")
	@ApiOperation(value="片区责任人管理-编辑", notes="片区责任人管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody YxdyglPqzrrgl yxdyglPqzrrgl) {
		yxdyglPqzrrglService.updateById(yxdyglPqzrrgl);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "片区责任人管理-通过id删除")
	@ApiOperation(value="片区责任人管理-通过id删除", notes="片区责任人管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		yxdyglPqzrrglService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "片区责任人管理-批量删除")
	@ApiOperation(value="片区责任人管理-批量删除", notes="片区责任人管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.yxdyglPqzrrglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "片区责任人管理-通过id查询")
	@ApiOperation(value="片区责任人管理-通过id查询", notes="片区责任人管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		YxdyglPqzrrgl yxdyglPqzrrgl = yxdyglPqzrrglService.getById(id);
		return Result.ok(yxdyglPqzrrgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param yxdyglPqzrrgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, YxdyglPqzrrgl yxdyglPqzrrgl) {
      return super.exportXls(request, yxdyglPqzrrgl, YxdyglPqzrrgl.class, "片区责任人管理");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  /*@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, YxdyglPqzrrgl.class);
  }*/


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
		 mv.addObject(NormalExcelConstants.FILE_NAME, "片区责任人管理导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, YxdyglPqzrrglImport.class);
		 ExportParams exportParams = new ExportParams("片区责任人管理导入模板", "模板信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<YxdyglPqzrrglImport>());
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
		 JSONObject obj = new JSONObject();
		 String[] filePathList = filePaths.split(",");
		 for (String filePath : filePathList) {
			 String baseFilePath = uploadpath + File.separator + filePath;
			 File file = new File(baseFilePath);
			 ImportParams params = new ImportParams();
			 params.setTitleRows(1);
			 params.setHeadRows(1);
			 params.setNeedSave(true);
			 InputStream fis = null;
			 HSSFWorkbook newBook = null;
			 try {
				 List<YxdyglPqzrrgl> yxdyglPqzrrglList = ExcelImportUtil.importExcel(file, YxdyglPqzrrgl.class, params);
				 List<String> ids = new ArrayList<String>();
				 List<YxdyglPqzrrgl> insertList = new ArrayList<YxdyglPqzrrgl>();
				 fis = new FileInputStream(baseFilePath);
				 newBook = new HSSFWorkbook(new POIFSFileSystem(fis));
				 HSSFSheet sheet = newBook.getSheetAt(0);
				 HSSFRow hssfRow = null;
				 int rCi = 0, rCii = 0;
				 int i = 2;

				 for (YxdyglPqzrrgl yxdyglPqzrrgl : yxdyglPqzrrglList) {
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
					 if (StringUtils.isEmpty(yxdyglPqzrrgl.getSszh())) {
						 result = "导入失败";
						 resultInfo = "所属支行不能为空！";
						 resultCell.setCellValue(result);
						 resultCellInfo.setCellValue(resultInfo);
						 continue;
					 }
					 if (StringUtils.isEmpty(yxdyglPqzrrgl.getYjyxdybh())) {
						 result = "导入失败";
						 resultInfo = "一级营销单元不能为空！";
						 resultCell.setCellValue(result);
						 resultCellInfo.setCellValue(resultInfo);
						 continue;
					 }
					 if (StringUtils.isEmpty(yxdyglPqzrrgl.getKhjl())) {
						 result = "导入失败";
						 resultInfo = "客户经理不能为空！";
						 resultCell.setCellValue(result);
						 resultCellInfo.setCellValue(resultInfo);
						 continue;
					 }
					 if (StringUtils.isEmpty(yxdyglPqzrrgl.getSfzkhjl())) {
						 result = "导入失败";
						 resultInfo = "是否主客户经理不能为空！";
						 resultCell.setCellValue(result);
						 resultCellInfo.setCellValue(resultInfo);
						 continue;
					 }
				 	Map<String,String> map = new HashMap<>();
				 	StringBuffer stringBuffer = new StringBuffer();
					/*stringBuffer.append(yxdyglPqzrrgl.getYjyxdybh()==null  ? " " : yxdyglPqzrrgl.getYjyxdybh());
					stringBuffer.append(yxdyglPqzrrgl.getEjyxdybh()==null ? " " : yxdyglPqzrrgl.getEjyxdybh());
					stringBuffer.append(yxdyglPqzrrgl.getSjyxdybh()==null ? " " : yxdyglPqzrrgl.getSjyxdybh());*/
					if (yxdyglPqzrrgl.getSjyxdybh()==null){
					 	stringBuffer.append(yxdyglPqzrrgl.getEjyxdybh()==null ? "" : yxdyglPqzrrgl.getEjyxdybh());
					}
					stringBuffer.append(yxdyglPqzrrgl.getSjyxdybh()==null ? "" : yxdyglPqzrrgl.getSjyxdybh());
					String qydm = stringBuffer.toString().replace(" ","");
					System.out.println(qydm);
					map.put("qydm",qydm);
					map.put("sszh",yxdyglPqzrrgl.getSszh());
					map.put("khjl",yxdyglPqzrrgl.getKhjl());
					YxdyglPqzrrgl yxdyglPqzrrgl1 = yxdyglPqzrrglService.queryqydm(map);
					if (yxdyglPqzrrgl1!=null){
					 	ids.add(yxdyglPqzrrgl1.getQydm()+"-"+yxdyglPqzrrgl1.getKhjl()+"-"+yxdyglPqzrrgl1.getSszh());
					}
					yxdyglPqzrrgl.setQydm(qydm);
					resultCell.setCellValue(result);
					resultCellInfo.setCellValue(resultInfo);
					insertList.add(yxdyglPqzrrgl);
					/*String yjyxdybh = yxdyglPqzrrgl.getYjyxdybh();
					String ejyxdybh = yxdyglPqzrrgl.getEjyxdybh();*/
				 }

				 //先删除已经存在的数据
				 if (!ids.isEmpty()) {
					 for (String id : ids) {
						 String[] split = id.split("-");
						 String qydm = split[0];
						 String khjl =split[1];
						 String sszh = split[2];
						 UpdateWrapper<YxdyglPqzrrgl> updateWrapper = new UpdateWrapper<>();
						 updateWrapper.eq("qydm",qydm).eq("khjl",khjl).eq("sszh",sszh);
						 yxdyglPqzrrglService.remove(updateWrapper);
					 }
				 }
				 obj.put("filePath", filePath);
				 yxdyglPqzrrglService.saveBatch(insertList);
				 FileOutputStream fos = new FileOutputStream(baseFilePath);
				 newBook.write(fos);
				 fos.flush();
				 fos.close();
				 return Result.ok("文件导入成功！数据行数:" + yxdyglPqzrrglList.size() + "，导入成功行数：" + insertList.size() + "，失败行数：" + (yxdyglPqzrrglList.size()-insertList.size()), obj);
			 } catch (Exception e) {
				 log.error(e.getMessage(),e);
				 return Result.error("文件导入失败:"+e.getMessage());
			 }
		 }
		 return Result.ok("文件导入失败！");
	 }


	 /**
	  * 通过id查询
	  *
	  * @param tjfxcssz
	  * @return
	  */
	 @AutoLog(value = "查询是否开通三级营销单元")
	 @ApiOperation(value="是否开通三级营销单元-通过id查询", notes="是否开通三级营销单元-通过id查询")
	 @RequestMapping(value = "/querycsz", method = RequestMethod.PUT)
	 public Result<?> querycsz(@RequestBody Tjfxcssz tjfxcssz) {
	 	if (tjfxcssz== null || tjfxcssz.getCsbm() == null)
	 		return Result.error("参数为空");
		 String csz = iTjfxCsszService.queryCszByCsbm(tjfxcssz.getCsbm());
		 JSONObject jsonObject = new JSONObject();
		 jsonObject.put("csz",csz);
		 return Result.ok(jsonObject);
	 }

}

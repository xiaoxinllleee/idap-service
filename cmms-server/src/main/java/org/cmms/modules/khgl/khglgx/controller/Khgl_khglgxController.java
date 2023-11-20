package org.cmms.modules.khgl.khglgx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.khglgx.entity.Khgl_khglgx;
import org.cmms.modules.khgl.khglgx.entity.Khgl_khglgxImport;

import org.cmms.modules.khgl.khglgx.entity.*;
import org.cmms.modules.khgl.khglgx.service.*;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

 /**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-12-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="1")
@RestController
@RequestMapping("/khglgx/khgl_khglgx")
public class Khgl_khglgxController {
     @Autowired
     private IKhgl_khglgxService khgl_khglgxService;
     @Autowired
     private TIKhzyrzglService khzyrzglService;
     @Autowired
     private IKhhmcService iKhhmcService;
     @Autowired
     private IKhjjrzService iKhjjrzService;
	 @Autowired
	 private ICamsZcsxNhcjxxService1 iCamsZcsxNhcjxxService1;

	 @Value(value = "${common.path.upload}")
	 private String uploadpath;

	/**
	  * 分页列表查询
	 * @param khgl_khglgx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "1-分页列表查询")
	@ApiOperation(value="1-分页列表查询", notes="1-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Khgl_khglgx>> queryPageList(Khgl_khglgx khgl_khglgx,
													@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
													@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
													HttpServletRequest req) {
		Result<IPage<Khgl_khglgx>> result = new Result<IPage<Khgl_khglgx>>();
		QueryWrapper<Khgl_khglgx> queryWrapper = QueryGenerator.initQueryWrapper(khgl_khglgx, req.getParameterMap());
		Page<Khgl_khglgx> page = new Page<Khgl_khglgx>(pageNo, pageSize);
		IPage<Khgl_khglgx> pageList = khgl_khglgxService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param khgl_khglgx
	 * @return
	 */
	@AutoLog(value = "1-添加")
	@ApiOperation(value="1-添加", notes="1-添加")
	@PostMapping(value = "/add")
	public Result<Khgl_khglgx> add(@RequestBody Khgl_khglgx khgl_khglgx) {
		Result<Khgl_khglgx> result = new Result<Khgl_khglgx>();
		try {
			khgl_khglgxService.save(khgl_khglgx);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param khgl_khglgx
	 * @return
	 */
	@AutoLog(value = "1-编辑")
	@ApiOperation(value="1-编辑", notes="1-编辑")
	@PutMapping(value = "/edit")
	public Result<Khgl_khglgx> edit(@RequestBody Khgl_khglgx khgl_khglgx) {
		Result<Khgl_khglgx> result = new Result<Khgl_khglgx>();
		Khgl_khglgx khgl_khglgxEntity = khgl_khglgxService.getById(khgl_khglgx.getZjhm());
		if(khgl_khglgxEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = khgl_khglgxService.updateById(khgl_khglgx);
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
	@AutoLog(value = "1-通过id删除")
	@ApiOperation(value="1-通过id删除", notes="1-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			khgl_khglgxService.removeById(id);
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
	@AutoLog(value = "1-批量删除")
	@ApiOperation(value="1-批量删除", notes="1-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<Khgl_khglgx> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Khgl_khglgx> result = new Result<Khgl_khglgx>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.khgl_khglgxService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "1-通过id查询")
	@ApiOperation(value="1-通过id查询", notes="1-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Khgl_khglgx> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Khgl_khglgx> result = new Result<Khgl_khglgx>();
		Khgl_khglgx khgl_khglgx = khgl_khglgxService.getById(id);
		if(khgl_khglgx==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(khgl_khglgx);
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
      QueryWrapper<Khgl_khglgx> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Khgl_khglgx khgl_khglgx = JSON.parseObject(deString, Khgl_khglgx.class);
              queryWrapper = QueryGenerator.initQueryWrapper(khgl_khglgx, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Khgl_khglgx> pageList = khgl_khglgxService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "1列表");
      mv.addObject(NormalExcelConstants.CLASS, Khgl_khglgx.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("1列表数据", "导出人:Jeecg", "导出信息"));
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
  public Result<?> importExcel(@RequestBody JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response) {
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
		  HSSFWorkbook newBook = null;
		  try {
			  List<Khgl_khglgx> listKhhmcs = ExcelImportUtil.importExcel(file, Khgl_khglgx.class, params);
			  //List<String> zzbz = new ArrayList<String>();
			  List<String> zjhm = new ArrayList<String>();
			  List<Khgl_khglgx> insertList = new ArrayList<Khgl_khglgx>();
			  fis = new FileInputStream(baseFilePath);
			  newBook = new HSSFWorkbook(new POIFSFileSystem(fis));
			  HSSFSheet sheet = newBook.getSheetAt(0);

			  HSSFRow hssfRow = null;
			  int rCi = 0, rCii = 0;
			  int i = 2;
			  for (Khgl_khglgx khhmc : listKhhmcs) {

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
				  if (StringUtils.isEmpty(khhmc.getZjhm())) {
					  result = "导入失败";
					  resultInfo = "证件号码不能为空！";
					  resultCell.setCellValue(result);
					  resultCellInfo.setCellValue(resultInfo);
					  continue;
				  }
				  if (StringUtils.isEmpty(khhmc.getSskhjl())) {
					  result = "导入失败";
					  resultInfo = "所属客户经理不能为空！";
					  resultCell.setCellValue(result);
					  resultCellInfo.setCellValue(resultInfo);
					  continue;
				  }
				  QueryWrapper<Khgl_khglgx> queryWrapper = new QueryWrapper<>();
				  queryWrapper.eq("zjhm", khhmc.getZjhm());
/*
				  queryWrapper.eq("zzbz",khhmc.getZzbz());
*/
				  Khgl_khglgx user = khgl_khglgxService.getOne(queryWrapper);
				  if (user != null) {
					  zjhm.add(user.getZjhm());
/*
					  zzbz.add(user.getZzbz());
*/
				  }
				  resultCell.setCellValue(result);
				  resultCellInfo.setCellValue(resultInfo);
                  QueryWrapper<Khhmc> queryWrapperKhhmc = new QueryWrapper<>();
                  queryWrapperKhhmc.eq("zjhm", khhmc.getZjhm());
                  Khhmc one = iKhhmcService.getOne(queryWrapperKhhmc);
                  khhmc.setKhxm(one.getKhmc());
                  khhmc.setZzbz(one.getSszh());
				 khhmc.setZjhm(khhmc.getZjhm().split("\\.")[0]);
				 khhmc.setKhlx(one.getKhlx());
				 khhmc.setSskhjl(khhmc.getSskhjl().split("\\.")[0]);
				 khhmc.setSsyxdy(one.getSsyxdy());
				  insertList.add(khhmc);

			  }
			  //先删除已经存在的数据
			  if (!zjhm.isEmpty()) {
				  UpdateWrapper<Khgl_khglgx> userUpdateWrapper = new UpdateWrapper<>();
				  for (int j = 0; j < zjhm.size(); j++) {
					  QueryWrapper<Khgl_khglgx> queryWrapper = new QueryWrapper<>();
					  queryWrapper
							  .eq("zjhm",zjhm.get(j));
					   khgl_khglgxService.remove(queryWrapper);
				  }
			  }
			  obj.put("filePath", filePath);

			  khgl_khglgxService.saveBatch(insertList);
			  FileOutputStream fos = new FileOutputStream(baseFilePath);
			  newBook.write(fos);
			  fos.flush();
			  fos.close();
			  return Result.ok("文件导入成功！数据行数:" + listKhhmcs.size() + "，导入成功行数：" + insertList.size() + "，失败行数：" + (listKhhmcs.size()-insertList.size()), obj);
		  } catch (Exception e) {
			  log.error(e.getMessage(),e);
			  return Result.error("文件导入失败:"+e.getMessage());
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
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "客户关联关系导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, Khgl_khglgxImport.class);
		 ExportParams exportParams = new ExportParams("客户关联关系导入模板", "模板信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<Khgl_khglgxImport>());
		 return mv;
	 }

	 /**
	  * 查询家庭成员信息
	  * @param kh
	  * @return
	  */
	 @ApiOperation(value = "删除", notes = "删除")
	 @RequestMapping(value = "/dele", method = RequestMethod.PUT)
     public  Result<Khgl_khglgx> dele(@RequestBody JSONObject kh) {
         Result<Khgl_khglgx> result = new Result<Khgl_khglgx>();
         try {
             QueryWrapper<Khgl_khglgx> khgl_khglgxQueryWrapper=new QueryWrapper<>();
             khgl_khglgxQueryWrapper.eq("zjhm",kh.get("zjhm"));
             khgl_khglgxService.remove(khgl_khglgxQueryWrapper);
             result.setSuccess(true);
         } catch (Exception e) {
             log.error(e.getMessage(),e);
             result.setSuccess(false);
         }
         return result;
     }


	 @ApiOperation(value = "转移", notes = "转移")
	 @RequestMapping(value = "/transfer", method = RequestMethod.PUT)
	 public  Result<Khgl_khglgx> transfer(@RequestBody JSONObject para) {
		 Result<Khgl_khglgx> result = new Result<Khgl_khglgx>();
		 try {
			 List<Map<String,String>> rows = (List<Map<String,String>> )para.get("rows");
			 for (int i = 0; i < rows.size(); i++) {
				 Khgl_khglgx khgl= new Khgl_khglgx();
				 khgl.setSskhjl(para.get("transferTo").toString());
                 UpdateWrapper<Khgl_khglgx> userUpdateWrapper = new UpdateWrapper<>();
                 userUpdateWrapper.eq("zjhm", rows.get(i).get("zjhm"));
                 khgl_khglgxService.update(khgl, userUpdateWrapper);

				 CamsZcsxNhcjxx camsZcsxNhcjxx = new CamsZcsxNhcjxx();
				 camsZcsxNhcjxx.setSskhjl(para.get("transferTo").toString());
				 UpdateWrapper<CamsZcsxNhcjxx> camsZcsxNhcjxxUpdateWrapper = new UpdateWrapper<>();
				 camsZcsxNhcjxxUpdateWrapper.eq("zjhm", rows.get(i).get("zjhm"));
				 iCamsZcsxNhcjxxService1.update(camsZcsxNhcjxx, camsZcsxNhcjxxUpdateWrapper);
				 Khzyrzgl khzyrzgl=new Khzyrzgl();
				 khzyrzgl.setZzbz(rows.get(i).get("zzbz"));
				 khzyrzgl.setZjhm(rows.get(i).get("zjhm"));
				 khzyrzgl.setSsyxdy(rows.get(i).get("ssyxdy"));
				 khzyrzgl.setKhxm(rows.get(i).get("khxm"));
				 khzyrzgl.setKhlx(rows.get(i).get("khlx"));
				 khzyrzgl.setZyqkhjl(rows.get(i).get("sskhjl"));
				 khzyrzgl.setZyhkhjl(para.get("transferTo").toString());
				 khzyrzglService.save(khzyrzgl);
			 }
			 result.setSuccess(true);
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
			 result.setSuccess(false);
		 }
		 return result;
	 }

     /**
      * 客户所属支行与所属客户经理同时交接
      * @param param
      * @return
      */
     @ApiOperation(value = "交接", notes = "交接")
     @RequestMapping(value = "/handover", method = RequestMethod.PUT)
     public Result<Khgl_khglgx> CustomerHandover(@RequestBody JSONObject param) {
         Result<Khgl_khglgx> result = new Result<Khgl_khglgx>();
         try {
             List<Map<String,String>> rows = (List<Map<String,String>>)param.get("rows");
             for(int i = 0; i < rows.size(); i++) {
                 Khgl_khglgx khglgx = new Khgl_khglgx();
                 khglgx.setZzbz(param.get("handoverToZzbz").toString());
                 khglgx.setSskhjl(param.get("handoverToKhjl").toString());
                 UpdateWrapper<Khgl_khglgx> handoverWrapper = new UpdateWrapper<>();
                 handoverWrapper.eq("zjhm", rows.get(i).get("zjhm"));
                 khgl_khglgxService.update(khglgx, handoverWrapper);

				 CamsZcsxNhcjxx camsZcsxNhcjxx = new CamsZcsxNhcjxx();
				 camsZcsxNhcjxx.setSskhjl(param.get("handoverToKhjl").toString());
				 UpdateWrapper<CamsZcsxNhcjxx> camsZcsxNhcjxxUpdateWrapper = new UpdateWrapper<>();
				 camsZcsxNhcjxxUpdateWrapper.eq("zjhm", rows.get(i).get("zjhm"));
				 iCamsZcsxNhcjxxService1.update(camsZcsxNhcjxx, camsZcsxNhcjxxUpdateWrapper);
                 // 客户交接日志记录
                 Khjjrz khjjrzEntity = new Khjjrz();
                 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                 khjjrzEntity.setCreateBy(loginUser.getUsername());
                 // khjjrzEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
                 khjjrzEntity.setSsyxdy(rows.get(i).get("ssyxdy"));
                 khjjrzEntity.setKhmc(rows.get(i).get("khxm"));
                 khjjrzEntity.setZjhm(rows.get(i).get("zjhm"));
                 khjjrzEntity.setYzzbz(rows.get(i).get("zzbz"));
                 khjjrzEntity.setYkhjl(rows.get(i).get("sskhjl"));
                 khjjrzEntity.setXzzbz(param.get("handoverToZzbz").toString());
                 khjjrzEntity.setXkhjl(param.get("handoverToKhjl").toString());
                 iKhjjrzService.save(khjjrzEntity);
             }
             result.setSuccess(true);
             //result.setMessage("客户交接成功！");
         } catch (Exception e) {
             log.error(e.getMessage(), e);
             result.setSuccess(false);
             //result.setMessage("客户交接失败！");
         }
         return result;
     }
 }

package org.cmms.modules.yxgl.dkcpjs.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.util.PdfToImgUtil;
import org.cmms.modules.yxgl.dkcpjs.entity.Dkcpjs;
import org.cmms.modules.yxgl.dkcpjs.service.IDkcpjsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

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
 * @Description: 贷款产品介绍
 * @Author: jeecg-boot
 * @Date:   2023-07-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款产品介绍")
@RestController
@RequestMapping("/dkcpjs/dkcpjs")
public class DkcpjsController extends JeecgController<Dkcpjs, IDkcpjsService> {
	@Autowired
	private IDkcpjsService dkcpjsService;
	 @Value(value = "${common.path.upload}")
	 private String uploadpath;
	/**
	 * 分页列表查询
	 *
	 * @param dkcpjs
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款产品介绍-分页列表查询")
	@ApiOperation(value="贷款产品介绍-分页列表查询", notes="贷款产品介绍-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dkcpjs dkcpjs,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dkcpjs> queryWrapper = QueryGenerator.initQueryWrapper(dkcpjs, req.getParameterMap());
		Page<Dkcpjs> page = new Page<Dkcpjs>(pageNo, pageSize);
		IPage<Dkcpjs> pageList = dkcpjsService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	 /**
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "贷款产品介绍-分页列表查询")
	 @ApiOperation(value="贷款产品介绍-分页列表查询", notes="贷款产品介绍-分页列表查询")
	 @GetMapping(value = "/getdkjsImages")
	 public Result<?> getdkjsImages(HttpServletRequest req) {
		 QueryWrapper<Dkcpjs> queryWrapper =new QueryWrapper<>();
		 queryWrapper.isNotNull("fjlj");
		 queryWrapper.orderByAsc("id");
		 List<Dkcpjs> list = dkcpjsService.list(queryWrapper);
		 return Result.ok(list);
	 }


	 /**
	 * 添加
	 *
	 * @param dkcpjs
	 * @return
	 */
	@AutoLog(value = "贷款产品介绍-添加")
	@ApiOperation(value="贷款产品介绍-添加", notes="贷款产品介绍-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dkcpjs dkcpjs) {
		if(dkcpjs.getPdflj()!=null&&dkcpjs.getPdflj()!=""){
			System.out.println(uploadpath+"/"+dkcpjs.getPdflj());
			try {
				LinkedList linkedList = PdfToImgUtil.pdfToImg(uploadpath +"/"+ dkcpjs.getPdflj());
				String imgPath=dkcpjs.getPdflj().replace(".pdf",".jpg").replace(".PDF",".jpg");
				String imgUrl=uploadpath+"/"+imgPath;
				PdfToImgUtil.montageImages(linkedList,false,imgUrl);
				dkcpjs.setPdftplj(imgPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		dkcpjsService.save(dkcpjs);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param dkcpjs
	 * @return
	 */
	@AutoLog(value = "贷款产品介绍-编辑")
	@ApiOperation(value="贷款产品介绍-编辑", notes="贷款产品介绍-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dkcpjs dkcpjs) {
		if(dkcpjs.getPdflj()!=null&&dkcpjs.getPdflj()!=""){
			System.out.println(uploadpath+"/"+dkcpjs.getPdflj());
			try {
				LinkedList linkedList = PdfToImgUtil.pdfToImg(uploadpath + "/"+dkcpjs.getPdflj());
				String imgPath=dkcpjs.getPdflj().replace(".pdf",".jpg").replace(".PDF",".jpg");
				String imgUrl=uploadpath+"/"+imgPath;
				PdfToImgUtil.montageImages(linkedList,false,imgUrl);
				dkcpjs.setPdftplj(imgPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		dkcpjsService.updateById(dkcpjs);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款产品介绍-通过id删除")
	@ApiOperation(value="贷款产品介绍-通过id删除", notes="贷款产品介绍-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkcpjsService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款产品介绍-批量删除")
	@ApiOperation(value="贷款产品介绍-批量删除", notes="贷款产品介绍-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkcpjsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款产品介绍-通过id查询")
	@ApiOperation(value="贷款产品介绍-通过id查询", notes="贷款产品介绍-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dkcpjs dkcpjs = dkcpjsService.getById(id);
		return Result.ok(dkcpjs);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkcpjs
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dkcpjs dkcpjs) {
      return super.exportXls(request, dkcpjs, Dkcpjs.class, "贷款产品介绍");
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
		  params.setTitleRows(0);
		  params.setHeadRows(1);
		  params.setNeedSave(true);
		  try {
			  List<Dkcpjs> list = ExcelImportUtil.importExcel(file.getInputStream(), Dkcpjs.class, params);
			  //update-begin-author:taoyan date:20190528 for:批量插入数据
			  long start = System.currentTimeMillis();
			  service.saveBatch(list);
			  //400条 saveBatch消耗时间1592毫秒  循环插入消耗时间1947毫秒
			  //1200条  saveBatch消耗时间3687毫秒 循环插入消耗时间5212毫秒
			  log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
			  //update-end-author:taoyan date:20190528 for:批量插入数据
			  return Result.ok("文件导入成功！数据行数：" + list.size());
		  } catch (Exception e) {
			  log.error(e.getMessage(), e);
			  return Result.error("文件导入失败:" + e.getMessage());
		  } finally {
			  try {
				  file.getInputStream().close();
			  } catch (IOException e) {
				  e.printStackTrace();
			  }
		  }
	  }
	  return Result.error("文件导入失败！");
  }

}

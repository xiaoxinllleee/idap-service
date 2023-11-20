package org.cmms.modules.khxxgl.yjzrbg.controller;

import java.io.BufferedReader;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import java.util.stream.Stream;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khxxgl.yjzrbg.entity.ZxbgPdfImg;
import org.cmms.modules.khxxgl.yjzrbg.service.IZxbgPdfImgService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 征信报告转PDF
 * @Author: jeecg-boot
 * @Date:   2023-04-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="征信报告转PDF")
@RestController
@RequestMapping("/yjzrbg/zxbgPdfImg")
public class ZxbgPdfImgController extends JeecgController<ZxbgPdfImg, IZxbgPdfImgService> {
	@Autowired
	private IZxbgPdfImgService zxbgPdfImgService;
	
	/**
	 * 分页列表查询
	 *
	 * @param zxbgPdfImg
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "征信报告转PDF-分页列表查询")
	@ApiOperation(value="征信报告转PDF-分页列表查询", notes="征信报告转PDF-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZxbgPdfImg zxbgPdfImg,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZxbgPdfImg> queryWrapper = QueryGenerator.initQueryWrapper(zxbgPdfImg, req.getParameterMap());
		Page<ZxbgPdfImg> page = new Page<ZxbgPdfImg>(pageNo, pageSize);
		IPage<ZxbgPdfImg> pageList = zxbgPdfImgService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param zxbgPdfImg
	 * @return
	 */
	@AutoLog(value = "征信报告转PDF-添加")
	@ApiOperation(value="征信报告转PDF-添加", notes="征信报告转PDF-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZxbgPdfImg zxbgPdfImg) {
		zxbgPdfImgService.save(zxbgPdfImg);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param zxbgPdfImg
	 * @return
	 */
	@AutoLog(value = "征信报告转PDF-编辑")
	@ApiOperation(value="征信报告转PDF-编辑", notes="征信报告转PDF-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZxbgPdfImg zxbgPdfImg) {
		zxbgPdfImgService.updateById(zxbgPdfImg);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "征信报告转PDF-通过id删除")
	@ApiOperation(value="征信报告转PDF-通过id删除", notes="征信报告转PDF-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zxbgPdfImgService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "征信报告转PDF-批量删除")
	@ApiOperation(value="征信报告转PDF-批量删除", notes="征信报告转PDF-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zxbgPdfImgService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "征信报告转PDF-通过id查询")
	@ApiOperation(value="征信报告转PDF-通过id查询", notes="征信报告转PDF-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZxbgPdfImg zxbgPdfImg = zxbgPdfImgService.getById(id);
		return Result.ok(zxbgPdfImg);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zxbgPdfImg
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ZxbgPdfImg zxbgPdfImg) {
      return super.exportXls(request, zxbgPdfImg, ZxbgPdfImg.class, "征信报告转PDF");
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
      return super.importExcel(request, response, ZxbgPdfImg.class);
  }

	 @GetMapping(value = "/queryByZjhm")
	 public Result<?> queryByZjhm(@RequestParam(name="zjhm",required=true) String zjhm) {
		 LambdaQueryWrapper<ZxbgPdfImg> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		 lambdaQueryWrapper.eq(ZxbgPdfImg::getZjhm,zjhm);
		 List<ZxbgPdfImg> list = service.list(lambdaQueryWrapper);
		 if (CollUtil.isNotEmpty(list)){
			//拿到了html的地址
			 ZxbgPdfImg zxbgPdfImg = list.get(0);
			 StringBuffer stringBuffer = new StringBuffer();
			 if (StringUtils.isNotBlank(zxbgPdfImg.getWljl()) && FileUtil.isFile(zxbgPdfImg.getWljl()) && zxbgPdfImg.getWljl().contains("html")){
				 BufferedReader utf8Reader = FileUtil.getUtf8Reader(zxbgPdfImg.getWljl());
				 Stream<String> lines = utf8Reader.lines();
				 //为了解决最后乱码的问题
				 lines.forEach(s->{
							if (s.contains("class=\"u-msg-list\"")){
								//s.replace("class=\"u-msg-list\""," style=\" text-align: left;line-height: 150%;padding-top: 10px;padding-bottom: 10px \" ");
								s.replace("list-style-type: none;","");
							}else if(s.contains("<input style=\"float: right\" type=\"button\" name=\"button_export\" title=\"打印报告\" onclick=\"window.print();\" value=\"打印报告\">")){
								s.replace("<input style=\"float: right\" type=\"button\" name=\"button_export\" title=\"打印报告\" onclick=\"window.print();\" value=\"打印报告\">","");
							}else  {
								stringBuffer.append(s);
							}
						 }
				 );

				 return Result.ok(stringBuffer.toString());
			 }

			 if (StringUtils.isNotBlank(zxbgPdfImg.getWljl()) && FileUtil.isFile(zxbgPdfImg.getWljl()) && zxbgPdfImg.getWljl().contains("pdf")){
				 return Result.ok(zxbgPdfImg);
			 }
		 }
		 return Result.ok();
	 }
}

package org.cmms.modules.khxxgl.yjzrbg.controller;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khxxgl.yjzrbg.entity.ZxbgQueryInfo;
import org.cmms.modules.khxxgl.yjzrbg.service.IZxbgQueryInfoService;
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
 * @Description: 征信报告查询记录
 * @Author: jeecg-boot
 * @Date:   2023-04-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="征信报告查询记录")
@RestController
@RequestMapping("/yjzrbg/zxbgQueryInfo")
public class ZxbgQueryInfoController extends JeecgController<ZxbgQueryInfo, IZxbgQueryInfoService> {
	@Autowired
	private IZxbgQueryInfoService zxbgQueryInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param zxbgQueryInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "征信报告查询记录-分页列表查询")
	@ApiOperation(value="征信报告查询记录-分页列表查询", notes="征信报告查询记录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZxbgQueryInfo zxbgQueryInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String rq = null;
		if (StringUtils.isNotBlank(zxbgQueryInfo.getCreator())){
			rq = zxbgQueryInfo.getCreator();
			zxbgQueryInfo.setCreator(null);
		}

		QueryWrapper<ZxbgQueryInfo> queryWrapper = QueryGenerator.initQueryWrapper(zxbgQueryInfo, req.getParameterMap());
		Page<ZxbgQueryInfo> page = new Page<ZxbgQueryInfo>(pageNo, pageSize);

		if (StringUtils.isNotBlank(rq)){
			System.out.println(rq);
			String[] split = rq.split(",");
			if (split.length == 2){
				String s = split[0];
				String s1 = split[1];

				try {
					DateTime parse = DateUtil.parse(s, "yyyyMMddHHmmss");
					DateTime parse1 = DateUtil.parse(s1, "yyyyMMddHHmmss");

					queryWrapper.ge("create_time",parse);
					queryWrapper.le("create_time",parse1);

				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		queryWrapper.orderByDesc("create_time");
		IPage<ZxbgQueryInfo> pageList = zxbgQueryInfoService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param zxbgQueryInfo
	 * @return
	 */
	@AutoLog(value = "征信报告查询记录-添加")
	@ApiOperation(value="征信报告查询记录-添加", notes="征信报告查询记录-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZxbgQueryInfo zxbgQueryInfo) {
		zxbgQueryInfoService.save(zxbgQueryInfo);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param zxbgQueryInfo
	 * @return
	 */
	@AutoLog(value = "征信报告查询记录-编辑")
	@ApiOperation(value="征信报告查询记录-编辑", notes="征信报告查询记录-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZxbgQueryInfo zxbgQueryInfo) {
		zxbgQueryInfoService.updateById(zxbgQueryInfo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "征信报告查询记录-通过id删除")
	@ApiOperation(value="征信报告查询记录-通过id删除", notes="征信报告查询记录-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zxbgQueryInfoService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "征信报告查询记录-批量删除")
	@ApiOperation(value="征信报告查询记录-批量删除", notes="征信报告查询记录-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zxbgQueryInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "征信报告查询记录-通过id查询")
	@ApiOperation(value="征信报告查询记录-通过id查询", notes="征信报告查询记录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZxbgQueryInfo zxbgQueryInfo = zxbgQueryInfoService.getById(id);
		return Result.ok(zxbgQueryInfo);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zxbgQueryInfo
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ZxbgQueryInfo zxbgQueryInfo) {
      return super.exportXls(request, zxbgQueryInfo, ZxbgQueryInfo.class, "征信报告查询记录");
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
      return super.importExcel(request, response, ZxbgQueryInfo.class);
  }

}

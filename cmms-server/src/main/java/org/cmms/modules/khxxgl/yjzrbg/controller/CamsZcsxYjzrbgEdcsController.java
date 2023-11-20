package org.cmms.modules.khxxgl.yjzrbg.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import org.cmms.modules.khxxgl.yjzrbg.entity.CamsZcsxYjzrbgEdcs;
import org.cmms.modules.khxxgl.yjzrbg.service.ICamsZcsxYjzrbgEdcsService;
import org.cmms.modules.khxxgl.yjzrbg.service.ICamsZcsxYjzrbgService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 额度测算
 * @Author: jeecg-boot
 * @Date:   2023-05-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="额度测算")
@RestController
@RequestMapping("/yjzrbg/edcs")
public class CamsZcsxYjzrbgEdcsController extends JeecgController<CamsZcsxYjzrbgEdcs, ICamsZcsxYjzrbgEdcsService> {
	@Autowired
	private ICamsZcsxYjzrbgEdcsService camsZcsxYjzrbgEdcsService;

	 @Autowired
	 private ICamsZcsxYjzrbgService camsZcsxYjzrbgService;
	/**
	 * 分页列表查询
	 *
	 * @param camsZcsxYjzrbgEdcs
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "额度测算-分页列表查询")
	@ApiOperation(value="额度测算-分页列表查询", notes="额度测算-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CamsZcsxYjzrbgEdcs camsZcsxYjzrbgEdcs,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CamsZcsxYjzrbgEdcs> queryWrapper = QueryGenerator.initQueryWrapper(camsZcsxYjzrbgEdcs, req.getParameterMap());
		Page<CamsZcsxYjzrbgEdcs> page = new Page<CamsZcsxYjzrbgEdcs>(pageNo, pageSize);
		IPage<CamsZcsxYjzrbgEdcs> pageList = camsZcsxYjzrbgEdcsService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param camsZcsxYjzrbgEdcs
	 * @return
	 */
	@AutoLog(value = "额度测算-添加")
	@ApiOperation(value="额度测算-添加", notes="额度测算-添加")
	@PostMapping(value = "/add")
	@Transactional
	public Result<?> add(@RequestBody CamsZcsxYjzrbgEdcs camsZcsxYjzrbgEdcs) {
		camsZcsxYjzrbgEdcs.setCreateTime(new Date());
		camsZcsxYjzrbgEdcs.setCreator(getWorkNo());
		camsZcsxYjzrbgEdcsService.save(camsZcsxYjzrbgEdcs);

		if (camsZcsxYjzrbgEdcs.getHzed() != null && StringUtils.isNotBlank(camsZcsxYjzrbgEdcs.getZjhm())){
			System.out.println("1111111111111111111111111111");
			System.out.println(camsZcsxYjzrbgEdcs.getHzed());
			System.out.println(camsZcsxYjzrbgEdcs.getZjhm());
			camsZcsxYjzrbgService.updateCsed(camsZcsxYjzrbgEdcs.getHzed(),camsZcsxYjzrbgEdcs.getZjhm());
		}

		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param camsZcsxYjzrbgEdcs
	 * @return
	 */
	@AutoLog(value = "额度测算-编辑")
	@ApiOperation(value="额度测算-编辑", notes="额度测算-编辑")
	@PutMapping(value = "/edit")
	@Transactional
	public Result<?> edit(@RequestBody CamsZcsxYjzrbgEdcs camsZcsxYjzrbgEdcs) {
		camsZcsxYjzrbgEdcs.setUpdator(getWorkNo());
		camsZcsxYjzrbgEdcs.setUpdateTime(new Date());
		camsZcsxYjzrbgEdcsService.updateById(camsZcsxYjzrbgEdcs);
		if (camsZcsxYjzrbgEdcs.getHzed() != null && StringUtils.isNotBlank(camsZcsxYjzrbgEdcs.getZjhm())){
			camsZcsxYjzrbgService.updateCsed(camsZcsxYjzrbgEdcs.getHzed(),camsZcsxYjzrbgEdcs.getZjhm());
		}
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "额度测算-通过id删除")
	@ApiOperation(value="额度测算-通过id删除", notes="额度测算-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		camsZcsxYjzrbgEdcsService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "额度测算-批量删除")
	@ApiOperation(value="额度测算-批量删除", notes="额度测算-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.camsZcsxYjzrbgEdcsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "额度测算-通过id查询")
	@ApiOperation(value="额度测算-通过id查询", notes="额度测算-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CamsZcsxYjzrbgEdcs camsZcsxYjzrbgEdcs = camsZcsxYjzrbgEdcsService.getById(id);
		return Result.ok(camsZcsxYjzrbgEdcs);
	}

	 @GetMapping(value = "/queryByZjhm")
	 public Result<?> queryByZjhm(@RequestParam(name="zjhm",required=true) String zjhm) {
		 LambdaQueryWrapper<CamsZcsxYjzrbgEdcs> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		 lambdaQueryWrapper.eq(CamsZcsxYjzrbgEdcs::getZjhm,zjhm);
		 List<CamsZcsxYjzrbgEdcs> list = service.list(lambdaQueryWrapper);
		 if (CollUtil.isNotEmpty(list)){
			 return Result.ok(list.get(0));
		 }
		 return Result.ok();
	 }

  /**
   * 导出excel
   *
   * @param request
   * @param camsZcsxYjzrbgEdcs
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, CamsZcsxYjzrbgEdcs camsZcsxYjzrbgEdcs) {
      return super.exportXls(request, camsZcsxYjzrbgEdcs, CamsZcsxYjzrbgEdcs.class, "额度测算");
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
      return super.importExcel(request, response, CamsZcsxYjzrbgEdcs.class);
  }

}

package org.cmms.modules.ywgl.yydj.etcyw.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.ywgl.yydj.etcyw.entity.Etcyw;
import org.cmms.modules.ywgl.yydj.etcyw.service.IEtcywService;
import java.util.Date;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: ETC业务
 * @Author: jeecg-boot
 * @Date:   2022-03-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags="ETC业务")
@RestController
@RequestMapping("/etcyw/etcyw")
public class EtcywController extends JeecgController<Etcyw, IEtcywService> {
	@Autowired
	private IEtcywService etcywService;
	 @Autowired
	 IDictValueQuery iDictValueQuery;

	/**
	 * 分页列表查询
	 *
	 * @param etcyw
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "ETC业务-分页列表查询")
	@ApiOperation(value="ETC业务-分页列表查询", notes="ETC业务-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Etcyw etcyw,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Etcyw> queryWrapper = QueryGenerator.initQueryWrapper(etcyw, req.getParameterMap());
		Page<Etcyw> page = new Page<Etcyw>(pageNo, pageSize);
		IPage<Etcyw> pageList = etcywService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param etcyw
	 * @return
	 */
	@AutoLog(value = "ETC业务-添加")
	@ApiOperation(value="ETC业务-添加", notes="ETC业务-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Etcyw etcyw) {
		if (StringUtils.isBlank(etcyw.getJgdm()))
			return Result.error("业务网点不能为空");
		if (StringUtils.isBlank(etcyw.getZjhm()))
			return Result.error("证件号码不能为空");
		if (etcyw.getYyrq() == null){
			return Result.error("预约日期不能为空");
		}
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq("jgdm",etcyw.getJgdm());
		queryWrapper.eq("zjhm",etcyw.getZjhm());
		queryWrapper.eq("yyrq",etcyw.getYyrq());
		List list = service.list(queryWrapper);
		if (CollUtil.isNotEmpty(list)){
			return Result.error("同一机构一个客户一天只能预约一次！");
		}
		etcyw.setYybh(Long.parseLong(iDictValueQuery.getSeqRateZxlldjbDjidNextval("SEQ_PUBLIC_ID_SJYH.nextval")));
		etcyw.setLrr(getUsername());
		etcywService.save(etcyw);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param etcyw
	 * @return
	 */
	@AutoLog(value = "ETC业务-编辑")
	@ApiOperation(value="ETC业务-编辑", notes="ETC业务-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Etcyw etcyw) {
		etcywService.updateById(etcyw);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ETC业务-通过id删除")
	@ApiOperation(value="ETC业务-通过id删除", notes="ETC业务-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		etcywService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "ETC业务-批量删除")
	@ApiOperation(value="ETC业务-批量删除", notes="ETC业务-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.etcywService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ETC业务-通过id查询")
	@ApiOperation(value="ETC业务-通过id查询", notes="ETC业务-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Etcyw etcyw = etcywService.getById(id);
		return Result.ok(etcyw);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param etcyw
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Etcyw etcyw) {
      return super.exportXls(request, etcyw, Etcyw.class, "ETC业务");
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
      return super.importExcel(request, response, Etcyw.class);
  }

}

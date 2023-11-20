package org.cmms.modules.ywgl.yydj.sjyh.controller;

import java.math.BigDecimal;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.ywgl.yydj.sjyh.entity.Yydjsjyh;
import org.cmms.modules.ywgl.yydj.sjyh.entity.YyjlVO;
import org.cmms.modules.ywgl.yydj.sjyh.service.IYydjsjyhService;
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
 * @Description: 手机银行
 * @Author: jeecg-boot
 * @Date:   2022-03-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="手机银行")
@RestController
@RequestMapping("/sjyh/yydjsjyh")
public class YydjsjyhController extends JeecgController<Yydjsjyh, IYydjsjyhService> {
	@Autowired
	private IYydjsjyhService yydjsjyhService;
	 @Autowired
	 IDictValueQuery iDictValueQuery;

	/**
	 * 分页列表查询
	 *
	 * @param yydjsjyh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "手机银行-分页列表查询")
	@ApiOperation(value="手机银行-分页列表查询", notes="手机银行-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Yydjsjyh yydjsjyh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Yydjsjyh> queryWrapper = QueryGenerator.initQueryWrapper(yydjsjyh, req.getParameterMap());
		Page<Yydjsjyh> page = new Page<Yydjsjyh>(pageNo, pageSize);
		IPage<Yydjsjyh> pageList = yydjsjyhService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 @GetMapping(value = "/getQb")
	 public Result<?> queryPageList2(Integer sbzt , @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									 				@RequestParam(name="pageSize", defaultValue="10") Integer pageSize){
		 Page<String> page = new Page<String>(pageNo, pageSize);
		 List<YyjlVO> result = service.getList(page, sbzt,getWorkNo());
		 return Result.ok(result);
	 }
	/**
	 * 添加
	 *
	 * @param yydjsjyh
	 * @return
	 */
	@AutoLog(value = "手机银行-添加")
	@ApiOperation(value="手机银行-添加", notes="手机银行-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Yydjsjyh yydjsjyh) {
		if (StringUtils.isBlank(yydjsjyh.getJgdm()))
			return Result.error("业务网点不能为空");
		if (StringUtils.isBlank(yydjsjyh.getZjhm()))
			return Result.error("证件号码不能为空");
		if (yydjsjyh.getYyrq() == null){
			return Result.error("预约日期不能为空");
		}
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq("jgdm",yydjsjyh.getJgdm());
		queryWrapper.eq("zjhm",yydjsjyh.getZjhm());
		queryWrapper.eq("yyrq",yydjsjyh.getYyrq());
		List list = service.list(queryWrapper);
		if (CollUtil.isNotEmpty(list)){
			return Result.error("同一机构一个客户一天只能预约一次！");
		}
		yydjsjyh.setYybh(Long.parseLong(iDictValueQuery.getSeqRateZxlldjbDjidNextval("SEQ_PUBLIC_ID_SJYH.nextval")));
		yydjsjyh.setLrr(getUsername());
		yydjsjyhService.save(yydjsjyh);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param yydjsjyh
	 * @return
	 */
	@AutoLog(value = "手机银行-编辑")
	@ApiOperation(value="手机银行-编辑", notes="手机银行-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Yydjsjyh yydjsjyh) {
		yydjsjyhService.updateById(yydjsjyh);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "手机银行-通过id删除")
	@ApiOperation(value="手机银行-通过id删除", notes="手机银行-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		yydjsjyhService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "手机银行-批量删除")
	@ApiOperation(value="手机银行-批量删除", notes="手机银行-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.yydjsjyhService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "手机银行-通过id查询")
	@ApiOperation(value="手机银行-通过id查询", notes="手机银行-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Yydjsjyh yydjsjyh = yydjsjyhService.getById(id);
		return Result.ok(yydjsjyh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param yydjsjyh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Yydjsjyh yydjsjyh) {
      return super.exportXls(request, yydjsjyh, Yydjsjyh.class, "手机银行");
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
      return super.importExcel(request, response, Yydjsjyh.class);
  }

}

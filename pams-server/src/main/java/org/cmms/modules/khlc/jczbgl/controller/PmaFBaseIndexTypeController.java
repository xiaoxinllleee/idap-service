package org.cmms.modules.khlc.jczbgl.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.RandomUtil;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khlc.jczbgl.entity.PmaFBaseIndexType;
import org.cmms.modules.khlc.jczbgl.service.IPmaFBaseIndexTypeService;
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
 * @Description: 基础指标类型表
 * @Author: jeecg-boot
 * @Date:   2021-01-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="基础指标类型表")
@RestController
@RequestMapping("/khlc/jczbgl/pmaFBaseIndexType")
public class PmaFBaseIndexTypeController extends JeecgController<PmaFBaseIndexType, IPmaFBaseIndexTypeService> {
	@Autowired
	private IPmaFBaseIndexTypeService pmaFBaseIndexTypeService;

	
	/**
	 * 分页列表查询
	 *
	 * @param pmaFBaseIndexType
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "基础指标类型表-分页列表查询")
	@ApiOperation(value="基础指标类型表-分页列表查询", notes="基础指标类型表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PmaFBaseIndexType pmaFBaseIndexType,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PmaFBaseIndexType> queryWrapper = QueryGenerator.initQueryWrapper(pmaFBaseIndexType, req.getParameterMap());
		Page<PmaFBaseIndexType> page = new Page<PmaFBaseIndexType>(pageNo, pageSize);
		IPage<PmaFBaseIndexType> pageList = pmaFBaseIndexTypeService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param pmaFBaseIndexType
	 * @return
	 */
	@AutoLog(value = "基础指标类型表-添加")
	@ApiOperation(value="基础指标类型表-添加", notes="基础指标类型表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PmaFBaseIndexType pmaFBaseIndexType) {
		pmaFBaseIndexType.setId(UUIDGenerator.generate());
		if (pmaFBaseIndexType.getDirType().equals("0")){
			pmaFBaseIndexType.setLeafFlag("Y");
		}else {
			pmaFBaseIndexType.setLeafFlag("N");
		}

		pmaFBaseIndexType.setLevel0(pmaFBaseIndexType.getLevel0()+1);
		pmaFBaseIndexType.setBussSysNo("00");
		pmaFBaseIndexType.setOrgId("00");

		pmaFBaseIndexTypeService.save(pmaFBaseIndexType);

		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param pmaFBaseIndexType
	 * @return
	 */
	@AutoLog(value = "基础指标类型表-编辑")
	@ApiOperation(value="基础指标类型表-编辑", notes="基础指标类型表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PmaFBaseIndexType pmaFBaseIndexType) {
		pmaFBaseIndexTypeService.updateById(pmaFBaseIndexType);
		return Result.ok("编辑成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "基础指标类型表-批量删除")
	@ApiOperation(value="基础指标类型表-批量删除", notes="基础指标类型表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pmaFBaseIndexTypeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "基础指标类型表-通过id查询")
	@ApiOperation(value="基础指标类型表-通过id查询", notes="基础指标类型表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PmaFBaseIndexType pmaFBaseIndexType = pmaFBaseIndexTypeService.getById(id);
		return Result.ok(pmaFBaseIndexType);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param pmaFBaseIndexType
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, PmaFBaseIndexType pmaFBaseIndexType) {
      return super.exportXls(request, pmaFBaseIndexType, PmaFBaseIndexType.class, "基础指标类型表");
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
      return super.importExcel(request, response, PmaFBaseIndexType.class);
  }


  @RequestMapping("/treeData")
  public Result<?> getTreeData(String dirType){
	  System.out.println(dirType);
	  List<PmaFBaseIndexType> pmaFBaseIndexTypes = pmaFBaseIndexTypeService.listTree(dirType);
	  return Result.ok(pmaFBaseIndexTypes);
  }


}

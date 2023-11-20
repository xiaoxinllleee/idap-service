package org.cmms.modules.hr.xsgl.gwxssz.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.modules.hr.xsgl.gwxssz.entity.ErpPostKhxs;
import org.cmms.modules.hr.xsgl.gwxssz.entity.ErpPostKhxsDTO;
import org.cmms.modules.hr.xsgl.gwxssz.service.IErpPostKhxsService;
import org.cmms.modules.hr.xsgl.gwxssz.verify.ErpPostKhxsImportVerify;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 岗位系数管理
 * @Author: jeecg-boot
 * @Date:   2021-10-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="岗位系数管理")
@RestController
@RequestMapping("/hr/erpPostKhxs")
public class ErpPostKhxsController extends JeecgController<ErpPostKhxs, IErpPostKhxsService> {
	@Autowired
	private ErpPostKhxsImportVerify erpPostKhxsImportVerify;
	/**
	 * 分页列表查询
	 *
	 * @param erpPostKhxs
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "岗位系数管理-分页列表查询")
	@ApiOperation(value="岗位系数管理-分页列表查询", notes="岗位系数管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ErpPostKhxs erpPostKhxs,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ErpPostKhxs> queryWrapper = QueryGenerator.initQueryWrapper(erpPostKhxs, req.getParameterMap());
		Page<ErpPostKhxs> page = new Page<ErpPostKhxs>(pageNo, pageSize);
		IPage<ErpPostKhxs> pageList = service.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param erpPostKhxs
	 * @return
	 */
	@AutoLog(value = "岗位系数管理-添加")
	@ApiOperation(value="岗位系数管理-添加", notes="岗位系数管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ErpPostKhxsDTO erpPostKhxs) {
		//service.save(erpPostKhxs);
		List<Integer> gwbz = erpPostKhxs.getGwbz();
		List<String> zzbz = erpPostKhxs.getZzbz();
		BigDecimal zxs = erpPostKhxs.getZxs();
		BigDecimal bcykhxs = erpPostKhxs.getBcykhxs();
		BigDecimal khxs = erpPostKhxs.getKhxs();
		int count = 0;
		for (int i = 0; i < gwbz.size(); i++) {
			 Integer integer = gwbz.get(i);

			for (int j = 0; j < zzbz.size(); j++) {
				String s = zzbz.get(j);
				ErpPostKhxs save = new ErpPostKhxs();
				save.setZxs(zxs);
				save.setKhxs(khxs);
				save.setBcykhxs(bcykhxs);
				save.setGwbz(integer);
				save.setZzbz(s);
				boolean result = service.save(save);
				if (result)
					count++;
			}
		}
		log.info("===/hr/erpPostKhxs/add本次新增{}条数据",count);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param erpPostKhxs
	 * @return
	 */
	@AutoLog(value = "岗位系数管理-编辑")
	@ApiOperation(value="岗位系数管理-编辑", notes="岗位系数管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ErpPostKhxs erpPostKhxs) {
		service.updateById(erpPostKhxs);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "岗位系数管理-通过id删除")
	@ApiOperation(value="岗位系数管理-通过id删除", notes="岗位系数管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		service.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "岗位系数管理-批量删除")
	@ApiOperation(value="岗位系数管理-批量删除", notes="岗位系数管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "岗位系数管理-通过id查询")
	@ApiOperation(value="岗位系数管理-通过id查询", notes="岗位系数管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ErpPostKhxs erpPostKhxs = service.getById(id);
		return Result.ok(erpPostKhxs);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param erpPostKhxs
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ErpPostKhxs erpPostKhxs) {
      return super.exportXls(request, erpPostKhxs, ErpPostKhxs.class, "岗位系数管理");
  }

	/**
	 * 导出模板Excel
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/exportTemplateXls")
	public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		// 导出文件名称
		mv.addObject(NormalExcelConstants.FILE_NAME, "岗位考核系数导入模板");
		mv.addObject(NormalExcelConstants.CLASS, ErpPostKhxs.class);
		ExportParams exportParams = new ExportParams("岗位考核系数导入模板", "岗位考核系数");
		mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
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
	  return super.importExcelByTemplate(jsonObject, request, response, ErpPostKhxs.class, erpPostKhxsImportVerify);
  }

}

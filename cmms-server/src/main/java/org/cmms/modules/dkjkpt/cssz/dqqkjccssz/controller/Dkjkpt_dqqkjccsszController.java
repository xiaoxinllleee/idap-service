package org.cmms.modules.dkjkpt.cssz.dqqkjccssz.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dkjkpt.cssz.dkqxcssz.entity.Dkjkpt_dkqxcssz;
import org.cmms.modules.dkjkpt.cssz.dqqkjccssz.entity.Dkjkpt_dqqkjccssz;
import org.cmms.modules.dkjkpt.cssz.dqqkjccssz.service.IDkjkpt_dqqkjccsszService;
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
 * @Description: 到期情况监测参数设置
 * @Author: jeecg-boot
 * @Date:   2020-11-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags="到期情况监测参数设置")
@RestController
@RequestMapping("/DKJKPT_DQQKJCCSSZ/dkjkpt_dqqkjccssz")
public class Dkjkpt_dqqkjccsszController extends JeecgController<Dkjkpt_dqqkjccssz, IDkjkpt_dqqkjccsszService> {
	@Autowired
	private IDkjkpt_dqqkjccsszService dkjkpt_dqqkjccsszService;

	/**
	 * 分页列表查询
	 *
	 * @param dkjkpt_dqqkjccssz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "到期情况监测参数设置-分页列表查询")
	@ApiOperation(value="到期情况监测参数设置-分页列表查询", notes="到期情况监测参数设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dkjkpt_dqqkjccssz dkjkpt_dqqkjccssz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dkjkpt_dqqkjccssz> queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_dqqkjccssz, req.getParameterMap());
		Page<Dkjkpt_dqqkjccssz> page = new Page<Dkjkpt_dqqkjccssz>(pageNo, pageSize);
		IPage<Dkjkpt_dqqkjccssz> pageList = dkjkpt_dqqkjccsszService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkjkpt_dqqkjccssz
	 * @return
	 */
	@AutoLog(value = "到期情况监测参数设置-添加")
	@ApiOperation(value="到期情况监测参数设置-添加", notes="到期情况监测参数设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dkjkpt_dqqkjccssz dkjkpt_dqqkjccssz) {
		dkjkpt_dqqkjccsszService.save(dkjkpt_dqqkjccssz);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkjkpt_dqqkjccssz
	 * @return
	 */
	@AutoLog(value = "到期情况监测参数设置-编辑")
	@ApiOperation(value="到期情况监测参数设置-编辑", notes="到期情况监测参数设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dkjkpt_dqqkjccssz dkjkpt_dqqkjccssz) {
		dkjkpt_dqqkjccsszService.updateById(dkjkpt_dqqkjccssz);
		return Result.ok("编辑成功!");
	}
	 /**
	  * 编辑
	  *
	  * @param dkjkpt_dkqxcssz
	  * @return
	  */
	 @AutoLog(value = "贷款期限参数设置-编辑")
	 @ApiOperation(value="贷款期限参数设置-编辑", notes="贷款期限参数设置-编辑")
	 @PutMapping(value = "/updateByCsbh")
	 public Result<?> updateByCsbh(@RequestBody Dkjkpt_dkqxcssz dkjkpt_dkqxcssz) {
		 int count = dkjkpt_dqqkjccsszService.updateByCsbh(dkjkpt_dkqxcssz);
		 if (count==1){
			 return Result.ok("编辑成功!");
		 }else {
			 return Result.ok("编辑失败，参数编号已存在!");

		 }
	 }
	 /**
	  * 通过id删除
	  *
	  * @param csbh
	  * @return
	  */
	 @AutoLog(value = "贷款期限参数设置-通过id删除")
	 @ApiOperation(value="贷款期限参数设置-通过id删除", notes="贷款期限参数设置-通过id删除")
	 @DeleteMapping(value = "/deleteByCsbh")
	 public Result<?> deleteByCsbh(@RequestParam(name="csbh",required=true) String csbh) {
		 dkjkpt_dqqkjccsszService.deleteByCsbh(csbh);
		 return Result.ok("删除成功!");
	 }
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "到期情况监测参数设置-通过id删除")
	@ApiOperation(value="到期情况监测参数设置-通过id删除", notes="到期情况监测参数设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkjkpt_dqqkjccsszService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "到期情况监测参数设置-批量删除")
	@ApiOperation(value="到期情况监测参数设置-批量删除", notes="到期情况监测参数设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkjkpt_dqqkjccsszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "到期情况监测参数设置-通过id查询")
	@ApiOperation(value="到期情况监测参数设置-通过id查询", notes="到期情况监测参数设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dkjkpt_dqqkjccssz dkjkpt_dqqkjccssz = dkjkpt_dqqkjccsszService.getById(id);
		return Result.ok(dkjkpt_dqqkjccssz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkjkpt_dqqkjccssz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dkjkpt_dqqkjccssz dkjkpt_dqqkjccssz) {
      return super.exportXls(request, dkjkpt_dqqkjccssz, Dkjkpt_dqqkjccssz.class, "到期情况监测参数设置");
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
      return super.importExcel(request, response, Dkjkpt_dqqkjccssz.class);
  }

}

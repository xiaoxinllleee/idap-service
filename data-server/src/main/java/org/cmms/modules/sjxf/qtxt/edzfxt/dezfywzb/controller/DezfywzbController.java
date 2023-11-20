package org.cmms.modules.sjxf.qtxt.edzfxt.dezfywzb.controller;

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
import org.cmms.modules.sjxf.qtxt.edzfxt.dezfywzb.entity.Dezfywzb;
import org.cmms.modules.sjxf.qtxt.edzfxt.dezfywzb.service.IDezfywzbService;
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
 * @Description: 大额支付业务主表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags="大额支付业务主表")
@RestController
@RequestMapping("/dezfywzb/dezfywzb")
public class DezfywzbController extends JeecgController<Dezfywzb, IDezfywzbService> {
	@Autowired
	private IDezfywzbService dezfywzbService;

	/**
	 * 分页列表查询
	 *
	 * @param dezfywzb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "大额支付业务主表-分页列表查询")
	@ApiOperation(value="大额支付业务主表-分页列表查询", notes="大额支付业务主表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dezfywzb dezfywzb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dezfywzb> queryWrapper = QueryGenerator.initQueryWrapper(dezfywzb, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IDezfywzbService.class,dezfywzbService,pageNo,pageSize,queryWrapper,"wt_date","orderno","or_br_no","lw_ind");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dezfywzb
	 * @return
	 */
	@AutoLog(value = "大额支付业务主表-添加")
	@ApiOperation(value="大额支付业务主表-添加", notes="大额支付业务主表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dezfywzb dezfywzb) {
		dezfywzbService.save(dezfywzb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dezfywzb
	 * @return
	 */
	@AutoLog(value = "大额支付业务主表-编辑")
	@ApiOperation(value="大额支付业务主表-编辑", notes="大额支付业务主表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dezfywzb dezfywzb) {
		dezfywzbService.updateById(dezfywzb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "大额支付业务主表-通过id删除")
	@ApiOperation(value="大额支付业务主表-通过id删除", notes="大额支付业务主表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dezfywzbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "大额支付业务主表-批量删除")
	@ApiOperation(value="大额支付业务主表-批量删除", notes="大额支付业务主表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dezfywzbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "大额支付业务主表-通过id查询")
	@ApiOperation(value="大额支付业务主表-通过id查询", notes="大额支付业务主表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dezfywzb dezfywzb = dezfywzbService.getById(id);
		return Result.ok(dezfywzb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dezfywzb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dezfywzb dezfywzb) {
      return super.exportXls(request, dezfywzb, Dezfywzb.class, "大额支付业务主表");
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
      return super.importExcel(request, response, Dezfywzb.class);
  }

}

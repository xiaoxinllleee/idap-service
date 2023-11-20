package org.cmms.modules.ywgl.dkyw.lsdkghtz.controller;

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
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ywgl.dkyw.lsdkghtz.entity.Lsdkghtz;
import org.cmms.modules.ywgl.dkyw.lsdkghtz.service.ILsdkghtzService;
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
 * @Description: 历史贷款管户台账
 * @Author: jeecg-boot
 * @Date:   2021-09-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="历史贷款管户台账")
@RestController
@RequestMapping("/lsdkghtz/lsdkghtz")
public class LsdkghtzController extends JeecgController<Lsdkghtz, ILsdkghtzService> {
	@Autowired
	private ILsdkghtzService lsdkghtzService;
	
	/**
	 * 分页列表查询
	 *
	 * @param lsdkghtz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "历史贷款管户台账-分页列表查询")
	@ApiOperation(value="历史贷款管户台账-分页列表查询", notes="历史贷款管户台账-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Lsdkghtz lsdkghtz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   String ckyeS,String ckyeE,String dqqxS,String dqqxE,
								   HttpServletRequest req) {
		QueryWrapper<Lsdkghtz> queryWrapper = QueryGenerator.initQueryWrapper(lsdkghtz, req.getParameterMap());
		if (!StringUtils.isBlank(ckyeE))queryWrapper.ge("ckye",ckyeS);
		if (!StringUtils.isBlank(ckyeE))queryWrapper.le("ckye",ckyeE);
		if (!StringUtils.isBlank(dqqxE))queryWrapper.ge("dqqx",dqqxS);
		if (!StringUtils.isBlank(dqqxE))queryWrapper.le("dqqx",dqqxE);
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(ILsdkghtzService.class,lsdkghtzService,pageNo,pageSize,queryWrapper,"dkzh");
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param lsdkghtz
	 * @return
	 */
	@AutoLog(value = "历史贷款管户台账-添加")
	@ApiOperation(value="历史贷款管户台账-添加", notes="历史贷款管户台账-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Lsdkghtz lsdkghtz) {
		lsdkghtzService.save(lsdkghtz);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param lsdkghtz
	 * @return
	 */
	@AutoLog(value = "历史贷款管户台账-编辑")
	@ApiOperation(value="历史贷款管户台账-编辑", notes="历史贷款管户台账-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Lsdkghtz lsdkghtz) {
		lsdkghtzService.updateById(lsdkghtz);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "历史贷款管户台账-通过id删除")
	@ApiOperation(value="历史贷款管户台账-通过id删除", notes="历史贷款管户台账-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		lsdkghtzService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "历史贷款管户台账-批量删除")
	@ApiOperation(value="历史贷款管户台账-批量删除", notes="历史贷款管户台账-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.lsdkghtzService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "历史贷款管户台账-通过id查询")
	@ApiOperation(value="历史贷款管户台账-通过id查询", notes="历史贷款管户台账-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Lsdkghtz lsdkghtz = lsdkghtzService.getById(id);
		return Result.ok(lsdkghtz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param lsdkghtz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Lsdkghtz lsdkghtz) {
      return super.exportXls(request, lsdkghtz, Lsdkghtz.class, "历史贷款管户台账");
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
      return super.importExcel(request, response, Lsdkghtz.class);
  }

}

package org.cmms.modules.sjxf.hxxt.ckjykb2.controller;

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
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.sjxf.hxxt.ckjykb2.entity.Ckjykb2;
import org.cmms.modules.sjxf.hxxt.ckjykb2.service.ICkjykb2Service;
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
 * @Description: 存款交易宽表2
 * @Author: jeecg-boot
 * @Date:   2021-12-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款交易宽表2")
@RestController
@RequestMapping("/ckjykb2/ckjykb2")
public class Ckjykb2Controller extends JeecgController<Ckjykb2, ICkjykb2Service> {
	@Autowired
	private ICkjykb2Service ckjykb2Service;

	/**
	 * 分页列表查询
	 *
	 * @param ckjykb2
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存款交易宽表2-分页列表查询")
	@ApiOperation(value="存款交易宽表2-分页列表查询", notes="存款交易宽表2-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ckjykb2 ckjykb2,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Ckjykb2> queryWrapper = QueryGenerator.initQueryWrapper(ckjykb2, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkjykb2Service.class,ckjykb2Service,pageNo,pageSize,queryWrapper,"trn_Date","trn_Time");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param ckjykb2
	 * @return
	 */
	@AutoLog(value = "存款交易宽表2-添加")
	@ApiOperation(value="存款交易宽表2-添加", notes="存款交易宽表2-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ckjykb2 ckjykb2) {
		ckjykb2Service.save(ckjykb2);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ckjykb2
	 * @return
	 */
	@AutoLog(value = "存款交易宽表2-编辑")
	@ApiOperation(value="存款交易宽表2-编辑", notes="存款交易宽表2-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ckjykb2 ckjykb2) {
		ckjykb2Service.updateById(ckjykb2);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款交易宽表2-通过id删除")
	@ApiOperation(value="存款交易宽表2-通过id删除", notes="存款交易宽表2-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ckjykb2Service.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "存款交易宽表2-批量删除")
	@ApiOperation(value="存款交易宽表2-批量删除", notes="存款交易宽表2-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckjykb2Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款交易宽表2-通过id查询")
	@ApiOperation(value="存款交易宽表2-通过id查询", notes="存款交易宽表2-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ckjykb2 ckjykb2 = ckjykb2Service.getById(id);
		return Result.ok(ckjykb2);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      //return super.exportXls(request, ckjykb2, Ckjykb2.class, "存款交易宽表2");
      QueryWrapper<Ckjykb2> queryWrapper = new QueryWrapper<>();
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Ckjykb2 ckjykb2 = JSON.parseObject(deString, Ckjykb2.class);
              queryWrapper = QueryGenerator.initQueryWrapper(ckjykb2, request.getParameterMap());
          }
      } catch (Throwable throwable) {
          throwable.printStackTrace();
          log.error("导出错误！存款交易宽表2："+throwable.getMessage());
      }
      queryWrapper.orderByDesc("trn_Date","trn_Time");
      List<Ckjykb2> pageList = ckjykb2Service.list(queryWrapper);

      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "存款交易宽表2");
      mv.addObject(NormalExcelConstants.CLASS, Ckjykb2.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("存款交易宽表2", "导出人:" + getLoginUser().getRealname(), "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
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
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, Ckjykb2.class);
  }

}

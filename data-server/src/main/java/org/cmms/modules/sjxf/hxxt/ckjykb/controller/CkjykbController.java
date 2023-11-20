package org.cmms.modules.sjxf.hxxt.ckjykb.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.cmms.modules.sjxf.hxxt.ckjykb.entity.Ckjykb;
import org.cmms.modules.sjxf.hxxt.ckjykb.service.ICkjykbService;
import org.cmms.modules.sjxf.hxxt.jjk.kzhglgx.entity.Kzhglgx;
import org.cmms.modules.sjxf.hxxt.jjk.kzhglgx.service.IKzhglgxService;
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
 * @Description: 存款交易宽表
 * @Author: jeecg-boot
 * @Date:   2021-12-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款交易宽表")
@RestController
@RequestMapping("/ckjykb/ckjykb")
public class CkjykbController extends JeecgController<Ckjykb, ICkjykbService> {
	@Autowired
	private ICkjykbService ckjykbService;
	 @Autowired
	 private IKzhglgxService iKzhglgxService;

	/**
	 * 分页列表查询
	 *
	 * @param ckjykb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存款交易宽表-分页列表查询")
	@ApiOperation(value="存款交易宽表-分页列表查询", notes="存款交易宽表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ckjykb ckjykb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String kh =ckjykb.getCardNo();
		ckjykb.setCardNo(null);

		QueryWrapper<Ckjykb> queryWrapper = QueryGenerator.initQueryWrapper(ckjykb, req.getParameterMap());
		if (StringUtils.isBlank(ckjykb.getAccNo()) && StringUtils.isNotBlank(kh)) {
			QueryWrapper<Kzhglgx> cbsc_link = new QueryWrapper<>();
			cbsc_link.eq("card", "0" + kh);
			Kzhglgx cbscLink = iKzhglgxService.getOne(cbsc_link, false);
			if (cbscLink != null) {
				queryWrapper.eq("acc_no", cbscLink.getAccount());
			} else {
				return Result.error("查询的卡号在核心系统中没有找到对应的存款账号，请确认卡号是否正确！");
			}
		}
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkjykbService.class,ckjykbService,pageNo,pageSize,queryWrapper,"trn_date","trn_time");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param ckjykb
	 * @return
	 */
	@AutoLog(value = "存款交易宽表-添加")
	@ApiOperation(value="存款交易宽表-添加", notes="存款交易宽表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ckjykb ckjykb) {
		ckjykbService.save(ckjykb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ckjykb
	 * @return
	 */
	@AutoLog(value = "存款交易宽表-编辑")
	@ApiOperation(value="存款交易宽表-编辑", notes="存款交易宽表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ckjykb ckjykb) {
		ckjykbService.updateById(ckjykb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款交易宽表-通过id删除")
	@ApiOperation(value="存款交易宽表-通过id删除", notes="存款交易宽表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ckjykbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "存款交易宽表-批量删除")
	@ApiOperation(value="存款交易宽表-批量删除", notes="存款交易宽表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckjykbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款交易宽表-通过id查询")
	@ApiOperation(value="存款交易宽表-通过id查询", notes="存款交易宽表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ckjykb ckjykb = ckjykbService.getById(id);
		return Result.ok(ckjykb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      //return super.exportXls(request, ckjykb, Ckjykb.class, "存款交易宽表");
      QueryWrapper<Ckjykb> queryWrapper = new QueryWrapper<>();
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Ckjykb ckjykb = JSON.parseObject(deString, Ckjykb.class);
              queryWrapper = QueryGenerator.initQueryWrapper(ckjykb, request.getParameterMap());
          }
      } catch (Throwable throwable) {
          throwable.printStackTrace();
          log.error("导出错误！存款交易宽表："+throwable.getMessage());
      }
      queryWrapper.orderByDesc("trn_date","trn_time");
      List<Ckjykb> pageList = ckjykbService.list(queryWrapper);

      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "存款交易宽表");
      mv.addObject(NormalExcelConstants.CLASS, Ckjykb.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("存款交易宽表", "导出人:" + getLoginUser().getRealname(), "导出信息"));
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
      return super.importExcel(request, response, Ckjykb.class);
  }

}

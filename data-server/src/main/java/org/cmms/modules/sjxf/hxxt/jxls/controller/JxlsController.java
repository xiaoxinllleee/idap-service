package org.cmms.modules.sjxf.hxxt.jxls.controller;

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
import org.cmms.modules.sjxf.hxxt.jjk.kzhglgx.entity.Kzhglgx;
import org.cmms.modules.sjxf.hxxt.jjk.kzhglgx.service.IKzhglgxService;
import org.cmms.modules.sjxf.hxxt.jxls.entity.Jxls;
import org.cmms.modules.sjxf.hxxt.jxls.service.IJxlsService;
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
 * @Description: 结息流水
 * @Author: jeecg-boot
 * @Date:   2021-12-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="结息流水")
@RestController
@RequestMapping("/jxls/jxls")
public class JxlsController extends JeecgController<Jxls, IJxlsService> {
	 @Autowired
	 private IJxlsService jxlsService;
	 @Autowired
	 private IKzhglgxService iKzhglgxService;

	/**
	 * 分页列表查询
	 *
	 * @param jxls
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "结息流水-分页列表查询")
	@ApiOperation(value="结息流水-分页列表查询", notes="结息流水-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Jxls jxls,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String kh =jxls.getCardNo();
		jxls.setCardNo(null);
		QueryWrapper<Jxls> queryWrapper = QueryGenerator.initQueryWrapper(jxls, req.getParameterMap());
		queryWrapper.eq("trx_code", "730");
		if (StringUtils.isBlank(jxls.getAccNo()) && StringUtils.isNotBlank(kh)) {
			QueryWrapper<Kzhglgx> cbsc_link = new QueryWrapper<>();
			cbsc_link.eq("card", "0" + kh);
			cbsc_link.eq("iso_type", "1");
			Kzhglgx cbscLink = iKzhglgxService.getOne(cbsc_link, false);
			if (cbscLink != null) {
				queryWrapper.eq("acc_no", cbscLink.getAccount());
			} else {
				return Result.error("查询的卡号在核心系统中没有找到对应的存款账号，请确认卡号是否正确！");
			}
		}
		IPage pageList = org.cmms.common.utils.PageUtil.toPage(IJxlsService.class, jxlsService, pageNo, pageSize, queryWrapper, "trn_date", "jrnl_no", "acc_no");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param jxls
	 * @return
	 */
	@AutoLog(value = "结息流水-添加")
	@ApiOperation(value="结息流水-添加", notes="结息流水-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Jxls jxls) {
		jxlsService.save(jxls);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param jxls
	 * @return
	 */
	@AutoLog(value = "结息流水-编辑")
	@ApiOperation(value="结息流水-编辑", notes="结息流水-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Jxls jxls) {
		jxlsService.updateById(jxls);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "结息流水-通过id删除")
	@ApiOperation(value="结息流水-通过id删除", notes="结息流水-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		jxlsService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "结息流水-批量删除")
	@ApiOperation(value="结息流水-批量删除", notes="结息流水-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.jxlsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "结息流水-通过id查询")
	@ApiOperation(value="结息流水-通过id查询", notes="结息流水-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Jxls jxls = jxlsService.getById(id);
		return Result.ok(jxls);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param jxls
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Jxls jxls) {
      return super.exportXls(request, jxls, Jxls.class, "结息流水");
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
      return super.importExcel(request, response, Jxls.class);
  }

}

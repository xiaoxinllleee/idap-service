package org.cmms.modules.yjgl.ygdkywfx.ygghdkmx.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
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
import org.cmms.config.RequestDataHelper;
import org.cmms.modules.yjgl.ygdkywfx.ygghdkmx.entity.Ygghdkmx;
import org.cmms.modules.yjgl.ygdkywfx.ygghdkmx.mapper.YgghdkmxMapper;
import org.cmms.modules.yjgl.ygdkywfx.ygghdkmx.service.IYgghdkmxService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jsoup.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 员工管户贷款明细
 * @Author: jeecg-boot
 * @Date:   2023-07-13
 * @Version: V1.0
 */
@Slf4j
@Api(tags="员工管户贷款明细")
@RestController
@RequestMapping("/ygghdkmx/ygghdkmx")
public class YgghdkmxController extends JeecgController<Ygghdkmx, IYgghdkmxService> {
	@Autowired
	private IYgghdkmxService ygghdkmxService;
	@Autowired(required = false)
	private YgghdkmxMapper ygghdkmxMapper;

	/**
	 * 分页列表查询
	 *
	 * @param ygghdkmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "员工管户贷款明细-分页列表查询")
	@ApiOperation(value="员工管户贷款明细-分页列表查询", notes="员工管户贷款明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ygghdkmx ygghdkmx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		Date dateTime = DateUtil.parseCST(ygghdkmx.getTjrq().toString());
		String yymmdd = dateTime.toString().replaceAll("-","").substring(2,8);
		RequestDataHelper.setRequestData("Tb_dk_ygghdksjmx", "Tb_dk_ygghdksjmx" + "_" + yymmdd);
		QueryWrapper<Ygghdkmx> queryWrapper = QueryGenerator.initQueryWrapper(ygghdkmx, req.getParameterMap());
		Page<Ygghdkmx> page = new Page<Ygghdkmx>(pageNo, pageSize);
		IPage<Ygghdkmx> pageList = ygghdkmxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param ygghdkmx
	 * @return
	 */
	@AutoLog(value = "员工管户贷款明细-添加")
	@ApiOperation(value="员工管户贷款明细-添加", notes="员工管户贷款明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ygghdkmx ygghdkmx) {
		ygghdkmxService.save(ygghdkmx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ygghdkmx
	 * @return
	 */
	@AutoLog(value = "员工管户贷款明细-编辑")
	@ApiOperation(value="员工管户贷款明细-编辑", notes="员工管户贷款明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ygghdkmx ygghdkmx) {
		ygghdkmxService.updateById(ygghdkmx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工管户贷款明细-通过id删除")
	@ApiOperation(value="员工管户贷款明细-通过id删除", notes="员工管户贷款明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ygghdkmxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "员工管户贷款明细-批量删除")
	@ApiOperation(value="员工管户贷款明细-批量删除", notes="员工管户贷款明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ygghdkmxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工管户贷款明细-通过id查询")
	@ApiOperation(value="员工管户贷款明细-通过id查询", notes="员工管户贷款明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ygghdkmx ygghdkmx = ygghdkmxService.getById(id);
		return Result.ok(ygghdkmx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ygghdkmx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ygghdkmx ygghdkmx) {
	  Date dateTime = DateUtil.parseCST(ygghdkmx.getTjrq().toString());
	  String yymmdd = dateTime.toString().replaceAll("-","").substring(2,8);
	  RequestDataHelper.setRequestData("Tb_dk_ygghdksjmx", "Tb_dk_ygghdksjmx" + "_" + yymmdd);
      return super.exportXls(request, ygghdkmx, Ygghdkmx.class, "员工管户贷款明细");
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
      return super.importExcel(request, response, Ygghdkmx.class);
  }
	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/init")
	 public Result<?> init(@RequestBody JSONObject jsonObject) {
		 String ksrq = jsonObject.getString("ksrq");
		 String jsrq = jsonObject.getString("jsrq");
		 String jgdm = jsonObject.getString("jgdm");
		 String yggh = jsonObject.getString("yggh");
		 ksrq = ksrq.replaceAll("-", "");
		 jsrq = jsrq.replaceAll("-", "");
		 if (StringUtil.isBlank(yggh)){
			 yggh="";
		 }
		 if (StringUtil.isBlank(jgdm)){
		 	jgdm="";
		 }
		 Result result = new Result<>();
		 try {
			 ygghdkmxMapper.init(ksrq, jsrq,jgdm,yggh);
			 result.setSuccess(true);
			 return result;
		 } catch (Exception e) {
			 log.error("提取失败", e.getMessage());
			 result.setMessage(e.getMessage());
			 result.setSuccess(false);
		 }
		 return result;
	 }
}

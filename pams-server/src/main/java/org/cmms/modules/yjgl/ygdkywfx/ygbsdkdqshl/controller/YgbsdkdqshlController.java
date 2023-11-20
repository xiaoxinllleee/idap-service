package org.cmms.modules.yjgl.ygdkywfx.ygbsdkdqshl.controller;

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
import org.cmms.modules.yjgl.ygdkywfx.ygbsdkdqshl.entity.Ygbsdkdqshl;
import org.cmms.modules.yjgl.ygdkywfx.ygbsdkdqshl.mapper.YgbsdkdqshlMapper;
import org.cmms.modules.yjgl.ygdkywfx.ygbsdkdqshl.service.IYgbsdkdqshlService;
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
 * @Description: 员工包收贷款到期收回率
 * @Author: jeecg-boot
 * @Date:   2023-07-13
 * @Version: V1.0
 */
@Slf4j
@Api(tags="员工包收贷款到期收回率")
@RestController
@RequestMapping("/ygbsdkdqshl/ygbsdkdqshl")
public class YgbsdkdqshlController extends JeecgController<Ygbsdkdqshl, IYgbsdkdqshlService> {
	@Autowired
	private IYgbsdkdqshlService ygbsdkdqshlService;
	@Autowired(required = false)
	private YgbsdkdqshlMapper ygbsdkdqshlMapper;

	/**
	 * 分页列表查询
	 *
	 * @param ygbsdkdqshl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "员工包收贷款到期收回率-分页列表查询")
	@ApiOperation(value="员工包收贷款到期收回率-分页列表查询", notes="员工包收贷款到期收回率-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ygbsdkdqshl ygbsdkdqshl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		Date dateTime = DateUtil.parseCST(ygbsdkdqshl.getTjyf().toString());
		String yyyy = dateTime.toString().replaceAll("-","").substring(0,4);
		RequestDataHelper.setRequestData("Tb_dk_ygbsdkdqshl", "Tb_dk_ygbsdkdqshl" + "_" + yyyy);
		QueryWrapper<Ygbsdkdqshl> queryWrapper = QueryGenerator.initQueryWrapper(ygbsdkdqshl, req.getParameterMap());
		Page<Ygbsdkdqshl> page = new Page<Ygbsdkdqshl>(pageNo, pageSize);
		IPage<Ygbsdkdqshl> pageList = ygbsdkdqshlService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param ygbsdkdqshl
	 * @return
	 */
	@AutoLog(value = "员工包收贷款到期收回率-添加")
	@ApiOperation(value="员工包收贷款到期收回率-添加", notes="员工包收贷款到期收回率-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ygbsdkdqshl ygbsdkdqshl) {
		ygbsdkdqshlService.save(ygbsdkdqshl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ygbsdkdqshl
	 * @return
	 */
	@AutoLog(value = "员工包收贷款到期收回率-编辑")
	@ApiOperation(value="员工包收贷款到期收回率-编辑", notes="员工包收贷款到期收回率-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ygbsdkdqshl ygbsdkdqshl) {
		ygbsdkdqshlService.updateById(ygbsdkdqshl);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工包收贷款到期收回率-通过id删除")
	@ApiOperation(value="员工包收贷款到期收回率-通过id删除", notes="员工包收贷款到期收回率-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ygbsdkdqshlService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "员工包收贷款到期收回率-批量删除")
	@ApiOperation(value="员工包收贷款到期收回率-批量删除", notes="员工包收贷款到期收回率-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ygbsdkdqshlService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工包收贷款到期收回率-通过id查询")
	@ApiOperation(value="员工包收贷款到期收回率-通过id查询", notes="员工包收贷款到期收回率-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ygbsdkdqshl ygbsdkdqshl = ygbsdkdqshlService.getById(id);
		return Result.ok(ygbsdkdqshl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ygbsdkdqshl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ygbsdkdqshl ygbsdkdqshl) {
	  Date dateTime = DateUtil.parseCST(ygbsdkdqshl.getTjyf().toString());
	  String yyyy = dateTime.toString().replaceAll("-","").substring(0,4);
	  RequestDataHelper.setRequestData("Tb_dk_ygbsdkdqshl", "Tb_dk_ygbsdkdqshl" + "_" + yyyy);
      return super.exportXls(request, ygbsdkdqshl, Ygbsdkdqshl.class, "员工包收贷款到期收回率");
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
      return super.importExcel(request, response, Ygbsdkdqshl.class);
  }

	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/init")
	 public Result<?> init(@RequestBody JSONObject jsonObject) {
		 String tjyf = jsonObject.getString("tjyf");
		 String jgdm = jsonObject.getString("jgdm");
		 String yggh = jsonObject.getString("yggh");
		 tjyf = tjyf.replaceAll("-", "");
		 if (StringUtil.isBlank(yggh)){
			 yggh="";
		 }
		 if (StringUtil.isBlank(jgdm)){
			 jgdm="";
		 }
		 Result result = new Result<>();
		 try {
			 ygbsdkdqshlMapper.init(tjyf,jgdm,yggh);
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

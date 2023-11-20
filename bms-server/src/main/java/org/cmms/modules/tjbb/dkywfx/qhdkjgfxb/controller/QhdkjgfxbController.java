package org.cmms.modules.tjbb.dkywfx.qhdkjgfxb.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjbb.dkywfx.qhdkjgfxb.entity.Qhdkjgfxb;
import org.cmms.modules.tjbb.dkywfx.qhdkjgfxb.mapper.QhdkjgfxbMapper;
import org.cmms.modules.tjbb.dkywfx.qhdkjgfxb.service.IQhdkjgfxbService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.PageUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 全行贷款结构分析表
 * @Author: jeecg-boot
 * @Date:   2021-10-20
 * @Version: V1.0
 */
@Slf4j
@Api(tags="全行贷款结构分析表")
@RestController
@RequestMapping("/qhdkjgfxb/qhdkjgfxb")
public class QhdkjgfxbController extends JeecgController<Qhdkjgfxb, IQhdkjgfxbService> {
	 @Autowired
	 private IQhdkjgfxbService qhdkjgfxbService;
	 @Autowired
	 private RedisUtil redisUtil;
	 @Value("${com.etl.sfdsjpt}")
	 private String sfdsjpt;

	/**
	 * 分页列表查询
	 *
	 * @param qhdkjgfxb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "全行贷款结构分析表-分页列表查询")
	@ApiOperation(value="全行贷款结构分析表-分页列表查询", notes="全行贷款结构分析表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Qhdkjgfxb qhdkjgfxb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(qhdkjgfxb, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IQhdkjgfxbService.class,qhdkjgfxbService,pageNo,pageSize,queryWrapper,"tjrq","jeqj");
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/init")
	 public Result<?> init(@RequestBody JSONObject jsonObject) {
		 String tjrq = jsonObject.getString("tjrq");
		 if (-1 == DateUtil.getCurrentTS().compareTo(DateUtil.parseDateFormat(tjrq, "yyyy-MM-dd"))) {
			 return Result.error("所选日期必须小于当前系统日期！");
		 }
		 Result result = new Result<>();
		 if ("true".equalsIgnoreCase(sfdsjpt)) {
			 HashMap<String, String> params = new HashMap<>();
			 params.put("fiscal_date", tjrq);
			 params.put("etl_task","kiss.domain.application.tjbb.proc_tjbb_dkyw_dkjgfxb_qh");
			 // count_tjbb_dkyw_dkjgfxb_qh
			 boolean falg = EtlUtil.callEtl("tjbb_common_init", params, 15);
			 result.setSuccess(falg);
		 } else {
			 try {
				 qhdkjgfxbService.pDkjgfxb(tjrq.replaceAll("-", ""));
				 result.setSuccess(true);
				 return result;
			 } catch (Exception e) {
				 log.error("提取失败", e.getMessage());
				 result.setMessage(e.getMessage());
				 result.setSuccess(false);
			 }
		 }
		 return result;
	 }

	/**
	 * 添加
	 *
	 * @param qhdkjgfxb
	 * @return
	 */
	@AutoLog(value = "全行贷款结构分析表-添加")
	@ApiOperation(value="全行贷款结构分析表-添加", notes="全行贷款结构分析表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Qhdkjgfxb qhdkjgfxb) {
		qhdkjgfxbService.save(qhdkjgfxb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param qhdkjgfxb
	 * @return
	 */
	@AutoLog(value = "全行贷款结构分析表-编辑")
	@ApiOperation(value="全行贷款结构分析表-编辑", notes="全行贷款结构分析表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Qhdkjgfxb qhdkjgfxb) {
		qhdkjgfxbService.updateById(qhdkjgfxb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行贷款结构分析表-通过id删除")
	@ApiOperation(value="全行贷款结构分析表-通过id删除", notes="全行贷款结构分析表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		qhdkjgfxbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "全行贷款结构分析表-批量删除")
	@ApiOperation(value="全行贷款结构分析表-批量删除", notes="全行贷款结构分析表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.qhdkjgfxbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行贷款结构分析表-通过id查询")
	@ApiOperation(value="全行贷款结构分析表-通过id查询", notes="全行贷款结构分析表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Qhdkjgfxb qhdkjgfxb = qhdkjgfxbService.getById(id);
		return Result.ok(qhdkjgfxb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param qhdkjgfxb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Qhdkjgfxb qhdkjgfxb) {
      return super.exportXls(request, qhdkjgfxb, Qhdkjgfxb.class, "全行贷款结构分析表");
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
      return super.importExcel(request, response, Qhdkjgfxb.class);
  }

}

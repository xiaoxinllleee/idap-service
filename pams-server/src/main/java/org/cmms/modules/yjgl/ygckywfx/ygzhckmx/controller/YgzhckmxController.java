package org.cmms.modules.yjgl.ygckywfx.ygzhckmx.controller;

import java.util.Arrays;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.config.RequestDataHelper;
import org.cmms.modules.yjgl.ygckywfx.ygtzckmx.mapper.YgtzckmxMapper;
import org.cmms.modules.yjgl.ygckywfx.ygzhckmx.entity.Ygzhckmx;
import org.cmms.modules.yjgl.ygckywfx.ygzhckmx.mapper.YgzhckmxMapper;
import org.cmms.modules.yjgl.ygckywfx.ygzhckmx.service.IYgzhckmxService;

import org.jsoup.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 员工综合存款明细
 * @Author: jeecg-boot
 * @Date:   2023-07-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="员工综合存款明细")
@RestController
@RequestMapping("/ygzhckmx/ygzhckmx")
public class YgzhckmxController extends JeecgController<Ygzhckmx, IYgzhckmxService> {
	@Autowired
	private IYgzhckmxService ygzhckmxService;
	 @Autowired(required = false)
	 private YgzhckmxMapper ygzhckmxMapper;
	/**
	 * 分页列表查询
	 *
	 * @param ygzhckmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "员工综合存款明细-分页列表查询")
	@ApiOperation(value="员工综合存款明细-分页列表查询", notes="员工综合存款明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ygzhckmx ygzhckmx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		Date dateTime = DateUtil.parseCST(ygzhckmx.getTjrq().toString());
		String yymmdd = dateTime.toString().replaceAll("-","").substring(2,8);
		RequestDataHelper.setRequestData("Tb_ck_ygzhcksjmx", "Tb_ck_ygzhcksjmx" + "_" + yymmdd);
		QueryWrapper<Ygzhckmx> queryWrapper = QueryGenerator.initQueryWrapper(ygzhckmx, req.getParameterMap());
		Page<Ygzhckmx> page = new Page<Ygzhckmx>(pageNo, pageSize);
		IPage<Ygzhckmx> pageList = ygzhckmxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param ygzhckmx
	 * @return
	 */
	@AutoLog(value = "员工综合存款明细-添加")
	@ApiOperation(value="员工综合存款明细-添加", notes="员工综合存款明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ygzhckmx ygzhckmx) {
		ygzhckmxService.save(ygzhckmx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ygzhckmx
	 * @return
	 */
	@AutoLog(value = "员工综合存款明细-编辑")
	@ApiOperation(value="员工综合存款明细-编辑", notes="员工综合存款明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ygzhckmx ygzhckmx) {
		ygzhckmxService.updateById(ygzhckmx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工综合存款明细-通过id删除")
	@ApiOperation(value="员工综合存款明细-通过id删除", notes="员工综合存款明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ygzhckmxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "员工综合存款明细-批量删除")
	@ApiOperation(value="员工综合存款明细-批量删除", notes="员工综合存款明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ygzhckmxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工综合存款明细-通过id查询")
	@ApiOperation(value="员工综合存款明细-通过id查询", notes="员工综合存款明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ygzhckmx ygzhckmx = ygzhckmxService.getById(id);
		return Result.ok(ygzhckmx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ygzhckmx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ygzhckmx ygzhckmx) {
	  Date dateTime = DateUtil.parseCST(ygzhckmx.getTjrq().toString());
	  String yymmdd = dateTime.toString().replaceAll("-","").substring(2,8);
	  RequestDataHelper.setRequestData("Tb_ck_ygzhcksjmx", "Tb_ck_ygzhcksjmx" + "_" + yymmdd);
      return super.exportXls(request, ygzhckmx, Ygzhckmx.class, "员工综合存款明细");
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
      return super.importExcel(request, response, Ygzhckmx.class);
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
			 ygzhckmxMapper.init(ksrq, jsrq,jgdm,yggh);
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

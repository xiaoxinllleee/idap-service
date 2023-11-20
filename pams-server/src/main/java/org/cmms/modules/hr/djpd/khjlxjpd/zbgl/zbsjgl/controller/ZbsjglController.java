package org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjgl.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.jcraft.jsch.Session;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.EexcSshUtil;
import org.cmms.common.util.SshUtil020;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjgl.entity.Zbsjgl;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjgl.entity.ZbsjglVO;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjgl.mapper.ZbsjglMapper;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjgl.service.IZbsjglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjgl.verify.ZbsjgjImportVerify;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjtz.entity.Zbsjtz;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjtz.entity.ZbsjtzVO;
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
 * @Description: 指标数据管理
 * @Author: jeecg-boot
 * @Date:   2021-09-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags="指标数据管理")
@RestController
@RequestMapping("/zbsjgl/zbsjgl")
public class ZbsjglController extends JeecgController<Zbsjgl, IZbsjglService> {
	@Autowired
	private IZbsjglService zbsjglService;
	@Autowired
	private ZbsjgjImportVerify zbsjgjImportVerify;

	@Autowired
	private SshUtil020 sshUtil;
	@Autowired(required = false)
	private ZbsjglMapper zbsjglMapper;

	 @Value("${common.shell.username:root}")
	 private String username1;
	 @Value("${common.shell.password:root@123}")
	 private String password1;
	 @Value("${common.shell.ip:10.18.10.92}")//impala
	 private String ip1;

	 @Value("${common.shell.username:root}")
	 private String username2;
	 @Value("${common.shell.password:root@123}")
	 private String password2;
	 @Value("${common.shell.ip:10.18.10.90}")//Oracle
	 private String ip2;

	 @Value("${common.oracle.username:idap}")//Oracle
	 private String name;
	 @Value("${common.oracle.password:Ods24_db}")//Oracle
	 private String pwd;
	 @Value("${common.oracle.oracleUrl:jdbc:oracle:thin:@10.18.10.90:1521:ods}")//Oracle
	 private String oracleUrl;



	 /**
	 * 分页列表查询
	 *
	 * @param zbsjgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "指标数据管理-分页列表查询")
	@ApiOperation(value="指标数据管理-分页列表查询", notes="指标数据管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zbsjgl zbsjgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zbsjgl> queryWrapper = QueryGenerator.initQueryWrapper(zbsjgl, req.getParameterMap());
		Page<Zbsjgl> page = new Page<Zbsjgl>(pageNo, pageSize);
		IPage<Zbsjgl> pageList = zbsjglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  */
	 @DS("eweb")
	 @RequestMapping(value = "/init")
	 public Result<?> init(@RequestBody JSONObject jsonObject){
		 String pdzq = jsonObject.getString("pdzq");
		 String pdrq = jsonObject.getString("pdrq");
		 log.info(pdzq+"=====评定周期");
		 log.info(pdrq.replaceAll("-","")+"=====评定日期");
		 try{
			 sshUtil.execShell("sh /home/csvdata/export.sh");
			 Session session= EexcSshUtil.getJSchSession("10.18.10.90", 22, "oracle", "root@123");
			 String  execShell ="source .bashrc && sh /home/oracle/csvdata/load_to_oracle.sh";
			 List<String> result = EexcSshUtil.getCmdResult(session, execShell);
			 result.forEach((x)-> log.info("========="+x));
			 EexcSshUtil.closeJSchSession(session);

			 zbsjglMapper.pZbsjgl(pdzq,pdrq.replaceAll("-",""));
		 }catch (Throwable e){
			 e.printStackTrace();
			 return Result.error("提取失败，请查看系统监控日志信息");
		 }
		 return Result.ok("提取成功");
	 }


	/**
	 * 添加
	 *
	 * @param zbsjgl
	 * @return
	 */
	@AutoLog(value = "指标数据管理-添加")
	@ApiOperation(value="指标数据管理-添加", notes="指标数据管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zbsjgl zbsjgl) {
		zbsjglService.save(zbsjgl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zbsjgl
	 * @return
	 */
	@AutoLog(value = "指标数据管理-编辑")
	@ApiOperation(value="指标数据管理-编辑", notes="指标数据管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zbsjgl zbsjgl) {
		QueryWrapper<Zbsjgl> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("pdzq",zbsjgl.getPdzq());
		queryWrapper.eq("pdrq",zbsjgl.getPdrq());
		queryWrapper.eq("zzbz",zbsjgl.getZzbz());
		queryWrapper.eq("gwbz",zbsjgl.getGwbz());
		queryWrapper.eq("yggh",zbsjgl.getYggh());
		queryWrapper.eq("zbid",zbsjgl.getZbid());
		zbsjglService.update(zbsjgl,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "指标数据管理-通过id删除")
	@ApiOperation(value="指标数据管理-通过id删除", notes="指标数据管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zbsjglService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "指标数据管理-批量删除")
	@ApiOperation(value="指标数据管理-批量删除", notes="指标数据管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zbsjglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "指标数据管理-通过id查询")
	@ApiOperation(value="指标数据管理-通过id查询", notes="指标数据管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zbsjgl zbsjgl = zbsjglService.getById(id);
		return Result.ok(zbsjgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zbsjgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zbsjgl zbsjgl) {
      return super.exportXls(request, zbsjgl, Zbsjgl.class, "指标数据管理");
  }


	 /**
	  * 导入模板
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "指标数据管理导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, ZbsjglVO.class);
		 ExportParams exportParams = new ExportParams("指标数据管理导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
	 }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
	  return super.importExcelByTemplate(jsonObject, request, response, Zbsjgl.class,ZbsjglVO.class,zbsjgjImportVerify);
  }

}

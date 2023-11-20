package org.cmms.modules.ywgl.cdkfx.qhdkkhckhbl.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.system.service.ISysLogService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.ywgl.cdkfx.dkkhckhbl.entity.ModCdkfxKhjldkkhckhblD;
import org.cmms.modules.ywgl.cdkfx.qhdkkhckhbl.entity.ModCdkfxQhdkkhckhbl;
import org.cmms.modules.ywgl.cdkfx.qhdkkhckhbl.service.IModCdkfxQhdkkhckhblService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ywgl.cdkfx.util.mapper.CallToolMapper;
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
 * @Description: 全行贷款客户存款回报率
 * @Author: jeecg-boot
 * @Date:   2021-07-02
 * @Version: V1.0
 */
@Slf4j
@Api(tags="全行贷款客户存款回报率")
@RestController
@RequestMapping("/qhdkkhckhbl/modCdkfxQhdkkhckhbl")
public class ModCdkfxQhdkkhckhblController extends JeecgController<ModCdkfxQhdkkhckhbl, IModCdkfxQhdkkhckhblService> {
	@Autowired
	private IModCdkfxQhdkkhckhblService modCdkfxQhdkkhckhblService;
	 @Value("${com.etl.sfdsjpt}")
	 private String sfdsjpt;
	 @Autowired
	 private ISysLogService sysLogService;
	/**
	 * 分页列表查询
	 *
	 * @param modCdkfxQhdkkhckhbl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "全行贷款客户存款回报率-分页列表查询")
	@ApiOperation(value="全行贷款客户存款回报率-分页列表查询", notes="全行贷款客户存款回报率-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ModCdkfxQhdkkhckhbl modCdkfxQhdkkhckhbl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ModCdkfxQhdkkhckhbl> queryWrapper = QueryGenerator.initQueryWrapper(modCdkfxQhdkkhckhbl, req.getParameterMap());
		IPage pageList = PageUtil.toPage(IModCdkfxQhdkkhckhblService.class,modCdkfxQhdkkhckhblService, pageNo, pageSize, queryWrapper, "ckye","tjyf","dkye");
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  */
	 @RequestMapping(value="/count")
	 public Result<?> count(String tjyf){
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 tjyf = tjyf.replaceAll("-", "");
		 Result result = new Result<>();
		 boolean flag = true;
		 String i_tjyf = tjyf.replaceAll("-", "");
		 String i_lrczy = loginUser.getRealname();
		 Date dksjrq = DateUtil.string2Date(sysLogService.dksjrqBig(),"yyyyMMdd");
		 String tjyf_zxrq = org.cmms.modules.util.DateUtil.getSjQmrq(i_tjyf, dksjrq, "yyyy-MM-dd");
		 HashMap<String, String> param = new HashMap<>();
		 if ("true".equalsIgnoreCase(sfdsjpt)) {
			 //存储调度-1：count_cdkfx_p_n_temp_dkyeb
			 if (flag) {
				 String lv_khzr_tablename = "";
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				 String format = sdf.format(new Date());
				 if (format.substring(0, 6).equals(i_tjyf.substring(0, 6))) {
					 lv_khzr_tablename = "ERP_BAS_DKZHKHZR WHERE 1=1";
				 }else{
					 lv_khzr_tablename = "ERP_BAS_DKZHKHZR_HIS WHERE fiscal_date = '"+i_tjyf+"'";
				 }
				 try {
					 lv_khzr_tablename = new String(lv_khzr_tablename.getBytes(), "UTF-8");
				 } catch (UnsupportedEncodingException e) {
					 e.printStackTrace();
				 }
				 //param.put("i_tjyf", tjyf_zxrq);
				 param.put("i_tjyf",i_tjyf);
				 param.put("lv_khzr_tablename", lv_khzr_tablename);
				 param.put("i_ycrq", i_tjyf);
				 param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_n_temp_dkyeb");
				 // count_cdkfx_p_n_temp_dkyeb
				 flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
				 if (!flag) {
					 return Result.error("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
				 }
			 }
			 //存储调度-12：count_cdkfx_p_dkfx_dkkhckhbl
			 if (flag) {
				 param = new HashMap<>();
				 param.put("i_tjyf", i_tjyf);
				 param.put("i_qmrq", tjyf_zxrq);
				 param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_dkfx_dkkhckhbl");
				 // count_cdkfx_p_dkfx_dkkhckhbl
				 flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
				 if (!flag) {
					 return Result.error("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
				 }
			 }
			 result.setSuccess(flag);
		 } else {
			 try {
				 modCdkfxQhdkkhckhblService.pDkfxDkkhckhbl(tjyf);
				 result.setSuccess(true);
				 return result;
			 } catch (Exception e) {
				 System.out.println(e);
				 log.error("提取失败", e.getMessage());
				 result.setSuccess(false);
			 }
		 }
		 return result;
	 }

	/**
	 * 添加
	 *
	 * @param modCdkfxQhdkkhckhbl
	 * @return
	 */
	@AutoLog(value = "全行贷款客户存款回报率-添加")
	@ApiOperation(value="全行贷款客户存款回报率-添加", notes="全行贷款客户存款回报率-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ModCdkfxQhdkkhckhbl modCdkfxQhdkkhckhbl) {
		modCdkfxQhdkkhckhblService.save(modCdkfxQhdkkhckhbl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param modCdkfxQhdkkhckhbl
	 * @return
	 */
	@AutoLog(value = "全行贷款客户存款回报率-编辑")
	@ApiOperation(value="全行贷款客户存款回报率-编辑", notes="全行贷款客户存款回报率-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ModCdkfxQhdkkhckhbl modCdkfxQhdkkhckhbl) {
		modCdkfxQhdkkhckhblService.updateById(modCdkfxQhdkkhckhbl);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行贷款客户存款回报率-通过id删除")
	@ApiOperation(value="全行贷款客户存款回报率-通过id删除", notes="全行贷款客户存款回报率-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		modCdkfxQhdkkhckhblService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "全行贷款客户存款回报率-批量删除")
	@ApiOperation(value="全行贷款客户存款回报率-批量删除", notes="全行贷款客户存款回报率-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.modCdkfxQhdkkhckhblService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行贷款客户存款回报率-通过id查询")
	@ApiOperation(value="全行贷款客户存款回报率-通过id查询", notes="全行贷款客户存款回报率-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ModCdkfxQhdkkhckhbl modCdkfxQhdkkhckhbl = modCdkfxQhdkkhckhblService.getById(id);
		return Result.ok(modCdkfxQhdkkhckhbl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param modCdkfxQhdkkhckhbl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ModCdkfxQhdkkhckhbl modCdkfxQhdkkhckhbl) {
      return super.exportXls(request, modCdkfxQhdkkhckhbl, ModCdkfxQhdkkhckhbl.class, "全行贷款客户存款回报率");
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
      return super.importExcel(request, response, ModCdkfxQhdkkhckhbl.class);
  }

}

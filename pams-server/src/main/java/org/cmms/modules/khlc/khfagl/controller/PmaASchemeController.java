package org.cmms.modules.khlc.khfagl.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khlc.khfagl.entity.ErpAssessAljc;
import org.cmms.modules.khlc.khfagl.entity.ErpAssessPhjfk;
import org.cmms.modules.khlc.khfagl.entity.PmaAScheme;
import org.cmms.modules.khlc.khfagl.service.IErpAssessAljcService;
import org.cmms.modules.khlc.khfagl.service.IErpAssessPhjfkService;
import org.cmms.modules.khlc.khfagl.service.IPmaASchemeService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.entity.DpJdrwgl;
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
 * @Description: 考核方案基础信息表
 * @Author: jeecg-boot
 * @Date:   2021-01-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="考核方案基础信息表")
@RestController
@RequestMapping("/khlc/khfagl/pmaAScheme")
public class PmaASchemeController extends JeecgController<PmaAScheme, IPmaASchemeService> {
	@Autowired
	private IPmaASchemeService pmaASchemeService;
	@Autowired
	private IErpAssessPhjfkService erpAssessPhjfkService;
	@Autowired
	private IErpAssessAljcService erpAssessAljcService;
	
	/**
	 * 分页列表查询
	 *
	 * @param pmaAScheme
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "考核方案基础信息表-分页列表查询")
	@ApiOperation(value="考核方案基础信息表-分页列表查询", notes="考核方案基础信息表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PmaAScheme pmaAScheme,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
/*		QueryWrapper<PmaAScheme> queryWrapper = QueryGenerator.initQueryWrapper(pmaAScheme, req.getParameterMap());
		Page<PmaAScheme> page = new Page<PmaAScheme>(pageNo, pageSize);
		IPage<PmaAScheme> pageList = pmaASchemeService.page(page, queryWrapper);
		return Result.ok(pageList);*/

		Result<IPage<PmaAScheme>> result = new Result<IPage<PmaAScheme>>();
		Page<PmaAScheme> page = new Page<PmaAScheme>(pageNo, pageSize);
		String menuId = req.getParameter("menuId");
		String famc = req.getParameter("schemeName");
		IPage<PmaAScheme> pageList = pmaASchemeService.getSchenmeByJdId(page,menuId,famc);
		return Result.ok(pageList);


	}

	 /**
	  * 分页列表查询
	  *
	  * @param pmaAScheme
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "考核方案基础信息表-分页列表查询")
	 @ApiOperation(value="考核方案基础信息表-分页列表查询", notes="考核方案基础信息表-分页列表查询")
	 @GetMapping(value = "/list2")
	 public Result<?> queryPageList2(PmaAScheme pmaAScheme,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		QueryWrapper<PmaAScheme> queryWrapper = QueryGenerator.initQueryWrapper(pmaAScheme, req.getParameterMap());
		Page<PmaAScheme> page = new Page<PmaAScheme>(pageNo, pageSize);
		IPage<PmaAScheme> pageList = pmaASchemeService.page(page, queryWrapper);
		return Result.ok(pageList);
	 }
	
	/**
	 * 添加
	 *
	 * @param pmaAScheme
	 * @return
	 */
	@AutoLog(value = "考核方案基础信息表-添加")
	@ApiOperation(value="考核方案基础信息表-添加", notes="考核方案基础信息表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PmaAScheme pmaAScheme) {
		if(pmaAScheme.getOrgId()==null||pmaAScheme.getOrgId().equals("")){
			LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			pmaAScheme.setOrgId(sysUser.getOrgCode());
		}
		pmaASchemeService.saveOrUpdate(pmaAScheme);
		return Result.ok("添加成功！");
	}


	 /**
	  * 冻结&解冻用户
	  * @param jsonObject
	  * @return
	  */
	 @RequestMapping(value = "/sfqyBatch", method = RequestMethod.PUT)
	 public Result<PmaAScheme> sfqyBatch(@RequestBody JSONObject jsonObject) {
		 Result<PmaAScheme> result = new Result<PmaAScheme>();
		 try {
			 String id = jsonObject.getString("id");
			 String sfqy = jsonObject.getString("sfqy");
			 if(oConvertUtils.isNotEmpty(id)) {
				 pmaASchemeService.update(new PmaAScheme().setSfqy(sfqy),
						 new UpdateWrapper<PmaAScheme>().lambda().eq(PmaAScheme::getId,id));
			 }
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.error500("操作失败"+e.getMessage());
		 }
		 result.success("操作成功!");
		 return result;
	 }
	/**
	 * 编辑
	 *
	 * @param pmaAScheme
	 * @return
	 */
	@AutoLog(value = "考核方案基础信息表-编辑")
	@ApiOperation(value="考核方案基础信息表-编辑", notes="考核方案基础信息表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PmaAScheme pmaAScheme) {
		pmaASchemeService.updateById(pmaAScheme);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "考核方案基础信息表-通过id删除")
	@ApiOperation(value="考核方案基础信息表-通过id删除", notes="考核方案基础信息表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pmaASchemeService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "考核方案基础信息表-批量删除")
	@ApiOperation(value="考核方案基础信息表-批量删除", notes="考核方案基础信息表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pmaASchemeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "考核方案基础信息表-通过id查询")
	@ApiOperation(value="考核方案基础信息表-通过id查询", notes="考核方案基础信息表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PmaAScheme pmaAScheme = pmaASchemeService.getById(id);
		return Result.ok(pmaAScheme);
	}

	 /**
	  * 通过方案ID查询设置的指标列表
	  *
	  * @param schemeId
	  * @return
	  */
	 @ApiOperation(value="通过方案ID查询设置的指标列表", notes="通过方案ID查询设置的指标列表")
	 @GetMapping(value = "/queryZblistBySchemeId")
	 public Result<?> queryZblistBySchemeId(@RequestParam(name="schemeId",required=true) String schemeId) {
	 	 QueryWrapper<PmaAScheme> queryWrapper = new QueryWrapper<>();
	 	 queryWrapper.eq("scheme_id", schemeId);
		 PmaAScheme pmaAScheme = pmaASchemeService.getOne(queryWrapper);
		 if("1".equals(pmaAScheme.getCheckScene())) {
		 	//平衡计分卡
			 List<ErpAssessPhjfk> erpAssessPhjfkList = erpAssessPhjfkService.getZbxxBySchemeId(schemeId);
			 return Result.ok(erpAssessPhjfkList);
		 } else {
		 	//按量计酬
			 List<ErpAssessAljc> erpAssessAljcList = erpAssessAljcService.getZbxxBySchemeId(schemeId);
			 return Result.ok(erpAssessAljcList);
		 }
	 }

  /**
   * 导出excel
   *
   * @param request
   * @param pmaAScheme
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, PmaAScheme pmaAScheme) {
      return super.exportXls(request, pmaAScheme, PmaAScheme.class, "考核方案基础信息表");
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
      return super.importExcel(request, response, PmaAScheme.class);
  }

}

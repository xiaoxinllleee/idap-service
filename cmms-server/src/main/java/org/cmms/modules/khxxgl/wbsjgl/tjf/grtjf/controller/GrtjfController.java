package org.cmms.modules.khxxgl.wbsjgl.tjf.grtjf.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.MD5Util;
import org.cmms.modules.khxxgl.wbsjgl.tjf.grtjf.entity.Grtjf;
import org.cmms.modules.khxxgl.wbsjgl.tjf.grtjf.service.IGrtjfService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.system.entity.SysUserRole;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 个人碳积分
 * @Author: jeecg-boot
 * @Date:   2022-11-21
 * @Version: V1.0
 */
@Slf4j
@Api(tags="个人碳积分")
@RestController
@RequestMapping("/khxxgl.wbsjgl.grtjf/grtjf")
public class GrtjfController extends JeecgController<Grtjf, IGrtjfService> {
	@Autowired
	private IGrtjfService grtjfService;
	
	/**
	 * 分页列表查询
	 *
	 * @param grtjf
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "个人碳积分-分页列表查询")
	@ApiOperation(value="个人碳积分-分页列表查询", notes="个人碳积分-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Grtjf grtjf,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Grtjf> queryWrapper = QueryGenerator.initQueryWrapper(grtjf, req.getParameterMap());
		Page<Grtjf> page = new Page<Grtjf>(pageNo, pageSize);
		IPage<Grtjf> pageList = grtjfService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param grtjf
	 * @return
	 */
	@AutoLog(value = "个人碳积分-添加")
	@ApiOperation(value="个人碳积分-添加", notes="个人碳积分-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Grtjf grtjf) {
		grtjfService.save(grtjf);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param grtjf
	 * @return
	 */
	@AutoLog(value = "个人碳积分-编辑")
	@ApiOperation(value="个人碳积分-编辑", notes="个人碳积分-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Grtjf grtjf) {
		grtjfService.updateById(grtjf);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "个人碳积分-通过id删除")
	@ApiOperation(value="个人碳积分-通过id删除", notes="个人碳积分-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		grtjfService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "个人碳积分-批量删除")
	@ApiOperation(value="个人碳积分-批量删除", notes="个人碳积分-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.grtjfService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "个人碳积分-通过id查询")
	@ApiOperation(value="个人碳积分-通过id查询", notes="个人碳积分-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Grtjf grtjf = grtjfService.getById(id);
		return Result.ok(grtjf);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param grtjf
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Grtjf grtjf) {
      return super.exportXls(request, grtjf, Grtjf.class, "个人碳积分");
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
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  String filePaths = jsonObject.getString("filePaths");
	  if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
		  return Result.error("请先上传文件！");
	  }
	  String[] filePathList = filePaths.split(",");
	  JSONObject obj = new JSONObject();
	  for (String filePath : filePathList) {
		  String baseFilePath = uploadpath + File.separator + filePath;
		  File file = new File(baseFilePath);
		  ImportParams params = new ImportParams();
		  params.setTitleRows(1);
		  params.setHeadRows(1);
		  params.setNeedSave(false);
		  FileOutputStream fos = null;
		  try {
			  ExcelImportResult<Grtjf> importResult = ExcelImportUtil.importExcelVerify(file, Grtjf.class, params);
			  List<Grtjf> list = importResult.getList();
			  List<Grtjf> ygddxxList = new ArrayList<>();
			  for (Grtjf ygddxx : list) {
				  ygddxx.setCreateBy(sysUser.getWorkNo());
				  ygddxx.setCreateTime(new Date());
				  ygddxx.setSrfs("0");
				  ygddxx.setSjly("第三方数据");
				  ygddxxList.add(ygddxx);
			  }
			  service.saveBatch(ygddxxList);
			  obj.put("filePath", filePath);
			  fos = new FileOutputStream(baseFilePath);
			  importResult.getWorkbook().write(fos);
			  fos.flush();
			  fos.close();
			  return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
		  } catch (Exception e) {
			  log.error(e.getMessage(), e);
			  return Result.error("文件导入失败:" + e.getMessage());
		  } finally {
			  IoUtil.close(fos);
		  }
	  }
	  return Result.ok("文件导入失败！");
  }

	 /**
	  * 导入模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 return super.exportTemplateXls(Grtjf.class, "个人碳积分导入模板");
	 }

}

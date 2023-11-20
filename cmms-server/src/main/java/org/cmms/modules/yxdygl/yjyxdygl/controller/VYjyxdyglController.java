package org.cmms.modules.yxdygl.yjyxdygl.controller;

import java.io.File;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.util.JwtUtil;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.utils.AreaUtil;
import org.cmms.modules.yxdygl.yjyxdygl.entity.VYjyxdygl;
import org.cmms.modules.yxdygl.yjyxdygl.entity.Yjyxdygl;
import org.cmms.modules.yxdygl.yxdyfjxx.entity.Yxdyfjxx;
import org.cmms.modules.yxdygl.yjyxdygl.service.IVYjyxdyglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.yxdygl.yjyxdygl.service.IYjyxdyglService;
import org.cmms.modules.yxdygl.yxdyfjxx.service.IYxdyfjxxService;
import org.cmms.modules.yxdygl.yjyxdygl.vo.YjyxdyglPage;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 一级营销单元管理
 * @Author: Penghr
 * @Date:   2020-07-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="一级营销单元管理")
@RestController
@RequestMapping("/yxdygl/yjyxdygl")
public class VYjyxdyglController extends JeecgController<VYjyxdygl, IVYjyxdyglService> {
	@Autowired
	private IVYjyxdyglService vYjyxdyglService;
	@Autowired
	private IYjyxdyglService iYjyxdyglService;
	@Autowired
	private IYxdyfjxxService yxdyfjxxService;
	@Value(value = "${common.path.upload}")
	private String uploadPath;
	 @Autowired
	 private AreaUtil areaUtil;
	/**
	 * 分页列表查询
	 *
	 * @param vYjyxdygl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "一级营销单元管理-分页列表查询")
	@ApiOperation(value="一级营销单元管理-分页列表查询", notes="一级营销单元管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VYjyxdygl vYjyxdygl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VYjyxdygl> queryWrapper = QueryGenerator.initQueryWrapper(vYjyxdygl, req.getParameterMap());
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String username = sysUser.getUsername();
		if (!"admin".equals(username)) {
			Map<Object, Object> map = areaUtil.getYjyxdyqx(req);
			if (map != null && map.size() > 0) {
				queryWrapper.in("dybh", map.keySet());
			} else {
				queryWrapper.isNull("dybh");
			}
		}
		Page<VYjyxdygl> page = new Page<VYjyxdygl>(pageNo, pageSize);
		IPage<VYjyxdygl> pageList = vYjyxdyglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 分页列表查询
	  *
	  * @param vYjyxdygl
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "一级营销单元管理-列表查询")
	 @ApiOperation(value="一级营销单元管理-列表查询", notes="一级营销单元管理-列表查询")
	 @GetMapping(value = "/listAll")
	 public Result<?> queryListAll(VYjyxdygl vYjyxdygl,
									HttpServletRequest req) {
		 QueryWrapper<VYjyxdygl> queryWrapper = QueryGenerator.initQueryWrapper(vYjyxdygl, req.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String username = sysUser.getUsername();
		 if (!"admin".equals(username)) {
			 Map<Object, Object> map = areaUtil.getYjyxdyqx(req);
			 if (map != null && map.size() > 0) {
				 queryWrapper.in("dybh", map.keySet());
			 } else {
				 queryWrapper.isNull("dybh");
			 }
		 }
		 List<VYjyxdygl> pageList = vYjyxdyglService.list(queryWrapper);
		 return Result.ok(pageList);
	 }

	/**
	 * 添加
	 *
	 * @param yjyxdyglPage
	 * @return
	 */
	@AutoLog(value = "一级营销单元管理-添加")
	@ApiOperation(value="一级营销单元管理-添加", notes="一级营销单元管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody YjyxdyglPage yjyxdyglPage) {
		Result<Yjyxdygl> result = new Result<Yjyxdygl>();
		try {
			Yjyxdygl yjyxdygl = new Yjyxdygl();
			BeanUtils.copyProperties(yjyxdyglPage, yjyxdygl);
			iYjyxdyglService.save(yjyxdygl);

			// TODO: 附件上传功能未开发，添加保存暂时无法正常使用
			//iYjyxdyglService.saveMain(yjyxdygl, yjyxdyglPage.getYxdyfjxxList());
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
//		iYjyxdyglService.save(yjyxdygl);
//		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param yjyxdyglPage
	 * @return
	 */
	@AutoLog(value = "一级营销单元管理-编辑")
	@ApiOperation(value="一级营销单元管理-编辑", notes="一级营销单元管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody YjyxdyglPage yjyxdyglPage, HttpServletRequest req) {
		Result<Yjyxdygl> result = new Result<Yjyxdygl>();
		Yjyxdygl yjyxdygl = new Yjyxdygl();
		BeanUtils.copyProperties(yjyxdyglPage, yjyxdygl);
		Yjyxdygl yjyxdyglEntity = iYjyxdyglService.getById(yjyxdygl.getId());
		List<Yxdyfjxx> yxdyfjxxList = yjyxdyglPage.getYxdyfjxxList();
		if (yjyxdyglEntity == null) {
			result.error500("未找到对应实体");
		} else {
//			boolean ok = iYjyxdyglService.updateById(yjyxdygl);
			/*iYjyxdyglService.updateMain(yjyxdygl, yjyxdyglPage.getYxdyfjxxList());*/
			iYjyxdyglService.updateById(yjyxdygl);
			/*List<String> deleteList = yjyxdyglPage.getDeleteIds();
			if (!deleteList.isEmpty()) {
				yxdyfjxxService.removeByIds(deleteList);
			}*/
			result.success("修改成功!");
		}
		return result;
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "一级营销单元管理-通过id删除")
	@ApiOperation(value="一级营销单元管理-通过id删除", notes="一级营销单元管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		iYjyxdyglService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "一级营销单元管理-批量删除")
	@ApiOperation(value="一级营销单元管理-批量删除", notes="一级营销单元管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.iYjyxdyglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "一级营销单元管理-通过id查询")
	@ApiOperation(value="一级营销单元管理-通过id查询", notes="一级营销单元管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Yjyxdygl yjyxdygl = iYjyxdyglService.getById(id);
		return Result.ok(yjyxdygl);
	}

	 /**
	  * 通过dybh查询
	  *
	  * @param dybh
	  * @return
	  */
	 @AutoLog(value = "一级营销单元管理-通过dybh查询")
	 @ApiOperation(value="一级营销单元管理-通过dybh查询", notes="一级营销单元管理-通过dybh查询")
	 @GetMapping(value = "/queryByDybh")
	 public Result<?> queryByDybh(@RequestParam(name="dybh",required=true) String dybh) {
		 Map<String,String> params = new HashMap<>();
		 params.put("dybh", dybh);
		 Yjyxdygl yjyxdyglEntity = iYjyxdyglService.queryDataByDybh(params);
		 return Result.ok(yjyxdyglEntity);
	 }

  /**
   * 导出excel
   *
   * @param request
   * @param vyjyxdygl
   */
  @RequestMapping(value = "/exportXls")

  public ModelAndView exportXls(HttpServletRequest request, VYjyxdygl vYjyxdygl) {
      return super.exportXls(request, vYjyxdygl, VYjyxdygl.class, "一级营销单元管理");
  }

	 /**
	  * 导出一级营销单元导入模板
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView templateExportXls() {
		 // AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "一级营销单元导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, Yjyxdygl.class);
		 ExportParams exportParams = new ExportParams("一级营销单元导入模板", "一级营销单元导入信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<Yjyxdygl>());
		 return mv;
	 }

	  /**
	   * 通过excel导入数据
	   *
	   * @param jsonObject
	   * @return
	   */
	  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	  public Result<?> importExcel(@RequestBody @NotNull JSONObject jsonObject) {
		  String filePaths = jsonObject.getString("filePaths");
		  if (StringUtils.isEmpty(filePaths)) {
			  return Result.error("请先上传导入文件!");
		  }
		  String[] filePathList = filePaths.split(",");
		  for (String filePath : filePathList) {
			  filePath = uploadPath + File.separator + filePath;
			  File file = new File(filePath);
			  ImportParams importParams = new ImportParams();
			  importParams.setTitleRows(1);
			  importParams.setHeadRows(1);
			  importParams.setNeedSave(true);
			  try {
				  System.out.println("开始导入：" + DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss"));
				  List<Yjyxdygl> yjyxdyglList = ExcelImportUtil.importExcel(file, Yjyxdygl.class, importParams);
				  List<String> stringList = new ArrayList<>();
				  for (Yjyxdygl yjyxdygl : yjyxdyglList) {
					  Map<String,String> params = new HashMap<>();
					  params.put("dybh", yjyxdygl.getDybh());
					  Yjyxdygl yjyxdyglEntity = iYjyxdyglService.queryDataByDybh(params);
					  if (yjyxdyglEntity != null) {
						  stringList.add(yjyxdygl.getDybh());
					  }
				  }
				  if (!stringList.isEmpty()) {
					  for (String bh : stringList) {
						  String dybh = bh;
						  UpdateWrapper<Yjyxdygl> updateWrapper = new UpdateWrapper<>();
						  updateWrapper.eq("dybh", dybh);
						  iYjyxdyglService.remove(updateWrapper);
					  }
				  }
				  iYjyxdyglService.saveBatch(yjyxdyglList);
				  System.out.println("导入完成：" + DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss"));
				  return Result.ok("文件导入成功！共[ "+yjyxdyglList.size()+" ]条数据！");
			  } catch (Exception e) {
				  log.error(e.getMessage(), e);
				  return Result.error("文件导入失败！"+e.getMessage());
			  }
		  }
		  return Result.ok("文件导入成功!");
	  }

}

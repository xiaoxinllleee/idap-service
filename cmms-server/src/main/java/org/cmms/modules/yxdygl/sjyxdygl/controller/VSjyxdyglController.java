package org.cmms.modules.yxdygl.sjyxdygl.controller;

import java.io.File;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.checkerframework.checker.units.qual.A;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.AreaUtil;
import org.cmms.modules.yxdygl.sjyxdygl.entity.EjyxdyglReuse;
import org.cmms.modules.yxdygl.sjyxdygl.entity.Sjyxdygl;
import org.cmms.modules.yxdygl.sjyxdygl.entity.VSjyxdygl;
import org.cmms.modules.yxdygl.sjyxdygl.service.ISjyxdyglService;
import org.cmms.modules.yxdygl.sjyxdygl.service.IVSjyxdyglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.yxdygl.sjyxdygl.vo.SjyxdyglPage;
import org.cmms.modules.yxdygl.yxdyfjxx.entity.Yxdyfjxx;
import org.cmms.modules.yxdygl.yxdyfjxx.service.IYxdyfjxxService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
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
 * @Description: 三级营销单元管理
 * @Author: Penghr
 * @Date:   2020-07-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="三级营销单元管理")
@RestController
@RequestMapping("/yxdygl/sjyxdygl")
public class VSjyxdyglController extends JeecgController<VSjyxdygl, IVSjyxdyglService> {
	@Autowired
	private IVSjyxdyglService vSjyxdyglService;
	@Autowired
	private ISjyxdyglService iSjyxdyglService;
	@Autowired
	private AreaUtil areaUtil;
	@Autowired
	private IYxdyfjxxService yxdyfjxxService;
	@Value(value = "${common.path.upload}")
	private String uploadPath;

	/**
	 * 分页列表查询
	 *
	 * @param vSjyxdygl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "三级营销单元管理-分页列表查询")
	@ApiOperation(value="三级营销单元管理-分页列表查询", notes="三级营销单元管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VSjyxdygl vSjyxdygl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VSjyxdygl> queryWrapper = QueryGenerator.initQueryWrapper(vSjyxdygl, req.getParameterMap());
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String username = sysUser.getUsername();
		if (!"admin".equals(username)) {
			queryWrapper.eq("yggh", sysUser.getWorkNo());
			queryWrapper.exists("select 1 from (" +
					"    select t3.dybh,t3.dymc" +
					"    from sys_bas_user t1,yxdygl_pqzrrgl t2,yxdygl_sjyxdygl t3" +
					"    where t1.tellid=t2.khjl and t1.username = '" + username + "' and t2.sjyxdybh=t3.dybh" +
					"    group by t3.dybh,t3.dymc) n1 where dybh=n1.dybh");
		}
		Page<VSjyxdygl> page = new Page<VSjyxdygl>(pageNo, pageSize);
		IPage<VSjyxdygl> pageList = vSjyxdyglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 分页列表查询
	  *
	  * @param sjyxdygl
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "三级营销单元管理-分页列表查询")
	 @ApiOperation(value="三级营销单元管理-分页列表查询", notes="三级营销单元管理-分页列表查询")
	 @GetMapping(value = "/querylist")
	 public Result<?> queryPageList(Sjyxdygl sjyxdygl,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 QueryWrapper<Sjyxdygl> queryWrapper = QueryGenerator.initQueryWrapper(sjyxdygl, req.getParameterMap());
		 Page<Sjyxdygl> page = new Page<Sjyxdygl>(pageNo, pageSize);
		 IPage<Sjyxdygl> pageList = iSjyxdyglService.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }

	 /**
	  * 分页列表查询
	  *
	  * @param vSjyxdygl
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "三级营销单元管理-列表查询")
	 @ApiOperation(value="三级营销单元管理-列表查询", notes="三级营销单元管理-列表查询")
	 @GetMapping(value = "/listAll")
	 public Result<?> queryListAll(VSjyxdygl vSjyxdygl,
								   HttpServletRequest req) {
		 QueryWrapper<VSjyxdygl> queryWrapper = QueryGenerator.initQueryWrapper(vSjyxdygl, req.getParameterMap());

		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String username = sysUser.getUsername();
		 if (!"admin".equals(username)) {
			 queryWrapper.eq("yggh", sysUser.getWorkNo());
			 queryWrapper.exists("select 1 from (" +
					 "    select t3.dybh,t3.dymc" +
					 "    from sys_bas_user t1,yxdygl_pqzrrgl t2,yxdygl_sjyxdygl t3" +
					 "    where t1.tellid=t2.khjl and t1.username = '" + username + "' and t2.sjyxdybh=t3.dybh" +
					 "    group by t3.dybh,t3.dymc) n1 where dybh=n1.dybh");
		 }

		/* Map<Object, Object> map = areaUtil.getSjyxdyqx(req);
		 if (map != null && map.size() > 0) {
			 queryWrapper.in("dybh", map.keySet());
		 } else {
			 queryWrapper.isNull("dybh");
		 }*/
		 List<VSjyxdygl> pageList = vSjyxdyglService.list(queryWrapper);
		 return Result.ok(pageList);
	 }

	/**
	 * 添加
	 *
	 * @param sjyxdygl
	 * @return
	 */
	@AutoLog(value = "三级营销单元管理-添加")
	@ApiOperation(value="三级营销单元管理-添加", notes="三级营销单元管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Sjyxdygl sjyxdygl) {
		iSjyxdyglService.save(sjyxdygl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sjyxdyglPage
	 * @return
	 */
	@AutoLog(value = "三级营销单元管理-编辑")
	@ApiOperation(value="三级营销单元管理-编辑", notes="三级营销单元管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SjyxdyglPage sjyxdyglPage, HttpServletRequest req) {
		Result<Sjyxdygl> result = new Result<Sjyxdygl>();
		Sjyxdygl sjyxdygl = new Sjyxdygl();
		BeanUtils.copyProperties(sjyxdyglPage, sjyxdygl);
		Sjyxdygl sjyxdyglEntity = iSjyxdyglService.getById(sjyxdygl.getId());
		List<Yxdyfjxx> yxdyfjxxList = sjyxdyglPage.getYxdyfjxxList();
		if (sjyxdyglEntity == null) {
			result.error500("未找到对应实体");
		} else {
//			boolean ok = iYjyxdyglService.updateById(yjyxdygl);
			iSjyxdyglService.updateMain(sjyxdygl, sjyxdyglPage.getYxdyfjxxList());
			/*List<String> deleteList = sjyxdyglPage.getDeleteIds();
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
	@AutoLog(value = "三级营销单元管理-通过id删除")
	@ApiOperation(value="三级营销单元管理-通过id删除", notes="三级营销单元管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		iSjyxdyglService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "三级营销单元管理-批量删除")
	@ApiOperation(value="三级营销单元管理-批量删除", notes="三级营销单元管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.iSjyxdyglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "三级营销单元管理-通过id查询")
	@ApiOperation(value="三级营销单元管理-通过id查询", notes="三级营销单元管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Sjyxdygl sjyxdygl = iSjyxdyglService.getById(id);
		return Result.ok(sjyxdygl);
	}

	 /**
	  * 通过二级营销单元编号查询所属支行
	  * @param ejyxdybh
	  * @return
	  */
	 @GetMapping(value = "/querySszh")
	 public Result<List<EjyxdyglReuse>> querySszhByEjyxdybh(@RequestParam(name = "ejyxdybh", required = true) String ejyxdybh) {
		 Result<List<EjyxdyglReuse>> result = new Result<List<EjyxdyglReuse>>();
		 List<EjyxdyglReuse> reuseList = iSjyxdyglService.QuerySszhByYjyxdybh(ejyxdybh);
		 result.setResult(reuseList);
		 result.setSuccess(true);
		 return result;
	 }

	  /**
	   * 导出excel
	   *
	   * @param request
	   * @param vSjyxdygl
	   */
	  @RequestMapping(value = "/exportXls")
	  public ModelAndView exportXls(HttpServletRequest request, VSjyxdygl vSjyxdygl) {
			  return super.exportXls(request, vSjyxdygl, VSjyxdygl.class, "三级营销单元管理");
	  }

	 /**
	  * 导出三级营销单元导入模板
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView templateExportXls() {
		 // AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "三级营销单元导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, Sjyxdygl.class);
		 ExportParams exportParams = new ExportParams("三级营销单元导入模板","三级营销单元导入信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<Sjyxdygl>());
		 return mv;
	 }

	 /**
	  * 通过excel导入数据
	  *
	  * @param jsonObject
	  * @return
	  */
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject) {
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
				 List<Sjyxdygl> sjyxdyglList = ExcelImportUtil.importExcel(file, Sjyxdygl.class, importParams);
				 List<String> stringList = new ArrayList<>();
				 for (Sjyxdygl sjyxdygl : sjyxdyglList) {
					 Map<String,String> params = new HashMap<>();
					 params.put("dybh", sjyxdygl.getDybh());
					 Sjyxdygl sjyxdyglEntity = iSjyxdyglService.queryDataByDybh(params);
					 if (sjyxdyglEntity != null) {
						 stringList.add(sjyxdygl.getDybh());
					 }
				 }
				 if (!stringList.isEmpty()) {
					 for (String bh : stringList) {
						 String dybh = bh;
						 UpdateWrapper<Sjyxdygl> updateWrapper = new UpdateWrapper<>();
						 updateWrapper.eq("dybh", dybh);
						 iSjyxdyglService.remove(updateWrapper);
					 }
				 }
				 iSjyxdyglService.saveBatch(sjyxdyglList);
				 return Result.ok("文件导入成功！共[ "+sjyxdyglList.size()+" ]条数据！");
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败！"+e.getMessage());
			 }
		 }
		 return Result.ok("文件导入成功!");
	 }

}

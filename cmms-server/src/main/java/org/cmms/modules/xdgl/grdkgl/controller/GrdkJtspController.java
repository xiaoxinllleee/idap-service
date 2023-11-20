package org.cmms.modules.xdgl.grdkgl.controller;

import java.math.BigDecimal;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.word.entity.CamsZcsxFxpjsc;
import org.cmms.modules.xdgl.grdkgl.entity.GrdkDbxx;
import org.cmms.modules.xdgl.grdkgl.entity.GrdkJtsp;
import org.cmms.modules.xdgl.grdkgl.entity.Vgrdkspjl;
import org.cmms.modules.xdgl.grdkgl.service.IGrdkJtspService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.xdgl.grdkgl.service.IVgrdkspjlService;
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
 * @Description: 集体审批书
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="集体审批书")
@RestController
@RequestMapping("/xdgl/grdkgl/grdkJtsp")
public class GrdkJtspController extends JeecgController<GrdkJtsp, IGrdkJtspService> {
	@Autowired
	private IGrdkJtspService grdkJtspService;
	 @Autowired
	 private ISysDictService sysDictService;
	 @Autowired
	 private IVgrdkspjlService iVGrdkSpjlService;
	/**
	 * 分页列表查询
	 *
	 * @param grdkJtsp
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "集体审批书-分页列表查询")
	@ApiOperation(value="集体审批书-分页列表查询", notes="集体审批书-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(GrdkJtsp grdkJtsp,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<GrdkJtsp> queryWrapper = QueryGenerator.initQueryWrapper(grdkJtsp, req.getParameterMap());
		Page<GrdkJtsp> page = new Page<GrdkJtsp>(pageNo, pageSize);
		IPage<GrdkJtsp> pageList = grdkJtspService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param grdkJtsp
	 * @return
	 */
	@AutoLog(value = "集体审批书-添加")
	@ApiOperation(value="集体审批书-添加", notes="集体审批书-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody GrdkJtsp grdkJtsp) {
		grdkJtspService.save(grdkJtsp);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param grdkJtsp
	 * @return
	 */
	@AutoLog(value = "集体审批书-编辑")
	@ApiOperation(value="集体审批书-编辑", notes="集体审批书-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody GrdkJtsp grdkJtsp) {
		System.out.println(grdkJtsp.getId()+"-----"+grdkJtsp.getSxze());
		grdkJtspService.saveOrUpdate(grdkJtsp);
		return Result.ok("编辑成功!");
	}

	//根据ID修改状态
	 @GetMapping  (value = "/updById")
	 public Result<?> updById(@RequestParam(name="id",required=true) String id) {
		 grdkJtspService.updById(id);
		 return Result.ok("操作成功!");
	 }



	 /**
	  * 编辑
	  *
	  * @param
	  * @return
	  */
	 @RequestMapping(value = "/jtsp")
	 public Result<?> lldjPrint(GrdkJtsp grdkJtsp) {
		 QueryWrapper queryWrapper  =new QueryWrapper();
		 queryWrapper.eq("id",grdkJtsp.getId());
		 GrdkJtsp jtsp = grdkJtspService.getOne(queryWrapper);
		 return Result.ok(jtsp);
	 }


	 /**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "集体审批书-通过id删除")
	@ApiOperation(value="集体审批书-通过id删除", notes="集体审批书-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		grdkJtspService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "集体审批书-批量删除")
	@ApiOperation(value="集体审批书-批量删除", notes="集体审批书-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.grdkJtspService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "集体审批书-通过id查询")
	@ApiOperation(value="集体审批书-通过id查询", notes="集体审批书-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		GrdkJtsp grdkJtsp = grdkJtspService.getById(id);
		return Result.ok(grdkJtsp);
	}


	 /**
	  * 通过zjhm查询
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "集体审批书-通过zjhm查询")
	 @ApiOperation(value="集体审批书-通过zjhm查询", notes="集体审批书-通过zjhm查询")
	 @GetMapping(value = "/getDbxx")
	 public Result<?> getDbxx(@RequestParam(name="id",required=true) String id) {
		 JSONObject jsonObject = new JSONObject();
		 QueryWrapper<Vgrdkspjl> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("id", id);
		 Vgrdkspjl vgrdkspjl= iVGrdkSpjlService.getOne(queryWrapper);
		 if(vgrdkspjl!=null){
			 List<GrdkDbxx> list = grdkJtspService.getDbxx(vgrdkspjl.getZjhm());
			 Integer count = 0;
			 BigDecimal xdywjz = new BigDecimal(count);
			 StringBuffer dbfs =new StringBuffer();
			 String xdbfs = "";
			 for (GrdkDbxx dbxx :list){
				 dbxx.setDbfs(dbxx.getDbfs() == null ? "" : sysDictService.queryDictTextByKey("dbfs", dbxx.getDbfs()));
				 xdywjz = xdywjz.add(dbxx.getDyje());
				 dbfs.append(dbxx.getDbfs()+",");
				 xdbfs = dbfs.substring(0,dbfs.length()-1);
			 }
			 System.out.println("xdywjz--------"+xdywjz);
			 System.out.println(dbfs.length()+"xdbfs---------"+xdbfs);
			 jsonObject.put("xdbfs",xdbfs);
			 jsonObject.put("xdywjz",xdywjz);
		 }else{
			 jsonObject.put("xdbfs","");
			 jsonObject.put("xdywjz",0);
		 }
		 return Result.ok(jsonObject);
	 }
  /**
   * 导出excel
   *
   * @param request
   * @param grdkJtsp
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, GrdkJtsp grdkJtsp) {
      return super.exportXls(request, grdkJtsp, GrdkJtsp.class, "集体审批书");
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
      return super.importExcel(request, response, GrdkJtsp.class);
  }

}

package org.cmms.modules.report.bbgl.bbsjgl.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.utils.ListToDictUtil;
import org.cmms.modules.report.bbgl.bbmbgl.entity.Bbmbgl;
import org.cmms.modules.report.bbgl.bbmbgl.entity.BbmbglVo;
import org.cmms.modules.report.bbgl.bbmbgl.service.IBbmbglService;
import org.cmms.modules.report.bbgl.bbsjgl.entity.VBbsjgl;
import org.cmms.modules.report.bbgl.bbsjgl.service.IVBbsjglService;
import org.cmms.modules.util.PageUtil;
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
 * @Description: 报表数据管理视图
 * @Author: jeecg-boot
 * @Date:   2022-03-31
 * @Version: V1.0
 */
@Slf4j
@Api(tags="报表数据管理视图")
@RestController
@RequestMapping("/bbgl/vBbsjgl")
public class VBbsjglController extends JeecgController<VBbsjgl, IVBbsjglService> {
	@Autowired
	private IVBbsjglService vBbsjglService;
	 @Autowired
	 private IBbmbglService bbmbglService;

	 @Autowired
	 private ListToDictUtil listToDictUtil;
	/**
	 * 分页列表查询
	 *
	 * @param vBbsjgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "报表数据管理视图-分页列表查询")
	@ApiOperation(value="报表数据管理视图-分页列表查询", notes="报表数据管理视图-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VBbsjgl vBbsjgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VBbsjgl> queryWrapper = QueryGenerator.initQueryWrapper(vBbsjgl, req.getParameterMap());
		Page<VBbsjgl> page = new Page<VBbsjgl>(pageNo, pageSize);
		IPage<VBbsjgl> pageList = vBbsjglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 @GetMapping(value = "/datalist")
	 public Result<?> queryList(BbmbglVo vBbsjgl,
								@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								HttpServletRequest req) {
		 DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		 String bbyf=DateUtil.getDateString(vBbsjgl.getBbyf(),dateFormat);
		 List<BbmbglVo> bbmbsjList = bbmbglService.getBbmbsjList(bbyf, vBbsjgl);
		 List list = listToDictUtil.parseDictText(bbmbsjList);
		 IPage<BbmbglVo> pageList = PageUtil.getPages(list, list == null ? 0 : list.size(), pageSize, pageNo);

		 /*Bbmbgl bbmbgl = new Bbmbgl();
		 BeanUtil.copyProperties(vBbsjgl, bbmbgl);
		 QueryWrapper<Bbmbgl> bbmbglQueryWrapper = QueryGenerator.initQueryWrapper(bbmbgl, req.getParameterMap());
		 //查询所有报表模板数据
		 List<Bbmbgl> bbmbglList = bbmbglService.list(bbmbglQueryWrapper);
		 //查询已创建的报表数据
		 QueryWrapper<VBbsjgl> vBbsjglQueryWrapper = QueryGenerator.initQueryWrapper(vBbsjgl, req.getParameterMap());
		 List<VBbsjgl> vBbsjglList = vBbsjglService.list(vBbsjglQueryWrapper);
		 bbmbglList.forEach(bbmbglItem -> {
		 	 List<VBbsjgl> existList = vBbsjglList.stream().filter(item -> item.getBbbh().equalsIgnoreCase(bbmbglItem.getBbbh())).collect(Collectors.toList());
			 if (existList.isEmpty()) {
				 VBbsjgl vBbsjglAdd = new VBbsjgl();
				 BeanUtil.copyProperties(bbmbglItem, vBbsjglAdd);
				 vBbsjglAdd.setBbyf(vBbsjgl.getBbyf());
				 vBbsjglList.add(vBbsjglAdd);
			 }
		 });*/
		 return Result.ok(pageList);



	 }
	/**
	 * 添加
	 *
	 * @param vBbsjgl
	 * @return
	 */
	@AutoLog(value = "报表数据管理视图-添加")
	@ApiOperation(value="报表数据管理视图-添加", notes="报表数据管理视图-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VBbsjgl vBbsjgl) {
		vBbsjglService.save(vBbsjgl);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param vBbsjgl
	 * @return
	 */
	@AutoLog(value = "报表数据管理视图-编辑")
	@ApiOperation(value="报表数据管理视图-编辑", notes="报表数据管理视图-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody VBbsjgl vBbsjgl) {
		vBbsjglService.updateById(vBbsjgl);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "报表数据管理视图-通过id删除")
	@ApiOperation(value="报表数据管理视图-通过id删除", notes="报表数据管理视图-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		vBbsjglService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "报表数据管理视图-批量删除")
	@ApiOperation(value="报表数据管理视图-批量删除", notes="报表数据管理视图-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.vBbsjglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "报表数据管理视图-通过id查询")
	@ApiOperation(value="报表数据管理视图-通过id查询", notes="报表数据管理视图-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VBbsjgl vBbsjgl = vBbsjglService.getById(id);
		return Result.ok(vBbsjgl);
	}



  /**
   * 导出excel
   *
   * @param request
   * @param vBbsjgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, VBbsjgl vBbsjgl) {
      return super.exportXls(request, vBbsjgl, VBbsjgl.class, "报表数据管理视图");
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
      return super.importExcel(request, response, VBbsjgl.class);
  }

}

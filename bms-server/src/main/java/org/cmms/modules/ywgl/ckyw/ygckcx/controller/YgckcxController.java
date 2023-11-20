package org.cmms.modules.ywgl.ckyw.ygckcx.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ywgl.ckyw.ygckcx.entity.Ygckcx;
import org.cmms.modules.ywgl.ckyw.ygckcx.mapper.YgckcxMapper;
import org.cmms.modules.ywgl.ckyw.ygckcx.service.IYgckcxService;
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
 * @Description: 员工存款查询
 * @Author: jeecg-boot
 * @Date:   2021-10-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="员工存款查询")
@RestController
@RequestMapping("/ygckcx/ygckcx")
public class YgckcxController extends JeecgController<Ygckcx, IYgckcxService> {
	@Autowired
	private IYgckcxService ygckcxService;

	
	/**
	 * 分页列表查询
	 *
	 * @param ygckcx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "员工存款查询-分页列表查询")
	@ApiOperation(value="员工存款查询-分页列表查询", notes="员工存款查询-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ygckcx ygckcx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Ygckcx> queryWrapper = QueryGenerator.initQueryWrapper(ygckcx, req.getParameterMap());
		IPage pageList = org.cmms.common.utils.PageUtil.toPage(IYgckcxService.class,ygckcxService, pageNo, pageSize, queryWrapper, "zzbz","yggh");
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/init")
	 public Result<?> init(@RequestBody JSONObject jsonObject){
		 String tjyf = jsonObject.getString("tjyf");
		 tjyf = tjyf.replaceAll("-", "");
		 Result result = new Result<>();
		 try {
			 ygckcxService.pYgckcx(tjyf);
			 result.setSuccess(true);
			 return result;
		 }catch (Exception e){
			 System.out.println(e);
			 log.error("提取失败", e.getMessage());
			 result.setSuccess(false);
		 }
		 return result;
	 }
	
	/**
	 * 添加
	 *
	 * @param ygckcx
	 * @return
	 */
	@AutoLog(value = "员工存款查询-添加")
	@ApiOperation(value="员工存款查询-添加", notes="员工存款查询-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ygckcx ygckcx) {
		ygckcxService.save(ygckcx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param ygckcx
	 * @return
	 */
	@AutoLog(value = "员工存款查询-编辑")
	@ApiOperation(value="员工存款查询-编辑", notes="员工存款查询-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ygckcx ygckcx) {
		ygckcxService.updateById(ygckcx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工存款查询-通过id删除")
	@ApiOperation(value="员工存款查询-通过id删除", notes="员工存款查询-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ygckcxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "员工存款查询-批量删除")
	@ApiOperation(value="员工存款查询-批量删除", notes="员工存款查询-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ygckcxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工存款查询-通过id查询")
	@ApiOperation(value="员工存款查询-通过id查询", notes="员工存款查询-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ygckcx ygckcx = ygckcxService.getById(id);
		return Result.ok(ygckcx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ygckcx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ygckcx ygckcx) {
      return super.exportXls(request, ygckcx, Ygckcx.class, "员工存款查询");
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
      return super.importExcel(request, response, Ygckcx.class);
  }

}

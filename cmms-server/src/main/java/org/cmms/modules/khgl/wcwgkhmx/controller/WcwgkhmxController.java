package org.cmms.modules.khgl.wcwgkhmx.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.wcwgkhmx.entity.Wcwgkhmx;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.wcwgkhmx.service.IWcwgkhmxService;
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
 * @Description: 外出务工客户明细
 * @Author: Penghr
 * @Date:   2020-02-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="外出务工客户明细")
@RestController
@RequestMapping("/wcwgkhmx/wcwgkhmx")
public class WcwgkhmxController extends JeecgController<Wcwgkhmx, IWcwgkhmxService> {
	@Autowired
	private IWcwgkhmxService iWcwgkhmxService;
	
	/**
	 * 分页列表查询
	 * @param wcwgkhmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "外出务工客户明细-分页列表查询")
	@ApiOperation(value="外出务工客户明细-分页列表查询", notes="外出务工客户明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Wcwgkhmx wcwgkhmx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Wcwgkhmx> queryWrapper = QueryGenerator.initQueryWrapper(wcwgkhmx, req.getParameterMap());
		Page<Wcwgkhmx> page = new Page<Wcwgkhmx>(pageNo, pageSize);
		IPage<Wcwgkhmx> pageList = iWcwgkhmxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 * @param wcwgkhmx
	 * @return
	 */
	/*@AutoLog(value = "外出务工客户明细-添加")
	@ApiOperation(value="外出务工客户明细-添加", notes="外出务工客户明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Wcwgkhmx wcwgkhmx) {
		iWcwgkhmxService.save(wcwgkhmx);
		return Result.ok("添加成功！");
	}*/
	
	/**
	 * 编辑
	 * @param wcwgkhmx
	 * @return
	 */
	/*@AutoLog(value = "外出务工客户明细-编辑")
	@ApiOperation(value="外出务工客户明细-编辑", notes="外出务工客户明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Wcwgkhmx wcwgkhmx) {
		iWcwgkhmxService.updateById(wcwgkhmx);
		return Result.ok("编辑成功!");
	}*/
	
	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	/*@AutoLog(value = "外出务工客户明细-通过id删除")
	@ApiOperation(value="外出务工客户明细-通过id删除", notes="外出务工客户明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		iWcwgkhmxService.removeById(id);
		return Result.ok("删除成功!");
	}*/
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	/*@AutoLog(value = "外出务工客户明细-批量删除")
	@ApiOperation(value="外出务工客户明细-批量删除", notes="外出务工客户明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.iWcwgkhmxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}*/
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	/*@AutoLog(value = "外出务工客户明细-通过id查询")
	@ApiOperation(value="外出务工客户明细-通过id查询", notes="外出务工客户明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Wcwgkhmx wcwgkhmx = iWcwgkhmxService.getById(id);
		return Result.ok(wcwgkhmx);
	}*/

     /**
      * 导出excel
      * @param request
      * @param wcwgkhmx
      */
     @RequestMapping(value = "/exportXls")
     public ModelAndView exportXls(HttpServletRequest request, Wcwgkhmx wcwgkhmx) {
         return super.exportXls(request, wcwgkhmx, Wcwgkhmx.class, "外出务工客户明细");
     }

     /**
      * 通过excel导入数据
      * @param request
      * @param response
      * @return
      */
     @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
     public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
         return super.importExcel(request, response, Wcwgkhmx.class);
     }

     /**
      * 数据统计
      * @return
      */
     @AutoLog(value = "外出务工客户明细-数据统计")
     @ApiOperation(value="外出务工客户明细-数据统计", notes="外出务工客户明细-数据统计")
     @GetMapping(value = "/init")
     public Result<?> init(HttpServletRequest request, HttpServletResponse response) {
         try {
            iWcwgkhmxService.initData();
         } catch (Exception e) {
             log.error("统计失败！", e.getMessage());
             return Result.error("统计失败！");
         }
         return Result.ok("统计成功！");
     }

}

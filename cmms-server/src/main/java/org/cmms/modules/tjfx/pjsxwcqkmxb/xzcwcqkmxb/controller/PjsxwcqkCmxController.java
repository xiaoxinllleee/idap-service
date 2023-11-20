package org.cmms.modules.tjfx.pjsxwcqkmxb.xzcwcqkmxb.controller;

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
import org.cmms.modules.tjfx.pjsxwcqkmxb.xzcwcqkmxb.entity.PjsxwcqkCmx;
import org.cmms.modules.tjfx.pjsxwcqkmxb.xzcwcqkmxb.service.IPjsxwcqkCmxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
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
 * @Description: 行政村完成情况明细表
 * @Author: Penghr
 * @Date:   2020-03-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="行政村完成情况明细表")
@RestController
@RequestMapping("/tjfx.pjsxwcqkmxb/xzcwcqkmxb")
public class PjsxwcqkCmxController extends JeecgController<PjsxwcqkCmx, IPjsxwcqkCmxService> {
	@Autowired
	private IPjsxwcqkCmxService pjsxwcqkCmxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param pjsxwcqkCmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "行政村完成情况明细表-分页列表查询")
	@ApiOperation(value="行政村完成情况明细表-分页列表查询", notes="行政村完成情况明细表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PjsxwcqkCmx pjsxwcqkCmx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PjsxwcqkCmx> queryWrapper = QueryGenerator.initQueryWrapper(pjsxwcqkCmx, req.getParameterMap());
		Page<PjsxwcqkCmx> page = new Page<PjsxwcqkCmx>(pageNo, pageSize);
		IPage<PjsxwcqkCmx> pageList = pjsxwcqkCmxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param pjsxwcqkCmx
	 * @return
	 */
	@AutoLog(value = "行政村完成情况明细表-添加")
	@ApiOperation(value="行政村完成情况明细表-添加", notes="行政村完成情况明细表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PjsxwcqkCmx pjsxwcqkCmx) {
		pjsxwcqkCmxService.save(pjsxwcqkCmx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param pjsxwcqkCmx
	 * @return
	 */
	@AutoLog(value = "行政村完成情况明细表-编辑")
	@ApiOperation(value="行政村完成情况明细表-编辑", notes="行政村完成情况明细表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PjsxwcqkCmx pjsxwcqkCmx) {
		pjsxwcqkCmxService.updateById(pjsxwcqkCmx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "行政村完成情况明细表-通过id删除")
	@ApiOperation(value="行政村完成情况明细表-通过id删除", notes="行政村完成情况明细表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pjsxwcqkCmxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "行政村完成情况明细表-批量删除")
	@ApiOperation(value="行政村完成情况明细表-批量删除", notes="行政村完成情况明细表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pjsxwcqkCmxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "行政村完成情况明细表-通过id查询")
	@ApiOperation(value="行政村完成情况明细表-通过id查询", notes="行政村完成情况明细表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PjsxwcqkCmx pjsxwcqkCmx = pjsxwcqkCmxService.getById(id);
		return Result.ok(pjsxwcqkCmx);
	}

    /**
     * 导出excel
     *
     * @param request
     * @param pjsxwcqkCmx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, PjsxwcqkCmx pjsxwcqkCmx) {
      return super.exportXls(request, pjsxwcqkCmx, PjsxwcqkCmx.class, "行政村完成情况明细表");
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
      return super.importExcel(request, response, PjsxwcqkCmx.class);
    }

     /**
      * 行政村完成情况明细表-提取
      * @param object
      * @return
      */
//    @PutMapping(value = "/init")
//    public Result<?> InitDataToXzc(@RequestBody JSONObject object) {
//        try {
//            Map<String, String> param = new HashMap<>();
//            param.put("ksrq", object.getString("ksrq"));
//            param.put("jsrq", object.getString("jsrq"));
//            param.put("jgdm", object.getString("jgdm"));
//        } catch (Exception e) {
//            log.error(e.getMessage(), "提取失败！");
//            return Result.error(e.getMessage());
//        }
//        return Result.ok("提取成功！");
//    }

}

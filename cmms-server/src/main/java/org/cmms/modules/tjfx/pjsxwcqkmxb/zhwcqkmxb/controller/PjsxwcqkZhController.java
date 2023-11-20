package org.cmms.modules.tjfx.pjsxwcqkmxb.zhwcqkmxb.controller;

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
import org.cmms.modules.tjfx.pjsxwcqkmxb.zhwcqkmxb.entity.PjsxwcqkZh;
import org.cmms.modules.tjfx.pjsxwcqkmxb.zhwcqkmxb.service.IPjsxwcqkZhService;
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
 * @Description: 支行完成情况明细表
 * @Author: Penghr
 * @Date:   2020-03-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行完成情况明细表")
@RestController
@RequestMapping("/tjfx.pjsxwcqkmxb/zhwcqkmxb")
public class PjsxwcqkZhController extends JeecgController<PjsxwcqkZh, IPjsxwcqkZhService> {
	@Autowired
	private IPjsxwcqkZhService pjsxwcqkZhService;
	
	/**
	 * 分页列表查询
	 *
	 * @param pjsxwcqkZh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行完成情况明细表-分页列表查询")
	@ApiOperation(value="支行完成情况明细表-分页列表查询", notes="支行完成情况明细表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PjsxwcqkZh pjsxwcqkZh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PjsxwcqkZh> queryWrapper = QueryGenerator.initQueryWrapper(pjsxwcqkZh, req.getParameterMap());
		Page<PjsxwcqkZh> page = new Page<PjsxwcqkZh>(pageNo, pageSize);
		IPage<PjsxwcqkZh> pageList = pjsxwcqkZhService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param pjsxwcqkZh
	 * @return
	 */
	@AutoLog(value = "支行完成情况明细表-添加")
	@ApiOperation(value="支行完成情况明细表-添加", notes="支行完成情况明细表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PjsxwcqkZh pjsxwcqkZh) {
		pjsxwcqkZhService.save(pjsxwcqkZh);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param pjsxwcqkZh
	 * @return
	 */
	@AutoLog(value = "支行完成情况明细表-编辑")
	@ApiOperation(value="支行完成情况明细表-编辑", notes="支行完成情况明细表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PjsxwcqkZh pjsxwcqkZh) {
		pjsxwcqkZhService.updateById(pjsxwcqkZh);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行完成情况明细表-通过id删除")
	@ApiOperation(value="支行完成情况明细表-通过id删除", notes="支行完成情况明细表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pjsxwcqkZhService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "支行完成情况明细表-批量删除")
	@ApiOperation(value="支行完成情况明细表-批量删除", notes="支行完成情况明细表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pjsxwcqkZhService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行完成情况明细表-通过id查询")
	@ApiOperation(value="支行完成情况明细表-通过id查询", notes="支行完成情况明细表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PjsxwcqkZh pjsxwcqkZh = pjsxwcqkZhService.getById(id);
		return Result.ok(pjsxwcqkZh);
	}

    /**
     * 导出excel
     *
     * @param request
     * @param pjsxwcqkZh
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, PjsxwcqkZh pjsxwcqkZh) {
      return super.exportXls(request, pjsxwcqkZh, PjsxwcqkZh.class, "支行完成情况明细表");
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
      return super.importExcel(request, response, PjsxwcqkZh.class);
    }

     /**
      * 支行完成情况明细表-提取
      * @param object
      * @return
      */
//    @PutMapping(value = "/init")
//    public Result<?> InitDataToZh(@RequestBody JSONObject object) {
//        try {
//            Map<String, String> param = new HashMap<>();
//            param.put("ksrq", object.getString("ksrq"));
//            param.put("jsrq", object.getString("jsrq"));
//            param.put("jgdm", object.getString("jgdm"));
//            pjsxwcqkZhService.InitDataToZh(param);
//        } catch (Exception e) {
//            log.error(e.getMessage(), "提取失败！");
//            return Result.error(e.getMessage());
//        }
//        return Result.ok("提取成功！");
//    }

}

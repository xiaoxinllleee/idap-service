package org.cmms.modules.ywgl.yxbldkgl.djkblxx.controller;

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
import org.cmms.modules.util.DateUtil;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.EtlUtilOld;
import org.cmms.modules.ywgl.yxbldkgl.djkblxx.entity.Djkblxx;
import org.cmms.modules.ywgl.yxbldkgl.djkblxx.service.IDjkblxxService;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 贷记卡不良信息
 * @Author: Penghr
 * @Date:   2022-08-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷记卡不良信息")
@RestController
@RequestMapping("/yxbldkgl/djkblxx")
public class DjkblxxController extends JeecgController<Djkblxx, IDjkblxxService> {
	@Autowired
	private IDjkblxxService djkblxxService;
	 @Value("${com.etl.sfdsjpt}")
	 private String sfdsjpt;

	/**
	 * 分页列表查询
	 *
	 * @param djkblxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷记卡不良信息-分页列表查询")
	@ApiOperation(value="贷记卡不良信息-分页列表查询", notes="贷记卡不良信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Djkblxx djkblxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Djkblxx> queryWrapper = QueryGenerator.initQueryWrapper(djkblxx, req.getParameterMap());
		Page<Djkblxx> page = new Page<Djkblxx>(pageNo, pageSize);
		IPage<Djkblxx> pageList = djkblxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 数据抽取
	  *
	  * @return jsonObject
	  */
	 @AutoLog(value = "贷记卡不良信息-数据抽取")
	 @ApiOperation(value = "贷记卡不良信息-数据抽取", notes = "贷记卡不良信息-数据抽取")
	 @RequestMapping(value = "/extraction")
	 public Result<?> Extraction(@RequestBody JSONObject jsonObject) {
	 	try {
			String tjyf = jsonObject.getString("tjyf");
			if ("true".equals(sfdsjpt)) {
				HashMap<String, String> params = new HashMap<>();
				params.put("tjyf",tjyf.replace("-",""));
				params.put("etl_task", "kiss.domain.application.cdkyw.proc_yxbldkgl_djkblxx");
				boolean flag = EtlUtil.callEtl("cdkyw_common_init", params, 15);
				log.info("贷记卡不良信息-数据抽取-"+flag);
				return Result.ok();
			} else {
				log.info("贷记卡不良信息-数据抽取-未做Oracle数据提取");
				return Result.ok();
			}
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			return Result.error(throwable.getMessage());
		}
	 }

	/**
	 * 添加
	 *
	 * @param djkblxx
	 * @return
	 */
	@AutoLog(value = "贷记卡不良信息-添加")
	@ApiOperation(value="贷记卡不良信息-添加", notes="贷记卡不良信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Djkblxx djkblxx) {
		djkblxxService.save(djkblxx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param djkblxx
	 * @return
	 */
	@AutoLog(value = "贷记卡不良信息-编辑")
	@ApiOperation(value="贷记卡不良信息-编辑", notes="贷记卡不良信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Djkblxx djkblxx) {
		djkblxxService.updateById(djkblxx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷记卡不良信息-通过id删除")
	@ApiOperation(value="贷记卡不良信息-通过id删除", notes="贷记卡不良信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		djkblxxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷记卡不良信息-批量删除")
	@ApiOperation(value="贷记卡不良信息-批量删除", notes="贷记卡不良信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.djkblxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷记卡不良信息-通过id查询")
	@ApiOperation(value="贷记卡不良信息-通过id查询", notes="贷记卡不良信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Djkblxx djkblxx = djkblxxService.getById(id);
		return Result.ok(djkblxx);
	}

    /**
     * 导出excel
     *
     * @param request
     * @param djkblxx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Djkblxx djkblxx) {
      return super.exportXls(request, djkblxx, Djkblxx.class, "贷记卡不良信息");
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
      return super.importExcel(request, response, Djkblxx.class);
    }

}

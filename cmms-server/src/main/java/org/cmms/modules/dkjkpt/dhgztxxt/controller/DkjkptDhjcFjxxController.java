package org.cmms.modules.dkjkpt.dhgztxxt.controller;

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
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dkjkpt.dhgztxxt.entity.DkjkptDhjcFjxx;
import org.cmms.modules.dkjkpt.dhgztxxt.service.IDkjkptDhjcFjxxService;
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
 * @Description: 贷后检查附件信息_湘潭
 * @Author: jeecg-boot
 * @Date:   2023-09-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷后检查附件信息_湘潭")
@RestController
@RequestMapping("/dhjcfjxx/dkjkptDhjcFjxx")
public class DkjkptDhjcFjxxController extends JeecgController<DkjkptDhjcFjxx, IDkjkptDhjcFjxxService> {
	@Autowired
	private IDkjkptDhjcFjxxService dkjkptDhjcFjxxService;

	/**
	 * 分页列表查询
	 *
	 * @param dkjkptDhjcFjxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷后检查附件信息_湘潭-分页列表查询")
	@ApiOperation(value="贷后检查附件信息_湘潭-分页列表查询", notes="贷后检查附件信息_湘潭-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DkjkptDhjcFjxx dkjkptDhjcFjxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DkjkptDhjcFjxx> queryWrapper = QueryGenerator.initQueryWrapper(dkjkptDhjcFjxx, req.getParameterMap());
		Page<DkjkptDhjcFjxx> page = new Page<DkjkptDhjcFjxx>(pageNo, pageSize);
		IPage<DkjkptDhjcFjxx> pageList = dkjkptDhjcFjxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 附件查询
	  */
	 @RequestMapping(value = "/queryFjxx",method = RequestMethod.GET)
	 public Result<?> queryFjxx(@RequestParam(name = "zjhm",required = true)String zjhm,@RequestParam(name = "jgdm",required = true)String jgdm){
		 QueryWrapper<DkjkptDhjcFjxx> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("jgdm",jgdm);
		 queryWrapper.eq("zjhm",zjhm);
		 List<DkjkptDhjcFjxx> list = dkjkptDhjcFjxxService.list(queryWrapper);
		 return Result.ok(list);
	 }


	/**
	 * 添加
	 *
	 * @param dkjkptDhjcFjxx
	 * @return
	 */
	@AutoLog(value = "贷后检查附件信息_湘潭-添加")
	@ApiOperation(value="贷后检查附件信息_湘潭-添加", notes="贷后检查附件信息_湘潭-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DkjkptDhjcFjxx dkjkptDhjcFjxx) {
		dkjkptDhjcFjxxService.save(dkjkptDhjcFjxx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkjkptDhjcFjxx
	 * @return
	 */
	@AutoLog(value = "贷后检查附件信息_湘潭-编辑")
	@ApiOperation(value="贷后检查附件信息_湘潭-编辑", notes="贷后检查附件信息_湘潭-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DkjkptDhjcFjxx dkjkptDhjcFjxx) {
		dkjkptDhjcFjxxService.updateById(dkjkptDhjcFjxx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "贷后检查附件信息_湘潭-通过id删除")
	@ApiOperation(value="贷后检查附件信息_湘潭-通过id删除", notes="贷后检查附件信息_湘潭-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="jgdm",required=true) String jgdm,@RequestParam(name="zjhm",required=true) String zjhm) {
		QueryWrapper<DkjkptDhjcFjxx> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("jgdm",jgdm);
		queryWrapper.eq("zjhm",zjhm);
		dkjkptDhjcFjxxService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷后检查附件信息_湘潭-批量删除")
	@ApiOperation(value="贷后检查附件信息_湘潭-批量删除", notes="贷后检查附件信息_湘潭-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkjkptDhjcFjxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷后检查附件信息_湘潭-通过id查询")
	@ApiOperation(value="贷后检查附件信息_湘潭-通过id查询", notes="贷后检查附件信息_湘潭-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DkjkptDhjcFjxx dkjkptDhjcFjxx = dkjkptDhjcFjxxService.getById(id);
		return Result.ok(dkjkptDhjcFjxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkjkptDhjcFjxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DkjkptDhjcFjxx dkjkptDhjcFjxx) {
      return super.exportXls(request, dkjkptDhjcFjxx, DkjkptDhjcFjxx.class, "贷后检查附件信息_湘潭");
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
      return super.importExcel(request, response, DkjkptDhjcFjxx.class);
  }

}

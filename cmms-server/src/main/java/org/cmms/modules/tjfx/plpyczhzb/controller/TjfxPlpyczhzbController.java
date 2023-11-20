package org.cmms.modules.tjfx.plpyczhzb.controller;

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
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.plpyczhzb.entity.TjfxPlpyczhzb;
import org.cmms.modules.tjfx.plpyczhzb.service.ITjfxPlpyczhzbService;
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
 * @Description: 批量评议村组汇总表
 * @Author: jeecg-boot
 * @Date:   2023-07-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="批量评议村组汇总表")
@RestController
@RequestMapping("/plpyczhzb/tjfxPlpyczhzb")
public class TjfxPlpyczhzbController extends JeecgController<TjfxPlpyczhzb, ITjfxPlpyczhzbService> {
	@Autowired
	private ITjfxPlpyczhzbService tjfxPlpyczhzbService;

	/**
	 * 分页列表查询
	 *
	 * @param tjfxPlpyczhzb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "批量评议村组汇总表-分页列表查询")
	@ApiOperation(value="批量评议村组汇总表-分页列表查询", notes="批量评议村组汇总表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TjfxPlpyczhzb tjfxPlpyczhzb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String wgbh = tjfxPlpyczhzb.getCzbm();
		tjfxPlpyczhzb.setCzbm(null);
		QueryWrapper<TjfxPlpyczhzb> queryWrapper = QueryGenerator.initQueryWrapper(tjfxPlpyczhzb, req.getParameterMap());
		if (StringUtils.isNotBlank(wgbh)){
			String sqlSswg = "select wgbh from yxdygl_main start with wgbh='"+wgbh+"'connect by prior  wgbh=parent_id";
			queryWrapper.inSql("czbm", sqlSswg);
		}
		Page<TjfxPlpyczhzb> page = new Page<TjfxPlpyczhzb>(pageNo, pageSize);
		IPage<TjfxPlpyczhzb> pageList = tjfxPlpyczhzbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param tjfxPlpyczhzb
	 * @return
	 */
	@AutoLog(value = "批量评议村组汇总表-添加")
	@ApiOperation(value="批量评议村组汇总表-添加", notes="批量评议村组汇总表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TjfxPlpyczhzb tjfxPlpyczhzb) {
		tjfxPlpyczhzbService.save(tjfxPlpyczhzb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param tjfxPlpyczhzb
	 * @return
	 */
	@AutoLog(value = "批量评议村组汇总表-编辑")
	@ApiOperation(value="批量评议村组汇总表-编辑", notes="批量评议村组汇总表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TjfxPlpyczhzb tjfxPlpyczhzb) {
		tjfxPlpyczhzbService.updateById(tjfxPlpyczhzb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "批量评议村组汇总表-通过id删除")
	@ApiOperation(value="批量评议村组汇总表-通过id删除", notes="批量评议村组汇总表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tjfxPlpyczhzbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "批量评议村组汇总表-批量删除")
	@ApiOperation(value="批量评议村组汇总表-批量删除", notes="批量评议村组汇总表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tjfxPlpyczhzbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "批量评议村组汇总表-通过id查询")
	@ApiOperation(value="批量评议村组汇总表-通过id查询", notes="批量评议村组汇总表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TjfxPlpyczhzb tjfxPlpyczhzb = tjfxPlpyczhzbService.getById(id);
		return Result.ok(tjfxPlpyczhzb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tjfxPlpyczhzb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, TjfxPlpyczhzb tjfxPlpyczhzb) {
      return super.exportXls(request, tjfxPlpyczhzb, TjfxPlpyczhzb.class, "批量评议村组汇总表");
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
      return super.importExcel(request, response, TjfxPlpyczhzb.class);
  }


	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/init")
	 public Result<?> init(){
		 Result result = new Result<>();
		 try{
			 tjfxPlpyczhzbService.init();
			 result.setSuccess(true);
			 return result;
		 }catch (Throwable e){
			 System.out.println(e);
			 log.error("提取失败",e.getMessage());
			 result.setSuccess(false);
		 }
		 return result;
	 }
}

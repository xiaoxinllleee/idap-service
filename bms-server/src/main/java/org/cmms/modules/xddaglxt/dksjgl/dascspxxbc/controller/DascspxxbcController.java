package org.cmms.modules.xddaglxt.dksjgl.dascspxxbc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.cmms.modules.xddaglxt.dksjgl.dascspxxbc.entity.Dascspxxbc;
import org.cmms.modules.xddaglxt.dksjgl.dascspxxbc.service.IDascspxxbcService;
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
 * @Description: 档案删除审批信息(补充)
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="档案删除审批信息(补充)")
@RestController
@RequestMapping("/dascspxxbc/dascspxxbc")
public class DascspxxbcController extends JeecgController<Dascspxxbc, IDascspxxbcService> {
	@Autowired
	private IDascspxxbcService dascspxxbcService;
	
	/**
	 * 分页列表查询
	 *
	 * @param dascspxxbc
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "档案删除审批信息(补充)-分页列表查询")
	@ApiOperation(value="档案删除审批信息(补充)-分页列表查询", notes="档案删除审批信息(补充)-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dascspxxbc dascspxxbc,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   String lrsjString,
								   HttpServletRequest req) {
		QueryWrapper<Dascspxxbc> queryWrapper = QueryGenerator.initQueryWrapper(dascspxxbc, req.getParameterMap());
		if (lrsjString != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] date = lrsjString.split(",");
			try {
				queryWrapper.between("lrsj",sdf.parse(date[0]),sdf.parse(date[1]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Page<Dascspxxbc> page = new Page<Dascspxxbc>(pageNo, pageSize);
		IPage<Dascspxxbc> pageList = dascspxxbcService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param dascspxxbc
	 * @return
	 */
	@AutoLog(value = "档案删除审批信息(补充)-添加")
	@ApiOperation(value="档案删除审批信息(补充)-添加", notes="档案删除审批信息(补充)-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dascspxxbc dascspxxbc) {
		dascspxxbcService.save(dascspxxbc);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param dascspxxbc
	 * @return
	 */
	@AutoLog(value = "档案删除审批信息(补充)-编辑")
	@ApiOperation(value="档案删除审批信息(补充)-编辑", notes="档案删除审批信息(补充)-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dascspxxbc dascspxxbc) {
		dascspxxbcService.updateById(dascspxxbc);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "档案删除审批信息(补充)-通过id删除")
	@ApiOperation(value="档案删除审批信息(补充)-通过id删除", notes="档案删除审批信息(补充)-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dascspxxbcService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "档案删除审批信息(补充)-批量删除")
	@ApiOperation(value="档案删除审批信息(补充)-批量删除", notes="档案删除审批信息(补充)-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dascspxxbcService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "档案删除审批信息(补充)-通过id查询")
	@ApiOperation(value="档案删除审批信息(补充)-通过id查询", notes="档案删除审批信息(补充)-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dascspxxbc dascspxxbc = dascspxxbcService.getById(id);
		return Result.ok(dascspxxbc);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dascspxxbc
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dascspxxbc dascspxxbc) {
      return super.exportXls(request, dascspxxbc, Dascspxxbc.class, "档案删除审批信息(补充)");
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
      return super.importExcel(request, response, Dascspxxbc.class);
  }

}

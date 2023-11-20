package org.cmms.modules.ywgl.ckyw.qhckkhflhzb.controller;

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
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.ywgl.ckyw.qhckkhflhzb.entity.Qhckkhflhzb;
import org.cmms.modules.ywgl.ckyw.qhckkhflhzb.service.IQhckkhflhzbService;
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
 * @Description: 全行存款客户分类汇总表
 * @Author: jeecg-boot
 * @Date:   2021-10-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="全行存款客户分类汇总表")
@RestController
@RequestMapping("/qhckkhflhzb/qhckkhflhzb")
public class QhckkhflhzbController extends JeecgController<Qhckkhflhzb, IQhckkhflhzbService> {
	@Autowired
	private IQhckkhflhzbService qhckkhflhzbService;
	
	/**
	 * 分页列表查询
	 *
	 * @param qhckkhflhzb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "全行存款客户分类汇总表-分页列表查询")
	@ApiOperation(value="全行存款客户分类汇总表-分页列表查询", notes="全行存款客户分类汇总表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Qhckkhflhzb qhckkhflhzb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   String zzkhrString,
								   HttpServletRequest req) {
		QueryWrapper<Qhckkhflhzb> queryWrapper = QueryGenerator.initQueryWrapper(qhckkhflhzb, req.getParameterMap());
		if (zzkhrString != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] date = zzkhrString.split(",");
			try {
				queryWrapper.between("zzkhr",sdf.parse(date[0]),sdf.parse(date[1]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		IPage pageList = org.cmms.common.utils.PageUtil.toPage(IQhckkhflhzbService.class,qhckkhflhzbService, pageNo, pageSize, queryWrapper, "tjyf","zjhm");
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param qhckkhflhzb
	 * @return
	 */
	@AutoLog(value = "全行存款客户分类汇总表-添加")
	@ApiOperation(value="全行存款客户分类汇总表-添加", notes="全行存款客户分类汇总表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Qhckkhflhzb qhckkhflhzb) {
		qhckkhflhzbService.save(qhckkhflhzb);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param qhckkhflhzb
	 * @return
	 */
	@AutoLog(value = "全行存款客户分类汇总表-编辑")
	@ApiOperation(value="全行存款客户分类汇总表-编辑", notes="全行存款客户分类汇总表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Qhckkhflhzb qhckkhflhzb) {
		qhckkhflhzbService.updateById(qhckkhflhzb);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行存款客户分类汇总表-通过id删除")
	@ApiOperation(value="全行存款客户分类汇总表-通过id删除", notes="全行存款客户分类汇总表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		qhckkhflhzbService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "全行存款客户分类汇总表-批量删除")
	@ApiOperation(value="全行存款客户分类汇总表-批量删除", notes="全行存款客户分类汇总表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.qhckkhflhzbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行存款客户分类汇总表-通过id查询")
	@ApiOperation(value="全行存款客户分类汇总表-通过id查询", notes="全行存款客户分类汇总表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Qhckkhflhzb qhckkhflhzb = qhckkhflhzbService.getById(id);
		return Result.ok(qhckkhflhzb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param qhckkhflhzb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Qhckkhflhzb qhckkhflhzb) {
      return super.exportXls(request, qhckkhflhzb, Qhckkhflhzb.class, "全行存款客户分类汇总表");
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
      return super.importExcel(request, response, Qhckkhflhzb.class);
  }

}

package org.cmms.modules.ywgl.ckyw.zhckkhflhzb.controller;

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
import org.cmms.modules.ywgl.ckyw.zhckkhflhzb.entity.Zhckkhflhzb;
import org.cmms.modules.ywgl.ckyw.zhckkhflhzb.service.IZhckkhflhzbService;
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
 * @Description: 支行存款客户分类汇总表
 * @Author: jeecg-boot
 * @Date:   2021-10-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行存款客户分类汇总表")
@RestController
@RequestMapping("/zhckkhflhzb/zhckkhflhzb")
public class ZhckkhflhzbController extends JeecgController<Zhckkhflhzb, IZhckkhflhzbService> {
	@Autowired
	private IZhckkhflhzbService zhckkhflhzbService;
	
	/**
	 * 分页列表查询
	 *
	 * @param zhckkhflhzb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行存款客户分类汇总表-分页列表查询")
	@ApiOperation(value="支行存款客户分类汇总表-分页列表查询", notes="支行存款客户分类汇总表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zhckkhflhzb zhckkhflhzb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   String zzkhrString,
								   HttpServletRequest req) {
		QueryWrapper<Zhckkhflhzb> queryWrapper = QueryGenerator.initQueryWrapper(zhckkhflhzb, req.getParameterMap());
		if (zzkhrString != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] date = zzkhrString.split(",");
			try {
				queryWrapper.between("zzkhr",sdf.parse(date[0]),sdf.parse(date[1]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		IPage pageList = org.cmms.common.utils.PageUtil.toPage(IZhckkhflhzbService.class,zhckkhflhzbService, pageNo, pageSize, queryWrapper, "jgdm","zjhm");
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param zhckkhflhzb
	 * @return
	 */
	@AutoLog(value = "支行存款客户分类汇总表-添加")
	@ApiOperation(value="支行存款客户分类汇总表-添加", notes="支行存款客户分类汇总表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zhckkhflhzb zhckkhflhzb) {
		zhckkhflhzbService.save(zhckkhflhzb);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param zhckkhflhzb
	 * @return
	 */
	@AutoLog(value = "支行存款客户分类汇总表-编辑")
	@ApiOperation(value="支行存款客户分类汇总表-编辑", notes="支行存款客户分类汇总表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zhckkhflhzb zhckkhflhzb) {
		zhckkhflhzbService.updateById(zhckkhflhzb);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行存款客户分类汇总表-通过id删除")
	@ApiOperation(value="支行存款客户分类汇总表-通过id删除", notes="支行存款客户分类汇总表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zhckkhflhzbService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "支行存款客户分类汇总表-批量删除")
	@ApiOperation(value="支行存款客户分类汇总表-批量删除", notes="支行存款客户分类汇总表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zhckkhflhzbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行存款客户分类汇总表-通过id查询")
	@ApiOperation(value="支行存款客户分类汇总表-通过id查询", notes="支行存款客户分类汇总表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zhckkhflhzb zhckkhflhzb = zhckkhflhzbService.getById(id);
		return Result.ok(zhckkhflhzb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zhckkhflhzb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zhckkhflhzb zhckkhflhzb) {
      return super.exportXls(request, zhckkhflhzb, Zhckkhflhzb.class, "支行存款客户分类汇总表");
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
      return super.importExcel(request, response, Zhckkhflhzb.class);
  }

}

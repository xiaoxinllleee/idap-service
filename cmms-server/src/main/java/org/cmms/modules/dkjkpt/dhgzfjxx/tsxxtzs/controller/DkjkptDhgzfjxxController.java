package org.cmms.modules.dkjkpt.dhgzfjxx.tsxxtzs.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dkjkpt.dhgzfjxx.tsxxtzs.entity.DkjkptDhgzfjxx;
import org.cmms.modules.dkjkpt.dhgzfjxx.tsxxtzs.service.IDkjkptDhgzfjxxService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dkjkpt.dhgzfjxx.tsxxtzs.vo.VO_vDkjkptTsfxtzs;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity.DkjkptBndksjjktz;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
@Slf4j
@Api(tags="1")
@RestController
@RequestMapping("/DkjkptDhgzfjxx/dkjkptDhgzfjxx")
public class DkjkptDhgzfjxxController extends JeecgController<DkjkptDhgzfjxx, IDkjkptDhgzfjxxService> {
	@Autowired
	private IDkjkptDhgzfjxxService dkjkptDhgzfjxxService;

	/**
	 * 分页列表查询
	 *
	 * @param dkjkptDhgzfjxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "1-分页列表查询")
	@ApiOperation(value="1-分页列表查询", notes="1-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DkjkptDhgzfjxx dkjkptDhgzfjxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DkjkptDhgzfjxx> queryWrapper = QueryGenerator.initQueryWrapper(dkjkptDhgzfjxx, req.getParameterMap());
		Page<DkjkptDhgzfjxx> page = new Page<DkjkptDhgzfjxx>(pageNo, pageSize);
		IPage<DkjkptDhgzfjxx> pageList = dkjkptDhgzfjxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkjkptDhgzfjxx
	 * @return
	 */
	@AutoLog(value = "1-添加")
	@ApiOperation(value="1-添加", notes="1-添加")
	@RequestMapping(value = "/add", method = RequestMethod.PUT)
	public Result<?> add(@RequestBody VO_vDkjkptTsfxtzs dkjkptDhgzfjxx) {
		try {
			for (DkjkptDhgzfjxx dhgzfjxx : dkjkptDhgzfjxx.getDkjkptDhgzfjxxList()) {
				dhgzfjxx.setWjid(new Date().getTime());
				dhgzfjxx.setDkzh(dkjkptDhgzfjxx.getDkzh());
				dhgzfjxx.setWjlj(dhgzfjxx.getFwlj());
				dhgzfjxx.setFjlx(1);
				dkjkptDhgzfjxxService.save(dhgzfjxx);
			}
			return Result.ok("添加成功！");
		}catch (Exception e){
			System.out.println(e);
			return Result.error("添加失败");
		}

	}

	 /**
	  *  查询附件
	  * @param
	  * @return
	  */
	 @RequestMapping(value = "/queryFjxx", method = RequestMethod.GET)
	 public Result<?> queryFjxx(@RequestParam(name="dkzh",required=true)String dkzh ) {
		 QueryWrapper<DkjkptDhgzfjxx> dkjkptDhgzfjxx=new QueryWrapper<>();
		 dkjkptDhgzfjxx.eq("dkzh",dkzh);
		 List<DkjkptDhgzfjxx> list = dkjkptDhgzfjxxService.list(dkjkptDhgzfjxx);
		 return  Result.ok(list);
	 }


	 /**
	  * 添加
	  *
	  * @param dkjkptDhgzfjxx
	  * @return
	  */
	 @AutoLog(value = "1-带回")
	 @ApiOperation(value="1-带回", notes="1-带回")
	 @RequestMapping(value = "/selectByDkzh", method = RequestMethod.PUT)
	 public Result<?> add(@RequestBody JSONObject dkjkptDhgzfjxx) {
	 	QueryWrapper<DkjkptDhgzfjxx> queryWrapper=new QueryWrapper<>();
		 queryWrapper.eq("dkzh",dkjkptDhgzfjxx.getString("dkzh"));
		 List<DkjkptDhgzfjxx> list = dkjkptDhgzfjxxService.list(queryWrapper);
		 return Result.ok(list);
	 }
	/**
	 * 编辑
	 *
	 * @param dkjkptDhgzfjxx
	 * @return
	 */
	@AutoLog(value = "1-编辑")
	@ApiOperation(value="1-编辑", notes="1-编辑")
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public Result<?> edit(@RequestBody VO_vDkjkptTsfxtzs dkjkptDhgzfjxx) {
		try {
			UpdateWrapper<DkjkptDhgzfjxx> updateWrapper=new UpdateWrapper<>();
			updateWrapper.eq("dkzh",dkjkptDhgzfjxx.getDkzh());
			dkjkptDhgzfjxxService.remove(updateWrapper);
			for (DkjkptDhgzfjxx dhgzfjxx : dkjkptDhgzfjxx.getDkjkptDhgzfjxxList()) {
				dhgzfjxx.setWjid(new Date().getTime());
				dhgzfjxx.setDkzh(dkjkptDhgzfjxx.getDkzh());
				dhgzfjxx.setWjlj(dhgzfjxx.getFwlj());
				dhgzfjxx.setFjlx(1);
				dkjkptDhgzfjxxService.save(dhgzfjxx);
			}
			return Result.ok("添加成功！");
		}catch (Exception e){
			System.out.println(e);
			return Result.error("添加失败");
		}
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "1-通过id删除")
	@ApiOperation(value="1-通过id删除", notes="1-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkjkptDhgzfjxxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "1-批量删除")
	@ApiOperation(value="1-批量删除", notes="1-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkjkptDhgzfjxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "1-通过id查询")
	@ApiOperation(value="1-通过id查询", notes="1-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DkjkptDhgzfjxx dkjkptDhgzfjxx = dkjkptDhgzfjxxService.getById(id);
		return Result.ok(dkjkptDhgzfjxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkjkptDhgzfjxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DkjkptDhgzfjxx dkjkptDhgzfjxx) {
      return super.exportXls(request, dkjkptDhgzfjxx, DkjkptDhgzfjxx.class, "提示付息通知书");
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
      return super.importExcel(request, response, DkjkptDhgzfjxx.class);
  }

}

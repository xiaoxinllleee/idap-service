package org.cmms.modules.ckjkpt.khgl.jgckhztj.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.ckjkpt.khgl.jgckhztj.entity.CkjkptKhfzJgckhz;
import org.cmms.modules.ckjkpt.khgl.jgckhztj.service.ICkjkptKhfzJgckhzService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ckjkpt.khgl.ygglckhz.entity.CkjkptKhfzYgglckhz;
import org.cmms.modules.util.DateUtil;
import org.cmms.modules.util.PageUtil;
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
 * @Description: 机构存款汇总统计
 * @Author: jeecg-boot
 * @Date:   2021-07-13
 * @Version: V1.0
 */
@Slf4j
@Api(tags="机构存款汇总统计")
@RestController
@RequestMapping("/jgckhztj/ckjkptKhfzJgckhz")
public class CkjkptKhfzJgckhzController extends JeecgController<CkjkptKhfzJgckhz, ICkjkptKhfzJgckhzService> {
	@Autowired
	private ICkjkptKhfzJgckhzService ckjkptKhfzJgckhzService;

	/**
	 * 分页列表查询
	 *
	 * @param ckjkptKhfzJgckhz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "机构存款汇总统计-分页列表查询")
	@ApiOperation(value="机构存款汇总统计-分页列表查询", notes="机构存款汇总统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CkjkptKhfzJgckhz ckjkptKhfzJgckhz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(ckjkptKhfzJgckhz, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkjkptKhfzJgckhzService.class,ckjkptKhfzJgckhzService,pageNo,pageSize,queryWrapper,"tjyf","zzbz");
		return Result.ok(pageList);
	}


	 /**
	  * 分页列表查询
	  * @return
	  */
	 @AutoLog(value = "员工关联存款客户明细-分页列表查询")
	 @ApiOperation(value="员工关联存款客户明细-分页列表查询", notes="员工关联存款客户明细-分页列表查询")
	 @PutMapping(value = "/queryList")
	 public Result<?> queryList(@RequestBody  CkjkptKhfzJgckhz ckjkptKhfzJgckhz) {
		 QueryWrapper<CkjkptKhfzJgckhz> queryWrapper =new QueryWrapper<>();
		 queryWrapper.eq("zzbz",ckjkptKhfzJgckhz.getZzbz());
		 queryWrapper.last(" tjyf >=(select add_months(max(tjyf),-12) from ckjkpt_khfz_jgckhz) order by tjyf asc");
		 List<CkjkptKhfzJgckhz> list = ckjkptKhfzJgckhzService.list(queryWrapper);
		 JSONArray jsonArray=new JSONArray();
		 for(CkjkptKhfzJgckhz c: list){
			 JSONObject jsonObject=new JSONObject();
			 jsonObject.put("type", DateUtil.date2String(c.getTjyf(),"yyyy-MM-dd"));
			 jsonObject.put("存款余额",c.getCkye());
			 jsonObject.put("存款月日平",c.getCkyrpye());
			 jsonArray.add(jsonObject);
		 }

		 return Result.ok(jsonArray);
	 }


	 /**
	  * 导出excel
	  *
	  * @param request
	  * @param ckjkptKhfzJgckhz
	  */
	 @RequestMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, CkjkptKhfzJgckhz ckjkptKhfzJgckhz) {
		 // Step.1 组装查询条件
		 QueryWrapper<CkjkptKhfzJgckhz> queryWrapper = QueryGenerator.initQueryWrapper(ckjkptKhfzJgckhz, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 // Step.2 获取导出数据
		 List<CkjkptKhfzJgckhz> list = service.list(queryWrapper);
		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, "机构存款汇总统计"); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, CkjkptKhfzJgckhz.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("机构存款汇总统计" + "报表", "导出人:" + sysUser.getRealname(), "机构存款汇总统计"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, list);
		 return mv;
		 //return super.exportXls(request, ckjkptKhfzJgckhz, CkjkptKhfzJgckhz.class, "机构存款汇总统计");
	 }
	/**
	 * 添加
	 *
	 * @param ckjkptKhfzJgckhz
	 * @return
	 */
	@AutoLog(value = "机构存款汇总统计-添加")
	@ApiOperation(value="机构存款汇总统计-添加", notes="机构存款汇总统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CkjkptKhfzJgckhz ckjkptKhfzJgckhz) {
		ckjkptKhfzJgckhzService.save(ckjkptKhfzJgckhz);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ckjkptKhfzJgckhz
	 * @return
	 */
	@AutoLog(value = "机构存款汇总统计-编辑")
	@ApiOperation(value="机构存款汇总统计-编辑", notes="机构存款汇总统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CkjkptKhfzJgckhz ckjkptKhfzJgckhz) {
		ckjkptKhfzJgckhzService.updateById(ckjkptKhfzJgckhz);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "机构存款汇总统计-通过id删除")
	@ApiOperation(value="机构存款汇总统计-通过id删除", notes="机构存款汇总统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ckjkptKhfzJgckhzService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "机构存款汇总统计-批量删除")
	@ApiOperation(value="机构存款汇总统计-批量删除", notes="机构存款汇总统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckjkptKhfzJgckhzService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "机构存款汇总统计-通过id查询")
	@ApiOperation(value="机构存款汇总统计-通过id查询", notes="机构存款汇总统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CkjkptKhfzJgckhz ckjkptKhfzJgckhz = ckjkptKhfzJgckhzService.getById(id);
		return Result.ok(ckjkptKhfzJgckhz);
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
      return super.importExcel(request, response, CkjkptKhfzJgckhz.class);
  }

}

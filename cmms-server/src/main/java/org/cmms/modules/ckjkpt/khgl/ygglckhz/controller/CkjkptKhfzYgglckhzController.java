package org.cmms.modules.ckjkpt.khgl.ygglckhz.controller;

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
import org.cmms.modules.ckjkpt.khgl.ygglckhz.entity.CkjkptKhfzYgglckhz;
import org.cmms.modules.ckjkpt.khgl.ygglckhz.service.ICkjkptKhfzYgglckhzService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ckjkpt.khgl.ygglckkhmx.entity.CkjkptKhfzYgglkhmx;
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
 * @Description: 员工关联存款汇总
 * @Author: jeecg-boot
 * @Date:   2021-07-13
 * @Version: V1.0
 */
@Slf4j
@Api(tags="员工关联存款汇总")
@RestController
@RequestMapping("/ygglckhz/ckjkptKhfzYgglckhz")
public class CkjkptKhfzYgglckhzController extends JeecgController<CkjkptKhfzYgglckhz, ICkjkptKhfzYgglckhzService> {
	@Autowired
	private ICkjkptKhfzYgglckhzService ckjkptKhfzYgglckhzService;

	/**
	 * 分页列表查询
	 *
	 * @param ckjkptKhfzYgglckhz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "员工关联存款汇总-分页列表查询")
	@ApiOperation(value="员工关联存款汇总-分页列表查询", notes="员工关联存款汇总-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CkjkptKhfzYgglckhz ckjkptKhfzYgglckhz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   // String ckyeS,String ckyeE,String cknrpyeS,String cknrpyeE,String ckyrpyeS,String ckyrpyeE,
								   HttpServletRequest req) {
//		QueryWrapper<CkjkptKhfzYgglckhz> queryWrapper = QueryGenerator.initQueryWrapper(ckjkptKhfzYgglckhz, req.getParameterMap());
//		queryWrapper.orderByDesc("tjyf", "zzbz", "yggh", "gwbz");
//		List<CkjkptKhfzYgglckhz> list = ckjkptKhfzYgglckhzService.list(queryWrapper);
//		IPage<CkjkptKhfzYgglckhz> pageList = PageUtil.getPages(list, list == null ? 0 : list.size(), pageSize, pageNo);
//		return Result.ok(pageList);
		QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(ckjkptKhfzYgglckhz, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkjkptKhfzYgglckhzService.class,ckjkptKhfzYgglckhzService,pageNo,pageSize,queryWrapper,"tjyf","zzbz","yggh","gwbz");
		return Result.ok(pageList);
	}

	 /**
	  * 分页列表查询
	  * @return
	  */
	 @AutoLog(value = "员工关联存款客户明细-分页列表查询")
	 @ApiOperation(value="员工关联存款客户明细-分页列表查询", notes="员工关联存款客户明细-分页列表查询")
	 @PutMapping(value = "/queryList")
	 public Result<?> queryList(@RequestBody  CkjkptKhfzYgglckhz ckjkptKhfzYgglckhz) {
		 QueryWrapper<CkjkptKhfzYgglckhz> queryWrapper =new QueryWrapper<>();
		 queryWrapper.eq("zzbz",ckjkptKhfzYgglckhz.getZzbz());
		 queryWrapper.eq("gwbz",ckjkptKhfzYgglckhz.getGwbz());
		 queryWrapper.eq("yggh",ckjkptKhfzYgglckhz.getYggh());
		 queryWrapper.last(" and tjyf >=(select add_months(max(tjyf),-12) from ckjkpt_khfz_ygglckhz) order by tjyf asc ");
		 List<CkjkptKhfzYgglckhz> list = ckjkptKhfzYgglckhzService.list(queryWrapper);
		 JSONArray jsonArray=new JSONArray();
		 for(CkjkptKhfzYgglckhz c: list){
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
	  * @param ckjkptKhfzYgglckhz
	  */
	 @RequestMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, CkjkptKhfzYgglckhz ckjkptKhfzYgglckhz,
								   String ckyeS,String ckyeE,String cknrpyeS,String cknrpyeE,String ckyrpyeS,String ckyrpyeE) {

		 // Step.1 组装查询条件
		 QueryWrapper<CkjkptKhfzYgglckhz> queryWrapper = QueryGenerator.initQueryWrapper(ckjkptKhfzYgglckhz, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//		 if (!StringUtils.isBlank(ckyeS))queryWrapper.ge("ckye",ckyeS);
//		 if (!StringUtils.isBlank(ckyeE))queryWrapper.le("ckye",ckyeE);
//		 if (!StringUtils.isBlank(cknrpyeS))queryWrapper.ge("cknrpye",cknrpyeS);
//		 if (!StringUtils.isBlank(cknrpyeE))queryWrapper.le("cknrpye",cknrpyeE);
//		 if (!StringUtils.isBlank(ckyrpyeS))queryWrapper.ge("ckyrpye",ckyrpyeS);
//		 if (!StringUtils.isBlank(ckyrpyeE))queryWrapper.le("ckyrpye",ckyrpyeE);
		 // Step.2 获取导出数据
		 List<CkjkptKhfzYgglckhz> list = service.list(queryWrapper);
		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, "员工关联存款汇总"); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, CkjkptKhfzYgglckhz.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("员工关联存款汇总" + "报表", "导出人:" + sysUser.getRealname(), "员工关联存款汇总"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, list);
		 return mv;
		// return super.exportXls(request, ckjkptKhfzYgglckhz, CkjkptKhfzYgglckhz.class, "员工关联存款汇总");
	 }

	/**
	 * 添加
	 *
	 * @param ckjkptKhfzYgglckhz
	 * @return
	 */
	@AutoLog(value = "员工关联存款汇总-添加")
	@ApiOperation(value="员工关联存款汇总-添加", notes="员工关联存款汇总-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CkjkptKhfzYgglckhz ckjkptKhfzYgglckhz) {
		ckjkptKhfzYgglckhzService.save(ckjkptKhfzYgglckhz);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ckjkptKhfzYgglckhz
	 * @return
	 */
	@AutoLog(value = "员工关联存款汇总-编辑")
	@ApiOperation(value="员工关联存款汇总-编辑", notes="员工关联存款汇总-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CkjkptKhfzYgglckhz ckjkptKhfzYgglckhz) {
		ckjkptKhfzYgglckhzService.updateById(ckjkptKhfzYgglckhz);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工关联存款汇总-通过id删除")
	@ApiOperation(value="员工关联存款汇总-通过id删除", notes="员工关联存款汇总-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ckjkptKhfzYgglckhzService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "员工关联存款汇总-批量删除")
	@ApiOperation(value="员工关联存款汇总-批量删除", notes="员工关联存款汇总-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckjkptKhfzYgglckhzService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工关联存款汇总-通过id查询")
	@ApiOperation(value="员工关联存款汇总-通过id查询", notes="员工关联存款汇总-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CkjkptKhfzYgglckhz ckjkptKhfzYgglckhz = ckjkptKhfzYgglckhzService.getById(id);
		return Result.ok(ckjkptKhfzYgglckhz);
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
      return super.importExcel(request, response, CkjkptKhfzYgglckhz.class);
  }

}

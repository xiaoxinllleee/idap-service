package org.cmms.modules.ckjkpt.khgl.ygglckkhmx.controller;

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
import org.cmms.modules.ckjkpt.khgl.ygglckkhmx.entity.CkjkptKhfzYgglkhmx;
import org.cmms.modules.ckjkpt.khgl.ygglckkhmx.service.ICkjkptKhfzYgglkhmxService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ckjkpt.khgl.ygglckzhmx.entity.CkjkptKhfzYgglzhmx;
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
 * @Description: 员工关联存款客户明细
 * @Author: jeecg-boot
 * @Date:   2021-07-13
 * @Version: V1.0
 */
@Slf4j
@Api(tags="员工关联存款客户明细")
@RestController
@RequestMapping("/ygglckkhmx/ckjkptKhfzYgglkhmx")
public class CkjkptKhfzYgglkhmxController extends JeecgController<CkjkptKhfzYgglkhmx, ICkjkptKhfzYgglkhmxService> {
	@Autowired
	private ICkjkptKhfzYgglkhmxService ckjkptKhfzYgglkhmxService;

	/**
	 * 分页列表查询
	 *
	 * @param ckjkptKhfzYgglkhmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "员工关联存款客户明细-分页列表查询")
	@ApiOperation(value="员工关联存款客户明细-分页列表查询", notes="员工关联存款客户明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CkjkptKhfzYgglkhmx ckjkptKhfzYgglkhmx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   // String ckyeS,String ckyeE,
								   HttpServletRequest req) {
		QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(ckjkptKhfzYgglkhmx, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkjkptKhfzYgglkhmxService.class,ckjkptKhfzYgglkhmxService,pageNo,pageSize,queryWrapper,"tjyf","yggh","zjhm","zzbz");
		return Result.ok(pageList);
	}


	 /**
	  * 分页列表查询
	  * @return
	  */
	 @AutoLog(value = "员工关联存款客户明细-分页列表查询")
	 @ApiOperation(value="员工关联存款客户明细-分页列表查询", notes="员工关联存款客户明细-分页列表查询")
	 @PutMapping(value = "/queryList")
	 public Result<?> queryList(@RequestBody  CkjkptKhfzYgglkhmx ckjkptKhfzYgglkhmx) {
		 QueryWrapper<CkjkptKhfzYgglkhmx> queryWrapper =new QueryWrapper<>();
		 queryWrapper.eq("zjhm",ckjkptKhfzYgglkhmx.getZjhm());
		 queryWrapper.last(" and tjyf >=(select add_months(max(tjyf),-12) from Ckjkpt_khfz_ygglkhmx) order by tjyf asc");
		 List<CkjkptKhfzYgglkhmx> list = ckjkptKhfzYgglkhmxService.list(queryWrapper);
		 JSONArray jsonArray=new JSONArray();
		 for(CkjkptKhfzYgglkhmx c: list){
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
	  * @param ckjkptKhfzYgglkhmx
	  */
	 @RequestMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, CkjkptKhfzYgglkhmx ckjkptKhfzYgglkhmx, String ckyeS,String ckyeE) {
		 // Step.1 组装查询条件
		 QueryWrapper<CkjkptKhfzYgglkhmx> queryWrapper = QueryGenerator.initQueryWrapper(ckjkptKhfzYgglkhmx, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 if (!StringUtils.isBlank(ckyeS)){
			 queryWrapper.ge("ckye",ckyeS);
		 }
		 if (!StringUtils.isBlank(ckyeE)){
			 queryWrapper.le("ckye",ckyeE);
		 }
		 // Step.2 获取导出数据
		 List<CkjkptKhfzYgglkhmx> list = service.list(queryWrapper);
		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, "员工关联存款客户明细"); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, CkjkptKhfzYgglkhmx.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("员工关联存款客户明细" + "报表", "导出人:" + sysUser.getRealname(), "员工关联存款客户明细"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, list);
		 return mv;
		 //return super.exportXls(request, ckjkptKhfzYgglkhmx, CkjkptKhfzYgglkhmx.class, "员工关联存款客户明细");
	 }

	/**
	 * 添加
	 *
	 * @param ckjkptKhfzYgglkhmx
	 * @return
	 */
	@AutoLog(value = "员工关联存款客户明细-添加")
	@ApiOperation(value="员工关联存款客户明细-添加", notes="员工关联存款客户明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CkjkptKhfzYgglkhmx ckjkptKhfzYgglkhmx) {
		ckjkptKhfzYgglkhmxService.save(ckjkptKhfzYgglkhmx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ckjkptKhfzYgglkhmx
	 * @return
	 */
	@AutoLog(value = "员工关联存款客户明细-编辑")
	@ApiOperation(value="员工关联存款客户明细-编辑", notes="员工关联存款客户明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CkjkptKhfzYgglkhmx ckjkptKhfzYgglkhmx) {
		ckjkptKhfzYgglkhmxService.updateById(ckjkptKhfzYgglkhmx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工关联存款客户明细-通过id删除")
	@ApiOperation(value="员工关联存款客户明细-通过id删除", notes="员工关联存款客户明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ckjkptKhfzYgglkhmxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "员工关联存款客户明细-批量删除")
	@ApiOperation(value="员工关联存款客户明细-批量删除", notes="员工关联存款客户明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckjkptKhfzYgglkhmxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工关联存款客户明细-通过id查询")
	@ApiOperation(value="员工关联存款客户明细-通过id查询", notes="员工关联存款客户明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CkjkptKhfzYgglkhmx ckjkptKhfzYgglkhmx = ckjkptKhfzYgglkhmxService.getById(id);
		return Result.ok(ckjkptKhfzYgglkhmx);
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
      return super.importExcel(request, response, CkjkptKhfzYgglkhmx.class);
  }

}

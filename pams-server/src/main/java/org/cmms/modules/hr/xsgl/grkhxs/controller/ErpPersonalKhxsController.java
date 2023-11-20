package org.cmms.modules.hr.xsgl.grkhxs.controller;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.hr.xsgl.grkhxs.entity.ErpPersonalKhxs;
import org.cmms.modules.hr.xsgl.grkhxs.entity.ErpPersonalKhxsDTO;
import org.cmms.modules.hr.xsgl.grkhxs.service.IErpPersonalKhxsService;
import org.cmms.modules.hr.xsgl.grkhxs.verify.ErpPersonalKhxsImportVerify;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description: 个人考核系数
 * @Author: jeecg-boot
 * @Date:   2021-10-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="个人考核系数")
@RestController
@RequestMapping("/khxs/erpPersonalKhxs")
public class ErpPersonalKhxsController extends JeecgController<ErpPersonalKhxs, IErpPersonalKhxsService> {

	@Autowired
	private IHrBasStaffService hrBasStaffService;

	@Autowired
	private ErpPersonalKhxsImportVerify erpPersonalKhxsImportVerify;
	/**
	 * 分页列表查询
	 *
	 * @param erpPersonalKhxs
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "个人考核系数-分页列表查询")
	@ApiOperation(value="个人考核系数-分页列表查询", notes="个人考核系数-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ErpPersonalKhxs erpPersonalKhxs,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String ygxm =erpPersonalKhxs.getYgxm();
		erpPersonalKhxs.setYgxm(null);
		QueryWrapper<ErpPersonalKhxs> queryWrapper = QueryGenerator.initQueryWrapper(erpPersonalKhxs, req.getParameterMap());
		if (StringUtils.isNotBlank(ygxm)){
			QueryWrapper<HrBasStaff> wrapper = new QueryWrapper<>();
			wrapper.like("ygxm",ygxm);
			List<HrBasStaff> list = hrBasStaffService.list(wrapper);
			List<String> arrayList = new ArrayList<>();
			for (HrBasStaff o : list) {
				arrayList.add(o.getYggh());
			}
			queryWrapper.in("yggh",arrayList);
		}
		Page<ErpPersonalKhxs> page = new Page<ErpPersonalKhxs>(pageNo, pageSize);
		IPage<ErpPersonalKhxs> pageList = service.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param erpPersonalKhxs
	 * @return
	 */
	@AutoLog(value = "个人考核系数-添加")
	@ApiOperation(value="个人考核系数-添加", notes="个人考核系数-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ErpPersonalKhxsDTO erpPersonalKhxs) {
		int count = 0;
		String ygghs = "";
		if (CollUtil.isNotEmpty(erpPersonalKhxs.getStaffs())){
			List<String> staffs = erpPersonalKhxs.getStaffs();
			for (int i = 0; i < staffs.size(); i++) {
				String yggh = staffs.get(i);
//				Date kssj = erpPersonalKhxs.getKssj();
//				Date jssj = erpPersonalKhxs.getJssj();
//				Boolean haveByDate = service.isHaveByDate(yggh, kssj, jssj);
				QueryWrapper<ErpPersonalKhxs> queryWrapper = new QueryWrapper<>();
				queryWrapper.eq("yggh", yggh);
				List<ErpPersonalKhxs> list = service.list(queryWrapper);
				if (list.isEmpty()){
					ErpPersonalKhxs insert = new ErpPersonalKhxs();
					insert.setYggh(yggh);
					insert.setKhxs(erpPersonalKhxs.getKhxs());
					insert.setBcykhxs(erpPersonalKhxs.getBcykhxs());
					insert.setZxs(erpPersonalKhxs.getZxs());
//					insert.setKssj(erpPersonalKhxs.getKssj());
//					insert.setJssj(erpPersonalKhxs.getJssj());
					boolean save = service.save(insert);
					if (save)
						count++;
				}else {
					ygghs += yggh + ", ";
				}

			}
		}
		log.info("===/khxs/erpPersonalKhxs/add新增{}条数据",count);
		//service.save(erpPersonalKhxs);
		System.out.println(ygghs);
		if (ygghs.length()>0){
			return Result.ok("添加成功"+count+"条数据，其中 "+ygghs +" 已存在！");
		}
		return Result.ok("添加成功"+count+"条数据！");
	}

	/**
	 * 编辑
	 *
	 * @param erpPersonalKhxs
	 * @return
	 */
	@AutoLog(value = "个人考核系数-编辑")
	@ApiOperation(value="个人考核系数-编辑", notes="个人考核系数-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ErpPersonalKhxs erpPersonalKhxs) {
		service.updateById(erpPersonalKhxs);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "个人考核系数-通过id删除")
	@ApiOperation(value="个人考核系数-通过id删除", notes="个人考核系数-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		service.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "个人考核系数-批量删除")
	@ApiOperation(value="个人考核系数-批量删除", notes="个人考核系数-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "个人考核系数-通过id查询")
	@ApiOperation(value="个人考核系数-通过id查询", notes="个人考核系数-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ErpPersonalKhxs erpPersonalKhxs = service.getById(id);
		return Result.ok(erpPersonalKhxs);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param erpPersonalKhxs
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ErpPersonalKhxs erpPersonalKhxs) {
	  String ygxm =erpPersonalKhxs.getYgxm();
	  erpPersonalKhxs.setYgxm(null);
	  // Step.1 组装查询条件
	  QueryWrapper<ErpPersonalKhxs> queryWrapper = QueryGenerator.initQueryWrapper(erpPersonalKhxs, request.getParameterMap());
	  if (StringUtils.isNotBlank(ygxm)){
		  QueryWrapper<HrBasStaff> wrapper = new QueryWrapper<>();
		  wrapper.like("ygxm",ygxm);
		  List<HrBasStaff> list = hrBasStaffService.list(wrapper);
		  List<String> arrayList = new ArrayList<>();
		  for (HrBasStaff o : list) {
			  arrayList.add(o.getYggh());
		  }
		  queryWrapper.in("yggh",arrayList);
	  }
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  String selections = request.getParameter("selections");
	  String rowKey = request.getParameter("rowKey");

	  //20211201 过滤选中数据
	  //20211201 BY LIUXIANGQUN 过滤选择的数据改为执行接sql  in  查询
	  if (oConvertUtils.isNotEmpty(selections)) {
		  List<String> selectionList = Arrays.asList(selections.split(","));
		  if(oConvertUtils.isNotEmpty(rowKey)){
			  queryWrapper.in(rowKey,selectionList);
		  }else{
			  queryWrapper.in("ID",selectionList);
		  }
	  }

	  // Step.2 获取导出数据
	  //List<T> pageList = service.list(queryWrapper);
	  //List<T> exportList = null;
	  List<ErpPersonalKhxs> exportList = service.list(queryWrapper);
	  String title = "个人考核系数";
	  // Step.3 AutoPoi 导出Excel
	  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
	  mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
	  mv.addObject(NormalExcelConstants.CLASS, ErpPersonalKhxs.class);
	  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
	  return mv;
  }

	/**
	 * 导出模板Excel
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/exportTemplateXls")
	public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		// 导出文件名称
		mv.addObject(NormalExcelConstants.FILE_NAME, "个人考核系数导入模板");
		mv.addObject(NormalExcelConstants.CLASS, ErpPersonalKhxs.class);
		ExportParams exportParams = new ExportParams("个人考核系数导入模板", "个人考核系数");
		mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		return mv;
	}

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
	  return super.importExcelByTemplate(jsonObject, request, response, ErpPersonalKhxs.class, erpPersonalKhxsImportVerify);
  }

}

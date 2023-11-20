package org.cmms.modules.khlc.khfagl.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khlc.jczbgl.entity.ErpBasZbk;
import org.cmms.modules.khlc.jczbgl.service.IErpBasZbkService;
import org.cmms.modules.khlc.khfagl.entity.*;
import org.cmms.modules.khlc.khfagl.service.IErpAssessPhjfkService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khlc.zbljgl.entity.ErpBasSjxArea;
import org.cmms.modules.khlc.zbljgl.service.IErpBasSjxAreaService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 平衡积分卡考核设置
 * @Author: jeecg-boot
 * @Date:   2023-02-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="平衡积分卡考核设置")
@RestController
@RequestMapping("/khfagl/erpAssessPhjfk")
public class ErpAssessPhjfkController extends JeecgController<ErpAssessPhjfk, IErpAssessPhjfkService> {
	@Autowired
	private IErpAssessPhjfkService erpAssessPhjfkService;
	 @Autowired
	 private IErpBasSjxAreaService erpBasSjxAreaService;
	 @Autowired
	 private IErpBasZbkService erpBasZbkService;

	/**
	 * 分页列表查询
	 *
	 * @param erpAssessPhjfk
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "平衡积分卡考核设置-分页列表查询")
	@ApiOperation(value="平衡积分卡考核设置-分页列表查询", notes="平衡积分卡考核设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ErpAssessPhjfk erpAssessPhjfk,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ErpAssessPhjfk> queryWrapper = QueryGenerator.initQueryWrapper(erpAssessPhjfk, req.getParameterMap());
		queryWrapper.isNotNull("gwbz");
		Page<ErpAssessPhjfk> page = new Page<ErpAssessPhjfk>(pageNo, pageSize);

		IPage<ErpAssessPhjfk> pageList = erpAssessPhjfkService.page(page, queryWrapper);
		return Result.ok(pageList);
	}



	 /**
	  * 分页列表查询
	  *
	  * @param erpAssessPhjfk
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "平衡积分卡考核设置-分页列表查询")
	 @ApiOperation(value="平衡积分卡考核设置-分页列表查询", notes="平衡积分卡考核设置-分页列表查询")
	 @GetMapping(value = "/listJG")
	 public Result<?> queryPagelistJG(ErpAssessPhjfk erpAssessPhjfk,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 QueryWrapper<ErpAssessPhjfk> queryWrapper = QueryGenerator.initQueryWrapper(erpAssessPhjfk, req.getParameterMap());
		 queryWrapper.isNull("gwbz");
		 Page<ErpAssessPhjfk> page = new Page<ErpAssessPhjfk>(pageNo, pageSize);
		 IPage<ErpAssessPhjfk> pageList = erpAssessPhjfkService.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }
	 /**
	  * 机构查找带回
	  * @param
	  * @return
	  */
	 @AutoLog(value = "平衡积分卡考核设置机构-列表查询")
	 @ApiOperation(value = "平衡积分卡考核设置机构-列表查询岗位", notes = "平衡积分卡考核设置机构-列表查询")
	 @GetMapping(value = "/listPhjfkJg")
	 public Result<?> listPhjfkJg(ErpBasSjxArea erpBasSjxArea,
								  @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
								  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
								  HttpServletRequest req) {
		 String zbbm=erpBasSjxArea.getZbbm();
		 erpBasSjxArea.setZbbm(null);
		 QueryWrapper<ErpBasSjxArea> queryWrapper = QueryGenerator.initQueryWrapper(erpBasSjxArea, req.getParameterMap());
		 QueryWrapper<ErpBasZbk> zbk = new QueryWrapper<>();
		 if (StringUtils.isNotBlank(zbbm)){
			 zbk.like("zbmc",zbbm);
			 List<ErpBasZbk> list = erpBasZbkService.list(zbk);
			 List<String> list1 = new ArrayList<>();
			 if (CollUtil.isNotEmpty(list)){
				 for (ErpBasZbk erpBasZbk : list) {
					 list1.add(erpBasZbk.getZbid());
				 }
				 queryWrapper.in("zbid",list1);
			 }
		 }
		 queryWrapper.eq("zblx",1);//机构
		 queryWrapper.eq("khfs",1);//平衡计分卡
		 queryWrapper.eq("sfqy",'1');//是否启用
		 Page<ErpBasSjxArea> page = new Page<ErpBasSjxArea>(pageNo, pageSize);
		 IPage<ErpBasSjxArea> pageList = erpBasSjxAreaService.page(page,queryWrapper);
		 return Result.ok(pageList);
	 }
	 @PostMapping(value = "/listPhjfkJgAll")
	 public Result<?> listPhjfkJgAll(@RequestBody JSONObject jsonObject) {
		 JSONArray jsonArray = jsonObject.getJSONArray("zbidList");
		 String zbid = JSONObject.toJSONString(jsonArray);
		 String zbid2 = zbid.substring(1).replaceAll("]", "").replaceAll("\"","");
		 String[] zbidList = zbid2.split(",");
		 System.out.println(zbidList);
		 List<ErpBasSjxArea> list2 = new ArrayList<>();
		 for (String s : zbidList) {
			 List<ErpBasSjxArea> list = erpAssessPhjfkService.phjfkJg(s.trim());
			 list2.addAll(list);
		 }
		 return Result.ok(list2);
	 }


	 /**
	  * 岗位查找带回
	  * @param
	  * @return
	  */
	 @AutoLog(value = "平衡积分卡考核设置岗位-列表查询")
	 @ApiOperation(value = "平衡积分卡考核设置岗位-列表查询机构", notes = "平衡积分卡考核设置岗位-列表查询")
	 @GetMapping(value = "/listPhjfkGw")
	 public Result<?> listPhjfkGw(ErpBasSjxArea erpBasSjxArea,
								  @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
								  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
								  HttpServletRequest req) {
		 String zbbm=erpBasSjxArea.getZbbm();
		 erpBasSjxArea.setZbbm(null);
		 QueryWrapper<ErpBasSjxArea> queryWrapper = QueryGenerator.initQueryWrapper(erpBasSjxArea, req.getParameterMap());
		 QueryWrapper<ErpBasZbk> zbk = new QueryWrapper<>();
		 if (StringUtils.isNotBlank(zbbm)){
			 zbk.like("zbmc",zbbm);
			 List<ErpBasZbk> list = erpBasZbkService.list(zbk);
			 List<String> list1 = new ArrayList<>();
			 if (CollUtil.isNotEmpty(list)){
				 for (ErpBasZbk erpBasZbk : list) {
					 list1.add(erpBasZbk.getZbid());
				 }
				 queryWrapper.in("zbid",list1);
			 }
		 }
		 queryWrapper.eq("zblx",3);//岗位
		 queryWrapper.eq("khfs",1);//平衡计分卡
		 queryWrapper.eq("sfqy",'1');//是否启用
		 Page<ErpBasSjxArea> page = new Page<ErpBasSjxArea>(pageNo, pageSize);
		 IPage<ErpBasSjxArea> pageList = erpBasSjxAreaService.page(page,queryWrapper);
		 return Result.ok(pageList);
	 }


	 @PostMapping(value = "/listPhjfkGwAll")
	 public Result<?> listPhjfkGwAll(@RequestBody JSONObject jsonObject) {
		 JSONArray jsonArray = jsonObject.getJSONArray("zbidList");
		 String zbid = JSONObject.toJSONString(jsonArray);
		 String zbid2 = zbid.substring(1).replaceAll("]", "").replaceAll("\"","");
		 String[] zbidList = zbid2.split(",");
		 System.out.println(zbidList);
		 List<ErpBasSjxArea> list2 = new ArrayList<>();
		 for (String s : zbidList) {
			 List<ErpBasSjxArea> list = erpAssessPhjfkService.phjfkGw(s.trim());
			 list2.addAll(list);
		 }
		 return Result.ok(list2);
	 }

	/**
	 * 添加
	 *
	 * @param erpAssessPhjfk
	 * @return
	 */
	@AutoLog(value = "平衡积分卡考核设置-添加")
	@ApiOperation(value="平衡积分卡考核设置-添加", notes="平衡积分卡考核设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ErpAssessPhjfk erpAssessPhjfk) {
		erpAssessPhjfkService.save(erpAssessPhjfk);
		return Result.ok("添加成功！");
	}

	/**
	 * 机构设置
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "平衡积分卡考核设置-添加")
	@ApiOperation(value="平衡积分卡考核设置-添加", notes="平衡积分卡考核设置-添加")
	@PostMapping(value = "/JgSave")
	public Result<?> JgSave(@RequestBody JSONObject jsonObject) {
		JSONArray jsonArray = jsonObject.getJSONArray("zbidList");
		String js = JSONObject.toJSONString(jsonArray);
		String schemeId = jsonObject.getString("schemeId");
		String jfz = jsonObject.getString("jfz");

		String zzbz = jsonObject.getString("zzbzLsit");
		String[] zzbzList = zzbz.split(",");

		List<ErpAssessPhjfk> erpAssessAljcs = JSONObject.parseArray(js, ErpAssessPhjfk.class);
		List<ErpAssessPhjfk> insertList = new ArrayList<>();
		for (ErpAssessPhjfk s : erpAssessAljcs) {//遍历指标
			QueryWrapper<ErpAssessPhjfk> queryWrapper = new QueryWrapper();
			queryWrapper.eq("scheme_id", schemeId);
			queryWrapper.in("zzbz", zzbzList);
			queryWrapper.eq("zbid", s.getZbid());
			queryWrapper.eq("zbwd", s.getZbwd());
			erpAssessPhjfkService.remove(queryWrapper);
			for (String bz : zzbzList) {
				ErpAssessPhjfk erpAssessPhjfk = new ErpAssessPhjfk();
				if (s.getZbqz() == null) {
					s.setZbqz(new BigDecimal(100));
				}
				erpAssessPhjfk.setId(IdUtil.simpleUUID());
				erpAssessPhjfk.setZzbz(bz.trim());
				erpAssessPhjfk.setZbid(s.getZbid());
				erpAssessPhjfk.setJfz(Integer.valueOf(jfz));
				erpAssessPhjfk.setSchemeId(schemeId);
				erpAssessPhjfk.setZbwd(s.getZbwd());
				erpAssessPhjfk.setZbfz(s.getZbfz());
				erpAssessPhjfk.setZbabs(s.getZbabs());
				erpAssessPhjfk.setXzfz(s.getXzfz());
				erpAssessPhjfk.setKqxzfz(s.getKqxzfz());
				erpAssessPhjfk.setJqbl(s.getJqbl());
				erpAssessPhjfk.setJqblfz(s.getJqblfz());
				erpAssessPhjfk.setKqbl(s.getKqbl());
				erpAssessPhjfk.setKqblfz(s.getKqblfz());
				erpAssessPhjfk.setZbqz(s.getZbqz());
				erpAssessPhjfk.setCreateBy(getUsername());
				erpAssessPhjfk.setCreateTime(new Date());
				insertList.add(erpAssessPhjfk);
			}
		}
		if (!insertList.isEmpty()) {
			erpAssessPhjfkService.saveBatch(insertList);
		}

		return Result.ok("添加成功！");
	}

	 /**
	  * 岗位设置
	  *
	  * @param
	  * @return
	  */
	 @AutoLog(value = "平衡积分卡考核设置-添加")
	 @ApiOperation(value="平衡积分卡考核设置-添加", notes="平衡积分卡考核设置-添加")
	 @PostMapping(value = "/GwSave")
	 public Result<?> GwSave(@RequestBody JSONObject jsonObject) {
		 JSONArray jsonArray = jsonObject.getJSONArray("zbidList");
		 String js = JSONObject.toJSONString(jsonArray);
		 String schemeId = jsonObject.getString("schemeId");
		 String jfz = jsonObject.getString("jfz");

		 String zzbz = jsonObject.getString("zzbzLsit");
		 String[] zzbzList = zzbz.split(",");

		 String gwbz = jsonObject.getString("gwbzLsit");
		 String[] gwbzList = gwbz.split(",");
		 List<ErpAssessPhjfk> erpAssessAljcs = JSONObject.parseArray(js, ErpAssessPhjfk.class);
		 List<ErpAssessPhjfk> insertList = new ArrayList<>();
		 for (ErpAssessPhjfk s : erpAssessAljcs) {//遍历指标
			 QueryWrapper<ErpAssessPhjfk> queryWrapper = new QueryWrapper();
			 queryWrapper.eq("scheme_id", schemeId);
			 queryWrapper.in("zzbz", zzbzList);
			 queryWrapper.in("gwbz", gwbzList);
			 queryWrapper.eq("zbid", s.getZbid());
			 queryWrapper.eq("zbwd", s.getZbwd());
			 erpAssessPhjfkService.remove(queryWrapper);
			 //遍历组织标识
			 for (String bz : zzbzList) {
				 for (String s1 : gwbzList) {//遍历岗位
					 ErpAssessPhjfk erpAssessPhjfk = new ErpAssessPhjfk();
					 if (s.getZbqz() == null) {
						 s.setZbqz(new BigDecimal(100));
					 }

					 erpAssessPhjfk.setId(IdUtil.simpleUUID());
					 erpAssessPhjfk.setZzbz(bz.trim());
					 erpAssessPhjfk.setZbid(s.getZbid());
					 erpAssessPhjfk.setJfz(Integer.valueOf(jfz));
					 erpAssessPhjfk.setSchemeId(schemeId);
					 erpAssessPhjfk.setGwbz(Integer.valueOf(s1.trim()));
					 erpAssessPhjfk.setZbwd(s.getZbwd());
					 erpAssessPhjfk.setZbfz(s.getZbfz());
					 erpAssessPhjfk.setXzfz(s.getXzfz());
					 erpAssessPhjfk.setKqxzfz(s.getKqxzfz());
					 erpAssessPhjfk.setZbabs(s.getZbabs());
					 erpAssessPhjfk.setKhlb(s.getKhlb());
					 erpAssessPhjfk.setJqbl(s.getJqbl());
					 erpAssessPhjfk.setJqblfz(s.getJqblfz());
					 erpAssessPhjfk.setKqbl(s.getKqbl());
					 erpAssessPhjfk.setKqblfz(s.getKqblfz());
					 erpAssessPhjfk.setZbqz(s.getZbqz());
					 erpAssessPhjfk.setCreateBy(getUsername());
					 erpAssessPhjfk.setCreateTime(new Date());
					 insertList.add(erpAssessPhjfk);
				 }
			 }
		 }
		 erpAssessPhjfkService.saveBatch(insertList);
		 return Result.ok("添加成功！");
	 }

	/**
	 * 编辑
	 *
	 * @param erpAssessPhjfk
	 * @return
	 */
	@AutoLog(value = "平衡积分卡考核设置-编辑")
	@ApiOperation(value="平衡积分卡考核设置-编辑", notes="平衡积分卡考核设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ErpAssessPhjfk erpAssessPhjfk) {
		erpAssessPhjfkService.updateById(erpAssessPhjfk);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "平衡积分卡考核设置-通过id删除")
	@ApiOperation(value="平衡积分卡考核设置-通过id删除", notes="平衡积分卡考核设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		erpAssessPhjfkService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "平衡积分卡考核设置-批量删除")
	@ApiOperation(value="平衡积分卡考核设置-批量删除", notes="平衡积分卡考核设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.erpAssessPhjfkService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "平衡积分卡考核设置-通过id查询")
	@ApiOperation(value="平衡积分卡考核设置-通过id查询", notes="平衡积分卡考核设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ErpAssessPhjfk erpAssessPhjfk = erpAssessPhjfkService.getById(id);
		return Result.ok(erpAssessPhjfk);
	}


	 /**
	  * 导出excel
	  *
	  * @param request
	  * @param erpAssessPhjfk
	  */
	 @RequestMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, ErpAssessPhjfk erpAssessPhjfk) {
		 QueryWrapper<ErpAssessPhjfk> queryWrapper = QueryGenerator.initQueryWrapper(erpAssessPhjfk, request.getParameterMap());
		 queryWrapper.isNotNull("gwbz");
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
		 List<ErpAssessPhjfk> exportList = service.list(queryWrapper);
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, "岗位平衡计分卡设置"); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, ErpAssessPhjfk.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("岗位平衡计分卡设置" + "报表", "导出人:" + sysUser.getRealname(), "岗位按量计酬考核设置"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
	 }

	 /**
	  * 导出excel
	  *
	  * @param request
	  * @param erpAssessPhjfk
	  */
	 @RequestMapping(value = "/exportXlsJG")
	 public ModelAndView exportXlsJG(HttpServletRequest request, ErpAssessPhjfk erpAssessPhjfk) {
		 QueryWrapper<ErpAssessPhjfk> queryWrapper = QueryGenerator.initQueryWrapper(erpAssessPhjfk, request.getParameterMap());
		 queryWrapper.isNull("gwbz");
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
		 List<ErpAssessPhjfk> exportList = service.list(queryWrapper);
		 List<ErpAssessPhjfkExportJGVO> exportListJg =new ArrayList<>();
		 for(ErpAssessPhjfk erpAssessPhjfk1 :exportList){
			 ErpAssessPhjfkExportJGVO erpAssessPhjfkJGVO=new ErpAssessPhjfkExportJGVO();
			 BeanUtils.copyProperties(erpAssessPhjfk1,erpAssessPhjfkJGVO);
			 exportListJg.add(erpAssessPhjfkJGVO);
		 }
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, "机构平衡计分卡设置"); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, ErpAssessPhjfkExportJGVO.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("机构平衡计分卡设置" + "报表", "导出人:" + sysUser.getRealname(), "岗位按量计酬考核设置"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportListJg);
		 return mv;
	 }



	 /**
	  * 导出模板
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "岗位平衡计分卡分值设置导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, ErpAssessPhjfkVO.class);
		 ExportParams exportParams = new ExportParams("岗位平衡计分卡分值设置导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
	 }

	 /**
	  * 导出模板
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXlsJG")
	 public ModelAndView exportTemplateXlsJG(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "机构平衡计分卡分值设置导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, ErpAssessPhjfkJGVO.class);
		 ExportParams exportParams = new ExportParams("机构平衡计分卡分值设置导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
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
	  String filePaths = jsonObject.getString("filePaths");
	  if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
		  return Result.error("请先上传文件！");
	  }
	  String[] filePathList = filePaths.split(",");
	  JSONObject obj = new JSONObject();
	  for (String filePath : filePathList) {
		  String baseFilePath = uploadpath + File.separator + filePath;
//          MultipartFile file = entity.getValue();// 获取上传文件对象
		  File file = new File(baseFilePath);
		  ImportParams params = new ImportParams();
		  params.setTitleRows(1);
		  params.setHeadRows(1);
		  params.setNeedSave(false);
			/* if (verifyHandler != null) {
				 params.setVerifyHanlder(verifyHandler);
			 }*/
		  FileOutputStream fos = null;
		  FileInputStream fis = null;
		  try {
			  fis = new FileInputStream(file);
			  boolean checkResult = ExcelImportCheckUtil.check(fis, ErpAssessPhjfkVO.class, params);
			  ExcelImportResult<ErpAssessPhjfkVO> importResult = ExcelImportUtil.importExcelVerify(file, ErpAssessPhjfkVO.class, params);
			  List<ErpAssessPhjfkVO> list = importResult.getList();
			  List<ErpAssessPhjfk> list1 =new ArrayList<>();
			  for(ErpAssessPhjfkVO erpAssessPhjfkVO:list){
				  ErpAssessPhjfk erpAssessPhjfk=new ErpAssessPhjfk();
				  BeanUtils.copyProperties(erpAssessPhjfkVO, erpAssessPhjfk);
				  QueryWrapper<ErpAssessPhjfk> queryWrapper=new QueryWrapper();
				  queryWrapper.eq("scheme_id",erpAssessPhjfk.getSchemeId());
				  queryWrapper.eq("zzbz",erpAssessPhjfk.getZzbz());
				  queryWrapper.eq("gwbz",erpAssessPhjfk.getGwbz());
				  queryWrapper.eq("zbid",erpAssessPhjfk.getZbid());
				  queryWrapper.eq("zbwd",erpAssessPhjfk.getZbwd());
				  service.remove(queryWrapper);
				  list1.add(erpAssessPhjfk);
			  }
			  service.saveBatch(list1);
			  obj.put("filePath", filePath);
			  fos = new FileOutputStream(baseFilePath);
			  importResult.getWorkbook().write(fos);
			  fos.flush();
			  fos.close();
			  return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
		  } catch (Exception e) {
			  log.error(e.getMessage(),e);
			  return Result.error("文件导入失败:"+e.getMessage());
		  } finally {
			  IoUtil.close(fis);
			  IoUtil.close(fos);
		  }
	  }
	  return Result.ok("文件导入失败！");
  }


	 /**
	  * 通过excel导入数据
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcelJG", method = RequestMethod.POST)
	 public Result<?> importExcelJG(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 String filePaths = jsonObject.getString("filePaths");
		 if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
			 return Result.error("请先上传文件！");
		 }
		 String[] filePathList = filePaths.split(",");
		 JSONObject obj = new JSONObject();
		 for (String filePath : filePathList) {
			 String baseFilePath = uploadpath + File.separator + filePath;
//          MultipartFile file = entity.getValue();// 获取上传文件对象
			 File file = new File(baseFilePath);
			 ImportParams params = new ImportParams();
			 params.setTitleRows(1);
			 params.setHeadRows(1);
			 params.setNeedSave(false);
			/* if (verifyHandler != null) {
				 params.setVerifyHanlder(verifyHandler);
			 }*/
			 FileOutputStream fos = null;
			 FileInputStream fis = null;
			 try {
				 fis = new FileInputStream(file);
				 ExcelImportResult<ErpAssessPhjfkJGVO> importResult = ExcelImportUtil.importExcelVerify(file, ErpAssessPhjfkJGVO.class, params);
				 List<ErpAssessPhjfkJGVO> list = importResult.getList();

				 List<ErpAssessPhjfk> list1 =new ArrayList<>();
				 for(ErpAssessPhjfkJGVO erpAssessPhjfkJGVO:list){
					 ErpAssessPhjfk erpAssessPhjfk=new ErpAssessPhjfk();
					 BeanUtils.copyProperties(erpAssessPhjfkJGVO, erpAssessPhjfk);
					 QueryWrapper<ErpAssessPhjfk> queryWrapper=new QueryWrapper();
					 queryWrapper.eq("scheme_id",erpAssessPhjfk.getSchemeId());
					 queryWrapper.eq("zzbz",erpAssessPhjfk.getZzbz());
					 queryWrapper.isNull("gwbz");
					 queryWrapper.eq("zbid",erpAssessPhjfk.getZbid());
					 queryWrapper.eq("zbwd",erpAssessPhjfk.getZbwd());
					 service.remove(queryWrapper);
					 list1.add(erpAssessPhjfk);
				 }
				 service.saveBatch(list1);
				 obj.put("filePath", filePath);
				 fos = new FileOutputStream(baseFilePath);
				 importResult.getWorkbook().write(fos);
				 fos.flush();
				 fos.close();
				 return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
			 } catch (Exception e) {
				 log.error(e.getMessage(),e);
				 return Result.error("文件导入失败:"+e.getMessage());
			 } finally {
				 IoUtil.close(fis);
				 IoUtil.close(fos);
			 }
		 }
		 return Result.ok("文件导入失败！");
	 }
}

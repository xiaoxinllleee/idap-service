package org.cmms.modules.khlc.khfagl.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.record.ArrayRecord;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdzbk.entity.Pdzbk;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.pdzbsz.entity.Pdzbsz;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasPost;
import org.cmms.modules.khlc.jczbgl.entity.ErpBasZbk;
import org.cmms.modules.khlc.jczbgl.entity.HrPostOrg;
import org.cmms.modules.khlc.jczbgl.service.IErpBasZbkService;
import org.cmms.modules.khlc.khfagl.entity.*;
import org.cmms.modules.khlc.khfagl.service.IErpAssessAljcService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khlc.khfagl.verify.KhzbAljcImportVerify;
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
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 按量计酬考核设置
 * @Author: jeecg-boot
 * @Date: 2023-02-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "按量计酬考核设置")
@RestController
@RequestMapping("/khfagl/erpAssessAljc")
public class ErpAssessAljcController extends JeecgController<ErpAssessAljc, IErpAssessAljcService> {
    @Autowired
    private IErpAssessAljcService erpAssessAljcService;
    @Autowired
    private KhzbAljcImportVerify khzbAljcImportVerify;
    @Autowired
    private IErpBasSjxAreaService erpBasSjxAreaService;
    @Autowired
    private IErpBasZbkService erpBasZbkService;

    /**
     * 分页列表查询
     *
     * @param erpAssessAljc
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "按量计酬考核设置-分页列表查询")
    @ApiOperation(value = "按量计酬考核设置-分页列表查询", notes = "按量计酬考核设置-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(ErpAssessAljc erpAssessAljc,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<ErpAssessAljc> queryWrapper = QueryGenerator.initQueryWrapper(erpAssessAljc, req.getParameterMap());
        queryWrapper.isNotNull("gwbz");
        Page<ErpAssessAljc> page = new Page<ErpAssessAljc>(pageNo, pageSize);
        IPage<ErpAssessAljc> pageList = erpAssessAljcService.page(page, queryWrapper);
        return Result.ok(pageList);
    }


    @AutoLog(value = "评定指标库-列表查询")
    @ApiOperation(value = "评定指标库-列表查询", notes = "评定指标库-列表查询")
    @GetMapping(value = "/listKhdx")
    public Result<?> listKhdx() {
        List<HrPostOrg> list = erpAssessAljcService.khdx();
        return Result.ok(list);
    }

	/**
	 * 岗位查找带回
	 * @param
	 * @return
	 */
    @AutoLog(value = "按量计酬考核设置岗位-列表查询")
    @ApiOperation(value = "按量计酬考核设置-列表查询岗位", notes = "按量计酬考核设置-列表查询")
    @GetMapping(value = "/listArea")
    public Result<?> listArea(ErpBasSjxArea erpBasSjxArea,
							  @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
							  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
							  HttpServletRequest req) {
		String zbbm	=erpBasSjxArea.getZbbm();
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
		queryWrapper.eq("khfs",3);//按量计酬
		queryWrapper.eq("sfqy",'1');
		Page<ErpBasSjxArea> page = new Page<ErpBasSjxArea>(pageNo, pageSize);
        IPage<ErpBasSjxArea> pageList = erpBasSjxAreaService.page(page,queryWrapper);
        return Result.ok(pageList);
    }

	@PostMapping(value = "/listArea2")
	public Result<?> listArea2(@RequestBody JSONObject jsonObject) {
		JSONArray jsonArray = jsonObject.getJSONArray("zbidList");
		String zbid = JSONObject.toJSONString(jsonArray);
		String zbid2 = zbid.substring(1).replaceAll("]", "").replaceAll("\"","");
		String[] zbidList = zbid2.split(",");
		List<ErpBasSjxArea> list2 = new ArrayList<>();
		for (String s : zbidList) {
			List<ErpBasSjxArea> list = erpAssessAljcService.area(s.trim());
			list2.addAll(list);
		}
		return Result.ok(list2);

	}

	/**
	 * 机构查找带回
	 * @param
	 * @return
	 */
	@AutoLog(value = "按量计酬考核设置机构-列表查询")
	@ApiOperation(value = "按量计酬考核设置-列表查询机构", notes = "按量计酬考核设置-列表查询")
	@GetMapping(value = "/listAllJg")
	public Result<?> listAllJg(ErpBasSjxArea erpBasSjxArea,
							  @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
							  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
							  HttpServletRequest req) {
		String zbbm	=erpBasSjxArea.getZbbm();
		erpBasSjxArea.setZbbm(null);
		QueryWrapper<ErpBasSjxArea> queryWrapper = QueryGenerator.initQueryWrapper(erpBasSjxArea, req.getParameterMap());
		QueryWrapper<ErpBasZbk> zbk = new QueryWrapper<>();
		List<String> list1 = new ArrayList<>();
		if (StringUtils.isNotBlank(zbbm)){
			zbk.like("zbmc",zbbm);
			List<ErpBasZbk> list = erpBasZbkService.list(zbk);
			if (CollUtil.isNotEmpty(list)){
				for (ErpBasZbk erpBasZbk : list) {
					list1.add(erpBasZbk.getZbid());
				}
				queryWrapper.in("zbid",list1);
			}
		}
		queryWrapper.eq("zblx",1);//机构
		queryWrapper.eq("khfs",3);//按量计酬
		queryWrapper.eq("sfqy",'1');
		Page<ErpBasSjxArea> page = new Page<ErpBasSjxArea>(pageNo, pageSize);
		IPage<ErpBasSjxArea> pageList = erpBasSjxAreaService.page(page,queryWrapper);
		return Result.ok(pageList);
	}

	@AutoLog(value = "按量计酬考核设置机构-列表查询")
	@ApiOperation(value = "按量计酬考核设置-列表查询机构", notes = "按量计酬考核设置-列表查询")
	@PostMapping(value = "/listAllJg2")
	public Result<?> listAllJg(@RequestBody JSONObject jsonObject) {
		JSONArray jsonArray = jsonObject.getJSONArray("zbidList");
		String zbid = JSONObject.toJSONString(jsonArray);
		String zbid2 = zbid.substring(1).replaceAll("]", "").replaceAll("\"","");
		String[] zbidList = zbid2.split(",");
		List<ErpBasSjxArea> list2 = new ArrayList<>();
		for (String s : zbidList) {
			List<ErpBasSjxArea> list = erpAssessAljcService.areaJg(s.trim());
			list2.addAll(list);
		}
		return Result.ok(list2);
	}
    /**
     * 分页列表查询
     *
     * @param erpAssessAljc
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "按量计酬考核设置-分页列表查询")
    @ApiOperation(value = "按量计酬考核设置-分页列表查询", notes = "按量计酬考核设置-分页列表查询")
    @GetMapping(value = "/listJG")
    public Result<?> queryPagelistJG(ErpAssessAljc erpAssessAljc,
                                     @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                     HttpServletRequest req) {
        QueryWrapper<ErpAssessAljc> queryWrapper = QueryGenerator.initQueryWrapper(erpAssessAljc, req.getParameterMap());
        queryWrapper.isNull("gwbz");
        Page<ErpAssessAljc> page = new Page<ErpAssessAljc>(pageNo, pageSize);
        IPage<ErpAssessAljc> pageList = erpAssessAljcService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param
     * @return
     */
    @AutoLog(value = "按量计酬考核设置-添加")
    @ApiOperation(value = "按量计酬考核设置-添加", notes = "按量计酬考核设置-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("zbidList");
        String js = JSONObject.toJSONString(jsonArray);
        String schemeId = jsonObject.getString("schemeId");

        String zzbz = jsonObject.getString("zzbzLsit");
        String[] zzbzList = zzbz.split(",");


        String gwbz = jsonObject.getString("gwbzLsit");
        String[] gwbzList = gwbz.split(",");
        List<ErpAssessAljc> erpAssessAljcs = JSONObject.parseArray(js, ErpAssessAljc.class);
		List<ErpAssessAljc> insertList = new ArrayList<>();
		for (ErpAssessAljc s : erpAssessAljcs) {//遍历指标
			//删除数据
			QueryWrapper<ErpAssessAljc> queryWrapper = new QueryWrapper();
			queryWrapper.eq("scheme_id", schemeId);
			queryWrapper.in("zzbz", zzbzList);
			queryWrapper.in("gwbz", gwbzList);
			queryWrapper.eq("zbid", s.getZbid());
			queryWrapper.eq("zbwd", s.getZbwd());
			erpAssessAljcService.remove(queryWrapper);
			//遍历组织标识
			for (String bz : zzbzList) {
				for (String s1 : gwbzList) {//遍历岗位
					if (s.getTjxs() == null) {
						s.setTjxs(new BigDecimal(1));
					}
					if (s.getZbqz() == null) {
						s.setZbqz(new BigDecimal(100));
					}
					ErpAssessAljc erpAssessAljc = new ErpAssessAljc();
					erpAssessAljc.setId(IdUtil.simpleUUID());
					erpAssessAljc.setSchemeId(schemeId);
					erpAssessAljc.setZzbz(bz.trim());
					erpAssessAljc.setGwbz(Integer.valueOf(s1.trim()));
					erpAssessAljc.setZbid(s.getZbid());
					erpAssessAljc.setZbwd(s.getZbwd());
					erpAssessAljc.setZbdj(s.getZbdj());
					erpAssessAljc.setZbdw(s.getZbdw());
					erpAssessAljc.setTjxs(s.getTjxs());
					erpAssessAljc.setZbqz(s.getZbqz());
					if (s.getRwwdj() != null) {
						erpAssessAljc.setRwwdj(s.getRwwdj());
					}
					erpAssessAljc.setCreateBy(getUsername());
					erpAssessAljc.setCreateTime(new Date());
					insertList.add(erpAssessAljc);
				}
			}
		}
		if (!erpAssessAljcs.isEmpty()) {
			erpAssessAljcService.saveBatch(insertList);
		}
        return Result.ok("设置成功！");
    }
    /**
     * 添加
     *
     * @param
     * @return
     */
    @AutoLog(value = "按量计酬考核设置-添加")
    @ApiOperation(value = "按量计酬考核设置-添加", notes = "按量计酬考核设置-添加")
    @PostMapping(value = "/addJg")
    public Result<?> addJg(@RequestBody JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("zbidList");
        String js = JSONObject.toJSONString(jsonArray);
        String schemeId = jsonObject.getString("schemeId");

        String zzbz = jsonObject.getString("zzbzLsit");
        String[] zzbzList = zzbz.split(",");

        List<ErpAssessAljc> erpAssessAljcs = JSONObject.parseArray(js, ErpAssessAljc.class);

		List<ErpAssessAljc> insertList = new ArrayList<>();
		for (ErpAssessAljc s : erpAssessAljcs) {//遍历指标
			//删除数据
			QueryWrapper<ErpAssessAljc> queryWrapper = new QueryWrapper();
			queryWrapper.eq("scheme_id", schemeId);
			queryWrapper.in("zzbz", zzbzList);
			queryWrapper.eq("zbid", s.getZbid());
			queryWrapper.eq("zbwd", s.getZbwd());
			erpAssessAljcService.remove(queryWrapper);
			//遍历组织标识
			for (String bz : zzbzList) {
				if (s.getTjxs() == null) {
					s.setTjxs(new BigDecimal(1));
				}
				if (s.getZbqz() == null) {
					s.setZbqz(new BigDecimal(100));
				}
				ErpAssessAljc erpAssessAljc = new ErpAssessAljc();
				erpAssessAljc.setId(IdUtil.simpleUUID());
				erpAssessAljc.setSchemeId(schemeId);
				erpAssessAljc.setZzbz(bz.trim());
				erpAssessAljc.setZbid(s.getZbid());
				erpAssessAljc.setZbwd(s.getZbwd());
				erpAssessAljc.setZbdj(s.getZbdj());
				erpAssessAljc.setZbdw(s.getZbdw());
				erpAssessAljc.setTjxs(s.getTjxs());
				erpAssessAljc.setZbqz(s.getZbqz());
				if (s.getRwwdj() != null) {
					erpAssessAljc.setRwwdj(s.getRwwdj());
				}
				erpAssessAljc.setCreateBy(getUsername());
				erpAssessAljc.setCreateTime(new Date());
				insertList.add(erpAssessAljc);
			}
		}
		if (!erpAssessAljcs.isEmpty()) {
			erpAssessAljcService.saveBatch(insertList);
		}

        return Result.ok("设置成功！");
    }

	/**
	 * 编辑
	 *
	 * @param erpAssessAljc
	 * @return
	 */
	@AutoLog(value = "按量计酬考核设置-编辑")
	@ApiOperation(value="按量计酬考核设置-编辑", notes="按量计酬考核设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ErpAssessAljc erpAssessAljc) {
		erpAssessAljcService.updateById(erpAssessAljc);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "按量计酬考核设置-通过id删除")
	@ApiOperation(value="按量计酬考核设置-通过id删除", notes="按量计酬考核设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		erpAssessAljcService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "按量计酬考核设置-批量删除")
	@ApiOperation(value="按量计酬考核设置-批量删除", notes="按量计酬考核设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.erpAssessAljcService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "按量计酬考核设置-通过id查询")
	@ApiOperation(value="按量计酬考核设置-通过id查询", notes="按量计酬考核设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ErpAssessAljc erpAssessAljc = erpAssessAljcService.getById(id);
		return Result.ok(erpAssessAljc);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param erpAssessAljc
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ErpAssessAljc erpAssessAljc) {
	  QueryWrapper<ErpAssessAljc> queryWrapper = QueryGenerator.initQueryWrapper(erpAssessAljc, request.getParameterMap());
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
	  List<ErpAssessAljc> exportList = service.list(queryWrapper);
	  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
	  mv.addObject(NormalExcelConstants.FILE_NAME, "岗位按量计酬考核设置"); //此处设置的filename无效 ,前端会重更新设置一下
	  mv.addObject(NormalExcelConstants.CLASS, ErpAssessAljc.class);
	  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("岗位按量计酬考核设置" + "报表", "导出人:" + sysUser.getRealname(), "岗位按量计酬考核设置"));
	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
      return mv;
  }

	 /**
	  * 导出excel
	  *
	  * @param request
	  * @param erpAssessAljc
	  */
	 @RequestMapping(value = "/exportXlsJG")
	 public ModelAndView exportXlsJG(HttpServletRequest request, ErpAssessAljc erpAssessAljc) {
		 QueryWrapper<ErpAssessAljc> queryWrapper = QueryGenerator.initQueryWrapper(erpAssessAljc, request.getParameterMap());
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
		 List<ErpAssessAljc> exportList = service.list(queryWrapper);
		 List<ErpAssessAljcExportJGVO> exportListJg =new ArrayList<>();
		 for(ErpAssessAljc erpAssessAljc1 :exportList){
			 ErpAssessAljcExportJGVO erpAssessAljcJGVO=new ErpAssessAljcExportJGVO();
			 BeanUtils.copyProperties(erpAssessAljc1,erpAssessAljcJGVO);
			 exportListJg.add(erpAssessAljcJGVO);
		 }
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, "机构按量计酬考核设置"); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, ErpAssessAljcExportJGVO.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("机构按量计酬考核设置" + "报表", "导出人:" + sysUser.getRealname(), "岗位按量计酬考核设置"));
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "岗位按量计酬考核设置导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, ErpAssessAljcVO.class);
		 ExportParams exportParams = new ExportParams("岗位按量计酬考核设置导入模板", "模板信息");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "机构按量计酬考核设置导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, ErpAssessAljcJGVO.class);
		 ExportParams exportParams = new ExportParams("机构按量计酬考核设置导入模板", "模板信息");
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
				 ExcelImportResult<ErpAssessAljcVO> importResult = ExcelImportUtil.importExcelVerify(file, ErpAssessAljcVO.class, params);
				 List<ErpAssessAljcVO> list = importResult.getList();
				 List<ErpAssessAljc> list1 =new ArrayList<>();
				 for(ErpAssessAljcVO erpAssessAljcVO:list){
					 ErpAssessAljc erpAssessAljc=new ErpAssessAljc();
					 BeanUtils.copyProperties(erpAssessAljcVO, erpAssessAljc);
					 QueryWrapper<ErpAssessAljc> queryWrapper=new QueryWrapper();
					 queryWrapper.eq("scheme_id",erpAssessAljc.getSchemeId());
					 queryWrapper.eq("zzbz",erpAssessAljc.getZzbz());
					 queryWrapper.eq("gwbz",erpAssessAljc.getGwbz());
					 queryWrapper.eq("zbid",erpAssessAljc.getZbid());
					 queryWrapper.eq("zbwd",erpAssessAljc.getZbwd());
					 erpAssessAljcService.remove(queryWrapper);
					 list1.add(erpAssessAljc);
				 }
				 erpAssessAljcService.saveBatch(list1);
				 obj.put("filePath", filePath);
				 fos = new FileOutputStream(baseFilePath);
				 importResult.getWorkbook().write(fos);
				 fos.flush();
				 fos.close();
				 return Result.ok("文件导入完成！成功导入数据行数:" + list1.size(), obj);
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
				 ExcelImportResult<ErpAssessAljcJGVO> importResult = ExcelImportUtil.importExcelVerify(file, ErpAssessAljcJGVO.class, params);
				 List<ErpAssessAljcJGVO> list = importResult.getList();

				 List<ErpAssessAljc> list1 =new ArrayList<>();
				 for(ErpAssessAljcJGVO erpAssessAljcJGVO:list){
					 ErpAssessAljc erpAssessAljc=new ErpAssessAljc();
					 BeanUtils.copyProperties(erpAssessAljcJGVO, erpAssessAljc);
					 QueryWrapper<ErpAssessAljc> queryWrapper=new QueryWrapper();
					 queryWrapper.eq("scheme_id",erpAssessAljc.getSchemeId());
					 queryWrapper.eq("zzbz",erpAssessAljc.getZzbz());
					 queryWrapper.eq("zbid",erpAssessAljc.getZbid());
					 queryWrapper.eq("zbwd",erpAssessAljc.getZbwd());
					 queryWrapper.isNull("gwbz");
					 service.remove(queryWrapper);
					 list1.add(erpAssessAljc);
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

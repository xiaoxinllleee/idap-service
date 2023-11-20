package org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbrwgl.controller;

import java.math.BigDecimal;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdzbk.entity.Pdzbk;
import org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdzbk.service.IPdzbkService;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.entity.Khjljcsjsz;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.service.IKhjljcsjszService;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.pdzbsz.service.IPdzbszService;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.rypdsz.entity.Rypdsz;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.rypdsz.service.IRypdszService;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbrwgl.entity.Zbrwgl;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbrwgl.entity.ZbrwglVo;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbrwgl.service.IZbrwglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbrwgl.verify.ZbrwglImportVerify;
import org.cmms.modules.khlc.khfagl.entity.ErpAssessAljc;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.servlet.tags.BindErrorsTag;

/**
 * @Description: 指标任务管理
 * @Author: jeecg-boot
 * @Date:   2021-09-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags="指标任务管理")
@RestController
@RequestMapping("/zbrwgl/zbrwgl")
public class ZbrwglController extends JeecgController<Zbrwgl, IZbrwglService> {
	@Autowired
	private IZbrwglService zbrwglService;
	@Autowired
	private ZbrwglImportVerify zbrwglImportVerify;
	@Autowired
	private IHrBasOrganizationService hrBasOrganizationService;
	@Autowired
	private IPdzbszService pdzbszService;
	@Autowired
	private IKhjljcsjszService khjljcsjszService;
	@Autowired
	private IPdzbkService pdzbkService;

	/**
	 * 分页列表查询
	 *
	 * @param zbrwgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "指标任务管理-分页列表查询")
	@ApiOperation(value="指标任务管理-分页列表查询", notes="指标任务管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zbrwgl zbrwgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zbrwgl> queryWrapper = QueryGenerator.initQueryWrapper(zbrwgl, req.getParameterMap());
		Page<Zbrwgl> page = new Page<Zbrwgl>(pageNo, pageSize);
		IPage<Zbrwgl> pageList = zbrwglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 获取业务机构性质
	  * @param zbrwgl
	  * @param req
	  * @return
	  */
	@GetMapping(value = "/zzxzmc")
	public Result<?> queryZzxzmc(Zbrwgl zbrwgl,HttpServletRequest req) {
		QueryWrapper<HrBasOrganization> queryWrapper = new QueryWrapper<HrBasOrganization>();
		queryWrapper.eq("zzbz",zbrwgl.getZzbz());
		HrBasOrganization organization = hrBasOrganizationService.getOne(queryWrapper);
		String ywjgxz =null;
		if (organization != null) {
			if (StringUtils.isNotBlank(organization.getYwjgxz())){
				zbrwgl.setYwjgxz(Integer.valueOf(organization.getYwjgxz()));
			}
			if (StringUtils.isNotBlank(organization.getSzqy())){
				zbrwgl.setSzqy(Integer.valueOf(organization.getSzqy()));
			}
			if(StringUtils.isNotBlank(organization.getYwjgxz())){
				ywjgxz = organization.getYwjgxz();
			}
		}
		return Result.ok("查询成功",ywjgxz);
	}

	 /**
	  * 获取指标信息
	  * @param zbrwgl
	  * @param req
	  * @return
	  */
	@GetMapping(value = "/zbxx")
	public Result<?> queryZbxx(Zbrwgl zbrwgl,HttpServletRequest req) {
		QueryWrapper queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("pdzq",zbrwgl.getPdzq());
		queryWrapper.eq("pdrq",zbrwgl.getPdrq());
		queryWrapper.eq("zzbz",zbrwgl.getZzbz());
		queryWrapper.eq("gwbz",zbrwgl.getGwbz());
		List list = pdzbszService.list(queryWrapper);
		return Result.ok(list);
	}

	/**
	 * 添加
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "指标任务管理-添加")
	@ApiOperation(value="指标任务管理-添加", notes="指标任务管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody JSONObject jsonObject) {
		Zbrwgl zbrwgl = new Zbrwgl();

		JSONArray jsonArray = jsonObject.getJSONArray("zbidList");
		String pdzq = jsonObject.getString("pdzq");
		Date pdrq = DateUtil.parse(jsonObject.getString("pdrq"));
		String zzbz = jsonObject.getString("zzbz");
		String gwbz = jsonObject.getString("gwbz");
		String js = JSONObject.toJSONString(jsonArray);
		List<Zbrwgl> zbrwgls = JSONObject.parseArray(js, Zbrwgl.class);

		QueryWrapper<Khjljcsjsz> queryWrapper = new QueryWrapper<Khjljcsjsz>();
		queryWrapper.eq("pdzq",pdzq);
		queryWrapper.eq("pdrq",pdrq);
		queryWrapper.eq("zzbz",zzbz);
		queryWrapper.eq("gwbz",gwbz);
		List<Khjljcsjsz> custStaff = khjljcsjszService.list(queryWrapper);
		List<String> zbid = new ArrayList<>();

		QueryWrapper<HrBasOrganization> hr_bas_org = new QueryWrapper<HrBasOrganization>();
		hr_bas_org.eq("zzbz",zzbz);
		HrBasOrganization organization = hrBasOrganizationService.getOne(hr_bas_org);


		if (CollUtil.isNotEmpty(custStaff)){
			for (Khjljcsjsz rypdsz : custStaff) {
				QueryWrapper<Zbrwgl> queryWrapper2 = new QueryWrapper<>();
				queryWrapper2.eq("pdzq",pdzq);
				queryWrapper2.eq("pdrq",pdrq);
				queryWrapper2.eq("zzbz",zzbz);
				queryWrapper2.eq("gwbz",gwbz);
				queryWrapper2.eq("yggh",rypdsz.getYggh());
				List<Zbrwgl> list = zbrwglService.list(queryWrapper2);
				if (CollUtil.isNotEmpty(list)){
					zbrwglService.remove(queryWrapper2);
				}
					for (Zbrwgl zbrwgl1 : zbrwgls) { //遍历指标
						zbid.add(zbrwgl1.getZbid());
						QueryWrapper<Pdzbk> queryWrapper1 = new QueryWrapper<>();
						queryWrapper1.in("zbid",zbid);
						List<Pdzbk> zbmc =pdzbkService.list(queryWrapper1);
						if (CollUtil.isEmpty(zbmc)){
							return Result.error("指标错误！");
						}

						if (organization != null) {
							if (StringUtils.isNotBlank(organization.getYwjgxz())){
								zbrwgl.setYwjgxz(Integer.valueOf(organization.getYwjgxz()));
							}
							if (StringUtils.isNotBlank(organization.getSzqy())){
								zbrwgl.setSzqy(Integer.valueOf(organization.getSzqy()));
							}
						}

						if (zbrwgl1.getZbrw() == null){
							zbrwgl.setZbrw(new BigDecimal(0));
						}else {
							zbrwgl.setZbrw(zbrwgl1.getZbrw());
						}
						zbrwgl.setZzbz(zzbz);
						zbrwgl.setGwbz(Integer.valueOf(gwbz));
						zbrwgl.setYggh(rypdsz.getYggh());
						zbrwgl.setPdzq(pdzq);
						zbrwgl.setPdrq(pdrq);
						zbrwgl.setZbid(zbrwgl1.getZbid());
						zbrwgl.setYgxm(rypdsz.getYgxm());
						zbrwgl.setKhjlbz(rypdsz.getKhjlbz());
						zbrwgl.setZbmc(zbrwgl1.getZbmc());
						zbrwgl.setLrbz(1);
						zbrwgl.setLrr(getUsername());
						zbrwgl.setLrsj(new Date());
						zbrwglService.save(zbrwgl);
					}
			}
		}else {
			return Result.error("未存在等级评定员工信息！");
		}
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zbrwgl
	 * @return
	 */
	@AutoLog(value = "指标任务管理-编辑")
	@ApiOperation(value="指标任务管理-编辑", notes="指标任务管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zbrwgl zbrwgl) {
		QueryWrapper<Zbrwgl> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("pdzq",zbrwgl.getPdzq());
		queryWrapper.eq("pdrq",zbrwgl.getPdrq());
		queryWrapper.eq("zzbz",zbrwgl.getZzbz());
		queryWrapper.eq("gwbz",zbrwgl.getGwbz());
		queryWrapper.eq("yggh",zbrwgl.getYggh());
		queryWrapper.eq("zbid",zbrwgl.getZbid());
		zbrwgl.setXgr(getUsername());
		zbrwgl.setXgsj(new Date());
		zbrwgl.setLrbz(2);
		zbrwglService.update(zbrwgl,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "指标任务管理-通过id删除")
	@ApiOperation(value="指标任务管理-通过id删除", notes="指标任务管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("pdzq")String pdzq,@Param("pdrq")String pdrq,@Param("zzbz")String zzbz,
							@Param("gwbz")String gwbz,@Param("yggh")String yggh, @Param("zbid")String zbid) {
		QueryWrapper<Zbrwgl> queryWrapper = new QueryWrapper<Zbrwgl>();
		queryWrapper.eq("pdzq",pdzq);
		queryWrapper.eq("pdrq", DateUtil.parse(pdrq));
		queryWrapper.eq("zzbz",zzbz);
		queryWrapper.eq("gwbz",gwbz);
		queryWrapper.eq("yggh",yggh);
		queryWrapper.eq("zbid",zbid);
		zbrwglService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "指标任务管理-批量删除")
	@ApiOperation(value="指标任务管理-批量删除", notes="指标任务管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zbrwglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "指标任务管理-通过id查询")
	@ApiOperation(value="指标任务管理-通过id查询", notes="指标任务管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zbrwgl zbrwgl = zbrwglService.getById(id);
		return Result.ok(zbrwgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zbrwgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zbrwgl zbrwgl) {
      return super.exportXls(request, zbrwgl, Zbrwgl.class, "指标任务管理");
  }


	 /**
	  * 导入模板
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "指标任务管理导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, ZbrwglVo.class);
		 ExportParams exportParams = new ExportParams("指标任务管理导入模板", "模板信息");
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
		 return super.importExcelByTemplate(jsonObject, request, response, Zbrwgl.class,ZbrwglVo.class,zbrwglImportVerify);
	 }
}

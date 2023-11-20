package org.cmms.modules.xdgl.grdkgl.controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.aspect.annotation.PermissionData;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.cmms.modules.khgl.grkhgl.entity.vKhglKrkhgl;
import org.cmms.modules.khgl.grkhgl.service.IvKhglKrkhglService;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.khgl.nh.service.IYwhywwlxxService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.xdgl.grdkgl.entity.*;
import org.cmms.modules.xdgl.grdkgl.service.*;
import org.cmms.modules.xdgl.grdkgl.service.impl.GrdkJtspServiceImpl;
import org.cmms.modules.xdgl.grdkgl.service.impl.GrxdzllbServiceImpl;
import org.cmms.modules.xdgl.grdkgl.vo.GrxdInfoPage;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.modules.xdgl.grdkgl.vo.GrdkglPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

 /**
 * @Description: 个人贷款
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@RestController
@RequestMapping("/xdgl/grdkgl")
@Slf4j
public class GrdkglController  extends JeecgController<Grdkgl, IGrdkglService>  {
	@Autowired
	private IGrdkglService grdkglService;
	@Autowired
	private IJtcyxxService jtcyxxService;
	@Autowired
	private IGlqyService glqyService;
	@Autowired
	private IFwxxService fwxxService;
	@Autowired
	private ICfxxService cfxxService;
	@Autowired
	private IClxxService clxxService;
	@Autowired
	private IQtglzcService qtglzcService;
	@Autowired
	private IYhdkService yhdkService;
	@Autowired
	private IBzdbService bzdbService;
	@Autowired
	private IDydbService dydbService;
	@Autowired
	private IZydbService zydbService;
	@Autowired
	private IXydbService xydbService;
	@Autowired
	private IGrdkcjxxService grdkcjxxService;
	@Autowired
	private ISysDictService sysDictService;
	@Autowired
	private GrxdzllbServiceImpl grxdzllbService;
	@Autowired
	private GrdkJtspServiceImpl grdkJtspServiceImpl;
	@Autowired
	private IYwhywwlxxService ywhywwlxxService;
	@Autowired
	private IGrdkBzxxService bzxxService;
	@Autowired
	private IGrdkDbxxService dbxxService;
	@Autowired
	private IKhglKhhmcxxGrxdService iKhglKhhmcxxGrxdService;

	/**
	 * 分页列表查询
	 * @param grdkgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	 @GetMapping(value = "/list")
	 @PermissionData(pageComponent = "xdgl/grdkgl/GrdkglList")
	 public Result<?> queryPageList(Grdkgl grdkgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Grdkgl> queryWrapper = QueryGenerator.initQueryWrapper(grdkgl, req.getParameterMap());
		queryWrapper.orderByDesc("create_time");
		Page<Grdkgl> page = new Page<Grdkgl>(pageNo, pageSize);
		IPage<Grdkgl> pageList = grdkglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 * @param grdkglPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody GrdkglPage grdkglPage) {
		//Grdkgl grdkgl = new Grdkgl();
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String yggh=sysUser.getWorkNo();
		Grdkcjxx cjxx=new Grdkcjxx();
		Jtcyxx  hmcxx=new Jtcyxx();
		//BeanUtils.copyProperties(grdkglPage, grdkgl);
		BeanUtils.copyProperties(grdkglPage, cjxx);
		BeanUtils.copyProperties(grdkglPage, hmcxx);
		hmcxx.setHhbm(sysDictService.queryhhbm("SEQ_HHBM_LY.nextval"));
		hmcxx.setLrr(yggh);
		cjxx.setSskhjl(yggh);
		grxdzllbService.savefjxxMain(grdkglPage.getImgdate(),"jkrsfzfileList",hmcxx,"1");
		grxdzllbService.savefjxxMain(grdkglPage.getImgdate(),"jkrhyztfileList",hmcxx,"2");
		grxdzllbService.savefjxxMain(grdkglPage.getImgdate(),"jyqyszfileList",hmcxx,"3");
		grxdzllbService.savefjxxMain(grdkglPage.getImgdate(),"qyzzzsfileList",hmcxx,"4");
		grxdzllbService.savefjxxMain(grdkglPage.getImgdate(),"tzhyscfileList",hmcxx,"5");
		grxdzllbService.savefjxxMain(grdkglPage.getImgdate(),"khxkfileList",hmcxx,"6");
		grxdzllbService.savefjxxMain(grdkglPage.getImgdate(),"gszcfileList",hmcxx,"7");
		grxdzllbService.savefjxxMain(grdkglPage.getImgdate(),"jsnbbfileList",hmcxx,"8");
		grxdzllbService.savefjxxMain(grdkglPage.getImgdate(),"gwhyfileList",hmcxx,"9");
		grxdzllbService.savefjxxMain(grdkglPage.getImgdate(),"dkytfileList",hmcxx,"10");
		grxdzllbService.savefjxxMain(grdkglPage.getImgdate(),"dywpgfileList",hmcxx,"11");
		grxdzllbService.savefjxxMain(grdkglPage.getImgdate(),"dyqzfileList",hmcxx,"12");
		grxdzllbService.savefjxxMain(grdkglPage.getImgdate(),"dyrsfzfileList",hmcxx,"13");
		grxdzllbService.savefjxxMain(grdkglPage.getImgdate(),"dyrgdhfileList",hmcxx,"14");
		grxdzllbService.savefjxxMain(grdkglPage.getImgdate(),"wczfileList",hmcxx,"15");
		grxdzllbService.savefjxxMain(grdkglPage.getImgdate(),"crhfileList",hmcxx,"16");
		grxdzllbService.savefjxxMain(grdkglPage.getImgdate(),"bzrjbzzfileList",hmcxx,"17");
		grxdzllbService.savefjxxMain(grdkglPage.getImgdate(),"bzrgdhfileList",hmcxx,"18");
		grdkglService.saveMain(cjxx, hmcxx,grdkglPage.getJtcyxxList(),grdkglPage.getGlqyList(),grdkglPage.getFwxxList(),grdkglPage.getCfxxList(),grdkglPage.getClxxList(),grdkglPage.getQtglzcList(),grdkglPage.getYhdkList(),grdkglPage.getBzdbList(),grdkglPage.getDydbList(),grdkglPage.getZydbList(),grdkglPage.getXydbList(),grdkglPage.getImgdate());
		return Result.ok("添加成功!");
	}

	 /**
	  * 编辑
	  * @param grdkglPage
	  * @return
	  */
	 @PutMapping(value = "/edit")
	 public Result<?> edit(@RequestBody GrdkglPage grdkglPage) {
		 Grdkgl grdkgl = new Grdkgl();
		 BeanUtils.copyProperties(grdkglPage, grdkgl);
		 Grdkcjxx cjxx=new Grdkcjxx();
		 Jtcyxx  hmcxx=new Jtcyxx();
		 //BeanUtils.copyProperties(grdkglPage, grdkgl);
		 BeanUtils.copyProperties(grdkglPage, cjxx);
		 BeanUtils.copyProperties(grdkglPage, hmcxx);
		 grxdzllbService.updatefjxxMain(grdkglPage.getImgdate(),"jkrsfzfileList",hmcxx,"1");
		 grxdzllbService.updatefjxxMain(grdkglPage.getImgdate(),"jkrhyztfileList",hmcxx,"2");
		 grxdzllbService.updatefjxxMain(grdkglPage.getImgdate(),"jyqyszfileList",hmcxx,"3");
		 grxdzllbService.updatefjxxMain(grdkglPage.getImgdate(),"qyzzzsfileList",hmcxx,"4");
		 grxdzllbService.updatefjxxMain(grdkglPage.getImgdate(),"tzhyscfileList",hmcxx,"5");
		 grxdzllbService.updatefjxxMain(grdkglPage.getImgdate(),"khxkfileList",hmcxx,"6");
		 grxdzllbService.updatefjxxMain(grdkglPage.getImgdate(),"gszcfileList",hmcxx,"7");
		 grxdzllbService.updatefjxxMain(grdkglPage.getImgdate(),"jsnbbfileList",hmcxx,"8");
		 grxdzllbService.updatefjxxMain(grdkglPage.getImgdate(),"gwhyfileList",hmcxx,"9");
		 grxdzllbService.updatefjxxMain(grdkglPage.getImgdate(),"dkytfileList",hmcxx,"10");
		 grxdzllbService.updatefjxxMain(grdkglPage.getImgdate(),"dywpgfileList",hmcxx,"11");
		 grxdzllbService.updatefjxxMain(grdkglPage.getImgdate(),"dyqzfileList",hmcxx,"12");
		 grxdzllbService.updatefjxxMain(grdkglPage.getImgdate(),"dyrsfzfileList",hmcxx,"13");
		 grxdzllbService.updatefjxxMain(grdkglPage.getImgdate(),"dyrgdhfileList",hmcxx,"14");
		 grxdzllbService.updatefjxxMain(grdkglPage.getImgdate(),"wczfileList",hmcxx,"15");
		 grxdzllbService.updatefjxxMain(grdkglPage.getImgdate(),"crhfileList",hmcxx,"16");
		 grxdzllbService.updatefjxxMain(grdkglPage.getImgdate(),"bzrjbzzfileList",hmcxx,"17");
		 grxdzllbService.updatefjxxMain(grdkglPage.getImgdate(),"bzrgdhfileList",hmcxx,"18");
		 grdkglService.updateMain(cjxx,hmcxx,grdkglPage.getJtcyxxList(),grdkglPage.getGlqyList(),grdkglPage.getFwxxList(),grdkglPage.getCfxxList(),grdkglPage.getClxxList(),grdkglPage.getQtglzcList(),grdkglPage.getYhdkList(),grdkglPage.getBzdbList(),grdkglPage.getDydbList(),grdkglPage.getZydbList(),grdkglPage.getXydbList(),grdkglPage.getImgdate());
		 return Result.ok("保存成功!");
	 }

	 /**
	  * 农户采集信息查看
	  * @param jsonObject
	  * @return
	  */
	 @RequestMapping(value = "/grdkcj", method = RequestMethod.PUT)
	 public Result<Grdkcjxx> nhcjxx(@RequestBody JSONObject jsonObject ) {
		 Result<Grdkcjxx> result = new Result<Grdkcjxx>();
		 try {
			 QueryWrapper<Grdkcjxx> queryWrapper = new QueryWrapper();
			 queryWrapper.eq("zjhm",jsonObject.getString("zjhm"));
			 List<Grdkcjxx> grdkcjxxList = grdkcjxxService.list(queryWrapper);
			 if(grdkcjxxList!=null&&grdkcjxxList.size()!=0) {
				 result.setResult(grdkcjxxList.get(0));
				 result.setMessage("该客户已存在采集信息，请勿重复操作");
			 }else{
				 QueryWrapper jtcyQuery= new QueryWrapper();
				 jtcyQuery.eq("zjhm",jsonObject.getString("zjhm"));
				 Jtcyxx hmcxx=jtcyxxService.getOne(jtcyQuery);
				 if(hmcxx.getHhbm()!=null&&hmcxx.getHhbm()!=""){
					 QueryWrapper<Grdkcjxx> queryWrapper1 = new QueryWrapper();
					 queryWrapper1.eq("hhbm",hmcxx.getHhbm());
					 queryWrapper1.eq("sfsxdx","1");
					 List<Grdkcjxx> grdkcjxxList1=  grdkcjxxService.list(queryWrapper1);
					 if(grdkcjxxList1!=null&&grdkcjxxList1.size()!=0) {
						 result.setResult(grdkcjxxList1.get(0));
						 result.setMessage("该户下已存在授信对象["+grdkcjxxList1.get(0).getKhmc()+"]");
					 }
				 }

			 }

		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
			 result.error500("操作失败");
		 }
		 return	result;
	 }

	 /**
	  * 农户采集信息查看
	  * @param jsonObject
	  * @return
	  */
	 @RequestMapping(value = "/qrsp", method = RequestMethod.PUT)
	 public Result<GrdkJtsp> qrsp(@RequestBody JSONObject jsonObject ) {
		 Result<GrdkJtsp> result = new Result<GrdkJtsp>();
		 try {
			 QueryWrapper<GrdkJtsp> queryWrapper = new QueryWrapper();
			 queryWrapper.eq("id",jsonObject.getString("id"));
			 GrdkJtsp  grdkcjxx =grdkJtspServiceImpl.getOne(queryWrapper);
			 if(grdkcjxx==null){
				 result.error500("暂无集体审批记录，确认失败！");
			 }else{
				 grdkcjxx.setJtspzzzt("1");
				 grdkJtspServiceImpl.updateById(grdkcjxx);
				 result.success("操作成功！");
			 }
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
			 result.error500("操作失败");
		 }
		 return	result;
	 }

	 /**
	  * 通过id删除
	  * @param id
	  * @return
	  */
	 @DeleteMapping(value = "/delete")
	 public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		 grdkglService.delMain(id);
		 return Result.ok("删除成功!");
	 }

	 /**
	  * 批量删除
	  * @param ids
	  * @return
	  */
	 @DeleteMapping(value = "/deleteBatch")
	 public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		 this.grdkglService.delBatchMain(Arrays.asList(ids.split(",")));
		 return Result.ok("批量删除成功！");
	 }

	 /**
	  * 通过id查询
	  * @param id
	  * @return
	  */
	 @GetMapping(value = "/queryById")
	 public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		 Grdkgl grdkgl = grdkglService.getById(id);
		 return Result.ok(grdkgl);
	 }

	 /**
	  * 通过id查询
	  * @param
	  * @return
	  */
	 @GetMapping(value = "/queryJtcyxxByMainId")
	 public Result<?> queryJtcyxxListByMainId(@RequestParam(name="hhbm",required=true) String hhbm ,@RequestParam(name="zjhm",required=true) String zjhm) {
		 List<Jtcyxx> jtcyxxList = jtcyxxService.selectByMainId(hhbm,zjhm);
		 return Result.ok(jtcyxxList);
	 }

	 /**
	  *
	  * @param hhbm
	  * @return
	  */
	 @GetMapping(value = "/queryJtcyxxByMainHhbm")
	 public Result<?> queryJtcyxxListByMainId(@RequestParam(name="hhbm",required=true) String hhbm) {
		 Jtcyxx jtcyxxList = jtcyxxService.selectPeiOuByHhbm(hhbm);
		 return Result.ok(jtcyxxList);
	 }

	 /**
	  * 通过id查询
	  * @param id
	  * @return
	  */
	 @GetMapping(value = "/queryGlqyByMainId")
	 public Result<?> queryGlqyListByMainId(@RequestParam(name="id",required=true) String id) {
		 List<Glqy> glqyList = glqyService.selectByMainId(id);
		 return Result.ok(glqyList);
	 }

	 /**
	  * 通过id查询
	  * @param id
	  * @return
	  */
	 @GetMapping(value = "/queryFwxxByMainId")
	 public Result<?> queryFwxxListByMainId(@RequestParam(name="id",required=true) String id) {
		 List<Fwxx> fwxxList = fwxxService.selectByMainId(id);
		 return Result.ok(fwxxList);
	 }

	 /**
	  * 通过id查询
	  * @param id
	  * @return
	  */
	 @GetMapping(value = "/queryCfxxByMainId")
	 public Result<?> queryCfxxListByMainId(@RequestParam(name="id",required=true) String id) {
		 List<Cfxx> cfxxList = cfxxService.selectByMainId(id);
		 return Result.ok(cfxxList);
	 }

	 /**
	  * 通过id查询
	  * @param id
	  * @return
	  */
	 @GetMapping(value = "/queryClxxByMainId")
	 public Result<?> queryClxxListByMainId(@RequestParam(name="id",required=true) String id) {
		 List<Clxx> clxxList = clxxService.selectByMainId(id);
		 return Result.ok(clxxList);
	 }

	 /**
	  * 通过id查询
	  * @param id
	  * @return
	  */
	 @GetMapping(value = "/queryQtglzcByMainId")
	 public Result<?> queryQtglzcListByMainId(@RequestParam(name="id",required=true) String id) {
		 List<Qtglzc> qtglzcList = qtglzcService.selectByMainId(id);
		 return Result.ok(qtglzcList);
	 }

	 /**
	  * 通过id查询
	  * @param id
	  * @return
	  */
	 @GetMapping(value = "/queryYhdkByMainId")
	 public Result<?> queryYhdkListByMainId(@RequestParam(name="id",required=true) String id) {
		 List<Yhdk> yhdkList = yhdkService.selectByMainId(id);
		 return Result.ok(yhdkList);
	 }

	 /**
	  * 通过id查询
	  * @param id
	  * @return
	  */
	 @GetMapping(value = "/queryBzdbByMainId")
	 public Result<?> queryBzdbListByMainId(@RequestParam(name="id",required=true) String id) {
		 List<Bzdb> bzdbList = bzdbService.selectByMainId(id);
		 return Result.ok(bzdbList);
	 }

	 /**
	  * 通过id查询
	  * @param id
	  * @return
	  */
	 @GetMapping(value = "/queryDydbByMainId")
	 public Result<?> queryDydbListByMainId(@RequestParam(name="id",required=true) String id) {
		 List<Dydb> dydbList = dydbService.selectByMainId(id);
		 return Result.ok(dydbList);
	 }

	 /**
	  * 通过id查询
	  * @param id
	  * @return
	  */
	 @GetMapping(value = "/queryZydbByMainId")
	 public Result<?> queryZydbListByMainId(@RequestParam(name="id",required=true) String id) {
		 List<Zydb> zydbList = zydbService.selectByMainId(id);
		 return Result.ok(zydbList);
	 }

	 /**
	  * 通过id查询
	  * @param id
	  * @return
	  */
	 @GetMapping(value = "/queryXydbByMainId")
	 public Result<?> queryXydbListByMainId(@RequestParam(name="id",required=true) String id) {
		 List<Xydb> xydbList = xydbService.selectByMainId(id);
		 return Result.ok(xydbList);
	 }

	 /**
	  * 通过id查询
	  * @param hhbm
	  * @return
	  */
	 @GetMapping(value = "/queryYwhywxxByHhbm")
	 public Result<?> queryYwhywxxByHhbm(@RequestParam(name="hhbm",required=true) String hhbm) {
		 List<Ywhywwlxx> ywhywwlxxList = ywhywwlxxService.selectByHhbm(hhbm);
		 return Result.ok(ywhywwlxxList);
	 }

	 /**
	  * 通过id查询
	  * @param id
	  * @return
	  */
	 @GetMapping(value = "/queryBzxxByMainId")
	 public Result<?> queryBzxxByMainId(@RequestParam(name="id",required=true) String id) {
		 List<GrdkBzxx> bzxxList = bzxxService.selectByMainId(id);
		 return Result.ok(bzxxList);
	 }

	 /**
	  * 通过id查询
	  * @param id
	  * @return
	  */
	 @GetMapping(value = "/queryDbxxByMainId")
	 public Result<?> queryDbxxByMainId(@RequestParam(name="id",required=true) String id) {
		 List<GrdkDbxx> dbxxList = dbxxService.selectByMainId(id);
		 return Result.ok(dbxxList);
	 }

	 /**
	  * 导出excel
	  * @param request
	  * @param
	  */
	 @RequestMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, Grdkgl grdkgl) {
		 return super.exportXls(request, grdkgl, Grdkgl.class, "个人贷款采集信息");
	 }

	 /**
	  * 导出excel
	  * @param request
	  * @param grdkgl
	  */
	 /*@RequestMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, Grdkgl grdkgl) {
		 // Step.1 组装查询条件
		 QueryWrapper<Grdkgl> queryWrapper = QueryGenerator.initQueryWrapper(grdkgl, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 //Step.2 获取导出数据
		 List<GrdkglPage> pageList = new ArrayList<GrdkglPage>();
		 List<Grdkgl> grdkglList = grdkglService.list(queryWrapper);
		 for (Grdkgl temp : grdkglList) {
			 GrdkglPage vo = new GrdkglPage();
			 BeanUtils.copyProperties(temp, vo);
			 List<Jtcyxx> jtcyxxList = jtcyxxService.selectByMainId(temp.getHhbm(),temp.getZjhm());
			 vo.setJtcyxxList(jtcyxxList);
			 List<Glqy> glqyList = glqyService.selectByMainId(temp.getZjhm());
			 vo.setGlqyList(glqyList);
			 List<Fwxx> fwxxList = fwxxService.selectByMainId(temp.getZjhm());
			 vo.setFwxxList(fwxxList);
			 List<Cfxx> cfxxList = cfxxService.selectByMainId(temp.getZjhm());
			 vo.setCfxxList(cfxxList);
			 List<Clxx> clxxList = clxxService.selectByMainId(temp.getZjhm());
			 vo.setClxxList(clxxList);
			 List<Qtglzc> qtglzcList = qtglzcService.selectByMainId(temp.getZjhm());
			 vo.setQtglzcList(qtglzcList);
			 List<Yhdk> yhdkList = yhdkService.selectByMainId(temp.getZjhm());
			 vo.setYhdkList(yhdkList);
			 List<Bzdb> bzdbList = bzdbService.selectByMainId(temp.getZjhm());
			 vo.setBzdbList(bzdbList);
			 List<Dydb> dydbList = dydbService.selectByMainId(temp.getZjhm());
			 vo.setDydbList(dydbList);
			 List<Zydb> zydbList = zydbService.selectByMainId(temp.getZjhm());
			 vo.setZydbList(zydbList);
			 List<Xydb> xydbList = xydbService.selectByMainId(temp.getZjhm());
			 vo.setXydbList(xydbList);
			 pageList.add(vo);
		 }
		 //Step.3 调用AutoPoi导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, "个人贷款");
		 mv.addObject(NormalExcelConstants.CLASS, GrdkglPage.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("个人贷款数据", "导出人:"+sysUser.getRealname(), "个人贷款"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 return mv;
	 }*/

	 /**
	  * 通过excel导入数据
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		 Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		 for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			 MultipartFile file = entity.getValue();// 获取上传文件对象
			 ImportParams params = new ImportParams();
			 params.setTitleRows(2);
			 params.setHeadRows(1);
			 params.setNeedSave(true);
			 try {
				 List<GrdkglPage> list = ExcelImportUtil.importExcel(file.getInputStream(), GrdkglPage.class, params);
				 for (GrdkglPage page : list) {
					 Grdkcjxx cjxx=new Grdkcjxx();
					 Jtcyxx  hmcxx=new Jtcyxx();
					 //BeanUtils.copyProperties(grdkglPage, grdkgl);
					 BeanUtils.copyProperties(page, cjxx);
					 BeanUtils.copyProperties(page, hmcxx);
					 grdkglService.saveMain(cjxx,hmcxx,page.getJtcyxxList(),page.getGlqyList(),page.getFwxxList(),page.getCfxxList(),page.getClxxList(),page.getQtglzcList(),page.getYhdkList(),page.getBzdbList(),page.getDydbList(),page.getZydbList(),page.getXydbList(),page.getImgdate());
				 }
				 return Result.ok("文件导入成功！数据行数:" + list.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(),e);
				 return Result.error("文件导入失败:"+e.getMessage());
			 } finally {
				 try {
					 file.getInputStream().close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		 return Result.ok("文件导入失败！");
	 }

	 /**
	  * 附件信息
	  * @param zjhm
	  * @return
	  */
	 @RequestMapping(value = "/img",method = RequestMethod.GET)
	 public Result<?> img(@RequestParam(name = "zjhm",required = true)String zjhm){
		 JSONObject json = new JSONObject();

		 try {
			 Map<String, String[]> map = new HashMap<>();
			 JSONArray jkrsfzfileList = new JSONArray();
			 JSONArray jkrhyztfileList = new JSONArray();
			 JSONArray jyqyszfileList = new JSONArray();
			 JSONArray qyzzzsfileList = new JSONArray();
			 JSONArray tzhyscfileList  = new JSONArray();
			 JSONArray khxkfileList = new JSONArray();
			 JSONArray gszcfileList = new JSONArray();
			 JSONArray jsnbbfileList  = new JSONArray();
			 JSONArray gwhyfileList = new JSONArray();
			 JSONArray dkytfileList  = new JSONArray();
			 JSONArray dywpgfileList  = new JSONArray();
			 JSONArray dyqzfileList  = new JSONArray();
			 JSONArray dyrsfzfileList  = new JSONArray();
			 JSONArray dyrgdhfileList  = new JSONArray();
			 JSONArray wczfileList  = new JSONArray();
			 JSONArray crhfileList  = new JSONArray();
			 JSONArray bzrjbzzfileList  = new JSONArray();
			 JSONArray bzrgdhfileList  = new JSONArray();
			 Grxdzllb grxdzllb = new Grxdzllb();
			 grxdzllb.setZjhm(zjhm);
			 QueryWrapper<Grxdzllb> queryWrapper = QueryGenerator.initQueryWrapper(grxdzllb, map);
			 List<Grxdzllb> fjglList = grxdzllbService.list(queryWrapper);
			 for (Grxdzllb fjgl1 : fjglList) {
				 if (fjgl1.getZllx() != null) {
					 JSONObject jsonObject = new JSONObject();
					 if (fjgl1.getZllx().equals("1")) {
						 jsonObject.put("uid", fjgl1.getZlbh());
						 jsonObject.put("name", fjgl1.getZlmc());
						 jsonObject.put("status", "done");
						 jsonObject.put("url", fjgl1.getFwlj());
						 jkrsfzfileList.add(jsonObject);
					 } else if (fjgl1.getZllx().equals("2")) {
						 jsonObject.put("uid", fjgl1.getZlbh());
						 jsonObject.put("name", fjgl1.getZlmc());
						 jsonObject.put("status", "done");
						 jsonObject.put("url", fjgl1.getFwlj());
						 jkrhyztfileList.add(jsonObject);
					 } else if (fjgl1.getZllx().equals("3")) {
						 jsonObject.put("uid", fjgl1.getZlbh());
						 jsonObject.put("name", fjgl1.getZlmc());
						 jsonObject.put("status", "done");
						 jsonObject.put("url", fjgl1.getFwlj());
						 jyqyszfileList.add(jsonObject);
					 } else if (fjgl1.getZllx().equals("4")) {
						 jsonObject.put("uid", fjgl1.getZlbh());
						 jsonObject.put("name", fjgl1.getZlmc());
						 jsonObject.put("status", "done");
						 jsonObject.put("url", fjgl1.getFwlj());
						 qyzzzsfileList.add(jsonObject);
					 } else if (fjgl1.getZllx().equals("5")) {
						 jsonObject.put("uid", fjgl1.getZlbh());
						 jsonObject.put("name", fjgl1.getZlmc());
						 jsonObject.put("status", "done");
						 jsonObject.put("url", fjgl1.getFwlj());
						 tzhyscfileList.add(jsonObject);
					 } else if (fjgl1.getZllx().equals("6")) {
						 jsonObject.put("uid", fjgl1.getZlbh());
						 jsonObject.put("name", fjgl1.getZlmc());
						 jsonObject.put("status", "done");
						 jsonObject.put("url", fjgl1.getFwlj());
						 khxkfileList.add(jsonObject);
					 } else if (fjgl1.getZllx().equals("7")) {
						 jsonObject.put("uid", fjgl1.getZlbh());
						 jsonObject.put("name", fjgl1.getZlmc());
						 jsonObject.put("status", "done");
						 jsonObject.put("url", fjgl1.getFwlj());
						 gszcfileList.add(jsonObject);
					 }else if (fjgl1.getZllx().equals("8")) {
						 jsonObject.put("uid", fjgl1.getZlbh());
						 jsonObject.put("name", fjgl1.getZlmc());
						 jsonObject.put("status", "done");
						 jsonObject.put("url", fjgl1.getFwlj());
						 jsnbbfileList.add(jsonObject);
					 } else if (fjgl1.getZllx().equals("9")) {
						 jsonObject.put("uid", fjgl1.getZlbh());
						 jsonObject.put("name", fjgl1.getZlmc());
						 jsonObject.put("status", "done");
						 jsonObject.put("url", fjgl1.getFwlj());
						 gwhyfileList.add(jsonObject);
					 } else if (fjgl1.getZllx().equals("10")) {
						 jsonObject.put("uid", fjgl1.getZlbh());
						 jsonObject.put("name", fjgl1.getZlmc());
						 jsonObject.put("status", "done");
						 jsonObject.put("url", fjgl1.getFwlj());
						 dkytfileList.add(jsonObject);
					 } else if (fjgl1.getZllx().equals("11")) {
						 jsonObject.put("uid", fjgl1.getZlbh());
						 jsonObject.put("name", fjgl1.getZlmc());
						 jsonObject.put("status", "done");
						 jsonObject.put("url", fjgl1.getFwlj());
						 dywpgfileList.add(jsonObject);
					 } else if (fjgl1.getZllx().equals("12")) {
						 jsonObject.put("uid", fjgl1.getZlbh());
						 jsonObject.put("name", fjgl1.getZlmc());
						 jsonObject.put("status", "done");
						 jsonObject.put("url", fjgl1.getFwlj());
						 dyqzfileList.add(jsonObject);
					 } else if (fjgl1.getZllx().equals("13")) {
						 jsonObject.put("uid", fjgl1.getZlbh());
						 jsonObject.put("name", fjgl1.getZlmc());
						 jsonObject.put("status", "done");
						 jsonObject.put("url", fjgl1.getFwlj());
						 dyrsfzfileList.add(jsonObject);
					 } else if (fjgl1.getZllx().equals("14")) {
						 jsonObject.put("uid", fjgl1.getZlbh());
						 jsonObject.put("name", fjgl1.getZlmc());
						 jsonObject.put("status", "done");
						 jsonObject.put("url", fjgl1.getFwlj());
						 dyrgdhfileList.add(jsonObject);
					 } else if (fjgl1.getZllx().equals("15")) {
						 jsonObject.put("uid", fjgl1.getZlbh());
						 jsonObject.put("name", fjgl1.getZlmc());
						 jsonObject.put("status", "done");
						 jsonObject.put("url", fjgl1.getFwlj());
						 wczfileList.add(jsonObject);
					 } else if (fjgl1.getZllx().equals("16")) {
						 jsonObject.put("uid", fjgl1.getZlbh());
						 jsonObject.put("name", fjgl1.getZlmc());
						 jsonObject.put("status", "done");
						 jsonObject.put("url", fjgl1.getFwlj());
						 crhfileList.add(jsonObject);
					 } else if (fjgl1.getZllx().equals("17")) {
						 jsonObject.put("uid", fjgl1.getZlbh());
						 jsonObject.put("name", fjgl1.getZlmc());
						 jsonObject.put("status", "done");
						 jsonObject.put("url", fjgl1.getFwlj());
						 bzrjbzzfileList.add(jsonObject);
					 } else if (fjgl1.getZllx().equals("18")) {
						 jsonObject.put("uid", fjgl1.getZlbh());
						 jsonObject.put("name", fjgl1.getZlmc());
						 jsonObject.put("status", "done");
						 jsonObject.put("url", fjgl1.getFwlj());
						 bzrgdhfileList.add(jsonObject);
					 }
				 }
			 }
			 json.put("jkrsfzfileList",jkrsfzfileList);
			 json.put("jkrhyztfileList",jkrhyztfileList);
			 json.put("jyqyszfileList",jyqyszfileList);
			 json.put("qyzzzsfileList",qyzzzsfileList);
			 json.put("tzhyscfileList",tzhyscfileList);
			 json.put("khxkfileList",khxkfileList);
			 json.put("gszcfileList",gszcfileList);
			 json.put("jsnbbfileList",jsnbbfileList);
			 json.put("gwhyfileList",gwhyfileList);
			 json.put("dkytfileList",dkytfileList);
			 json.put("dywpgfileList",dywpgfileList);
			 json.put("dyqzfileList",dyqzfileList);
			 json.put("dyrsfzfileList",dyrsfzfileList);
			 json.put("dyrgdhfileList",dyrgdhfileList);
			 json.put("wczfileList",wczfileList);
			 json.put("crhfileList",crhfileList);
			 json.put("bzrjbzzfileList",bzrjbzzfileList);
			 json.put("bzrgdhfileList",bzrgdhfileList);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 return Result.ok(json);
	 }

}

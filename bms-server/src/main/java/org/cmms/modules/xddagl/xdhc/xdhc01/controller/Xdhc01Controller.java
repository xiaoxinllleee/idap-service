package org.cmms.modules.xddagl.xdhc.xdhc01.controller;

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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.system.entity.SysUserRole;
import org.cmms.modules.system.service.ISysUserRoleService;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjglbc.entity.DkdahtsjglbcVo;
import org.cmms.modules.xddagl.dkdaglfjxx.entity.Xdhcfjxx;
import org.cmms.modules.xddagl.dkdaglfjxx.service.IXdhcfjxxService;
import org.cmms.modules.xddagl.xdhc.xdhc01.entity.Xdhc01;
import org.cmms.modules.xddagl.xdhc.xdhc01.entity.Xdhc01Fjsc;
import org.cmms.modules.xddagl.xdhc.xdhc01.entity.Xdhc01Vo;
import org.cmms.modules.xddagl.xdhc.xdhc01.service.IXdhc01Service;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.xddagl.xdhc.xdhc01.verify.Xdhc01Verify;
import org.cmms.modules.xddagl.xtgl.xddaglcsgl.service.IXddaglcsglService;
import org.cmms.modules.xddaglxt.dksjgl.dksjjktz.entity.Dksjjktz;
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
 * @Description: 信贷T+1核查
 * @Author: jeecg-boot
 * @Date:   2022-01-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="信贷T+1核查")
@RestController
@RequestMapping("/xdhc01/xdhc01")
public class Xdhc01Controller extends JeecgController<Xdhc01, IXdhc01Service> {
	@Autowired
	private IXdhc01Service xdhc01Service;
	@Autowired
	private Xdhc01Verify xdhc01Verify;
	 @Autowired
	 IDictValueQuery iDictValueQuery;
	 @Autowired
	 private IXddaglcsglService iXddaglcsglService;
	 @Autowired
	 private IXdhcfjxxService xdhcfjxxService;
	 @Autowired
	 ISysUserRoleService sysUserRoleService;
	/**
	 * 分页列表查询
	 *
	 * @param xdhc01
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "信贷T+1核查-分页列表查询")
	@ApiOperation(value="信贷T+1核查-分页列表查询", notes="信贷T+1核查-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Xdhc01 xdhc01,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper wrapper=new QueryWrapper();
		wrapper.eq("csbm","00001");
		String jkrq = iXddaglcsglService.getOne(wrapper).getCsz();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date = sdf.parse(jkrq);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		QueryWrapper<Xdhc01> queryWrapper = QueryGenerator.initQueryWrapper(xdhc01, req.getParameterMap());
		queryWrapper.gt("jkrq",date);
		queryWrapper.ne("xddkpz","13181000000000");
		queryWrapper.ne("xddkpz","13181200000000");
		queryWrapper.eq("shzt","1");
		Page<Xdhc01> page = new Page<Xdhc01>(pageNo, pageSize);
		IPage<Xdhc01> pageList = xdhc01Service.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param xdhc01
	 * @return
	 */
	@AutoLog(value = "信贷T+1核查-添加")
	@ApiOperation(value="信贷T+1核查-添加", notes="信贷T+1核查-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Xdhc01 xdhc01) {
		xdhc01Service.save(xdhc01);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "信贷T+1核查-编辑")
	@ApiOperation(value="信贷T+1核查-编辑", notes="信贷T+1核查-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Xdhc01Fjsc xdhc01Fjsc) {
		Xdhc01 xdhc01 = new Xdhc01();
		BeanUtils.copyProperties(xdhc01Fjsc,xdhc01);
		JSONArray fjxxs = xdhc01Fjsc.getImgdate();
		Integer lx = xdhc01Fjsc.getLx();
		Xdhcfjxx wjxx = new Xdhcfjxx();
		if (fjxxs != null && fjxxs.size()>0){
			for (int i = 0; i < fjxxs.size(); i++) {
				String fjname = (String) fjxxs.getJSONObject(i).get("name");
				String fjlx = fjname.split("_")[0];
				String wllj = uploadpath + "/" + fjxxs.getJSONObject(i).getJSONObject("response").getString("message");
				String fwlj =   "/" + fjxxs.getJSONObject(i).getJSONObject("response").getString("message");
				if (fjxxs != null){
				wjxx.setWjid(Long.parseLong(iDictValueQuery.getSeqRateZxlldjbDjidNextval("SEQ_PUBLIC_ID.nextval")));
				wjxx.setDkzh(xdhc01.getDkzh());
				wjxx.setWjlj(wllj);
				wjxx.setFwlj(fwlj);
				wjxx.setFjlx(lx);
				wjxx.setLrbz(1);
				wjxx.setLrr(getUsername());
				wjxx.setLrsj(new Date());
				}
			}
			xdhcfjxxService.save(wjxx);
		}
		//判断是不是管理员
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		LambdaQueryWrapper<SysUserRole> sysRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
		sysRoleLambdaQueryWrapper.eq(SysUserRole::getUserId, sysUser.getId());
		List<SysUserRole> list = sysUserRoleService.list(sysRoleLambdaQueryWrapper);
		if ( !list.get(0).getId().equals("1")) {
				if (xdhc01.getShzt() != 2){
					xdhc01.setBlxcyy(xdhc01Fjsc.getBlxcyy());
					xdhc01.setZrjd(xdhc01Fjsc.getZrjd());
					xdhc01.setQsczcs(xdhc01Fjsc.getQsczcs());
					xdhc01.setQsczsx(xdhc01Fjsc.getQsczsx());
					xdhc01.setZyzrr(xdhc01Fjsc.getZyzrr());
					xdhc01.setQszrr(xdhc01Fjsc.getQszrr());
					xdhc01.setBz(xdhc01Fjsc.getBz());
				}
		}else {
			xdhc01.setBlxcyy(xdhc01Fjsc.getBlxcyy());
			xdhc01.setZrjd(xdhc01Fjsc.getZrjd());
			xdhc01.setQsczcs(xdhc01Fjsc.getQsczcs());
			xdhc01.setQsczsx(xdhc01Fjsc.getQsczsx());
			xdhc01.setZyzrr(xdhc01Fjsc.getZyzrr());
			xdhc01.setQszrr(xdhc01Fjsc.getQszrr());
			xdhc01.setBz(xdhc01Fjsc.getBz());
		}
		xdhc01.setLrsj(new Date());
		xdhc01.setLrbz(2);
		xdhc01.setLrr(getUsername());
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq("dkzh",xdhc01.getDkzh());
		xdhc01Service.update(xdhc01,queryWrapper);
		return Result.ok("编辑成功!");
	}

	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/init")
	 public Result<?> init(){
		 Result result = new Result<>();
		 try{
			 xdhc01Service.pXdhc01();
			 result.setSuccess(true);
			 return result;
		 }catch (Throwable e){
			 System.out.println(e);
			 log.error("提取失败",e.getMessage());
			 result.setSuccess(false);
		 }
		 return result;
	 }
	 /**
	  * 审核
	  */
	 @PutMapping(value = "/audit")
	 public Result<?> audit(@RequestBody Xdhc01 xdhc01){
		 QueryWrapper<Xdhc01> queryWrapper = new QueryWrapper<Xdhc01>();
		 queryWrapper.eq("dkzh",xdhc01.getDkzh());
		 xdhc01.setShzt(2);
		 xdhc01Service.update(xdhc01,queryWrapper);
		 return Result.ok("审核成功！");
	 }

	 /**
	  * 撤销审核
	  */
	 @PutMapping(value = "/unaudit")
	 public Result<?> unaudit(@RequestBody Xdhc01 xdhc01){
	 	QueryWrapper<Xdhc01> queryWrapper = new QueryWrapper<>();
	 	queryWrapper.eq("dkzh",xdhc01.getDkzh());
	 	xdhc01.setShzt(1);
	 	xdhc01Service.update(xdhc01,queryWrapper);
	 	return Result.ok("撤销审核成功！");
	 }


	 /**
	  * 全部审核
	  */
	 @PutMapping(value = "/allAudit")
	 public Result<?> allAudit(@RequestBody Xdhc01 xdhc01){
		 QueryWrapper<Xdhc01> queryWrapper = new QueryWrapper<Xdhc01>();
		 queryWrapper.eq("shzt",1);
		 xdhc01.setShzt(2);
		 xdhc01Service.update(xdhc01,queryWrapper);
		 return Result.ok("审核成功！");
	 }

	 /**
	  * 全部撤销
	  */
	 @PutMapping(value = "/allunAudit")
	 public Result<?> allunAudit(@RequestBody Xdhc01 xdhc01){
		 QueryWrapper<Xdhc01> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("shzt",2);
		 xdhc01.setShzt(1);
		 xdhc01Service.update(xdhc01,queryWrapper);
		 return Result.ok("全部撤销审核成功！");
	 }

	 /**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "信贷T+1核查-通过id删除")
	@ApiOperation(value="信贷T+1核查-通过id删除", notes="信贷T+1核查-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		xdhc01Service.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "信贷T+1核查-批量删除")
	@ApiOperation(value="信贷T+1核查-批量删除", notes="信贷T+1核查-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.xdhc01Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "信贷T+1核查-通过id查询")
	@ApiOperation(value="信贷T+1核查-通过id查询", notes="信贷T+1核查-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Xdhc01 xdhc01 = xdhc01Service.getById(id);
		return Result.ok(xdhc01);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param xdhc01
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Xdhc01 xdhc01) {
      return super.exportXls(request, xdhc01, Xdhc01.class, "信贷T+1核查");
  }

	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 return super.exportTemplateXls(Xdhc01Vo.class, "信贷T+1核查_01导入模板");
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
      return super.importExcelByTemplate(jsonObject,request, response, Xdhc01.class,Xdhc01Vo.class,xdhc01Verify);
  }

}

package org.cmms.modules.xddagl.xdhc.xdhcyc.controller;

import java.sql.Timestamp;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.xddagl.gwgl.sfwglfjxx.controller.SfwglFjxxController;
import org.cmms.modules.xddagl.gwgl.sfwglfjxx.entity.SfwglFjxx;
import org.cmms.modules.xddagl.gwgl.sfwglfjxx.service.ISfwglFjxxService;
import org.cmms.modules.xddagl.xdhc.xdhc02.entity.Xdhc02Vo;
import org.cmms.modules.xddagl.xdhc.xdhcyc.entity.Xdhcyc;
import org.cmms.modules.xddagl.xdhc.xdhcyc.entity.XdhcycFjVo;
import org.cmms.modules.xddagl.xdhc.xdhcyc.entity.XdhcycVo;
import org.cmms.modules.xddagl.xdhc.xdhcyc.service.IXdhcycService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.xddagl.xdhc.xdhcyc.verify.XdhcycVerify;
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
 * @Description: 信贷T+1核查先隐藏
 * @Author: jeecg-boot
 * @Date:   2022-01-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="信贷T+1核查先隐藏")
@RestController
@RequestMapping("/xdhcyc/xdhcyc")
public class XdhcycController extends JeecgController<Xdhcyc, IXdhcycService> {
	@Autowired
	private IXdhcycService xdhcycService;
	@Autowired
	private XdhcycVerify xdhcycVerify;
	@Autowired
	private ISfwglFjxxService sfwglFjxxService;

	/**
	 * 分页列表查询
	 *
	 * @param xdhcyc
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "信贷T+1核查先隐藏-分页列表查询")
	@ApiOperation(value="信贷T+1核查先隐藏-分页列表查询", notes="信贷T+1核查先隐藏-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Xdhcyc xdhcyc,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Xdhcyc> queryWrapper = QueryGenerator.initQueryWrapper(xdhcyc, req.getParameterMap());
		Page<Xdhcyc> page = new Page<Xdhcyc>(pageNo, pageSize);
		IPage<Xdhcyc> pageList = xdhcycService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param xdhcyc
	 * @return
	 */
	@AutoLog(value = "信贷T+1核查先隐藏-添加")
	@ApiOperation(value="信贷T+1核查先隐藏-添加", notes="信贷T+1核查先隐藏-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Xdhcyc xdhcyc) {
		xdhcycService.save(xdhcyc);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "信贷T+1核查先隐藏-编辑")
	@ApiOperation(value="信贷T+1核查先隐藏-编辑", notes="信贷T+1核查先隐藏-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody XdhcycFjVo xdhcycFjVo) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		Xdhcyc xdhcyc = new Xdhcyc();
		BeanUtils.copyProperties(xdhcycFjVo,xdhcyc);
		JSONArray fjxxs = xdhcycFjVo.getImgdate();
		SfwglFjxx fjxx = new SfwglFjxx();
		if (fjxxs != null && fjxxs.size()>0){
			for (int i = 0; i < fjxxs.size(); i++){
				String id = UUIDGenerator.generate();
				String fjname = (String) fjxxs.getJSONObject(i).get("name");
				String fjlx = fjname.split("_")[0];
				String wllj = uploadpath + "/" + fjxxs.getJSONObject(i).getJSONObject("response").getString("message");
				String fwlj =   "/" + fjxxs.getJSONObject(i).getJSONObject("response").getString("message");
				fjxx.setFwbhid(xdhcyc.getHth());
				fjxx.setWjid(UUID.randomUUID().toString().replaceAll("-", ""));
				fjxx.setFjlx(fjlx);
				fjxx.setWjlj(wllj);
				fjxx.setFwlj(fwlj);
				fjxx.setLrbz(1);
				fjxx.setLrr(sysUser.getUsername());
				fjxx.setLrsj(new Timestamp(System.currentTimeMillis()));
				sfwglFjxxService.save(fjxx);
			}
		}
		QueryWrapper<Xdhcyc> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("hth",xdhcyc.getHth());
		Xdhcyc xdhcyc1 = xdhcycService.getOne(queryWrapper);
		xdhcyc1.setXh(xdhcyc.getXh());
		xdhcyc1.setJgdm(xdhcyc.getJgdm());
		xdhcyc1.setKhxm(xdhcyc.getKhxm());
		xdhcyc1.setSjhm(xdhcyc.getSjhm());
		xdhcyc1.setZjhm(xdhcyc.getZjhm());
		xdhcyc1.setHth(xdhcyc.getHth());
		xdhcyc1.setZxjkrq(xdhcyc.getZxjkrq());
		xdhcyc1.setDkje(xdhcyc.getDkje());
		xdhcyc1.setDbfs(xdhcyc.getDbfs());
		xdhcyc1.setZrr(xdhcyc.getZrr());
		xdhcyc1.setLl(xdhcyc.getLl());
		xdhcyc1.setKhjl(xdhcyc.getKhjl());
		xdhcyc1.setHtzpr(xdhcyc.getHtzpr());
		xdhcyc1.setHtje(xdhcyc.getHtje());
		xdhcyc1.setBz(xdhcyc.getBz());
		xdhcyc1.setSfhc(xdhcyc.getSfhc());
		xdhcyc1.setLrsj(new Date());
		xdhcyc1.setLrr(sysUser.getUsername());
		xdhcycService.update(xdhcyc1,queryWrapper);

		return Result.ok("操作成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "信贷T+1核查先隐藏-通过id删除")
	@ApiOperation(value="信贷T+1核查先隐藏-通过id删除", notes="信贷T+1核查先隐藏-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("hth") String hth) {
		QueryWrapper<Xdhcyc> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("hth",hth);
		xdhcycService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}
	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/init")
	 public Result<?> init(){
		 Result result = new Result<>();
		 try{
			 xdhcycService.pXdhcyc();
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
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "信贷T+1核查先隐藏-批量删除")
	@ApiOperation(value="信贷T+1核查先隐藏-批量删除", notes="信贷T+1核查先隐藏-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.xdhcycService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "信贷T+1核查先隐藏-通过id查询")
	@ApiOperation(value="信贷T+1核查先隐藏-通过id查询", notes="信贷T+1核查先隐藏-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Xdhcyc xdhcyc = xdhcycService.getById(id);
		return Result.ok(xdhcyc);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param xdhcyc
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Xdhcyc xdhcyc) {
      return super.exportXls(request, xdhcyc, Xdhcyc.class, "信贷T+1核查先隐藏");
  }

	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 return super.exportTemplateXls(XdhcycVo.class, "信贷T+1核查先隐藏导入模板");
	 }
  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(@RequestBody JSONObject jsonObject ,HttpServletRequest request, HttpServletResponse response) {
      return super.importExcelByTemplate(jsonObject,request, response, Xdhcyc.class,XdhcycVo.class,xdhcycVerify);
  }

}

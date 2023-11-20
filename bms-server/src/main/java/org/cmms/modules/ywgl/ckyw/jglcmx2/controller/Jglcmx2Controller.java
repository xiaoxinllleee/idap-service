package org.cmms.modules.ywgl.ckyw.jglcmx2.controller;

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

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.ywgl.ckyw.jglcmx2.entity.Jglcmx2;
import org.cmms.modules.ywgl.ckyw.jglcmx2.mapper.Jglcmx2Mapper;
import org.cmms.modules.ywgl.ckyw.jglcmx2.service.IJglcmx2Service;
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
 * @Description: 机关揽储明细
 * @Author: jeecg-boot
 * @Date:   2021-10-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="机关揽储明细")
@RestController
@RequestMapping("/jglcmx2/jglcmx2")
public class Jglcmx2Controller extends JeecgController<Jglcmx2, IJglcmx2Service> {
	@Autowired
	private IJglcmx2Service jglcmx2Service;

	@Autowired(required = false)
	private Jglcmx2Mapper jglcmx2Mapper;

	 @Autowired
	 private RedisUtil redisUtil;
	
	/**
	 * 分页列表查询
	 *
	 * @param jglcmx2
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "机关揽储明细-分页列表查询")
	@ApiOperation(value="机关揽储明细-分页列表查询", notes="机关揽储明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Jglcmx2 jglcmx2,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   String begindayString,String dqrqString,
								   HttpServletRequest req) {
		QueryWrapper<Jglcmx2> queryWrapper = QueryGenerator.initQueryWrapper(jglcmx2, req.getParameterMap());
		if (begindayString != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] date = begindayString.split(",");
			try {
				queryWrapper.between("khrq",sdf.parse(date[0]),sdf.parse(date[1]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (dqrqString != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] date = dqrqString.split(",");
			try {
				queryWrapper.between("dqrq",sdf.parse(date[0]),sdf.parse(date[1]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IJglcmx2Service.class,jglcmx2Service,pageNo,pageSize,queryWrapper,"tjyf","ckzh","tzrgh");
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/init")
	 public Result<?> init(@RequestBody JSONObject jsonObject){
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
		 String tjyf = jsonObject.getString("tjyf");
		 String jgdm = jsonObject.getString("jgdm");
		 String tzrgh = jsonObject.getString("tzrgh");
		 if (-1 == DateUtil.getCurrentTS().compareTo(DateUtil.parseDateFormat(tjyf, "yyyy-MM-dd"))) {
			 return Result.error("统计日期必须小于当前系统日期！");
		 }
		 try {
			 jglcmx2Mapper.pJglcmx2(tjyf, jgdm, tzrgh);
		 }catch (Throwable e){
		 	e.printStackTrace();
		 	return Result.error("提取失败，请查看系统监控日志信息");
		 }
		 return Result.ok("提取成功");
	 }
	
	/**
	 * 添加
	 *
	 * @param jglcmx2
	 * @return
	 */
	@AutoLog(value = "机关揽储明细-添加")
	@ApiOperation(value="机关揽储明细-添加", notes="机关揽储明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Jglcmx2 jglcmx2) {
		jglcmx2Service.save(jglcmx2);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param jglcmx2
	 * @return
	 */
	@AutoLog(value = "机关揽储明细-编辑")
	@ApiOperation(value="机关揽储明细-编辑", notes="机关揽储明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Jglcmx2 jglcmx2) {
		jglcmx2Service.updateById(jglcmx2);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "机关揽储明细-通过id删除")
	@ApiOperation(value="机关揽储明细-通过id删除", notes="机关揽储明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		jglcmx2Service.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "机关揽储明细-批量删除")
	@ApiOperation(value="机关揽储明细-批量删除", notes="机关揽储明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.jglcmx2Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "机关揽储明细-通过id查询")
	@ApiOperation(value="机关揽储明细-通过id查询", notes="机关揽储明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Jglcmx2 jglcmx2 = jglcmx2Service.getById(id);
		return Result.ok(jglcmx2);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param jglcmx2
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Jglcmx2 jglcmx2) {
      return super.exportXls(request, jglcmx2, Jglcmx2.class, "机关揽储明细");
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
      return super.importExcel(request, response, Jglcmx2.class);
  }

}

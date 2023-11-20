package org.cmms.modules.ywgl.ckyw.jxlcyw.yglcjxmx.controller;

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
import org.cmms.modules.ywgl.ckyw.jxlcyw.yglcjxmx.entity.Yglcjxmx;
import org.cmms.modules.ywgl.ckyw.jxlcyw.yglcjxmx.service.IYglcjxmxService;
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
 * @Description: 员工揽储绩效明细
 * @Author: jeecg-boot
 * @Date:   2021-10-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="员工揽储绩效明细")
@RestController
@RequestMapping("/yglcjxmx/yglcjxmx")
public class YglcjxmxController extends JeecgController<Yglcjxmx, IYglcjxmxService> {
	@Autowired
	private IYglcjxmxService yglcjxmxService;
	@Autowired
	private RedisUtil redisUtil;
	
	/**
	 * 分页列表查询
	 *
	 * @param yglcjxmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "员工揽储绩效明细-分页列表查询")
	@ApiOperation(value="员工揽储绩效明细-分页列表查询", notes="员工揽储绩效明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Yglcjxmx yglcjxmx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   String khrqString,
								   HttpServletRequest req) {
		QueryWrapper<Yglcjxmx> queryWrapper = QueryGenerator.initQueryWrapper(yglcjxmx, req.getParameterMap());
		if (khrqString != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] date = khrqString.split(",");
			try {
				queryWrapper.between("khrq",sdf.parse(date[0]),sdf.parse(date[1]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IYglcjxmxService.class,yglcjxmxService,pageNo,pageSize,queryWrapper,"tjyf","zzbz","gwbz","yggh","ckzh");
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
		 if (-1 == DateUtil.getCurrentTS().compareTo(DateUtil.parseDateFormat(tjyf, "yyyy-MM-dd"))) {
			 return Result.error("统计日期必须小于当前系统日期！");
		 }
		 try {
		 	yglcjxmxService.pYglcjxmx(tjyf);
		 }catch (Throwable e){
		 	e.printStackTrace();
		 	return Result.error("提取失败，请查看系统监控日志信息");
		 }
		 return Result.ok("提取成功");
	 }
	
	/**
	 * 添加
	 *
	 * @param yglcjxmx
	 * @return
	 */
	@AutoLog(value = "员工揽储绩效明细-添加")
	@ApiOperation(value="员工揽储绩效明细-添加", notes="员工揽储绩效明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Yglcjxmx yglcjxmx) {
		yglcjxmxService.save(yglcjxmx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param yglcjxmx
	 * @return
	 */
	@AutoLog(value = "员工揽储绩效明细-编辑")
	@ApiOperation(value="员工揽储绩效明细-编辑", notes="员工揽储绩效明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Yglcjxmx yglcjxmx) {
		yglcjxmxService.updateById(yglcjxmx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工揽储绩效明细-通过id删除")
	@ApiOperation(value="员工揽储绩效明细-通过id删除", notes="员工揽储绩效明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		yglcjxmxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "员工揽储绩效明细-批量删除")
	@ApiOperation(value="员工揽储绩效明细-批量删除", notes="员工揽储绩效明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.yglcjxmxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工揽储绩效明细-通过id查询")
	@ApiOperation(value="员工揽储绩效明细-通过id查询", notes="员工揽储绩效明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Yglcjxmx yglcjxmx = yglcjxmxService.getById(id);
		return Result.ok(yglcjxmx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param yglcjxmx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Yglcjxmx yglcjxmx) {
      return super.exportXls(request, yglcjxmx, Yglcjxmx.class, "员工揽储绩效明细");
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
      return super.importExcel(request, response, Yglcjxmx.class);
  }

}

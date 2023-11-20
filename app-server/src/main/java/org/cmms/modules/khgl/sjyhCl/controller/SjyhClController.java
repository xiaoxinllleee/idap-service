package org.cmms.modules.khgl.sjyhCl.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.ezf.entity.Ezf;
import org.cmms.modules.khgl.sjyh.entity.Ckglsjyh;
import org.cmms.modules.khgl.sjyhCl.entity.SjyhCl;
import org.cmms.modules.khgl.sjyhCl.service.ISjyhClService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
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
 * @Description: 手机银行_慈利
 * @Author: jeecg-boot
 * @Date:   2022-11-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags="手机银行_慈利")
@RestController
@RequestMapping("/sjyhCl/sjyhCl")
public class SjyhClController extends JeecgController<SjyhCl, ISjyhClService> {
	@Autowired
	private ISjyhClService sjyhClService;
	@Autowired
	private IHrBasOrganizationService iHrBasOrganizationService;
	/**
	 * 分页列表查询
	 *
	 * @param sjyhCl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "手机银行_慈利-分页列表查询")
	@ApiOperation(value="手机银行_慈利-分页列表查询", notes="手机银行_慈利-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SjyhCl sjyhCl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		System.out.println(getRedisQydm());
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		HrBasOrganization hrBasOrganization = iHrBasOrganizationService.queryByZzbz(loginUser.getOrgCode());
		QueryWrapper<SjyhCl> queryWrapper = QueryGenerator.initQueryWrapper(sjyhCl, req.getParameterMap());
		Page<SjyhCl> page = new Page<SjyhCl>(pageNo, pageSize);
		IPage<SjyhCl> pageList = sjyhClService.page(page, queryWrapper);
		//登录人组织类别为3的直接查询全部数据
		/*log.info(hrBasOrganization.getZzlb()+"=======zzlb");
		if ("3".equals(hrBasOrganization.getZzlb())){
			return Result.ok(pageList);
		}
		QueryWrapper<SjyhCl> queryWrapper1 = QueryGenerator.initQueryWrapper(sjyhCl, req.getParameterMap());
		queryWrapper1.eq("jgdm",hrBasOrganization.getYwjgdm());
		List<SjyhCl> list1 = sjyhClService.list(queryWrapper1);
		QueryWrapper<SjyhCl> queryWrapper2 = QueryGenerator.initQueryWrapper(sjyhCl, req.getParameterMap());
		queryWrapper2.ne("jgdm",hrBasOrganization.getYwjgdm());
		List<SjyhCl> list2 = sjyhClService.list(queryWrapper2);

		List<SjyhCl> list =new ArrayList();
		list.addAll(list1);
		list.addAll(list2);
		pageList.setRecords(list);*/
		return Result.ok(pageList);
	}

	@GetMapping(value = "/getDate")
	public Result<?> getDate(){
		String date = sjyhClService.getDate().substring(0,10);
		return Result.ok("操作成功",date);
	}
	/**
	 * 添加
	 *
	 * @param sjyhCl
	 * @return
	 */
	@AutoLog(value = "手机银行_慈利-添加")
	@ApiOperation(value="手机银行_慈利-添加", notes="手机银行_慈利-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SjyhCl sjyhCl) {
		sjyhClService.save(sjyhCl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sjyhCl
	 * @return
	 */
	@AutoLog(value = "手机银行_慈利-编辑")
	@ApiOperation(value="手机银行_慈利-编辑", notes="手机银行_慈利-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SjyhCl sjyhCl) {
		sjyhClService.updateById(sjyhCl);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "手机银行_慈利-通过id删除")
	@ApiOperation(value="手机银行_慈利-通过id删除", notes="手机银行_慈利-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sjyhClService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "手机银行_慈利-批量删除")
	@ApiOperation(value="手机银行_慈利-批量删除", notes="手机银行_慈利-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sjyhClService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "手机银行_慈利-通过id查询")
	@ApiOperation(value="手机银行_慈利-通过id查询", notes="手机银行_慈利-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SjyhCl sjyhCl = sjyhClService.getById(id);
		return Result.ok(sjyhCl);
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param sjyhCl
	 */
	@RequestMapping(value = "/exportXls")
	public ModelAndView exportXls(HttpServletRequest request, SjyhCl sjyhCl) {
		return super.exportXls(request, sjyhCl, SjyhCl.class, "手机银行_慈利");
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
		return super.importExcel(request, response, SjyhCl.class);
	}

}

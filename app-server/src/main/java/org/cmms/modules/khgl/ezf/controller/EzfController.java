package org.cmms.modules.khgl.ezf.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.checkerframework.checker.units.qual.A;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.ezf.entity.Ezf;
import org.cmms.modules.khgl.ezf.service.IEzfService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.sjyhCl.entity.SjyhCl;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.util.PageUtil;
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
 * @Description: E支付
 * @Author: jeecg-boot
 * @Date:   2022-11-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags="E支付")
@RestController
@RequestMapping("/ezf/ezf")
public class EzfController extends JeecgController<Ezf, IEzfService> {
	@Autowired
	private IEzfService ezfService;
	@Autowired
	private IHrBasOrganizationService iHrBasOrganizationService;
	/**
	 * 分页列表查询
	 *
	 * @param ezf
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "E支付-分页列表查询")
	@ApiOperation(value="E支付-分页列表查询", notes="E支付-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ezf ezf,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		QueryWrapper<Ezf> queryWrapper = QueryGenerator.initQueryWrapper(ezf, req.getParameterMap());
		Page<Ezf> page = new Page<Ezf>(pageNo, pageSize);
		IPage<Ezf> pageList = ezfService.page(page, queryWrapper);
		HrBasOrganization hrBasOrganization = iHrBasOrganizationService.queryByZzbz(loginUser.getOrgCode());
		//登录人组织类别为3的直接查询全部数据
		/*log.info(hrBasOrganization.getZzlb()+"=======zzlb");
		if ("3".equals(hrBasOrganization.getZzlb())){
			return Result.ok(pageList);
		}
		log.info("JGDM=========="+hrBasOrganization.getYwjgdm());
		//备注：page 首先是肯定不能最开始去构造
		//1.先把本支行的数据筛选出来， 然后结合非支行支行的数据  构造成一个new  list   (表中符合条件的所有记录数 )
		QueryWrapper<Ezf> queryWrapper1 = QueryGenerator.initQueryWrapper(ezf, req.getParameterMap());
		queryWrapper1.eq("jgdm",hrBasOrganization.getYwjgdm());
		List<Ezf> list1 = ezfService.list(queryWrapper1);
		QueryWrapper<Ezf> queryWrapper2 = QueryGenerator.initQueryWrapper(ezf, req.getParameterMap());
		queryWrapper2.ne("jgdm",hrBasOrganization.getYwjgdm());
		List<Ezf> list2 = ezfService.list(queryWrapper2);
		//2. 把新的list 去构造成page （一个list  怎么去变成一个page）
		List<Ezf> list = new ArrayList<>();
		list.addAll(list1);
		list.addAll(list2);
		log.info("list"+list);
		//IPage pages = PageUtil.getPages(list, list.size(), pageNo, pageSize);
		pageList.setRecords(list);*/
		return Result.ok(pageList);
	}
	@GetMapping(value = "/getDate")
	public Result<?> getDate(){
		String date = ezfService.getDate().substring(0,10);
		return Result.ok("操作成功",date);
	}
	/**
	 * 添加
	 *
	 * @param ezf
	 * @return
	 */
	@AutoLog(value = "E支付-添加")
	@ApiOperation(value="E支付-添加", notes="E支付-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ezf ezf) {
		ezfService.save(ezf);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ezf
	 * @return
	 */
	@AutoLog(value = "E支付-编辑")
	@ApiOperation(value="E支付-编辑", notes="E支付-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ezf ezf) {
		ezfService.updateById(ezf);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "E支付-通过id删除")
	@ApiOperation(value="E支付-通过id删除", notes="E支付-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ezfService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "E支付-批量删除")
	@ApiOperation(value="E支付-批量删除", notes="E支付-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ezfService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "E支付-通过id查询")
	@ApiOperation(value="E支付-通过id查询", notes="E支付-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ezf ezf = ezfService.getById(id);
		return Result.ok(ezf);
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param ezf
	 */
	@RequestMapping(value = "/exportXls")
	public ModelAndView exportXls(HttpServletRequest request, Ezf ezf) {
		return super.exportXls(request, ezf, Ezf.class, "E支付");
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
		return super.importExcel(request, response, Ezf.class);
	}

}

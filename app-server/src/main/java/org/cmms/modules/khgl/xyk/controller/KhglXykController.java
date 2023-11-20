package org.cmms.modules.khgl.xyk.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.Base64Util;
import org.cmms.modules.common.appXykFjxx.entity.AppXykFjxx;
import org.cmms.modules.common.appXykFjxx.service.IAppXykFjxxService;
import org.cmms.modules.common.appfjxx.entity.AppFjxx;
import org.cmms.modules.common.appfjxx.service.IAppFjxxService;
import org.cmms.modules.khgl.xjjl.entity.AppJhshXjjl;
import org.cmms.modules.khgl.xyk.entity.KhglXyk;
import org.cmms.modules.khgl.xyk.service.IKhglXykService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysDic;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDicService;
import org.cmms.modules.system.service.ISysUserService;
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
 * @Description: 信用卡_慈利
 * @Author: jeecg-boot
 * @Date:   2022-11-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags="信用卡_慈利")
@RestController
@RequestMapping("/xyk/khglXyk")
public class KhglXykController extends JeecgController<KhglXyk, IKhglXykService> {
	@Autowired
	private IKhglXykService khglXykService;
	@Autowired
	private IAppXykFjxxService appFjxxService;
	@Autowired
	ISysDicService sysDicService;
	@Autowired
	private IHrBasOrganizationService iHrBasOrganizationService;
	/**
	 * 分页列表查询
	 *
	 * @param khglXyk
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "信用卡_慈利-分页列表查询")
	@ApiOperation(value="信用卡_慈利-分页列表查询", notes="信用卡_慈利-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhglXyk khglXyk,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		//QueryWrapper<KhglXyk> queryWrapper = QueryGenerator.initQueryWrapper(khglXyk, req.getParameterMap());
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		HrBasOrganization hrBasOrganization = iHrBasOrganizationService.queryByZzbz(loginUser.getOrgCode());
		String format = DateUtil.format(khglXyk.getTjrq(), "yyyy-MM-dd");
		Date parse = DateUtil.parse(format);
		QueryWrapper<KhglXyk> queryWrapper = new QueryWrapper<>();
		if (khglXyk.getTjrq() != null){
			queryWrapper.eq("tjrq",parse);
		}
		if (StringUtils.isNotBlank(khglXyk.getKh())){
			queryWrapper.eq("kh",khglXyk.getKh());
		}
		//登录人机构代码不为空就根据机构代码，查本机构的数据
		if (StringUtils.isNotBlank(hrBasOrganization.getYwjgdm())){
			queryWrapper.eq("jgdm",hrBasOrganization.getYwjgdm());
		}else {
			//登录人机构代码为空就根据登录人工号查推广员工号
			queryWrapper.eq("yggh",loginUser.getWorkNo());
		}
		queryWrapper.orderByDesc("sfcs");
		Page<KhglXyk> page = new Page<KhglXyk>(pageNo, pageSize);
		IPage<KhglXyk> pageList = khglXykService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	@GetMapping(value = "/getDate")
	public Result<?> getDate(){
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		HrBasOrganization hrBasOrganization = iHrBasOrganizationService.queryByZzbz(loginUser.getOrgCode());
		String date = khglXykService.getDate(hrBasOrganization.getYwjgdm(),loginUser.getWorkNo()).substring(0,10);
		return Result.ok("操作成功",date);
	}
	/**
	 * 应催收合计
	 * @param khglXyk
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/getYcshj")
	public Result<?> getYcshj(KhglXyk khglXyk,HttpServletRequest req) {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String format = DateUtil.format(khglXyk.getTjrq(), "yyyy-MM-dd");
		Date parse = DateUtil.parse(format);
		HrBasOrganization hrBasOrganization = iHrBasOrganizationService.queryByZzbz(loginUser.getOrgCode());
		log.info(hrBasOrganization.getYwjgdm()+"===jgdm==="+parse);
		String ycshj = khglXykService.getYcshj(hrBasOrganization.getYwjgdm(), parse);
		return Result.ok("操作成功！",ycshj);
	}

	/**
	 * 添加
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "信用卡_慈利-添加")
	@ApiOperation(value="信用卡_慈利-添加", notes="信用卡_慈利-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody JSONObject jsonObject) {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		KhglXyk appXyk = JSON.toJavaObject(jsonObject,KhglXyk.class);
		//JSONObject jsonObject = new JSONObject();
		System.out.println(appXyk+"===========");
		String format = DateUtil.format(appXyk.getTjrq(), "yyyy-MM-dd");
		Date parse = DateUtil.parse(format);

		String filePath = jsonObject.getString("filePath");
		JSONArray array = jsonObject.getJSONArray("filePath");
		System.out.println(array+"===========");
		AppXykFjxx fjxx = null;
		if (array.size()>0){
			String s = IdUtil.fastSimpleUUID();
			for (int i = 0; i < array.size(); i++) {
				String o = (String)array.get(i);
				if (o.contains("app")){
					String app = o.substring(o.indexOf("app") - 1);
					fjxx = new AppXykFjxx();
					fjxx.setTjrq(parse);
					fjxx.setKh(appXyk.getKh());
					fjxx.setBz(appXyk.getBz());
					fjxx.setId(s);
					fjxx.setWjlj(uploadpath+app);
					fjxx.setFwlj(app);
					fjxx.setLrsj(new Date());
					fjxx.setLrr(getWorkNo());
					appFjxxService.save(fjxx);
				}
			}
		}
		QueryWrapper<KhglXyk> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("tjrq",parse);
		queryWrapper.eq("kh",appXyk.getKh());
		KhglXyk xyk = khglXykService.getOne(queryWrapper, false);
		if (xyk != null){
			khglXykService.remove(queryWrapper);
		}
		appXyk.setSfcs("1");
		log.info("===信用卡催收保存的数据{}===",appXyk.toString());
		khglXykService.save(appXyk);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param khglXyk
	 * @return
	 */
	@AutoLog(value = "信用卡_慈利-编辑")
	@ApiOperation(value="信用卡_慈利-编辑", notes="信用卡_慈利-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhglXyk khglXyk) {
		khglXykService.updateById(khglXyk);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "信用卡_慈利-通过id删除")
	@ApiOperation(value="信用卡_慈利-通过id删除", notes="信用卡_慈利-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khglXykService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "信用卡_慈利-批量删除")
	@ApiOperation(value="信用卡_慈利-批量删除", notes="信用卡_慈利-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khglXykService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "信用卡_慈利-通过id查询")
	@ApiOperation(value="信用卡_慈利-通过id查询", notes="信用卡_慈利-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		KhglXyk khglXyk = khglXykService.getById(id);
		return Result.ok(khglXyk);
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param khglXyk
	 */
	@RequestMapping(value = "/exportXls")
	public ModelAndView exportXls(HttpServletRequest request, KhglXyk khglXyk) {
		return super.exportXls(request, khglXyk, KhglXyk.class, "信用卡_慈利");
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
		return super.importExcel(request, response, KhglXyk.class);
	}

}

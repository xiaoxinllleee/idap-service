package org.cmms.modules.ywgl.dkyw.hxdkgl.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.aspectj.util.LangUtil;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.sjxf.hxxt.dkzdkb.entity.Dkzdkb;
import org.cmms.modules.sjxf.hxxt.dkzdkb.service.IDkzdkbService;
import org.cmms.modules.sjxf.xdxt.dkywjjb.entity.Dkywjjb;
import org.cmms.modules.sjxf.xdxt.dkywjjb.service.IDkywjjbService;
import org.cmms.modules.ywgl.dkyw.hxdkgl.entity.HrBasStaffPostVo;
import org.cmms.modules.ywgl.dkyw.hxdkgl.entity.Hxdkgl;
import org.cmms.modules.ywgl.dkyw.hxdkgl.entity.HxdkglVo;
import org.cmms.modules.ywgl.dkyw.hxdkgl.service.IHxdkglService;
import org.cmms.modules.ywgl.dkyw.hxdkgl.verify.HxdkglImportVerify;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.entity.Lsdksjgl;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.service.ILsdksjglService;
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
 * @Description: 核销贷款管理
 * @Author: jeecg-boot
 * @Date:   2021-09-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="核销贷款管理")
@RestController
@RequestMapping("/hxdkgl/hxdkgl")
public class HxdkglController extends JeecgController<Hxdkgl, IHxdkglService> {
	 @Autowired
	 private IHxdkglService hxdkglService;
	 @Autowired
	 private IHrBasStaffService hrBasStaffService;
	 @Autowired
	 private IDkzdkbService dkzdkbService;
	 @Autowired
	 private ILsdksjglService lsdksjglService;
	 @Autowired
	 private IDkywjjbService dkywjjbService;
	 @Autowired
	 private HxdkglImportVerify hxdkglImportVerify;




	/**
	 * 分页列表查询
	 *
	 * @param hxdkgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "核销贷款管理-分页列表查询")
	@ApiOperation(value="核销贷款管理-分页列表查询", notes="核销贷款管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Hxdkgl hxdkgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Hxdkgl> queryWrapper = QueryGenerator.initQueryWrapper(hxdkgl, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IHxdkglService.class,hxdkglService,pageNo,pageSize,queryWrapper,"acct_no");
		return Result.ok(pageList);
	}

	 /**
	  * 查找带回
	  */
	 @AutoLog(value = "认领列表")
	 @ApiOperation(value = "认领", notes = "待分配存款帐号管理")
	 @PostMapping(value = "/getListClaim")
	 public Result<?> getListClaim(@RequestBody JSONObject jsonObject){
	 	System.out.println(jsonObject);
		 String gyh = jsonObject.getString("gyh");
		 String khjlbz = jsonObject.getString("khjlbz");
		 String yggh = jsonObject.getString("yggh");
		 List<HrBasStaffPostVo> list = hxdkglService.getListClaim(gyh, khjlbz, yggh);
		 return Result.ok(list);
	 }

	/**
	 * 添加
	 *
	 * @param hxdkgl
	 * @return
	 */
	@AutoLog(value = "核销贷款管理-添加")
	@ApiOperation(value="核销贷款管理-添加", notes="核销贷款管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Hxdkgl hxdkgl) {

		{//传输的参数检查
			if (org.apache.commons.lang3.StringUtils.isEmpty(hxdkgl.getJobnumber())) {
				return Result.error("员工工号不能为空");
			}
		}

		try {
			QueryWrapper<HrBasStaff> queryWrapper=new QueryWrapper();
			queryWrapper.eq("yggh",hxdkgl.getJobnumber());
			HrBasStaff ygxx = hrBasStaffService.getOne(queryWrapper);
 			if(ygxx==null){
				return Result.error("添加失败,员工信息不存在!");
			}

			QueryWrapper<Hxdkgl> queryWrapperHxdk = new QueryWrapper<>();
			queryWrapperHxdk.eq("acct_no",hxdkgl.getAcctNo());
			Hxdkgl hxdkxx = hxdkglService.getOne(queryWrapperHxdk);
			if(hxdkxx!=null){
				return Result.error("添加失败,数据已经存在!");
			}
			QueryWrapper<Dkzdkb> queryWrapperDkzb= new QueryWrapper<>();
			queryWrapperDkzb.eq("acct_no",hxdkgl.getAcctNo());
			Dkzdkb dkyeb = dkzdkbService.getOne(queryWrapperDkzb);
			if(dkyeb==null){
				QueryWrapper<Lsdksjgl> queryWrapperLsdksj= new QueryWrapper<>();
				queryWrapperLsdksj.eq("acct_no",hxdkgl.getAcctNo());
				Lsdksjgl lsdksj = lsdksjglService.getOne(queryWrapperLsdksj);
				if(lsdksj==null){
					return Result.error("添加失败,贷款账号不存在!");
				}else{
					if (lsdksj.getBalance().doubleValue() > 0) {
						return Result.error("此账号在贷款历史数据表中还有贷款余额【" + lsdksj.getBalance().toString()+ "】，不允许添加为已核销贷款！");
					}
					BeanUtils.copyProperties(lsdksj, hxdkgl);
				}
			}else{
				if (dkyeb.getLoanBal().doubleValue() > 0) {
					return Result.error("此账号在贷款余额表中还有贷款余额【" + dkyeb.getLoanBal().toString() +
							"】，不允许添加为已核销贷款！");
				}

				hxdkgl.setOrg(dkyeb.getBrNo());
				hxdkgl.setFinInsName(dkyeb.getBrName());
				hxdkgl.setPutOutDate(new Timestamp(DateUtil.string2Date(dkyeb.getQxDate(), "yyyyMMdd").getTime()));
				hxdkgl.setMaturity(new Timestamp(DateUtil.string2Date(dkyeb.getEndDate(), "yyyyMMdd").getTime()));
				hxdkgl.setPutoutSum(dkyeb.getAdvVal());
				hxdkgl.setCtfcCd(dkyeb.getIdentNo());
				hxdkgl.setAppRate(dkyeb.getIntRate());
				hxdkgl.setRate(dkyeb.getIntRate());
				hxdkgl.setBusinessNo(dkyeb.getBusinessNo());
				BeanUtils.copyProperties(dkyeb, hxdkgl);
				hxdkgl.setCustTel(dkyeb.getLinkmanTel());
				hxdkgl.setJzkm(dkyeb.getSubjNo());
				hxdkgl.setCurrency(dkyeb.getCurr());
				hxdkgl.setQxDate(new Timestamp(DateUtil.string2Date(dkyeb.getIntStrtDate(), "yyyyMMdd").getTime()));
		    	QueryWrapper queryWrapper1 =new QueryWrapper();
				queryWrapper1.eq("acct_no",hxdkgl.getAcctNo());
				Dkywjjb ywjjb = dkywjjbService.getOne(queryWrapper1);
				if(ywjjb!=null){
					hxdkgl.setCustManagerId(ywjjb.getUserId());
				}
			}
			if (org.apache.commons.lang3.StringUtils.isEmpty(hxdkgl.getCustManagerId())) {
				hxdkgl.setCustManagerId(ygxx.getKhjlbh());
			}
			LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

			hxdkgl.setLrbz(1);
			hxdkgl.setLrczy(sysUser.getUsername());
			hxdkgl.setLrsj(new Timestamp(System.currentTimeMillis()));
			hxdkglService.save(hxdkgl);
			return Result.ok("添加核销贷款信息成功！");
		} catch (Throwable tx) {
			return Result.error("系统错误！" + tx.getMessage());
		}
	}

	/**
	 * 编辑
	 *
	 * @param hxdkgl
	 * @return
	 */
	@AutoLog(value = "核销贷款管理-编辑")
	@ApiOperation(value="核销贷款管理-编辑", notes="核销贷款管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Hxdkgl hxdkgl) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		hxdkgl.setLrbz(2);
		hxdkgl.setXgczy(sysUser.getUsername());
		hxdkgl.setXgsj(new Timestamp(System.currentTimeMillis()));
		QueryWrapper<Hxdkgl> queryWrapper = new QueryWrapper<Hxdkgl>();
		queryWrapper.eq("acct_No",hxdkgl.getAcctNo());
		hxdkgl.setAcctNo(null);
		hxdkglService.update(hxdkgl,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "核销贷款管理-通过id删除")
	@ApiOperation(value="核销贷款管理-通过id删除", notes="核销贷款管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("acctNo")String acctNo) {
		QueryWrapper<Hxdkgl> queryWrapper = new QueryWrapper<Hxdkgl>();
		queryWrapper.eq("acct_No",acctNo);
		hxdkglService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "核销贷款管理-批量删除")
	@ApiOperation(value="核销贷款管理-批量删除", notes="核销贷款管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.hxdkglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "核销贷款管理-通过id查询")
	@ApiOperation(value="核销贷款管理-通过id查询", notes="核销贷款管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Hxdkgl hxdkgl = hxdkglService.getById(id);
		return Result.ok(hxdkgl);
	}

	  /**
	   * 导出excel
	   *
	   * @param request
	   * @param hxdkgl
	   */
	  @RequestMapping(value = "/exportXls")
	  public ModelAndView exportXls(HttpServletRequest request, Hxdkgl hxdkgl) {
		  return super.exportXls(request, hxdkgl, Hxdkgl.class, "核销贷款管理");
	  }


	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 //return super.exportTemplateXls(HxdkglVo.class, "核销贷款管理导入模板");

		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "核销贷款管理导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, HxdkglVo.class);
		 ExportParams exportParams = new ExportParams("核销贷款管理导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;

	 }


	 /**
	  * 通过excel导入数据
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcelByTemplate(jsonObject, request, response, Hxdkgl.class,HxdkglVo.class, hxdkglImportVerify);
	 }


  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
/*  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, Hxdkgl.class);
  }*/

}

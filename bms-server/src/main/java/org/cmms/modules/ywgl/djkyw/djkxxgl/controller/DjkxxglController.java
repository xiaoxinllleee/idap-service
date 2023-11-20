package org.cmms.modules.ywgl.djkyw.djkxxgl.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.entity.Djkdksjmx;
import org.cmms.modules.ywgl.djkyw.djkrygl.entity.Djkrygl;
import org.cmms.modules.ywgl.djkyw.djkrygl.entity.DjkryglImportVo;
import org.cmms.modules.ywgl.djkyw.djkrygl.service.IDjkryglService;
import org.cmms.modules.ywgl.djkyw.djkwdgl.entity.Djkwdgl;
import org.cmms.modules.ywgl.djkyw.djkwdgl.service.IDjkwdglService;
import org.cmms.modules.ywgl.djkyw.djkxxgl.entity.Djkxxgl;
import org.cmms.modules.ywgl.djkyw.djkxxgl.entity.DjkxxglImportVo;
import org.cmms.modules.ywgl.djkyw.djkxxgl.service.IDjkxxglService;
import org.cmms.modules.ywgl.djkyw.djkxxgl.verify.DjkxxlImportVerify;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
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
 * @Description: 贷记卡信息管理
 * @Author: jeecg-boot
 * @Date:   2021-12-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷记卡信息管理")
@RestController
@RequestMapping("/djkxxgl/djkxxgl")
public class DjkxxglController extends JeecgController<Djkxxgl, IDjkxxglService> {
	 @Autowired
	 private IDjkxxglService djkxxglService;
	 @Autowired
	 private IDjkwdglService djkwdglService;
	 @Autowired
	 private IDjkryglService djkryglServicel;
	 @Autowired
	 private IHrBasOrganizationService hrBasOrganizationService;
	 @Autowired
	 private IHrBasStaffPostService hrBasStaffPostService;
	 @Autowired
	 private IHrBasStaffService hrBasStaffService;
	 @Autowired
	 private ISysDictService sysDictService;
	 @Autowired
	 private DjkxxlImportVerify djkxxlImportVerify;
	 @Autowired
	 private IDjkryglService djkryglService;

	/**
	 * 分页列表查询
	 *
	 * @param djkxxgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷记卡信息管理-分页列表查询")
	@ApiOperation(value="贷记卡信息管理-分页列表查询", notes="贷记卡信息管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Djkxxgl djkxxgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Djkxxgl> queryWrapper = QueryGenerator.initQueryWrapper(djkxxgl, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IDjkxxglService.class,djkxxglService,pageNo,pageSize,queryWrapper,"tjyf","jobnumber","acct_no");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param djkxxgl
	 * @return
	 */
	@AutoLog(value = "贷记卡信息管理-添加")
	@ApiOperation(value="贷记卡信息管理-添加", notes="贷记卡信息管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Djkxxgl djkxxgl) {
		if (StringUtils.isEmpty(djkxxgl.getAcctNo())) {
			return Result.error("贷记卡卡号不能为空!");
		}
		if (StringUtils.isEmpty(djkxxgl.getCtfcCd())) {
			return Result.error("证件号码不能为空!");
		}
		if (StringUtils.isEmpty(djkxxgl.getCustName())) {
			return Result.error("客户名称不能为空!");
		}
		if (StringUtils.isEmpty(djkxxgl.getTgh())) {
			return Result.error("推广人员编号不能为空!");
		}
		if (djkxxgl.getTjyf() == null) {
			return Result.error("统计月份不能为空!");
		}
		if (djkxxgl.getYqqs() == null || djkxxgl.getYqqs() <= 0) {
			return Result.error("贷记卡账号【" + djkxxgl.getAcctNo() +
					"】的逾期期数必须大于0！");
		}
		String strTgh = djkxxgl.getTgh();  //根据推广人员编号截取机构代码和员工号
		strTgh = retTgh(strTgh);

		if(strTgh.length()<13){
			return Result.error("推广人员编号格式有误！");
		}
		String strJgdm = strTgh.substring(0,8);
		String strJob = strTgh.substring(8,13);

		QueryWrapper<Djkxxgl> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("tjyf", djkxxgl.getTjyf());
		queryWrapper.eq("acct_no", djkxxgl.getAcctNo());
		List<Djkxxgl> djkxxglList = djkxxglService.list(queryWrapper);
		if (!djkxxglList.isEmpty()) {
			return Result.error("数据已经存在！");
		}

		QueryWrapper<Djkwdgl> djkwdglQueryWrapper = new QueryWrapper<>();
		djkwdglQueryWrapper.eq("tgjgbh", strJgdm);
		List<Djkwdgl> djkwdglList = djkwdglService.list(djkwdglQueryWrapper);
		if (djkwdglList.isEmpty()) {
			return Result.error("请先在贷记卡网点关联中维护推广人员编码的机构信息！");
		} else {
			djkxxgl.setOrg(djkwdglList.get(0).getJgdm());
		}
		QueryWrapper<Djkrygl> djkryglQueryWrapper = new QueryWrapper<>();
		djkryglQueryWrapper.eq("tgrybh", strJob);
		List<Djkrygl> djkryglList = djkryglServicel.list(djkryglQueryWrapper);
		if (djkryglList.isEmpty()) {
			return Result.error("请先在贷记卡人员关联中维护推广人员编码对应的员工号！");
		} else {
			djkxxgl.setJobnumber(djkryglList.get(0).getYggh());
		}
		if (StringUtils.isEmpty(djkxxgl.getOrg())) {
			return Result.error("机构代码不能为空！");
		}
		if (StringUtils.isEmpty(djkxxgl.getJobnumber())) {
			return Result.error("员工工号不能为空！");
		}
		//计算到期日期
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(djkxxgl.getTjyf());
		gc.add(2,Double.valueOf(djkxxgl.getYqqs() * -1).intValue());
		djkxxgl.setMaturity(new Timestamp(gc.getTimeInMillis()));
		//存储过程中需要贷款发放日期
		djkxxgl.setPutOutDate(new Timestamp(gc.getTimeInMillis()));

		if (djkxxgl.getMaturity()==null) {
			return Result.error("到期日期不能为空!");
		}

		QueryWrapper<HrBasOrganization> organQueryWrapper  = new QueryWrapper<>();
		organQueryWrapper.eq("ywjgdm", djkxxgl.getOrg());
		List<HrBasOrganization> organList = hrBasOrganizationService.list(organQueryWrapper);
		if (organList.isEmpty()) {
			return Result.error("机构代码【" + djkxxgl.getOrg() + "】错误！");
		}
		QueryWrapper<HrBasStaff> staffQueryWrapper = new QueryWrapper<>();
		staffQueryWrapper.eq("yggh", djkxxgl.getJobnumber());
		List<HrBasStaff> staffList = hrBasStaffService.list(staffQueryWrapper);
		if (staffList.isEmpty()) {
			return Result.error("员工工号【" + djkxxgl.getJobnumber() + "】错误！");
		}
//		if (StringUtils.isEmpty(staffList.get(0).getKhjlbh())) {
//			return Result.error("没有为员工【" + djkxxgl.getJobnumber() +
//					"】配置客户经理标识信息！");
//		}
		if (!StringUtils.isEmpty(staffList.get(0).getKhjlbh())) {
			djkxxgl.setCustManagerId(staffList.get(0).getKhjlbh());
		}
		djkxxgl.setPutoutSum(djkxxgl.getBalance());
		djkxxgl.setLrbz(1);
		djkxxgl.setLrsj(new Date());
		djkxxgl.setLrczy(getUsername());
		djkxxglService.save(djkxxgl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param djkxxgl
	 * @return
	 */
	@AutoLog(value = "贷记卡信息管理-编辑")
	@ApiOperation(value="贷记卡信息管理-编辑", notes="贷记卡信息管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Djkxxgl djkxxgl) {
		if (StringUtils.isEmpty(djkxxgl.getAcctNo())) {
			return Result.error("贷记卡卡号不能为空!");
		}
		if (StringUtils.isEmpty(djkxxgl.getCtfcCd())) {
			return Result.error("证件号码不能为空!");
		}
		if (StringUtils.isEmpty(djkxxgl.getCustName())) {
			return Result.error("客户名称不能为空!");
		}
		if (StringUtils.isEmpty(djkxxgl.getTgh())) {
			return Result.error("推广人员编号不能为空!");
		}
		if (djkxxgl.getTjyf() == null) {
			return Result.error("统计月份不能为空!");
		}
		if (djkxxgl.getYqqs() == null || djkxxgl.getYqqs() <= 0) {
			return Result.error("贷记卡账号【" + djkxxgl.getAcctNo() +
					"】的逾期期数必须大于0！");
		}
		String strTgh = djkxxgl.getTgh();  //根据推广人员编号截取机构代码和员工号
		strTgh = retTgh(strTgh);

		if(strTgh.length()<13){
			return Result.error("推广人员编号格式有误！");
		}
		String strJgdm = strTgh.substring(0,8);
		String strJob = strTgh.substring(8,13);

		QueryWrapper<Djkwdgl> djkwdglQueryWrapper = new QueryWrapper<>();
		djkwdglQueryWrapper.eq("tgjgbh", strJgdm);
		List<Djkwdgl> djkwdglList = djkwdglService.list(djkwdglQueryWrapper);
		if (djkwdglList.isEmpty()) {
			return Result.error("请先在贷记卡网点关联中维护推广人员编码的机构信息！");
		} else {
			djkxxgl.setOrg(djkwdglList.get(0).getJgdm());
		}
		QueryWrapper<Djkrygl> djkryglQueryWrapper = new QueryWrapper<>();
		djkryglQueryWrapper.eq("tgrybh", strJob);
		List<Djkrygl> djkryglList = djkryglServicel.list(djkryglQueryWrapper);
		if (djkryglList.isEmpty()) {
			return Result.error("请先在贷记卡人员关联中维护推广人员编码对应的员工号！");
		} else {
			djkxxgl.setJobnumber(djkryglList.get(0).getYggh());
		}
		if (StringUtils.isEmpty(djkxxgl.getOrg())) {
			return Result.error("机构代码不能为空！");
		}
		if (StringUtils.isEmpty(djkxxgl.getJobnumber())) {
			return Result.error("员工工号不能为空！");
		}

		//计算到期日期
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(djkxxgl.getTjyf());
		gc.add(2,Double.valueOf(djkxxgl.getYqqs() * -1).intValue());
		djkxxgl.setMaturity(new Timestamp(gc.getTimeInMillis()));
		//存储过程中需要贷款发放日期
		djkxxgl.setPutOutDate(new Timestamp(gc.getTimeInMillis()));

		if (djkxxgl.getMaturity()==null) {
			return Result.error("到期日期不能为空!");
		}

		QueryWrapper<Djkxxgl> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("tjyf", djkxxgl.getTjyf());
		queryWrapper.eq("acct_no", djkxxgl.getAcctNo());
		List<Djkxxgl> djkxxglList = djkxxglService.list(queryWrapper);
		if (djkxxglList.isEmpty()) {
			return Result.error("数据不存在！");
		}

		QueryWrapper<HrBasOrganization> organQueryWrapper  = new QueryWrapper<>();
		organQueryWrapper.eq("ywjgdm", djkxxgl.getOrg());
		List<HrBasOrganization> organList = hrBasOrganizationService.list(organQueryWrapper);
		if (organList.isEmpty()) {
			return Result.error("机构代码【" + djkxxgl.getOrg() + "】错误！");
		}

		QueryWrapper<HrBasStaff> staffQueryWrapper = new QueryWrapper<>();
		staffQueryWrapper.eq("yggh", djkxxgl.getJobnumber());
		List<HrBasStaff> staffList = hrBasStaffService.list(staffQueryWrapper);
		if (staffList.isEmpty()) {
			return Result.error("员工工号【" + djkxxgl.getJobnumber() + "】错误！");
		}
		Djkxxgl djkxxglUpdate = djkxxglList.get(0);
		djkxxglUpdate.setOrg(djkxxgl.getOrg());
		djkxxglUpdate.setJobnumber(djkxxgl.getJobnumber());
		djkxxglUpdate.setAcctNo(djkxxgl.getAcctNo());
		djkxxglUpdate.setCtfcCd(djkxxgl.getCtfcCd());
		djkxxglUpdate.setCustName(djkxxgl.getCustName());
		djkxxglUpdate.setPutOutDate(djkxxgl.getPutOutDate());
		djkxxglUpdate.setMaturity(djkxxgl.getMaturity());
		djkxxglUpdate.setMinCalcDate(djkxxgl.getMinCalcDate());
		djkxxglUpdate.setPutoutSum(djkxxgl.getPutoutSum());
		djkxxglUpdate.setBalance(djkxxgl.getBalance());
		djkxxglUpdate.setYqqs(djkxxgl.getYqqs());
		djkxxglUpdate.setLrsj(new Date());
		djkxxglUpdate.setLrbz(2);
		djkxxglUpdate.setLrczy(getUsername());

		UpdateWrapper<Djkxxgl> updateWrapper = new UpdateWrapper<Djkxxgl>();
		updateWrapper.eq("acct_no",djkxxgl.getAcctNo());
		updateWrapper.eq("tjyf",djkxxgl.getTjyf());
		djkxxglService.update(djkxxglUpdate, updateWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "贷记卡信息管理-通过id删除")
	@ApiOperation(value="贷记卡信息管理-通过id删除", notes="贷记卡信息管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("acct_no")String acctNo,@Param("tjyf")String tjyf) {
		QueryWrapper<Djkxxgl> queryWrapper = new QueryWrapper<Djkxxgl>();
		queryWrapper.eq("acct_no",acctNo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			queryWrapper.eq("tjyf",sdf.parse(tjyf));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		djkxxglService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷记卡信息管理-批量删除")
	@ApiOperation(value="贷记卡信息管理-批量删除", notes="贷记卡信息管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.djkxxglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷记卡信息管理-通过id查询")
	@ApiOperation(value="贷记卡信息管理-通过id查询", notes="贷记卡信息管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Djkxxgl djkxxgl = djkxxglService.getById(id);
		return Result.ok(djkxxgl);
	}

	 /**
	  * 通过id查询
	  *
	  * @param djkkh
	  * @return
	  */
	 @AutoLog(value = "贷记卡信息管理-通过djkkh查询")
	 @ApiOperation(value="贷记卡信息管理-通过djkkh查询", notes="贷记卡信息管理-通过djkkh查询")
	 @GetMapping(value = "/queryByKh")
	 public Result<?> queryByKh(@RequestParam(name="djkkh",required=true) String djkkh) {
	 	 QueryWrapper<Djkxxgl> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("acct_no", djkkh);
		 queryWrapper.orderByDesc("tjyf");
		 List<Djkxxgl> djkxxglList = djkxxglService.list(queryWrapper);
		 if (djkxxglList.isEmpty()) {
		 	 return Result.error("卡号不存在！");
		 }
		 JSONObject jsonObject = new JSONObject();
		 Djkxxgl djkxxgl = djkxxglList.get(0);
		 if (!StringUtils.isEmpty(djkxxgl.getCustManagerId())) {
			 QueryWrapper<HrBasStaff> staffQueryWrapper = new QueryWrapper<>();
			 staffQueryWrapper.eq("khjlbh", djkxxgl.getCustManagerId());
			 List<HrBasStaff> staffList = hrBasStaffService.list(staffQueryWrapper);
			 if(!staffList.isEmpty()) {
				 jsonObject.put("jobnumber", staffList.get(0).getYggh());
				 jsonObject.put("jobnumber_dictText", staffList.get(0).getYgxm());
				 jsonObject.put("jobnumberzr_dictText", staffList.get(0).getYgxm());
			 }
		 }
		 jsonObject.put("djkkh", djkkh);
		 jsonObject.put("khmc", djkxxgl.getCustName());
		 jsonObject.put("jgdm", djkxxgl.getOrg());
		 jsonObject.put("jgdm_dictText", sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "ywjgdm", djkxxgl.getOrg()));
		 jsonObject.put("zjhm", djkxxgl.getCtfcCd());
		 jsonObject.put("custid", djkxxgl.getCustManagerId());
		 jsonObject.put("custidzr", djkxxgl.getCustManagerId());
		 jsonObject.put("jobnumberzr", djkxxgl.getJobnumber());
		 return Result.ok(jsonObject);
	 }

  /**
   * 导出excel
   *
   * @param request
   * @param djkxxgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Djkxxgl djkxxgl) {
      return super.exportXls(request, djkxxgl, Djkxxgl.class, "贷记卡信息管理");
  }

	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {

		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "贷记卡信息导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, DjkxxglImportVo.class);
		 ExportParams exportParams = new ExportParams("贷记卡信息导入模板", "模板信息");
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
		 //return super.importExcelByTemplate(jsonObject, request, response, Djkxxgl.class, djkxxlImportVerify);
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String filePaths = jsonObject.getString("filePaths");
		 if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
			 return Result.error("请先上传文件！");
		 }

		 String[] filePathList = filePaths.split(",");
		 JSONObject obj = new JSONObject();
		 for (String filePath : filePathList) {
			 System.out.println(filePath);
			 String baseFilePath = uploadpath + File.separator + filePath;
//          MultipartFile file = entity.getValue();// 获取上传文件对象
			 File file = new File(baseFilePath);
			 ImportParams params = new ImportParams();
			 params.setTitleRows(1);
			 params.setHeadRows(1);
			 params.setNeedSave(false);
			 if (djkxxlImportVerify != null) {
				 params.setVerifyHanlder(djkxxlImportVerify);
			 }
			 FileOutputStream fos = null;

			 try {
				 ExcelImportResult<Djkxxgl> importResult = ExcelImportUtil.importExcelVerify(file, Djkxxgl.class,DjkxxglImportVo.class, params);
				 List<Djkxxgl> list = importResult.getList();
				 List<String> listAcctNO=new ArrayList<>();
				 for(Djkxxgl djkxxgl: list){
					 listAcctNO.add(djkxxgl.getAcctNo());
				 }
				 QueryWrapper<Djkxxgl> queryWrapper2 =new QueryWrapper();
				 queryWrapper2.in("acct_no",listAcctNO);
				 queryWrapper2.eq("tjyf",DateUtil.getMonthBeginDay(list.get(0).getTjyf()));
				 djkxxglService.remove(queryWrapper2);

				 service.saveBatch(list);
				 obj.put("filePath", filePath);
				 fos = new FileOutputStream(baseFilePath);
				 importResult.getWorkbook().write(fos);
				 fos.flush();
				 fos.close();
				 return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
			 } catch (Exception e) {
				 log.error(e.getMessage(),e);
				 return Result.error("文件导入失败:"+e.getMessage());
			 } finally {
				 IoUtil.close(fos);
			 }
		 }
		 return Result.ok("文件导入失败！");
	 }

	 /**
	  * 推广人员编号格式化
	  * @param tgh
	  * @return
	  */
	 public static String retTgh(String tgh){
		 if(tgh!=null&&!"".equals(tgh)){
			 tgh = tgh.replace(".","") ;
			 return !tgh.equals("")&&tgh.length()>1?tgh.substring(0,tgh.length()).trim():tgh.trim();
		 }
		 return  "" ;
	 }

}

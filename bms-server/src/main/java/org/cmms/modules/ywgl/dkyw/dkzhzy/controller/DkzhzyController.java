package org.cmms.modules.ywgl.dkyw.dkzhzy.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.DateUtil;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.ywgl.dkyw.dkzhzy.entity.Dkzhzy;
import org.cmms.modules.ywgl.dkyw.dkzhzy.entity.DkzhzyImportVo;
import org.cmms.modules.ywgl.dkyw.dkzhzy.entity.DzyzhsVO;
import org.cmms.modules.ywgl.dkyw.dkzhzy.service.IDkzhzyService;
import org.cmms.modules.ywgl.dkyw.dkzhzy.verify.DkzhzyImportVerify;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 贷款账号转移
 * @Author: jeecg-boot
 * @Date:   2021-09-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款账号转移")
@RestController
@RequestMapping("/dkzhzy/dkzhzy")
public class DkzhzyController extends JeecgController<Dkzhzy, IDkzhzyService> {
	@Autowired
	private IDkzhzyService dkzhzyService;
	 @Autowired
	 private IHrBasStaffService hrBasStaffService;

	 @Autowired
	 private DkzhzyImportVerify dkzhzyImportVerify;


	 @Value("${com.etl.sfdsjpt}")
	 private String sfdsjpt;

	 @Autowired
	 private ISysDictService iSysDictService;
	/**
	 * 分页列表查询
	 *
	 * @param dkzhzy
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款账号转移-分页列表查询")
	@ApiOperation(value="贷款账号转移-分页列表查询", notes="贷款账号转移-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dkzhzy dkzhzy,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dkzhzy> queryWrapper = QueryGenerator.initQueryWrapper(dkzhzy, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IDkzhzyService.class,dkzhzyService,pageNo,pageSize,queryWrapper,"org","acct_no","jobnumber");
		return Result.ok(pageList);
	}

	 /**
	  * 转移
	  */
	 @RequestMapping(value = "/init")
	 public Result<?> init(@RequestBody JSONObject jsonObject){
		 String org = jsonObject.getString("org");
		 String custManagerId = jsonObject.getString("custManagerId");
		 String acctNo = jsonObject.getString("acctNo");

		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 Result result = new Result<>();
		if ("true".equalsIgnoreCase(sfdsjpt)) {
			 HashMap<String, String> parm = new HashMap<>();
			 parm.put("in_custid",custManagerId);
			 parm.put("in_dkzh",acctNo);
			 parm.put("in_org",org);
			 parm.put("etl_task","kiss.domain.application.cdkyw.proc_ywgl_dkyw_dkzhzy_zhinit");
			 // count_ywgl_dkyw_dkzhzy_zhinit
			 boolean falg = EtlUtil.callEtl("cdkyw_common_init", parm, 20);
		 } else {
			 try {
			 	 if(acctNo==null){
					 dkzhzyService.pDkzhzy1(org,custManagerId);
				 }else if(custManagerId==null){
					 dkzhzyService.pDkzhzy2(org,acctNo);
				 }else{
					 dkzhzyService.pDkzhzy3(org,custManagerId,acctNo);
				 }
			 } catch (Exception e) {
				 System.out.println(e);
				 log.error("提取失败", e.getMessage());
			 }
		 }
		 DzyzhsVO dzysByKhjlOrDkzh = dkzhzyService.getDzysByKhjlOrDkzh(org, custManagerId, acctNo);
		 if(dzysByKhjlOrDkzh==null||dzysByKhjlOrDkzh.getZhs()==0){
			 return  Result.error("未找到需要转移的账号信息！");
		 }else{
			 QueryWrapper<HrBasStaff> queryWrapper = new QueryWrapper<>();
			 queryWrapper.eq("khjlbh",dzysByKhjlOrDkzh.getKhjlbz());
			 HrBasStaff one = hrBasStaffService.getOne(queryWrapper);
			 if(one==null){
				 return  Result.error("未找到客户经理标志[" + dzysByKhjlOrDkzh.getKhjlbz() + "]对应的员工信息！");
			 }else{
				 dzysByKhjlOrDkzh.setDkzh(acctNo);
				 dzysByKhjlOrDkzh.setYggh(one.getYggh());
				 dzysByKhjlOrDkzh.setYgxm(one.getYgxm());
				 dzysByKhjlOrDkzh.setJgdm(org);
			 }
			 return  Result.ok(dzysByKhjlOrDkzh);
		 }
	 }

	/**
	 * 添加
	 *
	 * @param dkzhzy
	 * @return
	 */
	@AutoLog(value = "贷款账号转移保存")
	@ApiOperation(value="贷款账号转移保存", notes="贷款账号转移保存")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DzyzhsVO dkzhzy) {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		try {
			String oldzhjgdm =iSysDictService.queryTableDictTextByKey("V_hr_bas_organization","sjywjgdm","ywjgdm",dkzhzy.getJgdm());
			String newzhjgdm=iSysDictService.queryTableDictTextByKey("V_hr_bas_organization","sjywjgdm","zzbz",dkzhzy.getZyhzzbz());
			String newjgdm=iSysDictService.queryTableDictTextByKey("V_hr_bas_organization","ywjgdm","zzbz",dkzhzy.getZyhzzbz());
			if (!oldzhjgdm.equals(newzhjgdm)) {
				return Result.error("只能转移给员工所在支行的客户经理！");
			}
			if ("true".equalsIgnoreCase(sfdsjpt)) {
				String glid = dkzhzyService.getGlid();
				HashMap<String, String> parm = new HashMap<>();
				parm.put("in_org",dkzhzy.getJgdm());
				parm.put("in_custid",dkzhzy.getKhjlbz());
				parm.put("in_neworg",newjgdm);
				parm.put("in_newcustid",dkzhzy.getZyhkhjlbz());
				parm.put("in_newjobnumber",dkzhzy.getZyhyggh());
				parm.put("in_newgyh",dkzhzy.getZyhgyh());
				parm.put("in_newgwbz",dkzhzy.getZyhgwbz());
				parm.put("in_dkzh",dkzhzy.getDkzh());
				parm.put("in_czy",loginUser.getUsername());
				parm.put("in_type","2");
				parm.put("in_glbz",null);
				parm.put("glid",glid);
				parm.put("etl_task","kiss.domain.application.cdkyw.proc_ywgl_dkyw_dkzhzy_two");
				// count_ywgl_dkyw_dkzhzy_two
				boolean falg = EtlUtil.callEtl("cdkyw_common_init", parm, 20);
			} else {
				try {
					if(dkzhzy.getDkzh()!=null){
						dkzhzyService.dkzhzy1(dkzhzy.getJgdm(),dkzhzy.getKhjlbz(),newjgdm,dkzhzy.getZyhkhjlbz(),dkzhzy.getZyhyggh(),dkzhzy.getZyhgwbz(),dkzhzy.getZyhgyh(),dkzhzy.getDkzh(),loginUser.getUsername());
					}else{
						dkzhzyService.dkzhzy2(dkzhzy.getJgdm(),dkzhzy.getKhjlbz(),newjgdm,dkzhzy.getZyhkhjlbz(),dkzhzy.getZyhyggh(),dkzhzy.getZyhgwbz(),dkzhzy.getZyhgyh(),loginUser.getUsername());
					}
				} catch (Exception e) {
					System.out.println(e);
					log.error("提取失败", e.getMessage());
				}
			}
			return Result.ok("转移操作成功");
		} catch (Throwable tx) {
			log.error("系统错误！", tx);
			return Result.error("系统错误！" + tx.getMessage());
		}
	}

	/**
	 * 编辑
	 *
	 * @param dkzhzy
	 * @return
	 */
	@AutoLog(value = "贷款账号转移-编辑")
	@ApiOperation(value="贷款账号转移-编辑", notes="贷款账号转移-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dkzhzy dkzhzy) {
		dkzhzyService.updateById(dkzhzy);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "贷款账号转移-通过id删除")
	@ApiOperation(value="贷款账号转移-通过id删除", notes="贷款账号转移-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("acctNo")String acctNo) {
		QueryWrapper<Dkzhzy> queryWrapper = new QueryWrapper<Dkzhzy>();
		queryWrapper.eq("acct_No",acctNo);
		dkzhzyService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款账号转移-批量删除")
	@ApiOperation(value="贷款账号转移-批量删除", notes="贷款账号转移-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkzhzyService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款账号转移-通过id查询")
	@ApiOperation(value="贷款账号转移-通过id查询", notes="贷款账号转移-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dkzhzy dkzhzy = dkzhzyService.getById(id);
		return Result.ok(dkzhzy);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkzhzy
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dkzhzy dkzhzy) {
      return super.exportXls(request, dkzhzy, Dkzhzy.class, "贷款账号转移");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "贷款账号转移导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, DkzhzyImportVo.class);
		 ExportParams exportParams = new ExportParams("贷款账号转移导入模板", "模板信息");
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
//		 return super.importExcelByTemplate(jsonObject, request, response, Dkzhzy.class, dkzhzyImportVerify);

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
			 if (dkzhzyImportVerify != null) {
				 params.setVerifyHanlder(dkzhzyImportVerify);
			 }
			 FileInputStream fis = null;
			 FileOutputStream fos = null;

			 try {
				 fis = new FileInputStream(file);
				 boolean checkResult = ExcelImportCheckUtil.check(fis, DkzhzyImportVo.class, params, 1.0);
				 ExcelImportResult<DkzhzyImportVo> importResult = ExcelImportUtil.importExcelVerify(file, DkzhzyImportVo.class, params, false);
				 List<DkzhzyImportVo> list = importResult.getList();

				 List<Dkzhzy> dkzhzyList = new ArrayList<>();
				 for (DkzhzyImportVo dkzhzyImportVo : list) {
					 Dkzhzy dkzhzy = new Dkzhzy();
					 BeanUtil.copyPropertiesIgnoreNull(dkzhzyImportVo, dkzhzy);
					 dkzhzyList.add(dkzhzy);

				 }
				 if (!dkzhzyList.isEmpty()) {
					 dkzhzyService.saveBatch(dkzhzyList);
				 }

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
				 IoUtil.close(fis);
				 IoUtil.close(fos);
			 }
		 }
		 return Result.ok("文件导入失败！");
	 }
 }

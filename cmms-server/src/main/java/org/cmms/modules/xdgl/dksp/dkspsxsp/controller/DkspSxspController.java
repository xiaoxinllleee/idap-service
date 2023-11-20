package org.cmms.modules.xdgl.dksp.dkspsxsp.controller;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.net.URLDecoder;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Comment;
import org.apache.commons.lang.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.ActivitiConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.ImageOverlapUtil;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.activiti.entity.ActBusiness;
import org.cmms.modules.activiti.service.ActBusinessService;
import org.cmms.modules.activiti.service.ActProcessService;
import org.cmms.modules.activiti.service.mybatis.IHistoryIdentityService;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
import org.cmms.modules.system.entity.*;
import org.cmms.modules.system.service.*;
import org.cmms.modules.util.WaterMarkUtil;
import org.cmms.modules.util.WordUtils;
import org.cmms.modules.xdgl.dksp.dkspedsz.entity.Dkspedsz;
import org.cmms.modules.xdgl.dksp.dkspedsz.service.IDkspedszService;
import org.cmms.modules.xdgl.dksp.dkspkhzl.entity.DkspKhzl;
import org.cmms.modules.xdgl.dksp.dkspkhzl.service.IDkspKhzlService;
import org.cmms.modules.xdgl.dksp.dkspsxsp.entity.DkspSxsp;
import org.cmms.modules.xdgl.dksp.dkspsxsp.entity.VDkspSxsp;
import org.cmms.modules.xdgl.dksp.dkspsxsp.service.IDkspSxspService;
import org.cmms.modules.xdgl.dksp.dkspsxsp.service.IVDkspSxspService;
import org.cmms.modules.xdgl.dksp.dkspsxsp.vo.DkspSxspPage;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import sun.misc.BASE64Encoder;

/**
 * @Description: 贷款审批授信审批
 * @Author: jeecg-boot
 * @Date:   2021-12-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款审批授信审批")
@RestController
@RequestMapping("/dksp/sxsp")
public class DkspSxspController extends JeecgController<DkspSxsp, IDkspSxspService> {
	@Autowired
	private IDkspSxspService dkspSxspService;
	@Autowired
	private IVDkspSxspService vDkspSxspService;
	@Autowired
	private IDkspKhzlService dkspKhzlService;
	@Autowired
	private IDkspedszService dkspedszService;
	 @Autowired
	 private ActBusinessService actBusinessService;
	 @Autowired
	 private ActProcessService actProcessService;
	 @Autowired
	 private TaskService taskService;

	 @Autowired
	 private HistoryService historyService;
	 @Autowired
	 private IHistoryIdentityService iHistoryIdentityService;
	@Autowired
	private ISysUserService userService;
	@Autowired
	private IVhrbasstaffpostService vhrbasstaffpostService;
	@Autowired
	private ISysDictService sysDictService;
	@Autowired
	private ISysDicService sysDicService;
	@Autowired
	private ISysUserSignService sysUserSignService;
	@Autowired
	private ISysDictService iSysDictService;
	@Value(value = "${common.path.upload}")
	private String uploadpath;
	@Value(value = "${common.path.export}")
	private String exportpath;
	@Autowired
	private ISysDepartmentSignService sysDepartmentSignService;
	@Autowired
	private ISysUserSignOtherService sysUserSignOtherService;
	@Autowired
	private IHrBasStaffPostService hrBasStaffPostService;
	/**
	 * 分页列表查询
	 *
	 * @param vDkspSxsp
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款审批授信审批-分页列表查询")
	@ApiOperation(value="贷款审批授信审批-分页列表查询", notes="贷款审批授信审批-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VDkspSxsp vDkspSxsp,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VDkspSxsp> queryWrapper = QueryGenerator.initQueryWrapper(vDkspSxsp, req.getParameterMap());
		Page<VDkspSxsp> page = new Page<VDkspSxsp>(pageNo, pageSize);
		IPage<VDkspSxsp> pageList = vDkspSxspService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param dkspSxspPage
	 * @return
	 */
	@AutoLog(value = "贷款审批授信审批-添加")
	@ApiOperation(value="贷款审批授信审批-添加", notes="贷款审批授信审批-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DkspSxspPage dkspSxspPage) {
		DkspSxsp dkspSxsp = new DkspSxsp();
		BeanUtils.copyProperties(dkspSxspPage, dkspSxsp);
		String uuid = IdUtil.simpleUUID();
		dkspSxsp.setId(uuid);
		List<DkspKhzl> dkspKhzlList = dkspSxspPage.getDkspSxzlList();
		for(DkspKhzl dkspKhzl : dkspKhzlList) {
			dkspKhzl.setKhid(uuid);
			dkspKhzl.setZjhm(dkspSxsp.getZjhm());
			dkspKhzl.setScr(getUsername());
			dkspKhzl.setScsj(new Date());
		}
		dkspKhzlService.saveBatch(dkspKhzlList);

		//根据所在机构与审批金额判断是否需要进行审批
		//默认需要审批
		String businessNumber = "creditApproval";
		String processId = actProcessService.findByProcessKeyAndLatest(businessNumber, true).getId();
		JSONObject jsonObject = new JSONObject();
		//需要审批
		jsonObject.put("process", true);
		dkspSxsp.setBusinessNumber(businessNumber);
		dkspSxsp.setProcessId(processId);
		// 提交申请流程
		ActBusiness actBusiness = new ActBusiness();
		// 删除标识(0 正常 1 已删除)
		actBusiness.setDelFlag(0);
		actBusiness.setApplyTime(new Timestamp(System.currentTimeMillis()));
		actBusiness.setProcDefId(processId);
		// 结果状态(0 默认未提交 1 处理中 2 通过 3 驳回)
		actBusiness.setResult(0);
		// 处理状态(0 默认草稿 1 处理中 2 结束)
		actBusiness.setStatus(0);
		actBusiness.setTableId(uuid);
		String title = String.format("%s发起的授信审批申请[申请人：%s，最高授信额度: %s，已使用授信额：%s]",
				getLoginUser().getRealname(), dkspSxsp.getKhmc(), dkspSxsp.getZgsxed().toString() + "万元", dkspSxsp.getYysxed().toString() + "万元");
		actBusiness.setTitle(title);
		actBusiness.setUserId(getLoginUser().getId());
		actBusiness.setCreateBy(getLoginUser().getUsername());
		actBusiness.setCreateTime(new Timestamp(System.currentTimeMillis()));
		actBusinessService.save(actBusiness);
		dkspSxspService.save(dkspSxsp);
		return Result.ok("添加成功！", jsonObject);
	}
	
	/**
	 * 编辑
	 *
	 * @param dkspSxspPage
	 * @return
	 */
	@AutoLog(value = "贷款审批授信审批-编辑")
	@ApiOperation(value="贷款审批授信审批-编辑", notes="贷款审批授信审批-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DkspSxspPage dkspSxspPage) {
		DkspSxsp dkspSxsp = new DkspSxsp();
		BeanUtils.copyProperties(dkspSxspPage, dkspSxsp);
		//是否删除附件
		if(dkspSxspPage.getDeleteFiles() != null && !dkspSxspPage.getDeleteFiles().isEmpty()) {
			dkspKhzlService.removeByIds(dkspSxspPage.getDeleteFiles());
		}
		//是否上传附件
		List<DkspKhzl> dkspKhzlList = dkspSxspPage.getDkspSxzlList();
		for(DkspKhzl dkspKhzl : dkspKhzlList) {
			dkspKhzl.setKhid(dkspSxsp.getId());
			dkspKhzl.setZjhm(dkspSxsp.getZjhm());
			dkspKhzl.setScr(getUsername());
			dkspKhzl.setScsj(new Date());
		}
		dkspKhzlService.saveBatch(dkspKhzlList);
		JSONObject jsonObject = new JSONObject();
		//判断是否已经进入审批流程
		List<ActBusiness> actBusinessList = actBusinessService.findByTableId(dkspSxsp.getId());
		if (actBusinessList.isEmpty()) {
			String businessNumber = "creditApproval";
			String processId = actProcessService.findByProcessKeyAndLatest(businessNumber, true).getId();
			//需要审批
			jsonObject.put("process", true);
			dkspSxsp.setBusinessNumber(businessNumber);
			dkspSxsp.setProcessId(processId);
			// 提交申请流程
			ActBusiness actBusiness = new ActBusiness();
			// 删除标识(0 正常 1 已删除)
			actBusiness.setDelFlag(0);
			actBusiness.setApplyTime(new Timestamp(System.currentTimeMillis()));
			actBusiness.setProcDefId(processId);
			// 结果状态(0 默认未提交 1 处理中 2 通过 3 驳回)
			actBusiness.setResult(0);
			// 处理状态(0 默认草稿 1 处理中 2 结束)
			actBusiness.setStatus(0);
			actBusiness.setTableId(dkspSxsp.getId());
			String title = String.format("%s发起的授信审批申请[申请人：%s，最高授信额度: %s，已使用授信额：%s]",
					getLoginUser().getRealname(), dkspSxsp.getKhmc(), dkspSxsp.getZgsxed().toString() + "万元", dkspSxsp.getYysxed().toString() + "万元");
			actBusiness.setTitle(title);
			actBusiness.setUserId(getLoginUser().getId());
			actBusiness.setCreateBy(getLoginUser().getUsername());
			actBusiness.setCreateTime(new Timestamp(System.currentTimeMillis()));
			actBusinessService.save(actBusiness);
		} else {
			//如果是驳回状态，需要重新申请
			if(actBusinessList.get(0).getResult() == ActivitiConstant.RESULT_FAIL) {
				jsonObject.put("process", true);
			} else {
				jsonObject.put("process", false);
			}
		}

		dkspSxspService.updateById(dkspSxsp);
		return Result.ok("编辑成功!", jsonObject);
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款审批授信审批-通过id删除")
	@ApiOperation(value="贷款审批授信审批-通过id删除", notes="贷款审批授信审批-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkspSxspService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款审批授信审批-批量删除")
	@ApiOperation(value="贷款审批授信审批-批量删除", notes="贷款审批授信审批-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkspSxspService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款审批授信审批-通过id查询")
	@ApiOperation(value="贷款审批授信审批-通过id查询", notes="贷款审批授信审批-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VDkspSxsp dkspSxsp = vDkspSxspService.getById(id);
		JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(dkspSxsp));
		jsonObject.put("zzbz_dictText", sysDictService.queryTableDictTextByKey("V_HR_BAS_ORGANIZATION_CMMS", "zzjc", "zzbz", dkspSxsp.getZzbz()));
		return Result.ok(jsonObject);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkspSxsp
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DkspSxsp dkspSxsp) {
      return super.exportXls(request, dkspSxsp, DkspSxsp.class, "贷款审批授信审批");
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
      return super.importExcel(request, response, DkspSxsp.class);
  }

	/**
	 * 通过excel导入数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/download")
	public void download(DkspSxsp dkspSxsp, HttpServletRequest request, HttpServletResponse response) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		OutputStream pdfOutPutStream = null;
		try {
			Map<String, Object> data = new HashMap<>();
			SysDic sysDic = sysDicService.queryByCode("101002");
			dkspSxsp.setXydj(sysDictService.queryDictTextByKey("rate_xydj", dkspSxsp.getXydj()));
			data.put("sxsp", dkspSxsp);
			if (dkspSxsp.getGksxed() != null) {
				data.put("gksxedShow", dkspSxsp.getGksxed() + "万元");
			} else {
				data.put("gksxedShow", "");
			}
			if (dkspSxsp.getNbsxed() != null) {
				data.put("nbsxedShow", dkspSxsp.getNbsxed() + "万元");
			} else {
				data.put("nbsxedShow", "");
			}
			data.put("bankName", sysDic.getValue());
			data.put("zzjc", sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", dkspSxsp.getZzbz()));
			data.put("date", DateUtil.formatDateTime("yyyy年MM月dd日"));
			data.put("sxqxBegin", DateUtil.formatDateTime("yyyy年MM月dd日", dkspSxsp.getSxqxBegin()));
			data.put("sxqxEnd", DateUtil.formatDateTime("yyyy年MM月dd日", dkspSxsp.getSxqxEnd()));
			//查询审批历史
			data.put("spList", getApprovalHistory(dkspSxsp.getId()));

			String docFileName = IdUtil.simpleUUID() + "授信审批意见表.docx";
			String docFilePath = uploadpath + File.separator + "dksp" + File.separator + docFileName;
			FileUtil.mkParentDirs(docFilePath);
			//生成doc文件
			String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001");
			String resourceName = "授信审批意见表.ftl";
			if("390".equals(qydm)) {
				resourceName = "授信审批意见表_文字.ftl";
			}
			WordUtils.generateWord(data, docFilePath, resourceName);

			inputStream = new FileInputStream(docFilePath);
			String pdfFileName = IdUtil.simpleUUID() + "授信审批意见表.pdf";
			String pdfFilePath = uploadpath + File.separator + "dksp" + File.separator + pdfFileName;

			pdfOutPutStream = new FileOutputStream(pdfFilePath);
			//转换成pdf文件
			IConverter converter = LocalConverter.builder().build();
			converter.convert(inputStream).as(DocumentType.DOCX).to(pdfOutPutStream).as(DocumentType.PDF).execute();
			String waterFileName = IdUtil.simpleUUID() + "授信审批意见表_水印.pdf";
			String waterFilePath = uploadpath + File.separator + "dksp" + File.separator + waterFileName;
			WaterMarkUtil.markPdf(pdfFilePath, waterFilePath, getLoginUser().getRealname()+getLoginUser().getWorkNo());

			FileInputStream fileInputStream = new FileInputStream(waterFilePath);
			byte[] bys = new byte[fileInputStream.available()];
			fileInputStream.read(bys);
			response.setContentType("application/force-download");// 设置强制下载不打开            
			response.addHeader("Content-Disposition", "attachment;fileName=" + new String(pdfFileName.getBytes("UTF-8"),"iso-8859-1"));
			outputStream = response.getOutputStream();
			outputStream.write(bys);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IoUtil.close(inputStream);
			IoUtil.close(outputStream);
			IoUtil.close(pdfOutPutStream);
		}
	}

	public List getApprovalHistory(String tableId) {
		List<HashMap<String, Object>> list = new ArrayList<>();
		List<ActBusiness> actBusinessesList = actBusinessService.findByTableId(tableId);
		String id = actBusinessesList.get(0).getProcInstId();
		List<HistoricTaskInstance> taskList = historyService.createHistoricTaskInstanceQuery()
				.processInstanceId(id).orderByHistoricTaskInstanceEndTime().asc().list();

		// 转换vo
		taskList.forEach(e -> {
			HashMap<String, Object> map = new HashMap<>();
			map.put("sprq", DateUtil.format(e.getEndTime(), "yyyy年MM月dd日"));
			// 获取实际审批用户id
			String userId = iHistoryIdentityService.findUserIdByTypeAndTaskId(ActivitiConstant.EXECUTOR_TYPE, e.getId());
			//获取用户部门
			SysUser u = userService.getById(userId);
			HrBasStaffPost hrBasStaffPost=hrBasStaffPostService.getStaffPostInfoBySprq(u.getWorkNo(),DateUtil.format(e.getEndTime(),"yyyyMMdd"));
			map.put("realname", u.getRealname());
			map.put("username", u.getUsername());
			map.put("spbm", sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", hrBasStaffPost.getZzbz()));

			// 关联审批意见
			List<Comment> comments = taskService.getTaskComments(e.getId(), "comment");
			if(comments!=null&&comments.size()>0){
				map.put("spyj", comments.get(0).getFullMessage());
			} else {
				map.put("spyj", "");
			}
			//获取部门印章图片
			QueryWrapper<SysDepartmentSign> queryWrapper=new QueryWrapper<>();
			queryWrapper.eq("depart_id",hrBasStaffPost.getZzbz());
			SysDepartmentSign sysDepartmentSign=sysDepartmentSignService.getOne(queryWrapper,false);
			//获取用户签名图片
			QueryWrapper<SysUserSignOther> sysUserSignOtherQueryWrapper = new QueryWrapper<>();
			sysUserSignOtherQueryWrapper.eq("user_id", userId);
			SysUserSignOther sysUserSignOther = sysUserSignOtherService.getOne(sysUserSignOtherQueryWrapper,false);
			if (sysUserSignOther != null && sysDepartmentSign != null) {
				String dirPath=uploadpath+File.separator+"spyhqmyz";
				String savePath=uploadpath+File.separator+"spyhqmyz"+File.separator+"userSign"+DateUtil.format(new Date(),"yyyyMMddHHmmssSSS")+".png";
				log.info("===============图片保存路径=========="+savePath);
				log.info("===============部门印章图片=========="+uploadpath+File.separator+sysDepartmentSign.getFwlj());
				log.info("===============用户签名图片=========="+uploadpath+File.separator+sysUserSignOther.getFwlj());
				ImageOverlapUtil.getTwoImageOverlap(uploadpath+File.separator+sysDepartmentSign.getFwlj(),uploadpath+File.separator+sysUserSignOther.getFwlj(),savePath,dirPath);
				map.put("image", getImageBase64String(savePath));
			} else {
				map.put("image", "");
				QueryWrapper<SysUserSign> userSignQueryWrapper = new QueryWrapper<>();
				userSignQueryWrapper.eq("user_id", userId);
				SysUserSign sysUserSign = sysUserSignService.getOne(userSignQueryWrapper);
				if (sysUserSign!=null){
					String signPath = uploadpath + File.separator + sysUserSign.getFwlj();
					map.put("image", getImageBase64String(signPath));
				}
			}
			list.add(map);
		});
		return list;
	}

	public static String getImageBase64String(String imageFile) {
		if (StringUtils.isEmpty(imageFile)) {
			return "";
		}
		File file = new File(imageFile);
		if (!file.exists()) {
			return "";
		}
		InputStream is = null;
		byte[] data = null;
		try {
			is = new FileInputStream(file);
			data = new byte[is.available()];
			is.read(data);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}
}

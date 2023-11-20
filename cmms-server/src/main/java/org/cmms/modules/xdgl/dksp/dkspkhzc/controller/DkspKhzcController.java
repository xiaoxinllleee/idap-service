package org.cmms.modules.xdgl.dksp.dkspkhzc.controller;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;
import java.net.URLDecoder;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.UUID;
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
import org.apache.ibatis.annotations.Param;
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
import org.cmms.modules.khgl.nh.entity.Nhbkbpy;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.khgl.nh.service.INhbkbpyService;
import org.cmms.modules.khgl.nh.service.IYwhywwlxxService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.pad.nhxxgl.entity.KhglYwhywwlxxPad;
import org.cmms.modules.pad.nhxxgl.entity.KhywxxDksjmxPad;
import org.cmms.modules.pad.nhxxgl.entity.NhJtcyxx;
import org.cmms.modules.pad.nhxxgl.service.IKhglYwhywwlxxPadService;
import org.cmms.modules.pad.nhxxgl.service.IKhywxxDksjmxPadService;
import org.cmms.modules.system.entity.*;
import org.cmms.modules.system.service.*;
import org.cmms.modules.tjfx.zfsjtj.qhhfsjmx.entity.Hfsjmx;
import org.cmms.modules.tjfx.zfsjtj.qhhfsjmx.service.IHfsjmxService;
import org.cmms.modules.util.*;
import org.cmms.modules.xdgl.dksp.dkspdbrxx.entity.DkspDbrxx;
import org.cmms.modules.xdgl.dksp.dkspdbrxx.service.IDkspDbrxxService;
import org.cmms.modules.xdgl.dksp.dkspdywxx.entity.DkspDywxx;
import org.cmms.modules.xdgl.dksp.dkspdywxx.service.IDkspDywxxService;
import org.cmms.modules.xdgl.dksp.dkspedsz.entity.Dkspedsz;
import org.cmms.modules.xdgl.dksp.dkspedsz.entity.DkspedszImport;
import org.cmms.modules.xdgl.dksp.dkspedsz.service.IDkspedszService;
import org.cmms.modules.xdgl.dksp.dkspkhzc.entity.DkspKhzc;
import org.cmms.modules.xdgl.dksp.dkspkhzc.entity.VDkspKhzc;
import org.cmms.modules.xdgl.dksp.dkspkhzc.entity.VDksxsp;
import org.cmms.modules.xdgl.dksp.dkspkhzc.service.IDkspKhzcService;
import org.cmms.modules.xdgl.dksp.dkspkhzc.service.IVDkspKhzcService;
import org.cmms.modules.xdgl.dksp.dkspkhzc.service.IVDksxspService;
import org.cmms.modules.xdgl.dksp.dkspkhzc.vo.DkspKhzcPage;
import org.cmms.modules.xdgl.dksp.dkspkhzl.entity.DkspKhzl;
import org.cmms.modules.xdgl.dksp.dkspkhzl.service.IDkspKhzlService;
import org.cmms.modules.xdgl.dksp.dkspsxsp.entity.VDkspSxsp;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
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
 * @Description: 贷款审批客户注册
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款审批客户注册")
@RestController
@RequestMapping("/dksp/khzc")
public class DkspKhzcController extends JeecgController<DkspKhzc, IDkspKhzcService> {
	@Autowired
	private IDkspKhzcService dkspKhzcService;
	@Autowired
	private IVDkspKhzcService vDkspKhzcService;
	@Autowired
	private IVDksxspService ivDksxspService;
	@Autowired
	private IKhywxxDksjmxPadService khywxxDksjmxPadMapper;
	@Autowired
	private IKhglYwhywwlxxPadService khglYwhywwlxxPadMapper;
	@Autowired
	private IYwhywwlxxService ywhywwlxxService;
	@Autowired
	private INhxqService nhxqService;
	@Autowired
	private IHfsjmxService hfsjmxService;
	@Autowired
	private INhbkbpyService nhbkbpyService;
	@Autowired
	private IDkspedszService dkspedszService;
	@Autowired
	private IDkspKhzlService dkspKhzlService;
	@Autowired
	private IDkspDbrxxService dkspDbrxxService;
	@Autowired
	private IDkspDywxxService dkspDywxxService;
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
	 * @param vDkspKhzc
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款审批客户注册-分页列表查询")
	@ApiOperation(value="贷款审批客户注册-分页列表查询", notes="贷款审批客户注册-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VDkspKhzc vDkspKhzc,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VDkspKhzc> queryWrapper = QueryGenerator.initQueryWrapper(vDkspKhzc, req.getParameterMap());
		Page<VDkspKhzc> page = new Page<VDkspKhzc>(pageNo, pageSize);
		IPage<VDkspKhzc> pageList = vDkspKhzcService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 信贷审批与档案管理分页列表查询
	 *
	 * @param vDksxsp
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款审批客户注册-分页列表查询")
	@ApiOperation(value="贷款审批客户注册-分页列表查询", notes="贷款审批客户注册-分页列表查询")
	@GetMapping(value = "/list2")
	public Result<?> querySxspPageList(VDksxsp vDksxsp,
									   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									   HttpServletRequest req) {
		QueryWrapper<VDksxsp> queryWrapper = QueryGenerator.initQueryWrapper(vDksxsp, req.getParameterMap());
		Page<VDksxsp> page = new Page<VDksxsp>(pageNo, pageSize);
		IPage<VDksxsp> pageList = ivDksxspService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 信贷审批与档案管理根据证件号查询信息
	 * @param zjhm
	 * @return
	 */
	@GetMapping(value = "/queryByZjhm")
	public Result<?> queryByZjhm(@Param("zjhm") String zjhm) {
		JSONObject jsonObject = new JSONObject();
		try {
			if (StringUtils.isNotEmpty(zjhm)){
				QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
				nhxqQueryWrapper.eq("zjhm", zjhm);
				nhxqQueryWrapper.orderByAsc("yhzgx");
				List<Nhxq> list = nhxqService.list(nhxqQueryWrapper);
				List<NhJtcyxx> nhJtcyxx = new ArrayList<NhJtcyxx>();
				for (int i = 0; i < list.size(); i++) {
					NhJtcyxx nhJtcyxx1 = new NhJtcyxx();
					BeanUtils.copyProperties(list.get(i), nhJtcyxx1);
					nhJtcyxx.add(nhJtcyxx1);
					List<Ywhywwlxx> ywhywwlxxes = ywhywwlxxService.selectByMainId(list.get(i).getZjhm());
					if (ywhywwlxxes != null && ywhywwlxxes.size() > 0) {
						if (nhJtcyxx.size() > 0) {
							ywhywwlxxes.get(0).setId(list.get(i).getId());
							BeanUtils.copyProperties(ywhywwlxxes.get(0), nhJtcyxx.get(i));
						}
					}
				}
				if (CollUtil.isNotEmpty(nhJtcyxx)) {
					for (NhJtcyxx jtcyxx : nhJtcyxx) {
						jtcyxx.setYhzgx(jtcyxx.getYhzgx() == null ? "" : sysDictService.queryDictTextByKey("yhzgx", jtcyxx.getYhzgx()));
						jtcyxx.setXb(jtcyxx.getXb() == null ? "" : sysDictService.queryDictTextByKey("sex", jtcyxx.getXb()));
						jtcyxx.setHyzk(jtcyxx.getHyzk() == null ? "" : sysDictService.queryDictTextByKey("hyzk", jtcyxx.getHyzk()));
					}
					jsonObject.put("nhJtcyxx", nhJtcyxx);
				}
			//评议信息
			QueryWrapper<Nhbkbpy> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("zjhm", zjhm);
			List<Nhbkbpy> pyxxList = nhbkbpyService.list(queryWrapper);
			if (CollUtil.isNotEmpty(pyxxList)){
				jsonObject.put("pyxxList", pyxxList);
			}
			//存贷款信息
			QueryWrapper<KhywxxDksjmxPad> dksjmx = new QueryWrapper<>();
			dksjmx.eq("zjhm", zjhm);
			List<KhywxxDksjmxPad> dksjmxList = khywxxDksjmxPadMapper.list(dksjmx);
			if (CollUtil.isNotEmpty(dksjmxList)){
				for (KhywxxDksjmxPad dksjmxPad : dksjmxList) {
					dksjmxPad.setSszh(dksjmxPad.getSszh() == null ? "" : sysDictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION", "zzjc", "ywjgdm", dksjmxPad.getSszh()));
				}
				jsonObject.put("dksjmxList", dksjmxList);
			}
			//存款信息
				QueryWrapper<KhglYwhywwlxxPad> ywhywwlxxPadQueryWrapper = new QueryWrapper<>();
				ywhywwlxxPadQueryWrapper.eq("zjhm", zjhm);
				List<KhglYwhywwlxxPad> ckxxList = khglYwhywwlxxPadMapper.list(ywhywwlxxPadQueryWrapper);
				if (CollUtil.isNotEmpty(ckxxList)){
					jsonObject.put("ckxxList", ckxxList);
				}
				//营销记录
				QueryWrapper<Hfsjmx> hfsjmxQueryWrapper = new QueryWrapper<>();
				hfsjmxQueryWrapper.eq("zjhm", zjhm);
				List<Hfsjmx> yxjlList = hfsjmxService.list(hfsjmxQueryWrapper);
				if (CollUtil.isNotEmpty(yxjlList)){
					for (Hfsjmx hfsjmx : yxjlList) {
						hfsjmx.setKhlx(hfsjmx.getKhlx() == null ? "" : sysDictService.queryDictTextByKey("khlx", hfsjmx.getKhlx()));
						hfsjmx.setSfyxzf(hfsjmx.getSfyxzf() == null ? "" : sysDictService.queryDictTextByKey("sfbz", hfsjmx.getSfyxzf()));
						hfsjmx.setYxdy(hfsjmx.getYxdy() == null ? "" : sysDictService.queryTableDictTextByKey("v_yxdygl_main", "wgmc_show", "id", hfsjmx.getYxdy()));
					}
					jsonObject.put("yxjlList", yxjlList);
				}

			return Result.ok(jsonObject);
			}
		}catch (Exception e){
			e.printStackTrace();
			return  Result.error(e.toString());
		}
		return Result.ok("没有数据");
	}

	/**
	 * 信贷审批与档案管理添加
	 *
	 * @param dkspKhzcPage
	 * @return
	 */
	@AutoLog(value = "贷款审批客户注册-添加")
	@ApiOperation(value="贷款审批客户注册-添加", notes="贷款审批客户注册-添加")
	@PostMapping(value = "/addAll")
	public Result<?> addAll(@RequestBody DkspKhzcPage dkspKhzcPage) {
		DkspKhzc dkspKhzc = new DkspKhzc();
		BeanUtils.copyProperties(dkspKhzcPage, dkspKhzc);
		String uuid = IdUtil.simpleUUID();
		List<DkspKhzl> fjxxList = new ArrayList<>();
		List<DkspKhzl> dkspKhzlList = dkspKhzcPage.getDkspKhzlList();
		for(DkspKhzl dkspKhzl : dkspKhzlList) {
			dkspKhzc.setId(IdUtil.simpleUUID());
			dkspKhzl.setKhid(uuid);
			dkspKhzl.setZjhm(dkspKhzc.getZjhm());
			dkspKhzl.setScr(getUsername());
			dkspKhzl.setScsj(new Date());
			fjxxList.add(dkspKhzl);
		}
		List<DkspKhzl> dkspKhzlList2 = dkspKhzcPage.getDkspSxzlList();
		for(DkspKhzl dkspKhzl : dkspKhzlList2) {
			dkspKhzc.setId(IdUtil.simpleUUID());
			dkspKhzl.setKhid(uuid);
			dkspKhzl.setZjhm(dkspKhzc.getZjhm());
			dkspKhzl.setScr(getUsername());
			dkspKhzl.setScsj(new Date());
			fjxxList.add(dkspKhzl);
		}
		dkspKhzlService.saveBatch(fjxxList);
		//根据所在机构与审批金额判断是否需要进行审批
		QueryWrapper<Dkspedsz> dkspedszQueryWrapper = new QueryWrapper<>();
		dkspedszQueryWrapper.eq("zzbz", dkspKhzc.getZzbz());
		Dkspedsz dkspedsz = dkspedszService.getOne(dkspedszQueryWrapper);
		BigDecimal dkje = dkspKhzc.getDkje();
		BigDecimal spedBegin = dkspedsz.getSpedBegin();
		BigDecimal spedEnd = dkspedsz.getSpedEnd();
		JSONObject jsonObject = new JSONObject();
		if((spedBegin == null || dkje.compareTo(spedBegin)>=0) && (spedEnd == null || dkje.compareTo(spedEnd) <= 0)) {
			String businessNumber = "loanApproval";
			String processId = actProcessService.findByProcessKeyAndLatest(businessNumber, true).getId();
			//需要审批
			jsonObject.put("process", true);
			dkspKhzc.setBusinessNumber(businessNumber);
			dkspKhzc.setProcessId(processId);
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
			String title = String.format("%s发起的贷款审批申请[客户名称：%s，贷款金额: %s，期限：%s，利率：%s]",
					getLoginUser().getRealname(), dkspKhzc.getKhmc(), dkspKhzc.getDkje().toString() + "万元", dkspKhzc.getDkqx(), dkspKhzc.getLl());
			actBusiness.setTitle(title);
			actBusiness.setUserId(getLoginUser().getId());
			actBusiness.setCreateBy(getLoginUser().getUsername());
			actBusiness.setCreateTime(new Timestamp(System.currentTimeMillis()));
			actBusinessService.save(actBusiness);
		} else {
			jsonObject.put("process", false);
		}
		dkspKhzc.setId(uuid);
		dkspKhzcService.save(dkspKhzc);
		//保存担保人信息
		List<DkspDbrxx> dbrxxList = dkspKhzcPage.getDbrxxList();
		if (dbrxxList != null) {
			for(DkspDbrxx dkspDbrxx : dbrxxList) {
				dkspDbrxx.setKhId(uuid);
			}
			dkspDbrxxService.saveBatch(dbrxxList);
		}
		//保存抵押物信息
		List<DkspDywxx> dywxxList = dkspKhzcPage.getDywxxList();
		if (dywxxList != null) {
			for(DkspDywxx dkspDywxx : dywxxList) {
				dkspDywxx.setId(IdUtil.simpleUUID());
				dkspDywxx.setKhId(uuid);
			}
			dkspDywxxService.saveBatch(dywxxList);
		}
		return Result.ok("添加成功！", jsonObject);
	}

	/**
	 * 添加
	 *
	 * @param dkspKhzcPage
	 * @return
	 */
	@AutoLog(value = "贷款审批客户注册-添加")
	@ApiOperation(value="贷款审批客户注册-添加", notes="贷款审批客户注册-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DkspKhzcPage dkspKhzcPage) {
		DkspKhzc dkspKhzc = new DkspKhzc();
		BeanUtils.copyProperties(dkspKhzcPage, dkspKhzc);
		String uuid = IdUtil.simpleUUID();
		dkspKhzc.setId(uuid);
		List<DkspKhzl> fjxxList = new ArrayList<>();
		List<DkspKhzl> dkspKhzlList = dkspKhzcPage.getDkspKhzlList();
		for(DkspKhzl dkspKhzl : dkspKhzlList) {
			dkspKhzl.setKhid(uuid);
			dkspKhzl.setZjhm(dkspKhzc.getZjhm());
			dkspKhzl.setScr(getUsername());
			dkspKhzl.setScsj(new Date());
			fjxxList.add(dkspKhzl);
		}
		dkspKhzlService.saveBatch(dkspKhzlList);
		//根据所在机构与审批金额判断是否需要进行审批
		QueryWrapper<Dkspedsz> dkspedszQueryWrapper = new QueryWrapper<>();
		dkspedszQueryWrapper.eq("zzbz", dkspKhzc.getZzbz());
		Dkspedsz dkspedsz = dkspedszService.getOne(dkspedszQueryWrapper);
		BigDecimal dkje = dkspKhzc.getDkje();
		BigDecimal spedBegin = dkspedsz.getSpedBegin();
		BigDecimal spedEnd = dkspedsz.getSpedEnd();
		JSONObject jsonObject = new JSONObject();
		if((spedBegin == null || dkje.compareTo(spedBegin)>=0) && (spedEnd == null || dkje.compareTo(spedEnd) <= 0)) {
			String businessNumber = "loanApproval";
			String processId = actProcessService.findByProcessKeyAndLatest(businessNumber, true).getId();
			//需要审批
			jsonObject.put("process", true);
			dkspKhzc.setBusinessNumber(businessNumber);
			dkspKhzc.setProcessId(processId);
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
			String title = String.format("%s发起的贷款审批申请[客户名称：%s，贷款金额: %s，期限：%s，利率：%s]",
					getLoginUser().getRealname(), dkspKhzc.getKhmc(), dkspKhzc.getDkje().toString() + "万元", dkspKhzc.getDkqx(), dkspKhzc.getLl());
			actBusiness.setTitle(title);
			actBusiness.setUserId(getLoginUser().getId());
			actBusiness.setCreateBy(getLoginUser().getUsername());
			actBusiness.setCreateTime(new Timestamp(System.currentTimeMillis()));
			actBusinessService.save(actBusiness);
		} else {
			jsonObject.put("process", false);
		}
		dkspKhzcService.save(dkspKhzc);
		//保存担保人信息
		List<DkspDbrxx> dbrxxList = dkspKhzcPage.getDbrxxList();
		if (dbrxxList != null) {
			for(DkspDbrxx dkspDbrxx : dbrxxList) {
				dkspDbrxx.setKhId(uuid);
			}
			dkspDbrxxService.saveBatch(dbrxxList);
		}
		//保存抵押物信息
		List<DkspDywxx> dywxxList = dkspKhzcPage.getDywxxList();
		if (dywxxList != null) {
			for(DkspDywxx dkspDywxx : dywxxList) {
				dkspDywxx.setKhId(uuid);
			}
			dkspDywxxService.saveBatch(dywxxList);
		}
		return Result.ok("添加成功！", jsonObject);
	}

	/**
	 * 信贷审批与档案管理编辑
	 *
	 * @param dkspKhzcPage
	 * @return
	 */
	@AutoLog(value = "贷款审批客户注册-编辑")
	@ApiOperation(value="贷款审批客户注册-编辑", notes="贷款审批客户注册-编辑")
	@PutMapping(value = "/editAll")
	public Result<?> editAll(@RequestBody DkspKhzcPage dkspKhzcPage) {
		DkspKhzc dkspKhzc = new DkspKhzc();
		BeanUtils.copyProperties(dkspKhzcPage, dkspKhzc);
		//是否删除附件
		if(dkspKhzcPage.getDeleteFiles() != null && !dkspKhzcPage.getDeleteFiles().isEmpty()) {
			dkspKhzlService.removeByIds(dkspKhzcPage.getDeleteFiles());
		}
		List<DkspKhzl> fjxxList = new ArrayList<>();
		//是否上传附件
		List<DkspKhzl> dkspKhzlList = dkspKhzcPage.getDkspKhzlList();
		for(DkspKhzl dkspKhzl : dkspKhzlList) {
			dkspKhzl.setKhid(dkspKhzc.getId());
			dkspKhzl.setZjhm(dkspKhzc.getZjhm());
			dkspKhzl.setScr(getUsername());
			dkspKhzl.setScsj(new Date());
			fjxxList.add(dkspKhzl);
		}
		List<DkspKhzl> dkspKhzlList2 = dkspKhzcPage.getDkspSxzlList();
		for(DkspKhzl dkspKhzl : dkspKhzlList2) {
			dkspKhzc.setId(IdUtil.simpleUUID());
			dkspKhzl.setZjhm(dkspKhzc.getZjhm());
			dkspKhzl.setScr(getUsername());
			dkspKhzl.setScsj(new Date());
			fjxxList.add(dkspKhzl);
		}
		dkspKhzlService.saveBatch(fjxxList);
		JSONObject jsonObject = new JSONObject();
		//判断是否已经进入审批流程
		List<ActBusiness> actBusinessList = actBusinessService.findByTableId(dkspKhzc.getId());
		if (actBusinessList.isEmpty()) {
			//根据所在机构与审批金额判断是否需要进行审批
			QueryWrapper<Dkspedsz> dkspedszQueryWrapper = new QueryWrapper<>();
			dkspedszQueryWrapper.eq("zzbz", dkspKhzc.getZzbz());
			Dkspedsz dkspedsz = dkspedszService.getOne(dkspedszQueryWrapper);
			BigDecimal dkje = dkspKhzc.getDkje();
			BigDecimal spedBegin = dkspedsz.getSpedBegin();
			BigDecimal spedEnd = dkspedsz.getSpedEnd();
			if((spedBegin == null || dkje.compareTo(spedBegin)>=0) && (spedEnd == null || dkje.compareTo(spedEnd) <= 0)) {
				String businessNumber = "loanApproval";
				String processId = actProcessService.findByProcessKeyAndLatest(businessNumber, true).getId();
				//需要审批
				jsonObject.put("process", true);
				dkspKhzc.setBusinessNumber(businessNumber);
				dkspKhzc.setProcessId(processId);
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
				actBusiness.setTableId(dkspKhzc.getId());
				actBusiness.setTitle(getLoginUser().getRealname()+"发起的贷款审批申请.");
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
		} else {
			jsonObject.put("process", false);
		}

		//担保人信息

		//抵押物信息
		dkspKhzcService.updateById(dkspKhzc);
		return Result.ok("编辑成功!", jsonObject);
	}


	/**
	 * 编辑
	 *
	 * @param dkspKhzcPage
	 * @return
	 */
	@AutoLog(value = "贷款审批客户注册-编辑")
	@ApiOperation(value="贷款审批客户注册-编辑", notes="贷款审批客户注册-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DkspKhzcPage dkspKhzcPage) {
		DkspKhzc dkspKhzc = new DkspKhzc();
		BeanUtils.copyProperties(dkspKhzcPage, dkspKhzc);
		//是否删除附件
		if(dkspKhzcPage.getDeleteFiles() != null && !dkspKhzcPage.getDeleteFiles().isEmpty()) {
			dkspKhzlService.removeByIds(dkspKhzcPage.getDeleteFiles());
		}
		//是否上传附件
		List<DkspKhzl> dkspKhzlList = dkspKhzcPage.getDkspKhzlList();
		for(DkspKhzl dkspKhzl : dkspKhzlList) {
			dkspKhzl.setKhid(dkspKhzc.getId());
			dkspKhzl.setZjhm(dkspKhzc.getZjhm());
			dkspKhzl.setScr(getUsername());
			dkspKhzl.setScsj(new Date());
		}
		dkspKhzlService.saveBatch(dkspKhzlList);
		JSONObject jsonObject = new JSONObject();
		//判断是否已经进入审批流程
		List<ActBusiness> actBusinessList = actBusinessService.findByTableId(dkspKhzc.getId());
		if (actBusinessList.isEmpty()) {
			//根据所在机构与审批金额判断是否需要进行审批
			QueryWrapper<Dkspedsz> dkspedszQueryWrapper = new QueryWrapper<>();
			dkspedszQueryWrapper.eq("zzbz", dkspKhzc.getZzbz());
			Dkspedsz dkspedsz = dkspedszService.getOne(dkspedszQueryWrapper);
			BigDecimal dkje = dkspKhzc.getDkje();
			BigDecimal spedBegin = dkspedsz.getSpedBegin();
			BigDecimal spedEnd = dkspedsz.getSpedEnd();
			if((spedBegin == null || dkje.compareTo(spedBegin)>=0) && (spedEnd == null || dkje.compareTo(spedEnd) <= 0)) {
				String businessNumber = "loanApproval";
				String processId = actProcessService.findByProcessKeyAndLatest(businessNumber, true).getId();
				//需要审批
				jsonObject.put("process", true);
				dkspKhzc.setBusinessNumber(businessNumber);
				dkspKhzc.setProcessId(processId);
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
				actBusiness.setTableId(dkspKhzc.getId());
				actBusiness.setTitle(getLoginUser().getRealname()+"发起的贷款审批申请.");
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
		} else {
			jsonObject.put("process", false);
		}

		//担保人信息

		//抵押物信息
		dkspKhzcService.updateById(dkspKhzc);
		return Result.ok("编辑成功!", jsonObject);
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款审批客户注册-通过id删除")
	@ApiOperation(value="贷款审批客户注册-通过id删除", notes="贷款审批客户注册-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkspKhzcService.removeById(id);
		//同步删除申请流程中的信息
		actBusinessService.deleteByTableId(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款审批客户注册-批量删除")
	@ApiOperation(value="贷款审批客户注册-批量删除", notes="贷款审批客户注册-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkspKhzcService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款审批客户注册-通过id查询")
	@ApiOperation(value="贷款审批客户注册-通过id查询", notes="贷款审批客户注册-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		if ("440".equals(getRedisQydm())){
			VDkspKhzc dkspKhzc = vDkspKhzcService.getById(id);
			JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(dkspKhzc));
			jsonObject.put("ygxm", sysDictService.queryTableDictTextByKey("hr_bas_staff", "ygxm", "yggh", dkspKhzc.getYggh()));
			jsonObject.put("zzbz_dictText", sysDictService.queryTableDictTextByKey("V_HR_BAS_ORGANIZATION_CMMS", "zzjc", "zzbz", dkspKhzc.getZzbz()));
			return Result.ok(jsonObject);
		}else {
			VDksxsp dkspKhzc = ivDksxspService.getById(id);
			JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(dkspKhzc));
			jsonObject.put("ygxm", sysDictService.queryTableDictTextByKey("hr_bas_staff", "ygxm", "yggh", dkspKhzc.getYggh()));
			jsonObject.put("zzbz_dictText", sysDictService.queryTableDictTextByKey("V_HR_BAS_ORGANIZATION_CMMS", "zzjc", "zzbz", dkspKhzc.getZzbz()));
			return Result.ok(jsonObject);
		}
	}

	@GetMapping(value = "/getImgPreviewPath")
	public Result<?> getImgPreviewPath(String fwlj, int page) {
		String filePath = uploadpath + File.separator + fwlj;
		int pageCount = PdfToImgUtil.getPageCount(filePath);
		String imgPath = PdfToImgUtil.pdfToImg(filePath, page);
		if (StringUtils.isEmpty(imgPath) || !FileUtil.exist(imgPath)) {
			return Result.error("获取预览图片失败");
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pageCount", pageCount);
		jsonObject.put("imgPath", FileUtil.subPath(uploadpath, imgPath));
		return Result.ok(jsonObject);
	}
  /**
   * 导出excel
   *
   * @param request
   * @param dkspKhzc
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DkspKhzc dkspKhzc) {
      return super.exportXls(request, dkspKhzc, DkspKhzc.class, "贷款审批客户注册");
  }

	 /**
	  * 通过excel导入数据
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
//      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
		 String filePaths = jsonObject.getString("filePaths");
		 if (StringUtils.isEmpty(filePaths)) {
			 return Result.error("请先上传文件！");
		 }
		 String[] filePathList = filePaths.split(",");
		 JSONObject obj = new JSONObject();
		 for (String filePath : filePathList) {
			 String baseFilePath = uploadpath + File.separator + filePath;
//          MultipartFile file = entity.getValue();// 获取上传文件对象
			 File file = new File(baseFilePath);
			 ImportParams params = new ImportParams();
			 params.setTitleRows(1);
			 params.setHeadRows(1);
			 params.setNeedSave(false);
			 FileOutputStream fos = null;
			 try {
//              List<Khdjpd> listKhdjpds = ExcelImportUtil.importExcel(file.getInputStream(), Khdjpd.class, params);
				 ExcelImportResult<Dkspedsz> importResult = ExcelImportUtil.importExcelVerify(file, Dkspedsz.class, params);
				 List<Dkspedsz> list = importResult.getList();
				 dkspedszService.saveBatch(list);
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
	  * 通过excel导入数据
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/download")
	 public void download(DkspKhzc dkspKhzc, HttpServletRequest request, HttpServletResponse response) {
		 InputStream inputStream = null;
		 OutputStream outputStream = null;
		 OutputStream pdfOutPutStream = null;
		 try {
			 Map<String, Object> data = new HashMap<>();
			 SysDic sysDic = sysDicService.queryByCode("101002");
			 dkspKhzc.setDkzl(sysDictService.queryDictTextByKey("dkzl", dkspKhzc.getDkzl()));
			 dkspKhzc.setXydj(sysDictService.queryDictTextByKey("rate_xydj", dkspKhzc.getXydj()));
			 dkspKhzc.setJkfs(sysDictService.queryDictTextByKey("dbfs", dkspKhzc.getJkfs()));
			 String dkqx = sysDictService.queryDictTextByKey("khgl_dkqx", dkspKhzc.getDkqx());
			 if (StringUtils.isNotEmpty(dkqx)) {
			 	dkspKhzc.setDkqx(dkqx);
			 }
			 if(StringUtils.isEmpty(dkspKhzc.getDz())) {
				 dkspKhzc.setDz("");
			 }
			 data.put("khzc", dkspKhzc);
			 data.put("bankName", sysDic.getValue());
			 data.put("khlx", sysDictService.queryDictTextByKey("lldj_khlx", dkspKhzc.getKhlx()));
			 data.put("zzjc", sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", dkspKhzc.getZzbz()));
			 data.put("date", DateUtil.formatDateTime("yyyy年MM月dd日"));
			 data.put("dkjedx", Convert.digitToChinese(dkspKhzc.getDkje().multiply(new BigDecimal(10000))));
			 //获取担保人信息
			 String khId = dkspKhzc.getId();
			 QueryWrapper<DkspDbrxx> dbrxxQueryWrapper = new QueryWrapper<>();
			 dbrxxQueryWrapper.eq("kh_id", khId);
			 List<DkspDbrxx> dbrxxList = dkspDbrxxService.list(dbrxxQueryWrapper);
			 List<String> dbrmcList = dbrxxList.stream().map(DkspDbrxx::getDbrmc).collect(Collectors.toList());
			 //获取抵押物信息
			 QueryWrapper<DkspDywxx> dywxxQueryWrapper = new QueryWrapper<>();
			 dywxxQueryWrapper.eq("kh_id", khId);
			 List<DkspDywxx> dywxxList = dkspDywxxService.list(dywxxQueryWrapper);
			 List<String> dywmcList = dywxxList.stream().map(DkspDywxx::getDywmc).collect(Collectors.toList());
			 List<String> dywjzList = dywxxList.stream().map(DkspDywxx::getDywjz).collect(Collectors.toList());

			 data.put("dbr", StringUtils.join(dbrmcList, ","));
			 data.put("dyw", StringUtils.join(dywmcList, ","));
			 data.put("dywjz", StringUtils.join(dywjzList, ","));

			 //查询审批历史
			 data.put("spList", getApprovalHistory(dkspKhzc.getId()));

			 outputStream = response.getOutputStream();
			 String docFileName = IdUtil.simpleUUID() + "信贷审批意见表.docx";
			 String docFilePath = uploadpath + File.separator + "dksp" + File.separator + docFileName;
			 FileUtil.mkParentDirs(docFilePath);
			 String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001");
			 String resourceName = "信贷审批意见表.ftl";
//			 if("390".equals(qydm)) {
//				 resourceName = "信贷审批意见表_文字.ftl";
//			 }
			 WordUtils.generateWord(data, docFilePath, resourceName);

			 inputStream = new FileInputStream(docFilePath);

			 String pdfFileName = IdUtil.simpleUUID() + "信贷审批意见表.pdf";
			 String pdfFilePath = uploadpath + File.separator + "dksp" + File.separator + pdfFileName;

			 pdfOutPutStream = new FileOutputStream(pdfFilePath);
			 //转换成pdf文件
			 IConverter converter = LocalConverter.builder().build();
			 converter.convert(inputStream).as(DocumentType.DOCX).to(pdfOutPutStream).as(DocumentType.PDF).execute();
			 String waterFileName = IdUtil.simpleUUID() + "信贷审批意见表_水印.pdf";
			 String waterFilePath = uploadpath + File.separator + "dksp" + File.separator + waterFileName;
			 WaterMarkUtil.markPdf(pdfFilePath, waterFilePath, getLoginUser().getRealname()+getLoginUser().getWorkNo());

			 FileInputStream fileInputStream = new FileInputStream(waterFilePath);
			 byte[] bys = new byte[fileInputStream.available()];
			 fileInputStream.read(bys);
			 response.setContentType("application/force-download");// 设置强制下载不打开            
			 response.addHeader("Content-Disposition", "attachment;fileName=" + new String(pdfFileName.getBytes("UTF-8"),"iso-8859-1"));
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
//		 DocxUtil.mkdirCatalog(uploadpath + "/xdspyjb/");
//		 String uploadPath = uploadpath + "/xdspyjb/" + dkspKhzc.getId() + "信贷审批意见表.docx";
//		 DocxUtil2.exportX2Doc(dkspKhzc, FileUtil.getTempFilePath("信贷审批意见表.docx") , uploadPath);
//	 	return Result.ok();
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
			map.put("username", u.getUsername());
			map.put("realname", u.getRealname());
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
				log.info("===============部门印章图片=========="+uploadpath+File.separator+sysUserSignOther.getFwlj());
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

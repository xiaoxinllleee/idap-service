package org.cmms.modules.performance.depositcustomer.ckkhghyj.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.activiti.entity.ActBusiness;
import org.cmms.modules.activiti.service.ActBusinessService;
import org.cmms.modules.performance.depositcustomer.ckkhghyj.entity.Ckkhghyjxx;
import org.cmms.modules.performance.depositcustomer.ckkhghyj.service.ICkkhghyjxxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.performance.depositcustomer.ckkhspxx.entity.Ckkhspxx;
import org.cmms.modules.performance.depositcustomer.ckkhspxx.service.ICkkhspxxService;
import org.cmms.modules.system.entity.SysUser;
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
 * @Description: 存款客户管户移交
 * @Author: Penghr
 * @Date:   2021-03-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款客户管户移交")
@RestController
@RequestMapping("/performance/ckkhghyj")
public class CkkhghyjxxController extends JeecgController<Ckkhghyjxx, ICkkhghyjxxService> {

	@Autowired
	private ICkkhghyjxxService ckkhghyjxxService;
	@Autowired
	private ICkkhspxxService iCkkhspxxService;
	@Autowired
	private ActBusinessService actBusinessService;
	@Autowired
	private ISysUserService iSysUserService;

	/**
	 * 分页列表查询
	 *
	 * @param ckkhghyjxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存款客户管户移交-分页列表查询")
	@ApiOperation(value="存款客户管户移交-分页列表查询", notes="存款客户管户移交-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ckkhghyjxx ckkhghyjxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		ckkhghyjxx.setGhr(loginUser.getWorkNo()==null?"":loginUser.getWorkNo());
		// 管户类型(1 拓展 2 管户 3 包收 4 审批 5 调查 6 安装)
		ckkhghyjxx.setGhlx("2");
		QueryWrapper<Ckkhghyjxx> queryWrapper = QueryGenerator.initQueryWrapper(ckkhghyjxx, req.getParameterMap());
		Page<Ckkhghyjxx> page = new Page<Ckkhghyjxx>(pageNo, pageSize);
		IPage<Ckkhghyjxx> pageList = ckkhghyjxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 待移交客户信息-分页列表查询
	 * @param ckkhghyjxx
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@AutoLog(value = "待移交客户信息-分页列表查询")
	@ApiOperation(value = "待移交客户信息-分页列表查询", notes = "待移交客户信息-分页列表查询")
	@GetMapping(value = "/transferList")
	public Result<?> transferPageList(Ckkhghyjxx ckkhghyjxx,
									  @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
									  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
									  HttpServletRequest request) {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		ckkhghyjxx.setGhr(getWorkNo());
		QueryWrapper<Ckkhghyjxx> queryWrapper = QueryGenerator.initQueryWrapper(ckkhghyjxx, request.getParameterMap());
		queryWrapper.orderByAsc("jgdm");
		Page<Ckkhghyjxx> page = new Page<Ckkhghyjxx>(pageNo, pageSize);
		IPage<Ckkhghyjxx> pageList = ckkhghyjxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 存款客户管户移交-所选机构可移交客户信息
	 * @param ckkhghyjxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存款客户管户移交-所选机构可移交客户信息")
	@ApiOperation(value="存款客户管户移交-所选机构可移交客户信息", notes="存款客户管户移交-所选机构可移交客户信息")
	@GetMapping(value = "/transferableCustomers")
	public Result<?> transferableCustomers(Ckkhghyjxx ckkhghyjxx,
										   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
										   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
										   HttpServletRequest req) {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		ckkhghyjxx.setGhr(loginUser.getWorkNo());
		// 管户类型(1 拓展 2 管户 3 包收 4 审批 5 调查 6 安装)
		ckkhghyjxx.setGhlx("2");
		QueryWrapper<Ckkhghyjxx> queryWrapper = QueryGenerator.initQueryWrapper(ckkhghyjxx, req.getParameterMap());
		Page<Ckkhghyjxx> page = new Page<Ckkhghyjxx>(pageNo, pageSize);
		IPage<Ckkhghyjxx> pageList = ckkhghyjxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 存款客户管户移交
	 * @param jsonObject
	 * @return
	 */
	@AutoLog(value = "管户移交")
	@ApiOperation(value="管户移交", notes="管户移交")
	@PostMapping(value = "/transfer")
	public Result<?> handleTransfer(@RequestBody JSONObject jsonObject) {
		String uuid = IdUtil.simpleUUID();
		String processId = ckkhghyjxxService.getProcessIdByProcessKey("ckkhghyj");
		// 接收人
		String receiver = jsonObject.getString("yggh");
		// 申请说明
		String remark   = jsonObject.getString("remark");
		// 移交日期
		String transferDateStr = jsonObject.getString("transferDate");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 移交日期
		Date transferDate = null;
		try {
			transferDate = simpleDateFormat.parse(transferDateStr);
		} catch (ParseException parseException) {
			parseException.printStackTrace();
		}
		// 移交类型
		String yjfs = jsonObject.getString("yjfs");
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		SysUser sysUser = iSysUserService.getUserByName(loginUser.getUsername());
		List<Ckkhghyjxx> ckkhghyjxxList = new ArrayList<>();
		try {
			if ("1".equals(yjfs)) {
				//批量移交
				//查询出移交清单
				String khlx = jsonObject.getString("khlx");
				QueryWrapper<Ckkhghyjxx> queryWrapper = new QueryWrapper<>();
				queryWrapper.eq("ghr", getWorkNo());
				if (StringUtils.isNotEmpty(khlx)) {
					String[] khlxList = khlx.split(",");
					queryWrapper.in("khlx", khlxList);
				}
				ckkhghyjxxList = ckkhghyjxxService.list(queryWrapper);

			} else if ("2".equals(yjfs)) {
				//选择移交
				//获取选择的列表
				JSONArray jsonArray = jsonObject.getJSONArray("ids");
				if (jsonArray == null || jsonArray.isEmpty()) {
					return Result.error("未选择移交的客户信息！");
				}
				List<String> ids = jsonArray.toJavaList(String.class);

				//根据id获取所有的客户清单
				ckkhghyjxxList = ckkhghyjxxService.getListByIds(ids);
			}
			List<Ckkhspxx> ckkhspxxList = new ArrayList<>();
			for (int i = 0; i < ckkhghyjxxList.size(); i++) {
				Ckkhghyjxx form = ckkhghyjxxList.get(i);
				// Step1. 审批信息
				Ckkhspxx spxx = new Ckkhspxx();
				spxx.setId(form.getId());
				spxx.setBusinessNumber("ckkhghyj");
				spxx.setTableId(uuid);
				spxx.setProcessId(processId);
				// 流程状态(0 未提交 1 处理中 2 已结束)
				spxx.setProcessStatus("0");
				spxx.setJgdm(form.getJgdm());
				spxx.setKhbh(form.getKhbh());
				spxx.setKhmc(form.getKhmc());
				spxx.setZjlx(form.getZjlx());
				spxx.setZjhm(form.getZjhm());
				spxx.setKhlx(form.getKhlx());
				spxx.setYxlx(form.getYxlx());
				spxx.setCpxx(form.getCpxx());
				spxx.setZzkhrq(form.getZzkhrq());
				// 业务类型(1 认领 2 移交)
				spxx.setYwlx("2");
				spxx.setYghr(sysUser.getWorkNo());
				spxx.setYghbl(form.getGhbl());
				spxx.setGhr(receiver);
				spxx.setGhbl(form.getGhbl());
				spxx.setYjrq(transferDate);
				spxx.setSqsm(remark);
				// 录入标识(0 导入 1 录入 2 修改)
				spxx.setLrbz("1");
				ckkhspxxList.add(spxx);
			}
			iCkkhspxxService.saveBatch(ckkhspxxList);
			// Step2. 申请流程
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
			actBusiness.setTitle(sysUser.getRealname()+"发起的存款客户管户移交申请.");
			actBusiness.setUserId(sysUser.getId());
			actBusiness.setCreateBy(sysUser.getUsername());
			actBusiness.setCreateTime(new Timestamp(System.currentTimeMillis()));
			actBusinessService.save(actBusiness);
		} catch (Exception exception) {
			log.error("移交失败！", exception);
			return Result.error("移交失败！请联系管理员处理！");
		}

		return Result.ok("移交成功，进入待审批状态，请到\"我的申请\"提交审批申请！");
	}

	/**
	 * 添加
	 *
	 * @param ckkhghyjxx
	 * @return
	 */
	@AutoLog(value = "存款客户管户移交-添加")
	@ApiOperation(value="存款客户管户移交-添加", notes="存款客户管户移交-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ckkhghyjxx ckkhghyjxx) {
		ckkhghyjxxService.save(ckkhghyjxx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ckkhghyjxx
	 * @return
	 */
	@AutoLog(value = "存款客户管户移交-编辑")
	@ApiOperation(value="存款客户管户移交-编辑", notes="存款客户管户移交-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ckkhghyjxx ckkhghyjxx) {
		ckkhghyjxxService.updateById(ckkhghyjxx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款客户管户移交-通过id删除")
	@ApiOperation(value="存款客户管户移交-通过id删除", notes="存款客户管户移交-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ckkhghyjxxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "存款客户管户移交-批量删除")
	@ApiOperation(value="存款客户管户移交-批量删除", notes="存款客户管户移交-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckkhghyjxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款客户管户移交-通过id查询")
	@ApiOperation(value="存款客户管户移交-通过id查询", notes="存款客户管户移交-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ckkhghyjxx ckkhghyjxx = ckkhghyjxxService.getById(id);
		return Result.ok(ckkhghyjxx);
	}

    /**
     * 导出excel
     *
     * @param request
     * @param ckkhghyjxx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Ckkhghyjxx ckkhghyjxx) {
        return super.exportXls(request, ckkhghyjxx, Ckkhghyjxx.class, "存款客户管户移交");
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
        return super.importExcel(request, response, Ckkhghyjxx.class);
    }

}

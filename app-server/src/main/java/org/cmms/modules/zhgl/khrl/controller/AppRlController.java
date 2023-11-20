package org.cmms.modules.zhgl.khrl.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import org.checkerframework.checker.units.qual.A;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.activiti.entity.ActBusiness;
import org.cmms.modules.activiti.service.ActBusinessService;
import org.cmms.modules.activiti.service.ActProcessService;
import org.cmms.modules.khgl.dkkh.entity.KhgxglDkkhghlsb;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.zhgl.khrl.entity.*;
import org.cmms.modules.zhgl.khrl.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
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
 * @Description: 客户认领
 * @Author: jeecg-boot
 * @Date:   2022-03-21
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户认领")
@RestController
@RequestMapping("/khrl/appRl")
public class AppRlController extends JeecgController<AppRl, IAppRlService> {
	@Autowired
	private IAppRlService appRlService;
	@Autowired
	private ActProcessService actProcessService;
	@Autowired
	private ActBusinessService actBusinessService;
	@Autowired
	private IKhgxglSjyhkhxxService khgxglSjyhkhxxService;
	@Autowired
	private ISysDictService dictService;
	@Autowired
	private IKhgxglSjyhkhghlsbService khgxglSjyhkhghlsbService;
	@Autowired
	private IKhgxglEtckhghlsbService khgxglEtckhghlsbService;
	@Autowired
	IHrBasOrganizationService hrBasOrganizationService;
	@Autowired
	IAppRlDspService appRlDspService;
	@Autowired
	IKhgxglEtckhxxService khgxglEtckhxxService;
	@Autowired
	ICcdCustrService ccdCustrService;
	private final String SJYH_BNUMBER="sjyhrl";

	 /**
	 * 分页列表查询
	 *
	 * @param appRl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户认领-分页列表查询")
	@ApiOperation(value="客户认领-分页列表查询", notes="客户认领-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(AppRl appRl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AppRl> queryWrapper = QueryGenerator.initQueryWrapper(appRl, req.getParameterMap());
		Page<AppRl> page = new Page<AppRl>(pageNo, pageSize);
		IPage<AppRl> pageList = appRlService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *  1,2,3 手机银行 ETC 信用卡
	 *	先加入认领表   循环营销人进去各自的管户历史表   进入流程
	 */
	@AutoLog(value = "客户认领-添加")
	@ApiOperation(value="客户认领-添加", notes="客户认领-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AppRlDTO appRlDTO) {
		String[] split = appRlDTO.getGhrs().split(",");
		String[] split2 = appRlDTO.getZbs().split(",");
		AppRl appRl = new AppRl();
		BeanUtil.copyProperties(appRlDTO,appRl);
		String id = IdUtil.fastSimpleUUID();
		appRl.setLrr(getUsername());
		appRl.setId(id);
		service.save(appRl);

		for (int i = 0; i < split.length; i++) {
			Date date = DateUtil.parseDateFormat(appRlDTO.getKssj(),"yyyy-MM-dd");
			AppRlDsp appRlDsp = new AppRlDsp();
			appRlDsp.setRlId(id);
			appRlDsp.setKssj(date);
			appRlDsp.setYggh(split[i]);
			appRlDsp.setGhbl(Integer.parseInt(split2[i]));
			appRlDsp.setBz(appRlDTO.getBz());
			appRlDspService.save(appRlDsp);
		}

		String typeVal="";

		if ("0".equals(appRlDTO.getType())){
			typeVal = "手机银行";
		}else if ("1".equals(appRlDTO.getType())){
			typeVal = "ETC";
		}else if ("2".equals(appRlDTO.getType())){
			typeVal = "信用卡";
		}

			String processId = actProcessService.findByProcessKeyAndLatest(SJYH_BNUMBER, true).getId();

			ActBusiness actBusiness = new ActBusiness();
			actBusiness.setDelFlag(0);
			actBusiness.setApplyTime(new Timestamp(System.currentTimeMillis()));
			actBusiness.setProcDefId(processId);
			// 结果状态(0 默认未提交 1 处理中 2 通过 3 驳回)
			actBusiness.setResult(0);
			// 处理状态(0 默认草稿 1 处理中 2 结束)
			actBusiness.setStatus(0);
			actBusiness.setTableId(id);
			StringBuffer stringBuffer  = new StringBuffer();
			stringBuffer.append(getLoginUser().getRealname());
			stringBuffer.append(" 发起了").append(typeVal);
			stringBuffer.append("认领申请[");
			stringBuffer.append(appRlDTO.getKhmc()).append("-").append(appRlDTO.getZjhm());
			stringBuffer.append("]");
			actBusiness.setTitle(stringBuffer.toString());
			actBusiness.setUserId(getLoginUser().getId());
			actBusiness.setCreateBy(getUsername());
			actBusiness.setCreateTime(new Timestamp(System.currentTimeMillis()));
			actBusinessService.save(actBusiness);
			return Result.ok("添加成功！");

		}


	 @GetMapping(value = "/listAll")
	 public Result<?> listAll(String khmc,String type) {
		 LoginUser loginUser = getLoginUser();
		 String orgCode = loginUser.getOrgCode();
		 HrBasOrganization byId = hrBasOrganizationService.getById(orgCode);
		 if (byId != null){
			 if ("0".equals(type)){
				 List<KhgxglSjyhkhxx> listBykhmc = khgxglSjyhkhxxService.getListBykhmc(khmc, byId.getYwjgdm());
				 return Result.ok(listBykhmc);
			 }else if ("1".equals(type)){
				 List<KhgxglEtckhxx> etcListByKhmc = khgxglEtckhxxService.getEtcListByKhmc(khmc, byId.getYwjgdm());
				 return Result.ok(etcListByKhmc);
			 }else if ("2".equals(type)){
				 List<CcdCustrVO> xykListByKhmc = ccdCustrService.getXykListByKhmc(khmc, byId.getYwjgdm());
				 return Result.ok(xykListByKhmc);
			 }
		 }
		 return Result.ok();
	 }

	/**
	 * 编辑
	 *
	 * @param appRl
	 * @return
	 */
	@AutoLog(value = "客户认领-编辑")
	@ApiOperation(value="客户认领-编辑", notes="客户认领-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AppRl appRl) {
		appRlService.updateById(appRl);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户认领-通过id删除")
	@ApiOperation(value="客户认领-通过id删除", notes="客户认领-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		appRlService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户认领-批量删除")
	@ApiOperation(value="客户认领-批量删除", notes="客户认领-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.appRlService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户认领-通过id查询")
	@ApiOperation(value="客户认领-通过id查询", notes="客户认领-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AppRl appRl = appRlService.getById(id);
		return Result.ok(appRl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param appRl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, AppRl appRl) {
      return super.exportXls(request, appRl, AppRl.class, "客户认领");
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
      return super.importExcel(request, response, AppRl.class);
  }

}

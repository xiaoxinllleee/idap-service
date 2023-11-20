package org.cmms.modules.ywgl.nxt.shpj.glzhxx.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.sjxf.hxxt.ckzdkb.entity.Ckzdkb;
import org.cmms.modules.sjxf.hxxt.ckzdkb.service.ICkzdkbService;
import org.cmms.modules.sjxf.hxxt.jjk.kzhglgx.entity.Kzhglgx;
import org.cmms.modules.sjxf.hxxt.jjk.kzhglgx.service.IKzhglgxService;
import org.cmms.modules.sjxf.hxxt.xjztdzb.entity.Xjztdzb;
import org.cmms.modules.sjxf.hxxt.xjztdzb.service.IXjztdzbService;
import org.cmms.modules.ywgl.nxt.shpj.glzhxx.entity.Glzhxx;
import org.cmms.modules.ywgl.nxt.shpj.glzhxx.entity.GlzhxxImportVo;
import org.cmms.modules.ywgl.nxt.shpj.glzhxx.service.IGlzhxxService;
import org.cmms.modules.ywgl.nxt.shpj.glzhxx.verify.ShZhglxxImportVerify;
import org.cmms.modules.ywgl.nxt.shpj.shjbxx.entity.Shjbxx;
import org.cmms.modules.ywgl.nxt.shpj.shjbxx.entity.ShjbxxImportVo;
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
 * @Description: 关联账号信息
 * @Author: jeecg-boot
 * @Date:   2021-09-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="关联账号信息")
@RestController
@RequestMapping("/glzhxx/glzhxx")
public class GlzhxxController extends JeecgController<Glzhxx, IGlzhxxService> {
	 @Autowired
	 private IGlzhxxService glzhxxService;

	 @Autowired
	 private IXjztdzbService xjztdzbService;

	 @Autowired
	 private ICkzdkbService ckzdkbService;

	 @Autowired
	 private IKzhglgxService kzhglgxService;


	 @Autowired
	 private ShZhglxxImportVerify shZhglxxImportVerify;
	/**
	 * 分页列表查询
	 *
	 * @param glzhxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "关联账号信息-分页列表查询")
	@ApiOperation(value="关联账号信息-分页列表查询", notes="关联账号信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Glzhxx glzhxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Glzhxx> queryWrapper = QueryGenerator.initQueryWrapper(glzhxx, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IGlzhxxService.class,glzhxxService,pageNo,pageSize,queryWrapper,"jgdm","shbm","ckzh");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param glzhxx
	 * @return
	 */
	@AutoLog(value = "关联账号信息-添加")
	@ApiOperation(value="关联账号信息-添加", notes="关联账号信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Glzhxx glzhxx,
						 @RequestParam(value = "oldckzh", required = false) String oldckzh) {
		String zhmc = "";
		QueryWrapper<Glzhxx> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("shbm", glzhxx.getShbm());
		queryWrapper.eq("ckzh", glzhxx.getCkzh());
		Glzhxx glzhxx1 = glzhxxService.getOne(queryWrapper);
		if (glzhxx1 != null) {
			return Result.error("数据已经存在！");
		}
		String dkzh = "";
		QueryWrapper<Xjztdzb> queryWrapper1 = new QueryWrapper<>();
		if (glzhxx.getCkzh().length() == 17) {
			Ckzdkb ckzdkb = new Ckzdkb();
			//ckzdkb.getSubAcctNo().equals(glzhxx.getCkzh());
			QueryWrapper<Ckzdkb> queryWrapper3 = new QueryWrapper<>();
			queryWrapper3.eq("sub_acct_no", glzhxx.getCkzh());
			Ckzdkb ckzh = ckzdkbService.getOne(queryWrapper3);
			if (ckzh != null) {
				if (ckzh.getAcctGrp().equals("P") && ckzh.getAcctDesc().equals("S")) {
					glzhxx.setZhlx(1);
				} else if (ckzh.getAcctGrp().equals("C") && ckzh.getAcctDesc().equals("S")) {
					glzhxx.setZhlx(2);
				} else {
					return Result.error("存款账号/卡号[" + glzhxx.getCkzh() + "]必须是对公活期或对私活期账号/卡号！");
				}
				zhmc = ckzh.getCustName();
			} else {
				return Result.error("存款账号信息不存在！");
			}
			glzhxx.setNewCkzh(ckzh.getSubAcctNo());
		} else if (glzhxx.getCkzh().length() == 19) {
			dkzh = "0" + glzhxx.getCkzh();
			Kzhglgx kzhglgx = new Kzhglgx();
			kzhglgx.getCard().equals(dkzh);
			kzhglgx.getIsoType().equals("1");
			kzhglgx.getIsPrimary().equals("Y");
			QueryWrapper<Kzhglgx> queryWrapper4 = new QueryWrapper<>();
			Kzhglgx card = kzhglgxService.getOne(queryWrapper4);
			if (card == null) {
				return Result.error("存款账号信息不存在！");
			}
			Ckzdkb ckzdkb = new Ckzdkb();
			//ckzdkb.getSubAcctNo().equals(kzhglgx.getAccount());
			QueryWrapper<Ckzdkb> queryWrapper5 = new QueryWrapper<>();
			queryWrapper5.eq("sub_acct_no", kzhglgx.getAccount());
			Ckzdkb ckzh = ckzdkbService.getOne(queryWrapper5);
			if (ckzh != null) {
				if (ckzh.getAcctGrp().equals("P") && ckzh.getAcctDesc().equals("S")) {
					glzhxx.setZhlx(1);
				} else if (ckzh.getAcctGrp().equals("C") && ckzh.getAcctDesc().equals("S")) {
					glzhxx.setZhlx(2);
				} else {
					return Result.error("存款账号/卡号[" + glzhxx.getCkzh() + "]必须是对公活期或对私活期账号/卡号！");
				}
				zhmc = ckzh.getCustName();
			} else {
				return Result.error("存款账号信息不存在！");
			}
			glzhxx.setNewCkzh(ckzh.getSubAcctNo());
		} else if (glzhxx.getCkzh().length() == 20) {
			dkzh = "43" + glzhxx.getCkzh();
			queryWrapper1.eq("extn_ref_no", dkzh);
			Xjztdzb xjztdzb = xjztdzbService.getOne(queryWrapper1);
			if (xjztdzb == null) {
				return Result.error("存款账号信息不存在！");
			}
			Ckzdkb ckzdkb = new Ckzdkb();
			//ckzdkb.getAccNo().equals(xjztdzb.getIntnRefNo());
			QueryWrapper<Ckzdkb> queryWrapper2 = new QueryWrapper<>();
			queryWrapper2.eq("sub_acct_no", xjztdzb.getIntnRefNo());
			Ckzdkb ckzh = ckzdkbService.getOne(queryWrapper2);
			if (ckzh != null) {
				if (ckzh.getAcctGrp().equals("P") && ckzh.getAcctDesc().equals("S")) {
					glzhxx.setZhlx(1);
				} else if (ckzh.getAcctGrp().equals("C") && ckzh.getAcctDesc().equals("S")) {
					glzhxx.setZhlx(2);
				} else {
					return Result.error("存款账号/卡号[" + glzhxx.getCkzh() + "]必须是对公活期或对私活期账号/卡号！");
				}
				zhmc = ckzh.getCustName();
			} else {
				return Result.error("存款账号信息不存在！");
			}
			glzhxx.setNewCkzh(ckzh.getSubAcctNo());
		} else {
			return Result.error("存款账号有误，请核实！");
		}
		if (StringUtils.isEmpty(zhmc)) {
			return Result.error("核心系统户名为空！");
		}
		if (!zhmc.equalsIgnoreCase(glzhxx.getZhmc())) {
			return Result.error("账号名称[" + glzhxx.getZhmc() + "]与核心系统的账号名称[" + zhmc + "]不一致，请核实！");
		}
		glzhxx.setGlrq(new Date());
		glzhxx.setLrr(getLoginUser().getRealname());
		glzhxx.setLrrq(new Date());
		glzhxx.setLrbz(1);
		glzhxxService.save(glzhxx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param glzhxx
	 * @return
	 */
	@AutoLog(value = "关联账号信息-编辑")
	@ApiOperation(value="关联账号信息-编辑", notes="关联账号信息-编辑")
	@RequestMapping(value = "/edit")
	public Result<?> edit(@RequestParam(value ="oldckzh",required = false) String oldckzh,@RequestBody Glzhxx glzhxx) {

		QueryWrapper<Glzhxx> glzhxxQueryWrapper = new QueryWrapper<>();
		glzhxxQueryWrapper.eq("shbm",glzhxx.getShbm());
		glzhxxQueryWrapper.eq("ckzh",glzhxx.getCkzh());
		Glzhxx one = glzhxxService.getOne(glzhxxQueryWrapper);
		if (one == null){
			return Result.error("该信息不存在！");
		}

		String zhmc = "";
		String  dkzh="";
		QueryWrapper<Xjztdzb> queryWrapper1 = new QueryWrapper<>();
		if (glzhxx.getCkzh().length()==20){
			dkzh = "43"+glzhxx.getCkzh();
			Xjztdzb xjztdzb = xjztdzbService.getOne(queryWrapper1);
			if (xjztdzb == null){
				return Result.error("存款账号信息不存在！");
			}
			Ckzdkb ckzdkb = new Ckzdkb();
			QueryWrapper<Ckzdkb> queryWrapper2 = new QueryWrapper<>();
			queryWrapper2.eq("sub_acct_no",xjztdzb.getIntnRefNo());
			Ckzdkb ckzh = ckzdkbService.getOne(queryWrapper2);
			if (ckzh != null){
				if (ckzh.getAcctGrp().equals("P") && ckzh.getAcctDesc().equals("S")){
					glzhxx.setZhlx(1);
				}else if (ckzh.getAcctGrp().equals("C") && ckzh.getAcctDesc().equals("S")){
					glzhxx.setZhlx(2);
				}else {
					return Result.error("存款账号/卡号[" + glzhxx.getCkzh() + "]必须是对公活期或对私活期账号/卡号！");
				}
				zhmc = ckzh.getCustName();
			}else {
				return Result.error("存款账号信息不存在！");
			}
			glzhxx.setNewCkzh(ckzh.getSubAcctNo());
		}else if(glzhxx.getCkzh().length()==17){
			Ckzdkb ckzdkb = new Ckzdkb();
			QueryWrapper<Ckzdkb> queryWrapper3 = new QueryWrapper<>();
			queryWrapper3.eq("sub_acct_no",glzhxx.getCkzh());
			Ckzdkb ckzh = ckzdkbService.getOne(queryWrapper3);
			if (ckzh != null){
				if (ckzh.getAcctGrp().equals("P") && ckzh.getAcctDesc().equals("S")){
					glzhxx.setZhlx(1);
				}else if (ckzh.getAcctGrp().equals("C") && ckzh.getAcctDesc().equals("S")){
					glzhxx.setZhlx(2);
				}else {
					return Result.error("存款账号/卡号[" + glzhxx.getCkzh() + "]必须是对公活期或对私活期账号/卡号！");
				}
				zhmc = ckzh.getCustName();
			}else {
				return Result.error("存款账号信息不存在！");
			}
			glzhxx.setNewCkzh(ckzh.getSubAcctNo());
		}else if(glzhxx.getCkzh().length()==19){
			dkzh = "0"+glzhxx.getCkzh();
			Kzhglgx kzhglgx = new Kzhglgx();
			kzhglgx.getCard().equals(dkzh);
			kzhglgx.getIsoType().equals("1");
			kzhglgx.getIsPrimary().equals("Y");
			QueryWrapper<Kzhglgx> queryWrapper4 = new QueryWrapper<>();
			Kzhglgx card = kzhglgxService.getOne(queryWrapper4);
			if (card == null){
				return Result.error("存款账号信息不存在！");
			}
			Ckzdkb ckzdkb = new Ckzdkb();
			QueryWrapper<Ckzdkb> queryWrapper5 = new QueryWrapper<>();
			queryWrapper5.eq("sub_acct_no",kzhglgx.getAccount());
			Ckzdkb ckzh = ckzdkbService.getOne(queryWrapper5);
			if (ckzh != null){
				if (ckzh.getAcctGrp().equals("P") && ckzh.getAcctDesc().equals("S")){
					glzhxx.setZhlx(1);
				}else if (ckzh.getAcctGrp().equals("C") && ckzh.getAcctDesc().equals("S")){
					glzhxx.setZhlx(2);
				}else {
					return Result.error("存款账号/卡号[" + glzhxx.getCkzh() + "]必须是对公活期或对私活期账号/卡号！");
				}
				zhmc = ckzh.getCustName();
			}else {
				return Result.error("存款账号信息不存在！");
			}
			glzhxx.setNewCkzh(ckzh.getSubAcctNo());
		}else{
			return Result.error("存款账号有误，请核实！");
		}
		if (StringUtils.isEmpty(zhmc)){
			return Result.error("核心系统户名为空！");
		}
		if (!zhmc.equalsIgnoreCase(glzhxx.getZhmc())){
			return Result.error("账号名称[" + glzhxx.getZhmc() + "]与核心系统的账号名称[" + zhmc + "]不一致，请核实！");
		}
		QueryWrapper<Glzhxx> queryWrapper =new QueryWrapper<>();
		queryWrapper.eq("shbm",glzhxx.getShbm());
		queryWrapper.eq("ckzh",oldckzh);
		List<Glzhxx> glzhxxList = glzhxxService.list(queryWrapper);
		if(glzhxxList.isEmpty()){
			return Result.error("数据不存在！");
		}
		Glzhxx glzhxxUpdate = glzhxxList.get(0);
		glzhxxUpdate.setCkzh(glzhxx.getCkzh());
		glzhxxUpdate.setZhmc(glzhxx.getZhmc());
		glzhxxUpdate.setXgr(getLoginUser().getRealname());
		glzhxxUpdate.setXgrq(new Date());
		glzhxxUpdate.setLrbz(2);

		UpdateWrapper<Glzhxx> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("shbm",glzhxx.getShbm());
		updateWrapper.eq("ckzh",oldckzh);
		glzhxxService.update(glzhxxUpdate,updateWrapper);
		return Result.ok("编辑成功！");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "关联账号信息-通过id删除")
	@ApiOperation(value="关联账号信息-通过id删除", notes="关联账号信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("shbm")String shbm,@Param("ckzh")String ckzh) {
		QueryWrapper<Glzhxx> queryWrapper = new QueryWrapper<Glzhxx>();
		queryWrapper.eq("shbm",shbm);
		queryWrapper.eq("ckzh",ckzh);
		Glzhxx glzhxx = glzhxxService.getOne(queryWrapper);
		if (glzhxx == null){
			return Result.error("该条信息不存在！");
		}
		glzhxxService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "关联账号信息-批量删除")
	@ApiOperation(value="关联账号信息-批量删除", notes="关联账号信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.glzhxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "关联账号信息-通过id查询")
	@ApiOperation(value="关联账号信息-通过id查询", notes="关联账号信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Glzhxx glzhxx = glzhxxService.getById(id);
		return Result.ok(glzhxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param glzhxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Glzhxx glzhxx) {
      return super.exportXls(request, glzhxx, Glzhxx.class, "关联账号信息");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "商户关联账户信息导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, GlzhxxImportVo.class);
		 ExportParams exportParams = new ExportParams("商户关联账户信息导入模板", "模板信息");
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
		 return super.importExcelByTemplate(jsonObject, request, response, Glzhxx.class, GlzhxxImportVo.class, shZhglxxImportVerify);
	 }

}

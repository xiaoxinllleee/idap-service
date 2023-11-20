package org.cmms.modules.ywgl.nxt.shpj.shdjpd.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.ywgl.nxt.shpj.shdjpd.entity.ErpNxtMymTmp;
import org.cmms.modules.ywgl.nxt.shpj.shdjpd.entity.Shdjpd;
import org.cmms.modules.ywgl.nxt.shpj.shdjpd.entity.ShdjpdImportVo;
import org.cmms.modules.ywgl.nxt.shpj.shdjpd.service.IErpNxtMymTmpService;
import org.cmms.modules.ywgl.nxt.shpj.shdjpd.service.IShdjpdService;
import org.cmms.modules.ywgl.nxt.shpj.shdjpd.verify.ShdjpdImportVerify;
import org.cmms.modules.ywgl.nxt.shpj.shjbxx.entity.Shjbxx;
import org.cmms.modules.ywgl.nxt.shpj.shjbxx.entity.ShjbxxImportVo;
import org.cmms.modules.ywgl.nxt.shpj.shjbxx.service.IShjbxxService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 商户等级评定
 * @Author: jeecg-boot
 * @Date:   2021-09-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="商户等级评定")
@RestController
@RequestMapping("/shdjpd/shdjpd")
public class ShdjpdController extends JeecgController<Shdjpd, IShdjpdService> {
	 @Autowired
	 private IShdjpdService shdjpdService;
	 @Autowired
	 private IShjbxxService shjbxxService;
	 @Autowired
	 private RedisUtil redisUtil;
	 @Value("${com.etl.sfdsjpt}")
	 private String sfdsjpt;
	 @Autowired
	 private IErpNxtMymTmpService erpNxtMymTmpService;

	 @Autowired
	 private ShdjpdImportVerify  shdjpdImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param shdjpd
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "商户等级评定-分页列表查询")
	@ApiOperation(value="商户等级评定-分页列表查询", notes="商户等级评定-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Shdjpd shdjpd,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Shdjpd> queryWrapper = QueryGenerator.initQueryWrapper(shdjpd, req.getParameterMap());

		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IShdjpdService.class,shdjpdService,pageNo,pageSize,queryWrapper,"jgdm","pdlx","pdzq","shbm","glzh");
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  * @param jsonObject
	  * @return
	  */
	 @RequestMapping(value = "/init")
	 public Result<?> init(@RequestBody JSONObject jsonObject) throws ParseException {
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String pdlx = jsonObject.getString("pdlx"); // MM 月 Q 季 YY 半年 YYYY 年
		 String pdzq = jsonObject.getString("pdzq"); // yyyy-MM-dd
		 Date pdzqDate= DateUtil.string2Date(pdzq,"yyyy-MM-dd");
		 System.out.println("评定周期="+pdzq);
		 String shbm = jsonObject.getString("shbm");
		 if (!StringUtils.isEmpty(shbm)) {
			 QueryWrapper<Shjbxx> queryWrapper = new QueryWrapper<>();
			 queryWrapper.eq("shbm", shbm);
			 Shjbxx check = shjbxxService.getOne(queryWrapper);
			 if (check == null) {
				 return Result.error("商户信息不存在！");
			 }
		 } else {
			 shbm = "";
		 }

		 String beginDateStr = "";
		 String endDateStr = DateUtil.getDateString(DateUtil.addDays(pdzqDate,-1));

		 Date endDate = DateUtil.addDays(pdzqDate,-1);
		 Date beginDate = null;
		 if("MM".equalsIgnoreCase(pdlx)){
			 beginDateStr=DateUtil.getDateString(DateUtil.getFirstday_Month(pdzqDate,-1));
			 beginDate=DateUtil.getFirstday_Month(pdzqDate,-1);
		 } else if ("Q".equalsIgnoreCase(pdlx)){
			 beginDateStr=DateUtil.getDateString(DateUtil.getFirstday_Month(pdzqDate,-3));
			 beginDate=DateUtil.getFirstday_Month(pdzqDate,-3);
		 } else if ("YY".equalsIgnoreCase(pdlx)) {
			 beginDateStr= DateUtil.getDateString(DateUtil.getFirstday_Month(pdzqDate,-6));
			 beginDate=DateUtil.getFirstday_Month(pdzqDate,-6);
		 } else if ("YYYY".equalsIgnoreCase(pdlx)) {
			 beginDateStr= DateUtil.getDateString(DateUtil.getFirstday_Month(pdzqDate,-12));
			 beginDate=DateUtil.getFirstday_Month(pdzqDate,-12);
		 }
		 long tjts = DateUtil.getDiffDays(endDate, beginDate)+1L;

		 Result result = new Result<>();
		 ArrayList<ErpNxtMymTmp> erpNxtMymTmpArrayList =new ArrayList();

		 if ("true".equalsIgnoreCase(sfdsjpt)) {
			 Date nextDay  = beginDate;
			 QueryWrapper<ErpNxtMymTmp> queryWrapper=new QueryWrapper<>();
			 erpNxtMymTmpService.remove(queryWrapper);
			 while (nextDay.compareTo(endDate)<=0) {//当明天不在结束时间之前是终止循环
				 if(DateUtil.getLastDay(nextDay).compareTo(nextDay) == 0){
					 ErpNxtMymTmp erpNxtMymTmp=new ErpNxtMymTmp();
					 erpNxtMymTmp.setYmrq(DateUtil.getDateString(nextDay));
					 erpNxtMymTmpArrayList.add(erpNxtMymTmp);
				 }
				 nextDay =DateUtil.addDays(nextDay,1);
			 }
			 erpNxtMymTmpService.saveBatch(erpNxtMymTmpArrayList);
			 HashMap<String, String> param = new HashMap<>();
			 param.put("i_pdlx", pdlx);
			 param.put("i_pdzq", pdzq.replace("-","").trim());
			 param.put("i_shbm", shbm);
			 param.put("i_czy", loginUser.getUsername());
			 param.put("li_begindate", beginDateStr);
//			 param.put("li_enddate", enddate.replace("-","").trim());
    		 param.put("li_tjts", String.valueOf(tjts).trim());
			 System.out.println("param：" + param);
			 param.put("etl_task","kiss.domain.application.cdkyw.proc_merchant_rating");
			 // count_merchant_rating
			 boolean flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
			 result.setSuccess(flag);
		 } else {
			 try {
				 Map<String, String> param = new HashMap<>();
				 param.put("pdzq", pdlx);
				 param.put("pdrq", pdzq.replace("-","").trim());
				 System.out.println("param：" + param);
				 shdjpdService.pShdjpd(param,loginUser.getUsername());
				 result.setSuccess(true);
			 } catch (Exception e) {
				 System.out.println(e);
				 log.error("提取失败", e.getMessage());
				 result.setSuccess(false);
			 }
		 }
		 return result;
	 }

	/**
	 * 添加
	 *
	 * @param shdjpd
	 * @return
	 */
	@AutoLog(value = "商户等级评定-添加")
	@ApiOperation(value="商户等级评定-添加", notes="商户等级评定-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Shdjpd shdjpd) {
		if (StringUtils.isEmpty(shdjpd.getShbm())) {
			return Result.error("商户编码不能为空！");
		}
		QueryWrapper<Shdjpd> shdjpdQueryWrapper = new QueryWrapper<>();
		shdjpdQueryWrapper.eq("shbm", shdjpd.getShbm());
		shdjpdQueryWrapper.eq("pdlx", shdjpd.getPdlx());
		shdjpdQueryWrapper.eq("pdzq", shdjpd.getPdzq());
		shdjpdQueryWrapper.eq("jgdm", shdjpd.getJgdm());
		List<Shdjpd> shdjpdList = shdjpdService.list(shdjpdQueryWrapper);
		if(!shdjpdList.isEmpty()) {
			return Result.error("数据已经存在！");
		}
		QueryWrapper<Shjbxx> shjbxxQueryWrapper = new QueryWrapper<>();
		shjbxxQueryWrapper.eq("shbm", shdjpd.getShbm());
		List<Shjbxx> shjbxxList = shjbxxService.list(shjbxxQueryWrapper);
		if (shjbxxList.isEmpty()) {
			return Result.error("该商户不存在于商户基本信息管理，请核对！");
		}
		shdjpd.setPdrq(new Date());
		shdjpd.setLrbz(1);
		shdjpd.setLrrq(new Date());
		shdjpd.setLrr(getUsername());
		shdjpd.setKhjlbz(shjbxxList.get(0).getKhjlbz());
		shdjpd.setLxdh(shjbxxList.get(0).getLxdh());
		shdjpdService.save(shdjpd);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param shdjpd
	 * @return
	 */
	@AutoLog(value = "商户等级评定-编辑")
	@ApiOperation(value="商户等级评定-编辑", notes="商户等级评定-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Shdjpd shdjpd) {
		if (StringUtils.isEmpty(shdjpd.getShbm())) {
			return Result.error("商户编码不能为空！");
		}
		if (StringUtils.isEmpty(shdjpd.getPdlx())) {
			return Result.error("评定类型不能为空！");
		}
		if (shdjpd.getPdzq() == null) {
			return Result.error("评定周期不能为空！");
		}
		QueryWrapper<Shdjpd> shdjpdQueryWrapper = new QueryWrapper<>();
		shdjpdQueryWrapper.eq("pdlx", shdjpd.getPdlx());
		shdjpdQueryWrapper.eq("pdzq", shdjpd.getPdzq());
		shdjpdQueryWrapper.eq("jgdm", shdjpd.getJgdm());
		shdjpdQueryWrapper.eq("shbm", shdjpd.getShbm());
		List<Shdjpd> shdjpdList = shdjpdService.list(shdjpdQueryWrapper);
		if (shdjpdList.isEmpty()) {
			return Result.error("该信息不存在！");
		}
		Shdjpd shdjpdUpdate = shdjpdList.get(0);
		shdjpdUpdate.setPdlx(null);
		shdjpdUpdate.setPdzq(null);
		shdjpdUpdate.setJgdm(null);
		shdjpdUpdate.setShbm(null);
		shdjpdUpdate.setSqpdrp(shdjpd.getSqpdrp());
		shdjpdUpdate.setSqpddj(shdjpd.getSqpddj());
		shdjpdUpdate.setBqpddj(shdjpd.getBqpddj());
		shdjpdUpdate.setBqpdrp(shdjpd.getBqpdrp());
		shdjpdUpdate.setDjsfytz(shdjpd.getDjsfytz());
		shdjpdUpdate.setLrbz(2);
		shdjpdUpdate.setXgr(getUsername());
		shdjpdUpdate.setXgrq(new Date());
		shdjpdService.update(shdjpdUpdate, shdjpdQueryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "商户等级评定-通过id删除")
	@ApiOperation(value="商户等级评定-通过id删除", notes="商户等级评定-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("shbm")String shbm,@Param("pdzq")String pdzq,
							@Param("jgdm")String jgdm,@Param("pdlx")String pdlx) {
		QueryWrapper<Shdjpd> queryWrapper = new QueryWrapper<Shdjpd>();
		queryWrapper.eq("shbm",shbm);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			queryWrapper.eq("pdzq",sdf.parse(pdzq));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		queryWrapper.eq("jgdm",jgdm);
		queryWrapper.eq("pdlx",pdlx);
		shdjpdService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "商户等级评定-批量删除")
	@ApiOperation(value="商户等级评定-批量删除", notes="商户等级评定-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.shdjpdService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商户等级评定-通过id查询")
	@ApiOperation(value="商户等级评定-通过id查询", notes="商户等级评定-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Shdjpd shdjpd = shdjpdService.getById(id);
		return Result.ok(shdjpd);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param shdjpd
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Shdjpd shdjpd) {
      return super.exportXls(request, shdjpd, Shdjpd.class, "商户等级评定");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "商户等級评定信息导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, ShdjpdImportVo.class);
		 ExportParams exportParams = new ExportParams("商户等級评定信息导入模板", "模板信息");
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
		 return super.importExcelByTemplate(jsonObject, request, response, Shdjpd.class,ShdjpdImportVo.class, shdjpdImportVerify);
	 }

}

package org.cmms.modules.ywgl.cdkfx.dkdqshlmx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.utils.PageUtil;
import org.cmms.config.MybatisPlusConfig;
import org.cmms.modules.util.DateUtil;
import org.cmms.modules.ywgl.cdkfx.dkdqshlmx.entity.MidDmpmDkyebmxqktjT;
import org.cmms.modules.ywgl.cdkfx.dkdqshlmx.service.IMidDmpmDkyebmxqktjTService;
import org.cmms.modules.ywgl.cdkfx.khjlcdktjjg.entity.ErpYljcJgkhjldkkhzbtj;
import org.cmms.modules.ywgl.cdkfx.util.mapper.HrbasStaffToolMapper;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description: 贷款到期收回率明细
 * @Author: jeecg-boot
 * @Date:   2021-07-02
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款到期收回率明细")
@RestController
@RequestMapping("/dkdqshlmx/midDmpmDkyebmxqktjT")
public class MidDmpmDkyebmxqktjTController extends JeecgController<MidDmpmDkyebmxqktjT, IMidDmpmDkyebmxqktjTService> {
	@Autowired
	private IMidDmpmDkyebmxqktjTService midDmpmDkyebmxqktjTService;

	@Autowired(required = false)
	private HrbasStaffToolMapper hrbasStaffToolMapper;


	@Value("${com.etl.sfdsjpt}")
	private  String sfdsjpt;

	private static boolean sfdsjptValue;

	@PostConstruct
	public void init(){
		sfdsjptValue = Boolean.valueOf(this.sfdsjpt);
	}
	/**
	 * 分页列表查询
	 *
	 * @param midDmpmDkyebmxqktjT
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款到期收回率明细-分页列表查询")
	@ApiOperation(value="贷款到期收回率明细-分页列表查询", notes="贷款到期收回率明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MidDmpmDkyebmxqktjT midDmpmDkyebmxqktjT,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MidDmpmDkyebmxqktjT> queryWrapper = QueryGenerator.initQueryWrapper(midDmpmDkyebmxqktjT, req.getParameterMap());
		if (org.cmms.common.util.DateUtil.getMonthBeginDay(new Date()).equals(midDmpmDkyebmxqktjT.getTjyf())){
			queryWrapper.isNull("fiscal_date");
		}else {
			queryWrapper.eq("fiscal_date",DateUtil.date2String(midDmpmDkyebmxqktjT.getTjyf(),"yyyyMMdd"));
		}
		Calendar c=Calendar.getInstance();
		c.setTime(midDmpmDkyebmxqktjT.getTjyf());
		 String hxrq = DateUtil.date2String(DateUtil.getLastDayOfMonth(c), "yyyy-MM-dd");
		 try {
		 	if(sfdsjptValue){
				queryWrapper.notInSql("dkzh","select acct_no from cdkyw.Erp_bas_dkyeb_yhx where hxrq <= to_date('"+hxrq+"')");
			}else{
				queryWrapper.notInSql("dkzh","select acct_no from cdkyw.Erp_bas_dkyeb_yhx where hxrq <= to_date('"+hxrq+"'"+",'YYYY-MM-DD')");
			}
			 IPage pageList=org.cmms.common.utils.PageUtil.toPage(IMidDmpmDkyebmxqktjTService.class,midDmpmDkyebmxqktjTService,pageNo,pageSize,queryWrapper,"tjyf","jgdm","custid","dkzh");
			 return  Result.ok(pageList);
		 }catch (Exception e){
		 	e.printStackTrace();
		 }
		 return Result.error("表名不存在");
	}
  /**
   * 导出excel
   *
   * @param request
   * @param midDmpmDkyebmxqktjT
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, MidDmpmDkyebmxqktjT midDmpmDkyebmxqktjT) {
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
  	List<MidDmpmDkyebmxqktjT> list=null;
	  // Step.1 组装查询条件
	  QueryWrapper<MidDmpmDkyebmxqktjT> queryWrapper = QueryGenerator.initQueryWrapper(midDmpmDkyebmxqktjT, request.getParameterMap());
	  if (midDmpmDkyebmxqktjT.getTjyf() !=null){
		  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		  String format = sdf.format(midDmpmDkyebmxqktjT.getTjyf()).replaceAll("-","");
		  MybatisPlusConfig.myTableName.set("MID_DMPM_DKYEBMXQKTJ_"+format);
	  }
	  // Step.2 获取导出数据
	  if (midDmpmDkyebmxqktjT.getTjyf()!=null){
		  Calendar c=Calendar.getInstance();
		  c.setTime(midDmpmDkyebmxqktjT.getTjyf());
		  String hxrq = DateUtil.date2String(DateUtil.getLastDayOfMonth(c), "yyyy-MM-dd");
			  if(sfdsjptValue){
				  queryWrapper.notInSql("dkzh","select acct_no from ckdkyw.Erp_bas_dkyeb_yhx where hxrq <= to_date('"+hxrq+"')");
			  }else{
				  queryWrapper.notInSql("dkzh","select acct_no from ckdkyw.Erp_bas_dkyeb_yhx where hxrq <= to_date('"+hxrq+"'"+",'YYYY-MM-DD')");
			  }
			  list=PageUtil.toPage(IMidDmpmDkyebmxqktjTService.class,midDmpmDkyebmxqktjTService,1,10,queryWrapper,"tjyf","jgdm","custid","dkzh").getRecords();
	  }else {
		 list= PageUtil.toPage(IMidDmpmDkyebmxqktjTService.class,midDmpmDkyebmxqktjTService,1,10,queryWrapper,"jobnumber","dkzh").getRecords();

	  }
	  // 过滤选中数据
	  // Step.3 AutoPoi 导出Excel
	  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
	  mv.addObject(NormalExcelConstants.FILE_NAME, "贷款到期收回率明细"); //此处设置的filename无效 ,前端会重更新设置一下
	  mv.addObject(NormalExcelConstants.CLASS, MidDmpmDkyebmxqktjT.class);
	  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("贷款到期收回率明细" + "报表", "导出人:" + sysUser.getRealname(), "贷款到期收回率明细"));
	  mv.addObject(NormalExcelConstants.DATA_LIST, list);
	  return mv;
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
      return super.importExcel(request, response, MidDmpmDkyebmxqktjT.class);
  }

}

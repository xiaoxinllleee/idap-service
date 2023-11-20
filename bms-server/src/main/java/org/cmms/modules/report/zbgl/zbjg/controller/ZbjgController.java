package org.cmms.modules.report.zbgl.zbjg.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.report.util.ReportDateDin;
import org.cmms.modules.report.zbgl.zbjg.entity.Zbjg;
import org.cmms.modules.report.zbgl.zbjg.service.IZbjgService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.zbgl.zbsjx.entity.Zbsjxgl;
import org.cmms.modules.report.zbgl.zbsjx.service.IZbsjxglService;
import org.cmms.modules.report.zbgl.zbtqrzcx.entity.RepIndexExecuteLog;
import org.cmms.modules.report.zbgl.zbtqrzcx.service.IRepIndexExecuteLogService;
import org.cmms.modules.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 指标结果
 * @Author: jeecg-boot
 * @Date:   2022-03-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="指标结果")
@RestController
@RequestMapping("/zbgl/zbjg")
public class ZbjgController extends JeecgController<Zbjg, IZbjgService> {
	 @Autowired
	 private IZbjgService zbjgService;
	 @Autowired
	 private IZbsjxglService zbsjxglService;
	 @Autowired
	 private ISysDictService iSysDictService;
	 @Value("${com.etl.sfdsjpt}")
	 private String sfdsjpt;
	 @Autowired
	 private IRepIndexExecuteLogService zbzxrzService;
	 /**
	 * 分页列表查询
	 *
	 * @param zbjg
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "指标结果-分页列表查询")
	@ApiOperation(value="指标结果-分页列表查询", notes="指标结果-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zbjg zbjg,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zbjg> queryWrapper = QueryGenerator.initQueryWrapper(zbjg, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IZbjgService.class,zbjgService,pageNo,pageSize,queryWrapper,"sjrq","zbid","zblx");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param zbjg
	 * @return
	 */
	@AutoLog(value = "指标结果-添加")
	@ApiOperation(value="指标结果-添加", notes="指标结果-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zbjg zbjg) {
		zbjgService.save(zbjg);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zbjg
	 * @return
	 */
	@AutoLog(value = "指标结果-编辑")
	@ApiOperation(value="指标结果-编辑", notes="指标结果-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zbjg zbjg) {
		zbjgService.updateById(zbjg);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "指标结果-通过id删除")
	@ApiOperation(value="指标结果-通过id删除", notes="指标结果-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zbjgService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "指标结果-批量删除")
	@ApiOperation(value="指标结果-批量删除", notes="指标结果-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zbjgService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "指标结果-通过id查询")
	@ApiOperation(value="指标结果-通过id查询", notes="指标结果-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zbjg zbjg = zbjgService.getById(id);
		return Result.ok(zbjg);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zbjg
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zbjg zbjg) {
      return super.exportXls(request, zbjg, Zbjg.class, "指标结果");
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
      return super.importExcel(request, response, Zbjg.class);
  }


	 /**
	  * 提取指标结果
	  * @param object
	  * @return
	  */
	 @PutMapping(value = "/init")
	 public Result<?> InitZbxx(@RequestBody JSONObject object) {
		 System.out.println("sjrq-----"+object.getString("sjrq"));
		 try {
		 	 String zbwd = object.getString("zbwd");
			 String zbid = object.getString("zbid");

			 ReportDateDin reportDate = new ReportDateDin(DateUtil.string2Date(object.getString("sjrq"),"yyyy-MM-dd"),sfdsjpt);
			 String bqrq_m=DateUtil.getDateString(reportDate.getDateBq_MM());
			 String bqrq_q=DateUtil.getDateString(reportDate.getDateBq_Q());
			 String bqrq_yy=DateUtil.getDateString(reportDate.getDateNc_YY());
			 String bqrq_yyyy=DateUtil.getDateString(reportDate.getDateNc_YYYY());

			 String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC", "VALUE", "CODE", "101001");
			 QueryWrapper<Zbjg> queryWrapper=new QueryWrapper();
			 queryWrapper.eq("zbwd", zbwd);
			 queryWrapper.eq("sjrq",reportDate.getDateBq());
			 if(StringUtils.isNotEmpty(zbid)){
				 queryWrapper.eq("zbid",zbid);
			 }
			 zbjgService.remove(queryWrapper);
			 QueryWrapper<RepIndexExecuteLog> queryWrapperLog=new QueryWrapper();
			 queryWrapperLog.eq("zbwd", zbwd);
			 queryWrapperLog.eq("sjrq",reportDate.getDateBq());
			 if(StringUtils.isNotEmpty(zbid)){
				 queryWrapperLog.eq("zbid",zbid);
			 }
			 zbzxrzService.remove(queryWrapperLog);



			 //普通指标
			 List<Zbsjxgl> listByQydmPtzb = zbsjxglService.getListByQydm(qydm,"1", zbwd,zbid);
			 List<Zbsjxgl> listByQydmDwzb = zbsjxglService.getListByQydm(qydm,"2", zbwd,zbid);
			 List<Zbsjxgl> sqlListPtzb= new LinkedList<>();
			 List<Zbsjxgl> sqlListDwzb= new LinkedList<>();
			 for(Zbsjxgl zbsjxgl :listByQydmPtzb){
				 try {
					 String sql =reportDate.replaceStringVal(zbsjxgl.getJssql(),zbsjxgl.getZbwd());
					 log.info("================待执行的单值指标sql========"+sql);
					 zbsjxgl.setJssql(sql);
					 sqlListPtzb.add(zbsjxgl);

				 }catch (Exception e){
					 log.error("-------------替换sql失败："+e.getMessage());
				 }

					/* if(zbsjxgl.getZbwd().equals("MM")){
						 sqlListPtzb.add(zbsjxgl);
					 }else if(zbsjxgl.getZbwd().equals("Q")&&bqrq_m.equals(bqrq_q)){
						 sqlListPtzb.add(zbsjxgl);
					 }else if(zbsjxgl.getZbwd().equals("YY")&&bqrq_m.equals(bqrq_yy)){
						 sqlListPtzb.add(zbsjxgl);
					 }else if(zbsjxgl.getZbwd().equals("YYYY")&&bqrq_m.equals(bqrq_yyyy)){
						 sqlListPtzb.add(zbsjxgl);
					 }*/
			 }
			 for(Zbsjxgl zbsjxgl :listByQydmDwzb){
				 try {
					 String sql =reportDate.replaceStringVal(zbsjxgl.getJssql(),zbsjxgl.getZbwd());
					 log.info("================待执行的多维指标sql========"+sql);
					 zbsjxgl.setJssql(sql);
					 sqlListDwzb.add(zbsjxgl);
				 }catch (Exception e){
					 log.error("-------------替换sql失败："+e.getMessage());
				 }

			 }
			 log.info("================开始执行==================");
			 if(sqlListPtzb!=null&&sqlListPtzb.size()>0){
				 String ptzb = zbjgService.executePcSql(sqlListPtzb, DateUtil.string2Date(object.getString("sjrq"), "yyyy-MM-dd"));
			 }

			 if(sqlListDwzb!=null&&sqlListDwzb.size()>0){
				 String dwzb = zbjgService.executeDwzbSql(sqlListDwzb, DateUtil.string2Date(object.getString("sjrq"), "yyyy-MM-dd"), sfdsjpt);
			 }


			 return Result.ok("提取成功,请查看提取日志！");
		 } catch (Exception e) {
			 log.error(e.getMessage(), "提取失败！");
			 return Result.error(e.getMessage());
		 }
	 }

}

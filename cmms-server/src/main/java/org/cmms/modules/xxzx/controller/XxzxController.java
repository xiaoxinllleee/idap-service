package org.cmms.modules.xxzx.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.ckjkpt.ckjkptdqckdqyj.entity.VckjkptCkdqyj;
import org.cmms.modules.ckjkpt.ckjkptdqckdqyj.service.IVckjkptCkdqyjService;
import org.cmms.modules.ckjkpt.jcyj.ckyebd.service.ICkjkptCkyebdService;
import org.cmms.modules.ckjkpt.jcyj.ckyebd.service.impl.CkjkptCkyebdServiceImpl;
import org.cmms.modules.ckjkpt.jcyj.debdcx.service.ICkjkptDebdcxService;
import org.cmms.modules.dkjkpt.bldkftjk.service.IDkjkptBldkftjkBsrService;
import org.cmms.modules.dkjkpt.dksjjk.dkdqyj.service.IDkdqyjService;
import org.cmms.modules.dkjkpt.ydqkjk.service.IVdkjkptYqdkjkService;
import org.cmms.modules.khgl.clkhxxgl.entity.ClgrkhHfxx;
import org.cmms.modules.khgl.clkhxxgl.service.IClgrkhHfxxService;
import org.cmms.modules.khxxgl.khjbzl.service.IKhjbzlService;
import org.cmms.modules.xxzx.entity.Xxzx;
import org.cmms.modules.xxzx.service.IXxzxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.yxdygl.pqqxgl.entity.YxdyglPqqxgl;
import org.cmms.modules.yxdygl.pqqxgl.service.IYxdyglPqqxglService;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.service.IYxdyglMainService;
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
 * @Description: 消息中心
 * @Author: jeecg-boot
 * @Date:   2022-01-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="消息中心")
@RestController
@RequestMapping("/xxzx")
public class XxzxController extends JeecgController<Xxzx, IXxzxService> {
	@Autowired
	private IXxzxService xxzxService;
	@Autowired
	IClgrkhHfxxService clgrkhHfxxService;
	@Autowired
	IYxdyglMainService yxdyglMainService;
	@Autowired
	IYxdyglPqqxglService yxdyglPqqxglService;
	@Autowired
	IKhjbzlService khjbzlServicel;
	 @Autowired
	 private IVckjkptCkdqyjService vckjkptCkdqyjService;
	 @Autowired
	 private ICkjkptDebdcxService ckjkptDebdcxService;
	 @Autowired
	 private ICkjkptCkyebdService ckjkptCkyebdService;
	 @Autowired
	 private IDkdqyjService dkdqyjService;
	 @Autowired
	 private IVdkjkptYqdkjkService vdkjkptYqdkjkService;
	 @Autowired
	 private IDkjkptBldkftjkBsrService dkjkptBldkftjkBsrService;


	 /**
	 * 分页列表查询
	 *
	 * @param xxzx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "消息中心-分页列表查询")
	@ApiOperation(value="消息中心-分页列表查询", notes="消息中心-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Xxzx xxzx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Xxzx> queryWrapper = QueryGenerator.initQueryWrapper(xxzx, req.getParameterMap());
		Page<Xxzx> page = new Page<Xxzx>(pageNo, pageSize);
		IPage<Xxzx> pageList = xxzxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param xxzx
	 * @return
	 */
	@AutoLog(value = "消息中心-添加")
	@ApiOperation(value="消息中心-添加", notes="消息中心-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Xxzx xxzx) {
		xxzxService.save(xxzx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param xxzx
	 * @return
	 */
	@AutoLog(value = "消息中心-编辑")
	@ApiOperation(value="消息中心-编辑", notes="消息中心-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Xxzx xxzx) {
		xxzxService.updateById(xxzx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "消息中心-通过id删除")
	@ApiOperation(value="消息中心-通过id删除", notes="消息中心-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		xxzxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "消息中心-批量删除")
	@ApiOperation(value="消息中心-批量删除", notes="消息中心-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.xxzxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "消息中心-通过id查询")
	@ApiOperation(value="消息中心-通过id查询", notes="消息中心-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Xxzx xxzx = xxzxService.getById(id);
		return Result.ok(xxzx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param xxzx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Xxzx xxzx) {
      return super.exportXls(request, xxzx, Xxzx.class, "消息中心");
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
      return super.importExcel(request, response, Xxzx.class);
  }

  //存储的每天的日期字符创 20220111
  String dtString = null;
  Map<String,List<Xxzx>>  xxzxmap = new HashMap<>();
  @RequestMapping("/getXxzxList")
  public Result<?> list(){
	  if (xxzxmap != null){
		  xxzxmap.forEach((k, v) -> System.out.println("key:value = " + k + ":" + v));
	  }

	  //每天只要查询一次  根据日期和工号来取当前角色的值
	  String format = DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN);
	  if (dtString == null){
		  dtString = format;
	  }
	  //不相等就是新的一天需要进行重置
	  if (!dtString.equals(format)){
		  dtString = format;
		  xxzxmap = new HashMap<>();
	  }
	  System.out.println(format);
	  System.out.println(dtString);
	  if (CollUtil.isNotEmpty(xxzxmap) && CollUtil.isNotEmpty(xxzxmap.get(format+getUsername()))){
		  return Result.ok(xxzxmap.get(format+getUsername()));
	  }else
	  {

			  //查出当前人所属得网格
			  LambdaQueryWrapper<YxdyglPqqxgl> lambdaQueryWrapper = new LambdaQueryWrapper<>();
			  lambdaQueryWrapper.eq(YxdyglPqqxgl::getKhjl,getUsername());
			  List<YxdyglPqqxgl> list = yxdyglPqqxglService.list(lambdaQueryWrapper);
			  boolean notEmpty = CollUtil.isNotEmpty(list);
			  List<String> wgbhs = new ArrayList<>();
			  if (notEmpty){
				  wgbhs = list.stream().map(YxdyglPqqxgl::getMenuId).collect(Collectors.toList());
			  }
			  List<Xxzx> result = new ArrayList<>();
			  //今日回访客户
			  List<String> jthfkh = clgrkhHfxxService.queryTodayHfxx(getUsername());
			  Xxzx xxzx = new Xxzx();
			  xxzx.setTitle("今天回访客户");
			  xxzx.setPath("/cmms/khxxgl/khjbzl/KhjbzlList");
			  xxzx.setContent(CollUtil.isNotEmpty(jthfkh)?jthfkh.size()+"":"0");
			  xxzx.setBz("khhf.png");
			  result.add(xxzx);
			  //生日提醒
			  //khjbzlServicel.getTodayBirthDayMans()
			  Xxzx srtx = new Xxzx();
			  srtx.setTitle("生日提醒");
			  srtx.setPath("/cmms/khxxgl/khjbzl/KhjbzlList");
			  srtx.setBz("srtx.png");
			  srtx.setContent("0");
			  if (notEmpty){
				  Integer todayBirthDayMans = khjbzlServicel.getTodayBirthDayMans(getWorkNo());
				  srtx.setContent(todayBirthDayMans+"");
			  }
			  result.add(srtx);
			  //定期存款到期预警
			  Xxzx dq = new Xxzx();
			  dq.setTitle("定期存款到期预警");
			  dq.setPath("/ckjk/ckjkptdqckdqyj/VckjkptCkdqyjList");
			  dq.setContent(vckjkptCkdqyjService.count()+"");
			  dq.setBz("dqck.png");
			  result.add(dq);
			  //存款大额变动
			  Xxzx de = new Xxzx();
			  de.setTitle("存款大额变动");
			  de.setPath("/ckjk/jcyj/debdcx");
			  de.setContent(ckjkptDebdcxService.count()+"");
		      //de.setContent(RandomUtil.randomInt(100)+"");
			  de.setBz("de.png");
			  result.add(de);

			  //cun
			  Xxzx ckye = new Xxzx();
			  ckye.setTitle("存款余额变动");
			  ckye.setPath("/ckjk/jcyj/ckyebd");
			  ckye.setContent(ckjkptCkyebdService.count()+"");
			  ckye.setBz("ckye.png");
			  result.add(ckye);

			  //贷款到期预警
			  Xxzx dkdq = new Xxzx();
			  dkdq.setTitle("贷款到期预警");
			  dkdq.setPath("/dkjkpt/dksjjk/dkdqyj");
			  //dkdq.setContent(RandomUtil.randomInt(100)+"");
			  dkdq.setBz("dkdq.png");
			  dkdq.setContent(dkdqyjService.count()+"");
			  result.add(dkdq);
			  //预期贷款提醒
			  Xxzx dkyq = new Xxzx();
			  dkyq.setTitle("贷款逾期提醒");
			  dkyq.setPath("/dkjkpt/dksjjk/yqdkjk/VdkjkptYqdkjkList");
			  dkyq.setContent(vdkjkptYqdkjkService.count()+"");
			  dkyq.setBz("dkyq.png");
			  result.add(dkyq);

			  //贷后检查提醒
			  //不良贷款反弹提醒
			  Xxzx blft = new Xxzx();
			  blft.setTitle("不良贷款反弹提醒");
			  blft.setPath("/dkjkpt/bldkftjk/bldkftjkmx/DkjkptBldkftjk");
			  //blft.setContent(RandomUtil.randomInt(100)+"");
			  blft.setBz("blft.png");
			  blft.setContent(dkjkptBldkftjkBsrService.count()+"");
			  result.add(blft);

			  xxzxmap.put(format+getUsername(),result);
			  return Result.ok(result);
	  }
  }

  public boolean saveTjsj(String yggh,String xxzl){
	  Xxzx xxzx = new Xxzx();
	  xxzx.setCreateBy(getUsername());
	  xxzx.setCreateTime(new Date());
	  xxzx.setSjtjsj(DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN));
	  xxzx.setKhjl(getUsername());
	  return false;
  }

}

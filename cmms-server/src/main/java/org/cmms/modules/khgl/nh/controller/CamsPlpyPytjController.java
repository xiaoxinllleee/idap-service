package org.cmms.modules.khgl.nh.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import org.checkerframework.checker.units.qual.C;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.nh.entity.CamsPlpyPytj;
import org.cmms.modules.khgl.nh.entity.CamsPlpyYsxx;
import org.cmms.modules.khgl.nh.service.ICamsPlpyPytjService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.nh.service.ICamsPlpyYsxxService;
import org.cmms.modules.khgl.nh.service.INhbkbpyService;
import org.cmms.modules.khgl.nh.vo.HzKhlxVO;
import org.cmms.modules.pad.pyxx.entity.Nhplpy;
import org.cmms.modules.pad.pyxx.service.INhplpyService;
import org.cmms.modules.pad.pyxx.service.IPyyxxService;
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
 * @Description: 批量评议统计
 * @Author: jeecg-boot
 * @Date:   2022-04-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="批量评议统计")
@RestController
@RequestMapping("/nh/camsPlpyPytj")
public class CamsPlpyPytjController extends JeecgController<CamsPlpyPytj, ICamsPlpyPytjService> {
	@Autowired
	private ICamsPlpyPytjService camsPlpyPytjService;
	@Autowired
	private INhplpyService nhplpyService;
	@Autowired
	private IPyyxxService pyyxxService;
	@Autowired
	private ICamsPlpyYsxxService camsPlpyYsxxService;
	@Autowired
	INhbkbpyService nhbkbpyService;
	/**
	 * 分页列表查询
	 *
	 * @param camsPlpyPytj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "批量评议统计-分页列表查询")
	@ApiOperation(value="批量评议统计-分页列表查询", notes="批量评议统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CamsPlpyPytj camsPlpyPytj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CamsPlpyPytj> queryWrapper = QueryGenerator.initQueryWrapper(camsPlpyPytj, req.getParameterMap());
		String sqlSswg = "select  menu_id from YXDYGL_PQQXGL t where khjl =" + "'" + getUsername() + "'";
		queryWrapper.and(i -> i.inSql("wgbh", sqlSswg));
		Page<CamsPlpyPytj> page = new Page<CamsPlpyPytj>(pageNo, pageSize);
		IPage<CamsPlpyPytj> pageList = camsPlpyPytjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param camsPlpyPytj
	 * @return
	 */
	@AutoLog(value = "批量评议统计-添加")
	@ApiOperation(value="批量评议统计-添加", notes="批量评议统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CamsPlpyPytj camsPlpyPytj) {
		camsPlpyPytjService.save(camsPlpyPytj);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param camsPlpyPytj
	 * @return
	 */
	@AutoLog(value = "批量评议统计-编辑")
	@ApiOperation(value="批量评议统计-编辑", notes="批量评议统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CamsPlpyPytj camsPlpyPytj) {
		camsPlpyPytjService.updateById(camsPlpyPytj);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "批量评议统计-通过id删除")
	@ApiOperation(value="批量评议统计-通过id删除", notes="批量评议统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		camsPlpyPytjService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "批量评议统计-批量删除")
	@ApiOperation(value="批量评议统计-批量删除", notes="批量评议统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.camsPlpyPytjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "批量评议统计-通过id查询")
	@ApiOperation(value="批量评议统计-通过id查询", notes="批量评议统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CamsPlpyPytj camsPlpyPytj = camsPlpyPytjService.getById(id);
		return Result.ok(camsPlpyPytj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param camsPlpyPytj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, CamsPlpyPytj camsPlpyPytj) {
      return super.exportXls(request, camsPlpyPytj, CamsPlpyPytj.class, "批量评议统计");
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
      return super.importExcel(request, response, CamsPlpyPytj.class);
  }

  @RequestMapping("/count")
  public Result<?> count(){
	  //清空统计信息
	  QueryWrapper rw = new QueryWrapper();
	  rw.isNotNull("id");
	  service.remove(rw);
	  //获取评议过的村网格编号  和 可评议户数
	  Map<String, Integer> list = service.getList();
	  Map<String, HzKhlxVO> hzkhlx = service.getHzkhlx();

	  //获取已评议户数
	  Map<String, Integer> listByPyls = service.getListByPyls(1);
	  Map<String, Integer> listByPyls2 = service.getListByPyls(2);
	  Map<String, Integer> listByPyls3 = service.getListByPyls(3);

	  //获取所有评议员
	  List<Nhplpy> byPyls = nhplpyService.getByPyls(1);
	  List<Nhplpy> byPyls2 = nhplpyService.getByPyls(2);
	  List<Nhplpy> byPyls3 = nhplpyService.getByPyls(3);

	  //获取所有轮数验收信息
	  Map<String, String> wgbhYsqkAndPyls = camsPlpyYsxxService.getWgbhYsqkAndPyls(1);
	  Map<String, String> wgbhYsqkAndPyls2 = camsPlpyYsxxService.getWgbhYsqkAndPyls(2);
	  Map<String, String> wgbhYsqkAndPyls3 = camsPlpyYsxxService.getWgbhYsqkAndPyls(3);

	  Map<String, Integer> ypyrs = nhbkbpyService.getYpyrs();


	  if (CollUtil.isNotEmpty(list)){
		  for (String k:list.keySet()){
			  Integer v = list.get(k);
			  //}
		  //list.forEach((k,v)->{
			  CamsPlpyPytj camsPlpyPytj = new CamsPlpyPytj();
			  camsPlpyPytj.setWgbh(k);

			  //第一轮
			  if (CollUtil.isNotEmpty(byPyls)){
				  for (int i = 0; i < byPyls.size(); i++) {
					  Nhplpy nhplpy = byPyls.get(i);
					  if (nhplpy.getPywg().equals(k)){
						  camsPlpyPytj.setPyy1(nhplpy.getPyyzjhm());
						  camsPlpyPytj.setSfjs1(nhplpy.getSfjspy());
						  break;
					  }
				  }
			  }

			  //可评议户数要减黑名单和灰名单
			  HzKhlxVO hzKhlxVO = hzkhlx.get(k);
			  int hei = 0;
			  int hui = 0;
			  int bai = 0;
			  int kpyhs = 0;
			  if (hzKhlxVO != null){
				  hei = hzKhlxVO.getHei();
				  bai = hzKhlxVO.getBai();
				  hui = hzKhlxVO.getHui();
			  }
			  kpyhs = v - hei - hui;

			  Integer integer = listByPyls.get(k)!=null?listByPyls.get(k):0;
			  int wpyhs1 = kpyhs - integer;
//			  if (wpyhs1 == 0){
//				  camsPlpyPytj.setSfjs1("2");
//			  }else {
//				  camsPlpyPytj.setSfjs1("1");
//			  }
			  String s = wgbhYsqkAndPyls.get(k);
			  camsPlpyPytj.setSfys1(s);
			  camsPlpyPytj.setCzmx1("可评议户数："+kpyhs+"户；已经评议户数："+integer+"户；未评议户数"+wpyhs1+"户；");


			  //第二轮
			  if (CollUtil.isNotEmpty(byPyls2)){
				  for (int i = 0; i < byPyls2.size(); i++) {
					  Nhplpy nhplpy = byPyls2.get(i);
					  if (nhplpy.getPywg().equals(k)){
						  camsPlpyPytj.setPyy2(nhplpy.getPyyzjhm());
						  camsPlpyPytj.setSfjs2(nhplpy.getSfjspy());
						  break;
					  }
				  }
			  }

			  Integer integer2 = listByPyls2.get(k)!=null?listByPyls2.get(k):0;
			  int wpyhs2 = kpyhs - integer2;
//			  if (wpyhs2 == 0){
//				  camsPlpyPytj.setSfjs2("2");
//			  }else {
//				  camsPlpyPytj.setSfjs2("1");
//			  }
			  String s2 = wgbhYsqkAndPyls2.get(k);
			  camsPlpyPytj.setSfys2(s2);

			  camsPlpyPytj.setCzmx2("可评议户数："+kpyhs+"户；已经评议户数："+integer2+"户；未评议户数"+wpyhs2+"户；");

			  //第三轮
			  if (CollUtil.isNotEmpty(byPyls3)){
				  for (int i = 0; i < byPyls3.size(); i++) {
					  Nhplpy nhplpy = byPyls3.get(i);
					  if (nhplpy.getPywg().equals(k)){
						  camsPlpyPytj.setPyy3(nhplpy.getPyyzjhm());
						  camsPlpyPytj.setSfjs3(nhplpy.getSfjspy());
						  camsPlpyPytj.setPysfwc(nhplpy.getSfjspy());
						  break;
					  }
				  }
			  }

			  Integer integer3 = listByPyls3.get(k)!=null?listByPyls3.get(k):0;
			  int wpyhs3 = kpyhs - integer3;
//			  if (wpyhs3 == 0){
//				  camsPlpyPytj.setPysfwc("2");
//			  }else {
//				  camsPlpyPytj.setPysfwc("1");
//			  }
			  String s3 = wgbhYsqkAndPyls3.get(k);
			  camsPlpyPytj.setSfys3(s3);
			  camsPlpyPytj.setSfys(s3);

			  camsPlpyPytj.setCzmx3("可评议户数："+kpyhs+"户；已经评议户数："+integer3+"户；未评议户数"+wpyhs3+"户；");

			  int ypyhs = ypyrs.get(k)!=null?ypyrs.get(k):0;




			  camsPlpyPytj.setCzmx("户籍数："+v+"户；白名单："+bai+"户；黑名单："+hei+"户；灰名单"+hui+"户；未评议"+(kpyhs-ypyhs)+"户；");
			  service.save(camsPlpyPytj);
		  };
	  }
	  return Result.ok();
  }
}

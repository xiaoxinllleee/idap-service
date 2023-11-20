package org.cmms.modules.tjfx.wgtjfx.wgxxtj.controller;

import java.math.BigDecimal;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.khhmc.entity.Khfjxxgl;
import org.cmms.modules.tjfx.wgtjfx.wgxxtj.entity.*;
import org.cmms.modules.tjfx.wgtjfx.wgxxtj.service.*;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.entity.Wgywtj;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.service.IWgywtjService;
import org.cmms.modules.tjfx.zftjysb.xzcysb.entity.ZftjysbXzc;
import org.cmms.modules.tjfx.zftjysb.xzcysb.service.IZftjysbXzcService;
import org.cmms.modules.yxdygl.yxdyglmain.entity.VYxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.service.IVYxdyglMainService;
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
 * @Description: 网格信息统计
 * @Author: jeecg-boot
 * @Date:   2022-03-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="网格信息统计")
@RestController
@RequestMapping("/wgtjfx/wgxxtj")
public class WgxxtjController extends JeecgController<Wgxxtj, IWgxxtjService> {
	 @Autowired
	 private IWgxxtjService wgxxtjService;
	 @Autowired
	 private IWgywtjService wgywtjService;
	 @Autowired
	 private IVYxdyglMainService vYxdyglMainService;
	 @Autowired
	 private IWgxxtjCkywService wgxxtjCkywService;
	 @Autowired
	 private IWgxxtjDkywService wgxxtjDkywService;
	 @Autowired
	 private IWgxxtjBnbldkService wgxxtjBnbldkService;
	 @Autowired
	 private IWgxxtjBwbldkService wgxxtjBwbldkService;

	 @Autowired
	 private IWgxxtjZftjysbTownService wgxxtjZftjysbTownService;

	 @Autowired
	 private IZftjysbXzcService zftjysbXzcService;
	/**
	 * 分页列表查询
	 *
	 * @param wgxxtj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "网格信息统计-分页列表查询")
	@ApiOperation(value="网格信息统计-分页列表查询", notes="网格信息统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Wgxxtj wgxxtj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Wgxxtj> queryWrapper = QueryGenerator.initQueryWrapper(wgxxtj, req.getParameterMap());
		Page<Wgxxtj> page = new Page<Wgxxtj>(pageNo, pageSize);
		IPage<Wgxxtj> pageList = wgxxtjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	@GetMapping(value = "/jbxxlist")
	public Result<?> jbxxPageList(String cs,String wgbh,
								  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
		System.out.println(cs);
		System.out.println(wgbh);
		Page<WgxxtjVo> page = new Page<WgxxtjVo>(pageNo, pageSize);
		if ("4".equals(cs)){
			IPage<WgxxtjVo> pageList = wgxxtjService.wbsbk(page,Arrays.asList(wgbh.split(",")));
			return Result.ok(pageList);
		}
		if ("5".equals(cs)){
			IPage<WgxxtjVo> pageList = wgxxtjService.tpjjch(page,Arrays.asList(wgbh.split(",")));
			return Result.ok(pageList);
		}
		if ("13".equals(cs)){
			IPage<WgxxtjVo> pageList = wgxxtjService.getSarsByWgbh(page,Arrays.asList(wgbh.split(",")));
			return Result.ok(pageList);
		}
		IPage<WgxxtjVo> pageList = wgxxtjService.jbxxCs(page,cs,Arrays.asList(wgbh.split(",")));
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param wgxxtj
	 * @return
	 */
	@AutoLog(value = "网格信息统计-添加")
	@ApiOperation(value="网格信息统计-添加", notes="网格信息统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Wgxxtj wgxxtj) {
		wgxxtjService.save(wgxxtj);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param wgxxtj
	 * @return
	 */
	@AutoLog(value = "网格信息统计-编辑")
	@ApiOperation(value="网格信息统计-编辑", notes="网格信息统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Wgxxtj wgxxtj) {
		wgxxtjService.updateById(wgxxtj);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "网格信息统计-通过id删除")
	@ApiOperation(value="网格信息统计-通过id删除", notes="网格信息统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		wgxxtjService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "网格信息统计-批量删除")
	@ApiOperation(value="网格信息统计-批量删除", notes="网格信息统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.wgxxtjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "网格信息统计-通过id查询")
	@ApiOperation(value="网格信息统计-通过id查询", notes="网格信息统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Wgxxtj wgxxtj = wgxxtjService.getById(id);
		return Result.ok(wgxxtj);
	}

	 /**
	  * 通过wgbh查询
	  *
	  * @param wgbh
	  * @return
	  */
	 @GetMapping(value = "/queryByWgbh")
	 public Result<?> queryByWgbh(@RequestParam(name="wgbh",required=true) String wgbh) {
	 	if (StringUtils.isNotBlank(wgbh)) {
			String qydm = getRedisQydm();
			Wgxxtj wgxxtj = null;
			if (StringUtils.isNotBlank(qydm) && qydm.equals(QydmEnums.TIANYI.getQydmCode())) {
				wgxxtj = wgxxtjService.getWgxxByWgbhTy(Arrays.asList(wgbh.split(",")));
			} else {
				wgxxtj = wgxxtjService.getWgxxByWgbh(wgbh);
			}
			return Result.ok(wgxxtj);
		}
		 return Result.ok(null);
	 }

	 /**
	  * 通过wgbh查询
	  *
	  * @param wgbh
	  * @return
	  */
	 @GetMapping(value = "/queryWgxqByWgbh")
	 public Result<?> queryWgxqByWgbh(@RequestParam(name="wgbh",required=true) String wgbh) {
		 WgxqtjVo wgxqtjVo =new WgxqtjVo();
		 QueryWrapper queryWrapper =new QueryWrapper();
		 queryWrapper.eq("wgbh",wgbh);
		 VYxdyglMain yxdyglMain = vYxdyglMainService.getOne(queryWrapper);
		 if(yxdyglMain!=null){
			 log.info("==================网格详情业务信息开始查询================="+new Date());
			 Wgxxtj wgxxtj = wgxxtjService.getWgxxByWgbh(wgbh);
			 Wgywtj wgywtj = wgywtjService.getWgywxxByWgbh(wgbh);
			 WgxxtjCkyw ckyw = wgxxtjCkywService.getOne(queryWrapper,false);
			 WgxxtjDkyw dkyw = wgxxtjDkywService.getOne(queryWrapper,false);
			 WgxxtjBnbldk bnbldk = wgxxtjBnbldkService.getOne(queryWrapper,false);
			 WgxxtjBwbldk bwbldk = wgxxtjBwbldkService.getOne(queryWrapper,false);
			 if(wgxxtj!=null){
				 BeanUtil.copyPropertiesIgnoreNull(wgxxtj, wgxqtjVo);
			 }
			 if(wgywtj!=null){
				 BeanUtil.copyPropertiesIgnoreNull(wgywtj, wgxqtjVo);
			 }
			 if(ckyw!=null){
				 BeanUtil.copyPropertiesIgnoreNull(ckyw, wgxqtjVo);
			 }
			 if(dkyw!=null){
				 BeanUtil.copyPropertiesIgnoreNull(dkyw, wgxqtjVo);
			 }
			 if(bnbldk!=null){
				 BeanUtil.copyPropertiesIgnoreNull(bnbldk, wgxqtjVo);
			 }
			 if(bwbldk!=null){
				 BeanUtil.copyPropertiesIgnoreNull(bwbldk, wgxqtjVo);
			 }
			 if(wgxqtjVo.getZhs()!=null&&wgxqtjVo.getDkkhs()!=null){
				 if(wgxqtjVo.getDkkhs()==0||wgxqtjVo.getZhs()==0){
					 wgxqtjVo.setDkfgm(BigDecimal.valueOf(0));
				 }else{
					 wgxqtjVo.setDkfgm(BigDecimal.valueOf(wgxqtjVo.getDkkhs()/wgxqtjVo.getZhs()*100).setScale(2));
				 }
			 }
			 log.info("==================网格详情业务信息查询完毕================="+new Date());
			 log.info("==================****网格详情走访信息开始查询****================="+new Date());
			 if (yxdyglMain.getWgxz().equals("1")) {
				 wgxqtjVo.setWgxz(1);
				 QueryWrapper queryWrapper1 = new QueryWrapper();
				 queryWrapper1.eq("ssxz", wgbh);
				 queryWrapper1.orderByDesc("tjrq");
				 WgxxtjZftjysbTown zftjysbTown = wgxxtjZftjysbTownService.list(queryWrapper1).size() > 0 ? (WgxxtjZftjysbTown) wgxxtjZftjysbTownService.list(queryWrapper1).get(0) : null;
				 if(zftjysbTown!=null){
					 BeanUtil.copyPropertiesIgnoreNull(zftjysbTown, wgxqtjVo);
				 }
			 } else if (yxdyglMain.getWgxz().equals("2")) {
				 wgxqtjVo.setWgxz(2);
				 QueryWrapper queryWrapper1 = new QueryWrapper();
				 queryWrapper1.eq("xzc", wgbh);
				 queryWrapper1.orderByDesc("tjrq");
				 ZftjysbXzc xzc = zftjysbXzcService.list(queryWrapper1).size() > 0 ? (ZftjysbXzc) zftjysbXzcService.list(queryWrapper1).get(0) : null;
				 if(xzc!=null){
					 BeanUtil.copyPropertiesIgnoreNull(xzc, wgxqtjVo);
				 }
			 }
			 log.info("==================****网格详情走访信息查询结束****================="+new Date());

		 }else{
			 return Result.error("网格信息不存在！");
		 }

		 return Result.ok(wgxqtjVo);
	 }


  /**
   * 导出excel
   *
   * @param request
   * @param wgxxtj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Wgxxtj wgxxtj) {
      return super.exportXls(request, wgxxtj, Wgxxtj.class, "网格信息统计");
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
      return super.importExcel(request, response, Wgxxtj.class);
  }

	 /**
	  * 根据网格编号获取相关走访信息
	  */
	 @GetMapping("/getPyxxPh")
	public Result<?> getPyxxPh(@RequestParam("wgbh") String wgbh,@RequestParam("sxtlx")String sxtlx){
	 	return Result.ok(service.getPyxxPh(Arrays.asList(wgbh.split(",")),sxtlx));
	 }
 }

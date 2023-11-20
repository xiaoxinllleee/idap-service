package org.cmms.modules.sbxj.fxzdxjjlb.controller;

import java.math.BigDecimal;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.sbxj.fxzdfjxx.entity.KhywxxFjxx;
import org.cmms.modules.sbxj.fxzdfjxx.service.IKhywxxFjxxService;
import org.cmms.modules.sbxj.fxzdxjjlb.entity.KhywxxFxzdxjjlb;
import org.cmms.modules.sbxj.fxzdxjjlb.entity.KhywxxFxzdxjjlbVO;
import org.cmms.modules.sbxj.fxzdxjjlb.entity.SbxxFjVO;
import org.cmms.modules.sbxj.fxzdxjjlb.service.IKhywxxFxzdxjjlbService;
import org.cmms.modules.yxdygl.cwhfjl.entity.CwhfjlFjxx;
import org.cmms.modules.yxdygl.yxdyglmain.entity.WgxxFjVO;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 巡检记录表
 * @Author: jeecg-boot
 * @Date:   2023-06-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="巡检记录表")
@RestController
@RequestMapping("/fxzdxjjlb/khywxxFxzdxjjlb")
public class KhywxxFxzdxjjlbController extends JeecgController<KhywxxFxzdxjjlb, IKhywxxFxzdxjjlbService> {
	@Autowired
	private IKhywxxFxzdxjjlbService khywxxFxzdxjjlbService;
	@Autowired
	private IKhywxxFjxxService khywxxFjxxService;

	/**
	 * 分页列表查询
	 *
	 * @param khywxxFxzdxjjlb
	 * @param pageNo
	 * @param pageSize/cwhfjl/cwhfjl
	 * @param req
	 * @return
	 */
	@AutoLog(value = "巡检记录表-分页列表查询")
	@ApiOperation(value="巡检记录表-分页列表查询", notes="巡检记录表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhywxxFxzdxjjlb khywxxFxzdxjjlb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		System.out.println(khywxxFxzdxjjlb);
		QueryWrapper<KhywxxFxzdxjjlb> queryWrapper = QueryGenerator.initQueryWrapper(khywxxFxzdxjjlb, req.getParameterMap());
		queryWrapper.orderByDesc("xjcs");
		queryWrapper.orderByDesc("xjsj");
		Page<KhywxxFxzdxjjlb> page = new Page<KhywxxFxzdxjjlb>(pageNo, pageSize);
		IPage<KhywxxFxzdxjjlb> pageList = khywxxFxzdxjjlbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param khywxxFxzdxjjlb
	 * @return
	 */
	@AutoLog(value = "巡检记录表-添加")
	@ApiOperation(value="巡检记录表-添加", notes="巡检记录表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhywxxFxzdxjjlb khywxxFxzdxjjlb) {
		khywxxFxzdxjjlbService.save(khywxxFxzdxjjlb);
		return Result.ok("添加成功！");
	}

	 /**
	  * 编辑
	  *
	  * @param khywxxFxzdxjjlbVO
	  * @return
	  */
	 @AutoLog(value = "福祥站点主表-编辑")
	 @ApiOperation(value="福祥站点主表-编辑", notes="福祥站点主表-编辑")
	 @RequestMapping(value = "/addApp")
	 public Result<?> addApp(KhywxxFxzdxjjlbVO khywxxFxzdxjjlbVO) {
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 Date xjsj = DateUtil.beginOfDay(new Date());
		 KhywxxFxzdxjjlb khywxxFxzdxjjlb = new KhywxxFxzdxjjlb();
		 BeanUtils.copyProperties(khywxxFxzdxjjlbVO, khywxxFxzdxjjlb);
		 //保存巡检信息
		 QueryWrapper<KhywxxFxzdxjjlb> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("xjsj",xjsj);
		 queryWrapper.eq("xjrgh",khywxxFxzdxjjlb.getXjrgh());
		 List<KhywxxFxzdxjjlb> list = khywxxFxzdxjjlbService.list(queryWrapper);
		 if (CollUtil.isNotEmpty(list)){
		 	khywxxFxzdxjjlb.setXjcs(String.valueOf(list.size()+1));
			 khywxxFxzdxjjlb.setXjsj(xjsj);
		 }else {
			 khywxxFxzdxjjlb.setXjsj(xjsj);
			 khywxxFxzdxjjlb.setXjcs("1");
		 }
		 khywxxFxzdxjjlb.setCreateBy(getUsername());
		 khywxxFxzdxjjlb.setCreateTime(new Date());
		 khywxxFxzdxjjlbService.save(khywxxFxzdxjjlb);
		 //附件有变化时 才会传值进来 当有值 先删除 后新增即可
		 if (CollUtil.isNotEmpty(khywxxFxzdxjjlbVO.getYxdyfjxxList())) {
			 List<SbxxFjVO> yxdyfjxxList = khywxxFxzdxjjlbVO.getYxdyfjxxList();
			 List<KhywxxFjxx> list1 = new ArrayList<>();
			 for (int i = 0; i < yxdyfjxxList.size(); i++) {
				 SbxxFjVO wgxxFjVO = yxdyfjxxList.get(i);
				 KhywxxFjxx yxdyfjxx = new KhywxxFjxx();
				 yxdyfjxx.setId(khywxxFxzdxjjlb.getId());
				 yxdyfjxx.setZdbh(khywxxFxzdxjjlb.getZdbh());
				 yxdyfjxx.setFjlx(wgxxFjVO.getZllx() + "");
				 yxdyfjxx.setScsj(new Date());
				 yxdyfjxx.setScr(loginUser.getUsername());
				 yxdyfjxx.setFjdx(new BigDecimal(wgxxFjVO.getSize()));
				 yxdyfjxx.setFjlj("/" + wgxxFjVO.getFwlj());
				 yxdyfjxx.setFjfwlj("/"+wgxxFjVO.getFwlj());
				 list1.add(yxdyfjxx);
			 }
			 khywxxFjxxService.saveBatch(list1);
		 }
		 return Result.ok("编辑成功!");
	 }



	/**
	 * 编辑
	 *
	 * @param khywxxFxzdxjjlb
	 * @return
	 */
	@AutoLog(value = "巡检记录表-编辑")
	@ApiOperation(value="巡检记录表-编辑", notes="巡检记录表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhywxxFxzdxjjlb khywxxFxzdxjjlb) {
		khywxxFxzdxjjlbService.updateById(khywxxFxzdxjjlb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "巡检记录表-通过id删除")
	@ApiOperation(value="巡检记录表-通过id删除", notes="巡检记录表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khywxxFxzdxjjlbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "巡检记录表-批量删除")
	@ApiOperation(value="巡检记录表-批量删除", notes="巡检记录表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khywxxFxzdxjjlbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "巡检记录表-通过id查询")
	@ApiOperation(value="巡检记录表-通过id查询", notes="巡检记录表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		KhywxxFxzdxjjlb khywxxFxzdxjjlb = khywxxFxzdxjjlbService.getById(id);
		return Result.ok(khywxxFxzdxjjlb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khywxxFxzdxjjlb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KhywxxFxzdxjjlb khywxxFxzdxjjlb) {
      return super.exportXls(request, khywxxFxzdxjjlb, KhywxxFxzdxjjlb.class, "巡检记录表");
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
      return super.importExcel(request, response, KhywxxFxzdxjjlb.class);
  }

}
